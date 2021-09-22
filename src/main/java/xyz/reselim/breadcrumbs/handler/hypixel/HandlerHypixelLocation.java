package xyz.reselim.breadcrumbs.handler.hypixel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.reselim.breadcrumbs.event.ServerLocationEvent;
import xyz.reselim.breadcrumbs.handler.HandlerDelay;
import xyz.reselim.breadcrumbs.handler.HandlerServer;
import xyz.reselim.breadcrumbs.handler.HandlerServer.Server;

public class HandlerHypixelLocation {
	public static final HandlerHypixelLocation INSTANCE = new HandlerHypixelLocation();

	private static final String LOCRAW_TEXT = "/locraw";
	private static final Pattern LOCRAW_PATTERN = Pattern.compile("^\\{.+\\}$");

	@Nullable
	public HypixelLocation location;

	private boolean sendPending = false;
	private boolean replyPending = false;

	private HandlerHypixelLocation() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setLocation(HypixelLocation newLocation) {
		location = newLocation;
		ServerLocationEvent.EVENT.invoker.onLocationChange(location);
	}

	@SubscribeEvent
	public void onWorldEnter(WorldEvent.Load event) {
		if (!sendPending) {
			setLocation(null);

			if (HandlerServer.INSTANCE.server == Server.HYPIXEL) {
				sendPending = true;

				HandlerDelay.INSTANCE.delay(() -> {
					sendPending = false;
					replyPending = true;

					EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
					player.sendChatMessage(LOCRAW_TEXT);
				});
			}
		}
	}

	@SubscribeEvent
	public void onWorldLeave(WorldEvent.Unload event) {
		setLocation(null);
	}

	@SubscribeEvent(
		priority = EventPriority.HIGHEST
	)
	public void onChatReceived(ClientChatReceivedEvent event) {
		if (replyPending && !event.isCanceled()) {
			String message = event.message.getUnformattedText();
			Matcher matcher = LOCRAW_PATTERN.matcher(message);

			if (matcher.find()) {
				event.setCanceled(true);
				replyPending = false;

				JsonObject data = new JsonParser()
					.parse(message)
					.getAsJsonObject();

				// no optional chaining
				// sobs

				JsonElement server = data.get("server");
				JsonElement lobby = data.get("lobbyname");

				JsonElement gameType = data.get("gametype");
				JsonElement gameMode = data.get("mode");
				JsonElement gameMap = data.get("map");

				System.out.println(server);
				System.out.println(lobby);
				System.out.println(gameType);
				System.out.println(gameMode);
				System.out.println(gameMap);

				HypixelLocationGame game = null;

				if (gameType != null) {
					game = new HypixelLocationGame(
						gameType != null ? gameType.getAsString() : null,
						gameMode != null ? gameMode.getAsString() : null,
						gameMap != null ? gameMap.getAsString() : null
					);
				}

				HypixelLocation location = new HypixelLocation(
					server != null ? server.getAsString() : null,
					lobby != null ? lobby.getAsString() : null,
					game
				);

				setLocation(location);
			}
		}
	}

	public class HypixelLocationGame {
		public final String type;

		@Nullable
		public final String mode;

		@Nullable
		public final String map;

		public HypixelLocationGame(String initType, String initMode, String initMap) {
			type = initType;
			mode = initMode;
			map = initMap;
		}
	}

	public class HypixelLocation implements ServerLocationEvent.ServerLocation {
		public final String server;

		@Nullable
		public final String lobby;

		@Nullable
		public final HypixelLocationGame game;

		public HypixelLocation(String initServer, String initLobby, HypixelLocationGame initGame) {
			server = initServer;
			lobby = initLobby;
			game = initGame;
		}

		public Server getServer() {
			return Server.HYPIXEL;
		}
	}
}
