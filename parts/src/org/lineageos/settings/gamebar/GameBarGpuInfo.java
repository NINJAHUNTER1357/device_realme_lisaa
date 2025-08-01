/*
 * Copyright (C) 2025 kenway214
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lineageos.settings.gamebar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameBarGpuInfo {

    private static final String GPU_TEMP_PATH  = "sys/class/thermal/thermal_zone13/temp";

    public static String getGpuTemp() {
        String line = readLine(GPU_TEMP_PATH);
        if (line == null) {
            return "N/A";
        }
        line = line.trim();
        try {
            float raw = Float.parseFloat(line);
            float c   = raw / 1000f;
            return String.format("%.1f", c);
        } catch (NumberFormatException e) {
            return "N/A";
        }
    }

    private static String readLine(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        } catch (IOException e) {
            return null;
        }
    }
}
