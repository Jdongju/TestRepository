package ch18.exam20;

import java.io.Serializable;

public class Member implements Serializable{

    //Field
    private String mname;
    private int age;

    //Constructor
    Member(String mname, int age){
        this.mname = mname;
        this.age = age;
    }
    //Method

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
