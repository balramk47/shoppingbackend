create TABLE category(
   id int auto_increment,
   name VARCHAR(50),
   description VARCHAR(255),
   image_url VARCHAR(50),
   is_active BOOLEAN,
   
   CONSTRAINT pk_category_id PRIMARY KEY (id)
   );