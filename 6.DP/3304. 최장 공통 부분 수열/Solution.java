import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

    static int N;
    static int M;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
//        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int testcase = 1; testcase <= T; ++testcase) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String str1 = st.nextToken();
            String str2 = st.nextToken();
            N = str1.length();
            M = str2.length();
            dp = new int[N + 1][M + 1];

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                    }
                }
            }

            System.out.println("#" + testcase + " " + dp[N][M]);
        }

        br.close();
    }
}
