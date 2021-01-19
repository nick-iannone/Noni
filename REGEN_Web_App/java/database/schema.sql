BEGIN TRANSACTION;
drop table if exists race;
drop table if exists class;
drop table if exists class_spell;
drop table if exists spell;
drop table if exists weapon;
drop table if exists character;
drop table if exists location;
drop table if exists encounter;
drop table if exists mob;
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



CREATE TABLE race (

race_id serial,
name varchar(32),
description varchar(500),
modAC int,
modHP int,
modAttack int,
modSpell int

);

INSERT INTO race (name, description, modAC, modHP, modAttack, modSpell) VALUES ('Goblin', 'These small but fierce creatures are skilled with melee weapons, able to get in close to their targets and land vicious strikes.', 1, -10, 5, -2);
INSERT INTO race (name, description, modAC, modHP, modAttack, modSpell) VALUES ('Human', 'Well-rounded in a variety of combat situations, humans are adept with both weapons and spells in addition to above average resilience.', 1, 5, 3, 2);
INSERT INTO race (name, description, modAC, modHP, modAttack, modSpell) VALUES ('Elf', 'Elves have a diverse skill set, similar to humans, sacrificing some resilience for more potent spells to protect their forested home.', 0, 5, 3, 3);
INSERT INTO race (name, description, modAC, modHP, modAttack, modSpell) VALUES ('Dwarf', 'These hardy mountain-dwellers are as reilient as they come. Dwarves prefer the use of martial weapons, but are decent casters as well.', 2, 10, 2, 1);
INSERT INTO race (name, description, modAC, modHP, modAttack, modSpell) VALUES ('Halfling', 'The small hill folk known as Halflings are evasive, durable targets. They are skilled with both weapons and magic.', 2, 5, 2, 2);
INSERT INTO race (name, description, modAC, modHP, modAttack, modSpell) VALUES ('Feyling', 'The Feylings draw on the magical powers of their ancestral domain, the Feywild. While somewhat vulnerable, they are unmatched in their mastery of magic.', -2, 10, 0, 4);
INSERT INTO race (name, description, modAC, modHP, modAttack, modSpell) VALUES ('Undead', 'Ceaseless in their hunger for life, undead are hard to bring down. They are not as skilled in battle as their warm-blooded counterparts.', -1, 20, 1, 1);

CREATE TABLE class (

class_id serial,
name varchar(32),
description varchar(500),
modAC int,
modHP int,
modAttack int,
modSpell int,
spellSlots int

);

INSERT INTO class (name, description, modAC, modHP, modAttack, modSpell, spellSlots) VALUES ('Rogue', 'Masters of both blade and subterfuge, the Rogue uses manipulative magic and deadly weapon strikes to vanquish their foes.', 0, -5, 4, 1, 2);
INSERT INTO class (name, description, modAC, modHP, modAttack, modSpell, spellSlots) VALUES ('Paladin', 'Paladins are charged with divine energy, using holy magic to both heal and smite their way to glory.', 2, 0, 1, 2, 4);
INSERT INTO class (name, description, modAC, modHP, modAttack, modSpell, spellSlots) VALUES ('Wizard', 'These masters of the arcane arts focus on defensive magic paired with powerful spell attacks.', -1, 0, -2, 5, 8);
INSERT INTO class (name, description, modAC, modHP, modAttack, modSpell, spellSlots) VALUES ('Ranger', 'The Ranger calls upon the power of the wild, summoning wild beasts to their aid and delivering deadly weapon strikes.', 1, 5, 3, 1, 2);
INSERT INTO class (name, description, modAC, modHP, modAttack, modSpell, spellSlots) VALUES ('Necromancer', 'Life, for the Necromancer, begins at death. They summon demonic servants to their aid and cast powerful spells.',-2, 10, 1, 4, 6);
INSERT INTO class (name, description, modAC, modHP, modAttack, modSpell, spellSlots) VALUES ('Warrior', 'The Warrior lusts for the scent of blood. These fierce warriors can go into a rage, greatly enhancing their weapon strikes.', 2, 0, 4, -2, 2);


CREATE TABLE spell (

spell_id serial,
name varchar(64),
school varchar(32),
damageType varchar(32),
damageDie int,
modAC int,
modHP int,
modAttack int,
modSpell int,
duration int,
target varchar(32),
description varchar(300)

);

