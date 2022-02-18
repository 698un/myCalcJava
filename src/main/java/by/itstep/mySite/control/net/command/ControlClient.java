

/*
This Class contain all command for the work of the User

 */
package by.itstep.mySite.control.net.command;

import by.itstep.mySite.control.net.NetRequest;
import by.itstep.mySite.control.net.enums.RequestType;
import by.itstep.mySite.control.net.enums.UserRole;
import by.itstep.mySite.service.AcceptException;

import by.itstep.mySite.service.ClientService;
import by.itstep.mySite.utilits.CalcOptions;
import by.itstep.mySite.utilits.loger.LogState;
import by.itstep.mySite.utilits.loger.MyLogger;

public class ControlClient {

    public static String getClientKey(NetRequest netReq) throws Exception{

        try {

            //getting Services
            ClientService clientService = ClientService.getService();

            //define quentity of client
            String clientKey = clientService.getClientKey();
            netReq.setClientKey(clientKey);

            StringBuffer sb1 = new StringBuffer("");

            sb1.append("{");
            sb1.append("\"ClientKey\":\"" + clientKey + "\"");
            sb1.append("}");

            MyLogger.getLogger().log(LogState.INFO,"Add new client. ClientKey:"+clientKey);

            return sb1.toString();

            } catch (Exception e) {
                MyLogger.getLogger().log(LogState.ERROR,"Error add new client.");
                throw new Exception("error registration");
                }

    }//getClientKey

    /**
     * This method delete client from calculation by his clientKey
     * @param netReq
     * @return message of error "none" or not "none"
     */
    public static String exitClient(NetRequest netReq) throws Exception{


        //System.out.println(CalcOptions.getOptions().getCurrentRootKey());


        try {

            //Если это ключ администратора
            if (netReq.getUserRole()== UserRole.ROOT) {
                MyLogger.getLogger().log(LogState.INFO,"root disconnected from server");
                CalcOptions.getOptions().getNewRootKey(CalcOptions.getOptions().getStr("rootPassword"));
                return "OK";
                }


            //если это ключ клиента
            ClientService clientService = ClientService.getService();
            MyLogger.getLogger().log(LogState.INFO,"Client "+netReq.getClientKey()+" leave from calculation");
            clientService.leaveClientByKey(netReq.getClientKey());
            return "OK";

             } catch (Exception e) { throw new Exception (e.getMessage());}




    }//exit client



    /**
     * This method delete client from calculation by his clientKey
     * @param netReq
     * @return message of error "none" or not "none"
     */
    public static String getRootKey(NetRequest netReq) throws Exception {

        //System.out.println("ROOT_KEY");
        MyLogger.getLogger().log(LogState.INFO,"user ask rootKey");

        try {



            //define password from body of the request
            String reqPassword = netReq.getBodyString();

            String clientKey = CalcOptions.getOptions().getNewRootKey(reqPassword);

            StringBuffer sb1 = new StringBuffer("");
            sb1.append("{");
            sb1.append("\"ClientKey\":\"" + clientKey + "\"");
            sb1.append("}");

            MyLogger.getLogger().log(LogState.INFO,"root connected to server");
            return sb1.toString();

            } catch (Exception e) {
              //netReq.setRequestType(RequestType.WebData);
              MyLogger.getLogger().log(LogState.WARN,"wrong access to rootKey");
              throw new Exception (e.getMessage());
              }

        }//getRootKey








}//class ControlCLient

