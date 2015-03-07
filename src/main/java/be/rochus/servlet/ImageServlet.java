package be.rochus.servlet;

import be.rochus.domain.DynamicImage;
import be.rochus.service.DynamicImageService;
import be.rochus.util.BeanLocator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ImageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String key = req.getParameter("key");
		DynamicImage image = BeanLocator.getBean(DynamicImageService.class).getImage(key);
		if (image == null) {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		String etagValue = image.getKey() + image.getModifiedDate().getTime();
		resp.setHeader("ETag", etagValue);
		resp.setHeader("Cache-Control", "max-age=60");

		String checkValue = req.getHeader("If-None-Match");
		if (checkValue != null && checkValue.equals(etagValue)) {
			resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			return;
		}

		resp.getOutputStream().write(image.getContent());
	}

}
