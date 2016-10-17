Use final_project;

Drop table if exists defensive_stat;
Drop table if exists defensive_team;
Drop table if exists offensive_stat;
Drop table if exists offensive_player;
Drop table if exists game_result;
Drop table if exists matchup;
Drop table if exists team;

Create table team (
id int primary key auto_increment,
division varchar(30),
teamName varchar(40),
abrev varchar(5),
wins int,
losses int,
ties int,
ovrRank int
);

INSERT into team (division, TeamName, abrev) values ('NFC West', 'Arizona Cardinals', 'ARI');
INSERT into team (division, TeamName, abrev) values ('NFC South', 'Atlanta Falcons', 'ATL');
INSERT into team (division, TeamName, abrev) values ('AFC North', 'Baltimore Ravens', 'BAL');
INSERT into team (division, TeamName, abrev) values ('AFC East', 'Buffalo Bills', 'BUF');
INSERT into team (division, TeamName, abrev) values ('NFC South', 'Carolina Panthers', 'CAR');
INSERT into team (division, TeamName, abrev) values ('NFC North', 'Chicago Bears', 'CHI');
INSERT into team (division, TeamName, abrev) values ('AFC North', 'Cincinatti Bengals', 'CIN');
INSERT into team (division, TeamName, abrev) values ('AFC North', 'Cleveland Browns', 'CLE');
INSERT into team (division, TeamName, abrev) values ('NFC East', 'Dallas Cowboys', 'DAL');
INSERT into team (division, TeamName, abrev) values ('AFC West', 'Denver Broncos', 'DEN');
INSERT into team (division, TeamName, abrev) values ('NFC North', 'Detroit Lions', 'DET');
INSERT into team (division, TeamName, abrev) values ('NFC North', 'Green Bay Packers', 'GB');
INSERT into team (division, TeamName, abrev) values ('AFC South', 'Houston Texans', 'HOU');
INSERT into team (division, TeamName, abrev) values ('AFC South', 'Indianapolis Colts', 'IND');
INSERT into team (division, TeamName, abrev) values ('AFC South', 'Jacksonville Jaguars', 'JAX');
INSERT into team (division, TeamName, abrev) values ('AFC West', 'Kansas City Chiefs', 'KC');
INSERT into team (division, TeamName, abrev) values ('NFC West', 'Los Angeles Rams', 'LA');
INSERT into team (division, TeamName, abrev) values ('AFC East', 'Miami Dolphins', 'MIA');
INSERT into team (division, TeamName, abrev) values ('NFC North', 'Minnesota Vikings', 'MIN');
INSERT into team (division, TeamName, abrev) values ('AFC East', 'New England Patriots', 'NE');
INSERT into team (division, TeamName, abrev) values ('NFC South', 'New Orleans Saints', 'NO');
INSERT into team (division, TeamName, abrev) values ('NFC East', 'New York Giants', 'NYG');
INSERT into team (division, TeamName, abrev) values ('AFC East', 'New York Jets', 'NYJ');
INSERT into team (division, TeamName, abrev) values ('AFC South', 'Oakland Raiders', 'OAK');
INSERT into team (division, TeamName, abrev) values ('NFC East', 'Philadelphia Eagles', 'PHI');
INSERT into team (division, TeamName, abrev) values ('AFC North', 'Pittsburgh Steelers', 'PIT');
INSERT into team (division, TeamName, abrev) values ('AFC West', 'San Diego Chargers', 'SD');
INSERT into team (division, TeamName, abrev) values ('NFC West', 'San Francisco 49ers', 'SF');
INSERT into team (division, TeamName, abrev) values ('NFC West', 'Seattle Seahawks', 'SEA');
INSERT into team (division, TeamName, abrev) values ('NFC South', 'Tampa Bay Buccaneers', 'TB');
INSERT into team (division, TeamName, abrev) values ('AFC South', 'Tennessee Titans', 'TEN');
INSERT into team (division, TeamName, abrev) values ('NFC East', 'Washington Redskins', 'WAS');

Create table offensive_player (
id int primary key auto_increment,
name varchar(30),
teamId int,
position varchar(30),
posRanking int,
CONSTRAINT fk_team FOREIGN KEY (teamId)
REFERENCES team (id)
);

Create table offensive_stat (
id int primary key auto_increment,
playerId int,
passAttempts int,
passCompletions int,
passYards int,
passTouchdowns int,
receptions int,
recYards int,
rushAttempts int,
rushYards int,
scoredTouchdowns int,
fieldGoals int,
CONSTRAINT fk_player FOREIGN KEY (playerId)
REFERENCES offensive_player (id)
);

Create table defensive_team (
id int primary key auto_increment,
teamId int,
defRanking int,
CONSTRAINT fk_defteam FOREIGN KEY (teamId)
REFERENCES team (id)
);

INSERT into defensive_team (teamId) values (1);
INSERT into defensive_team (teamId) values (2);
INSERT into defensive_team (teamId) values (3);
INSERT into defensive_team (teamId) values (4);
INSERT into defensive_team (teamId) values (5);
INSERT into defensive_team (teamId) values (6);
INSERT into defensive_team (teamId) values (7);
INSERT into defensive_team (teamId) values (8);
INSERT into defensive_team (teamId) values (9);
INSERT into defensive_team (teamId) values (10);
INSERT into defensive_team (teamId) values (11);
INSERT into defensive_team (teamId) values (12);
INSERT into defensive_team (teamId) values (13);
INSERT into defensive_team (teamId) values (14);
INSERT into defensive_team (teamId) values (15);
INSERT into defensive_team (teamId) values (16);
INSERT into defensive_team (teamId) values (17);
INSERT into defensive_team (teamId) values (18);
INSERT into defensive_team (teamId) values (19);
INSERT into defensive_team (teamId) values (20);
INSERT into defensive_team (teamId) values (21);
INSERT into defensive_team (teamId) values (22);
INSERT into defensive_team (teamId) values (23);
INSERT into defensive_team (teamId) values (24);
INSERT into defensive_team (teamId) values (25);
INSERT into defensive_team (teamId) values (26);
INSERT into defensive_team (teamId) values (27);
INSERT into defensive_team (teamId) values (28);
INSERT into defensive_team (teamId) values (29);
INSERT into defensive_team (teamId) values (30);
INSERT into defensive_team (teamId) values (31);
INSERT into defensive_team (teamId) values (32);

Create table defensive_stat (
id int primary key auto_increment,
yardsAllowed int,
defenseId int,
tackles int,
sacks int,
interceptions int,
fumbleRcvry int,
safety int,
CONSTRAINT fk_dteam FOREIGN KEY (defenseId)
REFERENCES defensive_team (id)
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
tie boolean,
CONSTRAINT fk_winteam FOREIGN KEY (winTeamId)
REFERENCES team (id),
CONSTRAINT fk_loseteam FOREIGN KEY (loseTeamId)
REFERENCES team (id),
CONSTRAINT fk_matchup FOREIGN KEY (matchupId)
REFERENCES matchup (id)
);
