// 代码生成时间: 2025-10-08 15:46:59
import org.springframework.stereotype.Service;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Service class for password encryption and decryption using AES algorithm.
 */
@Service
public class PasswordEncryptionDecryptionService {

    private static final String ALGORITHM = "AES";
    private static final String ALGORITHM_TRANSFORMATION = "AES";
    private static final String CHARSET_NAME = "UTF-8";
    private static final SecureRandom secureRandom = new SecureRandom();

    private SecretKey generateKey() throws Exception {
        // Generate a secret key using AES algorithm
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(256, secureRandom);
        return keyGenerator.generateKey();
    }

    public String encrypt(String data, SecretKey key) throws Exception {
        // Encrypt the data
        Cipher cipher = Cipher.getInstance(ALGORITHM_TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes(CHARSET_NAME));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String encryptedData, SecretKey key) throws Exception {
        // Decrypt the data
        Cipher cipher = Cipher.getInstance(ALGORITHM_TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes, CHARSET_NAME);
    }

    public String encryptPassword(String password) {
        try {
            SecretKey key = generateKey();
            return encrypt(password, key);
        } catch (Exception e) {
            // Handle encryption error
            e.printStackTrace();
            return null;
        }
    }

    public String decryptPassword(String encryptedPassword, byte[] keyBytes) {
        try {
            SecretKeySpec key = new SecretKeySpec(keyBytes, ALGORITHM);
            return decrypt(encryptedPassword, key);
        } catch (Exception e) {
            // Handle decryption error
            e.printStackTrace();
            return null;
        }
    }
}
