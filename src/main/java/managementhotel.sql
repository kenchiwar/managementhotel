-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Feb 25, 2024 at 10:47 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `managementhotel`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `first_name` varchar(250) DEFAULT NULL,
  `last_name` varchar(250) DEFAULT NULL,
  `email` varchar(250) DEFAULT NULL,
  `password` varchar(250) DEFAULT NULL,
  `phone` varchar(250) DEFAULT NULL,
  `image` varchar(250) DEFAULT NULL,
  `active` varchar(250) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `id_role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `first_name`, `last_name`, `email`, `password`, `phone`, `image`, `active`, `status`, `id_role`) VALUES
(2, 'SUPER', 'ADMIN', 'trungnghi202@gmail.com', '$2y$10$tb7mhFN5/f.7MbWRfhpv0OGfcXZ5irPK3EVa9wPM5j5.gSp7eHZSG', '0893939333', 'user.png', '123', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `id` int(11) NOT NULL,
  `id_sender` int(11) DEFAULT NULL,
  `service` text DEFAULT NULL,
  `check_in_from` date DEFAULT current_timestamp(),
  `check_in_until` date DEFAULT current_timestamp(),
  `check_out_from` date DEFAULT current_timestamp(),
  `check_out_until` date DEFAULT current_timestamp(),
  `main_guest` varchar(250) DEFAULT NULL,
  `status` varchar(250) DEFAULT NULL,
  `name` varchar(250) DEFAULT NULL,
  `email` varchar(250) DEFAULT NULL,
  `phone` varchar(250) DEFAULT NULL,
  `id_payment` int(11) NOT NULL,
  `note` text DEFAULT NULL,
  `security_code` varchar(250) DEFAULT NULL,
  `total` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `bill_detail`
--

CREATE TABLE `bill_detail` (
  `id` int(11) NOT NULL,
  `price` double DEFAULT NULL,
  `price_discount` double DEFAULT NULL,
  `number_day` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `reason_discount` varchar(250) DEFAULT NULL,
  `id_room` int(11) DEFAULT NULL,
  `id_bill` int(11) DEFAULT NULL,
  `numberHour` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `categoryimage`
--

CREATE TABLE `categoryimage` (
  `id` int(11) NOT NULL,
  `id_hotel` int(11) NOT NULL,
  `name` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `complain`
--

CREATE TABLE `complain` (
  `id` int(11) NOT NULL,
  `sender` int(11) NOT NULL,
  `handler` int(11) DEFAULT NULL,
  `date_sent` date DEFAULT NULL,
  `settlement_date` date DEFAULT NULL,
  `title` varchar(250) DEFAULT NULL,
  `content` text DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `evaluate`
--

CREATE TABLE `evaluate` (
  `id` int(11) NOT NULL,
  `number` int(11) DEFAULT NULL,
  `comment` varchar(250) DEFAULT NULL,
  `id_account` int(11) DEFAULT NULL,
  `id_bill` int(11) DEFAULT NULL,
  `name_account` varchar(250) DEFAULT NULL,
  `id_hotel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `hotel`
--

