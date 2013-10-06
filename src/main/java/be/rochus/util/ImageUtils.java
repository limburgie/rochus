package be.rochus.util;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {

	public static byte[] resize(byte[] fileData, int maxDimension) throws IOException {
		int height = 0;
		int width = 0;
		ByteArrayInputStream in = new ByteArrayInputStream(fileData);
		BufferedImage img = ImageIO.read(in);
		if(img.getHeight() > img.getWidth()) {
			height = maxDimension;
		} else {
			width = maxDimension;
		}
		if (height == 0) {
			height = (width * img.getHeight()) / img.getWidth();
		}
		if (width == 0) {
			width = (height * img.getWidth()) / img.getHeight();
		}
		Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		ImageIO.write(imageBuff, "jpg", buffer);
		return buffer.toByteArray();
	}
	
}
