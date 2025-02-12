package ru.vayflare;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vayflare.config.ButtonConfig;

public class UwUfications implements ModInitializer {
	public static final String MOD_ID = "uwu-fications";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ButtonConfig.loadConfig();
		LOGGER.info("Hello Fabric world!");
	}
}