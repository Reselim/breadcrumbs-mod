package xyz.reselim.breadcrumbs.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import xyz.reselim.breadcrumbs.data.provider.ComboProvider;

@Mixin(NetHandlerPlayClient.class)
public class NetHandlerPlayClientMixin {
	@Inject(at = @At("TAIL"), method = "handleEntityStatus")
	public void onEntityStatusPacket(S19PacketEntityStatus packet, CallbackInfo info) {
		ComboProvider.INSTANCE.onEntityStatus(packet);
	}
}
