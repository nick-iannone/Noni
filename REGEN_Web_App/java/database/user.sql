-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER regen_owner
WITH PASSWORD 'regen_owner';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO regen_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO regen_owner;

CREATE USER regen_appuser
WITH PASSWORD 'regen';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO regen_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO regen_appuser;
