DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_city') THEN
            CREATE TABLE tb_city
            (
              city_id          						UUID PRIMARY KEY DEFAULT gen_random_uuid(),
							name_city 							VARCHAR(45) NULL,
  						tb_state_id_state				  INT NOT NULL
            );

        comment on table tb_city  is 'This table serves to storge tb_city records.';

        RAISE INFO 'Table tb_city was created';
    ELSE
        RAISE INFO 'Table tb_city already exists';
    END IF;
END
$$;