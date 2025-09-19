// 代码生成时间: 2025-09-20 06:42:23
import org.springframework.stereotype.Component;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * FileCompressionUnzipTool provides functionalities to compress and unzip files.
 * It uses Apache Commons Compress and Java's built-in zip libraries.
 */
@Component
public class FileCompressionUnzipTool {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileCompressionUnzipTool.class);

    private static final String ZIP_FILE_EXTENSION = ".zip";
    private static final String GZIP_FILE_EXTENSION = ".gz";

    public void unzipFile(String zipFilePath, String destDirectory) {
        try {
            ArchiveInputStream archiveInputStream = new ArchiveStreamFactory().createArchiveInputStream(
                new FileInputStream(zipFilePath));
            ArchiveEntry entry;
            int count;
            byte[] data = new byte[1024];

            while ((entry = archiveInputStream.getNextEntry()) != null) {
                File newFile = new File(destDirectory, entry.getName());
                if (!entry.isDirectory()) {
                    try (FileOutputStream fos = new FileOutputStream(newFile)) {
                        while ((count = archiveInputStream.read(data)) != -1) {
                            fos.write(data, 0, count);
                        }
                    }
                } else {
                    newFile.mkdirs();
                }
            }
            archiveInputStream.close();
            LOGGER.info("Unzipped '{}' into '{}'", zipFilePath, destDirectory);
        } catch (IOException | ArchiveException e) {
            LOGGER.error("Error unzipping file: {}", e.getMessage());
        }
    }

    public void unzipGzipFile(String gzipFilePath, String destDirectory) {
        try {
            Path destPath = Paths.get(destDirectory);
            Path unzippedFilePath = Paths.get(destDirectory, gzipFilePath.substring(
                gzipFilePath.lastIndexOf(File.separator) + 1, gzipFilePath.lastIndexOf(GZIP_FILE_EXTENSION)));
            try (GZIPInputStream gzipInputStream = new GZIPInputStream(
                    new FileInputStream(gzipFilePath));
                 FileOutputStream fileOutputStream = new FileOutputStream(unzippedFilePath.toFile())) {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = gzipInputStream.read(buffer)) > 0) {
                    fileOutputStream.write(buffer, 0, len);
                }
            }
            LOGGER.info("Unzipped GZIP file '{}' into '{}'", gzipFilePath, destDirectory);
        } catch (IOException e) {
            LOGGER.error("Error unzipping GZIP file: {}", e.getMessage());
        }
    }

    // Additional methods for file compression can be added here

    // Example usage:
    // FileCompressionUnzipTool tool = new FileCompressionUnzipTool();
    // tool.unzipFile("/path/to/your.zip", "/path/to/destination");
    // tool.unzipGzipFile("/path/to/your.gz", "/path/to/destination");
}
