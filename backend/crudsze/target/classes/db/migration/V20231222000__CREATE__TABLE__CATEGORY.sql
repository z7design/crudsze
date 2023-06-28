DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_category') THEN
            CREATE TABLE tb_category
            (
              category_id          		UUID PRIMARY KEY DEFAULT gen_random_uuid(),
              name_category 					VARCHAR(45) NULL,
							description 						VARCHAR(45) NULL,
							tb_expenses_id_expenses INT NOT NULL,
							tb_accounts_receive_id_accounts_receivable INT NOT NULL,
							tb_accounts_payable_id_accounts_payable INT NOT NULL
            );

        comment on table tb_category  is 'This table serves to storge tb_category records.';

        RAISE INFO 'Table tb_category was created';
    ELSE
        RAISE INFO 'Table tb_category already exists';
    END IF;
END
$$;