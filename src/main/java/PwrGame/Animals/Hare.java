package PwrGame.Animals;

import PwrGame.Position;
import PwrGame.Terrain.Grass;
import PwrGame.Terrain.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Hare extends Animal
{
    public Hare(Position position, byte textureSize, int health, int age, int speed, int damage, int hunger, int thirst)
    {
        super(position, textureSize, health, age, speed, damage, hunger, thirst);
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
                         new ImageIcon(getClass().getClassLoader().getResource("animals/hare_drinking_reverse.png")).getImage()
//                         new ImageIcon(getClass().getClassLoader().getResource("animals/hare_sleeping.png")).getImage(),
//                         new ImageIcon(getClass().getClassLoader().getResource("animals/hare_eating1.png")).getImage(),
//                         new ImageIcon(getClass().getClassLoader().getResource("animals/hare_eating1_reverse.png")).getImage(),
//                         new ImageIcon(getClass().getClassLoader().getResource("animals/hare_eating2.png")).getImage(),
//                         new ImageIcon(getClass().getClassLoader().getResource("animals/hare_eating2_reverse.png")).getImage(),
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
//        else if(sleeping)
//        {
//            current=textures[2];
//        }
//        else if(!isAlive())
//        {
//            current = textures[3];
//        }
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


    protected void runInPanic()
    {
        this.speed += 3;
    }

    @Override
    protected void eat(Vector<Tile> tiles)
    {
        for(Tile t : tiles)
        {
            if(t instanceof Grass)
            {
                Position position = t.getPosition();
                if(position.equalsRight(this.position))
                {
                    eatingRight = true;
                }
                if(position.equalsLeft(this.position))
                {
                    eatingLeft = true;
                }
            }
        }
    }
/*    protected void searchForFood(Vector<Tile> tiles, Vector<Hare> animals)
    {
        for(Hare a : animals)
        {
            for(Tile t : tiles)
            {
                Position position = t.getPosition();
                Position positionX = t.getX;
                if(t.getX())
            }
        }
    }*/




}
