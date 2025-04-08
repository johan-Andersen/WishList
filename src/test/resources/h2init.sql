/*DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS WishLists;
DROP TABLE IF EXISTS Wishes;
*/
CREATE TABLE Users (
    userID INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(30) NOT NULL
    );

CREATE TABLE WishLists (
    wishlistID INT AUTO_INCREMENT PRIMARY KEY,
    userID INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    FOREIGN KEY (userID) REFERENCES Users(userID) ON DELETE CASCADE
    );

CREATE TABLE Wishes (
    wishID INT AUTO_INCREMENT PRIMARY KEY,
    wishlistID INT NOT NULL,
    name VARCHAR(30),
    description TEXT NOT NULL,
    price INT NOT NULL,
    link VARCHAR(1000),
    FOREIGN KEY (wishlistID) REFERENCES WishLists(wishlistID) ON DELETE CASCADE
    );


INSERT INTO Users (username, email, password) VALUES
('Gustav', 'gustav@gmail.com', 'gustavpassword'),
('johan', 'johan@gmail.com', 'johanpassword');


INSERT INTO WishLists (userID, name) VALUES
(1, 'GustavOenskeseddel'),
(1, 'GustavJulegaver'),
(2, 'johanOenskeseddek'),
(2, 'JohanJulegaver');


INSERT INTO Wishes (wishlistID, name, description, price, link) VALUES
(1, 'Nike sneakers', 'I sort str. 45', 799, 'https://www.nike.com/'),
(2, 'Adidas sneakers','I grøn str. 45',460,'https://www.adidas.dk/'),
(3, 'Sko fra naked','I grøn str. 50',750,'https://www.naked.dk/'),
(4, 'Donald trump hat','I rød onesize',250,'https://www.trump.com/');
