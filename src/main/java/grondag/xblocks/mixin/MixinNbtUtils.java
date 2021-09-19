package grondag.xblocks.mixin;

import com.mojang.datafixers.DataFixer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.util.datafix.DataFixTypes;

import grondag.xblocks.data.XbDataFixer;

@Mixin(NbtUtils.class)
public abstract class MixinNbtUtils {
	@Inject(at = @At("RETURN"), method = "update(Lcom/mojang/datafixers/DataFixer;Lnet/minecraft/util/datafix/DataFixTypes;Lnet/minecraft/nbt/CompoundTag;II)Lnet/minecraft/nbt/CompoundTag;", cancellable = false)
	private static void xb_update(DataFixer vanillaDataFixer, DataFixTypes dataFixTypes, CompoundTag inputTag$unused, int vanillaDynamicDataVersion, int vanillaRuntimeDataVersion, CallbackInfoReturnable<CompoundTag> cir) {
		XbDataFixer.update(vanillaDynamicDataVersion, vanillaRuntimeDataVersion, cir.getReturnValue());
	}
}
