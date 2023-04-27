package com.vermont.inditex.pruebamario.repository.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@IdClass(ProductPK.class)
@Entity
@Table(name = "PRICES")
@Data
public class ProductEntity {

    @Id
    @Column(name = "PRODUCT_ID")
    private Integer productId;

    @Id
    @Column(name = "BRAND_ID")
    private Integer brandId;

    @Id
    @Column(name = "PRICE_LIST")
    private Integer rate;

    @Column(name = "START_DATE")
    private String startDate;

    @Column(name = "END_DATE")
    private String endDate;

    @Column(name = "PRIORITY")
    private Integer priority;

    @Column(name = "PRICE")
    private Double pvp;

    @Column(name = "CURRENCY")
    private String currency;
}
