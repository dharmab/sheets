package com.dharmab.sheets.client.requestfactory;

import com.dharmab.sheets.server.character.CharacterLocator;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = com.dharmab.sheets.server.character.Character.class, locator = CharacterLocator.class)
public interface CharacterProxy extends EntityProxy {
    Integer getId();

    Integer getVersion();

    String getName();

    void setName(String value);

    String getCharacterClass();

    void setCharacterClass(String value);

    Integer getLevel();

    void setLevel(Integer value);

    String getBackground();

    void setBackground(String value);

    String getRace();

    void setRace(String value);

    Integer getExperiencePoints();

    void setExperiencePoints(Integer value);

    Integer getStrength();

    void setStrength(Integer value);

    Integer getDexterity();

    void setDexterity(Integer value);

    Integer getConstitution();

    void setConstitution(Integer value);

    Integer getIntelligence();

    void setIntelligence(Integer value);

    Integer getWisdom();

    void setWisdom(Integer value);

    Integer getCharisma();

    void setCharisma(Integer value);

    Integer getStrengthModifier();

    Integer getDexterityModifier();

    Integer getConstitutionModifier();

    Integer getIntelligenceModifier();

    Integer getWisdomModifier();

    Integer getCharismaModifier();

    Integer getArmorClass();

    void setArmorClass(Integer value);

    Integer getInitiative();

    Integer getSpeed();

    void setSpeed(Integer value);

    Integer getCurrentHitPoints();

    void setCurrentHitPoints(Integer value);

    Integer getMaximumHitPoints();

    void setMaximumHitPoints(Integer value);

    Integer getTemporaryHitPoints();

    void setTemporaryHitPoints(Integer value);

    Integer getPassiveWisdom();

    Integer getProficiency();

    void setProficiency(Integer value);

    Boolean getHasInspirationPoint();

    void setHasInspirationPoint(Boolean value);

    Integer getMaximumHitDice();

    void setMaximumHitDice(Integer value);

    Integer getCurrentHitDice();

    void setCurrentHitDice(Integer value);

    Integer getMaxFirstLevelSpellSlots();

    void setMaxFirstLevelSpellSlots(Integer value);

    Integer getCurrentFirstLevelSpellSlots();

    void setCurrentFirstLevelSpellSlots(Integer value);

    Integer getMaxSecondLevelSpellSlots();

    void setMaxSecondLevelSpellSlots(Integer value);

    Integer getCurrentSecondLevelSpellSlots();

    void setCurrentSecondLevelSpellSlots(Integer value);

    Integer getMaxThirdLevelSpellSlots();

    void setMaxThirdLevelSpellSlots(Integer value);

    Integer getCurrentThirdLevelSpellSlots();

    void setCurrentThirdLevelSpellSlots(Integer value);

    Integer getMaxFourthLevelSpellSlots();

    void setMaxFourthLevelSpellSlots(Integer value);

    Integer getCurrentFourthLevelSpellSlots();

    void setCurrentFourthLevelSpellSlots(Integer value);

    Integer getMaxFifthLevelSpellSlots();

    void setMaxFifthLevelSpellSlots(Integer value);

    Integer getCurrentFifthLevelSpellSlots();

    void setCurrentFifthLevelSpellSlots(Integer value);

    Integer getMaxSixthLevelSpellSlots();

    void setMaxSixthLevelSpellSlots(Integer value);

    Integer getCurrentSixthLevelSpellSlots();

    void setCurrentSixthLevelSpellSlots(Integer value);

    Integer getMaxSeventhLevelSpellSlots();

    void setMaxSeventhLevelSpellSlots(Integer value);

    Integer getCurrentSeventhLevelSpellSlots();

    void setCurrentSeventhLevelSpellSlots(Integer value);

    Integer getMaxEighthLevelSpellSlots();

    void setMaxEighthLevelSpellSlots(Integer value);

    Integer getCurrentEighthLevelSpellSlots();

    void setCurrentEighthLevelSpellSlots(Integer value);

    Integer getMaxNinthLevelSpellSlots();

    void setMaxNinthLevelSpellSlots(Integer value);

    Integer getCurrentNinthLevelSpellSlots();

    void setCurrentNinthLevelSpellSlots(Integer value);

}
