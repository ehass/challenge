package com.challenge.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProvinceList implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("provinces")
	private List<Province> provinces;

	public ProvinceList() {
	}

	public ProvinceList(List<Province> provinces) {
		this.provinces = provinces;
	}

	public List<Province> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<Province> provinces) {
		this.provinces = provinces;
	}
}
