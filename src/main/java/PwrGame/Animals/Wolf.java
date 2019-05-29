package PwrGame.Animals;

import PwrGame.Position;
import PwrGame.Terrain.HareCorpse;
import PwrGame.Terrain.Tile;
import PwrGame.Terrain.Water;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Wolf extends Animal
{
    public Wolf(Position position, byte textureSize, int health, int age, int speed, int damage, int hunger, int thirst, int fatigue, int lust, int maxLust, int maxHealth)
    {
        super(position, textureSize, health, age, speed, damage, hunger, thirst, fatigue, lust, maxLust, maxHealth);
    }

    public void setTextures()
    {
        try
        {
            textures = new Image[]{
                         new ImageIcon(getClass().getClassLoader().getResource("animals/wolf.png")).getImage(),
                         new ImageIcon(getClass().getClassLoader().getResource("animals/wolf_reverse.png")).getImage(),
                         new ImageIcon(getClass().getClassLoader().getResource("animals/wolf_lying.png")).getImage(),
                         new ImageIcon(getClass().getClassLoader().getResource("animals/wolf_walking1.png")).getImage(),
                         new ImageIcon(getClass().getClassLoader().getResource("animals/wolf_walking2.png")).getImage(),
                         new ImageIcon(getClass().getClassLoader().getResource("animals/wolf_walking3.png")).getImage(),
                         new ImageIcon(getClass().getClassLoader().getResource("animals/wolf_walking1_reverse.png")).getImage(),
                         new ImageIcon(getClass().getClassLoader().getResource("animals/wolf_walking2_reverse.png")).getImage(),
                         new ImageIcon(getClass().getClassLoader().getResource("animals/wolf_walking3_reverse.png")).getImage(),
                         new ImageIcon(getClass().getClassLoader().getResource("animals/wolf_drinking.png")).getImage(),
                         new ImageIcon(getClass().getClassLoader().getResource("animals/wolf_drinking_reverse.png")).getImage(),
                         new ImageIcon(getClass().getClassLoader().getResource("animals/wolf_eating.png")).getImage(),
                         new ImageIcon(getClass().getClassLoader().getResource("animals/wolf_eating_reverse.png")).getImage(),
                    new ImageIcon(getClass().getClassLoader().getResource("animals/wolf_sleeping.png")).getImage(),
                    new ImageIcon(getClass().getClassLoader().getResource("animals/wolf_attacking.png")).getImage(),
                    new ImageIcon(getClass().getClassLoader().getResource("animals/wolf_attacking_reverse.png")).getImage()
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
            current=textures[2];
        }
        else if(sleeping)
        {
            current=textures[13];
        }
        else if(eatingRight)
        {
            current = textures[12];
        }
        else if(eatingLeft)
        {
            current = textures[11];
        }
        else if(drinkingRight)
        {
            current = textures[10];
        }
        else if(drinkingLeft)
        {
            current = textures[9];
        }
        else if(attackingLeft)
        {
            current = textures[14];
        }
        else if(attackingRight)
        {
            current = textures[15];
        }
        else if(movement == 1)
        {
            current = textures[3];
        }
        else if(movement == 2)
        {
            current = textures[4];
        }
        else if(movement == 3)
        {
            current = textures[5];
        }
        else if(movement == 4)
        {
            current = textures[6];
        }
        else if(movement == 5)
        {
            current = textures[7];
        }
        else if(movement == 6)
        {
            current = textures[8];
        }
        else if(movement == 7)
        {
            current = textures[6];
        }
        else if(movement == 8)
        {
            current = textures[7];
        }
        else if(movement == 9)
        {
            current = textures[8];
        }
        else if(movement == 10)
        {
            current = textures[3];
        }
        else if(movement == 11)
        {
            current = textures[4];
        }
        else if(movement == 12)
        {
            current = textures[5];
        }
        else if (lookingRight)
        {
            current = textures[1];
        }
        else
        {
            current=textures[0];
        }

        g.drawImage(current,position.getX(),position.getY(),textureSize,textureSize,null);
    }

//    @Override
//    protected void procreate(Vector<Tile> tiles, Vector<Animal> animals)
//    {
//        for(Animal a: animals)
//        {
//            if(a instanceof Wolf)
//            {
//                Position position = a.getPosition();
//                if (a.lust == 0 && a.age > 2000 && (position.equalsLeft(this.position) || position.equalsRight(this.position) || position.equalsUp(this.position) || position.equalsDown(this.position)))
//                {
//                    if(prepareTile(tiles, animals) != null)
//                    {
//                        procreation = true;
//                        animals.add(
//                                new Wolf(prepareTile(tiles, animals), (byte) 40, 200, 8640, 5, 50, 1000, 700, 1000, 200, 200, 20)
//                        );
//                        a.lust = a.maxLust;
//                        a.fatigue -= 200;
//                        break;
//                    }
//                    else
//                        break;
//                }
//            }
//        }
//    }

//    @Override
//    public void process(Vector<Tile> tiles, Vector<Animal> animals)
//    {
//        super.process(tiles, animals);
//        System.out.println("hunger: " + this.hunger + "   " + "thirst" + this.thirst);
//    }

    @Override
    protected void eat(Vector<Tile> tiles)
    {
        for(Tile t : tiles)
        {
            if(t instanceof HareCorpse)
            {
                Position position = t.getPosition();
                if(position.equalsRight(this.position) && t.getResourceCount() > 0)
                {
                    eatingRight = true;
                    eating = true;
                    this.hunger += 500;
                    t.consumeResource();
                    break;
                }
                if(position.equalsLeft(this.position) && t.getResourceCount() > 0)
                {
                    eatingLeft = true;
                    eating = true;
                    this.hunger += 500;
                    t.consumeResource();
                    break;
                }
                if(position.equalsUp(this.position) && t.getResourceCount() > 0)
                {
                    eatingLeft = true;
                    eating = true;
                    this.hunger += 500;
                    t.consumeResource();
                    break;
                }
                if(position.equalsDown(this.position) && t.getResourceCount() > 0)
                {
                    eatingLeft = true;
                    eating = true;
                    this.hunger += 500;
                    t.consumeResource();
                    break;
                }
            }
        }
    }

    //protected void searchForFood();

    @Override
    protected void attack(Vector<Animal> animals)
    {
        for (Animal a : animals)
        {
            if(a instanceof Hare)
            {
                Position position = a.getPosition();
                if (position.equalsRight(this.position))
                {
                    attackingRight = true;
                    a.health -= this.damage;
                    this.fatigue -= 100;
                    attacking = true;
                    break;
                }
                else if (position.equalsLeft(this.position))
                {
                    attackingLeft = true;
                    a.health -= this.damage;
                    this.fatigue -= 100;
                    attacking = true;
                    break;
                }
                else if (position.equalsUp(this.position))
                {
                    attackingLeft = true;
                    a.health -= this.damage;
                    this.fatigue -= 100;
                    attacking = true;
                    break;
                }
                else if (position.equalsDown(this.position))
                {
                    attackingLeft = true;
                    a.health -= this.damage;
                    this.fatigue -= 100;
                    attacking = true;
                    break;
                }
                attackingLeft = false;
                attackingRight = false;
                attacking = false;
            }
        }
    }
}
