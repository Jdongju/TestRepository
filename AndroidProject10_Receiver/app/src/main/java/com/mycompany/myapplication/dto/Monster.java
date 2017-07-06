package com.mycompany.myapplication.dto;

public class Monster {
    private int mno;
    private String mname;
    private int mphoto;
    private int mstar;

    public int getMno() {
        return mno;
    }

    public void setMno(int mno) {
        this.mno = mno;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public int getMphoto() {
        return mphoto;
    }

    public void setMphoto(int mphoto) {
        this.mphoto = mphoto;
    }

    public int getMstar() {
        return mstar;
    }

    public void setMstar(int mstar) {
        this.mstar = mstar;
    }

    public String getMdesc() {
        return mdesc;
    }

    public void setMdesc(String mdesc) {
        this.mdesc = mdesc;
    }

    private String mdesc;

}
