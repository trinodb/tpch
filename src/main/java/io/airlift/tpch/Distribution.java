/*
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
package io.airlift.tpch;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static com.google.common.base.Preconditions.checkNotNull;

public class Distribution
{
    private final String name;
    private final List<String> values;
    private final int[] weights;

    public Distribution(String name, Map<String, Integer> distribution)
    {
        this.name = checkNotNull(name, "name is null");
        checkNotNull(distribution, "distribution is null");

        ImmutableList.Builder<String> values = ImmutableList.builder();
        this.weights = new int[distribution.size()];

        int runningWeight = 0;
        int index = 0;
        for (Entry<String, Integer> entry : distribution.entrySet()) {
            values.add(entry.getKey());

            runningWeight += entry.getValue();
            weights[index] = runningWeight;
            index++;
        }
        this.values = values.build();
    }

    public String getValue(int index)
    {
        return values.get(index);
    }

    public List<String> getValues()
    {
        return values;
    }

    public int getWeight(int index)
    {
        return weights[index];
    }

    public int size()
    {
        return values.size();
    }

    public String randomValue(RandomInt randomInt)
    {
        int i = randomIndex(randomInt);
        return values.get(i);
    }

    public int randomIndex(RandomInt randomInt)
    {
        int maxWeight = weights[values.size() - 1];
        int randomValue = randomInt.nextInt(1, maxWeight);

        int i = Arrays.binarySearch(weights, randomValue);
        if (i < 0) {
            // if the value is not found in the array, (- insertion point) is returned
            i = (-i) - 1;
        }
        return Math.abs(i);
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this)
                .add("name", name)
                .toString();
    }
}
