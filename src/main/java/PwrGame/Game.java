package PwrGame;

import PwrGame.GridMaker.GridMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Game
{
	private final int gridHeight=20;    //vertical (y) count
	private final int gridWidth=30;    //horizontal (x) count
	private final int gridSize=40;

	private Boolean isPaused=true;

	protected JPanel panel;
	protected BufferedImage img;
	protected Graphics imgG;
	protected GridMaker gridMaker;
	Grid grid;

	public Game()
	{
		img=new BufferedImage(gridSize*gridWidth,gridSize*gridHeight, BufferedImage.TYPE_INT_ARGB);
		imgG=img.getGraphics();

		gridMaker =  new GridMaker(gridWidth, gridHeight, gridSize);

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
		//TODO: frame double buffering?


		grid= gridMaker.setRandomSeed().noiseBased(8);

		grid.draw(imgG);
	}

	public JPanel getPanel()
	{
		return panel;
	}

	public void newTerrain()
	{
		grid = gridMaker.setRandomSeed().noiseBased(8);

	}

	public void setPaused(Boolean paused)
	{
		isPaused = paused;
	}

	public Boolean isPaused()
	{
		return isPaused;
	}

	public void process()
	{
		grid.process();
	}
	public void display()
	{

		grid.draw(imgG);
	}
}
