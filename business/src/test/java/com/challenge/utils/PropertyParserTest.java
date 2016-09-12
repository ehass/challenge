package com.challenge.utils;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.challenge.entity.Boundary;
import com.challenge.entity.Property;
import com.challenge.entity.Province;
import com.challenge.vo.PropertyVO;

public class PropertyParserTest {

	private Property property;
	private PropertyVO propertyVO;

	@Before
	public void setUp() throws Exception {
		property = TestUtils.getProperty("description");
		propertyVO = TestUtils.getPropertyVO("description");
	}

	@Test
	public void testConvertPropertyToPropertyVO() {
		try {
			PropertyVO vo = PropertyParser.converTo(property);
			assertTrue(vo.getBaths().equals(propertyVO.getBaths()));
			assertTrue(vo.getBeds().equals(propertyVO.getBeds()));
			assertTrue(vo.getDescription().equals(propertyVO.getDescription()));
			assertTrue(vo.getId().equals(propertyVO.getId()));
			assertTrue(vo.getPrice().equals(propertyVO.getPrice()));
			assertTrue(vo.getSquareMeters().equals(propertyVO.getSquareMeters()));
			assertTrue(vo.getTitle().equals(propertyVO.getTitle()));
			assertTrue(vo.getX().equals(propertyVO.getX()));
			assertTrue(vo.getY().equals(propertyVO.getY()));
			assertTrue(vo.getProvinces() != null);
			assertTrue(vo.getProvinces().size() == 1);
			assertTrue(vo.getProvinces().get(0).equals(propertyVO.getProvinces().get(0)));

		} catch (Exception e) {
			fail("This test must not have error.");
		}
	}

	@Test
	public void testConvertPropertyVOtoProperty() {
		try {
			Property p = PropertyParser.convertTo(propertyVO, TestUtils.getProvinces());

			assertTrue(p.getDescription().equals(property.getDescription()));
			assertTrue(p.getLatitude() == property.getLatitude());
			assertTrue(p.getLongitude() == property.getLongitude());
			assertTrue(p.getNumBaths() == property.getNumBaths());
			assertTrue(p.getNumBeds() == property.getNumBeds());
			assertTrue(p.getPrice().equals(property.getPrice()));
			assertTrue(p.getSquareMeters() == property.getSquareMeters());
			assertTrue(p.getTitle().equals(property.getTitle()));
			assertTrue(p.getProvinces() != null);
			assertTrue(p.getProvinces().getProvinces() != null);
			assertTrue(p.getProvinces().getProvinces().size() == 1);

			Province province = p.getProvinces().getProvinces().get(0);
			assertTrue(province != null);
			assertTrue(province.getName().equals(property.getProvinces().getProvinces().get(0).getName()));
			
			Boundary boundary = province.getBoundary();
			assertTrue(boundary != null);
			assertTrue(boundary.getBottomRight().equals(property.getProvinces().getProvinces().get(0).getBoundary().getBottomRight()));
			assertTrue(boundary.getUpperLeft().equals(property.getProvinces().getProvinces().get(0).getBoundary().getUpperLeft()));

		} catch (Exception e) {
			fail("This test must not have error.");
		}
	}
}
