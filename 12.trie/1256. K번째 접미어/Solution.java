import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public static void main(String[] args) throws Exception {
//        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int K = Integer.parseInt(br.readLine());
            String str = br.readLine();

            ArrayList<String> arr = new ArrayList<>();
            for (int i = 0; i < str.length(); i++) arr.add(str.substring(i));
            Collections.sort(arr);

            System.out.println("#" + test_case + " " + arr.get(K - 1));
        }
    }
}
