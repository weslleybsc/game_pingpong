package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {

	public double x, y;
	public int width, height;
	public double dx, dy;
	public double speed = 2;
	
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		this.height = 5;
		this.width = 5;
		
		int angulo = new Random().nextInt(359);
		dx = Math.cos(Math.toRadians(angulo));
		dy = Math.sin(Math.toRadians(angulo));
	}

	public void tick() {
		
		if(x + width > Game.WIDTH) {
			dx*= -1;
		} else if(x < 0){
			dx*= -1;
		}
		
		if(y > Game.HEIGHT) {
			System.out.println("Você perdeu!");
			new Game();
			return;
		} else if(y < 0) {
			System.out.println("Você Ganhou!");
			new Game();
			return;
		}
		
		Rectangle bounds = new Rectangle((int)x,(int)y,width,height);
		Rectangle boundsPlayer = new Rectangle(Game.player.x,Game.player.y,Game.player.width,Game.player.height);
		Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x,(int)Game.enemy.y,Game.enemy.width,Game.enemy.height);
		
		if(bounds.intersects(boundsPlayer)) {
			int angulo = new Random().nextInt(160);
			dx = Math.cos(Math.toRadians(angulo));
			dy = Math.sin(Math.toRadians(angulo));
			dy*= -1;
		} else if(bounds.intersects(boundsEnemy)){
			int angulo = new Random().nextInt(160);
			dx = Math.cos(Math.toRadians(-angulo));
			dy = Math.sin(Math.toRadians(-angulo));
			dy*= -1;
		}
		
		x+=dx*speed;
		y+=dy*speed;

	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect((int)x, (int)y, width, height);
	}
	
}
