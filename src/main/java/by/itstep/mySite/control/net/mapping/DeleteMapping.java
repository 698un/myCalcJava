package by.itstep.mySite.control.net.mapping;

import by.itstep.mySite.control.net.NetRequest;
import by.itstep.mySite.control.net.entity.ResponseText;
import by.itstep.mySite.control.net.enums.HttpType;
import by.itstep.mySite.control.net.enums.RequestType;

import by.itstep.mySite.control.net.command.ControlClient;



public class DeleteMapping {

    //NECESSARiLY
    private DeleteMapping(){


    }

    public static void mapping(NetRequest netReq){

        //exit if not DELETE
        if (netReq.getHttpType()!= HttpType.DELETE) return;

        //url request and response
        String urlString = netReq.getUrlString();
        String answer= null;

        try {
            if (urlString.indexOf("/clientkey") == 0) answer = ControlClient.exitClient(netReq);


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
            netReq.setRequestType(RequestType.WebData);//mark type request as webData
            netReq.setResponseText(new ResponseText(answer));//write actual ressponse if netRequest
            }

    }//mapping




}//DeleteMapping
