DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_payment') THEN
            CREATE TABLE tb_payment
            (
              payment_id           				UUID PRIMARY KEY DEFAULT gen_random_uuid(),
              id_client 									INT NULL DEFAULT NULL,
							due_date 										DATE NULL DEFAULT NULL,
							payment_date 								DATE NULL DEFAULT NULL,
							month_reference 						DATE NULL DEFAULT NULL,
							description 								VARCHAR(100) NULL DEFAULT NULL,
							payment_amount 							DECIMAL(10,0) NULL DEFAULT NULL,
							installment_number 					VARCHAR(45) NULL DEFAULT NULL,
							tb_type_of_payment_id_type_of_payment INT NOT NULL
            );

        comment on table tb_bank  is 'This table serves to storge tb_payment records.';

        RAISE INFO 'Table tb_payment was created';
    ELSE
        RAISE INFO 'Table tb_payment already exists';
    END IF;
END
$$;