INSERT INTO spell (name, school, damageType, damageDie, modAC, modHP, modAttack, modSpell, duration, target, description)
VALUES ('Firebolt', 'Elemental', 'Fire', 12, -1, 0, 0, 0, 1, 'enemy target', 'A blast of fire bursts forth from an outstretched hand, searing the target for 1d12 fire damage and making them easier to hit.');
INSERT INTO spell (name, school, damageType, damageDie, modAC, modHP, modAttack, modSpell, duration, target, description)
VALUES ('Lightning Shock', 'Elemental', 'Lightning', 10, 0, 0, -1, 0, 1, 'enemy target', 'A bolt of lightning crackles down from above, jolting the target for 1d10 lightning damage and lessening their weapon attacks against you.');
INSERT INTO spell (name, school, damageType, damageDie, modAC, modHP, modAttack, modSpell, duration, target, description)
VALUES ('Stunning Gale', 'Elemental', 'Wind', 6, 0, 0, 0, 0, 1, 'enemy target', 'A mighty gust of wind knocks the target off balance, dealing 1d4 damage stunning them for a turn.');
INSERT INTO spell (name, school, damageType, damageDie, modAC, modHP, modAttack, modSpell, duration, target, description)
VALUES ('Explode Minion', 'Unholy', 'Necrotic', 0, 0, 0, 0, 0, 0, 'multiple targets', 'You turn one of your minions into a explosion of putrid filth, damaging up to three random mobs.');
INSERT INTO spell (name, school, damageType, damageDie, modAC, modHP, modAttack, modSpell, duration, target, description)
VALUES ('Healing Hands', 'Holy', 'None', 0, 0, 17, 0, 0, 0, 'self', 'Radiant light surrounds the target, healing it for 17 hit points.');
INSERT INTO spell (name, school, damageType, damageDie, modAC, modHP, modAttack, modSpell, duration, target, description)
VALUES ('Summon Minion', 'Unholy', 'None', 0, 0, 0, 0, 0, 0, 'self', 'The caster summons up to three minions that fight for the caster until killed.');
INSERT INTO spell (name, school, damageType, damageDie, modAC, modHP, modAttack, modSpell, duration, target, description)
VALUES ('Heavenly Wrath', 'Holy', 'Radiant', 10, -1, -1, 0, 0, 2, 'enemy target', 'A holy radiance comes beaming down from on high, scorching the target for 1d12 holy damage.');
INSERT INTO spell (name, school, damageType, damageDie, modAC, modHP, modAttack, modSpell, duration, target, description)
VALUES ('Armor of the Wilds', 'Elemental', 'None', 0, 3, 0, 0, 0, 2, 'self', 'A thick layer of vines and brush form around the caster, bolstering their defences.');
INSERT INTO spell (name, school, damageType, damageDie, modAC, modHP, modAttack, modSpell, duration, target, description)
VALUES ('Hex', 'Unholy', 'Necrotic', 0, -2, -1, -1, -1, 2, 'enemy target', 'The caster mutters ancient magical words and places a hex on the target, lowering its defences.');
INSERT INTO spell (name, school, damageType, damageDie, modAC, modHP, modAttack, modSpell, duration, target, description)
VALUES ('Turn Mob', 'Deception', 'None', 0, 0, 0, 0, 0, 1, 'enemy target', 'The targeted mob must attack an ally mob instead of the player character on its next turn.');
INSERT INTO spell (name, school, damageType, damageDie, modAC, modHP, modAttack, modSpell, duration, target, description)
VALUES ('Poison Weapon', 'Deception', 'Toxic', 4, -1, -5, 0, 0, 1, 'enemy target', 'The caster makes a weapon attack with a poison-coated weapon, dealing extra damage and debilitating the target.');
INSERT INTO spell (name, school, damageType, damageDie, modAC, modHP, modAttack, modSpell, duration, target, description)
VALUES ('Arcane Assault', 'Arcane', 'Arcane', 6, -1, -1, 0, 0, 2, 'multiple targets', 'The caster unleahses an explosion of arcane energy, damaging all mobs in the encounter and weakening them for a short time.');
INSERT INTO spell (name, school, damageType, damageDie, modAC, modHP, modAttack, modSpell, duration, target, description)
VALUES ('Instill Awe', 'Holy', 'Radiant', 4, -1, -1, -1, 0, 2, 'multiple targets', 'A glowing aura surrounds the caster, instilling awe in all mobs in the encounter, greatly reducing their attacks and spells for a short time.');
INSERT INTO spell (name, school, damageType, damageDie, modAC, modHP, modAttack, modSpell, duration, target, description)
VALUES ('Scent of Blood', 'Blood', 'None', 0, 1, 10, 2, 0, 2, 'self', 'The caster is instilled with a murderous rage, increasing their ability to hit with weapon attacks.');
INSERT INTO spell (name, school, damageType, damageDie, modAC, modHP, modAttack, modSpell, duration, target, description)
VALUES ('Consume Minion', 'Blood', 'None', 0, 1, 0, 1, 0, 2, 'self', 'The caster absorbs the life-force of their strongest minion, healing for the amount drained.');


