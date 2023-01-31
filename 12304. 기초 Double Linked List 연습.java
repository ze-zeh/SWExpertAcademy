class Node {
    public int data;
    public Node prev;
    public Node next;

    public Node(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

public class UserSolution {

    private final static int MAX_NODE = 10000;

    private Node[] node = new Node[MAX_NODE];
    private int nodeCnt = 0;
    private Node head;

    public Node getNode(int data) {
        node[nodeCnt] = new Node(data);
        return node[nodeCnt++];
    }

    public void init() {
        head = null;
        nodeCnt = 0;
    }

    public void addNode2Head(int data) {
        Node temp = getNode(data);
        if (head != null) {
            temp.next = head;
            head.prev = temp;
        }

        head = temp;
    }

    public void addNode2Tail(int data) {
        if (head == null) {
            head = getNode(data);
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = getNode(data);
            temp.next.prev = temp;
        }
    }

    public void addNode2Num(int data, int num) {
        if (num == 1) {
            addNode2Head(data);
        } else {
            Node temp = head;
            for (int i = 2; i < num; i++) {
                temp = temp.next;
            }

            Node newNode = getNode(data);
            if (temp.next != null) {
                temp.next.prev = newNode;
            }
            newNode.prev = temp;
            newNode.next = temp.next;
            temp.next = newNode;
        }
    }

    public int findNode(int data) {
        Node temp = head;
        int index = 1;

        if (temp.data == data) {
            return index;
        }

        while (temp.next != null && temp.next.data != data) {
            temp = temp.next;
            index++;
        }

        return ++index;
    }

    public void removeNode(int data) {
        Node temp = head;

        if (temp.data == data) {
            head = temp.next;
            return;
        }

        while (temp.next != null && temp.next.data != data) {
            temp = temp.next;
        }

        if (temp.next != null) {
            temp.next = temp.next.next;
        }
    }

    public int getList(int[] output) {
        Node temp = head;
        int idx = 0;
        while (temp != null) {
            output[idx] = temp.data;
            idx++;
            temp = temp.next;
        }
        return idx;
    }

    public int getReversedList(int[] output) {
        Node temp = head;
        while (temp != null && temp.next != null) {
            temp = temp.next;
        }

        int idx = 0;
        while (temp != null) {
            output[idx] = temp.data;
            temp = temp.prev;
            idx++;
        }
        return idx;
    }
}
