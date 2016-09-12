package com.challenge.utils;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;

import com.challenge.entity.Boundary;
import com.challenge.entity.Property;
import com.challenge.entity.Province;
import com.challenge.entity.ProvinceList;
import com.challenge.vo.PropertyVO;
import com.challenge.vo.SearchResultVO;

public class TestUtils {

	public static Property getProperty(String description) {
		Property property = new Property();
		property.setDescription(description);
		property.setId(1L);
		property.setLatitude(679);
		property.setLongitude(680);
		property.setNumBaths(3);
		property.setNumBeds(4);
		property.setPrice(500000L);
		property.setSquareMeters(129);
		property.setTitle("Property Mock Title");

		Province province1 = new Province();
		province1.setName("Gode");
		Boundary boundary1 = new Boundary();
		boundary1.setBottomRight(new Point(1100, 500));
		boundary1.setUpperLeft(new Point(400, 1000));
		province1.setBoundary(boundary1);

		List<Province> provinces = Arrays.asList(province1);
		ProvinceList pList = new ProvinceList();
		pList.setProvinces(provinces);
		property.setProvinces(pList);
		return property;
	}

	public static PropertyVO getPropertyVO(String description) throws Exception {
		Property property = getProperty(description);
		return PropertyParser.converTo(property);
	}

	public static SearchResultVO getSearchResultVO() throws Exception {

		PropertyVO propertyVO1 = getPropertyVO("Property Description 1");
		PropertyVO propertyVO2 = getPropertyVO("Property Description 2");

		SearchResultVO vo = new SearchResultVO();
		vo.setProperties(Arrays.asList(propertyVO1, propertyVO2));
		vo.setFoundProperties(vo.getProperties().size());
		return vo;
	}

	public static ProvinceList getProvinces() {
		Province province = new Province();
		province = new Province();
		province.setName("Gode");
		Boundary boundary1 = new Boundary();
		boundary1.setBottomRight(new Point(1100, 500));
		boundary1.setUpperLeft(new Point(400, 1000));
		province.setBoundary(boundary1);

		List<Province> provinces = Arrays.asList(province);
		ProvinceList provinceList = new ProvinceList();
		provinceList.setProvinces(provinces);

		return provinceList;
	}
}
