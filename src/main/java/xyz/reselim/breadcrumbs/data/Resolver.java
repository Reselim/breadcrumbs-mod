package xyz.reselim.breadcrumbs.data;

import xyz.reselim.breadcrumbs.data.customizer.ComboCustomizer;
import xyz.reselim.breadcrumbs.data.customizer.SpeedCustomizer;

public final class Resolver {
	public static final Customizer<?> createCustomizer(CustomizerType type) throws RuntimeException {
		switch (type) {
			case SPEED: return new SpeedCustomizer();
			case COMBO: return new ComboCustomizer();
			default: throw new RuntimeException();
		}
	}

	public enum CustomizerType {
		SPEED,
		COMBO,
	}
}