package by.itstep.mySite.control.net.entity;

import by.itstep.mySite.control.net.NetRequest;
import by.itstep.mySite.control.net.enums.*;
import by.itstep.mySite.utilits.CalcOptions;

public class ClientResult {


    private int frameNum;//frame index
    private int lineNum;//line index in current frame
    private String sceneKey;//key of the scene (this in the case for a scene change)

    private short[] pixelArray;// array values of pixels in current line


    public void setFrameNum(int inpFrameNum){ this.frameNum = inpFrameNum;  }
    public int getFrameNum(){return this.frameNum; }

    public void setLineNum(int inpLineNum){ this.lineNum = inpLineNum;  }
    public int getLineNum(){return this.lineNum; }

    public short[] getPixelResult(){return this.pixelArray;}




    /**
     * This method define result from client and acstract pixelline from request
     * @return
     */
    public static ClientResult getClientResult(NetRequest netReq){

        System.out.println(netReq.getUrlString());
        if (isClientResult(netReq)==false) return null;
        try {
            ClientResult res = new ClientResult(netReq);
            return res;
            } catch (Exception e) {return null;}
        }//getClientResult


    /**locking for a  result in the url of then request
     * @param inpRequest
     * @return true OR false
     */
    public static boolean isClientResult(NetRequest inpRequest){
        //only POST request
        if (inpRequest.getHttpType()!=HttpType.POST) return false;
        //if substring in Url "/resultat" exist
        if (inpRequest.getUrlString().indexOf("/resultat/")==0) return true;
        return false;
        }//if ClientResult



    //Constructor
    public ClientResult(NetRequest inpRequest) throws Exception{
        String reqUrl = inpRequest.getUrlString();
        String reqBody = inpRequest.getBodyString();

        // FOR EXAMPLE !!!    "/resultat/{sceneKey}/10/23"
        //System.out.println(reqUrl);

        try {
            //defined argument of the resultat
            String[] valArray =  reqUrl.split("/"); //splite url by "/"
            this.sceneKey = valArray[2];
            this.frameNum = Integer.parseInt(valArray[3]);
            this.lineNum = Integer.parseInt(valArray[4]);
            } catch (Exception e) {throw new Exception(e.getMessage());}


        //System.out.println(this.sceneKey+"/"+ this.frameNum +"/"+ this.lineNum );

        //converted bodyString to number Array
        int byteCountInLine = reqBody.length();//count number id body
        int pixelCountInLine =byteCountInLine/3/3;

        //if length of resultat not equals width of the image
        if (pixelCountInLine!= CalcOptions.getOptions().getImageWidth()) throw new Exception ("Error line width("+pixelCountInLine+")");

        this.pixelArray = new short[byteCountInLine];
        int index;

        StringBuffer byteOneStr = new StringBuffer("");
        short byteOneInt;

        //преобразоывание строки цифр в массив int
        for (int i=0;i<pixelCountInLine;i++) {

            //перебор каналов
            for (int ri = 0; ri < 3; ri++) {
                index = i * 9 + ri*3;
                byteOneStr.setLength(0);
                byteOneInt =(short)(
                        (reqBody.charAt( index + 0) - '0') * 100 +
                        (reqBody.charAt( index + 1) - '0') * 10 +
                        (reqBody.charAt( index + 2) - '0')
                        );
                this.pixelArray[i*3+ri] = byteOneInt;
                }//next ri
            }//next i

        //mark this request as WebData(because need answer)
        inpRequest.setRequestType(RequestType.WebData);

        }//constructor


}//class ClientResult
