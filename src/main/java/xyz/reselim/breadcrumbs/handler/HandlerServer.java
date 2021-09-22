package xyz.reselim.breadcrumbs.handler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;
import xyz.reselim.breadcrumbs.event.ServerEvent;

public class HandlerServer {
	public static final HandlerServer INSTANCE = new HandlerServer();

	public Server server = Server.NONE;

	private HandlerServer() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setServer(Server newServer) {
		server = newServer;
		System.out.println(newServer);
		ServerEvent.EVENT.invoker.onServerChange(server);
	}

	@SubscribeEvent
	public void onWorldEnter(ClientConnectedToServerEvent event) {
		Minecraft minecraft = Minecraft.getMinecraft();

		if (minecraft.isSingleplayer()) {
			setServer(Server.LOCAL);
		} else {
			ServerData serverData = minecraft.getCurrentServerData();

			for (Server checkServer : Server.values()) {
				if (checkServer.pattern != null) {
					Matcher matcher = checkServer.pattern.matcher(serverData.serverIP);

					if (matcher.find()) {
						setServer(checkServer);
						return;
					}
				}
			}

			setServer(Server.UNKNOWN);
		}
	}

	@SubscribeEvent
	public void onServerLeave(ClientDisconnectionFromServerEvent event) {
		setServer(Server.NONE);
	}

	public enum Server {
		HYPIXEL("^(?:.+\\.)?hypixel\\.net$"),

		UNKNOWN,
		LOCAL,
		NONE;

		@Nullable
		public final Pattern pattern;

		Server() {
			pattern = null;
		}

		Server(String initPattern) {
			pattern = Pattern.compile(initPattern);
		}
	}
}
