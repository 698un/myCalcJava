package by.itstep.mySite.control.net.mapping;


import by.itstep.mySite.control.net.NetRequest;
import by.itstep.mySite.control.net.command.ControlCalculate;
import by.itstep.mySite.control.net.command.ControlClient;
import by.itstep.mySite.control.net.command.ControlServer;
import by.itstep.mySite.control.net.command.ControlVideo;
import by.itstep.mySite.control.net.entity.ResponseText;
import by.itstep.mySite.control.net.enums.HttpType;
import by.itstep.mySite.control.net.enums.RequestType;

public class PostMapping {

    public static void mapping(NetRequest netReq){

        //exit if not POST
        if (netReq.getHttpType()!= HttpType.POST) return;

        //url request and response
        String urlString = netReq.getUrlString();
        String answer= null;



        //System.out.println(netReq.getUrlString());

        try {

            if (urlString.equals("/clientkey")) answer = ControlClient.getClientKey(netReq);

            if (urlString.indexOf("/resultat") == 0) answer = ControlCalculate.postResultatLine(netReq);

            if (urlString.equals("/api/exit") ) answer = ControlClient.exitClient(netReq);

            if (urlString.indexOf("/api/createvideo/") == 0) answer = ControlVideo.createMP4(netReq);

            if (urlString.equals("/rootkey")) answer = ControlClient.getRootKey(netReq);

            if (urlString.equals("/api/reset")) answer = ControlServer.resetCalculation(netReq);

        } catch (Exception e) {
            netReq.setRequestType(RequestType.WebData);
            //create   ERROR ResponseText
            netReq.setResponseText(new ResponseText(400,e.getMessage()));
            }


        //if response is exist
        if (answer!=null) {
            netReq.setRequestType(RequestType.WebData);//mark type request as webData
            netReq.setResponseText(new ResponseText(answer));//write actual ressponse if netRequest
            }



    }//mapping




}//PostMapping
