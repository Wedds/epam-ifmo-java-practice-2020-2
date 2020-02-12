BEGIN;

INSERT INTO Passport ( Id, Issue_country, Issuer, Issue_date, Expiration_date, Serial_number) VALUES
(1, 'Russia', 'Отделением УФМС России по г.Москва по р-ну Камовники', '10.05.2000', '21.04.2020', '1234 568954'),
(2, 'Russia', 'Отделом УФМС России по Рязанской области и г.Рязань', '04.03.2011', '01.01.2036', '3508 569842'),
(3, 'Russia', 'Отделом УФМС России по г.Администраторов', '8.04.1996', NULL, '6842 418965'),
(4, 'Russia', '', '7.02.2018', '01.01.2024', '4912 734129'),
(5, 'Russia', 'Отделом УФМС Росии по Санкт-Петербургу и Ленинградской области', '26.05.2017', '04.04.2042', '9845 789654'),
(6, 'Russia', 'Отделом УФМС Росии по Санкт-Петербургу и Ленинградской области', '16.05.2013', NULL, '6614 745689');

INSERT INTO Driving_license (Id, Issue_date, Expiration_date, Serial_number) VALUES
(1,'26.02.2010', '26.02.2020', '55 55 789456'),
(2,'8.04.2015', '8.04.2025', '84 55 498215'),
(3,'15.12.2019', '15.12.2029', '11 12 897664'),
(4,'26.11.1995', '26.11.2005', '11 11 428935');

INSERT INTO Users (Id, Email, Password_hash, Role, Name, Birth_date, Signup_date, Pass_id, Driving_license_id, Contact_phone, Address, Is_blocked, Reputation) VALUES
( 1, 'denis.n@notmail.mock', 'aaFF343GHj2434', 'client', 'Денис Ноунейм', '1975.04.21', '11.02.2020', 1, 1, '+7(831)1998708', 'Москва, улица Ленина 41', False, 1.0 ),
( 2, 'raymond.johnson@notmail.mock', 'eWah9993dfj', 'client', 'Реймнод Джонсон', '1991.01.01', '11.02.2020', 2, 2, '+7 999 9999999', 'Рязань, Улица Дзержинского 8', False, 1.0 ),
( 3, 'admin@admin', 'admin_pass_hash', 'admin', 'Администратор Антон', '1951.03.07', '10.02.2020', 3, NULL, '+7(4812)276503', 'Город администраторов, ул. Управления 94', False, 4.3 ),
( 4, 'coolhacker123@notmail.mock', 'fgdfg343r2a', 'client', 'Хакер Вася', '2004.01.01', '11.02.2020', 4, NULL, '+7777777777', 'Не скажу', True, 0.1 ),
( 5, 'petya@notmail.mock', 'dfsad12323', 'client', 'Петя Петров', '1997.04.04', '10.02.2020', 5, 3, '+73952899129', 'Санкт-Петербург, Кронверкский пр. 49', False, 1.0 ),
( 6, 'galya888@notmail.mock', '1111dfsdJJFkk', 'client', 'Галина Орешкина', '1968.04.02', '11.02.2020', 6, 4, '+7(343)6985090', 'Санкт-Петербург', False, 1.0 );

INSERT INTO Car_model (Id, Brand_name, Model_name, Price_per_hour) VALUES
( 0, 'Toyota','Camry', 10),
( 1, 'Hyundai','Sonata', 7),
( 2, 'Kia','Rio', 6),
( 3, 'Volkswagen','Polo', 8),
( 4, 'Audi','A3', 8);

