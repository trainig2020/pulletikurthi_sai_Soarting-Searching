package com.satya.binary;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BinaryTreeDemo {

	Node root;

	public void add(int value) {
		root = addRecursive(root, value);
	}

	private Node addRecursive(Node current, int value) {

		if (current == null) {
			return new Node(value);
		}

		if (value < current.value) {
			current.left = addRecursive(current.left, value);
		} else if (value > current.value) {
			current.right = addRecursive(current.right, value);
		}

		return current;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public int getSize() {
		return getSizeRecursive(root);
	}

	private int getSizeRecursive(Node current) {
		return current == null ? 0 : getSizeRecursive(current.left) + 1 + getSizeRecursive(current.right);
	}

	public boolean containsNode(int value) {
		return containsNodeRecursive(root, value);
	}

	private boolean containsNodeRecursive(Node current, int value) {
		if (current == null) {
			return false;
		}

		if (value == current.value) {
			return true;
		}

		return value < current.value ? containsNodeRecursive(current.left, value)
				: containsNodeRecursive(current.right, value);
	}

	public void delete(int value) {
		root = deleteRecursive(root, value);
	}

	private Node deleteRecursive(Node current, int value) {
		if (current == null) {
			return null;
		}

		if (value == current.value) {
			// Case 1: no children
			if (current.left == null && current.right == null) {
				return null;
			}

			// Case 2: only 1 child
			if (current.right == null) {
				return current.left;
			}

			if (current.left == null) {
				return current.right;
			}

			// Case 3: 2 children
			int smallestValue = findSmallestValue(current.right);
			current.value = smallestValue;
			current.right = deleteRecursive(current.right, smallestValue);
			return current;
		}
		if (value < current.value) {
			current.left = deleteRecursive(current.left, value);
			return current;
		}

		current.right = deleteRecursive(current.right, value);
		return current;
	}

	private int findSmallestValue(Node root) {
		return root.left == null ? root.value : findSmallestValue(root.left);
	}

	public void traverseInOrder(Node node) {
		if (node != null) {
			traverseInOrder(node.left);
			visit(node.value);
			traverseInOrder(node.right);
		}
	}

//	public void traversePreOrder(Node node) {
//		if (node != null) {
//			visit(node.value);
//			traversePreOrder(node.left);
//			traversePreOrder(node.right);
//		}
//	}
//
//	public void traversePostOrder(Node node) {
//		if (node != null) {
//			traversePostOrder(node.left);
//			traversePostOrder(node.right);
//			visit(node.value);
//		}
//	}
//
//	public void traverseLevelOrder() {
//		if (root == null) {
//			return;
//		}
//
//		Queue<Node> nodes = new LinkedList<>();
//		nodes.add(root);
//
//		while (!nodes.isEmpty()) {
//
//			Node node = nodes.remove();
//
//			System.out.print(" " + node.value);
//
//			if (node.left != null) {
//				nodes.add(node.left);
//			}
//
//			if (node.right != null) {
//				nodes.add(node.right);
//			}
//		}
//	}
//
//	public void traverseInOrderWithoutRecursion() {
//		Stack<Node> stack = new Stack<Node>();
//		Node current = root;
//		stack.push(root);
//		while (!stack.isEmpty()) {
//			while (current.left != null) {
//				current = current.left;
//				stack.push(current);
//			}
//			current = stack.pop();
//			visit(current.value);
//			if (current.right != null) {
//				current = current.right;
//				stack.push(current);
//			}
//		}
//	}
//
//	public void traversePreOrderWithoutRecursion() {
//		Stack<Node> stack = new Stack<Node>();
//		Node current = root;
//		stack.push(root);
//		while (!stack.isEmpty()) {
//			current = stack.pop();
//			visit(current.value);
//
//			if (current.right != null)
//				stack.push(current.right);
//
//			if (current.left != null)
//				stack.push(current.left);
//		}
//	}
//
//	public void traversePostOrderWithoutRecursion() {
//		Stack<Node> stack = new Stack<Node>();
//		Node prev = root;
//		Node current = root;
//		stack.push(root);
//
//		while (!stack.isEmpty()) {
//			current = stack.peek();
//			boolean hasChild = (current.left != null || current.right != null);
//			boolean isPrevLastChild = (prev == current.right || (prev == current.left && current.right == null));
//
//			if (!hasChild || isPrevLastChild) {
//				current = stack.pop();
//				visit(current.value);
//				prev = current;
//			} else {
//				if (current.right != null) {
//					stack.push(current.right);
//				}
//				if (current.left != null) {
//					stack.push(current.left);
//				}
//			}
//		}
//	}
    
    public void containsNodeAndUpdate(int value , int newvalue) {
        containsNodeRecursiveAndUpdate(root, value ,newvalue);
    }

 

    private void containsNodeRecursiveAndUpdate(Node current, int value, int newvalue) {
        if (current == null) {
            System.out.println("tree is empty");
        }

 

        else if (value == current.value) {
             current.value = newvalue;
             System.out.println("Value has been updated");
        }

 

        else if (value < current.value ) {
            containsNodeRecursiveAndUpdate(current.left, value ,newvalue);
        }
        else {
         containsNodeRecursiveAndUpdate(current.right, value ,newvalue);
        }
    }
	private void visit(int value) {
		System.out.print(" " + value);
	}

	class Node {
		int value;
		Node left;
		Node right;

		Node(int value) {
			this.value = value;
			right = null;
			left = null;
		}
	}

	public static void main(String[] args) {
		char ch;

		BinaryTreeDemo bt = new BinaryTreeDemo();
		do {
			System.out.println("Enter the order number to perform operation on LinkedList : ");
			System.out.println(".........................1.Add element.................................");
			System.out.println(
					".........................2.Display Nodes in transverse order................................");

			System.out.println(".........................3.Delete a number .................................");
			System.out.println(".........................4.Search a Number .......................");
			System.out.println(".........................5.Update a Number .......................");

			Scanner sc = new Scanner(System.in);

			int x = sc.nextInt();
			switch (x) {
			case 1:
				System.out.println("Add Elements");
				Scanner scc = new Scanner(System.in);
				bt.add(scc.nextInt());
				break;
			case 2:
				System.out.println("List of Numbers are");
				Scanner scm = new Scanner(System.in);
				bt.traverseInOrder(bt.root);
				System.out.println("\n");
				break;
			case 3:
				System.out.println("Enter an element to Delete");
				int y = sc.nextInt();

				bt.delete(y);
				System.out.println(y + " Is Deleted");
				bt.traverseInOrder(bt.root);
				System.out.println("\n");
				break;

			case 4:
				System.out.println("Enter an element to Search");
				int a = sc.nextInt();
				if (bt.containsNode(a)) {
					System.out.println(a + " is  present");
				} else {
					System.out.println("value is not present");
				}
				break;
			case 5:
				bt.traverseInOrder(bt.root);
				System.out.println("\n");
				System.out.println("Enter the old value");
				int c=sc.nextInt();
				System.out.println("Enter the new value");
				int b= sc.nextInt();
				bt.containsNodeAndUpdate(c, b);
				bt.traverseInOrder(bt.root);
				System.out.println("\n");
				break;
			default:
				System.out.println("Wrong input\n");

			}
			System.out.println("Do you want to continue ? (Y/N)");

			Scanner acb = new Scanner(System.in);
			ch = acb.next().charAt(0);
		} while (ch == 'y' || ch == 'Y');

	}

}
