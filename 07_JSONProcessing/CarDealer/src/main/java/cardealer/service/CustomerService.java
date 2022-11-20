package cardealer.service;

import cardealer.domain.custumer.CustomerOrderBirthdateDto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface CustomerService {
    void seedCustomers() throws IOException;

    List<CustomerOrderBirthdateDto> getAllCustomers();
}
