package DatePackage;

import MartyrScreen.MartyrAVLTree;

public class HNode {
	private String data;
	private char state = 'E';
	MartyrAVLTree mAvl=new MartyrAVLTree();
	public HNode() {
	}

	public HNode(String object) {
		this.data = object;
	}

	public char getState() {
		return state;
	}

	public void setState(char state) {
		if (state == 'D' || state == 'E' || state == 'F')
			this.state = state;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public boolean isFull() {
		return state != 'D' && state != 'E';
	}

	@Override
	public String toString() {
		return data ;
	}

	public MartyrAVLTree getmAvl() {
		return mAvl;
	}

	public void setmAvl(MartyrAVLTree mAvl) {
		this.mAvl = mAvl;
	}
}
