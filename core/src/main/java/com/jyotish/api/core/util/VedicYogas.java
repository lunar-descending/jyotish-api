package com.jyotish.api.core.util;

import com.jyotish.api.core.models.input.FlatPlanetData;
import com.jyotish.api.core.models.entity.Planet;
import com.jyotish.api.core.models.input.PersonalData;
import com.jyotish.api.core.models.input.PlanetData;
import com.jyotish.api.core.models.yoga.Yoga;
import com.jyotish.api.core.models.yoga.Yogas;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static com.jyotish.api.core.models.VedicProperties.*;
import static com.jyotish.api.core.models.calc.ObjectUtils.*;
import static com.jyotish.api.core.models.yoga.Yogas.*;
import static java.util.Collections.emptyList;

public class VedicYogas extends FlatPlanetData {

    public VedicYogas(PlanetData planetData) {
        super(planetData);
    }

    public List<Yoga> getAllYogas() {
        List<Yoga> yogas = new ArrayList<>();
        try {
            for (Method method : getClass().getDeclaredMethods()) {
                if (method.getName().toLowerCase().startsWith("check") && method.getName().toLowerCase().endsWith("yoga")) {
                    Yoga yoga = (Yoga) method.invoke(this);
                    yogas.add(yoga);
                }
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return yogas;
    }

    public Yoga checkGajKesariYoga() {
        boolean isYogaPresent = moon.inKendraFrom(jupiter);
        return getYoga(GAJKESARI_YOGA, isYogaPresent, toList(moon, jupiter));
    }

    public Yoga checkSunaphaYoga() {
        String[] planetsWhichMayFormYoga = {JUPITER, VENUS, MERCURY, MARS, SATURN};
        List<Planet> planetsFormingYoga = moon.house2.getPlanetsPlaced(planetsWhichMayFormYoga);
        return getYoga(SUNAPHA_YOGA, hasAny(planetsFormingYoga), planetsFormingYoga);
    }

    public Yoga checkAnaphaYoga() {
        String[] planetsWhichMayFormYoga = {JUPITER, VENUS, MERCURY, MARS, SATURN};
        List<Planet> planetsFormingYoga = moon.house12.getPlanetsPlaced(planetsWhichMayFormYoga);
        return getYoga(ANAPHA_YOGA, hasAny(planetsFormingYoga), planetsFormingYoga);
    }

    public Yoga checkDhurdhuraYoga() {
        Yoga anaphaYoga = checkAnaphaYoga();
        Yoga sunaphaYoga = checkSunaphaYoga();

        boolean isPresentInHoroscope = anaphaYoga.isPresentInHoroscope() && sunaphaYoga.isPresentInHoroscope();
        List<Planet> planetsFormingYoga = new ArrayList<>();

        if(isPresentInHoroscope) {
            planetsFormingYoga.addAll(anaphaYoga.getPlanetsAssociated());
            planetsFormingYoga.addAll(sunaphaYoga.getPlanetsAssociated());
        }

        return getYoga(DHURDHURA_YOGA, isPresentInHoroscope, planetsFormingYoga);
    }

    public Yoga checkKemadrumaYoga() {
        List<Planet> planetsKendraFromMoon = joinList(
                moon.house4.planets,
                moon.house7.planets,
                moon.house10.planets
        );

        List<Planet> planetsInMoonHouseSecondTwelthHouseFromMoon = joinList(
                moon.house.planets,
                moon.house2.planets,
                moon.house12.planets
        );

        planetsInMoonHouseSecondTwelthHouseFromMoon.remove(moon);

        boolean isPresent = hasNotAny(planetsInMoonHouseSecondTwelthHouseFromMoon);
        boolean isCancelled = isPresent && hasAny(planetsKendraFromMoon);

        return getYoga(KEMADRUMA_YOGA, isPresent, emptyList(), isCancelled, planetsKendraFromMoon);
    }

    public Yoga checkChandraMangalaYoga() {
        boolean isYogaPresent = mars.house.number == moon.house.number ||
                Math.abs(mars.house.number - moon.house.number) == 6;
        return getYoga(CHANDRA_MANGALA_YOGA, isYogaPresent, toList(moon, mars));
    }

    public Yoga checkAdhiYoga() {
        String[] planetsFormingYoga = {JUPITER, VENUS, MERCURY};

        List<Planet> planetsFiltered = joinList(
                moon.house6.getPlanetsPlaced(planetsFormingYoga),
                moon.house7.getPlanetsPlaced(planetsFormingYoga),
                moon.house8.getPlanetsPlaced(planetsFormingYoga)
        );

        return getYoga(ADHI_YOGA, hasAny(planetsFiltered), planetsFiltered);
    }

    public Yoga checkChatussagaraYoga() {
        boolean isYogaPresent = hasAny(house1.planets)
                && hasAny(house4.planets)
                && hasAny(house7.planets)
                && hasAny(house10.planets);

        List<Planet> planetsAssociated = new ArrayList<>();

        if(isYogaPresent) {
            planetsAssociated.addAll(house1.planets);
            planetsAssociated.addAll(house4.planets);
            planetsAssociated.addAll(house7.planets);
            planetsAssociated.addAll(house10.planets);
            planetsAssociated.remove(lagna);
            if(planetsAssociated.isEmpty()) {
                isYogaPresent = false;
            }
        }

        return getYoga(CHATUSSAGARA_YOGA, isYogaPresent, planetsAssociated);
    }

    public Yoga checkVasumathiYoga() {
        String[] planetsWhichMayFormYoga = {JUPITER, VENUS, MERCURY, MOON};
        List<Planet> planetsFiltered = joinList(
                house3.getPlanetsPlaced(planetsWhichMayFormYoga),
                house6.getPlanetsPlaced(planetsWhichMayFormYoga),
                house10.getPlanetsPlaced(planetsWhichMayFormYoga),
                house11.getPlanetsPlaced(planetsWhichMayFormYoga)
        );
        return getYoga(VASUMATHI_YOGA, hasAny(planetsFiltered), planetsFiltered);
    }

    public Yoga checkRajalakshanaYoga() {
        String[] planetsWhichMayFormYoga = {JUPITER, VENUS, MERCURY, MOON};
        List<Planet> planetsFiltered = joinList(
                house1.getPlanetsPlaced(planetsWhichMayFormYoga),
                house4.getPlanetsPlaced(planetsWhichMayFormYoga),
                house7.getPlanetsPlaced(planetsWhichMayFormYoga),
                house10.getPlanetsPlaced(planetsWhichMayFormYoga)
        );
        return getYoga(RAJALAKSHANA_YOGA, hasAny(planetsFiltered), planetsFiltered);
    }

    public Yoga checkVanchanaChoraBheethiYoga() {
        String[] planetsWhichMayFormYoga = {SATURN, RAHU, KETU};
        List<Planet> planetsFiltered = joinList(
                house1.sign.ruler.house.getPlanetsPlaced(planetsWhichMayFormYoga),
                house1.getPlanetsPlaced(planetsWhichMayFormYoga)
        );
        return getYoga(VANCHANA_CHORA_BHEETHI_YOGA, hasAny(planetsFiltered), planetsFiltered);
    }

    public Yoga checkSakataYoga() {
        boolean isYogaPresent = hasAny(moon.house6.getPlanetsPlaced(JUPITER))
                || hasAny(moon.house8.getPlanetsPlaced(JUPITER))
                || hasAny(moon.house12.getPlanetsPlaced(JUPITER));
        return getYoga(SAKATA_YOGA, isYogaPresent, toList(moon, jupiter));
    }

    public Yoga checkAmalaYoga() {
        String[] planetsWhichMayFormYoga = {JUPITER, VENUS, MERCURY, MOON};
        List<Planet> planetsFiltered = joinList(
                house10.getPlanetsPlaced(planetsWhichMayFormYoga),
                moon.house10.getPlanetsPlaced(planetsWhichMayFormYoga)
        );
        return getYoga(AMALA_YOGA, hasAny(planetsFiltered), planetsFiltered);
    }

    public Yoga checkParvataYoga() {
        Yoga rajLakshanaYoga = checkRajalakshanaYoga();

        String[] naturalBeneficPlanets = {JUPITER, VENUS, MERCURY, MOON};
        String[] naturalMaleficPlanets = {SUN, MARS, SATURN, RAHU, KETU};

        List<Planet> planetsIn6And8 = joinList(house6.planets, house8.planets);
        List<Planet> maleficPlanetsIn6And8 = joinList(
                house6.getPlanetsPlaced(naturalMaleficPlanets),
                house8.getPlanetsPlaced(naturalMaleficPlanets)
        );

        boolean does6And8HouseHaveBeneficsOnly = hasNotAny(maleficPlanetsIn6And8);
        boolean does6And8HouseEmpty = hasNotAny(planetsIn6And8);

        boolean isYogaPresent = rajLakshanaYoga.isPresentInHoroscope() && (does6And8HouseHaveBeneficsOnly || does6And8HouseEmpty);

        List<Planet> planetsAssociated = joinList(
                rajLakshanaYoga.getPlanetsAssociated(),
                house6.getPlanetsPlaced(naturalBeneficPlanets),
                house8.getPlanetsPlaced(naturalBeneficPlanets)
        );

        return getYoga(PARVATA_YOGA, isYogaPresent, planetsAssociated);
    }

    public Yoga checkKahalaYoga() {
        boolean isYogaPresent = house4.sign.ruler.inKendraFrom(house9.sign.ruler)
                && house1.sign.ruler.inKendraTrikonaHouse()
                && house1.sign.ruler.inExaltationOwnMoolatrikona();

        return getYoga(KAHALA_YOGA, isYogaPresent, toList(house1.sign.ruler, house4.sign.ruler, house9.sign.ruler));
    }

    public Yoga checkVesiYoga() {
        String[] planetsWhichMayFormYoga = {JUPITER, VENUS, MERCURY, MARS, SATURN};
        List<Planet> planetsFormingYoga = sun.house2.getPlanetsPlaced(planetsWhichMayFormYoga);
        return getYoga(VESI_YOGA, hasAny(planetsFormingYoga), planetsFormingYoga);
    }

    public Yoga checkVasiYoga() {
        String[] planetsWhichMayFormYoga = {JUPITER, VENUS, MERCURY, MARS, SATURN};
        List<Planet> planetsFormingYoga = sun.house12.getPlanetsPlaced(planetsWhichMayFormYoga);
        return getYoga(VASI_YOGA, hasAny(planetsFormingYoga), planetsFormingYoga);
    }

    public Yoga checkObhayachariYoga() {
        Yoga vesiYoga = checkVesiYoga();
        Yoga vasiYoga = checkVasiYoga();

        boolean isPresentInHoroscope = vesiYoga.isPresentInHoroscope() && vasiYoga.isPresentInHoroscope();

        List<Planet> planetsFormingYoga = new ArrayList<>();

        if(isPresentInHoroscope) {
            planetsFormingYoga.addAll(vesiYoga.getPlanetsAssociated());
            planetsFormingYoga.addAll(vasiYoga.getPlanetsAssociated());
        }

        return getYoga(OBHAYACHARI_YOGA, isPresentInHoroscope, planetsFormingYoga);
    }

    public Yoga checkHamsaYoga() {
        boolean isYogaPresent = jupiter.inKendraHouse() && jupiter.inExaltationOwnMoolatrikona();
        return getYoga(HAMSA_YOGA, isYogaPresent, toList(jupiter));
    }

    public Yoga checkMalavyaYoga() {
        boolean isYogaPresent = venus.inKendraHouse() && venus.inExaltationOwnMoolatrikona();
        return getYoga(MALAVYA_YOGA, isYogaPresent, toList(venus));
    }

    public Yoga checkSasaYoga() {
        boolean isYogaPresent = saturn.inKendraHouse() && saturn.inExaltationOwnMoolatrikona();
        return getYoga(SASA_YOGA, isYogaPresent, toList(saturn));
    }

    public Yoga checkRuchakaYoga() {
        boolean isYogaPresent = mars.inKendraHouse() && mars.inExaltationOwnMoolatrikona();
        return getYoga(RUCHAKA_YOGA, isYogaPresent, toList(mars));
    }

    public Yoga checkBhadraYoga() {
        boolean isYogaPresent = mercury.inKendraHouse() && mercury.inExaltationOwnMoolatrikona();
        return getYoga(BHADRA_YOGA, isYogaPresent, toList(mercury));
    }

    public Yoga checkBudhaAdityaYoga() {
        boolean isYogaPresent = mercury.house.number == sun.house.number;
        return getYoga(BUDHA_ADITYA_YOGA, isYogaPresent, toList(sun, mercury));
    }

    public Yoga checkMahabhagyaYoga() {
        Integer[] oddSigns = {1, 3, 5, 7, 9, 11};
        Integer[] evenSigns = {2, 4, 6, 8, 10, 12};

        Integer[] dayTimeSunHouses = {1, 12, 11, 10, 9, 8};
        Integer[] nightTimeSunHouses = {7, 6, 5, 4, 3, 2};

        boolean isYogaPresent = false;

        if(planetData.getBirthData().getPersonalData().getGender() == PersonalData.Gender.MALE) {
            isYogaPresent = has(dayTimeSunHouses, sun.house.number)
                    && has(oddSigns, sun.sign.signData.number)
                    && has(oddSigns, moon.sign.signData.number)
                    && has(oddSigns, lagna.sign.signData.number);
        } else if (planetData.getBirthData().getPersonalData().getGender() == PersonalData.Gender.FEMALE) {
            isYogaPresent = has(nightTimeSunHouses, sun.house.number)
                    && has(evenSigns, sun.sign.signData.number)
                    && has(evenSigns, moon.sign.signData.number)
                    && has(evenSigns, lagna.sign.signData.number);
        }

        return getYoga(MAHABHAGYA_YOGA, isYogaPresent, toList(sun, moon, lagna));
    }

    public Yoga checkPushkalaYoga() {
        Planet moonDispositor = moon.house.sign.ruler;

        boolean isLagnaLordConjuctMoon = house1.sign.ruler.house.number == moon.house.number;
        boolean isMoonDispositorAspectingLagna = moonDispositor.isAspectingByPlanetDrishti(house1);
        boolean isMoonDispositorStrong = moonDispositor.inKendraTrikonaHouse() || moonDispositor.inExaltationOwnMoolatrikona();

        boolean isYogaPresent = isLagnaLordConjuctMoon && isMoonDispositorAspectingLagna && isMoonDispositorStrong;

        return getYoga(PUSHKALA_YOGA, isYogaPresent, toList(lagna.sign.ruler, moonDispositor, moon));
    }

    private Yoga getYoga(
            Yogas yogas,
            boolean isPresent,
            List<Planet> planetsAssociated,
            boolean isCancelled,
            List<Planet> cancellingPlanets
    ) {
        Yoga yoga = new Yoga();
        yoga.setYogaName(yogas.toString());
        yoga.setPresentInHoroscope(isPresent);
        yoga.setPlanetsAssociated(planetsAssociated);
        yoga.setYogaCancelled(isCancelled);
        yoga.setPlanetsCancelling(cancellingPlanets);
        return yoga;
    }

    private Yoga getYoga(
            Yogas yogas,
            boolean isPresent,
            List<Planet> planetsAssociated
    ) {
        return getYoga(yogas, isPresent, planetsAssociated, false, new ArrayList<>());
    }

}
