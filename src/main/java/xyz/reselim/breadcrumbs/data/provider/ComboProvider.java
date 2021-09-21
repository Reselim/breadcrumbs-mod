package xyz.reselim.breadcrumbs.data.provider;

import gg.essential.elementa.state.BasicState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import xyz.reselim.breadcrumbs.data.Provider;

public final class ComboProvider extends Provider {
	public static final ComboProvider INSTANCE = new ComboProvider();

	public static final int TIMEOUT = 1500;

	public final BasicState<ComboState> state = new BasicState<>(new ComboState(0));

	private ComboProvider() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	public class ComboState extends ProviderState {
		public final int count;

		public ComboState(int comboCount) {
			count = comboCount;
		}
	}

	// Counter

	private int counter = 0;

	private void incrementCounter() {
		counter++;
		state.set(new ComboState(counter));
	}

	private void resetCounter() {
		counter = 0;
		state.set(new ComboState(counter));
	}

	// Tracking

	private AttackDetails lastAttack;
	private AttackDetails pendingAttack;

	@SubscribeEvent
	public void onAttack(AttackEntityEvent event) {
		Minecraft minecraft = Minecraft.getMinecraft();
		Entity entity = event.target;

		if (entity == minecraft.pointedEntity && !event.isCanceled()) {
			pendingAttack = new AttackDetails(Minecraft.getSystemTime(), entity.getEntityId());

			if (lastAttack == null || pendingAttack.entityId != lastAttack.entityId) {
				resetCounter();
			}
		}
	}

	@SubscribeEvent
	public void onTick(ClientTickEvent event) {
		if (lastAttack != null && Minecraft.getSystemTime() - lastAttack.time >= TIMEOUT) {
			lastAttack = null;
			resetCounter();
		}
	}

	public void onEntityStatus(S19PacketEntityStatus packet) {
		if (packet.getOpCode() == 2) {
			Minecraft minecraft = Minecraft.getMinecraft();
			Entity entity = packet.getEntity(minecraft.theWorld);

			if (pendingAttack != null && entity != null && entity.getEntityId() == pendingAttack.entityId) {
				incrementCounter();

				lastAttack = pendingAttack;
				pendingAttack = null;
			}

			if (entity.getEntityId() == minecraft.thePlayer.getEntityId()) {
				resetCounter();
			}
		}
	}

	private class AttackDetails {
		public final long time;
		public final long entityId;

		public AttackDetails(long attackTime, int attackEntityId) {
			time = attackTime;
			entityId = attackEntityId;
		}
	}
}
