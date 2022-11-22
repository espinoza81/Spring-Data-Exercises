package cardealer.config;

import cardealer.domain.custumer.Customer;
import cardealer.domain.custumer.dtos.CustomerOrderBirthdateDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        customerToCustomerOrderBirthdateDto(mapper);

        return mapper;
    }

    private void customerToCustomerOrderBirthdateDto(ModelMapper mapper) {
        Converter<LocalDateTime, String> toDateToString =
                ctx -> DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(ctx.getSource());

        mapper.createTypeMap(Customer.class, CustomerOrderBirthdateDto.class)
                .addMapping(Customer::getBirthDate, CustomerOrderBirthdateDto::setBirthDate);
    }
}
