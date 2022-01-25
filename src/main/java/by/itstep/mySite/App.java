package by.itstep.mySite;




import by.itstep.mySite.control.net.ControlNet;
import by.itstep.mySite.dao.model.Client;
import by.itstep.mySite.utilits.CalcOptions;

import java.io.File;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

public class App {

    public static String appPath;
    public static void main(String[] args) {

        appPath = System.getProperty("user.dir");

        System.out.println("Папка программы: "+
                appPath
                );

        String[] arg = {"8091","---"};

        //запуск управления с консоли
        //ControlConsole.main(arg);

        CalcOptions opt = CalcOptions.getOptions();




        //Запуск WEb контроллера
        ControlNet.main(arg);
        System.out.println("Start");


    }
}