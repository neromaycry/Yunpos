package com.joeykwok.yunposv1.common;

/**
 * Created by gjwlg on 2017/3/24.
 */

public class rqBean {
    /**
     * poskey : 001
     * sign :
     * timestamp : 2017-03-24 13:31:58
     * token : eyJhbGciOiJIUzI1NiIsImV4cCI6MTQ5MDI3NTQyNCwiaWF0IjoxNDkwMjUxMDI0fQ.eyJpZCI6Nn0.P7_Tvnlos7OmAOnAub7xlNCjTZ9a4LNecnoKzsBX99U
     */

    private String poskey;
    private String sign;
    private String timestamp;
    private String token;

    public String getPoskey() {
        return poskey;
    }

    public void setPoskey(String poskey) {
        this.poskey = poskey;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
