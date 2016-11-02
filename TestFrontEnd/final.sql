Use final_project;

Drop table if exists fantasy_scoring;
Drop table if exists defensive_stat;
Drop table if exists offensive_stat;
Drop table if exists offensive_player;
Drop table if exists game_result;
Drop table if exists matchup;
Drop table if exists team;
Drop table if exists position;

Create table fantasy_scoring (
id int primary key auto_increment,
description varchar(20),
reception decimal(8,2),
rush_yard decimal(8,2),
rec_yard decimal(8,2),
pass_yard decimal(8,2),
pass_touchdown int,
rush_touchdown int,
rec_touchdown int,
interception int,
fumble_lost int,
def_0_points int,
def_6_points int,
def_13_points int,
def_17_points int,
def_27_points int,
def_34_points int,
def_45_points int,
def_over_45_points int,
def_fumble_rec int,
def_interception int,
def_sack int
);

Create table position (
id int primary key auto_increment,
code varchar(2),
description varchar(20)
);

Create table team (
id int primary key auto_increment,
division varchar(30),
teamName varchar(40),
abrev varchar(5),
wins int default 0,
losses int default 0,
ties int default 0,
ovrRank int,
stadiumLocation varchar(30)
);

Create table offensive_player (
id int primary key auto_increment,
name varchar(40),
teamId int,
position int,
offRanking int,
CONSTRAINT fk_team FOREIGN KEY (teamId)
REFERENCES team (id),
CONSTRAINT fk_position FOREIGN KEY (position)
REFERENCES position (id)
);

Create table matchup (
id int primary key auto_increment,
week int,
homeTeamId int,
awayTeamId int,
final boolean,
CONSTRAINT fk_hometeam FOREIGN KEY (homeTeamId)
REFERENCES team (id),
CONSTRAINT fk_awayteam FOREIGN KEY (awayTeamId)
REFERENCES team (id)
);

Create table game_result (
id int primary key auto_increment,
matchupId int, 
winTeamId int, 
loseTeamId int,
winScore int,
loseScore int,
tie boolean,
CONSTRAINT fk_winteam FOREIGN KEY (winTeamId)
REFERENCES team (id),
CONSTRAINT fk_loseteam FOREIGN KEY (loseTeamId)
REFERENCES team (id),
CONSTRAINT fk_matchup FOREIGN KEY (matchupId)
REFERENCES matchup (id)
);

Create table defensive_stat (
id int primary key auto_increment,
oppTeamId int,
passYardsAllowed int,
rushYardsAllowed int,
teamId int,
pointsAllowed int,
sacks int,
interceptions int,
fumbleRcvry int,
defRanking int,
CONSTRAINT fk_dteam FOREIGN KEY (teamId)
REFERENCES team (id),
CONSTRAINT fk_opp_off FOREIGN KEY (oppTeamId)
REFERENCES team (id)
);

Create table offensive_stat (
id int primary key auto_increment,
playerId int, 
oppTeamId int,
passAttempts int,
passCompletions int,
passYards int,
passTouchdowns int,
passInterceptions int,
receptions int,
recYards int,
recTouchdowns int,
rushAttempts int,
rushYards int,
rushTouchdowns int,
fumbles int,
CONSTRAINT fk_player FOREIGN KEY (playerId)
REFERENCES offensive_player (id),
CONSTRAINT fk_opp_def FOREIGN KEY (oppTeamId)
REFERENCES team (id)
);



