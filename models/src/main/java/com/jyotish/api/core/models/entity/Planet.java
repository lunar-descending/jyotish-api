package com.jyotish.api.core.models.entity;

import com.jyotish.api.core.models.input.PlanetData;
import com.jyotish.api.core.models.nakshtras.Nakshatra;
import com.jyotish.api.core.models.readers.PlanetAspect;
import com.jyotish.api.core.models.readers.PlanetSignDisposition;
import com.jyotish.api.core.models.readers.SignAspect;
import com.jyotish.api.core.models.signs.Sign;
import lombok.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.jyotish.api.core.models.VedicProperties.*;
import static com.jyotish.api.core.models.calc.Calculations.*;
import static com.jyotish.api.core.models.calc.ObjectUtils.*;

@Data
public class Planet {

    private Map<String, Set<String>> signDispositionMap = null;
    private Set<Integer> signAspects = null;
    private Set<Integer> planetAspects = null;

    public final String name;
    public Longitude longitude;
    public Longitude longitudeHouse;
    public boolean isDirect;
    public Sign sign;
    public House house;
    public Nakshatra nakshatra;
    public int padaNumber;

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

    public Planet(final String name, final PlanetData planetData) {
        this.name = name;
        this.longitude = planetData.getPlanetLongitudeMap().get(name);
        this.longitudeHouse = this.longitude.toHouseLongitude();
        this.isDirect = planetData.getPlanetLongitudeMap().get(name).isRetro();
        this.sign = new Sign(getSignNumberByLongitude(this.longitude));
        this.nakshatra = new Nakshatra(getNakshatraNumberByLongitude(this.longitude));
        this.padaNumber = getPlanetPadaNumber(this.longitude);
        this.house = getHouseDetails(planetData.getLagnaLongitude());
        this.signDispositionMap = PlanetSignDisposition.getSignDispositionMap().getOrDefault(getActualString(this.name), new HashMap<>());
        this.planetAspects = PlanetAspect.getPlanetAspects().getOrDefault(getActualString(this.name), new HashSet<>());
        this.signAspects = SignAspect.getSignAspects().getOrDefault(getActualString(this.sign.signData.signName), new HashSet<>());
    }

    private House getHouseDetails(Longitude lagnaLongitude) {
        int houseNumber = getHouseNumberByLongitude(lagnaLongitude, this.longitude);
        House house = new House();
        house.setNumber(houseNumber);
        house.setSign(this.sign);
        house.setRuler(this.sign.ruler);
        return house;
    }

    /**
     * returns true if passed planet in kendra from invoked planet
     *
     * @param planet planet reference
     * @return true if passed planet in kendra from invoked planet
     */
    public boolean inKendraFrom(final Planet planet) {
        int targetPlanetHouseNumber = planet.getHouse().getNumber();
        int sourcePlanetHouseNumber = getHouse().getNumber();
        int houseDifference = getHouseDistance(sourcePlanetHouseNumber, targetPlanetHouseNumber);
        return has(KENDRA_HOUSES, houseDifference);
    }

    /**
     * returns true if passed planet in trikona from invoked planet
     *
     * @param planet planet reference
     * @return true if passed planet in trikona from invoked planet
     */
    public boolean inTrikonaFrom(final Planet planet) {
        int targetPlanetHouseNumber = planet.getHouse().getNumber();
        int sourcePlanetHouseNumber = getHouse().getNumber();
        int houseDifference = getHouseDistance(sourcePlanetHouseNumber, targetPlanetHouseNumber);
        return has(TRIKONA_HOUSES, houseDifference);
    }

    /**
     * returns true if passed planet in kendra house (from lagna or ascendant)
     *
     * @return true if passed planet in kendra house (from lagna or ascendant)
     */
    public boolean inKendraHouse() {
        return has(KENDRA_HOUSES, this.house.number);
    }

    /**
     * returns true if passed planet in trikona house (from lagna or ascendant)
     *
     * @return true if passed planet in trikona house (from lagna or ascendant)
     */
    public boolean inTrikonaHouse() {
        return has(TRIKONA_HOUSES, this.house.number);
    }

    /**
     * returns true if passed planet in kendra or trikona house (from lagna or ascendant)
     *
     * @return true if passed planet in kendra or trikona house (from lagna or ascendant)
     */
    public boolean inKendraTrikonaHouse() {
        return inKendraHouse() || inTrikonaHouse();
    }

    /**
     * returns true if passed planet in dusthana house (from lagna or ascendant)
     *
     * @return true if passed planet in dusthana house (from lagna or ascendant)
     */
    public boolean inDusthanaHouse() {
        return has(DUSTHANA_HOUSES, this.house.number);
    }

