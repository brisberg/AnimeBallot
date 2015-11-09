package com.incra.services.exception;

/**
 */
public class ImageException extends ServiceException {
    public ImageException(String msg) {
        super(msg);
    }

    public ImageException(Exception e) {
        super(e);
    }
}
