-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 30, 2017 at 02:43 PM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gamestore_fx_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `CustomerID` int(11) NOT NULL,
  `UserName` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL,
  `CCNumber` varchar(16) DEFAULT NULL,
  `Balance` double DEFAULT '50'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`CustomerID`, `UserName`, `Email`, `Password`, `CCNumber`, `Balance`) VALUES
(1, 'Chris', 'lxristos@hotmail.com', '123456', '1596384569125478', 15),
(2, 'frank32', 'frank@gmail.com', 'lala', '1234567891234567', 43);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `OrderID` int(11) NOT NULL,
  `Quantity` tinyint(4) DEFAULT '1',
  `ProductID` int(11) DEFAULT NULL,
  `CustomerID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`OrderID`, `Quantity`, `ProductID`, `CustomerID`) VALUES
(1, 1, 11, 1),
(2, 1, 9, 1),
(3, 1, 3, 1),
(4, 1, 4, 1),
(5, 1, 10, 2),
(6, 1, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `ProductID` int(11) NOT NULL,
  `Title` varchar(45) DEFAULT NULL,
  `Price` double(5,2) DEFAULT NULL,
  `Description` text,
  `Video` varchar(45) DEFAULT NULL,
  `Platform` enum('Windows','Linux') DEFAULT NULL,
  `Cover` varchar(55) DEFAULT NULL,
  `Image1` varchar(55) DEFAULT NULL,
  `Image2` varchar(55) DEFAULT NULL,
  `Image3` varchar(55) DEFAULT NULL,
  `Image4` varchar(55) DEFAULT NULL,
  `ExeFile` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`ProductID`, `Title`, `Price`, `Description`, `Video`, `Platform`, `Cover`, `Image1`, `Image2`, `Image3`, `Image4`, `ExeFile`) VALUES
(1, 'Zombie Apocalypse', 7.00, 'After a technological disaster, most of the world''s population is turned into zombies. The cities are in ruins, but a small percentage of people survived, and are now hiding in shelters. You are one of the survivors. Your task is to protect your shelter from the crowds of zombies and bloodthirsty mutants. You have a wide arsenal of weapons and upgrades at your disposal. You can also improve barricades at the shelter entrance, or create machine-gun towers that are very efficient against crowds of enemies. Game features: 45 levels, 4 types of weapons and upgrades, 4 types of upgrades for your shelter, 7 types of enemies. Atmospheric music and sounds effects.', 'https://www.youtube.com/watch?v=jKTWSDOydww', 'Windows', '/resources/images/covers/zombie-apocalypse-cover.png', '/resources/images/zombie-apocalypse-1.jpg', '/resources/images/zombie-apocalypse-2.jpg', '/resources/images/zombie-apocalypse-3.jpg', '/resources/images/zombie-apocalypse-4.jpg', 'http://www.gametop.com/download-free-games/zombie-apocalypse/download.html'),
(2, 'City Racing', 5.00, 'City Racing is a hugely popular sandbox GTA style car racing game. This game gives you the freedom to explore the city in any way you want. Your can participate in illegal street races, win money, make spectacular jumping stunts, upgrade your car to beat your racing opponents or you can just work as a taxi driver. The gameplay is easy, you just need quick reflexes and a good eye to avoid the police cars and make some money. Download this full version racing game now and try to survive in the big city.', 'https://www.youtube.com/watch?v=3yFZk5SxFgg', 'Windows', '/resources/images/covers/city-racing-cover.jpg', '/resources/images/city-racing-1.jpg', '/resources/images/city-racing-2.jpg', '/resources/images/city-racing-3.jpg', '/resources/images/city-racing-4.jpg', 'http://www.gametop.com/download-free-games/city-racing/download.html'),
(3, 'Alien Shooter', 6.00, 'The endless darkness and the somber, long passages of a military complex have become the abode of evil, as thousands of blood-thirsty creatures fill its offices, storehouses and mysterious laboratories. Your mission is simple: clear the base at all costs. You will be provided with explosives to help you gain access to the teleports from where thousands of pitiless creatures pour. A stationary gun will aid in the defense of the area. You have been granted access to the most advanced weapons technology money can buy. As you earn your pay, you can equip yourself with additional weapons in the arming area and biomechanical implants that will make your fighting abilities super human. The alien invasion has begun, we have one chance, and that is to stop them cold in their staging area. Do not allow them escape this facility, you are our last hope. The fate of humanity now depends on you! Download free game today and dive into the middle of a war in Alien Shooter, a fast-paced action game.', 'https://www.youtube.com/watch?v=vR9706AMq8M', 'Windows', '/resources/images/covers/alien-shooter-cover.jpg', '/resources/images/alien-shooter-1.jpg', '/resources/images/alien-shooter-2.jpg', '/resources/images/alien-shooter-3.jpg', '/resources/images/alien-shooter-4.jpg', 'http://www.gametop.com/download-free-games/alien-shooter/download.html'),
(4, 'Crazy Cars', 4.00, 'Rediscover the sheer thrill of arcade gaming with this amazing racing game, taking bends at max speed or massive jumps that can go hundreds of meters high. You''ll be right in the middle of all the action, racing some of the best rides out there. Criss-cross different states of the USA at over 180 mph! Just make sure the cops don''t catch you.', 'https://www.youtube.com/watch?v=tmHm_sULlzk', 'Windows', '/resources/images/covers/crazy-cars-cover.jpg', '/resources/images/crazy-cars-1.jpg', '/resources/images/crazy-cars-2.jpg', '/resources/images/crazy-cars-3.jpg', '/resources/images/crazy-cars-4.jpg', 'http://www.gametop.com/download-free-games/crazy-cars/download.html'),
(5, 'Desert Hawk', 8.00, 'The centerpiece of this free game is a new terrorist base, well defended and cleverly designed to make any head-on attack pointless. This time around, the terrorists created a new monstrous weapon and a new type of explosives. Naturally, the only way to stop the destruction is to annihilate the opponents while they are on the move. After this is done, the player has to carefully plan the assault on the core base, which is defended by the towers and is fortified with a new type of metal alloy. With amazing graphics, a stirring soundtrack, and some of the most exciting action we''ve ever offered, this free full verion downloadable pc game is a bold new mission for arcade lovers.', 'https://www.youtube.com/watch?v=czJmb6QDiik', 'Windows', '/resources/images/covers/desert-hawk-cover.jpg', '/resources/images/desert-hawk-1.jpg', '/resources/images/desert-hawk-2.jpg', '/resources/images/desert-hawk-3.jpg', '/resources/images/desert-hawk-4.jpg', 'http://www.gametop.com/download-free-games/desert-hawk/download.html'),
(6, 'Fall Of The New Age', 12.00, 'Help Marla to uncover the dark secrets of a medieval cult in Fall of the New Age. The cult is planning to control all citizens and destroy all knowledge and culture. An original medieval world to explore with more than 40 amazingly detailed locations. Dozens of brain-twisting minigames and challenges. Beautiful animations in 3D graphics. Fall of the New Age is a fantastic adventure that will have you hooked right from the beginning.', 'https://www.youtube.com/watch?v=5pLNDqbMawg', 'Windows', '/resources/images/covers/fall-of-the-new-age-cover.jpg', '/resources/images/fall-of-the-new-age-1.jpg', '/resources/images/fall-of-the-new-age-2.jpg', '/resources/images/fall-of-the-new-age-3.jpg', '/resources/images/fall-of-the-new-age-4.jpg', 'http://www.gametop.com/download-free-games/fall-of-the-new-age/download.html'),
(7, 'Football World', 3.50, 'Football World is an excellent football simulator. Play for more than 70 countries. Choose your team and win the World Cup. Modern 3D graphics, realistic sounds and challenging AI.', 'https://www.youtube.com/watch?v=v4FeUs81MLU', 'Windows', '/resources/images/covers/football-world-cover.jpg', '/resources/images/football-world-1.jpg', '/resources/images/football-world-2.jpg', '/resources/images/football-world-3.jpg', '/resources/images/football-world-4.jpg', 'http://www.gametop.com/download-free-games/football-world/download.html'),
(8, 'Frontline Tactics', 9.00, 'A highly addictive military tactics game with cross platform multiplayer over a variety of desktop and mobile releases. Frontline Tactics lets you command a modern, elite fighting unit over various missions, from defending and controlling a location or asset to all-out elimination and survival. Equip your soldiers with modern weapons, armor and equipment and give them the skills to succeed on the battlefield. ', 'https://www.youtube.com/watch?v=vHqXjzFgm-A', 'Windows', '/resources/images/covers/frontline-tactics-cover.jpg', '/resources/images/frontline-tactics-1.jpg', '/resources/images/frontline-tactics-2.jpg', '/resources/images/frontline-tactics-3.jpg', '/resources/images/frontline-tactics-4.jpg', 'http://www.gametop.com/download-free-games/frontline-tactics/download.html'),
(9, 'Invention', 11.00, 'Invention is a 3D first-person shooter with RPG elements. Actions in the game take place on a mysterious island within a secret laboratory. In this laboratory, once working people were trying to give others super-abilities. However, they could not have imagined where these experiments led. The hero gets there as a result of a crash. In search of help, he finds the lab. Not knowing what was inside, he goes down in there...', 'https://www.youtube.com/watch?v=tmhZy3JJV-M', 'Windows', '/resources/images/covers/invention-cover.jpg', '/resources/images/invention-1.jpg', '/resources/images/invention-2.jpg', '/resources/images/invention-3.jpg', '/resources/images/invention-4.jpg', 'http://www.gametop.com/download-free-games/invention/download.html'),
(10, 'Super Bikes', 7.00, 'Take part in a greatest moto racing competition in this 3D superbike racing game. Practice in the Single Race mode or compete with your best result in Time Attack mode. Perform stunts to earn points and to get in time to the finish. Download full version game today and take part in a greatest moto racing competition. Game Features: - Real superbike racing game; - Modern 3D graphics; - Extraordinarily addictive game play; - Original soundtrack and powerful sound effects; - Save / Load game option; - Game statistics.', 'https://www.youtube.com/watch?v=oALZsm7XYho', 'Windows', '/resources/images/covers/super-bikes-cover.jpg', '/resources/images/super-bikes-1.jpg', '/resources/images/super-bikes-2.jpg', '/resources/images/super-bikes-3.jpg', '/resources/images/super-bikes-4.jpg', 'http://www.gametop.com/download-free-games/superbikes/download.html'),
(11, 'StarCraft', 9.00, 'A science fiction real-time strategy game, StarCraft is set in a distant sector of the Milky Way galaxy. The game''s story revolves around the appearance of two alien races in Terran space, and each race''s attempts to survive and adapt over the others. The player assumes three roles through the course of the three campaigns: a Confederate colonial governor who becomes a revolutionary commander, a Zerg cerebrate pushing forward the species'' doctrine of assimilation, and a Protoss fleet executor tasked with defending the Protoss from the Zerg. StarCraft soon gained critical acclaim, winning numerous awards, including being labelled "the best real-time strategy game ever made".', 'https://www.youtube.com/watch?v=F-7CuVmis5w', 'Windows', '/resources/images/covers/starcraft-cover.jpg', '/resources/images/starcraft-1.jpg', '/resources/images/starcraft-2.jpg', '/resources/images/starcraft-3.jpg', '/resources/images/starcraft-4.jpg', 'https://battle.net/download/getInstallerForGame?os=WIN&version=LIVE&gameProgram=STARCRAFT'),
(12, 'Alien Arena', 5.50, 'Do you like old school deathmatch with modern features? How about rich, colorful, arcadelike atmospheres? How about...retro Sci Fi? Then you''re going to love what Alien Arena has in store for you! This game combines some of the very best aspects of such games as Quake III and Unreal Tournament and wraps them up with a retro alien theme, while adding tons of original ideas to make the game quite unique. Alien Arena is a furious frag fest with arenas ranging from the small, to the massive. With a large built-in player base, it''s never hard to find a good match going on, at any hour of the day. The community is friendly, as well as prolific. Dozens of maps, models, and various accessories have been created by community members to add on to the game experience. ', 'https://www.youtube.com/watch?v=YNvep_oWFbs', 'Linux', '/resources/images/covers/alien-arena-cover.jpg', '/resources/images/alien-arena-1.jpg', '/resources/images/alien-arena-2.jpg', '/resources/images/alien-arena-3.jpg', '/resources/images/alien-arena-4.jpg', 'http://localhost/games/alienarena-7.66-linux.tar.gz'),
(13, 'Smokin'' Guns', 8.50, 'Smokin'' Guns (SG) is a first-person shooter video game. Smokin'' Guns is intended to be a semi-realistic simulation of the "Old West''s" atmosphere and was developed on id Software''s Quake III Arena Engine. Gameplay as well as locations are inspired by Western movies, particularly from the Italowestern respectively Spaghetti Western genre. Here''s a list of some of the most important features of Smokin'' Guns:\n- A full arsenal of weapons with historically correct design. Check the weapons page for more information.\n- A variety of western styled maps and player models.\n- A realistic damage system with different locations (head, chest, neck, etc) and height-dependant falling damage.\n- New western styled gametypes for more fun: Bank Robbery and Duel Modes.\n- A money system allowing for equipment purchase with money from rewards & pickups.\n- Easy to use graphical user interface and HUD.\n- Other small improvements for better gameplay and enhanced fun.', 'https://www.youtube.com/watch?v=RGIYZf-BBfA', 'Linux', '/resources/images/covers/smokin-guns-cover.jpg', '/resources/images/smokin-guns-1.jpg', '/resources/images/smokin-guns-2.jpg', '/resources/images/smokin-guns-3.jpg', '/resources/images/smokin-guns-4.jpg', 'https://www.smokin-guns.org/downloads/Smokin_Guns_1.1.zip');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`CustomerID`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`OrderID`),
  ADD KEY `ProductID` (`ProductID`),
  ADD KEY `CustomerID` (`CustomerID`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`ProductID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `CustomerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `OrderID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `ProductID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`),
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`CustomerID`) REFERENCES `customers` (`CustomerID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
