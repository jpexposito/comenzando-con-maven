package es.formacion.cip.ejemplo4.servlet3;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import es.formacion.cip.ejemplo4.config.SpringRootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import es.formacion.cip.ejemplo4.config.SpringWebConfig;

public class MyWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SpringRootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringWebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		servletContext.setInitParameter("spring.profiles.active", "hsql");
	}

}