package com.vermont.inditex.pruebamario.service;

import com.vermont.inditex.pruebamario.model.PriceRequestDto;
import com.vermont.inditex.pruebamario.model.PriceResponseDTO;
import com.vermont.inditex.pruebamario.repository.ProductsRepository;
import com.vermont.inditex.pruebamario.repository.entities.ProductEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductsServiceTests {
    //@InjectMocks
    @Autowired
    ProductsService productsService;

    @Mock
    ProductsRepository productsRepository;

    private PriceResponseDTO responseDTO;

    private Integer productId = 35455;

    private Integer brandId = BigInteger.ONE.intValue();

    private PriceRequestDto request;

    @BeforeEach
    public void setUp(){
        request = new PriceRequestDto();
        request.setProductId(productId);
        request.setBrandId(brandId);

        responseDTO = new PriceResponseDTO();
        responseDTO.setBrandId(brandId);
        responseDTO.setProductId(productId);

        productsService = new ProductsService(productsRepository);
    }
    @Test
    public void productServiceTest1(){
        request.setApplicationDate("2020-12-14 10.00.00");

        Timestamp startDate = Timestamp.valueOf(LocalDateTime.of(2020, Month.APRIL, 14, 10, 0));
        Timestamp endDate = Timestamp.valueOf(LocalDateTime.of(2020, Month.APRIL, 14, 10, 0));

        ProductEntity productEntity = new ProductEntity();
        productEntity.setPriority(BigInteger.ZERO.intValueExact());

        ProductEntity productEntity2 = new ProductEntity();
        productEntity2.setProductId(productId);
        productEntity2.setPriority(BigInteger.ONE.intValueExact());
        productEntity2.setBrandId(brandId);
        productEntity2.setStartDate(startDate);
        productEntity2.setEndDate(endDate);

        responseDTO.setStartDate(startDate.toString());
        responseDTO.setEndDate(endDate.toString());

        when(productsRepository.findProductRate(any(PriceRequestDto.class)))
                .thenReturn(Arrays.asList(productEntity, productEntity2));

        PriceResponseDTO response = productsService.getProductData(request);

        Assertions.assertEquals(response.getProductId(), responseDTO.getProductId());
        Assertions.assertEquals(response, responseDTO);
        verify(productsRepository, times(1)).findProductRate(request);
    }
    @Test
    public void productServiceTest2(){
        request.setApplicationDate("2020-12-14 16.00.00");

        Timestamp startDate = Timestamp.valueOf(LocalDateTime.of(2020, Month.APRIL, 14, 10, 0));
        Timestamp endDate = Timestamp.valueOf(LocalDateTime.of(2020, Month.APRIL, 14, 10, 0));

        ProductEntity productEntity = new ProductEntity();
        productEntity.setPriority(BigInteger.ZERO.intValueExact());

        ProductEntity productEntity2 = new ProductEntity();
        productEntity2.setProductId(productId);
        productEntity2.setPriority(BigInteger.ONE.intValueExact());
        productEntity2.setBrandId(brandId);
        productEntity2.setStartDate(startDate);
        productEntity2.setEndDate(endDate);

        responseDTO.setStartDate(startDate.toString());
        responseDTO.setEndDate(endDate.toString());

        when(productsRepository.findProductRate(any(PriceRequestDto.class)))
                .thenReturn(Arrays.asList(productEntity, productEntity2));

        PriceResponseDTO response = productsService.getProductData(request);

        Assertions.assertEquals(response.getProductId(), responseDTO.getProductId());
        Assertions.assertEquals(response, responseDTO);
        verify(productsRepository, times(1)).findProductRate(request);
    }
    @Test
    public void productServiceTest3(){
        request.setApplicationDate("2020-12-14 21.00.00");

        Timestamp startDate = Timestamp.valueOf(LocalDateTime.of(2020, Month.APRIL, 14, 10, 0));
        Timestamp endDate = Timestamp.valueOf(LocalDateTime.of(2020, Month.APRIL, 14, 10, 0));

        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductId(productId);
        productEntity.setPriority(BigInteger.ONE.intValueExact());
        productEntity.setBrandId(brandId);
        productEntity.setStartDate(startDate);
        productEntity.setEndDate(endDate);

        responseDTO.setStartDate(startDate.toString());
        responseDTO.setEndDate(endDate.toString());

        when(productsRepository.findProductRate(any(PriceRequestDto.class)))
                .thenReturn(Arrays.asList(productEntity));

        PriceResponseDTO response = productsService.getProductData(request);

        Assertions.assertEquals(response.getProductId(), responseDTO.getProductId());
        Assertions.assertEquals(response, responseDTO);
        verify(productsRepository, times(1)).findProductRate(request);
    }
    @Test
    public void productServiceTest4(){
        request.setApplicationDate("2020-12-15 10.00.00");

        Timestamp startDate = Timestamp.valueOf(LocalDateTime.of(2020, Month.APRIL, 14, 10, 0));
        Timestamp endDate = Timestamp.valueOf(LocalDateTime.of(2020, Month.APRIL, 14, 10, 0));

        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductId(productId);
        productEntity.setPriority(BigInteger.ONE.intValueExact());
        productEntity.setBrandId(brandId);
        productEntity.setStartDate(startDate);
        productEntity.setEndDate(endDate);

        responseDTO.setStartDate(startDate.toString());
        responseDTO.setEndDate(endDate.toString());

        when(productsRepository.findProductRate(any(PriceRequestDto.class)))
                .thenReturn(Arrays.asList(productEntity));

        PriceResponseDTO response = productsService.getProductData(request);

        Assertions.assertEquals(response.getProductId(), responseDTO.getProductId());
        Assertions.assertEquals(response, responseDTO);
        verify(productsRepository, times(1)).findProductRate(request);
    }
    @Test
    public void productServiceTest5(){
        request.setApplicationDate("2020-12-16 21.00.00");

        Timestamp startDate = Timestamp.valueOf(LocalDateTime.of(2020, Month.APRIL, 14, 10, 0));
        Timestamp endDate = Timestamp.valueOf(LocalDateTime.of(2020, Month.APRIL, 14, 10, 0));

        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductId(productId);
        productEntity.setPriority(BigInteger.ONE.intValueExact());
        productEntity.setBrandId(brandId);
        productEntity.setStartDate(startDate);
        productEntity.setEndDate(endDate);

        responseDTO.setStartDate(startDate.toString());
        responseDTO.setEndDate(endDate.toString());

        when(productsRepository.findProductRate(any(PriceRequestDto.class)))
                .thenReturn(Arrays.asList(productEntity));

        PriceResponseDTO response = productsService.getProductData(request);

        Assertions.assertEquals(response.getProductId(), responseDTO.getProductId());
        Assertions.assertEquals(response, responseDTO);
        verify(productsRepository, times(1)).findProductRate(request);
    }
}
