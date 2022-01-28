package by.itstep.mySite;

/**
 * This is start file to myCalc project
 */


import by.itstep.mySite.control.net.ControlNet;
import by.itstep.mySite.dao.model.Client;
import by.itstep.mySite.utilits.CalcOptions;
import by.itstep.mySite.utilits.loger.MyLogger;
import by.itstep.mySite.utilits.loger.MyLoggerState;

import java.io.File;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class App {

    public static String appPath;


    public static void main(String[] args) {

        appPath = System.getProperty("user.dir");

        System.out.println("Папка программы: "+
                appPath
                );

        //Date date = new Date();

        MyLogger.getLogger().setFileLevel(MyLoggerState.ALL);
        MyLogger.getLogger().setShowLevel(MyLoggerState.ALL);

        MyLogger.getLogger().log(MyLoggerState.ALL,  "testMessage");

        String[] arg = {"8091","---"};

        //запуск управления с консоли
        //ControlConsole.main(arg);

        //CalcOptions opt = CalcOptions.getOptions();


        //Запуск WEb контроллера
        ControlNet.main(arg);
        System.out.println("Start");


    }
}