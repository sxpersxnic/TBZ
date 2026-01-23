-- PostgreSQL migration script
CREATE TABLE items (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    assigned_user_id UUID,
    title VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(200),
    due_date TIMESTAMP DEFAULT NULL,
    is_completed BOOLEAN NOT NULL DEFAULT false,
    tags TEXT[] DEFAULT ARRAY[]::TEXT[]
);
