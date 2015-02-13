package com.dharmab.sheets.server.character;

import com.dharmab.sheets.server.database.HasIdAndVersion;
import com.dharmab.sheets.shared.character.DieType;
import com.dharmab.sheets.shared.character.GameLogic;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "characters")
public class Character implements HasIdAndVersion<Integer, Integer> {
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
    private Integer currentHitPoints;
    private Integer maximumHitPoints;
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


    /**
     * Zero-arg constructor for RPC/editor framework.
     */
    public Character() {
    }

    /**
     * Initialize a character with default values that will pass validation.
     * <p/>
     * This cannot be done in a zero-arg constructor because the zero-arg constructor is used by the RPC transport
     * mechanism and editor framework and the property set order can't be guaranteed. That approach led to bugs where,
     * for example, current HP couldn't be set to a value higher than max HP was set to in the constructor.
     */
    public void initializeDefaults() {
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
        proficiency = 2;
        maximumHitPoints = 10;
        currentHitPoints = 10;
        temporaryHitPoints = 0;
        hasInspirationPoint = false;
        maximumHitDice = 2;
        currentHitDice = 2;

        for (int i = 1; i <= 9; i++) {
            setCurrentSpellSlots(i, 0);
            setMaximumSpellSlots(i, 0);
        }
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
        return GameLogic.computeInitiative(dexterity);
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

    @Column(name = "current_hit_points")
    @NotNull
    @Min(value = 0, message = "hit points cannot be negative")
    public Integer getCurrentHitPoints() {
        return currentHitPoints;
    }

    public void setCurrentHitPoints(Integer currentHitPoints) {
        currentHitPoints = capValue(currentHitPoints, maximumHitPoints);
        this.currentHitPoints = currentHitPoints;
    }

    @Column(name = "maximum_hit_points")
    @NotNull
    @Min(value = 1, message = "maximum hit points cannot be less than 1")
    public Integer getMaximumHitPoints() {
        return maximumHitPoints;
    }

    public void setMaximumHitPoints(Integer maximumHitPoints) {
        this.maximumHitPoints = maximumHitPoints;
        setCurrentHitPoints(capValue(getCurrentHitPoints(), maximumHitPoints));
    }

    /**
     * "Cap" a value to be no greater than a given maximum
     *
     * @param value   The value to cap
     * @param maximum The maximum value
     * @return If maximum is not null and lower than the given value, return the maximum value. Otherwise, return the
     * given value.
     */
    private Integer capValue(Integer value, Integer maximum) {
        // Check for null values, which may occur during client-side GWT operations (RPC, editor, etc.)
        if (maximum == null) {
            return value;
        }
        return value > maximum ? maximum : value;
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

    @Transient
    public Integer getStrengthModifier() {
        return GameLogic.computeAbilityModifier(strength);
    }

    @Transient
    public Integer getDexterityModifier() {
        return GameLogic.computeAbilityModifier(dexterity);
    }

    @Transient
    public Integer getConstitutionModifier() {
        return GameLogic.computeAbilityModifier(constitution);
    }

    @Transient
    public Integer getIntelligenceModifier() {
        return GameLogic.computeAbilityModifier(intelligence);
    }

    @Transient
    public Integer getWisdomModifier() {
        return GameLogic.computeAbilityModifier(wisdom);
    }

    @Transient
    public Integer getCharismaModifier() {
        return GameLogic.computeAbilityModifier(charisma);
    }

    @Transient
    public Integer getPassiveWisdom() {
        return GameLogic.computePassiveWisdom(wisdom);
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
    @Min(value = 0, message = "maximum hit dice cannot be negative")
    public Integer getMaximumHitDice() {
        return maximumHitDice;
    }

    public void setMaximumHitDice(Integer maximumHitDice) {
        this.maximumHitDice = maximumHitDice;
        setCurrentHitDice(capValue(getCurrentHitDice(), maximumHitDice));
    }

    @Column(name = "current_hit_dice")
    @NotNull
    @Min(value = 0, message = "current hit dice cannot be negative")
    public Integer getCurrentHitDice() {
        return currentHitDice;
    }

    public void setCurrentHitDice(Integer currentHitDice) {
        currentHitDice = capValue(currentHitDice, maximumHitDice);
        this.currentHitDice = currentHitDice;
    }

    private Integer getCurrentSpellSlots(int level) {
        switch (level) {
            case 1:
                return currentFirstLevelSpellSlots;
            case 2:
                return currentSecondLevelSpellSlots;
            case 3:
                return currentThirdLevelSpellSlots;
            case 4:
                return currentFourthLevelSpellSlots;
            case 5:
                return currentFifthLevelSpellSlots;
            case 6:
                return currentSixthLevelSpellSlots;
            case 7:
                return currentSeventhLevelSpellSlots;
            case 8:
                return currentEighthLevelSpellSlots;
            case 9:
                return currentNinthLevelSpellSlots;
            default:
                throw new IllegalArgumentException("no spells slots for level " + level);
        }
    }

    private void setCurrentSpellSlots(int level, Integer value) {
        value = capValue(value, getMaximumSpellSlots(level));
        switch (level) {
            case 1:
                currentFirstLevelSpellSlots = value;
                break;
            case 2:
                currentSecondLevelSpellSlots = value;
                break;
            case 3:
                currentThirdLevelSpellSlots = value;
                break;
            case 4:
                currentFourthLevelSpellSlots = value;
                break;
            case 5:
                currentFifthLevelSpellSlots = value;
                break;
            case 6:
                currentSixthLevelSpellSlots = value;
                break;
            case 7:
                currentSeventhLevelSpellSlots = value;
                break;
            case 8:
                currentEighthLevelSpellSlots = value;
                break;
            case 9:
                currentNinthLevelSpellSlots = value;
                break;
            default:
                throw new IllegalArgumentException("no spells slots for level " + level);
        }
    }

    private Integer getMaximumSpellSlots(int level) {
        switch (level) {
            case 1:
                return maxFirstLevelSpellSlots;
            case 2:
                return maxSecondLevelSpellSlots;
            case 3:
                return maxThirdLevelSpellSlots;
            case 4:
                return maxFourthLevelSpellSlots;
            case 5:
                return maxFifthLevelSpellSlots;
            case 6:
                return maxSixthLevelSpellSlots;
            case 7:
                return maxSeventhLevelSpellSlots;
            case 8:
                return maxEighthLevelSpellSlots;
            case 9:
                return maxNinthLevelSpellSlots;
            default:
                throw new IllegalArgumentException("no spells slots for level " + level);
        }
    }

    private void setMaximumSpellSlots(int level, Integer value) {
        switch (level) {
            case 1:
                maxFirstLevelSpellSlots = value;
                break;
            case 2:
                maxSecondLevelSpellSlots = value;
                break;
            case 3:
                maxThirdLevelSpellSlots = value;
                break;
            case 4:
                maxFourthLevelSpellSlots = value;
                break;
            case 5:
                maxFifthLevelSpellSlots = value;
                break;
            case 6:
                maxSixthLevelSpellSlots = value;
                break;
            case 7:
                maxSeventhLevelSpellSlots = value;
                break;
            case 8:
                maxEighthLevelSpellSlots = value;
                break;
            case 9:
                maxNinthLevelSpellSlots = value;
                break;
            default:
                throw new IllegalArgumentException("no spells slots for level " + level);
        }
        setCurrentSpellSlots(level, capValue(getCurrentSpellSlots(level), value));
    }

    @Column(name = "maximum_first_level_spell_slots")
    @Min(value = 0, message = "maximum spell slots cannot be negative")
    @NotNull
    public Integer getMaxFirstLevelSpellSlots() {
        return getMaximumSpellSlots(1);
    }

    public void setMaxFirstLevelSpellSlots(Integer maxFirstLevelSpellSlots) {
        setMaximumSpellSlots(1, maxFirstLevelSpellSlots);
    }

    @Column(name = "current_first_level_spell_slots")
    @Min(value = 0, message = "current spell slots cannot be negative")
    @NotNull
    public Integer getCurrentFirstLevelSpellSlots() {
        return getCurrentSpellSlots(1);
    }

    public void setCurrentFirstLevelSpellSlots(Integer currentFirstLevelSpellSlots) {
        setCurrentSpellSlots(1, currentFirstLevelSpellSlots);
    }

    @Column(name = "maximum_second_level_spell_slots")
    @Min(value = 0, message = "maximum spell slots cannot be negative")
    @NotNull
    public Integer getMaxSecondLevelSpellSlots() {
        return getMaximumSpellSlots(2);
    }

    public void setMaxSecondLevelSpellSlots(Integer maxSecondLevelSpellSlots) {
        setMaximumSpellSlots(2, maxSecondLevelSpellSlots);
    }

    @Column(name = "current_second_level_spell_slots")
    @Min(value = 0, message = "current spell slots cannot be negative")
    @NotNull
    public Integer getCurrentSecondLevelSpellSlots() {
        return getCurrentSpellSlots(2);
    }

    public void setCurrentSecondLevelSpellSlots(Integer currentSecondLevelSpellSlots) {
        setCurrentSpellSlots(2, currentSecondLevelSpellSlots);
    }

    @Column(name = "maximum_third_level_spell_slots")
    @Min(value = 0, message = "maximum spell slots cannot be negative")
    @NotNull
    public Integer getMaxThirdLevelSpellSlots() {
        return getMaximumSpellSlots(3);
    }

    public void setMaxThirdLevelSpellSlots(Integer maxThirdLevelSpellSlots) {
        setMaximumSpellSlots(3, maxThirdLevelSpellSlots);
    }

    @Column(name = "current_third_level_spell_slots")
    @Min(value = 0, message = "current spell slots cannot be negative")
    @NotNull
    public Integer getCurrentThirdLevelSpellSlots() {
        return getCurrentSpellSlots(3);
    }

    public void setCurrentThirdLevelSpellSlots(Integer currentThirdLevelSpellSlots) {
        setCurrentSpellSlots(3, currentThirdLevelSpellSlots);
    }

    @Column(name = "maximum_fourth_level_spell_slots")
    @Min(value = 0, message = "maximum spell slots cannot be negative")
    @NotNull
    public Integer getMaxFourthLevelSpellSlots() {
        return getMaximumSpellSlots(4);
    }

    public void setMaxFourthLevelSpellSlots(Integer maxFourthLevelSpellSlots) {
        setMaximumSpellSlots(4, maxFourthLevelSpellSlots);
    }

    @Column(name = "current_fourth_level_spell_slots")
    @Min(value = 0, message = "current spell slots cannot be negative")
    @NotNull
    public Integer getCurrentFourthLevelSpellSlots() {
        return getCurrentSpellSlots(4);
    }

    public void setCurrentFourthLevelSpellSlots(Integer currentFourthLevelSpellSlots) {
        setCurrentSpellSlots(4, currentFourthLevelSpellSlots);
    }

    @Column(name = "maximum_fifth_level_spell_slots")
    @Min(value = 0, message = "maximum spell slots cannot be negative")
    @NotNull
    public Integer getMaxFifthLevelSpellSlots() {
        return getMaximumSpellSlots(5);
    }

    public void setMaxFifthLevelSpellSlots(Integer maxFifthLevelSpellSlots) {
        setMaximumSpellSlots(5, maxFifthLevelSpellSlots);
    }

    @Column(name = "current_fifth_level_spell_slots")
    @Min(value = 0, message = "current spell slots cannot be negative")
    @NotNull
    public Integer getCurrentFifthLevelSpellSlots() {
        return getCurrentSpellSlots(5);
    }

    public void setCurrentFifthLevelSpellSlots(Integer currentFifthLevelSpellSlots) {
        setCurrentSpellSlots(5, currentFifthLevelSpellSlots);
    }

    @Column(name = "maximum_sixth_level_spell_slots")
    @Min(value = 0, message = "maximum spell slots cannot be negative")
    @NotNull
    public Integer getMaxSixthLevelSpellSlots() {
        return getMaximumSpellSlots(6);
    }

    public void setMaxSixthLevelSpellSlots(Integer maxSixthLevelSpellSlots) {
        setMaximumSpellSlots(6, maxSixthLevelSpellSlots);
    }

    @Column(name = "current_sixth_level_spell_slots")
    @Min(value = 0, message = "current spell slots cannot be negative")
    @NotNull
    public Integer getCurrentSixthLevelSpellSlots() {
        return getCurrentSpellSlots(6);
    }

    public void setCurrentSixthLevelSpellSlots(Integer currentSixthLevelSpellSlots) {
        setCurrentSpellSlots(6, currentSixthLevelSpellSlots);
    }

    @Column(name = "maximum_seventh_level_spell_slots")
    @Min(value = 0, message = "maximum spell slots cannot be negative")
    @NotNull
    public Integer getMaxSeventhLevelSpellSlots() {
        return getMaximumSpellSlots(7);
    }

    public void setMaxSeventhLevelSpellSlots(Integer maxSeventhLevelSpellSlots) {
        setMaximumSpellSlots(7, maxSeventhLevelSpellSlots);
    }

    @Column(name = "current_seventh_level_spell_slots")
    @Min(value = 0, message = "current spell slots cannot be negative")
    @NotNull
    public Integer getCurrentSeventhLevelSpellSlots() {
        return getCurrentSpellSlots(7);
    }

    public void setCurrentSeventhLevelSpellSlots(Integer currentSeventhLevelSpellSlots) {
        setCurrentSpellSlots(7, currentSeventhLevelSpellSlots);
    }

    @Column(name = "maximum_eighth_level_spell_slots")
    @Min(value = 0, message = "maximum spell slots cannot be negative")
    @NotNull
    public Integer getMaxEighthLevelSpellSlots() {
        return getMaximumSpellSlots(8);
    }

    public void setMaxEighthLevelSpellSlots(Integer maxEightLevelSpellSlots) {
        setMaximumSpellSlots(8, maxEightLevelSpellSlots);
    }

    @Column(name = "current_eighth_level_spell_slots")
    @Min(value = 0, message = "current spell slots cannot be negative")
    @NotNull
    public Integer getCurrentEighthLevelSpellSlots() {
        return getCurrentSpellSlots(8);
    }

    public void setCurrentEighthLevelSpellSlots(Integer currentFirstLevelSpellSlots) {
        setCurrentSpellSlots(8, currentFirstLevelSpellSlots);
    }

    @Column(name = "maximum_ninth_level_spell_slots")
    @Min(value = 0, message = "maximum spell slots cannot be negative")
    @NotNull
    public Integer getMaxNinthLevelSpellSlots() {
        return getMaximumSpellSlots(9);
    }

    public void setMaxNinthLevelSpellSlots(Integer maxNinthLevelSpellSlots) {
        setMaximumSpellSlots(9, maxNinthLevelSpellSlots);
    }

    @Column(name = "current_ninth_level_spell_slots")
    @Min(value = 0, message = "current spell slots cannot be negative")
    @NotNull
    public Integer getCurrentNinthLevelSpellSlots() {
        return getCurrentSpellSlots(9);
    }

    public void setCurrentNinthLevelSpellSlots(Integer currentFirstLevelSpellSlots) {
        setCurrentSpellSlots(9, currentFirstLevelSpellSlots);
    }
}
