DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'state') THEN
            CREATE TABLE state
            (
              state_id          			UUID PRIMARY KEY DEFAULT gen_random_uuid(),
							name 										VARCHAR(45) NULL,
							sigla 									VARCHAR(3) NULL
            );

        comment on table state  is 'This table serves to storge state records.';

        RAISE INFO 'Table state was created';
    ELSE
        RAISE INFO 'Table state already exists';
    END IF;
END
$$;