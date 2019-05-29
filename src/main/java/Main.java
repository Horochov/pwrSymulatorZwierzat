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
	private JFormattedTextField tfHareCnt;
	private JFormattedTextField tfTreeCnt;
	private JFormattedTextField tfBushCnt;
	private JFormattedTextField tfRockCnt;

	private byte defGridWid = 30;
	private byte defGridHei = 20;
	private byte defGridSiz = 40;


	private int defWolfCnt = 10;
	private int defHareCnt = 10;
	private int defTreeCnt = 10;
	private int defBushCnt = 10;
	private int defRockCnt = 10;

	public Main()
	{



		frame = new JFrame();
		frame.setLayout(new GridBagLayout());

		//pre-init game window
		game = new Game(defGridWid,defGridHei,defGridSiz, defWolfCnt,defHareCnt,defBushCnt,defTreeCnt,defRockCnt);
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
					if(Integer.parseInt(tfWolfCnt.getText())<0)
					tfWolfCnt.setValue(0);
				} catch (NumberFormatException ex) {
					tfWolfCnt.setValue(defWolfCnt);
				}
				try {
					if(Integer.parseInt(tfHareCnt.getText())<0)
					tfHareCnt.setValue(0);
				} catch (NumberFormatException ex) {
					tfHareCnt.setValue(defHareCnt);
				}
				try {
					if(Integer.parseInt(tfTreeCnt.getText())<0)
					tfTreeCnt.setValue(0);
				} catch (NumberFormatException ex) {
					tfTreeCnt.setValue(defTreeCnt);
				}
				try {
					if(Integer.parseInt(tfRockCnt.getText())<0)
						tfRockCnt.setValue(0);
				} catch (NumberFormatException ex) {
					tfRockCnt.setValue(defRockCnt);
				}
				try {
					if(Integer.parseInt(tfBushCnt.getText())<0)
						tfBushCnt.setValue(0);
				} catch (NumberFormatException ex) {
					tfBushCnt.setValue(defBushCnt);
				}

				game = new Game(defGridWid,defGridHei,defGridSiz,
				                Integer.parseInt(tfWolfCnt.getText()),Integer.parseInt(tfHareCnt.getText()),
				                Integer.parseInt(tfBushCnt.getText()),Integer.parseInt(tfTreeCnt.getText()),
				                Integer.parseInt(tfRockCnt.getText())
				);
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
		tfWolfCnt.setValue(defWolfCnt);
		tfHareCnt = new JFormattedTextField();
		tfHareCnt.setValue(defHareCnt);

		JLabel lTreeCnt = new JLabel("Ilość drzew");
		JLabel lBushCnt = new JLabel("Ilość krzaków");
		JLabel lRockCnt = new JLabel("Ilość kamieni");
		tfTreeCnt = new JFormattedTextField();
		tfTreeCnt.setValue(defTreeCnt);
		tfBushCnt = new JFormattedTextField();
		tfBushCnt.setValue(defBushCnt);
		tfRockCnt = new JFormattedTextField();
		tfRockCnt.setValue(defRockCnt);

		//Manage menu layout
		c = new GridBagConstraints();
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=1;
		c.gridheight=1;
		c.insets.bottom=1;

		c.gridy=0;

		c.insets.left=2;
		c.insets.right=2;
		GridBagConstraints d = (GridBagConstraints)c.clone();
		d.insets.left=0;
		d.gridx++;

		frame.add(lWolfCnt,c);
		frame.add(tfWolfCnt,d);

		c.gridy++;
		d.gridy++;
		c.insets.bottom=10;
		d.insets.bottom=10;
		frame.add(lHareCnt,c);
		frame.add(tfHareCnt,d);
		c.insets.bottom=1;
		d.insets.bottom=1;

		c.gridy++;
		d = (GridBagConstraints)c.clone();
		d.insets.left=0;
		d.gridx++;
		frame.add(lRockCnt,c);
		frame.add(tfRockCnt,d);

		c.gridy++;
		d.gridy++;
		frame.add(lBushCnt,c);
		frame.add(tfBushCnt,d);

		c.gridy++;
		d.gridy++;
		frame.add(lTreeCnt,c);
		frame.add(tfTreeCnt,d);



		c.gridwidth=2;
		c.gridy++;
		c.insets.top=10;
		frame.add(bGenerate,c);
		c.insets.top=0;

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
