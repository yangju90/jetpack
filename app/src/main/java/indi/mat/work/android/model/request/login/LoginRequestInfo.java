package indi.mat.work.android.model.request.login;

public class LoginRequestInfo {
    // 登录账号
    private String employeeID;
    // 登录密码
    private String pin;
    // 机器名
    private String workStation;
    // Ip地址
    private String ipAddress;

    public LoginRequestInfo(){
    }

    public LoginRequestInfo(String employeeID, String pin, String workStation, String ipAddress) {
        this.employeeID = employeeID;
        this.pin = pin;
        this.workStation = workStation;
        this.ipAddress = ipAddress;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getWorkStation() {
        return workStation;
    }

    public void setWorkStation(String workStation) {
        this.workStation = workStation;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
