package com.CodeNaroNa.vendor.relief.Deprecated.Model;

@Deprecated
public class UserData {
    String cat,name,ot,ct,num;

    public UserData(String cat, String name, String ot, String ct,String num) {
        this.cat = cat;
        this.name = name;
        this.ot = ot;
        this.ct = ct;
        this.num=num;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOt() {
        return ot;
    }

    public void setOt(String ot) {
        this.ot = ot;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }
}
