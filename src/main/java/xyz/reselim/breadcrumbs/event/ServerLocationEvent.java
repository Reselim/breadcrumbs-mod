package xyz.reselim.breadcrumbs.event;

import xyz.reselim.breadcrumbs.handler.HandlerServer.Server;

public interface ServerLocationEvent {
	public static final Event<ServerLocationListener> EVENT = new Event<>((listeners) -> (location) -> {
		for (ServerLocationListener listener : listeners) {
			listener.onLocationChange(location);
		}
	});

	interface ServerLocationListener {
		void onLocationChange(ServerLocation location);
	}

	interface ServerLocation {
		public Server getServer();
	}
}
