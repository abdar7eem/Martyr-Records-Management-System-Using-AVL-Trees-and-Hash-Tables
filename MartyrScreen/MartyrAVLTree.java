package MartyrScreen;

import java.util.ArrayList;


public class MartyrAVLTree {
	private MartyrNode root; 
	int count=0;
	int ageSum=0;
	int dcount;
	int Lcount;
	String dmax="";
	String Lmax="";
	int size=0;
	int hight=0;
	Mstack stack=new Mstack();
	static Queue queue=new Queue();
	ArrayList <Martyr>arr=new ArrayList<>();
	ArrayList <Martyr>searchArr;
	Heap heap;

	int height(MartyrNode mn) { 
		if (mn == null) { 
			return 0;
		}
		return mn.height; 
	} 

	int max(int a, int b) { 
		return (a > b) ? a : b; 
	} 

	MartyrNode rightRotate(MartyrNode mn) { 
		MartyrNode temp = mn.left; 
		MartyrNode mn2= temp.right; 
		temp.right = mn; 
		mn.left = mn2; 
		mn.height = max(height(mn.left), height(mn.right)) + 1; 
		temp.height = max(height(temp.left), height(temp.right)) + 1; 
		return temp; 
	} 

	MartyrNode leftRotate(MartyrNode mn) { 
		MartyrNode temp = mn.right; 
		MartyrNode T2 = temp.left; 
		temp.left = mn; 
		mn.right = T2; 
		mn.height = max(height(mn.left), height(mn.right)) + 1; 
		temp.height = max(height(temp.left), height(temp.right)) + 1; 
		return temp; 
	} 

	int getBalance(MartyrNode mn) { 
		if (mn == null) { 
			return 0; 
		}
		return height(mn.left) - height(mn.right); 
	} 

	public void insert(Martyr data) {
		setRoot(insert(getRoot(), data));
	}

	public MartyrNode insert(MartyrNode node, Martyr data) {
		if(node==null) {
			node = new MartyrNode(data);
		}

		if(node.getData().getDistrict().compareTo(data.getDistrict())<0 ) {
			node.right= insert( node.right, data);
		}
		else if(node.getData().getDistrict().compareTo(data.getDistrict())>0) {
			node.left= insert(node.left, data);
		}
		else if (node.Data.getDistrict().compareTo((data.getDistrict()))==0){
			if(node.Data.getMname().compareTo(data.getMname())<0) {
				node.right=insert(node.right, data);
			}
			else if(node.Data.getMname().compareTo(data.getMname())>0) {
				node.left=insert(node.left, data);
			}

		}

		node.height = max(height(node.left), height(node.right)) + 1; 
		int bal = getBalance(node); 
		if (bal > 1 && getBalance(node.left) >= 0){
			return rightRotate(node); 
		}
		if (bal > 1 && getBalance(node.left) < 0){ 
			node.left = leftRotate(node.left); 
			return rightRotate(node); 
		} 
		if (bal < -1 && getBalance(node.right) <= 0) { 
			return leftRotate(node);
		}
		if (bal < -1 && getBalance(node.right) > 0) { 
			node.right = rightRotate(node.right); 
			return leftRotate(node); 
		} 
		return node;
	}
	MartyrNode minValueNode(MartyrNode node) { 
		MartyrNode current = node; 
		while (current.left != null) { 
			current = current.left; 
		}
		return current; 
	} 

	public void deleteNode(Martyr data) {
		setRoot(deleteNode(getRoot(),data));
	}
	private MartyrNode deleteNode(MartyrNode root, Martyr data) { 
		if (root == null){ 
			return root; 
		}
		if (data.getDistrict().compareTo(root.Data.getDistrict())<0){ 
			root.left = deleteNode(root.left, data); 
		}
		else if (data.getDistrict().compareTo(root.Data.getDistrict())>0){
			root.right = deleteNode(root.right, data); 
		}
		else if(data.getMname().compareTo(root.Data.getMname())<0) {
			root.left = deleteNode(root.left, data); 
		}
		else if (data.getMname().compareTo(root.Data.getMname())>0){
			root.right = deleteNode(root.right, data); 
		}
		else{ 
			if ((root.left == null) || (root.right == null)) { 
				MartyrNode temp = null; 
				if (temp == root.left){ 
					temp = root.right; 
				}
				else {
					temp = root.left; 
				}
				if (temp == null) { 
					temp = root; 
					root = null; 
				} 
				else {  
					root = temp; 
				}
			} 
			else{ 
				MartyrNode temp = minValueNode(root.right); 
				root.Data = temp.Data; 
				root.right = deleteNode(root.right, temp.Data); 
			} 
		}  
		if (root == null) { 
			return root; 
		}
		root.height = max(height(root.left), height(root.right)) + 1; 
		int bal = getBalance(root); 

		if (getBalance(root.left) >= 0 && bal > 1) { 
			return rightRotate(root); 
		}
		if (getBalance(root.left) < 0 && bal > 1) { 
			root.left = leftRotate(root.left); 
			return rightRotate(root); 
		} 
		if (getBalance(root.right) <= 0 && bal < -1) { 
			return leftRotate(root);
		}
		if (getBalance(root.right) > 0 && bal < -1 ) { 
			root.right = rightRotate(root.right); 
			return leftRotate(root); 
		} 
		return root; 
	}

	public void inOrder(MartyrNode node) { 
		if (node != null) { 
			inOrder(node.left); 
			inOrder(node.right); 
		} 
	} 

	public int totalMartyr() {
		ageSum=0;
		count=0;
		return totalMartyr(root);
	}

