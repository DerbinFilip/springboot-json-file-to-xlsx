package pl.derbin.readjsonfile.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.derbin.readjsonfile.entity.Weather;

public interface WeatherRepo extends JpaRepository<Weather, Long> {
}
