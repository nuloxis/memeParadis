-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2023. Máj 16. 20:43
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

DELIMITER $$
--
-- Eljárások
--
DROP PROCEDURE IF EXISTS `addNewUser`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addNewUser` (IN `nameIN` VARCHAR(200) CHARSET utf8mb4, IN `emailIN` VARCHAR(200) CHARSET utf8mb4, IN `passwordIN` TEXT CHARSET utf8mb4, IN `birth_dateIN` DATE)   INSERT INTO `user` (`user`.`name`,`user`.`email`,`user`.`password`,`user`.`birth_date`)
VALUES(nameIN,emailIN,passwordIN,birth_dateIN)$$

DROP PROCEDURE IF EXISTS `checkEmailUnique`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `checkEmailUnique` (OUT `result` INT(10), IN `emailIN` VARCHAR(100) CHARSET utf8)   SELECT COUNT(`user`.`id`) into result FROM `user` WHERE `user`.`email` = emailIN$$

DROP PROCEDURE IF EXISTS `check_user_age`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `check_user_age` (IN `user_id` INT, OUT `is_adult` BOOLEAN)   BEGIN
DECLARE birth_date DATE;
DECLARE today DATE;
DECLARE age INT;
SELECT `birth_date` INTO `birth_date` FROM `users` WHERE `id` = `user_id`;

SELECT CURDATE() INTO `today`;

SET `age` = TIMESTAMPDIFF(YEAR, `birth_date`, `today`);

IF `age` >= 18 THEN
    SET `is_adult` = TRUE;
ELSE
    SET `is_adult` = FALSE;
END IF;

END$$

DROP PROCEDURE IF EXISTS `createcomment`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createcomment` (IN `commentIN` VARCHAR(100))   INSERT INTO `comment` (`comment`.`comment`) VALUES (commentIN)$$

DROP PROCEDURE IF EXISTS `createContent`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createContent` (IN `adult_content` TINYINT, IN `uploader_id` INT, IN `language_IN` VARCHAR(100), IN `content_type` TINYINT, IN `content_uplade_name` VARCHAR(100), OUT `new_id` INT)   BEGIN
	INSERT INTO `content` 				(`content`.`adult_content`,`content`.`uploader_name`,`content`.`language`,`content`.`content_type`,`content`.`content_uplade_name`)
	VALUES(adult_content,uploader_id,language_IN,content_type,content_uplade_name);
    SET new_id=LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS `createContent_tag`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createContent_tag` (IN `content_idIN` INT, IN `tags_idIN` INT)   INSERT INTO `content_tag` (`content_tag`.`content_id`,`content_tag`.`tags_id`)
VALUES(content_idIN,tags_idIN)$$

DROP PROCEDURE IF EXISTS `createTag`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createTag` (IN `tagIN` VARCHAR(100))   BEGIN
INSERT INTO `tags` (`tags`.`tag`) VALUES (tagIN);
select last_insert_id();
END$$

DROP PROCEDURE IF EXISTS `deleteComment`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteComment` (IN `idIN` INT)   DELETE FROM `comment` WHERE `comment`.`id` = idIN$$

DROP PROCEDURE IF EXISTS `deleteContent`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteContent` (IN `idIN` INT)   DELETE FROM `content` WHERE `content`.`id` = idIN$$

DROP PROCEDURE IF EXISTS `deleteTag`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteTag` (IN `idIN` INT)   DELETE FROM `tags` WHERE `tags`.`id` = idIN$$

DROP PROCEDURE IF EXISTS `deleteUser`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteUser` (IN `idIN` INT)   DELETE FROM `user` WHERE `user`.`id`=idIN$$

DROP PROCEDURE IF EXISTS `getAllContentRand`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllContentRand` ()   SELECT * FROM content ORDER BY RAND()$$

