import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    static final int ARR_SIZE = 2000;
    static final long HASH_SIZE = (1 << 30);
    static final long DIV = (HASH_SIZE - 1);
    static BufferedReader br;
    static int H;
    static int W;
    static int N;
    static int M;
    static int[][] dream;
    static int[][] teacher;
    static int[][] tmp;
    static int[][] teacherHash;
    static int result;

    public static void main(String[] args) throws Exception {
//        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testcase = 1; testcase <= T; ++testcase) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            dream = new int[ARR_SIZE][ARR_SIZE];
            teacher = new int[ARR_SIZE][ARR_SIZE];
            tmp = new int[ARR_SIZE][ARR_SIZE];
            teacherHash = new int[ARR_SIZE][ARR_SIZE];
            result = 0;

            input();

            for (int i = 0; i < H; i++) tmp[0][i] = getHash(dream[i], W, 4);
            int hash = getHash(tmp[0], H, 5);
            int mulC = calcMul(W, 4);
            int mulR = calcMul(H, 5);

            for (int i = 0; i < N; i++) {
                tmp[0][i] = getHash(teacher[i], W, 4);
                for (int j = 1; j < M - W + 1; j++) {
                    tmp[j][i] = getNext(tmp[j - 1][i], teacher[i][j - 1], mulC, teacher[i][j + W - 1], 4);
                }
            }

            for (int i = 0; i < M - W + 1; i++) {
                teacherHash[0][i] = getHash(tmp[i], H, 5);
                for (int j = 1; j < N - H + 1; j++) {
                    teacherHash[j][i] = getNext(teacherHash[j - 1][i], tmp[i][j - 1], mulR, tmp[i][j + H - 1], 5);
                }
            }

            for (int i = 0; i < N - H + 1; i++) {
                for (int j = 0; j < M - W + 1; j++) {
                    if (teacherHash[i][j] == hash) result++;
                }
            }

            System.out.println("#" + testcase + " " + result);
        }

        br.close();
    }

    public static void input() throws IOException {
        for (int i = 0; i < H; i++) {
            String str = br.readLine();
            for (int j = 0; j < W; j++) {
                if (str.charAt(j) == 'o') dream[i][j] = 1;
            }
        }

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                if (str.charAt(j) == 'o') teacher[i][j] = 1;
            }
        }
    }

    public static int calcMul(int num, int shift) {
        long rev = 1;
        for (int i = 1; i < num; i++) rev = (rev << shift) + rev;
        return (int) (rev & DIV);
    }

    public static int getHash(int[] piv, int num, int shift) {
        long hash = 0;
        for (int i = 0; i < num; i++) hash = (hash << shift) + hash + piv[i];
        return (int) (hash & DIV);
    }

    public static int getNext(int prev, int sub, int mul, int add, int shift) {
        long hash = prev - ((long) sub * mul);
        hash = (hash << shift) + hash + add;
        return (int) (hash & DIV);
    }
}
