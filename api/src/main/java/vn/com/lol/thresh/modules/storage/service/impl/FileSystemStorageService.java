package vn.com.lol.thresh.modules.storage.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.com.lol.thresh.modules.storage.entities.FileMetadata;
import vn.com.lol.thresh.modules.storage.repository.FileMetaRepository;
import vn.com.lol.thresh.modules.storage.service.StorageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Objects;

import static vn.com.lol.thresh.commons.constant.StorageConstant.STORAGE_ROOT_PATH;

@Service
@RequiredArgsConstructor
public class FileSystemStorageService implements StorageService {


    private final FileMetaRepository fileMetadataRepository;

    @Override
    public String store(MultipartFile file) throws IOException {
        Path rootLocation = Paths.get(STORAGE_ROOT_PATH);
        try {
            // Ensure the storage directory exists
            Files.createDirectories(rootLocation);

            // Store the file on the file system
            Path destinationFile = rootLocation.resolve(
                            Paths.get(Objects.requireNonNull(file.getOriginalFilename())))
                    .normalize().toAbsolutePath();
            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

            // Save file metadata in the database
            FileMetadata metadata = new FileMetadata();
            metadata.setFilename(file.getOriginalFilename());
            metadata.setContentType(file.getContentType());
            metadata.setSize(file.getSize());
            metadata.setUploadTime(LocalDateTime.now());
            metadata.setUrl("/files/" + file.getOriginalFilename());
            fileMetadataRepository.save(metadata);

            return metadata.getUrl();
        } catch (Exception e) {
            throw new IOException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public Resource load(String filename) throws IOException {
        Path rootLocation = Paths.get(STORAGE_ROOT_PATH + "/pyke");
        if (!rootLocation.isAbsolute()) {
            Files.createDirectories(rootLocation);
        }
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new IOException("Could not read file: " + filename);
            }
        } catch (Exception e) {
            throw new IOException("Could not read file: " + filename, e);
        }
    }

    public FileMetadata getFileMetadata(String filename) {
        return fileMetadataRepository.findByFilename(filename).orElse(null);
    }
}
