package by.itstep.mySite.control.net;

import by.itstep.mySite.control.net.entity.ResponseText;
//4-32 Gusarin


import by.itstep.mySite.control.net.mapping.DeleteMapping;
import by.itstep.mySite.control.net.mapping.GetMapping;
import by.itstep.mySite.control.net.mapping.PostMapping;
import by.itstep.mySite.utilits.loger.LogState;
import by.itstep.mySite.utilits.loger.MyLogger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


    public class ControlNet extends Thread{

    Socket socket;

    //constructor
    public ControlNet(Socket inpSocket){
        this.socket = inpSocket;
        setDaemon(true);
        start();
        }//Constructor



    public void run(){

        try {
            InputStream input =   socket.getInputStream();//поток на входе серверу
            OutputStream output = socket.getOutputStream();//поток на выходе сервера(ответ)

            //объек запроса для удобства работы
            NetRequest netRequest = new NetRequest(input);//преобразование в объект запроса

            GetMapping.mapping(netRequest);
            DeleteMapping.mapping(netRequest);
            PostMapping.mapping(netRequest);

            //send answer by result of request
            switch (netRequest.getRequestType()) {

                //if request webFile
                case WebFile : netRequest.getWebFile().sendToClient(output);
                               break;

                case WebData : netRequest.getResponseText().sendResponse(output);//отправляем данные в ответ
                               break;

                case Unknow : netRequest.setResponseText(new ResponseText(400,"unknowRequest"));
                              netRequest.getResponseText().sendResponse(output);//отправляем данные в ответ
                              break;

                }//switch


            //netRequest = null;


            output.flush();
            output.close();
            socket.close();

        }//try

        catch(Exception e) {
            e.printStackTrace();
            }//catch


    }//run





    public static void main(String[] args)throws Exception{

        int portNumber = Integer.parseInt(args[0]);
        System.out.println(portNumber);

        try {
            ServerSocket server = new ServerSocket(portNumber);
            while (true){
                new ControlNet(server.accept());
                }//while
            }//try

        catch (Exception e) {
            MyLogger.getLogger().log(LogState.FATAL,"ServerNotRun!!! "+e.getMessage());
            throw new Exception(e.getMessage());
            }

    }//main







}//class Server
