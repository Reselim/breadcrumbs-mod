package xyz.reselim.breadcrumbs.ui;

import java.util.ArrayList;
import java.util.List;

import gg.essential.elementa.components.UIContainer;
import gg.essential.elementa.constraints.AdditiveConstraint;
import gg.essential.elementa.constraints.CenterConstraint;
import gg.essential.elementa.constraints.ChildBasedSizeConstraint;
import gg.essential.elementa.constraints.PixelConstraint;
import gg.essential.elementa.constraints.RelativeConstraint;
import gg.essential.elementa.constraints.SiblingConstraint;
import xyz.reselim.breadcrumbs.config.ConfigBreadcrumb;
import xyz.reselim.breadcrumbs.config.ConfigGroup;

public final class UIBreadcrumbGroup {
	private static final float SPACING = 2f;
	private static final float PADDING = 4f;

	private float x = 0;
	private float y = 0;
	private Anchor anchor = Anchor.TOP_LEFT;
	private List<UIBreadcrumb> breadcrumbs = new ArrayList<>();

	public final UIContainer object;
	private final UIContainer container;

	private UIBreadcrumbGroup() {
		object = new UIContainer();
		object.setWidth(new AdditiveConstraint(new ChildBasedSizeConstraint(), new PixelConstraint(PADDING)));
		object.setHeight(new AdditiveConstraint(new ChildBasedSizeConstraint(), new PixelConstraint(PADDING)));

		container = new UIContainer();
		container.setX(new CenterConstraint());
		container.setY(new CenterConstraint());
		container.setWidth(new ChildBasedSizeConstraint(SPACING));
		container.setHeight(new PixelConstraint(16f));
		container.setChildOf(object);
	}

	public void add(UIBreadcrumb breadcrumb) {
		breadcrumb.object.setChildOf(container);
		breadcrumb.object.setX(new SiblingConstraint(SPACING));
	}

	// Config

	public static final UIBreadcrumbGroup fromConfig(ConfigGroup groupConfig) {
		UIBreadcrumbGroup group = new UIBreadcrumbGroup();

		group.setPosition((float) groupConfig.x, (float) groupConfig.y);
		group.setAnchor(groupConfig.anchor);

		for (ConfigBreadcrumb breadcrumbConfig : groupConfig.breadcrumbs) {
			UIBreadcrumb breadcrumb = UIBreadcrumb.fromConfig(breadcrumbConfig);
			group.add(breadcrumb);
		}

		return group;
	}

	public ConfigGroup toConfig() {
		ConfigGroup config = new ConfigGroup();

		config.x = (double) x;
		config.y = (double) y;
		config.anchor = anchor;

		for (UIBreadcrumb breadcrumb : breadcrumbs) {
			config.breadcrumbs.add(breadcrumb.toConfig());
		}

		return config;
	}

	// Positioning

	private void updatePosition() {
		object.setX(new AdditiveConstraint(new RelativeConstraint(x), new PixelConstraint(0f, false, anchor.flipX)));
		object.setY(new AdditiveConstraint(new RelativeConstraint(y), new PixelConstraint(0f, false, anchor.flipY)));
	}

	public void setAnchor(Anchor newAnchor) {
		anchor = newAnchor;
		updatePosition();
	}

	public void setPosition(float newX, float newY) {
		x = newX;
		y = newY;
		System.out.println(newX);
		System.out.println(newY);
		updatePosition();
	}

	public enum Anchor {
		TOP_LEFT(false, false),
		TOP_RIGHT(true, false),
		BOTTOM_LEFT(false, true),
		BOTTOM_RIGHT(true, true);

		public boolean flipX;
		public boolean flipY;

		Anchor(boolean flipX, boolean flipY) {
			this.flipX = flipX;
			this.flipY = flipY;
		}
	}
}
