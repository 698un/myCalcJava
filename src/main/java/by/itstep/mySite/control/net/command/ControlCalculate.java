

/*
This Class contain all command for the work of the User

 */
package by.itstep.mySite.control.net.command;

import by.itstep.mySite.control.net.NetRequest;
import by.itstep.mySite.control.net.entity.ClientResult;
import by.itstep.mySite.control.net.enums.RequestType;
import by.itstep.mySite.dao.model.enums.StatusPixelLine;
import by.itstep.mySite.dao.repository.ClientRepositoryHM;
import by.itstep.mySite.service.AcceptException;

import by.itstep.mySite.dao.model.PixelLine;
import by.itstep.mySite.service.ClientService;
import by.itstep.mySite.service.TaskService;
import by.itstep.mySite.utilits.loger.LogState;
import by.itstep.mySite.utilits.loger.MyLogger;

public class ControlCalculate {



    public static String getNewTask(NetRequest netReq) throws Exception{

        //Verify regictration this client
        if (false== ClientService.getService().inRepository(netReq.getClientKey())) {
            MyLogger.getLogger().log(LogState.WARN,"Wrong user gets task");
            throw new Exception("Client is not actual");
            }


        try {

            //get new task with clientKey
            PixelLine pixLine = TaskService.getService().getNextTask(netReq.getClientKey());

            StringBuffer sb1 = new StringBuffer("");

            sb1.append("{");

            sb1.append("\"sceneKey\":\"" + TaskService.getService().getSceneKey() + "\",");
            sb1.append("\"frame\":" + pixLine.getFrameNumber() + ",");
            sb1.append("\"line\":"  + pixLine.getLineNumber());
            sb1.append("}");

            MyLogger.getLogger().log(LogState.DEBUG,"Client:"+netReq.getClientKey()+" get task:"+sb1);

            return sb1.toString();

        } catch (Exception e) {
            MyLogger.getLogger().log(LogState.ERROR,"Client:"+netReq.getClientKey()+" getTask ERROR");
            throw new Exception(e.getMessage());
            }

    }//getClientKey

    public static String postResultatLine(NetRequest netReq) throws Exception{

        netReq.setRequestType(RequestType.WebData);
        //Verify regictration this client
        if (false== ClientService.getService().inRepository(netReq.getClientKey())) {
            MyLogger.getLogger().log(LogState.WARN, "Expired client:"+netReq.getClientKey()+" getTask ERROR");
            throw new Exception("Client is not actual");
            }

        Long calcTime;//time of the full cicle of calculation the pixelLine
        try {

            if (netReq.getBodyString()==null) {

                MyLogger.getLogger().log(LogState.WARN, netReq.getClientKey()+" send null result");
                throw new Exception ("Null data in body");
                }

            //Object of pixelLine from request
            ClientResult thisResult= ClientResult.getClientResult(netReq);

            TaskService tskService = TaskService.getService();

            //create Pixel line from request
            PixelLine complettePixelLine =
                    new PixelLine(thisResult.getFrameNum(),
                                  thisResult.getLineNum());

            complettePixelLine.setByteArray(thisResult.getPixelResult());

            complettePixelLine.setClientKey(netReq.getClientKey());
            complettePixelLine.setStatus(StatusPixelLine.COMPLETTE);

            //send to Service complettePixelLine
            calcTime = tskService.postCompletteTask(complettePixelLine);

            //create report of calculation
            StringBuffer sb1 = new StringBuffer("");

            sb1.append("{");
            sb1.append("\"duration\":"+Long.toString(calcTime));
            sb1.append("}");

            MyLogger.getLogger().log(LogState.DEBUG,"Client:"+netReq.getClientKey()+" post resultat");
            return sb1.toString();

            //e.printStackTrace();
            } catch (Exception e) {
                MyLogger.getLogger().log(LogState.ERROR, "Error post result from "+netReq.getClientKey());
                throw new Exception(e.getMessage());
                }

    }//PostResultatLine

 }//ControlCalculate

