package vn.com.lol.thresh.modules.blob.repository;

import org.springframework.stereotype.Repository;
import vn.com.lol.common.repository.BaseRepository;
import vn.com.lol.thresh.modules.blob.entities.Blob;

@Repository
public interface BlobRepository extends BaseRepository <Blob, Long> {
}
