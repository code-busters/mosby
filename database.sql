-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: db4free.net:3306
-- Generation Time: Apr 02, 2014 at 01:17 PM
-- Server version: 5.6.17
-- PHP Version: 5.3.10-1ubuntu3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `mosby`
--
CREATE DATABASE IF NOT EXISTS `mosby` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `mosby`;

-- --------------------------------------------------------

--
-- Table structure for table `apis`
--


--
-- Table structure for table `event_categories`
--

DROP TABLE IF EXISTS `event_categories`;
CREATE TABLE IF NOT EXISTS `event_categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=33 ;

--
-- Dumping data for table `event_categories`
--

INSERT INTO `event_categories` (`id`, `category`) VALUES
(12, 'Auto, Boat & Air'),
(13, 'Business & Professional'),
(14, 'Charity & Causes'),
(15, 'Community & Culture'),
(16, 'Family & Education'),
(17, 'Fashion & Beauty'),
(18, 'Film, Media & Entertainment'),
(19, 'Food & Drink'),
(20, 'Government & Politics'),
(21, 'Health & Wellness'),
(22, 'Hobbies & Special Interest'),
(23, 'Home & Lifestyle'),
(24, 'Music'),
(25, 'Other'),
(26, 'Performing & Visual Arts'),
(27, 'Religion & Spirituality'),
(28, 'Science & Technology'),
(29, 'Seasonal & Holiday'),
(30, 'Sports & Fitness'),
(31, 'Travel & Outdoor');

-- --------------------------------------------------------

--
-- Table structure for table `event_types`
--

DROP TABLE IF EXISTS `event_types`;
CREATE TABLE IF NOT EXISTS `event_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=23 ;

--
-- Dumping data for table `event_types`
--

