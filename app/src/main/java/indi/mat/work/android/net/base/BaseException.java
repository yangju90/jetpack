package indi.mat.work.android.net.base;

import indi.mat.work.android.net.config.ApiCode;

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
