package com.vermont.inditex.pruebamario.repository;

import com.vermont.inditex.pruebamario.model.PriceRequestDto;
import com.vermont.inditex.pruebamario.repository.entities.ProductEntity;
import com.vermont.inditex.pruebamario.repository.entities.ProductPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<ProductEntity, ProductPK> {

    @Query("select p from ProductEntity p where p.productId = :#{#request.productId} and " +
            "p.brandId = :#{#request.brandId} and " +
            "(p.startDate = :#{#request.applicationDate} or p.endDate = :#{#request.applicationDate})")
    List<ProductEntity> findProductRate(@Param("request") PriceRequestDto requestDto);
   /*@Query(value = "select * from PRICES where PRODUCT_ID = :#{#request.productId} and " +
           "BRAND_ID = :#{#request.brandId} and " +
           "(START_DATE = :#{#request.applicationDate} or END_DATE = :#{#request.applicationDate})",
           nativeQuery = true)
    ProductEntity findProductRate(@Param("request") PriceRequestDto requestDto);*/
   /*@Query(value = "select * from PRICES where PRODUCT_ID = :productId and " +
           "BRAND_ID = :brandId and " +
           "(START_DATE = :date or END_DATE = :date)",
           nativeQuery = true)
   ProductEntity findProductRate(@Param("productId") Integer productId, @Param("brandId") Integer brandId, @Param("date") String date);*/
//    ProductEntity findByStartDateAndProductIdAndBrandId(String date, Integer productId, Integer brandId);
}
