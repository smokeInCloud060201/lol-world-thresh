package vn.com.lol.thresh.modules.blob.dtos.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BlobDTO {
    private String blobName;
    private String publicKey;
}
