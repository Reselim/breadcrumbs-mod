package xyz.reselim.breadcrumbs.data.provider;

import gg.essential.elementa.state.BasicState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import xyz.reselim.breadcrumbs.data.Provider;

public final class SpeedProvider extends Provider {
	public static final SpeedProvider INSTANCE = new SpeedProvider();

	public final BasicState<SpeedState> state = new BasicState<SpeedState>(new SpeedState(0, 0, 0));

	private SpeedProvider() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onRenderTick(RenderTickEvent event) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;

		if (player != null) {
			double speedX = Math.abs(player.lastTickPosX - player.posX) * 20d;
			double speedY = Math.abs(player.lastTickPosY - player.posY) * 20d;
			double speedZ = Math.abs(player.lastTickPosZ - player.posZ) * 20d;

			state.set(new SpeedState(speedX, speedY, speedZ));
		}
	}

	public class SpeedState extends ProviderState {
		public final double speedX;
		public final double speedY;
		public final double speedZ;

		public final double speedHorizontal;
		public final double speedCombined;

		public SpeedState(double speedX, double speedY, double speedZ) {
			this.speedX = speedX;
			this.speedY = speedY;
			this.speedZ = speedZ;

			this.speedHorizontal = Math.sqrt(speedX*speedX + speedZ*speedZ);
			this.speedCombined = Math.sqrt(speedHorizontal*speedHorizontal + speedY*speedY);
		}
	}
}
