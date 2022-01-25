package by.itstep.mySite.dao.model;


import by.itstep.mySite.dao.repository.VideoRepository;
import by.itstep.mySite.utilits.CalcOptions;

import java.io.File;

public class VideoFile {

    private String fileName;
    private String fullPath;


    public String getFullPath(){
        return this.fullPath;
        }



    public VideoFile(String fileName)throws Exception{
        this.fileName = fileName;

        //Create full Path for new videoFile
        StringBuffer videoFullPathSB=new StringBuffer("");

        videoFullPathSB.append(CalcOptions.getOptions().getApplicationPath());
        videoFullPathSB.append(File.separator);
        videoFullPathSB.append(CalcOptions.getOptions().getVideoResultatFolder());
        videoFullPathSB.append(File.separator);
        videoFullPathSB.append(fileName);

        //Verify exist this file
        String videoFullPath = videoFullPathSB.toString();
        File file1 = new File(videoFullPath);

        if (file1.exists()) throw new Exception("file is exists");

        this.fullPath = videoFullPath;
        }//videoFile



    public void create(){



        }



}
