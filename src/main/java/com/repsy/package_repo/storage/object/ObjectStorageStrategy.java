package com.repsy.package_repo.storage.object;

import org.springframework.stereotype.Service;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.multipart.MultipartFile;

import com.repsy.package_repo.storage.StorageStrategy;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;

@Service
@Qualifier("object-storage")
public class ObjectStorageStrategy implements StorageStrategy {

    private final MinioClient minioClient;
    private final String bucketName = "repsy-bucket";

    public ObjectStorageStrategy() {
        this.minioClient = MinioClient.builder()
                .endpoint("http://minio:9000")
                .credentials("minioadmin", "minioadmin")
                .build();

        try {
            boolean found = minioClient.bucketExists(
                    io.minio.BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                minioClient.makeBucket(
                        io.minio.MakeBucketArgs.builder().bucket(bucketName).build());
            }

        } catch (Exception e) {
            throw new RuntimeException("Couldn't create minio connection", e);
        }
    }

    @Override
    public String save(String packageName, String version, MultipartFile file, String fileType) {
        String objectName = packageName + "/" + version + "/" + fileType;
        try (InputStream is = file.getInputStream()) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(is, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());
            return objectName;
        } catch (Exception e) {
            throw new RuntimeException("File couldn't be saved to MinIO", e);
        }
    }

    @Override
    public byte[] load(String packageName, String version, String fileName) {
        String objectName = packageName + "/" + version + "/" + fileName;
        try {
            InputStream is = minioClient.getObject(
                    GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
            return is.readAllBytes();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't load the file from MinIO", e);
        }
    }
}
