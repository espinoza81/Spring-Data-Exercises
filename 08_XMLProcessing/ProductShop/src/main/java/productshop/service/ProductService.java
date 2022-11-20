package productshop.service;

import productshop.domain.product.ProductWithoutBuyerDto;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface ProductService {

    void seedProducts() throws IOException, JAXBException;

    List<ProductWithoutBuyerDto> findAllWithoutBuyer(double bottom, double top);
}