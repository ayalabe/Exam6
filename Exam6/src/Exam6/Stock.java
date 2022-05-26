package Exam6;

public class Stock {

	private String name;
	private double buyPrice;
	private double sellPrice;
	public Stock(String name, double buyPrice, double sellPrice) {
		setName(name);
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
	}
	private void setName(String name) {
		if(StockName.valueOf(name.toUpperCase()) != null) { //throws IllegalArgumentException
			this.name=name;
		}
	}
	
	
	
	public double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}
	@Override
	public String toString() {
		return "Stock [name=" + name + ", buyPrice=" + buyPrice + ", sellPrice=" + sellPrice + "]";
	}

}