DROP PROCEDURE IF EXISTS `getContentByID`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getContentByID` (IN `idIN` INT)   SELECT * from `content` WHERE `content`.`id` = idIN$$

DROP PROCEDURE IF EXISTS `getContentBytag`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getContentBytag` (IN `tag_id` VARCHAR(200))   BEGIN
	SELECT content.*
    FROM `content`
    INNER JOIN `content_tag` ON `content`.`id`=`content_tag`.`content_id`
    INNER JOIN `tags` ON `content_tag`.`tags_id`=`tags`.`id`
    WHERE `tags`.`tag` LIKE tag_id;
END$$

DROP PROCEDURE IF EXISTS `getContentByUserId`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getContentByUserId` (IN `user_id` INT)   BEGIN
	SELECT * FROM content WHERE `content`.`uploader_name`=user_id ORDER BY id DESC;
END$$

DROP PROCEDURE IF EXISTS `getEnglishContents`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getEnglishContents` ()   SELECT * FROM `content` WHERE
`content`.`language`="ENG" ORDER BY RAND()$$

DROP PROCEDURE IF EXISTS `getHowmanyContent`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getHowmanyContent` (OUT `szamolas` INT)   SELECT COUNT(*)+1 INTO szamolas FROM content$$

DROP PROCEDURE IF EXISTS `getHungarianContents`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getHungarianContents` ()   SELECT * FROM `content` WHERE
`content`.`language`="HUN" ORDER BY RAND()$$

DROP PROCEDURE IF EXISTS `GetMostLikedPosts`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetMostLikedPosts` ()   BEGIN
SELECT * FROM content WHERE `content`.`content_type`=0
ORDER BY RAND() DESC LIMIT 1;
END$$

DROP PROCEDURE IF EXISTS `getPictures`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getPictures` ()   SELECT * FROM `content` WHERE
`content`.`content_type`=0 ORDER BY RAND()$$

DROP PROCEDURE IF EXISTS `getUserByID`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getUserByID` (IN `idIN` INT)   SELECT * FROM user WHERE user.id = idIN$$

DROP PROCEDURE IF EXISTS `getVideos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getVideos` ()   SELECT * FROM `content` WHERE
`content`.`content_type`=1 ORDER BY RAND()$$

DROP PROCEDURE IF EXISTS `login`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `login` (IN `emailIN` VARCHAR(200), IN `pwIN` VARCHAR(200))   SELECT * FROM `user` WHERE `user`.`email` = emailIN AND `user`.`password` = pwIN$$

DROP PROCEDURE IF EXISTS `updateBirthDate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateBirthDate` (IN `birthDateIN` DATE, IN `idIN` INT)   UPDATE `user` SET `user`.`birth_date` = birthDateIN WHERE `user`.`id` = idIN$$

DROP PROCEDURE IF EXISTS `updateComment`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateComment` (IN `commentIN` VARCHAR(100), IN `idIN` INT)   UPDATE `comment` SET `comment`.`comment` = commentIN WHERE `comment`.`id` = idIN$$

DROP PROCEDURE IF EXISTS `updateEmail`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateEmail` (IN `emailIN` VARCHAR(200) CHARSET utf8mb4, IN `idIN` INT)   UPDATE `user` SET `user`.`email` = emailIN WHERE `user`.`id` = idIN$$

DROP PROCEDURE IF EXISTS `updatePassword`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updatePassword` (IN `currentPwIN` VARCHAR(200) CHARSET utf8mb4, IN `newPwIN` VARCHAR(200) CHARSET utf8mb4, IN `idIN` INT, OUT `result` VARCHAR(200) CHARSET utf8mb4)   BEGIN
	DECLARE currentPwDB varchar(200);
	SELECT `password` INTO currentPwDB FROM `user` WHERE `id`=idIN;
    if currentPwIN=currentPwDB THEN
    	UPDATE `user` SET `password` =newPwIN WHERE `id`=idIN;
        SET result ='Sikeres jelszó módosítás.';
    ELSE
    	SET result='A jelenlegi jelszó vagy az új jelszó nem megfelelő.';
    END IF;
    END$$

