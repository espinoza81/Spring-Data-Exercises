package cardealer.service;

import cardealer.domain.supplier.LocalSupplierDto;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface SupplierService {
    void seedSupplier() throws IOException, JAXBException;

    List<LocalSupplierDto> getAllLocalSuppliers();
}
