package cardealer.config;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public LocalDateTime unmarshal(String dateTime) throws Exception {
        return LocalDateTime.parse(dateTime, dateFormat);
    }

    @Override
    public String marshal(LocalDateTime dateTime) throws Exception {
        return dateTime.format(dateFormat);
    }
}
