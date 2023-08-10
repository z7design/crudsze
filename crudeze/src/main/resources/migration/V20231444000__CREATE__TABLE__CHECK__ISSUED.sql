DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_check_issued') THEN
            CREATE TABLE tb_check_issued
            (
              check_issued_id          								SERIAL PRIMARY KEY,
							emission_date 													DATE NULL,
							compensation_date 											DATE NULL,
							value_check_issue 											DECIMAL(18,6) NULL,
							nomial_a 																VARCHAR(100) NULL,
							bompara 																VARCHAR(45) NULL
            );

        comment on table tb_check_issued  is 'This table serves to storge tb_check_issued records.';

        RAISE INFO 'Table tb_check_issued was created';
    ELSE
        RAISE INFO 'Table tb_check_issued already exists';
    END IF;
END
$$;