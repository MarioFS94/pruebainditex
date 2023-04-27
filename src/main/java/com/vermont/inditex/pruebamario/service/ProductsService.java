package com.vermont.inditex.pruebamario.service;

import com.vermont.inditex.pruebamario.model.PriceRequestDto;
import com.vermont.inditex.pruebamario.model.PriceResponseDTO;
import com.vermont.inditex.pruebamario.repository.ProductsRepository;
import com.vermont.inditex.pruebamario.repository.entities.ProductEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public PriceResponseDTO getProductData(PriceRequestDto requestDto) {
        //productsRepository.findByStartDateAndProductIdAndBrandId(applicationDate, idProduct, idBrand);
        //ProductEntity productEntity = productsRepository.findProductRate(requestDto.getProductId(), requestDto.getBrandId(), requestDto.getApplicationDate());
        List<ProductEntity> productEntities = productsRepository.findProductRate(requestDto);
        ProductEntity productEntity = null;

        if (productEntities.isEmpty()) {
            throw new NotFoundException("No existen datos con esos parametros");
        } else {
            if (productEntities.size() > 1) {
                List<Integer> priorities = new ArrayList<>();
                //get product priorities
                productEntities.stream()
                        .map(p -> priorities.add(p.getPriority()))
                        .collect(Collectors.toList());

                //Compare and get the major
                Integer maxPriority = priorities.stream()
                        .max(Comparator.comparing(v -> v))
                        .orElseThrow(NoSuchElementException::new);

                //filter product by priority
                productEntity = productEntities.stream()
                        .filter(productEntity1 -> productEntity1.getPriority() == maxPriority.intValue())
                        .findAny()
                        .orElseThrow(NoSuchElementException::new);
            } else {
                productEntity = productEntities.get(0);
            }
        }
//mapear a price response
        PriceResponseDTO response = mapToPriceResponse(productEntity);

        return response;
    }

    private PriceResponseDTO mapToPriceResponse(ProductEntity productEntity) {
        PriceResponseDTO priceResponseDTO = new PriceResponseDTO();
        priceResponseDTO.setBrandId(productEntity.getBrandId());
        priceResponseDTO.setProductId(productEntity.getProductId());
        priceResponseDTO.setRate(productEntity.getBrandId());
        priceResponseDTO.setStartDate(productEntity.getStartDate());
        priceResponseDTO.setEndDate(productEntity.getEndDate());
        priceResponseDTO.setPvp(productEntity.getPvp());

        return priceResponseDTO;
    }

}
