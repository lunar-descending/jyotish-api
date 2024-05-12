package com.jyotish.api.core.util;

import com.jyotish.api.core.models.calc.ObjectUtils;
import com.jyotish.api.core.models.entity.House;
import com.jyotish.api.core.models.entity.Planet;
import com.jyotish.api.core.models.input.FlatPlanetData;

import java.util.*;

public class PredictionRulesSet1 {

    public String rule1(
            FlatPlanetData d1,
            FlatPlanetData d9
    ) {
        /**
         * D9 (1,5,9) - true nature
         */
        List<Planet> d9Trine = ObjectUtils.joinList(d9.house1.planets, d9.house5.planets, d9.house9.planets);
        return join("<br>",
                d9Trine.contains(d9.sun) ? "center of attraction, high authority, high status, leader, short tempered" : "",
                d9Trine.contains(d9.moon) ? "unique voice, emotional, caring, no enemies, spendthrift" : "",
                d9Trine.contains(d9.mars) ? "very short tempered, soldier, cannot be defeated, (male) good looking" : "",
                d9Trine.contains(d9.jupiter) ? "saint, very stable mind, extreme knowledgeable" : "",
                d9Trine.contains(d9.mercury) ? "business-man - mean, communicative, childish, smiling" : "",
                d9Trine.contains(d9.venus) ? "perfectionist, artist, creator, (female) good looking" : "",
                d9Trine.contains(d9.saturn) ? "traditional, work oriented, true enemies, best judge" : "",
                d9Trine.contains(d9.rahu) ? "many followers, perfect in handling machines, inventor" : "",
                d9Trine.contains(d9.ketu) ? "priest, intelligent, perfect in computers, researcher" : ""
        );
    }

    public String rule2(
            FlatPlanetData d1,
            FlatPlanetData d9
    ) {
        /**
         * D9 (2,6,10) - arth trikona
         */
        List<Planet> d9Trine = ObjectUtils.joinList(d9.house2.planets, d9.house6.planets, d9.house10.planets);
        return join("<br>",
                d9Trine.contains(d9.sun) ? "government servant, royal wealth - via father" : "",
                d9Trine.contains(d9.moon) ? "liquid cash, assets, unstable carrer" : "",
                d9Trine.contains(d9.mars) ? "financial security, posses land, big house" : "",
                d9Trine.contains(d9.jupiter) ? "teacher, earn via knowledge, advisor" : "",
                d9Trine.contains(d9.mercury) ? "wealth for and via business, consultant, service sector" : "",
                d9Trine.contains(d9.venus) ? "earn by artistic talent, abundance after marriage, jewellery" : "",
                d9Trine.contains(d9.saturn) ? "earn in grief of others, hardworking" : "",
                d9Trine.contains(d9.rahu) ? "big unecessary spendings, good flow of money, poor savings" : "",
                d9Trine.contains(d9.ketu) ? "excellent carrer, average income, will spend in charity" : ""
        ).replaceAll("<br><br>", "<br>");
    }

