package xyz.reselim.breadcrumbs;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import xyz.reselim.breadcrumbs.config.ConfigFile;
import xyz.reselim.breadcrumbs.config.Config;
import xyz.reselim.breadcrumbs.ui.UIBreadcrumbWindow;

@Mod(
    modid = BreadcrumbsMod.MODID,
    name = BreadcrumbsMod.MOD_NAME,
    version = BreadcrumbsMod.VERSION,
    acceptedMinecraftVersions = "[1.8.9]",
    clientSideOnly = true
)
public class BreadcrumbsMod {
    public static final String MODID = "breadcrumbs";
    public static final String MOD_NAME = "Breadcrumbs";
    public static final String VERSION = "0.0.1";

	@Mod.Instance
	public static BreadcrumbsMod INSTANCE;
	public static Config CONFIG;

	private UIBreadcrumbWindow window;

    @Mod.EventHandler
	public void onInitialization(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
		this.load();

		new BreadcrumbsCommand().register();
	}

	public void load() {
		CONFIG = ConfigFile.INSTANCE.read();
		window = UIBreadcrumbWindow.fromConfig(CONFIG);
	}

	@SubscribeEvent(
		priority = EventPriority.LOWEST
	)
	public void onRenderTick(RenderTickEvent event) {
		Minecraft minecraft = Minecraft.getMinecraft();

		if (minecraft.currentScreen == null) {
			window.draw();
		}
	}
}