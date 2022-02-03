package by.itstep.mySite.service;

import by.itstep.mySite.dao.repository.ClientRepositoryHM;
import by.itstep.mySite.utilits.CalcOptions;

public class ClientService {

    private ClientRepositoryHM clientRep;


    public boolean inRepository(String inpClientKey){

        //verifing root
        if (CalcOptions.getOptions().getCurrentRootKey().equals((inpClientKey))) return true;

        return ClientRepositoryHM.getRepository().inRepository(inpClientKey);
        }





    public void leaveClientByKey(String clientKey)throws Exception{
        try {
            clientRep.deleteClientByKey(clientKey);
            } catch (Exception e) {throw new Exception(e.getMessage());}
        }


    public String getClientKey(){
        return clientRep.newClient();
        }//getClientKey





    public int getClientCount() throws Exception{
        try {
            return clientRep.getClientCount();
            } catch (Exception e){ throw new Exception(e.getMessage());}
        }//getClientCount





    //@Override
    public void  updateLastTimeConnect(String clientKey){
        clientRep.updateLastTimeConnect(clientKey);
        }//getClientCount


    //================================SYSTEMSERVICE
    private static ClientService singleService;

    public static ClientService getService(){
        if (singleService==null) singleService = new ClientService();
        return singleService;
        }

    private ClientService() {

        clientRep = ClientRepositoryHM.getRepository();
        }



}//class ClientService
