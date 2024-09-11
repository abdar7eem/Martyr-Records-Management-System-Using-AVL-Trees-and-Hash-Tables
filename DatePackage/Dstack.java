package DatePackage;

public class Dstack {
	HNode[] stack;
	static int SIZE=100;
	int top;
	public Dstack( ) {
		this(SIZE);
	}
	public Dstack( int size) {
		super();
		stack = new HNode [size];
		this.top = -1;
	}
	public int getSize() {
		return stack.length;
	}
	public boolean isFull() {
		if(top>=getSize()-1) {
			return true;
		}else {
			return false;
		}
	}
	public boolean isEmpty() {
		if(top<0) {
			return true;
		}else {
			return false;
		}
	}
	public boolean push(HNode data) {
		if(isFull()) {
			return false;
		}
		else {
			top++;
			stack[top]=data;
			return true;
		}
	}
	public HNode pop() {
		if(isEmpty()) {
			return null;
		}
		else {
			return stack[top--];
		}
	}
	public HNode peek() {
		if(isEmpty()) {
			return null;
		}
		else {
			return stack[top];
		}
	}
	public void clear(Dstack s) {
		while(!s.isEmpty()) {
			s.pop();
		}
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
}