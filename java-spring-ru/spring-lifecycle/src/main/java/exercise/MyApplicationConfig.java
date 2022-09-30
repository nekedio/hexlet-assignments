package exercise;

import java.time.LocalDateTime;

import exercise.daytimes.Daytime;
import exercise.daytimes.Morning;
import exercise.daytimes.Day;
import exercise.daytimes.Evening;
import exercise.daytimes.Night;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// BEGIN
@Configuration
public class MyApplicationConfig {

    @Bean
    public Daytime myBean() {

        Daytime daytime;
        int now = LocalDateTime.now().getHour();

        if (now >= 6 && now < 12) {
            daytime = new Morning();
        } else if (now >= 12 && now < 18) {
            daytime = new Day();
        } else if (now >= 18 && now < 23) {
            daytime = new Evening();
        } else {
            daytime = new Night();
        }

        return daytime;
    }
}
// END
