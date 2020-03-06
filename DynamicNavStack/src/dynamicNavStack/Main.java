package dynamicNavStack;

import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		System.out.println("Creating a dynamic navigation stack.");
		DynamicNavStack<String> test = new DynamicNavStack<>();

		System.out.println("Size: " + test.size());
		System.out.println("Capacity: " + test.capacity());

		System.out.println("Pushing an action onto stack.");
		test.push("action1");

		System.out.println("Size: " + test.size());
		System.out.println("Capacity: " + test.capacity());

		System.out.println("Performed undo and got: " + test.undo());
		System.out.println("Top of undo stack: " + test.undoTop());
		System.out.println("Top of redo stack: " + test.redoTop());

		System.out.println("Size: " + test.size());
		System.out.println("Capacity: " + test.capacity());

		System.out.println("Pushing an action onto stack.");
		test.push("action2");

		System.out.println("Size: " + test.size());
		System.out.println("Capacity: " + test.capacity());

		System.out.println("Performed undo and got: " + test.undo());
		System.out.println("Top of undo stack: " + test.undoTop());
		System.out.println("Top of redo stack: " + test.redoTop());

		System.out.println("Size: " + test.size());
		System.out.println("Capacity: " + test.capacity());

		System.out.println("Pushing 2 actions onto stack.");
		test.push("action3");
		test.push("action4");

		System.out.println("Size: " + test.size());
		System.out.println("Capacity: " + test.capacity());

		System.out.println(test);

		System.out.println("Performed undo and got: " + test.undo());

		System.out.println(test);

		System.out.println("Performed redo and got: " + test.redo());

		System.out.println(test);
	}
}