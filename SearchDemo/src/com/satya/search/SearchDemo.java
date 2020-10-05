package com.satya.search;

import java.util.Arrays;
import java.util.Scanner;

public class SearchDemo {
	// Represent a node of the singly linked list
	class Node {
		int data;
		Node next;

		public Node(int data) {
			this.data = data;
			this.next = null;
		}
	}

	// Represent the head and tail of the singly linked list
	public Node head = null;
	public Node tail = null;

	// addNode() will add a new node to the list
	public void addNode(int data) {
		// Create a new node
		Node newNode = new Node(data);

		// Checks if the list is empty
		if (head == null) {
			// If list is empty, both head and tail will point to new node
			head = newNode;
			tail = newNode;
		} else {
			// newNode will be added after tail such that tail's next will point to newNode
			tail.next = newNode;
			// newNode will become new tail of the list
			tail = newNode;
		}
	}

	// display() will display all the nodes present in the list
	public void display() {
		// Node current will point to head
		Node current = head;

		if (head == null) {
			System.out.println("List is empty");
			return;
		}
		System.out.println("Nodes of singly linked list: ");
		while (current != null) {
			// Prints each node by incrementing pointer
			System.out.print(current.data + " ");
			current = current.next;
		}
		System.out.println();
	}
//******************Liner Search*****************************************************
	public void LinearsearchValue(int data) {
		int pos = 0;
		Node present = head;

		boolean flag = false;

		if (head == null) {
			System.out.println("List is Empty ");
		} else {

			while (present != null) {
				if (present.data == data) {
					flag = true;
					break;
				} else {
					present = present.next;
					pos++;
				}
			}
		}
		if (flag) {
			System.out.println("data is present in the list" + " At position " + pos);
		} else {
			System.out.println("data is not found");
		}
	}
//********************Binary Search*******************************************
	public static Node middleNode(Node start, Node last) {
		if (start == null)
			return null;

		Node slow = start;
		Node fast = start.next;

		while (fast != last) {
			fast = fast.next;
			if (fast != last) {
				slow = slow.next;
				fast = fast.next;
			}
		}
		return slow;
	}

	public static Node binarySearch(Node head, int value) {
		Node start = head;
		Node last = null;

		do {
			// Find Middle
			Node mid = middleNode(start, last);

			// If middle is empty
			if (mid == null)
				return null;

			// If value is present at middle
			if (mid.data == value)
				return mid;

			// If value is less than mid
			else if (mid.data < value) {
				start = mid.next;
			}

			// If the value is more than mid.
			else
				last = mid;
		} while (last == null || last != start);

		// value not present
		return null;
	}

	public void displayBinary(Node check) {
		if (check == null) {
			System.out.println("Value  Is Not present");
		} else {
			System.out.println(check.data + " Is Present");
		}
	}
//************************Deletion Of Node******************************************
	public void deleteNode(int position) {
		// If linked list is empty
		if (head == null)
			return;

		// Store head node
		Node temp = head;

		// If head needs to be removed
		if (position == 0) {
			head = temp.next; // Change head
			return;
		}

		// Find previous node of the node to be deleted
		for (int i = 0; i < position - 1; i++)
			temp = temp.next;
		// Node temp->next is the node to be deleted
		// Store pointer to the next of node to be deleted
		Node next = temp.next.next;

		temp.next = next; // Unlink the deleted node from list
	}
//***************************Bubble Sort********************************************
	public void BubblesortList() {
		Node current = head, index = null;
		int temp;
		if (head == null) {
			return;
		} else {
			while (current != null) {
				index = current.next;
				while (index != null) {
					if (current.data > index.data) {
						temp = current.data;
						current.data = index.data;
						index.data = temp;
					}
					index = index.next;
				}
				current = current.next;
			}
		}
	}
//***************************Selection Sort*****************************************
	public void selectionSort(Node head) {

		for (Node nodeA = head; nodeA != null; nodeA = nodeA.next) {
			Node min = nodeA;
			for (Node nodeB = nodeA; nodeB != null; nodeB = nodeB.next) {
				if (min.data > nodeB.data) {
					min = nodeB;
				}

				Node temp = new Node(nodeA.data);
				nodeA.data = min.data;
				min.data = temp.data;
			}
		}
	}
//**********************Insertion Sort*****************************************
	public void InsertionSort(int arr[]) {
		
		int n = arr.length;
		System.out.println("Original Array:" + Arrays.toString(arr));
		// apply insertion sort algorithm on the array
		for (int k = 1; k < n - 1; k++) {
			int temp = arr[k];
			int j = k - 1;
			while (j >= 0 && temp <= arr[j]) {
				arr[j + 1] = arr[j];
				j = j - 1;
			}
			arr[j + 1] = temp;
		}
		// print the sorted array
		System.out.println("Inserted Sorted Array:" + Arrays.toString(arr));
	}
//**********************Shell Sort**************************************************
	public void Shellsort(int arr[]) {
		int n = arr.length;
		System.out.println("Original Array:" + Arrays.toString(arr));
		for (int gap = n / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < n; i++) {
				int key = arr[i];
				int j = i;
				while (j >= gap && arr[j - gap] > key) {
					arr[j] = arr[j - gap];
					j -= gap;
				}
				arr[j] = key;
			}

		}
		System.out.println("Shell Sorted Array:" + Arrays.toString(arr));
	}
