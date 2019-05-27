import PwrGame.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.Timer;
import java.util.TimerTask;

public class Main
{

	private Game game;
	private Timer timer;
	private int tps=10;
	private boolean isPaused=true;

	private JFrame frame;
	private JButton  bGenerate;
	private JButton bPause;
	private JFormattedTextField tfWolfCnt;
	private JFormattedTextField tfhareCnt;

	public Main()
	{



		frame = new JFrame();
		frame.setLayout(new GridBagLayout());

		//pre-init game window
		game = new Game((byte) 30,(byte) 20,(byte) 40, 10, 10);
		GridBagConstraints c = new GridBagConstraints();
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		c.gridy=0;
		c.gridheight=GridBagConstraints.PAGE_END;
		frame.add(game.getPanel(),c);

		/*
		 *  SETTINGS PANEL
		 */

		//Prepare buttons & listeners
		bGenerate = new JButton("Generuj nową mapę");
		bGenerate.addActionListener(new Action() {
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
				frame.remove(game.getPanel());

				//filter textFields
				try {
					Integer.parseInt(tfWolfCnt.getText());
				} catch (NumberFormatException ex) {
					tfWolfCnt.setValue(10);
				}
				try {
					Integer.parseInt(tfhareCnt.getText());
				} catch (NumberFormatException ex) {
					tfhareCnt.setValue(10);
				}

				game = new Game((byte) 30,(byte) 20,(byte) 40,Integer.parseInt(tfWolfCnt.getText()),Integer.parseInt(tfhareCnt.getText()));
				GridBagConstraints c = new GridBagConstraints();
				c.fill=GridBagConstraints.HORIZONTAL;
				c.gridx=0;
				c.gridy=0;
				c.gridheight=GridBagConstraints.PAGE_END;

				frame.add(game.getPanel(),c);

				bPause.setText("Start");
				isPaused=true;
				frame.pack();
				frame.repaint();
			}
		});
		bPause = new JButton("Start");
		bPause.addActionListener(new Action() {
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
				bPause.setText(isPaused?"Wznów":"Pauza");
			}
		});

		//Prepare labels and textfields
		JLabel lWolfCnt = new JLabel("Ilość wilków");
		JLabel lHareCnt = new JLabel("Ilość zajęcy");
		tfWolfCnt = new JFormattedTextField();
		tfWolfCnt.setValue(10);
		tfhareCnt = new JFormattedTextField();
		tfhareCnt.setValue(10);
		//Manage menu layout
		c = new GridBagConstraints();
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=1;
		c.gridheight=1;

		c.gridy=0;
		frame.add(lWolfCnt,c);
		c.gridx++;
		frame.add(tfWolfCnt,c);
		c.gridx--;

		c.gridy++;
		frame.add(lHareCnt,c);
		c.gridx++;
		frame.add(tfhareCnt,c);
		c.gridx--;

		c.gridwidth=2;
		c.gridy++;
		frame.add(bGenerate,c);
//		frame.getRootPane().setDefaultButton(bGenerate);
//		bGenerate.requestFocusInWindow();

		c.gridy++;
		frame.add(bPause,c);


		//Finalize frame creation
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Symulator przetrwania zwierzat");
		frame.setLocationByPlatform(true);
		frame.pack();
		frame.setVisible(true);

		//Ticks timer
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
		timer.scheduleAtFixedRate(timerTask,0,1000/tps);
	}
	public static void main(String[] args)
	{
		new Main();
	}
}
