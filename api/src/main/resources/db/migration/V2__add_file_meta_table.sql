CREATE TABLE IF NOT EXISTS file_meta_data
(
    id           BIGINT                      NOT NULL,
    created_at   TIMESTAMP(6) WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at   TIMESTAMP(6) WITH TIME ZONE NOT NULL DEFAULT now(),
    is_deleted   BOOLEAN                     NOT NULL,
    file_name    VARCHAR(100)                NOT NULL,
    content_type TEXT                        ,
    size         BIGINT                      ,
    upload_time  TIMESTAMP(6) WITH TIME ZONE NOT NULL DEFAULT now(),
    url          TEXT                        NOT NULL,
    content      OID                        ,
    CONSTRAINT pk_file_meta_data PRIMARY KEY (id)
);
