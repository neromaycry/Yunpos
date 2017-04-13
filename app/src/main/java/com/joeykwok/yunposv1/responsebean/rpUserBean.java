package com.joeykwok.yunposv1.responsebean;

/**
 * Created by gjwlg on 2017/3/24.
 */

public class rpUserBean {


    /**
     * contract_code : PPC20161200001
     * phone :
     * rate_manager : 0.0000
     * status : 00
     * token : eyJhbGciOiJIUzI1NiIsImV4cCI6MTQ5MDM2NDI5OSwiaWF0IjoxNDkwMzM5ODk5fQ.eyJpZCI6Nn0.gKsOLjPhJzzMy3IESDKL9u5RvAYx8447ZBIpNdoe_SQ
     * user_id : 11001
     * user_name : 刘亦菲
     * worker_position : 02
     */

    private String contract_code;
    private String phone;
    private String rate_manager;
    private String status;
    private String token;
    private String user_id;
    private String user_name;
    private String worker_position;
    private String msg;

    public String getContract_code() {
        return contract_code;
    }

    public void setContract_code(String contract_code) {
        this.contract_code = contract_code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRate_manager() {
        return rate_manager;
    }

    public void setRate_manager(String rate_manager) {
        this.rate_manager = rate_manager;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getWorker_position() {
        return worker_position;
    }

    public void setWorker_position(String worker_position) {
        this.worker_position = worker_position;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "rpUserBean{" +
                "contract_code='" + contract_code + '\'' +
                ", phone='" + phone + '\'' +
                ", rate_manager='" + rate_manager + '\'' +
                ", status='" + status + '\'' +
                ", token='" + token + '\'' +
                ", user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", worker_position='" + worker_position + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
