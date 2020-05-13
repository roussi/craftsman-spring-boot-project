package com.aroussi.product.svc.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * @author aroussi
 * @version %I% %G%
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"createdDate","modifiedDate"})
@MappedSuperclass
public class ParentModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_date")
    @CreatedDate
    private ZonedDateTime createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private ZonedDateTime modifiedDate;

}
