DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_phone_type') THEN
            CREATE TABLE tb_phone_type
            (
              phone_type_id          						UUID PRIMARY KEY DEFAULT gen_random_uuid(),
							description 											VARCHAR(45) NULL

            );

        comment on table tb_city  is 'This table serves to storge tb_phone_type records.';

        RAISE INFO 'Table tb_phone_type was created';
    ELSE
        RAISE INFO 'Table tb_phone_type already exists';
    END IF;
END
$$;