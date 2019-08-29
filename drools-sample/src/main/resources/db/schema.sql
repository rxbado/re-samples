DROP TABLE IF EXISTS `itg_event`;
CREATE TABLE `itg_event` (
  `eventid` varchar(64) NOT NULL,
  `event_code` varbinary(64) DEFAULT NULL,
  `user_code` varbinary(64) DEFAULT NULL,
  `attrs` varchar(1000) DEFAULT NULL,
  `fired` tinyint(1) DEFAULT NULL,
  `message` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`eventid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `itg_event_source`;
CREATE TABLE `itg_event_source` (
  `code` varchar(64) NOT NULL DEFAULT '',
  `descr` varchar(1000) DEFAULT NULL,
  `from_source` varchar(200) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `itg_rule_event_source`;
CREATE TABLE `itg_rule_event_source` (
  `id` bigint(20) NOT NULL,
  `event_code` varchar(64) DEFAULT NULL,
  `rulekey` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `itg_rule_set`;
CREATE TABLE `itg_rule_set` (
  `rulekey` varchar(64) NOT NULL,
  `ruledesc` varchar(128) NOT NULL,
  `content` text,
  `priority` int(11) NOT NULL DEFAULT '0',
  `effective` date DEFAULT NULL,
  `expires` date DEFAULT NULL,
  `calctype` tinyint(4) DEFAULT NULL,
  `score` int(11) DEFAULT '0',
  `version` int(11) DEFAULT '0',
  `create_time` date DEFAULT NULL,
  `modified_time` date DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `ruletype` varchar(20) DEFAULT 'DRL',
  PRIMARY KEY (`rulekey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



