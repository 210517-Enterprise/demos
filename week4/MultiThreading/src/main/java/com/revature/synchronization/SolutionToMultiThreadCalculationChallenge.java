package com.revature.synchronization;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class SolutionToMultiThreadCalculationChallenge {

	public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) {

		BigInteger result;

		PowerCalculatingThread thread1 = new PowerCalculatingThread(base1, power1);
		PowerCalculatingThread thread2 = new PowerCalculatingThread(base2, power2);

		thread1.start();
		thread2.start();

		try {

			thread1.join();
			thread2.join();

		} catch (InterruptedException e) { // alternatively this could be thrown at the top of the method.

			e.printStackTrace();
		}

		result = thread1.getResult().add(thread2.getResult());

		return result;
	}

	private static class PowerCalculatingThread extends Thread {

		private BigInteger result = BigInteger.ONE;
		private BigInteger base;
		private BigInteger power;

		public PowerCalculatingThread(BigInteger base, BigInteger power) {
			this.base = base;
			this.power = power;
		}

		@Override
		public void run() {
			result = BigInteger.ONE;

			for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
				result = result.multiply(base);
			}
		}

		public BigInteger getResult() {
			return result;
		}
	}
}


/* ======== BELOW IS ANOTHER SOLUTION TO THE CHALLENGE ===================
 * Another way of solving the solution (less advanced than the first, as we
 * transform BigInteger to Long in order to iterate over values. Also, instead
 * of adding throws declaration in the method itself, we append a try/catch
 * block.
 */
class SolutionB {

	public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2)
			throws InterruptedException {

		BigInteger result = BigInteger.ZERO;
		/*
		 * Calculate result = ( base1 ^ power1 ) + (base2 ^ power2). Where each
		 * calculation in (..) is calculated on a different thread
		 */

		PowerCalculatingThread threadA = new PowerCalculatingThread(base1, power1);
		PowerCalculatingThread threadB = new PowerCalculatingThread(base2, power2);

		List<PowerCalculatingThread> threads = new ArrayList<>();
		threads.add(threadA);
		threads.add(threadB);

		for (PowerCalculatingThread t : threads) {
			t.start();
			t.join(); /*
						 * Remember that .join() tells the calling program to wait until the thread
						 * object upon which the join method has been called has finished its work.
						 */
			result = result.add(t.getResult());
		}

		return result;
	}

	private static class PowerCalculatingThread extends Thread {

		private BigInteger result = BigInteger.ONE;
		private BigInteger base;
		private BigInteger power;

		public PowerCalculatingThread(BigInteger base, BigInteger power) {
			this.base = base;
			this.power = power;
		}

		@Override
		public void run() {
			/*
			 * Implement the calculation of result = base ^ power
			 */
			for (long i = power.longValue(); i >= 1; i--) {
				result = result.multiply(base);
			}

		}

		public BigInteger getResult() {
			return result;
		}
	}
}