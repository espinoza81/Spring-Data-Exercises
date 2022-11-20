package productshop.service;

import productshop.domain.product.ProductWithoutBuyerDto;
import productshop.domain.product.XMLProductWithoutBuyerDto;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface ProductService {

    void seedProducts() throws IOException, JAXBException;

    XMLProductWithoutBuyerDto findAllWithoutBuyer(double bottom, double top);
}