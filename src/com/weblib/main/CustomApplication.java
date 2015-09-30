package com.weblib.main;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/*")
public class CustomApplication extends ResourceConfig {
    
	public CustomApplication() {
        packages("org.glassfish.jersey.examples.linking");
        register(DeclarativeLinkingFeature.class);
    }
	
}
