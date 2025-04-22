package com.repsy.package_repo.service;

import org.springframework.web.multipart.MultipartFile;

public interface PackageService {
    void deployPackage(String name, String version, MultipartFile repFile, MultipartFile metaFile);
}
