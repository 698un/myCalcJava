package by.itstep.mySite.dao.repository;

import by.itstep.mySite.dao.model.VideoFile;
import by.itstep.mySite.utilits.CalcOptions;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class VideoRepository implements iVideoRepository {



    /**
     * This method start the coding of videoFile
      * @return filename
     */
    public void createNewVideo(String fileName) throws Exception{

        //if process is not complete
        if (!videoIsComplette()) throw new Exception("Process is not Complette");

        try {
            VideoFile videoFile = new VideoFile(fileName);

            VideoSave saveThread = new VideoSave(videoFile);
            saveThread.start();


            } catch (Exception e){throw new Exception(e.getMessage());}


        //return videoFileName;
        }

    /**
     * This method defined complette process of video create or not
      * @return TRUE if not complette
     */
    public boolean videoIsComplette(){
       // System.out.println("COMPLETTE:"+getFileComplettePath());
        File fileComplette = new File(getFileComplettePath());
        if (fileComplette.exists()) return true;
        return false;
        }

    /**
     * This method set marker as video is complette
     */
    public void videoSetComplette(){
        // System.out.println("COMPLETTE:"+getFileComplettePath());
        File fileComplette = new File(getFileComplettePath());
        try {
            fileComplette.createNewFile();
            } catch (IOException e){}
        }//videoSetComplette


    /**
     * This method set marker as process create of video is running
     */
    public void videoSetUnComplette(){
        // System.out.println("COMPLETTE:"+getFileComplettePath());
        File fileComplette = new File(getFileComplettePath());
        try {
            fileComplette.delete();
            } catch (Exception e){}
        }//videoSetUnComplette



    /**
     * This method generate path to completteFile
      * @return
     */
    public String getFileComplettePath(){
        return CalcOptions.getOptions().getApplicationPath()+
                File.separator+
                CalcOptions.getOptions().getVideoResultatFolder()+
                File.separator+
                "complette.inf";

        }//getFileComplettePath

    /**
     * This method search completting videoFiles
     * @return ArrayList of filenames
     */
    public ArrayList<String> getVideoList(){

        String path = CalcOptions.getOptions().getStr("applicationPath")+
                      File.separator+
                      CalcOptions.getOptions().getVideoResultatFolder()+
                      File.separator;


        File dir = new File(path); //path указывает на директорию
        ArrayList<String> lst = new ArrayList<>();
        String fileName;

        for ( File file : dir.listFiles() ){

            if ( file.isFile() ){
                fileName = file.getName();
                if (fileName.indexOf(".mp4")>-1) lst.add(file.getName());
                }//if isFile
            }//next file

        return lst;

        }//getVideoList

    /**
     * This method return File object as videoFile by name
      * @param fileName
     * @return
     */
    public File getVideo(String fileName){
        return null;
        }








    //SYSTEM METHODS========================================================================
    public static void setNull(){
        singleRepository=null;
        }//setNull

    private static VideoRepository singleRepository;

    //constructor
    private VideoRepository(){

        //create folder if not exist
        String imageFolder = CalcOptions.getOptions().getStr("applicationPath")+
                File.separator+
                CalcOptions.getOptions().getStr("videoResultatFolder")+
                File.separator;
        try {
            new File(imageFolder).mkdirs();
            } catch (Exception e) {e.printStackTrace();}




        //Создание файла присзнака что можно склеивать
        videoSetComplette();

        }

    public static VideoRepository getRepository(){
        if (singleRepository==null) singleRepository = new VideoRepository();
        return singleRepository;
        }

    }
