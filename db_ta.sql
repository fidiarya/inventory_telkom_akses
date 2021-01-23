-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 26, 2020 at 03:17 AM
-- Server version: 10.1.25-MariaDB
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_ta`
--

-- --------------------------------------------------------

--
-- Table structure for table `pemakaian`
--

CREATE TABLE `pemakaian` (
  `id_pemakaian` varchar(20) NOT NULL,
  `id_pengeluaran` varchar(20) NOT NULL,
  `tgl_pemakaian` varchar(20) NOT NULL,
  `no_gangguan` varchar(20) NOT NULL,
  `keluhan` varchar(50) NOT NULL,
  `tindakan` varchar(50) NOT NULL,
  `id_material` varchar(20) NOT NULL,
  `nm_material` varchar(50) NOT NULL,
  `id_pelanggan` varchar(20) NOT NULL,
  `nm_pelanggan` varchar(50) NOT NULL,
  `alamat` text NOT NULL,
  `qty` varchar(10) NOT NULL,
  `satuan` varchar(10) NOT NULL,
  `nm_teknisi` varchar(50) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pemakaian`
--

INSERT INTO `pemakaian` (`id_pemakaian`, `id_pengeluaran`, `tgl_pemakaian`, `no_gangguan`, `keluhan`, `tindakan`, `id_material`, `nm_material`, `id_pelanggan`, `nm_pelanggan`, `alamat`, `qty`, `satuan`, `nm_teknisi`, `status`) VALUES
('PEM0001', 'OUT0001', '04 Desember 2020', '111111', 'WWWWWWWWW', 'SSSSSSSSSS', 'OD180001', 'ODP-Solid-8-LKP', '2222222', 'AAAAAAAAAAAAA', 'WWWWWWWWWW', '10', 'pcs', 'Robert', ''),
('PEM0002', 'OUT0002', '05 Desember 2020', '111112', 'SSSSSSSS', 'DDDDDDDDDD', 'OD180001', 'ODP-Solid-8-LKP', '2222223', 'QQEQ', 'DDDDDDD', '12', 'pcs', 'Robert', 'pakai'),
('PEM0003', 'OUT0003', '06 Desember 2020', '11234', 'AAAAAAAA', 'DDDDDDDD', 'OD180001', 'ODP-Solid-8-LKP', '13425', 'SSSSSSSFG', 'EETTTTTTTTTTTTU', '12', 'pcs', 'Robert', 'Rekon'),
('PEM0004', 'OUT0007', '10 Desember 2020', '1231234', 'Pecah', 'Ganti Baru', 'CV0001', 'Converter FO', '12345678', 'Susi', 'Pesona Gading', '50', 'pcs', 'Herman', 'Rekon'),
('PEM0005', 'OUT0008', '13 Desember 2020', '1234', 'kabel putus', 'ganti baru', 'CV0001', 'Converter FO', '1234124', 'adfafdfff', 'cibitung', '5', 'pcs', 'paijo', 'Rekon'),
('PEM0006', 'OUT0009', '15 Desember 2020', 'IN2009876', 'INTERNET LOS', 'GANTI KABEL', 'AC-OF-SM-1B', 'KABEL OPTIK', '122861234567', 'SITI', 'METLAND TAMBUN', '200', 'pcs', 'AGUS', 'Tidak Rekon'),
('PEM0007', 'OUT0009', '30 Desember 2020', 'IN6554389', 'INTERNET MATI', 'GANTI KABEL', 'AC-OF-SM-1B', 'KABEL OPTIK', '122861009889', 'PT DENSO TBK', 'KAWASAN MM 2100', '800', 'pcs', 'AGUS', 'Rekon'),
('PEM0008', 'OUT0009', '24 Desember 2020', '1234', 'rusak', 'ganti baru', 'AC-OF-SM-1B', 'KABEL OPTIK', '123123', 'gogon', 'bekasi', '1000', 'pcs', 'AGUS', 'Rekon'),
('PEM0009', 'OUT0004', '23 Desember 2020', '123', 'putus', 'sambung', 'www123', 'wolding-wow', '123123', 'bayu', 'cibitung', '100', 'pcs', 'Dudin', 'Tidak Rekon'),
('PEM0010', 'OUT0010', '27 Desember 2020', '14414', 'Kabel putus', 'Gnati Kabel', 'AC-OF-SM-1B', 'KABEL OPTIK', '123', 'tutik', 'bekasi', '100', 'meter', 'aji', 'Rekon');

-- --------------------------------------------------------

--
-- Table structure for table `penerimaan`
--

