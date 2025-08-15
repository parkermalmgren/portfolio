
public class BinaryTree
{
    private BinNode root;

    public BinaryTree()
    {
        root = new BinNode();
    }

    public BinNode getRoot(){
        return this.root;
    }

     /* Function to check if tree is empty */
    public boolean isEmpty()
    {
        return this.root == null;
    }
     /* Functions to insert data */
    public void insert(String data)
    {
         this.root = insert(this.root, data);
    }
    /* Function to insert data recursively */
    private BinNode insert(BinNode node, String data)
    {
         if (node == null)
             node = new BinNode(data);
         else
         {
             // write this!
         }
         return node;
    }
    /* Function to count number of nodes */
    public int countNodes()
    {
         return countNodes(this.root);
    }
     /* Function to count number of nodes recursively */
    private int countNodes(BinNode r)
    {
        int count = 0;
        //write this!
        return count;
    }
    /* Function to search for an element */
    public boolean search(String val)
    {
         return search(this.root, val);
    }
    /* Function to search for an element recursively */
    private boolean search(BinNode r, String val)
    {
         // write this!
         return false;
    }


    // removal logic - help from geeks4geeks
    public BinNode remove(BinNode node, String val){
        if (node == null) return null;


        if (val.compareTo(node.getData()) < 0) {
            node.setLeft(remove(node.getLeft(), val));
        } else if (val.compareTo(node.getData()) > 0) {
            node.setRight(remove(node.getRight(), val));
        } else {
            // Node with only one child or no child
            if (node.getLeft() == null)
                return node.getRight();
            else if (node.getRight() == null)
                return node.getLeft();

            BinNode minNode = minValueNode(node.getRight());
            node.setData(minNode.getData());
            node.setRight(remove(node.getRight(), minNode.getData()));
        }

        return node;

    }

    // direction from g4g
    private BinNode minValueNode(BinNode node){
        BinNode current = node;
        while (current.getLeft() != null)
            current = current.getLeft();
        return current;
    }

    public void setRoot(BinNode root) {
        this.root = root;
    }
}
