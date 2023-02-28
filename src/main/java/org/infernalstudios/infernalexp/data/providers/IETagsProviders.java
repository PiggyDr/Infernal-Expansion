/*
 * Copyright 2023 Infernal Studios
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

package org.infernalstudios.infernalexp.data.providers;

import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.infernalstudios.infernalexp.data.DataProviderCollection;
import org.jetbrains.annotations.Nullable;

public abstract class IETagsProviders<T> extends TagsProvider<T> {

    public IETagsProviders(DataGenerator generator, Registry<T> registry, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, registry, modId, existingFileHelper);
    }

    public TagsProvider.TagAppender<T> createTag(TagKey<T> tag) {
        return tag(tag);
    }

    @SafeVarargs
    public static <E> TagProviderConsumer<E> simple(E... elements) {
        return (provider, tag) -> {
            provider.createTag(tag.get()).add(elements);
        };
    }

    @SafeVarargs
    public static <E> TagProviderConsumer<E> simple(ResourceKey<E>... elements) {
        return (provider, tag) -> {
            provider.createTag(tag.get()).add(elements);
        };
    }

    @FunctionalInterface
    public interface TagProviderConsumer<T> extends DataProviderCollection.DataProviderConsumer<TagKey<T>, IETagsProviders<T>> {}

}
