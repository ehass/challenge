package com.challenge.factory;

import java.io.Serializable;

import com.challenge.entity.PropertyList;
import com.challenge.entity.Spotippos;
import com.challenge.util.DataFactory;

public class SearchFactory implements Serializable {

	private static final long serialVersionUID = 1L;
	private DataFactory dataFactory;

	private DataFactory getDataFactory() throws Exception {
		if (this.dataFactory == null) {
			this.dataFactory = new DataFactory();
		}
		return this.dataFactory;
	}

	public Spotippos getSpotippos() throws Exception {
		return this.getDataFactory().getSpotippos();
	}

	public PropertyList getProperties() throws Exception {
		return this.getDataFactory().getSpotippos().getProperties();
	}
}
