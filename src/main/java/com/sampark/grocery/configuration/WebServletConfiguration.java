package com.sampark.grocery.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebServletConfiguration implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) {

		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(SpringServletConfigs.class,DatabaseConfig.class);
		ctx.setServletContext(container);

		ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));

		servlet.setLoadOnStartup(1);
		/*servlet.addMapping("/web/*");*/
		servlet.addMapping("/rest/*");
		servlet.addMapping("/");

		//servlet.addMapping("/form/");
		//servlet.addMapping("/mobile/");
		//servlet.addMapping("/rest/");
		servlet.setLoadOnStartup(1);

	}
}