//****************************MergeSort************************************************
	public void mergeSort(int[] a, int n) {
		System.out.println(Arrays.toString(a));
		if (n < 2) {
			return;
		}
		int mid = n / 2;
		int[] l = new int[mid];
		int[] r = new int[n - mid];

		for (int i = 0; i < mid; i++) {
			l[i] = a[i];
		}
		for (int i = mid; i < n; i++) {
			r[i - mid] = a[i];
		}
		mergeSort(l, mid);
		mergeSort(r, n - mid);

		merge(a, l, r, mid, n - mid);
		System.out.println(Arrays.toString(a));
	}

	public void merge(int[] a, int[] l, int[] r, int left, int right) {
		//System.out.println(Arrays.toString(a));
		int i = 0, j = 0, k = 0;
		while (i < left && j < right) {
			if (l[i] <= r[j]) {
				a[k++] = l[i++];
			} else {
				a[k++] = r[j++];
			}
		}
		while (i < left) {
			a[k++] = l[i++];
		}
		while (j < right) {
			a[k++] = r[j++];
		}
		//System.out.println(Arrays.toString(a));
	}
//***************************Quick Sort********************************************
	public int partition(int intArray[], int low, int high) {
        int pi = intArray[high];
        int i = (low-1); // smaller element index  
        for (int j=low; j<high; j++) {
            // check if current element is less than or equal to pi
            if (intArray[j] <= pi) {
                i++;
                // swap intArray[i] and intArray[j]
                int temp = intArray[i];
                intArray[i] = intArray[j];
                intArray[j] = temp;
            }
        }
  
        // swap intArray[i+1] and intArray[high] (or pi)
        int temp = intArray[i+1];
        intArray[i+1] = intArray[high];
        intArray[high] = temp;
  
        return i+1;
    }
   
    void quick_sort(int intArray[], int low, int high) {
        if (low < high) {
            //partition the array around pi=>partitioning index and return pi
            int pi = partition(intArray, low, high);
  
            // sort each partition recursively
            quick_sort(intArray, low, pi-1);
            quick_sort(intArray, pi+1, high);
        }
      //  System.out.println(Arrays.toString(intArray));
    }
    
    //*****Heap Sort*************************  
        public void heapsort(int arr[]) {
          int n = arr.length;
      
          // Build max heap
          for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
          }
      
          // Heap sort
          for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
      
            // Heapify root element
            heapify(arr, i, 0);
          }
        }
      
        void heapify(int arr[], int n, int i) {
          // Find largest among root, left child and right child
          int largest = i;
          int l = 2 * i + 1;
          int r = 2 * i + 2;
      
          if (l < n && arr[l] > arr[largest])
            largest = l;
      
          if (r < n && arr[r] > arr[largest])
            largest = r;
      
          // Swap and continue heapifying if root is not largest
          if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
      
            heapify(arr, n, largest);
          }
        }
      
        // Function to print an array
         void printArray(int arr[]) {
          int n = arr.length;
          for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
          System.out.println();
          }
   //************Radix Sort******************************************
        public  void countingSort(int array[], int size, int place) {
        	    int[] output = new int[size + 1];
        	    int max = array[0];
        	    for (int i = 1; i < size; i++) {
        	      if (array[i] > max)
        	        max = array[i];
        	    }
        	    int[] count = new int[max + 1];

        	    for (int i = 0; i < max; ++i)
        	      count[i] = 0;

        	    // Calculate count of elements
        	    for (int i = 0; i < size; i++)
        	      count[(array[i] / place) % 10]++;

        	    // Calculate cummulative count
        	    for (int i = 1; i < 10; i++)
        	      count[i] += count[i - 1];

        	    // Place the elements in sorted order
        	    for (int i = size - 1; i >= 0; i--) {
        	      output[count[(array[i] / place) % 10] - 1] = array[i];
        	      count[(array[i] / place) % 10]--;
        	    }

        	    for (int i = 0; i < size; i++)
        	      array[i] = output[i];
        	  }

        	  // Function to get the largest element from an array
        	  int getMax(int array[], int n) {
        	    int max = array[0];
        	    for (int i = 1; i < n; i++)
        	      if (array[i] > max)
        	        max = array[i];
        	    return max;
        	  }

        	  // Main function to implement radix sort
        	  void radixSort(int array[], int size) {
        	    // Get maximum element
        	    int max = getMax(array, size);

        	    // Apply counting sort to sort elements based on place value.
        	    for (int place = 1; max / place > 0; place *= 10)
        	      countingSort(array, size, place);
        	  }
         
         
         
    //*************Main Method************************************
	public static void main(String[] args) {
		char ch;
		SearchDemo sList = new SearchDemo();
		int[] arr = { 10, 6, 15, 4, 1, 45 };
	//	System.out.println(arr[0]);
		do {
			System.out.println("Enter the order number to perform operation on LinkedList : ");
			System.out.println(".........................1.Display Nodes................................");
			System.out.println(".........................2.Add element.................................");
			System.out.println(".........................3.Liner Searching of Node.................................");
			System.out.println(".........................4.Bubble Sorting Node .......................");
			System.out.println(".........................5.Selection Sorting Node .......................");
			System.out.println(".........................6.Binary Searching of Node .......................");
			System.out.println(".........................7.Delete Node.................");
			System.out.println(".........................8.Insertion Sorting Node .......................");
			System.out.println(".........................9.Shell Sorting Node .......................");
			System.out.println(".........................10.Merge Sorting Node .......................");
			System.out.println(".........................11.Quick Sorting Node .......................");
			System.out.println(".........................12.Heap Sorting  .......................");
			System.out.println(".........................13.Radix Sorting  .......................");
			
			Scanner sc = new Scanner(System.in);

			int x = sc.nextInt();
			switch (x) {

			case 1:
				sList.display();
				break;
			case 2:
				System.out.println("Add Elements");
				Scanner scc = new Scanner(System.in);
				sList.addNode(scc.nextInt());
				break;
			case 3:
				System.out.println("Enter Element to Search ");
				Scanner scm = new Scanner(System.in);
				sList.LinearsearchValue(scm.nextInt());
				break;
			case 4:
				sList.BubblesortList();
				System.out.println("Node is Bubble  Sorted");
				sList.display();
				break;
			case 5:
				sList.selectionSort(sList.head);
				System.out.println("Node is Selection Sorted");
				sList.display();
				break;

			case 6:
				System.out.println("Enter Element to Search ");
				Scanner scl = new Scanner(System.in);
				int y = scl.nextInt();
				Node check = SearchDemo.binarySearch(sList.head, y);
				sList.displayBinary(check);
				break;

			case 7:
				sList.display();
				System.out.println("Enter the  position to delete");
				Scanner sce = new Scanner(System.in);
				sList.deleteNode(sce.nextInt());
				sList.display();
				System.out.println("Node is deleted  ");
				break;

			case 8:
				sList.InsertionSort(arr);
				break;
			case 9:
				sList.Shellsort(arr);
				break;
			case 10 :
				System.out.println("Original Array is"+Arrays.toString(arr));
				sList.mergeSort(arr, 6);
			//	System.out.println("Sorted Array Is"+Arrays.toString(arr));
				break;
			case 11:
				System.out.println("Original Array is"+Arrays.toString(arr));
				sList.quick_sort(arr, 0, 5);
				System.out.println("Quick Sorted Array is"+Arrays.toString(arr));
                 break;
			case 12 :
				sList.heapsort(arr);
				sList.printArray(arr);
				break;
			case 13 :
				sList.radixSort(arr, 6);
				System.out.println("Sorted Array in Ascending Order: ");
			    System.out.println(Arrays.toString(arr));
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
