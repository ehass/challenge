package com.challenge.resources;

import java.io.Serializable;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.jboss.resteasy.annotations.GZIP;

import com.challenge.business.PropertyBusiness;
import com.challenge.vo.PropertyVO;
import com.challenge.vo.SearchResultVO;

@Path("/properties")
public class PropertyResource implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(PropertyResource.class);

	@GET
	@GZIP
	@Path("/{id}")
	@Produces({ "application/json; charset=UTF-8" })
	public Response get(@PathParam("id") Long id) {
		try {
			PropertyBusiness business = new PropertyBusiness();
			PropertyVO property = business.find(id);
			if (property == null) {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
			return Response.ok(property).build();
		} catch (Exception e) {
			log.error("Error while finding property by id: " + id);
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}

	@GET
	@GZIP
	@Path("/")
	@Produces({ "application/json; charset=UTF-8" })
	public Response find(@QueryParam("ax") Integer ax, @QueryParam("ay") Integer ay, @QueryParam("bx") Integer bx,
			@QueryParam("by") Integer by) {
		try {
			PropertyBusiness business = new PropertyBusiness();
			SearchResultVO result = business.list(ax, ay, bx, by);
			if (result == null || result.getFoundProperties() == 0) {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
			return Response.ok(result).build();
		} catch (Exception e) {
			log.error("Error while finding property in area defined by [ax, ay, bx, by]: [" + ax + "," + ay + "," + bx
					+ "," + by + ",");
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}

	@POST
	@Path("/")
	@Consumes({ "application/json" })
	@Produces({ "application/json; charset=UTF-8" })
	public Response post(PropertyVO propertyVO) {
		try {
			PropertyBusiness business = new PropertyBusiness();
			PropertyVO addedProperty = business.add(propertyVO);
			if (addedProperty != null) {
				return Response.ok(addedProperty).status(Response.Status.CREATED).build();
			} else {
				return Response.status(Response.Status.BAD_GATEWAY).build();
			}

		} catch (Exception e) {
			log.error("Error while adding a new property");
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
}
