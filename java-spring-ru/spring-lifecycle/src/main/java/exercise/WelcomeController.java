package exercise;

import exercise.daytimes.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
class WelcomeController {

    @Autowired
    Meal meal;

    @Autowired
    Daytime daytime;

    @GetMapping(path = "/daytime")
    public String getWish() {
        return "It is " + daytime.getName() + "now. Enjoy your " + meal.getMealForDaytime(daytime.getName());
    }

}
// END
