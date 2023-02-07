import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    private final static UserSolution usersolution = new UserSolution();

    private static BufferedReader br;

    private static int cmd_bfs() throws Exception {
        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/bfs_sample_input.txt"));

        int score = 100;
        int N, M, x1, y1, x2, y2, dist, ans;
        int[][] map = new int[10][10];
        String str;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        N = Integer.parseInt(str);

        for(int i = 0; i < N; i++) {
            str = br.readLine();
            st = new StringTokenizer(str, " ");
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        usersolution.bfs_init(N, map);

        str = br.readLine();
        M = Integer.parseInt(str);

        for(int i = 1; i <= M; i++) {
            str = br.readLine();
            st = new StringTokenizer(str, " ");
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            ans = Integer.parseInt(st.nextToken());

            dist = usersolution.bfs(x1, y1, x2, y2);

            if(dist != ans) {
                score = 0;
            }
        }
        return score;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("#total score : " + cmd_bfs());
    }
}
