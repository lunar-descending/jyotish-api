package com.jyotish.api.core.util;

import com.jyotish.api.core.models.calc.ObjectUtils;
import com.jyotish.api.core.models.entity.House;
import com.jyotish.api.core.models.entity.Planet;
import com.jyotish.api.core.models.input.FlatPlanetData;
import com.jyotish.api.core.models.nakshtras.enums.NakshatraData;

import java.util.*;

import static com.jyotish.api.core.models.VedicProperties.*;

public class MiscRules {

    public String rule1(
            FlatPlanetData d1,
            FlatPlanetData d9
    ) {
        if (d9.house12.planets.contains(d9.venus)) {
            return "Venus in 12th House of D-9, marriage denied, unless chart is supporting strongly";
        }
        return "";
    }

    public String rule2(
            FlatPlanetData d1,
            FlatPlanetData d9
    ) {
        Planet lordOf9thInD9 = d9.getPlanetByName(d1.house9.sign.ruler.name);
        if (lordOf9thInD9.inExaltation()) {
            return String.format("D-1 9th Lord %s exalted in D-9 -> Great fortune and luck (bhagya), Good Dharma, Highly Auspicious", lordOf9thInD9.name);
        } else if (lordOf9thInD9.inOwnHouse() || lordOf9thInD9.inMoolatrikonaHouse()) {
            return String.format("D-1 9th Lord %s is in own sign or mooltrikona in D-9 -> Person has strong luck", lordOf9thInD9.name);
        } else if (lordOf9thInD9.inDebilitation()) {
            return String.format("D-1 9th Lord %s debilitated in D-9 -> Challenging life, Hard work, Loss of Fortune", lordOf9thInD9.name);
        }
        return "";
    }

    public String rule3(
            FlatPlanetData d1,
            FlatPlanetData d9
    ) {
        if (d9.house1.sign.ruler.inExaltation()) {
            return String.format("D-9 lagna lord %s exalted in D-9 -> Extremely Strong and Powerful chart (indicate personality), Good for general luck - health and dharma", d9.lagna.name);
        } else if (d9.house1.sign.ruler.inOwnHouse() || d9.house1.sign.ruler.inMoolatrikonaHouse()) {
            return String.format("D-9 lagna lord %s is in own sign or mooltrikona in D-9 -> Strong and determined person, Chart is overall good", d9.lagna.name);
        } else if (d9.house1.sign.ruler.inDebilitation()) {
            return String.format("D-1 lagna lord %s debilitated in D-9 -> Not good for health, weak constitution of body", d9.lagna.name);
        }
        return "";
    }

    public String rule4(
            FlatPlanetData d1,
            FlatPlanetData d9
    ) {
        House d9LagnaHouseInD1 = d1.getHouseBySign(d9.lagna.sign);
        if (d9LagnaHouseInD1.number == 6 || d9LagnaHouseInD1.number == 8 || d9LagnaHouseInD1.number == 12) {
            return "D-9 lagna sign in 6,8,12 of D-1 -> Will face major setbacks in life";
        } else if (d9LagnaHouseInD1.number == 1 || d9LagnaHouseInD1.number == 4 || d9LagnaHouseInD1.number == 7 || d9LagnaHouseInD1.number == 10) {
            return "D-9 lagna sign in 1,4,7,10 of D-1 -> Prosperous Life";
        } else if (d9LagnaHouseInD1.number == 5 || d9LagnaHouseInD1.number == 9) {
            return "D-9 lagna sign in 1,4,7,10 of D-1 -> Kingly Life - Uttam Rajyoga";
        }
        return "";
    }

