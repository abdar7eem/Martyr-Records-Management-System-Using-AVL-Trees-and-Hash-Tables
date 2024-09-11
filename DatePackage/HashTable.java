package DatePackage;

import java.util.ArrayList;

import MartyrScreen.Martyr;
import MartyrScreen.MartyrAVLTree;
import MartyrScreen.MartyrNode;

public class HashTable {
	private HNode[] hash = new HNode[11];
	private int size = 11;
	private int counter = 0;
	private Dstack stack=new Dstack();
	private Dstack tempStack=new Dstack();

	public HashTable() {
		for (int i = 0; i < hash.length; i++) {
			hash[i] = new HNode();
		}
	}
	public int getSize() {
		return size;
	}
	public HNode get(int index) {
		return hash[index];
	}
	public void insert(String  str) {
		if(search(str)!=-1) {
			return;
		}
		int k = hash(str);
		hash[k] = new HNode(str);
		hash[k].setState('F');
		counter++;
		if (counter>=size/2)
			rehash(getNextPrime(2*size));
	}
	private int getNextPrime(int y) {
		while (true) {
			if (isPrime(y))
				break;
			y++;
		}
		return y;
	}
	private boolean isPrime(int x) {
		if (x<=1) {
			return false;
		}
		for (int i=2;i<x;i++)
			if (x%i==0) {
				return false;
			}
		return true;
	}

	private void rehash(int nsize) {
		MartyrAVLTree Mavl=null;
		HNode [] h = hash;
		hash = new HNode[nsize];
		Def();
		counter = 0;
		size = nsize;
		for (int i = 0; i < h.length; i++) {
			if (h[i].getState() == 'F') {
				Mavl = h[i].getmAvl(); 
				insert(h[i].getData());
				hash[search(h[i].getData())].setmAvl(Mavl);
			}
		}
	}

	public String delete(String data) {
		int k = search(data);
		if (k != -1) {
			counter--;
			hash[k].setState('D');
			if (counter<=size/4) {
				rehash(getPrevPrime(size/2));
			}
			return data;
		} else
			return null;
	}

	private int getPrevPrime(int temp) {
		while (true) {
			if (isPrime(temp)) {
				break;
			}
			if (temp < 3) {
				temp = 3;
				break;
			}
			temp--;
		}

		return temp;
	}

	public int search(String data) {
		int res = Math.abs(data.hashCode());
		int k=1;
		int i= res%hash.length;
		int ptr = -1;
		while (hash[i].isFull()) {
			if (((hash[i].getData())).compareTo((data)) == 0){
				ptr=i;
				break;
			}
			i =(res+(int)Math.pow(k, 2))%hash.length;
			k++;
		}
		return ptr;
	}

	//	public Date convertDate(String d) {
	//		String[] tokens = d.split("/");
	//		int day = Integer.parseInt(tokens[1]);
	//		int month = Integer.parseInt(tokens[0]);
	//		int year = Integer.parseInt(tokens[2]);
	//		Date date = new Date(year - 1900, month - 1, day);
	//		return date;
	//	}

	private void Def() {
		for (int i = 0; i < hash.length; i++) {
			hash[i] = new HNode();
		}
	}

	private int hash(String data) {
		int h = Math.abs(data.hashCode()), j = 1, i = h % hash.length;
		while (hash[i].isFull()) {
			i = (h + (int) Math.pow(j, 2)) % hash.length;
			j++;
		}
		return i;
	}

	public void updateMyHash(String old, String data) {
		MartyrAVLTree temp=hash[search(old)].getmAvl();
		delete(old);
		insert(data);
		hash[search(data)].setmAvl(temp);
	}

	public void fillStack() {
		for(int i=0;i<hash.length;i++) {
			if(hash[i].getState()=='F') {
				tempStack.push(hash[i]);
			}
		}
		Rstack();
	}

	public void Rstack() {
		while(!tempStack.isEmpty()) {
			stack.push(tempStack.pop());
		}
	}

	public ArrayList<String> getDates(){
		ArrayList<String>arr=new ArrayList<>();
		for(int i=0;i<hash.length;i++) {
			if (hash[i].getState() == 'F') {
				arr.add(hash[i].getData());
			}
		}
		return arr;
	}

	public String getprint(boolean bol) {
		String str="";
		for (int i =0;i <hash.length;i++) {
			if (bol) {
				str+=("Index " + i + ": " + hash[i] + "\n");
			} else {
				if (hash[i].getState() == 'F') {
					str+=("Index " + i + ": " + hash[i] + "\n");
				}
			}
		}
		return str;
	}

	public HNode getFirstData() {
		for (int i = 0; i < hash.length; i++) {
			if (hash[i] != null && hash[i].getState() == 'F') {
				return hash[i];
			}
		}
		return null;
	}

	public ArrayList<String> searchDate(String name){
		ArrayList<String> arr=new ArrayList<>();

		for(int i=0; i<hash.length;i++) {
			if(hash[i].getState()=='F'){
				if(hash[i].getData().contains(name)){
					arr.add(hash[i].getData());
				}
			}
		}
		return arr;
	}
	public HNode[] getHash() {
		return hash;
	}

	public void setHash(HNode[] hash) {
		this.hash = hash;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Dstack getStack() {
		return stack;
	}

	public void setStack(Dstack stack) {
		this.stack = stack;
	}


}