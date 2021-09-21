package xyz.reselim.breadcrumbs;

import gg.essential.api.EssentialAPI;
import gg.essential.api.commands.Command;
import gg.essential.api.commands.DefaultHandler;
import gg.essential.api.commands.SubCommand;

public final class BreadcrumbsCommand extends Command {
	public BreadcrumbsCommand() {
		super("breadcrumbs");
	}

	@DefaultHandler
	public void handle() {
		EssentialAPI.getMinecraftUtil().sendMessage("Not yet implemented");
	}

	@SubCommand("reload")
	public void handleReload() {
		BreadcrumbsMod.INSTANCE.load();
	}
}
