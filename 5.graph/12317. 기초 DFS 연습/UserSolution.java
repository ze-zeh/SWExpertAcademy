public class UserSolution {
    int[][] tree;

    public void dfs_init(int N, int[][] path) {
        tree = new int[100][5];
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < 5; j++) {
                if (tree[path[i][0]][j] == 0) {
                    tree[path[i][0]][j] = path[i][1];
                    break;
                }
            }
        }
    }

    public int dfs(int N) {
        return myDFS(N, N);
    }

    public int myDFS(int N, int cur) {
        int result = -1;
        for (int i = 0; i < 5; i++) {
            if (tree[cur][i] == 0) break;
            if (N < tree[cur][i]) return tree[cur][i];

            result = myDFS(N, tree[cur][i]);
            if (result != -1) break;
        }
        return result;
    }
}
