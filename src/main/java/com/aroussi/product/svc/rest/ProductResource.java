package com.aroussi.product.svc.rest;

import com.aroussi.product.svc.model.Product;
import com.aroussi.product.svc.util.exception.ExceptionUtils;
import com.aroussi.product.svc.util.exception.ProductNotFoundException;
import com.aroussi.product.svc.repository.ProductRepository;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Optional;

/**
 * This class provide product endpoints
 *
 * <p>
 *     <h5>Note that : </h5>
 *     Even if we return Reactive Publishers, the underlying core business is blocking
 *    Since we are using postgres db.
 * </p>
 *
 * @author aroussi
 * @version %I% %G%
 */
@RequestMapping(ParentResource.API_VERSION + "/products")
@RestController
public class ProductResource extends ParentResource {

    private final ProductRepository productRepository;

    public ProductResource(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Product>> findProductByRef(@PathVariable Long id) {
        Optional<Product> productById = productRepository.findById(id);
        return productById.map(product -> ResponseEntity.ok(Mono.just(productById.get())))
                .orElseThrow(()-> {
                    Pair inputId = Pair.of("id", id);
                    String errorMessage = ExceptionUtils.getMessageFromEntry(
                            "Product with %s=%s not found", inputId
                    );
                    throw new ProductNotFoundException(errorMessage);
                });
    }

    @GetMapping("/ref/{product-ref}")
    public ResponseEntity<Flux<Product>> findProductByRef(@PathVariable("product-ref") String productRef) {
        Assert.notNull(productRef, "productRef should be provided");
        Flux<Product> refStartingWith = Flux.fromIterable(productRepository.findByProductRefStartingWith(productRef));
        return ResponseEntity.ok(refStartingWith);
    }

    @GetMapping
    public Flux<Product> findAllProducts() {
        return Flux.fromIterable(productRepository.findAll());
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody @Validated Product newProduct) {
        Product product = productRepository.save(newProduct);
        return Optional.ofNullable(product)
                .map(p-> ResponseEntity.created(URI.create("/v1/products/" + product.getProductRef())).build())
                .orElse(ResponseEntity.status(HttpStatus.ACCEPTED).build());
    }
}


