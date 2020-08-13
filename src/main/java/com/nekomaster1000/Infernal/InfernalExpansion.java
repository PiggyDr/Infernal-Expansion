package com.nekomaster1000.Infernal;

import com.nekomaster1000.Infernal.entities.*;
import com.nekomaster1000.Infernal.init.*;
import com.nekomaster1000.Infernal.util.RegistryHandler;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;


@Mod("infernalexp")
public class InfernalExpansion
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "infernalexp";

    public InfernalExpansion() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);

        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModEntityType.ENTITY_TYPES.register(modEventBus);
        ModPaintings.PAINTING_TYPES.register(modEventBus);
        //ModSurfaceBuilder.SURFACE_BUILDERS.register(modEventBus);
        ModBiomes.BIOMES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }


    private void setup(final FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(ModEntityType.GLOWSQUITO.get(), GlowsquitoEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.VOLINE.get(), VolineEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.PYRNO.get(), PyrnoEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.WARPBEETLE.get(), WarpbeetleEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.EMBODY.get(), EmbodyEntity.setCustomAttributes().create());
            Biomes.NETHER_WASTES.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(ModEntityType.VOLINE.get(), 3, 3, 7));
            Biomes.BASALT_DELTAS.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(ModEntityType.VOLINE.get(), 3, 3, 7));
            //Biomes.CRIMSON_FOREST.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(ModEntityType.WARPBEETLE.get(), 1, 1, 1));
            Biomes.WARPED_FOREST.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(ModEntityType.WARPBEETLE.get(), 2, 1, 1));
            Biomes.SOUL_SAND_VALLEY.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(ModEntityType.EMBODY.get(), 2, 1, 15));
        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) {    }

    public static final ItemGroup TAB = new ItemGroup("InfernalTab") {

        @Override
        public ItemStack createIcon() {
            return new ItemStack(com.nekomaster1000.Infernal.init.ModItems.DIMROCKS.get());
        };

    };
}
