package by.itstep.mySite.control.net.command;

import by.itstep.mySite.control.net.NetRequest;
import by.itstep.mySite.control.net.entity.WebFile;
import by.itstep.mySite.control.net.enums.RequestType;
import by.itstep.mySite.service.SystemService;
import by.itstep.mySite.service.VideoService;
import by.itstep.mySite.utilits.CalcOptions;
import by.itstep.mySite.utilits.loger.LogState;
import by.itstep.mySite.utilits.loger.MyLogger;

import java.io.File;
import java.util.ArrayList;

public class ControlVideo {

    public static String createMP4(NetRequest netReq) throws Exception{

        String fileName = netReq.getUrlString().split("createvideo/")[1];

        //Security verify(only for admin)
        String adminKey = CalcOptions.getOptions().getCurrentRootKey();
        //System.out.println("adminKey:"+adminKey);
        if (!netReq.getClientKey().equals(adminKey)) {
            MyLogger.getLogger().log(LogState.WARN,"Not valid rootKey for create video");
            throw new Exception("access denied (only for Administrator)!!!");
            }//if root key not valid

        fileName.toLowerCase();//NECESalary

        //append ext for filename
        if (fileName.indexOf(".mp4")<0) fileName+=".mp4";


        try {

            StringBuffer sb1 = new StringBuffer("startProcess");
            VideoService videoService = VideoService.getService();
            videoService.createMP4(fileName);

            MyLogger.getLogger().log(LogState.INFO,"Create video: "+fileName);
            return "start process";

            } catch (Exception e) {
                MyLogger.getLogger().log(LogState.ERROR,"Video "+fileName+" not created");
                throw new Exception (e.getMessage());
                }

    }//createMP4


    /**
     * method get JSON object of array of videofiles
     * @param netReq
     * @return
     * @throws Exception
     */
    public static String getVideoAll(NetRequest netReq) throws Exception{

        //verify right of root
        String adminKey = CalcOptions.getOptions().getCurrentRootKey();
        if (!netReq.getClientKey().equals(adminKey)) {
            MyLogger.getLogger().log(LogState.WARN,"Invalid rootKey for getting list of files");
            throw new Exception("access denied (only for Administrator)!!!");
            }

        try{
            // ArrayList of string(filenames) from service
            ArrayList<String> videoList = VideoService.getService().getVideoList();

            //return empty array if not files
            if (videoList.size()==0) return "[]";

            //Create JSON array
            StringBuffer sb1=new StringBuffer("");
            sb1.append("[");
            for (int i=0;i<videoList.size();i++){
                sb1.append("\""+videoList.get(i)+"\"");
                if (i!=videoList.size()-1) sb1.append(",");
                }//next i
            sb1.append("]");

            return sb1.toString();

            } catch (Exception e) {
                MyLogger.getLogger().log(LogState.ERROR,e.getMessage());
                throw new Exception (e.getMessage());
                }

    }//video/all


    /**
     * This method return video file in response of request
     * @param netReq
     * @return
     * @throws Exception
     */
    public static String getVideoFile(NetRequest netReq) throws Exception{


        String fileName = netReq.getUrlString().split("video/file/")[1];

        //Security virify(pnly for admin)
        String adminKey = CalcOptions.getOptions().getCurrentRootKey();
        if (!netReq.getClientKey().equals(adminKey)) {
            MyLogger.getLogger().log(LogState.WARN,"Invalid rootKey");
            throw new Exception("access denied (only for Administrator)!!!");
            }

        String filePath = System.getProperty("user.dir")+
                File.separator+
                CalcOptions.getOptions().getVideoResultatFolder()+
                File.separator+
                fileName;

        MyLogger.getLogger().log(LogState.DEBUG,"request of videoFile:"+fileName);

        //search file in disc
        File videoFileObject = new File(filePath);

        if (!videoFileObject.exists()) {
                netReq.setRequestType(RequestType.WebData);
                MyLogger.getLogger().log(LogState.WARN,"Video "+fileName+" not exist");
                throw new Exception ("File not exist!!");
                }


        netReq.setWebFile(new WebFile(videoFileObject));
        netReq.setRequestType(RequestType.WebFile);

        MyLogger.getLogger().log(LogState.INFO,"Send videoFile to browser: "+fileName);

        return "OK";
        }//GetVideoFile

    }//class VideoControl
