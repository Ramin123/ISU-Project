import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

public class Shop extends JFrame{
	private JLabel moneyLabel = new JLabel();
	private JButton backButton = new JButton();
	static Font font = new Font("Arial", Font.PLAIN, 13);
	private static Upgrade blueBall = new Upgrade(3, 1, true);
	private static Upgrade redBall = new Upgrade(3, 2, true);
	private static Upgrade greenBall = new Upgrade(3, 3, true);
	private static Upgrade yellowBall = new Upgrade(3, 4, true);
	private static Upgrade purpleBall = new Upgrade(3, 5, true);
	private static Upgrade dragonBall = new Upgrade(10, 6, true);
	
	private static Upgrade size = new Upgrade(5, 7, false);
	private static Upgrade blackTargetChance = new Upgrade(5, 8, false);
	
	private DrawShop shop = new DrawShop();
	private static Color currentColor = Color.BLACK;
	
	private final int SIZE_UP_MULTIPLYER = 2, BLACK_TARGET_MULTIPLYER = 2;
	public Shop(){
		setTitle("Shop!");
		setSize(610,350);
		setLocation(300,20);
		setVisible(true);
		this.setResizable(false);
		checkFunds();
		
		moneyLabel.setText("Coins: " + Leg.money);
		moneyLabel.setBounds(10,7,60,10);
		
		blueBall.getButton().addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					if(blueBall.getButton().isEnabled()){		
						if(!blueBall.getButton().getText().equals("FREE")){
							Leg.money-= blueBall.getPrice();
							moneyLabel.setText("Coins: " + Leg.money);
							blueBall.getButton().setText("FREE");
							checkFunds();
						}
							currentColor = Color.BLUE;
					}
				}
		});	
		redBall.getButton().addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					if(redBall.getButton().isEnabled()){		
						if(!redBall.getButton().getText().equals("FREE")){
							Leg.money-= redBall.getPrice();
							moneyLabel.setText("Coins: " + Leg.money);
							redBall.getButton().setText("FREE");
							checkFunds();
						}
							currentColor = Color.RED;
					}
				}
		});	
		greenBall.getButton().addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					if(greenBall.getButton().isEnabled()){		
						if(!greenBall.getButton().getText().equals("FREE")){
							Leg.money-= greenBall.getPrice();
							moneyLabel.setText("Coins: " + Leg.money);
							greenBall.getButton().setText("FREE");
							checkFunds();
						}
							currentColor = Color.GREEN;
					}
				}
		});	
		yellowBall.getButton().addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					if(yellowBall.getButton().isEnabled()){		
						if(!yellowBall.getButton().getText().equals("FREE")){
							Leg.money-= yellowBall.getPrice();
							moneyLabel.setText("Coins: " + Leg.money);
							yellowBall.getButton().setText("FREE");
							checkFunds();
						}
							currentColor = Color.YELLOW;
					}
				}
		});	
		purpleBall.getButton().addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(purpleBall.getButton().isEnabled()){		
					if(!purpleBall.getButton().getText().equals("FREE")){
						Leg.money-= purpleBall.getPrice();
						moneyLabel.setText("Coins: " + Leg.money);
						purpleBall.getButton().setText("FREE");
						checkFunds();
					}
						currentColor = new Color(95, 0, 165);
				}
			}
		});	
		dragonBall.getButton().addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(dragonBall.getButton().isEnabled()){		
					if(!dragonBall.getButton().getText().equals("FREE")){
						Leg.money-= dragonBall.getPrice();
						moneyLabel.setText("Coins: " + Leg.money);
						dragonBall.getButton().setText("FREE");
						checkFunds();
					}
						currentColor = Color.CYAN;
				}
			}
		});	
		size.getButton().addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(size.getButton().isEnabled()){		
						Leg.money-= size.getPrice();
						moneyLabel.setText("Coins: " + Leg.money);
						size.setPrice(size.getPrice()*SIZE_UP_MULTIPLYER);
						size.getButton().setText(size.getPrice() + " Coins");
						Leg.setBallSize(Leg.ballSize + 3);
						Leg.setBallAdjust(Leg.adjustYLoc - 3);
						checkFunds();
				}
			}
		});	
		blackTargetChance.getButton().addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(blackTargetChance.getButton().isEnabled()){		
						Leg.money-= size.getPrice();
						moneyLabel.setText("Coins: " + Leg.money);
						blackTargetChance.setPrice(blackTargetChance.getPrice()*BLACK_TARGET_MULTIPLYER);
						blackTargetChance.getButton().setText(blackTargetChance.getPrice() + " Coins");
						Leg.blTarget.setRarity(Leg.blTarget.getRarity() + 1);
						checkFunds();
				}
			}
		});	
		WindowEvent close = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		backButton.setFont(font);
		backButton.setBounds(520, 290, 70, 20);
		backButton.setText("Back");
		backButton.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e){
				dispatchEvent(close);
			}
		});	
		
		Container c = this.getContentPane();
		shop.setLayout(null);
		shop.add(moneyLabel);
		shop.add(backButton);
		shop.add(blueBall.getButton());
		shop.add(redBall.getButton());
		shop.add(greenBall.getButton());
		shop.add(yellowBall.getButton());
		shop.add(purpleBall.getButton());
		shop.add(dragonBall.getButton());
		shop.add(size.getButton());
		shop.add(blackTargetChance.getButton());
		c.add(shop);
	
	}//constructor
	public void checkFunds(){
		if (Leg.money >= blueBall.getPrice() || blueBall.getButton().getText().equals("FREE"))
			blueBall.getButton().setEnabled(true);
		else
			blueBall.getButton().setEnabled(false);
			
		if (Leg.money >= redBall.getPrice() || redBall.getButton().getText().equals("FREE"))
			redBall.getButton().setEnabled(true);
		else
			redBall.getButton().setEnabled(false);
		
		if (Leg.money >= greenBall.getPrice() || greenBall.getButton().getText().equals("FREE"))
			greenBall.getButton().setEnabled(true);
		else
			greenBall.getButton().setEnabled(false);
		
		if (Leg.money >= yellowBall.getPrice() || yellowBall.getButton().getText().equals("FREE"))
			yellowBall.getButton().setEnabled(true);
		else
			yellowBall.getButton().setEnabled(false);

		if (Leg.money >= purpleBall.getPrice() || purpleBall.getButton().getText().equals("FREE"))
			purpleBall.getButton().setEnabled(true);
		else
			purpleBall.getButton().setEnabled(false);
		
		if (Leg.money >= dragonBall.getPrice() || dragonBall.getButton().getText().equals("FREE"))
			dragonBall.getButton().setEnabled(true);
		else
			dragonBall.getButton().setEnabled(false);
		
		if (Leg.money >= size.getPrice())
			size.getButton().setEnabled(true);
		else
			size.getButton().setEnabled(false);
		
		if (Leg.money >= blackTargetChance.getPrice())
			blackTargetChance.getButton().setEnabled(true);
		else
			blackTargetChance.getButton().setEnabled(false);
	
	}
	public static Color getColor(){
		return currentColor;
	}
	class DrawShop extends JPanel{
		public DrawShop(){
			setLayout(null);
		}
		public void draw(){
			repaint();
		}
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
		
			Ellipse2D blueBall = new Ellipse2D.Double(Shop.blueBall.getXLoc(), Shop.blueBall.getYLoc(), 30,30);
			Ellipse2D redBall = new Ellipse2D.Double(Shop.redBall.getXLoc(), Shop.redBall.getYLoc(), 30, 30);
			Ellipse2D greenBall = new Ellipse2D.Double(Shop.greenBall.getXLoc(), Shop.greenBall.getYLoc() ,30,30);
			Ellipse2D yellowBall = new Ellipse2D.Double(Shop.yellowBall.getXLoc(), Shop.yellowBall.getYLoc() ,30,30);
			Ellipse2D purpleBall = new Ellipse2D.Double(Shop.purpleBall.getXLoc(), Shop.purpleBall.getYLoc() ,30,30);
			Ellipse2D dragonBall = new Ellipse2D.Double(Shop.dragonBall.getXLoc(), Shop.dragonBall.getYLoc() ,30,30);
			Ellipse2D targetOutter = new Ellipse2D.Double(Shop.blackTargetChance.getXLoc(), Shop.blackTargetChance.getYLoc(),30,30);
			Ellipse2D targetMid = new Ellipse2D.Double(Shop.blackTargetChance.getXLoc()+5,Shop.blackTargetChance.getYLoc()+5, 20, 20);
			Ellipse2D targetInner = new Ellipse2D.Double(Shop.blackTargetChance.getXLoc()+10,Shop.blackTargetChance.getYLoc()+10,10,10);
			g.setColor(Color.BLACK);
			g2d.fill(targetOutter);
			g.setColor(Color.WHITE);
			g2d.fill(targetMid);
			g.setColor(Color.BLACK);
			g2d.fill(targetInner);
			
			g.setColor(Color.BLUE);
			g2d.fill(blueBall);
			g.setColor(Color.RED);
			g2d.fill(redBall);
			g.setColor(Color.GREEN);
			g2d.fill(greenBall);
			g.setColor(Color.YELLOW);
			g2d.fill(yellowBall);
			g.setColor(new Color(95, 0, 165));
			g2d.fill(purpleBall);
			g.setColor(Color.CYAN);
			g2d.fill(dragonBall);
			//Pre: Angle: -2.9 Transform: 0,-8
			//Post: Angle: -1.4 Transfomr: 0, 0
		}//method
	}//class
	
}//class
