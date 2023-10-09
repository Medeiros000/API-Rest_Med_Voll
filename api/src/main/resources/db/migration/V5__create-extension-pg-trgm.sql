create extension if not exists pg_trgm;
-- CREATE INDEX if not exists idx_trgm_especialidade ON vollmed_api.vollmed_api.medicos USING GIN (especialidade gin_trgm_ops);
-- CREATE INDEX if not exists idx_trgm_nome ON vollmed_api.vollmed_api.medicos USING GIN (nome gin_trgm_ops);