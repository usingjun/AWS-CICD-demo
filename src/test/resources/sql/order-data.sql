-- User 테이블에 데이터 삽입
INSERT INTO users (email, password, user_role, user_name, created_at, updated_at)
VALUES
    ('test@test.com', 'test', 'ROLE_USER', 'tester', now(), now());

-- Order 테이블에 데이터 삽입
INSERT INTO orders (
    created_at, updated_at, receiver_address, receiver_name, receiver_phone,
    receiver_postal_code, shipping_message, order_number, order_status,
    total_price, user_id
)
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Address 1', 'User 1', '0100000001',
     '10001', 'Test message', '1test', 'PAID', 10500.00, 1),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Address 2', 'User 2', '0100000002',
     '10002', 'Test message', '2test', 'PAID', 11000.00, 1),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Address 3', 'User 3', '0100000003',
     '10003', 'Test message', '3test', 'PAID', 11500.00, 1),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Address 4', 'User 4', '0100000004',
     '10004', 'Test message', '4test', 'PAID', 12000.00, 1),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Address 5', 'User 5', '0100000005',
     '10005', 'Test message', '5test', 'PAID', 12500.00, 1);
