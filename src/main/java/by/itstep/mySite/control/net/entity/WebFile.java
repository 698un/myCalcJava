package by.itstep.mySite.control.net.entity;
import by.itstep.mySite.control.net.NetRequest;
import by.itstep.mySite.control.net.enums.*;
import by.itstep.mySite.utilits.loger.LogState;
import by.itstep.mySite.utilits.loger.MyLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WebFile {

    private String fileExt;//расширенме запрашиваемого файла
    private String mimeType;
    private File fileObject;

    public File   getFile(){    return this.fileObject;}

    public void   setFile(File cFile){  this.fileObject = cFile;
                                        this.defineFileExt();}



    /**
     * \This method return object WebFile if file is exist in "Web" folder
     * @param netReq
     * @return
     */
    public static WebFile getStaticWebFile(NetRequest netReq){

        //Browser request files only per GET request
        if (netReq.getHttpType()!=HttpType.GET) return null;

        //getting urlString from  NetRequest
        String urlString = netReq.getUrlString();

        //Empty URL reDefined as "/index.html"
        if ("/".equals(urlString)) urlString = "/index.html";

        //defined file in the disc
        String filePath = System.getProperty("user.dir")+
                File.separator+
                "web"+
                File.separator+
                urlString;

        //search file in disc
        File myFile = new File(filePath);

        if (myFile.exists()==false) {
                    //System.out.println("такого нет");
                    return null;
                    }

        //System.out.println("Проверяем наличие файла: "+ myFile.getAbsoluteFile());
       // System.out.println("такой есть!!!");
        WebFile result = new WebFile(myFile);
        MyLogger.getLogger().log(LogState.DEBUG,"User reguest file:"+urlString);


        //mark request as static content file before return object of webFile
        netReq.setRequestType(RequestType.WebFile);

        return result;
        }//getFileInReguest



    //constructor
    public WebFile(File file1){
           this.fileObject = file1;

           this.defineFileExt();
           }//constructor

    private void defineFileExt(){

        //extract fileExt in LowerCase representation
        int indexDot = this.fileObject.getAbsolutePath().lastIndexOf(".");
        this.fileExt = this.fileObject.getAbsolutePath().substring(indexDot).toLowerCase();

        //defined MimeType
        if ("jpg".equals(fileExt)) this.mimeType = "image/jpeg";
        if ("js".equals(fileExt))  this.mimeType = "text/javascript";
        if ("html".equals(fileExt))this.mimeType = "text/html";
        if ("gif".equals(fileExt)) this.mimeType = "image/gif";
        if ("ico".equals(fileExt)) this.mimeType = "image/vnd.microsoft.icon";
        if ("mp4".equals(fileExt)) this.mimeType = "video/mp4";

        }//defineFileExt

    /**
     * This method convert file in array of byte
     * write headers and array of byte in output
     * @param output
     */
    public void sendToClient(OutputStream output){

            StringBuffer response = new StringBuffer("HTTP/1.1 200 OK\n");

            //System.out.println("отправляем файл "+this.fileObject.getAbsoluteFile());
            MyLogger.getLogger().log(LogState.DEBUG,"Send file to user: "+this.fileObject.getAbsoluteFile());


            response.append("Last-Modified: " + new java.util.Date(this.fileObject.lastModified()) + "\n");
            response.append("Content-Length: " + this.fileObject.length() + "\n");
            response.append("Content-Type: " + this.mimeType + "\n");
            response.append("Cache-Control: no-cache, no-store, must-revalidate"+"\n");//without cash
            response.append("Connection: close\n");
            response.append("Server: Server\n\n");

            //write header of response
            try {
                output.write(response.toString().getBytes());
              } catch (IOException e) { System.out.println("IOE");}


            //write data of response
            try {
                FileInputStream fis = new FileInputStream(this.fileObject.getAbsolutePath());

                //Create buffer of byte with length as fileSize
                byte[] buffer = new byte[(int)this.fileObject.length()];

                int writeCount = 1;
                while (writeCount > 0) {
                    writeCount = fis.read(buffer);
                    if (writeCount > 0) output.write(buffer, 0, writeCount);
                 }

                fis.close();//close file of reading

            } catch (IOException e) {System.out.println("IOE");}

        } //sendWebFile

}//class webFile