	private int totalMartyr(MartyrNode node) { 
		if (node != null) { 
			totalMartyr(node.left); 
			count++;
			totalMartyr(node.right); 
		}
		return count;
	} 

	public int avgMartyr() {
		ageSum=0;
		count=0;
		if(totalMartyr()!=0) {
			return avgMartyr(root)/totalMartyr();
		}else {
			return 0;
		}
	}

	private int avgMartyr(MartyrNode node) { 
		if (node != null) { 
			avgMartyr(node.left); 
			ageSum+=node.getData().getAge();
			avgMartyr(node.right); 
		}
		return ageSum;
	} 

	public int DistrictMar(String Dname) {
		dcount=0;
		return DistrictMar(root, Dname);
	}

	public int DistrictMar(MartyrNode node , String Dname) {
		if (node != null) { 
			if(node.getData().getDistrict().equals(Dname)) {
				dcount++;
			}
			DistrictMar(node.left, Dname); 
			DistrictMar(node.right, Dname); 
		}		
		return dcount;
	}


	public int LocationMar(String Lname) {
		Lcount=0;
		return LocationMar(root, Lname);
	}

	public int LocationMar(MartyrNode node , String Lname) {
		if (node != null) { 
			if(node.getData().getLocation().equals(Lname)) {
				Lcount++;
			}
			LocationMar(node.left, Lname); 
			LocationMar(node.right, Lname); 
		}		
		return Lcount;
	}

	public Martyr find(Martyr data) {
		return find(getRoot(), data);
	}

	public Martyr find(MartyrNode Mnode, Martyr Mdata) {
		if(Mnode==null) {
			return null;
		}
		if(Mnode.getData()==Mdata) {
			return Mnode.getData();
		}
		else {
			if(Mnode.getData().getMname().compareTo(Mdata.getMname())<0) {
				return find(Mnode.right, Mdata);
			}
			else{
				return find(Mnode.left, Mdata);
			}
		}
	}

	public String maxDistrict() {
		dmax="";
		if(root!=null) {
			dmax=root.getData().getDistrict();
			return maxDistrict(root);
		}
		else {
			return dmax;
		}
	}

	public String maxDistrict(MartyrNode node) {
		if(node !=null) {
			if(DistrictMar(node.getData().getDistrict())>DistrictMar(dmax)) {

				dmax=node.getData().getDistrict();
			}
			maxDistrict(node.left);	
			maxDistrict(node.right);
		}
		return dmax;
	}


	public String maxLocation() {
		Lmax="";
		if(root!=null) {
			Lmax=root.getData().getLocation();
			return maxLocation(root);
		}
		else {
			return Lmax;
		}
	}

	public String maxLocation(MartyrNode node) {
		if(node !=null) {
			if(LocationMar(node.getData().getLocation())>LocationMar(Lmax)) {
				Lmax=node.getData().getLocation();
			}
			maxLocation(node.left);	
			maxLocation(node.right);
		}
		return Lmax;
	}

	public int treeSize() {
		size=0;
		return treeSize(root);
	}

	private int treeSize(MartyrNode root) {
		if(root!=null) {
			treeSize(root.left);
			size++;
			treeSize(root.right);
		}
		return size;
	}

	public int treeHight() {
		if(root!=null) {
			hight=root.height;
		}
		return treeHight(root);
	}

	private int treeHight(MartyrNode root) {
		if(root!=null) {
			treeHight(root.left);
			treeHight(root.right);

			if(root.height>hight) {
				hight=root.height;
			}
			else if(root.height>hight) {
				hight=root.height;
			}	
		}
		return hight-1;
	}

	public MartyrNode getRoot() {
		return root;
	}

	public void setRoot(MartyrNode root) {
		this.root = root;
	}

	public void updateAvl(Martyr old, String name, String date, int age, String loc, String dis, String gender) {
		deleteNode(old);
		insert(new Martyr(name, date, age, loc, dis, gender));
	}

	public void NavigateLevelOreder(){
		NavigateLevelOreder(root);
		while(!queue.isEmpty()) {
			stack.push(queue.deQueue());
		}
	}

	static void NavigateLevelOreder(MartyrNode root) {
		if (root == null) {
			return;
		}
		Queue newq=new Queue();
		newq.enQueue(root); 
		while (!newq.isEmpty()) {
			MartyrNode curr = newq.deQueue();
			if (curr != null) {
				if (curr.right != null) {
					newq.enQueue(curr.right);
				}
				if (curr.left != null) {
					newq.enQueue(curr.left);
				}
				queue.enQueue(curr);    
			}
		}
	}

	public ArrayList<Martyr> getMar() {
		arr=new ArrayList<>();
		heap =new Heap(treeSize());
		return getMar(root);
	}

	public ArrayList<Martyr> getMar(MartyrNode node) {
		if(root!=null) {
			if(node.left!=null)
				getMar(node.left);
			heap.insert(node.getData());
			if(node.right!=null)
				getMar(node.right);
		}
		arr=heap.getMartyr();
		return heap.getMartyr() ;
	}

	public ArrayList<Martyr> searchMartyr(String name){
		arr.clear();
		searchArr=new ArrayList<>();
		return searchMartyr(root, name);
	}

	public ArrayList<Martyr> searchMartyr(MartyrNode root, String name){
		if(root!=null) {
			searchMartyr(root.left, name);
			if(root.getData().getMname().contains(name)) {
				arr.add(root.getData());
			}
			searchMartyr(root.right, name);
		}
		return arr;
	}

	public Mstack getStack() {
		return stack;
	}

	public void setStack(Mstack stack) {
		this.stack = stack;
	}
}