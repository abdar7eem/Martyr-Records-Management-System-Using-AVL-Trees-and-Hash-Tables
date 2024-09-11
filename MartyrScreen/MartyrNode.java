package MartyrScreen;


public class MartyrNode {
	Martyr Data;
	int height=0; 
    MartyrNode left, right; 
  
    MartyrNode(Martyr d) { 
        Data = d; 
    }

	public Martyr getData() {
		return Data;
	}

	public void setData(Martyr data) {
		Data = data;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public MartyrNode getLeft() {
		return left;
	}

	public void setLeft(MartyrNode left) {
		this.left = left;
	}

	public MartyrNode getRight() {
		return right;
	}

	public void setRight(MartyrNode right) {
		this.right = right;
	}
    
    
        
}