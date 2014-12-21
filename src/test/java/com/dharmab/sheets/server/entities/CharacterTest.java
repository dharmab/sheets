package com.dharmab.sheets.server.entities;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
}