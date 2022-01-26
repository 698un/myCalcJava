package by.itstep.mySite.utilits.loger;


public class Loger {







    //system methods========================================================
    private static Loger singleLoger;

    //private constructor
    private Loger(){

        }

    public static Loger getLoger(){
        if (singleLoger==null) singleLoger = new Loger();
        return singleLoger;
        }

    }//class loger


    enum LogerState{

    WARN,INFO
    }