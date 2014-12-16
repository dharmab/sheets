package com.dharmab.sheets.client.requestfactory;

import com.dharmab.sheets.server.character.Character;
import com.dharmab.sheets.server.character.CharacterLocator;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = Character.class, locator = CharacterLocator.class)
public interface CharacterProxy extends EntityProxy {
    Integer getId();

    Integer getVersion();

    String getCharacterClass();

    void setCharacterClass(String characterClass);

    String getName();

    void setName(String name);

    int getLevel();

    void setLevel(int level);

    String getBackground();

    void setBackground(String background);

    String getRace();

    void setRace(String race);

    int getExperiencePoints();

    void setExperiencePoints(int xp);

    int getStrength();

    void setStrength(int score);

    int getDexterity();

    void setDexterity(int score);

    int getConstitution();

    void setConstitution(int score);

    int getIntelligence();

    void setIntelligence(int score);

    int getWisdom();

    void setWisdom(int score);

    int getCharisma();

    void setCharisma(int score);

    int getArmorClass();

    void setArmorClass(int ac);

    int getInitiative();

    int getSpeed();

    void setSpeed(int speed);

    int getMaximumHitPoints();

    void setMaximumHitPoints(int hp);

    int getCurrentHitPoints();

    void setCurrentHitPoints(int hp);

    int getTemporaryHitPoints();

    void setTemporaryHitPoints(int hp);
}
