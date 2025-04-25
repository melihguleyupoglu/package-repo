package com.repsy.package_repo.storage.filesystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.repsy.package_repo.storage.StorageStrategy;

@Service
@Qualifier("file-system")
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
            if (!Files.exists(filePath)) {
                throw new FileNotFoundException("Couldn't find the file: " + filePath);
            }
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read the file.");
        }
    }
}
