USE `kiki's` ;
set foreign_key_checks=0;

drop table if exists address;
drop table if exists clients;
drop table if exists delivery;
drop table if exists credit_card;
drop table if exists cash;
drop table if exists center;
drop table if exists parcel;

create table address
	(country varchar(20),
	province varchar(2),
	city varchar(20),
	street_name varchar(30),
	house_num int NOT NULL,
	PC varchar(7) NOT NULL,
	PRIMARY KEY(PC, house_num)
	);

create table clients
	(clID int(11) NOT NULL,
	fname varchar(30),
	lname varchar(30),
	PC varchar(7) NOT NULL,
	house_num int NOT NULL,
	phone_num varchar(12),
	PRIMARY KEY(clID),
	FOREIGN KEY(PC, house_num) REFERENCES address(PC, house_num)
		ON DELETE NO ACTION
		ON UPDATE CASCADE
	);

create table delivery
	(
	dID int(6) not null,
	type varchar(20),
	status varchar(20),
	sender_ID int(6) not null,
	receiver_ID int(6) not null,
	PRIMARY KEY(dID),
	FOREIGN KEY(sender_ID) REFERENCES clients(clID),
	FOREIGN KEY(receiver_ID) REFERENCES clients(clID)
		ON DELETE CASCADE
		ON UPDATE CASCADE
	);
    
create table credit_card
	(payID int(3) NOT NULL,
	amount float,
	onDate varchar(10),
	credit_card_num varchar(30),
	CSV int,
	name varchar(30),
	expiry_date varchar(5),
	type varchar(255),
	dID int(6),
	PRIMARY KEY(payID),
	FOREIGN KEY(dID) REFERENCES delivery(dID)
		ON DELETE NO ACTION
		ON UPDATE CASCADE
	);

create table cash
	(amount float,
	payID int(3) NOT NULL,
	onDate varchar(10),
	dID int(6),
	PRIMARY KEY(payID),
	FOREIGN KEY(dID) REFERENCES delivery(dID)
		ON DELETE NO ACTION
		ON UPDATE CASCADE
	);

create table center
    (cID varchar(30) NOT NULL,
    center_addr varchar(100),
    PRIMARY KEY(cID)
    );

create table parcel
	(pID int(6) not null,
	length float,
	width float,
	weight float,
	height float,
	cID varchar(30),
	next_cID varchar(30),
	dID int(6) not null,
	PRIMARY KEY(pID, dID),
	FOREIGN KEY (cID) REFERENCES center(cID),
	FOREIGN KEY(dID) REFERENCES delivery(dID)
		ON DELETE CASCADE
		ON UPDATE CASCADE
	);

INSERT INTO address (country,province,city,street_name,house_num,PC) VALUES ('Canada','MI','Flint','Glacier Hill',65,'U2D 7Z0');
INSERT INTO address (country,province,city,street_name,house_num,PC) VALUES ('United States','CA','Fresno','Pleasure',1,'Y1Y 6W8');
INSERT INTO address (country,province,city,street_name,house_num,PC) VALUES ('United States','GA','Decatur','La Follette',123,'I4W 3N0');
INSERT INTO address (country,province,city,street_name,house_num,PC) VALUES ('United States','CA','San Rafael','Northport',145,'Z5W 8G3');
INSERT INTO address (country,province,city,street_name,house_num,PC) VALUES ('Canada','FL','Fort Lauderdale','Lyons',41980,'P8W 4S1');
INSERT INTO address (country,province,city,street_name,house_num,PC) VALUES ('United States','LA','Baton Rouge','Clarendon',41,'M1V 1P9');
INSERT INTO address (country,province,city,street_name,house_num,PC) VALUES ('United States','GA','Atlanta','Transport',8,'O4O 4C5');
INSERT INTO address (country,province,city,street_name,house_num,PC) VALUES ('Canada','FL','Tampa','Surrey',27359,'K6C 6Z8');
INSERT INTO address (country,province,city,street_name,house_num,PC) VALUES ('Canada','PA','Philadelphia','Toban',1019,'S8M 2U8');
INSERT INTO address (country,province,city,street_name,house_num,PC) VALUES ('Canada','TX','Lubbock','4th',4987,'L0G 4C3');
INSERT INTO address (country,province,city,street_name,house_num,PC) VALUES ('Canada','CO','Denver','Corben',75,'W0A 7O6');
INSERT INTO address (country,province,city,street_name,house_num,PC) VALUES ('United States','CA','Los Angeles','Center',5,'X8L 1Q5');
INSERT INTO address (country,province,city,street_name,house_num,PC) VALUES ('United States','MA','Boston','Ronald Regan',87,'V1D 7Q6');
INSERT INTO address (country,province,city,street_name,house_num,PC) VALUES ('Canada','WA','Vancouver','Kedzie',65,'O7F 1H9');
INSERT INTO address (country,province,city,street_name,house_num,PC) VALUES ('Canada','UT','Ogden','Sheridan',10,'R9Q 4R9');
INSERT INTO address (country,province,city,street_name,house_num,PC) VALUES ('Canada','TX','Austin','Moland',120,'S4A 7F1');
INSERT INTO address (country,province,city,street_name,house_num,PC) VALUES ('United States','TX','Houston','Forster',440,'Z9B 0W8');
INSERT INTO address (country,province,city,street_name,house_num,PC) VALUES ('United States','GA','Decatur','Stephen',107,'V6U 8O7');
INSERT INTO address (country,province,city,street_name,house_num,PC) VALUES ('United States','TX','Denton','Hintze',5,'T1P 7P5');
INSERT INTO address (country,province,city,street_name,house_num,PC) VALUES ('United States','GA','Albany','Milwaukee',1,'M3X 4D9');

