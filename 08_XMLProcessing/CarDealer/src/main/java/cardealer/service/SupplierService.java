package cardealer.service;

import cardealer.domain.supplier.wrapper.LocalSupplierWrapper;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface SupplierService {
    void seedSupplier() throws IOException, JAXBException;

    LocalSupplierWrapper getAllLocalSuppliers();
}
