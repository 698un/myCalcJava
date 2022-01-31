package by.itstep.mySite.utilits;


 import by.itstep.mySite.utilits.loger.LogState;
 import by.itstep.mySite.utilits.loger.MyLogger;

 import java.io.File;
 import java.io.IOException;
 import java.util.HashMap;
 import java.util.Map;
 import java.util.Random;
 import java.util.Scanner;
 import java.util.concurrent.ConcurrentHashMap;


public class CalcOptions {

    //private ConcurrentHashMap<String,String> optList;

    private volatile Map<String,String> optList;

    private int imageInBufferCount;

    //control properties
    private int lineLifeTime;
    private int clientLifeTime;
    private int clientClearTime;

    //image properties
    private int fps;
    private int imageHeight;
    private int imageWidth;
    private int antialiasing;

    //paths properties
    private String imageResultatFolder;
    private String videoResultatFolder;
    private String commandFolder;

    private String applicationPath;

    //GETTERS OF PARAMETERS CALCULATIONS
    public String getImageResultatFolder(){return this.imageResultatFolder;}
    public String getApplicationPath()    {return this.applicationPath;}
    public String getVideoResultatFolder(){return this.videoResultatFolder;}
    public String getCommandFolder(){return this.videoResultatFolder;}


    public int getImageHeight()  {return this.imageHeight;}
    public int getImageWidth()   {return this.imageWidth;}
    public int getAntialiasing() {return this.antialiasing;}
    public int getFps()          {return this.fps;}

    public int getClientLifeTime(){return this.clientLifeTime;}
    public int getLineLifeTime()  {return this.lineLifeTime;}
    public int getClientClearTime()  {return this.clientClearTime;}


    public void set(String name,String value){
        optList.put(name,value);
    }
    public void set(String name,int value){
        optList.put(name,String.valueOf(value));
    }

    public String  getStr(String name){
        return optList.get(name);
    }
    public int     getInt(String name){
        return   Integer.parseInt(optList.get(name));
        }
    public boolean getBoolean(String name){return Boolean.parseBoolean(optList.get(name)); }

    /**
     * This method generate random key
     * @param lengthOfKey
     * @return
     */
    public String getRandomKey(int lengthOfKey){

       if (lengthOfKey<3) lengthOfKey = 3;
       Random rnd = new Random();
       StringBuilder sb1=new StringBuilder("");
       for (int i=0;i<lengthOfKey;i++) sb1.append((char)('a'+rnd.nextInt('z'-'a')));
       return sb1.toString();
       }//getRandomKey

    private String rootKey;

    public String getCurrentRootKey(){
        return this.rootKey;
        }

    /**
     * This method compares rootPassword and input passsord
     * @param inpPassword
     * @return  rootKey (new created key for root)
     * @return  "none" if password not equals
     */
    public String getNewRootKey(String inpPassword){
        String rootPassword = getStr("rootPassword");
        if (rootPassword.equals(inpPassword)) {
                    this.rootKey = getRandomKey(10);
                    return this.rootKey;
                    }
        return "none";
        }//rootPasswordEquals





    private void fieldToProperties(){

        this.applicationPath =     getStr("applicationPath");
        this.imageResultatFolder = getStr("imageResultatFolder");
        this.videoResultatFolder = getStr("videoResultatFolder");

        this.imageHeight =  getInt("imageHeight");
        this.imageWidth =   getInt("imageWidth");
        this.antialiasing = getInt("antialiasing");
        this.fps = getInt("fps");

        this.imageInBufferCount = getInt("imageInBuffer");

        this.lineLifeTime =   getInt("lineLifeTime");
        this.clientLifeTime = getInt("clientLifeTime");
        this.clientClearTime = getInt("clientClearTime");

        }//fields to properties




    private void reLoad(){

        //Read options from file
        String appPath = System.getProperty("user.dir");


        //add properties ApplicationPath
        this.set("applicationPath",appPath);

        File fileConfig = new File(appPath+ File.separator+"config.ini");

        System.out.println(appPath+ File.separator+"config.ini");


        try {
            Scanner scanner = new Scanner(fileConfig);

            String keyStr;//key of property
            String valStr;//value of property
            String lineString;
            //построчно считываем файл
            scanner.useDelimiter(System.getProperty("line.separator"));
            while (scanner.hasNext()) {

                lineString = scanner.next();
                int limitIndex = lineString.indexOf("=");

                keyStr  = lineString.substring(0,limitIndex);
                valStr  = lineString.substring(limitIndex+1);

                this.set(keyStr,valStr);
                }//While file not end

            scanner.close();
        } catch (IOException e) {
            //MyLogger.getLogger().log(LogState.ERROR,"ERROR CONFIG "+e.getMessage());
            e.printStackTrace();
            }

        //MyLogger.getLogger().log(LogState.DEBUG,"Config file is reading");


        }


    //===========SYSTEM_METHODS==============================================
    private static CalcOptions singleOptions;


    /**
     * This method release resetOptions
     */
    public static void setNull(){ singleOptions = null; }

    public static CalcOptions getOptions(){
        if (singleOptions==null) singleOptions = new CalcOptions();
        return singleOptions;
        }//getOptions


    private CalcOptions (){
        optList = new ConcurrentHashMap<String,String>();
        this.reLoad();//read fields from file

        this.fieldToProperties();//set properties from hashMap
        this.rootKey = getRandomKey(10);
        }//constructor

    }//CalcOptions
