import static org.junit.Assert.*;

import org.junit.Test;

public class FitTest {

	@Test
	public void testBestFit() {
		double[] bins = { 0.2, 0.8, 0.4, 0.6, 0.1, 0.9, 0.1, 0.5, 0.5, 0.7, 0.3, 0.8, 0.2, 0.4, 0.6, 0.9, 0.1, 0.6, 0.4 };
		int size = bins.length;
		double[] used = new double[size];
		int numBins = 0;
		int binCost = FirstFit.bestFit(bins, size, used, numBins, 0, 100);
		assertEquals("cost not same: ", 10, binCost);
		
	}

}
