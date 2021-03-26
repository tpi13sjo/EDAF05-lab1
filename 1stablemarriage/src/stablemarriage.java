import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class stablemarriage {
	
	public static void main(String[] args) {
		
		int N = 0;
		boolean[] wSeen; 
		int[][] mPref = null;
		int[][] wPref = null;
		
		File sample1 = new File("../EDAF05-labs-public/1stablemarriage/data/sample/1.in");
		File sample2 = new File("../EDAF05-labs-public/1stablemarriage/data/sample/2.in");
		
		File secret0 = new File("../EDAF05-labs-public/1stablemarriage/data/secret/0testsmall.in");
		File secret1 = new File("../EDAF05-labs-public/1stablemarriage/data/secret/1testsmallmessy.in");
		File secret2 = new File("../EDAF05-labs-public/1stablemarriage/data/secret/2testmid.in");
		File secret3 = new File("../EDAF05-labs-public/1stablemarriage/data/secret/3testlarge.in");
		File secret4 = new File("../EDAF05-labs-public/1stablemarriage/data/secret/4testhuge.in");
		File secret5 = new File("../EDAF05-labs-public/1stablemarriage/data/secret/5testhugemessy.in");
		
//		File solution = new File(args[0]);

//		File file = solution;
//		System.out.println(file);
//		try {
			Scanner in = new Scanner(System.in);
			N = Integer.parseInt(in.nextLine());
//			Scanner sc = new Scanner(file);
//			N = Integer.parseInt(sc.nextLine());
			mPref = new int[N][N];
			wPref = new int[N][N];
			wSeen = new boolean[N];
//			while (sc.hasNext()) {
			while (in.hasNext()) {
//				String nextNotSplit = sc.nextLine();
				String nextNotSplit = in.nextLine();
				String[] nextDirty = nextNotSplit.split(" ");
				int nextPerson = Integer.parseInt(nextDirty[0]);
				ArrayList<Integer> next = new ArrayList<Integer>();
				for(int i = 1; i < nextDirty.length; i++) {
					int nextNumber = Integer.parseInt(nextDirty[i]);
					next.add(nextNumber);
//					if(!next.contains(nextNumber)) {
//						next.add(nextNumber);
//					}
				}
				if(wSeen[nextPerson-1]) {
					for(int i = 0; i < next.size(); i++) {
						int wNext = next.get(i);
						mPref[nextPerson-1][i] = wNext;
					}
				} else {
					for(int i = 0; i < next.size(); i++) {
						wSeen[nextPerson-1] = true;
						int mNext = next.get(i);
						wPref[nextPerson-1][mNext-1] = i+1;
					}
				}
			}
//			sc.close();
			in.close();
//		} catch (FileNotFoundException e) {
//			System.out.println("File not found");
//		}
		
		ArrayList<Integer> p = new ArrayList<Integer>(); 
		for(int i = 1; i <= N; i++) {
			p.add(i);
		}
		int man = 0;
		int woman = 0;
		int[] mProposed = new int[N];
		boolean[] wProposed = new boolean[N];
		int[] pairs = new int[N]; //Woman as index
		while(!p.isEmpty()) {
			man = p.remove(0);
			woman = mPref[man-1][mProposed[man-1]];
//			System.out.println("Man: " + man);
//			System.out.println("Woman: " + woman);
			mProposed[man-1]++;
			if(!wProposed[woman-1]) {
//				System.out.println("Man: " + man + " proposed to woman: " + woman);
				pairs[woman-1] = man;
				wProposed[woman-1] = true;
			}
			else if(wPref[woman-1][man-1] > wPref[woman-1][pairs[woman-1]-1]) {
//				System.out.println("Woman: " + woman + " prefer " + man + " to " + pairs[woman-1]);
				p.add(pairs[woman-1]);
				pairs[woman-1] = man;
			}
			else {
//				System.out.println("Man: " + man + " did not propose to " + woman);
				p.add(man);
			}
		}
		
		for(int i = 0; i < N; i++) {
			System.out.println(pairs[i]);
		}
	}
}
