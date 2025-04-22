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
}
