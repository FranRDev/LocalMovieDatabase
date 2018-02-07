-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-02-2018 a las 21:42:43
-- Versión del servidor: 10.1.26-MariaDB
-- Versión de PHP: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `info_cine`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actor`
--

CREATE TABLE `actor` (
  `ID_ACTOR` int(11) NOT NULL,
  `NOMBRE` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `director`
--

CREATE TABLE `director` (
  `ID_DIRECTOR` int(11) NOT NULL,
  `NOMBRE` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pelicula`
--

CREATE TABLE `pelicula` (
  `ID_PELICULA` int(11) NOT NULL,
  `TITULO_ESPANHA` varchar(100) NOT NULL,
  `TITULO_ORIGINAL` varchar(100) NOT NULL,
  `ANHO` date NOT NULL,
  `DURACION` int(3) NOT NULL,
  `PAIS` int(3) NOT NULL,
  `ID_DIRECTOR` int(11) DEFAULT NULL,
  `GENERO` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pelicula_actor`
--

CREATE TABLE `pelicula_actor` (
  `ID_ACTOR` int(11) NOT NULL,
  `ID_PELICULA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `actor`
--
ALTER TABLE `actor`
  ADD PRIMARY KEY (`ID_ACTOR`);

--
-- Indices de la tabla `director`
--
ALTER TABLE `director`
  ADD PRIMARY KEY (`ID_DIRECTOR`);

--
-- Indices de la tabla `pelicula`
--
ALTER TABLE `pelicula`
  ADD PRIMARY KEY (`ID_PELICULA`),
  ADD KEY `ID_DIRECTOR` (`ID_DIRECTOR`);

--
-- Indices de la tabla `pelicula_actor`
--
ALTER TABLE `pelicula_actor`
  ADD PRIMARY KEY (`ID_ACTOR`,`ID_PELICULA`),
  ADD KEY `ID_PELICULA` (`ID_PELICULA`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `actor`
--
ALTER TABLE `actor`
  MODIFY `ID_ACTOR` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `director`
--
ALTER TABLE `director`
  MODIFY `ID_DIRECTOR` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pelicula`
--
ALTER TABLE `pelicula`
  MODIFY `ID_PELICULA` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `pelicula`
--
ALTER TABLE `pelicula`
  ADD CONSTRAINT `PELICULA_ibfk_1` FOREIGN KEY (`ID_DIRECTOR`) REFERENCES `director` (`ID_DIRECTOR`);

--
-- Filtros para la tabla `pelicula_actor`
--
ALTER TABLE `pelicula_actor`
  ADD CONSTRAINT `PELICULA_ACTOR_ibfk_1` FOREIGN KEY (`ID_ACTOR`) REFERENCES `actor` (`ID_ACTOR`),
  ADD CONSTRAINT `PELICULA_ACTOR_ibfk_2` FOREIGN KEY (`ID_PELICULA`) REFERENCES `pelicula` (`ID_PELICULA`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
