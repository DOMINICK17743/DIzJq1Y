// 代码生成时间: 2025-09-19 00:44:00
import org.springframework.stereotype.Service;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service class to analyze memory usage.
 */
@Service
public class MemoryAnalysisService {

    private final MemoryMXBean memoryMXBean;

    // Cache to store memory usage data
    private final Map<String, MemoryUsage> memoryUsageCache = new ConcurrentHashMap<>();

    public MemoryAnalysisService() {
        this.memoryMXBean = ManagementFactory.getMemoryMXBean();
    }

    /**
     * Get the current memory usage.
     * @return Memory usage data as a map.
     */
    public Map<String, MemoryUsage> getCurrentMemoryUsage() {
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();

        // Store the memory usage data into the cache
        memoryUsageCache.put("heap", heapMemoryUsage);
        memoryUsageCache.put("nonHeap", nonHeapMemoryUsage);

        return memoryUsageCache;
    }

    /**
     * Get the memory usage at a specific point in time.
     * @param key The key to retrieve the memory usage from the cache.
     * @return Memory usage data or null if not found.
     */
    public MemoryUsage getMemoryUsage(String key) {
        return memoryUsageCache.get(key);
    }

    /**
     * Clear the memory usage cache.
     */
    public void clearMemoryUsageCache() {
        memoryUsageCache.clear();
    }
}
