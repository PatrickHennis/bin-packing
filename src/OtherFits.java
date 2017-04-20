import java.util.Arrays;

public class OtherFits {
	static double[] worstFitBins;
	static double[] bestFitBins;
	
	public static int WorstFit(double[] weights) {
		worstFitBins = new double[weights.length];
		for (int i = 0; i < weights.length; i++) {
			findWorstPlace(weights[i]);
		}
		int totalBins = 0;
		for (int i = 0; i < worstFitBins.length; i++) {
			if (worstFitBins[i] != 0) {
				totalBins++;
			}
		}
		return totalBins;
	}
	
	public static void findWorstPlace(double weight) {
		int worstPos = 0;
		for (int i = 0; i < worstFitBins.length; i++) {
			if (worstFitBins[i] < worstFitBins[worstPos] && worstFitBins[i] != 0) {
				worstPos = i;
			}
		}
		int firstOpenPos = -1;
		for (int i = 0; i < worstFitBins.length; i++) {
			if (worstFitBins[i] == 0) {
				firstOpenPos = i;
				break;
			}
		}
		if (worstFitBins[worstPos] + weight < 1.0) {
			worstFitBins[worstPos] += weight;
		} else {
			if (firstOpenPos != -1) {
				worstFitBins[firstOpenPos] = weight;
			}
		}
	}
	
	public static void worstFitPrint() {
		double[] weights = {0.7, 0.6, 0.6, 0.5, 0.4, 0.3, 0.2, 0.2, 0.1, 0.1, 0.1};
		int actual = WorstFit(weights);
		System.out.println("********* Worst Fit *********");
		System.out.println("num of bins: " + actual);
		System.out.print("bins: [");
		for (int i = 0; i < worstFitBins.length; i++) {
			System.out.print(worstFitBins[i] + ", ");
		}
		System.out.println("]");
		System.out.println("*****************************");
	}

	public static int BestFit(double[] weights) {
		Arrays.sort(weights);
		
		for(int i = 0, j =  weights.length-1; i < (weights.length/2); i++, j--){
            Double temp = weights[i];
            weights[i] = weights[j];
            weights[j] = temp;          
        }
		
		bestFitBins = new double[weights.length];
	    for (int i = 0; i < weights.length; i++) {
	    	findBestPlace(weights[i]);
	    }
	    int totalBins = 0;
		for (int i = 0; i < bestFitBins.length; i++) {
			if (bestFitBins[i] != 0) {
				totalBins++;
			}
		}
		return totalBins;
	}
	
	public static void findBestPlace(double weight) {
		int bestPos = -1;
		for (int i = 0; i < bestFitBins.length; i++) {
			if (bestFitBins[i] + weight <= 1) {
				if (bestPos == -1) {
					bestPos = i;
				} else if (bestFitBins[i] > bestFitBins[bestPos]) {
					bestPos = i;
				}
			}
		}
		int firstOpenPos = -1;
		for (int i = 0; i < bestFitBins.length; i++) {
			if (bestFitBins[i] == 0) {
				firstOpenPos = i;
				break;
			}
		}
		if (bestFitBins[bestPos] + weight <= 1.0) {
			bestFitBins[bestPos] += weight;
		} else {
			if (firstOpenPos != -1) {
				bestFitBins[firstOpenPos] = weight;
			}
		}
		
	}
	
	public static void bestFitPrint() {
		double weights[] = {0.2, 0.5, 0.4, 0.7, 0.1, 0.3, 0.8};
//		double weights[] = {0.4, 0.8, 0.5, 0.1, 0.7, 0.6, 0.1, 0.4, 0.2, 0.2};
		
		System.out.println("********* Best Fit *********");
		System.out.print("weights: [");
		for (int i = 0; i < weights.length; i++) {
			System.out.print(weights[i] + ", ");
		}
		System.out.println("]");
		System.out.println("num of bins: " + BestFit(weights));
		System.out.print("bins: [");
		for (int i = 0; i < bestFitBins.length; i++) {
			System.out.print(bestFitBins[i] + ", ");
		}
		System.out.println("]");
		System.out.println("*****************************");
	}
	
	
	public static void main(String[] args) {
		worstFitPrint();
		bestFitPrint();
	}
	
}
