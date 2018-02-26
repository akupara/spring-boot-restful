ALTER TABLE `dept`
  ADD COLUMN `created_on` DATETIME NOT NULL
  AFTER `location`,
  ADD COLUMN `created_by` VARCHAR(45) NOT NULL
  AFTER `created_on`,
  ADD COLUMN `updated_on` DATETIME NOT NULL
  AFTER `created_by`,
  ADD COLUMN `updated_by` VARCHAR(45) NOT NULL
  AFTER `updated_on`;

ALTER TABLE `emp`
  ADD COLUMN `created_on` DATETIME NOT NULL
  AFTER `salary`,
  ADD COLUMN `created_by` VARCHAR(45) NOT NULL
  AFTER `created_on`,
  ADD COLUMN `updated_on` DATETIME NOT NULL
  AFTER `created_by`,
  ADD COLUMN `updated_by` VARCHAR(45) NOT NULL
  AFTER `updated_on`;

