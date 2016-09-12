package com.challenge.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidatorUtilsTest {

	@Test
	public void testIsValidString() {
		assertTrue(ValidatorUtils.isValidString("string"));
		assertFalse(ValidatorUtils.isValidString(""));
		assertFalse(ValidatorUtils.isValidString(null));
	}

	@Test
	public void testIsBetween() {
		assertTrue(ValidatorUtils.isBetween(10, 5, 20));
		assertFalse(ValidatorUtils.isBetween(10, 15, 20));
		assertFalse(ValidatorUtils.isBetween(10, 5, 9));
	}
}
