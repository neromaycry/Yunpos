package com.joeykwok.yunposv1.requestbean;

import com.joeykwok.yunposv1.common.rqBean;

/**
 * Created by gjwlg on 2017/3/24.
 */

public class rqUserBean extends rqBean {
    /**
     * userId : 11001
     * userPassword : c4ca4238a0b923820dcc509a6f75849b
     * accreditType : 00
     */

    private String userId;
    private String userPassword;
    private String accreditType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getAccreditType() {
        return accreditType;
    }

    public void setAccreditType(String accreditType) {
        this.accreditType = accreditType;
    }

    @Override
    public String toString() {
        return "rqUserBean{" +
                "userId='" + userId + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", accreditType='" + accreditType + '\'' +
                '}';
    }
}