    public String rule5(
            FlatPlanetData d1,
            FlatPlanetData d9
    ) {
        List<Planet> planetsIn10thD9 = d9.house10.planets;
        if (!planetsIn10thD9.isEmpty()) {
            String outcome = "";
            String[] naturalBeneficPlanets = {JUPITER, VENUS, MERCURY, MOON};
            for (Planet planet : planetsIn10thD9) {
                outcome = join("<br>",
                        outcome,
                        String.format("%s in 10th House of D-9, wealth giving combination", planet.name)
                );

                if (ObjectUtils.has(naturalBeneficPlanets, planet.name)) {
                    outcome = join("", outcome, ". planet is functional benefic - indicate strong wealth");
                } else {
                    outcome = join("", outcome, ". planet is functional malefic - indicate fluctuating wealth");
                    if (planet.inExaltationOwnMoolatrikona()) {
                        outcome = join(". ",
                                outcome,
                                "planet is in exaltation, ownsign or mooltrikona - indicate somewhat stable source of wealth"
                        );
                    } else if (planet.inDebilitation()) {
                        outcome = join(". ",
                                outcome,
                                "planet is in debilitation - indicate illegal means to acquire wealth or may lead to expenses"
                        );
                    }
                }
            }
            return outcome;
        }
        return "";
    }

    public String rule6(
            FlatPlanetData d1,
            FlatPlanetData d9
    ) {
        List<Planet> planetsIn7thD9 = d9.house7.planets;
        if (!planetsIn7thD9.isEmpty()) {
            String outcome = "";
            String[] naturalBeneficPlanets = {JUPITER, VENUS, MERCURY, MOON};
            for (Planet planet : planetsIn7thD9) {
                outcome = join("<br>",
                        outcome,
                        String.format("%s in 7th House of D-9, Impacting relationships", planet.name)
                );

                if (ObjectUtils.has(naturalBeneficPlanets, planet.name)) {
                    outcome = join(". ",
                            outcome,
                            "planet is functional benefic - support relationships"
                    );
                } else {
                    outcome = join(". ",
                            outcome,
                            "planet is functional malefic - will create related problems"
                    );
                }
            }
            return outcome;
        }
        return "";
    }

    public String rule7(
            FlatPlanetData d1,
            FlatPlanetData d9
    ) {
        List<Planet> planetsInVenusD9 = ObjectUtils.joinList(
                d9.getHouseBySign(2).planets,
                d9.getHouseBySign(7).planets
        );
        if (!planetsInVenusD9.isEmpty()) {
            String outcome = "";
            String[] naturalBeneficPlanets = {JUPITER, VENUS, MERCURY, MOON};
            for (Planet planet : planetsInVenusD9) {
                outcome = join("<br>",
                        outcome,
                        String.format("%s in Venus (Taurus or Libra) Navamsha, will help in relationship, also indicate good relationship (person likes the most) with indicating relation of planet", planet.name)
                );

                if (ObjectUtils.has(naturalBeneficPlanets, planet.name)) {
                    outcome = join(". ",
                            outcome,
                            "planet is functional benefic - will help to convert relationship to marriage easily"
                    );
                } else {
                    outcome = join(". ",
                            outcome,
                            "planet is functional malefic - will create challenges, problems and difficult to reunite"
                    );
                }
            }
            return outcome;
        }
        return "";
    }

    public String rule8(
            FlatPlanetData d1,
            FlatPlanetData d9
    ) {
        Planet saturnDispositorInD9 = d9.saturn.sign.ruler;
        Planet saturnDispositorInD1 = d1.getPlanetByName(saturnDispositorInD9.name);

        Map<String, String> planetRelationMap = new HashMap<>();
        planetRelationMap.put(SUN, "Father, Father-in-law");
        planetRelationMap.put(MOON, "Mother, Mother-in-law");
        planetRelationMap.put(MARS, "Friends, Siblings (Male)");
        planetRelationMap.put(MERCURY, "Friends, Siblings (Female), Aunt");
        planetRelationMap.put(JUPITER, "Consultant, Guru");
        planetRelationMap.put(VENUS, "Wife, Sisters");
        planetRelationMap.put(SATURN, "Colleague, Animals, Uncle");

        String outcome = String.format("Saturn disposed in %s Navamsha, %s (%s) can help to come out of suffering and pain",
                saturnDispositorInD9.name,
                saturnDispositorInD9.name,
                planetRelationMap.getOrDefault(saturnDispositorInD9.name, "")
        );

        if (saturnDispositorInD1.inExaltationOwnMoolatrikona()) {
            outcome = join(". ",
                    outcome,
                    String.format("%s is in good dignity in D-1, so effect would be more prominent and outcome would be positive", saturnDispositorInD9.name)
            );
        } else if (saturnDispositorInD1.inDebilitation()) {
            outcome = join(". ",
                    outcome,
                    String.format("%s is in bad dignity in D-1, so effect would be less prominent", saturnDispositorInD9.name)
            );
        } else {
            outcome = join(". ",
                    outcome,
                    String.format("%s is in average dignity in D-1, so effect would be of mixed kind", saturnDispositorInD9.name)
            );
        }

        return outcome;
    }

