
package by.itstep.mySite.service;

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
public class VideoServiceTest {


    @InjectMocks
    private VideoService videoService;

    private VideoRepository videoRepository;

    @Test
    public void getVideoList()throws Exception {


//        @Before
        videoRepository = Mockito.mock(VideoRepository.class);

        setMock(videoRepository);
        Mockito.when(videoRepository.getVideoList()).thenReturn(new ArrayList<String>());
        videoService.getVideoList();


        }//getVideoList



    @Test
    public void createMP4(){

        String fileName = "myVideo.mp4";


        videoRepository = Mockito.mock(VideoRepository.class);

        setMock(videoRepository);
        Mockito.when(videoRepository.getVideoList()).thenReturn(new ArrayList<String>());
        videoService.getVideoList();





    }



 private void setMock(VideoRepository inpVideoRepository){

     try {
         Field singleRep = VideoRepository.class.getDeclaredField("singleRepository");
         singleRep.setAccessible(true);
         singleRep.set(singleRep,inpVideoRepository );
     } catch (NoSuchFieldException | IllegalAccessException e) {
         e.printStackTrace();
     }


 }//setMock










}