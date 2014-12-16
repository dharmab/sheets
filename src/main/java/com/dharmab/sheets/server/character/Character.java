package com.dharmab.sheets.server.character;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "characters")
public class Character {
    // Regexp matchs strings that are not empty and start with a non-whitespace character
    private static final String NAME_REGEXP = "\\S.*";

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
    private int speed;
    private int maximumHitPoints;
    private int currentHitPoints;
    private int temporaryHitPoints;
    private int proficiency;
    private boolean hasInspirationPoint;
    private int maxHitDice;
    private int currentHitDice;
    private DieType hitDie;
    private int deathSavingThrowSuccesses;
    private int deathSavingThrowFailures;
    private int maxFirstLevelSpellSlots;
    private int currentFirstLevelSpellSlots;
    private int maxSecondLevelSpellSlots;
    private int currentSecondLevelSpellSlots;
    private int maxThirdLevelSpellSlots;
    private int currentThirdLevelSpellSlots;
    private int maxFourthLevelSpellSlots;
    private int currentFourthLevelSpellSlots;
    private int maxFifthLevelSpellSlots;
    private int currentFifthLevelSpellSlots;
    private int maxSixthLevelSpellSlots;
    private int currentSixthLevelSpellSlots;
    private int maxSeventhLevelSpellSlots;
    private int currentSeventhLevelSpellSlots;
    private int maxEighthLevelSpellSlots;
    private int currentEighthLevelSpellSlots;
    private int maxNinthLevelSpellSlots;
    private int currentNinthLevelSpellSlots;
    // todo traits, ideals, bonds, flaws
    // todo proficiences
    // todo languages
    // todo equipment
    // todo features
    // todo spells
    // todo items

    public Character() {
        // Default values
        name = "New Character";
        characterClass = "None";
        background = "None";
        race = "Human";
        level = 1;
        experiencePoints = 0;
        strength = 8;
        dexterity = 8;
        constitution = 8;
        intelligence = 8;
        wisdom = 8;
        charisma = 8;
        armorClass = 0;
        speed = 5;
        maximumHitPoints = 10;
        currentHitPoints = 10;
        temporaryHitPoints = 0;
        proficiency = 2;
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
    @Pattern(regexp = NAME_REGEXP, message = "character name must not be empty and start with a non-whitespace character")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    /**
     * This is NOT the same as {@link #getClass() getClass()}!
     *
     * @return The character's class, e.g. fighter, rogue, wizard.
     */
    @Column(name = "character_class")
    @NotNull
    @Pattern(regexp = NAME_REGEXP, message = "character class must not be empty and start with a non-whitespace character")
    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass.trim();
    }

    @Column(name = "level")
    @NotNull
    @Min(value = 1, message = "character level must be in range 1-30")
    @Max(value = 30, message = "character level must be in range 1-30")
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Column(name = "background")
    @NotNull
    @Pattern(regexp = NAME_REGEXP, message = "background must not be empty and start with a non-whitespace character")
    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background.trim();
    }

    @Column(name = "race")
    @NotNull
    @Pattern(regexp = NAME_REGEXP, message = "character race must not be empty and start with a non-whitespace character")
    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race.trim();
    }

    @Column(name = "experience_points")
    @NotNull
    @Min(value = 0, message = "experience points cannot be negative")
    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    @Column(name = "strength")
    @NotNull
    @Min(value = 1, message = "strength score cannot be negative")
    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Column(name = "dexterity")
    @NotNull
    @Min(value = 1, message = "dexterity score cannot be negative")
    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    @Column(name = "constitution")
    @NotNull
    @Min(value = 1, message = "constitution score cannot be negative")
    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    @Column(name = "intelligence")
    @NotNull
    @Min(value = 1, message = "intelligence score cannot be negative")
    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    @Column(name = "wisdom")
    @NotNull
    @Min(value = 1, message = "wisdom score cannot be negative")
    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    @Column(name = "charisma")
    @NotNull
    @Min(value = 1, message = "charisma score cannot be negative")
    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    @Column(name = "armor_class")
    @NotNull
    @Min(value = 0, message = "armor class cannot be negative")
    public int getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    @Transient
    public int getInitiative() {
        return getDexterityModifier();
    }

    @Column(name = "speed")
    @NotNull
    @Min(value = 5, message = "speed cannot be less than 5 feet")
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Column(name = "maximum_hit_points")
    @NotNull
    @Min(value = 1, message = "maximum hit points cannot be less than 1")
    public int getMaximumHitPoints() {
        return maximumHitPoints;
    }

    public void setMaximumHitPoints(int maximumHitPoints) {
        this.maximumHitPoints = maximumHitPoints;
    }

    @Column(name = "current_hit_points")
    @NotNull
    // todo: min hit points = -conmod, max = maxhitpoints
    public int getCurrentHitPoints() {
        return currentHitPoints;
    }

    public void setCurrentHitPoints(int currentHitPoints) {
        this.currentHitPoints = currentHitPoints;
    }

    @Column(name = "temporary_hit_points")
    @NotNull
    @Min(value = 0, message = "temporary hit points cannot be negative")
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

    @Transient
    public int getPassiveWisdom() {
        return getWisdomModifier() + 10;
    }

    @Column(name = "proficiency")
    @NotNull
    @Min(value = 2, message = "proficiency bonus cannot be less than 2")
    @Max(value = 6, message = "proficiency bonus cannot be greater than 6")
    public int getProficiency() {
        return proficiency;
    }

    public void setProficiency(int proficiency) {
        this.proficiency = proficiency;
    }
}
