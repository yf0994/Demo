package com.example.fengyin.plugin_framework;

/**
 * Created by fengyin on 16-4-20.
 */
public class Result {
    private int rt;
    private String sid;

    public Result() {
    }

    public Result(int rt, String sid) {
        this.rt = rt;
        this.sid = sid;
    }

    public int getRt() {
        return rt;
    }

    public void setRt(int rt) {
        this.rt = rt;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "Result{" +
                "rt=" + rt +
                ", sid='" + sid + '\'' +
                '}';
    }
}
