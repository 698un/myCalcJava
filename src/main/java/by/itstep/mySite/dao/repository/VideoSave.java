package by.itstep.mySite.dao.repository;

import by.itstep.mySite.dao.model.VideoFile;
import by.itstep.mySite.utilits.CalcOptions;
import by.itstep.mySite.utilits.loger.LogState;
import by.itstep.mySite.utilits.loger.MyLogger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class VideoSave extends Thread{

    VideoFile videoFile;

    public VideoSave(VideoFile inpVideoFile){
        this.videoFile = inpVideoFile;
        }

    @Override
    public void run(){


        try{

            //Create Command File
            String batPath = createBatFile(videoFile.getFullPath());

            //Run creating command file
            launchBatFile(batPath);







        } catch (Exception e) {

            // При открытии и сохранении файлов, может произойти неожиданный случай.
            // И на этот случай у нас try catch
            e.printStackTrace();
            MyLogger.getLogger().log(LogState.ERROR,"not started video create!!!");

        }

    }//saveToDisc



    private void launchBatFile(String bathPath)throws Exception{

        String workDir = CalcOptions.getOptions().getApplicationPath()+
                         File.separator+
                         CalcOptions.getOptions().getImageResultatFolder()+
                         File.separator;

        try {
            Runtime.getRuntime().exec("cmd /c start "+bathPath,null,new File(workDir));



             } catch (IOException e) {
                    MyLogger.getLogger().log(LogState.ERROR,"video create not starting");
                    throw new Exception(e.getMessage()) ;
                        }

        //mark in fileSystem that process is started
        VideoRepository.getRepository().videoSetUnComplette();
        MyLogger.getLogger().log(LogState.INFO,"Start video create");


        }//launchBatFile



    private String createBatFile(String fullVideoPath)throws IOException {

        String batFullPath = CalcOptions.getOptions().getApplicationPath()+
                             File.separator+
                             CalcOptions.getOptions().getStr("imageResultatFolder")+
                             File.separator+
                             "create.bat";

        //create first Command line in bat file
        String data1 =CalcOptions.getOptions().getStr("ffmpegPath")+
                     " -r "+
                     CalcOptions.getOptions().getInt("fps")+
                     " -y -i \"%%10d.png\" "+
                     fullVideoPath+
                     "\n";

        String data2 = "NUL>  "+
                      VideoRepository.getRepository().getFileComplettePath()+
                      "\n";

        String data3 = "exit";


        System.out.println("bat_data:"+data1);
        System.out.println("bat_data:"+data2);
        System.out.println("bat_data:"+data3);


        System.out.println("bat_path:"+batFullPath);



        File batFile = new File(batFullPath);

        FileWriter fr = null;
        try {
            fr = new FileWriter(batFile);
            fr.write(data1);
            fr.write(data2);
            fr.write(data3);


        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return batFullPath;

    }//createBatFile


}//MyImage
