package com.dharmab.sheets.server.entities;

import com.dharmab.sheets.server.character.Character;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CharacterTest {

    private static final Map<Integer, Integer> abilityScoreModifierLookup = buildAbilityScoreModifierLookup();

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

    @Test
    public void strengthModifierTest() {
        com.dharmab.sheets.server.character.Character character = new Character();
        for (int score = 1; score <= 30; score++) {
            character.setStrength(score);
            assertEquals((int) abilityScoreModifierLookup.get(score), character.getStrengthModifier());
        }
    }

    @Test
    public void dexterityModifierTest() {
        Character character = new Character();
        for (int score = 1; score <= 30; score++) {
            character.setDexterity(score);
            assertEquals((int) abilityScoreModifierLookup.get(score), character.getDexterityModifier());
        }
    }

    @Test
    public void constitutionModifierTest() {
        Character character = new Character();
        for (int score = 1; score <= 30; score++) {
            character.setConstitution(score);
            assertEquals((int) abilityScoreModifierLookup.get(score), character.getConstitutionModifier());
        }
    }

    @Test
    public void intelligenceModifierTest() {
        Character character = new Character();
        for (int score = 1; score <= 30; score++) {
            character.setIntelligence(score);
            assertEquals((int) abilityScoreModifierLookup.get(score), character.getIntelligenceModifier());
        }
    }

    @Test
    public void wisdomModifierTest() {
        Character character = new Character();
        for (int score = 1; score <= 30; score++) {
            character.setWisdom(score);
            assertEquals((int) abilityScoreModifierLookup.get(score), character.getWisdomModifier());
        }
    }

    @Test
    public void charismaModifierTest() {
        Character character = new Character();
        for (int score = 1; score <= 30; score++) {
            character.setCharisma(score);
            assertEquals((int) abilityScoreModifierLookup.get(score), character.getCharismaModifier());
        }
    }
}