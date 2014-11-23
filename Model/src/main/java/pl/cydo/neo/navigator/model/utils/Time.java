package pl.cydo.neo.navigator.model.utils;

import java.security.InvalidParameterException;

public class Time {
    private int time = 0;

    public Time(int time) {
        this.time = time;
    }

    public Time(int hour, int minutes) {
        validNewTime(hour, minutes);
        this.time = (hour*60)+minutes;
    }

    private void validNewTime(int hour, int minutes) {
        if (hour < 0 || hour > 23 || minutes < 0 || minutes > 59) {
            throw new InvalidParameterException();
        }
    }

    public int getTime() {
        return time;
    }
    public int getHour(){
        return time/60;
    }
    public int getMinutes(){
        return time%60;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Time time1 = (Time) o;

        if (time != time1.time) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return time;
    }
}
