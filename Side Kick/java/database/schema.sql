BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

CREATE SEQUENCE seq_collections_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE collections (
	collection_id int DEFAULT nextval('seq_collections_id'::regclass) NOT NULL,
	name varchar(50) NOT NULL,
	username varchar(50),
	user_id int NOT NULL,
	visibility varchar(20) NOT NULL,

	CONSTRAINT pk_collections PRIMARY KEY (collection_id)
);

INSERT INTO collections (name, username, user_id, visibility) VALUES (E'Nick\'s Awesome Collection', 'ComicLord77', 3, 'Public');
INSERT INTO collections (name, username, user_id, visibility) VALUES (E'Clay\'s Rockin\' Collection', 'SecretAsianMan', 4, 'Public');
INSERT INTO collections (name, username, user_id, visibility) VALUES (E'Chris\' Jammin\' Collection', 'COMIC-KAZE', 5, 'Public');


CREATE SEQUENCE seq_comics_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE comics (
	comic_id int DEFAULT nextval('seq_comics_id'::regclass) NOT NULL,
	title varchar(300) NOT NULL,
	volume varchar(300),
	issue_number int,
	category varchar(100),
	main_character varchar(100),
	description varchar(400),
	publisher varchar(100),
	year_published int,
	imagename varchar(300),
	
	CONSTRAINT pk_comics PRIMARY KEY (comic_id)
);

INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('The Big Burn: Ignition', 'Batman and Robin' , 26, 'Action','Batman',E'More light is shed on Erin McKillen\'s story and Two-Face sets a plan in motion.','DC Comics', 2013, 'batmanrobin.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('The Riddle of the Missing Card', 'Batman', 5, 'Action', 'Batman', 'The Joker appears along with numerous nefarious villains to squash our bat hero...', 'DC Comics', 1941, 'batman5.png');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('Sisterhood Part 3', 'The Uncanny X-Men', 510, 'Action', 'X-Men', E'The Sisterhood executes a massive raid on the Graymalkin Facility, taking their fight to the Uncanny X-Men, and you won\'t believe the ferocity!', 'Marvel', 2009, 'uncannyx-men510.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('Doomsday!', 'Superman', 75, 'Action', 'Superman', 'Superman fights his strongest opponent to the death in the streets of Metropolis...', 'DC Comics', 1992, 'superman75.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('Captain Underpants and the Perilous Plot of Professor Poopypants', 'Captain Underpants', 4, 'Humor', 'Captain Underpants', 'George and Harold almost cause the entire planet to be taken over by a maniacal, mad-scientist guy in a giant robot suit! Who will stop the perilous plot of Poopypants?!!? ', 'Scholastic', 2000, 'cptunderpants.jpeg' );
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('Fever Dream', 'The Uncanny X-Men', 251, 'Action', 'X-Men', 'Wolverine is being savagely tortured by the anti-mutant extremists known as The Reavers. In his semi-conscious state, Wolverine begins to hallucinate about the past and future for mutantkind.', 'Marvel', 1989, 'feverdream.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('Counting Coup Part 2!', 'Wonder Woman', 213, 'Action', 'Wonder Woman', E'It\'s war on Olympus as Wonder Woman is embroiled in the battle between Zeus and Athena!', 'DC Comics', 2005, 'wonderwoman.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('Earthquake Logic Part 3: Seismics', 'Doctor Strange, Sorcerer Supreme', 82, 'Action', 'Doctor Strange', 'Doc has the rareified opportunity to meet the leader of the Coven…before he decides to destroy it.', 'Marvel', 1995, 'doctorstrange.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('God', 'The Infinity Gauntlet', 1, 'Action', 'The Avengers', E'For Thanos, the Infinity Gauntlet was the ultimate prize to be coveted above all else. With it came omnipotence. Now it\'s up to Earth\'s super heroes to make a desperate attempt to thwart this mad god\'s insane plunge into galactic self-destruction.', 'Marvel', 1991, 'infinitygauntlet.jpeg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('Elliptical Pursuit!', 'The Amazing Spider-Man', 346, 'Action', 'Spider-Man', E'Eddie Brock is back…for Peter’s head! After escaping prison, Venom taunts Spider-Man into a final showdown. Peter Parker’s known for his smarts- why then does he stumble straight into Venom’s trap?', 'Marvel', 1991, 'spiderman2.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('And Be A Villian', 'Suicide Squad', 66, 'Action', 'Suicide Squad', 'Final Issue! Amanda Waller and what remains of the Suicide Squad face a villain that may be as powerful and cold-hearted as Death itself.', 'DC Comics', 1992, 'suicidesquad.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('Autumn', 'Black Widow', 13, 'Action', 'Black Widow', 'The world has turned against Black Widow. Her web is broken. How will she deal with this?', 'Marvel', 2014, 'blackwidow.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('Alone Against A.I.M.!', 'Iron Man', 1, 'Action', 'Iron Man', E'It\'s the big Premiere! The Invincible Iron Man versus the menaces of Whiplash and AIM. Featuring Nick Fury and SHIELD.', 'Marvel', 1968, 'ironman.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('Crisis in the Thunderbolt Dimension!', 'Justice League of America', 219, 'Action', 'Justice League', E'Johnny Thunder’s Thunderbolt invades the annual JLA/JSA reunion and attacks the assembled heroes, but, strangely, incapacitates only those born on Earth-1, while leaving Black Canary, Red Tornado and the Justice Society members unhurt.', 'DC Comics', 1983, 'justiceleague.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('Guilding Day', 'Supergirl', 43, 'Action', 'Supergirl', 'Supergirl writes a letter to her deceased father on the day she is to choose her guild on New Krypton.', 'DC Comics', 2009, 'supergirl.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('Goku or Ginyu!?', 'Dragon Ball', 24, 'Action', 'Goku', 'Its Goku versus the Ginyu Force!', 'Jump Comics', 1991, 'dragonball.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('Recording Tape','Hawk Eye', 16, 'Action', 'Hawk Eye', 'The adventures of Lady Hawkguy out west continue as Kate helps a reclusive and Sixties-damaged pop music genius find his lost masterpiece.', 'Marvel', 2014, 'Hawkeye.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('The Trial Of Jean Grey', 'All New X-Men', 22, 'Action', 'X-Men', 'When alien races learn that Jean Grey, host of the destructive Phoenix Force, is back on earth, they do something about it.', 'Marvel', 2014, 'xmang.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('Let There Be Light', 'Pandora', 7, 'Action', 'Pandora', 'A major turning point for Pandora, who reluctantly joins forces with John Constantine’s team of mystical underdogs to confront the personification of all evil: Blight!', 'DC Comics', 2014, 'pandora.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('Brainiac, Part 3 - Greetings', 'Action Comics', 868, 'Action', 'Superman', 'Superman journeys to the deepest reaches of space to initiate contact with an alien being he thought he knew: Brainiac.', 'DC Comics', 2008, 'brainiac.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('Trial Of The Mask', 'Adventures of the Mask', 12, 'Action', 'The Mask', 'Bigger than the Menendez brothers! More controversial than O.J.! It is the trial of the millennium -- the trial of The Mask!', 'Dark Horse Comics', 1996, 'mask.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('Annihilation, Book One', 'Annihilation', 1, 'Action', 'Nova', 'The Annihilation Wave has come! Annihilus, lord of the Negative Zone, has declared war! Hordes of loyal soldiers swarm from the center of the universe, and only a handful of heroes can resist destruction!','Marvel', 2007, 'annihilation.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('The Doom that Came to Robo', 'Atomic Robo and the Shadow From Beyond Time', 2, 'Sci-Fi', 'Atomic Robo', 'ATOMIC ROBO must stop THE SHADOW FROM BEYOND TIME before it swallows the world in darkness forever', 'IDW Publishing', 2009, 'robo.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('Black to the Future', 'Black Panther Annual', 1, 'Action', 'Black Panther', 'T’Challa prepares the next Black Panther for the great responsibility ahead, he must first reflect on the road traveled–a long and winding journey filled with surprises.', 'Marvel', 2008, 'panther.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('The Kingpin Must Die', 'Daredevil', 170, 'Action', 'Daredevil', E'Retired in Japan, Kingpin sends his wife, Vanessa, to New York to hire Nelson and Murdock. Kingpin\'s plan is to turn the state\'s evidence against the East Coast crime bosses.', 'Marvel', 1981, 'daredevil.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('Decalogue, Part 3', 'Daredevil', 73, 'Action', 'Daredevil', E'A new costumed foe takes to the streets of Hell\'s Kitchen. Who is he and what will his effect on Daredevil\'s new status quo be?', 'Marvel', 2005, 'dare.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('The Scorpion', 'Blue Beetle', 50, 'Action', 'Blue Beetle', 'Our hero the Blue Beetle goes toe to toe with THE SCORPION!!!', 'DC Comics', 1965, 'bluebeetle.jpg' );
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('The Impostor', 'Korak, Son of Tarzan', 12, 'Action', 'Korak', E'A false Tarzan, a cave of genetic freaks... it\'s just another working week for Korak!', 'Gold Key Publishing', 1966, 'korak.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('Never Bother a Barracuda', 'Sub-Mariner', 10, 'Action', 'Sub-Mariner', 'Prince Namor has many obstacles in his way in an attempt to capture the Serpent Crown…one of them being the creature named Barracuda!', 'Marvel', 1969, 'submariner.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('Arctic Standoff - Part 1', 'Captain Canuck', 1, 'Canadian', 'Captain Canuck', 'After Canada becomes the most important country in the world two men of unequaled physical and mental prowess,Captain Canuck and Bluefox, begin their journey to protect Canada.', 'Comely Comix', 1975, 'canuck.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('The Haunted House', 'Felix the Cat', 105, 'Humor', 'Felix the Cat', 'Felix finds himself in the most haunted of houses. Should Felix be scared or should the Ghosts?!', 'Harvey Comics', 105, 'felix.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('Comic Capers and Curios', 'The Adventures of Cuphead', 1, 'Adventure', 'Cuphead', 'Prone to unexpected predicaments and thrilling adventures, Cuphead and Mugman feature front and center in a series of short but sweet side stories that reimagine the world of the all-cartoon magical wondergame.', 'Dark Horse Publishing', 2020, 'cup.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('Monarch of Monster Isle', 'Kona', 1, 'Adventure', 'Kona', 'Monarch of Monster Isle tells the story of a strange hero known only as Kona, saving the lives of Dr. Henry Dodd, his daughter Mary, and his grandchildren Mason and Lily, after this group crashes their army surplus blimp on the prehistoric Pacific island that Kona calls home. Befriending them, Kona becomes their protector, saving them from giant animals, monsters and much more!', 'Dell Comics', 2020, 'kona.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('Lethal Legion', 'Dark Reign: Lethal Legion', 1, 'Action', 'Grim Reaper', E'When Norman Osborn takes over, what happens to the villains who don\'t make his short list of acceptable allies?', 'Marvel', 2009, 'grim.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('The Hero and The Hammer', 'Thor', 390, 'Action', 'Thor', 'Avengers Island is attacked and the Mighty Thor assists the Avengers in defending the island.', 'Marvel', 1988, 'thor.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('The Longest Winter, Part 3', 'Winter Soldier', 3, 'Action', 'Winter Soldier', 'Winter Soldier versus Doctor Doom! Nuff Said!!!', 'Marvel', 2012, 'winter.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('Si, Change', 'Deadpool', 36, 'Action', 'Deadpool', 'SECRET EMPIRE AFTERMATH! Everything works out great and Deadpool gets a happy ending! Wait...are we allowed to lie in solicits? We are, right?', 'Marvel', 2017, 'pool.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('Red Light, Green Light', 'Hulk', 4, 'Action', 'Hulk', E'This is it folks! What everyone has been waiting for. It\'s Hulk vs Hulk and we know how he loves to smash. So be ready for everything. In fact, even Uatu wants to watch it!', 'Marvel', 2008, 'hulk.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('The Sad Saga Of Willie The Weeper', 'The Joker', 2, 'Action', 'The Joker', 'The Joker teams up with Willie the Weeper when Willie breaks him out of Arkham. The Joker then helps him steal platinum but is thwarted by Gordon.', 'DC Comics', 1975, 'joker.jpg');
INSERT INTO comics (title, volume, issue_number, category, main_character, description, publisher, year_published, imagename)
VALUES ('Wolverine Forever', 'Wolverine', 16, 'Action', 'Wolverine', E'In the wake of last issue\'s gut-wrenching ending, Logan has turned his back on humanity and retreated once again to the Canadian wilderness. Is this the end of Wolverine as we know him?', 'Marvel', 2011, 'wolf.jpg');

CREATE TABLE comics_collections (
	comic_id int NOT NULL,
	collection_id int NOT NULL
);

INSERT INTO comics_collections VALUES (1, 1);
INSERT INTO comics_collections VALUES (2, 1);
INSERT INTO comics_collections VALUES (3, 1);
INSERT INTO comics_collections VALUES (4, 1);
INSERT INTO comics_collections VALUES (5, 1);
INSERT INTO comics_collections VALUES (6, 1);
INSERT INTO comics_collections VALUES (7, 1);
INSERT INTO comics_collections VALUES (8, 1);
INSERT INTO comics_collections VALUES (9, 1);
INSERT INTO comics_collections VALUES (10, 1);
INSERT INTO comics_collections VALUES (11, 1);
INSERT INTO comics_collections VALUES (12, 1);
INSERT INTO comics_collections VALUES (13, 1);
INSERT INTO comics_collections VALUES (14, 1);
INSERT INTO comics_collections VALUES (15, 1);
INSERT INTO comics_collections VALUES (16, 1);
INSERT INTO comics_collections VALUES (17, 1);
INSERT INTO comics_collections VALUES (18, 1);
INSERT INTO comics_collections VALUES (19, 1);
INSERT INTO comics_collections VALUES (20, 1);
INSERT INTO comics_collections VALUES (21, 1);
INSERT INTO comics_collections VALUES (30, 1);
INSERT INTO comics_collections VALUES (32, 1);
INSERT INTO comics_collections VALUES (34, 1);
INSERT INTO comics_collections VALUES (36, 1);
INSERT INTO comics_collections VALUES (38, 1);
INSERT INTO comics_collections VALUES (31, 1);

INSERT INTO comics_collections VALUES (3, 2);
INSERT INTO comics_collections VALUES (4, 2);
INSERT INTO comics_collections VALUES (5, 2);
INSERT INTO comics_collections VALUES (6, 2);
INSERT INTO comics_collections VALUES (27, 2);
INSERT INTO comics_collections VALUES (9, 2);
INSERT INTO comics_collections VALUES (12, 2);
INSERT INTO comics_collections VALUES (13, 2);
INSERT INTO comics_collections VALUES (14, 2);
INSERT INTO comics_collections VALUES (16, 2);
INSERT INTO comics_collections VALUES (22, 2);
INSERT INTO comics_collections VALUES (23, 2);
INSERT INTO comics_collections VALUES (24, 2);
INSERT INTO comics_collections VALUES (25, 2);
INSERT INTO comics_collections VALUES (26, 2);
INSERT INTO comics_collections VALUES (31, 2);
INSERT INTO comics_collections VALUES (33, 2);
INSERT INTO comics_collections VALUES (35, 2);
INSERT INTO comics_collections VALUES (37, 2);
INSERT INTO comics_collections VALUES (39, 2);
INSERT INTO comics_collections VALUES (18, 2);

INSERT INTO comics_collections VALUES (8, 3);
INSERT INTO comics_collections VALUES (9, 3);
INSERT INTO comics_collections VALUES (4, 3);
INSERT INTO comics_collections VALUES (5, 3);
INSERT INTO comics_collections VALUES (2, 3);
INSERT INTO comics_collections VALUES (3, 3);
INSERT INTO comics_collections VALUES (10, 3);
INSERT INTO comics_collections VALUES (11, 3);
INSERT INTO comics_collections VALUES (7, 3);
INSERT INTO comics_collections VALUES (6, 3);
INSERT INTO comics_collections VALUES (27, 3);
INSERT INTO comics_collections VALUES (28, 3);
INSERT INTO comics_collections VALUES (29, 3);
INSERT INTO comics_collections VALUES (18, 3);
INSERT INTO comics_collections VALUES (20, 3);
INSERT INTO comics_collections VALUES (32, 3);
INSERT INTO comics_collections VALUES (33, 3);
INSERT INTO comics_collections VALUES (34, 3);
INSERT INTO comics_collections VALUES (35, 3);
INSERT INTO comics_collections VALUES (36, 3);
INSERT INTO comics_collections VALUES (37, 3);
INSERT INTO comics_collections VALUES (40, 3);


CREATE SEQUENCE seq_trade_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE trades (
	trade_id int DEFAULT nextval('seq_trade_id'::regclass) NOT NULL,
	sender_id int NOT NULL,
	receiver_id int NOT NULL,
	trade_status int NOT NULL,

CONSTRAINT pk_trade PRIMARY KEY (trade_id)	

);

CREATE TABLE sender_comics_trades (
	sender_comic_id int,
	trade_id int NOT NULL
);

CREATE TABLE receiver_comics_trades (
	receiver_comic_id int,
	trade_id int NOT NULL
);

CREATE TABLE wish_list (
	user_id int NOT NULL,
	comic_id int NOT NULL
);

COMMIT TRANSACTION;
