
package by.itstep.mySite.dao.repository;

import by.itstep.mySite.dao.model.PixelLine;
import by.itstep.mySite.dao.repository.VideoRepository;
import by.itstep.mySite.utilits.loger.LogState;
import by.itstep.mySite.utilits.loger.MyLogger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class ImageRepository2Test {


    private ImageRepository imgRep = ImageRepository.getRepository();


    //private VideoRepository videoRepository;

    /**
     *  Test of method
     *  public PixelLine getEmptyPixelLine(String clientKey)
     */

    @Test
    public void getEmptyPixelLine()throws Exception {


//        @Before
        String clientKey = "1234567890";

        //ImageRepository imgRep= Mockito.mock(VideoRepository.class);
         imgRep= Mockito.mock(ImageRepository.class);

        setMock(imgRep);
        Mockito.when(imgRep.getEmptyPixelLine(clientKey)).thenReturn(new PixelLine(0,0));


        imgRep.getEmptyPixelLine(clientKey);


    }//getVideoList






    private void setMock(ImageRepository inpImageRepository){

        try {
            Field singleRep = ImageRepository.class.getDeclaredField("singleRepository");
            singleRep.setAccessible(true);
            singleRep.set(singleRep,inpImageRepository );
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }


    }//setMock










}