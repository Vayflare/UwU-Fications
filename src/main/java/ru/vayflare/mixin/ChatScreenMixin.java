package ru.vayflare.mixin;

import net.minecraft.client.gui.screen.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import ru.vayflare.config.ButtonConfig;

import java.util.List;
import java.util.Random;

@Mixin(ChatScreen.class)
public class ChatScreenMixin {

	@Unique
	private static final List<String> EMOTICONS = List.of(" UwU", " :3", " ;3", " >w<", " ^w^", " OwO", " XD", " :D", " :P");
	@Unique
	private static final Random RANDOM = new Random();

	@ModifyVariable(method = "sendMessage", at = @At("HEAD"), ordinal = 0, argsOnly = true)
	private String modifyMessage(String message) {
		if (ButtonConfig.getEnabled()) {
			if (!message.startsWith("/") && ButtonConfig.getAppendRandomEmoticons()) {
				String emoticon = EMOTICONS.get(RANDOM.nextInt(EMOTICONS.size()));
				return message + emoticon;
			}
			if (!message.startsWith("/") && !ButtonConfig.getAppendRandomEmoticons()) {
				return message + " UwU";
			}
		}
		return message;
	}
}