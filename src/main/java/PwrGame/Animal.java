package PwrGame;

import PwrGame.GridMaker.AnimalStats;
import PwrGame.GridMaker.IAnimal;
import java.util.Random;

import java.awt.*;

public abstract class Animal implements IAnimal
{
    private Image[] texture;
    protected int posX;
    protected int posXnew;
    protected int posY;
    protected int posYnew;
    public Grid parent;


//TODO: include accessibility
    protected void move(){
        Random r = new Random();
        int x = r.nextInt(5);
        switch(x){
            case 0:
                break;
            case 1:
                posXnew = posX + 40;
                break;
            case 2:
                posXnew = posX - 40;
                break;
            case 3:
                posYnew = posY + 40;
                break;
            case 4:
                posYnew = posY - 40;
                break;
        }
        parent.tile(posXnew,posYnew).setAnimal(this);
        parent.tile(posX,posY).setAnimal(null);
    };
    //protected abstract void searchForFood();
    //protected abstract void eat();
    protected void starve()
    {

    }
    protected abstract void idle();
    //protected void updateStatus();
    protected abstract void sleep();
    public abstract Image getTexture();
    //protected boolean isAlive();
    //protected void process();
    //protected void procreate();
    //protected void drink();
    //protected void lookAround();
}
