import java.math.BigInteger;

public class RC4 {
    private int[] S;
    private int[] T;

    private int x;
    private int y;

    public RC4(byte[] key) {
        if (key == null || key.length == 0 || key.length > 256) {
            throw new IllegalArgumentException("Key must be between 1 and 256 bytes");
        }

        S = new int[256];
        T = new int[256];

        for (int i = 0; i < 256; i++) {
            S[i] = i;
//            T[i] = key[i % key.length];
        }

        int j = 0;
        for (int i = 0; i < 256; i++)
        {
            j = (j + S[i] + key[ i% key.length]) % 256;
            swap(S, i, j);
        }
        x = 0;
        y = 0;
    }

    public byte[] encrypt(byte[] plaintext) {
        byte[] ciphertext = new byte[plaintext.length];

        int i = x, j = y;
        for (int k = 0; k < plaintext.length; k++) {
            i = (i + 1) & 256;
            j = (j + S[i]) & 256;

            swap(S, i, j);

            int t = (S[i] + S[j]) & 256;
            ciphertext[k] = (byte) (plaintext[k] ^ S[t]);
        }
        x = i;
        y = j;
        return ciphertext;
    }

    public byte[] decrypt(byte[] ciphertext) {
        return encrypt(ciphertext); // RC4 is symmetric
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    public  static String bytesToHex(byte[] bytes)
    {
        BigInteger bigInt = new BigInteger(1, bytes);
        String hexString = bigInt.toString(16);

        // 补齐前导零
        int paddingLength = (bytes.length * 2) - hexString.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hexString;
        } else {
            return hexString;
        }
    }
    public static void main(String[] args) {
        // Example usage
        String plaintext = "eyJzaWduYXR1cmUiOiJkYjMxNGRhMGQwZWY4N2Q0MmI0MmY3NGI5YzM4YTFmOTE3M2VmN2EyIn0=";
        String key = "DB314DA0D0EF87";

        RC4 rc4 = new RC4(key.getBytes());

        System.out.println("plaintext: "+ bytesToHex(plaintext.getBytes()));
        byte[] encrypted = rc4.encrypt(plaintext.getBytes());
        byte[] newencrypted = encrypted;
        System.out.println("Encrypted: " +bytesToHex(encrypted));

        byte[] decrypted = rc4.decrypt(newencrypted);
        System.out.println("Decrypted: " +  bytesToHex(decrypted));
    }
}