INSERT INTO `event_types` (`id`, `type`) VALUES
(4, 'Appearance or Signing'),
(5, 'Attraction'),
(6, 'Camp, Trip or Retreat'),
(7, 'Class, Training or Workshop'),
(8, 'Concert or Perfomance'),
(9, 'Conference'),
(10, 'Dinner or Gala'),
(11, 'Festival or Fair'),
(12, 'Game or Competition'),
(13, 'Meeting or Networking Event'),
(14, 'Other'),
(15, 'Party or Social Gathering'),
(16, 'Race or Endurance Event'),
(17, 'Rally'),
(18, 'Screening'),
(19, 'Seminar Talk'),
(20, 'Tour'),
(21, 'Tournament'),
(22, 'Tradeshow, Consumer Show or Expo');

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
CREATE TABLE IF NOT EXISTS `events` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `organizer_ref` int(11) DEFAULT NULL,
  `name` varchar(40) NOT NULL,
  `description` longtext CHARACTER SET latin1,
  `category_ref` int(11) DEFAULT NULL,
  `type_ref` int(11) DEFAULT NULL,
  `start_date` date NOT NULL,
  `start_time` time DEFAULT NULL,
  `end_date` date NOT NULL,
  `end_time` time DEFAULT NULL,
  `location` varchar(150) DEFAULT NULL,
  `logo` varchar(260) NOT NULL DEFAULT 'default.png',
  `background` varchar(260) NOT NULL DEFAULT 'default.jpg',
  `privacy` tinyint(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `events_topic_ref` (`category_ref`),
  KEY `events_type_ref` (`type_ref`),
  KEY `events_organizers_ref` (`organizer_ref`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=83 ;

--
-- Dumping data for table `events`
--

-- --------------------------------------------------------

--
-- Table structure for table `organizers`
--

DROP TABLE IF EXISTS `organizers`;
CREATE TABLE IF NOT EXISTS `organizers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_ref` int(11) NOT NULL,
  `name` varchar(150) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(18) DEFAULT NULL,
  `about` varchar(500) DEFAULT NULL,
  `site` varchar(100) DEFAULT NULL,
  `google_plus` varchar(100) DEFAULT NULL,
  `facebook` varchar(100) DEFAULT NULL,
  `twitter` varchar(100) DEFAULT NULL,
  `logo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `organizers_users_ref` (`user_ref`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `organizers`
--


-- --------------------------------------------------------

--
-- Table structure for table `promo_codes`
--

DROP TABLE IF EXISTS `promo_codes`;
CREATE TABLE IF NOT EXISTS `promo_codes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_ref` int(11) NOT NULL,
  `code` varchar(50) NOT NULL,
  `discount` int(11) NOT NULL DEFAULT '0',
  `description` mediumtext,
  `quantity_available` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `promo_codes_events_ref` (`event_ref`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=23 ;

--
-- Dumping data for table `promo_codes`
--



-- --------------------------------------------------------

--
-- Table structure for table `tickets`
--

DROP TABLE IF EXISTS `tickets`;
CREATE TABLE IF NOT EXISTS `tickets` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ticket_info_ref` int(11) NOT NULL,
  `time_of_purchase` datetime NOT NULL,
  `promo_codes_ref` int(11) DEFAULT NULL,
  `checked` tinyint(4) DEFAULT NULL,
  `user_ref` int(11) DEFAULT NULL,
  `event_ref` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tickets_users_ref` (`user_ref`),
  KEY `tickets_events_ref` (`event_ref`),
  KEY `tickets_tickets_infos_ref` (`ticket_info_ref`),
  KEY `tickets_promo_codes_ref` (`promo_codes_ref`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=57 ;

--
-- Dumping data for table `tickets`
--



-- --------------------------------------------------------

--
-- Table structure for table `tickets_infos`
--

DROP TABLE IF EXISTS `tickets_infos`;
CREATE TABLE IF NOT EXISTS `tickets_infos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `event_ref` int(11) NOT NULL,
  `type` varchar(45) NOT NULL,
  `description` varchar(60) DEFAULT NULL,
  `quantity_available` int(11) NOT NULL,
  `price` int(11) DEFAULT NULL,
  `start_date` date NOT NULL,
  `start_time` time NOT NULL,
  `end_date` date NOT NULL,
  `end_time` time NOT NULL,
  PRIMARY KEY (`id`),
  KEY `tickets_infos_events_ref` (`event_ref`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=32 ;

--
-- Dumping data for table `tickets_infos`
--


-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(250) DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `credits` int(11) DEFAULT NULL,
  `admin` tinyint(11) DEFAULT '0',
  `image` varchar(100) DEFAULT 'default.png',
  `country` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `site` varchar(45) DEFAULT NULL,
  `about` longtext,
  `authentication_code` varchar(200) DEFAULT '0',
  `active` tinyint(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=58 ;


-- Constraints for dumped tables
--
DROP TABLE IF EXISTS `apis`;
CREATE TABLE IF NOT EXISTS `apis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `organizer_ref` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `code` varchar(80) NOT NULL,
  `time_of_creation` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `apis_organizers_ref` (`organizer_ref`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `apis`
--

-- --------------------------------------------------------

--
-- Constraints for table `events`
--
ALTER TABLE `events`
  ADD CONSTRAINT `events_topic_ref` FOREIGN KEY (`category_ref`) REFERENCES `event_categories` (`id`),
  ADD CONSTRAINT `events_type_ref` FOREIGN KEY (`type_ref`) REFERENCES `event_types` (`id`),
  ADD CONSTRAINT `events_organizers_ref` FOREIGN KEY (`organizer_ref`) REFERENCES `organizers` (`id`);

--
-- Constraints for table `apis`

-- Constraints for table `organizers`
--
ALTER TABLE `organizers`
  ADD CONSTRAINT `organizers_users_ref` FOREIGN KEY (`user_ref`) REFERENCES `users` (`id`);

ALTER TABLE `apis`
  ADD CONSTRAINT `apis_organizers_ref` FOREIGN KEY (`organizer_ref`) REFERENCES `organizers` (`id`);
  
--
-- Constraints for table `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `tickets_users_ref` FOREIGN KEY (`user_ref`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `tickets_events_ref` FOREIGN KEY (`event_ref`) REFERENCES `events` (`id`),
  ADD CONSTRAINT `tickets_tickets_infos_ref` FOREIGN KEY (`ticket_info_ref`) REFERENCES `tickets_infos` (`id`),
  ADD CONSTRAINT `tickets_promo_codes_ref` FOREIGN KEY (`promo_codes_ref`) REFERENCES `promo_codes` (`id`);
  
--
-- Constraints for table `tickets_infos`
--
ALTER TABLE `tickets_infos`
  ADD CONSTRAINT `tickets_infos_events_ref` FOREIGN KEY (`event_ref`) REFERENCES `events` (`id`);
  
--
-- Constraints for table `promo_codes`
--
ALTER TABLE `promo_codes`
  ADD CONSTRAINT `promo_codes_events_ref` FOREIGN KEY (`event_ref`) REFERENCES `events` (`id`);


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
