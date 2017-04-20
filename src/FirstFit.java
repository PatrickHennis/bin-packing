import java.util.Random;

public class FirstFit {
	public static void main(String[] args) {
		// double[] binsToBePlaced = { 0.5, 0.7, 0.3, 0.9, 0.6, 0.8, 0.1, 0.4, 0.2, 0.5 };
		int numberOfTests = 100;
		double[] binsToBePlaced = new double[numberOfTests];
		for (int i = 0; i < numberOfTests + 1; i++) {
			final Random r = new Random();
			for (int j = 0; j < 100; j++) {
				int n = r.nextInt(100) + 1;
				double d = n / 100.0;
				binsToBePlaced[j] = d;
			}
			int size = binsToBePlaced.length; // size of instance
			double[] used = new double[size]; // fraction of bin occupied
			int numBins = 0; // number of nonempty bins

			firstFit(binsToBePlaced, size, used, numBins, i, numberOfTests);
			used = new double[size];
			bestFit(binsToBePlaced, size, used, numBins, i, numberOfTests);
			used = new double[size];
			worstFit(binsToBePlaced, size, used, numBins, i, numberOfTests);
		}
	}

	public static int firstFit(double[] binsToBePlaced, int size, double[] used, int numBins, int testCase, int numberOfTests) {
		double currBinToBePlaced;
		int performance = 0;

		for (int i = 0; i < size; i++) {
			performance++;

			// find location for item i
			currBinToBePlaced = binsToBePlaced[i];

			int j = 0;
			boolean placed = false;
			while (!placed) {
				if (used[j] + currBinToBePlaced <= 1.0) {
					used[j] += currBinToBePlaced;
					placed = true;
				}
				j++;
				performance++;
			}
		}

		System.out.println("First fit:");
		System.out.println("Test case: " + testCase + "/" + numberOfTests);
		System.out.println("Bins used: " + getSize(used));
		printBins(used, performance);
		return getSize(used);
	}

	// put in smallest bin that != 0
	public static int worstFit(double[] binsToBePlaced, int size, double[] used, int numBins, int testCase, int numberOfTests) {
		int usedSize = 1;
		double currBinToBePlaced;
		int performance = 0;

		for (int i = 0; i < size; i++) {
			performance++;
			currBinToBePlaced = binsToBePlaced[i];
			int leastBinLoc = 0;

			// find least bin
			for (int j = 0; j < usedSize; j++) {
				if (used[j] < used[leastBinLoc]) {
					leastBinLoc = j;
				}
				performance++;
			}

			// if smallest bin + current bin to be placed > 1, then make new bin
			// and increase size
			if (used[leastBinLoc] + currBinToBePlaced > 1.0) {
				used[usedSize] = currBinToBePlaced;
				usedSize++;
				// else, add it to the smallest bin
			} else {
				used[leastBinLoc] += currBinToBePlaced;
			}
		}

		System.out.println("Worst fit:");
		System.out.println("Test case: " + testCase + "/" + numberOfTests);
		System.out.println("Bins used: " + usedSize);
		printBins(used, performance);
		return getSize(used);
	}

	// put in largest bin that won't go over 1
	public static int bestFit(double[] binsToBePlaced, int size, double[] used, int numBins, int testCase, int numberOfTests) {
		int usedSize = 1;
		double currBinToBePlaced;
		int performance = 0;

		for (int i = 0; i < size; i++) {
			performance++;
			currBinToBePlaced = binsToBePlaced[i];
			int greatestBinLoc = 0;

			// find greatest bin
			for (int j = 0; j < usedSize; j++) {
				performance++;
				if (used[j] > used[greatestBinLoc] && used[j] + currBinToBePlaced <= 1.0) {
					greatestBinLoc = j;
				}
			}

			// if greatest bin + current bin to be placed > 1, then make new bin
			// and increase size
			if (used[greatestBinLoc] + currBinToBePlaced > 1.0) {
				used[usedSize] = currBinToBePlaced;
				usedSize++;
				// else, add it to the smallest bin
			} else {
				used[greatestBinLoc] += currBinToBePlaced;
			}
		}

		System.out.println("Best fit:");
		System.out.println("Test case: " + testCase + "/" + numberOfTests);
		System.out.println("Bins used: " + usedSize);
		printBins(used, performance);
		return getSize(used);
	}

	public static void printBins(double[] used, int performance) {
		System.out.print("[");
		for (int i = 0; i < used.length; i++) {
			System.out.print(used[i]);
			if (i != used.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("]");
		System.out.print("Performance: " + performance + "\n\n");
	}

	public static int getSize(double[] used) {
		int size = 0;
		for (int i = 0; i < used.length; i++) {
			if (used[i] != 0.0) {
				size++;
			}
		}

		return size;
	}
}