package packages.ui;

import greenfoot.*;
import java.util.HashMap;
public class UIWorld extends World{
    private HashMap<Actor, double[]> elements;
    private packages.tools.Time time;
    public UIWorld(int x, int y, int cellSize){
        super(x, y, cellSize);
        elements = new HashMap<Actor, double[]>();

        time = new packages.tools.Time();
        addObject(time, 0, 0);
    }
    
    public UIWorld(int x, int y, int cellSize, boolean bound){
        super(x, y, cellSize, bound);
        elements = new HashMap<Actor, double[]>();

        time = new packages.tools.Time();
        addObject(time, 0, 0);
    }
    
    public void add(Actor element, double x, double y){
        if(element == null) return;
        double[] loc = {x, y};
        elements.put(element, loc);
        addObject(
            element,
            (int)(getWidth() * elements.get(element)[0]),
            (int)(getHeight() * elements.get(element)[1])
        );
    }
    
    public void move(Actor element, double x, double y){
        if(element != null && elements.keySet().contains(element)){
            add(element, x, y);
        }
    }

    public double deltaTime(){
        return time.deltaTime();
    }
    public int fps(){
        return time.fps();
    }
    public int stableFps(){
        return time.stableFps();
    }
}