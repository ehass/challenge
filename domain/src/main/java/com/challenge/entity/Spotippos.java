package com.challenge.entity;

import java.io.Serializable;

public class Spotippos implements Serializable {

	private static final long serialVersionUID = 1L;
	private ProvinceList provinces;
	private PropertyList properties;

	public ProvinceList getProvinces() {
		return provinces;
	}

	public void setProvinces(ProvinceList provinces) {
		this.provinces = provinces;
	}

	public PropertyList getProperties() {
		return properties;
	}

	public void setProperties(PropertyList properties) {
		this.properties = properties;
	}

}
