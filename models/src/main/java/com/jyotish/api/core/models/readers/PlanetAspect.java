package com.jyotish.api.core.models.readers;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

import static com.jyotish.api.core.models.VedicProperties.*;

@Component
public class PlanetAspect extends BaseReader {

    private static final String FILE_NAME_PLACE_HOLDER = "planet.aspect.file.name";

    @Getter
    private static Map<String, Set<Integer>> planetAspects = null;

    @PostConstruct
    private static void initMap() {
        String fileName = getValue(FILE_NAME_PLACE_HOLDER);
        Properties properties = readPropertiesFile(fileName);
        Map<String, Set<Integer>> finalMap = new HashMap<>();
        for (Object keyValue : properties.keySet()) {
            if (keyValue != null) {
                String planetName = keyValue.toString().trim();
                String[] aspects = properties.getProperty(keyValue.toString()).trim().split(",");
                putToMap(finalMap, planetName, aspects);
            }
        }
        planetAspects = finalMap;
    }

    private static void putToMap(
            Map<String, Set<Integer>> finalMap,
            String planetName,
            String[] aspects
    ) {
        planetName = getActualString(planetName).trim();
        Set<Integer> planetAspects = finalMap.getOrDefault(planetName, new HashSet<>());
        for (String aspectString : aspects) {
            try {
                planetAspects.add(Integer.parseInt(aspectString));
            } catch (NumberFormatException ignored) {
            }
        }
        finalMap.put(planetName, planetAspects);
    }

}