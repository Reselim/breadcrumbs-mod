package xyz.reselim.breadcrumbs.ui;

import gg.essential.elementa.constraints.AdditiveConstraint;
import gg.essential.elementa.constraints.CenterConstraint;
import gg.essential.elementa.constraints.PixelConstraint;
import gg.essential.elementa.constraints.PositionConstraint;
import gg.essential.elementa.constraints.RelativeConstraint;

public enum AxisAnchor {
	START(0f),
	MIDDLE(0.5f),
	END(1f);

	public final float value;

	AxisAnchor(float initValue) {
		value = initValue;
	}

	public PositionConstraint getConstraint() {
		switch (this) {
			case START:
				return new PixelConstraint(0f, false, false);
			case MIDDLE:
				return new AdditiveConstraint(new CenterConstraint(), new RelativeConstraint(-0.5f));
			case END:
				return new PixelConstraint(0f, false, true);
			default:
				throw new RuntimeException();
		}
	}
}
