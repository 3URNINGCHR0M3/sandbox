package org.forje.gurps.model.combat;

import org.forje.gurps.model.GenericMap;

import java.util.HashMap;
import java.util.Map;

public class DamageTypeMap extends GenericMap<DamageType> {

    private static DamageTypeMap _instance = new DamageTypeMap();

    public static DamageTypeMap getInstance() {
        return _instance;
    }

    static {
        _instance.add("cut", DamageType.Cutting);
        _instance.add("burn", DamageType.Burning);
        _instance.add("cr", DamageType.Crushing);
        _instance.add("imp", DamageType.Impaling);
        _instance.add("pi+", DamageType.LargePiercing);
        _instance.add("pi++", DamageType.HugePiercing);
        _instance.add("pi", DamageType.Piercing);
        _instance.add("pi-", DamageType.SmallPiercing);
    }

}
