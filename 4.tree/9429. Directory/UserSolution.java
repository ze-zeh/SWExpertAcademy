import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class UserSolution {
    static class Node {
        String name;
        Node parent;
        int treeSize;
        List<Node> children;

        public Node(String name) {
            this.name = name;
            this.treeSize = 1;
            this.children = new LinkedList<>();
        }
    }

    private static Node tree;

    void init(int n) {
        tree = new Node("/");
    }

    void cmd_mkdir(char[] path, char[] name) {
        Node parent = getDirNode(path);
        Node newNode = new Node(arrToString(name));
        connect(parent, newNode);
    }

    void cmd_rm(char[] path) {
        Node removeDir = getDirNode(path);
        Node removeParentDir = removeDir.parent;

        removeParentDir.children.remove(removeDir);

        Node temp = removeDir;

        while (temp.parent != null) {
            temp.parent.treeSize -= removeDir.treeSize;
            temp = temp.parent;
        }
    }

    void cmd_cp(char[] srcPath, char[] dstPath) {
        Node srcDir = getDirNode(srcPath);
        Node dstDir = getDirNode(dstPath);
        Node newDir = new Node(srcDir.name);
        
        copyTree(srcDir, newDir);
        connect(dstDir, newDir);
    }

    void cmd_mv(char[] srcPath, char[] dstPath) {
        Node srcDir = getDirNode(srcPath);
        Node dstDir = getDirNode(dstPath);

        srcDir.parent.children.remove(srcDir);

        Node temp = srcDir.parent;
        while (temp != null) {
            temp.treeSize -= srcDir.treeSize;
            temp = temp.parent;
        }

        connect(dstDir, srcDir);
    }

    int cmd_find(char[] path) {
        return getDirNode(path).treeSize - 1;
    }

    private String arrToString(char[] arr) {
        return String.valueOf(arr, 0, arr.length - 1);
    }

    private static void connect(Node parent, Node child) {
        parent.children.add(child);
        child.parent = parent;
        
        while (parent != null) {
            parent.treeSize += child.treeSize;
            parent = parent.parent;
        }
    }

    private static void copyTree(Node parent, Node copyParent) {
        copyParent.treeSize = parent.treeSize;
        
        for (Node child : parent.children) {
            Node temp = new Node(child.name);
            temp.parent = copyParent;
            copyParent.children.add(temp);
            copyTree(child, temp);
        }
    }

    private Node getDirNode(char[] path) {
        StringTokenizer st = new StringTokenizer(arrToString(path), "/");
        Node curNode = tree;

        while (st.hasMoreTokens()) {
            String nextDirName = st.nextToken();
            for (Node child : curNode.children) {
                if (child.name.equals(nextDirName)) {
                    curNode = child;
                    break;
                }
            }
        }

        return curNode;
    }
}
