package indi.mat.work.android.model.bean;

public class VersionInfo {

    public String getVersionMsg() {
        return versionMsg;
    }

    public void setVersionMsg(String versionMsg) {
        this.versionMsg = versionMsg;
    }

    public String getVersionLink() {
        return versionLink;
    }

    public void setVersionLink(String versionLink) {
        this.versionLink = versionLink;
    }

    private String versionMsg;
    private String versionLink;
    private Boolean status;


    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
