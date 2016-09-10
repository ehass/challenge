package com.challenge.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.challenge.resources.PropertyResource;

public class ApplicationResource extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		resources(s);
		return s;
	}

	private void resources(Set<Class<?>> s) {
		s.add(PropertyResource.class);
	}
}
