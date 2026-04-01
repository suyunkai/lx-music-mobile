package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SetObjectAclRequest extends GenericObjectRequest {
    private List<Grant> accessControlList;
    private CannedAccessControlList cannedAcl;
    private String jsonObjectAcl;
    private String xBceGrantFullControl;
    private String xBceGrantRead;

    public SetObjectAclRequest() {
    }

    public SetObjectAclRequest(String str, String str2, String str3) {
        super(str, str2);
        this.jsonObjectAcl = str3;
    }

    public SetObjectAclRequest(String str, String str2, List<Grant> list) {
        super(str, str2);
        this.accessControlList = list;
    }

    public SetObjectAclRequest(String str, String str2, CannedAccessControlList cannedAccessControlList) {
        super(str, str2);
        this.cannedAcl = cannedAccessControlList;
    }

    @Override // com.baidubce.model.AbstractBceRequest
    public SetObjectAclRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }

    @Override // com.baidubce.services.bos.model.GenericBucketRequest
    public SetObjectAclRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }

    @Override // com.baidubce.services.bos.model.GenericObjectRequest
    public SetObjectAclRequest withKey(String str) {
        setKey(str);
        return this;
    }

    public String getJsonObjectAcl() {
        return this.jsonObjectAcl;
    }

    public void setJsonObjectAcl(String str) {
        this.jsonObjectAcl = str;
    }

    public List<Grant> getAccessControlList() {
        return this.accessControlList;
    }

    public void setAccessControlList(List<Grant> list) {
        this.accessControlList = list;
    }

    public CannedAccessControlList getCannedAcl() {
        return this.cannedAcl;
    }

    public void setCannedAcl(CannedAccessControlList cannedAccessControlList) {
        this.cannedAcl = cannedAccessControlList;
    }

    public String getxBceGrantRead() {
        return this.xBceGrantRead;
    }

    public void setxBceGrantRead(String str) {
        this.xBceGrantRead = str;
    }

    public String getxBceGrantFullControl() {
        return this.xBceGrantFullControl;
    }

    public void setxBceGrantFullControl(String str) {
        this.xBceGrantFullControl = str;
    }
}
