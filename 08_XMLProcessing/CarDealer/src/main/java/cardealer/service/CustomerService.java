package cardealer.service;

import cardealer.domain.custumer.dtos.CustomerTotalSalesDto;
import cardealer.domain.custumer.wrapper.CustomerOrderBirthdateWrapperDto;
import cardealer.domain.custumer.wrapper.CustomerTotalSalesWrapper;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface CustomerService {
    void seedCustomers() throws IOException, JAXBException;

    CustomerOrderBirthdateWrapperDto getAllCustomers();

    CustomerTotalSalesWrapper getAllWithTotalSales();
}
