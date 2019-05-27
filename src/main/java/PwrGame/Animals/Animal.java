package PwrGame.Animals;

import PwrGame.Position;

import java.util.Random;

import java.awt.*;

public abstract class Animal implements IAnimal
{
    protected Image[] textures;
    protected Position position;

    protected boolean sleeping = false;
    protected boolean idling = false;
    protected int health;
    protected int age;
    protected int speed;
    protected int damage;
    protected int hunger;
    protected byte textureSize;

    public Animal(Position position, byte textureSize, int health, int age, int speed, int damage, int hunger)
    {
        this.position = position;
        this.health = health;
        this.age = age;
        this.speed = speed;
        this.damage = damage;
        this.hunger = hunger;
        this.textureSize=textureSize;

        setTextures();
    }



//TODO: include accessibility
    protected void move(){
        Random r = new Random();
        switch(r.nextInt(5))
        {
            case 0:
                break;
            case 1:
                position.modifyX(40);
                break;
            case 2:
                position.modifyX(-40);
                break;
            case 3:
                position.modifyY(40);
                break;
            case 4:
                position.modifyY(-40);
                break;
        }
    };


    protected abstract void setTextures();
    //protected abstract void searchForFood();
    //protected abstract void eat();
    protected void starve()
    {

    }
    protected abstract void idle();
    //protected void updateStatus();
    protected abstract void sleep();
    public abstract void display(Graphics g);
    //protected boolean isAlive();
    //protected void process();
    //protected void procreate();
    //protected void drink();
    //protected void lookAround();
    public void process()
    {

    }

    public Position getPosition()
    {
        return position;
    }
}
