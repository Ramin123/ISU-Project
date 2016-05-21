import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BlackTarget extends Target{
	DrawTarget draw = new DrawTarget();
	public BlackTarget(Color color, int points, int rarity) {
		super(color, points, rarity); 
	}
	@Override
	public void draw(Graphics g){
		System.out.println("ALLAHHAHAHAHAHAHAHAHHAHAHA");
		draw.paintComponent(g);
		
	}
	public class DrawTarget extends JPanel{
		
		/*	
		pre: nothing
		post: constucts DrawLeg object
	    */
		public DrawTarget(){
			setLayout(null);
			
			
		}

		/*	
		pre: there is a DrawLeg object
		post: calls repaint method
	    */
		public void draw(){
			//repaint();
		}

		/*	
		pre: there is a DrawLeg object
		post: paints the leg and ball
	    */
		public void paintComponent(Graphics g){
			super.paintComponent(g);

			Graphics2D g2d = (Graphics2D) g;
			//creates background image
				Ellipse2D targetOutter = new Ellipse2D.Double(Leg.blTarget.getXCord(),Leg.blTarget.getYCord(),50,30);
				Ellipse2D targetMid = new Ellipse2D.Double(Leg.blTarget.getXCord()+5,Leg.blTarget.getYCord()+5, 20, 20);
				Ellipse2D targetInner = new Ellipse2D.Double(Leg.blTarget.getXCord()+10,Leg.blTarget.getYCord()+10,10,10);
					g.setColor(Color.BLACK);
					g2d.fill(targetOutter);
					g.setColor(Color.WHITE);
					g2d.fill(targetMid);
					g.setColor(Color.BLACK);
					g2d.fill(targetInner);
			}	
			
		}//method
	
}
