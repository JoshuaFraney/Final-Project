Use final_project;

Drop table if exists defensive_stat;
Drop table if exists defensive_team;
Drop table if exists offensive_stat;
Drop table if exists offensive_player;
Drop table if exists game_result;
Drop table if exists matchup;
Drop table if exists team;
Drop table if exists position;

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
ovrRank int
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

Create table offensive_stat (
id int primary key auto_increment,
playerId int,
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
REFERENCES offensive_player (id)
);

Create table defensive_stat (
id int primary key auto_increment,
passYardsAllowed int,
rushYardsAllowed int,
teamId int,
pointsAllowed int,
sacks int,
interceptions int,
fumbleRcvry int,
defRanking int,
CONSTRAINT fk_dteam FOREIGN KEY (teamId)
REFERENCES team (id)
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

INSERT into position (code,description) values ('QB','Quarterback');
INSERT into position (code,description) values ('WR','Wide Reciever');
INSERT into position (code,description) values ('RB','Running Back');
INSERT into position (code,description) values ('TE','Tight End');
