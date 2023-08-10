DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_client') THEN
            CREATE TABLE tb_client
            (
               client_id           			SERIAL PRIMARY KEY,
               client_name							VARCHAR(100) NOT NULL,
							 corporate_name 					VARCHAR(100) NOT NULL,
							 fatasy_name 							VARCHAR(100) NOT NULL,
							 email 										VARCHAR(100) NOT NULL,
							 cpf 											VARCHAR(15) NOT NULL,
							 cnpj 										VARCHAR(15) NOT NULL,
							 rg 											VARCHAR(45) NOT NULL,
							 orgao_emissor 						VARCHAR(45) NOT NULL,
							 state_registration 			VARCHAR(45) NOT NULL,
							 municipal_registration 	VARCHAR(45) NOT NULL,
							 phone 										VARCHAR(20) NOT NULL,
							 celphone 								VARCHAR(20) NOT NULL,
							 observation 							VARCHAR(100) NOT NULL,
							 date_of_birth 						DATE NOT NULL,
							 registration_date 				DATE NOT NULL

            );

        comment on table tb_client  is 'This table serves to storge brand records.';

        RAISE INFO 'Table client was created';
    ELSE
        RAISE INFO 'Table client already exists';
    END IF;
END
$$;