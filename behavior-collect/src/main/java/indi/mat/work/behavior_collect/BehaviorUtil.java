package indi.mat.work.behavior_collect;

public class BehaviorUtil {
  private static boolean isCollectClickEvent = false;
    private static boolean isCollectLifeEvent = false;
    private static DbUtil dbUtil;
    private static ExecutorService pool = Executors.newSingleThreadExecutor();

    static void clickEvent(EventPoint eventPoint) {
        if (dbUtil != null){
           // dbUtil.excuteSql(dbUtil.getDatabase().eventPointDao(),Constants.EVENTDAO_INSERT_EVENT,Constants.EVENT_TYPE_CLICK,msg);
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    dbUtil.getDatabase().eventPointDao().insert(eventPoint);
                }
            });

        }
    }
    static void lifecycleEvent(EventPoint eventPoint){
        pool.submit(new Runnable() {
            @Override
            public void run() {
                dbUtil.getDatabase().eventPointDao().insert(eventPoint);
            }
        });
    }
     static void checkPush(String userName){
        pool.submit(new Runnable() {
            @Override
            public void run() {
                long currentTime = System.currentTimeMillis();
                boolean timeFlag = currentTime- SPUtils.get(Constants.LAST_PUSH_TIME,0L) >(24*60*60*1000);
                timeFlag = true;
                if (timeFlag){
                    pushEvent(userName);
                    return;
                }
                int count =  dbUtil.getDatabase().eventPointDao().getCount();
                if (count > 999){
                    pushEvent(userName);
                    return;
                }
            }
        });

    }
     private static void pushEvent(String userName){
        int count = dbUtil.getDatabase().eventPointDao().getCount();
        int loopTimes = count%1000 == 0 ? count/1000 : count/1000+1; //999-->1  1000-->1 1001-->2
        for (int i = 0; i < loopTimes; i++) {
            List<EventPoint> eventPointList = dbUtil.getDatabase().eventPointDao().getpageByIndex(1000,i*1000);
            //调用rest
            UploadLogService uploadLogService = BehaviorSDK.getInstance().getUploadLogService();
            int times = 0;
            while (uploadLogService ==null && times < 5){
                try {
                    Thread.sleep(3000);
                    times++;
                    uploadLogService = BehaviorSDK.getInstance().getUploadLogService();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
            if (uploadLogService != null){
                uploadLogService.upLoad(eventPointList);
            }

        }
        SPUtils.put(Constants.LAST_PUSH_TIME,System.currentTimeMillis());
    }
    static void clearDB(){
        pool.submit(new Runnable() {
            @Override
            public void run() {
                //清表
                dbUtil.getDatabase().eventPointDao().clear();
                //重置主键id序列
                dbUtil.getDatabase().eventPointDao().resetSequence();
            }
        });

    }

     static boolean isIsCollectClickEvent() {
        return isCollectClickEvent;
    }

     static void setIsCollectClickEvent(boolean isCollectClickEvent) {
        BehaviorUtil.isCollectClickEvent = isCollectClickEvent;
    }

     static boolean isIsCollectLifeEvent() {
        return isCollectLifeEvent;
    }

     static void setIsCollectLifeEvent(boolean isCollectLifeEvent) {
        BehaviorUtil.isCollectLifeEvent = isCollectLifeEvent;
    }

    public static void setDbUtil(DbUtil dbUtil) {
        BehaviorUtil.dbUtil = dbUtil;
    }
}