INSERT INTO clients (clID,fname,lname,PC,house_num,phone_num) VALUES ('100000','Simon','Ford','U2D 7Z0','65','422-605-5196');
INSERT INTO clients (clID,fname,lname,PC,house_num,phone_num) VALUES ('100001','Cole','Phillips','Y1Y 6W8','1','772-300-7230');
INSERT INTO clients (clID,fname,lname,PC,house_num,phone_num) VALUES ('100002','Eden','Hood','I4W 3N0','123','243-960-7278');
INSERT INTO clients (clID,fname,lname,PC,house_num,phone_num) VALUES ('100003','Mara','Goodman','Z5W 8G3','145','764-626-7790');
INSERT INTO clients (clID,fname,lname,PC,house_num,phone_num) VALUES ('100004','Sawyer','Fuentes','P8W 4S1','41980','940-209-5827');
INSERT INTO clients (clID,fname,lname,PC,house_num,phone_num) VALUES ('100005','Glenna','Richmond','M1V 1P9','41','188-634-5564');
INSERT INTO clients (clID,fname,lname,PC,house_num,phone_num) VALUES ('100006','Keith','Williams','O4O 4C5','8','907-678-7140');
INSERT INTO clients (clID,fname,lname,PC,house_num,phone_num) VALUES ('100007','Sylvester','Shaffer','K6C 6Z8','27359','452-444-7647');
INSERT INTO clients (clID,fname,lname,PC,house_num,phone_num) VALUES ('100008','Orson','Stanley','S8M 2U8','1019','509-533-3053');
INSERT INTO clients (clID,fname,lname,PC,house_num,phone_num) VALUES ('100009','Garrett','Hyde','L0G 4C3','4987','447-604-6478');
INSERT INTO clients (clID,fname,lname,PC,house_num,phone_num) VALUES ('100010','Lionel','Wolf','W0A 7O6','75','930-713-1815');
INSERT INTO clients (clID,fname,lname,PC,house_num,phone_num) VALUES ('100011','Allen','Simon','X8L 1Q5','5','475-838-0984');
INSERT INTO clients (clID,fname,lname,PC,house_num,phone_num) VALUES ('100012','Yardley','Holden','V1D 7Q6','87','926-537-7650');
INSERT INTO clients (clID,fname,lname,PC,house_num,phone_num) VALUES ('100013','Clayton','Soto','O7F 1H9','65','835-953-7602');
INSERT INTO clients (clID,fname,lname,PC,house_num,phone_num) VALUES ('100014','Vanna','Shepherd','R9Q 4R9','10','810-624-5289');
INSERT INTO clients (clID,fname,lname,PC,house_num,phone_num) VALUES ('100015','Cullen','Haney','S4A 7F1','120','564-637-1039');
INSERT INTO clients (clID,fname,lname,PC,house_num,phone_num) VALUES ('100016','Dominic','Delgado','Z9B 0W8','440','415-879-5704');
INSERT INTO clients (clID,fname,lname,PC,house_num,phone_num) VALUES ('100017','Wyoming','Fernandez','V6U 8O7','107','552-292-9229');
INSERT INTO clients (clID,fname,lname,PC,house_num,phone_num) VALUES ('100018','Gil','Tyler','T1P 7P5','5','336-100-4532');
INSERT INTO clients (clID,fname,lname,PC,house_num,phone_num) VALUES ('100019','Hedwig','Delacruz','M3X 4D9','1','903-153-6209');

