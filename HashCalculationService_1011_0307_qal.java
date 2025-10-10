// 代码生成时间: 2025-10-11 03:07:19
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Service class for calculating hash values.
 */
@Service
public class HashCalculationService {

    private static final String HASHING_ALGORITHM = "SHA-256";

    /**
     * Calculates the hash value of the given data.
     *
     * @param data The data to be hashed.
# FIXME: 处理边界情况
     * @return The hash value of the data as a Base64 encoded string.
     * @throws NoSuchAlgorithmException If the specified hashing algorithm does not exist.
     */
    public String calculateHash(String data) throws NoSuchAlgorithmException {
        // Check for null or empty data
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Data cannot be null or empty");
# 添加错误处理
        }

        // Get a MessageDigest instance for the specified hashing algorithm
        MessageDigest digest = MessageDigest.getInstance(HASHING_ALGORITHM);

        // Update the digest with the data bytes
        digest.update(data.getBytes(StandardCharsets.UTF_8));

        // Calculate the hash
        byte[] hash = digest.digest();

        // Encode the hash as a Base64 string
        String encodedHash = Base64.getEncoder().encodeToString(hash);

        return encodedHash;
    }
}
