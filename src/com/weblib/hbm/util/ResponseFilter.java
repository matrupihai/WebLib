package com.weblib.hbm.util;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;


public class ResponseFilter implements ContainerResponseFilter {

	@Override
	public ContainerResponse filter(ContainerRequest request,
			ContainerResponse response) {
		response.getHttpHeaders().putSingle("Access-Control-Allow-Origin", "*");
		
		return response;
	}
}