INSERT INTO delivery (dID,type,status,sender_ID,receiver_ID) VALUES (1,'standard','in transit',100001,100010);
INSERT INTO delivery (dID,type,status,sender_ID,receiver_ID) VALUES (2,'expedited','in transit',100014,100002);
INSERT INTO delivery (dID,type,status,sender_ID,receiver_ID) VALUES (3,'standard','in transit',100018,100010);
INSERT INTO delivery (dID,type,status,sender_ID,receiver_ID) VALUES (4,'expedited','delivered',100014,100011);
INSERT INTO delivery (dID,type,status,sender_ID,receiver_ID) VALUES (5,'standard','in transit',100008,100003);
INSERT INTO delivery (dID,type,status,sender_ID,receiver_ID) VALUES (6,'expedited','in transit',100004,100006);
INSERT INTO delivery (dID,type,status,sender_ID,receiver_ID) VALUES (7,'expedited','delivered',100004,100016);
INSERT INTO delivery (dID,type,status,sender_ID,receiver_ID) VALUES (8,'standard','in transit',100018,100004);
INSERT INTO delivery (dID,type,status,sender_ID,receiver_ID) VALUES (9,'expedited','just left',100019,100014);
INSERT INTO delivery (dID,type,status,sender_ID,receiver_ID) VALUES (10,'standard','in transit',100015,100006);
INSERT INTO delivery (dID,type,status,sender_ID,receiver_ID) VALUES (11,'express','delivered',100009,100016);
INSERT INTO delivery (dID,type,status,sender_ID,receiver_ID) VALUES (12,'standard','just left',100013,100017);
INSERT INTO delivery (dID,type,status,sender_ID,receiver_ID) VALUES (13,'standard','delivered',100005,100019);
INSERT INTO delivery (dID,type,status,sender_ID,receiver_ID) VALUES (14,'express','delivered',100008,100013);
INSERT INTO delivery (dID,type,status,sender_ID,receiver_ID) VALUES (15,'standard','just left',100013,100007);
INSERT INTO delivery (dID,type,status,sender_ID,receiver_ID) VALUES (16,'express','in transit',100017,100014);
INSERT INTO delivery (dID,type,status,sender_ID,receiver_ID) VALUES (17,'express','delivered',100018,100005);
INSERT INTO delivery (dID,type,status,sender_ID,receiver_ID) VALUES (18,'standard','in transit',100005,100013);
INSERT INTO delivery (dID,type,status,sender_ID,receiver_ID) VALUES (19,'standard','just left',100008,100017);
INSERT INTO delivery (dID,type,status,sender_ID,receiver_ID) VALUES (20,'standard','just left',100014,100004);

