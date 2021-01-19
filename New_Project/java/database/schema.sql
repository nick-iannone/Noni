BEGIN TRANSACTION;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS cards CASCADE;
DROP TABLE IF EXISTS decks CASCADE;
DROP TABLE IF EXISTS decks_cards CASCADE;
DROP TABLE IF EXISTS decks_contracts CASCADE;
DROP TABLE IF EXISTS contracts CASCADE;
DROP TABLE IF EXISTS histories CASCADE;
DROP TABLE IF EXISTS threads CASCADE;
DROP TABLE IF EXISTS threads_histories CASCADE;
DROP TABLE IF EXISTS messages CASCADE;
DROP TABLE IF EXISTS threads_messages CASCADE;
DROP SEQUENCE IF EXISTS seq_user_id;
DROP SEQUENCE IF EXISTS seq_card_id;
DROP SEQUENCE IF EXISTS seq_deck_id;
DROP SEQUENCE IF EXISTS seq_cont_id;
DROP SEQUENCE IF EXISTS seq_history_id;
DROP SEQUENCE IF EXISTS seq_thread_id;
DROP SEQUENCE IF EXISTS seq_message_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_card_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_deck_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_cont_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_history_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_thread_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_message_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
        status varchar(20) DEFAULT 'offline' NOT NULL,
        session_id varchar(8),
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role, status) 
VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'offline');
INSERT INTO users (username,password_hash,role, status) 
VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', 'offline');

CREATE TABLE histories (
        history_id int DEFAULT nextval('seq_history_id'::regClass) NOT NULL,
        user_id int NOT NULL,
        CONSTRAINT PK_history PRIMARY KEY (history_id),
        CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(user_id) 
);

CREATE TABLE threads (
        thread_id int DEFAULT nextval('seq_thread_id'::regClass) NOT NULL,
        owner_username varchar(20) NOT NULL,
        chat_with varchar(20) NOT NULL,
        CONSTRAINT PK_thread PRIMARY KEY (thread_id)
);

CREATE TABLE messages (
        message_id int DEFAULT nextval('seq_message_id'::regClass) NOT NULL,
        from_username varchar(20),
        to_username varchar(20),
        content varchar(1000),
        timestamp varchar(50),
        CONSTRAINT PK_message PRIMARY KEY (message_id)
);

CREATE TABLE threads_histories (
        history_id int,
        thread_id int,
        CONSTRAINT fk_history FOREIGN KEY (history_id) REFERENCES histories(history_id),
        CONSTRAINT fk_thread FOREIGN KEY (thread_id) REFERENCES threads(thread_id)
        
);

CREATE TABLE threads_messages (
        thread_id int,
        message_id int,
        CONSTRAINT fk_thread FOREIGN KEY (thread_id) REFERENCES threads(thread_id),
        CONSTRAINT fk_message FOREIGN KEY (message_id) REFERENCES messages(message_id)
);

CREATE TABLE decks (
        deck_id int DEFAULT nextval('seq_deck_id'::regClass)NOT NULL,
        owner_id int,
        CONSTRAINT fk_owner FOREIGN KEY (owner_id) REFERENCES users(user_id),
        CONSTRAINT PK_deck PRIMARY KEY (deck_id)
);

CREATE TABLE contracts (
	id int DEFAULT nextval('seq_cont_id'::regclass)NOT NULL,
	name varchar(200) NOT NULL,
	cost varchar(4) NOT NULL,
	text varchar(300) NOT NULL,
	duration int,
	counters int,
	is_active boolean,
	CONSTRAINT PK_contracts PRIMARY KEY (id)
);

INSERT INTO contracts (name, cost, text, duration, counters, is_active) 
VALUES ('Plot: Bounty', '20', 'At the beginning of your turn, if this contract is active, put a hunt counter on this card. When there are 2 or more hunt counters on this card, destroy an enemy troop.', 2, 0, false);
INSERT INTO contracts (name, cost, text, duration, counters, is_active) 
VALUES ('Requisition: Enlist the Masses', '10', 'At the beginning of your turn, if this contract is active, put a Loyal Peasant into the combat zone.', 3, 0, false);
INSERT INTO contracts (name, cost, text, duration, counters, is_active) 
VALUES ('Plot: Misinformation Campaign', '25', E'At the beginning of your turn, reduce your opponent\'s Reputation by 5.', 2, 0, false);
INSERT INTO contracts (name, cost, text, duration, counters, is_active)
VALUES ('Requisition: Repair Services', '20', 'At the beginning of your turn, restore 5 integrity points to your Fortress.', 3, 0, false);
INSERT INTO contracts (name, cost, text, duration, counters, is_active) 
VALUES ('Tactic: Morale Boost', '20', 'At the beginning of your turn, put a morale counter on this card. Your troops gain +1 to all attributes for each morale counter on this card.', 2, 0, false);
INSERT INTO contracts (name, cost, text, duration, counters, is_active) 
VALUES ('Loan: Short-Term Loan', '10', 'At the beginning of your turn put an interest counter on this card, then you gain 5 coins for each interest counter.', 2, 0, false);

