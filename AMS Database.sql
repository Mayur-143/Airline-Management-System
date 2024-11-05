create database AMS;
use AMS;

CREATE TABLE AIRPLANE (
    airplane_id INT PRIMARY KEY auto_increment,          -- Primary key for aircraft
    registration_no VARCHAR(20) UNIQUE not null,   -- Unique registration number of the aircraft
	description varchar(500),
    model VARCHAR(50),                    -- Model of the aircraft (e.g., A320, 737 Max)
    total_seats int not null,
    total_economy_seats int not null,
    total_business_seats int not null,
    total_first_class_seats int not null 
);

CREATE TABLE AIRPORT (
    airport_id INT PRIMARY KEY auto_increment,           -- Unique identifier for each airport
	airport_name VARCHAR(100) NOT NULL,   -- Name of the airport
    IATA_code CHAR(3) UNIQUE,             -- IATA code (3-character airport code)
    location VARCHAR(50) NOT NULL,            -- City where the airport is located
    address varchar(200)

);

CREATE TABLE FLIGHTS (
    flight_id INT PRIMARY KEY AUTO_INCREMENT,  -- Unique identifier for each flight
    flight_number VARCHAR(10) NOT NULL,        -- Unique flight number
    departure_time DATETIME NOT NULL,          -- Scheduled departure time
    arrival_time DATETIME NOT NULL,            -- Scheduled arrival time
    flight_status VARCHAR(20) DEFAULT 'Scheduled',    -- Status of the flight (Scheduled, Delayed, Cancelled, etc.)
    economy_seat_fare int not null,
    business_seat_fare int not null,
    first_class_seat_fare int not null,
    airport_id int,
    airplane_id INT,
    FOREIGN KEY (airport_id) REFERENCES AIRPORT(airport_id) ON DELETE CASCADE,
    FOREIGN KEY (airplane_id) REFERENCES AIRPLANE(airplane_id) ON DELETE CASCADE
);




CREATE TABLE USERS (
    user_id INT PRIMARY KEY auto_increment,              -- Primary key for users
    first_name VARCHAR(50) not null,               -- First name of the user
    middle_name VARCHAR(50),              -- Middle name of the user (optional)
    last_name VARCHAR(50),                -- Last name of the user
    password VARCHAR(255) not null,                -- Password of the user (should be hashed in practice)
    date_of_birth date not null,
    street VARCHAR(100),          -- Street address of the user
    city VARCHAR(50),                     -- City of the user
    state VARCHAR(50),                    -- State of the user
    country VARCHAR(50),                  -- Country of the user
    pin_code VARCHAR(10),                  -- pin code of the user
    email VARCHAR(50) unique not null
);

CREATE TABLE BOOKING (
    booking_id INT PRIMARY KEY auto_increment,            -- Primary key for the booking
    booking_date datetime,                     -- Date when the booking was made
    total_passenger INT,                   -- Seat number assigned for the booking
    status VARCHAR(20),                    -- Status of the booking (e.g., confirmed, canceled)
    user_id INT,                           -- Foreign key referencing USER table
    flight_id INT,                         -- Foreign key referencing FLIGHT table
    FOREIGN KEY (user_id) REFERENCES USERS(user_id) ON DELETE CASCADE,
    FOREIGN KEY (flight_id) REFERENCES FLIGHTS(flight_id) ON DELETE CASCADE
);

CREATE TABLE PASSENGER (
    passenger_id INT PRIMARY KEY auto_increment,          -- Primary key for passenger
    first_name VARCHAR(50) not null,                -- First name of the passenger
    middle_name VARCHAR(50),               -- Middle name of the passenger (optional)
    last_name VARCHAR(50),                 -- Last name of the passenger
    gender VARCHAR(10) not null,                    -- Gender of the passenger
	age INT not null,
    aadhar_number INT unique not null,
    -- Foreign Key
    booking_id INT,                        -- Foreign key referencing the BOOKING table
    FOREIGN KEY (booking_id) REFERENCES BOOKING(booking_id) ON DELETE CASCADE
);

