package dynamicNavStack;

public class DynamicNavStack<E> {

	// navigation stack array
	private E[] navStack;
	// index of top of undo stack
	private int undoI;
	// index of top of redo stack
	private int redoI;
	// initial capacity of array
	private int initialCapacity;
	// capacity of array
	private int capacity;
	// default capacity of array
	private static final int DEFAULT_CAPACITY = 2;

	// constructor for when the capacity is not specified
	DynamicNavStack() {
		navStack = (E[]) new Object[DEFAULT_CAPACITY];
		undoI = 0;
		redoI = DEFAULT_CAPACITY - 1;
		initialCapacity = DEFAULT_CAPACITY;
		capacity = DEFAULT_CAPACITY;
	}

	// constructor for when the capacity is specified
	DynamicNavStack(int c) {
		navStack = (E[]) new Object[c];
		undoI = 0;
		redoI = c - 1;
		initialCapacity = c;
		capacity = c;
	}

	// returns the size of both arrays combined
	public int size() {
		return (undoI) + ((capacity - 1) - redoI);
	}

	// checks if an undo can be performed
	public boolean canUndo() {
		/* if the index undoI is at 0, that means the undo stack is empty, this will
		 * return false
		 */
		if (undoI == 0) {
			return false;
		}
		// if the undo stack is not empty, return true
		return true;
	}

	// checks if a redo can be performed
	public boolean canRedo() {
		/* if the index of redoI is 1 less than the capacity, the redo stack is empty,
		 * this will return false
		 */
		   
		if (redoI == capacity - 1) {
			return false;
		}
		// if the redo stack is not empty, return true
		return true;
	}

	// returns the element that is at the top of the undo stack
	public E undoTop() {
		// if there is nothing in the stack, return null
		if (canUndo() == false) {
			return null;
		}
		// otherwise return the value
		return navStack[undoI - 1];
	}

	// returns the element that is at the top of the redo stack
	public E redoTop() {
		// if there is nothing in the stack, return null
		if (canRedo() == false) {
			return null;
		}
		// otherwise return the value
		return navStack[redoI + 1];
	}

	// if the undo stack and redo stack are empty, return false
	public boolean isEmpty() {
		if ((canUndo() == false) && (canRedo() == false)) {
			return false;
		}
		// if they are not empty, return true
		return true;
	}

	// returns the capacity of the array
	public int capacity() {
		return capacity;
	}

	/* pushes a new element onto the undo stack and deletes whatever is in the redo
	 * stack
	 */
	public void push(E e) {

		// deletes the redo stack
		for (int i = capacity - 1; i > redoI; i--) {
			navStack[i] = null;
		}
		// resets the redoI index
		redoI = capacity - 1;

		// if size equals the capacity plus 1, double the size of the array
		if (size() + 1 == capacity) {
			resize(capacity * 2);
		}
		/* otherwise, while the size is less than the capacity divided by 4 and the 
		 * initial capacity is less than or equal to the capacity divided by 2, divide the size of the array by 2
		 */
		else {
			while((size() <= capacity / 4) && (initialCapacity <= capacity / 2)) {
				resize(capacity / 2);
			}
		}
		
		// adds the new element to the undo stack
		navStack[undoI++] = e;
	}

	/* pops value off undo stack and pushes it onto the redo stack as long as the
	 * undo stack is not empty
	 */
	public E undo() {

		// if the undo stack is empty, returns null
		if (canUndo() == false) {
			return null;
		}
		// sets temp equal to the top value in the stack
		E temp = navStack[undoI - 1];
		// deletes the top element of the undo stack and lowers the index of undoI by 1
		navStack[(undoI--) - 1] = null;
		// sets the index of redoI to temp and lowers redoI by 1
		navStack[redoI--] = temp;

		// returns the value
		return temp;
	}

	/* pops value off the redo stack and pushes it onto the undo stack as long as
	 * the redo stack is not empty
	 */
	public E redo() {

		// if the redo stack is empty, returns null
		if (canRedo() == false) {
			return null;
		}

		// sets temp equal to the top of the redo stack
		E temp = navStack[redoI + 1];
		// deletes the top element of the redo stack and raises the index of redoI by 1
		navStack[(redoI++) + 1] = null;
		// sets the index of undoI to temp and raises undoI by 1
		navStack[undoI++] = temp;

		// returns the value
		return temp;
	}

	// resizes the array
	private void resize(int newSize) {

		// initializes a new temporary array with the new size
		E[] temp = (E[]) new Object[newSize];

		// copies all of the contents of the undo stack to the new array
		for (int i = 0; i < undoI; i++) {
			temp[i] = navStack[i];
		}

		// sets the navigation stack equal to the temporary array
		navStack = temp;

		// sets the capacity to the new size
		capacity = newSize;

		// sets the redoI index to the capacity minus 1
		redoI = capacity - 1;
	}

	public String toString() {
		String ret = "Array Looks Like this: [";
		for (int i = 0; i < capacity; i++)
			if (navStack[i] != null)
				ret += navStack[i].toString() + " ";
			else
				ret += "null ";
		ret += "]\n";
		ret += "undo stack: [";
		for (int i = 0; i < undoI; i++)
			ret += navStack[i].toString() + " ";
		ret += "]\n";
		ret += "redo stack: [";
		for (int i = capacity - 1; i > redoI; i--)
			ret += navStack[i].toString() + " ";
		ret += "]";
		return ret;
	}
}

