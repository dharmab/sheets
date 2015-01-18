package com.dharmab.sheets.shared.character;

import com.google.gwt.user.client.rpc.IsSerializable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "characters")
public class Character implements IsSerializable {
    // Regexp matchs strings that are not empty and start with a non-whitespace character
    private static final String NAME_REGEXP = "\\S.*";

    private Integer id;
    private Integer version;
    private String name;
    private String characterClass;
    private Integer level;
    private String background;
    private String race;
    private Integer experiencePoints;
    private Integer strength;
    private Integer dexterity;
    private Integer constitution;
    private Integer intelligence;
    private Integer wisdom;
    private Integer charisma;
    private Integer armorClass;
    private Integer speed;
    private Integer maximumHitPoints;
    private Integer currentHitPoints;
    private Integer temporaryHitPoints;
    private Integer proficiency;
    private Boolean hasInspirationPoint;
    private Integer maximumHitDice;
    private Integer currentHitDice;
    private DieType hitDie;
    private Integer deathSavingThrowSuccesses;
    private Integer deathSavingThrowFailures;
    private Integer maxFirstLevelSpellSlots;
    private Integer currentFirstLevelSpellSlots;
    private Integer maxSecondLevelSpellSlots;
    private Integer currentSecondLevelSpellSlots;
    private Integer maxThirdLevelSpellSlots;
    private Integer currentThirdLevelSpellSlots;
    private Integer maxFourthLevelSpellSlots;
    private Integer currentFourthLevelSpellSlots;
    private Integer maxFifthLevelSpellSlots;
    private Integer currentFifthLevelSpellSlots;
    private Integer maxSixthLevelSpellSlots;
    private Integer currentSixthLevelSpellSlots;
    private Integer maxSeventhLevelSpellSlots;
    private Integer currentSeventhLevelSpellSlots;
    private Integer maxEighthLevelSpellSlots;
    private Integer currentEighthLevelSpellSlots;
    private Integer maxNinthLevelSpellSlots;
    private Integer currentNinthLevelSpellSlots;
    // todo traits, ideals, bonds, flaws
    // todo proficiences
    // todo languages
    // todo equipment
    // todo features
    // todo spells
    // todo items

    public Character() {
        // Default values
        name = "New character";
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
        maximumHitPoints = 10;
        currentHitPoints = 10;
        temporaryHitPoints = 0;
        hasInspirationPoint = false;
        maximumHitDice = 2;
        currentHitDice = 2;
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
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
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
    public Integer getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(Integer experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    @Column(name = "strength")
    @NotNull
    @Min(value = 1, message = "strength score cannot be negative")
    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    @Column(name = "dexterity")
    @NotNull
    @Min(value = 1, message = "dexterity score cannot be negative")
    public Integer getDexterity() {
        return dexterity;
    }

    public void setDexterity(Integer dexterity) {
        this.dexterity = dexterity;
    }

    @Column(name = "constitution")
    @NotNull
    @Min(value = 1, message = "constitution score cannot be negative")
    public Integer getConstitution() {
        return constitution;
    }

    public void setConstitution(Integer constitution) {
        this.constitution = constitution;
    }

    @Column(name = "intelligence")
    @NotNull
    @Min(value = 1, message = "intelligence score cannot be negative")
    public Integer getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    @Column(name = "wisdom")
    @NotNull
    @Min(value = 1, message = "wisdom score cannot be negative")
    public Integer getWisdom() {
        return wisdom;
    }

    public void setWisdom(Integer wisdom) {
        this.wisdom = wisdom;
    }

    @Column(name = "charisma")
    @NotNull
    @Min(value = 1, message = "charisma score cannot be negative")
    public Integer getCharisma() {
        return charisma;
    }

    public void setCharisma(Integer charisma) {
        this.charisma = charisma;
    }

    @Column(name = "armor_class")
    @NotNull
    @Min(value = 0, message = "armor class cannot be negative")
    public Integer getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(Integer armorClass) {
        this.armorClass = armorClass;
    }

    @Transient
    public Integer getInitiative() {
        return getDexterityModifier();
    }

    @Column(name = "speed")
    @NotNull
    @Min(value = 5, message = "speed cannot be less than 5 feet")
    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    @Column(name = "maximum_hit_points")
    @NotNull
    @Min(value = 1, message = "maximum hit points cannot be less than 1")
    public Integer getMaximumHitPoints() {
        return maximumHitPoints;
    }

    public void setMaximumHitPoints(Integer maximumHitPoints) {
        this.maximumHitPoints = maximumHitPoints;
        if (getCurrentHitPoints() > maximumHitPoints) {
            setCurrentHitPoints(maximumHitPoints);
        }
    }

    @Column(name = "current_hit_points")
    @NotNull
    @Min(value = 0, message = "hit points cannot be negative")
    public Integer getCurrentHitPoints() {
        return currentHitPoints;
    }

    public void setCurrentHitPoints(Integer currentHitPoints) {
        if (currentHitPoints > getMaximumHitPoints()) {
            currentHitPoints = getMaximumHitPoints();
        }
        this.currentHitPoints = currentHitPoints;
    }

    @Column(name = "temporary_hit_points")
    @NotNull
    @Min(value = 0, message = "temporary hit points cannot be negative")
    public Integer getTemporaryHitPoints() {
        return temporaryHitPoints;
    }

    public void setTemporaryHitPoints(Integer temporaryHitPoints) {
        this.temporaryHitPoints = temporaryHitPoints;
    }

    private int getAbilityModifier(Integer abilityScore) {
        if (abilityScore >= 10) {
            return (abilityScore - 10) / 2;
        } else {
            return (int) Math.floor((abilityScore - 10) / 2d);
        }
    }

    @Transient
    public Integer getStrengthModifier() {
        return getAbilityModifier(strength);
    }

    @Transient
    public Integer getDexterityModifier() {
        return getAbilityModifier(dexterity);
    }

    @Transient
    public Integer getConstitutionModifier() {
        return getAbilityModifier(constitution);
    }

    @Transient
    public Integer getIntelligenceModifier() {
        return getAbilityModifier(intelligence);
    }

    @Transient
    public Integer getWisdomModifier() {
        return getAbilityModifier(wisdom);
    }

    @Transient
    public Integer getCharismaModifier() {
        return getAbilityModifier(charisma);
    }

    @Transient
    public Integer getPassiveWisdom() {
        return getWisdomModifier() + 10;
    }

    @Column(name = "proficiency")
    @NotNull
    @Min(value = 2, message = "proficiency bonus cannot be less than 2")
    @Max(value = 6, message = "proficiency bonus cannot be greater than 6")
    public Integer getProficiency() {
        return proficiency;
    }

    public void setProficiency(Integer proficiency) {
        this.proficiency = proficiency;
    }

    @Column(name = "inspiration")
    @NotNull
    public Boolean getHasInspirationPoint() {
        return hasInspirationPoint;
    }

    public void setHasInspirationPoint(Boolean hasInspirationPoint) {
        this.hasInspirationPoint = hasInspirationPoint;
    }

    @Column(name = "maximum_hit_dice")
    @NotNull
    public Integer getMaximumHitDice() {
        return maximumHitDice;
    }

    public void setMaximumHitDice(Integer maximumHitDice) {
        this.maximumHitDice = maximumHitDice;
    }

    @Column(name = "current_hit_dice")
    @NotNull
    public Integer getCurrentHitDice() {
        return currentHitDice;
    }

    public void setCurrentHitDice(Integer currentHitDice) {
        this.currentHitDice = currentHitDice;
    }
}
