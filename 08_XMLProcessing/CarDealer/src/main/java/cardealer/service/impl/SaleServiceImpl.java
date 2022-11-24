package cardealer.service.impl;

import cardealer.constant.Discount;
import cardealer.domain.car.Car;
import cardealer.domain.custumer.Customer;
import cardealer.domain.sale.Sale;
import cardealer.domain.sale.dtos.SaleDto;
import cardealer.domain.sale.dtos.SaleWithDiscountDto;
import cardealer.domain.sale.wrapper.SaleDiscountWrapper;
import cardealer.repository.CarRepository;
import cardealer.repository.CustomerRepository;
import cardealer.repository.SaleRepository;
import cardealer.service.SaleService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.LongStream;

@Service
public class SaleServiceImpl implements SaleService {
    private final ModelMapper mapper;
    private final Gson gson;
    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public SaleServiceImpl(
            ModelMapper mapper,
            Gson gson,
            SaleRepository saleRepository,
            CarRepository carRepository,
            CustomerRepository customerRepository) {
        this.mapper = mapper;
        this.gson = gson;
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void seedSales() {
        if(saleRepository.count() == 0) {
            final Random random = new Random();

            long high = this.carRepository.count();

            final long numberOfSales = random.nextLong(high);

            final List<Sale> sales = new ArrayList<>();
            Set<Car> saleCars = new HashSet<>();

            LongStream.range(0, numberOfSales)
                    .forEach(number -> {
                        Car car = this.carRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);

                        while (saleCars.contains(car)){
                            car = this.carRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);
                        }

                        saleCars.add(car);

                        Customer customer = this.customerRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);

                        Integer discount = Discount.getDiscount();

                        Sale newSale = new Sale(discount, car, customer);
                        sales.add(newSale);
                    });

            this.saleRepository.saveAllAndFlush(sales);
        }
    }

    @Override
    @Transactional
    public SaleDiscountWrapper getAllSalesWithDiscount() {

        List<SaleWithDiscountDto> saleWithDiscountDtos = this.saleRepository
                .findAll()
                .stream()
                .map(sale -> mapper.map(sale, SaleDto.class))
                .map(SaleDto::saleWithDiscountDto)
                .toList();

        return new SaleDiscountWrapper(saleWithDiscountDtos);
    }
}