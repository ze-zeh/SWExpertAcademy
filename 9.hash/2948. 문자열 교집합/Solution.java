import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Solution {
    static int N;
    static int M;
    static int result;
    static HashSet<String> hs;

    public static void main(String[] args) throws Exception {
//        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testcase = 1; testcase <= T; ++testcase) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            hs = new HashSet<>();
            result = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) hs.add(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) if (hs.contains(st.nextToken())) result++;

            System.out.println("#" + testcase + " " + result);
        }

        br.close();
    }
}
