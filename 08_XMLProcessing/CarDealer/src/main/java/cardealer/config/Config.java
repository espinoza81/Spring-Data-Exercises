package cardealer.config;

import com.google.gson.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Scanner;

@Configuration
public class Config {

//    @Bean
//    public ModelMapper createModelMapper() {
//        return new ModelMapper();
//    }

    @Bean
    public Scanner createScanner() {
        return new Scanner(System.in);
    }

    @Bean
    public Gson createGson(){
        JsonDeserializer<LocalDateTime> toLocalDate =
                (json, t, c) -> LocalDateTime.parse(json.getAsString());

        JsonSerializer<String> fromLocalDate =
                (date, t, c) -> new JsonPrimitive(date);

        return new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .registerTypeAdapter(LocalDateTime.class, toLocalDate)
                .registerTypeAdapter(LocalDateTime.class, fromLocalDate)
                .create();
    }
}
