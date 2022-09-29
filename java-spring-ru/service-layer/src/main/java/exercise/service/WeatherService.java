package exercise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.HttpClient;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import exercise.CityNotFoundException;
import exercise.repository.CityRepository;
import exercise.model.City;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class WeatherService {

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    // BEGIN
    public Map<String, String> getWeatherToCity(City city) {

        Map<String, String> result = new HashMap<>();

        String weather = client.get("http://weather/api/v2/cities/" + city.getName());

        ObjectMapper mapper = new ObjectMapper();

        try {
            result = mapper.readValue(weather, Map.class);
        } catch (JsonProcessingException ex) {
            return result;
        }

        return result;
    }
    // END
}
