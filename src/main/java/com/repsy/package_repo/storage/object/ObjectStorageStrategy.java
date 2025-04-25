package com.repsy.package_repo.storage.object;

import org.springframework.web.multipart.MultipartFile;

import com.repsy.package_repo.storage.StorageStrategy;

public class ObjectStorageStrategy implements StorageStrategy {

    @Override
    public String save(String packageName, String version, MultipartFile file, String fileType) {
        // TODO: integrate minio
        return "a";
    }

    public byte[] load(String packageName, String version, String fileName) {
        // TODO: integrate minio
        byte[] returnValue = new byte[] { 1, 3, 2 };
        return returnValue;
    }
}