INSERT into team (division, TeamName, abrev, stadiumLocation) values ('NFC West', 'Arizona Cardinals', 'ARI', '33.5277,-112.262608');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('NFC South', 'Atlanta Falcons', 'ATL', '33.757614,-84.400972');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('AFC North', 'Baltimore Ravens', 'BAL', '39.277969,-76.622767');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('AFC East', 'Buffalo Bills', 'BUF', '42.773739,-78.786978');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('NFC South', 'Carolina Panthers', 'CAR', '35.225808,-80.852861');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('NFC North', 'Chicago Bears', 'CHI', '41.862306,-87.616672');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('AFC North', 'Cincinatti Bengals', 'CIN', '39.095442,-84.516039');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('AFC North', 'Cleveland Browns', 'CLE', '41.506022,-81.699564');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('NFC East', 'Dallas Cowboys', 'DAL', '32.747778,-97.092778');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('AFC West', 'Denver Broncos', 'DEN', '39.743936,-105.020097');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('NFC North', 'Detroit Lions', 'DET', '42.340156,-83.045808');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('NFC North', 'Green Bay Packers', 'GB', '44.501306,-88.062167');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('AFC South', 'Houston Texans', 'HOU', '29.684781,-95.410956');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('AFC South', 'Indianapolis Colts', 'IND', '39.760056,-86.163806');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('AFC South', 'Jacksonville Jaguars', 'JAX', '30.323925,-81.637356');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('AFC West', 'Kansas City Chiefs', 'KC', '39.048914,-94.484039');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('NFC West', 'Los Angeles Rams', 'LA', '34.0139,-118.2881');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('AFC East', 'Miami Dolphins', 'MIA', '25.957919,-80.238842');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('NFC North', 'Minnesota Vikings', 'MIN', '44.973881,-93.258094');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('AFC East', 'New England Patriots', 'NE', '42.090925,-71.26435');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('NFC South', 'New Orleans Saints', 'NO', '29.950931,-90.081364');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('NFC East', 'New York Giants', 'NYG', '40.8128,-74.0742');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('AFC East', 'New York Jets', 'NYJ', '40.8128,-74.0742');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('AFC West', 'Oakland Raiders', 'OAK', '37.751411,-122.200889');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('NFC East', 'Philadelphia Eagles', 'PHI', '39.900775,-75.167453');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('AFC North', 'Pittsburgh Steelers', 'PIT', '40.446786,-80.015761');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('AFC West', 'San Diego Chargers', 'SD', '32.783117,-117.119525');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('NFC West', 'San Francisco 49ers', 'SF', '37.4032,-121.9698');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('NFC West', 'Seattle Seahawks', 'SEA', '47.595153,-122.331625');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('NFC South', 'Tampa Bay Buccaneers', 'TB', '27.975967,-82.50335');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('AFC South', 'Tennessee Titans', 'TEN', '36.1669,-86.7698');
INSERT into team (division, TeamName, abrev, stadiumLocation) values ('NFC East', 'Washington Redskins', 'WAS', '38.907697,-76.864517');

INSERT into position (code,description) values ('QB','Quarterback');
INSERT into position (code,description) values ('WR','Wide Reciever');
INSERT into position (code,description) values ('RB','Running Back');
INSERT into position (code,description) values ('TE','Tight End');

