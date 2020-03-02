package com.litvas.airport.utils.impl;

import com.litvas.airport.utils.TimeUtils;
import org.springframework.stereotype.Component;

@Component
public class RandomTimeUtils implements TimeUtils {

    @Override
    public void addWaiting() {
        try {
            int seconds = 5 + (int) (Math.random() * 5);
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