    public String rule9(
            FlatPlanetData d1,
            FlatPlanetData d9
    ) {
        Planet d9FirstLordinD1 = d1.getPlanetByName(d9.house1.sign.ruler.name);
        Planet d9FourthLordinD1 = d1.getPlanetByName(d9.house4.sign.ruler.name);
        Planet d9SeventhLordinD1 = d1.getPlanetByName(d9.house7.sign.ruler.name);
        Planet d9TenthLordinD1 = d1.getPlanetByName(d9.house10.sign.ruler.name);

        String outcome = "";

        //FIRST LORD

        if (d9FirstLordinD1.inKendraTrikonaHouse()) {
            outcome = join("<br>",
                    outcome,
                    String.format("<br>D9 1st Lord %s placed in good house (%s) in D1, body will not be troubled by diseases. good resistance power. good immunity",
                            d9FirstLordinD1.name,
                            "House-" + d9FirstLordinD1.house.number
                    )
            );
            if (d9FirstLordinD1.inExaltationOwnMoolatrikona()) {
                outcome = join(". ",
                        outcome,
                        String.format("%s placed in good sign (%s), excellent health is promised (shiva and sun blessing)",
                                d9FirstLordinD1.name,
                                d9FirstLordinD1.sign.signData.signName
                        )
                );
            } else if (d9FirstLordinD1.inDebilitation()) {
                outcome = join(". ",
                        outcome,
                        String.format("%s placed in debilitated sign (%s), person may deterioate health himself or herself (self harming)",
                                d9FirstLordinD1.name,
                                d9FirstLordinD1.sign.signData.signName
                        )
                );
            }
        } else if (d9FirstLordinD1.inDusthanaHouse()) {
            outcome += String.format("<br>D9 1st Lord %s placed in dusthana (%s) in D1, could face serious health issues",
                    d9FirstLordinD1.name,
                    "House-" + d9FirstLordinD1.house.number
            );
            if (d9FirstLordinD1.inExaltationOwnMoolatrikona()) {
                outcome = join(". ",
                        outcome,
                        String.format("%s placed in good sign (%s), issues will be overcome easily by remedy or care",
                                d9FirstLordinD1.name,
                                d9FirstLordinD1.sign.signData.signName
                        )
                );
            } else if (d9FirstLordinD1.inDebilitation()) {
                outcome = join(". ",
                        outcome,
                        String.format("%s placed in debilitated sign (%s), health maybe a serios concern (negative) in native's life",
                                d9FirstLordinD1.name,
                                d9FirstLordinD1.sign.signData.signName
                        )
                );
            }
        }

        //FOURTH LORD

        if (d9FourthLordinD1.inKendraTrikonaHouse()) {
            outcome = join("<br>",
                    outcome,
                    String.format("<br>D9 4th Lord %s placed in good house (%s) in D1, adds to peace loving nature of native, good spiritual progress.",
                            d9FourthLordinD1.name,
                            "House-" + d9FourthLordinD1.house.number
                    )
            );
            if (d9FourthLordinD1.inExaltationOwnMoolatrikona()) {
                outcome = join(". ",
                        outcome,
                        String.format("%s placed in good sign (%s), excellent spiritual progress is promised (ketu blessing)",
                                d9FourthLordinD1.name,
                                d9FourthLordinD1.sign.signData.signName
                        )
                );
            } else if (d9FourthLordinD1.inDebilitation()) {
                outcome = join(". ",
                        outcome,
                        String.format("%s placed in debilitated sign (%s), person may be deviated from spirtiual path or with time peace might be disturbed. need check here.",
                                d9FourthLordinD1.name,
                                d9FourthLordinD1.sign.signData.signName
                        )
                );
            }
        } else if (d9FourthLordinD1.inDusthanaHouse()) {
            outcome += String.format("<br>D9 4th Lord %s placed in dusthana (%s) in D1, person will not get peace easily or devoid of spiritual growth",
                    d9FourthLordinD1.name,
                    "House-" + d9FourthLordinD1.house.number
            );
            if (d9FourthLordinD1.inExaltationOwnMoolatrikona()) {
                outcome = join(". ",
                        outcome,
                        String.format("%s placed in good sign (%s), by efforts spiritual growth can be achieved",
                                d9FourthLordinD1.name,
                                d9FourthLordinD1.sign.signData.signName
                        )
                );
            } else if (d9FourthLordinD1.inDebilitation()) {
                outcome = join(". ",
                        outcome,
                        String.format("%s placed in debilitated sign (%s), family life will be destroyed. need to remediate",
                                d9FourthLordinD1.name,
                                d9FourthLordinD1.sign.signData.signName
                        )
                );
            }
        }

        //SEVENTH LORD

        if (d9SeventhLordinD1.inKendraTrikonaHouse()) {
            outcome = join("<br>",
                    outcome,
                    String.format("<br>D9 7th Lord %s placed in good house (%s) in D1, person will enjoy happiness from partner, is happy all the time",
                            d9SeventhLordinD1.name,
                            "House-" + d9SeventhLordinD1.house.number
                    )
            );
            if (d9SeventhLordinD1.inExaltationOwnMoolatrikona()) {
                outcome = join(". ",
                        outcome,
                        String.format("%s placed in good sign (%s), excellent joy and happiness is promised (higher order harmony)",
                                d9SeventhLordinD1.name,
                                d9SeventhLordinD1.sign.signData.signName
                        )
                );
            } else if (d9SeventhLordinD1.inDebilitation()) {
                outcome = join(". ",
                        outcome,
                        String.format("%s placed in debilitated sign (%s), maybe depressed at times but joyful nature",
                                d9SeventhLordinD1.name,
                                d9SeventhLordinD1.sign.signData.signName
                        )
                );
            }
        } else if (d9SeventhLordinD1.inDusthanaHouse()) {
            outcome += String.format("<br>D9 7th Lord %s placed in dusthana (%s) in D1, person will wander in search of joy and happiness",
                    d9SeventhLordinD1.name,
                    "House-" + d9SeventhLordinD1.house.number
            );
            if (d9SeventhLordinD1.inExaltationOwnMoolatrikona()) {
                outcome = join(". ",
                        outcome,
                        String.format("%s placed in good sign (%s), by efforts joy and happiness can be achieved",
                                d9SeventhLordinD1.name,
                                d9SeventhLordinD1.sign.signData.signName
                        )
                );
            } else if (d9SeventhLordinD1.inDebilitation()) {
                outcome = join(". ",
                        outcome,
                        String.format("%s placed in debilitated sign (%s), person may be devoid from happiness and joy. depressed",
                                d9SeventhLordinD1.name,
                                d9SeventhLordinD1.sign.signData.signName
                        )
                );
            }
        }

        //TENTH LORD

        if (d9TenthLordinD1.inKendraTrikonaHouse()) {
            outcome = join("<br>",
                    outcome,
                    String.format("<br>D9 10th Lord %s placed in good house (%s) in D1, person will enjoy wealth, will earn good and posses many assets",
                            d9TenthLordinD1.name,
                            "House-" + d9TenthLordinD1.house.number
                    )
            );
            if (d9TenthLordinD1.inExaltationOwnMoolatrikona()) {
                outcome = join(". ",
                        outcome,
                        String.format("%s placed in good sign (%s), excellent wealth is promised (Maha lakshmi blessing)",
                                d9TenthLordinD1.name,
                                d9TenthLordinD1.sign.signData.signName
                        )
                );
            } else if (d9TenthLordinD1.inDebilitation()) {
                outcome = join(". ",
                        outcome,
                        String.format("%s placed in debilitated sign (%s), expenditure will be more or wealth created by sinful means",
                                d9TenthLordinD1.name,
                                d9TenthLordinD1.sign.signData.signName
                        )
                );
            }
        } else if (d9TenthLordinD1.inDusthanaHouse()) {
            outcome += String.format("<br>D9 10th Lord %s placed in dusthana (%s) in D1, maybe devoid of income or wealth by bhagya",
                    d9TenthLordinD1.name,
                    "House-" + d9TenthLordinD1.house.number
            );
            if (d9TenthLordinD1.inExaltationOwnMoolatrikona()) {
                outcome = join(". ",
                        outcome,
                        String.format("%s placed in good sign (%s), by extreme hardork wealth can be accumulated",
                                d9TenthLordinD1.name,
                                d9TenthLordinD1.sign.signData.signName
                        )
                );
            } else if (d9TenthLordinD1.inDebilitation()) {
                outcome = join(". ",
                        outcome,
                        String.format("%s placed in debilitated sign (%s), wealth is denied - need remediation for sustainence (question on 2nd House)",
                                d9TenthLordinD1.name,
                                d9TenthLordinD1.sign.signData.signName
                        )
                );
            }
        }

        return outcome;
    }

