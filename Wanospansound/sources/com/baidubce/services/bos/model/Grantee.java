package com.baidubce.services.bos.model;

/* JADX INFO: loaded from: classes.dex */
public class Grantee {
    private String id;

    public Grantee() {
    }

    public Grantee(String str) {
        setId(str);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public Grantee withId(String str) {
        setId(str);
        return this;
    }

    public int hashCode() {
        String str = this.id;
        return 31 + (str == null ? 0 : str.hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Grantee grantee = (Grantee) obj;
        String str = this.id;
        if (str == null) {
            if (grantee.id != null) {
                return false;
            }
        } else if (!str.equals(grantee.id)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "Grantee [id=" + this.id + "]";
    }
}