CREATE TABLE `hotel` (
  `id_account` int(11) NOT NULL,
  `name` varchar(250) DEFAULT NULL,
  `cancellation_policy` text DEFAULT NULL,
  `description` text DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `manager` varchar(250) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `main_photo` varchar(250) DEFAULT NULL,
  `secondary_photo` varchar(250) DEFAULT NULL,
  `papers` text DEFAULT NULL,
  `regulation` varchar(250) DEFAULT NULL,
  `id_handler` int(11) DEFAULT NULL,
  `address` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `image`
--

CREATE TABLE `image` (
  `id` int(11) NOT NULL,
  `name` varchar(250) DEFAULT NULL,
  `id_categoryImage` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `image_papers`
--

CREATE TABLE `image_papers` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `id_hotel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `id` int(11) NOT NULL,
  `method` varchar(250) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_SUPER_ADMIN'),
(2, 'ROLE_ADMIN'),
(3, 'ROLE_BUSINESS'),
(4, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `id` int(11) NOT NULL,
  `name` varchar(250) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `price_discount` double DEFAULT NULL,
  `reason_discount` varchar(250) DEFAULT NULL,
  `room_max` int(11) DEFAULT NULL,
  `room_now` int(11) DEFAULT NULL,
  `people_min` int(11) DEFAULT NULL,
  `people_max` int(11) DEFAULT NULL,
  `describes` text DEFAULT NULL,
  `id_hotel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

CREATE TABLE `service` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `service_detail`
--

CREATE TABLE `service_detail` (
  `id_room` int(11) NOT NULL,
  `id_service` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `fk_account_role` (`id_role`);

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_bill_payment` (`id_payment`),
  ADD KEY `fk_bill_account` (`id_sender`);

--
-- Indexes for table `bill_detail`
--
ALTER TABLE `bill_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_billDetail_bill` (`id_bill`),
  ADD KEY `fk_billDetail_room` (`id_room`);

--
-- Indexes for table `categoryimage`
--
ALTER TABLE `categoryimage`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_categoryImage_hotel` (`id_hotel`);

--
-- Indexes for table `complain`
--
ALTER TABLE `complain`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_sender_account` (`sender`),
  ADD KEY `fk_handler_account` (`handler`);

--
-- Indexes for table `evaluate`
--
ALTER TABLE `evaluate`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_evulate_hotel` (`id_hotel`);

--
-- Indexes for table `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id_account`);

--
-- Indexes for table `image`
--
ALTER TABLE `image`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_image_CategoryImage` (`id_categoryImage`);

--
-- Indexes for table `image_papers`
--
ALTER TABLE `image_papers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_paper_hotel` (`id_hotel`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_room_hotel` (`id_hotel`);

--
-- Indexes for table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `service_detail`
--
ALTER TABLE `service_detail`
  ADD PRIMARY KEY (`id_room`,`id_service`),
  ADD KEY `fk_serviceDetail_room` (`id_service`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `bill_detail`
--
ALTER TABLE `bill_detail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `categoryimage`
--
ALTER TABLE `categoryimage`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `complain`
--
ALTER TABLE `complain`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `evaluate`
--
ALTER TABLE `evaluate`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `image`
--
ALTER TABLE `image`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `image_papers`
--
ALTER TABLE `image_papers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `room`
--
ALTER TABLE `room`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `service`
--
ALTER TABLE `service`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `fk_account_role` FOREIGN KEY (`id_role`) REFERENCES `role` (`id`);

--
-- Constraints for table `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `fk_bill_account` FOREIGN KEY (`id_sender`) REFERENCES `account` (`id`) ON DELETE SET NULL,
  ADD CONSTRAINT `fk_bill_payment` FOREIGN KEY (`id_payment`) REFERENCES `payment` (`id`);

--
-- Constraints for table `bill_detail`
--
ALTER TABLE `bill_detail`
  ADD CONSTRAINT `fk_billDetail_bill` FOREIGN KEY (`id_bill`) REFERENCES `bill` (`id`) ON DELETE SET NULL,
  ADD CONSTRAINT `fk_billDetail_room` FOREIGN KEY (`id_room`) REFERENCES `room` (`id`) ON DELETE SET NULL;

--
-- Constraints for table `categoryimage`
--
ALTER TABLE `categoryimage`
  ADD CONSTRAINT `fk_categoryImage_hotel` FOREIGN KEY (`id_hotel`) REFERENCES `hotel` (`id_account`) ON DELETE NO ACTION;

--
-- Constraints for table `complain`
--
ALTER TABLE `complain`
  ADD CONSTRAINT `fk_handler_account` FOREIGN KEY (`handler`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `fk_sender_account` FOREIGN KEY (`sender`) REFERENCES `account` (`id`);

--
-- Constraints for table `evaluate`
--
ALTER TABLE `evaluate`
  ADD CONSTRAINT `fk_evulate_hotel` FOREIGN KEY (`id_hotel`) REFERENCES `hotel` (`id_account`) ON DELETE CASCADE;

--
-- Constraints for table `hotel`
--
ALTER TABLE `hotel`
  ADD CONSTRAINT `fk_hotel_account` FOREIGN KEY (`id_account`) REFERENCES `account` (`id`);

--
-- Constraints for table `image`
--
ALTER TABLE `image`
  ADD CONSTRAINT `fk_image_CategoryImage` FOREIGN KEY (`id_categoryImage`) REFERENCES `categoryimage` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `image_papers`
--
ALTER TABLE `image_papers`
  ADD CONSTRAINT `fk_paper_hotel` FOREIGN KEY (`id_hotel`) REFERENCES `hotel` (`id_account`) ON DELETE CASCADE;

--
-- Constraints for table `room`
--
ALTER TABLE `room`
  ADD CONSTRAINT `fk_room_hotel` FOREIGN KEY (`id_hotel`) REFERENCES `hotel` (`id_account`) ON DELETE CASCADE;

--
-- Constraints for table `service_detail`
--
ALTER TABLE `service_detail`
  ADD CONSTRAINT `fk_serviceDetail_hotel` FOREIGN KEY (`id_room`) REFERENCES `room` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_serviceDetail_room` FOREIGN KEY (`id_service`) REFERENCES `service` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
