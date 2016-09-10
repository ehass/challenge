package com.challenge.entity;

import java.awt.Point;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Boundary implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("upperLeft")
	private Point upperLeft;

	@JsonProperty("bottomRight")
	private Point bottomRight;

	public Point getUpperLeft() {
		return upperLeft;
	}

	public void setUpperLeft(Point upperLeft) {
		this.upperLeft = upperLeft;
	}

	public Point getBottomRight() {
		return bottomRight;
	}

	public void setBottomRight(Point bottomRight) {
		this.bottomRight = bottomRight;
	}
}
