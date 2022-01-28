package by.itstep.mySite;

/**
 * This is start file to myCalc project
 */


import by.itstep.mySite.control.net.ControlNet;
import by.itstep.mySite.utilits.loger.LogState;
import by.itstep.mySite.utilits.loger.MyLogger;

public class App {

    public static String appPath;


    public static void main(String[] args) {

        appPath = System.getProperty("user.dir");

        System.out.println("Папка программы: "+
                appPath
                );


        //set levels for registry events
        MyLogger.getLogger().setFileLevel(LogState.ALL);
        MyLogger.getLogger().setShowLevel(LogState.ALL);

        MyLogger.getLogger().log(LogState.ALL,  "testMessage");

        String[] arg = {"8091","---"};

        //запуск управления с консоли
        //ControlConsole.main(arg);

        //CalcOptions opt = CalcOptions.getOptions();


        //Запуск WEb контроллера
        ControlNet.main(arg);
        System.out.println("Start");


    }
}