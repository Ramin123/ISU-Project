import java.awt.*;

public class Target{
	private double xCordTarget = 400, yCordTarget = 40, rand;
	private int size, points, rarity;
	private Boolean paintTarget;
	private Color color; 
	public Target(Color color, int points, int rarity){
		this.color = color;
		this.points = points;
		this.rarity = rarity;
		
		genTarget();
		
	}
	public void draw(Graphics g){
		
	}
	public void genTarget(){
		rand = 275 + Math.random()*500;
		xCordTarget = rand;
		rand = 10 + Math.random()*200;
		yCordTarget = rand;
		//Check for a generation that puts the ball in the top right corner, and then REgenerate. This location is unreachable. 
	}
	public void setPaint(Boolean paintTarget){
		this.paintTarget = paintTarget;
	}
	public Color getColor(){
		return color;
	}
	public int getPoints(){
		return points;
	}
	public int getRarity(){
		return rarity;
	}
	public void setRarity(int rarity){
		this.rarity = rarity;
	}
	public int getSize(){
		return size;
	}
	public double getXCord(){
		return xCordTarget;
	}
	public double getYCord(){
		return yCordTarget;
	}
	public void setXCord(double xCordTarget){
		 this.xCordTarget= xCordTarget;
	}
	public void setYCord(double yCordTarget){
		 this.yCordTarget= yCordTarget;
	}

	
	
	
	
	
}