INSERT into offensive_player(teamId,name,position) values (1,'C.Palmer',1);
INSERT into offensive_player(teamId,name,position) values (1,'D.Johnson',3);
INSERT into offensive_player(teamId,name,position) values (1,'A.Ellington',3);
INSERT into offensive_player(teamId,name,position) values (1,'L.Fitzgerald',2);
INSERT into offensive_player(teamId,name,position) values (1,'M.Floyd',2);
INSERT into offensive_player(teamId,name,position) values (1,'J.Brown',2);
INSERT into offensive_player(teamId,name,position) values (1,'Ja.Brown',2);
INSERT into offensive_player(teamId,name,position) values (1,'J.Gresham',4);
INSERT into offensive_player(teamId,name,position) values (1,'D.Fells',4);
INSERT into offensive_player(teamId,name,position) values (2,'M.Ryan',1);
INSERT into offensive_player(teamId,name,position) values (2,'D.Freeman',3);
INSERT into offensive_player(teamId,name,position) values (2,'T.Coleman',3);
INSERT into offensive_player(teamId,name,position) values (2,'J.Jones',2);
INSERT into offensive_player(teamId,name,position) values (2,'M.Sanu',2);
INSERT into offensive_player(teamId,name,position) values (2,'J.Hardy',2);
INSERT into offensive_player(teamId,name,position) values (2,'A.Robinson',2);
INSERT into offensive_player(teamId,name,position) values (2,'J.Tamme',4);
INSERT into offensive_player(teamId,name,position) values (3,'J.Flacco',1);
INSERT into offensive_player(teamId,name,position) values (3,'T.West',3);
INSERT into offensive_player(teamId,name,position) values (3,'J.Allen',3);
INSERT into offensive_player(teamId,name,position) values (3,'K.Dixon',3);
INSERT into offensive_player(teamId,name,position) values (3,'S.Smith',2);
INSERT into offensive_player(teamId,name,position) values (3,'M.Wallace',2);
INSERT into offensive_player(teamId,name,position) values (3,'B.Perriman',2);
INSERT into offensive_player(teamId,name,position) values (3,'D.Pitta',4);
INSERT into offensive_player(teamId,name,position) values (3,'C.Gillmore',4);
INSERT into offensive_player(teamId,name,position) values (4,'T.Taylor',1);
INSERT into offensive_player(teamId,name,position) values (4,'L.McCoy',3);
INSERT into offensive_player(teamId,name,position) values (4,'M.Gillislee',3);
INSERT into offensive_player(teamId,name,position) values (4,'R.Woods',2);
INSERT into offensive_player(teamId,name,position) values (4,'M.Goodwin',2);
INSERT into offensive_player(teamId,name,position) values (4,'C.Clay',4);
INSERT into offensive_player(teamId,name,position) values (5,'C.Newton',1);
INSERT into offensive_player(teamId,name,position) values (5,'D.Anderson',1);
INSERT into offensive_player(teamId,name,position) values (5,'J.Stewart',3);
INSERT into offensive_player(teamId,name,position) values (5,'C.Artis-Payne',3);
INSERT into offensive_player(teamId,name,position) values (5,'M.Tolbert',3);
INSERT into offensive_player(teamId,name,position) values (5,'F.Whitaker',3);
INSERT into offensive_player(teamId,name,position) values (5,'K.Benjamin',2);
INSERT into offensive_player(teamId,name,position) values (5,'T.Ginn',2);
INSERT into offensive_player(teamId,name,position) values (5,'D.Funchess',2);
INSERT into offensive_player(teamId,name,position) values (5,'G.Olsen',4);
INSERT into offensive_player(teamId,name,position) values (5,'E.Dickson',4);
INSERT into offensive_player(teamId,name,position) values (6,'J.Cutler',1);
INSERT into offensive_player(teamId,name,position) values (6,'B.Hoyer',1);
INSERT into offensive_player(teamId,name,position) values (6,'K.Carey',3);
INSERT into offensive_player(teamId,name,position) values (6,'J.Howard',3);
INSERT into offensive_player(teamId,name,position) values (6,'J.Langford',3);
INSERT into offensive_player(teamId,name,position) values (6,'A.Jeffery',2);
INSERT into offensive_player(teamId,name,position) values (6,'E.Royal',2);
INSERT into offensive_player(teamId,name,position) values (6,'C.Meredith',2);
INSERT into offensive_player(teamId,name,position) values (6,'Z.Miller',4);
INSERT into offensive_player(teamId,name,position) values (7,'A.Dalton',1);
INSERT into offensive_player(teamId,name,position) values (7,'J.Hill',3);
INSERT into offensive_player(teamId,name,position) values (7,'G.Bernard',3);
INSERT into offensive_player(teamId,name,position) values (7,'A.Green',2);
INSERT into offensive_player(teamId,name,position) values (7,'B.LaFell',2);
INSERT into offensive_player(teamId,name,position) values (7,'T.Boyd',2);
INSERT into offensive_player(teamId,name,position) values (7,'T.Kroft',4);
INSERT into offensive_player(teamId,name,position) values (7,'C.Uzomah',4);
INSERT into offensive_player(teamId,name,position) values (8,'C.Kessler',1);
INSERT into offensive_player(teamId,name,position) values (8,'J.McCown',1);
INSERT into offensive_player(teamId,name,position) values (8,'I.Crowell',3);
INSERT into offensive_player(teamId,name,position) values (8,'D.Johnson',3);
INSERT into offensive_player(teamId,name,position) values (8,'C.Coleman',2);
INSERT into offensive_player(teamId,name,position) values (8,'T.Pryor',2);
INSERT into offensive_player(teamId,name,position) values (8,'A.Hawkins',2);
INSERT into offensive_player(teamId,name,position) values (8,'G.Barnidge',4);
INSERT into offensive_player(teamId,name,position) values (9,'D.Prescott',1);
INSERT into offensive_player(teamId,name,position) values (9,'E.Elliott',3);
INSERT into offensive_player(teamId,name,position) values (9,'A.Morris',3);
INSERT into offensive_player(teamId,name,position) values (9,'D.Bryant',2);
INSERT into offensive_player(teamId,name,position) values (9,'C.Beasley',2);
INSERT into offensive_player(teamId,name,position) values (9,'B.Butler',2);
INSERT into offensive_player(teamId,name,position) values (9,'T.Williams',2);
INSERT into offensive_player(teamId,name,position) values (9,'J.Witten',4);
INSERT into offensive_player(teamId,name,position) values (10,'T.Siemian',1);
INSERT into offensive_player(teamId,name,position) values (10,'C.Anderson',3);
INSERT into offensive_player(teamId,name,position) values (10,'D.Booker',3);
INSERT into offensive_player(teamId,name,position) values (10,'D.Thomas',2);
INSERT into offensive_player(teamId,name,position) values (10,'E.Sanders',2);
INSERT into offensive_player(teamId,name,position) values (10,'J.Norwood',2);
INSERT into offensive_player(teamId,name,position) values (10,'V.Green',4);
INSERT into offensive_player(teamId,name,position) values (11,'M.Stafford',1);
INSERT into offensive_player(teamId,name,position) values (11,'T.Riddick',3);
INSERT into offensive_player(teamId,name,position) values (11,'D.Washington',3);
INSERT into offensive_player(teamId,name,position) values (11,'M.Jones',2);
INSERT into offensive_player(teamId,name,position) values (11,'G.Tate',2);
INSERT into offensive_player(teamId,name,position) values (11,'A.Boldin',2);
INSERT into offensive_player(teamId,name,position) values (11,'E.Ebron',4);
INSERT into offensive_player(teamId,name,position) values (12,'A.Rodgers',1);
INSERT into offensive_player(teamId,name,position) values (12,'J.Starks',3);
INSERT into offensive_player(teamId,name,position) values (12,'R.Cobb',2);
INSERT into offensive_player(teamId,name,position) values (12,'J.Nelson',2);
INSERT into offensive_player(teamId,name,position) values (12,'D.Adams',2);
INSERT into offensive_player(teamId,name,position) values (12,'T.Montgomery',2);
INSERT into offensive_player(teamId,name,position) values (12,'J.Cook',4);
INSERT into offensive_player(teamId,name,position) values (12,'R.Rodgers',4);
INSERT into offensive_player(teamId,name,position) values (13,'B.Osweiler',1);
INSERT into offensive_player(teamId,name,position) values (13,'L.Miller',3);
INSERT into offensive_player(teamId,name,position) values (13,'A.Blue',3);
INSERT into offensive_player(teamId,name,position) values (13,'D.Hopkins',2);
INSERT into offensive_player(teamId,name,position) values (13,'W.Fuller',2);
INSERT into offensive_player(teamId,name,position) values (13,'J.Strong',2);
INSERT into offensive_player(teamId,name,position) values (13,'C.Fiedorowicz',4);
INSERT into offensive_player(teamId,name,position) values (13,'R.Griffin',4);
INSERT into offensive_player(teamId,name,position) values (14,'A.Luck',1);
INSERT into offensive_player(teamId,name,position) values (14,'F.Gore',3);
INSERT into offensive_player(teamId,name,position) values (14,'R.Turbin',3);
INSERT into offensive_player(teamId,name,position) values (14,'T.Hilton',2);
INSERT into offensive_player(teamId,name,position) values (14,'D.Moncrief',2);
INSERT into offensive_player(teamId,name,position) values (14,'P.Dorsett',2);
INSERT into offensive_player(teamId,name,position) values (14,'D.Allen',4);
INSERT into offensive_player(teamId,name,position) values (14,'J.Doyle',4);
INSERT into offensive_player(teamId,name,position) values (15,'B.Bortles',1);
INSERT into offensive_player(teamId,name,position) values (15,'T.Yeldon',3);
INSERT into offensive_player(teamId,name,position) values (15,'C.Ivory',3);
INSERT into offensive_player(teamId,name,position) values (15,'D.Robinson',3);
INSERT into offensive_player(teamId,name,position) values (15,'A.Robinson',2);
INSERT into offensive_player(teamId,name,position) values (15,'A.Hurns',2);
INSERT into offensive_player(teamId,name,position) values (15,'M.Lee',2);
INSERT into offensive_player(teamId,name,position) values (15,'J.Thomas',4);
INSERT into offensive_player(teamId,name,position) values (15,'M.Lewis',4);
INSERT into offensive_player(teamId,name,position) values (16,'A.Smith',1);
INSERT into offensive_player(teamId,name,position) values (16,'J.Charles',3);
INSERT into offensive_player(teamId,name,position) values (16,'S.Ware',3);
INSERT into offensive_player(teamId,name,position) values (16,'J.Maclin',2);
INSERT into offensive_player(teamId,name,position) values (16,'A.Wilson',2);
INSERT into offensive_player(teamId,name,position) values (16,'T.Kelce',4);
INSERT into offensive_player(teamId,name,position) values (17,'C.Keenum',1);
INSERT into offensive_player(teamId,name,position) values (17,'T.Gurley',3);
INSERT into offensive_player(teamId,name,position) values (17,'T.Austin',2);
INSERT into offensive_player(teamId,name,position) values (17,'K.Britt',2);
INSERT into offensive_player(teamId,name,position) values (17,'B.Quick',2);
INSERT into offensive_player(teamId,name,position) values (17,'L.Kendricks',4);
INSERT into offensive_player(teamId,name,position) values (18,'R.Tannehill',1);
INSERT into offensive_player(teamId,name,position) values (18,'A.Foster',3);
INSERT into offensive_player(teamId,name,position) values (18,'K.Drake',3);
INSERT into offensive_player(teamId,name,position) values (18,'J.Ajayi',3);
INSERT into offensive_player(teamId,name,position) values (18,'J.Landry',2);
INSERT into offensive_player(teamId,name,position) values (18,'K.Stills',2);
INSERT into offensive_player(teamId,name,position) values (18,'D.Parker',2);
INSERT into offensive_player(teamId,name,position) values (18,'J.Cameron',4);
INSERT into offensive_player(teamId,name,position) values (19,'S.Bradford',1);
INSERT into offensive_player(teamId,name,position) values (19,'S.Hill',1);
INSERT into offensive_player(teamId,name,position) values (19,'J.McKinnon',3);
INSERT into offensive_player(teamId,name,position) values (19,'M.Asiata',3);
INSERT into offensive_player(teamId,name,position) values (19,'A.Peterson',3);
INSERT into offensive_player(teamId,name,position) values (19,'S.Diggs',2);
INSERT into offensive_player(teamId,name,position) values (19,'C.Patterson',2);
INSERT into offensive_player(teamId,name,position) values (19,'K.Rudolph',4);
INSERT into offensive_player(teamId,name,position) values (20,'T.Brady',1);
INSERT into offensive_player(teamId,name,position) values (20,'J.Garoppolo',1);
INSERT into offensive_player(teamId,name,position) values (20,'J.White',3);
INSERT into offensive_player(teamId,name,position) values (20,'L.Blount',3);
INSERT into offensive_player(teamId,name,position) values (20,'J.Edelman',2);
INSERT into offensive_player(teamId,name,position) values (20,'D.Amendola',2);
INSERT into offensive_player(teamId,name,position) values (20,'C.Hogan',2);
INSERT into offensive_player(teamId,name,position) values (20,'R.Gronkowski',4);
INSERT into offensive_player(teamId,name,position) values (20,'M.Bennett',4);
INSERT into offensive_player(teamId,name,position) values (21,'D.Brees',1);
INSERT into offensive_player(teamId,name,position) values (21,'M.Ingram',3);
INSERT into offensive_player(teamId,name,position) values (21,'B.Cooks',2);
INSERT into offensive_player(teamId,name,position) values (21,'W.Snead',2);
INSERT into offensive_player(teamId,name,position) values (21,'M.Thomas',2);
INSERT into offensive_player(teamId,name,position) values (21,'C.Fleener',4);
INSERT into offensive_player(teamId,name,position) values (22,'E.Manning',1);
INSERT into offensive_player(teamId,name,position) values (22,'R.Jennings',3);
INSERT into offensive_player(teamId,name,position) values (22,'B.Rainey',3);
INSERT into offensive_player(teamId,name,position) values (22,'O.Beckham',2);
INSERT into offensive_player(teamId,name,position) values (22,'V.Cruz',2);
INSERT into offensive_player(teamId,name,position) values (22,'S.Shepard',2);
INSERT into offensive_player(teamId,name,position) values (22,'L.Donnell',4);
INSERT into offensive_player(teamId,name,position) values (22,'W.Tye',4);
INSERT into offensive_player(teamId,name,position) values (23,'R.Fitzpatrick',1);
INSERT into offensive_player(teamId,name,position) values (23,'M.Forte',3);
INSERT into offensive_player(teamId,name,position) values (23,'B.Powell',3);
INSERT into offensive_player(teamId,name,position) values (23,'B.Marshall',2);
INSERT into offensive_player(teamId,name,position) values (23,'Q.Enuwa',2);
INSERT into offensive_player(teamId,name,position) values (23,'B.Bostick',4);
INSERT into offensive_player(teamId,name,position) values (24,'D.Carr',1);
INSERT into offensive_player(teamId,name,position) values (24,'L.Murray',3);
INSERT into offensive_player(teamId,name,position) values (24,'D.Washington',3);
INSERT into offensive_player(teamId,name,position) values (24,'J.Richard',3);
INSERT into offensive_player(teamId,name,position) values (24,'A.Cooper',2);
INSERT into offensive_player(teamId,name,position) values (24,'M.Crabtree',2);
INSERT into offensive_player(teamId,name,position) values (24,'M.Rivera',4);
INSERT into offensive_player(teamId,name,position) values (24,'C.Walford',4);
INSERT into offensive_player(teamId,name,position) values (25,'C.Wentz',1);
INSERT into offensive_player(teamId,name,position) values (25,'R.Mathews',3);
INSERT into offensive_player(teamId,name,position) values (25,'D.Sproles',3);
INSERT into offensive_player(teamId,name,position) values (25,'W.Smallwood',3);
INSERT into offensive_player(teamId,name,position) values (25,'J.Matthews',2);
INSERT into offensive_player(teamId,name,position) values (25,'N.Agholor',2);
INSERT into offensive_player(teamId,name,position) values (25,'Z.Ertz',4);
INSERT into offensive_player(teamId,name,position) values (25,'B.Celek',4);
INSERT into offensive_player(teamId,name,position) values (26,'B.Roethlisberger',1);
INSERT into offensive_player(teamId,name,position) values (26,'L.Jones',1);
INSERT into offensive_player(teamId,name,position) values (26,'L.Bell',3);
INSERT into offensive_player(teamId,name,position) values (26,'D.Williams',3);
INSERT into offensive_player(teamId,name,position) values (26,'A.Brown',2);
INSERT into offensive_player(teamId,name,position) values (26,'S.Coates',2);
INSERT into offensive_player(teamId,name,position) values (26,'M.Wheaton',2);
INSERT into offensive_player(teamId,name,position) values (26,'D.Heyward-Bey',2);
INSERT into offensive_player(teamId,name,position) values (26,'J.Hames',4);
INSERT into offensive_player(teamId,name,position) values (27,'P,Rivers',1);
INSERT into offensive_player(teamId,name,position) values (27,'M.Gordon',3);
INSERT into offensive_player(teamId,name,position) values (27,'T.Benjamin',2);
INSERT into offensive_player(teamId,name,position) values (27,'T.Williams',2);
INSERT into offensive_player(teamId,name,position) values (27,'A.Gates',4);
INSERT into offensive_player(teamId,name,position) values (27,'H.Henry',4);
INSERT into offensive_player(teamId,name,position) values (28,'R.Wilson',1);
INSERT into offensive_player(teamId,name,position) values (28,'C.Michael',3);
INSERT into offensive_player(teamId,name,position) values (28,'D.Baldwin',2);
INSERT into offensive_player(teamId,name,position) values (28,'T.Lockett',2);
INSERT into offensive_player(teamId,name,position) values (28,'J.Graham',4);
INSERT into offensive_player(teamId,name,position) values (29,'B.Gabbert',1);
INSERT into offensive_player(teamId,name,position) values (29,'C.Kaepernick',1);
INSERT into offensive_player(teamId,name,position) values (29,'C.Hyde',3);
INSERT into offensive_player(teamId,name,position) values (29,'T.Smith',2);
INSERT into offensive_player(teamId,name,position) values (29,'J.Kerley',2);
INSERT into offensive_player(teamId,name,position) values (30,'J.Winston',1);
INSERT into offensive_player(teamId,name,position) values (30,'D.Martin',3);
INSERT into offensive_player(teamId,name,position) values (30,'J.Rodgers',3);
INSERT into offensive_player(teamId,name,position) values (30,'M.Evans',2);
INSERT into offensive_player(teamId,name,position) values (30,'A.Humphries',2);
INSERT into offensive_player(teamId,name,position) values (30,'C.Brate',4);
INSERT into offensive_player(teamId,name,position) values (31,'M.Mariota',1);
INSERT into offensive_player(teamId,name,position) values (31,'D.Murray',3);
INSERT into offensive_player(teamId,name,position) values (31,'R.Matthews',2);
INSERT into offensive_player(teamId,name,position) values (31,'K.Wright',2);
INSERT into offensive_player(teamId,name,position) values (31,'T.Sharpe',2);
INSERT into offensive_player(teamId,name,position) values (31,'D.Walker',4);
INSERT into offensive_player(teamId,name,position) values (32,'K.Cousins',1);
INSERT into offensive_player(teamId,name,position) values (32,'M.Jones',3);
INSERT into offensive_player(teamId,name,position) values (32,'J.Crowder',2);
INSERT into offensive_player(teamId,name,position) values (32,'D.Jackson',2);
INSERT into offensive_player(teamId,name,position) values (32,'P.Garcon',2);
INSERT into offensive_player(teamId,name,position) values (32,'J.Reed',4);
INSERT into offensive_player(teamId,name,position) values (32,'V.Davis',4);

