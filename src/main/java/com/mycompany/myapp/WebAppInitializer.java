package com.mycompany.myapp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
		annotationConfigWebApplicationContext.setConfigLocation("com.mycompany.myapp.config");
		servletContext.addListener(new ContextLoaderListener(annotationConfigWebApplicationContext));
		Dynamic dynamic = servletContext.addServlet("appServlet", new DispatcherServlet(annotationConfigWebApplicationContext));
		dynamic.setLoadOnStartup(1);
		dynamic.addMapping("/");
	}

}
