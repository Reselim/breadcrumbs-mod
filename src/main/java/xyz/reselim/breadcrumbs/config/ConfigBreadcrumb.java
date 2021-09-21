package xyz.reselim.breadcrumbs.config;

import com.electronwill.nightconfig.core.conversion.PreserveNotNull;

import xyz.reselim.breadcrumbs.data.Customizer;
import xyz.reselim.breadcrumbs.data.Resolver;
import xyz.reselim.breadcrumbs.data.Resolver.CustomizerType;

public final class ConfigBreadcrumb {
	@PreserveNotNull
	public CustomizerType type;

	@PreserveNotNull
	public String label = "Label";

	@PreserveNotNull
	public ConfigCustomizer config;

	public Customizer<?> getCustomizer() {
		return Resolver.createCustomizer(type);
	}
}
