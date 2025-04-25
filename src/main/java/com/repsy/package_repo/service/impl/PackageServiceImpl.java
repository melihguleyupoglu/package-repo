package com.repsy.package_repo.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.repsy.package_repo.model.PackageEntity;
import com.repsy.package_repo.model.PackageMetadataDTO;
import com.repsy.package_repo.repository.PackageRepository;
import com.repsy.package_repo.service.PackageService;
import com.repsy.package_repo.storage.StorageStrategy;

@Service
public class PackageServiceImpl implements PackageService {

    private final PackageRepository repository;
    private final ObjectMapper objectMapper;
    private final StorageStrategy storageStrategy;

    public PackageServiceImpl(PackageRepository repository, ObjectMapper objectMapper,
            StorageStrategy storageStrategy) {
        this.repository = repository;
        this.objectMapper = objectMapper;
        this.storageStrategy = storageStrategy;
    }

    @Override
    public void deployPackage(String name, String version, MultipartFile repFile, MultipartFile metaFile) {
        try {
            PackageMetadataDTO metadata = objectMapper.readValue(metaFile.getBytes(), PackageMetadataDTO.class);

            // TODO: Validation

            String repFilePath = storageStrategy.save(name, version, repFile, "package.rep");
            String metaFilePath = storageStrategy.save(name, version, metaFile, "meta.json");

            PackageEntity entity = new PackageEntity();
            entity.setName(name);
            entity.setVersion(version);
            entity.setAuthor(metadata.getAuthor());
            entity.setUploadedAt(LocalDateTime.now());

            entity.setFilePath(repFilePath);
            entity.setMetaPath(metaFilePath);

            repository.save(entity);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't process meta file", e);
        }
    }

    @Override
    public byte[] downloadFile(String packageName, String version, String fileName) {
        return storageStrategy.load(packageName, version, fileName);
    }
}
