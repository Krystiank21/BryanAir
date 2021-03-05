-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 05 Mar 2021, 22:36
-- Wersja serwera: 10.4.17-MariaDB
-- Wersja PHP: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `bryan_air`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tflight`
--

CREATE TABLE `tflight` (
  `id` int(11) NOT NULL,
  `arrival` varchar(255) DEFAULT NULL,
  `departure` varchar(255) DEFAULT NULL,
  `flightNumber` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `seats` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `tflight`
--

INSERT INTO `tflight` (`id`, `arrival`, `departure`, `flightNumber`, `price`, `seats`) VALUES
(22, 'Madryt', 'Moskwa', 'FR-8080', 499.99, 70),
(20, 'Porto', 'Moskwa ', 'FR-1050', 499.99, 120),
(19, 'Toronto', 'Madryt', 'FR-1050', 1199.99, 160),
(18, 'Berlin', 'Kraków', 'FR-4620', 650.99, 90);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `tflight`
--
ALTER TABLE `tflight`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `tflight`
--
ALTER TABLE `tflight`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
