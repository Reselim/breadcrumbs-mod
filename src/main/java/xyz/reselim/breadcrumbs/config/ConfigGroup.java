package xyz.reselim.breadcrumbs.config;

import java.util.ArrayList;
import java.util.List;

import com.electronwill.nightconfig.core.conversion.PreserveNotNull;
import com.electronwill.nightconfig.core.conversion.SpecDoubleInRange;

import xyz.reselim.breadcrumbs.ui.Anchor;

public final class ConfigGroup {
	@SpecDoubleInRange(min = 0, max = 1)
	@PreserveNotNull
	public double x = 1;

	@SpecDoubleInRange(min = 0, max = 1)
	@PreserveNotNull
	public double y = 1;

	@PreserveNotNull
	public Anchor anchor = Anchor.BOTTOM_RIGHT;

	@PreserveNotNull
	public List<ConfigBreadcrumb> breadcrumbs = new ArrayList<>();
}
