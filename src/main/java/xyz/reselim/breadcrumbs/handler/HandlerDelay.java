package xyz.reselim.breadcrumbs.handler;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class HandlerDelay {
	public static final HandlerDelay INSTANCE = new HandlerDelay();

	private List<Delay> delays = new ArrayList<>();

	public HandlerDelay() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onTick(ClientTickEvent event) {
		delays.removeIf((delay) -> {
			return delay.tick();
		});
	}

	public void delay(Runnable callback, int ticks) {
		Delay delay = new Delay(callback, ticks);
		delays.add(delay);
	}

	public void delay(Runnable callback) {
		delay(callback, 1);
	}

	public class Delay {
		private final Runnable callback;
		private int ticks;

		public Delay(Runnable initCallback, int initTicks) {
			callback = initCallback;
			ticks = initTicks;
		}

		public boolean tick() {
			ticks -= 1;

			if (ticks <= 0) {
				callback.run();
				return true;
			} else {
				return false;
			}
		}
	}
}
