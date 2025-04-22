package com.repsy.package_repo.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.repsy.package_repo.model.PackageEntity;
import com.repsy.package_repo.model.PackageMetadataDTO;
import com.repsy.package_repo.repository.PackageRepository;
import com.repsy.package_repo.service.PackageService;

@Service
public class PackageServiceImpl implements PackageService {

    private final PackageRepository repository;
    private final ObjectMapper objectMapper;

    public PackageServiceImpl(PackageRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void deployPackage(String name, String version, MultipartFile repFile, MultipartFile metaFile) {
        try {
            PackageMetadataDTO metadata = objectMapper.readValue(metaFile.getBytes(), PackageMetadataDTO.class);

            // TODO: Validation

            PackageEntity entity = new PackageEntity();
            entity.setName(name);
            entity.setVersion(version);
            entity.setAuthor(metadata.getAuthor());
            entity.setUploadedAt(LocalDateTime.now());

            entity.setFilePath(null);
            entity.setMetaPath(null);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't process meta file", e);
        }
    }
}
