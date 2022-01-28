package by.itstep.mySite.service;

import by.itstep.mySite.dao.repository.VideoRepository;
import by.itstep.mySite.utilits.loger.LogState;
import by.itstep.mySite.utilits.loger.MyLogger;

import java.io.File;
import java.util.ArrayList;

public class VideoService {




    public void createMP4(String fileName)throws Exception {
        try {
            VideoRepository.getRepository().createNewVideo(fileName);
            } catch (Exception e) {throw new Exception(e.getMessage());}

        }//createMP4


    /**
     * get list of complette videofiles
     * @param fileName
     * @return
     * @throws Exception
     */

    public ArrayList getVideoList()throws Exception {
        try {
            return VideoRepository.getRepository().getVideoList();
            } catch (Exception e) {throw new Exception(e.getMessage());}

        }//getVideoList

    public File getFileObject(String fileName) throws Exception{

        try{
            return VideoRepository.getRepository().getVideo(fileName);
            } catch (Exception e) {throw new Exception("file error");}

        }//getFileObject


    //===========================SYSTEM====METHODS===========================================
    private static VideoService singleService;//возвраащемый одиночный экземпляр

    public static VideoService getService() {
        if (singleService == null) singleService = new VideoService();
        return singleService;
        }//getService


    //приватный конструктор
    private VideoService(){
        MyLogger.getLogger().log(LogState.INFO,"Init video service");
        }//приватный конструктор


}//VideoService
