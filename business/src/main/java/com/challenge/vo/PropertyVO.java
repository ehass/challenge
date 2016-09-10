package com.challenge.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement(name = "property")
public class PropertyVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Long id;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String title;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Long price;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String description;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Integer x;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Integer y;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Integer beds;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Integer baths;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Integer squareMeters;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private List<String> provinces;

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

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getBeds() {
		return beds;
	}

	public void setBeds(Integer beds) {
		this.beds = beds;
	}

	public Integer getBaths() {
		return baths;
	}

	public void setBaths(Integer baths) {
		this.baths = baths;
	}

	public Integer getSquareMeters() {
		return squareMeters;
	}

	public void setSquareMeters(Integer squareMeters) {
		this.squareMeters = squareMeters;
	}

	public List<String> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<String> provinces) {
		this.provinces = provinces;
	}

}
