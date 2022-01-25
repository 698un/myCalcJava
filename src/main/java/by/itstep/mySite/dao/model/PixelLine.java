package by.itstep.mySite.dao.model;

import by.itstep.mySite.control.net.NetRequest;
import by.itstep.mySite.dao.model.enums.StatusPixelLine;
import by.itstep.mySite.service.CalcException;
import by.itstep.mySite.service.TaskService;
import by.itstep.mySite.utilits.CalcOptions;
import by.itstep.mySite.utilits.MyLocker;

public class PixelLine {

    private int frameNum;//номер кадра
    private int lineNum;//номер линии

    private short[] pixelByteArray;

    private StatusPixelLine status;//0,1,2
    private int width;
    private String clientKey;

    private long BT;//time of the birth
    private long DT;//time of the birth

    public void setStatus(StatusPixelLine inpStatus){
        this.status=inpStatus;
        }//setStatus
    public StatusPixelLine getStatus(){return this.status;}

    public long getBT(){return this.BT;}
    public long getDT(){return this.DT;}

    public String getClientKey(){return this.clientKey;}
    public void setClientKey(String inpClientKey){this.clientKey=inpClientKey;}



    //constructor
    public PixelLine(int inpFrameNum,int inpLineNum) {
        this.status = StatusPixelLine.PROCESS;
        this.frameNum=inpFrameNum;
        this.lineNum=inpLineNum;

        this.BT = System.currentTimeMillis();
        this.DT = this.BT+ TaskService.getService().getLineLifeTime();
        }


    public int getFrameNumber(){   return this.frameNum;  }
    public int getLineNumber(){    return this.lineNum;  }
    public short[] getByteArray(){  return this.pixelByteArray;}
    public void setByteArray(short[] inpByteArray){  this.pixelByteArray=inpByteArray;}




    //TODO
    public void setComplette(PixelLine complettePixelLine) throws Exception{

        //verify status
        if (this.status!=StatusPixelLine.PROCESS) throw new CalcException("Line not actual");

        //verify clientKey
        if (!this.clientKey.equals(complettePixelLine.clientKey)) throw new CalcException("Line not actual");

        //verify format
        if (this.width!=complettePixelLine.width) throw new CalcException("ERROR FORMAT PIXEL LINE");

        this.status=StatusPixelLine.COMPLETTE;
        synchronized(MyLocker.getLocker()) {
            this.pixelByteArray = complettePixelLine.pixelByteArray;
            }

        }//fill from net




    }//class PixelLine
