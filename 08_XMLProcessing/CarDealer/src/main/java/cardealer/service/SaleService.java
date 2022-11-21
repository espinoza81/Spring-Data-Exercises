package cardealer.service;

import cardealer.domain.sale.SaleWithDiscountDto;

import java.util.List;

public interface SaleService {
    void seedSales();

    List<SaleWithDiscountDto> getAllSalesWithDiscount();
}
