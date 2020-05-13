package com.aroussi.product.svc.rest;

import com.aroussi.product.svc.util.dto.exception.ResponseError;
import com.aroussi.product.svc.util.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author aroussi
 * @version %I% %G%
 */
@ControllerAdvice
public abstract class ParentResource {
    protected static final String API_VERSION = "v1";

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseError handleNotFoundProduct(ProductNotFoundException exception) {
        return ResponseError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .code("PRODUCT-NOT-FOUND")
                .message(exception.getMessage())
                .build();
    }
}

