package be.rochus.faces.renderer;

import org.primefaces.component.imagecropper.ImageCropper;
import org.primefaces.component.imagecropper.ImageCropperRenderer;
import org.primefaces.model.CroppedImage;

import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.ConverterException;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class CustomImageCropperRenderer extends ImageCropperRenderer {

	protected void encodeScript(FacesContext context, ImageCropper cropper) throws IOException{
		ResponseWriter writer = context.getResponseWriter();
		String widgetVar = cropper.resolveWidgetVar();
		String clientId = cropper.getClientId(context);

		startScript(writer, clientId);

		writer.write("$(PrimeFaces.escapeClientId('" + clientId + "_image')).load(function(){");

		writer.write("PrimeFaces.cw('ImageCropper','" + widgetVar + "',{");
		writer.write("id:'" + clientId + "'");
		writer.write(",image:'" + clientId + "_image'");

		if(cropper.getMinSize() != null) writer.write(",minSize:[" + cropper.getMinSize() + "]");
		if(cropper.getMaxSize() != null) writer.write(",maxSize:[" + cropper.getMaxSize() + "]");
		if(cropper.getBackgroundColor() != null) writer.write(",bgColor:'" + cropper.getBackgroundColor() + "'");
		if(cropper.getBackgroundOpacity() != 0.6) writer.write(",bgOpacity:" + cropper.getBackgroundOpacity());
		if(cropper.getAspectRatio() != Double.MIN_VALUE) writer.write(",aspectRatio:" + cropper.getAspectRatio());

		//Initial crop area
		if(cropper.getValue() != null) {
			CroppedImage croppedImage = (CroppedImage) cropper.getValue();

			int x = croppedImage.getLeft();
			int y = croppedImage.getTop();
			int x2 = x + croppedImage.getWidth();
			int y2 = y + croppedImage.getHeight();

			writer.write(",setSelect:[" + x +  "," + y + "," + x2 + "," + y2 + "]");

		} else if(cropper.getInitialCoords() != null) {
			writer.write(",setSelect:[" + cropper.getInitialCoords() + "]");
		}


		writer.write("},'imagecropper');});");

		endScript(writer);
	}

	@Override
	public Object getConvertedValue(FacesContext context, UIComponent component, Object submittedValue) throws ConverterException {
		String coords = (String) submittedValue;
		if(isValueBlank(coords) || coords.endsWith("_0_0")) {
			return null;
		}

		ImageCropper cropper = (ImageCropper) component;

		//remove query string
		String originalImagePath = cropper.getImage();
		String imagePath = removeQueryString(originalImagePath);

		String[] cropCoords = coords.split("_");
		String format = getFormat(imagePath);

		int x = Integer.parseInt(cropCoords[0]);
		int y = Integer.parseInt(cropCoords[1]);
		int w = Integer.parseInt(cropCoords[2]);
		int h = Integer.parseInt(cropCoords[3]);

		try {
			BufferedImage outputImage = getSourceImage(context, originalImagePath);
			BufferedImage cropped = outputImage.getSubimage(x, y, w, h);

			ByteArrayOutputStream croppedOutImage = new ByteArrayOutputStream();
			ImageIO.write(cropped, format, croppedOutImage);

			return new CroppedImage(cropper.getImage(), croppedOutImage.toByteArray(), x, y, w, h);

		} catch (IOException e) {
			throw new ConverterException(e);
		}
	}

	private String removeQueryString(String url) {
		int queryStringIndex = url.indexOf("?");
		if(queryStringIndex != -1 ) {
			url = url.substring(0, queryStringIndex);
		}
		return url;
	}

	private void renderImage(FacesContext context, ImageCropper cropper, String clientId) throws IOException{
		ResponseWriter writer = context.getResponseWriter();
		String alt = cropper.getAlt() == null ? "" : cropper.getAlt();

		writer.startElement("img", null);
		writer.writeAttribute("id", clientId + "_image", null);
		writer.writeAttribute("alt", alt, null);
		writer.writeAttribute("src", getResourceURL(context, cropper.getImage()), null);
		writer.endElement("img");
	}

	protected String getFormat(String path) {
		String[] pathTokens = path.split("\\.");

		return pathTokens[pathTokens.length - 1];
	}

	protected boolean isExternalImage(ImageCropper cropper) {
		return cropper.getImage().startsWith("http");
	}

	private BufferedImage getSourceImage(FacesContext context, String imagePath) throws IOException {
		BufferedImage outputImage = null;
		boolean isExternal = false;

		ExternalContext externalContext = context.getExternalContext();

		if(!imagePath.startsWith("http")) {

			if(imagePath.startsWith("/")) {
				String contextPath = context.getExternalContext().getRequestContextPath();
				imagePath = "http://"+
						externalContext.getRequestServerName()+":"+
						externalContext.getRequestServerPort() +
						externalContext.getRequestContextPath() + imagePath;
			} else {
				imagePath = "http://"+
						externalContext.getRequestServerName()+":"+
						externalContext.getRequestServerPort() + "/" +
						externalContext.getRequestServletPath() + imagePath;
			}

		}

		HttpSession session = (HttpSession) externalContext.getSession(false);

		URL url = new URL(imagePath);

		HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
		httpConnection.setRequestMethod("GET");
		httpConnection.setRequestProperty("Connection", "Keep-Alive");

		if(session != null) {
			String sessionId = session.getId();
			httpConnection.setRequestProperty("Cookie", "JSESSIONID="+sessionId);
		}

		outputImage =  ImageIO.read(httpConnection.getInputStream());

		return outputImage;
	}

}
