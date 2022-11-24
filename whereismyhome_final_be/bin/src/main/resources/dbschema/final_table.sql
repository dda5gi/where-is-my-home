use ssafy_final;

CREATE TABLE IF NOT EXISTS `ssafy_final`.`member` (
  `userid` VARCHAR(16) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  `userpwd` VARCHAR(16) NOT NULL,
  `joindate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `token` VARCHAR(1000) NULL DEFAULT NULL,
  PRIMARY KEY (`userid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

insert into member
values ('ssafy', '김싸피', 'ssafy', now(), null);

insert into member
values ('admin', '관리자', 'admin', now(), null);