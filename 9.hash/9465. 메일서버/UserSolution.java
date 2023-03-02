import java.util.Arrays;

public class UserSolution {
    static final int ALPHABET_LEN = 26;
    static final int MAX_USER = 1000;
    static final int MAX_MAIL = 10000;

    static class Trie {
        static int nodeNum;
        Node root;

        public Trie() {
            nodeNum = 0;
            this.root = new Node();
            root.c = ' ';
        }

        private static class Node {
            int num;
            char c;
            boolean isTerminal;
            Node[] child;

            public Node() {
                this.num = nodeNum++;
                child = new Node[ALPHABET_LEN];
            }
        }

        void clear() {
            for (int i = 0; i < ALPHABET_LEN; i++) {
                root.child[i] = null;
            }
        }

        int insert(String str) {
            int len = str.length();

            Node cur = root;

            for (int i = 0; i < len; i++) {
                int key = str.charAt(i) - 'a';

                if (cur.child[key] == null) {
                    cur.child[key] = new Node();
                    cur.child[key].c = str.charAt(i);
                }

                cur = cur.child[key];
            }
            cur.isTerminal = true;
            return cur.num;
        }

        int find(String str) {
            int len = str.length();
            Node cur = root;

            for (int i = 0; i < len; i++) {
                int key = str.charAt(i) - 'a';
                if (cur.child[key] == null) {
                    return -1;
                }
                cur = cur.child[key];
            }

            if (cur != null && cur.isTerminal) {
                return cur.num;
            } else {
                return -1;
            }
        }
    }

    static class Node {
        int mailNum;
        Node next;
        Node prev;

        public Node(int mailNum) {
            this.mailNum = mailNum;
        }
    }

    static int[][] mails;
    static int[] mailWordCount;
    static int[] userMailCount;
    static Node[] userMailBox;
    static Node[] tails;
    static Trie trie;
    static int k;
    static int mailNum;

    void init(int N, int K) {
        trie = new Trie();
        k = K;
        mailNum = 0;
        mails = new int[MAX_MAIL][10];
        mailWordCount = new int[MAX_MAIL];
        userMailBox = new Node[MAX_USER];
        tails = new Node[MAX_USER];
        userMailCount = new int[MAX_USER];

        Arrays.fill(mailWordCount, 0);
        Arrays.fill(userMailCount, 0);

        for (int i = 0; i < MAX_USER; i++) {
            userMailBox[i] = new Node(-1);
            tails[i] = new Node(-1);

            userMailBox[i].next = tails[i];
            tails[i].prev = userMailBox[i];
        }

        trie.clear();
    }

    String arrToString(char[] arr) {
        StringBuilder sb = new StringBuilder();

        for (char c : arr) {
            if (c == '\0') break;
            sb.append(c);
        }

        return sb.toString();
    }

    void addNode(Node head, int mailNum) {
        Node newNode = new Node(mailNum);
        newNode.next = head.next;
        newNode.next.prev = newNode;
        head.next = newNode;
        newNode.prev = head;
    }

    void removeTail(int uID) {
        Node tail = tails[uID];
        Node remove = tail.prev;
        remove.prev.next = tail;
        tail.prev = remove.prev;
    }

    void removeNode(Node remove) {
        remove.prev.next = remove.next;
        remove.next.prev = remove.prev;
    }

    void sendMail(char[] subject, int uID, int cnt, int[] rIDs) {
        String[] words = arrToString(subject).split(" ");
        for (int i = 0; i < words.length; i++) {
            mails[mailNum][i] = trie.insert(words[i]);
        }
        mailWordCount[mailNum] = words.length;

        for (int i = 0; i < cnt; i++) {
            int userId = rIDs[i];
            addNode(userMailBox[userId], mailNum);
            userMailCount[userId]++;

            if (userMailCount[userId] > k) {
                removeTail(userId);
                userMailCount[userId]--;
            }
        }

        mailNum++;
    }

    int deleteMail(int uID, char[] subject) {
        int answer = 0;
        String[] subjects = arrToString(subject).split(" ");
        Node cur = this.userMailBox[uID];

        while (cur.next != tails[uID]) {
            cur = cur.next;
            int len = mailWordCount[cur.mailNum];
            boolean flag = true;
            int[] mail = mails[cur.mailNum];

            if (len != subjects.length) continue;

            for (int i = 0; i < len; i++) {
                if (mail[i] != trie.find(subjects[i])) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                removeNode(cur);
                answer++;
            }
        }

        userMailCount[uID] -= answer;

        return answer;
    }

    int searchMail(int uID, char[] text) {
        int answer = 0;
        Node cur = userMailBox[uID];
        Node tail = tails[uID];
        String target = arrToString(text);
        int num = trie.find(target);

        while (cur.next != tail) {
            cur = cur.next;
            int len = mailWordCount[cur.mailNum];

            for (int i = 0; i < len; i++) {
                if (mails[cur.mailNum][i] == num) {
                    answer++;
                    break;
                }
            }
        }

        return answer;
    }

    int getCount(int uID) {
        return userMailCount[uID];
    }
}
