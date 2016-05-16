import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

public class Target{
	double xCordTarget = 400, yCordTarget = 40;
	double rand;
	Boolean paintTarget;
	public Target(){
		genTarget();
	}
	public void genTarget(){
		rand = 275 + Math.random()*500;
		xCordTarget = rand;
		rand = 10 + Math.random()*150;
		yCordTarget = rand;
		//Check for a generation that puts the ball in the top right corner, and then REgenerate. This location is unreachable. 
	}
	public void setPaint(Boolean paintTarget){
		this.paintTarget = paintTarget;
	}
}
