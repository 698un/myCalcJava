package by.itstep.mySite.control.net.entity;

public interface IResponse {


    void sendResponse();


    void setError(int inpCode,String inpMessage);


    void setData(WebFile webFile);


    void setData(String inpString);

    }//IResponse
