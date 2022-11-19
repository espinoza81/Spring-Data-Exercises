package cardealer.service.impl;

import cardealer.constant.PathFiles;
import cardealer.domain.supplier.Supplier;
import cardealer.domain.supplier.SupplierImportDto;
import cardealer.repository.SupplierRepository;
import cardealer.service.SupplierService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ModelMapper mapper;
    private final Gson gson;

    @Autowired
    public SupplierServiceImpl(
            SupplierRepository supplierRepository,
            ModelMapper mapper,
            Gson gson) {
        this.supplierRepository = supplierRepository;
        this.mapper = mapper;
        this.gson = gson;
    }

    @Override
    public void seedSupplier() throws IOException {
        if (supplierRepository.count() == 0) {
            final FileReader fileReader = new FileReader(PathFiles.SUPPLIERS_FILE_PATH.toFile());

            final List<Supplier> suppliers = Arrays.stream(gson.fromJson(fileReader, SupplierImportDto[].class))
                    .map(supplierImportDto -> mapper.map(supplierImportDto, Supplier.class))
                    .toList();

            fileReader.close();

            this.supplierRepository.saveAllAndFlush(suppliers);
        }
    }
}
