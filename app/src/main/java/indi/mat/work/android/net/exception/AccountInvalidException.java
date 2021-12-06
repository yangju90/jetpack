package indi.mat.work.android.net.exception;

import indi.mat.work.android.net.base.BaseException;
import indi.mat.work.android.net.config.ApiCode;

public class AccountInvalidException extends BaseException {
    public AccountInvalidException() {
        super(ApiCode.CODE_ACCOUNT_INVALID);
    }
}
