DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_veicle') THEN
            CREATE TABLE tb_veicle
            (
              veicle_id         						SERIAL PRIMARY KEY,
							plate 												VARCHAR(45) NULL,
							color 												VARCHAR(45) NULL,
							model 							          VARCHAR(45) NULL,
							value 						            DECIMAL(14,2)

            );

        comment on table tb_phone  is 'This table serves to storge tb_phone records.';

        RAISE INFO 'Table tb_phone was created';
    ELSE
        RAISE INFO 'Table tb_phone already exists';
    END IF;
END
$$;