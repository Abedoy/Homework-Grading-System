-- phpMyAdmin SQL Dump
-- version 3.3.8.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 29, 2013 at 02:33 PM
-- Server version: 5.6.10
-- PHP Version: 5.3.26

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `gradingsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `Person`
--

CREATE TABLE `Person` (
  `PersonId` int(10) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `MInitial` char(2) DEFAULT NULL,
  `LastName` varchar(50) NOT NULL,
  `email` varchar(30) NOT NULL,
  `Password` varchar(10) NOT NULL,
  UNIQUE KEY `PersonId` (`PersonId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