CREATE TABLE `penerimaan` (
  `id_penerimaan` varchar(20) NOT NULL,
  `tgl_penerimaan` varchar(20) NOT NULL,
  `id_material` varchar(20) NOT NULL,
  `nm_material` varchar(50) NOT NULL,
  `id_vendor` varchar(20) NOT NULL,
  `qty` varchar(10) NOT NULL,
  `satuan` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `penerimaan`
--

INSERT INTO `penerimaan` (`id_penerimaan`, `tgl_penerimaan`, `id_material`, `nm_material`, `id_vendor`, `qty`, `satuan`) VALUES
('PEN0001', '30 November 2020', 'OD180001', 'ODP-Solid-8-LKP', 'HW0001', '240', 'pcs'),
('PEN0002', '30 November 2020', 'OD180001', 'ODP-Solid-8-LKP', 'HW0001', '810', 'pcs'),
('PEN0003', '30 November 2020', 'www123', 'wolding-wow', 'HW0001', '100', 'pcs'),
('PEN0004', '30 November 2020', 'www123', 'wolding-wow', 'HW0001', '200', 'pcs');

-- --------------------------------------------------------

--
-- Table structure for table `pengeluaran`
--

CREATE TABLE `pengeluaran` (
  `id_pengeluaran` varchar(20) NOT NULL,
  `tgl_pengeluaran` varchar(20) NOT NULL,
  `id_permintaan` varchar(10) NOT NULL,
  `id_material` varchar(20) NOT NULL,
  `nm_material` varchar(50) NOT NULL,
  `qty` varchar(10) NOT NULL,
  `satuan` varchar(10) NOT NULL,
  `nm_teknisi` varchar(50) NOT NULL,
  `nm_admin` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pengeluaran`
--

INSERT INTO `pengeluaran` (`id_pengeluaran`, `tgl_pengeluaran`, `id_permintaan`, `id_material`, `nm_material`, `qty`, `satuan`, `nm_teknisi`, `nm_admin`) VALUES
('OUT0001', '01 Desember 2020', 'PER0001', 'OD180001', 'ODP-Solid-8-LKP', '12', 'pcs', 'Robert', 'Ilham Muharif'),
('OUT0002', '01 Desember 2020', 'PER0001', 'OD180001', 'ODP-Solid-8-LKP', '12', 'pcs', 'Robert', 'Ilham Muharif'),
('OUT0003', '01 Desember 2020', 'PER0001', 'OD180001', 'ODP-Solid-8-LKP', '12', 'pcs', 'Robert', 'Ilham Muharif'),
('OUT0004', '03 Desember 2020', 'PER0002', 'www123', 'wolding-wow', '20', 'pcs', 'Dudin', 'Ilham Muharif'),
('OUT0005', '04 Desember 2020', 'PER0003', 'OD180001', 'ODP-Solid-8-LKP', '10', 'pcs', 'Abdul Hamid', 'Ilham Muharif'),
('OUT0006', '04 Desember 2020', 'PER0004', 'OD180001', 'ODP-Solid-8-LKP', '13', 'pcs', 'Abdul', 'Ilham Muharif'),
('OUT0007', '09 Desember 2020', 'PER0005', 'CV0001', 'Converter FO', '50', 'pcs', 'Herman', 'Ilham Muharif'),
('OUT0008', '13 Desember 2020', 'PER0006', 'CV0001', 'Converter FO', '5', 'pcs', 'paijo', 'Ilham Muharif'),
('OUT0009', '13 Desember 2020', 'PER0007', 'AC-OF-SM-1B', 'KABEL OPTIK', '1000', 'pcs', 'AGUS', 'Ilham Muharif'),
('OUT0010', '26 Desember 2020', 'PER0008', 'AC-OF-SM-1B', 'KABEL OPTIK', '100', 'meter', 'aji', 'Ilham Muharif');

-- --------------------------------------------------------

--
-- Table structure for table `permintaan`
--

CREATE TABLE `permintaan` (
  `id_permintaan` varchar(20) NOT NULL,
  `tgl_permintaan` varchar(20) NOT NULL,
  `id_material` varchar(20) NOT NULL,
  `nm_material` varchar(50) NOT NULL,
  `qty` varchar(10) NOT NULL,
  `satuan` varchar(10) NOT NULL,
  `nm_teknisi` varchar(50) NOT NULL,
  `nm_leader` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `permintaan`
--

INSERT INTO `permintaan` (`id_permintaan`, `tgl_permintaan`, `id_material`, `nm_material`, `qty`, `satuan`, `nm_teknisi`, `nm_leader`, `status`) VALUES
('PER0001', '01 Desember 2020', 'OD180001', 'ODP-Solid-8-LKP', '12', 'pcs', 'Robert', 'Doni', 'Keluar'),
('PER0002', '03 Desember 2020', 'www123', 'wolding-wow', '20', 'pcs', 'Dudin', 'Sutoro Waluyo', 'Approve'),
('PER0003', '04 Desember 2020', 'OD180001', 'ODP-Solid-8-LKP', '10', 'pcs', 'Abdul Hamid', 'Sutoro Waluyo', 'Keluar'),
('PER0004', '04 Desember 2020', 'OD180001', 'ODP-Solid-8-LKP', '13', 'pcs', 'Abdul', 'Sutoro Waluyo', 'Keluar'),
('PER0005', '09 Desember 2020', 'CV0001', 'Converter FO', '50', 'pcs', 'Herman', 'Sutoro Waluyo', 'Keluar'),
('PER0006', '13 Desember 2020', 'CV0001', 'Converter FO', '5', 'pcs', 'paijo', 'Sutoro Waluyo', 'Keluar'),
('PER0007', '13 Desember 2020', 'AC-OF-SM-1B', 'KABEL OPTIK', '1000', 'pcs', 'AGUS', 'Sutoro Waluyo', 'Keluar'),
('PER0008', '26 Desember 2020', 'AC-OF-SM-1B', 'KABEL OPTIK', '100', 'meter', 'aji', 'Sutoro Waluyo', 'Keluar');

-- --------------------------------------------------------

--
-- Table structure for table `rekon`
--

CREATE TABLE `rekon` (
  `id_pakai` varchar(20) NOT NULL,
  `id_material` varchar(20) NOT NULL,
  `nm_material` varchar(50) NOT NULL,
  `qty_pakai` varchar(10) NOT NULL,
  `sat_pakai` varchar(10) NOT NULL,
  `id_keluar` varchar(20) NOT NULL,
  `qty_keluar` varchar(10) NOT NULL,
  `sat_keluar` varchar(10) NOT NULL,
  `tgl_rekon` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rekon`
--

INSERT INTO `rekon` (`id_pakai`, `id_material`, `nm_material`, `qty_pakai`, `sat_pakai`, `id_keluar`, `qty_keluar`, `sat_keluar`, `tgl_rekon`, `status`) VALUES
('PEM0003', 'OD180001', 'ODP-Solid-8-LKP', '12', 'pcs', 'OUT0003', '12', 'pcs', '23 Desember 2020', 'Rekon'),
('PEM0004', 'CV0001', 'Converter FO', '50', 'pcs', 'OUT0007', '50', 'pcs', '26 Desember 2020', 'Rekon'),
('PEM0005', 'CV0001', 'Converter FO', '5', 'pcs', 'OUT0008', '5', 'pcs', '26 Desember 2020', 'Rekon'),
('PEM0010', 'AC-OF-SM-1B', 'KABEL OPTIK', '100', 'meter', 'OUT0010', '100', 'meter', '26 Desember 2020', 'Rekon');

-- --------------------------------------------------------

--
-- Table structure for table `tb_material`
--

CREATE TABLE `tb_material` (
  `id_material` varchar(20) NOT NULL,
  `nm_material` varchar(50) NOT NULL,
  `id_vendor` varchar(20) NOT NULL,
  `qty` varchar(10) NOT NULL,
  `satuan` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_material`
--

INSERT INTO `tb_material` (`id_material`, `nm_material`, `id_vendor`, `qty`, `satuan`) VALUES
('AC-OF-SM-1B', 'KABEL OPTIK', 'PTSAJ', '28900', 'pcs'),
('CV0001', 'Converter FO', 'HW0001', '95', 'pcs'),
('FB0001', 'Fiber Optic', 'HW0001', '100', 'pcs'),
('OD180001', 'ODP-Solid-8-LKP', 'HW0001', '255', 'pcs');

-- --------------------------------------------------------

--
-- Table structure for table `tb_user`
--

CREATE TABLE `tb_user` (
  `username` varchar(50) NOT NULL,
  `password` text NOT NULL,
  `nama` varchar(50) NOT NULL,
  `level` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_user`
--

INSERT INTO `tb_user` (`username`, `password`, `nama`, `level`) VALUES
('admin', '1234', 'Ilham Muharif', 'admin'),
('commerce', '1234', 'Hasan Amin, MBA', 'commerce'),
('leader', '1234', 'Sutoro Waluyo', 'leader'),
('manager', '1234', 'Muhammad Sanusi, S.I, M.Kom', 'manager');

-- --------------------------------------------------------

--
-- Table structure for table `tb_vendor`
--

CREATE TABLE `tb_vendor` (
  `id_vendor` varchar(20) NOT NULL,
  `nm_vendor` varchar(50) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `no_telp` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_vendor`
--

INSERT INTO `tb_vendor` (`id_vendor`, `nm_vendor`, `alamat`, `no_telp`) VALUES
('HW0001', 'Huawei', 'Jakarta', '02166778899'),
('PTSAJ', 'PT SURYA AGUNG JAYA', 'KEBON JERUK', '02188334051');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pemakaian`
--
ALTER TABLE `pemakaian`
  ADD PRIMARY KEY (`id_pemakaian`);

--
-- Indexes for table `penerimaan`
--
ALTER TABLE `penerimaan`
  ADD PRIMARY KEY (`id_penerimaan`);

--
-- Indexes for table `pengeluaran`
--
ALTER TABLE `pengeluaran`
  ADD PRIMARY KEY (`id_pengeluaran`);

--
-- Indexes for table `permintaan`
--
ALTER TABLE `permintaan`
  ADD PRIMARY KEY (`id_permintaan`);

--
-- Indexes for table `rekon`
--
ALTER TABLE `rekon`
  ADD PRIMARY KEY (`id_pakai`);

--
-- Indexes for table `tb_material`
--
ALTER TABLE `tb_material`
  ADD PRIMARY KEY (`id_material`);

--
-- Indexes for table `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `tb_vendor`
--
ALTER TABLE `tb_vendor`
  ADD PRIMARY KEY (`id_vendor`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
