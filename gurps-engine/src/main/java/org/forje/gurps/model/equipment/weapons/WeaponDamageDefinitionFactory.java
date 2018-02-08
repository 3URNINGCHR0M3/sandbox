package org.forje.gurps.model.equipment.weapons;

import org.forje.gurps.model.combat.DamageType;
import org.forje.gurps.model.combat.DamageTypeMap;
import org.forje.gurps.model.combat.MeleeType;
import org.forje.gurps.model.combat.MeleeTypeMap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* This class is responsible for parsing weapon damage definition Strings and returning an object representing it.
*/
public class WeaponDamageDefinitionFactory {

    private final static String MUSCLE_PATTERN_STRING = "(sw|thr)((\\+|-)\\d+)? (cut|imp|cr|burn|pi|pi+|pi-|pi++)";

    // group 1 = melee type
    // group 2 = modifier
    // group 4 = damage type

    private final static Pattern MUSCLE_PATTERN = Pattern.compile(MUSCLE_PATTERN_STRING, Pattern.CASE_INSENSITIVE);

    private final static String FIREARM_PATTERN_STRING = "(\\d)+d((\\+|-)\\d+)* (cut|imp|cr|burn|pi(\\+{0,2}|-)?)";
    private final static Pattern FIREARM_PATTERN = Pattern.compile(FIREARM_PATTERN_STRING, Pattern.CASE_INSENSITIVE);


    public static MuscleBasedDamageDefinition parseMuscleBased(final String s) {

        final Matcher matcher = MUSCLE_PATTERN.matcher(s);

        matcher.matches();

        final String meleeType = matcher.group(1);
        final String modifier = matcher.group(2);
        final String damageType = matcher.group(4);

        final MuscleWDDImpl wdd = new MuscleWDDImpl();

        wdd._meleeType = MeleeTypeMap.getInstance().getValue(meleeType);
        wdd._modifier = Integer.parseInt(modifier);
        wdd._damageType = DamageTypeMap.getInstance().getValue(damageType);

        return wdd;

    }

    public static FirearmDamageDefinition parseFirearm(final String definitionString) {

        final Matcher matcher = FIREARM_PATTERN.matcher(definitionString);

        final boolean matches = matcher.matches();

        // todo check boolean return

        final String diceString = matcher.group(1);
        final String modifierString = matcher.group(2);
        final String damageTypeString = matcher.group(4);

        final FirearmWDDImpl wdd = new FirearmWDDImpl();

        wdd._dice = Integer.parseInt(diceString);
        wdd._damageType = DamageTypeMap.getInstance().getValue(damageTypeString);

        if (modifierString != null) {
            wdd._modifier = Integer.parseInt(modifierString);
        }

        return wdd;

    }

    private static class FirearmWDDImpl implements FirearmDamageDefinition {

        private int _dice;
        private int _modifier;
        private DamageType _damageType;

        @Override
        public int getDiceCount() {
            return _dice;
        }

        @Override
        public int getModifier() {
            return _modifier;
        }

        @Override
        public DamageType getDamageType() {
            return _damageType;
        }

    }

    private static class MuscleWDDImpl implements MuscleBasedDamageDefinition {

        private MeleeType _meleeType;

        /**
        * The modifier to be added to the character attribute associated with the attack type;
        */
        private int _modifier;

        private DamageType _damageType;

        @Override
        public MeleeType getMeleeType() {
            return _meleeType;
        }

        @Override
        public int getDamageModifier() {
            return _modifier;
        }

        @Override
        public DamageType getDamageType() {
            return _damageType;
        }

    }

}
