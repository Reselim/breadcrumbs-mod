package xyz.reselim.breadcrumbs.config;

import java.util.ArrayList;
import java.util.List;

import com.electronwill.nightconfig.core.conversion.PreserveNotNull;

public final class Config {
	@PreserveNotNull
	public List<ConfigGroup> groups = new ArrayList<>();
}
