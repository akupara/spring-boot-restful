CREATE TABLE `dept` (
  `id`       VARCHAR(22)  NOT NULL,
  `code`     VARCHAR(20)  NOT NULL,
  `name`     VARCHAR(200) NOT NULL,
  `location` VARCHAR(200) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uidx_dept_code` (`code` ASC)
);

CREATE TABLE `emp` (
  `id`           VARCHAR(22)     NOT NULL,
  `code`         VARCHAR(20)     NOT NULL,
  `name`         VARCHAR(200)    NOT NULL,
  `mgr_id`       VARCHAR(22)     NULL,
  `dept_id`      VARCHAR(22)     NULL,
  `salary`       DECIMAL(10, 2)  NULL      DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uidx_emp_code` (`code` ASC),
  INDEX `idx_emp_mgr_id` (`mgr_id` ASC),
  FOREIGN KEY fk_employee_dept_code (dept_id) REFERENCES dept (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


