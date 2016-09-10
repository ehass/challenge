package com.challenge.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertyList implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("totalProperties")
	private Integer totalProperties;

	private List<Property> properties;

	private HashMap<Long, Property> propertyMap;

	public Integer getTotalProperties() {
		return totalProperties;
	}

	public void setTotalProperties(Integer totalProperties) {
		this.totalProperties = totalProperties;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	public HashMap<Long, Property> getPropertyMap() {
		return propertyMap;
	}

	public void setPropertyMap(HashMap<Long, Property> propertyMap) {
		this.propertyMap = propertyMap;
	}

}
