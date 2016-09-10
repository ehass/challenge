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

	public static PropertyVO getPropertyVO(String description) throws Exception {
		Property property = new Property();
		property.setDescription(description);
		property.setId(999L);
		property.setLatitude(99);
		property.setLongitude(999);
		property.setNumBaths(3);
		property.setNumBeds(4);
		property.setPrice(500000L);
		property.setSquareMeters(129);
		property.setTitle("Property Mock Title");

		Province province1 = new Province();
		province1.setName("Province 1");
		Boundary boundary1 = new Boundary();
		boundary1.setBottomRight(new Point(10, 20));
		boundary1.setUpperLeft(new Point(10, 20));
		province1.setBoundary(boundary1);

		Province province2 = new Province();
		province2.setName("Province 2");
		Boundary boundary2 = new Boundary();
		boundary2.setBottomRight(new Point(10, 20));
		boundary2.setUpperLeft(new Point(10, 20));
		province2.setBoundary(boundary2);

		List<Province> provinces = Arrays.asList(province1, province2);
		ProvinceList pList = new ProvinceList();
		pList.setProvinces(provinces);
		property.setProvinces(pList);

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
}
