package PwrGame.Animals;

import PwrGame.Position;
import PwrGame.Terrain.Tile;

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

//    protected void move(Vector<Tile> tiles)
//    {
//        Random r = new Random();
//        switch (r.nextInt(4))
//        {
//            case 0:
//                for (Tile t : tiles)
//                {
//                    Position position = t.getPosition();
//                    if (position.equalsRight(this.position))
//                    {
//                        if (t.isAccessible())
//                       {
//                           this.position.modifyX(40);
//
//                           break;
//                       }
//                    }
//                }
//                break;
//            case 1:
//                for (Tile t : tiles)
//                {
//                    Position position = t.getPosition();
//                    if (position.equalsLeft(this.position))
//                    {
//                        if (t.isAccessible())
//                        {
//                            this.position.modifyX(-40);
//                            break;
//                        }
//                    }
//                }
//                break;
//            case 2:
//                for (Tile t : tiles)
//                {
//                    Position position = t.getPosition();
//                    if (position.equalsUp(this.position))
//                    {
//                        if (t.isAccessible())
//                        {
//                            this.position.modifyY(-40);
//                            break;
//                        }
//                    }
//                }
//                break;
//            case 3:
//                for (Tile t : tiles)
//                {
//                    Position position = t.getPosition();
//                    if (position.equalsDown(this.position))
//                    {
//                        if (t.isAccessible())
//                        {
//                            this.position.modifyY(40);
//                            break;
//                        }
//                    }
//                }
//                break;
//        }
//    }

    public int checker = 0;
    protected void move(Vector<Tile> tiles)
    {
        Random r = new Random();
        if(movement == 1) {
            for (Tile t : tiles) {
                Position position = t.getPosition();
                if (position.equalsLeft(this.position)) {
                    if (t.isAccessible()) {
                        System.out.println("accessibleLEFT");
                        System.out.println(checker);
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
                              System.out.println("accessibleUP");
                              System.out.println(checker);
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
                        System.out.println("accessibleRIGHT");
                        System.out.println(checker);
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
                        System.out.println("accessibleDOWN");
                        System.out.println(checker);
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
            isAlive = false;
        }
    }


    //protected void updateStatus();
    public abstract void display(Graphics g);
    //protected boolean isAlive();
    //protected void process();
    //protected void procreate();
    //protected abstract void drink();
    protected abstract void eat(Vector<Tile> tiles);
    //protected void lookAround();

    protected void drink(Vector<Tile> tiles)
    {
        for(Tile t : tiles)
        {
            if(t.getResourceType() == Tile.ResourceType.water)
            {
                Position position = t.getPosition();
                if(position.equalsRight(this.position))
                {
                    drinkingRight = true;
                }
                if(position.equalsLeft(this.position))
                {
                    drinkingLeft = true;
                }
            }
        }
    }



    private int time = 0;
    protected int movement = 0;
    public void process(Vector<Tile> tiles, Vector<Animal> animals)
    {
        Random r = new Random();
        if(movement != 0) {
            time += 3;
            if(time == 9) {
                if (movement == 2) {
                    this.position.modifyX(-15);
                    movement = 3;
                } else if (movement == 3) {
                    this.position.modifyX(-10);
                    movement = 0;
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
                //System.out.println("movement jest 0");
            time++;
            if (time == 9) {
                if (idling) {
                    switch (r.nextInt(2)) {
                        case 0:
                            idling = false;
                            break;
                        case 1:
                            break;
                    }
                } else if (this.thirst < 40) {
                    drink(tiles);
                } else if (this.hunger < 40) {
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