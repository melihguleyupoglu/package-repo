package com.repsy.package_repo.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageStrategy {
    String save(String packageName, String version, MultipartFile file, String fileType);

    byte[] load(String packageName, String version, String fileName);
}
