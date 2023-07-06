DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'city') THEN
            CREATE TABLE city
            (
              city_id          		UUID PRIMARY KEY DEFAULT gen_random_uuid(),
              state_id			 			UUID REFERENCES state(state_id),
							name 								VARCHAR(45) NOT NULL

            );

        comment on table city  is 'This table serves to storge city records.';

        RAISE INFO 'Table city was created';
    ELSE
        RAISE INFO 'Table city already exists';
    END IF;
END
$$;