import PwrGame.Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class Main
{

	private JFrame frame;
	private Game game;
	private Timer timer;
	private boolean isPaused=true;

	public Main()
	{
		timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run()
			{
				if(!isPaused)
					game.process();
				game.display();
				frame.repaint();
			}
		};

		game = new Game((byte) 30,(byte) 20,(byte) 40,(byte) 10,(byte) 10);

		frame = new JFrame();
		frame.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		c.gridy=0;
		c.gridheight=GridBagConstraints.PAGE_END;
		frame.add(game.getPanel(),c);

		/*
		 *  SETTINGS PANEL
		 */

		c.gridx=1;
		c.gridheight=1;
		JButton generate = new JButton("Generuj nową symulację");
		generate.addActionListener(new Action() {
			@Override
			public Object getValue(String key)
			{
				return null;
			}

			@Override
			public void putValue(String key, Object value)
			{

			}

			@Override
			public void setEnabled(boolean b)
			{

			}

			@Override
			public boolean isEnabled()
			{
				return false;
			}

			@Override
			public void addPropertyChangeListener(PropertyChangeListener listener)
			{

			}

			@Override
			public void removePropertyChangeListener(PropertyChangeListener listener)
			{

			}

			@Override
			public void actionPerformed(ActionEvent e)
			{
				game = new Game((byte) 30,(byte) 20,(byte) 40,(byte) 10,(byte) 10);
				c.fill=GridBagConstraints.HORIZONTAL;
				c.gridx=0;
				c.gridy=0;
				c.gridheight=GridBagConstraints.PAGE_END;

				frame.add(game.getPanel(),c);
				frame.pack();
				frame.repaint();
			}
		});
		c.gridx=1;
		c.gridheight=1;
		frame.add(generate,c);

		c.gridy=1;
		JButton pause = new JButton("Start");
		pause.addActionListener(new Action() {
			@Override
			public Object getValue(String key)
			{
				return null;
			}

			@Override
			public void putValue(String key, Object value)
			{

			}

			@Override
			public void setEnabled(boolean b)
			{

			}

			@Override
			public boolean isEnabled()
			{
				return false;
			}

			@Override
			public void addPropertyChangeListener(PropertyChangeListener listener)
			{

			}

			@Override
			public void removePropertyChangeListener(PropertyChangeListener listener)
			{

			}

			@Override
			public void actionPerformed(ActionEvent e)
			{

				isPaused=!isPaused;
				pause.setText(isPaused?"Wznów":"Pauza");
			}
		});
		frame.add(pause,c);

//		c.gridy=2;
//		JSlider tps = new JSlider();
//		tps.setMaximum(5000);
//		tps.setMinimum(1000/60);
//		tps.addChangeListener(new ChangeListener() {
//			@Override
//			public void stateChanged(ChangeEvent e)
//			{
//				timer.cancel();
//				timer.scheduleAtFixedRate(timerTask,0,tps.getValue());
//			}
//		});
//		frame.add(tps,c);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Symulator przetrwania zwierzat");
		frame.setLocationByPlatform(true);
		frame.pack();
		frame.setVisible(true);

		//Probably the worst way to do this.
		timer.scheduleAtFixedRate(timerTask,0,1000/1);


	}
	public static void main(String[] args)
	{
		new Main();
	}
}
