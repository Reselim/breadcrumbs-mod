package xyz.reselim.breadcrumbs.data;

import gg.essential.elementa.state.State;
import net.minecraftforge.common.MinecraftForge;

public abstract class Provider {
	public State<? extends ProviderState> state;

	public Provider() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	public abstract class ProviderState {}
}