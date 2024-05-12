package com.jyotish.api.core.models.readers;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

import static com.jyotish.api.core.models.VedicProperties.*;

@Component
public class PlanetSignDisposition extends BaseReader {

    private static final String FILE_NAME_PLACE_HOLDER = "planet.sign.disposition.file.name";

    @Getter
    private static Map<String, Map<String, Set<String>>> signDispositionMap = null;

    @PostConstruct
    private static void initMap() {
        String fileName = getValue(FILE_NAME_PLACE_HOLDER);
        Properties properties = readPropertiesFile(fileName);
        Map<String, Map<String, Set<String>>> finalMap = new HashMap<>();
        for (Object keyValue : properties.keySet()) {
            if (keyValue != null) {
                String[] keyValues = keyValue.toString().trim().split("\\.");
                String planetName = keyValues[0].trim();
                String signName = keyValues[1].trim();
                String[] dispositionValues = properties.getProperty(keyValue.toString()).trim().split(",");
                putToMap(finalMap, planetName, signName, dispositionValues);
            }
        }
        signDispositionMap = finalMap;
    }

    private static void putToMap(
            Map<String, Map<String, Set<String>>> finalMap,
            String planetName,
            String signName,
            String[] dispositionValues
    ) {
        planetName = getActualString(planetName).trim();
        signName = getActualString(signName).trim();
        for (int i = 0; i < dispositionValues.length; i++) {
            String dispositionValue = dispositionValues[i];
            if (dispositionValue != null && !dispositionValue.trim().isEmpty()) {
                dispositionValues[i] = getActualString(dispositionValue.trim()).trim();
            }
        }
        Map<String, Set<String>> signMap = finalMap.getOrDefault(planetName, new HashMap<>());
        Set<String> planetDignitiesList = signMap.getOrDefault(signName, new HashSet<>());
        planetDignitiesList.addAll(Arrays.asList(dispositionValues));
        signMap.put(signName, planetDignitiesList);
        finalMap.put(planetName, signMap);
    }
}
