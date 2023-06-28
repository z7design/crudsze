DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_driver') THEN
            CREATE TABLE tb_person
            (
              person_id          					UUID PRIMARY KEY DEFAULT gen_random_uuid(),
							scale_name 									VARCHAR(45) NULL DEFAULT NULL,
							name_completed 							VARCHAR(60) NULL DEFAULT NULL,
							email 											VARCHAR(45) NULL DEFAULT NULL,
							code_registration 					VARCHAR(45) NULL DEFAULT NULL,
							admission_date 							DATE NULL DEFAULT NULL,
							cpf 												VARCHAR(45) NULL DEFAULT NULL,
							rg 													VARCHAR(45) NULL DEFAULT NULL,
							mandatory_document 					VARCHAR(45) NULL DEFAULT NULL,
							trips 											VARCHAR(45) NULL DEFAULT NULL,
							type_tourism 								VARCHAR(45) NULL DEFAULT NULL,
							knowledge 									VARCHAR(45) NULL DEFAULT NULL,
							observation 								VARCHAR(100) NULL DEFAULT NULL,
							operational_core 						VARCHAR(45) NULL DEFAULT NULL,
							cnh 												VARCHAR(45) NULL DEFAULT NULL,
							date_venc_cnh 							DATE NULL DEFAULT NULL,
							normal_hour_value 					VARCHAR(45) NULL DEFAULT NULL,
							category_driver							VARCHAR(45) NULL DEFAULT NULL,
							origin_state 								VARCHAR(45) NULL DEFAULT NULL,
							total_trips 								VARCHAR(45) NULL DEFAULT NULL,
							commission									VARCHAR(45) NULL DEFAULT NULL,
							tb_veicle_vc_code 					INT NOT NULL,
							tb_type_driver 							INT NOT NULL,
							tb_address_fk 							INT NOT NULL
            );

        comment on table tb_state  is 'This table serves to storge tb_state records.';

        RAISE INFO 'Table tb_state was created';
    ELSE
        RAISE INFO 'Table tb_state already exists';
    END IF;
END
$$;