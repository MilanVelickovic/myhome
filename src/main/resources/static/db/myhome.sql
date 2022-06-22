-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 21, 2022 at 03:55 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `myhome`
--

-- --------------------------------------------------------

--
-- Table structure for table `addresses`
--

CREATE TABLE `addresses` (
  `id` int(8) UNSIGNED NOT NULL,
  `country` varchar(32) NOT NULL,
  `city` varchar(32) NOT NULL,
  `street` varchar(64) NOT NULL,
  `number` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `addresses`
--

INSERT INTO `addresses` (`id`, `country`, `city`, `street`, `number`) VALUES
(1, 'Serbia', 'Niš', 'Učitelj Milina 29b', '15'),
(2, 'Turkey', 'Istanbul', 'Street 10', '12A'),
(8, 'Portugal', 'Some city in Portugal', 'Some street in Portugal', '10'),
(21, 'Botswana', '1', '1', '1'),
(22, 'Botswana', '1', '1', '1'),
(23, 'Botswana', '1', '1', '1'),
(24, 'Botswana', '1', '1', '1'),
(25, 'Botswana', '1', '1', '1'),
(26, 'Azerbaijan', '1', '1', '1'),
(28, 'Egypt', '1', '1', '1'),
(32, 'Afghanistan', '1', '1', '1'),
(34, 'Norway', 'Oslo', 'Street 17', '3rd floor, 9 ');

-- --------------------------------------------------------

--
-- Table structure for table `advertisement`
--

CREATE TABLE `advertisement` (
  `id` int(8) UNSIGNED NOT NULL,
  `user` int(8) UNSIGNED NOT NULL,
  `title` varchar(32) NOT NULL,
  `real_estate` int(8) UNSIGNED NOT NULL,
  `service` int(2) UNSIGNED NOT NULL,
  `price` bigint(9) UNSIGNED NOT NULL,
  `description` text NOT NULL,
  `images` varchar(32) DEFAULT NULL,
  `published_on` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `advertisement`
--

INSERT INTO `advertisement` (`id`, `user`, `title`, `real_estate`, `service`, `price`, `description`, `images`, `published_on`) VALUES
(1, 1, 'Apartment in the city center', 1, 3, 800, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', NULL, '2022-06-14 15:11:35'),
(2, 2, 'Sea view apartment in Besiktas', 2, 2, 4950000, 'With views out towards the sea and surrounding city, these designer apartments are found in the city centre of Istanbul in Besiktas and form part of a top quality project equipped with a range of facilities including a pool and spaces for relaxing. Enquire today as these are selling out fast.', NULL, '2022-06-17 00:35:46'),
(5, 1, 'Beautiful villa on the coast', 7, 1, 1200000, 'Loerm ipsum dolor sit amet.', NULL, '2022-06-19 23:37:41'),
(23, 4, 'Apartment Blue', 33, 2, 80000, ' Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', NULL, '2022-06-21 01:48:01');

-- --------------------------------------------------------

--
-- Table structure for table `real_estates`
--

CREATE TABLE `real_estates` (
  `id` int(8) UNSIGNED NOT NULL,
  `type` int(2) UNSIGNED NOT NULL,
  `address` int(8) UNSIGNED NOT NULL,
  `size` int(4) UNSIGNED NOT NULL,
  `bedrooms` int(2) UNSIGNED NOT NULL,
  `bathrooms` int(2) UNSIGNED NOT NULL,
  `car_spaces` int(2) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `real_estates`
--

INSERT INTO `real_estates` (`id`, `type`, `address`, `size`, `bedrooms`, `bathrooms`, `car_spaces`) VALUES
(1, 2, 1, 75, 3, 1, 2),
(2, 1, 2, 350, 5, 6, 3),
(7, 1, 8, 400, 6, 7, 4),
(20, 2, 21, 1, 1, 1, 1),
(22, 2, 23, 1, 1, 1, 1),
(23, 2, 24, 1, 1, 1, 1),
(24, 2, 25, 1, 1, 1, 1),
(25, 2, 26, 3321, 1, 1, 1),
(27, 1, 28, 1, 2, 3, 5),
(31, 1, 32, 1, 1, 1, 1),
(33, 2, 34, 70, 2, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `real_estate_types`
--

CREATE TABLE `real_estate_types` (
  `id` int(2) UNSIGNED NOT NULL,
  `name` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `real_estate_types`
--

INSERT INTO `real_estate_types` (`id`, `name`) VALUES
(1, 'House'),
(2, 'Apartment'),
(3, 'Land');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(1) UNSIGNED NOT NULL,
  `name` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'User'),
(2, 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `service_types`
--

CREATE TABLE `service_types` (
  `id` int(2) UNSIGNED NOT NULL,
  `name` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `service_types`
--

INSERT INTO `service_types` (`id`, `name`) VALUES
(1, 'Purchase'),
(2, 'Sale'),
(3, 'Rent');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(8) UNSIGNED NOT NULL,
  `email` varchar(64) NOT NULL,
  `first_name` varchar(32) NOT NULL,
  `last_name` varchar(32) NOT NULL,
  `phone_number` varchar(32) NOT NULL,
  `password` varchar(64) NOT NULL,
  `role` int(8) UNSIGNED NOT NULL,
  `avatar` varchar(8) NOT NULL,
  `joined_on` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `first_name`, `last_name`, `phone_number`, `password`, `role`, `avatar`, `joined_on`) VALUES
(1, 'milan.velickovic@gmail.com', 'Milan', 'Veličković', '+381 063 7711999', '$2y$10$3kXqTcMxJS0HSxlDTNKMP.SOMYiJEQkqLnDQPur5aInO1X2FpaRX2', 2, 'ava3', '2022-06-14 15:04:37'),
(2, 'can.tekin@gmail.com', 'Can', 'Tekin', '+90 109 212 2965', '$2y$10$a0djnmpUChDSJU1T/m/Zoe.VTJXQAEVGq1Me0H/YWxAy5FEAbj1Km', 1, 'ava6', '2022-06-17 01:13:02'),
(3, 'monica.geller@yahoo.com', 'Monica', 'Geller', '+1 746 080 2241', '$2y$10$UQmcDsEI2REv6J2oyVM59.1jOGsJZf3dLBTqHew7dfxvbiudhloN6', 1, 'ava1', '2022-06-19 00:57:23'),
(4, 'leyla.tanlar@outlook.com', 'Leyla', 'Tanlar', '+374 213 2965', '$2a$10$QidWQSTykYqWZHMM9OKi7uIiki1.D1lKjML0u5kLel5f6QgYjcfGC', 1, 'ava1', '2022-06-19 01:47:24');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `addresses`
--
ALTER TABLE `addresses`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `advertisement`
--
ALTER TABLE `advertisement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `advertisement_real_estate` (`real_estate`),
  ADD KEY `advertisement_service` (`service`),
  ADD KEY `advertisement_user` (`user`);

--
-- Indexes for table `real_estates`
--
ALTER TABLE `real_estates`
  ADD PRIMARY KEY (`id`),
  ADD KEY `real_estate_real_estate_types` (`type`),
  ADD KEY `real_estate_addresses` (`address`);

--
-- Indexes for table `real_estate_types`
--
ALTER TABLE `real_estate_types`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `service_types`
--
ALTER TABLE `service_types`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `users_roles` (`role`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `addresses`
--
ALTER TABLE `addresses`
  MODIFY `id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `advertisement`
--
ALTER TABLE `advertisement`
  MODIFY `id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `real_estates`
--
ALTER TABLE `real_estates`
  MODIFY `id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `real_estate_types`
--
ALTER TABLE `real_estate_types`
  MODIFY `id` int(2) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(1) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `service_types`
--
ALTER TABLE `service_types`
  MODIFY `id` int(2) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `advertisement`
--
ALTER TABLE `advertisement`
  ADD CONSTRAINT `advertisement_real_estate` FOREIGN KEY (`real_estate`) REFERENCES `real_estates` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `advertisement_service` FOREIGN KEY (`service`) REFERENCES `service_types` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `advertisement_user` FOREIGN KEY (`user`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `real_estates`
--
ALTER TABLE `real_estates`
  ADD CONSTRAINT `real_estate_addresses` FOREIGN KEY (`address`) REFERENCES `addresses` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `real_estate_real_estate_types` FOREIGN KEY (`type`) REFERENCES `real_estate_types` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_roles` FOREIGN KEY (`role`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
