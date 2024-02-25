-- phpMyAdmin SQL Dump
-- version 5.3.0-dev+20220709.4e08d2933b
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th2 25, 2024 lúc 08:40 AM
-- Phiên bản máy phục vụ: 10.4.24-MariaDB
-- Phiên bản PHP: 8.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `managementhotel`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
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
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`id`, `first_name`, `last_name`, `email`, `password`, `phone`, `image`, `active`, `status`, `id_role`) VALUES
(2, 'fdsfdsf', 'dsfsdfs', 'sdfsdfsf', NULL, 'sdfsfsfs', 'sdfsfsf', 'sdfsfsf', NULL, 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bill`
--

CREATE TABLE `bill` (
  `id` int(11) NOT NULL,
  `idSender` int(11) DEFAULT NULL,
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
-- Cấu trúc bảng cho bảng `bill_detail`
--

CREATE TABLE `bill_detail` (
  `id` int(11) NOT NULL,
  `price` double DEFAULT NULL,
  `price_discount` double DEFAULT NULL,
  `number_day` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `reason_discount` varchar(250) DEFAULT NULL,
  `id_room` int(11) DEFAULT NULL,
  `idBill` int(11) DEFAULT NULL,
  `numberHour` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `categoryimage`
--

CREATE TABLE `categoryimage` (
  `id` int(11) NOT NULL,
  `idHotel` int(11) NOT NULL,
  `name` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `complain`
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
-- Cấu trúc bảng cho bảng `evaluate`
--

CREATE TABLE `evaluate` (
  `id` int(11) NOT NULL,
  `number` int(11) DEFAULT NULL,
  `comment` varchar(250) DEFAULT NULL,
  `id_account` int(11) DEFAULT NULL,
  `id_bill` int(11) DEFAULT NULL,
  `name_account` varchar(250) DEFAULT NULL,
  `idHotel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hotel`
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
  `idHandler` int(11) DEFAULT NULL,
  `address` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `image`
--

CREATE TABLE `image` (
  `id` int(11) NOT NULL,
  `name` varchar(250) DEFAULT NULL,
  `idCategoryImage` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `image_papers`
--

CREATE TABLE `image_papers` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `idHotel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `payment`
--

CREATE TABLE `payment` (
  `id` int(11) NOT NULL,
  `method` varchar(250) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'fdsfsfsd'),
(2, 'sdfsdfsdfdsdf');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `room`
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
  `idHotel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `service`
--

CREATE TABLE `service` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `service_detail`
--

CREATE TABLE `service_detail` (
  `id_room` int(11) NOT NULL,
  `id_service` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `fk_account_role` (`id_role`);

--
-- Chỉ mục cho bảng `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_bill_payment` (`id_payment`),
  ADD KEY `fk_bill_account` (`idSender`);

--
-- Chỉ mục cho bảng `bill_detail`
--
ALTER TABLE `bill_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_billDetail_bill` (`idBill`),
  ADD KEY `fk_billDetail_room` (`id_room`);

--
-- Chỉ mục cho bảng `categoryimage`
--
ALTER TABLE `categoryimage`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_categoryImage_hotel` (`idHotel`);

--
-- Chỉ mục cho bảng `complain`
--
ALTER TABLE `complain`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_sender_account` (`sender`),
  ADD KEY `fk_handler_account` (`handler`);

--
-- Chỉ mục cho bảng `evaluate`
--
ALTER TABLE `evaluate`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_evulate_hotel` (`idHotel`);

--
-- Chỉ mục cho bảng `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id_account`);

--
-- Chỉ mục cho bảng `image`
--
ALTER TABLE `image`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_image_CategoryImage` (`idCategoryImage`);

--
-- Chỉ mục cho bảng `image_papers`
--
ALTER TABLE `image_papers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_paper_hotel` (`idHotel`);

--
-- Chỉ mục cho bảng `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_room_hotel` (`idHotel`);

--
-- Chỉ mục cho bảng `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `service_detail`
--
ALTER TABLE `service_detail`
  ADD PRIMARY KEY (`id_room`,`id_service`),
  ADD KEY `fk_serviceDetail_room` (`id_service`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `bill`
--
ALTER TABLE `bill`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `bill_detail`
--
ALTER TABLE `bill_detail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `categoryimage`
--
ALTER TABLE `categoryimage`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `complain`
--
ALTER TABLE `complain`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `evaluate`
--
ALTER TABLE `evaluate`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `image`
--
ALTER TABLE `image`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `image_papers`
--
ALTER TABLE `image_papers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `payment`
--
ALTER TABLE `payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `room`
--
ALTER TABLE `room`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `service`
--
ALTER TABLE `service`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `fk_account_role` FOREIGN KEY (`id_role`) REFERENCES `role` (`id`);

--
-- Các ràng buộc cho bảng `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `fk_bill_account` FOREIGN KEY (`idSender`) REFERENCES `account` (`id`) ON DELETE SET NULL,
  ADD CONSTRAINT `fk_bill_payment` FOREIGN KEY (`id_payment`) REFERENCES `payment` (`id`);

--
-- Các ràng buộc cho bảng `bill_detail`
--
ALTER TABLE `bill_detail`
  ADD CONSTRAINT `fk_billDetail_bill` FOREIGN KEY (`idBill`) REFERENCES `bill` (`id`) ON DELETE SET NULL,
  ADD CONSTRAINT `fk_billDetail_room` FOREIGN KEY (`id_room`) REFERENCES `room` (`id`) ON DELETE SET NULL;

--
-- Các ràng buộc cho bảng `categoryimage`
--
ALTER TABLE `categoryimage`
  ADD CONSTRAINT `fk_categoryImage_hotel` FOREIGN KEY (`idHotel`) REFERENCES `hotel` (`id_account`) ON DELETE NO ACTION;

--
-- Các ràng buộc cho bảng `complain`
--
ALTER TABLE `complain`
  ADD CONSTRAINT `fk_handler_account` FOREIGN KEY (`handler`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `fk_sender_account` FOREIGN KEY (`sender`) REFERENCES `account` (`id`);

--
-- Các ràng buộc cho bảng `evaluate`
--
ALTER TABLE `evaluate`
  ADD CONSTRAINT `fk_evulate_hotel` FOREIGN KEY (`idHotel`) REFERENCES `hotel` (`id_account`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `hotel`
--
ALTER TABLE `hotel`
  ADD CONSTRAINT `fk_hotel_account` FOREIGN KEY (`id_account`) REFERENCES `account` (`id`);

--
-- Các ràng buộc cho bảng `image`
--
ALTER TABLE `image`
  ADD CONSTRAINT `fk_image_CategoryImage` FOREIGN KEY (`idCategoryImage`) REFERENCES `categoryimage` (`id`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `image_papers`
--
ALTER TABLE `image_papers`
  ADD CONSTRAINT `fk_paper_hotel` FOREIGN KEY (`idHotel`) REFERENCES `hotel` (`id_account`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `room`
--
ALTER TABLE `room`
  ADD CONSTRAINT `fk_room_hotel` FOREIGN KEY (`idHotel`) REFERENCES `hotel` (`id_account`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `service_detail`
--
ALTER TABLE `service_detail`
  ADD CONSTRAINT `fk_serviceDetail_hotel` FOREIGN KEY (`id_room`) REFERENCES `room` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_serviceDetail_room` FOREIGN KEY (`id_service`) REFERENCES `service` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;



