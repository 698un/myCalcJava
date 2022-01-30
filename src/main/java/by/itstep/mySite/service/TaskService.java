package by.itstep.mySite.service;


import by.itstep.mySite.dao.model.PixelLine;
import by.itstep.mySite.dao.repository.ClientRepositoryHM;
import by.itstep.mySite.dao.repository.ImageRepository2;
import by.itstep.mySite.utilits.CalcOptions;
import by.itstep.mySite.utilits.MyLocker;
import by.itstep.mySite.utilits.loger.LogState;
import by.itstep.mySite.utilits.loger.MyLogger;

public class TaskService {

    private long lineLifeTime;

    public long getLineLifeTime(){return this.lineLifeTime;}
    public void setLineLifeTime(long inpLong){this.lineLifeTime = inpLong;}



    public String getSceneKey(){
        return ImageRepository2.getRepository().getSceneKey();
        }//get SceneKey

    public PixelLine getNextTask(String clientKey){

        //update lastTimeConnect for this client
        ClientRepositoryHM.getRepository().updateLastTimeConnect(clientKey);

        //return new task from imageRepository
        synchronized(MyLocker.getLocker()) {
            return ImageRepository2.getRepository().getEmptyPixelLine(clientKey);
            }

        }//get New Task (PixelLine

    /**
     * This method POST complette pixelLine to imageRepository
     * @param complettePixelLine
     * @return duration time from send to client to write resultat
     * @throws Exception
     */
    public Long postCompletteTask(PixelLine complettePixelLine) throws Exception{
        try {

            synchronized(MyLocker.getLocker()) {
                return ImageRepository2.getRepository().postCompletteTask(complettePixelLine);
                }//synchronized

            } catch (Exception e) {
                MyLogger.getLogger().log(LogState.WARN,e.getMessage());
                throw new CalcException(e.getMessage());
                }
        }//get New Task (PixelLine

    //===========================SYSTEM====METHODS===========================================
    private static TaskService singleService;//возвраащемый одиночный экземпляр

    public static TaskService getService(){
        if (singleService==null) singleService=new TaskService();
        return singleService;
        }//getService

    //приватный конструктор
    private TaskService(){
        this.lineLifeTime = CalcOptions.getOptions().getInt("lineLifeTime");
        //imageRep = ImageRepository.getRepository();
        }//приватный конструктор



}//class TaskService
