
package by.itstep.mySite.service;

import by.itstep.mySite.dao.repository.VideoRepository;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Field;
import java.util.ArrayList;



@ExtendWith(MockitoExtension.class)
public class VideoServiceTest {


    private VideoService videoService = VideoService.getService();

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
//        Mockito.when(videoRepository.createNewVideo(Mockito.anyString())).;
        try {
            videoService.createMP4(fileName);

            } catch(Exception e) {throw new AssertionError();}

    }





    /**
     * This method extracts singleton object from VideoRepository
     * @param inpVideoRepository
     */
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