package ru.vayflare.config;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Unique;

public class ButtonScreen extends Screen {
    @Unique
    private CheckboxWidget enabledButton;

    @Unique
    private CheckboxWidget randomEmoticonsButton;

    @Unique
    private final Screen parent;

    public ButtonScreen(Screen parent) {
        super(Text.of("Emoticons Configuration"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        enabledButton = CheckboxWidget.builder(Text.of("Enabled"), textRenderer)
                .pos(100, 40)
                .tooltip(Tooltip.of(Text.of("Enable or Disable mod functions")))
                .checked(ButtonConfig.getEnabled())
                .build();
        this.addDrawableChild(enabledButton);

        randomEmoticonsButton = CheckboxWidget.builder(Text.of("Random Emoticons"), textRenderer)
                .pos(this.width - 150 - 20, 40)
                .tooltip(Tooltip.of(Text.of("Randomize Emoticons")))
                .checked(ButtonConfig.getAppendRandomEmoticons())
                .build();
        this.addDrawableChild(randomEmoticonsButton);

        this.addDrawableChild(ButtonWidget.builder(Text.of("Save"), button -> {
            ButtonConfig.save(
                    enabledButton.isChecked(),
                    randomEmoticonsButton.isChecked()
            );
            MinecraftClient.getInstance().setScreen(parent);
        }).dimensions(this.width / 2 - 100, this.height - 40, 200, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(textRenderer, "Config", this.width / 2, 20, 0xFFFFFF);
    }
}