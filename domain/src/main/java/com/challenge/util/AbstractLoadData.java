package com.challenge.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.challenge.entity.Property;
import com.challenge.entity.PropertyList;
import com.challenge.entity.Province;
import com.challenge.entity.ProvinceList;
import com.challenge.entity.Spotippos;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractLoadData implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String PROPERTIES_JSON = "src/main/resources/files/properties.json";
	private static final String PROVINCES_JSON = "src/main/resources/files/provinces.json";
	private static final String ADDED_PROPERTIES_JSON = "src/main/resources/files/addedProperties.json";

	protected DataFactory dataFactory;
	protected Spotippos spotippos;
	protected PropertyList properties;
	protected ProvinceList provinces;

	protected Spotippos getSpotippos() throws Exception {
		if (spotippos == null) {
			this.spotippos = loadSpottipos();
		}
		return this.spotippos;
	}

	protected Spotippos loadSpottipos() throws Exception {
		try {
			Spotippos spottipos = new Spotippos();

			for (Property property : this.getProperties().getProperties()) {
				ProvinceSearch search = new ProvinceSearch();
				property.setProvinces(search.findProvinces(property, this.getProvinces()));
			}

			spottipos.setProvinces(this.getProvinces());
			spottipos.setProperties(this.getProperties());
			return spottipos;
		} catch (Exception e) {
			throw e;
		}
	}

	private PropertyList getProperties() throws Exception {
		if (this.properties == null) {
			this.properties = loadProperties();
		}
		return this.properties;
	}

	private ProvinceList getProvinces() throws Exception {
		if (this.provinces == null) {
			this.provinces = loadProvinces();
		}
		return this.provinces;
	}

	private ProvinceList loadProvinces() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Province> provinceMap = mapper.readValue(new File(PROVINCES_JSON), new TypeReference<Map<String, Province>>() {
		});

		List<Province> provinces = new ArrayList<Province>();
		for (String provinceName : provinceMap.keySet()) {
			Province province = provinceMap.get(provinceName);
			province.setName(provinceName);
			provinces.add(province);
		}

		ProvinceList provinceList = new ProvinceList();
		provinceList.setProvinces(provinces);
		return provinceList;
	}

	private PropertyList loadProperties() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		PropertyList propertyList = mapper.readValue(new File(PROPERTIES_JSON), PropertyList.class);
		HashMap<Long, Property> propertyMap = new HashMap<Long, Property>();
		for (Property p : propertyList.getProperties()) {
			propertyMap.put(p.getId(), p);
		}
		propertyList.setPropertyMap(propertyMap);
		// propertyMap.putAll(loadAddedProperties());
		return propertyList;
	}

	private HashMap<Long, Property> loadAddedProperties() throws Exception {
		HashMap<Long, Property> propertyMap = new HashMap<Long, Property>();
		File addedProperties = new File(ADDED_PROPERTIES_JSON);
		if (addedProperties.exists()) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true);

			PropertyList propertyList = mapper.readValue(addedProperties, PropertyList.class);
			for (Property p : propertyList.getProperties()) {
				propertyMap.put(p.getId(), p);
			}
		} else {
			Writer writer = null;
			try {
				writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ADDED_PROPERTIES_JSON), "utf-8"));
				writer.write("{}");
				;
			} catch (Exception e1) {
				throw e1;
			} finally {
				writer.close();
			}
		}
		return propertyMap;
	}
}
