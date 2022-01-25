package by.itstep.mySite.control.net.command;

import by.itstep.mySite.control.net.NetRequest;
import by.itstep.mySite.control.net.entity.WebFile;
import by.itstep.mySite.control.net.enums.RequestType;
import by.itstep.mySite.service.SystemService;
import by.itstep.mySite.service.VideoService;
import by.itstep.mySite.utilits.CalcOptions;

import java.io.File;
import java.util.ArrayList;

public class ControlVideo {

    public static String createMP4(NetRequest netReq) throws Exception{

        //System.out.println("SAVE_CONTROL");

        String fileName = netReq.getUrlString().split("createvideo/")[1];

        //Security virify(pnly for admin)
        String adminKey = CalcOptions.getOptions().getCurrentRootKey();
        System.out.println("adminKEy:"+adminKey);
        if (!netReq.getClientKey().equals(adminKey)) throw new Exception("access denied (only for Administrator)!!!");

        //append ext for filename
        if (fileName.indexOf(".mp4")<0) fileName+=".mp4";

        fileName.toLowerCase();//NECESalary

        //System.out.println("POST /video/create");

        try {

            StringBuffer sb1 = new StringBuffer("startProcess");
            VideoService videoService = VideoService.getService();
            videoService.createMP4(fileName);
            return "start process";

            } catch (Exception e) { throw new Exception (e.getMessage());}

    }//createMP4



    public static String getVideoAll(NetRequest netReq) throws Exception{


        System.out.println("Control_Video_all");

        //verify right of root
        String adminKey = CalcOptions.getOptions().getCurrentRootKey();
        if (!netReq.getClientKey().equals(adminKey)) throw new Exception("access denied (only for Administrator)!!!");



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

            } catch (Exception e) { throw new Exception (e.getMessage());}

    }//video/all



    public static String getVideoFile(NetRequest netReq) throws Exception{


        String fileName = netReq.getUrlString().split("video/file/")[1];

        //Security virify(pnly for admin)
        String adminKey = CalcOptions.getOptions().getCurrentRootKey();
        System.out.println("adminKEy:"+adminKey);
        if (!netReq.getClientKey().equals(adminKey)) throw new Exception("access denied (only for Administrator)!!!");

        System.out.println("ACCESS GRANTED");

        String filePath = System.getProperty("user.dir")+
                File.separator+
                CalcOptions.getOptions().getVideoResultatFolder()+
                File.separator+
                fileName;

        System.out.println("filePath:"+filePath);

        //search file in disc
        File videoFileObject = new File(filePath);

        if (!videoFileObject.exists()) {
                netReq.setRequestType(RequestType.WebData);
                throw new Exception ("File not exist!!");
                }


        netReq.setWebFile(new WebFile(videoFileObject));

        //netReq.getWebFile().setFile(videoFileObject);

        netReq.setRequestType(RequestType.WebFile);

        return "OK";
        }//GetVideoFile


    //if (urlString.indexOf("/video/file/") == 0) answer = ControlVideo.getVideoAll(netReq);




}
