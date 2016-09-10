package com.challenge.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.challenge.entity.Property;
import com.challenge.entity.PropertyList;
import com.challenge.utils.PropertyParser;
import com.challenge.utils.ValidatorUtils;
import com.challenge.vo.PropertyVO;
import com.challenge.vo.SearchResultVO;

public abstract class AbstractPropertyBusiness implements Serializable {

	private static final long serialVersionUID = 1L;

	protected SearchResultVO findMatchProperties(PropertyList properties, int ax, int ay, int bx, int by) throws Exception {

		List<PropertyVO> matchProperties = match(properties, ax, ay, bx, by);
		SearchResultVO vo = new SearchResultVO();
		vo.setProperties(matchProperties);
		vo.setFoundProperties(matchProperties.size());
		return vo;
	}

	private List<PropertyVO> match(PropertyList propertyList, int ax, int ay, int bx, int by) throws Exception {
		HashMap<Long, Property> properties = propertyList.getPropertyMap();
		List<PropertyVO> matches = new ArrayList<PropertyVO>();
		for (Property p : properties.values()) {
			if (ValidatorUtils.isBetween(p.getLatitude(), ax, bx) && ValidatorUtils.isBetween(p.getLongitude(), ay, by)) {

				matches.add(PropertyParser.converTo(p));
			}
		}
		return matches;
	}
}
