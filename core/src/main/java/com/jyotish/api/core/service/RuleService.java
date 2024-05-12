package com.jyotish.api.core.service;

import com.jyotish.api.core.ComputeHoroscope;
import com.jyotish.api.core.models.input.BirthData;
import com.jyotish.api.core.models.input.FlatPlanetData;
import com.jyotish.api.core.models.input.PlanetData;
import com.jyotish.api.core.util.MiscRules;
import com.jyotish.api.core.util.PredictionRulesSet1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleService {

    @Autowired
    private ComputeHoroscope computeHoroscope;

    public String apply(BirthData birthData) {
        PlanetData mainPlanetData = computeHoroscope.getPlanetData(birthData);
        PlanetData navamshaPlanetData = computeHoroscope.convertToNavamshaPlanetData(mainPlanetData);

        FlatPlanetData mainPlanetFlatData = new FlatPlanetData(mainPlanetData);
        FlatPlanetData navamshaPlanetFlatData = new FlatPlanetData(navamshaPlanetData);

        PredictionRulesSet1 rules1 = new PredictionRulesSet1();

        return String.join("<br>",
                String.format("<br><b>Rule1 ::</b> <br> %s", rules1.rule1(mainPlanetFlatData, navamshaPlanetFlatData)),
                String.format("<br><b>Rule2 ::</b> <br> %s", rules1.rule2(mainPlanetFlatData, navamshaPlanetFlatData)),
                String.format("<br><b>Rule3 ::</b> <br> %s", rules1.rule3(mainPlanetFlatData, navamshaPlanetFlatData)),
                String.format("<br><b>Rule5 ::</b> <br> %s", rules1.rule5(mainPlanetFlatData, navamshaPlanetFlatData))
        );
    }

    public String misc(BirthData birthData) {
        PlanetData mainPlanetData = computeHoroscope.getPlanetData(birthData);
        PlanetData navamshaPlanetData = computeHoroscope.convertToNavamshaPlanetData(mainPlanetData);

        FlatPlanetData mainPlanetFlatData = new FlatPlanetData(mainPlanetData);
        FlatPlanetData navamshaPlanetFlatData = new FlatPlanetData(navamshaPlanetData);

        MiscRules rules = new MiscRules();

        return String.join("<br>",
                String.format("<br><br><b>Rule1 ::</b> <br> %s", rules.rule1(mainPlanetFlatData, navamshaPlanetFlatData)),
                String.format("<br><br><b>Rule2 ::</b> <br> %s", rules.rule2(mainPlanetFlatData, navamshaPlanetFlatData)),
                String.format("<br><br><b>Rule3 ::</b> <br> %s", rules.rule3(mainPlanetFlatData, navamshaPlanetFlatData)),
                String.format("<br><br><b>Rule4 ::</b> <br> %s", rules.rule4(mainPlanetFlatData, navamshaPlanetFlatData)),
                String.format("<br><br><b>Rule5 ::</b> <br> %s", rules.rule5(mainPlanetFlatData, navamshaPlanetFlatData)),
                String.format("<br><br><b>Rule6 ::</b> <br> %s", rules.rule6(mainPlanetFlatData, navamshaPlanetFlatData)),
                String.format("<br><br><b>Rule7 ::</b> <br> %s", rules.rule7(mainPlanetFlatData, navamshaPlanetFlatData)),
                String.format("<br><br><b>Rule8 ::</b> <br> %s", rules.rule8(mainPlanetFlatData, navamshaPlanetFlatData)),
                String.format("<br><br><b>Rule9 ::</b> <br> %s", rules.rule9(mainPlanetFlatData, navamshaPlanetFlatData)),
                String.format("<br><br><b>Rule10 ::</b> <br> %s", rules.rule10(mainPlanetFlatData, navamshaPlanetFlatData)),
                String.format("<br><br><b>Rule11 ::</b> <br> %s", rules.rule11(mainPlanetFlatData, navamshaPlanetFlatData)),
                String.format("<br><br><b>Rule12 ::</b> <br> %s", rules.rule12(mainPlanetFlatData, navamshaPlanetFlatData)),
                String.format("<br><br><b>Rule13 ::</b> <br> %s", rules.rule13(mainPlanetFlatData, navamshaPlanetFlatData))
        );
    }

}
