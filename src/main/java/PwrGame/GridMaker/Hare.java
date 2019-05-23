package PwrGame.GridMaker;

import PwrGame.Animal;
import PwrGame.Grid;

import javax.swing.*;
import java.awt.*;

public class Hare extends Animal
{
    public Hare(Grid parent, int health, int age, int speed, int damage, int hunger, int posX, int posY)
    {
        this.parent = parent;
        this.health = health;
        this.age = age;
        this.speed = speed;
        this.damage = damage;
        this.hunger = hunger;
        this.posX = posX;
        this.posY = posY;

        setTextures();
    }
    /*public Hare(Image image)
    {
        this.image = image;
    }*/
    public void setTextures()
    {
        try
        {
            hareImages = new Image[]{
                         new ImageIcon(getClass().getClassLoader().getResource("animals/hare.png")).getImage(),
                         //new ImageIcon(getClass().getClassLoader().getResource("animals/hare_lying.png")).getImage(),
                         //new ImageIcon(getClass().getClassLoader().getResource("animals/hare_sleeping.png")).getImage(),
            };
        }
        catch (Exception e)
        {
            System.out.println("Failed to load wolf texture(s)");
        }
    }

    public Image getTexture()
    {
        return hareImages[0];
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
    Grid parent;
    Image[] hareImages;
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
