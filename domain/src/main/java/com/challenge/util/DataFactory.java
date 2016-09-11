package com.challenge.util;

import com.challenge.entity.Property;
import com.challenge.entity.PropertyList;
import com.challenge.entity.ProvinceList;
import com.challenge.entity.Spotippos;

public class DataFactory extends AbstractLoadData {
	private static final long serialVersionUID = 1L;

	public Spotippos getSpotippos() throws Exception {
		return super.getSpotippos();
	}
	
	public PropertyList getProperties() throws Exception {
		return super.getSpotippos().getProperties();
	}
	
	public ProvinceList getProvinces() throws Exception {
		return super.getSpotippos().getProvinces();
	}

	public void addProperty(Property property) throws Exception {
		super.addProperty(property);
	}
}
