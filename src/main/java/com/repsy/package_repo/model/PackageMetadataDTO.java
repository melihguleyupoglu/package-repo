package com.repsy.package_repo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PackageMetadataDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String version;

    @NotBlank
    private String author;

    @NotNull
    private List<DependencyDTO> dependencies;

    public static class DependencyDTO {
        @NotBlank
        private String packageName;

        @NotBlank
        private String version;
    }
}