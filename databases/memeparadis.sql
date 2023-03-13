-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2023. Már 14. 00:04
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `addNewUser` (IN `nameIN` VARCHAR(200), IN `emailIN` VARCHAR(200), IN `passwordIN` TEXT, IN `birth_dateIN` DATE)   INSERT INTO `user` (`user`.`name`,`user`.`email`,`user`.`password`,`user`.`birth_date`)
VALUES(nameIN,emailIN,passwordIN,birth_dateIN)$$

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
CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `comment` varchar(100) NOT NULL,
  `userId` int(11) NOT NULL,
  `contentId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `content`
--

DROP TABLE IF EXISTS `content`;
CREATE TABLE `content` (
  `id` int(11) NOT NULL,
  `adult_content` tinyint(1) NOT NULL,
  `uploader_name` int(11) DEFAULT NULL,
  `language` varchar(100) NOT NULL,
  `likes` int(11) NOT NULL DEFAULT 0,
  `content_type` tinyint(1) NOT NULL,
  `content_src` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- A tábla adatainak kiíratása `content`
--

INSERT INTO `content` (`id`, `adult_content`, `uploader_name`, `language`, `likes`, `content_type`, `content_src`) VALUES
(1, 0, 2, 'hun', 0, 0, ''),
(2, 1, NULL, 'hun', 0, 0, ''),
(3, 1, NULL, 'awad', 0, 0, ''),
(4, 1, 1, 'FR', 0, 0, '');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `content_tag`
--

DROP TABLE IF EXISTS `content_tag`;
CREATE TABLE `content_tag` (
  `id` int(11) NOT NULL,
  `content_id` int(11) DEFAULT NULL,
  `tags_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `password_reset`
--

DROP TABLE IF EXISTS `password_reset`;
CREATE TABLE `password_reset` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `token` varchar(100) NOT NULL,
  `token_expire` date NOT NULL,
  `used` tinyint(1) NOT NULL,
  `created_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `tags`
--

DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags` (
  `id` int(11) NOT NULL,
  `tag` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `registration_date` datetime NOT NULL DEFAULT current_timestamp(),
  `birth_date` date DEFAULT NULL,
  `access_type` tinyint(1) NOT NULL DEFAULT 0,
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- A tábla adatainak kiíratása `user`
--

INSERT INTO `user` (`id`, `name`, `email`, `password`, `registration_date`, `birth_date`, `access_type`, `is_deleted`) VALUES
(1, 'lkj', 'krcs@gmail.com', '4551', '2023-02-20 15:50:37', '2023-02-16', 0, 0),
(2, 'mjhh', 'jjhh@gmail.com', '665485', '2023-02-20 15:57:59', '2023-02-15', 0, 0),
(3, 'oulé', 'klélk@gmail.com', '5766', '2023-02-20 15:59:02', '2023-02-09', 1, 0),
(4, 'teszt', 'tesz@gmail.com', '54687', '2023-02-21 09:48:59', '2023-12-11', 0, 0);

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `userId` (`userId`),
  ADD KEY `contentId` (`contentId`);

--
-- A tábla indexei `content`
--
ALTER TABLE `content`
  ADD PRIMARY KEY (`id`),
  ADD KEY `uploader_name` (`uploader_name`);

--
-- A tábla indexei `content_tag`
--
ALTER TABLE `content_tag`
  ADD PRIMARY KEY (`id`),
  ADD KEY `content_id` (`content_id`),
  ADD KEY `tags_id` (`tags_id`);

--
-- A tábla indexei `password_reset`
--
ALTER TABLE `password_reset`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- A tábla indexei `tags`
--
ALTER TABLE `tags`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT a táblához `content`
--
ALTER TABLE `content`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT a táblához `content_tag`
--
ALTER TABLE `content_tag`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `password_reset`
--
ALTER TABLE `password_reset`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `tags`
--
ALTER TABLE `tags`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `commentContentId_contentId` FOREIGN KEY (`contentId`) REFERENCES `content` (`id`),
  ADD CONSTRAINT `commentUserId_userId` FOREIGN KEY (`userId`) REFERENCES `user` (`id`);

--
-- Megkötések a táblához `content`
--
ALTER TABLE `content`
  ADD CONSTRAINT `userId_contentUploaderId` FOREIGN KEY (`uploader_name`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Megkötések a táblához `content_tag`
--
ALTER TABLE `content_tag`
  ADD CONSTRAINT `contentID_contentTagContentId` FOREIGN KEY (`content_id`) REFERENCES `content` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  ADD CONSTRAINT `tagsId_contentTagContentTagsId` FOREIGN KEY (`tags_id`) REFERENCES `tags` (`id`) ON DELETE SET NULL ON UPDATE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
