DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_document') THEN
            CREATE TABLE tb_document
            (
              document_id          		UUID PRIMARY KEY DEFAULT gen_random_uuid(),
              description 						VARCHAR(45) NULL DEFAULT NULL,
  						obligatoriness 					VARCHAR(45) NULL DEFAULT NULL,
							government_agency 			VARCHAR(45) NULL DEFAULT NULL,
							id_driver 							INT NOT NULL,
							id_veicle 							INT NOT NULL,
							id_empresa 							INT NOT NULL,
							tb_document_type_id			INT NOT NULL,
							tb_expenses_id_expenses INT NOT NULL,
							tb_accounts_receive_id_accounts_receivable INT  NULL
            );

        comment on table tb_document  is 'This table serves to storge tb_document records.';

        RAISE INFO 'Table tb_document was created';
    ELSE
        RAISE INFO 'Table tb_company already exists';
    END IF;
END
$$;