package by.itstep.mySite.dao.repository;


import by.itstep.mySite.dao.model.Client;

public interface iClientRepository {

    void deleteClientByKey(String inpKey) throws Exception;
    void updateLastTimeConnect(String clientKey);
    String newClient();
    int getClientCount() throws Exception;
    //void clearOldClients();
    boolean inRepository(String inpKey);


    }
