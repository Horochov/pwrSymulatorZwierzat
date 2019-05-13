
import Terrain.Grid;
import Terrain.TerrainMaker;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.*;

/*
  Main To Do List (TODO)
	terrain
	animals
	game turns system



 */


public class Main
{

	protected int gridHeight=20;    //vertical (y) count
	protected int gridWidth=30;    //horizontal (x) count
	protected int gridSize=40;


	protected JFrame frame;
	protected JPanel panel;
	protected BufferedImage img;
	protected Graphics imgG;
	protected KeyListener keyListener;
	protected TerrainMaker terrainMaker;
	Grid grid;

	public Main()
	{

		//Prepare framebuffer
		img=new BufferedImage(gridSize*gridWidth,gridSize*gridHeight, BufferedImage.TYPE_INT_ARGB);
		imgG=img.getGraphics();

		terrainMaker =  new TerrainMaker(gridWidth, gridHeight, gridSize);

		panel = new JPanel()
		{
			//Woah, lovely way to override!
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// Creating a copy of the Graphics
				// so any reconfiguration we do on
				// it doesn't interfere with what
				// Swing is doing.
				Graphics2D g2 = (Graphics2D) g.create();
				float scale=Math.min((float)getParent().getWidth()/gridWidth,(float)getParent().getHeight()/gridHeight)/gridSize;
				int w= (int) (img.getWidth()*scale),
						h= (int) (img.getHeight()*scale);
				g2.drawImage(img, 0, 0, w,h, null);
				g2.dispose();
			}
			@Override
			public Dimension getPreferredSize() {
				return new Dimension(img.getWidth(), img.getHeight());
			}

		};
		//TODO: frame double buffering

		keyListener= new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e)
			{

			}

			@Override
			public void keyPressed(KeyEvent e)
			{
//				System.out.println(e.getKeyCode());
				switch (e.getKeyCode())
				{
					case 32:    //space
						terrainMaker.setRandomSeed();
						break;
					case 38:    //up
					case 40:    //down
						terrainMaker.noiseOffset.modifyY(e.getKeyCode()-39 );
						break;
					case 37:
					case 39:
						terrainMaker.noiseOffset.modifyX(e.getKeyCode()-38);
						break;
				}
				grid = terrainMaker.noiseBased(8);
				grid.draw(imgG);
				frame.repaint();


			}

			@Override
			public void keyReleased(KeyEvent e)
			{
//

			}
		};

		frame = new JFrame();
		frame.add(panel);
		frame.addKeyListener(keyListener);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Symulator zabijania zwierzat");
		frame.setLocationByPlatform(true);
		frame.pack();

		frame.setVisible(true);
//		paintGrid(imgG);
		grid= terrainMaker.setRandomSeed().noiseBased(8);

		grid.draw(imgG);
		frame.repaint();



	}
	public static void main(String[] args)
	{
		new Main();

	}
}
