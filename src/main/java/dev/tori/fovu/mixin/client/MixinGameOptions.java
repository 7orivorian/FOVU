package dev.tori.fovu.mixin.client;

import com.mojang.serialization.Codec;
import dev.tori.fovu.client.FovUClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;

import static net.minecraft.client.option.GameOptions.getGenericValueText;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
@Mixin(GameOptions.class)
public class MixinGameOptions {

    @Mutable
    @Shadow
    @Final
    private SimpleOption<Integer> fov;

    @Inject(
            method = "<init>(Lnet/minecraft/client/MinecraftClient;Ljava/io/File;)V",
            at = @At(
                    value = "FIELD", target = "Lnet/minecraft/client/option/GameOptions;fov:Lnet/minecraft/client/option/SimpleOption;",
                    shift = At.Shift.AFTER
            )
    )
    private void onGameOptionsInit(MinecraftClient client, File file, CallbackInfo ci) {
        this.fov = new SimpleOption<>(
                "options.fov",
                SimpleOption.emptyTooltip(),
                (optionText, value) -> {
                    Text text = Text.of("Value is not an Integer.");

                    if (value instanceof Integer val) {
                        switch (val) {
                            case 70 -> text = getGenericValueText(optionText, Text.translatable("options.fov.min"));
                            case 110 -> text = getGenericValueText(optionText, Text.translatable("options.fov.max"));
                            default -> text = getGenericValueText(optionText, Text.of(val.toString()));
                        }
                    }

                    return text;
                },
                new SimpleOption.ValidatingIntSliderCallbacks(FovUClient.MIN_FOV, FovUClient.MAX_FOV),
                Codec.DOUBLE.xmap((value) -> (int) (value * (double) 40.0F + (double) 70.0F), (value) -> ((double) value - (double) 70.0F) / (double) 40.0F),
                70,
                (value) -> MinecraftClient.getInstance().worldRenderer.scheduleTerrainUpdate()
        );
    }
}