    /**
     * physical body, complexion, apperance (D-9).
     * Rest all to be seen from D-1
     * If the As lord conjoins sun, or aspected by sun (even by rasi dristi) a good moral character, gives a happy and sunny disposition.
     * Sun having unobstructed argala on lagna or obstructing papargala gives fame
     * AK having unobstructed argala on lagna or obstructing papargala gives good health
     * Position of Sun and AK should be seen from the Arudha lagna to determine the status and position earned.
     */
    public String rule3(
            FlatPlanetData d1,
            FlatPlanetData d9
    ) {
        Map<Integer, String> physicalCharacteristicsBySign = new HashMap<>();
        physicalCharacteristicsBySign.put(1, "dry and lean body; powerful limbs; middle stature; triangular face; dark and thick eyebrows; long neck; dark or reddish complexion; rough, wiry and curly hair; broad shoulders;\n");
        physicalCharacteristicsBySign.put(2, "short, well-set and stumpy body; full face; small eyes; short thick neck; wide nostrils and mouth; lustrous face; large shoulders; short and thick hands; love curl on forehead;\n");
        physicalCharacteristicsBySign.put(3, "tall &. straight body; long and thin arms; nervous and fidgety (quick response); dark complexion; dark hair; hazel and bright eyes;\n");
        physicalCharacteristicsBySign.put(4, "moderate stature; round face; yellow/pale compleXion and dedicate features; small or flat nose; small eyes. Features of the Moon sign are pronounced here, but, in general the natives are timid;");
        physicalCharacteristicsBySign.put(5, "good stature; full body; well framed and upright; broad, well-set shoulders; bright brownish; oval face; ruddy complexion; dignified &. royal bearing;\n");
        physicalCharacteristicsBySign.put(6, "middle stature; slender; neat; dark or ruddy complexion; pleasing countenance; long nose;\n");
        physicalCharacteristicsBySign.put(7, "Fair or tall stature; graceful body; large hips; smooth hair; round and sweet face; reddish - white rich complexion; large eyes; good looking people; polite and graceful;\n");
        physicalCharacteristicsBySign.put(8, "Medium size; strong and robust body; broad face; dark complexion; dark and curly hair; dark and small eyes; shrewd look; peculiar gait;\n");
        physicalCharacteristicsBySign.put(9, "Tall stature; well formed body; oval face, high forehead, prominent nose and clear eyes; fair complexion; open hearted; good bearing;\n");
        physicalCharacteristicsBySign.put(10, "Medium stature; slender body; thin face, plain looking; long protruding chin; thin neck; dark hair and weak knees; emaciated figure;\n");
        physicalCharacteristicsBySign.put(11, "Middle stature; stout and robust body that is not internally strong; long face; clear complexion; hazel eyes;\n");
        physicalCharacteristicsBySign.put(12, "Short to middle stature; thick build; round shoulders; large face; fair or pale complexion; sleepy or fish like eyes; soft speech.\n");

        Map<Integer, String> natureBySign = new HashMap<>();
        natureBySign.put(1, "Energetic, impulsive, enthusiastic, courageous, outspoken, enterprising and able; prompt to react and brave, quick and rational decision-making although not cool, self-reliant, ambitious and confident; independent and reacts to restrictions or interference; emotional and generous; zealous and ascribing to groups; prone to accidents and errors due of impulse; tendency to dominate;");
        natureBySign.put(2, "Artistic, skilled, beautiful, melodious, elegant, cool and poised, firm, determined (rather stubborn), and harmonious. Very practical especially in evaluating people, project and ideas. Industrious, self - centered and respective. Cold hatred, passion and proneness to flattery are the failings.");
        natureBySign.put(3, "Intellectual, active mind, flexible, writing abilities, rational mind, fluent writing/ speech, very comprehensive and adaptable to all pursuits but changeable, irresolute and lacking self-confidence. Nervous, excessive worry and irritation are other weakspots. high spirited nature that is liable to excess and lacking continuity or will power. Linguistic and scientific skills will be a marked feature.");
        natureBySign.put(4, "Sensitive, receptive, emotional, novel, and sensational; affectionate, home loving, and easily influenced. Fantastic and active imagination, good anticipation, prudent, good forethought and planning and caution are the strong features of a cancerian. A good sense of value and economy add to the natural ability for trade and business arrangement. The practical mind works well towards a good reputation and fame/defame depends on the Moon sign and influences on it.");
        natureBySign.put(5, "Honourable, magnanimous, candid, generous, lion hearted, dignified, confident, ambitious and proud. Fondness for display could reach ridiculous proportion if Leo is afflicted. Faithful and warm, leadership is often justified by matching abilities like comparison, helping nature and protective. Short-tempered and a great warrior, yet of a forgiving disposition. Very cheerful and sociable, with strong motion and fondness for luxury, the danger lies in the excesses.");
        natureBySign.put(6, "Quiet, modest, reserved, prudent, sympathetic, adaptable, and quite difficult to understand. Good mental abilities, studious and capable of planned action. Practical and methodical, attending to minute details, tendency to store, yet lacking in selfconfidence and easily defeated. A good follower.");
        natureBySign.put(7, "Courteous, gentle, affable, kind, affectionate, mixing, sociable, active, beauty conscious, artistic and intuitive. Fickle and changeable, the tastes vary with the mood. Companionship, partnership and marriage are the foundation stones for success.");
        natureBySign.put(8, "Strong, forceful, strong willed, brave, with extreme likes and dislikes. A good fighter who will neither shirk responsibility nor avoid controversy / dispute. Endurance, dignity and persistence are other positive characteristics of Scorpions. The weakness is anger, criticisms and sarcasm. Mystical and secretive, they make good astrologers and occultists.");
        natureBySign.put(9, "Honorable, open minded, frank, generous, sympathetic, truthful and just. Dignified and neat, life and surroundings are generally well ordered and beautiful. Independent minded, active and cheerful, they excel in sports and physical exercises. At times calm and balanced, but generally a follower of etiquette and well behaved. Versatile mind capable of higher learning and natural religiosity are the blessings of Jupiter.");
        natureBySign.put(10, "Steady, quiet, hard working, patient, tactful and subject to melancholy. They lack cheer and hope and are Iron believers I requiring scientific proof for everything. Practical, executive ability, selfrestraint, strong willed, ambitious just, economic, cautious and prudent. The Capricorn can deliver goods due to his steadfast determination. Rarely loved, generally respected, strong and a bad enemy.");
        natureBySign.put(11, "Strong willed, with fixed opinions, good intellectual abilities and tastes, scientific and literacy pursuits; open and truthful, silent but cheerful, good friends, strong memory and education. Inclination for occult and mysteries will be marked.");
        natureBySign.put(12, "Sympathetic, benevolent, generous, emotional and helpful, sociable & imaginative. All arts and imaginative writing are naturally suited. Easy going, good-natured and well disposed towards all. Pisceans are dignified, well behaved and ceremonious. Sensitive, intuitional and receptive, they have the healing touch.");

        Map<Integer, String> houseLordPlaced = new HashMap<>();
        houseLordPlaced.put(1, "the native is strong, fickle minded, brave, intelligent, with two or more wives!, mahapurush (great person) and blessed by the Sun God.");
        houseLordPlaced.put(2, "the native is scholarly, wealthy, happy, religious, honourable and passionate. Progeny bliss will be hard to obtain.");
        houseLordPlaced.put(3, "the native is courageous, wealthy, honourable, fortunate, intelligent, happy and passionate. Brothers will be helpful.");
        houseLordPlaced.put(4, "parental happiness is assured. Endowed with co-borns, properties, virtues and charm, the native leads a happy life. He works with a missionary zeal.");
        houseLordPlaced.put(5, "progeny bliss is not assured and the first child could be lost. the native is honourable, friendly, short-tempered and a favourite of the rulers, but tends to serve others.");
        houseLordPlaced.put(6, "debts and ill health are indicated. He will be troubled by enemies. He will be intelligent and courageous and if benefics aspect or during the period of lagnesh will destroy enemies, acquire wealth and a royal status. Early loss of spouse is indicated.");
        houseLordPlaced.put(7, "wife is short lived, more than one marriage and misfortunes are indicated. Unless the lagnesh is strong the native could become an ascetic or nomad. If strong, great wealth and prosperity are indicated");
        houseLordPlaced.put(8, "the native is learned, diseased, short-tempered, unfortunate, a gambler, profligate and disreputed. Married life will be miserable and wealth nil.");
        houseLordPlaced.put(9, "the native is fortunate, popular, skilled, eloquent with a happy family, wealth and blessed by Jupiter\n");
        houseLordPlaced.put(10, "the native is honourable, famous, self-made, blessed by his parents and Shri Ganesh, ambitious and prosperous.");
        houseLordPlaced.put(11, "the native will have a good income, reputation, love affairs/wives and will have excellant qualities of head and heart. He will be blessed by Sri Hari.");
        houseLordPlaced.put(12, "the native will be the cause of his own misfortunes, physical felicity will be lacking and temper will be short. He will be a spend thrift and expenses will depend on the nature of the ascendant/ twelfth Lord.");

        String physicalCharacteristicsByD1Lagna = physicalCharacteristicsBySign.get(d1.lagna.sign.signData.number);
        String physicalCharacteristicsByD9Lagna = physicalCharacteristicsBySign.get(d9.lagna.sign.signData.number);
        String natureByD1Lagna = natureBySign.get(d1.lagna.sign.signData.number);
        String natureByD9Lagna = natureBySign.get(d9.lagna.sign.signData.number);
        String housePlacementLagnaLord = houseLordPlaced.get(d1.lagna.sign.ruler.house.number);
        String housePlacementLagnaNakshtraLord = houseLordPlaced.get(d1.lagna.nakshatra.ruler.house.number);
        String pakaLagnaSignNature = natureBySign.get(d1.lagna.sign.ruler.house.sign.signData.number);


        return join("<br>",
                String.format("Physical Attributes :: D9-As :: %s", physicalCharacteristicsByD9Lagna),
                String.format("Nature :: D9-As :: %s", natureByD9Lagna),
                String.format("Physical Attributes :: D1-As :: %s", physicalCharacteristicsByD1Lagna),
                String.format("Nature :: D1-As :: %s", natureByD1Lagna),
                String.format("House Placememt (LagnaLord) :: D1-As :: %s", housePlacementLagnaLord),
                String.format("House Placement (NakshtraLord) :: D1-As :: %s", housePlacementLagnaNakshtraLord),
                String.format("PakaLagna :: D1-As :: %s", pakaLagnaSignNature),
                d1.sun.conjunctAny(d1.lagna.sign.ruler)
                        || d1.sun.isAspectingByPlanetDrishti(d1.lagna.sign.ruler.house)
                        || d1.sun.isAspectingBySignDrishti(d1.lagna.sign.ruler.sign)
                        ? "good moral character, happy and sunny disposition" : ""
        );
    }

