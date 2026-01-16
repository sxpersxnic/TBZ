-- PostgreSQL migration script
CREATE TABLE items (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    assigned_user_id UUID,
    title VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(200),
    tags TEXT[] DEFAULT ARRAY[]::TEXT[]
);
