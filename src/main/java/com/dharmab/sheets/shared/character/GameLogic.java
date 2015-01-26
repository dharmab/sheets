package com.dharmab.sheets.shared.character;

public class GameLogic {
    /**
     * Calculate an ability score modifier
     *
     * @param abilityScore ability score
     * @return The ability score modifier for the given ability score
     */
    public static int computeAbilityModifier(Integer abilityScore) {
        if (abilityScore >= 10) {
            return (abilityScore - 10) / 2;
        } else {
            return (int) Math.floor((abilityScore - 10) / 2d);
        }
    }

    /**
     * Calculate passive wisdom (i.e. passive perception)
     *
     * @param wisdom wisdom ability score
     * @return passive wisdom
     */
    public static int computePassiveWisdom(int wisdom) {
        return wisdom + 10;
    }

    /**
     * Calculate base initiative
     *
     * @param dexterity dexterity ability score
     * @return base initiative
     */
    public static int computeInitiative(int dexterity) {
        return computeAbilityModifier(dexterity);
    }
}

