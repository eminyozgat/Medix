-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 21 May 2025, 14:23:24
-- Sunucu sürümü: 10.4.32-MariaDB
-- PHP Sürümü: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `hastane`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `hasta`
--

CREATE TABLE `hasta` (
  `id` int(11) NOT NULL,
  `isim` varchar(50) DEFAULT NULL,
  `soyisim` varchar(50) DEFAULT NULL,
  `telefon` varchar(20) DEFAULT NULL,
  `tc` varchar(11) DEFAULT NULL,
  `yas` int(11) DEFAULT NULL,
  `poliklinik` varchar(50) DEFAULT NULL,
  `doktor_adi` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `hasta`
--

INSERT INTO `hasta` (`id`, `isim`, `soyisim`, `telefon`, `tc`, `yas`, `poliklinik`, `doktor_adi`) VALUES
(20, 'Emin', 'Yozğat', '5394623611', '10101010101', 22, 'Dahiliye', 'Ahmet Yıldız'),
(21, 'Mehmet', 'Akif', '5554443322', '12121212123', 28, 'Dahiliye', 'Ahmet Yıldız'),
(22, 'Şeref', 'Kaan', '5455455544', '19191919198', 32, 'Cildiye', 'Mehmet Yılmaz'),
(23, 'Salih', 'Yıldız', '5465463212', '21212121218', 24, 'Cildiye', 'Salih Demir'),
(24, 'Ahmet', 'Yılmaz', '5394623611', '15151515158', 58, 'Cildiye', 'Salih Demir'),
(25, 'Burak', 'Gezer', '5424815454', '41414141417', 17, 'Cildiye', 'Mehmet Yılmaz'),
(26, 'Ali', 'Recep', '5324516784', '54545757277', 21, 'Cildiye', 'Mehmet Yılmaz'),
(27, 'Suat', 'Dönmez', '5417584576', '64646464875', 38, 'Cildiye', 'Salih Demir'),
(28, 'Burak', 'Kaya', '2545454545', '41245478504', 21, 'Cildiye', 'Salih Demir');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kullanici`
--

CREATE TABLE `kullanici` (
  `id` int(11) NOT NULL,
  `kullanici_adi` varchar(50) NOT NULL,
  `sifre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `kullanici`
--

INSERT INTO `kullanici` (`id`, `kullanici_adi`, `sifre`) VALUES
(1, 'admin', '12345');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `hasta`
--
ALTER TABLE `hasta`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `kullanici`
--
ALTER TABLE `kullanici`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `kullanici_adi` (`kullanici_adi`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `hasta`
--
ALTER TABLE `hasta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- Tablo için AUTO_INCREMENT değeri `kullanici`
--
ALTER TABLE `kullanici`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
