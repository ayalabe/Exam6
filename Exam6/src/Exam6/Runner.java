package Exam6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import Exam6.Command.Operation;

public class Runner {
	private static final int NUM_THREADS = 3;
	private static double totalFunds = 0;
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		System.out.println(StocksDB.getStockByName("doodle"));
		System.out.println(StocksDB.getStockByName("headbook"));
		System.out.println(StocksDB.getStockByName("barvazon"));
		
		System.out.println("Total Funds is: " + totalFunds);
		 
		List<Command> commands = new ArrayList<>();
		commands.add(new Command("doodle", Operation.BUY));
		commands.add(new Command("doodle", Operation.SELL));
		commands.add(new Command("doodle", Operation.SELL));
		commands.add(new Command("headbook", Operation.BUY));
		commands.add(new Command("headbook", Operation.SELL));
		commands.add(new Command("headbook", Operation.BUY));
		commands.add(new Command("barvazon", Operation.BUY));
		commands.add(new Command("barvazon", Operation.SELL));
		commands.add(new Command("barvazon", Operation.SELL));
		commands.add(new Command("barvazon", Operation.BUY));
//		commands.add(new Command("baravazon", Operation.BUY)); //throw exaption
		
		ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
		List<Future<Double>> futures = new ArrayList<Future<Double>>();

		for (Command command : commands) {
			futures.add(executorService.submit(() -> {
				return command.stockUpdated();
			}));
		} 
	
		for (Future<Double> future : futures) {
			totalFunds += future.get();
		}
		
		System.out.println(StocksDB.getStockByName("doodle"));
		System.out.println(StocksDB.getStockByName("headbook"));
		System.out.println(StocksDB.getStockByName("barvazon"));

		System.out.println("Total Funds is: " + totalFunds);

		executorService.shutdown();
		executorService.awaitTermination(2, TimeUnit.SECONDS);
		
	}

}
