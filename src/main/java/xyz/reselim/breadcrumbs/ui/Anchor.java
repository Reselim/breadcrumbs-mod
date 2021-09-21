package xyz.reselim.breadcrumbs.ui;

import gg.essential.elementa.constraints.PositionConstraint;

public enum Anchor {
	TOP_LEFT(AxisAnchor.START, AxisAnchor.START),
	TOP_MIDDLE(AxisAnchor.MIDDLE, AxisAnchor.START),
	TOP_RIGHT(AxisAnchor.END, AxisAnchor.START),

	MIDDLE_LEFT(AxisAnchor.START, AxisAnchor.MIDDLE),
	MIDDLE_MIDDLE(AxisAnchor.MIDDLE, AxisAnchor.MIDDLE),
	MIDDLE_RIGHT(AxisAnchor.END, AxisAnchor.MIDDLE),

	BOTTOM_LEFT(AxisAnchor.START, AxisAnchor.END),
	BOTTOM_MIDDLE(AxisAnchor.MIDDLE, AxisAnchor.END),
	BOTTOM_RIGHT(AxisAnchor.END, AxisAnchor.END);

	public final AxisAnchor x;
	public final AxisAnchor y;

	Anchor(AxisAnchor initX, AxisAnchor initY) {
		x = initX;
		y = initY;
	}

	public PositionConstraint getXConstraint() {
		return x.getConstraint();
	}

	public PositionConstraint getYConstraint() {
		return y.getConstraint();
	}
}
