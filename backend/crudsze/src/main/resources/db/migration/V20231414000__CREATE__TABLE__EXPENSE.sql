DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_expenses') THEN
            CREATE TABLE tb_expenses
            (
              expenses_id          		UUID PRIMARY KEY DEFAULT gen_random_uuid(),
							date_registration 			DATE NULL DEFAULT NULL,
							due_date 								DATE NULL DEFAULT NULL,
							pay_day 								DATE NOT NULL,
							description 						VARCHAR(60) NULL DEFAULT NULL,
							invoice 								VARCHAR(45) NULL DEFAULT NULL,
							locality 								VARCHAR(100) NULL DEFAULT NULL,
							value_expenses 					DECIMAL(18,6) NULL DEFAULT NULL,
							amount_paid 						DECIMAL(18,6) NULL DEFAULT NULL,
							id_supplier 						INT NOT NULL
            );

        comment on table tb_expenses  is 'This table serves to storge tb_expenses records.';

        RAISE INFO 'Table tb_expenses was created';
    ELSE
        RAISE INFO 'Table tb_cash_flow already exists';
    END IF;
END
$$;