import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    private final static int MAX_N = 40;
    private final static int MAX_K = 98;
    private final static int MIN_N = 2;
    private final static int MAX_CHILD = 5;

    private final static UserSolution usersolution = new UserSolution();

    private static BufferedReader br;

    private static long seed = 12345;

    private static int[][] path = new int[MAX_N][2];
    private static int[] p = new int[MAX_K+2];
    private static int[] c = new int[MAX_K+2];

    public static long pseudo_rand(int max) {
        seed = (seed * 1103515245 + 12345) & 0x7fffffffL;
        return seed % max;
    }

    public static void makeTree(int N) {

        boolean[] check = new boolean[MAX_K+2];

        for(int i = 1; i < MAX_K + 2; i++) {
            p[i] = c[i] = -1;
        }
        c[(int)(pseudo_rand(MAX_K+1)+1)] = 0;

        for(int i = 0; i < N; i++) {
            int pi = (int)(pseudo_rand(MAX_K+1)+1);
            while(c[pi] < 0 || c[pi] >= MAX_CHILD) {
                pi++;
                if(pi == MAX_K+2) pi = 1;
            }

            int ci = (int)(pseudo_rand(MAX_K+1)+1);
            while(c[ci] >= 0) {
                ci++;
                if(ci == MAX_K+2) ci = 1;
            }

            p[ci] = pi;
            c[pi]++;
            c[ci] = 0;
        }

        for(int i = 0; i < N; i++) {
            int e = (int)(pseudo_rand(MAX_K+1)+1);
            while(check[e] == true || c[e] < 0 || p[e] == -1) {
                e++;
                if(e == MAX_K + 2) e = 1;
            }
            check[e] = true;
            path[i][0] = p[e];
            path[i][1] = e;
        }
    }

    public static void main(String[] args) throws Exception {
        int TC, N, Q, K, ans, ret;
        int totalScore = 0, score;
        String str;
        StringTokenizer st;

        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/dfs_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        TC = Integer.parseInt(str);

        for (int tc = 1; tc <= TC; tc++) {
            str = br.readLine();
            st = new StringTokenizer(str, " ");

            N = Integer.parseInt(st.nextToken());
            Q = Integer.parseInt(st.nextToken());
            seed = Long.parseLong(st.nextToken());

            makeTree(N-1);
            usersolution.dfs_init(N, path);

            score = 100;
            for(int i = 1; i <= Q; i++) {
                str = br.readLine();
                st = new StringTokenizer(str, " ");

                K = Integer.parseInt(st.nextToken());
                ans = Integer.parseInt(st.nextToken());

                ret = usersolution.dfs(K);

                if(ret != ans) score = 0;
            }

            System.out.println("#" + tc + " : " + score);
            totalScore += score;
        }

        System.out.println("#totalScore score : " + totalScore / TC);
    }
}
