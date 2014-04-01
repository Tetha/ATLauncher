package com.atlauncher.event;

public final class PacksEvent{
    public static final byte REFRESH = 0x1;
    public static final byte COLLAPSE = 0x2;
    public static final byte UNCOLLAPSE = 0x4;

    private final int type;

    public PacksEvent(int type){
        this.type = type;

        if(this.isCollapse() && this.isUncollapse()){
            throw new ArithmeticException("Cannot collapse and Uncollase");
        }
    }

    public boolean isRefresh(){
        return (this.type & REFRESH) == REFRESH;
    }

    public boolean isCollapse(){
        return (this.type & COLLAPSE) == COLLAPSE;
    }

    public boolean isUncollapse(){
        return (this.type & UNCOLLAPSE) == UNCOLLAPSE;
    }
}