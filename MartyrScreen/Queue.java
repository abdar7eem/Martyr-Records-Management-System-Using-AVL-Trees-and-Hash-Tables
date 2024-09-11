package MartyrScreen;

public class Queue{
	int front,rear;
	static int SIZE =1000;
	MartyrNode []queue;
	int counter=0;

	public Queue() {
		this(SIZE);
	}

	public Queue(int size) {
		this.queue=new MartyrNode[size];
		front=0;
		rear=size-1;
	}

	public boolean isEmpty() {
		return counter==0;
	}

	public boolean isFull() {
		return counter==getSize() ;
	}

	public void enQueue(MartyrNode data) {
		if(isFull()) {
			return;
		}
		else {
			rear=(rear+1)%getSize();
			queue[rear]=data;
			counter++;
		}
	}

	public MartyrNode deQueue( ) {
		if(isEmpty()) {
			return null;
		}
		MartyrNode temp = queue[front];
		front=(front+1)%getSize();
		counter--;
		return temp;
	}

	public  int getSize() {
		return queue.length;
	}
	
	public Object getRear() {
		if(isEmpty()) {
			return null;
		}
		return  queue[rear];
	}
	
	public MartyrNode getFront() {
		if(isEmpty()) {
			return null;
		}
		return  queue[front];
	}
	
	public MartyrNode peek() {
		if(isEmpty()) {
			return null;
		}
		return queue[rear];
	}

}