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

import org.infernalstudios.infernalexp.confignew.IEConfig;
import org.infernalstudios.infernalexp.confignew.elements.ConfigOption;

/**
 * This interface exists to allow easier migration between different config file systems
 */
public interface IConfigBuilder {

    void init(IEConfig config);

    void push(String name);

    void pop();

    <T> void define(ConfigOption<T> configOption);

}