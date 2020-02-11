
-- Passport



-- (TODO: add passports instead of NULL)
INSERT INTO Users (Id, Email, Password_hash, Role, Name, Birth_date, Signup_date, Pass_id, Contact_phone, Address, Is_blocked, Reputation) VALUES
( 1, 'denis.n@notmail.mock', 'aaFF343GHj2434', 'client', 'Денис Ноунейм', '1975.04.21', '11.02.2020', NULL, '+7(831)1998708', 'Москва, улица Ленина 41', False, 1.0 ),
( 2, 'raymond.johnson@notmail.mock', 'eWah9993dfj', 'client', 'Реймнод Джонсон', '1991.01.01', '11.02.2020', NULL, '+7 999 9999999', 'Рязань, Улица Дзержинского 8', False, 1.0 ),
( 3, 'admin@admin', 'admin_pass_hash', 'admin', 'Администратор Антон', '1951.03.07', '10.02.2020', NULL, '+7(4812)276503', 'Город администраторов, ул. Управления 94', False, 4.3 ),
( 4, 'coolhacker123@notmail.mock', 'fgdfg343r2a', 'client', 'Хакер Вася', '2004.01.01', '11.02.2020', NULL, '+7777777777', 'Не скажу', True, 0.1 ),
( 5, 'petya@notmail.mock', 'dfsad12323', 'client', 'Петя Петров', '1997.04.04', '10.02.2020', NULL, '+73952899129', 'Санкт-Петербург, Кронверкский пр. 49', False, 1.0 ),
( 6, 'galya888@notmail.mock', '1111dfsdJJFkk', 'client', 'Галина Орешкина', '1968.04.02', '11.02.2020', NULL, '+7(343)6985090', 'Санкт-Петербург', False, 1.0 );

-- Car_model
-- Car

-- TODO: admin and client should be different people
INSERT INTO Orders (Id, Car_id, Client_id, Admin_id, Status, Rent_start_date,Rent_end_date, Discount) VALUES
( 1, 1, 1, 1, 'open','2020.02.10', '2020.02.15', 28),
( 2, 2, 2, 2, 'open','2020.02.10', '2020.02.15', 36),
( 3, 3, 3, 3, 'canceled','2020.02.10', '2020.02.15', 61),
( 4, 4, 4, 4, 'open','2020.02.10', '2020.02.15', 79),
( 5, 5, 5, 5, 'approved','2020.02.10', '2020.02.15', 59),
( 6, 6, 6, 6, 'canceled','2020.02.10', '2020.02.15', 23),
( 7, 7, 7, 7, 'approved','2020.02.10', '2020.02.15', 63),
( 8, 8, 8, 8, 'waiting for payment','2020.02.10', '2020.02.15', 37),
( 9, 9, 9, 9, 'close','2020.02.10', '2020.02.15', 79),
( 10, 10, 10, 10, 'approved','2020.02.10', '2020.02.15', 27);

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

-- Car_damage