    /**
     * returns true if passed planet in upachaya house (from lagna or ascendant)
     *
     * @return true if passed planet in upachaya house (from lagna or ascendant)
     */
    public boolean inUpachayaHouse() {
        return has(UPACHAYA_HOUSES, this.house.number);
    }

    /**
     * returns true if passed planet in exaltation sign
     *
     * @return true if passed planet in exaltation sign
     */
    public boolean inExaltation() {
        return signDispositionMap.getOrDefault(sign.signData.signName, new HashSet<>()).contains(EXALTATION);
    }

    /**
     * returns true if passed planet in own sign
     *
     * @return true if passed planet in own sign
     */
    public boolean inOwnHouse() {
        return signDispositionMap.getOrDefault(sign.signData.signName, new HashSet<>()).contains(OWN);
    }

    /**
     * returns true if passed planet in moolatrikona sign
     *
     * @return true if passed planet in moolatrikona sign
     */
    public boolean inMoolatrikonaHouse() {
        return signDispositionMap.getOrDefault(sign.signData.signName, new HashSet<>()).contains(MOOLATRIKONA);
    }

    /**
     * returns true if passed planet in debilitation sign
     *
     * @return true if passed planet in debilitation sign
     */
    public boolean inDebilitation() {
        return signDispositionMap.getOrDefault(sign.signData.signName, new HashSet<>()).contains(DEBILITATION);
    }

    /**
     * returns true if passed planet in exaltation or own or moolatrikona sign
     *
     * @return true if passed planet in exaltation or own or moolatrikona sign
     */
    public boolean inExaltationOwnMoolatrikona() {
        return inExaltation() || inOwnHouse() || inMoolatrikonaHouse();
    }

    /**
     * returns true if any of passed planet conjunct invoked planet
     *
     * @param planets planets array to check if any conjunct
     * @return true if any of passed planet conjunct invoked planet
     */
    public boolean conjunctAny(final Planet... planets) {
        if (planets == null) {
            return false;
        }
        for (Planet planet : planets) {
            if (house.number == planet.house.number) {
                return true;
            }
        }
        return false;
    }

    /**
     * returns true if any of passed planet conjunct invoked planet
     *
     * @param planets planets array to check if any conjunct
     * @return true if any of passed planet conjunct invoked planet
     */
    public boolean conjunctAny(final String... planets) {
        if (planets == null) {
            return false;
        }
        return this.house.containsAny(planets);
    }

    /**
     * returns true if passed house number is aspected by the planetary aspect
     *
     * @param houseNumber house number to check if aspected by planet
     * @return true if passed house number is aspected by the planetary aspect
     */
    public boolean isAspectingByPlanetDrishti(final int houseNumber) {
        int houseDistance = getHouseDistance(this.house.number, houseNumber);
        return planetAspects.contains(houseDistance);
    }

    /**
     * returns true if passed house is aspected by the planetary aspect
     *
     * @param house house to check if aspected by planet
     * @return true if passed house is aspected by the planetary aspect
     */
    public boolean isAspectingByPlanetDrishti(final House house) {
        return isAspectingByPlanetDrishti(house.number);
    }

    /**
     * returns true if passed sign is aspected by the planetary aspect
     *
     * @param sign sign to check if aspected by planet
     * @return true if passed sign is aspected by the planetary aspect
     */
    public boolean isAspectingByPlanetDrishti(final Sign sign) {
        int signDistance = getHouseDistance(this.sign.signData.number, sign.signData.number);
        return planetAspects.contains(signDistance);
    }

    /**
     * returns true if passed sign number is aspected by the sign based aspect
     *
     * @param signNumber sign number to check if aspected by planet
     * @return true if passed sign number is aspected by the sign based aspect
     */
    public boolean isAspectingBySignDrishti(final int signNumber) {
        return signAspects.contains(signNumber);
    }

    /**
     * returns true if passed house is aspected by the sign based aspect
     *
     * @param house house to check if aspected by planet
     * @return true if passed house is aspected by the sign based aspect
     */
    public boolean isAspectingBySignDrishti(final House house) {
        return isAspectingBySignDrishti(house.sign.signData.number);
    }

    /**
     * returns true if passed sign is aspected by the sign based aspect
     *
     * @param sign sign to check if aspected by planet
     * @return true if passed sign is aspected by the sign based aspect
     */
    public boolean isAspectingBySignDrishti(final Sign sign) {
        return isAspectingBySignDrishti(sign.signData.number);
    }

    @Override
    public String toString() {
        return name;
    }
}
