package problems.p2;

import java.util.Scanner;

public class CountIpAndMask {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int k = in.nextInt();
        String[] ips = new String[k];
        for (int i = 0; i < k; i++) {
            String line = null;
            while (in.hasNextLine() && (line == null || line.isEmpty())) {
                line = in.nextLine().trim();
            }
            ips[i] = line;
        }
        count(ips);
    }

    private static void count(String[] ips) {
        int w = 0, p = 0;
        int[] stat = new int[5];
        for (String ip : ips) {
            String[] arr = ip.split("~");
            int ipGroup = classifyIp(arr[0]);
            boolean validMask = validateMask(arr[1]);
            if (!validMask) {
                w++;
            } else if (ipGroup < 0) {
                w++;
            } else {
                 if (ipGroup > 5) {
                    p++;
                    ipGroup -= 5;
                }
                stat[ipGroup-1]++;
            }
        }
        System.out.printf("%d %d %d %d %d %d %d\n", stat[0], stat[1], stat[2], stat[3], stat[4], w, p);
    }

    private static int classifyIp(String ip) {
        String[] ips = splitIp(ip);
        if (ips.length != 4) {
            return -1;
        }
        int ip0, ip1;
        try {
            ip0 = Integer.parseInt(ips[0]);
            if (ip0 < 0 || ip0 > 255) {
                return -1;
            }
            ip1 = Integer.parseInt(ips[1]);
            if (ip1 < 0 || ip1 > 255) {
                return -1;
            }
            int ip2 = Integer.parseInt(ips[2]);
            if (ip2 < 0 || ip2 > 255) {
                return -1;
            }
            int ip3 = Integer.parseInt(ips[3]);
            if (ip3 < 0 || ip3 > 255) {
                return -1;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
        int res = 0;
        if (ip0 >= 1 && ip0 <= 126) {
            res = 1;
        } else if (ip0 >= 128 && ip0 <= 191) {
            res = 2;
        } else if (ip0 >= 192 && ip0 <= 223) {
            res = 3;
        } else if (ip0 >= 224 && ip0 <= 239) {
            res = 4;
        } else if (ip0 >= 240 && ip0 <= 255) {
            res = 5;
        }
        if (res == 0) {
            return res;
        }
        if (isPrivateIp(ip0, ip1)) {
            res += 5;
        }
        return res;
    }

    private static boolean isPrivateIp(int ip0, int ip1) {
        if (ip0 == 10) {
            return true;
        } else if (ip0 == 172 && ip1 >= 16 && ip1 <= 31) {
            return true;
        } else {
            return ip0 == 192 && ip1 == 168;
        }
    }

    private static boolean validateMask(String ip) {
        String[] ips = splitIp(ip);
        if (ips.length != 4) {
            return false;
        }
        int ip0, ip1, ip2, ip3;
        try {
            ip0 = Integer.parseInt(ips[0]);
            ip1 = Integer.parseInt(ips[1]);
            ip2 = Integer.parseInt(ips[2]);
            ip3 = Integer.parseInt(ips[3]);
        } catch (NumberFormatException e) {
            return false;
        }
        if (ip0 == 255 && ip1 == 255 && ip2 == 255 && ip3 == 0) {
            return true;
        } else if (ip0 == 255 && ip1 == 255 && ip2 == 0 && ip3 == 0) {
            return true;
        } else {
            return ip0 == 255 && ip1 == 0 && ip2 == 0 && ip3 == 0;
        }
    }

    private static String[] splitIp(String ip) {
        return ip.split("\\.");
    }
}
