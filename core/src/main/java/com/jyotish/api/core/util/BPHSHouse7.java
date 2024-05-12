package com.jyotish.api.core.util;

import com.jyotish.api.core.models.input.FlatPlanetData;

import static com.jyotish.api.core.models.VedicProperties.*;

public class BPHSHouse7 {

    public String rule1(
            FlatPlanetData d1,
            FlatPlanetData d9
    ) {
        if (d1.house4.containsAny(NATURAL_MALEFICS)) {
            return "Malefic in 4th house - difficulty in mating/making sexual relations";
        }
        return EMPTY;
    }

    public String rule2(
            FlatPlanetData d1,
            FlatPlanetData d9
    ) {
        if (d1.house10.containsExaltedPlanet()) {
            return "exalted planet in 10th House - good profession and marriage ceremony";
        }
        return "";
    }

    /**
     * 7th lord in his own sign or exaltation or moolatrikona sign
     * one will derive full happiness through his wife (and marriage)
     */
    public String rule3(
            FlatPlanetData d1,
            FlatPlanetData d9
    ) {
        if (d1.house7.ruler.inExaltationOwnMoolatrikona()) {
            return "7th lord in exaltation or own or moolatrikona sign - derive full happiness through his partner (and marriage)";
        }
        return "";
    }


    /**
     * 7th lord or venus in the 6th, 8th or 12th
     * the wife/husband will be sickly
     * This does not apply to own house or exaltation
     */
    public String rule4(
            FlatPlanetData d1,
            FlatPlanetData d9
    ) {
        if (d1.house7.ruler.inDusthanaHouse() && !d1.house7.ruler.inExaltationOwnMoolatrikona()) {
            return "7th lord in 6th, 8th or 12th and not in own or exaltation or moolatrikona sign - the wife/husband will be sickly";
        }
        if (d1.venus.inDusthanaHouse() && !d1.venus.inExaltationOwnMoolatrikona()) {
            return "venus in 6th, 8th or 12th and not in own or exaltation or moolatrikona sign - the wife/husband will be sickly";
        }
        return "";
    }


    /**
     * Venus in 7th - exceedingly libidinous
     */
    public String rule5(
            FlatPlanetData d1,
            FlatPlanetData d9
    ) {
        if (d1.house7.containsAny(VENUS)) {
            return "Venus in 7th House - exceedingly libidinous";
        }
        return "";
    }

    /**
     * Venus conjunct malefic in any house will cause-loss of wife
     */
    public String rule6(
            FlatPlanetData d1,
            FlatPlanetData d9
    ) {
        if (d1.venus.conjunctAny(NATURAL_MALEFICS)) {
            return "Venus conjunct malefic in any house will cause-loss of wife";
        }
        return "";
    }

    /**
     * 7th lord in exalted/own sign + conjunct with benefic - wealthy, honourable happy and fortunate
     * 7th lord in exaltation - indicate many partner
     * 7th lord in debility + conjunct with malefic - sick partner due to which many partner
     */
    public String rule7(
            FlatPlanetData d1,
            FlatPlanetData d9
    ) {
        String prediction = "";

        if (d1.house7.ruler.inExaltationOwnMoolatrikona() && d1.house7.ruler.conjunctAny(NATURAL_BENEFICS)) {
            prediction = String.join("<br>", prediction,
                    String.format("7th lord (%s) in exalted/own sign and conjunct with benefic - wealthy, honourable happy and fortunate",
                            d1.house7.ruler.name
                    )
            );
        }

        if (d1.house7.ruler.inExaltation()) {
            prediction = String.join("<br>", prediction,
                    String.format("7th lord (%s) in exalted sign - indicate many partner",
                            d1.house7.ruler.name
                    )
            );
        }

        if (d1.house7.ruler.inDebilitation() && d1.house7.ruler.conjunctAny(NATURAL_MALEFICS)) {
            prediction = String.join("<br>", prediction,
                    String.format("7th lord (%s) in debilitated sign - sick partner due to which many partner",
                            d1.house7.ruler.name
                    )
            );
        }
        return prediction;
    }


}
