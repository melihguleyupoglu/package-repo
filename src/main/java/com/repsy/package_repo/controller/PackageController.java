package com.repsy.package_repo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.repsy.package_repo.service.PackageService;

@RestController
@RequestMapping
public class PackageController {
    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @PostMapping("/{packageName}/{version}")
    public ResponseEntity<String> uploadPackage(
            @PathVariable String packageName,
            @PathVariable String version,
            @RequestPart("package.rep") MultipartFile repFile,
            @RequestPart("meta.json") MultipartFile metaFile) {
        packageService.deployPackage(packageName, version, repFile, metaFile);
        return ResponseEntity.status(HttpStatus.CREATED).body("Package successfully loaded.");
    }

    @GetMapping("/{packageName}/{version}/{fileName}")
    public ResponseEntity<byte[]> downloadPackage(
            @PathVariable String packageName,
            @PathVariable String version,
            @PathVariable String fileName) {
        byte[] fileData = packageService.downloadFile(packageName, version, fileName);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + fileName)
                .body(fileData);
    }
}
