package github.kituin.chatimage.gui;

import github.kituin.chatimage.config.ChatImageConfig;
import github.kituin.chatimage.widget.GifSlider;
import github.kituin.chatimage.widget.TimeOutSlider;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static github.kituin.chatimage.ChatImage.CONFIG;

@OnlyIn(Dist.CLIENT)
public class ConfigScreen extends ConfigRawScreen {
    public ConfigScreen(Screen screen) {
        super(Component.translatable("config.chatimage.category"), screen);
    }


    protected void init() {
        super.init();
        this.addRenderableWidget(Button.builder(getNsfw(CONFIG.nsfw), (p_96270_) -> {
            CONFIG.nsfw = !CONFIG.nsfw;
            p_96270_.setMessage(getNsfw(CONFIG.nsfw));
            ChatImageConfig.saveConfig(CONFIG);
        }).bounds(this.width / 2 - 154, this.height / 4 + 24 - 16, 150, 20)
                .tooltip(Tooltip.create(Component.translatable("nsfw.chatimage.tooltip"))).build());
        this.addRenderableWidget(new GifSlider(this.width / 2 + 4, this.height / 4 + 24 - 16, 150, 20, createSliderTooltip(GifSlider.tooltip())));
        this.addRenderableWidget(new TimeOutSlider(this.width / 2 - 154, this.height / 4 + 48 - 16, 150, 20, createSliderTooltip(TimeOutSlider.tooltip())));
        this.addRenderableWidget(Button.builder(Component.translatable("padding.chatimage.gui"),
                        (button) -> {
                            if (this.minecraft != null) {
                                this.minecraft.setScreen(new LimitPaddingScreen(this));
                            }
                        }).bounds(this.width / 2 + 4, this.height / 4 + 48 - 16, 150, 20)
                .tooltip(Tooltip.create(Component.translatable("padding.chatimage.tooltip"))).build());
        this.addRenderableWidget(Button.builder(getCq(CONFIG.cqCode), (button) -> {
                    CONFIG.cqCode = !CONFIG.cqCode;
                    button.setMessage(getCq(CONFIG.cqCode));
                    ChatImageConfig.saveConfig(CONFIG);
                }).bounds(this.width / 2 - 154, this.height / 4 + 72 - 16, 150, 20)
                .tooltip(Tooltip.create(Component.translatable("cq.chatimage.tooltip"))).build());
        this.addRenderableWidget(Button.builder(Component.translatable("gui.back"),
                        (button) -> {
                            if (this.minecraft != null) {
                                this.minecraft.setScreen(this.parent);
                            }
                        }).bounds(this.width / 2 - 77, this.height / 4 + 96 - 16, 150, 20)
                .build());
    }

    private MutableComponent getCq(boolean enable) {
        return Component.translatable(enable ? "open.cq.chatimage.gui" : "close.cq.chatimage.gui");
    }
    private MutableComponent getNsfw(boolean enable) {
        return Component.translatable(enable ? "close.nsfw.chatimage.gui" : "open.nsfw.chatimage.gui");
    }


}
