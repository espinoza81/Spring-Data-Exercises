package cardealer.service.impl;

import cardealer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Service
public class SeedServiceImpl implements SeedService {
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;

    private final SaleService saleService;

    @Autowired
    public SeedServiceImpl(
            SupplierService supplierService,
            PartService partService,
            CarService carService,
            CustomerService customerService,
            SaleService saleService){
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void seedData() throws IOException, JAXBException {
        supplierService.seedSupplier();
        partService.seedPart();
        carService.seedCars();
        customerService.seedCustomers();
        saleService.seedSales();
    }
}