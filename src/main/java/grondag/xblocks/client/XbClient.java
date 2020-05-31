package grondag.xblocks.client;

import net.fabricmc.api.ClientModInitializer;

import grondag.fermion.client.ClientRegistrar;
import grondag.fermion.client.models.SimpleUnbakedModel;
import grondag.xblocks.Xb;
import grondag.xblocks.init.FestiveLights;

public class XbClient implements ClientModInitializer {
	public static final ClientRegistrar REGISTRAR = new ClientRegistrar(Xb.MODID);

	@Override
	public void onInitializeClient() {
		final SimpleUnbakedModel model = new SimpleUnbakedModel(FestiveLightsModel::create, FestiveLightsModel.TEXTURES);

		for (final String name : FestiveLights.FESTIVE_LIGHTS) {
			REGISTRAR.modelVariant(name, model);
		}
	}
}