    public String rule10(
            FlatPlanetData d1,
            FlatPlanetData d9
    ) {
        String outcome = "";

        if (SUN.equalsIgnoreCase(d1.house1.sign.ruler.name)
                || d1.house1.sign.ruler.house.planets.contains(d1.sun)) {
            outcome = join("<br>",
                    outcome,
                    "D1 LagnaLord conjunct sun (karaka), it makes a person intelligent. Brings in a lot of fortune and high status in life"
            );
        }

        if (JUPITER.equalsIgnoreCase(d1.house2.sign.ruler.name)
                || d1.house2.sign.ruler.house.planets.contains(d1.jupiter)) {
            outcome = join("<br>",
                    outcome,
                    "D1 2nd Lord conjunct jupiter (karaka), the person will have good support system from family and good finance."
            );
        }

        if (MARS.equalsIgnoreCase(d1.house3.sign.ruler.name)
                || d1.house3.sign.ruler.house.planets.contains(d1.mars)) {
            outcome = join("<br>",
                    outcome,
                    "D1 3rd Lord conjunct mars (karaka), the person will be bold, courageous, passionate, go-getter and be supported by siblings."
            );
        }

        if (MOON.equalsIgnoreCase(d1.house4.sign.ruler.name)
                || d1.house4.sign.ruler.house.planets.contains(d1.moon)) {
            outcome = join("<br>",
                    outcome,
                    "D1 4th Lord conjunct moon (karaka), the person will be peaceful, happy, enjoy all kinds of comforts, have a great relationship with the mother and a solid foundation in education."
            );
        }

        if (JUPITER.equalsIgnoreCase(d1.house5.sign.ruler.name)
                || d1.house5.sign.ruler.house.planets.contains(d1.jupiter)) {
            outcome = join("<br>",
                    outcome,
                    "D1 5th Lord conjunct jupiter (karaka), the person will be strong and full of wisdom. Will get happiness from children and have a positive outlook on life."
            );
        }

        if (SATURN.equalsIgnoreCase(d1.house6.sign.ruler.name)
                || d1.house6.sign.ruler.house.planets.contains(d1.saturn)) {
            outcome = join("<br>",
                    outcome,
                    "D1 6th Lord conjunct saturn (karaka), the person will destroy his enemies and will enjoy good health."
            );
        }

        if (VENUS.equalsIgnoreCase(d1.house7.sign.ruler.name)
                || d1.house7.sign.ruler.house.planets.contains(d1.venus)) {
            outcome = join("<br>",
                    outcome,
                    "D1 7th Lord conjunct venus (karaka), the person will enjoy the little things in life and have a wonderful spouse."
            );
        }

        if (SATURN.equalsIgnoreCase(d1.house8.sign.ruler.name)
                || d1.house8.sign.ruler.house.planets.contains(d1.saturn)) {
            outcome = join("<br>",
                    outcome,
                    "D1 8th Lord conjunct saturn (karaka), the person will enjoy inheritances."
            );
        }

        if (JUPITER.equalsIgnoreCase(d1.house9.sign.ruler.name)
                || d1.house9.sign.ruler.house.planets.contains(d1.jupiter)) {
            outcome = join("<br>",
                    outcome,
                    "D1 9th Lord conjunct jupiter (karaka), the person will enjoy good fortune, will do higher studies and will be approached by people for advice and support."
            );
        }

        if (MERCURY.equalsIgnoreCase(d1.house10.sign.ruler.name)
                || d1.house10.sign.ruler.house.planets.contains(d1.mercury)) {
            outcome = join("<br>",
                    outcome,
                    "D1 10th Lord conjunct mercury (karaka), the person will have a great career. Will have many opportunities to succeed in life."
            );
        }

        if (JUPITER.equalsIgnoreCase(d1.house11.sign.ruler.name)
                || d1.house11.sign.ruler.house.planets.contains(d1.jupiter)) {
            outcome = join("<br>",
                    outcome,
                    "D1 11th Lord conjunct jupiter (karaka), the person will have a lot of gains and will have the support of a huge network of friends and elder siblings."
            );
        }

        if (SATURN.equalsIgnoreCase(d1.house12.sign.ruler.name)
                || d1.house12.sign.ruler.house.planets.contains(d1.saturn)) {
            outcome = join("<br>",
                    outcome,
                    "D1 12th Lord conjunct saturn (karaka), the person will manage his expenses well and will not spend on unwanted things."
            );
        }

        return outcome;

    }

