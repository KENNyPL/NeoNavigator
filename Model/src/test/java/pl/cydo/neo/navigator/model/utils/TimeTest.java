package pl.cydo.neo.navigator.model.utils;


import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimeTest {

    @Test
    public void shouldGetValidTimeParts(){
        //given
        Time time = new Time(22, 49);
        Time time2 = new Time(0, 59);
        Time time3 = new Time(1, 1);

        //then
        assertEquals(time.getHour(), 22);
        assertEquals(time2.getHour(), 0);
        assertEquals(time3.getHour(), 1);
        assertEquals(time.getMinutes(), 49);
        assertEquals(time2.getMinutes(), 59);
        assertEquals(time3.getMinutes(), 1);
    }
}
