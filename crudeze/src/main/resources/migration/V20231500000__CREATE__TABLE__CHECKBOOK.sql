DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_checkbook') THEN
            CREATE TABLE tb_checkbook
            (
              checkbook_id          						SERIAL PRIMARY KEY,
							id_bank														INT NULL,
							number_talon 											VARCHAR(45) NULL,
							status 														CHAR(1) NULL
            );

        comment on table tb_check  is 'This table serves to storge tb_checkbook records.';

        RAISE INFO 'Table tb_checkbook was created';
    ELSE
        RAISE INFO 'Table tb_checkbook already exists';
    END IF;
END
$$;