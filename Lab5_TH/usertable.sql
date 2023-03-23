
--
-- Database: `manager`
--

-- --------------------------------------------------------

--
-- Table structure for table `usertable`
--

CREATE TABLE `usertable` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `pwd` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
)

INSERT INTO `usertable` (`ID`, `username`, `email`, `pwd`) VALUES (NULL, 'admin', 'admin@app.com', 'admin');


CREATE TABLE `product` (
   `ID` int(11) NOT NULL AUTO_INCREMENT,
   `name` varchar(50) NOT NULL,
   `price` float NOT NULL,
   PRIMARY KEY (ID)
);
INSERT INTO `product` (`ID`, `name`, `price`) VALUES (NULL, 'Iphone 7', '42323');