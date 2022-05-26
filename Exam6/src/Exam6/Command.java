package Exam6;

public class Command {

	private final double INC_STOCK = 0.02;
	private final double DEC_STOCK = 0.01;
	String stockName;
	Operation operation;

	public Command(String stockName, Operation operation) {
		this.stockName = stockName;
		this.operation = operation;
	}

	public static enum Operation{
		BUY, SELL;
	}

	public double buyStock() {
		Stock stock = StocksDB.getStockByName(stockName);
		double buyPrice = stock.getBuyPrice();
		stock.setBuyPrice(buyPrice + INC_STOCK);
		return buyPrice;
	}

	public double sellStock() {
		Stock stock = StocksDB.getStockByName(stockName);
		double sellPrice = stock.getSellPrice();
		stock.setSellPrice(sellPrice + DEC_STOCK);
		return sellPrice;

	}

	public double stockUpdated() {
		switch(operation) {
		case BUY:
			return buyStock();
		case SELL:
			return sellStock();
		}
		return 0;
	}
}
