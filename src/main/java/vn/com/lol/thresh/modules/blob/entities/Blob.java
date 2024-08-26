package vn.com.lol.thresh.modules.blob.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import vn.com.lol.common.entities.BaseEntity;
import vn.com.lol.thresh.modules.storage.entities.FileMetadata;
import vn.com.lol.common.utils.EncryptUtil;

import java.io.Serializable;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Blob extends BaseEntity implements Serializable {

    @Column(name = "name")
    private String name;

    @Column(name = "private_key")
    private String privateKey;

    @Column(name = "public_key")
    private String publicKey;

    @Column(name = "service_id")
    private String serviceId;

    @Transient
    private KeyPair keyPair;

    @OneToMany(mappedBy = "fileBlob")
    private List<FileMetadata> blobFiles;

    public KeyPair getKeyPair() throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (keyPair == null && StringUtils.isNotBlank(privateKey) && StringUtils.isNotBlank(publicKey)) {
                PublicKey pk = EncryptUtil.retrievePublicKey(this.publicKey);
                PrivateKey sk = EncryptUtil.retrievePrivateKey(this.privateKey);
                keyPair = new KeyPair(pk, sk);
                return keyPair;
            }

        return keyPair;
    }
}
