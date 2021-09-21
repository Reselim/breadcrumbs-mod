package xyz.reselim.breadcrumbs.ui;

import java.util.ArrayList;
import java.util.List;

import gg.essential.elementa.components.UIContainer;
import gg.essential.elementa.components.Window;
import gg.essential.elementa.constraints.RelativeWindowConstraint;
import xyz.reselim.breadcrumbs.config.Config;
import xyz.reselim.breadcrumbs.config.ConfigGroup;

public final class UIBreadcrumbWindow {
	private List<UIBreadcrumbGroup> groups = new ArrayList<>();

	private final Window window;
	public final UIContainer object;

	private UIBreadcrumbWindow() {
		window = new Window();

		object = new UIContainer();
		object.setWidth(new RelativeWindowConstraint(1f));
		object.setHeight(new RelativeWindowConstraint(1f));
		object.setChildOf(window);
	}

	public void add(UIBreadcrumbGroup group) {
		groups.add(group);
		group.object.setChildOf(object);
	}

	public void remove(UIBreadcrumbGroup group) {
		groups.remove(group);
		group.object.setChildOf(null);
	}

	public void draw() {
		window.draw();
	}

	// Config

	public static final UIBreadcrumbWindow fromConfig(Config config) {
		UIBreadcrumbWindow window = new UIBreadcrumbWindow();

		for (ConfigGroup groupConfig : config.groups) {
			UIBreadcrumbGroup group = UIBreadcrumbGroup.fromConfig(groupConfig);
			window.add(group);
		}

		return window;
	}

	public Config toConfig() {
		Config config = new Config();

		for (UIBreadcrumbGroup group : groups) {
			config.groups.add(group.toConfig());
		}

		return config;
	}
}
