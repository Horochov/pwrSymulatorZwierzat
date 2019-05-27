package PwrGame.Animals;

import PwrGame.Position;
import javax.swing.*;
import java.awt.*;

public class Hare extends Animal
{
    public Hare(Position position, byte textureSize, int health, int age, int speed, int damage, int hunger)
    {
        super(position, textureSize, health, age, speed, damage, hunger);
    }

    public void setTextures()
    {
        try
        {
            textures = new Image[]{
                         new ImageIcon(getClass().getClassLoader().getResource("animals/hare.png")).getImage(),
                         //new ImageIcon(getClass().getClassLoader().getResource("animals/hare_lying.png")).getImage(),
                         //new ImageIcon(getClass().getClassLoader().getResource("animals/hare_sleeping.png")).getImage(),
            };
        }
        catch (Exception e)
        {
            System.out.println("Failed to load hare texture(s)");
            System.exit(5);
        }
    }

    public void display(Graphics g)
    {
        g.drawImage( textures[0],position.getX(),position.getY(),textureSize,textureSize,null);
    }

    @Override
    protected void idle()
    {
        idling = true;
    }

    @Override
    protected void sleep()
    {
        sleeping = true;
    }

    //protected void unInPanic();
    //protected void eat();
    //protected void searchForFood();




}
