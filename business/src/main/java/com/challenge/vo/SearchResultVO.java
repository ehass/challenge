package com.challenge.vo;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

public class SearchResultVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private int foundProperties;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private List<PropertyVO> properties;

	public int getFoundProperties() {
		return foundProperties;
	}

	public void setFoundProperties(int foundProperties) {
		this.foundProperties = foundProperties;
	}

	public List<PropertyVO> getProperties() {
		return properties;
	}

	public void setProperties(List<PropertyVO> properties) {
		this.properties = properties;
	}

}
