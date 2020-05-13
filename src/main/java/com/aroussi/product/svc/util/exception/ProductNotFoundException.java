package com.aroussi.product.svc.util.exception;

/**
 * @author aroussi
 * @version %I% %G%
 */
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
