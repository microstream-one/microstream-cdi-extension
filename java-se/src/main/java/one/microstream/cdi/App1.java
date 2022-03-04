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

package one.microstream.cdi;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.concurrent.ThreadLocalRandom;

public class App1 {

    public static void main(String[] args) {


        try(SeContainer  container = SeContainerInitializer.newInstance().initialize()) {
            String sebastian = "Sebastian";
            String otavio = "Otavio";
            NameCounter counter = container.select(NameCounter.class).get();

            System.out.println("The name counter values: " + counter.getNames());
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int sebastianCounter = random.nextInt(1, 10);
            int otavioCounter = random.nextInt(1, 10);
            System.out.printf("The counters: Sebastian %d and Otavio %d %n",
                    sebastianCounter, otavioCounter);

            for (int index = 0; index < sebastianCounter; index++) {
                counter.count(sebastian);
            }
            for (int index = 0; index < otavioCounter; index++) {
                counter.count(otavio);
            }
            System.out.println("The Sebastian's count: " + counter.show("Sebastian"));
            System.out.println("The Otavio's count: " + counter.show("Otavio"));
        }
    }
}
