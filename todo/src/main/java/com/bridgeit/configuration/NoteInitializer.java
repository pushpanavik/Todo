package com.bridgeit.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Configuration
public class NoteInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		System.out.println("Working");
		return new Class[] { BeanConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {

		return new Class[] { NoteConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {

		return new String[] { "/" };
	}

}
