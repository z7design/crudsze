DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_enty_to_receive') THEN
            CREATE TABLE tb_enty_to_receive
            (
              enty_to_receive_id          						UUID PRIMARY KEY DEFAULT gen_random_uuid(),
							id_financial_nature 										INT NULL,
							id_client  															INT NULL,
							id_bank    															INT NULL,
							number_of_installments 									INT NOT NULL,
							amount_receivable 	 										DECIMAL(18,6) NULL,
							date_registration  											VARCHAR(45) NULL,
							docment_number  										 		VARCHAR(45) NULL,
							commission_rate  												DECIMAL(18,6) NULL,
							rate_value 															DECIMAL(18,6) NULL
            );

        comment on table tb_enty_to_receive  is 'This table serves to storge tb_enty_to_receive records.';

        RAISE INFO 'Table tb_enty_to_receive was created';
    ELSE
        RAISE INFO 'Table tb_enty_to_receive already exists';
    END IF;
END
$$;