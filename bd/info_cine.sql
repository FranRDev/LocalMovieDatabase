-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 24-01-2018 a las 10:16:51
-- Versión del servidor: 5.7.21-0ubuntu0.16.04.1
-- Versión de PHP: 7.0.22-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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
-- Estructura de tabla para la tabla `ACTOR`
--

CREATE TABLE `ACTOR` (
  `ID_ACTOR` int(11) NOT NULL,
  `NOMBRE` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `DIRECTOR`
--

CREATE TABLE `DIRECTOR` (
  `ID_DIRECTOR` int(11) NOT NULL,
  `NOMBRE` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PELICULA`
--

CREATE TABLE `PELICULA` (
  `ID_PELICULA` int(11) NOT NULL,
  `TITULO_ESPANHA` varchar(100) NOT NULL,
  `TITULO_ORIGINAL` int(100) NOT NULL,
  `ANHO` date NOT NULL,
  `DURACION` int(3) NOT NULL,
  `PAIS` int(15) NOT NULL,
  `ID_DIRECTOR` int(11) NOT NULL,
  `SINOPSIS` text NOT NULL,
  `GENERO` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PELICULA_ACTOR`
--

CREATE TABLE `PELICULA_ACTOR` (
  `ID_ACTOR` int(11) NOT NULL,
  `ID_PELICULA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ACTOR`
--
ALTER TABLE `ACTOR`
  ADD PRIMARY KEY (`ID_ACTOR`);

--
-- Indices de la tabla `DIRECTOR`
--
ALTER TABLE `DIRECTOR`
  ADD PRIMARY KEY (`ID_DIRECTOR`);

--
-- Indices de la tabla `PELICULA`
--
ALTER TABLE `PELICULA`
  ADD PRIMARY KEY (`ID_PELICULA`),
  ADD KEY `ID_DIRECTOR` (`ID_DIRECTOR`);

--
-- Indices de la tabla `PELICULA_ACTOR`
--
ALTER TABLE `PELICULA_ACTOR`
  ADD PRIMARY KEY (`ID_ACTOR`,`ID_PELICULA`),
  ADD KEY `ID_PELICULA` (`ID_PELICULA`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ACTOR`
--
ALTER TABLE `ACTOR`
  MODIFY `ID_ACTOR` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `DIRECTOR`
--
ALTER TABLE `DIRECTOR`
  MODIFY `ID_DIRECTOR` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `PELICULA`
--
ALTER TABLE `PELICULA`
  MODIFY `ID_PELICULA` int(11) NOT NULL AUTO_INCREMENT;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `PELICULA`
--
ALTER TABLE `PELICULA`
  ADD CONSTRAINT `PELICULA_ibfk_1` FOREIGN KEY (`ID_DIRECTOR`) REFERENCES `DIRECTOR` (`ID_DIRECTOR`);

--
-- Filtros para la tabla `PELICULA_ACTOR`
--
ALTER TABLE `PELICULA_ACTOR`
  ADD CONSTRAINT `PELICULA_ACTOR_ibfk_1` FOREIGN KEY (`ID_ACTOR`) REFERENCES `ACTOR` (`ID_ACTOR`),
  ADD CONSTRAINT `PELICULA_ACTOR_ibfk_2` FOREIGN KEY (`ID_PELICULA`) REFERENCES `PELICULA` (`ID_PELICULA`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
