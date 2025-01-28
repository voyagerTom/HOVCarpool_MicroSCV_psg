
-- hov.hov_user definition

CREATE TABLE hov_user (
  user_id int NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  PRIMARY KEY (user_id)
);




-- hov.carpool definition

CREATE TABLE carpool (
  id int NOT NULL AUTO_INCREMENT,
  driver_Id int NOT NULL,
  launch_time varchar(255) DEFAULT NULL,
  site varchar(255) DEFAULT NULL,
  destination varchar(255) DEFAULT NULL,
  pick_AMT int NOT NULL,
  order_AMT int NOT NULL,
  is_cancel varchar(255) DEFAULT NULL,
  create_time varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);


-- hov.driver definition

CREATE TABLE driver (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  role_name varchar(255) DEFAULT NULL,
  role_name_chinese varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- hov.role definition

CREATE TABLE role (
  id int NOT NULL ,
  role_name varchar(30) NOT NULL ,
  role_name_chinese varchar(30) NOT NULL ,
  PRIMARY KEY (id)
);


-- hov.approve_todo_list definition

CREATE TABLE approve_todo_list (
  atl_id int NOT NULL AUTO_INCREMENT ,
  user_id int NOT NULL  ,
  role_id int NOT NULL  ,
  msg varchar(255) DEFAULT NULL,
  apply_time varchar(255) DEFAULT NULL,
  is_approve varchar(255) DEFAULT 'TBD',
  PRIMARY KEY (atl_id),
  CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES role (id),
  CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES hov_user (user_id)
);


-- hov.carpool_map definition

CREATE TABLE carpool_map (
  cmId int NOT NULL AUTO_INCREMENT ,
  carpool_id int NOT NULL  ,
  user_id int NOT NULL  ,
  order_time varchar(255) DEFAULT CURRENT_TIMESTAMP ,
  is_cancel varchar(255) DEFAULT NULL,
  PRIMARY KEY (cmId),
  CONSTRAINT carpool_map_fk_user_id FOREIGN KEY (user_id) REFERENCES hov_user (user_id),
  CONSTRAINT carpool_map_ibfk_1 FOREIGN KEY (carpool_id) REFERENCES carpool (id)
);

-- hov.user_role_map definition

CREATE TABLE user_role_map (
  urm_id int NOT NULL AUTO_INCREMENT ,
  user_id int NOT NULL ,
  role_id int NOT NULL ,
  PRIMARY KEY (urm_id),
  CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES role (id),
  CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES hov_user (user_id)
);