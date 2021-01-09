package com.nekomaster1000.infernalexp.world.dimension;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.Layer;
import net.minecraft.world.gen.layer.ZoomLayer;

import java.util.function.LongFunction;

public class ModNetherLayerProvider {
    public static Layer stackLayers(Registry<Biome> biomeRegistry, long seed) {
        LongFunction<IExtendedNoiseRandom<LazyArea>> randomProvider = salt -> new LazyAreaLayerContext(1, seed, salt);

        IAreaFactory<LazyArea> netherFactory = new ModNetherMasterLayer(biomeRegistry).apply(randomProvider.apply(1000L));

        for (int netherBiomeSize = 0; netherBiomeSize <= 3; netherBiomeSize++) {
            netherFactory = ZoomLayer.NORMAL.apply(randomProvider.apply(1000L + netherBiomeSize), netherFactory);
        }

        netherFactory = ZoomLayer.FUZZY.apply(randomProvider.apply(1000L), netherFactory);
        netherFactory = ZoomLayer.NORMAL.apply(randomProvider.apply(1000L), netherFactory);

        return new Layer(netherFactory);
    }
}
