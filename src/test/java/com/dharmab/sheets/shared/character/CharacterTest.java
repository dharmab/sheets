package com.dharmab.sheets.shared.character;

import com.dharmab.sheets.server.character.Character;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class CharacterTest {
    private static final Map<Integer, Integer> abilityScoreModifierLookup = buildAbilityScoreModifierLookup();
    private final Validator validator;
    private Character character;

    public CharacterTest() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    private static Map<Integer, Integer> buildAbilityScoreModifierLookup() {
        Map<Integer, Integer> map = new HashMap<>();
        // Values from Player DND Basic Rules V2, page 57
        // http://media.wizards.com/2014/downloads/dnd/PlayerDnDBasicRules_v0.2.pdf
        map.put(1, -5);
        map.put(2, -4);
        map.put(3, -4);
        map.put(4, -3);
        map.put(5, -3);
        map.put(6, -2);
        map.put(7, -2);
        map.put(8, -1);
        map.put(9, -1);
        map.put(10, 0);
        map.put(11, 0);
        map.put(12, 1);
        map.put(13, 1);
        map.put(14, 2);
        map.put(15, 2);
        map.put(16, 3);
        map.put(17, 3);
        map.put(18, 4);
        map.put(19, 4);
        map.put(20, 5);
        map.put(21, 5);
        map.put(22, 6);
        map.put(23, 6);
        map.put(24, 7);
        map.put(25, 7);
        map.put(26, 8);
        map.put(27, 8);
        map.put(28, 9);
        map.put(29, 9);
        map.put(30, 10);

        return map;
    }

    @Before
    public void setUp() throws Exception {
        character = new Character();
        character.initializeDefaults();
    }

    @After
    public void tearDown() throws Exception {
        character = null;
    }

    @Test
    public void testZeroStrength() {
        character.setStrength(0);
        assertEquals(numberOfConstraintViolations(character), 1);
    }

    @Test
    public void testZeroDexterity() {
        character.setDexterity(0);
        assertEquals(numberOfConstraintViolations(character), 1);
    }

    @Test
    public void testZeroConstitution() {
        character.setConstitution(0);
        assertEquals(numberOfConstraintViolations(character), 1);
    }

    @Test
    public void testZeroIntelligence() {
        character.setIntelligence(0);
        assertEquals(numberOfConstraintViolations(character), 1);
    }

    @Test
    public void testZeroWisdom() {
        character.setWisdom(0);
        assertEquals(numberOfConstraintViolations(character), 1);
    }

    @Test
    public void testZeroCharisma() {
        character.setCharisma(0);
        assertEquals(numberOfConstraintViolations(character), 1);
    }

    @Test
    public void testStrengthModifierValues() {
        for (int score = 1; score <= 30; score++) {
            character.setStrength(score);
            assertEquals(abilityScoreModifierLookup.get(score), character.getStrengthModifier());
            assertFalse(hasConstraintViolations(character));
        }
    }

    @Test
    public void testDexterityModifierValues() {
        for (int score = 1; score <= 30; score++) {
            character.setDexterity(score);
            assertEquals(abilityScoreModifierLookup.get(score), character.getDexterityModifier());
            assertFalse(hasConstraintViolations(character));
        }
    }

    @Test
    public void testConstitutionModifierValues() {
        for (int score = 1; score <= 30; score++) {
            character.setConstitution(score);
            assertEquals(abilityScoreModifierLookup.get(score), character.getConstitutionModifier());
            assertFalse(hasConstraintViolations(character));
        }
    }

    @Test
    public void testIntelligenceModifierValues() {
        for (int score = 1; score <= 30; score++) {
            character.setIntelligence(score);
            assertEquals(abilityScoreModifierLookup.get(score), character.getIntelligenceModifier());
            assertFalse(hasConstraintViolations(character));
        }
    }

    @Test
    public void testWisdomModifierValues() {
        for (int score = 1; score <= 30; score++) {
            character.setWisdom(score);
            assertEquals(abilityScoreModifierLookup.get(score), character.getWisdomModifier());
            assertFalse(hasConstraintViolations(character));
        }
    }

    @Test
    public void testCharismaModifierValues() {
        for (int score = 1; score <= 30; score++) {
            character.setCharisma(score);
            assertEquals(abilityScoreModifierLookup.get(score), character.getCharismaModifier());
            assertFalse(hasConstraintViolations(character));
        }
    }

    @Test
    public void testZeroLevel() {
        character.setLevel(0);
        assertEquals(numberOfConstraintViolations(character), 1);
    }

    @Test
    public void testLevelValues() {
        for (int level = 1; level <= 20; level++) {
            character.setLevel(level);
            assertEquals((int) character.getLevel(), level);
            assertFalse(hasConstraintViolations(character));
        }
    }

    @Test
    public void testNegativeExperiencePoints() {
        character.setExperiencePoints(-1);
        assertEquals(numberOfConstraintViolations(character), 1);
    }

    @Test
    public void testZeroExperiencePoints() {
        character.setExperiencePoints(0);
        assertEquals((int) character.getExperiencePoints(), 0);
        assertFalse(hasConstraintViolations(character));

    }

    @Test
    public void testPositiveExperiencePoints() throws Exception {
        character.setExperiencePoints(1);
        assertEquals((int) character.getExperiencePoints(), 1);
        assertFalse(hasConstraintViolations(character));

    }

    @Test
    public void testNegativeArmorClass() throws Exception {
        character.setArmorClass(-1);
        assertEquals(numberOfConstraintViolations(character), 1);
    }

    @Test
    public void testZeroArmorClass() throws Exception {
        character.setArmorClass(0);
        assertEquals((int) character.getArmorClass(), 0);
        assertFalse(hasConstraintViolations(character));
    }

    @Test
    public void testPositiveArmorClass() throws Exception {
        character.setArmorClass(1);
        assertEquals((int) character.getArmorClass(), 1);
        assertFalse(hasConstraintViolations(character));
    }

    @Test
    public void testInitiativeValues() {
        for (int dexterity = 1; dexterity <= 30; dexterity++) {
            character.setDexterity(dexterity);
            assertEquals(character.getInitiative(), abilityScoreModifierLookup.get(dexterity));
        }
    }

    @Test
    public void testSimpleName() {
        character.setName("John");
        assertEquals("John", character.getName());
        assertFalse(hasConstraintViolations(character));
    }

    @Test
    public void testFullName() {
        character.setName("John Doe");
        assertEquals("John Doe", character.getName());
        assertFalse(hasConstraintViolations(character));
    }

    @Test
    public void testNameWithTrailingWhitespace() {
        character.setName("John Doe ");
        assertEquals("John Doe", character.getName());
        assertFalse(hasConstraintViolations(character));
    }

    @Test
    public void testNameWithLeadingWhitespace() {
        character.setName(" John Doe");
        assertEquals("John Doe", character.getName());
        assertFalse(hasConstraintViolations(character));
    }

    @Test
    public void testNameWithEmptyString() {
        character.setName("");
        assertEquals(numberOfConstraintViolations(character), 1);
    }

    @Test
    public void testNameWithWhitespaceString() {
        character.setName(" ");
        assertEquals(numberOfConstraintViolations(character), 1);
    }

    @Test
    public void testSimpleCharacterClass() {
        character.setCharacterClass("Fighter");
        assertEquals("Fighter", character.getCharacterClass());
        assertFalse(hasConstraintViolations(character));
    }

    @Test
    public void testComplexCharacterClass() {
        character.setCharacterClass("Drow Ranger");
        assertEquals("Drow Ranger", character.getCharacterClass());
        assertFalse(hasConstraintViolations(character));
    }

    @Test
    public void testCharacterClassWithTrailingWhitespace() {
        character.setCharacterClass("Drow Ranger ");
        assertEquals("Drow Ranger", character.getCharacterClass());
        assertFalse(hasConstraintViolations(character));
    }

    @Test
    public void testCharacterClassWithLeadingWhitespace() {
        character.setCharacterClass(" Drow Ranger");
        assertEquals("Drow Ranger", character.getCharacterClass());
        assertFalse(hasConstraintViolations(character));
    }

    @Test
    public void testCharacterClassWithEmptyString() {
        character.setCharacterClass("");
        assertEquals(numberOfConstraintViolations(character), 1);
    }

    @Test
    public void testCharacterClassWithWhitespaceString() {
        character.setCharacterClass(" ");
        assertEquals(numberOfConstraintViolations(character), 1);
    }

    @Test
    public void testNegativeCharacterLevel() {
        character.setLevel(-1);
        assertEquals(numberOfConstraintViolations(character), 1);
    }

    @Test
    public void testZeroCharacterLevel() throws Exception {
        character.setLevel(0);
        assertEquals(numberOfConstraintViolations(character), 1);
    }

    @Test
    public void testLevelOneCharacter() throws Exception {
        character.setLevel(1);
        assertFalse(hasConstraintViolations(character));
    }

    @Test
    public void testLevelThirtyCharacter() {
        character.setLevel(30);
        assertFalse(hasConstraintViolations(character));
    }

    @Test
    public void testLevelThirtyOneCharacter() {
        character.setLevel(31);
        assertEquals(numberOfConstraintViolations(character), 1);
    }

    @Test
    public void testLessThanFiveSpeed() throws Exception {
        character.setSpeed(4);
        assertEquals(numberOfConstraintViolations(character), 1);
    }

    @Test
    public void testFiveSpeed() throws Exception {
        character.setSpeed(5);
        assertFalse(hasConstraintViolations(character));
    }

    @Test
    public void testNewCharacterDoesNotViolateConstraints() {
        assertFalse(hasConstraintViolations(character));
    }

    @Test
    public void testSimpleBackground() {
        character.setBackground("Wanderer");
        assertEquals("Wanderer", character.getBackground());
        assertFalse(hasConstraintViolations(character));
    }

    @Test
    public void testComplexBackground() {
        character.setBackground("Traveling Minstrel");
        assertEquals("Traveling Minstrel", character.getBackground());
        assertFalse(hasConstraintViolations(character));
    }

    @Test
    public void testBackgroundWithTrailingWhitespace() {
        character.setBackground("Traveling Minstrel ");
        assertEquals("Traveling Minstrel", character.getBackground());
        assertFalse(hasConstraintViolations(character));
    }

    @Test
    public void testBackgroundWithLeadingWhitespace() {
        character.setBackground(" Traveling Minstrel");
        assertEquals("Traveling Minstrel", character.getBackground());
        assertFalse(hasConstraintViolations(character));
    }

    @Test
    public void testBackgroundWithEmptyString() {
        character.setBackground("");
        assertEquals(numberOfConstraintViolations(character), 1);
    }

    @Test
    public void testBackgroundWithWhitespaceString() {
        character.setBackground(" ");
        assertEquals(numberOfConstraintViolations(character), 1);
    }

    @Test
    public void testSimpleRace() {
        character.setRace("Drow");
        assertEquals("Drow", character.getRace());
        assertFalse(hasConstraintViolations(character));
    }

    @Test
    public void testFullRace() {
        character.setRace("Fire Elemental");
        assertEquals("Fire Elemental", character.getRace());
        assertFalse(hasConstraintViolations(character));
    }

    @Test
    public void testRaceWithTrailingWhitespace() {
        character.setRace("Fire Elemental ");
        assertEquals("Fire Elemental", character.getRace());
        assertFalse(hasConstraintViolations(character));
    }

    @Test
    public void testRaceWithLeadingWhitespace() {
        character.setRace(" Fire Elemental");
        assertEquals("Fire Elemental", character.getRace());
        assertFalse(hasConstraintViolations(character));
    }

    @Test
    public void testRaceWithEmptyString() {
        character.setRace("");
        assertEquals(numberOfConstraintViolations(character), 1);
    }

    @Test
    public void testRaceWithWhitespaceString() {
        character.setRace(" ");
        assertEquals(numberOfConstraintViolations(character), 1);
    }

    private boolean hasConstraintViolations(Character character) {
        return numberOfConstraintViolations(character) != 0;
    }

    private int numberOfConstraintViolations(Character character) {
        Set<ConstraintViolation<Character>> violations = validator.validate(character);
        return violations.size();
    }

    @Test
    public void testLessThanZeroCurrentHitPoints() {
        character.setCurrentHitPoints(-1);
        assertTrue(hasConstraintViolations(character));
    }

    @Test
    public void testZeroCurrentHitPoints() throws Exception {
        character.setCurrentHitPoints(0);
        assertFalse(hasConstraintViolations(character));
    }

    @Test
    public void testLessCurrentHitPointsThanMaximumHitPoints() throws Exception {
        character.setMaximumHitPoints(10);
        character.setCurrentHitPoints(9);
        assertFalse(hasConstraintViolations(character));
        assertEquals(character.getCurrentHitPoints(), (Integer) 9);
    }

    @Test
    public void testCurrentHitPointsEqualToMaximumHitPoints() throws Exception {
        character.setMaximumHitPoints(10);
        character.setCurrentHitPoints(10);
        assertFalse(hasConstraintViolations(character));
        assertEquals(character.getCurrentHitPoints(), (Integer) 10);
    }

    @Test
    public void testCurrentHitPointsGreaterThanMaximumHitPoints() throws Exception {
        character.setMaximumHitPoints(10);
        character.setCurrentHitPoints(11);
        assertEquals(character.getCurrentHitPoints(), (Integer) 10);
    }

    @Test
    public void testMaximumHitPointsLessThanOne() throws Exception {
        character.setMaximumHitPoints(-1);
        assertTrue(hasConstraintViolations(character));
    }

    @Test
    public void testMaximumHitPointsLessThanCurrentHitPoints() throws Exception {
        character.setMaximumHitPoints(10);
        character.setCurrentHitPoints(10);
        character.setMaximumHitPoints(5);
        assertFalse(hasConstraintViolations(character));
        assertEquals(character.getMaximumHitPoints(), (Integer) 5);
        assertEquals(character.getCurrentHitPoints(), (Integer) 5);
    }

    @Test
    public void testMaximumHitPointsEqualToCurrentHitPoints() throws Exception {
        character.setMaximumHitPoints(10);
        character.setCurrentHitPoints(8);
        character.setMaximumHitPoints(8);
        assertFalse(hasConstraintViolations(character));
        assertEquals(character.getMaximumHitPoints(), (Integer) 8);
        assertEquals(character.getCurrentHitPoints(), (Integer) 8);
    }

    @Test
    public void testMaximumHitPointsGreaterThanCurrentHitPoints() throws Exception {
        character.setMaximumHitPoints(10);
        character.setCurrentHitPoints(10);
        character.setMaximumHitPoints(15);
        assertFalse(hasConstraintViolations(character));
        assertEquals(character.getMaximumHitPoints(), (Integer) 15);
        assertEquals(character.getCurrentHitPoints(), (Integer) 10);
    }

    @Test
    public void testLessThanZeroCurrentHitDice() {
        character.setCurrentHitDice(-1);
        assertTrue(hasConstraintViolations(character));
    }

    @Test
    public void testZeroCurrentHitDice() throws Exception {
        character.setCurrentHitDice(0);
        assertFalse(hasConstraintViolations(character));
    }

    @Test
    public void testLessCurrentHitDiceThanMaximumHitDice() throws Exception {
        character.setMaximumHitDice(10);
        character.setCurrentHitDice(9);
        assertFalse(hasConstraintViolations(character));
        assertEquals(character.getCurrentHitDice(), (Integer) 9);
    }

    @Test
    public void testCurrentHitDiceEqualToMaximumHitDice() throws Exception {
        character.setMaximumHitDice(10);
        character.setCurrentHitDice(10);
        assertFalse(hasConstraintViolations(character));
        assertEquals(character.getCurrentHitDice(), (Integer) 10);
    }

    @Test
    public void testCurrentHitDiceGreaterThanMaximumHitDice() throws Exception {
        character.setMaximumHitDice(10);
        character.setCurrentHitDice(11);
        assertEquals(character.getCurrentHitDice(), (Integer) 10);
    }

    @Test
    public void testMaximumHitDiceLessThanOne() throws Exception {
        character.setMaximumHitDice(-1);
        assertTrue(hasConstraintViolations(character));
    }

    @Test
    public void testMaximumHitDiceLessThanCurrentHitDice() throws Exception {
        character.setMaximumHitDice(10);
        character.setCurrentHitDice(10);
        character.setMaximumHitDice(5);
        assertFalse(hasConstraintViolations(character));
        assertEquals(character.getMaximumHitDice(), (Integer) 5);
        assertEquals(character.getCurrentHitDice(), (Integer) 5);
    }

    @Test
    public void testMaximumHitDiceEqualToCurrentHitDice() throws Exception {
        character.setMaximumHitDice(10);
        character.setCurrentHitDice(8);
        character.setMaximumHitDice(8);
        assertFalse(hasConstraintViolations(character));
        assertEquals(character.getMaximumHitDice(), (Integer) 8);
        assertEquals(character.getCurrentHitDice(), (Integer) 8);
    }

    @Test
    public void testMaximumHitDiceGreaterThanCurrentHitDice() throws Exception {
        character.setMaximumHitDice(10);
        character.setCurrentHitDice(10);
        character.setMaximumHitDice(15);
        assertFalse(hasConstraintViolations(character));
        assertEquals(character.getMaximumHitDice(), (Integer) 15);
        assertEquals(character.getCurrentHitDice(), (Integer) 10);
    }

    /**
     * Test for UI bug where current hit points could not exceed 10.
     */
    @Test
    public void testCurrentHitPointsGreaterThanTen() {
        character.setMaximumHitPoints(10);
        character.setCurrentHitPoints(10);
        character.setMaximumHitPoints(11);
        character.setCurrentHitPoints(11);

        assertFalse(hasConstraintViolations(character));
        assertEquals(character.getMaximumHitPoints(), (Integer) 11);
        assertEquals(character.getCurrentHitPoints(), (Integer) 11);
    }

    @Test
    public void testCurrentSpellSlotsLessThanZero() throws Exception {
        for (int i = 1; i <= 9; i++) {
            int previousValue = getCurrentSpellSlots(character, i);
            setCurrentSpellSlots(character, i, -1);
            assertTrue(hasConstraintViolations(character));
            setCurrentSpellSlots(character, i, previousValue);
        }
    }

    @Test
    public void testMaxFirstLevelSpellSlotsLessThanZero() throws Exception {
        for (int i = 1; i <= 9; i++) {
            int previousValue = getMaxSpellSlots(character, i);
            setMaxSpellSlots(character, i, -1);
            assertTrue(hasConstraintViolations(character));
            setMaxSpellSlots(character, i, previousValue);
        }
    }

    @Test
    public void testCurrentSpellSlotsEqualToMaximumSpellSlots() throws Exception {
        for (int i = 1; i <= 9; i++) {
            setMaxSpellSlots(character, i, 5);
            setCurrentSpellSlots(character, i, 5);
            assertFalse(hasConstraintViolations(character));
            assertEquals(getCurrentSpellSlots(character, i), (Integer) 5);
            assertEquals(getMaxSpellSlots(character, i), (Integer) 5);
        }
    }

    @Test
    public void testCurrentSpellSlotsLessThanMaximumSpellSlots() throws Exception {
        for (int i = 1; i <= 9; i++) {
            setMaxSpellSlots(character, i, 5);
            setCurrentSpellSlots(character, i, 4);
            assertFalse(hasConstraintViolations(character));
            assertEquals(getCurrentSpellSlots(character, i), (Integer) 4);
            assertEquals(getMaxSpellSlots(character, i), (Integer) 5);
        }
    }

    @Test
    public void testCurrentSpellSlotsGreaterThanMaximumSpellSlots() throws Exception {
        for (int i = 1; i <= 9; i++) {
            setMaxSpellSlots(character, i, 5);
            setCurrentSpellSlots(character, i, 6);
            assertFalse(hasConstraintViolations(character));
            assertEquals(getCurrentSpellSlots(character, i), (Integer) 5);
            assertEquals(getMaxSpellSlots(character, i), (Integer) 5);
        }
    }

    @Test
    public void testMaximumSpellSlotsLessThanCurrentSpellSlots() throws Exception {
        for (int i = 1; i <= 9; i++) {
            setMaxSpellSlots(character, i, 5);
            setCurrentSpellSlots(character, i, 5);
            setMaxSpellSlots(character, i, 4);
            assertFalse(hasConstraintViolations(character));
            assertEquals(getCurrentSpellSlots(character, i), (Integer) 4);
            assertEquals(getMaxSpellSlots(character, i), (Integer) 4);
        }
    }

    @Test
    public void testMaximumSpellSlotsEqualToCurrentSpellSlots() throws Exception {
        for (int i = 1; i <= 9; i++) {
            setMaxSpellSlots(character, i, 6);
            setCurrentSpellSlots(character, i, 5);
            setMaxSpellSlots(character, i, 5);
            assertFalse(hasConstraintViolations(character));
            assertEquals(getCurrentSpellSlots(character, i), (Integer) 5);
            assertEquals(getMaxSpellSlots(character, i), (Integer) 5);
        }
    }

    @Test
    public void testMaximumSpellSlotsGreaterThanCurrentSpellSlots() throws Exception {
        for (int i = 1; i <= 9; i++) {
            setMaxSpellSlots(character, i, 5);
            setCurrentSpellSlots(character, i, 5);
            setMaxSpellSlots(character, i, 6);
            assertFalse(hasConstraintViolations(character));
            assertEquals(getCurrentSpellSlots(character, i), (Integer) 5);
            assertEquals(getMaxSpellSlots(character, i), (Integer) 6);
        }
    }

    private Integer getCurrentSpellSlots(com.dharmab.sheets.server.character.Character character, int level) {
        switch (level) {
            case 1:
                return character.getCurrentFirstLevelSpellSlots();
            case 2:
                return character.getCurrentSecondLevelSpellSlots();
            case 3:
                return character.getCurrentThirdLevelSpellSlots();
            case 4:
                return character.getCurrentFourthLevelSpellSlots();
            case 5:
                return character.getCurrentFifthLevelSpellSlots();
            case 6:
                return character.getCurrentSixthLevelSpellSlots();
            case 7:
                return character.getCurrentSeventhLevelSpellSlots();
            case 8:
                return character.getCurrentEighthLevelSpellSlots();
            case 9:
                return character.getCurrentNinthLevelSpellSlots();
            default:
                throw new IllegalArgumentException();
        }
    }

    private void setCurrentSpellSlots(Character character, int level, int value) {
        switch (level) {
            case 1:
                character.setCurrentFirstLevelSpellSlots(value);
                break;
            case 2:
                character.setCurrentSecondLevelSpellSlots(value);
                break;
            case 3:
                character.setCurrentThirdLevelSpellSlots(value);
                break;
            case 4:
                character.setCurrentFourthLevelSpellSlots(value);
                break;
            case 5:
                character.setCurrentFifthLevelSpellSlots(value);
                break;
            case 6:
                character.setCurrentSixthLevelSpellSlots(value);
                break;
            case 7:
                character.setCurrentSeventhLevelSpellSlots(value);
                break;
            case 8:
                character.setCurrentEighthLevelSpellSlots(value);
                break;
            case 9:
                character.setCurrentNinthLevelSpellSlots(value);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private Integer getMaxSpellSlots(Character character, int level) {
        switch (level) {
            case 1:
                return character.getMaxFirstLevelSpellSlots();
            case 2:
                return character.getMaxSecondLevelSpellSlots();
            case 3:
                return character.getMaxThirdLevelSpellSlots();
            case 4:
                return character.getMaxFourthLevelSpellSlots();
            case 5:
                return character.getMaxFifthLevelSpellSlots();
            case 6:
                return character.getMaxSixthLevelSpellSlots();
            case 7:
                return character.getMaxSeventhLevelSpellSlots();
            case 8:
                return character.getMaxEighthLevelSpellSlots();
            case 9:
                return character.getMaxNinthLevelSpellSlots();
            default:
                throw new IllegalArgumentException();
        }
    }

    private void setMaxSpellSlots(Character character, int level, int value) {
        switch (level) {
            case 1:
                character.setMaxFirstLevelSpellSlots(value);
                break;
            case 2:
                character.setMaxSecondLevelSpellSlots(value);
                break;
            case 3:
                character.setMaxThirdLevelSpellSlots(value);
                break;
            case 4:
                character.setMaxFourthLevelSpellSlots(value);
                break;
            case 5:
                character.setMaxFifthLevelSpellSlots(value);
                break;
            case 6:
                character.setMaxSixthLevelSpellSlots(value);
                break;
            case 7:
                character.setMaxSeventhLevelSpellSlots(value);
                break;
            case 8:
                character.setMaxEighthLevelSpellSlots(value);
                break;
            case 9:
                character.setMaxNinthLevelSpellSlots(value);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }
}