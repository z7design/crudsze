DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_phone') THEN
            CREATE TABLE tb_phone
            (
              phone_id         						UUID PRIMARY KEY DEFAULT gen_random_uuid(),
							ddd 												VARCHAR(45) NULL,
							ddi 												VARCHAR(45) NULL,
							numeber_phone 							VARCHAR(45) NULL,
							tb_phone_type_id 						INT NOT NULL

            );

        comment on table tb_phone  is 'This table serves to storge tb_phone records.';

        RAISE INFO 'Table tb_phone was created';
    ELSE
        RAISE INFO 'Table tb_phone already exists';
    END IF;
END
$$;