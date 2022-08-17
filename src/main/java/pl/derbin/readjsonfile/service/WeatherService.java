package pl.derbin.readjsonfile.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import pl.derbin.readjsonfile.entity.Weather;
import pl.derbin.readjsonfile.excel.WeatherExcel;
import pl.derbin.readjsonfile.repo.WeatherRepo;

import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class WeatherService {
    private static final Logger logger = getLogger(WeatherService.class);
    private final WeatherRepo weatherRepo;

    public WeatherService(WeatherRepo weatherRepo) {
        this.weatherRepo = weatherRepo;
        getWeatherInfo();
    }

    public Weather saveWeatherInfo(Weather weather) {
        return weatherRepo.save(weather);
    }

    public List<Weather> getAllWeatherInfo() {
        return weatherRepo.findAll();
    }

    public ByteArrayInputStream load() {
        List<Weather> weatherList = weatherRepo.findAll();
        return WeatherExcel.weatherToExcel(weatherList);
    }

    public void getWeatherInfo() {
        JSONParser jsonParser = new JSONParser();
        try {
            Object obj = jsonParser.parse(new FileReader("weather_14.json"));
            JSONArray city = (JSONArray) obj;
            for (int i = 0; i < city.size(); i++) {
                storeInfo(city, i);
            }
        } catch (IOException | ParseException ex) {
            logger.error("Exception: ", ex);
        }
    }

    private void storeInfo(JSONArray city, int i) {
        final JSONObject jsonObject = (JSONObject) city.get(i);
        final JSONArray weatherObject = (JSONArray) jsonObject.get("weather");
        final JSONObject object = (JSONObject) weatherObject.get(0);
        final JSONObject cityObject = (JSONObject) jsonObject.get("city");

        final String cityName = (String) cityObject.get("name");
        final String countryName = (String) cityObject.get("country");
        final String mainName = (String) object.get("main");
        final String descriptionName = (String) object.get("description");

        saveWeatherEntity(cityName, countryName, mainName, descriptionName);
    }

    private void saveWeatherEntity(String cityName, String countryName, String mainName, String descriptionName) {
        saveWeatherInfo(new Weather()
                .setName(cityName)
                .setCountry(countryName)
                .setMain(mainName)
                .setDescription(descriptionName));
    }
}
