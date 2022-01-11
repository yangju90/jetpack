package indi.mat.work.android.utilities;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import indi.mat.work.android.model.bean.VersionInfo;
import indi.mat.work.android.widget.PromptDialog;
import indi.mat.work.android.widget.UpdatePromptDialog;

/**
 *
 * // 程序安装注意签名文件
 *
 */
public class UpdateManager {

    // 应用程序Context
    private Context mContext;
    // 是否是最新的应用,默认为false
    private boolean isNew = false;
    private boolean intercept = false;
    // 保存APK的文件夹
    private static final String saveFileName = "wms.apk";
    // 下载线程
    private Thread downLoadThread;
    private int progress;// 当前进度
    TextView text;
    private static final int DOWN_UPDATE = 1;
    private static final int DOWN_OVER = 2;
    private VersionInfo version = new VersionInfo();

    public UpdateManager(Context context) {
        mContext = context;
    }

    /**
     * 检查是否更新的内容
     */
    public void checkUpdateInfo(VersionInfo versionInfo) {
        version = versionInfo;
        //这里的isNew本来是要从服务器获取的，我在这里先假设他需要更新
        if (!version.getStatus()) {
            ToastHolder.showToast(versionInfo.getVersionMsg());
            return;
        } else {
            showUpdateDialog();
        }
    }

    /**
     * 显示更新程序对话框，供主程序调用
     */

    UpdatePromptDialog dialog;

    private void showUpdateDialog() {
        PromptDialog.PromptDialogBuilder builder = new PromptDialog.PromptDialogBuilder(mContext);
        dialog = (UpdatePromptDialog) builder.setTitle("UPDATES").setContent(version.getVersionMsg())
                .setRightButton("ok", (view, dialog1) -> {
                    showDownloadDialog();
                }).setLeftButton("cancel", (view, dialog12) -> {
                    dialog12.dismiss();
                }).hasProgress().setCancelable(false).create();

        dialog.show();
    }

    /**
     * 显示下载进度的对话框
     */
    private void showDownloadDialog() {
        dialog.showProgressBar();
        //强制更新 不设置 cancel。
        dialog.setCancelDownloadButton("cancel", (view, dialog) -> {
            intercept=true;
            dialog.dismiss();
        });
        downloadApk();
        postCheckSpeeds();
    }

    /**
     * 从服务器下载APK安装包
     */
    private void downloadApk() {
        downLoadThread = new Thread(mdownApkRunnable);
        downLoadThread.start();
    }

    double downloads;

    private Runnable mdownApkRunnable = new Runnable() {
        @Override
        public void run() {
            URL url;
            try {
                url = new URL(version.getVersionLink());
                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                conn.connect();
                int length = conn.getContentLength();
                InputStream ins = conn.getInputStream();
                File file = new File(mContext.getFilesDir().getPath().toString() + "/" + saveFileName);
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream fos = new FileOutputStream(file);
                int count = 0;
                byte[] buf = new byte[1024];
                while (!intercept) {
                    int numread = ins.read(buf);
                    count += numread;
                    downloads+=numread;

                    progress = (int) (((float) count / length) * 100);
                    // 下载进度
                    mHandler.sendEmptyMessage(DOWN_UPDATE);
                    if (numread <= 0) {
                        // 下载完成通知安装
                        mHandler.sendEmptyMessage(DOWN_OVER);
                        break;
                    }
                    fos.write(buf, 0, numread);
                }
                fos.close();
                ins.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 安装APK内容
     */
    private boolean installAPK() {
        File apkFile = new File(mContext.getFilesDir().getPath().toString() + "/" + saveFileName);
        if (!apkFile.exists()) {
            return false;
        }
        Uri uri = null;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //检查是否拥有安装未知应用的权限，如果没有则申请
        if (Build.VERSION.SDK_INT >= 26) {
            boolean hasInstallPermission = isHasInstallPermissionWithO(mContext);
            if (!hasInstallPermission) {
                startInstallPermissionSettingActivity(mContext);
            }
        }
        try {
            if (Build.VERSION.SDK_INT >= 24) {//7.0 Android N
                //com.xxx.xxx.fileprovider为上述manifest中provider所配置相同
                uri = FileProvider.getUriForFile(mContext, mContext.getPackageName() + ".fileprovider", new File(String.valueOf(apkFile)));
                intent.setAction(Intent.ACTION_INSTALL_PACKAGE);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//7.0以后，系统要求授予临时uri读取权限，安装完毕以后，系统会自动收回权限，该过程没有用户交互
            } else {//7.0以下
                uri = Uri.fromFile(new File(String.valueOf(apkFile)));
                intent.setAction(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            mContext.startActivity(intent);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mContext, "installation failed！", Toast.LENGTH_SHORT);
            return false;
        }
        return true;
    }

    ;


    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean isHasInstallPermissionWithO(Context context) {
        if (context == null) {
            return false;
        }
        return context.getPackageManager().canRequestPackageInstalls();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent();
        //获取当前apk包URI，并设置到intent中（这一步设置，可让“未知应用权限设置界面”只显示当前应用的设置项）
        Uri packageURI = Uri.parse("package:" + context.getPackageName());
        intent.setData(packageURI);
        //设置不同版本跳转未知应用的动作
        if (Build.VERSION.SDK_INT >= 26) {
            //intent = new Intent(android.provider.Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,packageURI);
            intent.setAction(android.provider.Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
        } else {
            intent.setAction(android.provider.Settings.ACTION_SECURITY_SETTINGS);
        }
        ((Activity) context).startActivity(intent);
        Toast.makeText(mContext, "Please open unknown app installation permissions", Toast.LENGTH_SHORT).show();
    }

    private Handler checkSpeedsH =new Handler();

    /**
     * 一秒判断一次下载量
     */
    private void postCheckSpeeds(){
        checkSpeedsH.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.setSpeedBytes(downloads);
                downloads=0;
                postCheckSpeeds();
            }
        },1000);
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {

                case DOWN_UPDATE:
                    dialog.setProgress(progress);
                    break;
                case DOWN_OVER:
                    dialog.dismiss();
                    installAPK();
                    break;

                default:
                    break;
            }
        }

    };
}
