package PwrGame.Animals;

import PwrGame.Position;
import PwrGame.Terrain.Tile;
import PwrGame.Terrain.Water;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Wolf extends Animal
{
    public Wolf(Position position, byte textureSize, int health, int age, int speed, int damage, int hunger, int thirst)
    {
        super(position, textureSize, health, age, speed, damage, hunger, thirst);
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
                         new ImageIcon(getClass().getClassLoader().getResource("animals/wolf_drinking_reverse.png")).getImage()
//                         new ImageIcon(getClass().getClassLoader().getResource("animals/wolf_sleeping.png")).getImage(),
//                         new ImageIcon(getClass().getClassLoader().getResource("animals/wolf_eating.png")).getImage(),
//                         new ImageIcon(getClass().getClassLoader().getResource("animals/wolf_eating_reverse.png")).getImage(),
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
//        else if(sleeping)
//        {
//            current=textures[2];
//        }
//        else if(eatingRight)
//        {
//            current = textures[3];
//        }
//        else if(eatingLeft)
//        {
//            current = textures[3];
//        }
//        else if(!isAlive())
//        {
//            current = textures[4];
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

    @Override
    protected void eat(Vector<Tile> tiles)
    {
        for(Tile t : tiles)
        {
            if(t instanceof Water)
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

    //protected void searchForFood();
    //protected void attack();
}
