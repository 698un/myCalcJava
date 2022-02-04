package by.itstep.mySite.control.net.entity;

import by.itstep.mySite.control.net.NetRequest;

import java.io.IOException;
import java.io.OutputStream;

public class ResponseText {

    //private byte[] byteResponse;//representation of byteArray before sending


    String errorMessage="OK";
    int errorCode=200;//default no error
    String responseString;

    //constructor for normal response
    public ResponseText(String inpString){
        this.errorCode = 200;
        this.errorMessage = "OK";
        this.responseString = inpString;
        }

    //constructor for error message
    public ResponseText(int inpCode,String inpString){
        this.errorCode = inpCode;
        this.errorMessage = inpString;
        this.responseString = null;
        }


    /**
     * This metod mark response as Error
     * @param inpCode
     * @param inpMessage
     */
    public void setError(int inpCode,String inpMessage){
        this.errorCode = inpCode;
        this.errorMessage = inpMessage;
        }


    public void sendResponse( OutputStream output){


        boolean error = false;//we assume that there are not error
        StringBuffer responseAll;//head and data of response
        int len;//=0;//length of responceData

        //write in Head message "Not Error"
        responseAll = new StringBuffer("HTTP/1.1 "+
                                        this.errorCode+" "+
                                        this.errorMessage+"\n");

        //if error then body set errorString
        if (this.responseString==null) this.responseString = "error:"+this.errorMessage;

        //Calculate size of the Data of answer
        byte[] responseDataBytes = this.responseString.getBytes();
        len = responseDataBytes.length;

        responseAll.append("Last-Modified: " + new java.util.Date() + "\n");
        responseAll.append("Content-Length: " + len + "\n");
        responseAll.append("Content-Type: " + "text/plane; charset=utf-8" + "\n");
        responseAll.append("Cache-Control: no-cache, no-store, must-revalidate"+"\n");//without cash
        responseAll.append("Connection: close\n");

        //hier send clientKey in header if client need
        if (this.responseString.indexOf("ClientKey")>=0) {
                //SET COOKIE TO BROWSER OF CLIENT
                String subClientKey = this.responseString.substring(14,responseString.indexOf("}")-1);
                responseAll.append("Set-cookie: ClientKey="+subClientKey+ "\n");
                }

        //mark end of the Heding and begin data of the response
        responseAll.append("Server: Server\n\n");

        //отправляем результат в ответ
        responseAll.append( this.responseString );

        //производим запись параметров ответа
        try {
            output.write(responseAll.toString().getBytes());
            //System.out.println("SENDING:"+this.responseString);
            output.flush();
            output.close();
            } catch (IOException e) { System.out.println("IOE");}

    }//sendResponse


}//class Response

