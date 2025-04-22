package com.repsy.package_repo.model;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;;

public class PackageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @SuppressWarnings("unused")
    private String name;
    @SuppressWarnings("unused")
    private String version;
    @SuppressWarnings("unused")
    private String author;

    @SuppressWarnings("unused")
    private String filePath; // rep formatted file location
    @SuppressWarnings("unused")
    private String metaPath; // meta.json formatted file location

    @SuppressWarnings("unused")
    private LocalDateTime uploadedAt;

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setMetaPath(String metaPath) {
        this.metaPath = metaPath;
    }

}
