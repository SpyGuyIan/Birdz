package birdz.UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JFrame;

import birdz.lib.environment.Bird;
import birdz.lib.environment.*;
import birdz.lib.genetic.Individual;
import birdz.lib.genetic.Learner;
import birdz.lib.simulations.BirdFitnessCalc;


/**
 * This is the file that should be run to test our environment and bird implementation of our neural network.
 * @author Ian and Ian
 * 
 */

public class EnvInit {

	public static void main(String[] args){
		//spam();
		//testGetSight();
		test();
	}

	
	static Environment env;
	static void test(){
		ArrayList<EnvObject> objects = new ArrayList<EnvObject>();
		Bird bird = new Bird(300, 300, 0, 10, Color.BLUE);
		objects.add(bird);
		objects.add(new Rock(100, 200));
//		objects.add(new Goal(500, 100, 50));
		env = new Environment(objects, 800, 500);		

		BirdFitnessCalc fc = new BirdFitnessCalc(env);
		Learner l = new Learner(fc, 6);
		
		for(int i = 0; i < 4000; i++)
			l.nextGeneration(true);
	
		HashMap<Bird, Individual> hm = new HashMap<Bird, Individual>();
		hm.put(bird, l.nextGeneration(false));
		
		env = new Environment(hm, objects, 800, 500); 
		
		final EnvFrame frame = new EnvFrame("--", env);
		
		
		frame.startButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(frame.startButton.getText().equals("Start")){
					env.runEnvironment(20);
					frame.startButton.setText("Reset");
				}else{
					env.resetEnvironment();
					frame.startButton.setText("Start");
				}
				
			}
		});
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Deprecated
	private static void testGetSight() {
		ArrayList<EnvObject> objects = new ArrayList<EnvObject>();
		//objects.add(new Bird(100, 100));
		objects.add(new Bird(150, 200, 180, 10, Color.BLUE));
		objects.add(new Rock(100,200));

		
		JFrame frame = new EnvFrame("--", new Environment(objects,800,500));
		
		for(int i = 0; i < 5000; i++){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {e.printStackTrace();}
			//System.out.println(Arrays.toString(((Bird)objects.get(0)).getSight(3, objects)));
			
			if(((Bird)objects.get(0)).getSight(1, objects)[0] != -1){
				((Bird)objects.get(0)).rotate(1);
			}
			
			frame.repaint();
		}
		
	}
	
	
	@Deprecated
	static void spam(){
		ArrayList<EnvObject> objects = new ArrayList<EnvObject>();
		//objects.add(new Bird(100, 100));
		//objects.add(new Bird(100, 200, -90, 10, Color.BLUE));
		
		JFrame frame = new EnvFrame("--", new Environment(objects, 1920, 1080));

		for(int i = 0; i < 500; i++){
			synchronized(objects){
				objects.add(new Bird((int)(Math.random() * 1920),
						(int)(Math.random() * 1080),
						(int)(Math.random() * 360),
						(int)(Math.random() * 40),
						new Color((int)(Math.random() * 255),
								(int)(Math.random() * 255),
								(int)(Math.random() * 255))
						));
			}
			frame.repaint();
			
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {e.printStackTrace();}

		for(int i = 0; i < 10; i++){
			moveForward(objects, 1);
			spin(objects, 1);
			frame.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		
	}
	private static void spin(ArrayList<EnvObject> objects, int deg){
		for(EnvObject e : objects)
			if(e instanceof Bird)
				((Bird)e).rotate(deg);
	}
	private static void moveForward(ArrayList<EnvObject> objects, int dist){
		for(EnvObject e : objects)
			if(e instanceof Bird)
				((Bird)e).moveForward(dist);
	}


}
