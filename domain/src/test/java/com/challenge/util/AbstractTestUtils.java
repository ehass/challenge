package com.challenge.util;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;

import com.challenge.entity.Boundary;
import com.challenge.entity.Property;
import com.challenge.entity.Province;
import com.challenge.entity.ProvinceList;

public class AbstractTestUtils {

	protected ProvinceSearch provinceSearch;
	protected DataFactory dataFactory;
	protected Property property;
	protected ProvinceList spotipposProvinces;
	protected Province province;

	@Before
	public void setUp() {
		dataFactory = EasyMock.createMock(DataFactory.class);

		provinceSearch = new ProvinceSearch();
		property = new Property();
		property.setLatitude(679);
		property.setLongitude(680);

		province = new Province();
		province.setName("Gode");
		Boundary boundary1 = new Boundary();
		boundary1.setBottomRight(new Point(1100, 500));
		boundary1.setUpperLeft(new Point(400, 1000));
		province.setBoundary(boundary1);

		List<Province> provinces = Arrays.asList(province);
		spotipposProvinces = new ProvinceList();
		spotipposProvinces.setProvinces(provinces);
	}
}
