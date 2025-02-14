INSERT INTO financial_transactions
(payment_id, transaction_date, user_id, service, status, reference, amount, currency)
VALUES
    ('PAY-001', '2024-01-15', 'user123', 'Subscription', 'Completed', 'SUB-2024-01', 99.99, 'USD'),
    ('PAY-002', '2024-01-20', 'user123', 'One-time Purchase', 'Completed', 'OTP-2024-01', 49.99, 'USD'),
    ('PAY-003', '2024-01-25', 'user456', 'Subscription', 'Failed', 'SUB-2024-02', 29.99, 'EUR'),
    ('PAY-004', '2024-02-01', 'user789', 'Recurring', 'Pending', 'REC-2024-01', 19.99, 'GBP'),
    ('PAY-005', '2024-02-05', 'user123', 'Subscription', 'Completed', 'SUB-2024-03', 99.99, 'USD'),
    ('PAY-006', '2024-02-10', 'user456', 'One-time Purchase', 'Refunded', 'OTP-2024-02', 199.99, 'EUR'),
    ('PAY-007', '2024-02-15', 'user789', 'Subscription', 'Completed', 'SUB-2024-04', 9.99, 'GBP'),
    ('PAY-008', '2024-03-01', 'user123', 'Recurring', 'Completed', 'REC-2024-02', 99.99, 'USD'),
    ('PAY-009', '2024-03-05', 'user456', 'Subscription', 'Failed', 'SUB-2024-05', 29.99, 'EUR'),
    ('PAY-010', '2024-03-10', 'user789', 'One-time Purchase', 'Completed', 'OTP-2024-03', 149.99, 'GBP'),
    ('PAY-011', '2024-03-15', 'user123', 'Subscription', 'Completed', 'SUB-2024-06', 99.99, 'USD'),
    ('PAY-012', '2024-03-20', 'user456', 'Recurring', 'Pending', 'REC-2024-03', 19.99, 'EUR'),
    ('PAY-013', '2024-04-01', 'user789', 'Subscription', 'Completed', 'SUB-2024-07', 9.99, 'GBP'),
    ('PAY-014', '2024-04-05', 'user123', 'One-time Purchase', 'Completed', 'OTP-2024-04', 299.99, 'USD'),
    ('PAY-015', '2024-04-10', 'user456', 'Subscription', 'Failed', 'SUB-2024-08', 29.99, 'EUR');