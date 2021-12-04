package com.newegg.logistics.net.exception;


import com.newegg.logistics.net.base.BaseException;
import com.newegg.logistics.net.config.ApiCode;

public class AccountInvalidException extends BaseException {
    public AccountInvalidException() {
        super(ApiCode.CODE_ACCOUNT_INVALID);
    }
}
