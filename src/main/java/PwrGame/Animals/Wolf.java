package PwrGame.Animals;

import PwrGame.Position;

import javax.swing.*;
import java.awt.*;

public class Wolf extends Animal
{
    public Wolf(Position position, byte textureSize, int health, int age, int speed, int damage, int hunger)
    {
        super(position, textureSize, health, age, speed, damage, hunger);
    }

    public void setTextures()
    {
        try
        {
            textures = new Image[]{
                         new ImageIcon(getClass().getClassLoader().getResource("animals/wolf.png")).getImage(),
                         new ImageIcon(getClass().getClassLoader().getResource("animals/wolf_lying.png")).getImage(),
                         new ImageIcon(getClass().getClassLoader().getResource("animals/wolf_sleeping.png")).getImage(),
            };
        }
        catch (Exception e)
        {
            System.out.println("Failed to load wolf texture(s)");
            System.exit(5);
        }
    }

    public void display(Graphics g)
    {
        Image current;
        if(idling)
        {
            current=textures[1];
        }
        else if(sleeping)
        {
            current=textures[2];
        }
        else
        {
            current=textures[0];
        }

        g.drawImage(current,position.getX(),position.getY(),textureSize,textureSize,null);
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

    //protected void eat();
    //protected void searchForFood();
    //protected void attack();
}
