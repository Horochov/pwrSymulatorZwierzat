package PwrGame.GridMaker;

import PwrGame.Animal;
import PwrGame.Grid;

import javax.swing.*;
import java.awt.*;

public class Wolf extends Animal
{
    public Wolf(Grid parent, int health, int age, int speed, int damage, int hunger, int posX, int posY)
    {
//        parent.tile(xnew,ynew).setAnimal(this);
//        parent.tile(x,y).setAnimal(null);
        this.parent=parent;
        this.health = health;
        this.age = age;
        this.speed = speed;
        this.damage = damage;
        this.hunger = hunger;
        this.posX = posX;
        this.posY = posY;

        setTextures();
    }
    /*public Wolf(Image image)
    {
        this.image = image;
    }*/

    public void setTextures()
    {
        try
        {
            wolfImages = new Image[]{
                         new ImageIcon(getClass().getClassLoader().getResource("animals/wolf.png")).getImage(),
                         new ImageIcon(getClass().getClassLoader().getResource("animals/wolf_lying.png")).getImage(),
                         new ImageIcon(getClass().getClassLoader().getResource("animals/wolf_sleeping.png")).getImage(),
            };
        }
        catch (Exception e)
        {
            System.out.println("Failed to load wolf texture(s)");
        }
    }

    public Image getTexture()
    {
        if(idling)
        {
            return wolfImages[1];
        }
        else if(sleeping)
        {
            return wolfImages[2];
        }
        else
        {
            return wolfImages[0];
        }
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
    Grid parent;
    Image[] wolfImages;
    protected boolean sleeping = false;
    protected boolean idling = false;
    protected int health;
    protected int age;
    protected int speed;
    protected int damage;
    protected int hunger;
    protected int posX;
    protected int posY;
}
