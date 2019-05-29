package PwrGame.Animals;

import PwrGame.Position;
import PwrGame.Terrain.Tile;
import PwrGame.Terrain.Water;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

public abstract class Animal implements IAnimal
{
    protected Image[] textures;
    protected Position position;

    protected boolean sleeping = false;
    protected boolean idling = false;
    protected boolean eatingRight = false;
    protected boolean eatingLeft = false;
    protected boolean drinkingRight = false;
    protected boolean drinkingLeft = false;
    protected boolean lookingRight = false;
    private boolean isAlive = true;
    protected int health;
    protected int age;
    protected int speed;
    protected int damage;
    protected int hunger;
    protected int thirst;
    protected byte textureSize;

    public Animal(Position position, byte textureSize, int health, int age, int speed, int damage, int hunger, int thirst)
    {
        this.position = position;
        this.health = health;
        this.age = age;
        this.speed = speed;
        this.damage = damage;
        this.hunger = hunger;
        this.thirst = thirst;
        this.textureSize=textureSize;

        setTextures();
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }


    protected void move(Vector<Tile> tiles)
    {
        Random r = new Random();
        if(movement == 1) {
            for (Tile t : tiles) {
                Position position = t.getPosition();
                if (position.equalsLeft(this.position)) {
                    if (t.isAccessible()) {
                        this.position.modifyX(-15);
                        movement = 2;
                        break;
                    }
                    movement = 0;
                }
                movement = 0;
            }
        }
            else if(movement == 4)
            {
                 for (Tile t : tiles)
                 {
                     Position position = t.getPosition();
                     if (position.equalsUp(this.position))
                     {
                         if (t.isAccessible())
                          {
                              this.position.modifyY(-15);
                              movement = 5;
                              break;
                          }
                         movement = 0;
                     }
                     movement = 0;
                 }
            }
        else if(movement == 7)
        {
            for (Tile t : tiles)
            {
                Position position = t.getPosition();
                if (position.equalsRight(this.position))
                {
                    if (t.isAccessible())
                    {
                        this.position.modifyX(15);
                        movement = 8;
                        break;
                    }
                    movement = 0;
                }
                movement = 0;
            }
        }
        else if(movement == 10)
        {
            for (Tile t : tiles)
            {
                Position position = t.getPosition();
                if (position.equalsDown(this.position))
                {
                    if (t.isAccessible())
                    {
                        this.position.modifyY(15);
                        movement = 11;
                        break;
                    }
                    movement = 0;
                }
                movement = 0;
            }
        }
    }


    protected abstract void setTextures();
    //protected abstract void searchForFood();
    protected void starve()
    {
        this.health -=1;
        if(this.health == 0)
        {
            this.isAlive = false;
        }
    }


    //protected void updateStatus();
    public abstract void display(Graphics g);
    //protected void procreate();

    protected abstract void eat(Vector<Tile> tiles);
    //protected void lookAround();

    protected void drink(Vector<Tile> tiles)
    {
        for(Tile t : tiles)
        {
            if(t instanceof Water)
            {
                Position position = t.getPosition();
                if(this.thirst < 300 && position.equalsRight(this.position))
                {
                    drinkingRight = true;
                    drinking = true;
                    this.thirst += 300;
                    break;
                }
                if(this.thirst < 300 && position.equalsLeft(this.position))
                {
                    drinkingLeft = true;
                    drinking = true;
                    this.thirst += 300;
                    break;
                }
            }
        }
    }


    private int time = 0;
    protected int movement = 0;
    private boolean drinking = false;
    protected boolean eating = false;
    public void process(Vector<Tile> tiles, Vector<Animal> animals)
    {
        Random r = new Random();
        this.thirst -= 1;
        this.hunger -= 1;


        if(this.thirst < 0)
        {
            starve();
        }

        if(movement != 0) {
            time += 3;
            if(time == 9) {
                if (movement == 2) {
                    this.position.modifyX(-15);
                    movement = 3;
                } else if (movement == 3) {
                    this.position.modifyX(-10);
                    movement = 0;
                    lookingRight = false;
                } else if (movement == 5) {
                    this.position.modifyY(-15);
                    movement = 6;
                } else if (movement == 6) {
                    this.position.modifyY(-10);
                    movement = 0;
                } else if (movement == 8) {
                    this.position.modifyX(15);
                    movement = 9;
                } else if (movement == 9) {
                    this.position.modifyX(10);
                    movement = 0;
                    lookingRight = true;
                } else if (movement == 11) {
                    this.position.modifyY(15);
                    movement = 12;
                } else if (movement == 12) {
                    this.position.modifyY(10);
                    movement = 0;
                }
                time = 0;
            }
        }
        else
            {
            time++;
            if (time == 9) {

                drink(tiles);

                if (idling) {
                    switch (r.nextInt(2)) {
                        case 0:
                            idling = false;
                            break;
                        case 1:
                            break;
                    }
                }
                else if(drinking)
                {
                    if(this.thirst < 600)
                        drink(tiles);
                    else {
                        drinking = false;
                        drinkingLeft = false;
                        drinkingRight = false;
                    }
                } else if (this.hunger < 300) {
                    eat(tiles);
                } else {
                    switch (r.nextInt(5)) {
                        case 0:
                            movement = 1;
                            move(tiles);
                            break;
                        case 1:
                            movement = 4;
                            move(tiles);
                            break;
                        case 2:
                            movement = 7;
                            move(tiles);
                            break;
                        case 3:
                            movement = 10;
                            move(tiles);
                            break;
                        case 4:
                            idling = true;
                            break;
                    }
                }
                time = 0;
            }
        }
    }

    public Position getPosition()
    {
        return position;
    }
}
