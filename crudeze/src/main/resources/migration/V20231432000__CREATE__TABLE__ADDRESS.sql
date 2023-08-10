DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_address') THEN
            CREATE TABLE tb_address
            (
              address_id          		SERIAL PRIMARY KEY,
							public_place 						VARCHAR(60) NOT NULL,
							name_address 						VARCHAR(45) NOT NULL,
							street_number 					INT NOT NULL,
							type_address 						VARCHAR(45) NOT NULL,
							postal_code 						VARCHAR(45) NOT NULL,
							id_city 								VARCHAR(45) NOT NULL
            );

        comment on table tb_address  is 'This table serves to storge tb_address records.';

        RAISE INFO 'Table tb_address was created';
    ELSE
        RAISE INFO 'Table tb_address already exists';
    END IF;
END
$$;