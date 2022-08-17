package pl.derbin.readjsonfile.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.derbin.readjsonfile.entity.Weather;
import pl.derbin.readjsonfile.service.WeatherService;

import java.util.List;

@RestController
@RequestMapping("weather")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/getAll")
    public List<Weather> getAllWeatherInfo() {
        return weatherService.getAllWeatherInfo();
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> getFile() {
        String filename = "weatherInfo.xlsx";
        InputStreamResource file = new InputStreamResource(weatherService.load());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }
}

@Controller
class WebController {
    @RequestMapping
    public String index(){
        return "index.html";
    }
}
