package dev.tori.fovu.mixin.client;

import com.mojang.serialization.Codec;
import dev.tori.fovu.client.FovUClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.network.chat.Component;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
@Mixin(Options.class)
public abstract class MixinOptions {

    @Mutable
    @Shadow
    @Final
    private OptionInstance<Integer> fov;

    @Inject(
            method = "<init>(Lnet/minecraft/client/Minecraft;Ljava/io/File;)V",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/Options;fov:Lnet/minecraft/client/OptionInstance;",
                    shift = At.Shift.AFTER,
                    opcode = Opcodes.PUTFIELD
            )
    )
    private void onGameOptionsInit(Minecraft minecraft, File workingDirectory, CallbackInfo ci) {
        this.fov = new OptionInstance<>(
                "options.fov",
                OptionInstance.noTooltip(),
                (caption, value) -> {
                    Component component;
                    switch (value) {
                        case 70 ->
                                component = Options.genericValueLabel(caption, Component.translatable("options.fov.min"));
                        case 110 ->
                                component = Options.genericValueLabel(caption, Component.translatable("options.fov.max"));
                        default -> component = Options.genericValueLabel(caption, value);
                    }

                    return component;
                },
                new OptionInstance.IntRange(FovUClient.MIN_FOV, FovUClient.MAX_FOV),
                Codec.DOUBLE.xmap(
                        (value) -> (int) (value * (double) 40.0F + (double) 70.0F),
                        (value) -> ((double) value - (double) 70.0F) / (double) 40.0F
                ),
                70,
                OptionInstance.NO_ACTION
        );
    }
}