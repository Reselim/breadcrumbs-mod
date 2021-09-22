package xyz.reselim.breadcrumbs.data.provider.hypixel;

import javax.annotation.Nullable;

import gg.essential.elementa.state.BasicState;
import xyz.reselim.breadcrumbs.data.Provider;
import xyz.reselim.breadcrumbs.event.ServerLocationEvent;
import xyz.reselim.breadcrumbs.handler.HandlerServer.Server;
import xyz.reselim.breadcrumbs.handler.hypixel.HandlerHypixelLocation;
import xyz.reselim.breadcrumbs.handler.hypixel.HandlerHypixelLocation.HypixelLocation;

public class HypixelLocationProvider extends Provider {
	public static final HypixelLocationProvider INSTANCE = new HypixelLocationProvider();

	public BasicState<HypixelLocationState> state = new BasicState<>(new HypixelLocationState(HandlerHypixelLocation.INSTANCE.location));

	private HypixelLocationProvider() {
		ServerLocationEvent.EVENT.subscribe((location) -> {
			state.set(new HypixelLocationState(null));

			if (location != null && location.getServer() == Server.HYPIXEL) {
				state.set(new HypixelLocationState((HypixelLocation) location));
			}
		});
	}

	public class HypixelLocationState extends ProviderState {
		@Nullable
		public final HypixelLocation location;

		public HypixelLocationState(HypixelLocation initLocation) {
			location = initLocation;
		}
	}
}
