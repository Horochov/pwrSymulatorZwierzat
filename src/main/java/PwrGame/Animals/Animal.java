package PwrGame.Animals;

import PwrGame.Position;
import PwrGame.Terrain.TerrainHandler;
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
    private boolean drinking = false;
    protected boolean eating = false;
    protected boolean attacking = false;
    protected boolean procreation = false;
    protected boolean eatingRight = false;
    protected boolean eatingLeft = false;
    protected boolean drinkingRight = false;
    protected boolean drinkingLeft = false;
    protected boolean lookingRight = false;
    protected boolean attackingLeft = false;
    protected boolean attackingRight = false;
    protected Vector<Tile> possiblyOccupied;
    private boolean isAlive = true;
    protected int health;
    protected int age;
    protected int speed;
    protected int damage;
    protected int hunger;
    protected int thirst;
    protected int fatigue;
    protected int lust;
    protected int maxLust;
    protected int maxHealth;
    private int deathPossibility;
    protected byte textureSize;

    public Animal(Position position, byte textureSize, int health, int age, int speed, int damage, int hunger, int thirst, int fatigue, int lust, int maxLust, int maxHealth)
    {
        this.position = position;
        this.health = health;
        this.age = age;
        this.speed = speed;
        this.damage = damage;
        this.hunger = hunger;
        this.thirst = thirst;
        this.textureSize=textureSize;
        this.fatigue = fatigue;
        this.lust = lust;
        this.maxLust = maxLust;
        this.maxHealth = maxHealth;

        setTextures();
    }

    protected Position prepareTile(Vector<Tile> tiles, Vector<Animal> animals)
    {
        Random r = new Random();
        possiblyOccupied = TerrainHandler.limitViewSquare(tiles, this.position, 40);
        possiblyOccupied.removeIf(tile -> !tile.isAccessible());
        for(Animal a : animals)
        {
            for(int i = 0 ; i < possiblyOccupied.size() ; i++)
            {
                if(possiblyOccupied.elementAt(i).getPosition().equals(a.getPosition()))
                {
                    possiblyOccupied.remove(i--);
                }
            }

        }
        if(possiblyOccupied.size() > 0)
        {
            return possiblyOccupied.elementAt(r.nextInt(possiblyOccupied.size())).getPosition();
        }
        else
            return null;
    }

    @Override
    public boolean isAlive() {
        return health>0;
    }


    protected void move(Vector<Tile> possiblyOccupied)
    {
        Random r = new Random();
        if(movement == 1)
        {
            for (int i = 0 ; i < possiblyOccupied.size() ; i++) {
//                Position position = t.getPosition();
                if (possiblyOccupied.elementAt(i).getPosition().equalsLeft(this.position))
                {
                    if (possiblyOccupied.elementAt(i).isAccessible()) {
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
                 for (int i = 0 ; i < possiblyOccupied.size() ; i++)
                 {
//                     Position position = t.getPosition();
                     if (possiblyOccupied.elementAt(i).getPosition().equalsUp(this.position))
                     {
                         if (possiblyOccupied.elementAt(i).isAccessible())
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
            for (int i = 0 ; i < possiblyOccupied.size() ; i++)
            {
//                Position position = t.getPosition();
                if (possiblyOccupied.elementAt(i).getPosition().equalsRight(this.position))
                {
                    if (possiblyOccupied.elementAt(i).isAccessible())
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
            for (int i = 0 ; i < possiblyOccupied.size() ; i++)
            {
//                Position position = t.getPosition();
                if (possiblyOccupied.elementAt(i).getPosition().equalsDown(this.position))
                {
                    if (possiblyOccupied.elementAt(i).isAccessible())
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
    }

    protected  void sleep()
    {
        sleeping = true;
        this.fatigue += 300;
    }

    protected void gettingTired()
    {
        Random r = new Random();
        switch(r.nextInt(2))
        {
            case 0:
                sleep();
                break;
            case 1:
            {
                break;
            }
        }
    }

    public abstract void display(Graphics g);

    protected abstract void eat(Vector<Tile> tiles);

//    protected abstract void procreate(Vector<Tile> tiles, Vector<Animal> animals);

    //protected void lookAround();

//    private int x = 0;
//    private int y = 0;
//    protected Position searchForWater(Vector<Tile> tiles)
//    {
//        Vector<Tile> tile = TerrainHandler.limitViewSquare(tiles, this.position, 119);
//        for(Tile t : tile)
//        {
//            if(t instanceof Water)
//            {
//                return t.getPosition();
//            }
//        }
//        return null;
//    }
//
//    protected void goForWater(Position pos)
//    {
//        if(this.searchForWater())
//        {
//
//        }
//    }

    protected void drink(Vector<Tile> tiles)
    {
        for(Tile t : tiles)
        {
            if(t instanceof Water)
            {
                Position position = t.getPosition();
                if(position.equalsRight(this.position))
                {
                    drinkingRight = true;
                    drinking = true;
                    this.thirst += 300;
                    break;
                }
                if(position.equalsLeft(this.position))
                {
                    drinkingLeft = true;
                    drinking = true;
                    this.thirst += 300;
                    break;
                }
                if(position.equalsUp(this.position))
                {
                    drinkingLeft = true;
                    drinking = true;
                    this.thirst += 300;
                    break;
                }
                if(position.equalsDown(this.position))
                {
                    drinkingLeft = true;
                    drinking = true;
                    this.thirst += 300;
                    break;
                }
            }
        }
    }

    protected abstract void attack(Vector<Animal> animals);


    private int time = 0;
    protected int movement = 0;
    public void process(Vector<Tile> tiles, Vector<Animal> animals)
    {
        Random r = new Random();
        this.thirst -= 1;
        this.hunger -= 1;
        this.fatigue -= 1;
        this.age -= 1;


        if(this.thirst < 0)
        {
            starve();
            this.lust = this.maxLust;
        }

        if(this.hunger < 0)
        {
            starve();
            this.lust = this.maxLust;
        }

        if(this.fatigue < 0)
        {
            sleep();
            this.lust = this.maxLust;
        }

        if(this. health < 1)
        {
            this.isAlive = false;
        }

        if(this.age < 0)
        {
            deathPossibility = r.nextInt(8);
            if(deathPossibility == 8) {this.isAlive = false;}
        }

        time++;

        if(movement != 0) {
            if(time == 3 || time == 6 || time == 9) {
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
            if ((this.age > 2000 && time == 9) || (this.age < 2000 && time == 18)) {


                if(attacking)
                {
                    attacking = false;
                    attackingLeft = false;
                    attackingRight = false;
                }

//                if(procreation)
//                {
//                    this.lust = this.maxLust;
//                    this.fatigue -= 200;
//                    procreation = false;
//                }

                if(this.lust >= 0)
                {
                    this.lust -= 5;
                }

                if(this.thirst < 300)
                {
                    drink(tiles);
                }

                if(this.hunger < 500)
                {
                    this.eat(tiles);
                }

                if(this.fatigue < 400)
                {
                    gettingTired();
                }

                if(this.hunger < 600)
                {
                    attack(animals);
                }

//                if(/*this.age > 2000 && */this.lust <= 0 )
//                {
//                    procreate(tiles, animals);
//                }
                if(sleeping)
                {
                    if(this.fatigue < 700)
                    {
                        sleep();
                    }
                    else
                    {
                        switch(r.nextInt(2))
                        {
                            case 0:
                                sleeping = false;
                            case 1:
                                break;
                        }
                    }
                }
                else if (idling) {
                    this.fatigue += 100;
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
                }
                else if(eating) {
                    if (this.hunger < 700)
                        this.eat(tiles);
                    else {
                        this.fatigue -= 100;
                        this.lust -= 5;
                        eating = false;
                        eatingLeft = false;
                        eatingRight = false;
                    }
                }
                else {
                    possiblyOccupied = TerrainHandler.limitViewSquare(tiles, this.position, 39);
                    for(Animal a : animals)
                    {
                        for(int i = 0 ; i < possiblyOccupied.size() ; i++)
                        {
                            if(possiblyOccupied.elementAt(i).getPosition().equals(a.getPosition()))
                            {
                                possiblyOccupied.remove(i);
                            }
                        }
                    }
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
                            if (this.hunger > 700 && this.thirst > 600)
                            {
                                idling = true;
                            }
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
