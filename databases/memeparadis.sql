-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2023. Már 29. 13:23
-- Kiszolgáló verziója: 10.4.24-MariaDB
-- PHP verzió: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `memeparadis`
--
CREATE DATABASE IF NOT EXISTS `memeparadis` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `memeparadis`;

DELIMITER $$
--
-- Eljárások
--
DROP PROCEDURE IF EXISTS `addNewUser`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addNewUser` (IN `nameIN` VARCHAR(200) CHARSET utf8mb4, IN `emailIN` VARCHAR(200) CHARSET utf8mb4, IN `passwordIN` TEXT CHARSET utf8mb4, IN `birth_dateIN` DATE)   INSERT INTO `user` (`user`.`name`,`user`.`email`,`user`.`password`,`user`.`birth_date`)
VALUES(nameIN,emailIN,passwordIN,birth_dateIN)$$

DROP PROCEDURE IF EXISTS `checkEmailUnique`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `checkEmailUnique` (OUT `result` INT(10), IN `emailIN` VARCHAR(100) CHARSET utf8)   SELECT COUNT(`user`.`id`) into result FROM `user` WHERE `user`.`email` = emailIN$$

DROP PROCEDURE IF EXISTS `createcomment`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createcomment` (IN `commentIN` VARCHAR(100))   INSERT INTO `comment` (`comment`.`comment`) VALUES (commentIN)$$

DROP PROCEDURE IF EXISTS `createContent`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createContent` (IN `uploader_id` INT, IN `adult_content` TINYINT, IN `language` VARCHAR(100), IN `content_type` VARCHAR(100))   INSERT INTO `content` (`content`.`uploader_name`,`content`.`adult_content`,`content`.`language`,`content`.`content_type`)
VALUES(uploader_id,adult_content,language,content_type)$$

DROP PROCEDURE IF EXISTS `createTag`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createTag` (IN `tagIN` VARCHAR(255))   INSERT INTO `tag` (`tags`.`tag`) VALUES (tagIN)$$

DROP PROCEDURE IF EXISTS `deleteComment`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteComment` (IN `idIN` INT)   DELETE FROM `comment` WHERE `comment`.`id` = idIN$$

DROP PROCEDURE IF EXISTS `deleteContent`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteContent` (IN `idIN` INT)   DELETE FROM `content` WHERE `content`.`id` = idIN$$

DROP PROCEDURE IF EXISTS `deleteTag`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteTag` (IN `idIN` INT)   DELETE FROM `tags` WHERE `tags`.`id` = idIN$$

DROP PROCEDURE IF EXISTS `deleteUser`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteUser` (IN `idIN` INT)   DELETE FROM `user` WHERE `user`.`id`=idIN$$

DROP PROCEDURE IF EXISTS `GetMostLikedPosts`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetMostLikedPosts` ()   BEGIN
SELECT `content`.`id`,`content`.`likes` FROM content
ORDER BY `likes` DESC;
END$$

DROP PROCEDURE IF EXISTS `getUserByID`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getUserByID` (IN `idIN` INT)   SELECT * FROM user WHERE user.id = idIN$$

DROP PROCEDURE IF EXISTS `login`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `login` (IN `emailIN` VARCHAR(200), IN `pwIN` VARCHAR(200))   SELECT * FROM `user` WHERE `user`.`email` = emailIN AND `user`.`password` = pwIN$$

DROP PROCEDURE IF EXISTS `updateComment`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateComment` (IN `commentIN` VARCHAR(100), IN `idIN` INT)   UPDATE `comment` SET `comment`.`comment` = commentIN WHERE `comment`.`id` = idIN$$

DROP PROCEDURE IF EXISTS `updateTag`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateTag` (IN `tagIN` VARCHAR(255), IN `idIN` INT)   UPDATE `tags` SET `tags`.`tag` = tagIN WHERE `tags`.`id` = idIN$$

