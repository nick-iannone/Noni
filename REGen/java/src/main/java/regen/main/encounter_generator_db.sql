drop table race;
drop table class;
drop table class_spell;
drop table spell;
drop table weapon;
drop table character;
drop table location;
drop table encounter;
drop table mob;

CREATE TABLE race (

race_id serial,
name varchar(32),
modAC int,
modHP int,
modAttack int,
modSpell int

);

INSERT INTO race (name, modAC, modHP, modAttack, modSpell) VALUES ('Goblin', 1, -10, 5, -2);
INSERT INTO race (name, modAC, modHP, modAttack, modSpell) VALUES ('Human', 1, 5, 3, 2);
INSERT INTO race (name, modAC, modHP, modAttack, modSpell) VALUES ('Elf', 0, 5, 3, 3);
INSERT INTO race (name, modAC, modHP, modAttack, modSpell) VALUES ('Dwarf', 2, 10, 2, 1);
INSERT INTO race (name, modAC, modHP, modAttack, modSpell) VALUES ('Halfling', 2, 10, 1, 2);
INSERT INTO race (name, modAC, modHP, modAttack, modSpell) VALUES ('Feyling', -2, 10, 0, 4);
INSERT INTO race (name, modAC, modHP, modAttack, modSpell) VALUES ('Undead', -1, 20, 1, -1);

CREATE TABLE class (

class_id serial,
name varchar(32),
modAC int,
modHP int,
modAttack int,
modSpell int,
spellSlots int,
weaponType varchar(32)

);

INSERT INTO class (name, modAC, modHP, modAttack, modSpell, spellSlots, weaponType) VALUES ('Rogue', 0, -5, 4, 1, 2, 'Dagger');
INSERT INTO class (name, modAC, modHP, modAttack, modSpell, spellSlots, weaponType) VALUES ('Paladin', 2, 0, 1, 2, 4, 'Mace');
INSERT INTO class (name, modAC, modHP, modAttack, modSpell, spellSlots, weaponType) VALUES ('Wizard', -1, 0, -2, 5, 8, 'Staff');
INSERT INTO class (name, modAC, modHP, modAttack, modSpell, spellSlots, weaponType) VALUES ('Ranger', 1, 5, 3, 1, 2, 'Bow');
INSERT INTO class (name, modAC, modHP, modAttack, modSpell, spellSlots, weaponType) VALUES ('Necromancer', -2, 10, 1, 4, 6, 'Wand');
INSERT INTO class (name, modAC, modHP, modAttack, modSpell, spellSlots, weaponType) VALUES ('Warrior', 2, 0, 4, -2, 2, 'Greataxe');


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
damageDie int,
modAttack int,
modSpell int,
isRanged boolean
);

INSERT INTO weapon (name, damageDie, modAttack, modSpell, isRanged) VALUES ('Dagger', 8, 2, 0, false);
INSERT INTO weapon (name, damageDie, modAttack, modSpell, isRanged) VALUES ('Mace', 10, 1, 1, false);
INSERT INTO weapon (name, damageDie, modAttack, modSpell, isRanged) VALUES ('Staff', 4, 1, 2, false);
INSERT INTO weapon (name, damageDie, modAttack, modSpell, isRanged) VALUES ('Bow', 12, 1, 0, true);
INSERT INTO weapon (name, damageDie, modAttack, modSpell, isRanged) VALUES ('Wand', 6, 0, 2, true);
INSERT INTO weapon (name, damageDie, modAttack, modSpell, isRanged) VALUES ('Shortsword', 10, 2, 0, false);
INSERT INTO weapon (name, damageDie, modAttack, modSpell, isRanged) VALUES ('Greataxe', 12, 2, -1, false);

CREATE TABLE character (

character_id serial,
name varchar(64),
class_id int,
race_id int,
weapon_id int,
AC int,
HP int,
XP int,
spell_pool_id int,
saved_on varchar(32)

);

INSERT INTO character (name, class_id, race_id, weapon_id, ac, hp, xp) VALUES ('Earl Ensalada', 2, 2, 2, 16, 45, 0);

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


--SELECT * FROM spells ORDER BY school, name