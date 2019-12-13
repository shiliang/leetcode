package leetcode.DFSBFS;

public class Decode {
    StringBuilder sb = new StringBuilder();
    int index = 1;
    int netxk = 0;
    public String decodeString(String s) {
        int k = s.charAt(0) - '0';
        helper(s, k);
        return sb.toString();
    }

    public String helper(String str, int k) {
        StringBuilder tmp = new StringBuilder();
        while (index < str.length()) {
            if (str.charAt(index) == '[') {
                index++;
                String substr = helper(str, k);

                for (int i = 0; i < k; i++) {
                    sb.append(substr);
                }
                k = netxk;
            } else if (str.charAt(index) == ']') {
                index++;
                if (index < str.length() && str.charAt(index) >= '0'
                        && str.charAt(index) <= '9') netxk = str.charAt(index++) - '0';
                return tmp.toString();
            } else {
                tmp.append(str.charAt(index++));
            }
        }
        return tmp.toString();
    }
}