    public String rule11(
            FlatPlanetData d1,
            FlatPlanetData d9
    ) {
        String outcome = "";

        Planet lord3 = d1.house3.sign.ruler;
        Planet lord6 = d1.house6.sign.ruler;
        Planet lord10 = d1.house10.sign.ruler;
        Planet lord11 = d1.house11.sign.ruler;

        if (lord3.inUpachayaHouse()) {
            outcome = join("<br>",
                    outcome,
                    String.format("D-1 3rd Lord %s is in upachaya bhava, indicate skilled worker (improvised skill)", lord3.name)
            );
        }
        if (lord6.inUpachayaHouse()) {
            outcome = join("<br>",
                    outcome,
                    String.format("D-1 6th Lord %s is in upachaya bhava, indicate skilled worker (improvised skill)", lord6.name)
            );
        }
        if (lord10.inUpachayaHouse()) {
            outcome = join("<br>",
                    outcome,
                    String.format("D-1 10th Lord %s is in upachaya bhava, indicate skilled worker (improvised skill)", lord10.name)
            );
        }
        if (lord11.inUpachayaHouse()) {
            outcome = join("<br>",
                    outcome,
                    String.format("D-1 11th Lord %s is in upachaya bhava, indicate skilled worker (improvised skill)", lord11.name)
            );
        }

        if (lord3.house.planets.contains(lord6) || lord3.house.planets.contains(lord10) || lord3.house.planets.contains(lord11)) {
            outcome = join("<br>",
                    outcome,
                    String.format("D-1 Conjunction between 3rd Lord %s with another upachaya lord, indicate skilled worker (improvised skill)", lord3.name)
            );
        }

        if (lord6.house.planets.contains(lord3) || lord6.house.planets.contains(lord10) || lord6.house.planets.contains(lord11)) {
            outcome = join("<br>",
                    outcome,
                    String.format("D-1 Conjunction between 6th Lord %s with another upachaya lord, indicate skilled worker (improvised skill)", lord6.name)
            );
        }

        if (lord10.house.planets.contains(lord3) || lord10.house.planets.contains(lord6) || lord10.house.planets.contains(lord11)) {
            outcome = join("<br>",
                    outcome,
                    String.format("D-1 Conjunction between 10th Lord %s with another upachaya lord, indicate skilled worker (improvised skill)", lord10.name)
            );
        }

        if (lord11.house.planets.contains(lord3) || lord11.house.planets.contains(lord6) || lord11.house.planets.contains(lord10)) {
            outcome = join("<br>",
                    outcome,
                    String.format("D-1 Conjunction between 11th Lord %s with another upachaya lord, indicate skilled worker (improvised skill)", lord11.name)
            );
        }

        return outcome;
    }

