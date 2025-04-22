package com.repsy.package_repo.storage.filesystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

import com.repsy.package_repo.storage.StorageStrategy;

public class FileSystemStorageStrategy implements StorageStrategy {
    private final Path rootDir = Paths.get("storage");

    public FileSystemStorageStrategy() throws IOException {
        Files.createDirectories(rootDir);
    }

    @Override
    public String save(String packageName, String version, MultipartFile file, String fileType) {
        try {
            Path packagePath = rootDir.resolve(packageName).resolve(version);
            Files.createDirectories(packagePath);

            Path filePath = packagePath.resolve(fileType);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.toString();
        } catch (IOException e) {
            throw new RuntimeException("File couldn't be saved.");
        }
    }

    @Override
    public byte[] load(String packageName, String version, String fileName) {
        try {
            Path filePath = rootDir.resolve(packageName).resolve(version).resolve(fileName);
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read the file.");
        }
    }
}
