package dev.tori.fovu.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.tori.fovu.client.FovUClient;
import net.minecraft.client.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
@Mixin(Camera.class)
public class MixinCamera {

    @ModifyReturnValue(
            method = "getFov",
            at = @At(
                    "RETURN"
            )
    )
    private float getFov_Return(float fov) {
        return Math.clamp(fov, FovUClient.MIN_FOV, FovUClient.MAX_FOV);
    }

    @ModifyReturnValue(
            method = "calculateFov(F)F",
            at = @At(
                    "RETURN"
            )
    )
    private float calculateFov_Return(float original) {
        return Math.clamp(original, FovUClient.MIN_FOV, FovUClient.MAX_FOV);
    }
}