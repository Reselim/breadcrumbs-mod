package xyz.reselim.breadcrumbs.data;

import xyz.reselim.breadcrumbs.data.customizer.ComboCustomizer;
import xyz.reselim.breadcrumbs.data.customizer.ServerCustomizer;
import xyz.reselim.breadcrumbs.data.customizer.SpeedCustomizer;
import xyz.reselim.breadcrumbs.data.customizer.hypixel.HypixelServerCustomizer;

public final class Resolver {
	public static final Customizer<?> createCustomizer(CustomizerType type) throws RuntimeException {
		switch (type) {
			case SPEED: return new SpeedCustomizer();
			case COMBO: return new ComboCustomizer();

			case SERVER: return new ServerCustomizer();

			case HYPIXEL_SERVER: return new HypixelServerCustomizer();

			default: throw new RuntimeException();
		}
	}

	public enum CustomizerType {
		UNKNOWN,

		SPEED,
		COMBO,

		SERVER,

		HYPIXEL_SERVER,
		HYPIXEL_LOBBY,
		HYPIXEL_GAME_TYPE,
		HYPIXEL_GAME_MODE,
		HYPIXEL_GAME_MAP,
	}
}