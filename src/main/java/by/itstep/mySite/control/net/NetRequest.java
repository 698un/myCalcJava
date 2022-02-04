/*
Этот класс описывает сущность и методы
запроса от клиента
 */


package by.itstep.mySite.control.net;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import by.itstep.mySite.control.net.enums.*;
import by.itstep.mySite.control.net.entity.*;

import by.itstep.mySite.App;//for acces to options
import by.itstep.mySite.service.ClientService;
import by.itstep.mySite.utilits.CalcOptions;

//    3091515

/**
 * Created by 698UN on 11.09.2021.
 */
public class NetRequest {


    private String contentString;//Full request in String representation
    private String firstLine;//first line in request
    private HttpType httpType;//Http type (Get,Put,Delete,Post.....)
    private String urlString;//URL

    private RequestType requestType;//тип запроса( webfile , webdata,....
    private String bodyString;//data in user command
    private String clientKey;

    private WebFile webFile;//object that need for file request (static content)
    //private ClientResult clientResult;//object of the resultat
    private ResponseText responseText;//object of response
    private UserRole userRole;



    //exit from calculation
    //  GET /logout HTTP1.1



    public String   getContent()          {return this.contentString;}
    public HttpType getHttpType()         {return this.httpType;}
    public String   getUrlString()        {return this.urlString; }//getUrlString
    public String   getBodyString()       {return this.bodyString; }//getUrlString
    public ResponseText getResponseText() {return this.responseText;}//get response field

    public void     setResponseText(ResponseText inpResponseText)
                                          {this.responseText = inpResponseText;}//set response field


    public void        setRequestType(RequestType inpRequestType){this.requestType = inpRequestType; }//set RequestType
    public RequestType getRequestType(){return this.requestType; }//set DataType

    public String   getClientKey(){return this.clientKey;}//data in user request
    public void     setClientKey(String inpKey){this.clientKey = inpKey;}//data in user request
    public UserRole getUserRole(){return this.userRole;}

    public WebFile  getWebFile(){return this.webFile;}
    public void     setWebFile(WebFile inpWebFile){this.webFile = inpWebFile;}
    //public ClientResult getClientResult(){return this.clientResult;}



    //Constructor
    //Convert byteStream to fields of request
    public NetRequest( InputStream input) throws Exception {

        this.requestType=RequestType.Unknow;//default type is not defined


        byte[] buffer = new byte[1024*1024];//массив байт в который запишем запрос клиента
        int bytesCount = input.read(buffer);//читаем массив байтов на входе

       // System.out.println( bytesCount+" / "+buffer.length+"ostatok:"+input.available());

        //if request is empty
        if (bytesCount<=0) return;



        //update read data from request
        while (input.available()>0) {
            //read add array
            byte[] addBuffer = new byte[1024*1024];
            int addBytesCount = input.read(addBuffer);//читаем массив байтов на входе

            if (addBytesCount>0) {
                //merge two array

         //       System.out.println( bytesCount+" add "+addBytesCount);

                byte[] arraySum = new byte[bytesCount+ addBytesCount];

                System.arraycopy(buffer, 0, arraySum, 0, bytesCount);
                System.arraycopy(addBuffer, 0, arraySum, bytesCount, addBytesCount);

                //reLink
                buffer = arraySum;
                bytesCount = bytesCount+addBytesCount;
                }

            }//while input read not complete




        //if request is empty
        if (bytesCount<=0) return;

        //convert full request(in bytes) to one String
        this.contentString = new String(buffer,0,bytesCount);

        this.firstLine = contentString.split("\n")[0]; //for example "GET /index.html HTTP/1.1"
        this.urlString = firstLine.split(" ")[1];      //for example "/index.html"


        this.defineHttpType(firstLine);//defined request type (GET,POST, .....UNKNOW)



        //============DEFINED TYPE OF THE CLIENT REQUEST
        this.defineRequestType();

        //SECURITY FILTER  !!!! NECCESALARY
        this.securityVerify();//call the security filter


        buffer = null;
        }//constructor

    /**This method define type of reguest (get file,get data or send resultat)
     *
     */
    private void defineRequestType(){

        //defined static file in request (if exist)
        this.webFile = WebFile.getStaticWebFile(this);

        //defined bodyString
        this.bodyString = defineBodyString();

        //this.searchCommand();         //defined request  to data from DataBase

        //System.out.println("typeRequest :"+this.requestType);
        }


    /**
     * This method extract bodyString from contentRequest
      * @return String representation of body
     *  @return null if body not found
     */
    private String defineBodyString(){

        //Search sign of begin body in request
        int indexBody = this.contentString.indexOf("\n\r");
        if (indexBody<=0) {
            indexBody = this.contentString.indexOf("\n\n");
            if (indexBody<=0) return null;
            }




        String body = this.contentString.substring(indexBody+3);
        if (body!=null && body.length()==0) return null;
        return body;
        }




    /**
     * This method define type of the reguest (Get,Post,......)
     * @param inpFirstLine
     */
    private void defineHttpType(String inpFirstLine){
        this.httpType = HttpType.UNKNOW;//default Http is UNKNOWN
        if (this.firstLine.indexOf("GET")==0)    this.httpType=HttpType.GET;
        if (this.firstLine.indexOf("POST")==0)   this.httpType=HttpType.POST;
        if (this.firstLine.indexOf("DELETE")==0) this.httpType=HttpType.DELETE;
        }//defineRequestType

    //=====================================s e c u r i t y    m e t h o d s========================================
    public void securityVerify(){
        this.clientKey = ClientKey.getClientKey(this);

        //defined role
        this.userRole = UserRole.GUEST;
        if (ClientService.getService().inRepository(clientKey)) this.userRole = UserRole.CLIENT;
        if (CalcOptions.getOptions().getCurrentRootKey().equals(clientKey)) this.userRole = UserRole.ROOT;


        //defined access to files
        securityVerifyFiles();



        }


    /**
     * This method verify access to file, that request user
     */
    private void securityVerifyFiles(){
        //abort method if user regue access from not client directory

        if (this.getRequestType()!=RequestType.WebFile) return;
        if (this.getHttpType()!=HttpType.GET) return;

        int userType = 0;//unknow

        if (this.getUrlString().indexOf("client")>-1 &&
                this.userRole==UserRole.GUEST) {
                urlString = "/access_denied.html";
                WebFile.getStaticWebFile(this);
                }

        if (this.getUrlString().indexOf("admin")>-1 &&
                this.userRole!=UserRole.ROOT) {
                urlString = "/access_denied.html";
                this.webFile = WebFile.getStaticWebFile(this);
                }




        }// securityVerifyFiles



}//class NetRequest
