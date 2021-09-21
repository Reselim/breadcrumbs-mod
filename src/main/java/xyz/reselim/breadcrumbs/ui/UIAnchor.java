package xyz.reselim.breadcrumbs.ui;

import java.awt.Color;

import gg.essential.elementa.UIComponent;
import gg.essential.elementa.components.UIBlock;
import gg.essential.elementa.components.UIContainer;
import gg.essential.elementa.constraints.AdditiveConstraint;
import gg.essential.elementa.constraints.CenterConstraint;
import gg.essential.elementa.constraints.PixelConstraint;
import gg.essential.elementa.constraints.RelativeConstraint;

public class UIAnchor {
	private static final Color COLOR = new Color(0x009dff);

	public boolean active;
	public final Anchor anchor;

	private final UIContainer container;
	private final UIBlock object;
	private final UIContainer hitbox;

	public UIAnchor(Anchor initAnchor) {
		anchor = initAnchor;

		container = new UIContainer();

		object = new UIBlock();
		object.setX(new CenterConstraint());
		object.setY(new CenterConstraint());
		object.setWidth(new PixelConstraint(5f));
		object.setHeight(new PixelConstraint(5f));
		object.setChildOf(container);

		hitbox = new UIContainer();
		hitbox.setX(new CenterConstraint());
		hitbox.setY(new CenterConstraint());
		hitbox.setWidth(new PixelConstraint(10f));
		hitbox.setHeight(new PixelConstraint(10f));
		hitbox.setChildOf(container);

		setAlpha(0);

		hitbox.onMouseEnterRunnable(new Runnable() {
			public void run() {
				if (active) setAlpha(127);
			}
		});

		hitbox.onMouseLeaveRunnable(new Runnable() {
			public void run() {
				if (active) setAlpha(0);
			}
		});
	}

	private void setAlpha(int alpha) {
		Color color = new Color(
			COLOR.getRed(),
			COLOR.getGreen(),
			COLOR.getBlue(),
			alpha
		);

		object.setColor(color);
	}

	public void setActive(boolean shouldBeActive) {
		active = shouldBeActive;
		setAlpha(255);
	}

	public void setPosition(float x, float y) {
		container.setX(new AdditiveConstraint(new RelativeConstraint(x), AxisAnchor.MIDDLE.getConstraint()));
		container.setY(new AdditiveConstraint(new RelativeConstraint(y), AxisAnchor.MIDDLE.getConstraint()));
	}

	public void setChildOf(UIComponent component) {
		container.setChildOf(component);
	}
}
