package com.challenge.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class Property implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Long id;

	@JsonProperty("title")
	private String title;

	@JsonProperty("price")
	private Long price;

	@JsonProperty("description")
	private String description;

	@SerializedName("lat")
	@JsonProperty("lat")
	private int latitude;

	@SerializedName("long")
	@JsonProperty("long")
	private int longitude;

	@SerializedName("beds")
	@JsonProperty("beds")
	private int numBeds;

	@SerializedName("baths")
	@JsonProperty("baths")
	private int numBaths;

	@JsonProperty("squareMeters")
	private int squareMeters;

	@JsonProperty("provinces")
	private transient ProvinceList provinces;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}

	public int getNumBeds() {
		return numBeds;
	}

	public void setNumBeds(int numBeds) {
		this.numBeds = numBeds;
	}

	public int getNumBaths() {
		return numBaths;
	}

	public void setNumBaths(int numBaths) {
		this.numBaths = numBaths;
	}

	public int getSquareMeters() {
		return squareMeters;
	}

	public void setSquareMeters(int squareMeters) {
		this.squareMeters = squareMeters;
	}

	public ProvinceList getProvinces() {
		return provinces;
	}

	public void setProvinces(ProvinceList provinces) {
		this.provinces = provinces;
	}
}
