-- Test data for integration tests
INSERT INTO items (id, title, description, is_completed, due_date) VALUES
    ('11111111-1111-1111-1111-111111111111', 'Buy groceries', 'Milk, eggs, bread, and butter', false, '2026-02-15 10:30:00'),
    ('22222222-2222-2222-2222-222222222222', 'Clean the house', 'Vacuum, mop, and dust all rooms', false, '2026-02-20 14:00:00'),
    ('33333333-3333-3333-3333-333333333333', 'Finish project', 'Complete the TDD integration tests', true, '2026-01-31 18:00:00');
