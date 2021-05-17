/*
 * this is a basic LinkedList program showing the various ways to  create Linked Lists,
 * adding nodes using various techniques, and reversing the linked list using different techniques
 */

package linkedlist;

import java.util.Stack;

public class LinkedList {

	/*
	 * the head is always at the beginning of the LinkedList starting leftmost and
	 * moving to the right
	 */
	Node head;

	/*
	 * this class defines each node of the LinkedList
	 */
	static class Node {
		// this node is the pointer or reference to the next node in the List.
		Node next;
		// int is arbitrary; can use character, strings, etc.
		int data;

		// Node constructor creates each node in the List with the given int param
		public Node(int data) {
			this.data = data;
			next = null;
		}
	}

	public static void main(String[] args) {
		// class LinkedList object
		LinkedList myList = new LinkedList();
		/*
		 * standard way of creating, adding, and connecting nodes to the list
		 */

		// creating the first node with value 10
		// List picture: 10
		myList.head = new Node(10);

		// creating second node
		Node second = new Node(83);

		// point the first node to the second node
		// List picture: 10->83
		myList.head.next = second;

		// creating third node
		Node third = new Node(1000);
		// List picture: 10->83->1000
		// point second node to third node
		second.next = third;

		/*
		 * etc...
		 */

		/*
		 * next method is adding new nodes to the front of the list
		 */
		// List picture: 42->10->83->1000
		myList.goToTheFront(42);
		// List picture: 8->42->10->83->1000
		myList.goToTheFront(8);
		// LinkedLists allow nodes with duplicate values
		// List picture: 10->8->42->10->83->1000
		myList.goToTheFront(10);

		/*
		 * next method is adding a node after any given reference node
		 */

		// List picture: 10->44->8->42->10->83->1000
		myList.addAfterGivenNode(myList.head, 44);
		/*
		 * use head.next.next.next..... etc. to place the node wherever you want
		 */
		// List picture: 10->44->45->8->42->10->83->1000
		myList.addAfterGivenNode(myList.head.next, 45);

		// List picture: 10->44->45->46->8->42->10->83->1000
		myList.addAfterGivenNode(myList.head.next.next, 46);

		// List picture: 10->44->45->46->47->8->42->10->83->1000
		myList.addAfterGivenNode(myList.head.next.next.next, 47);

		/*
		 * next is adding a node at the end of the list
		 */
		// List picture: 10->44->45->46->47->8->42->10->83->1000->9000
		myList.addToEndOfList(9000);
		// List picture: 10->44->45->46->47->8->42->10->83->1000->9000->0
		myList.addToEndOfList(0);

		// printing out list
		System.out.printf("Linked List:\n");
		myList.printLinkedList();

		/*
		 * this next part will show the various ways to reverse linkedlists
		 */

		// reverse the LinkedList using recursion
		myList.head = myList.recursiveReverse(myList.head);
		// call print method again to see reversed list
		System.out.println("\n");
		System.out.printf("Reversed Linked List using recursion:\n");
		myList.printLinkedList();

		// reverse the LinkedList using iteration
		myList.iterativeReverse();
		// call print method again to see reversed list
		System.out.println("\n");
		System.out.printf("Reversed Linked List using iteration:\n");
		myList.printLinkedList();

		// reverse the Linked List using stack
		myList.stackReverse();
		// call print method again to see reversed list
		System.out.println("\n");
		System.out.printf("Reversed Linked List using stack:\n");
		myList.printLinkedList();
	}

	// this function adds the node to the front(head) of the LinkedList
	void goToTheFront(int data) {

		Node newNode = new Node(data);

		// setting newNode's next node to the head
		newNode.next = head;
		// then, set the head to point to newNode
		head = newNode;
	}

	//this function adds a node to the LinkedList after the given parameter node 
	void addAfterGivenNode(Node previous, int data) {

		// have to check if the node is not null
		if (previous == null) {
			System.out.println("The previous node is null");
			return;
		}

		// else let's get down to business
		Node node = new Node(data);

		node.next = previous.next;
		previous.next = node;

	}

	//this function adds a node to the end of the list
	void addToEndOfList(int data) {
		// new node to be added to the back of the bus
		Node node = new Node(data);

		// first check if the List is empty and if so, set the head to this node
		if (head == null) {
			head = node;
		}

		// since this node is at the end, set next = null
		node.next = null;

		/*
		 * create another new Node pointing to the head as the placeholder to traverse
		 * the LinkedList
		 */
		Node placeHolder = head;

		while (placeHolder.next != null) {
			placeHolder = placeHolder.next;
		}

		// set node to placeHolder.next
		placeHolder.next = node;
	}

	// printing out the nodes to see the LinkedList
	void printLinkedList() {

		/*
		 * this node will be placed at the beginning at the list in order to traverse it
		 * and print out all list contents
		 */
		Node node = head;

		/*
		 * while the LinkedList has more nodes, traverse the LinkedList from left to
		 * right until the next node equals null i.e. has nothing in the allocated
		 * memory
		 */
		while (node != null) {
			System.out.printf(node.data + " ");
			// move one node to the right
			node = node.next;
		}

	}

	// reversing list using recursion
	Node recursiveReverse(Node head) {
		if (head.next == null || head == null) {
			return head;
		}

		// recursion
		Node node1 = recursiveReverse(head.next);
		head.next.next = head;

		head.next = null;

		return node1;

	}

	// reversing list using iteration
	Node iterativeReverse() {

		Node previous = null;
		Node current = head;
		Node next = null;

		while (current != null) {
			next = current.next;
			current.next = previous;
			previous = current;
			current = next;
		}
		head = previous;
	}

	void stackReverse() {

		// stack of type Node
		Stack<Node> stack = new Stack<>();
		Node temporaryNode = head;

		// throwing in all of the nodes from the linked list into the stack
		while (temporaryNode.next != null) {
			stack.add(temporaryNode);
			temporaryNode = temporaryNode.next;
		}

		// head is updated to the last node
		head = temporaryNode;

		// reverse the list by popping stack
		// reversal is possible because
		// stack is first-in-first-out data structure
		// so first item popped was the last node in linked list
		// which will now be the head of the new reversed list
		while (!stack.isEmpty()) {
			temporaryNode.next = stack.pop();

			temporaryNode = temporaryNode.next;
		}

		// finally, point final node to null
		temporaryNode.next = null;
	}

}
