public class User extends BaseModel {

    private String employeeID;
    private String nickName;
    private String userID;
    private String userName;
    private String token;
    private List<String> whList;
    private String appID;

    private String displayName;
    private String wh;
    private String iconText;

    private Warehouse warehouse;

    public User(){}

    public User(String employeeID, String nickName, String userID, String userName, String token, List<String> whList, String appID, String wh,Warehouse warehouse) {
        this.employeeID = employeeID;
        this.nickName = nickName;
        this.userID = userID;
        this.userName = userName;
        this.token = token;
        this.whList = whList;
        this.appID = appID;
        this.wh = wh;
        this.warehouse = warehouse;
    }

    public String getUserId() {
        return userID;
    }

    public String getDisplayName() {
        return nickName;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getNickName() {
        return nickName;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
        setDisplayName(nickName);
        setIconText(nickName);
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getWhList() {
        return whList;
    }

    public void setWhList(List<String> whList) {
        this.whList = whList;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getWh() {
        return wh;
    }

    public void setWh(String wh) {
        this.wh = "当前位于" + wh;
    }

    public void setDisplayName(String displayName) {
        this.displayName = "Hi, " + displayName;
    }


    public String getIconText() {
        return iconText;
    }

    public void setIconText(String iconText) {
        if (iconText.length() != 0) {
            this.iconText = String.valueOf(iconText.toUpperCase().charAt(0));
        } else {
            this.iconText = iconText;
        }
    }

    public User getUser(){
        User user = new User(employeeID, nickName, userID, userName, token, whList, appID, wh,warehouse);
        user.setDisplayName(nickName);
        user.setIconText(nickName);
        return user;
    }
}
