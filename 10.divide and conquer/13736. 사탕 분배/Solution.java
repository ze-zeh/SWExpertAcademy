import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    static long sum;

    public static void main(String[] args) throws Exception {
//        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            long A = Integer.parseInt(st.nextToken());
            long B = Integer.parseInt(st.nextToken());
            long K = Integer.parseInt(st.nextToken());
            sum = A + B;
            long result = (pow(K) * B) % sum;

            System.out.println("#" + test_case + " " + Math.min(result, sum - result));
        }
    }

    static long pow(long k) {
        if (k == 0) return 1;
        if (k == 1) return 2;

        if (k % 2 == 0) {
            long result = pow(k / 2);
            return (result * result) % sum;
        } else {
            long result = pow((k - 1) / 2);
            return (result * result) * 2 % sum;
        }
    }
}
