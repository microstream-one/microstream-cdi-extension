/*
 *    Copyright 2021 Otavio Santana
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package one.microstream.cdi.config;

import one.microstream.cdi.test.CDIExtension;
import org.eclipse.microprofile.config.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@CDIExtension
class ConfigurationCorePropertiesTest {

    @Inject
    private Config config;

    @Test
    public void shouldCreateEmptyMap() {
        Map<String, String> properties = ConfigurationCoreProperties.getProperties(config);
        Assertions.assertNotNull(properties);
        Assertions.assertTrue(properties.isEmpty());
    }

}