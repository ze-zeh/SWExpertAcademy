import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            long N = Integer.parseInt(br.readLine());
            bw.write("#" + test_case + " " + solve(N) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static long solve(long n) {
        boolean[] arr = new boolean[10];
        int count = 0;
        boolean flag = true;

        while (flag) {
            count++;
            String cur = String.valueOf(count * n);
            for (int i = 0; i < cur.length(); i++) {
                int idx = cur.charAt(i) - '0';
                if (arr[idx]) continue;
                arr[idx] = true;
            }
            for (int i = 0; i < 10; i++) {
                if (!arr[i]) {
                    flag = true;
                    break;
                }
                flag = false;
            }
        }

        return count * n;
    }
}
