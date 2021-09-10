-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 30, 2019 at 12:37 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `java.b06`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `bookID` varchar(15) NOT NULL,
  `name` varchar(30) NOT NULL,
  `author` varchar(30) NOT NULL,
  `quantity` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`bookID`, `name`, `author`, `quantity`) VALUES
('b001', 'Megh Boleche Jabo Jabo', 'Humayun Ahmed', 11),
('b002', 'Brishty', 'Md Zafar Iqbal', 11),
('b11', 'test', 'no', 100);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `cstId` varchar(15) NOT NULL,
  `name` varchar(30) NOT NULL,
  `address` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`cstId`, `name`, `address`, `email`) VALUES
('c0034', 'Tabid', 'Mirpur', 'ahsan@gmail.com'),
('Mahi01', 'Mahi', 'Agargaon', 'nomail'),
('Sajid01', 'Sajid', 'Mohakhali', 'sajid@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `empId` varchar(6) NOT NULL,
  `employeeName` varchar(30) NOT NULL,
  `designation` varchar(20) NOT NULL,
  `salary` double(8,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`empId`, `employeeName`, `designation`, `salary`) VALUES
('e00001', 'Employe1', 'manager', 300000.00),
('e00002', 'Employee2', 'salesman', 220000.00),
('e00003', 'emp3', 'salesman', 10000.00),
('e00006', 'e6', 'cashier', 10000.00),
('e013', 'Employee13', 'manager', 5345.00),
('e11', 'mp10', 'cash', 13000.00),
('e16', 'asdgg', 'cashier', 10000.00),
('emp1', 'Salehin', 'Assistant ', 16000.00),
('emp2', 'hg', 'gh', 123.00),
('eooo23', 'Name1', 'Manager', 2000.00),
('TDB', 'TstDb', 'DataBase', 1111.00);

-- --------------------------------------------------------

--
-- Table structure for table `loanlist`
--

CREATE TABLE `loanlist` (
  `loanId` varchar(15) NOT NULL,
  `bookId` varchar(15) NOT NULL,
  `cstId` varchar(15) NOT NULL,
  `returnDate` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `loanlist`
--

INSERT INTO `loanlist` (`loanId`, `bookId`, `cstId`, `returnDate`) VALUES
('Loan1', 'B11', 'Mahi01', '31.04');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `userId` varchar(8) NOT NULL,
  `password` varchar(10) NOT NULL,
  `status` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`userId`, `password`, `status`) VALUES
('c0034', '1234', 2),
('emp1', '12226879', 1),
('emp2', '13599982', 1),
('Mahi01', 'Mahi01', 2),
('manager', '1234', 0),
('Sajid01', '12345', 2),
('TDB', '12134856', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`bookID`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`cstId`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`empId`);

--
-- Indexes for table `loanlist`
--
ALTER TABLE `loanlist`
  ADD PRIMARY KEY (`loanId`),
  ADD UNIQUE KEY `bookId` (`bookId`),
  ADD UNIQUE KEY `cstId` (`cstId`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`userId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
