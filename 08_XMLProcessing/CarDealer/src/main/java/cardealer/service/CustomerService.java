package cardealer.service;

import cardealer.domain.custumer.CustomerTotalSalesDto;
import cardealer.domain.custumer.wrapper.CustomerOrderBirthdateWrapperDto;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface CustomerService {
    void seedCustomers() throws IOException, JAXBException;

    CustomerOrderBirthdateWrapperDto getAllCustomers();

    List<CustomerTotalSalesDto> getAllWithTotalSales();
}
