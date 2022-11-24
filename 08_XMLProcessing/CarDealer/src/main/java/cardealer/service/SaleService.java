package cardealer.service;

import cardealer.domain.sale.dtos.SaleWithDiscountDto;
import cardealer.domain.sale.wrapper.SaleDiscountWrapper;

import java.util.List;

public interface SaleService {
    void seedSales();

    SaleDiscountWrapper getAllSalesWithDiscount();
}
