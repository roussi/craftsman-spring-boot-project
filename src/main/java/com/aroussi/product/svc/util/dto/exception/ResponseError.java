package com.aroussi.product.svc.util.dto.exception;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author aroussi
 * @version %I% %G%
 */
@Builder
@Data
public class ResponseError implements Serializable {
    private int status;
    private String code;
    private String message;
}
