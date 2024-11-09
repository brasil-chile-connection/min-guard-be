ALTER TABLE incident_image
    ADD COLUMN url VARCHAR;

ALTER TABLE incident_image
    RENAME COLUMN image_path TO key;
