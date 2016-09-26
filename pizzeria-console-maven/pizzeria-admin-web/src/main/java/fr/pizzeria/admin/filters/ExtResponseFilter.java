package fr.pizzeria.admin.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class ExtResponseFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext req, ContainerResponseContext resp) throws IOException {

		resp.getHeaders().add("Access-Control-Allow-Origin", "*");
		resp.getHeaders().add("Access-Control-Allow-Headers", "*");
		resp.getHeaders().add("Access-Control-Allow-Methods", "GET");
	}

}
