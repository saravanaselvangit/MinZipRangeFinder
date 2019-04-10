import java.util.Scanner;

public class ZipRangeFinder {
	
	public static void main(String a[]) {
		
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		
		System.out.println("Input : " + input);
		
		if(input == null || input.length() == 0) {
			System.out.println("Invlid input !");
			System.exit(0);
		}		
		
		ZipRange[] parsedZipRange = parseInput(input);
		
		System.out.println("parsedZipRange : ");
		for(ZipRange i : parsedZipRange) {
			System.out.printf("[%d,%d]", i.getStart(), i.getEnd());
		}			
		
		sort(parsedZipRange, 0, parsedZipRange.length-1);
		
		System.out.println("Sorted Zip Range : ");
		for(ZipRange i : parsedZipRange) {
			System.out.printf("[%d,%d]", i.getStart(), i.getEnd());
		}	
		
		
		String[] minRange = findMinRange(parsedZipRange);
		
		System.out.println("minRange : ");
		for(String output: minRange) {
			if(output != null)
				System.out.print(output + " ");
		}
		
	}

	private static String[] findMinRange(ZipRange[] parsedZipRange) {
		int pointer1 = parsedZipRange[0].getStart();
		int pointer2 = parsedZipRange[0].getEnd();
		String[] output = new String[parsedZipRange.length];
		int i=0;
		
		for(int j=1;j<parsedZipRange.length;j++) {
		
			if(parsedZipRange[j].getStart()-pointer2 > 1) {				
				output[i++] = String.format("[%d,%d]", pointer1, pointer2);				
				pointer1 = parsedZipRange[j].getStart();
				pointer2 = parsedZipRange[j].getEnd();
			}else if(parsedZipRange[j].getStart() >= pointer1 && parsedZipRange[j].getStart() <= pointer2+1) {				
				pointer2 = parsedZipRange[j].getEnd();				
			}			
			if(j == parsedZipRange.length-1)
				output[i++] = String.format("[%d,%d]", pointer1, pointer2);
		}
		
		return output;
	}
	

	private static void sort(ZipRange[] parsedZipRange, int left, int right) {
		int i = left;
		int j = right;
		
		int pivot = parsedZipRange[i+(j-i)/2].getStart();
		
		while(i <= j) {
			while(parsedZipRange[i].getStart() < pivot) {
				i++;
			}
			
			while(parsedZipRange[j].getStart() > pivot) {
				j--;
			}		
		
		
			if(i <= j) {
				swap(parsedZipRange, i , j);
				i++;
				j--;
			}
		
		if(i < right) {
			sort(parsedZipRange, i, right);
		}
		
		if(j > left) {
			sort(parsedZipRange, left, j);
		}
	}
		
	}
	

	private static void swap(ZipRange[] parsedZipRange, int i, int j) {
		ZipRange tmp = parsedZipRange[i];
		parsedZipRange[i] = parsedZipRange[j];
		parsedZipRange[j] = tmp;		
	}

	private static ZipRange[] parseInput(String input) {		
		String[] inputRanges = input.split("\\s");
		ZipRange[] parsedInputArray = new ZipRange[inputRanges.length];
		int i=0;
		for(String eachRange: inputRanges) {
			String[] rangeValues = eachRange.split(",");
			if(rangeValues == null || rangeValues.length < 2 ) {
				System.out.println("Invlid input !");
				System.exit(0);
			}	
			ZipRange zipRange = new ZipRange();
			zipRange.setStart(Integer.parseInt(rangeValues[0].substring(1, 6)));
			zipRange.setEnd(Integer.parseInt(rangeValues[1].substring(0, 5)));
			parsedInputArray[i++] = zipRange;		
		}
		return parsedInputArray;
	}

}
