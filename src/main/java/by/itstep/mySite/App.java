package by.itstep.mySite;

/**
 * This is start file to myCalc project
 */


import by.itstep.mySite.control.net.ControlNet;
import by.itstep.mySite.utilits.loger.LogState;
import by.itstep.mySite.utilits.loger.MyLogger;

import java.io.File;

public class App {

    public static String appPath;


    public static void main(String[] args) {
        //this log_to_file
        appPath = System.getProperty("user.dir");

        System.out.println("Папка программы: "+
                appPath
                );


        //set levels for registry events

        /**
        File testFile = new File("test.txt");
        try {
            testFile.createNewFile();
            } catch (Exception e) {e.printStackTrace();};

         */








        MyLogger.getLogger().log(LogState.INFO,  "Start service");

        String[] arg = {"8091","---"};

        //запуск управления с консоли
        //ControlConsole.main(arg);

        //CalcOptions opt = CalcOptions.getOptions();


        //Запуск WEb контроллера
        ControlNet.main(arg);
        System.out.println("Start");


    }
}