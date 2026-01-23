-- PostgreSQL migration script
CREATE TABLE items (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    assigned_user_id UUID,
    title VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(200),
    is_completed BOOLEAN NOT NULL DEFAULT false,
    due_date TIMESTAMP DEFAULT NULL,
    priority VARCHAR(20) DEFAULT 'MEDIUM' CHECK (priority IN ('HIGH', 'MEDIUM', 'LOW'))
);
