-- Test data for integration tests
INSERT INTO items (id, title, description, tags) VALUES
    ('11111111-1111-1111-1111-111111111111', 'Buy groceries', 'Milk, eggs, bread, and butter', ARRAY['shopping', 'urgent']),
    ('22222222-2222-2222-2222-222222222222', 'Clean the house', 'Vacuum, mop, and dust all rooms', ARRAY['chores']),
    ('33333333-3333-3333-3333-333333333333', 'Finish project', 'Complete the TDD integration tests', ARRAY['work', 'important']);
