package labs;
import java.util.Arrays;
import java.util.Scanner;

public class arraysearch {
	
	
	public static int binarySearch(int[] array, int x, int low, int high) {
		 if (low > high) return -1; 
	      int mid = (low + high)/2;
	      if (array[mid] == x) return mid;
	      else if (array[mid] < x)
	         return binarySearch(array, x, mid+1, high);
	      else // last possibility: a[mid] > x
	         return binarySearch(array, x, low, mid-1);
	}

	public static void main(String[] args) {
		Scanner kb =  new Scanner (System.in);
		System.out.println("array length: ");
		int length = kb.nextInt();
		
		int[] array = new int [length];
		
		for(int i = 1; i <= length; i++){
			System.out.println("array element"+i+":");
			array[i-1] = kb.nextInt();
		}
		System.out.println("searchKey:");
		int search = kb.nextInt();
		
		
		Arrays.sort(array);
		int low = array[0];
		int high = array[array.length - 1];
		System.out.println("sorted**low = "+low+"// high ="+high);
		
		System.out.println("sorted**index**low = 0  // high ="+ (array.length - 1));
		
		
		System.out.println("Binary Search:");
		int pos = binarySearch(array, search, 0, array.length - 1);
		
		if(pos < 0){
			System.out.println("Element not found!");
		} else{
			System.out.println(" first occurrence at: " + pos);
		}
	}
}

