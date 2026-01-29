-- Test data for integration tests
INSERT INTO items (id, title, description, tags, assigned_user_id, is_completed, due_date, priority) VALUES
    ('11111111-1111-1111-1111-111111111111', 'Buy groceries', 'Milk, eggs, bread, and butter', ARRAY['shopping', 'urgent'], '11111111-1111-4111-a111-111111111111', false, '2026-02-15 10:30:00', 'HIGH'),
    ('22222222-2222-2222-2222-222222222222', 'Clean the house', 'Vacuum, mop, and dust all rooms', ARRAY['chores'], '22222222-2222-4222-b222-222222222222', false, '2026-02-20 14:00:00', 'MEDIUM'),
    ('33333333-3333-3333-3333-333333333333', 'Finish project', 'Complete the TDD integration tests', ARRAY['work', 'important'], '33333333-3333-4333-8333-333333333333', false, '2026-01-31 18:00:00', 'HIGH');
