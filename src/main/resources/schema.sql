CREATE TABLE financial_transactions (
                                        id SERIAL PRIMARY KEY,
                                        payment_id VARCHAR(50) NOT NULL,
                                        transaction_date DATE NOT NULL,
                                        user_id VARCHAR(50) NOT NULL,
                                        service VARCHAR(100) NOT NULL,
                                        status VARCHAR(50) NOT NULL,
                                        reference VARCHAR(100),
                                        amount DECIMAL(15,2) NOT NULL,
                                        currency VARCHAR(3) NOT NULL
);

CREATE INDEX idx_fin_trans_date ON financial_transactions(transaction_date);
CREATE INDEX idx_fin_trans_user ON financial_transactions(user_id);
CREATE INDEX idx_fin_trans_service ON financial_transactions(service);
CREATE INDEX idx_fin_trans_status ON financial_transactions(status);
CREATE INDEX idx_fin_trans_reference ON financial_transactions(reference);