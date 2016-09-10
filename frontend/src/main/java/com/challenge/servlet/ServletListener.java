package com.challenge.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ServletListener implements ServletContextListener {

	private static final Logger LOGGER = Logger.getLogger(ServletListener.class);

	private void initLogger(ServletContext context) {
		try {
			String fileResource = context.getRealPath("/WEB-INF/log4j.properties");
			InputStream inputStream = new FileInputStream(fileResource);
			Properties properties = new Properties();
			properties.load(inputStream);
			PropertyConfigurator.configure(properties);
			LOGGER.info("Servlet initiated !");
		} catch (IOException ex) {
			System.out.println("Error while initiating application and loading log4j" + ex.getStackTrace());
		} catch (Exception e) {
			LOGGER.error("Error while initiating application.");
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent context) {
		// nothing
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		initLogger(event.getServletContext());
	}

}