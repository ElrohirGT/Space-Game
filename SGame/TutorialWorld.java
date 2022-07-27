import greenfoot.*;
import packages.ui.UIWorld;
import packages.ui.Button;
import packages.ui.Text;

/**
 * Write a description of class TutorialWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TutorialWorld extends UIWorld
{
    public static final int WORLD_WIDTH = 1024;
    public static final int WORLD_HEIGHT = 720;

    public TutorialWorld() {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);

        setBackground("space1.jpg");

        Text title = new Text("Bienvenido!", 60, Color.RED);
        add(title, 1f/2, 3f/10);
        
        String tutorial = "Hola Superviviente! Te enseñaré cómo jugar, primero te mueves con WASD como en la mayoría de los juegos actuales.\nPara disparar tu nave presiona espacio. El objetivo es sobrevivir la mayor cantidad de tiempo posible \ny es en base a esto que te darán la mayor cantidad de puntos.\n Al matar enemigos tienes una probabilidad de obtener un power up de color amarillo, junta una cierta\ncantidad de estos y podrás activar tu definitiva (presionando Q). Tienes 3 vidas, suerte!";
        add(new Text(tutorial, 24, Color.YELLOW), 1f/2, 1f/2);

        add(new MainMenuButton(), 1f/2, 9f/10);
    }

}
