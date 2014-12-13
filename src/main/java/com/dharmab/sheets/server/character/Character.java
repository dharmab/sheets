package com.dharmab.sheets.server.character;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "characters")
public class Character {
    private Integer id;
    private Integer version;
    private String name;
    private String characterClass;
    private int level;
    private String background;
    private String race;
    private int experiencePoints;
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
    private int armorClass;
    private int initiative;
    private int speed;
    private int maximumHitPoints;
    private int currentHitPoints;
    private int temporaryHitPoints;
    private int proficiency;

    public Character() {
        // Default values
        name = "New Character";
        characterClass = "None";
        background = "None";
        race = "Human";
        level = 1;
        speed = 5;
        strength = 8;
        dexterity = 8;
        constitution = 8;
        intelligence = 8;
        wisdom = 8;
        charisma = 8;
    }

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "characters_id_seq", sequenceName = "characters_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "characters_id_seq")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Version
    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Column(name = "name")
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * This is NOT the same as {@link #getClass() getClass()}!
     *
     * @return The character's class, e.g. fighter, rogue, wizard.
     */
    @Column(name = "character_class")
    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    @Column(name = "level")
    @NotNull
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Column(name = "background")
    @NotNull
    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    @Column(name = "race")
    @NotNull
    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    @Column(name = "experience_points")
    @NotNull
    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    @Column(name = "strength")
    @NotNull
    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Column(name = "dexterity")
    @NotNull
    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    @Column(name = "constitution")
    @NotNull
    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    @Column(name = "intelligence")
    @NotNull
    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    @Column(name = "wisdom")
    @NotNull
    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    @Column(name = "charisma")
    @NotNull
    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    @Column(name = "armor_class")
    @NotNull
    public int getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    @Column(name = "initiative")
    @NotNull
    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    @Column(name = "speed")
    @NotNull
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Column(name = "maximum_hit_points")
    @NotNull
    public int getMaximumHitPoints() {
        return maximumHitPoints;
    }

    public void setMaximumHitPoints(int maximumHitPoints) {
        this.maximumHitPoints = maximumHitPoints;
    }

    @Column(name = "current_hit_points")
    @NotNull
    public int getCurrentHitPoints() {
        return currentHitPoints;
    }

    public void setCurrentHitPoints(int currentHitPoints) {
        this.currentHitPoints = currentHitPoints;
    }

    @Column(name = "temporary_hit_points")
    @NotNull
    public int getTemporaryHitPoints() {
        return temporaryHitPoints;
    }

    public void setTemporaryHitPoints(int temporaryHitPoints) {
        this.temporaryHitPoints = temporaryHitPoints;
    }

    private int getAbilityModifier(int abilityScore) {
        if (abilityScore >= 10) {
            return (abilityScore - 10) / 2;
        } else {
            return (int) Math.floor((abilityScore - 10) / 2d);
        }

    }

    @Transient
    public int getStrengthModifier() {
        return getAbilityModifier(strength);
    }

    @Transient
    public int getDexterityModifier() {
        return getAbilityModifier(dexterity);
    }

    @Transient
    public int getConstitutionModifier() {
        return getAbilityModifier(constitution);
    }

    @Transient
    public int getIntelligenceModifier() {
        return getAbilityModifier(intelligence);
    }

    @Transient
    public int getWisdomModifier() {
        return getAbilityModifier(wisdom);
    }

    @Transient
    public int getCharismaModifier() {
        return getAbilityModifier(charisma);
    }

    @Column(name = "proficiency")
    @NotNull
    public int getProficiency() {
        return proficiency;
    }

    public void setProficiency(int proficiency) {
        this.proficiency = proficiency;
    }
}
