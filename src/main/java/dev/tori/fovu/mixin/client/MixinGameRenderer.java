package dev.tori.fovu.mixin.client;

import dev.tori.fovu.client.FovUClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
@Mixin(GameRenderer.class)
public class MixinGameRenderer {

    @Inject(
            method = "getFov(Lnet/minecraft/client/render/Camera;FZ)F",
            at = @At(
                    value = "RETURN"
            ),
            cancellable = true
    )
    private void getFov(Camera camera, float tickProgress, boolean changingFov, CallbackInfoReturnable<Float> cir) {
        final float clampedFov = Math.clamp(cir.getReturnValue(), FovUClient.MIN_FOV, FovUClient.MAX_FOV);
        cir.setReturnValue(clampedFov);
    }
}