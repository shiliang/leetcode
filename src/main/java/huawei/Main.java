package huawei;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        ArrayList<String> strlist = new ArrayList<>();
        for(int i = 0; i < num; i++) {
            String s = input.next();
            if (s.length() % 8 != 0) {
                s = s + "00000000";
            }
            while (s.length() >= 8) {
                strlist.add(s.substring(0,8));
                s = s.substring(8);
            }
        }
        Collections.sort(strlist);
        for (String s: strlist
             ) {
            System.out.print(s + " ");
        }

    }
}
