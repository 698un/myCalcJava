package by.itstep.mySite.service;



import by.itstep.mySite.dao.model.PixelLine;
import by.itstep.mySite.dao.repository.ImageRepository;
import by.itstep.mySite.dao.repository.VideoRepository;
import by.itstep.mySite.utilits.CalcOptions;
import by.itstep.mySite.utilits.loger.LogState;
import by.itstep.mySite.utilits.loger.MyLogger;

public class SystemService {


    public int getImageWidth(){
        return ImageRepository.getRepository().getImageWidth();
        }//get width of image

    public int getImageHeight(){
        return ImageRepository.getRepository().getImageHeight();
        }//get height of image

    public int getImageAntialiasing(){
        return ImageRepository.getRepository().getImageAntialiasing();
        }//get antia of image

    public void reset()throws Exception{
        try{
        CalcOptions.setNull();
        ImageRepository.setNull();
        VideoRepository.setNull();

        MyLogger.getLogger().log(LogState.INFO,"reset calculation");

        } catch (Exception e) {
            MyLogger.getLogger().log(LogState.WARN,"reset error "+e.getMessage());
            throw new Exception(e.getMessage());
            }

       }//reset







    //===========================SYSTEM====METHODS===========================================
    private static SystemService singleService;//возвраащемый одиночный экземпляр

    public static SystemService getService(){
        if (singleService==null) singleService=new SystemService();
        return singleService;
    }//getService

    //приватный конструктор
    private SystemService(){
        //imageRep = ImageRepository.getRepository();
        }//приватный конструктор



}//class TaskService
