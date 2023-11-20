import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Simple_executor_test {

    private static final int NTHREADS = 8;
        
    public static void main(String[] args){

		double dx = 0.001;
		double stride = Math.PI / 50;
		double start = 0;
		double end = stride;

		List<Future<Double>> list = new ArrayList<>();

		ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);
		double sum2 = 0.0;
		try {
			for (int i = 0; i < 50; i++) {
				Calka_callable calka_callable = new Calka_callable(start, end, dx);
				sum2 += calka_callable.compute_integral();

				start = end;
				end += stride;
				Future<Double> future = executor.submit(calka_callable);
				list.add(future);
			}

			double sum = 0.0;
			for (Future<Double> future_double : list) {
				sum += future_double.get();
			}


			// This will make the executor accept no new threads
			// and finish all existing threads in the queue
			executor.shutdown();

			// Wait until all threads finish
			while (!executor.isTerminated()) {
			}

			System.out.println("\nCalka sekwencyjnie: " + sum2);
			System.out.println("Calka z puli watkow: " + sum);
			System.out.println("Finished all threads");

		} catch(ExecutionException e){
			e.printStackTrace();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
    }
} 
