// 代码生成时间: 2025-10-05 19:56:12
package com.example.filepermissionmanager;

import org.springframework.stereotype.Service;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Set;

@Service
public class FilePermissionManager {

    /**
     * Sets the permissions of a file.
     *
     * @param filePath The path to the file.
     * @param permissions A set of permissions to be set.
     * @throws IOException If an I/O error occurs.
     */
    public void setPermissions(String filePath, Set<PosixFilePermission> permissions) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new IOException("File does not exist: " + filePath);
        }
        Files.setPosixFilePermissions(path, permissions);
    }

    /**
     * Gets the permissions of a file.
     *
     * @param filePath The path to the file.
     * @return A set of permissions for the file.
     * @throws IOException If an I/O error occurs.
     */
    public Set<PosixFilePermission> getPermissions(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new IOException("File does not exist: " + filePath);
        }
        return Files.getPosixFilePermissions(path);
    }

    /**
     * Checks if a file has the specified permission.
     *
     * @param filePath The path to the file.
     * @param permission The permission to check for.
     * @return True if the file has the permission, false otherwise.
     * @throws IOException If an I/O error occurs.
     */
    public boolean hasPermission(String filePath, PosixFilePermission permission) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new IOException("File does not exist: " + filePath);
        }
        return Files.getPosixFilePermissions(path).contains(permission);
    }
}
