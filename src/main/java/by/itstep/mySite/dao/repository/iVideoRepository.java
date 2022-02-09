package by.itstep.mySite.dao.repository;

import by.itstep.mySite.dao.model.VideoFile;
import by.itstep.mySite.utilits.CalcOptions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface iVideoRepository {

      void createNewVideo(String fileName) throws Exception;
      boolean videoIsComplette();

      void videoSetComplette();
      void videoSetUnComplette();

      String getFileComplettePath();

      ArrayList<String> getVideoList();

      File getVideo(String fileName);

      }//interface VideoRepository
