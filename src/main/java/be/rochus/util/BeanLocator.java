package be.rochus.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.inject.Named;

@Named
public class BeanLocator implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public static <T> T getBean(Class<T> beanClass) {
		return applicationContext.getBean(beanClass);
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