INSERT INTO Car (Id, Model_id, Color, REG_number, VIN_number, Status) VALUES
( 0, 0,'white','A001AD|78', '1JJV502W42L760952', 'taken'),
( 1, 0,'white','A002QW|78', '2G4GN5EX0E9193519', 'free'),
( 2, 0,'black','G003EW|98', 'KNAFE161675007871', 'taken'),
( 3, 0,'white','D004AD|78', '2GCEK19R5T1120022', 'broken'),
( 4, 1,'black','F005AF|98', '5J6RE3H38BL040055', 'free'),
( 5, 1,'white','L006DD|78', 'JN8AS5MV5AW610187', 'taken'),
( 6, 1,'white','S007JJ|78', 'KL5VJ56L05B161211', 'free'),
( 7, 1,'black','A008IO|78', '1C3CCCFBXFN560817', 'taken'),
( 8, 1,'white','K009GH|98', 'JNKDA31A83T114000', 'free'),
( 9, 1,'black','A010FG|78', 'WDBUF56X59B412027', 'taken'),
( 10, 1,'white','H011KJ|78', '2B3KA43G58H285583', 'free'),
( 11, 1,'black','A012DF|78', '1FTFW1R66DFD03194', 'broken'),
( 12, 2,'white','S013BV|98', '1FTFW1EF1EKE72974', 'taken'),
( 13, 2,'white','C014TR|78', '1GYEE637390129781', 'free'),
( 14, 2,'white','E015IO|78', '1FTPX12V47FB57417', 'taken'),
( 15, 2,'black','Q016PO|98', '1GAHG39U641105091', 'broken'),
( 16, 2,'white','R017DF|78', '5TDDK3EH3CS110621', 'taken'),
( 17, 2,'white','Y018QW|78', '5N1ED28TX2C575580', 'free'),
( 18, 2,'black','A019SD|98', '1GC1KVEG5FF106581', 'taken'),
( 19, 2,'white','I020BG|78', '1GC2KWE87FZ119379', 'broken'),
( 20, 2,'white','P021TG|98', '5TFRM5F18FX082827', 'free'),
( 21, 2,'black','K022DC|78', '1FMCU9E75BKC43320', 'taken'),
( 22, 2,'black','J023MV|98', '1GCPKSE77CF169086', 'taken'),
( 23, 3,'white','L024KM|78', '19UUB2F37FA009016', 'free'),
( 24, 3,'white','F025XD|78', '1GCHK23295F952831', 'taken'),
( 25, 3,'black','C026CI|98', 'JM3ER2AM9B0365439', 'broken'),
( 26, 3,'white','V027BR|78', '1VWAS7A34FC009255', 'taken'),
( 27, 3,'black','D028XR|78', '1G1JF524827222787', 'free'),
( 28, 3,'white','A029IR|98', '2T1BU4EE8AC256709', 'taken'),
( 29, 3,'black','X030DH|78', 'YS3EH55G723049272', 'taken'),
( 30, 3,'white','Z031BY|78', '19XFB2F83FE008204', 'free'),
( 31, 3,'white','M032XT|98', '5XYKWDA28DG369347', 'broken'),
( 32, 3,'white','N033KF|98', '1J4GL58K32W302921', 'free'),
( 33, 3,'black','J034BE|78', '1GNEC13V92R298189', 'taken'),
( 34, 4,'black','E035ZH|98', '5LMFU28569LJ01784', 'free'),
( 35, 4,'white','H036KS|78', 'JTJGA31U140020162', 'taken'),
( 36, 4,'black','U037CU|98', '1FTFW1CF3EKF26195', 'taken');


INSERT INTO Orders (Id, Car_id, Client_id, Admin_id, Status, Rent_start_date,Rent_end_date, Discount) VALUES
( 1, 1, 1, 3, 'open','2020.02.10', '2020.02.15', 28),
( 2, 2, 2, 3, 'open','2020.02.10', '2020.02.15', 36),
( 3, 3, 3, 3, 'canceled','2020.02.10', '2020.02.15', 61),
( 4, 4, 5, 3, 'open','2020.02.10', '2020.02.15', 79),
( 5, 5, 5, 3, 'approved','2020.02.10', '2020.02.15', 59),
( 6, 6, 6, 3, 'canceled','2020.02.10', '2020.02.15', 23),
( 7, 7, 1, 3, 'approved','2020.02.10', '2020.02.15', 63),
( 8, 8, 2, 3, 'waiting for payment','2020.02.10', '2020.02.15', 37),
( 9, 9, 3, 3, 'close','2020.02.10', '2020.02.15', 79),
( 10, 10, 5, 3, 'approved','2020.02.10', '2020.02.15', 27);

INSERT INTO Invoice (Id, Order_id, Status, Issue_date,Payment_date,Total_price) VALUES
( 1, 1,'close','2020.02.13', '2020.02.14', 2349),
( 2, 2,'close','2020.02.13', '2020.02.14', 4214),
( 3, 3,'close','2020.02.13', '2020.02.14', 7733),
( 4, 4,'open','2020.02.13', '2020.02.14', 1222),
( 5, 5,'close','2020.02.13', '2020.02.14', 9372),
( 6, 6,'close','2020.02.13', '2020.02.14', 3420),
( 7, 7,'close','2020.02.13', '2020.02.14', 2246),
( 8, 8,'close','2020.02.13', '2020.02.14', 6440),
( 9, 9,'open','2020.02.13', '2020.02.14', 7290),
( 10, 10,'open','2020.02.13', '2020.02.14', 4215);

INSERT INTO Car_damage (Id, Order_id, Description, Accident_date, Severity, Repair_cost, Status) VALUES
(1, 1, 'вмятина на двери','2020.02.14', 4, 5600, 'fixed'),
(2, 2, 'сбил косулю, много повреждений','2020.02.12', 9, 250899, 'under repair'),
(3, 4, 'птица погнула дворник, замена дворника','2020.02.15', 1, 400, 'fixed'),
(4, 5, 'прокол колеса','2020.02.14', 1, 800, 'fixed'),
(5, 7, 'потерял коврик под водительским креслом','2020.02.14', 2, 1000, 'not fixed');

COMMIT;