package com.challenge.util;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.challenge.entity.Property;
import com.challenge.entity.Province;
import com.challenge.entity.ProvinceList;

public class ProvinceSearch implements Serializable {

	private static final long serialVersionUID = 1L;

	public ProvinceList findProvinces(Property p, ProvinceList spotipposProvinces) {
		List<Province> provinces = new ArrayList<Province>();
		for (Province province : spotipposProvinces.getProvinces()) {
			if (insideOf(new Point(p.getLatitude(), p.getLongitude()), province)) {
				provinces.add(province);
			}
		}
		ProvinceList propertyProvinces = new ProvinceList();
		propertyProvinces.setProvinces(provinces);
		return propertyProvinces;
	}

	private boolean insideOf(Point property, Province province) {

		if (property.getX() < province.getBoundary().getUpperLeft().getX() || property.getX() > province.getBoundary().getBottomRight().getX()) {
			return false;
		}

		if (property.getY() < province.getBoundary().getBottomRight().getY() || property.getY() > province.getBoundary().getUpperLeft().getY()) {
			return false;
		}
		return true;
	}
}
