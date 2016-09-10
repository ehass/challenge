package com.challenge.utils;

public class ValidatorUtils {

	public static boolean isValidString(String value) {
		if (value != null && !value.trim().isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isBetween(int value, int min, int max) {
		if (value >= min && value <= max) {
			return true;
		}
		return false;
	}
}
