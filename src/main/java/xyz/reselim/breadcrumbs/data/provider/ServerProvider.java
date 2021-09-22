package xyz.reselim.breadcrumbs.data.provider;

import gg.essential.elementa.state.BasicState;
import xyz.reselim.breadcrumbs.data.Provider;
import xyz.reselim.breadcrumbs.event.ServerEvent;
import xyz.reselim.breadcrumbs.handler.HandlerServer;
import xyz.reselim.breadcrumbs.handler.HandlerServer.Server;

public class ServerProvider extends Provider {
	public static final ServerProvider INSTANCE = new ServerProvider();

	public BasicState<ServerState> state = new BasicState<>(new ServerState(HandlerServer.INSTANCE.server));

	private ServerProvider() {
		ServerEvent.EVENT.subscribe((server) -> {
			state.set(new ServerState(server));
		});
	}

	public class ServerState extends ProviderState {
		public final Server server;

		public ServerState(Server initServer) {
			server = initServer;
		}
	}
}
