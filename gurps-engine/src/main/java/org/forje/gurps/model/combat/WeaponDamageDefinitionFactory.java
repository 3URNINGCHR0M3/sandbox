package org.forje.gurps.model.combat;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* This class is responsible for parsing weapon damage definition Strings and returning an object representing it.
*/
public class WeaponDamageDefinitionFactory {

    private final static String REGEX_STRING = "(sw|thr)((\\+|-)\\d+)? (cut|imp|cr|burn|pi|pi+|pi-|pi++)";
    private final static Pattern PATTERN = Pattern.compile(REGEX_STRING, Pattern.CASE_INSENSITIVE);
    // group 1 = melee type
    // group 2 = modifier
    // group 4 = damage type



    public static WeaponDamageDefinition instance(final String s) {

        final Matcher matcher = PATTERN.matcher(s);

        matcher.matches();

        final String meleeType = matcher.group(1);
        final String modifier = matcher.group(2);
        final String damageType = matcher.group(4);

        final DefaultWDDImpl wdd = new DefaultWDDImpl();

        wdd._meleeType = MeleeType.Swinging;
        wdd._modifier = Integer.parseInt(modifier);
        wdd._damageType = DamageTypeMap.getInstance().getValue(damageType);

        return wdd;

    }

    private static class DefaultWDDImpl implements WeaponDamageDefinition {

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
