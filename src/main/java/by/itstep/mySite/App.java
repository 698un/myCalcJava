package by.itstep.mySite;

/**
 * This is start file to myCalc project
 */


import by.itstep.mySite.control.net.ControlNet;
import by.itstep.mySite.utilits.CalcOptions;
import by.itstep.mySite.utilits.MyUtil;
import by.itstep.mySite.utilits.loger.LogState;
import by.itstep.mySite.utilits.loger.MyLogger;

import java.io.File;

public class App {

    public static String appPath;


    public static void main(String[] args) {
        //this log_to_file
        appPath = System.getProperty("user.dir");

        MyUtil.startLog();

        System.out.println("Application folder: "+
                appPath
                );

        //folder_correct

        MyLogger.getLogger().log(LogState.INFO,  "Start service");

        String[] arg = {CalcOptions.getOptions().getStr("port"),"---"};

        //запуск управления с консоли
        //ControlConsole.main(arg);

        //CalcOptions opt = CalcOptions.getOptions();


        //Запуск WEb контроллера
        ControlNet.main(arg);
        System.out.println("Start");


    }
}