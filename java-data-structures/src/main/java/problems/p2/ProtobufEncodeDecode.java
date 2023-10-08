package problems.p2;

public class ProtobufEncodeDecode {
    public static void main(String[] args) {
        System.out.println(encode(100L));
        System.out.println(decode("0xe70x07"));
        System.out.println(encode(999L));
        System.out.println(decode("0x64"));
    }

    public static String encode(long n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            long k = n & ((1 << 7) - 1);
            n >>= 7;
            if (n > 0) {
                k |= (1 << 7);
            }
            String hex = Long.toHexString(k);
            sb.append("0X" + (hex.length() > 1 ? hex : "0" + hex));
        }
        return sb.toString();
    }

    public static long decode(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i += 4) {
            String sub = str.substring(i+2, i+4);
            int k = Integer.parseInt(sub, 16);
            k &= ((1 << 7) - 1);
            sb.insert(0, Integer.toBinaryString(k));
        }
        return Long.parseLong(sb.toString(), 2);
    }
}
