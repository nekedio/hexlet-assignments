package exercise.controller;

import exercise.CityNotFoundException;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherService weatherService;

    // BEGIN
    @GetMapping("/cities/{id}")
    public Map<String, String> getCityById(@PathVariable Long id) {

        City city = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException("City not found"));

        return weatherService.getWeatherToCity(city);
    }

    @GetMapping("search")
    public List<Map<String, String>> getCities(@RequestParam(defaultValue = "") String name) {

        List<City> cities = cityRepository.findByNameStartsWithIgnoreCase(name);

        List<Map<String, String>> weather = cities.stream()
                .map(c -> weatherService.getWeatherToCity(c))
                .map(c -> {
                    c.remove("cloudy");
                    c.remove("humidity");
                    c.remove("wind");
                    return c;
                })
                .sorted((c1, c2) -> c1.get("name").compareTo(c2.get("name")))
                .collect(Collectors.toList());

        return weather;
    }

    // END
}
