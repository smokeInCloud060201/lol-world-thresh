package vn.com.lol.thresh.modules.storage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.com.lol.common.controller.BaseController;
import vn.com.lol.thresh.modules.storage.entities.FileMetadata;
import vn.com.lol.thresh.modules.storage.service.StorageService;
import vn.com.lol.thresh.modules.storage.service.impl.FileSystemStorageService;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileUploadController extends BaseController {

    private final StorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) throws Exception {
        String fileUrl = storageService.store(file);
        return ResponseEntity.ok(fileUrl);
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws Exception {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @GetMapping("/metadata/{filename:.+}")
    public ResponseEntity<FileMetadata> getFileMetadata(@PathVariable String filename) {
        FileMetadata metadata = ((FileSystemStorageService) storageService).getFileMetadata(filename);
        return ResponseEntity.ok(metadata);
    }
}
