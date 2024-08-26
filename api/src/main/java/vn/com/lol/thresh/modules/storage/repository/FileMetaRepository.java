package vn.com.lol.thresh.modules.storage.repository;

import org.springframework.stereotype.Repository;
import vn.com.lol.common.repository.BaseRepository;
import vn.com.lol.thresh.modules.storage.entities.FileMetadata;

import java.util.Optional;

@Repository
public interface FileMetaRepository extends BaseRepository<FileMetadata, Long> {

    Optional<FileMetadata> findByFilename(String filename);
}