DROP PROCEDURE IF EXISTS `updateTag`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateTag` (IN `tagIN` VARCHAR(255), IN `idIN` INT)   UPDATE `tags` SET `tags`.`tag` = tagIN WHERE `tags`.`id` = idIN$$

DROP PROCEDURE IF EXISTS `updateUser`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateUser` (IN `usernameIN` VARCHAR(200) CHARSET utf8mb4, IN `idIN` INT, IN `emailIN` VARCHAR(200) CHARSET utf8mb4, IN `passwordIN` TEXT, IN `birth_dateIN` DATE)   UPDATE `user` SET `user`.`name` = usernameIN,`user`.`email` = emailIN,`user`.`password`=passwordIN,`user`.`birth_date`=birth_dateIN
WHERE `user`.`id` = idIN$$

DROP PROCEDURE IF EXISTS `updateUserName`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateUserName` (IN `usernameIN` VARCHAR(200) CHARSET utf8mb4, IN `idIN` INT)   UPDATE `user` SET `user`.`name` = usernameIN WHERE `user`.`id` = idIN$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `comment`
--

DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `content` varchar(100) NOT NULL
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
  `content_uplade_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- A tábla adatainak kiíratása `content`
--

INSERT INTO `content` (`id`, `adult_content`, `uploader_name`, `language`, `likes`, `content_type`, `content_uplade_name`) VALUES
(5, 1, 12, 'Magyar', 300, 0, 'testpicturefavorite.jpg'),
(138, 0, 17, 'HUN', 0, 0, '328150237_3427459547511701_6960065148979400892_n.jpg'),
(139, 0, 17, 'ENG', 0, 1, 'FV_gQQd0q2ic58wT.mp4'),
(140, 0, 17, 'ENG', 0, 1, '338777909_6740431752651490_6952288897049060844_n.mp4'),
(141, 0, 17, 'ENG', 0, 0, '330127686_1320951928472275_1174998534837050643_n.jpg'),
(142, 0, 17, 'HUN', 0, 0, '331404268_586996743015712_6165990220723175662_n.jpg'),
(143, 0, 17, 'ENG', 0, 0, '334528941_600162074962020_5670980728910800348_n.jpg'),
(144, 0, 17, 'ENG', 0, 0, '336858355_781675473381309_3665304734640803129_n.jpg'),
(145, 0, 17, 'ENG', 0, 0, '338680141_9853201068038622_318187649322917900_n.jpg'),
(146, 0, 17, 'ENG', 0, 0, '339979760_231264726147688_7081157899227147566_n.jpg'),
(147, 0, 17, 'HUN', 0, 0, '346318519_6166904816736374_5369752781034069868_n.jpg'),
(155, 0, 17, 'ENG', 0, 0, '341014071_1234863227152645_4800330610345280951_n.jpg'),
(156, 0, 17, 'ENG', 0, 0, 'FB_IMG_1675955632958.png'),
(157, 0, 17, 'ENG', 0, 0, '340455485_578637467665029_8698473392396051838_n.jpg'),
(158, 0, 17, 'ENG', 0, 0, '341012320_1607105839770191_8801039898197503806_n.jpg'),
(159, 0, 17, 'ENG', 0, 0, '342040271_1337820500410293_1990752037516640952_n.jpg'),
(160, 0, 17, 'HUN', 0, 0, '342518087_109754222097032_5800093328045229866_n.jpg'),
(161, 0, 17, 'OTHER', 0, 0, '344226853_612744810788345_2354615466899960627_n.png'),
(162, 0, 17, 'ENG', 0, 0, 'FB_IMG_1683367807677.png'),
(163, 0, 17, 'HUN', 0, 1, 'a3f30e11b0eb428eb65e987ac01aca02.mp4'),
(164, 0, 17, 'OTHER', 0, 1, 'trim.EB74CBE3-8048-42F3-B2B1-EAE2009EDD90.mov'),
(165, 0, 17, 'ENG', 0, 1, 'v12044gd0000c8jvebbc77ufcsr27uug.mp4'),
(166, 0, 17, 'OTHER', 0, 1, 'this_video_is_so_cute_--720P_HD.mp4');

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

