package by.itstep.mySite.dao.repository;
import  by.itstep.mySite.dao.model.MyImage;
import by.itstep.mySite.utilits.CalcOptions;
import by.itstep.mySite.utilits.MyLocker;
import by.itstep.mySite.utilits.loger.LogState;
import by.itstep.mySite.utilits.loger.MyLogger;

import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;


public class MyImageSave extends Thread{

    private MyImage image1;

    public MyImageSave(MyImage inpImage){
        this.image1 = inpImage;
        }

     @Override
     public void run(){

        try{

        //Load template format of the imageFile
        File file = new File(CalcOptions.getOptions().getApplicationPath()+
                             File.separator+
                             //"src"+
                             //File.separator+
                             "null.png");


        BufferedImage source=ImageIO.read(file);

        BufferedImage result = new BufferedImage(image1.getWidth(), image1.getHeight(),source.getType());

        int colR;
        int colB;
        int colG;

        Color pixelColor;
//        for (int x = 0; x < image1.getWidth(); x++) {
            for (int ye = 0; ye < image1.getHeight(); ye++) {

                // Получаем цвет текущего пикселя
                short[] lineByte = image1.pixelLine[ye].getByteArray();

                //System..out.println("ye          :"+ye);
                //System.out.println("imageWidth*3:"+image1.getWidth()*3);
                //System.out.println("lineBWidth  :"+lineByte.length);

                int errorCode=0;
                if (lineByte.length!=image1.getWidth()*3) errorCode = 1;


                for (int xe=0;xe<image1.getWidth();xe++) {

                //pixelColor = new Color( 127+lineByte[xe]*3+0,127+lineByte[xe]*3+1,127+lineByte[xe]*3+2);

                    colR = 255;
                    colG = 255;
                    colB = 255;


                        if (xe * 3 + 2 < lineByte.length) {
                            synchronized(MyLocker.getLocker()) {
                                colR = lineByte[xe * 3 + 0];//+127;
                                colG = lineByte[xe * 3 + 1];//+127;
                                colB = lineByte[xe * 3 + 2];//+127;
                                }//synchronized
                            }


                    if (colR>255) colR=255;
                    if (colG>255) colG=255;
                    if (colB>255) colB=255;

                    if (colR<0) colR=0;
                    if (colG<0) colG=0;
                    if (colB<0) colB=0;

                    pixelColor = new Color( colR,colG,colB);

                //    pixelColor = new Color( 255,0,0);

                // И устанавливаем этот цвет в текущий пиксель результирующего изображения
                result.setRGB(xe, ye, pixelColor.getRGB());
            }//next y
        }//next x


        // create full path and filename for saved image
        StringBuffer fileName = new StringBuffer("");
        fileName.append( String.valueOf(image1.getFrameNum()));
        while (fileName.length()<10) fileName.insert(0,"0");
        fileName.append(".png");


        // Созраняем результат в новый файл
        File output = new File(CalcOptions.getOptions().getApplicationPath()+
                                  File.separator+
                                  CalcOptions.getOptions().getImageResultatFolder()+
                                  File.separator+
                                  fileName);


        ImageIO.write(result, "png", output);
        //System.out.println("Сохранен :"+fileName);
        MyLogger.getLogger().log(LogState.INFO,"Save image: "+fileName);


        image1.complette();

        } catch (IOException e) {

            // При открытии и сохранении файлов, может произойти неожиданный случай.
            // И на этот случай у нас try catch
            //e.printStackTrace();

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String sStackTrace = sw.toString(); // stack trace as a string
            //System.out.println(sStackTrace);


            MyLogger.getLogger().log(LogState.ERROR,sStackTrace);

            }




    }//saveToDisc


}//MyImage
