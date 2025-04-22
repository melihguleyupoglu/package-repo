package com.repsy.package_repo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.repsy.package_repo.model.PackageEntity;

public interface PackageRepository extends JpaRepository<PackageEntity, Long> {
    boolean existsByNameAndVersion(String name, String version);
}