--
-- A tábla adatainak kiíratása `content_tag`
--

INSERT INTO `content_tag` (`id`, `content_id`, `tags_id`) VALUES
(121, 138, 202),
(122, 138, 203),
(123, 138, 204),
(124, 138, 205),
(125, 138, 206),
(126, 138, 207),
(127, 139, 208),
(128, 139, 209),
(129, 140, 210),
(130, 140, 211),
(131, 140, 212),
(132, 141, 213),
(133, 141, 214),
(134, 141, 215),
(135, 141, 216),
(136, 141, 217),
(137, 142, 218),
(138, 142, 219),
(139, 142, 220),
(140, 143, 221),
(141, 143, 222),
(142, 144, 223),
(143, 144, 224),
(144, 145, 225),
(145, 145, 226),
(146, 145, 227),
(147, 145, 228),
(148, 146, 229),
(149, 147, 230),
(150, 147, 231),
(151, 147, 232),
(152, 147, 233),
(153, 147, 234),
(156, 155, 237),
(157, 155, 238),
(158, 155, 239),
(159, 155, 240),
(160, 156, 241),
(161, 157, 242),
(162, 157, 243),
(163, 157, 244),
(164, 158, 245),
(165, 158, 246),
(166, 159, 247),
(167, 159, 248),
(168, 160, 249),
(169, 160, 250),
(170, 161, 251),
(171, 162, 252),
(172, 162, 253),
(173, 162, 254),
(174, 162, 255),
(175, 162, 256),
(176, 163, 257),
(177, 163, 258),
(178, 164, 259),
(179, 165, 260),
(180, 165, 261),
(181, 166, 262);

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

--
-- A tábla adatainak kiíratása `tags`
--