DROP PROCEDURE IF EXISTS `updateUser`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateUser` (IN `usernameIN` VARCHAR(200) CHARSET utf8mb4, IN `idIN` INT, IN `emailIN` VARCHAR(200) CHARSET utf8mb4, IN `passwordIN` TEXT, IN `birth_dateIN` DATE)   UPDATE `user` SET `user`.`name` = usernameIN,`user`.`email` = emailIN,`user`.`password`=passwordIN,`user`.`birth_date`=birth_dateIN
WHERE `user`.`id` = idIN$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `comment`
--

DROP TABLE IF EXISTS `comment`;
CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `content` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `content`
--

DROP TABLE IF EXISTS `content`;
CREATE TABLE IF NOT EXISTS `content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adult_content` tinyint(1) NOT NULL,
  `uploader_name` int(11) DEFAULT NULL,
  `language` varchar(100) NOT NULL,
  `likes` int(11) NOT NULL DEFAULT 0,
  `content_type` tinyint(1) NOT NULL,
  `content_src` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `uploader_name` (`uploader_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- A tábla adatainak kiíratása `content`
--

INSERT INTO `content` (`id`, `adult_content`, `uploader_name`, `language`, `likes`, `content_type`, `content_src`) VALUES
(1, 0, 2, 'hun', 0, 0, ''),
(2, 1, NULL, 'hun', 0, 0, ''),
(3, 1, NULL, 'awad', 0, 0, ''),
(4, 1, NULL, 'FR', 0, 0, '');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `content_tag`
--

DROP TABLE IF EXISTS `content_tag`;
CREATE TABLE IF NOT EXISTS `content_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content_id` int(11) DEFAULT NULL,
  `tags_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `content_id` (`content_id`),
  KEY `tags_id` (`tags_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `password_reset`
--

DROP TABLE IF EXISTS `password_reset`;
CREATE TABLE IF NOT EXISTS `password_reset` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `token` varchar(100) NOT NULL,
  `token_expire` date NOT NULL,
  `used` tinyint(1) NOT NULL,
  `created_at` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `tags`
--

DROP TABLE IF EXISTS `tags`;
CREATE TABLE IF NOT EXISTS `tags` (
  `id` int(11) NOT NULL,
  `tag` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `registration_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `birth_date` date DEFAULT NULL,
  `access_type` tinyint(1) NOT NULL DEFAULT 0,
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

--
-- A tábla adatainak kiíratása `user`
--

INSERT INTO `user` (`id`, `name`, `email`, `password`, `registration_date`, `birth_date`, `access_type`, `is_deleted`) VALUES
(2, 'mjhh', 'jjhh@gmail.com', '665485', '2023-02-20 14:57:59', '2023-02-15', 0, 0),
(3, 'oulé', 'klélk@gmail.com', '5766', '2023-02-20 14:59:02', '2023-02-09', 1, 0),
(4, 'teszt', 'tesz@gmail.com', '54687', '2023-02-21 08:48:59', '2023-12-11', 0, 0),
(5, 'asdasasd', 'ghfgh@fgzuhfgu.com', 'asd123$R', '2023-03-20 15:48:15', '2001-02-03', 0, 0),
(6, 'asasad', 'valami@valami.hu', 'asd123$R', '2023-03-20 15:48:42', '2001-02-03', 0, 0),
(7, 'ASdasddddd', 'YYYafasf@fgzuhfgu.com', 'asd123$R', '2023-03-20 15:57:15', '2001-02-03', 0, 0),
(8, 'asdasdasd', 'aSasdas@gmail.hu', 'AScasas!221', '2023-03-20 16:02:43', '2023-03-15', 0, 0),
(10, 'kléklklélkékl', 'AAAAa@fgzuhfgu.com', 'asd123$R', '2023-03-23 18:35:55', '2001-02-03', 0, 0),
(11, 'Teszt0011', 'Teszttel1@gmtesz.com', 'AAwwee455_4', '2023-03-24 12:35:58', '2023-02-10', 0, 0),
(12, 'Proabaaaa', 'asddsA@gahjj.com', 'AAA222fff__', '2023-03-26 12:18:28', '2023-03-16', 0, 0),
(13, 'kléklklélkékl', 'teszta@fgzuhfgu.com', 'asd123$R', '2023-03-27 13:21:53', '2001-02-03', 0, 0);

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `content`
--
ALTER TABLE `content`
  ADD CONSTRAINT `userId_contentUploaderId` FOREIGN KEY (`uploader_name`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
