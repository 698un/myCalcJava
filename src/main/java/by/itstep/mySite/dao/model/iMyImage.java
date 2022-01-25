package by.itstep.mySite.dao.model;

public interface iMyImage {
    public PixelLine getNewPixelLine(String clientKey);
    public Long flushComplettePixelLine(PixelLine completteLine) throws Exception;
    }
