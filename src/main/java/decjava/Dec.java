/*
 * Copyright 2016 - Per Wendel
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package decjava;

import java.lang.management.ManagementFactory;

/**
 * TODO be renamed
 * A first step to change the way we do Java code. Write business logic that anyone can understand.
 */
public class Dec {

    public static void main(String[] args) {

        given(davidIsAwake(), call("david"));
        unless(davisIsAsleep(), call("david"));
        during(daytime(), waterLawn());
        during(nighttime(), drinkBlood());
        
    }

    public static void given(boolean statement, Runnable then) {
        if (statement) {
            then.run();
        }
    }

    public static void unless(boolean statement, Runnable then) {
        if (!statement) {
            then.run();
        }
    }

    public static void during(Checkable statement, Runnable then) {
        while (statement.check()) {
            then.run();
        }
    }

    // TODO: rename
    public interface Checkable {
        boolean check();
    }


    public static Runnable call(String who) {
        return () -> System.out.println(who + " - Ring ring");
    }

    public static Runnable waterLawn() {
        return () -> System.out.println("Watering my beautiful lawn");
    }

    public static Runnable drinkBlood() {
        return () -> System.out.println("Drinking tons of blood");
    }

    public static boolean davidIsAwake() {
        return true;
    }

    private static boolean davisIsAsleep() {
        return false;
    }

    private static Checkable daytime() {
        long started = ManagementFactory.getRuntimeMXBean().getStartTime();
        return () -> (System.currentTimeMillis() - started) < 100;
    }

    private static Checkable nighttime() {
        long started = ManagementFactory.getRuntimeMXBean().getStartTime();
        return () -> (System.currentTimeMillis() - started) < 200;
    }
}
