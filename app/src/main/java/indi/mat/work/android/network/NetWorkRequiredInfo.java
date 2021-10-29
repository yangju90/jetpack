package indi.mat.work.android.network;

import indi.mat.work.android.BuildConfig;

public class NetWorkRequiredInfo implements INetworkRequiredInfo{
    @Override
    public String getAppVersionName() {
        return null;
    }

    @Override
    public String getAppVersionCode() {
        return null;
    }

    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}
