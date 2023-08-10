DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_state') THEN
            CREATE TABLE tb_state
            (
              state_id          			SERIAL PRIMARY KEY,
							name 										VARCHAR(45),
							uf 									    VARCHAR(3)
            );

        comment on table tb_state  is 'This table serves to storge state records.';

        RAISE INFO 'Table tb_state was created';
    ELSE
        RAISE INFO 'Table tb_state already exists';
    END IF;
END
$$;