package MartyrScreen;

import java.util.ArrayList;
public class Heap {
	static Martyr heap[] ;
	static int count=-1;
	Heap () {
		this(100);
	}
	Heap (int size){
		heap =new Martyr [size];
	}
	public static void heapify(Martyr heap[], int x, int y) {
		int	maximum =y;
		int a =1+(2*y);
		int	b =2+(2*y);
		if (heap[maximum]!= null &&a < x && heap[a]!= null&& heap[a].getAge() > heap[maximum].getAge()){
			maximum = a;
		}
		if (heap[maximum]!= null && b < x&& heap[b]!= null && heap[b].getAge() > heap[maximum].getAge()){
			maximum = b;
		}
		if (maximum != y) {
			Martyr temp = heap[y];
			heap[y] = heap[maximum];
			heap[maximum] = temp;
			heapify(heap, x, maximum);
		}
	}
	public ArrayList<Martyr> getMartyr() {
		ArrayList<Martyr> arr = new ArrayList<Martyr>();
		heapSort(heap);
		for(int i=0;i<heap.length;i++){
			if(heap[i]!=null)
				arr.add(heap[i]);
		}
		return arr; 
	}
	public  void insert(Martyr data) {
		if(isFull()) {
			return;
		}
		for (int i=0 ; i<heap.length;i++) {
			if(heap[i]==null) {
				heap [i]=data;
				count++;
				break;
			}
		}
		for (int i=heap.length/2-1;i>=0;i--) {
			heapify(heap, heap.length, i);
		}

	}
	public static void buildHeap(Martyr heap[], int lenght) {
		for (int i = lenght/2-1;i>=0;i--)
			heapify(heap, lenght, i);
	}
	public static void heapSort(Martyr heap[]) {
		buildHeap(heap, heap.length);
		for (int i = heap.length-1;i>=0;i--) {
			Martyr ptr = heap[0];
			heap[0] = heap[i];
			heap[i] = ptr;
			heapify(heap, i, 0);
		}
	}
	public boolean isFull() {
		return heap.length==count+1;
	}



}