package cardealer.service;

import cardealer.domain.supplier.LocalSupplierDto;

import java.io.IOException;
import java.util.List;

public interface SupplierService {
    void seedSupplier() throws IOException;

    List<LocalSupplierDto> getAllLocalSuppliers();
}
