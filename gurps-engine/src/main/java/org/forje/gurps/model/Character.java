package org.forje.gurps.model;

import org.forje.gurps.model.combat.Targetable;

/**
* Represents some character in the simulation.
*/
public interface Character extends Entity, Targetable {
    public Posture getPosture();
    public int getStrength();
    public int getDexterity();
    public int getIntelligence();
    public int getHealth();
    public int getTotalHitPoints();
    public int getCurrentHitPoints();
    public int getWill();
    public int getPerception();
    public int getFatiguePoints();
    public int getBasicSpeed();
    public int getBasicMove();
    public String getName();
    public CharacterClass getCharacterClass();
}
