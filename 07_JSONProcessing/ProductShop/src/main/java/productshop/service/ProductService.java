package productshop.service;

import productshop.domain.product.ProductWithoutBuyerDto;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    void seedProducts() throws IOException;

    List<ProductWithoutBuyerDto> findAllWithoutBuyer(double bottom, double top);
}