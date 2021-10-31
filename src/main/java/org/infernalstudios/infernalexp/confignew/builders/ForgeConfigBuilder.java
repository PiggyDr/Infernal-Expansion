/*
 * Copyright 2021 Infernal Studios
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.infernalstudios.infernalexp.confignew.builders;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.infernalstudios.infernalexp.confignew.IEConfig;
import org.infernalstudios.infernalexp.confignew.elements.ConfigOption;

public class ForgeConfigBuilder implements IConfigBuilder {

    private final String fileName;
    private final ForgeConfigSpec.Builder builder;

    public ForgeConfigBuilder(String fileName) {
        this.fileName = fileName;
        this.builder = new ForgeConfigSpec.Builder();
    }

    @Override
    public void init(IEConfig config) {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, builder.configure(configBuilder -> config.buildConfigFile(this)).getRight(), fileName);
    }

    @Override
    public void push(String name) {
        builder.push(name);
    }

    @Override
    public void pop() {
        builder.pop();
    }

    @Override
    public <T> void define(ConfigOption<T> configOption) {
        if (configOption.getDescription() != null)
            builder.comment(" " + configOption.getDescription());

        if (configOption.getRestriction("min") != null && configOption.getRestriction("max") != null)
            builder.comment(" Range: " + configOption.getRestriction("min") + " ~ " + configOption.getRestriction("max"));

        else if (configOption.getRestriction("min") != null)
            builder.comment(" Range: " + configOption.getRestriction("min") + " ~ ∞");

        else if (configOption.getRestriction("max") != null)
            builder.comment(" Range: -∞ ~ " + configOption.getRestriction("max"));

        configOption.addConfigValue(builder.define(configOption.getTranslationName(), configOption.getDefaultValue()));
    }
}