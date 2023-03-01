import java.util.Scanner;

class Solution {

    private static int mSeed = 5;

    private static final int MAXL = 50005;
    private static final int DUMMY_LEN = 5959;
    private static int score = 0;
    private static int total_score = 0;

    private static Scanner sc;
    private static UserSolution user = new UserSolution();

    private static char dummy1[] = new char[DUMMY_LEN];
    private static char init_string[] = new char[MAXL];
    private static char dummy2[] = new char[DUMMY_LEN];
    private static char result_string[] = new char[MAXL];
    private static char dummy3[] = new char[DUMMY_LEN];
    private static char user_ans_string[] = new char[MAXL];
    private static char dummy4[] = new char[DUMMY_LEN];
    private static char string_A[] = new char[4];
    private static char string_B[] = new char[4];
    private static int init_string_len = 0;
    private static int char_type = 0;
    private static int query_cnt = 0;

    private static int pseudo_rand()
    {
        mSeed = mSeed * 214013 + 2531011;
        return (mSeed >> 16) & 0x7FFF;
    }

    public static void main(String[] args) throws Exception {
//		System.setIn(new java.io.FileInputStream("sample_input.txt"));

        sc = new Scanner(System.in);

        int T = sc.nextInt();
        total_score = 0;
        for (int TC = 1; TC <= T; TC++) {
            score = 100;
            mSeed = sc.nextInt();
            init_string_len = sc.nextInt();
            char_type = sc.nextInt();
            query_cnt = sc.nextInt();

            for (int i = 0; i < init_string_len; i++)
            {
                init_string[i] = (char)(pseudo_rand() % char_type + 'a');
            }
            init_string[init_string_len] = '\0';

            user.init(init_string_len, init_string);

            string_A[3] = string_B[3] = '\0';
            for (int i = 0; i < query_cnt; i++)
            {
                for (int k = 0; k < 3; k++)
                {
                    string_A[k] = (char) ((pseudo_rand() % char_type) + 'a');
                    string_B[k] = (char) ((pseudo_rand() % char_type) + 'a');
                }
                int user_ans = user.change(string_A, string_B);
                int ans = sc.nextInt();

                if (ans != user_ans)
                {
                    score = 0;
                }
            }

            user.result(user_ans_string);


            sc.nextLine();
            String result_str_java = sc.nextLine();
            result_string = result_str_java.toString().toCharArray();

            for (int i = 0; i < init_string_len; i++)
            {
                if (result_string[i] != user_ans_string[i])
                {
                    score = 0;
                    break;
                }
            }

            total_score += score;
            System.out.println("#" + TC +" " + score);

        }
        System.out.println("Total score : " + total_score/T);
        sc.close();
    }

    ////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////

}