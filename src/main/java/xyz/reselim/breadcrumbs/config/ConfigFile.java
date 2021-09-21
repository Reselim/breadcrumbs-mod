package xyz.reselim.breadcrumbs.config;

import java.nio.file.Path;

import com.electronwill.nightconfig.core.conversion.ObjectConverter;
import com.electronwill.nightconfig.core.file.FileConfig;

import net.minecraft.client.Minecraft;

public final class ConfigFile {
	public static final ConfigFile INSTANCE = new ConfigFile();
	private final static ObjectConverter CONVERTER = new ObjectConverter();

	private final Path path;

	private ConfigFile() {
		Minecraft minecraft = Minecraft.getMinecraft();
		path = minecraft.mcDataDir.toPath().resolve("config/breadcrumbs.json");
	}

	public void write(Config data) {
		FileConfig config = FileConfig.of(path);
		CONVERTER.toConfig(data, config);
		config.save();
		config.close();
	}

	public Config read() {
		FileConfig config = FileConfig.of(path);
		config.load();
		Config data = CONVERTER.toObject(config, Config::new);
		config.close();

		return data;
	}
}
