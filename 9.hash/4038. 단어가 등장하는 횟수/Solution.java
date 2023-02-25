import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {
    static String B;
    static String S;
    static int result;
    static int[] table;

    public static void main(String[] args) throws Exception {
//        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int testcase = 1; testcase <= T; ++testcase) {
            B = br.readLine();
            S = br.readLine();
            result = 0;
            table = new int[S.length()];
            int i = 0;
            int j = 0;

            makeTable();

            while (i <= B.length() - S.length()) {
                if (j < S.length() && B.charAt(i + j) == S.charAt(j)) {
                    if (++j == S.length()) result++;
                } else {
                    if (j == 0) {
                        ++i;
                    } else {
                        i += j - table[j - 1];
                        j = table[j - 1];
                    }
                }
            }

            System.out.println("#" + testcase + " " + result);
        }

        br.close();
    }

    static void makeTable() {
        int index = 0;
        for (int i = 1; i < S.length(); i++) {
            while (index > 0 && S.charAt(i) != S.charAt(index)) index = table[index - 1];
            if (S.charAt(i) == S.charAt(index)) table[i] = ++index;
        }
    }
}
