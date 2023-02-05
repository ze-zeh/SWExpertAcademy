import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            String result;

            if (solve(N, M)) {
                result = "ON";
            } else {
                result = "OFF";
            }
            bw.write("#" + test_case + " " + result + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean solve(int n, int m) {
        int check = m % (1 << n);
        boolean result = true;

        for (int i = 0; i < n; i++) {
            if (((1 << i) & check) == 0) {
                result = false;
                break;
            }
        }

        return result;
    }
}
