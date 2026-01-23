-- PostgreSQL migration script
CREATE TABLE items (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(200),
    is_completed BOOLEAN DEFAULT false,
    due_date TIMESTAMP DEFAULT NULL,
    priority VARCHAR(20) DEFAULT 'MEDIUM'
);
