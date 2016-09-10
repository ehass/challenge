package com.challenge.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Province implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	@JsonProperty("boundaries")
	private Boundary boundary;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boundary getBoundary() {
		return boundary;
	}

	public void setBoundary(Boundary boundary) {
		this.boundary = boundary;
	}

}
