

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

            //System.out.println("New client:" + clientKey);
            return sb1.toString();

            } catch (Exception e) {
                throw new Exception("error registration");
                }

    }//getClientKey

    /**
     * This method delete client from calculation by his clientKey
     * @param netReq
     * @return message of error "none" or not "none"
     */
    public static String exitClient(NetRequest netReq) throws Exception{


        System.out.println(CalcOptions.getOptions().getCurrentRootKey());


        try {

            //Если это ключ администратора
            if (netReq.getUserRole()== UserRole.ROOT) {
                CalcOptions.getOptions().getNewRootKey(CalcOptions.getOptions().getStr("rootPassword"));
                return "OK";
                }


            //если это ключ клиента
            ClientService clientService = ClientService.getService();
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

        System.out.println("ROOT_KEY");

        try {

            ClientService clientService = ClientService.getService();

            //define password from body of the request
            String reqPassword = netReq.getBodyString();

            String clientKey = CalcOptions.getOptions().getNewRootKey(reqPassword);

            StringBuffer sb1 = new StringBuffer("");
            sb1.append("{");
            sb1.append("\"ClientKey\":\"" + clientKey + "\"");
            sb1.append("}");


            return sb1.toString();

            } catch (Exception e) {
              //netReq.setRequestType(RequestType.WebData);
              throw new Exception (e.getMessage());
              }

        }//getRootKey








}//class ControlCLient

