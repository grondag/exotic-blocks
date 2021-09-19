package grondag.xblocks.mixin;

import com.mojang.datafixers.DataFixer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import grondag.xblocks.data.XbDataFixer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.util.datafix.DataFixTypes;

@Mixin(NbtUtils.class)
public abstract class MixinNbtHelper {
	@Inject(at = @At("RETURN"), method = "update(Lcom/mojang/datafixers/DataFixer;Lnet/minecraft/datafixer/DataFixTypes;Lnet/minecraft/nbt/NbtCompound;II)Lnet/minecraft/nbt/NbtCompound;", cancellable = false)
	private static void xb_update(DataFixer vanillaDataFixer, DataFixTypes dataFixTypes, CompoundTag inputTag$unused, int vanillaDynamicDataVersion, int vanillaRuntimeDataVersion, CallbackInfoReturnable<CompoundTag> cir) {
		XbDataFixer.update(vanillaDynamicDataVersion, vanillaRuntimeDataVersion, cir.getReturnValue());
	}
}