CREATE TABLE decks_contracts (
        deck_id int,
        contract_id int,
        CONSTRAINT fk_deck FOREIGN KEY (deck_id) REFERENCES decks(deck_id),
        CONSTRAINT fk_contract FOREIGN KEY (contract_id) REFERENCES contracts(id)
);

CREATE TABLE cards (
	id int DEFAULT nextval('seq_card_id'::regclass) NOT NULL,
	name varchar(50) NOT NULL,
	cost varchar(4) NOT NULL,
	image varchar(200) NOT NULL,
	abilities varchar(200),
	flavor varchar(200),
	attack int,
	resilience int,
	loyalty int,
	is_attacking boolean DEFAULT false,
	is_target boolean DEFAULT false,
	can_attack boolean DEFAULT true,

	CONSTRAINT PK_cards PRIMARY KEY (id)
);

INSERT INTO cards (name, cost, image, abilities, flavor, attack, resilience, loyalty) 
VALUES ('Loyal Peasant', '5', 'foot_soldier.jpg', 'If there is an allied Knight in the combat zone, this troop gains +1 Resilience.', E'"The backbone of any great military force oft consists of the kingdom\'s most downtrodden..."', 1, 1, 4);
INSERT INTO cards (name, cost, image, abilities, flavor, attack, resilience, loyalty) 
VALUES ('Valiant Knight', '10', 'valiant_knight.jpg', 'Give all other allied troops in the combat zone +1 Loyalty.', '"What they lack in humility they make up for in skill and bravery."', 3, 3, 5);
INSERT INTO cards (name, cost, image, abilities, flavor, attack, resilience, loyalty) 
VALUES ('Phalanx', '15', 'phalanx.jpg', 'This troop gets +1 Attack and +1 Resilience for each other troop in the combat zone.', '"United we stand..."', 1, 3, 6);
INSERT INTO cards (name, cost, image, abilities, flavor, attack, resilience, loyalty) 
VALUES ('Ballista', '20', 'ballista_1.jpg','This troop cannot defend.', '"The single destructive output of an efficient, unified crew."', 7, 5, 10);
INSERT INTO cards (name, cost, image, flavor, attack, resilience, loyalty) 
VALUES ('Fickle Sellsword', '5', 'sellsword.jpg', '"He is only as consistent as the flow of coins into his pocket."', 4, 1, 1);
INSERT INTO cards (name, cost, image, abilities, flavor, attack, resilience, loyalty) 
VALUES ('Pinnacle Assassin', '40', 'assassin.jpg', 'This troop may attack Reputation directly. When this troop attacks a fortess, destory up to one opposing troop. ', '"Did you hear that? Hmm... Must have been the wind..."', 5, 5, 4);
INSERT INTO cards (name, cost, image, abilities, flavor, attack, resilience, loyalty) 
VALUES ('Veteran Archers', '20', 'archers.jpg', 'Whenever this troop attacks, it deals one damage to each enemy troop.', '"Loose!"', 3, 3, 5);
INSERT INTO cards (name, cost, image, abilities, flavor, attack, resilience, loyalty) 
VALUES ('Battering Ram', '15', 'battering_ram.jpg', 'This troop only deals damage to fortresses and cannot defend.', '"Breach the gates and the city is yours!"', 10, 8, 10);
INSERT INTO cards (name, cost, image, attack, resilience, loyalty) 
VALUES ('Apprentice Mercenary', '5', 'merc.png', 2, 2, 1);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty) 
VALUES ('Catapult', '20', 'catapult.jpg','If this troop damages a fortress, it deals twice as much damage instead. This troop cannot defend.', 5, 5, 10);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty) 
VALUES ('Thunderous Cavalry', '15', 'cavalry.jpg','This troop can only attack if there are enemy troops in the opposing combat zone.', 7, 3, 6);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty)
VALUES ('Veteran Healer', '20', 'healer.jpg', 'While this troop is in the combat zone, if another allied troop would take damage, reduce the damage by 2.', 0, 3, 6);

CREATE TABLE decks_cards (
        deck_id int,
        card_id int,
        CONSTRAINT fk_deck FOREIGN KEY (deck_id) REFERENCES decks(deck_id),
        CONSTRAINT fk_card FOREIGN KEY (card_id) REFERENCES cards(id)
);

COMMIT TRANSACTION;