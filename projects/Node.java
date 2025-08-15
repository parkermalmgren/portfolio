/* ***************************************************
 * Parker Malmgren
 * 2/21/2025
 *
 * Node Class - handles any form of data with List
 * as its super class
 *************************************************** */

class Node<Type>
{
	private Type data;
	private Node<Type> link;

	// constructor
	public Node(Type data)
	{
		this.data = data;
		this.link = null;
	}

	// accessor and mutator for the data component
	public Type getData()
	{
		return this.data;
	}

	public void setData(Type data)
	{
		this.data = data;
	}

	// accessor and mutator for the link component
	public Node<Type> getLink()
	{
		return this.link;
	}

	public void setLink(Node<Type> link)
	{
		this.link = link;
	}
}
