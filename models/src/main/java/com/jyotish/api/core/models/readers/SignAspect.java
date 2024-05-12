package com.jyotish.api.core.models.readers;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

import static com.jyotish.api.core.models.VedicProperties.*;

@Component
public class SignAspect extends BaseReader {

    private static final String FILE_NAME_PLACE_HOLDER = "sign.aspect.file.name";

    @Getter
    private static Map<String, Set<Integer>> signAspects = null;

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
        signAspects = finalMap;
    }

    private static void putToMap(
            Map<String, Set<Integer>> finalMap,
            String planetName,
            String[] aspects
    ) {
        planetName = getActualString(planetName).trim();
        Set<Integer> signAspects = finalMap.getOrDefault(planetName, new HashSet<>());
        for (String aspectingSignNumber : aspects) {
            try {
                signAspects.add(Integer.parseInt(aspectingSignNumber));
            } catch (NumberFormatException ignored) {
            }
        }
        finalMap.put(planetName, signAspects);
    }
}
