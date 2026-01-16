-- PostgreSQL migration script
CREATE TABLE items (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(200),
    tags TEXT[] DEFAULT ARRAY[]::TEXT[]
);
