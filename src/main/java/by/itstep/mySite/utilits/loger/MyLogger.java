package by.itstep.mySite.utilits.loger;


import by.itstep.mySite.utilits.CalcOptions;

import java.io.File;
import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MyLogger {



    private String folderPath;    //path to folder "log"
    private String nowFolderPath; //path to folder "log/date"
    private long dayNow=-1L;//date in stringToInt format(YYYYMMDD), value inposible that necesalary reCalculate in first time

    private long dayNowTemp;


    private MyLoggerState levelShow = MyLoggerState.ALL;
    private MyLoggerState levelWrite= MyLoggerState.ALL;;

    /**
     * This method construction record for write to display or to file
     */
    public void log(MyLoggerState logState,
                    String message ){

        String recordString = "["+logState+"]\t"+
                              LocalDate.now()+" "+
                              LocalTime.now()+"\t"+
                              message;

        if (logState.ordinal()<=levelShow.ordinal()) System.out.println(recordString);

        if (logState.ordinal()<=levelWrite.ordinal()) System.out.println(recordString);

        }//log



    public void writeRecord(String record){




        }//writeRecord




    /**
     * This method reDefined datefolder
      */
    private void dateFolderChange(){

        //defined current date in long representation "YYYYMMDD"
        dayNowTemp = LocalDate.now().getYear()*10000+
                     LocalDate.now().getMonthValue()*100+
                     LocalDate.now().getDayOfMonth();

        System.out.println("dayNowTemp:"+dayNowTemp);

        //if date is change then reDefined path to logFolder
        if (dayNowTemp!=dayNow) {
                dayNow = dayNowTemp;
                nowFolderPath=folderPath +
                              LocalDate.now().getYear()+"-"+LocalDate.now().getMonthValue()+"-"+LocalDate.now().getDayOfMonth();//+
                              //File.separator;

                System.out.println(nowFolderPath);

                //search folder on the disc
                new File(nowFolderPath).mkdir();

                System.out.println("ok");



                }//if day is change


        }//folderChange



    //system methods========================================================
    private static MyLogger singleLogger;

    //private constructor
    private MyLogger() {
        //defined folder for logs;
        folderPath = CalcOptions.getOptions().getApplicationPath() +
                File.separator +
                "log" +
                File.separator;
        } //Constructor




    public static MyLogger getLogger(){
        if (singleLogger==null) singleLogger = new MyLogger();

        //defined folder by date
        singleLogger.dateFolderChange();

        return singleLogger;
        }

}//class loger

