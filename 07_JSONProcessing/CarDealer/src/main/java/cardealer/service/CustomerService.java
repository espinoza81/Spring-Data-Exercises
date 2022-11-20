package cardealer.service;

import cardealer.domain.custumer.CustomerOrderBirthdateDto;
import cardealer.domain.custumer.CustomerTotalSalesDto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface CustomerService {
    void seedCustomers() throws IOException;

    List<CustomerOrderBirthdateDto> getAllCustomers();

    List<CustomerTotalSalesDto> getAllWithTotalSales();
}
