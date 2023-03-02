import java.util.Scanner;

class Solution {

	private static UserSolution usersolution = new UserSolution();
	
	private final static int CMD_MKDIR		= 1;
	private final static int CMD_RM			= 2;
	private final static int CMD_CP			= 3;
	private final static int CMD_MV			= 4;
	private final static int CMD_FIND		= 5;
	
	private final static int NAME_MAXLEN	= 6;
	private final static int PATH_MAXLEN	= 1999;

	
	private static boolean run(Scanner sc, int m) throws Exception {

		boolean isAccepted = true;
		int cmd;
		char[] name;
		char[] path1;
		char[] path2;
		String inputStr = "";

		while (m-- > 0) {
			
			cmd = sc.nextInt();

			if (cmd == CMD_MKDIR) {
				inputStr = sc.next() + " ";
				path1 = inputStr.toCharArray();
				path1[inputStr.length() - 1] = '\0';
				inputStr = sc.next() + " ";
				name = inputStr.toCharArray();
				name[inputStr.length() - 1] = '\0';
				
				usersolution.cmd_mkdir(path1, name);
			}
			else if (cmd == CMD_RM) {
				inputStr = sc.next() + " ";
				path1 = inputStr.toCharArray();
				path1[inputStr.length() - 1] = '\0';
				
				usersolution.cmd_rm(path1);
			}
			else if (cmd == CMD_CP) {
				inputStr = sc.next() + " ";
				path1 = inputStr.toCharArray();
				path1[inputStr.length() - 1] = '\0';
				inputStr = sc.next() + " ";
				path2 = inputStr.toCharArray();
				path2[inputStr.length() - 1] = '\0';
				
				usersolution.cmd_cp(path1, path2);
			}
			else if (cmd == CMD_MV) {
				inputStr = sc.next() + " ";
				path1 = inputStr.toCharArray();
				path1[inputStr.length() - 1] = '\0';
				inputStr = sc.next() + " ";
				path2 = inputStr.toCharArray();
				path2[inputStr.length() - 1] = '\0';
				
				usersolution.cmd_mv(path1, path2);
			}
			else {
				int ret;
				int answer;

				inputStr = sc.next() + " ";
				path1 = inputStr.toCharArray();
				path1[inputStr.length() - 1] = '\0';
				
				ret = usersolution.cmd_find(path1);
				
				answer = sc.nextInt();

				isAccepted &= (ret == answer);
			}
		}

		return isAccepted;
	}
	
	public static void main(String[] args) throws Exception {
		int test, T;
		int n, m;

		// System.setIn(new java.io.FileInputStream("sample_input.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for (test = 1; test <= T; ++test) {
			
			n = sc.nextInt();
			m = sc.nextInt();

			usersolution.init(n);

			if (run(sc, m)) {
				System.out.println("#" + test + " 100");
			}
			else {
				System.out.println("#" + test + " 0");
			}
		}
		
		sc.close();
	}
}
