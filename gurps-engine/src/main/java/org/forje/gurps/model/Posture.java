package org.forje.gurps.model;

public enum Posture {
    Standing(0,0, 0, 1),
    Crouching(-2,0,-2,2/3),
    Kneeling(-2,-2,-2,1/3),
    Crawling(-4,-3,-2,1/3),
    Sitting(-2,-2,-2,0),
    Prone(-4,-3,-2,1/10);

    private final int _attackModifier;
    private final int _defenseModifier;
    private final int _targetModifier;
    private final float _movementModifier;

    private Posture(final int attackModifier,
                    final int defenceModifier,
                    final int targetModifier,
                    final float movementModifier) {
        _attackModifier = attackModifier;
        _defenseModifier = defenceModifier;
        _targetModifier = targetModifier;
        _movementModifier = movementModifier;
    }

    public int getAttackModifier() {
        return _attackModifier;
    }

    public int getDefenseModifier() {
        return _defenseModifier;
    }

    public int getTargetModifier() {
        return _targetModifier;
    }

    public float getMovementModifier() {
        return _movementModifier;
    }
}