INSERT into fantasy_scoring(description,
	reception,rush_yard,rec_yard,pass_yard,
	rush_touchdown,rec_touchdown,pass_touchdown,
	interception,fumble_lost,def_0_points,
	def_6_points,def_13_points,def_17_points,
	def_27_points,def_34_points,def_45_points,
	def_over_45_points,def_fumble_rec,
	def_interception,def_sack) values
	('Standard',0.0,0.1,0.1,0.04,6,6,4,-2,-2,5,4,3,1,0,-1,-3,-5,2,2,1);
INSERT into fantasy_scoring(description,
	reception,rush_yard,rec_yard,pass_yard,
	rush_touchdown,rec_touchdown,pass_touchdown,
	interception,fumble_lost,def_0_points,
	def_6_points,def_13_points,def_17_points,
	def_27_points,def_34_points,def_45_points,
	def_over_45_points,def_fumble_rec,
	def_interception,def_sack) values
	('Half PPR',0.5,0.1,0.1,0.04,6,6,4,-2,-2,5,4,3,1,0,-1,-3,-5,2,2,1);
INSERT into fantasy_scoring(description,
	reception,rush_yard,rec_yard,pass_yard,
	rush_touchdown,rec_touchdown,pass_touchdown,
	interception,fumble_lost,def_0_points,
	def_6_points,def_13_points,def_17_points,
	def_27_points,def_34_points,def_45_points,
	def_over_45_points,def_fumble_rec,
	def_interception,def_sack) values
	('Full PPR',1.0,0.1,0.1,0.04,6,6,4,-2,-2,5,4,3,1,0,-1,-3,-5,2,2,1);