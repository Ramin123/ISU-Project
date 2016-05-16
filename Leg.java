import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.*;
import java.util.TimerTask;
import java.awt.*;
import javax.swing.*;

public class Leg extends JFrame{
	private JButton kickButton = new JButton();
	private JButton shopButton = new JButton();
	private JLabel moneyLabel = new JLabel();
	private DrawLeg leg = new DrawLeg();
	private Target target = new Target();
	private Timer timer,PowerTracker;
	
	private double angle = 0.6, xCord = 70, yCord = -45, xCordBall = 105, yCordBall = 250, xSpeed = 0, ySpeed = 0, speed = 5, 
			g = 1, yBoundary = 250;
	private int counter = 0, bounceCounter = 0;
	static int money = 0, ballSize = 20, adjustYLoc = 5;
	
	/*	
	pre: weight is defeined + non-zero
	post: constructs Leg object. Properly sets up and lays out all Labels and Buttons on frame. Creates Button and Timer Action Events.
    */
	public Leg(){
		setTitle("Kick Foot Game");
		setSize(809,306);
		setLocation(300,20);
		setVisible(true);
		this.setResizable(false);

		Container c = getContentPane();
		c.add(leg);
		
		PowerTracker = new Timer(50, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				speed+=1;
				
				}
		});
		kickButton.setText("Kick");
		kickButton.setMnemonic('K');
		kickButton.setBounds(50,25,120,25);
		kickButton.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					PowerTracker.start();
					counter =0;
				}
				public void mouseReleased (MouseEvent e){
					doFunction(0);
					xSpeed = speed;
					ySpeed = speed;		
					timer.start();
					leg.draw();
					kickButton.setVisible(false);
					
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
		leg.add(kickButton);
		leg.add(moneyLabel);
			
		timer = new Timer(5, new ActionListener(){
				public void actionPerformed(ActionEvent e){
					counter++;
					//System.out.println(yCordBall + "     " + ySpeed + "   " + counter);
					
					if(counter<10){
						moveBall();
					}else{
						if(yCordBall+Math.abs(ySpeed)<= yBoundary){ /////FIX HERE
							moveBall(); //combined Code
						}else if(ySpeed<=0){
							bounceCounter++;
							yCordBall = yBoundary;
							xCordBall+=xSpeed;
							ySpeed*=-0.5;
							xSpeed*=0.6;
							for(int i=0;i<5;i++){
								moveBall();
							//System.out.println(yCordBall + "   //  " + ySpeed + "   " + counter);
								}
							if(bounceCounter >=6){
								xCordBall = 105;
								yCordBall = 250;
								ySpeed =0;
								leg.draw();
								timer.stop();
								speed = 5;
								bounceCounter =0;
								PowerTracker.stop();
								kickButton.setVisible(true);
							}//if
						}//else
					}//else	
				}//while	
			});//Timer;	
	}//Leg
	
	public void moveBall(){
		if (counter > 20){
			angle = 0.6; 
			xCord = 70;
			yCord = -45;
			leg.draw();
		}
		if (true){
			yCordBall-=ySpeed;
			xCordBall+=xSpeed;
			if (xCordBall >=770 ){
				xCordBall = 770;
				xSpeed*=-0.5;
				ySpeed*=0.6;
				
			}
			if (xCordBall <=1 ){
				xCordBall = 1;
				xSpeed*=-0.5;
				
			}
			if (yCordBall <=0){
				yCordBall = 1;
				ySpeed*=-0.6;
			}
			ySpeed-=g; 
			leg.draw();
			checkCollision();
		}//if
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

	class DrawLeg extends JPanel{
	/*	
	pre: nothing
	post: constucts DrawLeg object
    */
	public DrawLeg(){
		setLayout(null);
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

