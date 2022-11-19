package cardealer.service.impl;

import cardealer.constant.PathFiles;
import cardealer.domain.custumer.Customer;
import cardealer.domain.custumer.CustomerImportDto;
import cardealer.repository.CustomerRepository;
import cardealer.service.CustomerService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
}
