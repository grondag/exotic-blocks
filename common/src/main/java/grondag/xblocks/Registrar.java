/*
 * This file is part of Exotic Blocks and is licensed to the project under
 * terms that are compatible with the GNU Lesser General Public License.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership and licensing.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package grondag.xblocks;

import java.util.function.BiFunction;
import java.util.function.Function;

import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.Tag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Fluid;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fabricmc.fabric.api.tag.TagRegistry;

public class Registrar extends AbstractRegistrar {
	public final CreativeModeTab itemGroup;

	public Registrar(String modId, String groupItemName) {
		super(modId);
		final ResourceLocation itemGroupItemId = new ResourceLocation(modId, groupItemName);
		itemGroup = FabricItemGroupBuilder.build(new ResourceLocation(modId, "group"), () -> new ItemStack(Registry.ITEM.get(itemGroupItemId)));
	}

	public Item.Properties itemSettings() {
		return new Item.Properties().tab(itemGroup);
	}

	public <T extends Item> T item(String name, T item) {
		return Registry.register(Registry.ITEM, id(name), item);
	}

	public Item item(String name) {
		return item(name, new Item(itemSettings()));
	}

	public ArmorItem armorItem(String name, ArmorMaterial material, EquipmentSlot slot) {
		return item(name, new ArmorItem(material, slot, itemSettings()));
	}

	public <T extends Fluid> T fluid(String name, T fluid) {
		final T b = Registry.register(Registry.FLUID, id(name), fluid);
		return b;
	}

	public <T extends Block> T block(String name, T block, Item.Properties settings) {
		return block(name, block, new BlockItem(block, settings));
	}

	public <T extends Block> T block(String name, T block) {
		return block(name, block, itemSettings());
	}

	public <T extends Block> T block(String name, T block, Function<T, BlockItem> itemFactory) {
		return block(name, block, itemFactory.apply(block));
	}

	public <T extends Block> T block(String name, T block, BiFunction<T, Item.Properties, BlockItem> itemFactory) {
		return block(name, block, itemFactory.apply(block, itemSettings()));
	}

	public <T extends Block> T block(String name, T block, BlockItem item) {
		final T b = Registry.register(Registry.BLOCK, id(name), block);

		if (item != null) {
			final BlockItem bi = item(name, item);
			bi.registerBlocks(BlockItem.BY_BLOCK, bi);
		}

		return b;
	}

	public <T extends Block> T blockNoItem(String name, T block) {
		final T b = Registry.register(Registry.BLOCK, id(name), block);
		return b;
	}

	public <T extends BlockEntity> BlockEntityType<T> blockEntityType(String id, FabricBlockEntityTypeBuilder.Factory<T> supplier, Block... blocks) {
		return Registry.register(Registry.BLOCK_ENTITY_TYPE, id(id), FabricBlockEntityTypeBuilder.create(supplier, blocks).build(null));
	}

	public <T extends RecipeSerializer<?>> T recipeSerializer(String id, T serializer) {
		return Registry.register(Registry.RECIPE_SERIALIZER, id(id), serializer);
	}

	public <T extends Recipe<?>> RecipeType<T> recipeType(String id) {
		return Registry.register(Registry.RECIPE_TYPE, id(id), new RecipeType<T>() {
			@Override
			public String toString() {
				return id;
			}
		});
	}

	public SoundEvent sound(String id) {
		final ResourceLocation idid = id(id);
		return Registry.register(Registry.SOUND_EVENT, idid, new SoundEvent(idid));
	}

	public Tag<Fluid> fluidTag(String id) {
		return TagRegistry.fluid(id(id));
	}

	public Tag<Block> blockTag(String id) {
		return TagRegistry.block(id(id));
	}

	public Tag<Item> itemTag(String id) {
		return TagRegistry.item(id(id));
	}

	public Tag<EntityType<?>> entityTag(String id) {
		return TagRegistry.entityType(id(id));
	}

	public SimpleParticleType particle(String id, boolean alwaysSpawn) {
		return Registry.register(Registry.PARTICLE_TYPE, id(id), FabricParticleTypes.simple(alwaysSpawn));
	}

	public <T extends ParticleOptions> ParticleType<T> particle(String id, boolean alwaysSpawn, ParticleOptions.Deserializer<T> factory) {
		return Registry.register(Registry.PARTICLE_TYPE, id(id), FabricParticleTypes.complex(alwaysSpawn, factory));
	}

	public MobEffect statusEffect(String id, MobEffect effect) {
		return Registry.register(Registry.MOB_EFFECT, id(id), effect);
	}
}
