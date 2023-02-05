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
        Node newNode = getNode(data);
        if (head != null) {
            newNode.next = head;
            head.prev = newNode;
        }
        head = newNode;
    }

    public void addNode2Tail(int data) {
        if (head == null) {
            head = getNode(data);
        } else {
            Node prevNode = head;
            while (prevNode.next != null) {
                prevNode = prevNode.next;
            }
            Node newNode = getNode(data);
            newNode.prev = prevNode;
            prevNode.next = newNode;
        }
    }

    public void addNode2Num(int data, int num) {
        if (num == 1) {
            addNode2Head(data);
        } else {
            Node prevNode = head;
            for (int i = 2; i < num; i++) {
                prevNode = prevNode.next;
            }

            Node newNode = getNode(data);
            if (prevNode.next != null) {
                prevNode.next.prev = newNode;
            }
            newNode.prev = prevNode;
            newNode.next = prevNode.next;
            prevNode.next = newNode;
        }
    }

    public int findNode(int data) {
        Node targetNode = head;
        int index = 1;

        if (targetNode.data == data) {
            return index;
        }

        while (targetNode.next != null && targetNode.next.data != data) {
            targetNode = targetNode.next;
            index++;
        }

        return ++index;
    }

    public void removeNode(int data) {
        Node targetNode = head;

        if (head.data == data) {
            head = head.next;
            return;
        }

        while (targetNode.next != null && targetNode.next.data != data) {
            targetNode = targetNode.next;
        }

        if (targetNode.next != null) {
            targetNode.next = targetNode.next.next;
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
