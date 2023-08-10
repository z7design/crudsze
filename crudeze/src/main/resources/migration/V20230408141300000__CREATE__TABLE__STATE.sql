DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_state') THEN
            CREATE TABLE tb_state
            (
              state_id          SERIAL PRIMARY KEY DEFAULT,
							name							VARCHAR(30) NULL DEFAULT NULL,
							uf 								VARCHAR(100) NULL DEFAULT NULL
            );

        comment on table category  is 'This table serves to storge category records.';

        RAISE INFO 'Table tb_state was created';
    ELSE
        RAISE INFO 'Table tb_state already exists';
    END IF;
END
$$;