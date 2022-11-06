-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.9.3-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- 테이블 demo.post 구조 내보내기
CREATE TABLE IF NOT EXISTS `post` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USER_ID` int(10) unsigned DEFAULT NULL,
  `TITLE` varchar(255) NOT NULL,
  `WEBSITE` varchar(255) NOT NULL,
  `STARTDATE` date NOT NULL,
  `ENDDATE` date NOT NULL,
  `COMPANY` varchar(255) NOT NULL,
  `COMPANYTYPE` int(10) unsigned NOT NULL,
  `COMPANY2` varchar(255) NOT NULL,
  `COMPANY3` varchar(255) NOT NULL,
  `PRIZETOP` int(10) unsigned NOT NULL,
  `PRIZETOTAL` int(10) unsigned NOT NULL,
  `DESCRIPTION` text NOT NULL,
  `IMAGE` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_USER` (`USER_ID`),
  FULLTEXT KEY `DESCRIPTION` (`DESCRIPTION`),
  CONSTRAINT `FK_USER` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 demo.post_category 구조 내보내기
CREATE TABLE IF NOT EXISTS `post_category` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `POST_ID` int(10) unsigned NOT NULL,
  `CATEGORY_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_POST` (`POST_ID`),
  CONSTRAINT `FK_POST` FOREIGN KEY (`POST_ID`) REFERENCES `post` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 demo.post_prizebenefit 구조 내보내기
CREATE TABLE IF NOT EXISTS `post_prizebenefit` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `POST_ID` int(10) unsigned NOT NULL,
  `PRIZEBENEFIT_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_POST2` (`POST_ID`),
  CONSTRAINT `FK_POST2` FOREIGN KEY (`POST_ID`) REFERENCES `post` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 demo.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `PHONE` varchar(255) NOT NULL,
  `COMPANY` varchar(255) NOT NULL,
  `ACCOUNTNONEXPIRED` int(10) NOT NULL,
  `ACCOUNTNONLOCKED` int(10) NOT NULL,
  `CREDENTIALSNONEXPIRED` int(10) NOT NULL,
  `ENABLED` int(10) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USERNAME` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 demo.user_authority 구조 내보내기
CREATE TABLE IF NOT EXISTS `user_authority` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USER_ID` int(10) unsigned NOT NULL,
  `AUTHORITY` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_USER2` (`USER_ID`),
  CONSTRAINT `FK_USER2` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- 내보낼 데이터가 선택되어 있지 않습니다.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
