package vn.com.lol.thresh.modules.storage.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.lol.common.entities.BaseEntity;
import vn.com.lol.thresh.modules.blob.entities.Blob;

import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "file_meta_data")
@Entity(name = "FileMetaData")
@Getter
@Setter
@NoArgsConstructor
public class FileMetadata extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 3405172041950211807L;

    @Column(name = "file_name")
    private String filename;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "size")
    private long size;

    @Column(name = "upload_time")
    private LocalDateTime uploadTime;

    @Column(name = "url")
    private String url;

    @Lob
    @Column(name = "content")
    private byte[] content;

    @ManyToOne
    @JoinColumn(name = "blob_id")
    private Blob fileBlob;
}
