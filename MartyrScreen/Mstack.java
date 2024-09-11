package MartyrScreen;

public class Mstack {
	MartyrNode [] stack;
	static int SIZE=100;
	int top;
	public Mstack( ) {
		this(SIZE);
	}
	public Mstack( int size) {
		super();
		stack = new MartyrNode [size];
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
		}
		else {
			return false;
		}
	}
	public boolean push(MartyrNode data) {
		if(isFull()) {
			return false;
		}
		else {
			top++;
			stack[top]=data;
			return true;
		}
	}


	public MartyrNode pop() {
		if(isEmpty()) {
			return null;
		}
		else {
			return stack[top--];
		}
	}

	public MartyrNode peek() {
		if(isEmpty()) {
			return null;
		}
		else {
			return stack[top];
		}
	}

	public void clear(Mstack s) {
		while(!s.isEmpty()) {
			s.pop();
		}
	}//////
	public String getPrintStack(Mstack s1) {
		String str="";
		Mstack s2  = new Mstack(s1.getSize());
		while(!s1.isEmpty()) {
			s2.push(s1.pop());
			str+=(s2.peek().getData() +"\n");
		}
		while(!s2.isEmpty()) {
			s1.push(s2.pop());
		}
		return str;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
}