CREATE TABLE class_spell (

spell_id int,
class_id int

);

INSERT INTO class_spell (spell_id, class_id) VALUES (1, 3);
INSERT INTO class_spell (spell_id, class_id) VALUES (2, 3);
INSERT INTO class_spell (spell_id, class_id) VALUES (3, 3);
INSERT INTO class_spell (spell_id, class_id) VALUES (4, 3);
INSERT INTO class_spell (spell_id, class_id) VALUES (6, 3);
INSERT INTO class_spell (spell_id, class_id) VALUES (8, 3);
INSERT INTO class_spell (spell_id, class_id) VALUES (9, 3);
INSERT INTO class_spell (spell_id, class_id) VALUES (12, 3);
INSERT INTO class_spell (spell_id, class_id) VALUES (5, 2);
INSERT INTO class_spell (spell_id, class_id) VALUES (6, 2);
INSERT INTO class_spell (spell_id, class_id) VALUES (7, 2);
INSERT INTO class_spell (spell_id, class_id) VALUES (13, 2);
INSERT INTO class_spell (spell_id, class_id) VALUES (6, 1);
INSERT INTO class_spell (spell_id, class_id) VALUES (9, 1);
INSERT INTO class_spell (spell_id, class_id) VALUES (10, 1);
INSERT INTO class_spell (spell_id, class_id) VALUES (11, 1);
INSERT INTO class_spell (spell_id, class_id) VALUES (5, 4);
INSERT INTO class_spell (spell_id, class_id) VALUES (6, 4);
INSERT INTO class_spell (spell_id, class_id) VALUES (8, 4);
INSERT INTO class_spell (spell_id, class_id) VALUES (10, 4);
INSERT INTO class_spell (spell_id, class_id) VALUES (11, 4);
INSERT INTO class_spell (spell_id, class_id) VALUES (1, 5);
INSERT INTO class_spell (spell_id, class_id) VALUES (4, 5);
INSERT INTO class_spell (spell_id, class_id) VALUES (5, 5);
INSERT INTO class_spell (spell_id, class_id) VALUES (6, 5);
INSERT INTO class_spell (spell_id, class_id) VALUES (8, 5);
INSERT INTO class_spell (spell_id, class_id) VALUES (9, 5);
INSERT INTO class_spell (spell_id, class_id) VALUES (10, 5);
INSERT INTO class_spell (spell_id, class_id) VALUES (6, 6);
INSERT INTO class_spell (spell_id, class_id) VALUES (8, 6);
INSERT INTO class_spell (spell_id, class_id) VALUES (11, 6);
INSERT INTO class_spell (spell_id, class_id) VALUES (14, 6);
INSERT INTO class_spell (spell_id, class_id) VALUES (15, 6);


CREATE TABLE weapon (

weapon_id serial,
name varchar(32),
description varchar(500),
damageDie int,
modAttack int,
modSpell int,
isRanged boolean
);