CREATE TABLE CONTACT(
	contact_id int  primary key auto_increment,
    email varchar(100),
    name varchar(50),
    phone_number int,
    message varchar(500),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES USERS(user_id) ON DELETE CASCADE
);

CREATE TABLE EMPLOYEE(
	employee_id int primary key auto_increment,
	first_name VARCHAR(50) not null,                -- First name of the passenger
    middle_name VARCHAR(50),               -- Middle name of the passenger (optional)
    last_name VARCHAR(50),
	street VARCHAR(100),          -- Street address of the user
    city VARCHAR(50),                     -- City of the user
    state VARCHAR(50),                    -- State of the user
    country VARCHAR(50),                  -- Country of the user
    pin_code VARCHAR(10),
    designation varchar(100),
    mobile_number int,
    airport_id int,
    FOREIGN KEY (airport_id) REFERENCES AIRPORT(airport_id) ON DELETE CASCADE
);

CREATE TABLE PAYMENT_DETAILS(
	payment_id int primary key auto_increment,
	card_number int unique not null,
    card_holder_name varchar(50) not null,
    cvv int not null,
    expiry_date date,
    user_id int,
    FOREIGN KEY (user_id) REFERENCES USERS(user_id) ON DELETE CASCADE
);

CREATE TABLE USER_EMAIL (
    user_id INT,                          -- Foreign key referencing USERS table
    email VARCHAR(100),                   -- Email address of the user
    PRIMARY KEY (user_id, email),         -- Composite primary key to allow multiple emails
    FOREIGN KEY (user_id) REFERENCES USERS(user_id) ON DELETE CASCADE
);

ALTER TABLE USERS RENAME COLUMN email TO primary_email;
ALTER TABLE USERS ADD COLUMN role VARCHAR(50);
ALTER TABLE USERS
MODIFY COLUMN role VARCHAR(50) DEFAULT 'user';
SHOW CREATE TABLE USERS;
ALTER TABLE employee MODIFY COLUMN mobile_number VARCHAR(15);
ALTER TABLE flights
DROP CONSTRAINT flights_ibfk_1;
alter table flights
drop column airport_id;

ALTER TABLE flights 
ADD COLUMN arrival_airport_id INT,
ADD COLUMN departure_airport_id INT,
ADD CONSTRAINT flights_ibfk_arrival FOREIGN KEY (arrival_airport_id) REFERENCES airport(airport_id) ON DELETE CASCADE,
ADD CONSTRAINT flights_ibfk_departure FOREIGN KEY (departure_airport_id) REFERENCES airport(airport_id) ON DELETE CASCADE;

-- creating a new table for seats
CREATE TABLE FLIGHT_SEATS (
    flight_seat_id INT PRIMARY KEY AUTO_INCREMENT,       -- Unique ID for the flight seat record
    flight_id INT NOT NULL,                              -- Foreign key to the FLIGHTS table
    available_economy_seats INT NOT NULL,                -- Available economy seats
    available_business_seats INT NOT NULL,               -- Available business seats
    available_first_class_seats INT NOT NULL,            -- Available first-class seats
    FOREIGN KEY (flight_id) REFERENCES FLIGHTS(flight_id) ON DELETE CASCADE
);

ALTER TABLE booking
ADD class_type varchar(20);

ALTER TABLE payment_details
ADD booking_id int;

ALTER TABLE payment_details
ADD FOREIGN KEY (booking_id) REFERENCES booking(booking_id);
SHOW CREATE TABLE payment_details;
ALTER TABLE payment_details
DROP CONSTRAINT payment_details_ibfk_1;
alter table payment_details
drop column user_id;
alter table contact modify column phone_number varchar(15);
ALTER TABLE passenger MODIFY COLUMN aadhar_number VARCHAR(15);