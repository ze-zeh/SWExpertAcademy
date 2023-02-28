public class UserSolution {
    final int MAX = 20011;

    static class Node {
        int index;
        Node next, prev;

        public Node(int data) {
            this.index = data;
        }
    }

    Node[] table = new Node[MAX];
    Node[] tails = new Node[MAX];
    char[] str = new char[50000];
    int n;

    void add(int key, int index) {
        Node cur = table[key];
        Node tail = tails[key];
        Node newNode = new Node(index);

        while (cur.next != tail) {
            if (cur.next.index > index) {
                newNode.next = cur.next;
                newNode.next.prev = newNode;
                cur.next = newNode;
                newNode.prev = cur;
                return;
            }
            cur = cur.next;
        }

        newNode.next = tail;
        tail.prev = newNode;
        cur.next = newNode;
        newNode.prev = cur;
    }

    void remove(int key, int index) {
        Node cur = table[key];
        Node tail = tails[key];

        while (cur.next != tail) {
            cur = cur.next;

            if (cur.index == index) {
                cur.prev.next = cur.next;
                cur.next.prev = cur.prev;
                return;
            }
        }
    }

    int getKey(char[] arr, int start) {
        long hash = 5381L;

        for (int i = start; i < start + 3; i++) {
            hash = (((hash << 5) + hash) + arr[i]);
        }
        return (int) (((hash % MAX) + MAX) % MAX);
    }

    void init(int N, char[] init_String) {
        n = N;

        for (int i = 0; i < MAX; i++) {
            table[i] = new Node(-1);
            tails[i] = new Node(-1);
        }

        for (int i = 0; i < MAX; i++) {
            table[i].next = tails[i];
            tails[i].prev = table[i];
        }

        for (int i = 0; i < N; i++) str[i] = init_String[i];

        for (int i = 0; i <= N - 3; i++) add(getKey(str, i), i);
    }

    int change(char[] string_A, char[] string_B) {
        int key = getKey(string_A, 0);
        int count = 0;
        int index;
        Node cur = table[key];
        Node tail = tails[key];

        while (cur.next != tail) {
            cur = cur.next;

            boolean flag = false;
            int curIndex = cur.index;
            for (int i = 0; i < 3; i++) {
                if (str[curIndex++] != string_A[i]) {
                    flag = true;
                    break;
                }
            }

            if (flag) continue;

            count++;

            index = cur.index;
            cur = cur.prev;

            for (int i = index - 2; i <= index + 2; i++) {
                if (i < 0 || i > n - 3) continue;
                remove(getKey(str, i), i);
            }

            curIndex = 0;
            for (int i = index; i < index + 3; i++) str[i] = string_B[curIndex++];

            for (int i = index - 2; i <= index + 2; i++) {
                if (i < 0 || i > n - 3) continue;
                add(getKey(str, i), i);
            }

            while (cur.next.index < index + 3 && cur.next != tail) cur = cur.next;
        }

        return count;
    }

    void result(char[] ret) {
        System.arraycopy(str, 0, ret, 0, str.length);
    }
}
