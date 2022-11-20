
CREATE TABLE bidlist (
  bid_list_id INT AUTO_INCREMENT PRIMARY KEY,
  account VARCHAR(125) NOT NULL,
  type VARCHAR(125) NOT NULL,
  bid_quantity DOUBLE NOT NULL,
  ask_quantity DOUBLE NULL,
  bid DOUBLE NULL,
  ask DOUBLE NULL,
  benchmark VARCHAR(125) NULL,
  bid_list_date DATETIME NULL,
  commentary VARCHAR(125) NULL,
  security VARCHAR(125) NULL,
  status VARCHAR(10) NULL,
  trader VARCHAR(125) NULL,
  book VARCHAR(125) NULL,
  creation_name VARCHAR(125) NULL,
  creation_date DATETIME NULL,
  revision_name VARCHAR(125) NULL,
  revision_date DATETIME NULL,
  deal_name VARCHAR(125) NULL,
  deal_type VARCHAR(125) NULL,
  source_list_id VARCHAR(125) NULL,
  side VARCHAR(125) NULL
)
    ENGINE = MyISAM;

CREATE TABLE trade (
  trade_id INT AUTO_INCREMENT PRIMARY KEY,
  account VARCHAR(125) NOT NULL,
  type VARCHAR(125) NOT NULL,
  buy_quantity DOUBLE NOT NULL,
  sell_quantity DOUBLE NULL,
  buy_price DOUBLE NULL,
  sell_price DOUBLE NULL,
  trade_date DATETIME NULL,
  security VARCHAR(125) NULL,
  status VARCHAR(125) NULL,
  trader VARCHAR(125) NULL,
  benchmark VARCHAR(125) NULL,
  book VARCHAR(125) NULL,
  creation_name VARCHAR(125) NULL,
  creation_date DATETIME NULL,
  revision_name VARCHAR(125) NULL,
  revision_date DATETIME NULL,
  deal_name VARCHAR(125) NULL,
  deal_type VARCHAR(125) NULL,
  source_list_id VARCHAR(125) NULL,
  side VARCHAR(125) NULL
)
    ENGINE = MyISAM;

CREATE TABLE curvepoint (
  id INT AUTO_INCREMENT PRIMARY KEY,
  curve_id INT NOT NULL,
  as_of_date DATETIME NULL,
  term DOUBLE NOT NULL,
  value DOUBLE NOT NULL,
  creation_date DATETIME NULL
)
    ENGINE = MyISAM;

CREATE TABLE rating (
  id INT AUTO_INCREMENT PRIMARY KEY,
  moodys_rating VARCHAR(125) NOT NULL,
  sand_prating VARCHAR(125) NOT NULL,
  fitch_rating VARCHAR(125) NOT NULL,
  order_number INT
)
    ENGINE = MyISAM;

CREATE TABLE rulename (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(125) NOT NULL,
  description VARCHAR(125) NOT NULL,
  json VARCHAR(125) NOT NULL,
  template VARCHAR(125) NOT NULL,
  sql_str VARCHAR(125) NOT NULL,
  sql_part VARCHAR(125) NOT NULL
)
    ENGINE = MyISAM;

CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(125) NULL UNIQUE,
  password VARCHAR(125) NULL,
  fullname VARCHAR(125) NULL,
  role VARCHAR(125) NULL
)
    ENGINE = MyISAM;

#insert into Users(fullname, username, password, role) values("Administrator", "admin", "$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa", "ADMIN")
#insert into Users(fullname, username, password, role) values("User", "user", "$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa", "USER")