INSERT INTO `tags` (`id`, `tag`) VALUES
(203, 'star wars'),
(204, 'baby yoda'),
(205, 'yoda'),
(206, 'Grogu'),
(207, 'grogu'),
(208, 'minecraft'),
(209, 'Minecraft'),
(210, 'Star Wars'),
(211, 'Mandalorian'),
(212, 'Fat'),
(213, 'Elon Musk'),
(214, 'chip'),
(215, 'rick roll'),
(216, 'musk'),
(217, 'elon'),
(218, 'mobil gamer'),
(219, 'toilet'),
(220, 'game'),
(221, 'Elden ring'),
(222, 'elden ring'),
(223, 'programing'),
(224, 'regex'),
(225, 'star wars'),
(227, 'obiwan'),
(228, 'anakin'),
(229, 'programing'),
(230, 'ship'),
(231, 'baszokra'),
(232, 'gyalog'),
(233, 'chras'),
(234, 'ütközés'),
(237, 'toy story'),
(238, 'rick and morty'),
(239, 'jerry'),
(240, 'andy'),
(241, 'gaming'),
(242, 'programing'),
(243, 'if'),
(244, 'AI'),
(245, 'gaming'),
(246, 'sniper'),
(247, 'spongebob'),
(248, 'my pc'),
(249, 'auto'),
(250, 'benzines'),
(251, 'star wars'),
(252, 'homelander'),
(253, 'the boys'),
(254, 'boys'),
(255, 'teacher'),
(256, 'sun'),
(257, 'rezsi'),
(258, 'számla'),
(259, 'chiken'),
(260, 'hot'),
(261, 'chips'),
(262, 'cat');

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
  `registration_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `birth_date` date DEFAULT NULL,
  `access_type` tinyint(1) NOT NULL DEFAULT 0,
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- A tábla adatainak kiíratása `user`
--

INSERT INTO `user` (`id`, `name`, `email`, `password`, `registration_date`, `birth_date`, `access_type`, `is_deleted`) VALUES
(2, 'mjhh', 'jjhh@gmail.com', 'Kecsketeszt', '2023-02-20 14:57:59', '2023-02-15', 0, 0),
(3, 'oulé', 'klélk@gmail.com', '5766', '2023-02-20 14:59:02', '2023-02-09', 1, 0),
(4, 'teszt', 'tesz222ww@gmail.com', '54687', '2023-02-21 08:48:59', '2023-12-11', 0, 0),
(5, 'asdasasd', 'ghfgh@fgzuhfgu.com', 'asd123$R', '2023-03-20 15:48:15', '2001-02-03', 0, 0),
(6, 'asasad', 'valami@valami.hu', 'asd123$R', '2023-03-20 15:48:42', '2001-02-03', 0, 0),
(7, 'ASdasddddd', 'YYYafasf@fgzuhfgu.com', 'asd123$R', '2023-03-20 15:57:15', '2001-02-03', 0, 0),
(8, 'asdasdasd', 'aSasdas@gmail.hu', 'AScasas!221', '2023-03-20 16:02:43', '2023-03-15', 0, 0),
(10, 'kléklklélkékl', 'AAAAa@fgzuhfgu.com', 'asd123$R', '2023-03-23 18:35:55', '2001-02-03', 0, 0),
(11, 'Teszt0011', 'Teszttel1@gmtesz.com', 'AAwwee455_4', '2023-03-24 12:35:58', '2023-02-10', 0, 0),
(12, 'Proabaaaa', 'asddsA@gahjj.com', 'AAA222fff__', '2023-03-26 12:18:28', '2023-03-16', 0, 0),
(13, 'kléklklélkékl', 'teszta@fgzuhfgu.com', 'asd123$R', '2023-03-27 13:21:53', '2001-02-03', 0, 0),
(14, 'Passwordhashteszt', 'tesztaas@fgzuhfgu.com', '95cc3cda7f9345c194983b169c167d5b', '2023-04-27 20:36:31', '2001-02-03', 0, 0),
(15, 'Passwordhashteszt002', 'teszt2aas@fgzuhfgu.com', 'c33d0f32c2ec3c5fa98005cd4aa9bff6', '2023-04-27 21:38:43', '2001-02-03', 0, 0),
(17, 'nulox', 'benkozsolt200223@gmail.com', '453fd7addd34012c01d80594d6a70930', '2023-05-03 16:38:11', '2002-02-05', 0, 0),
(18, 'asdfasfda', 'afaffaa@asd.asd', 'asdsadasdas', '2023-05-03 19:33:38', '0000-00-00', 0, 0),
(19, 'Passwordhashteszt002', 'teszt2argas@fgzuhfgu.com', 'c33d0f32c2ec3c5fa98005cd4aa9bff6', '2023-05-08 22:28:49', '2001-02-03', 0, 0),
(20, 'tesztbirth', 'tesztaabirths@fgzuhfgu.com', 'c6594b7ceb76742bdac791f12782c05', '2023-05-10 13:47:28', '2023-05-18', 0, 0),
(21, 'aWasdsdsdsad', 'asdasdasdasd@ada.com', '129e7f0d8073e27dc5a49ac7163a76f0', '2023-05-14 13:53:42', '2001-02-07', 0, 0),
(22, 'awdawdawdawd', 'awdblaésd@asdadf.com', 'e2efd5fd04f126de4096d7876f9bbcdf', '2023-05-14 13:54:58', '1998-01-29', 0, 0),
(23, 'Passwordhawdashteszt002', 'teszwdat2argas@fgzuhfgu.com', '56e5c0f8abce9b85f1fa9287c795198f', '2023-05-14 13:55:51', '2001-02-03', 0, 0),
(24, 'awdawdwdawadawdawd', 'awkdglafaf@asfk.com', '127fbef59d98563f585233ee4f36975b', '2023-05-14 13:57:12', '2000-02-09', 0, 0),
(25, 'asdasdaasdasd', 'awdwawdwadawdadasd@asda.com', 'cefabb6f3130d3bcfbe83d4ac6bb422b', '2023-05-14 14:00:08', '1995-02-01', 0, 0),
(26, 'awdawggfhgh', 'fgjhgjkghkgh@kjhbsg.com', '24359a9c0cadfc345276add97de9df71', '2023-05-14 14:01:10', '2004-06-09', 0, 0),
(27, 'Passwordhawdashteszt002', 'teszwdat2aawdrgas@fgzuhfgu.com', '56e5c0f8abce9b85f1fa9287c795198f', '2023-05-14 14:50:48', '2001-02-03', 0, 0),
(28, 'awdawggfhgh', 'fgjhgjawdwakghkgh@kjhbsg.com', '24359a9c0cadfc345276add97de9df71', '2023-05-14 14:51:43', '2004-06-09', 0, 0),
(29, 'Passwordhawdashteszt002', 'teszwdaat2aawdrgas@fgzuhfgu.com', '56e5c0f8abce9b85f1fa9287c795198f', '2023-05-14 15:18:08', '2001-02-03', 0, 0),
(30, 'awdkkgdsd', 'kojahfkjlléél@gmaa.com', 'b7864f9eef3e8c9a5d88f02e9b79bf7b', '2023-05-14 15:18:51', '1997-02-05', 0, 0),
(31, 'afddf', '', 'fa8e70f65aafd4a2117bee26213ae53c', '2023-05-14 15:22:58', '1999-02-14', 0, 0),
(32, 'afddf', 'hdkhjkjsdkk@gmail.com', 'fa8e70f65aafd4a2117bee26213ae53c', '2023-05-14 15:23:08', '1999-02-14', 0, 0),
(33, 'adfaasdaasdasd', 'asfsa@hasdf.com', 'e36c6845d0637f9fe2616f6723b097d0', '2023-05-14 16:22:02', '2001-07-05', 0, 0),
(34, 'awdawdaw', 'jghjdasfasdf@ijadsf.com', 'd68de09be2dd60abfe5277c2f92c812d', '2023-05-14 16:30:17', '1999-02-03', 0, 0),
(35, 'awdasda', 'awdaw@gsd.com', '1c0791ea56724e7c478f70de0ae1ab62', '2023-05-14 16:38:21', '2000-03-02', 0, 0),
(36, 'Passwordhawdashteszt002', 'tawdrgas@fgzuhfgu.com', '56e5c0f8abce9b85f1fa9287c795198f', '2023-05-14 16:50:48', '2001-02-03', 0, 0),
(37, 'Passwordhawdashteszt002', 'tawwasdasddrgas@fgzuhfgu.com', '56e5c0f8abce9b85f1fa9287c795198f', '2023-05-14 16:52:39', '2001-02-03', 0, 0),
(38, 'sdfkhgsdf', 'jhdgfvkghsdf@kjhgsdf.com', '561b6c65a3beb21f94aa9371f51afda4', '2023-05-14 17:29:21', '2000-02-02', 0, 0),
(39, 'kjsdbfkjds', 'ihasgdfih@ashjkdg.com', 'd979ae31d03d990fb6da012c202ba763', '2023-05-14 17:30:50', '2002-01-30', 0, 0),
(40, 'asdasdasdasdasd', 'qwdqwqwqdww@awsd.com', '1bad7932cf5c1f65fc8738f2b1e5491', '2023-05-14 18:39:03', '1995-02-01', 0, 0);

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `content`
--
ALTER TABLE `content`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=167;

--
-- AUTO_INCREMENT a táblához `content_tag`
--
ALTER TABLE `content_tag`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=182;

--
-- AUTO_INCREMENT a táblához `tags`
--
ALTER TABLE `tags`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=263;

--
-- AUTO_INCREMENT a táblához `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `userId_commentUserId` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Megkötések a táblához `content`
--
ALTER TABLE `content`
  ADD CONSTRAINT `userId_contentUploaderId` FOREIGN KEY (`uploader_name`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
