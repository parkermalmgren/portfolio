/* ***************************************************
 * Parker Malmgren
 * 2/21/2025
 *
 * List Class - handles any form of data
 * in a Linked List
 *************************************************** */

public class List<Type> {
    public static final int MAX_SIZE = 40000;
    private Node<Type> head;
    private Node<Type> tail;
    private Node<Type> curr;
    private int num_items;

    public List() {
        this.num_items = 0;
        this.head = null;
        this.curr = null;
        this.tail = null;
    }

    public List(List<Type> l) {
        Node<Type> n = l.head;
        this.head = this.tail = this.curr = null;
        this.num_items = 0;
        while (n != null) {
            this.InsertAfter(n.getData());
            n = n.getLink();
        }
    }

    public void First() {
        this.curr = this.head;
    }

    public void Last() {
        this.curr = this.tail;
    }

    public void SetPos(int pos) {
        if (IsEmpty()) {
            System.out.println("List is empty");
        } else if (pos >= GetSize()) {
            System.out.println("Index out of range");
        } else {
            this.First();
            for (int i = 0; i < pos; i++) {
                curr = this.curr.getLink();
            }
        }
    }

    public void Prev() {
        if (IsEmpty()) {
            System.out.println("List is empty");
        } else {
            int here = GetPos();
            if (here > 0) {
                SetPos(here - 1);
            } else {
                System.out.println("Current is at head");
            }
        }
    }

    public void Next() {
        if (IsEmpty()) {
            System.out.println("List is empty");
        } else {
            int here = GetPos();
            if (here < GetSize() - 1) {
                SetPos(here + 1);
            } else {
                System.out.println("Current is at Tail");
            }
        }
    }

    public int GetPos() {
        if (IsEmpty()) {
            return -1;
        }
        Node<Type> temp = this.head;
        int counter = 0;
        while (temp != this.curr) {
            temp = temp.getLink();
            counter++;
        }
        return counter;
    }

    public Type GetValue(int n) {
        this.SetPos(n);  // Set the current position to 'n'

        // Ensure 'curr' is not null before trying to access data
        return curr != null ? curr.getData() : null;
    }


    public int GetSize() {
        return num_items;
    }

    public void InsertBefore(Type data) {
        if (IsFull()) {
            System.out.println("List Full");
            return;
        }

        Node<Type> newNode = new Node<Type>(data); // Fix: assign data to the new node

        if (IsEmpty()) {
            head = tail = curr = newNode;
        } else if (curr == head) {
            newNode.setLink(head);
            head = newNode;
        } else {
            Node<Type> prev = head;
            while (prev.getLink() != curr) {
                prev = prev.getLink();
            }
            prev.setLink(newNode);
            newNode.setLink(curr);
        }
        curr = newNode;
        num_items++;
    }

    public void InsertAfter(Type data) {
        if (IsFull()) {
            System.out.println("List Full");
            return;
        }
        Node<Type> newNode = new Node<>(data);
        if (IsEmpty()) {
            head = tail = curr = newNode;
        } else {
            newNode.setLink(curr.getLink());
            curr.setLink(newNode);
            if (curr == tail) {
                tail = newNode;
            }
        }
        curr = newNode;
        num_items++;
    }

    public void Remove() {
        if (IsEmpty()) {
            System.out.println("List is empty");
            return;
        }
        if (curr == head) {
            head = head.getLink();
            curr = head;
        } else {
            Node<Type> prev = head;
            while (prev.getLink() != curr) {
                prev = prev.getLink();
            }
            prev.setLink(curr.getLink());
            if (curr == tail) {
                tail = prev;
            }
            curr = prev;
        }
        num_items--;
    }

    public void Replace(Type data) {
        if (!IsEmpty() && curr != null) {
            curr.setData(data);
        }
    }

    public boolean IsEmpty() {
        return head == null;
    }

    public boolean IsFull() {
        return GetSize() >= MAX_SIZE;
    }

    public boolean Equals(List<Type> l) {
        if (this.GetSize() != l.GetSize()) {
            return false;
        }
        Node<Type> a = this.head;
        Node<Type> b = l.head;
        while (a != null && b != null) {
            if (!a.getData().equals(b.getData())) {
                return false;
            }
            a = a.getLink();
            b = b.getLink();
        }
        return true;
    }

    public List<Type> Add(List<Type> l) {
        List<Type> newList = new List<>(this);
        Node<Type> n = l.head;
        while (n != null && newList.GetSize() < MAX_SIZE) {
            newList.InsertAfter(n.getData());
            n = n.getLink();
        }
        return newList;
    }

    public String toString() {
        if (IsEmpty()) {
            return "NULL";
        }
        StringBuilder sb = new StringBuilder();
        Node<Type> n = this.head;
        while (n != null) {
            sb.append(n.getData()).append(" ");
            n = n.getLink();
        }
        return sb.toString().trim();
    }
}
