import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {
    final static int CORRECTION_VALUE = 1_000_000_007;
    final static char CHAR_TO_INT = 'A';
    static int[][] dp;
    static String str;

    public static void main(String[] args) throws Exception {
//        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int testcase = 1; testcase <= T; ++testcase) {
            str = br.readLine();
            dp = new int[str.length()][16];
            int result = 0;

            int key = 1 << (str.charAt(0) - CHAR_TO_INT);
            for (int i = 1; i < 16; i++) {
                if ((i & key) != 0 && (i & 1) != 0) {
                    dp[0][i] = 1;
                }
            }

            for (int i = 1; i < str.length(); i++) {
                dp(i);
            }

            for (int i = 1; i < 16; i++) {
                result += dp[dp.length - 1][i];
                result %= CORRECTION_VALUE;
            }

            System.out.println("#" + testcase + " " + result);
        }

        br.close();
    }

    public static void dp(int day) {
        int key = 1 << (str.charAt(day) - CHAR_TO_INT);

        for (int i = 1; i < 16; i++) {
            if (dp[day - 1][i] != 0) {
                for (int j = 1; j < 16; j++) {
                    if ((j & i) != 0 && (j & key) != 0) {
                        dp[day][j] += dp[day - 1][i];
                        dp[day][j] %= CORRECTION_VALUE;
                    }
                }
            }
        }
    }
}
