package com.jyotish.api.core.models.entity;

import com.jyotish.api.core.models.signs.Sign;
import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class House {

    public int number;
    public Sign sign;
    public Planet ruler;
    public List<Planet> planets = new ArrayList<>();

    public House house1;
    public House house2;
    public House house3;
    public House house4;
    public House house5;
    public House house6;
    public House house7;
    public House house8;
    public House house9;
    public House house10;
    public House house11;
    public House house12;

    /**
     * from the list of planets names passed in arguement, returns list of planets objects which are present in house
     * @param planets list of planets to search from
     * @return list of planets objects present in house
     */
    public List<Planet> getPlanetsPlaced(String...planets) {
        if (planets == null || planets.length == 0) {
            return Collections.emptyList();
        }
        List<Planet> planetsPresentInHouseFiltered = new ArrayList<>();
        for (String planet : planets) {
            for (Planet planetInHouse : this.planets) {
                if (planet != null && planet.equalsIgnoreCase(planetInHouse.getName())) {
                    planetsPresentInHouseFiltered.add(planetInHouse);
                }
            }
        }
        return planetsPresentInHouseFiltered;
    }

    /**
     * from the list of planets objects passed in arguement, returns list of planets objects which are present in house
     * @param planets list of planets objects to search from
     * @return list of planets objects present in house
     */
    public List<Planet> getPlanetsPlaced(Planet...planets) {
        if (planets == null || planets.length == 0) {
            return Collections.emptyList();
        }
        String[] planetNamesArray = new String[planets.length];
        int index = 0;
        for (Planet planet : planets) {
            if (planet != null) {
                planetNamesArray[index++] = planet.name;
            }
        }
        return getPlanetsPlaced(planetNamesArray);
    }

    /**
     * from the list of planets names passed in arguement, returns true if any planet present in house
     * @param planets list of planets to search from
     * @return true if any planet present in house from list
     */
    public boolean containsAny(String...planets) {
        return !getPlanetsPlaced(planets).isEmpty();
    }

    /**
     * from the list of planets objects passed in arguement, returns true if any planet present in house
     * @param planets list of planets objects to search from
     * @return true if any planet present in house from list
     */
    public boolean containsAny(Planet...planets) {
        return !getPlanetsPlaced(planets).isEmpty();
    }

    /**
     * returns true if any exalted planet present in house
     * @return true if any exalted planet present in house
     */
    public boolean containsExaltedPlanet() {
        for(Planet planet: planets) {
            if(planet != null && planet.inExaltation()) {
                return true;
            }
        }
        return false;
    }

    /**
     * returns true if any debilitated planet present in house
     * @return true if any debilitated planet present in house
     */
    public boolean containsDebilitatedPlanet() {
        for(Planet planet: planets) {
            if(planet != null && planet.inDebilitation()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "H-" + number;
    }
}
