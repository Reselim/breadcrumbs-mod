package xyz.reselim.breadcrumbs.event;

import xyz.reselim.breadcrumbs.handler.HandlerServer.Server;

public class ServerEvent {
	public static final Event<ServerListener> EVENT = new Event<>((listeners) -> (server) -> {
		for (ServerListener listener : listeners) {
			listener.onServerChange(server);
		}
	});

	public interface ServerListener {
		void onServerChange(Server server);
	}
}
