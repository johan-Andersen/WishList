CREATE DATABASE IF NOT EXISTS WishListTest;

USE WishListTest;

CREATE TABLE IF NOT EXISTS UsersTest (
    userID INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(30) NOT NULL
    );

CREATE TABLE IF NOT EXISTS WishListsTest (
    wishlistID INT AUTO_INCREMENT PRIMARY KEY,
    userID INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    FOREIGN KEY (userID) REFERENCES UsersTest(userID) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS WishesTest (
    wishID INT AUTO_INCREMENT PRIMARY KEY,
    wishlistID INT NOT NULL,
    name VARCHAR(30),
    description TEXT NOT NULL,
    price DOUBLE NOT NULL,
    link VARCHAR(1000),
    FOREIGN KEY (wishlistID) REFERENCES WishlistsTest(wishlistID) ON DELETE CASCADE
    );

-- User 1
INSERT INTO UsersTest (username, email, password)
VALUES ('johan', 'johan@gmail.com', '123');
INSERT INTO WishListsTest (userID, name)
VALUES ('1', 'christmas');
INSERT INTO WishesTest (wishlistID, name, description, price, link)
VALUES ('1', 'baseballbat', 'big bat', '20.99', 'www.bat.com');

-- User 2
INSERT INTO UsersTest (username, email, password)
VALUES ('maria', 'maria@gmail.com', '1234');
INSERT INTO WishListsTest (userID, name)
VALUES (2, 'birthday');
INSERT INTO WishesTest (wishlistID, name, description, price, link)
VALUES (2, 'camera', 'nice camera', 250.00, 'www.camera.com');

-- User 3
INSERT INTO UsersTest (username, email, password)
VALUES ('lucas', 'lucas@gmail.com', 'pass');
INSERT INTO WishListsTest (userID, name)
VALUES (3, 'summer');
INSERT INTO WishesTest (wishlistID, name, description, price, link)
VALUES (3, 'sunglasses', 'polarized shades', 50.00, 'www.shades.com');

-- User 4
INSERT INTO UsersTest (username, email, password)
VALUES ('emma', 'emma@gmail.com', '321');
INSERT INTO WishListsTest (userID, name)
VALUES (4, 'wedding');
INSERT INTO WishesTest (wishlistID, name, description, price, link)
VALUES (4, 'blender', 'kitchen blender', 89.99, 'www.blender.com');

-- User 5
INSERT INTO UsersTest (username, email, password)
VALUES ('oliver', 'oliver@gmail.com', 'abc');
INSERT INTO WishListsTest (userID, name)
VALUES (5, 'new apartment');
INSERT INTO WishesTest (wishlistID, name, description, price, link)
VALUES (5, 'sofa', 'comfortable sofa', 499.99, 'www.sofa.com');

-- User 6
INSERT INTO UsersTest (username, email, password)
VALUES ('sofia', 'sofia@gmail.com', 'pass123');
INSERT INTO WishListsTest (userID, name)
VALUES (6, 'baby shower');
INSERT INTO WishesTest (wishlistID, name, description, price, link)
VALUES (6, 'baby clothes', 'set of clothes', 35.00, 'www.baby.com');

-- User 7
INSERT INTO UsersTest (username, email, password)
VALUES ('liam', 'liam@gmail.com', 'xyz');
INSERT INTO WishListsTest (userID, name)
VALUES (7, 'sports gear');
INSERT INTO WishesTest (wishlistID, name, description, price, link)
VALUES (7, 'football', 'standard size', 30.00, 'www.football.com');

-- User 8
INSERT INTO UsersTest (username, email, password)
VALUES ('nora', 'nora@gmail.com', 'pwd123');
INSERT INTO WishListsTest (userID, name)
VALUES (8, 'tech');
INSERT INTO WishesTest (wishlistID, name, description, price, link)
VALUES (8, 'headphones', 'noise-cancelling', 120.00, 'www.headphones.com');

-- User 9
INSERT INTO UsersTest (username, email, password)
VALUES ('felix', 'felix@gmail.com', 'hello');
INSERT INTO WishListsTest (userID, name)
VALUES (9, 'gaming');
INSERT INTO WishesTest (wishlistID, name, description, price, link)
VALUES (9, 'controller', 'xbox style', 60.00, 'www.controller.com');

-- User 10
INSERT INTO UsersTest (username, email, password)
VALUES ('mia', 'mia@gmail.com', 'qwerty');
INSERT INTO WishListsTest (userID, name)
VALUES (10, 'travel');
INSERT INTO WishesTest (wishlistID, name, description, price, link)
VALUES (10, 'backpack', 'waterproof travel bag', 80.00, 'www.backpack.com');

-- User 11
INSERT INTO UsersTest (username, email, password)
VALUES ('noah', 'noah@gmail.com', 'noahpass');
INSERT INTO WishListsTest (userID, name)
VALUES (11, 'music');
INSERT INTO WishesTest (wishlistID, name, description, price, link)
VALUES (11, 'record player', 'vinyl player', 150.00, 'www.vinyl.com');