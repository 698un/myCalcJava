/**
 * 2022-01-28  Correct filename of log file
 */
package by.itstep.mySite.utilits.loger;


import by.itstep.mySite.utilits.CalcOptions;
import by.itstep.mySite.utilits.MyLocker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MyLogger {



    private String folderPath;    //path to folder "log"
    private String nowFilePath; //path to folder "log/date"
    private long dayNow=-1L;//date in stringToInt format(YYYYMMDD), value inposible that necesalary reCalculate in first time

    private long dayNowTemp;


    private LogState levelShow = LogState.ALL;
    private LogState levelFile= LogState.ALL;;

    /**
     * method set minimal level for event that write on file
     * @param state
     */
    public void setFileLevel(LogState state){
        this.levelFile = state;
        }
    /**
     * method set minimal level for event that write on screen
     * @param state
     */

    public void setShowLevel(LogState state){
        this.levelShow = state;
        }




    /**
     * This method construction record for write to display or to file
     * and send this record on screen and logFile
     */
    public void log(LogState logState,
                    String message ){

        String recordString = "["+logState+"]\t"+
                              LocalDate.now()+" "+
                              LocalTime.now()+"\t"+
                              message;

        if (logState.ordinal()<=levelShow.ordinal()) System.out.println(recordString);
        if (logState.ordinal()<=levelFile.ordinal()) writeRecord(recordString);
        }//log



    public void writeRecord(String record){

        //synchronize access to log file
        synchronized (MyLocker.getLocker()) {

            try {
                FileWriter writer = new FileWriter(nowFilePath, true);
                BufferedWriter bufferWriter = new BufferedWriter(writer);
                bufferWriter.write(record);
                bufferWriter.write("\n");
                bufferWriter.close();
            } catch (Exception e) {
                   e.printStackTrace();
                    }

            }//synchronized

        }//writeRecord




    /**
     * This method reDefined dateFileName
      */
    private void fileNameChange(){

        //defined current date in long representation "YYYYMMDD"
        dayNowTemp = LocalDate.now().getYear()*10000+
                     LocalDate.now().getMonthValue()*100+
                     LocalDate.now().getDayOfMonth();

        System.out.println("dayNowTemp:"+dayNowTemp);

        //if date is change then reDefined path to logFile
        if (dayNowTemp!=dayNow) {


                dayNow = dayNowTemp;

                String dateString= String.valueOf(dayNow);

                //Create fileName for "YYYY-MM-DD.log"
                String fileName = dateString.substring(0,4)+
                                  "-"+
                                  dateString.substring(4,6)+
                                  "-"+
                                  dateString.substring(6)+
                                  ".log";

                nowFilePath=folderPath +fileName;


                //System.out.println(nowFilePath);

                //search folder on the disc
                //create new file of log id not exist this file
                File fileLog = new File(nowFilePath);
                if (!fileLog.exists()) {
                        try{fileLog.createNewFile();}catch (Exception e){e.printStackTrace();};
                        }


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

        //defined fileName by date
        singleLogger.fileNameChange();

        return singleLogger;
        }

}//class loger

