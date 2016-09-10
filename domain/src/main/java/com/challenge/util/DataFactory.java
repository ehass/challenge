package com.challenge.util;

import com.challenge.entity.Spotippos;

public class DataFactory extends AbstractLoadData {
	private static final long serialVersionUID = 1L;

	public DataFactory() throws Exception {
		this.spotippos = this.loadSpottipos();
	}

	public Spotippos getSpotippos() throws Exception {
		if (spotippos == null) {
			this.spotippos = loadSpottipos();
		}
		return this.spotippos;
	}
}
