package by.itstep.mySite.service;



import by.itstep.mySite.dao.model.PixelLine;
import by.itstep.mySite.dao.repository.ImageRepository2;
import by.itstep.mySite.dao.repository.VideoRepository;
import by.itstep.mySite.utilits.CalcOptions;

public class SystemService {


    public int getImageWidth(){
        return ImageRepository2.getRepository().getImageWidth();
        }//get width of image

    public int getImageHeight(){
        return ImageRepository2.getRepository().getImageHeight();
        }//get height of image

    public int getImageAntialiasing(){
        return ImageRepository2.getRepository().getImageAntialiasing();
        }//get antia of image

    public void reset()throws Exception{
        try{
        CalcOptions.getOptions().setNull();
        ImageRepository2.setNull();
        VideoRepository.setNull();
        } catch (Exception e) {
            throw new Exception(e.getMessage());}

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
