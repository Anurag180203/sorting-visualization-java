package Anurag.Sorts;

import java.util.ArrayList;

import Anurag.SortingVisualizer;
import Anurag.VisualizerFrame;

public class RadixSort implements Runnable{
	
	private Integer[] toBeSorted;
	private VisualizerFrame frame;
	
	public RadixSort(Integer[] toBeSorted, VisualizerFrame frame) {
		this.toBeSorted = toBeSorted;
		this.frame = frame;
	}
	
	public void run() {
		radixlsd(toBeSorted, 1);
		SortingVisualizer.isSorting=false;
	}
	
	private void radixlsd(Integer[] x, int digit){
		ArrayList<Integer>[] buckets = new ArrayList[10];
		for(int i = 0; i<10; i++){
			buckets[i] = new ArrayList<Integer>();
		}
		int theDig = 0;
		int maxI = 0;
		for(int i = 0; i<x.length; i++){
			theDig = (int) (x[i]%Math.pow(10, digit));
			for(int t = 0; t<digit-1; t++){
				theDig/=10;
			}
			if (x[i] > maxI) maxI = x[i];
			frame.reDrawArray(x, -1, -1, i);
			try {
				Thread.sleep(SortingVisualizer.sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			buckets[theDig].add(x[i]);
		}
		ArrayList<Integer> finalList = new ArrayList<>();
		for(int i = 0; i<10; i++){
			finalList.addAll(buckets[i]);
		}
		
		Integer[] y = finalList.toArray(new Integer[0]);
		
		frame.reDrawArray(y);
		try {
			Thread.sleep(SortingVisualizer.sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (maxI < Math.pow(10, digit)) return;

		radixlsd(y, digit+1);
	}
}
