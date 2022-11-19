package cardealer.service.impl;

import cardealer.constant.PathFiles;
import cardealer.domain.part.Part;
import cardealer.domain.part.PartImportDto;
import cardealer.domain.supplier.Supplier;
import cardealer.repository.PartRepository;
import cardealer.repository.SupplierRepository;
import cardealer.service.PartService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.LongStream;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;
    private final ModelMapper mapper;
    private final Gson gson;

    @Autowired
    public PartServiceImpl(
            PartRepository partRepository,
            SupplierRepository supplierRepository,
            ModelMapper mapper,
            Gson gson) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
        this.mapper = mapper;
        this.gson = gson;
    }

    @Override
    public void seedPart() throws IOException {
        if(partRepository.count() == 0){
            final FileReader fileReader = new FileReader(PathFiles.PARTS_FILE_PATH.toFile());

            final List<Part> parts = Arrays.stream(gson.fromJson(fileReader, PartImportDto[].class))
                    .map(partImportDto -> mapper.map(partImportDto, Part.class))
                    .map(this::setRandomSupplier)
                    .toList();

            fileReader.close();

            this.partRepository.saveAllAndFlush(parts);
        }

    }

    private Supplier getRandomSupplier() {
        return this.supplierRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);
    }

    private Part setRandomSupplier(Part part) {

        final Supplier supplier = getRandomSupplier();

        part.setSupplier(supplier);

        return part;
    }
}
