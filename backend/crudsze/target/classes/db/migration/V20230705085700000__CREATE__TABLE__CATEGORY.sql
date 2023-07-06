DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'category') THEN
            CREATE TABLE category
            (
              category_id           			UUID PRIMARY KEY DEFAULT gen_random_uuid(),
							name												VARCHAR(30) NULL DEFAULT NULL,
							description 								VARCHAR(100) NULL DEFAULT NULL
            );

        comment on table category  is 'This table serves to storge category records.';

        RAISE INFO 'Table category was created';
    ELSE
        RAISE INFO 'Table category already exists';
    END IF;
END
$$;