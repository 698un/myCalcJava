package by.itstep.mySite.dao.repository;

import by.itstep.mySite.dao.model.PixelLine;
import by.itstep.mySite.service.AcceptException;
import by.itstep.mySite.service.CalcException;
import by.itstep.mySite.utilits.CalcOptions;
import by.itstep.mySite.dao.model.MyImage;
import by.itstep.mySite.utilits.MyLocker;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;


public class ImageRepository {

    private int imgWidth;
    private int imgHeight;
    private int imgAntialiasing;//глубина антиальясинга
    private String sceneKey;
    private String imagePath;
    private int fps;


    public int getImageWidth() {
        return this.imgWidth;
    }

    public int getImageHeight() {
        return this.imgHeight;
    }

    public int getImageAntialiasing() {
        return this.imgAntialiasing;
    }

    public String getSceneKey() {
        return this.sceneKey;
    }

    public int getFps() {
        return this.fps;
    }


    private MyImage[] imgInBuffer;
    private int imgCountInBuffer;
    private int newFrameNum;

    /**
     * This method extract empty pixelLine for calculation
     * and mark as clientKey in repository (for identification resultat)
     * @param clientKey
     * @return
     */
    public PixelLine getEmptyPixelLine(String clientKey) {

        PixelLine currentPixelLine;
        int calcLineNumber;

        //Search free linePixel in buffer of the images
        for (int i = 0; i < imgCountInBuffer; i++) {

            synchronized (MyLocker.getLocker()) {
                currentPixelLine = imgInBuffer[i].getNewPixelLine(clientKey);
            }

            //if free pixelLine  is exist then mark this pixel line
            //as clientKey and return it
            synchronized (MyLocker.getLocker()) {
                if (currentPixelLine != null) {
                    currentPixelLine.setClientKey(clientKey);//mark clientKey
                    return currentPixelLine;
                }//if getting pixelLine
            }

        }//next i


        //search completted images
        for (int i = 0; i < imgCountInBuffer; i++) {

            if (imgInBuffer[i].isComplette()) {

                synchronized (MyLocker.getLocker()) {
                    int bufferSize = CalcOptions.getOptions().getInt("imageInBuffer");//getImageInBufferCount();

                    for (int j = i; j < bufferSize - 1; j++) imgInBuffer[j] = imgInBuffer[j + 1];

                    imgInBuffer[bufferSize - 1] = new MyImage(this.imgWidth, this.imgHeight, this.newFrameNum);
                    newFrameNum++;
                    break;
                }//Synchronized


            }//if complette


        }//next i


        return new PixelLine(0, 0);

    }

    /**
     * This method search image that pixelLine sending
     *
     * @param complettePixelLine
     * @return time in millis
     * @throws Exception
     */
    public Long postCompletteTask(PixelLine complettePixelLine) throws Exception {

        //define image from that getting the line
        int frameNumber = complettePixelLine.getFrameNumber();
        MyImage currentImage = getImageByFrameNumber(frameNumber);

        //System.out.println("Resultat_frameNumber:"+frameNumber);

        //exception if image not found
        if (currentImage == null) throw new CalcException("This line not actual");

        //define lineNumber if resultat
        //int lineNumber = complettePixelLine.getLineNumber();

        //flush in searched image line of resultat
        return currentImage.flushComplettePixelLine(complettePixelLine);

    }

    private MyImage getImageByFrameNumber(int inpFrame) {
        synchronized (MyLocker.getLocker()) {
            for (int i = 0; i < imgCountInBuffer; i++)
                if (imgInBuffer[i].getFrameNum() == inpFrame) return imgInBuffer[i];
        }
        return null;
    }//getImageByFrameNumber


    private void deleteAllImages() throws Exception{

        String folderPath = CalcOptions.getOptions().getApplicationPath() +
                File.separator +
                CalcOptions.getOptions().getImageResultatFolder() +
                File.separator;

        try {
            //deleting in cicle all files in directory
            for (File myFile : new File(folderPath).listFiles())
                if (myFile.isFile()) myFile.delete();
        } catch (Exception e) {throw new Exception(e.getMessage());}


    }//deleteAllImages

    ///=================================================================/==================

    /**
     * This method reset Repository
     */
    public static void setNull(){
        singleRepository=null;
    }

    private static ImageRepository singleRepository;

    public static ImageRepository getRepository(){
        if (singleRepository==null )  singleRepository = new ImageRepository();
        return singleRepository;
    }


    //constructor
    private ImageRepository() {

        //defined path to imageResultatFolder
        String imageFolder = CalcOptions.getOptions().getStr("applicationPath")+
                             File.separator+
                             CalcOptions.getOptions().getStr("imageResultatFolder")+
                             File.separator;

        //create folder for resuiltat if not exist
        try {
            new File(imageFolder).mkdirs();
            } catch (Exception e) {}





        //Deleting All image in resultat Folder
        try {
            this.deleteAllImages();
            } catch (Exception e){};


        //set options from config
        this.imgWidth=           CalcOptions.getOptions().getInt(   "imageWidth" );
        this.imgHeight=          CalcOptions.getOptions().getInt(   "imageHeight");
        this.imgAntialiasing =   CalcOptions.getOptions().getInt("antialiasing" );

        this.imagePath = CalcOptions.getOptions().getStr("appPath")+
                File.separator+
                CalcOptions.getOptions().getStr("imageResultatPath");

        this.fps = CalcOptions.getOptions().getInt("fps");

        this.imgCountInBuffer =CalcOptions.getOptions().getInt("imageInBuffer");//ImageInBufferCount();

        this.imgInBuffer = new MyImage[imgCountInBuffer];

        for (int i=0;i<imgCountInBuffer;i++){
            imgInBuffer[i] = new MyImage(this.imgWidth,
                    this.imgHeight,
                    i);

            newFrameNum = imgCountInBuffer;

        }//Next i

        //generated sceneKey
        this.sceneKey = getNewKey();

    }//constructor




    private String getNewKey(){
        Random rnd = new Random();
        StringBuilder sb1=new StringBuilder("");
        for (int i=0;i<20;i++) sb1.append((char)('a'+rnd.nextInt('z'-'a')));
        return sb1.toString();
    }//getNewKey


}
