

public class Item {
	public String Name;
	public double UPrice,RPrice;
	public int boxQty, Quantity;
	
	
	public Item() {
		Name = "";
		UPrice = 0;
		Quantity =1;
		boxQty =0;
		RPrice =0;

	}
	public Item(String Name, double UPrice) {
		this.Name = Name;
		this.UPrice = UPrice;
		
	}
	
	public void setQty(int Quantity) {
		this.Quantity = Quantity;
		
	}
	
	public void setName(String Name) {
		this.Name =Name;
		
	}
	
	public void setBoxQty(int boxQty) {
		this.boxQty = boxQty;
		
	}
	
	public void setRPrice(double RPrice) {
		this.RPrice = RPrice;
		
	}
	
	
	
	
	public int getQuantity() {
		return Quantity;
	}
	
	public String getName() {
		return Name;
	}
	
	public double getUPrice() {
		double unit;
		unit = RPrice/boxQty;	
		return unit;
	}
	
	public double getPrice() {
		double unitP = RPrice/boxQty;
		return unitP * Quantity;
	}
	
	
	
	
	


}