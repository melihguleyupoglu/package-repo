package com.repsy.package_repo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.repsy.package_repo.storage.StorageStrategy;

public class StorageConfig {

    @Autowired
    private Environment env;

    @Bean
    public StorageStrategy storageStrategy(
            @Qualifier("file-system") StorageStrategy fileSystem,
            @Qualifier("object-storage") StorageStrategy objectStorage) {
        String strategy = env.getProperty("storage.strategy", "file-system");
        return strategy.equals("object-strategy") ? objectStorage : fileSystem;
    }

}
