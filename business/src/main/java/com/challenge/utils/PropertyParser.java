package com.challenge.utils;

import java.util.ArrayList;
import java.util.List;

import com.challenge.entity.Property;
import com.challenge.entity.Province;
import com.challenge.entity.ProvinceList;
import com.challenge.util.ProvinceSearch;
import com.challenge.vo.PropertyVO;

public class PropertyParser {

	/**
	 * Convert Property view object to Property <br>
	 * Required value validation is implemented because jackson annotation
	 * (required = true) doesn't work on version applied.
	 * 
	 * @param vo
	 * @param spotipposProvinceList
	 * @return
	 * @throws Exception
	 */
	public static Property convertTo(PropertyVO vo, ProvinceList spotipposProvinceList) throws Exception {

		Property p = new Property();
		try {
			if (ValidatorUtils.isValidString(vo.getTitle())) {
				p.setTitle(vo.getTitle());
			} else {
				throw new Exception("title is required");
			}

			if (ValidatorUtils.isValidString(vo.getDescription())) {
				p.setDescription(vo.getDescription());
			} else {
				throw new Exception("description is required");
			}

			if (vo.getX() != null) {
				p.setLatitude(vo.getX());
			} else {
				throw new Exception("latitude is required");
			}

			if (vo.getY() != null) {
				p.setLongitude(vo.getY());
			} else {
				throw new Exception("longitude is required");
			}

			if (vo.getBaths() != null) {
				p.setNumBaths(vo.getBaths());
			} else {
				throw new Exception("baths is required");
			}

			if (vo.getBeds() != null) {
				p.setNumBeds(vo.getBeds());
			} else {
				throw new Exception("beds is required");
			}

			if (vo.getPrice() != null) {
				p.setPrice(vo.getPrice());
			} else {
				throw new Exception("price is required");
			}

			if (vo.getSquareMeters() != null) {
				p.setSquareMeters(vo.getSquareMeters());
			} else {
				throw new Exception("squareMeters is required");
			}

			ProvinceSearch provinceSearch = new ProvinceSearch();
			p.setProvinces(provinceSearch.findProvinces(p, spotipposProvinceList));

		} catch (Exception e) {
			throw new Exception("Error while converting PropertyVO to Property", e);
		}
		return p;
	}

	public static PropertyVO converTo(Property property) throws Exception {
		PropertyVO vo = new PropertyVO();

		try {
			if (property.getId() != null) {
				vo.setId(property.getId());
			} else {
				throw new Exception("id is required");
			}

			if (ValidatorUtils.isValidString(property.getTitle())) {
				vo.setTitle(property.getTitle());
			} else {
				throw new Exception("title is required");
			}

			if (ValidatorUtils.isValidString(property.getDescription())) {
				vo.setDescription(property.getDescription());
			} else {
				throw new Exception("description is required");
			}

			vo.setX(property.getLatitude());
			vo.setY(property.getLongitude());
			vo.setBaths(property.getNumBaths());
			vo.setBeds(property.getNumBeds());
			vo.setSquareMeters(property.getSquareMeters());

			if (property.getPrice() != null) {
				vo.setPrice(property.getPrice());
			} else {
				throw new Exception("price is required");
			}

			ProvinceList provinces = property.getProvinces();
			if (provinces != null && !provinces.getProvinces().isEmpty()) {
				List<String> provincesNames = new ArrayList<String>();
				for (Province p : provinces.getProvinces()) {
					provincesNames.add(p.getName());
				}
				vo.setProvinces(provincesNames);
			}

		} catch (Exception e) {
			throw new Exception("Error while converting Property to PropertyVO", e);
		}

		return vo;
	}
}
