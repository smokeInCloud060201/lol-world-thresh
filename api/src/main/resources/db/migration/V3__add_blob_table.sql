CREATE TABLE IF NOT EXISTS blob
(
    id           BIGINT                      NOT NULL,
    created_at   TIMESTAMP(6) WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at   TIMESTAMP(6) WITH TIME ZONE NOT NULL DEFAULT now(),
    is_deleted   BOOLEAN                     NOT NULL,
    name VARCHAR(80)  NOT NULL,
    service_id VARCHAR(80)  NOT NULL,
    public_key VARCHAR(255) NOT NULL,
    private_key VARCHAR(255) NOT NULL,
    CONSTRAINT pk_blob PRIMARY KEY (id)
);

ALTER TABLE file_meta_data
    ADD COLUMN blob_id BIGINT NOT NULL;

ALTER TABLE file_meta_data
    ADD CONSTRAINT fk_file_meta_data_blob FOREIGN KEY (blob_id) REFERENCES blob (id);
