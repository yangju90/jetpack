package indi.mat.work.behavior_collect;

public class CommonClick {
  private static CommonClick instance;
    private int[]touchDownLocation = new int[2];
    private HashMap<Integer, String> ids;
    private EventPoint eventPoint =  new EventPoint();


    private CommonClick(HashMap<Integer, String> ids) {
        this.ids = ids;
    }

    public static CommonClick getInstance(HashMap<Integer, String> ids){
        if (instance==null){
            instance = new CommonClick(ids);
        }
        return instance;
    }

    public void clickCollect(MotionEvent ev, View rootView){
        if (ev.getAction()==MotionEvent.ACTION_DOWN){
            touchDownLocation[0]=(int) ev.getRawX();
            touchDownLocation[1] =(int) ev.getRawY();
        }
        if (ev.getAction() == MotionEvent.ACTION_UP){
            if (BehaviorUtil.isIsCollectClickEvent() && checkLocation(ev)){
                dealAutoCollect(ev,rootView);
            }
        }
    }

    private  boolean checkLocation(MotionEvent ev){
        int clickX = (int) ev.getRawX();
        int clickY = (int) ev.getRawY();
        if (touchDownLocation[0]==clickX && touchDownLocation[1]==clickY)
            return true;
        return false;
    }

    private void dealAutoCollect(MotionEvent ev,View rootView) {
        View clickView = searchClickView(rootView,ev);
        if (clickView ==null){
            return;
        }
        if (ids.get(clickView.getId())!=null){
            eventPoint.userName = BehaviorSDK.getInstance().getUserName();
            eventPoint.message = ids.get(clickView.getId());
            eventPoint.type = Constants.EVENT_TYPE_CLICK;
            eventPoint.deviceInfo = BehaviorSDK.getInstance().getDeviceInfo();
            eventPoint.warehouse = BehaviorSDK.getInstance().getWarehouse();
            eventPoint.time = BehaviorSDK.getInstance().sdf.format(new Date());
            BehaviorUtil.clickEvent(eventPoint);
        }
    }


     public  View searchClickView(View currentView, MotionEvent event){
        View clickView = null;
        View view = currentView;
        if (isInView(view, event)){
            if (view instanceof ViewGroup){
                ViewGroup group = (ViewGroup) view;
                int childCount = group.getChildCount();
                if (childCount == 0) {
                    return currentView;
                }

                for (int i = childCount - 1; i >= 0; i--) {
                    currentView = group.getChildAt(i);
                    clickView = searchClickView(currentView, event);
                    if (clickView != null) {
                        return clickView;
                    }
                }
            }else {
                clickView =currentView;
            }

        }

        return clickView;
    }

    private  boolean isInView(View view, MotionEvent event) {
        if (view == null || view.getVisibility() != View.VISIBLE) {
            return false;
        }
        int clickX = (int) event.getRawX();
        int clickY = (int) event.getRawY();
        //如下的view表示Activity中的子View或者控件
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        int width = view.getWidth();
        int height = view.getHeight();
        //返回true，则判断这个view被点击了
        return clickX > x && clickX < (x + width) && clickY > y && clickY < (y + height);
    }


  
}