    public String rule12(
            FlatPlanetData d1,
            FlatPlanetData d9
    ) {
        List<Planet> planetsIn12thFromA1 = ObjectUtils.joinList(
                d1.arudha1.house12.planets,
                Collections.singletonList(d1.arudha1.house12.sign.ruler)
        );

        Map<String, String> worshippingDieties = new HashMap<>();
        worshippingDieties.put(SUN, "Shiva, Surya");
        worshippingDieties.put(MOON, "Brahma");
        worshippingDieties.put(MARS, "Rudra, Hanuman");
        worshippingDieties.put(MERCURY, "Vishnu, Durga");
        worshippingDieties.put(JUPITER, "Vishnu");
        worshippingDieties.put(VENUS, "Lakshmi");
        worshippingDieties.put(SATURN, "Narayan, Kaali");

        String outcome = "";
        for (Planet planet : planetsIn12thFromA1) {
            outcome = join(", ",
                    outcome,
                    String.format("%s (%s) ", planet.name, worshippingDieties.getOrDefault(planet.name, ""))
            );
        }

        return String.format("To Overcome Losses and Secret Enemies :: worship planets :: %s", outcome);
    }

    public String rule13(
            FlatPlanetData d1,
            FlatPlanetData d9
    ) {
        int luckyNakshatra = (d1.moon.nakshatra.nakshatraData.number + 12) % 27;
        luckyNakshatra = luckyNakshatra == 0 ? 27 : luckyNakshatra;
        NakshatraData nakshatraData = NakshatraData.getNakshatraDataByNumber(luckyNakshatra);

        return join(". ",
                String.format("13th nakshatra from natal moon - %s (lord - %s) - Secret to Manifesting your desires", nakshatraData.nakshatraName, nakshatraData.nakshatraRuler),
                "<br>Moon transit over this - good for desire manifestation (materialistic)",
                "<br>Color Represented by lord - lucky (materialistic)",
                "<br>Day represented by lord - lucky (materialistic)",
                "<br>Person with moon in this nakshatra - lucky (materialistic)"
        );
    }