INSERT INTO weapon (name, description, damageDie, modAttack, modSpell, isRanged) VALUES ('Dagger', 'The go-to weapon for Rogues, the dagger delivers quick strikes and can be duel wielded. Goblins and Halflings are dagger experts.', 8, 2, 0, false);
INSERT INTO weapon (name, description, damageDie, modAttack, modSpell, isRanged) VALUES ('Mace', 'These powerful hammers deliver solid blows and slightly enhance the magical abilities of the wielder, especially so for Humans and Paladins.', 10, 1, 1, false);
INSERT INTO weapon (name, description, damageDie, modAttack, modSpell, isRanged) VALUES ('Staff', 'This iconic weapon is used by casters to channel magical energies. The magical abilities of Wizards are slightly enhanced when using a staff.', 4, 1, 2, false);
INSERT INTO weapon (name, description, damageDie, modAttack, modSpell, isRanged) VALUES ('Bow', 'The favored weapon of Rangers, the bow delivers deadly strikes at range, especially when wielded by Elves.', 12, 1, 0, true);
INSERT INTO weapon (name, description, damageDie, modAttack, modSpell, isRanged) VALUES ('Wand', 'Known to be of benefit to both Necromancers and Feylings, wands amplify the magical abilities of the wielder and allow for ranged weapon attacks.',6, 0, 2, true);
INSERT INTO weapon (name, description, damageDie, modAttack, modSpell, isRanged) VALUES ('Shortsword', 'This classic weapon enhances the melee attacks of all who wield it, especially the Undead.', 10, 2, 0, false);
INSERT INTO weapon (name, description, damageDie, modAttack, modSpell, isRanged) VALUES ('Greataxe', 'The most menacing of the martial melee weapons, the Greataxe is right at home in the grip of a Warrior.', 12, 2, -1, false);

CREATE TABLE character (

character_id serial,
name varchar(64) NOT NULL,
class_id int NOT NULL,
race_id int NOT NULL,
weapon_id int NOT NULL,
AC int NOT NULL,
HP int NOT NULL,
XP int,
saved_on varchar(32),
user_id int NOT NULL

);

INSERT INTO character (name, class_id, race_id, weapon_id, ac, hp, xp, user_id) VALUES ('Earl Ensalada', 2, 2, 2, 16, 45, 0, 1);

CREATE TABLE location (

location_id serial,
name varchar(64),
mob_pool_id int,
description varchar(300)

);

INSERT INTO location (name) VALUES ('Ancient Jungle Ruins');
INSERT INTO location (name) VALUES ('Capital City Crypt');
INSERT INTO location (name) VALUES ('The Twisted Wood');
INSERT INTO location (name) VALUES ('Misty Marsh');
INSERT INTO location (name) VALUES ('The Molten Chasm');

CREATE TABLE encounter (

id serial,
playerChar_id int,
location_id int,
difficulty int

);

INSERT INTO encounter (playerChar_id, location_id, difficulty) VALUES (1, 1, 1);


CREATE TABLE mob (

mob_id serial,
name varchar(32),
ac int,
hp int,
race_id int,
class_id int,
weapon_id int,
difficulty_rating int

);

INSERT INTO mob (name, ac, hp, race_id, class_id, weapon_id, difficulty_rating) VALUES ('Goblin Scout', 14, 20, 1, 1, 1, 1);
INSERT INTO mob (name, ac, hp, race_id, class_id, weapon_id, difficulty_rating) VALUES ('Goblin Chieftan', 15, 30, 1, 4, 7, 2);
INSERT INTO mob (name, ac, hp, race_id, class_id, weapon_id, difficulty_rating) VALUES ('Human Cultist', 13, 30, 2, 5, 5, 2);
INSERT INTO mob (name, ac, hp, race_id, class_id, weapon_id, difficulty_rating) VALUES ('Human Bandit', 12, 35, 2, 1, 6, 2);
INSERT INTO mob (name, ac, hp, race_id, class_id, weapon_id, difficulty_rating) VALUES ('Archmage', 16, 50, 3, 3, 3, 4);
INSERT INTO mob (name, ac, hp, race_id, class_id, weapon_id, difficulty_rating) VALUES ('Sinister Pixie', 17, 40, 6, 5, 5, 4);
INSERT INTO mob (name, ac, hp, race_id, class_id, weapon_id, difficulty_rating) VALUES ('Zombie Soldier', 14, 25, 7, 4, 2, 3);
INSERT INTO mob (name, ac, hp, race_id, class_id, weapon_id, difficulty_rating) VALUES ('Skeletal Dark Wizard', 17, 60, 7, 5, 3, 5);
INSERT INTO mob (name, ac, hp, race_id, class_id, weapon_id, difficulty_rating) VALUES ('Molten Fiend', 16, 45, 6, 2, 2, 3);
INSERT INTO mob (name, ac, hp, race_id, class_id, weapon_id, difficulty_rating) VALUES ('Chasm Stalker', 11, 65, 4, 4, 4, 2);


COMMIT TRANSACTION;
