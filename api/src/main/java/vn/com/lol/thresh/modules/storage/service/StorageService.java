package vn.com.lol.thresh.modules.storage.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.IOException;

public interface StorageService {
    String store(MultipartFile file) throws IOException;
    Resource load(String filename) throws IOException;
}