    public String rule14(
            FlatPlanetData d1,
            FlatPlanetData d9
    ) {
        Map<String, String> voiceImpression = new HashMap<>();
        voiceImpression.put(SUN, "Appropriate Volume, Commanding");
        voiceImpression.put(MARS, "High Volume");
        voiceImpression.put(SATURN, "Low Volume");
        voiceImpression.put(JUPITER, "Perfect pitch and volume");
        voiceImpression.put(MOON, "Melodious");
        voiceImpression.put(VENUS, "Beautiful and Calm");
        voiceImpression.put(RAHU, "Weird, Heavy pitch, Foreigner's Tone");
        voiceImpression.put(KETU, "Sometime low Volume and then abruptly high, breaks in speech");

        List<Planet> planets2ndFromMercury = d1.mercury.house2.planets;

        String outcome = "";

        for (Planet planet : planets2ndFromMercury) {
            outcome = join("<br>",
                    outcome,
                    String.format("%s is present in 2nd from mercury (voice feature), indicate: %s",
                            planet.name,
                            voiceImpression.getOrDefault(planet.name, "")
                    )
            );
        }

        return outcome;
    }

    private String join(String delimiter, String... predictions) {
        if (predictions == null || predictions.length == 0) {
            return "";
        }
        if (delimiter == null) {
            delimiter = "";
        }
        List<String> validStrings = new ArrayList<>();
        for (String prediction : predictions) {
            if (prediction != null && !prediction.trim().isEmpty()) {
                validStrings.add(prediction);
            }
        }
        return String.join(delimiter, validStrings);
    }

}

















