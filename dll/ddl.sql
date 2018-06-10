DROP TABLE
IF EXISTS ccshsip.schedule_job;

/*任务表*/
CREATE TABLE ccshsip.schedule_job (
  schedule_job_id BIGINT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  job_name        VARCHAR(255)                    DEFAULT NULL,
  alias_name      VARCHAR(255)                    DEFAULT NULL,
  job_group       VARCHAR(255)                    DEFAULT NULL,
  job_trigger     VARCHAR(255)                    DEFAULT NULL,
  STATUS          VARCHAR(255)                    DEFAULT NULL,
  cron_expression VARCHAR(255)                    DEFAULT NULL,
  is_sync         VARCHAR(1) NOT NULL,
  description     VARCHAR(255)                    DEFAULT NULL,
  gmt_create      TIMESTAMP  NULL                 DEFAULT now(),
  gmt_modify      TIMESTAMP  NULL                 DEFAULT NULL
#PRIMARY KEY (schedule_job_id)
)
  ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

SELECT *
FROM ccshsip.schedule_job