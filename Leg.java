import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.TimerTask;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Leg extends JFrame{
	private JButton kickButton = new JButton();
	private JButton shopButton = new JButton();
	private JLabel moneyLabel = new JLabel();
	private DrawLeg leg = new DrawLeg();
	private Target target = new Target();
	private Timer animator,PowerTracker;
	private JProgressBar bar = new JProgressBar(0,30);
	private JLabel kickLabel = new JLabel();
		
		private Image kickImg = null;
		private BufferedImage getImg = null;
		private ImageIcon kickIcon;
	
	private double angle = 0.6, xCord = 70, yCord = -45, xCordBall = 105, yCordBall = 250, xSpeed = 0, ySpeed = 0, speed = 5, 
					g = 1, yBoundary = 250;
	private int counter = 0, bounceCounter = 0;
	static int money = 0, ballSize = 20, adjustYLoc = 5;
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();	//gets the dimensions of the screen
	private Boolean kickEnabled = true;
	/*	
	pre: weight is defeined + non-zero
	post: constructs Leg object. Properly sets up and lays out all Labels and Buttons on frame. Creates Button and Timer Action Events.
    */
	public Leg(){
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Kick Foot Game");
		setSize(809,306);
		setLocation(dim.width/2 - 404, dim.height/2 - 153);	//frame is at the center of the screen
		this.setResizable(false);
		bar.setValue(0);
		//bar.setStringPainted(true);
		bar.setBounds(50, 90, 120, 13);
		Container c = getContentPane();
		c.add(leg);
		
		
		try{
			getImg = ImageIO.read(new File("src/kick.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		kickImg = getImg.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		kickIcon = new ImageIcon(kickImg);
		kickLabel.setIcon(kickIcon);
		kickLabel.setBounds(80,5,60,60);
		
		PowerTracker = new Timer(50, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (speed - 5 < bar.getMaximum()){
					speed+=1;
					bar.setValue((int) speed-5);
					bar.setForeground(new Color(255 * (int) (speed - 5) / bar.getMaximum(), 255 * (int) (bar.getMaximum() - speed + 5) / bar.getMaximum(), 0));
				}			
			}	
		});
		kickButton.setText("Kick");
		kickButton.setMnemonic('K');
		//kickButton.setBounds(50,25,120,25);
		kickLabel.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					if (kickEnabled){
						kickImg = getImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
						kickIcon = new ImageIcon(kickImg);
						kickLabel.setIcon(kickIcon);
						kickLabel.setBounds(85,10,50,50);
						PowerTracker.start();
						counter = 0;
					}
				}
				public void mouseReleased (MouseEvent e){
					if (kickEnabled){
						kickImg = getImg.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
						kickIcon = new ImageIcon(kickImg);
						kickLabel.setIcon(kickIcon);
						kickLabel.setBounds(80,5,60,60);
						doFunction(0);
						xSpeed = speed;
						ySpeed = -speed;		
						animator.start();
						leg.draw();
						PowerTracker.stop();
					}
				}
		});	
		shopButton.setText("Shop");
		shopButton.setMnemonic('S');
		shopButton.setBounds(75,60,70,25);
		shopButton.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					Shop shop = new Shop();
					shop.addWindowListener(new WindowAdapter(){
						public void windowClosing(WindowEvent e){
							setVisible(true);
							moneyLabel.setText("Coins: " + money);
						}
					});
					setVisible(false);
				}
		});	
		moneyLabel.setText("Coins: " + money);
		moneyLabel.setBounds(10,7,100,10);
		leg.add(shopButton);
		//leg.add(kickButton);
		leg.add(moneyLabel);
		leg.add(bar);	
		leg.add(kickLabel);
		animator = new Timer(20, new ActionListener(){
				public void actionPerformed(ActionEvent e){
					
					for(int i = 0; i < 10; i++){
						if (xCordBall + xSpeed / 10.0 >= 809 - 26 || xCordBall <= 0){
							xSpeed *= -.8;
							ySpeed *= .8;
						} else if (yCordBall + ySpeed / 10.0 >= 306 - 54 || yCordBall <= -6){
							ySpeed *= -.8;
							xSpeed *= .8;
						}
						xCordBall += xSpeed / 10.0;
						yCordBall += ySpeed / 10.0;
						leg.draw();
						checkCollision();
						if (Math.hypot(xSpeed, ySpeed) < 1){
							resetBall();
						}
					}
					ySpeed += g;
					
					
				}//action performed	
			});//animator;	
		setVisible(true);
	}//Leg
	public void resetBall(){
		xCordBall = 105;
		yCordBall = 250;
		ySpeed =0;
		leg.draw();
		animator.stop();
		speed = 5;
		bar.setValue(0);
		kickEnabled = true;
	}
	
	/*	
	pre: there is a leg object
	post: sets initial ball coords + rotation
    */
	public void doFunction(double input){
		angle = -0.2; 
		xCord = 80;
		yCord = 17;
		leg.draw();
		
	}
    //pre:none
    //post: constructs String with relevant information about the Leg.
    public Boolean checkCollision(){
    	Boolean collision = false;
    	if(xCordBall >= target.xCordTarget-ballSize && xCordBall <= target.xCordTarget+ballSize && 
    		yCordBall >= target.yCordTarget-ballSize && yCordBall <= target.yCordTarget+ballSize){
    		target.genTarget();
    		leg.draw();
    		money += 3;
    		moneyLabel.setText("Coins: " + money);
    		
    	}
    	return collision;
    }
    public static void setBallSize(int size){
    	ballSize = size;
    }
    public static void setBallAdjust(int adjust){
    	adjustYLoc = adjust;
    }

	public class DrawLeg extends JPanel{
		
	private BufferedImage image;
	/*	
	pre: nothing
	post: constucts DrawLeg object
    */
	public DrawLeg(){
		setLayout(null);
		
		try {                
			image = ImageIO.read(new File("src/beach background good.jpg"));
	    } catch (IOException ex) {
	    	System.out.println("No image could be found");
	    }
	}

	/*	
	pre: there is a DrawLeg object
	post: calls repaint method
    */
	public void draw(){
		repaint();
	}

	/*	
	pre: there is a DrawLeg object
	post: paints the leg and ball
    */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g.drawImage(image, 0, 0, null);
		//creates background image
		
		//Foot
		Rectangle2D upper = new Rectangle2D.Double(0,0,85,10);
		Rectangle2D lower = new Rectangle2D.Double(0,0,85,10);//65 110
		Ellipse2D foot = new Ellipse2D.Double(80,-15, 10, 25);
		//Ball
		Ellipse2D ball = new Ellipse2D.Double(xCordBall,yCordBall+adjustYLoc, Leg.ballSize, Leg.ballSize);
		//Target
		Ellipse2D targetOutter = new Ellipse2D.Double(target.xCordTarget,target.yCordTarget,30,30);
		Ellipse2D targetMid = new Ellipse2D.Double(target.xCordTarget+5,target.yCordTarget+5, 20, 20);
		Ellipse2D targetInner = new Ellipse2D.Double(target.xCordTarget+10,target.yCordTarget+10,10,10);
		
		AffineTransform transform = new AffineTransform();	
			g.setColor(Color.RED);
			g2d.fill(targetOutter);
			g.setColor(Color.WHITE);
			g2d.fill(targetMid);
			g.setColor(Color.RED);
			g2d.fill(targetInner);
				g.setColor(new Color(242,225,189));//skin-color
				transform.rotate(1.2);
				transform.translate(120, -10);
				Shape rotatedUpper = transform.createTransformedShape(upper);
			g2d.fill(rotatedUpper);
			//Second Rectangle
				transform.rotate(angle);
				transform.translate(xCord,yCord);
				Shape rotatedLower = transform.createTransformedShape(lower);
				Shape rotatedFoot = transform.createTransformedShape(foot);
			g2d.fill(rotatedLower);
			g2d.fill(rotatedFoot);
			g.fillOval(69,178,15,15);
			g.setColor(Shop.getColor());
			g2d.fill(ball);
		
		//Pre: Angle: -2.9 Transform: 0,-8
		//Post: Angle: -1.4 Transfomr: 0, 0
	}//method
}//class
}//class

