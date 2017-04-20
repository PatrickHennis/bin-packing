import static org.junit.Assert.*;

import org.junit.Test;

public class FitTest {

	@Test
	public void testBestFit() {
		double[] bins = { 0.2, 0.8, 0.4, 0.6, 0.1, 0.9, 0.1, 0.5, 0.5, 0.7, 0.3, 0.8, 0.2, 0.4, 0.6, 0.9, 0.1, 0.6, 0.4 };
		int size = bins.length;
		int binCost = OtherFits.BestFit(bins);
		double totalBinWeight = 0;
		for (int i = 0; i < size; i++) {
			totalBinWeight += bins[i];
		}
		int expected = (int)Math.ceil(totalBinWeight);
		assertEquals("cost not same: ", expected, binCost);
	}
	
	@Test
	public void testBestFit2() {
		double[] bins = { 0.2, 0.8, 0.4, 0.6, 0.1, 0.9, 0.1, 0.5, 0.9, 0.7, 0.3, 0.8, 0.2, 0.9, 0.6, 0.9, 0.2, 0.6, 0.4 };
		int size = bins.length;
		int binCost = OtherFits.BestFit(bins);
		double totalBinWeight = 0;
		for (int i = 0; i < size; i++) {
			totalBinWeight += bins[i];
		}
		int expected = (int)Math.ceil(totalBinWeight);
		assertEquals("cost not same: ", expected, binCost);
	}

}
