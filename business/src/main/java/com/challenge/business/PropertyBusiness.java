package com.challenge.business;

import java.util.HashMap;
import java.util.List;

import com.challenge.entity.Property;
import com.challenge.entity.PropertyList;
import com.challenge.factory.SearchFactory;
import com.challenge.utils.PropertyParser;
import com.challenge.utils.ValidatorUtils;
import com.challenge.vo.PropertyVO;
import com.challenge.vo.SearchResultVO;

public class PropertyBusiness extends AbstractPropertyBusiness {
	private static final long serialVersionUID = 1L;
	private static final int MAX_AXIS_X = 1400;
	private static final int MAX_AXYS_Y = 1000;

	/**
	 * Find property by id
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PropertyVO find(Long id) throws Exception {
		SearchFactory searchFactory = new SearchFactory();
		Property property = searchFactory.getProperties().getPropertyMap().get(id);
		return PropertyParser.converTo(property);
	}

	/**
	 * Add new property to database
	 * 
	 * @param propertyVO
	 * @return
	 * @throws Exception
	 */
	public PropertyVO add(PropertyVO propertyVO) throws Exception {
		if (validatePropertyRestrictions(propertyVO)) {

			SearchFactory searchFactory = new SearchFactory();
			PropertyList properties = searchFactory.getProperties();
			Long newId = new Long(properties.getTotalProperties() + 1);
			Property property = PropertyParser.convertTo(propertyVO, searchFactory.getSpotippos().getProvinces());
			property.setId(newId);

			List<Property> pList = properties.getProperties();
			pList.add(property);

			HashMap<Long, Property> propertyMap = properties.getPropertyMap();
			propertyMap.put(newId, property);

			return PropertyParser.converTo(property);
		}
		return null;
	}

	/**
	 * Find all match properties in a specific area
	 * 
	 * @param ax
	 * @param ay
	 * @param bx
	 * @param by
	 * @return
	 * @throws Exception
	 */
	public SearchResultVO list(int ax, int ay, int bx, int by) throws Exception {
		SearchFactory searchFactory = new SearchFactory();
		PropertyList propertyList = searchFactory.getProperties();
		return findMatchProperties(propertyList, ax, ay, bx, by);
	}

	/**
	 * Validate required attributes from view object
	 * 
	 * @param property
	 * @return
	 */
	private boolean validatePropertyRestrictions(PropertyVO propertyVO) {
		return ValidatorUtils.isBetween(propertyVO.getX(), 0, MAX_AXIS_X) 
				&& ValidatorUtils.isBetween(propertyVO.getY(), 0, MAX_AXYS_Y) 
				&& ValidatorUtils.isBetween(propertyVO.getBeds(), 1, 5)
				&& ValidatorUtils.isBetween(propertyVO.getBaths(), 1, 4) 
				&& ValidatorUtils.isBetween(propertyVO.getSquareMeters(), 20, 240);
	}
}
