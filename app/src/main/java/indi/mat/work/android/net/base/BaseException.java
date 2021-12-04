package com.newegg.logistics.net.base;


import com.newegg.logistics.net.config.ApiCode;

public class BaseException extends RuntimeException {
    public BaseException() {
        super();
    }

    public BaseException(ApiCode apiCode) {
        super(apiCode.getReasonPhrase());
    }

    public BaseException(String message) {
        super(message);
    }
}
