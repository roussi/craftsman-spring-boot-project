package com.aroussi.product.svc;

import com.aroussi.product.svc.model.Product;
import com.aroussi.product.svc.repository.ProductRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@Transactional
public class ProductApplicationTests {

    static PostgreSQLContainer pgContainer;

    @BeforeAll
    public static void beforeAll(){
        pgContainer = new PostgreSQLContainer()
                .withDatabaseName("panache-db-test")
                .withUsername("aroussi")
                .withPassword("123aze");
        pgContainer.start();
    }

    @AfterAll
    public static void afterAll() {
        pgContainer.stop();
    }

    @DynamicPropertySource
    public static void registerDynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", pgContainer::getJdbcUrl);
        registry.add("spring.datasource.username",pgContainer::getUsername);
        registry.add("spring.datasource.password",pgContainer::getPassword);
    }

    @Autowired
    ProductRepository productRepository;

    @Test
    void testSaveFailed() {
        Product ref123 = Product.builder().productRef("Ref123").build();
        Assertions.assertThrows(ConstraintViolationException.class, () -> productRepository.save(ref123));
    }

    @Test
    void testSaveSuccess() {
        Assert.assertNotNull(productRepository);
        Product productABC = Product.builder().productRef("Ref123").productLabel("abc").build();
        productRepository.save(productABC);
        assertEquals(productRepository.findAll().size(),1);
    }

    @Test
    void testFindProductStartingWith() {
        Product productABC = Product.builder().productRef("Ref123").productLabel("abc").build();
        productRepository.save(productABC);

        //wanted to test StepVerifier
        Flux<Product> productFlux = Flux.fromIterable(productRepository.findByProductRefStartingWith("Ref1"));

        Assert.assertNotEquals(productFlux, Flux.empty());
        StepVerifier.create(productFlux)
                .expectNext(productABC)
                .verifyComplete();
    }

    @Test
    void testFindProductStartingWithForSimilarRef() {
        Product productABC = Product.builder().productRef("Ref123").productLabel("abc").build();
        Product productDEF = Product.builder().productRef("Ref145").productLabel("def").build();

        productRepository.save(productABC);
        productRepository.save(productDEF);

        List<Product> products = productRepository.findByProductRefStartingWith("Ref1");
        Assert.assertEquals(products.size(),2);
    }
}