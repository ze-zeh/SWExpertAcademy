import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            List<Integer> list = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();

                if (command.equals("I")) {
                    int idx = Integer.parseInt(st.nextToken());
                    int num = Integer.parseInt(st.nextToken());
                    list.add(idx, num);
                } else if (command.equals("D")) {
                    int idx = Integer.parseInt(st.nextToken());
                    list.remove(idx);
                } else if (command.equals("C")) {
                    int idx = Integer.parseInt(st.nextToken());
                    int num = Integer.parseInt(st.nextToken());
                    list.set(idx, num);
                }
            }

            if (list.size() > L) {
                bw.write("#" + test_case + " " + list.get(L) + "\n");
            } else {
                bw.write("#" + test_case + " -1\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
