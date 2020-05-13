package com.aroussi.product.svc.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author aroussi
 * @version %I% %G%
 */
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "product")
public class Product extends ParentModel {

    @NotNull(message = "product ref is mandatory")
    @Size(min = 3)
    @Column(name= "product_ref")
    private String productRef;

    @NotNull
    @Column(name= "product_label")
    private String productLabel;

    @Builder
    public Product(String productRef, String productLabel) {
        this.productRef = productRef;
        this.productLabel = productLabel;
    }
}
