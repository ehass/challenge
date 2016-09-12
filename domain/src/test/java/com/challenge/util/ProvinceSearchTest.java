package com.challenge.util;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.challenge.entity.ProvinceList;

public class ProvinceSearchTest extends AbstractTestUtils {

	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void testFindProvinceSuccess() {
		try {
			EasyMock.expect(dataFactory.getProvinces()).andReturn(spotipposProvinces);
			EasyMock.replay(dataFactory);

			ProvinceList provinces = provinceSearch.findProvinces(property, spotipposProvinces);

			assertTrue("Found province list.", provinces != null);
			assertTrue("Found one province", provinces.getProvinces().size() == 1);
			assertTrue("Found correct province", provinces.getProvinces().get(0).getName().equals(province.getName()));

		} catch (Exception e) {
			fail("This test must not have error.");
		}
	}

	@Test
	public void testNotFoundProvince() {
		try {
			EasyMock.expect(dataFactory.getProvinces()).andReturn(spotipposProvinces);
			EasyMock.replay(dataFactory);
			property.setLongitude(9999);
			ProvinceList provinces = provinceSearch.findProvinces(property, spotipposProvinces);

			assertTrue("Found province list.", provinces != null);
			assertTrue("Found no provinces", provinces.getProvinces().isEmpty());

		} catch (Exception e) {
			fail("This test must not have error.");
		}
	}

}
