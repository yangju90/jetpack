package indi.mat.work.behavior_collect;

public class BehaviorSDK {
  private static BehaviorSDK behaviorSDK;
    private BehaviorDatabase dataBase;
    private HashMap<String,String>  trackSettingsMap;
    private HashMap<Integer, String> ids;
    private String customPackageName;
    private Context customContext;
    private String fileName;
    private boolean isCollectClickEvent;
    private boolean isCollectLifeEvent;
    private DbUtil dbUtil;
    private UploadLogService uploadLogService;
    private String userName = "unknow";
    private String warehouse = "unknow";



    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static BehaviorSDK getInstance(){
        if (behaviorSDK==null){
          throw new UninitializedException("Not initialized by Initer");
        }
        return behaviorSDK;
    }
    private void init(){
        if (dataBase==null){
            dataBase = BehaviorDatabase.getInstance(customContext);
        }
        if (dbUtil==null){
            dbUtil = DbUtil.getInstance(customContext);
            BehaviorUtil.setDbUtil(dbUtil);
        }
        BehaviorUtil.setIsCollectClickEvent(isCollectClickEvent);
        BehaviorUtil.setIsCollectLifeEvent(isCollectLifeEvent);
        //从项目asset文件中读取json文件
        trackSettingsMap = new Gson().fromJson(GetJsonUtil.getJson(customContext,fileName), HashMap.class);
        castMap(trackSettingsMap);
    }
    private BehaviorSDK(Initer initer){
        customContext = initer.context;
        customPackageName = initer.packageName;
        fileName = initer.fileName;
        isCollectClickEvent = initer.isCollectClickEvent;
        isCollectLifeEvent = initer.isCollectLifeEvent;
        init();
    }

    public String getDeviceInfo() {
        Map<String,String> infoMap = new HashMap<>();
        infoMap.put("Brand", Build.BRAND);
        infoMap.put("Manufacturer",Build.MANUFACTURER);
        infoMap.put("Device",Build.DEVICE);
        infoMap.put("Model",Build.MODEL);
        infoMap.put("Product",Build.PRODUCT);
        infoMap.put("Board",Build.BOARD);
        infoMap.put("Hardware",Build.HARDWARE);
        infoMap.put("Id",Build.ID);
        infoMap.put("Device_User",Build.USER);
        infoMap.put("Display_Country", Locale.getDefault().getDisplayCountry());
        infoMap.put("Display_Language", Locale.getDefault().getDisplayLanguage());
        return new Gson().toJson(infoMap);
    }

    public void collectCommonClickEvents(MotionEvent ev, View rootView){
        CommonClick.getInstance(ids).clickCollect(ev,rootView);
    }

    public void collectCommonLifeEvents(String msg){
        CommonLifecycle.getInstance().lifecycleCollect(msg);
    }

//    public LiveData<List<EventPoint>> getLiveEventList(){
//        return dbUtil.excuteListLiveData(dataBase.eventPointDao(),EventPoint.class,Constants.EVENTDAO_GET_ALL);
//    }
//    public List<EventPoint> getEventList(){
//        return (List<EventPoint>) dbUtil.excuteSql(dataBase.eventPointDao(),Constants.EVENTDAO_GET_ALL);
//    }

    public PagingSource<Integer,EventPoint> getEventPagingSource(){
        return dataBase.eventPointDao().getAllByPage();
    }

    public void checkPush(String userName){
        BehaviorUtil.checkPush(userName);
    }

    public void clearDB(){
        BehaviorUtil.clearDB();
    }

    public UploadLogService getUploadLogService() {
        return uploadLogService;
    }

    public void setUploadLogService(UploadLogService uploadLogService) {
        this.uploadLogService = uploadLogService;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (userName!=null && userName.trim()!=""){
            this.userName = userName;
        }
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        if (warehouse!=null && warehouse.trim()!=""){
            this.warehouse = warehouse;
        }
    }

    private void castMap(HashMap<String, String> trackSettingsMap) {
        ids = new HashMap<>(trackSettingsMap.size());
        for (Map.Entry<String,String> e :trackSettingsMap.entrySet()) {
            int id = customContext.getResources().getIdentifier(e.getKey(),"id",customPackageName);
            if (id !=0){
                ids.put(id,e.getKey()+": "+e.getValue());
            }
        }
    }

    public static class Initer{
        private Context context;
        private String packageName;
        private String fileName;
        private boolean isCollectClickEvent;
        private boolean isCollectLifeEvent;
        public Initer(Context context ,String packageName,String fileName){
            this.context = context;
            this.packageName = packageName;
            this.fileName = fileName;
        }

        public Initer isCollectClickEvent(boolean isCollectClickEvent){
            this.isCollectClickEvent = isCollectClickEvent;
            return this;
        }

        public Initer isCollectLifeEvent(boolean isCollectLifeEvent){
            this.isCollectLifeEvent = isCollectLifeEvent;
            return this;
        }
        public BehaviorSDK init(){
            if (behaviorSDK == null){
                behaviorSDK = new BehaviorSDK(this);
            }
            return behaviorSDK;
        }
    }
}
