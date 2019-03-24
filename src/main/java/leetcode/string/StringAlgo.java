package leetcode.string;

public class StringAlgo {

    public String validIPAddress(String IP) {
        if (IP.contains(".")) {
            return IPV4(IP);
        }

        if (IP.contains(":")) {
            return IPV6(IP);
        }

        return "Neither";
    }

    private String IPV4(String IP) {
        if (IP.charAt(IP.length() - 1) == '.') {
            return "Neither";
        }
        String[] strings = IP.split("\\.");
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].startsWith("0")) {
                return "Neither";
            }
            try {
                if (Integer.parseInt(strings[i]) > 255 ||
                        Integer.parseInt(strings[i]) < 0) {
                    return "Neither";
                }
            } catch (Exception e) {
                return "Neither";
            }

        }
        return "IPv4";
    }

    private String IPV6(String IP) {
        if (IP.charAt(IP.length() - 1) == ':') {
            return "Neither";
        }
        String[] strings = IP.split(":");
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].length() > 4 ) {
                return "Neither";
            }
        }
        return "IPv6";
    }
}
