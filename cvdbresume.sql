CREATE TABLE resume (
  uuid CHAR(36) PRIMARY KEY NOT NULL ,
  full_name TEXT NOT NULL ,
  location TEXT ,
  home_page TEXT
);

CREATE TABLE contact (
  id SERIAL,
  resume_uuid CHAR(36) NOT NULL ,
  type TEXT NOT NULL ,
  value TEXT NOT NULL ,
  CONSTRAINT contact_pkey PRIMARY KEY(id),
  CONSTRAINT contact_fk FOREIGN KEY (resume_uuid)
  REFERENCES resume(uuid)
  ON DELETE CASCADE
  ON UPDATE NO ACTION
  NOT DEFERRABLE
);


  CREATE UNIQUE INDEX contakc_idx ON contact USING BTREE (resume_uuid, type);