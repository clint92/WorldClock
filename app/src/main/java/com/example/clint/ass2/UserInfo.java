package com.example.clint.ass2;

import java.io.Serializable;

/**
 * Created by clint on 15-09-2017.
 */

// Implements serializable so that objects of this class can be written to a file with the ObjectOutputStream.
public class UserInfo implements Serializable {

    //Contains login information
    private String userName;
    private String password;
    private int hashPass;
    private boolean remember;
    private boolean lock;
    private static final int SerID = 000000123;


    //___________SETTERS
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public void setHashPass(int hashPass) {
        this.hashPass = hashPass;
    }



    //____________GETTERS
    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean isRemember() {
        return remember;
    }

    public boolean isLock() {
        return lock;
    }

    public int getHashPass() {
        return hashPass;
    }
}
