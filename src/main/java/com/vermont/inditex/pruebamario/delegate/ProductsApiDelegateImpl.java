package com.vermont.inditex.pruebamario.delegate;

import com.vermont.inditex.pruebamario.api.ProductsApiDelegate;
import com.vermont.inditex.pruebamario.model.PriceRequestDto;
import com.vermont.inditex.pruebamario.model.PriceResponseDTO;
import com.vermont.inditex.pruebamario.service.ProductsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductsApiDelegateImpl implements ProductsApiDelegate {

    private final ProductsService productsService;

    public ProductsApiDelegateImpl(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Override
    public ResponseEntity<PriceResponseDTO> getProductData(Integer idProduct, Integer idBrand, String applicationDate) {
        PriceResponseDTO productData = productsService.getProductData(new PriceRequestDto(idProduct, idBrand, applicationDate));
        Optional<PriceResponseDTO> opt = Optional.of(productData);
        return ResponseEntity.of(opt);
    }
}
