DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_state') THEN
            CREATE TABLE tb_state
            (
              state_id          						UUID PRIMARY KEY DEFAULT gen_random_uuid(),
							name_state 							VARCHAR(45) NULL
            );

        comment on table tb_state  is 'This table serves to storge tb_state records.';

        RAISE INFO 'Table tb_state was created';
    ELSE
        RAISE INFO 'Table tb_state already exists';
    END IF;
END
$$;