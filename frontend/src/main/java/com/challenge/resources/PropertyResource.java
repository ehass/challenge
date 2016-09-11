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

	private PropertyBusiness business;

	/**
	 * Find a {@link PropertyVO} with {@link id}. <br>
	 * 
	 * @param id
	 * @return {@link PropertyVO} if exists <br>
	 *         HTTP 404 if doesn't exists <br>
	 *         HTTP 503 - INTERNAL SERVER ERROR - if occur an internal error
	 */
	@GET
	@GZIP
	@Path("/{id}")
	@Produces({ "application/json; charset=UTF-8" })
	public Response get(@PathParam("id") Long id) {
		try {
			business = new PropertyBusiness();
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

	/**
	 * Look for all properties inside a region limit
	 * 
	 * @param ax
	 * @param ay
	 * @param bx
	 * @param by
	 * @return a list of properties if exists <br>
	 *         HTTP 404 - NOT FOUND - if there is no properties inside the
	 *         limits <br>
	 *         HTTP 503 - INTERNAL SERVER ERROR - if occurred an internal error
	 */
	@GET
	@GZIP
	@Path("/")
	@Produces({ "application/json; charset=UTF-8" })
	public Response find(@QueryParam("ax") Integer ax, @QueryParam("ay") Integer ay, @QueryParam("bx") Integer bx,
			@QueryParam("by") Integer by) {
		try {
			business = new PropertyBusiness();
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

	/**
	 * Add a new property on database
	 * 
	 * @param propertyVO
	 * @return HTTP 201 - CREATED if it was added successfully <br>
	 *         HTTP 400 - BAD REQUEST - if a required value was not informed
	 *         <br>
	 *         HTTP 503 - INTERNAL SERVER ERROR if occurred an error while
	 *         adding
	 */
	@POST
	@Path("/")
	@Consumes({ "application/json" })
	@Produces({ "application/json; charset=UTF-8" })
	public Response post(PropertyVO propertyVO) {
		try {
			business = new PropertyBusiness();
			PropertyVO addedProperty = business.add(propertyVO);
			if (addedProperty != null) {
				return Response.ok(addedProperty).status(Response.Status.CREATED).build();
			} else {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}

		} catch (Exception e) {
			log.error("Error while adding a new property");
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
}
