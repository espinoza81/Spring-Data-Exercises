package cardealer.service.impl;

import cardealer.constant.PathFiles;
import cardealer.domain.supplier.dtos.LocalSupplierDto;
import cardealer.domain.supplier.Supplier;
import cardealer.domain.supplier.wrapper.LocalSupplierWrapper;
import cardealer.domain.supplier.wrapper.SupplierImportWrapperDto;
import cardealer.repository.SupplierRepository;
import cardealer.service.SupplierService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

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
    public void seedSupplier() throws IOException, JAXBException {
        if (supplierRepository.count() == 0) {

            final FileReader fileReader = new FileReader(PathFiles.SUPPLIERS_FILE_PATH.toFile());

            final JAXBContext context = JAXBContext.newInstance(SupplierImportWrapperDto.class);
            final Unmarshaller unmarshaller = context.createUnmarshaller();
            final SupplierImportWrapperDto suppliersDto = (SupplierImportWrapperDto) unmarshaller.unmarshal(fileReader);

            final List<Supplier> suppliers = suppliersDto
                    .getSuppliers()
                    .stream()
                    .map(supplier -> mapper.map(supplier, Supplier.class))
                    .toList();

            fileReader.close();

            this.supplierRepository.saveAllAndFlush(suppliers);
        }
    }

    @Override
    public LocalSupplierWrapper getAllLocalSuppliers() {
        List<LocalSupplierDto> localSupplierDtos = this.supplierRepository
                .getLocalSupplierPartCount()
                .orElseThrow(NoSuchElementException::new);

        return new LocalSupplierWrapper(localSupplierDtos);
    }
}
