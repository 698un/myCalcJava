package by.itstep.mySite.control.net.mapping;

import by.itstep.mySite.control.net.NetRequest;

import by.itstep.mySite.control.net.command.*;
import by.itstep.mySite.control.net.entity.ResponseText;
import by.itstep.mySite.control.net.enums.HttpType;
import by.itstep.mySite.control.net.enums.RequestType;

import by.itstep.mySite.service.ClientService;


public class GetMapping {


        //beecause nobody has roight to create this objects
    private GetMapping(){


    }


    public static void mapping(NetRequest netReq){

        //exit if not GET
        if (netReq.getHttpType()!= HttpType.GET) return;

        //Mark last time connect this Client
        ClientService.getService().updateLastTimeConnect(netReq.getClientKey());

        //url request and response
        String urlString = netReq.getUrlString();
        String answer= null;

        //System.out.println(urlString);

        try {

            if (urlString.indexOf("/newtask") == 0) answer = ControlCalculate.getNewTask(netReq);

            if (urlString.indexOf("/server/status") == 0) answer = ControlServer.getServerStatus(netReq);

            if (urlString.indexOf("/video/all") == 0) answer = ControlVideo.getVideoAll(netReq);

            if (urlString.indexOf("/video/file/") == 0) answer = ControlVideo.getVideoFile(netReq);


        } catch (Exception e) {
                    //If ERROR IN CONTROL
                    //create   ERROR ResponseText
                    System.out.println(e.getMessage());
                    //answer ="error";
                    netReq.setRequestType(RequestType.WebData);
                    netReq.setResponseText(new ResponseText(400,e.getMessage()));
                    return;
                    }


        //if response is exist
        if (answer!=null) {
                 if (netReq.getRequestType()!=RequestType.WebFile)
                            netReq.setRequestType(RequestType.WebData);//mark type request as webData

                 netReq.setResponseText(new ResponseText(answer));//write actual ressponse if netRequest
                 }

    }//mapping


}//class GetMapping
