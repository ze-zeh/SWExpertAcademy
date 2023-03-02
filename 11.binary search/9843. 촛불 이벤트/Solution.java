import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {
    static final long MAX = 10_000_000_000L;

    public static void main(String[] args) throws Exception {
//        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            long N = Long.parseLong(br.readLine());
            long start = 0;
            long end = MAX;
            long mid;
            long answer = -1;

            while (start <= end) {
                mid = (start + end) / 2;

                if (mid * (mid + 1) / 2 < N) {
                    start = mid + 1;
                } else if (mid * (mid + 1) / 2 > N) {
                    end = mid - 1;
                } else {
                    answer = mid;
                    break;
                }
            }

            System.out.println("#" + test_case + " " + answer);
        }
    }
}
