import packages.ui.Button;

/**
 * Write a description of class MainMenuButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainMenuButton extends Button
{
    public MainMenuButton() {
        super("MainMenu");
    }

    @Override
    public void onClick() {
        onClick = new MainMenu();
    }
}
