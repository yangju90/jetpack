package indi.mat.work.android.net.config;

public enum ApiCode {
    CODE_DEMO(10000, "示例"),
    CODE_SUCCESS(0, "成功"),
    CODE_ACCOUNT_INVALID(1, "账号或密码错误"),
    CODE_DEMO_50000(50000, "系统错误"),
    CODE_DEMO_50001(50001, "网络错误"),

    CODE_DEMO_12001(12001,"非法Token"),
    CODE_DEMO_12002(12002,"当前Token还未过期"),
    CODE_DEMO_12003(12003,"当前刷新Token已经过期"),

    DATA_CONVERT_ERROR(51001, "数据转换出错"),
    REFRESH_TOKEN_ERROR(50050, "刷新密钥时异常出错"),
    UNKNOWN_ERROR(99999, "未知错误");



    private final int code;

    private final String reasonPhrase;


    ApiCode(int code, String reasonPhrase) {
        this.code = code;
        this.reasonPhrase = reasonPhrase;
    }

    public int getCode(){
        return this.code;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

}
