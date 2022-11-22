package cardealer.service.impl;

import cardealer.constant.PathFiles;
import cardealer.domain.custumer.*;
import cardealer.domain.custumer.dtos.CustomerOrderBirthdateDto;
import cardealer.domain.custumer.wrapper.CustomerImportWrapperDto;
import cardealer.domain.custumer.wrapper.CustomerOrderBirthdateWrapperDto;
import cardealer.repository.CustomerRepository;
import cardealer.service.CustomerService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final ModelMapper mapper;
    private final Gson gson;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(ModelMapper mapper,
                               Gson gson,
                               CustomerRepository customerRepository) {
        this.mapper = mapper;
        this.gson = gson;
        this.customerRepository = customerRepository;
    }

    @Override
    public void seedCustomers() throws IOException, JAXBException {
        if(customerRepository.count() == 0) {
            final FileReader fileReader = new FileReader(PathFiles.CUSTOMERS_FILE_PATH.toFile());

            final JAXBContext context = JAXBContext.newInstance(CustomerImportWrapperDto.class);
            final Unmarshaller unmarshaller = context.createUnmarshaller();
            final CustomerImportWrapperDto customersDto = (CustomerImportWrapperDto) unmarshaller.unmarshal(fileReader);

            List<Customer> customers = customersDto
                    .getCustomers()
                    .stream()
                    .map(customer -> mapper.map(customer, Customer.class))
                    .toList();

            fileReader.close();

            this.customerRepository.saveAllAndFlush(customers);
        }
    }

    @Override
    public CustomerOrderBirthdateWrapperDto getAllCustomers() {

        List<CustomerOrderBirthdateDto> customers = this.customerRepository.findAllByOrderByBirthDateAscYoungDriverDesc()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(customer -> mapper.map(customer, CustomerOrderBirthdateDto.class))
                .toList();

        return new CustomerOrderBirthdateWrapperDto(customers);
    }

    @Override
    public List<CustomerTotalSalesDto> getAllWithTotalSales() {
        return this.customerRepository
                .getAllCustomersWithTotalSale()
                .orElseThrow(NoSuchElementException::new);
    }
}