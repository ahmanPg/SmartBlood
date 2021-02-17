-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 28, 2019 at 12:27 PM
-- Server version: 10.1.35-MariaDB
-- PHP Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `smbb`
--

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `ID` int(11) NOT NULL,
  `districit` varchar(250) NOT NULL,
  `ward` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`ID`, `districit`, `ward`) VALUES
(1, 'Zanzibar West', 'Chukwani'),
(2, 'Zanzibar West', 'Bububu');

-- --------------------------------------------------------

--
-- Table structure for table `blood`
--

CREATE TABLE `blood` (
  `bagNo` int(11) NOT NULL,
  `donorID` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `blood`
--

INSERT INTO `blood` (`bagNo`, `donorID`, `date`) VALUES
(1, 1, '2018-12-31');

-- --------------------------------------------------------

--
-- Table structure for table `center`
--

CREATE TABLE `center` (
  `centerName` varchar(30) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `phone2` varchar(20) NOT NULL,
  `district` varchar(100) NOT NULL,
  `province` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `center`
--

INSERT INTO `center` (`centerName`, `phone`, `phone2`, `district`, `province`) VALUES
('Global Hospital', '+255677887767', '+25512346577', 'Zanzibar West', 'Vuga'),
('Jumbi Hospital', '+255778987645', '', 'Centra;l Unguja', 'Jumbi'),
('Nungwi Hospital', '+255778987649', '', 'North Unguja', 'Nungwi');

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `docID` int(11) NOT NULL,
  `fname` varchar(30) NOT NULL,
  `lname` varchar(30) NOT NULL,
  `centerName` varchar(30) NOT NULL,
  `profile` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `donation`
--

CREATE TABLE `donation` (
  `donationNo` int(11) NOT NULL,
  `donorID` int(11) NOT NULL,
  `centerName` varchar(30) NOT NULL,
  `docID` int(11) NOT NULL,
  `donatedDate` date NOT NULL,
  `bloodPressure` varchar(7) NOT NULL,
  `haemContent` varchar(7) NOT NULL,
  `pulse` varchar(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `donation`
--

INSERT INTO `donation` (`donationNo`, `donorID`, `centerName`, `docID`, `donatedDate`, `bloodPressure`, `haemContent`, `pulse`) VALUES
(4, 1, 'Jumbi Hospital', 3, '2019-02-13', '120/79', '5', '80'),
(5, 82, 'Nungwi Hospital', 3, '2019-02-13', '120/79', '5.4', '76'),
(6, 1, 'Global Hospital', 3, '2019-02-14', '110/65', '1209', '76'),
(7, 88, 'Jumbi Hospital', 3, '2019-02-14', '120/78', '6', '79'),
(8, 84, 'Global Hospital', 3, '2019-02-23', '120/79', '1200', '76'),
(9, 82, 'Global Hospital', 3, '2019-04-18', '120/80', '1200', '67'),
(10, 87, 'Global Hospital', 3, '2019-04-18', '120/79', '1209', '79'),
(11, 82, 'Nungwi Hospital', 3, '2019-04-18', '120/78', '5', '70'),
(12, 87, 'Global Hospital', 3, '2019-05-11', '121/87', '4.5', '78/min'),
(13, 82, 'Nungwi Hospital', 3, '2019-05-27', '120/80', '5.4', '76');

-- --------------------------------------------------------

--
-- Table structure for table `donors`
--

CREATE TABLE `donors` (
  `donorID` int(11) NOT NULL,
  `donorNo` int(11) NOT NULL,
  `fname` varchar(50) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `bloodType` varchar(3) NOT NULL,
  `weight` int(3) NOT NULL,
  `gender` varchar(6) NOT NULL,
  `birthDate` date NOT NULL,
  `district` varchar(100) NOT NULL,
  `province` varchar(100) NOT NULL,
  `profile` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `donors`
--

INSERT INTO `donors` (`donorID`, `donorNo`, `fname`, `lname`, `phone`, `bloodType`, `weight`, `gender`, `birthDate`, `district`, `province`, `profile`) VALUES
(1, 2025527, 'Ahmed', 'Nassour', '+255778987649', 'O+', 68, 'male', '1994-08-07', 'Zanzibar West', 'Chukwani', 'html/SmartBlood/PhotoUpload/uploads/Ahmed.png'),
(82, 2432721, 'Summy', 'Moh`d', '+255715457515', 'A+', 76, 'female', '1996-05-18', 'Zanzibar West', 'Fuoni', 'Summy.jpg'),
(84, 277152, 'Katumbi', 'Hussein', '+255778987677', 'B-', 87, 'male', '1997-12-11', 'South', 'Bwejuu', 'default.png'),
(87, 9878652, 'Said', 'Hamad', '+365656677', 'O+', 68, 'male', '2016-01-13', 'South', 'Jambiani', 'default.png'),
(88, 876644, 'akram', 'omar', '+786543244', 'AB+', 87, 'Male', '2003-01-13', 'North Pemba', 'Wete', 'akram.png'),
(93, 122377, 'IMran', 'Islam', '0718763498', 'AB+', 78, 'male', '1988-04-13', 'Central Unguja', 'Binguni', 'default.png'),
(94, 200002, 'Asya', 'Yusuf', '0776569823', 'AB+', 68, 'female', '2001-04-13', 'Zaznibar West', 'Bububu', 'default.png'),
(96, 2025520, 'Mussa', 'Khamis', '0774228745', 'A-', 76, 'male', '1989-03-19', 'Zanzibar West', 'Shangani', 'default.png'),
(97, 2025521, 'Awena', 'Massoud', '0768904532', 'B-', 60, 'male', '2000-09-18', 'North Pemba', 'Wete', 'default.png'),
(98, 2025523, 'Issa', 'Mussaddiq', '0654980732', 'AB+', 57, 'female', '1995-12-24', 'South', 'Nungwi', 'default.png');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(1) NOT NULL,
  `roleName` varchar(30) NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `roleName`, `createdAt`, `updatedAt`) VALUES
(1, 'admin', '2018-12-30 01:18:19', '0000-00-00 00:00:00'),
(2, 'donor', '2018-12-30 01:18:19', '0000-00-00 00:00:00'),
(3, 'doctor', '2018-12-31 22:30:05', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `seekers`
--

CREATE TABLE `seekers` (
  `fname` varchar(30) NOT NULL,
  `lname` varchar(30) NOT NULL,
  `bloodType` varchar(3) NOT NULL,
  `center` varchar(30) NOT NULL,
  `tel` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sms`
--

CREATE TABLE `sms` (
  `smsID` int(11) NOT NULL,
  `donorID` int(11) NOT NULL,
  `centerName` varchar(30) NOT NULL,
  `smsContent` varchar(500) NOT NULL,
  `time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `testresults`
--

CREATE TABLE `testresults` (
  `bagNo` int(11) NOT NULL,
  `donorID` int(11) NOT NULL,
  `date` date NOT NULL,
  `HIV/AIDS` varchar(8) NOT NULL,
  `pregnancy` varchar(8) NOT NULL,
  `hepatitis B/C` varchar(8) NOT NULL,
  `HTLV` varchar(8) NOT NULL,
  `maliria` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userID` int(11) NOT NULL,
  `userName` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `passID` varchar(255) NOT NULL,
  `utype` int(1) NOT NULL,
  `status` varchar(10) NOT NULL,
  `createdAt` timestamp NULL DEFAULT NULL,
  `updatedAt` timestamp NULL DEFAULT NULL,
  `secQuestion` varchar(250) NOT NULL DEFAULT 'Who is your favourite aunt?',
  `secAnswer` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userID`, `userName`, `email`, `passID`, `utype`, `status`, `createdAt`, `updatedAt`, `secQuestion`, `secAnswer`) VALUES
(1, 'Ahman', 'ahmanmassoud@gmail.com', '8cb2237d0679ca88db6464eac60da96345513964', 1, 'active', '2019-02-11 08:00:00', '2019-02-04 08:00:00', 'Who is your favourite aunt?', 'Asya'),
(2, 'sada', 'sada@yahoo.com', '8cb2237d0679ca88db6464eac60da96345513964', 3, 'active', '2019-02-11 08:00:00', '2019-02-04 08:00:00', 'Who is your favourite aunt?', 'Asya'),
(82, 'summy', 'summy@gmail.com', '8cb2237d0679ca88db6464eac60da96345513964', 2, 'active', '2019-02-11 08:00:00', '2019-02-04 08:00:00', 'Who is your favourite aunt?', ''),
(84, 'Katumbi', '', '54626743092f88f5ed64eb17bf56c65f017d8ee4', 2, 'active', '2019-02-14 08:25:11', '2019-02-14 08:25:11', 'Who is your favourite aunt?', ''),
(87, 'Said', '', '1778f9e97b75c3d8f196a72ce7512a4b9297685d', 2, 'active', '2019-02-14 08:39:19', '2019-02-14 08:39:19', 'Who is your favourite aunt?', ''),
(88, 'akram', '', 'c58933beb6ab44b5308f094136fde9795ce7d652', 2, 'active', '2019-02-14 09:22:19', '2019-02-14 09:22:19', 'Who is your favourite aunt?', ''),
(93, 'IMran', '', '89f11fbb2e2ddb13c67f03b735ca02f712a4302a', 2, 'active', '2019-05-14 08:20:51', '2019-05-14 08:20:51', 'Who is your favourite aunt?', ''),
(94, 'Asya', '', '5f3ea2b97cdf5412a7cbea261c00163c47aa4875', 2, 'active', '2019-05-14 08:29:57', '2019-05-14 08:29:57', 'Who is your favourite aunt?', ''),
(95, 'Ahmed', '', 'bf85cd6726e5995f55de4e9faf4360eb81e9a186', 2, 'active', '2019-05-18 20:08:27', '2019-05-18 20:08:27', 'Who is your favourite aunt?', ''),
(96, 'Mussa', '', '8dfbd8672ac9ed5db0589474607cfbf97a97d875', 2, 'active', '2019-05-20 15:20:19', '2019-05-20 15:20:19', 'Who is your favourite aunt?', ''),
(97, 'Awena', '', '816c87af8f2c23557c48cb11294cca2442b5fd47', 2, 'active', '2019-05-20 15:21:33', '2019-05-20 15:21:33', 'Who is your favourite aunt?', ''),
(98, 'Issa', '', '8befce978e9b3422d6c3a944bd66696026550a63', 2, 'active', '2019-05-20 15:23:25', '2019-05-20 15:23:25', 'Who is your favourite aunt?', ''),
(109, 'salimm', 'lkkjdlksj', '83b0c3d63e8a11eb6e40077030b59e95bfe31ffa', 2, 'active', '2019-05-27 06:30:01', '2019-05-27 06:30:01', 'Who is your favorite Aunt?', 'ad'),
(110, 'Salmini', 'salmin@gmail.com', '8cb2237d0679ca88db6464eac60da96345513964', 2, 'active', '2019-05-27 06:35:13', '2019-05-27 06:35:13', 'Who is your favorite Aunt?', 'Saida');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `blood`
--
ALTER TABLE `blood`
  ADD PRIMARY KEY (`bagNo`),
  ADD KEY `donorID` (`donorID`);

--
-- Indexes for table `center`
--
ALTER TABLE `center`
  ADD PRIMARY KEY (`centerName`);

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`docID`),
  ADD KEY `centerName` (`centerName`);

--
-- Indexes for table `donation`
--
ALTER TABLE `donation`
  ADD PRIMARY KEY (`donationNo`),
  ADD KEY `donorID` (`donorID`),
  ADD KEY `centerName` (`centerName`);

--
-- Indexes for table `donors`
--
ALTER TABLE `donors`
  ADD PRIMARY KEY (`donorID`),
  ADD UNIQUE KEY `phone` (`phone`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `seekers`
--
ALTER TABLE `seekers`
  ADD UNIQUE KEY `tel` (`tel`),
  ADD KEY `center` (`center`);

--
-- Indexes for table `sms`
--
ALTER TABLE `sms`
  ADD PRIMARY KEY (`smsID`),
  ADD KEY `donorID` (`donorID`,`centerName`),
  ADD KEY `centerName` (`centerName`);

--
-- Indexes for table `testresults`
--
ALTER TABLE `testresults`
  ADD PRIMARY KEY (`bagNo`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userID`),
  ADD KEY `utype` (`utype`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `address`
--
ALTER TABLE `address`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `blood`
--
ALTER TABLE `blood`
  MODIFY `bagNo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `donation`
--
ALTER TABLE `donation`
  MODIFY `donationNo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=111;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `blood`
--
ALTER TABLE `blood`
  ADD CONSTRAINT `blood_ibfk_1` FOREIGN KEY (`donorID`) REFERENCES `donors` (`donorID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `doctor`
--
ALTER TABLE `doctor`
  ADD CONSTRAINT `doctor_ibfk_1` FOREIGN KEY (`docID`) REFERENCES `users` (`userID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `doctor_ibfk_2` FOREIGN KEY (`centerName`) REFERENCES `center` (`centerName`);

--
-- Constraints for table `donation`
--
ALTER TABLE `donation`
  ADD CONSTRAINT `donation_ibfk_1` FOREIGN KEY (`donorID`) REFERENCES `donors` (`donorID`),
  ADD CONSTRAINT `donation_ibfk_2` FOREIGN KEY (`centerName`) REFERENCES `center` (`centerName`);

--
-- Constraints for table `donors`
--
ALTER TABLE `donors`
  ADD CONSTRAINT `donors_ibfk_1` FOREIGN KEY (`donorID`) REFERENCES `users` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `seekers`
--
ALTER TABLE `seekers`
  ADD CONSTRAINT `seekers_ibfk_1` FOREIGN KEY (`center`) REFERENCES `center` (`centerName`);

--
-- Constraints for table `testresults`
--
ALTER TABLE `testresults`
  ADD CONSTRAINT `testresults_ibfk_1` FOREIGN KEY (`bagNo`) REFERENCES `blood` (`bagNo`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`utype`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
