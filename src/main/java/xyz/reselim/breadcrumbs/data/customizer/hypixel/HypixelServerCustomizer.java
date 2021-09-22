package xyz.reselim.breadcrumbs.data.customizer.hypixel;

import xyz.reselim.breadcrumbs.data.Customizer;
import xyz.reselim.breadcrumbs.data.provider.hypixel.HypixelLocationProvider;
import xyz.reselim.breadcrumbs.data.provider.hypixel.HypixelLocationProvider.HypixelLocationState;

public class HypixelServerCustomizer extends Customizer<HypixelLocationState> {
	public HypixelServerCustomizer() {
		super(HypixelLocationProvider.INSTANCE.state);
	}

	public String map(HypixelLocationState state) {
		return state.location != null ? state.location.server : "?";
	}
}
