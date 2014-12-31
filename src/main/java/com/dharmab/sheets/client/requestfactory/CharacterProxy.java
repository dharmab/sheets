package com.dharmab.sheets.client.requestfactory;

import com.dharmab.sheets.server.character.Character;
import com.dharmab.sheets.server.character.CharacterLocator;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

// Methods that appear unused are used by Editor framework at runtime
@SuppressWarnings("UnusedDeclaration")
@ProxyFor(value = Character.class, locator = CharacterLocator.class)
public interface CharacterProxy extends EntityProxy {
    Integer getId();

    Integer getVersion();

    String getCharacterClass();

    void setCharacterClass(String characterClass);

    String getName();

    void setName(String name);

    Integer getLevel();

    void setLevel(Integer level);

    String getBackground();

    void setBackground(String background);

    String getRace();

    void setRace(String race);

    Integer getExperiencePoints();

    void setExperiencePoints(Integer xp);

    Integer getStrength();

    void setStrength(Integer score);

    Integer getStrengthModifier();

    Integer getDexterity();

    void setDexterity(Integer score);

    Integer getDexterityModifier();

    Integer getConstitution();

    void setConstitution(Integer score);

    Integer getConstitutionModifier();

    Integer getIntelligence();

    void setIntelligence(Integer score);

    Integer getIntelligenceModifier();

    Integer getWisdom();

    void setWisdom(Integer score);

    Integer getWisdomModifier();

    Integer getCharisma();

    void setCharisma(Integer score);

    Integer getCharismaModifier();

    Integer getArmorClass();

    void setArmorClass(Integer ac);

    Integer getInitiative();

    Integer getSpeed();

    void setSpeed(Integer speed);

    Integer getMaximumHitPoints();

    void setMaximumHitPoints(Integer hp);

    Integer getCurrentHitPoints();

    void setCurrentHitPoints(Integer hp);

    Integer getTemporaryHitPoints();

    void setTemporaryHitPoints(Integer hp);
}
