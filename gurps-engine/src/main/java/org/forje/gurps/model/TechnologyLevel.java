package org.forje.gurps.model;

public enum TechnologyLevel {

    StoneAge(0, "StoneAge"),
    BronzeAge(1, "Bronze Age"),
    IronAge (2, "Iron Age"),
    Medieval(3, "Medieval"),
    AgeOfSail (4,"Age of Sail"),
    IndustrialRevolution (5,"Industrial Revolution"),
    MechanizedAge (6,"Mechanized Age"),
    NuclearAge (7,"Nuclear Age"),
    DigitalAge (8,"Digital Age"),
    MicrotechAge (9,"Microtech Age"),
    RoboticAge (10,"Robotic Age"),
    AgeOfExoticMatter (11,"Age of Exotic Matter"),
    Unrestriced (12, "Unrestricted"); // @todo need to find a better name for this

    private int _level;
    private String _name;

    private TechnologyLevel(final int level, final String name) {
        _level = level;
        _name = name;
    }

    public String toString() {
        return _name;
    }

}
