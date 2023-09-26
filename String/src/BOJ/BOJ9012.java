package BOJ;

import java.io.*;
import java.util.*;

public class BOJ9012 {
	static int T;
    static String str;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
 
    public static void main(String[] args) throws IOException {
    	T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            str = br.readLine();
            Stack<Character> s = new Stack<>();
            boolean check = true;
            for (int i = 0; i < str.length(); i++) {
                char tmp = str.charAt(i);
                if (tmp == '(' || tmp == '[' || tmp == '{' || tmp == '<') {
                    s.push(tmp);
                } else {
                    if (s.isEmpty()) {
                        sb.append("NO").append('\n');
                        check = false;
                        break;
                    } else {
                        if (tmp == ')') {
                            if (s.pop() != '(') {
                                sb.append("NO").append('\n');
                                check = false;
                                break;
                            }
                        } else if (tmp == ']') {
                            if (s.pop() != '[') {
                                sb.append("NO").append('\n');
                                check = false;
                                break;
                            }
                        } else if (tmp == '>') {
                            if (s.pop() != '<') {
                                sb.append("NO").append('\n');
                                check = false;
                                break;
                            }
                        } else if (tmp == '}') {
                            if (s.pop() != '{') {
                                sb.append("NO").append('\n');
                                check = false;
                                break;
                            }
                        }
                    }
                }
            }
            if (s.size() == 0 && check)
                sb.append("YES").append('\n');
            else if(s.size() != 0 && check) {
            	sb.append("NO").append('\n');
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
