package dev.tori.fovu.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.tori.fovu.client.FovUClient;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
@Mixin(GameRenderer.class)
public class MixinGameRenderer {

    @ModifyReturnValue(
            method = "getFov(Lnet/minecraft/client/render/Camera;FZ)F",
            at = @At(
                    "RETURN"
            )
    )
    private float getFov(float fov) {
        return Math.clamp(fov, FovUClient.MIN_FOV, FovUClient.MAX_FOV);
    }
}