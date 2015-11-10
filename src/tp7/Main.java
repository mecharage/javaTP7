package tp7;

import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static final int ETUD_COUNT = 5_000_000, SAMPLES = 10;

	public static void main(String[] args) throws InterruptedException {

		for (int threadCount = 1; threadCount <= 15; ++threadCount) {
			long meanTime = 0;
			
			for (int sample = 0; sample < SAMPLES; ++sample) {
				final int batchCount = ETUD_COUNT / threadCount;

				final ArrayList<ArrayList<Etudiant>> lists = new ArrayList<>();
				final ArrayList<Thread> threads = new ArrayList<>();

				long c1 = System.nanoTime();

				for (int i = 0; i < threadCount; ++i) {
					final int batchNum = i;
					final ArrayList<Etudiant> list = new ArrayList<>();
					final Thread thread = new Thread(new Runnable() {
						@Override
						public void run() {
							final Random rng = new Random();
							final int first = batchNum * batchCount, last = Math.min(first + batchCount, ETUD_COUNT);
							for (int i = first; i < last; ++i)
								list.add(EtudiantGenerator.generate(rng));
						}
					});

					lists.add(list);
					threads.add(thread);

					thread.start();
				}

				final ArrayList<Etudiant> finalList = new ArrayList<>();

				for (int i = 0; i < threadCount; ++i) {
					threads.get(i).join();
					finalList.addAll(lists.get(i));
				}

				long c2 = System.nanoTime();
				
				double sampleWeight = 1.0 / (sample + 1);
				meanTime = (long) ((1.0 - sampleWeight) * meanTime + sampleWeight * (c2 - c1));
				System.out.print('.');
			}
			System.out.println("Mean time taken by " + threadCount + " threads in "+ SAMPLES +" samples : " + meanTime / 1_000_000_000.0f + "s");
		}
	}
}
