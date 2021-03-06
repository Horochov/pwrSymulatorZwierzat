package PwrGame.Animals;

import PwrGame.Position;
import PwrGame.Terrain.Grass;
import PwrGame.Terrain.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Hare extends Animal
{
    public Hare(Position position, byte textureSize, int health, int age, int speed, int damage, int hunger, int thirst, int fatigue, int lust, int maxLust, int maxHealth)
    {
        super(position, textureSize, health, age, speed, damage, hunger, thirst, fatigue, lust, maxLust, maxHealth);
    }

    public void setTextures()
    {
        try
        {
            textures = new Image[]{
                        new ImageIcon(getClass().getClassLoader().getResource("animals/hare.png")).getImage(),
                        new ImageIcon(getClass().getClassLoader().getResource("animals/hare_reverse.png")).getImage(),
                        new ImageIcon(getClass().getClassLoader().getResource("animals/hare_lying.png")).getImage(),
                        new ImageIcon(getClass().getClassLoader().getResource("animals/hare_moving1.png")).getImage(),
                        new ImageIcon(getClass().getClassLoader().getResource("animals/hare_moving2.png")).getImage(),
                        new ImageIcon(getClass().getClassLoader().getResource("animals/hare_moving3.png")).getImage(),
                        new ImageIcon(getClass().getClassLoader().getResource("animals/hare_moving1_reverse.png")).getImage(),
                        new ImageIcon(getClass().getClassLoader().getResource("animals/hare_moving2_reverse.png")).getImage(),
                        new ImageIcon(getClass().getClassLoader().getResource("animals/hare_moving3_reverse.png")).getImage(),
                         new ImageIcon(getClass().getClassLoader().getResource("animals/hare_drinking.png")).getImage(),
                         new ImageIcon(getClass().getClassLoader().getResource("animals/hare_drinking_reverse.png")).getImage(),
                         new ImageIcon(getClass().getClassLoader().getResource("animals/hare_eating1.png")).getImage(),
                         new ImageIcon(getClass().getClassLoader().getResource("animals/hare_eating1_reverse.png")).getImage(),
                         new ImageIcon(getClass().getClassLoader().getResource("animals/hare_sleeping.png")).getImage()
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
        else if(lookingRight)
        {
            current = textures[1];
        }
        else
        {
            current=textures[0];
        }

        g.drawImage(current,position.getX(),position.getY(),textureSize,textureSize,null);
    }

    @Override
    protected void attack(Vector<Animal> animals) {

    }

    protected void runInPanic()
    {
        this.speed += 3;
    }

//    @Override
//    protected void procreate(Vector<Tile> tiles, Vector<Animal> animals)
//    {
//        boolean success = false;
//        for(Animal a: animals)
//        {
//            if(a instanceof Hare)
//            {
//                Position position = a.getPosition();
//                if (a.lust <= 0 /*&& a.age > 2000 */&& (position.equalsLeft(this.position) || position.equalsRight(this.position) || position.equalsUp(this.position) || position.equalsDown(this.position)))
//                {
//                    if(prepareTile(tiles, animals) != null)
//                    {
//                        System.out.println("nie jest null");
//                        success = true;
//                        a.lust = a.maxLust;
//                        a.fatigue -= 200;
//                        a.procreation = true;
//                    }
//                    else
//                        System.out.println("no ciagle jest 0 :|");
//                    break;
//                }
//            }
//        }
//        if(success)
//        {
//            System.out.println("dociera se tu");
//            procreation = true;
//            animals.add(
//                    new Hare(prepareTile(tiles, animals), (byte) 40, 100, 7560, 3, 0, 1000, 700, 1000, 50, 50, 10)
//            );
//        }
//    }

    @Override
    protected void eat(Vector<Tile> tiles)
    {
        for(Tile t : tiles)
        {
            if(t instanceof Grass)
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
            }
        }
    }

}