    /**
     * d1 -> lord, placed planets, aspecting (graha)
     * AL -> lord, placed planets, aspecting (graha)
     * d9 -> lord, placed planets, aspecting (graha)
     * AK -> itself, lord, conjunct
     */
    public String rule4(
            FlatPlanetData d1,
            FlatPlanetData d9
    ) {
        List<Planet> planetsAffectingD1Lagna = ObjectUtils.joinList(
                Collections.singletonList(d1.house1.sign.ruler),
                d1.house1.planets,
                getPlanetsAspectingHouse(d1, d1.house1)
        );
        planetsAffectingD1Lagna.remove(d1.lagna);

        List<Planet> planetsAffectingD9Lagna = ObjectUtils.joinList(
                Collections.singletonList(d9.house1.sign.ruler),
                d9.house1.planets,
                getPlanetsAspectingHouse(d9, d9.house1)
        );
        planetsAffectingD9Lagna.remove(d9.lagna);

        List<Planet> atmakarakaRelations = ObjectUtils.joinList(
                Collections.singletonList(d1.atmakaraka),
                Collections.singletonList(d1.atmakaraka.sign.ruler),
                d1.atmakaraka.house.planets
        );

        return null;
    }

    /**
     * Vargottam (Only Sign)
     * Brahma(1,4,7,10 -> 1st), Vishnu(2,5,8,11 -> 5th), Mahesh (Shiva) (3,6,9,12 -> 9th)
     * Attributes/Bal in person
     * Lagna -> Long Life (Mrityunja - Shiva Bless)
     * Sun -> Leadership, knows self
     * Moon -> Nurturing, Stable Mind, Happy
     * Venus -> Beauty, Ojas, Fighter
     * Jupiter -> Intelligence, Understanding, Wisdom
     * Mercury -> Speech, Communication, Learning
     * Mars -> Physical Strength, Health
     * Saturn -> tremendous will power, can tolerate any sorrow, fully emotionless/expressionless
     * Rahu -> Crossing Limits in sign significations
     * Ketu -> Intuitive, Spiritual, Good in numbers, liberated
     */
    public String rule5(
            FlatPlanetData d1,
            FlatPlanetData d9
    ) {
        Map<String, String> vargottamaPredictions = new HashMap<>();
        vargottamaPredictions.put("lagna", "Long Life (Special Shiva Blessing)");
        vargottamaPredictions.put("sun", "Leader, knowledge of self");
        vargottamaPredictions.put("moon", "Nurturing, Stable Mind, Happy");
        vargottamaPredictions.put("mars", "Physical Strength, Health");
        vargottamaPredictions.put("mercury", "Speech, Communication, Learning");
        vargottamaPredictions.put("jupiter", "Intelligence, Understanding, Wisdom");
        vargottamaPredictions.put("venus", "Beauty, Ojas, excellent fighting spirit");
        vargottamaPredictions.put("saturn", "strong will power, can tolerate any sorrow, expressionless");
        vargottamaPredictions.put("rahu", "Crossing Limits in sign significations");
        vargottamaPredictions.put("ketu", "Intuitive, Spiritual, Good in numbers, liberated");

        String prediction = "";

        for (Planet planet : new Planet[]{
                d1.lagna,
                d1.sun, d1.moon, d1.mars,
                d1.mercury, d1.jupiter, d1.venus,
                d1.saturn, d1.rahu, d1.ketu,
        }) {
            String planetName = planet.name;
            int signD1 = planet.sign.signData.number;
            int signD9 = d9.getPlanetByName(planetName).sign.signData.number;
            if (signD1 == signD9) {

                String blessingGod = "";
                if (planet.sign.signData.isMovable()) {
                    blessingGod = "Brahma";
                } else if (planet.sign.signData.isFixed()) {
                    blessingGod = "Vishnu";
                } else if (planet.sign.signData.isDual()) {
                    blessingGod = "Shiva";
                }


                prediction = join("<br>",
                        prediction,
                        String.format("%s is vargottama - blessing of %s - %s <br>",
                                planetName,
                                blessingGod,
                                vargottamaPredictions.getOrDefault(planetName.trim().toLowerCase(), "")
                        )
                );
            }
        }
        return prediction;
    }

    private List<Planet> getPlanetsAspectingHouse(final FlatPlanetData planetData, final House house) {
        List<Planet> planets = new ArrayList<>();
        for (Planet planet : new Planet[]{
                planetData.sun, planetData.moon, planetData.mars,
                planetData.mercury, planetData.jupiter, planetData.venus,
                planetData.saturn, planetData.rahu, planetData.ketu
        }) {
            if (planet.isAspectingByPlanetDrishti(house)) {
                planets.add(planet);
            }
        }
        return planets;
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
