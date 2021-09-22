package xyz.reselim.breadcrumbs.data.customizer;

import xyz.reselim.breadcrumbs.data.Customizer;
import xyz.reselim.breadcrumbs.data.provider.ServerProvider;
import xyz.reselim.breadcrumbs.data.provider.ServerProvider.ServerState;

public class ServerCustomizer extends Customizer<ServerState> {
	public ServerCustomizer() {
		super(ServerProvider.INSTANCE.state);
	}

	public String map(ServerState state) {
		return state.server.name();
	}
}