INSERT INTO credit_card (payID,amount,onDate,credit_card_num,CSV,name,expiry_date,type,dID) VALUES ('1','684.81','08/22/1992','3552246614436400',456,'Simon Ford','04/16','jcb',1);
INSERT INTO credit_card (payID,amount,onDate,credit_card_num,CSV,name,expiry_date,type,dID) VALUES ('2','2403.91','09/12/2008','3531748987803120',219,'Cole Phillips','05/18','jcb',2);
INSERT INTO credit_card (payID,amount,onDate,credit_card_num,CSV,name,expiry_date,type,dID) VALUES ('3','1110.89','07/02/2015','5405507783726030',600,'Eden Hood','06/19','mastercard',3);
INSERT INTO credit_card (payID,amount,onDate,credit_card_num,CSV,name,expiry_date,type,dID) VALUES ('4','587.8','09/11/2015','3576971229222520',400,'Mara Goodman','08/18','jcb',4);
INSERT INTO credit_card (payID,amount,onDate,credit_card_num,CSV,name,expiry_date,type,dID) VALUES ('5','1412.72','09/11/2015','5602256617878250',428,'Sawyer Fuentes','07/16','bankcard',5);
INSERT INTO credit_card (payID,amount,onDate,credit_card_num,CSV,name,expiry_date,type,dID) VALUES ('6','3706.03','09/11/2015','3566655681036910',599,'Glenna Richmond','07/18','jcb',6);
INSERT INTO credit_card (payID,amount,onDate,credit_card_num,CSV,name,expiry_date,type,dID) VALUES ('7','2903.03','07/02/2015','6759059166667820',957,'Keith Williams','08/15','maestro',7);
INSERT INTO credit_card (payID,amount,onDate,credit_card_num,CSV,name,expiry_date,type,dID) VALUES ('8','2188.57','08/22/1992','3578649060524940',573,'Sylvester Shaffer','03/19','jcb',8);
INSERT INTO credit_card (payID,amount,onDate,credit_card_num,CSV,name,expiry_date,type,dID) VALUES ('9','2364.25','08/22/1992','3551493259764980',439,'Orson Stanley','05/16','jcb',9);
INSERT INTO credit_card (payID,amount,onDate,credit_card_num,CSV,name,expiry_date,type,dID) VALUES ('10','4052.03','07/02/2015','337941093737582',736,'Garrett Hyde','08/18','americanexpress',10);
INSERT INTO credit_card (payID,amount,onDate,credit_card_num,CSV,name,expiry_date,type,dID) VALUES ('11','2487.39','09/12/2008','3571561702872140',521,'Lionel Wolf','09/18','jcb',11);
INSERT INTO credit_card (payID,amount,onDate,credit_card_num,CSV,name,expiry_date,type,dID) VALUES ('12','4482.33','09/12/2008','201503858198005',425,'Allen Simon','12/15','diners-club-enroute',12);
INSERT INTO credit_card (payID,amount,onDate,credit_card_num,CSV,name,expiry_date,type,dID) VALUES ('13','860.81','07/02/2015','3579051227656800',643,'Yardley Holden','07/18','jcb',13);
INSERT INTO credit_card (payID,amount,onDate,credit_card_num,CSV,name,expiry_date,type,dID) VALUES ('14','4802.99','09/12/2008','3538969234529020',573,'Clayton Soto','07/16','jcb',14);

INSERT INTO cash (amount,payID,onDate,dID) VALUES ('2605.06','15','09/12/2008','15');
INSERT INTO cash (amount,payID,onDate,dID) VALUES ('2616.09','16','07/02/2015','16');
INSERT INTO cash (amount,payID,onDate,dID) VALUES ('4973.39','17','09/12/2008','17');
INSERT INTO cash (amount,payID,onDate,dID) VALUES ('2303.8','18','07/02/2015','18');
INSERT INTO cash (amount,payID,onDate,dID) VALUES ('592.17','19','09/12/2008','19');
INSERT INTO cash (amount,payID,onDate,dID) VALUES ('3622.17','20','07/02/2015','20');

INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('1','20.79','33.75', '65.7','89.67','ubc','burnabysouth','4');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('2','95.26','76.34', '32.49','55.27','burnabynorth','ubc','14');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('3','65.14','33.34', '13.46','76.61','portcoquitlam','surrey','17');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('4','5.65','78.97', '31.62','22.9','ubc','portcoquitlam','20');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('5','29.58','77.75', '80.9','53.98','burnabysouth','portcoquitlam','6');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('6','38.38','48.64', '97.5','20.89','burnabynorth','ubc','18');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('7','39.61','92.7', '23.84','55.13','ubc','portcoquitlam','6');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('8','69.7','47.68', '82.35','84.5','ubc','burnabysouth','15');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('9','60.21','87.12', '26.28','19.44','burnabysouth','burnabynorth','5');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('10','40.04','78.29', '62.12','44.83','portcoquitlam','ubc','2');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('11','34.78','11.68', '88.52','12.07','portcoquitlam','surrey','4');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('12','91.18','6.04', '62.74','74.59','ubc','surrey','13');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('13','58.85','93.88', '62.29','2.86','surrey','ubc','13');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('14','13.28','61.65', '70.25','50.68','portcoquitlam','burnabysouth','14');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('15','56.06','71.61', '41.76','94.89','burnabynorth','portcoquitlam','8');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('16','65.82','59.32', '10.52','94.58','burnabysouth','ubc','4');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('17','13.31','46.15', '34.13','78.64','ubc','portcoquitlam','14');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('18','56.52','79.81', '54.32','10.47','portcoquitlam','burnabynorth','3');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('19','42.19','1.69', '87.71','53.7','burnabynorth','ubc','4');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('20','12.68','86.45', '87.38','4.53','ubc','surrey','20');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('21','39.14','98.93', '80.59','8.31','ubc','surrey','15');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('22','16.36','5.97', '37.08','13.28','surrey','portcoquitlam','1');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('23','42.06','76.01', '94.86','6.03','burnabynorth','ubc','1');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('24','18.4','97.68', '62.65','53.5','portcoquitlam','surrey','6');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('25','92.29','82.94', '3.52','47.22','ubc','portcoquitlam','5');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('26','53.62','33.72', '52.28','57.45','portcoquitlam','burnabynorth','19');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('27','82.31','61.99', '6.23','40.21','portcoquitlam','burnabysouth','6');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('28','50.58','21.95', '84.66','74.67','ubc','portcoquitlam','15');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('29','49.59','90.39', '24.07','63.22','burnabynorth','ubc','19');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('30','10.94','34.36', '42.06','54.97','burnabysouth','ubc','18');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('31','65.76','28.51', '85.58','13.48','ubc','surrey','19');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('32','21.43','23.26', '50.08','84.25','burnabynorth','ubc','8');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('33','39.82','18.9', '99.51','84.27','portcoquitlam','ubc','17');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('34','71.01','17.22', '65.13','90.62','portcoquitlam','burnabysouth','14');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('35','5.73','72.04', '28.21','53.55','ubc', 'surrey','13');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('36','27.99','81.51', '24.89','20.44','portcoquitlam','ubc','6');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('37','18.1','30.17', '35.43','2.07','portcoquitlam','surrey','11');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('38','6.7','78.88', '37.02','47.1','surrey','portcoquitlam','6');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('39','1.51','84.89', '97.14','81.87','ubc','portcoquitlam','19');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('40','79.18','49.23', '27.99','7.73','burnabysouth','ubc','10');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('41','26.82','4.9', '39.52','39.12','surrey','portcoquitlam','4');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('42','25.27','94.68', '28.8','36.55','ubc','surrey','3');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('43','36.72','69.75', '23.45','55.78','ubc','burnabynorth','10');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('44','31.84','99.97', '60.1','51.67','portcoquitlam','burnabysouth','12');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('45','88.01','87.73', '26.41','14.98','burnabynorth','ubc','17');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('46','37.05','62.14', '92.35','5.09','surrey','surrey','14');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('47','25.36','82.7', '45.73','37.11','ubc','burnabysouth','13');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('48','51.55','44.95', '60.74','92.76','surrey','ubc','19');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('49','90.94','76.02', '80.22','49.3','burnabysouth','portcoquitlam','10');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('50','56.44','43.44', '76.99','86.54','ubc','portcoquitlam','1');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('51','73.86','1.01', '65.7','99.03','ubc','portcoquitlam','8');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('52','8.75','61.16', '32.49','84.57','surrey','burnabysouth','20');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('53','87.17','46.65', '13.46','80.78','portcoquitlam','ubc','13');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('54','72.08','32.88', '31.62','41.81','ubc','surrey','9');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('55','95.27','48.89', '80.9','65.28','ubc','burnabynorth','12');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('56','78.21','11.83', '97.5','6.24','surrey','ubc','18');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('57','22.73','1.98', '23.84','36.05','surrey','portcoquitlam','12');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('58','30.49','79.32', '82.35','89.88','burnabysouth','portcoquitlam','15');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('59','93.92','98.07', '26.28','62.76','burnabynorth','portcoquitlam','18');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('60','99.45','46.92', '62.12','59.23','ubc','portcoquitlam','11');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('61','91.61','43.34', '88.52','87.3','ubc','burnabysouth','17');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('62','72.11','91.44', '62.74','30.11','surrey','burnabynorth','13');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('63','77.44','60.12', '62.29','45.03','surrey','portcoquitlam','10');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('64','3.56','41.27', '70.25','7.43','ubc','portcoquitlam','3');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('65','6.52','41.04', '41.76','8.68','surrey','ubc','8');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('66','46.49','88.88', '10.52','94.64','portcoquitlam','surrey','17');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('67','33.16','67.11', '34.13','90.88','burnabynorth','8','18');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('68','55.59','96.3', '54.32','72.21','ubc','surrey','18');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('69','77.05','50.91', '87.71','7.26','burnabysouth','5','12');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('70','63.9','44.84', '87.38','76.14','ubc','burnabynorth','19');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('71','74.23','11.31', '80.59','85.77','ubc','burnabysouth','17');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('72','3.4','41.07', '37.08','38.45','portcoquitlam','ubc','17');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('73','26.81','13.97', '94.86','9.27','burnabynorth','portcoquitlam','5');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('74','5.12','56.11', '62.65','59.61','surrey','portcoquitlam','10');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('75','46.6','52.97', '3.52','22.64','portcoquitlam','burnabynorth','20');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('76','31.05','79.02', '52.28','96.65','ubc','ubc','20');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('77','89.54','13.74', '6.23','94.29','burnabysouth','14','19');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('78','69.81','77.16', '84.66','41.83','portcoquitlam','surrey','16');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('79','44.3','65.05', '24.07','64.92','burnabynorth','portcoquitlam','20');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('80','43.31','47.91', '42.06','14.23','ubc','burnabysouth','1');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('81','8.21','43.19', '85.58','68.13','portcoquitlam','ubc','8');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('82','73.49','68.86', '50.08','81.14','portcoquitlam','ubc','3');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('83','3.85','92.25', '99.51','45.3','surrey','ubc','10');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('84','55.25','52.07', '65.13','74.23','portcoquitlam','ubc','15');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('85','3.04','78.03', '28.21','58.03','ubc','burnabynorth','16');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('86','16.01','77.87', '24.89','70.5','portcoquitlam','surrey','8');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('87','58.46','73.84', '35.43','15.39','burnabysouth','ubc','6');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('88','91.03','60.15', '37.02','18.94','ubc','surrey','18');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('89','95.87','63.92', '97.14','69.36','burnabynorth','burnabysouth','13');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('90','6.58','61.53', '27.99','7.79','portcoquitlam','burnabynorth','2');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('91','75.59','63.59', '39.52','42.98','surrey','ubc','16');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('92','55.69','34.76', '28.8','84.14','ubc','portcoquitlam','14');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('93','85.15','87.93', '23.45','62.5','surrey','portcoquitlam','13');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('94','41.88','73.14', '60.1','73.4','burnabynorth','portcoquitlam','16');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('95','75.52','46.77', '26.41','83.34','burnabysouth','portcoquitlam','2');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('96','15.71','21.75', '92.35','94.7','portcoquitlam','ubc','17');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('97','86.31','92.88', '45.73','76.34','surrey','burnabynorth','14');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('98','39.53','99.97', '60.74','50.9','ubc','burnabysouth','20');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('99','44.79','79.51', '80.22','78.68','burnabynorth','ubc','3');
INSERT INTO parcel (pID,length,width,weight,height,cID,next_cID,dID) VALUES ('100','36.48','15.31', '76.99','73.57','burnabysouth','burnabynorth','15');

