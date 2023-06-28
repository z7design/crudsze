DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_company') THEN
            CREATE TABLE tb_company
            (
              company_id          		UUID PRIMARY KEY DEFAULT gen_random_uuid(),
              name_company 						VARCHAR(80) NULL DEFAULT NULL,
							date_closed 						DATE NULL DEFAULT NULL,
							date_registration 			DATE NULL DEFAULT NULL,
							logomarca 							VARCHAR(100) NULL DEFAULT NULL,
							tb_address_id_address 	INT NOT NULL
            );

        comment on table tb_company  is 'This table serves to storge tb_company records.';

        RAISE INFO 'Table tb_company was created';
    ELSE
        RAISE INFO 'Table tb_company already exists';
    END IF;
END
$$;