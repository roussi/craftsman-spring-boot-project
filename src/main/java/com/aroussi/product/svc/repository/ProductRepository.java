package com.aroussi.product.svc.repository;

import com.aroussi.product.svc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Optional;

/**
 * @author aroussi
 * @version %I% %G%
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductRefStartingWith(String productRef);
}