INSERT INTO center (cID, center_addr) VALUES ('ubc', '2329 West Mall, Vancouver, BC V6T 1Z4');
INSERT INTO center (cID, center_addr) VALUES ('burnabysouth', '5455 Rumble St, Burnaby, BC V5J 2B7');
INSERT INTO center (cID, center_addr) VALUES ('burnabynorth', '751 Hammarskjold Dr, Burnaby, BC V5B 4A1');
INSERT INTO center (cID, center_addr) VALUES ('surrey', '10153 King George Blvd, Surrey, BC V3T 2W1');
INSERT INTO center (cID, center_addr) VALUES ('portcoquitlam', '2215 Reeve St, Port Coquitlam, BC V3C 6K8');

-- INSERT INTO center (cID,center_addr) VALUES ('1','52 Troy Drive');
-- INSERT INTO center (cID,center_addr) VALUES ('2','6137 Oxford Circle');
-- INSERT INTO center (cID,center_addr) VALUES ('3','1154 Park Meadow Plaza');
-- INSERT INTO center (cID,center_addr) VALUES ('4','244 Forest Run Terrace');
-- INSERT INTO center (cID,center_addr) VALUES ('5','07584 Doe Crossing Junction');
-- INSERT INTO center (cID,center_addr) VALUES ('6','48103 Forster Court');
-- INSERT INTO center (cID,center_addr) VALUES ('7','935 Crownhardt Lane');
-- INSERT INTO center (cID,center_addr) VALUES ('8','926 Eastwood Lane');
-- INSERT INTO center (cID,center_addr) VALUES ('9','71 Warrior Circle');
-- INSERT INTO center (cID,center_addr) VALUES ('10','32 Waywood Street');
-- INSERT INTO center (cID,center_addr) VALUES ('11','761 Norway Maple Plaza');
-- INSERT INTO center (cID,center_addr) VALUES ('12','2 Roth Hill');
-- INSERT INTO center (cID,center_addr) VALUES ('13','6300 Talmadge Center');
-- INSERT INTO center (cID,center_addr) VALUES ('14','93 Bay Court');
-- INSERT INTO center (cID,center_addr) VALUES ('15','885 Bunker Hill Trail');
-- INSERT INTO center (cID,center_addr) VALUES ('16','61936 American Place');
-- INSERT INTO center (cID,center_addr) VALUES ('17','893 Evergreen Crossing');
-- INSERT INTO center (cID,center_addr) VALUES ('18','25 Chinook Road');
-- INSERT INTO center (cID,center_addr) VALUES ('19','47298 Eagan Center');
-- INSERT INTO center (cID,center_addr) VALUES ('20','5 Village Hill');
-- INSERT INTO center (cID,center_addr) VALUES ('21','7559 Bartillon Point');
-- INSERT INTO center (cID,center_addr) VALUES ('22','552 Daystar Trail');
-- INSERT INTO center (cID,center_addr) VALUES ('23','499 Menomonie Parkway');
-- INSERT INTO center (cID,center_addr) VALUES ('24','99 Summer Ridge Plaza');
-- INSERT INTO center (cID,center_addr) VALUES ('25','91 Lotheville Place');
-- INSERT INTO center (cID,center_addr) VALUES ('26','13915 Fair Oaks Hill');
-- INSERT INTO center (cID,center_addr) VALUES ('27','15313 Oxford Lane');
-- INSERT INTO center (cID,center_addr) VALUES ('28','8 Moland Way');
-- INSERT INTO center (cID,center_addr) VALUES ('29','6127 Paget Avenue');
-- INSERT INTO center (cID,center_addr) VALUES ('30','0423 New Castle Circle');
-- INSERT INTO center (cID,center_addr) VALUES ('31','268 Meadow Ridge Pass');
-- INSERT INTO center (cID,center_addr) VALUES ('32','624 Vernon Road');
-- INSERT INTO center (cID,center_addr) VALUES ('33','89 Surrey Court');
-- INSERT INTO center (cID,center_addr) VALUES ('34','2 Fulton Lane');
-- INSERT INTO center (cID,center_addr) VALUES ('35','084 Fremont Junction');
-- INSERT INTO center (cID,center_addr) VALUES ('36','35 Drewry Junction');
-- INSERT INTO center (cID,center_addr) VALUES ('37','56706 Heath Way');
-- INSERT INTO center (cID,center_addr) VALUES ('38','93 Stuart Court');
-- INSERT INTO center (cID,center_addr) VALUES ('39','9 High Crossing Plaza');
-- INSERT INTO center (cID,center_addr) VALUES ('40','37971 Paget Park');
-- INSERT INTO center (cID,center_addr) VALUES ('41','83 Mccormick Point');
-- INSERT INTO center (cID,center_addr) VALUES ('42','464 Vahlen Street');
-- INSERT INTO center (cID,center_addr) VALUES ('43','924 Miller Alley');
-- INSERT INTO center (cID,center_addr) VALUES ('44','433 Grim Court');
-- INSERT INTO center (cID,center_addr) VALUES ('45','11844 Merrick Road');
-- INSERT INTO center (cID,center_addr) VALUES ('46','17916 Mallory Place');
-- INSERT INTO center (cID,center_addr) VALUES ('47','39361 Carey Circle');
-- INSERT INTO center (cID,center_addr) VALUES ('48','97290 Boyd Court');
-- INSERT INTO center (cID,center_addr) VALUES ('49','63891 Commercial Alley');
-- INSERT INTO center (cID,center_addr) VALUES ('50','88235 Mayfield Parkway');
