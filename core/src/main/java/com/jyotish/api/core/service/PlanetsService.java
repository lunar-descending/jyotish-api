package com.jyotish.api.core.service;

import com.jyotish.api.core.ComputeHoroscope;
import com.jyotish.api.core.models.calc.Calculations;
import com.jyotish.api.core.models.entity.House;
import com.jyotish.api.core.models.entity.Longitude;
import com.jyotish.api.core.models.entity.Planet;
import com.jyotish.api.core.models.input.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PlanetsService {

    @Autowired
    private ComputeHoroscope computeHoroscope;

    public Map<String, Map<String, Object>> getPlanetResponseMap(final BirthData birthData) {
        PlanetData mainPlanetData = computeHoroscope.getPlanetData(birthData);
        PlanetData navamshaPlanetData = computeHoroscope.convertToNavamshaPlanetData(mainPlanetData);

        FlatPlanetData mainPlanetFlatData = new FlatPlanetData(mainPlanetData);
        FlatPlanetData navamshaPlanetFlatData = new FlatPlanetData(navamshaPlanetData);

        Map<String, Map<String, Object>> finalMap = new LinkedHashMap<>();
        for (String planetName : mainPlanetData.getPlanetLongitudeMap().keySet()) {
            populateDetailsToMap(finalMap, planetName, mainPlanetFlatData, navamshaPlanetFlatData);
        }

        return finalMap;
    }

    private void populateDetailsToMap(
            Map<String, Map<String, Object>> finalMap,
            String planetName,
            FlatPlanetData mainPlanetFlatData,
            FlatPlanetData navamshaPlanetFlatData
    ) {
        Planet mainPlanet = mainPlanetFlatData.getPlanetByName(planetName);
        Planet navamshaPlanet = navamshaPlanetFlatData.getPlanetByName(planetName);

        Map<String, Object> mainPlanetDetailsMap = new LinkedHashMap<>();
        populatePlanetDetailsMap(mainPlanetDetailsMap, mainPlanet, mainPlanetFlatData);

        Map<String, Object> navamshaPlanetDetailsMap = new LinkedHashMap<>();
        populatePlanetDetailsMap(navamshaPlanetDetailsMap, navamshaPlanet, navamshaPlanetFlatData);
        mainPlanetDetailsMap.put("navamsha", navamshaPlanetDetailsMap);

        finalMap.put(planetName, mainPlanetDetailsMap);
    }

    private void populatePlanetDetailsMap(
            Map<String, Object> valuesMap,
            Planet planet,
            FlatPlanetData flatPlanetData
    ) {

        Map<String, Object> signData = new LinkedHashMap<>();
        signData.put("signName", planet.getSign().getSignData().getSignName());
        signData.put("signRuler", planet.getSign().getSignData().getSignRuler());

        Map<String, Object> nakshatraData = new LinkedHashMap<>();
        nakshatraData.put("nakshatraName", planet.getNakshatra().getNakshatraData().getNakshatraName());
        nakshatraData.put("nakshatraRuler", planet.getNakshatra().getNakshatraData().getNakshatraRuler());

        valuesMap.put("degreeZodiac", planet.getLongitude().toString());
        valuesMap.put("degreeHouse", planet.getLongitudeHouse().toString());
        valuesMap.put("retro", planet.isDirect());
        valuesMap.put("sign", signData);
        valuesMap.put("house", planet.getHouse().toString());
        valuesMap.put("nakshatra", nakshatraData);
        valuesMap.put("pada", planet.getPadaNumber());

        List<Planet> planetsInSameHouse = planet.getHouse().getPlanets();
        planetsInSameHouse.remove(planet);
        valuesMap.put("conjunct", getReadableString(planetsInSameHouse, Planet::getName));

        List<Planet> planetsInOpposition = planet.house7.getPlanets();
        valuesMap.put("oppositions", getReadableString(planetsInOpposition, Planet::getName));

        List<Planet> planetsInTrine = new ArrayList<>();
        planetsInTrine.addAll(planet.house5.getPlanets());
        planetsInTrine.addAll(planet.house9.getPlanets());
        valuesMap.put("trine", getReadableString(planetsInTrine, Planet::getName));

        long ascendantIntensityNormalised = Math.abs(flatPlanetData.lagna.getLongitudeHouse().getNormaliseValue() - planet.getLongitudeHouse().getNormaliseValue());
        valuesMap.put("ascendantIntensity", Longitude.reverseNormalise(ascendantIntensityNormalised).toString());

        //Set<Integer> aspects = Calculations.getPlanetAspect(planet.name);
        List<String> housesAspecting = new ArrayList<>();
//        if (aspects != null) {
//            for (Integer aspect : aspects) {
//                int effectiveHouseNumber = (planet.house.number + aspect - 1) % 12;
//                House effectiveHouse = flatPlanetData.getHouseByNumber(effectiveHouseNumber == 0 ? 12 : effectiveHouseNumber);
//                housesAspecting.add(effectiveHouse.toString());
//            }
//        }
        valuesMap.put("aspecting", getReadableString(housesAspecting, String::toString));
    }

    private <T> String getReadableString(final List<T> list, Function<T, String> conversionFunction) {
        List<String> readableList = list
                .stream()
                .map(conversionFunction)
                .collect(Collectors.toList());
        return String.join(", ", readableList);
    }

}
