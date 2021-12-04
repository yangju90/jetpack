public class BaseActivity extends AppCompatActivity {

    protected NetStatusModel netStatusModel;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        netStatusModel = NetStatusModel.getInstance();

        //判断是否是斑马设备来开启EMDK
        if(Build.MANUFACTURER.contains("Zebra Technologies") || Build.MANUFACTURER.contains("Motorola Solutions")){
            EMDKAPIWrapper emdkapiWrapper = new EMDKAPIWrapper();
            emdkapiWrapper.getEMDKManager(getApplicationContext());
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        BehaviorSDK.getInstance().collectCommonClickEvents(ev,getWindow().getDecorView());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onStart() {
        setObserver();
        setListener();
        super.onStart();
    }


    protected void setObserver() {
        netStatusModel.getActionStatus().observe(this, s -> {
            switch (s){
                case APP_ACTION_STATUS_INIT: {
                    break;
                }
                case APP_ACTION_STATUS_PROCESS_START: {
                    startLoading();
                    break;
                }
                case APP_ACTION_STATUS_PROCESS_END: {
                    dismissLoading();
                    break;
                }

                case APP_ACTION_STATUS_LOGIN_OUT:{
                    logout();
                    break;
                }
            }
        });
    }


    protected void setListener(){
    }

    public void logout(){
    }

    protected void startLoading() {
        progressBar = createLoadingBar(this);
    }

    protected void dismissLoading() {
        if (progressBar != null && progressBar.getVisibility() == View.VISIBLE) {
            progressBar.setVisibility(View.GONE);
        }
    }

    public ProgressBar createLoadingBar(Context context) {
        ProgressBar progressBar = new ProgressBar(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.bottomMargin=175;

        addContentView(progressBar, layoutParams);
        return progressBar;
    }
}
