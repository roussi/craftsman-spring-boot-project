package com.aroussi.product.svc.util.exception;

import org.springframework.data.util.Pair;

/**
 * @author aroussi
 * @version %I% %G%
 */
public class ExceptionUtils {
    public static String getMessageFromEntry(String abstractMessage, Pair data) {
        return String.format(abstractMessage, data.getFirst(), data.getSecond());
    }
}
