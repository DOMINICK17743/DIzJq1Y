// 代码生成时间: 2025-09-20 11:09:51
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

/*
 * ApiResponseFormatterService is a service class designed to format API responses.
 * It includes error handling and follows Java best practices for maintainability and scalability.
 */
@Service
public class ApiResponseFormatterService {

    /*
     * Formats a successful API response with a status code.
     * @param data The payload data to be included in the response.
     * @param statusCode The HTTP status code to be returned.
     * @return A formatted response map.
     */
    public Map<String, Object> formatSuccessResponse(Object data, HttpStatus statusCode) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("statusCode", statusCode.value());
        response.put("data", data);
        return response;
    }

    /*
     * Formats an error API response with a status code.
     * @param errorMessage The error message to be included in the response.
     * @param statusCode The HTTP status code to be returned.
     * @return A formatted response map.
     */
    public Map<String, Object> formatErrorResponse(String errorMessage, HttpStatus statusCode) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("statusCode", statusCode.value());
        response.put("errorMessage", errorMessage);
        return response;
    }

    /*
     * Throws a ResponseStatusException with the specified status and error message.
     * @param errorMessage The error message to be included in the exception.
     * @param statusCode The HTTP status code to be thrown.
     * @throws ResponseStatusException with the specified status and error message.
     */
    public void throwFormattedException(String errorMessage, HttpStatus statusCode) {
        throw new ResponseStatusException(statusCode, errorMessage);
    }
}
