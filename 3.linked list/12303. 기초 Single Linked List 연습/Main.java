import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	private final static int MAX_NODE = 10000;
	private final static int ADD_HEAD = 1;
	private final static int ADD_TAIL = 2;
	private final static int ADD_NUM = 3;
	private final static int REMOVE = 4;
	private final static int PRINT = 5;
	private final static int END = 99;
	
	private final static UserSolution usersolution = new UserSolution();
	
	private static BufferedReader br;
	
	private static void run() throws Exception {
		
		int cmd, data, num, ret;
		int[] output = new int[MAX_NODE];
		String str;
		StringTokenizer st;
		
		while(true) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			cmd = Integer.parseInt(st.nextToken());
			
			switch(cmd) {
			case ADD_HEAD :
				data = Integer.parseInt(st.nextToken());
				usersolution.addNode2Head(data);
				break;
				
			case ADD_TAIL :
				data = Integer.parseInt(st.nextToken());
				usersolution.addNode2Tail(data);
				break;
				
			case ADD_NUM :
				data = Integer.parseInt(st.nextToken());
				num = Integer.parseInt(st.nextToken());
				usersolution.addNode2Num(data, num);
				break;
				
			case REMOVE :
				data = Integer.parseInt(st.nextToken());
				usersolution.removeNode(data);
				break;
				
			case PRINT :
				ret = usersolution.getList(output);
				for(int i = 0; i < ret; i++) {
					System.out.print(output[i] + " ");
				}
				System.out.println();
				break;
				
			case END :
				return;
			}
		}
	}
	public static void main(String[] args) throws Exception {
		int TC;
		//System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
		
		br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		TC = Integer.parseInt(str);
		
		for (int tc = 1; tc <= TC; tc++) {
			System.out.println("#" + tc);
			usersolution.init();
			run();
			System.out.println();
		}
	}

}
