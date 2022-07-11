package packages.tools;

public class Time extends greenfoot.Actor{
    int lastMillis;
    double deltaTime;
    long frameIndex = 0;

    double timeSinceFpsUpdate = 0;
    int frameNum;
    int frameCount;
    int stableFps;

    private static final double DETAIL = 1000;
    public Time(){
        setImage(new greenfoot.GreenfootImage(1, 1));
    }
    public void act(){
        int currentMillis = (int)(System.currentTimeMillis() % (int)DETAIL);
        if(currentMillis - lastMillis < 0){
            deltaTime = (currentMillis - lastMillis + DETAIL) / DETAIL;
        }
        else{
            deltaTime = (currentMillis - lastMillis) / DETAIL;
        }
        lastMillis = currentMillis;


        timeSinceFpsUpdate += deltaTime;
        frameNum++;
        frameCount += fps();
        if(timeSinceFpsUpdate >= 1){
            timeSinceFpsUpdate--;
            stableFps = (int)(frameCount / (double)frameNum);
            frameNum = frameCount = 0;
        }


        frameIndex++;
    }
    
    /**
     * Fraction of time since the last frame
     */
    public double deltaTime(){
        return deltaTime;
    }
    
    /**
     * Updated once per frame
     */
    public int fps(){
        return (int)(1 / deltaTime);
    }
    
    /**
     * Updated once per second
     */
    public int stableFps(){
        return stableFps;
    }
    
    public long frameIndex(){
        return frameIndex;
    }
    
    public void resetFrameIndex(){
        frameIndex = 0;
    }
}