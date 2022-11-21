package cardealer.service.impl;

import cardealer.constant.PathFiles;
import cardealer.domain.custumer.*;
import cardealer.domain.sale.Sale;
import cardealer.repository.CustomerRepository;
import cardealer.service.CustomerService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.Arrays;
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
    public void seedCustomers() throws IOException {
        if(customerRepository.count() == 0) {
            final FileReader fileReader = new FileReader(PathFiles.CUSTOMERS_FILE_PATH.toFile());

            List<Customer> customers = Arrays.stream(gson.fromJson(fileReader, CustomerImportDto[].class))
                    .map(customerImportDto -> mapper.map(customerImportDto, Customer.class))
                    .toList();

            fileReader.close();

            this.customerRepository.saveAllAndFlush(customers);
        }
    }

    @Override
    public List<CustomerOrderBirthdateDto> getAllCustomers() {

        return this.customerRepository.findAllByOrderByBirthDateAscYoungDriverDesc()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(customer -> mapper.map(customer, CustomerOrderBirthdateDto.class))
                .toList();
    }

    @Override
    public List<CustomerTotalSalesDto> getAllWithTotalSales() {
        return this.customerRepository
                .getAllCustomersWithTotalSale()
                .orElseThrow(NoSuchElementException::new);
    }
}