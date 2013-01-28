package net.forje.taskcoach.types;

public class Duration {

    /**
     * The duration in minutes.
     */
    private final int _duration;


    public Duration(final int duration) {
        _duration = duration;
    }

    @Override
    public String toString() {
        return Long.toString(_duration);
    }


    public int getValue() {
        return _duration;
    }
}
