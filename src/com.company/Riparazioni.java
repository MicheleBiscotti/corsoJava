package com.company;

public class Riparazioni {

    public String address;
    public int priority;
    public StatoRiparazione state;
    public int numrip;

    public int getNumrip() {
        return numrip;
    }

    public void setNumrip(int numrip) {
        this.numrip = numrip;
    }

    public Riparazioni() {
        this.address = "";
        this.priority = 1;
        this.state = StatoRiparazione.wait;
        this.numrip= 0;
    }

    public Riparazioni(String address, int numrip) {
        this.address = address;
        this.priority = 1;
        this.state = StatoRiparazione.wait;
        this.numrip=numrip;
    }

    public Riparazioni(String address, int priority, StatoRiparazione state, int numrip) {
        this.address = address;
        this.priority = priority;
        this.state = state;
        this.numrip=numrip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public StatoRiparazione getState() {
        return state;
    }

    public void setState(StatoRiparazione state) {
        this.state = state;
    }
}

enum StatoRiparazione {
    wait, inProgress, done;
}


