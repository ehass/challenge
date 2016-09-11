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
import com.google.gson.Gson;

public abstract class AbstractLoadData implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String PROPERTIES_JSON = "src/main/resources/files/properties.json";
	private static final String PROVINCES_JSON = "src/main/resources/files/provinces.json";
	private static final String ADDED_PROPERTIES_JSON = "src/main/resources/files/addedProperties.json";

	protected DataFactory dataFactory;

	protected Spotippos getSpotippos() throws Exception {
		return loadSpottipos();
	}

	protected void addProperty(Property newProperty) throws Exception {
		PropertyList addedPropertyList = new PropertyList();
		try {
			addedPropertyList = loadAddedProperties();
			List<Property> propertiesList = addedPropertyList.getProperties();
			if (propertiesList == null) {
				propertiesList = new ArrayList<>();
			}
			propertiesList.add(newProperty);
			addedPropertyList.setProperties(propertiesList);
			addedPropertyList.setTotalProperties(propertiesList.size());
		} catch (Exception e) {
		}

		try {
			writeAddedProperties(addedPropertyList);
		} catch (Exception e) {
			throw new Exception("Error while adding new property");
		}
	}

	private Spotippos loadSpottipos() throws Exception {
		try {
			Spotippos spottipos = new Spotippos();
			PropertyList properties = this.loadProperties();
			ProvinceList provinces = this.loadProvinces();

			for (Property property : properties.getProperties()) {
				ProvinceSearch search = new ProvinceSearch();
				property.setProvinces(search.findProvinces(property, provinces));
			}

			spottipos.setProvinces(provinces);
			spottipos.setProperties(properties);
			return spottipos;
		} catch (Exception e) {
			throw e;
		}
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
		PropertyList addedPropertyList = loadAddedProperties();

		propertyList.getProperties().addAll(addedPropertyList.getProperties());

		HashMap<Long, Property> propertyMap = new HashMap<Long, Property>();
		propertyMap.putAll(addedPropertyList.getPropertyMap());

		for (Property p : propertyList.getProperties()) {
			propertyMap.put(p.getId(), p);
		}
		propertyList.setPropertyMap(propertyMap);

		int totalProperties = propertyList.getTotalProperties() + addedPropertyList.getTotalProperties();
		propertyList.setTotalProperties(totalProperties);
		return propertyList;
	}

	private PropertyList loadAddedProperties() {
		PropertyList propertyList = new PropertyList();
		try {
			File addedProperties = new File(ADDED_PROPERTIES_JSON);
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true);

			propertyList = mapper.readValue(addedProperties, PropertyList.class);
			HashMap<Long, Property> propertyMap = new HashMap<>();
			for (Property p : propertyList.getProperties()) {
				propertyMap.put(p.getId(), p);
			}
			propertyList.setPropertyMap(propertyMap);
			propertyList.setTotalProperties(propertyList.getProperties().size());
		} catch (Exception e) {
			propertyList.setProperties(new ArrayList<>());
			propertyList.setPropertyMap(new HashMap<Long, Property>());
			propertyList.setTotalProperties(0);
		}
		return propertyList;
	}

	private void writeAddedProperties(PropertyList addedProperties) throws Exception {
		Writer writer = null;
		try {
			Gson gson = new Gson();
			String jsonString = gson.toJson(addedProperties);
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ADDED_PROPERTIES_JSON), "utf-8"));
			writer.write(jsonString);
		} catch (Exception e1) {
			throw e1;
		} finally {
			writer.close();
		}
	}

}
