package com.training.demo.common.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.LocalDateTime;

@Configuration
public class TimeUtil {

    public static String timeAgo(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(dateTime, now);

        long seconds = duration.getSeconds();
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        long months = days / 30; // Approximation
        long years = days / 365; // Approximation

        if (minutes == 0) {
            return "À l'instant";
        } else if (minutes < 60) {
            return minutes + " minute" + (minutes >= 0 ? "s" : "");
        } else if (hours < 24) {
            return hours + " heure" + (hours >= 1 ? "s" : "");
        } else if (days < 30) {
            return days + " jour" + (days >= 1 ? "s" : "");
        } else if (months < 12) {
            return months + " mois";
        } else {
            return years + " an" + (years >= 1 ? "s" : "");
        }
    }

    @Bean
    public TimeUtil timeAgoUtil() {
        return new TimeUtil();
    }

}
