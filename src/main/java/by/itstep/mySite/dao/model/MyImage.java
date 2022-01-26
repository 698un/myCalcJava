package by.itstep.mySite.dao.model;

import by.itstep.mySite.dao.model.enums.StatusPixelLine;
import by.itstep.mySite.dao.repository.MyImageSave;
import by.itstep.mySite.service.CalcException;
import by.itstep.mySite.utilits.MyLocker;
//import by.itstep.mySite.utilits.loger.Loger;

public class MyImage implements iMyImage {

    private static final PixelLine nullPixelLine = new PixelLine(-1, -1);

    private int width;
    private int height;
    public PixelLine[] pixelLine;
    private int completeMinIndex;//index of minimal complette line
    private int frameNum;
    private boolean completteImage;
    private int completteLineCount;

    //Getters and Setters====================================================================
    public int getFrameNum() {
        return this.frameNum;
    }

    public boolean isComplette() {
        return this.completteImage;
    }

    public void complette() {
        this.completteImage = true;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }


    //=============CONSTRUCTOR=====================================================
    public MyImage(int inpWidth, int inpHeight, int inpFrameNum) {
        this.frameNum = inpFrameNum;
        this.width = inpWidth;
        this.height = inpHeight;
        this.pixelLine = new PixelLine[inpHeight];
        this.completeMinIndex = -1;//noLine is complette
        this.completteImage = false;//image if complette
        this.completteLineCount = 0;
    }//MyImage


    //this method search next uncalculate line
    @Override
    public PixelLine getNewPixelLine(String clientKey) {

        boolean thisIsComplette = true;//предполагаем что изображжени просчитано

        //начиная с минимально посчитанной ищем свободную
        //for (int i=this.completeMinIndex+1;i<this.height;i++) {

        for (int i = 0; i < this.height; i++) {
            //If pixelLine not exist then return it index
            if (pixelLine[i] == null) {
                    pixelLine[i] = new PixelLine(this.frameNum, i);
                    pixelLine[i].setClientKey(clientKey);
                    return pixelLine[i];
                    }

            //if duration of calculate pixelLine very long
            if (pixelLine[i] != null &&
                    pixelLine[i].getStatus() != StatusPixelLine.COMPLETTE &&
                    pixelLine[i].getDT() < System.currentTimeMillis()) {
                pixelLine[i] = new PixelLine(this.frameNum, i);
                pixelLine[i].setClientKey(clientKey);
                return pixelLine[i];
            }

            if (pixelLine[i].getStatus() != StatusPixelLine.COMPLETTE) thisIsComplette = false;
        }//next i(pixelLine)

        //signed completted image
        this.completteImage = thisIsComplette;

        return null;
    }//getNewCalcNumber


    @Override
    public Long flushComplettePixelLine(PixelLine completteLine) throws Exception {

        boolean error = false;

        int lineNum = completteLine.getLineNumber();

        if (completteLine.getByteArray().length < this.width * 3) error = true;

        int postLength=completteLine.getByteArray().length;

    //    if (completteLine.getByteArray()[postLength-1]==0 &&
    //       completteLine.getByteArray()[postLength-2]==0 &&
    //       completteLine.getByteArray()[postLength-3]==0 ) error = true;


        //Verifing correct size of the array
        if (error==true) {
            synchronized(MyLocker.getLocker()) {
                pixelLine[lineNum] = null;
                }//synchronized
            throw new CalcException("InCorrect line");
             }


        //verify clientKey
        if (pixelLine[lineNum].getClientKey().equals(completteLine.getClientKey())) {

                  long time = System.currentTimeMillis() - pixelLine[lineNum].getBT();

                  pixelLine[lineNum] = completteLine;
                  this.completteLineCount++;
                  if (completteLineCount == this.height) {

                        MyImageSave saveThread = new MyImageSave(this);
                        saveThread.start();
                        }//IF IMAGE COMPLETTE

            return (Long)time;

            }//IF EQAULS CLIENT




        throw new CalcException("InCorrect format");

    }//flushComplettePixelLine






}//MyImage
