

/*
This Class contain all command for the work of the User

 */
package by.itstep.mySite.control.net.command;

import by.itstep.mySite.control.net.NetRequest;
import by.itstep.mySite.control.net.enums.RequestType;
import by.itstep.mySite.control.net.enums.UserRole;
import by.itstep.mySite.dao.repository.ImageRepository2;
import by.itstep.mySite.service.AcceptException;

import by.itstep.mySite.service.ClientService;
import by.itstep.mySite.service.SystemService;
import by.itstep.mySite.utilits.CalcOptions;

public class ControlServer {

    public static String getServerStatus(NetRequest netReq) throws Exception{


        //System.out.println("GET \SERVER\STATUS");

        try {
            //getting Services
            ClientService clientService = ClientService.getService();
            SystemService sysService = SystemService.getService();

            //define quentity of client
            int clientCount = clientService.getClientCount();
            //defined size of image
            int imgHeight = sysService.getImageHeight();
            int imgWidth = sysService.getImageWidth();
            int imgAntialiasing = sysService.getImageAntialiasing();

            int fps = ImageRepository2.getRepository().getFps();


            StringBuffer sb1 = new StringBuffer("");


            sb1.append("{");
            sb1.append("\"clientCount\":" + Integer.toString(clientCount));

            sb1.append(",");
            sb1.append("\"imgHeight\":" + Integer.toString(imgHeight));

            sb1.append(",");
            sb1.append("\"imgWidth\":" + Integer.toString(imgWidth));

            sb1.append(",");
            sb1.append("\"imgAntialiasing\":" + Integer.toString(imgAntialiasing));

            sb1.append(",");
            sb1.append("\"fps\":" + Integer.toString(fps));

            sb1.append("}");

            //System.out.println("SERVER_STATUS:" + sb1);

            //netReq.setRequestType(RequestType.WebData);

            return sb1.toString();

            } catch (Exception e) { throw new Exception (e.getMessage());}



    }//getServerStatus



    public static String resetCalculation(NetRequest netReq) throws Exception{


        //System.out.println("GET \SERVER\STATUS");

        if (netReq.getUserRole()!= UserRole.ROOT) throw new Exception("only for root");

        try {
            //getting Services

            SystemService sysService = SystemService.getService();

            sysService.reset();

            return "Server is reset";

        } catch (Exception e) { throw new Exception (e.getMessage());}



    }//getServerStatus






}//class controlServer

