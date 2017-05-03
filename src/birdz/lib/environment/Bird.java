package birdz.lib.environment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Bird extends EnvObject {

	private static final int DEFAULT_SIZE = 15;
	private static final Color DEFAULT_COLOR = Color.BLUE;
	private static final int HITBOX_POINTS = 4;

	private int degRotation;
	private int size;
	private Color color;

	public Bird() {
		this(0, 0, 0, DEFAULT_SIZE, DEFAULT_COLOR);
	}

	public Bird(int x, int y, int rot, int size, Color c) {
		this.setPosition(x, y);
		this.degRotation = rot;
		this.size = size;
		color = c;
	}
	
	public double[] getInputs() {
		return null; //FIXME
	}

	@Override
	public boolean isTouching(Point p) {
		return ((p.x - position.x)*(p.x - position.x) + (p.y - position.y)*(p.y - position.y) < size); //TODO include beak
	}

	@Override
	public Point[] getHitbox() {
		Point[] hitbox = new Point[HITBOX_POINTS];
		for(int i = 0; i < HITBOX_POINTS; i++)
			hitbox[i] = new Point((int) (size * Math.cos(((double) i / HITBOX_POINTS) * Math.PI * 2)), (int) (size * Math.sin(((double)(i) / HITBOX_POINTS) * Math.PI * 2)));
		return hitbox;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval(position.x-size, position.y-size, size*2, size*2);

		g.fillPolygon(new int[] {position.x+(int)(size*Math.cos(Math.toRadians(degRotation))),
				position.x+(int)(size*Math.cos((Math.PI/2)+Math.toRadians(degRotation))),
				position.x+(int)(2*size*Math.cos((Math.PI/4)+Math.toRadians(degRotation)))}, 	  	  
				new int[] {position.y+(int)(size*Math.sin(Math.toRadians(degRotation))),
						position.y+(int)(size*Math.sin((Math.PI/2)+Math.toRadians(degRotation))),
						position.y+(int)(2*size*Math.sin((Math.PI/4)+Math.toRadians(degRotation)))}, 
				3);

	}

}
