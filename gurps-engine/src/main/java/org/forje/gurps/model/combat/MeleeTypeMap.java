package org.forje.gurps.model.combat;

import org.forje.gurps.model.GenericMap;

public class MeleeTypeMap extends GenericMap<MeleeType> {

    private static MeleeTypeMap _instance = new MeleeTypeMap();

    static {
        _instance.add("sw", MeleeType.Swinging);
        _instance.add("thr", MeleeType.Thrusting);
    }

    public static MeleeTypeMap getInstance() {
        return _instance;
    }

}
