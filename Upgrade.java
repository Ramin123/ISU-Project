import javax.swing.*;

public class Upgrade {
	private int price, xLoc, yLoc, itemID, locationCounter = 0;
	private Boolean isSinglePurchase;
	private final int BUFFER = 100;
	private JButton buyButton = new JButton();
	
	public Upgrade(int price, int itemID, Boolean isSinglePurchase){
		this.price = price;
		this.itemID = itemID;
		this.isSinglePurchase = isSinglePurchase;
		locationCounter = itemID;
		
		if (itemID > 12){
			yLoc = 230;
			locationCounter -= 12;
		}else if (itemID > 6){
			yLoc = 130;
			locationCounter -= 6;
		}else
			yLoc = 30;
		
		switch (locationCounter){
			case 1:
				xLoc = 35; break;
			case 2:
				xLoc = 35 + BUFFER; break;
			case 3:
				xLoc = 35 + (BUFFER * 2); break;
			case 4:
				xLoc = 35 + (BUFFER * 3); break;
			case 5:
				xLoc = 35 + (BUFFER * 4); break;
			case 6:
				xLoc = 35 + (BUFFER * 5); break;	
		}
		
		buyButton.setBounds(xLoc - 25, yLoc + 40, 85, 20);
		buyButton.setText(price + " Coins");
		buyButton.setFont(Shop.font);
	}
	public JButton getButton(){
		return buyButton;
	}
	public int getXLoc(){
		return xLoc;
	}
	public int getYLoc(){
		return yLoc;
	}
	public Boolean getSinglePurchase(){
		return isSinglePurchase;
	}
	public int getPrice(){
		return price;
	}
	public void setPrice(int price){
		this.price = price;
	}
}
