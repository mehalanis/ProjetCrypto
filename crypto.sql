-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  Dim 12 jan. 2020 à 23:11
-- Version du serveur :  10.1.38-MariaDB
-- Version de PHP :  7.1.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `crypto`
--

-- --------------------------------------------------------

--
-- Structure de la table `auto`
--

CREATE TABLE `auto` (
  `ID_U` int(11) DEFAULT NULL,
  `ID_F` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `crypto`
--

CREATE TABLE `crypto` (
  `ID_CRYPTO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `crypto`
--

INSERT INTO `crypto` (`ID_CRYPTO`) VALUES
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9),
(10),
(11),
(12),
(13),
(14),
(15),
(16),
(17),
(18);

-- --------------------------------------------------------

--
-- Structure de la table `fichier`
--

CREATE TABLE `fichier` (
  `ID_F` int(11) NOT NULL,
  `ID_U` int(11) DEFAULT NULL,
  `ID_TYPE` int(11) DEFAULT NULL,
  `ID_CRYPTO` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `fichier`
--

INSERT INTO `fichier` (`ID_F`, `ID_U`, `ID_TYPE`, `ID_CRYPTO`) VALUES
(1, 1, 3, 11),
(2, 1, 1, 12),
(3, 1, 3, 13),
(4, 1, 4, 14),
(5, 1, 3, 15),
(6, 1, 4, 16),
(7, 1, 1, 17),
(8, 1, 2, 18);

-- --------------------------------------------------------

--
-- Structure de la table `hill`
--

CREATE TABLE `hill` (
  `ID_HILL` int(11) NOT NULL,
  `ID_CRYPTO` int(11) DEFAULT NULL,
  `M00` int(11) DEFAULT NULL,
  `M01` int(11) DEFAULT NULL,
  `M10` int(11) DEFAULT NULL,
  `M11` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `rsa`
--

CREATE TABLE `rsa` (
  `ID_RSA` int(11) NOT NULL,
  `ID_CRYPTO` int(11) DEFAULT NULL,
  `P` bigint(20) DEFAULT NULL,
  `Q` bigint(20) DEFAULT NULL,
  `E` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `type`
--

CREATE TABLE `type` (
  `ID_TYPE` int(11) NOT NULL,
  `NOM` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `type`
--

INSERT INTO `type` (`ID_TYPE`, `NOM`) VALUES
(1, 'Cesar'),
(2, 'Vigenere'),
(3, 'DES'),
(4, 'AES');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `ID_U` int(11) NOT NULL,
  `EMAIL` varchar(40) DEFAULT NULL,
  `PASSWORD` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`ID_U`, `EMAIL`, `PASSWORD`) VALUES
(1, 'test', 'test');

-- --------------------------------------------------------

--
-- Structure de la table `vig_aes_des_cesar`
--

CREATE TABLE `vig_aes_des_cesar` (
  `ID_VADC` int(11) NOT NULL,
  `ID_CRYPTO` int(11) DEFAULT NULL,
  `PASS` varchar(600) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `vig_aes_des_cesar`
--

INSERT INTO `vig_aes_des_cesar` (`ID_VADC`, `ID_CRYPTO`, `PASS`) VALUES
(1, 12, '0'),
(2, 13, '12345678'),
(3, 14, '1234567812345678'),
(4, 15, '12345678'),
(5, 16, '1234567812345678'),
(6, 17, '1'),
(7, 18, 'anis');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `auto`
--
ALTER TABLE `auto`
  ADD KEY `FK_REFERENCE_10` (`ID_F`),
  ADD KEY `FK_REFERENCE_11` (`ID_U`);

--
-- Index pour la table `crypto`
--
ALTER TABLE `crypto`
  ADD PRIMARY KEY (`ID_CRYPTO`);

--
-- Index pour la table `fichier`
--
ALTER TABLE `fichier`
  ADD PRIMARY KEY (`ID_F`),
  ADD KEY `FK_REFERENCE_12` (`ID_U`),
  ADD KEY `FK_REFERENCE_4` (`ID_TYPE`),
  ADD KEY `FK_REFERENCE_9` (`ID_CRYPTO`);

--
-- Index pour la table `hill`
--
ALTER TABLE `hill`
  ADD PRIMARY KEY (`ID_HILL`),
  ADD KEY `FK_REFERENCE_8` (`ID_CRYPTO`);

--
-- Index pour la table `rsa`
--
ALTER TABLE `rsa`
  ADD PRIMARY KEY (`ID_RSA`),
  ADD KEY `FK_REFERENCE_13` (`ID_CRYPTO`);

--
-- Index pour la table `type`
--
ALTER TABLE `type`
  ADD PRIMARY KEY (`ID_TYPE`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID_U`);

--
-- Index pour la table `vig_aes_des_cesar`
--
ALTER TABLE `vig_aes_des_cesar`
  ADD PRIMARY KEY (`ID_VADC`),
  ADD KEY `FK_REFERENCE_7` (`ID_CRYPTO`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `crypto`
--
ALTER TABLE `crypto`
  MODIFY `ID_CRYPTO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT pour la table `fichier`
--
ALTER TABLE `fichier`
  MODIFY `ID_F` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `hill`
--
ALTER TABLE `hill`
  MODIFY `ID_HILL` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `rsa`
--
ALTER TABLE `rsa`
  MODIFY `ID_RSA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `type`
--
ALTER TABLE `type`
  MODIFY `ID_TYPE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `ID_U` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `vig_aes_des_cesar`
--
ALTER TABLE `vig_aes_des_cesar`
  MODIFY `ID_VADC` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `auto`
--
ALTER TABLE `auto`
  ADD CONSTRAINT `FK_REFERENCE_10` FOREIGN KEY (`ID_F`) REFERENCES `fichier` (`ID_F`),
  ADD CONSTRAINT `FK_REFERENCE_11` FOREIGN KEY (`ID_U`) REFERENCES `user` (`ID_U`);

--
-- Contraintes pour la table `fichier`
--
ALTER TABLE `fichier`
  ADD CONSTRAINT `FK_REFERENCE_12` FOREIGN KEY (`ID_U`) REFERENCES `user` (`ID_U`),
  ADD CONSTRAINT `FK_REFERENCE_4` FOREIGN KEY (`ID_TYPE`) REFERENCES `type` (`ID_TYPE`),
  ADD CONSTRAINT `FK_REFERENCE_9` FOREIGN KEY (`ID_CRYPTO`) REFERENCES `crypto` (`ID_CRYPTO`);

--
-- Contraintes pour la table `hill`
--
ALTER TABLE `hill`
  ADD CONSTRAINT `FK_REFERENCE_8` FOREIGN KEY (`ID_CRYPTO`) REFERENCES `crypto` (`ID_CRYPTO`);

--
-- Contraintes pour la table `rsa`
--
ALTER TABLE `rsa`
  ADD CONSTRAINT `FK_REFERENCE_13` FOREIGN KEY (`ID_CRYPTO`) REFERENCES `crypto` (`ID_CRYPTO`);

--
-- Contraintes pour la table `vig_aes_des_cesar`
--
ALTER TABLE `vig_aes_des_cesar`
  ADD CONSTRAINT `FK_REFERENCE_7` FOREIGN KEY (`ID_CRYPTO`) REFERENCES `crypto` (`ID_CRYPTO`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
