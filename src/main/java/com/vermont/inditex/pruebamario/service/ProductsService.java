package com.vermont.inditex.pruebamario.service;

import com.vermont.inditex.pruebamario.model.PriceRequestDto;
import com.vermont.inditex.pruebamario.model.PriceResponseDTO;
import com.vermont.inditex.pruebamario.repository.ProductsRepository;
import com.vermont.inditex.pruebamario.repository.entities.ProductEntity;
import com.vermont.inditex.pruebamario.utils.Constants;
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

    /**
     * Principal method, get product data and build response
     * @param requestDto the input data
     * @return  product data
     */
    public PriceResponseDTO getProductData(PriceRequestDto requestDto) {

        ProductEntity productEntity;

        List<ProductEntity> productEntities = productsRepository.findProductRate(requestDto);

        if (productEntities.isEmpty()) {
            throw new NotFoundException(Constants.NOT_FOUND_MSG);
        } else {
            //if is more than one object, check product priorities
            if (productEntities.size() > 1) {
                productEntity = getProductWithMaxPriority(productEntities);
            } else {
                productEntity = productEntities.get(0);
            }
        }

        //mapear a price response
        PriceResponseDTO response = mapToPriceResponse(productEntity);

        return response;
    }

    /**
     * Select product with maximum priority
     * @param productEntities the product entity
     * @return the product with max priority
     */
    private ProductEntity getProductWithMaxPriority(List<ProductEntity> productEntities) {
        ProductEntity productEntity;
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

        return productEntity;
    }

    /**
     * Mapeos de product entity a price response
     * @param productEntity the entity
     * @return  the object mapped
     */
    private PriceResponseDTO mapToPriceResponse(ProductEntity productEntity) {
        PriceResponseDTO priceResponseDTO = new PriceResponseDTO();

        priceResponseDTO.setBrandId(productEntity.getBrandId());
        priceResponseDTO.setProductId(productEntity.getProductId());
        priceResponseDTO.setRate(productEntity.getRate());
        priceResponseDTO.setStartDate(productEntity.getStartDate().toString());
        priceResponseDTO.setEndDate(productEntity.getEndDate().toString());
        priceResponseDTO.setPvp(productEntity.getPvp());

        return priceResponseDTO;
    }

}
