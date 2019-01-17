//Scott Ha
//CS 3310

package Sort;
import java.util.Random;

public class Sort {

public static void main(String[] args) {

int quickArray[], mergeArray[];
long start, end;

//size of arrays to be sorted
int sizeArray[]={10000, 20000, 50000, 100000, 500000, 1000000, 1500000, 2000000};

	for(int size: sizeArray) {

		System.out.println("\n\nSIZE: "+size);
		quickArray=getRandomArray(size);
		mergeArray=getRandomArray(size);
		System.out.printf("%30s%30s\n\n", "Type of sorting", "Time in ns");
		//calculate time
		start=System.nanoTime();
		quickSort(quickArray, 0, size-1);
		end=System.nanoTime();
		System.out.printf("%30s%30d\n", "Quick Sort", end-start);
		start=System.nanoTime();
		mergeSort(mergeArray, 0, size-1);
		end=System.nanoTime();
		System.out.printf("%30s%30d\n", "Merge Sort", end-start);
		}
}

//random array generator
public static int[] getRandomArray(int size) {

	int array[]=new int[size];
	Random rand = new Random();
	
	//generate random int for each input array size n
	for(int i=0; i<array.length; i++)
		array[i]=rand.nextInt();
		return array;
}

public static void quickSort(int array[], int low, int high) {

	if(low>high)
	return;

	//pIndex is partitioning index
	int pIndex=getPartitionIndex(array, low, high);	
	//Recursively sort elements before and after partition
	quickSort(array, low, pIndex-1);	
	quickSort(array, pIndex+1, high);
}

public static int getPartitionIndex(int array[], int low,int high) {
	
	int pivot=array[high];
	int pIndex=low;	//index of smaller element

		for(int i=low; i<high; i++) {
			
			if(array[i]<pivot) {	//if element is smaller than pivot
				int temp=array[i];	//swap elements array[i] and array[pindex]
				array[i]=array[pIndex];
				array[pIndex]=temp;
				pIndex++;
			}
		}	
		
		array[high]=array[pIndex];	//swap array[high] and array[pindex]
		array[pIndex]=pivot;	
		return pIndex;
}

public static void mergeSort(int array[], int low, int high) {

	if(low>=high) 
	return;
	
	int mid=(low+high)/2;	//Find middle of array
	mergeSort(array, low, mid); //sort first array	
	mergeSort(array, mid+1, high); //sort second array
	merge(array, low, mid, high); //Merge the sorted halves
}

public static void merge(int array[], int low, int mid, int high) {

	//initial index of array
	int index=0;			
	int i=low;				
	int j=mid+1;
	int ar[]=new int[high-low+1];
	
	//initial index of merged subarray
	while(i<=mid && j<=high) {
		if(array[i]<array[j]) {
		ar[index++]=array[i];
		i++;
		}

		else {
			ar[index++]=array[j];
			j++;
		}
	}
	
	//copy remaining elements of lower subarray
	while(i<=mid) {
		
		ar[index++]=array[i++];
	}
	
	//copy remaining elements of upper subarray
	while(j<=high) {

		ar[index++]=array[j++];
}

System.arraycopy(ar, 0, array, low, high-low+1);
	}
}