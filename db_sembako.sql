-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 29, 2022 at 08:33 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_sembako`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `kode_barang` varchar(20) NOT NULL,
  `nm_barang` varchar(50) NOT NULL,
  `ukuran` longtext NOT NULL,
  `harga_beli` int(10) NOT NULL,
  `harga_jual` int(15) NOT NULL,
  `stok` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`kode_barang`, `nm_barang`, `ukuran`, `harga_beli`, `harga_jual`, `stok`) VALUES
('BRG-0001', 'Beras', 'None', 10000, 12000, 4),
('BRG-0002', 'GG Filter 12', 'None', 20000, 22000, 9),
('BRG-0003', 'Indomie Goreng', 'None', 2500, 3000, 19),
('BRG-0004', 'Indomie Soto', 'None', 2500, 3000, 20),
('BRG-0005', 'Indomie Kari Ayam', 'None', 2500, 3000, 20),
('BRG-0006', 'Indomie Ayam Bawang', 'None', 2500, 3000, 20),
('BRG-0007', 'Indomie Rendang', 'None', 2500, 3000, 20),
('BRG-0008', 'Mie Sedap Goreng', 'None', 2500, 3000, 20),
('BRG-0009', 'Mie Sedap Soto', 'None', 2500, 3000, 20),
('BRG-0010', 'Aqua Botol 1500 ML', 'None', 4000, 5000, 18),
('BRG-0011', 'Aqua Botol 600 ML', 'None', 2000, 3000, 20),
('BRG-0012', 'Sampoerna Mild 16', 'Bks', 25000, 27000, 14);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `id_user` varchar(15) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `Nama` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id_user`, `username`, `password`, `Nama`) VALUES
('001', 'admin', 'admin', 'Pras');

-- --------------------------------------------------------

--
-- Table structure for table `suplier`
--

CREATE TABLE `suplier` (
  `id_suplier` varchar(25) NOT NULL,
  `nama_suplier` varchar(25) NOT NULL,
  `email_suplier` varchar(25) NOT NULL,
  `no_telpon` bigint(25) NOT NULL,
  `alamat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `suplier`
--

INSERT INTO `suplier` (`id_suplier`, `nama_suplier`, `email_suplier`, `no_telpon`, `alamat`) VALUES
('AS02', 'Agen Asep', 'asep01@gmail.com', 81368779815, 'Jalan jani nasir no 34'),
('AT01', 'Agen Ating', 'ating01@gmail.com', 81268779814, 'Jalan dewi sartika no 42'),
('JM01', 'Agen Jambul', 'agen_jambul@gmail.com', 81278999988, 'Jalan jambul jaya no 48');

-- --------------------------------------------------------

--
-- Table structure for table `tb_pembelian`
--

CREATE TABLE `tb_pembelian` (
  `no_transaksi` varchar(25) NOT NULL,
  `tgl` date NOT NULL,
  `suplier` varchar(25) NOT NULL,
  `kd_barang` varchar(25) NOT NULL,
  `nm_barang` varchar(25) NOT NULL,
  `ukuran` varchar(25) NOT NULL,
  `harga_beli` int(20) NOT NULL,
  `jumlah` int(20) NOT NULL,
  `subtot` int(20) NOT NULL,
  `total` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_pembelian`
--

INSERT INTO `tb_pembelian` (`no_transaksi`, `tgl`, `suplier`, `kd_barang`, `nm_barang`, `ukuran`, `harga_beli`, `jumlah`, `subtot`, `total`) VALUES
('T001', '2022-07-25', 'Agen Asep', 'BRG-0001', 'Beras', 'None', 10000, 2, 20000, 20000),
('T002', '2022-07-26', 'Agen Asep', 'BRG-0002', 'GG Filter 12', 'None', 20000, 10, 200000, 200000);

-- --------------------------------------------------------

--
-- Table structure for table `tb_penjualan`
--

CREATE TABLE `tb_penjualan` (
  `no_transaksi` varchar(25) NOT NULL,
  `tgl` date NOT NULL,
  `pembeli` varchar(25) NOT NULL,
  `kd_barang` varchar(25) NOT NULL,
  `nm_barang` varchar(25) NOT NULL,
  `ukuran` varchar(25) NOT NULL,
  `harga` int(20) NOT NULL,
  `jumlah` int(20) NOT NULL,
  `subtot` int(20) NOT NULL,
  `total` int(20) NOT NULL,
  `ubayar` int(20) NOT NULL,
  `ukembali` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_penjualan`
--

INSERT INTO `tb_penjualan` (`no_transaksi`, `tgl`, `pembeli`, `kd_barang`, `nm_barang`, `ukuran`, `harga`, `jumlah`, `subtot`, `total`, `ubayar`, `ukembali`) VALUES
('T001', '2022-07-25', 'Joko', 'BRG-0001', 'Beras', 'None', 12000, 1, 12000, 12000, 20000, 8000),
('T002', '2022-07-25', 'Pras', 'BRG-0002', 'GG Filter 12', 'None', 22000, 1, 22000, 22000, 23000, 1000),
('T003', '2022-07-25', 'Tito', 'BRG-0002', 'GG Filter 12', 'None', 22000, 1, 22000, 22000, 23000, 1000),
('T004', '2022-07-25', 'Rian', 'BRG-0002', 'GG Filter 12', 'None', 22000, 1, 22000, 22000, 22000, 0),
('T005', '2022-07-25', 'Eko', 'BRG-0001', 'Beras', 'None', 12000, 1, 12000, 34000, 35000, 1000),
('T006', '2022-07-25', 'Beno', 'BRG-0001', 'Beras', 'None', 12000, 1, 12000, 34000, 35000, 1000),
('T007', '2022-07-25', 'Toy', 'BRG-0001', 'Beras', 'None', 12000, 1, 12000, 34000, 35000, 1000),
('T008', '2022-07-25', 'roy', 'BRG-0001', 'Beras', 'None', 12000, 1, 12000, 34000, 35000, 1000),
('T009', '2022-07-25', 'Riri', 'BRG-0001', 'Beras', 'None', 12000, 1, 12000, 34000, 35000, 1000),
('T009', '2022-07-25', 'Riri', 'BRG-0002', 'GG Filter 12', 'None', 22000, 1, 22000, 34000, 35000, 1000),
('T010', '2022-07-27', 'Koko', 'BRG-0003', 'Indomie Goreng', 'None', 3000, 1, 3000, 74000, 75000, 1000),
('T010', '2022-07-27', 'Koko', 'BRG-0001', 'Beras', 'None', 12000, 1, 12000, 74000, 75000, 1000),
('T010', '2022-07-27', 'Koko', 'BRG-0002', 'GG Filter 12', 'None', 22000, 1, 22000, 74000, 75000, 1000),
('T010', '2022-07-27', 'Koko', 'BRG-0010', 'Aqua Botol 1500 ML', 'None', 5000, 2, 10000, 74000, 75000, 1000),
('T010', '2022-07-27', 'Koko', 'BRG-0012', 'Sampoerna Mild 16', 'Bks', 27000, 1, 27000, 74000, 75000, 1000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kode_barang`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id_user`);

--
-- Indexes for table `suplier`
--
ALTER TABLE `suplier`
  ADD PRIMARY KEY (`id_suplier`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
