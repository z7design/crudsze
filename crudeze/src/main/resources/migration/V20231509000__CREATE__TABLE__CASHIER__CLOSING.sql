DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_cashier_closing') THEN
            CREATE TABLE tb_cashier_closing
            (
              cashier_closing_id          						SERIAL PRIMARY KEY

            );

        comment on table tb_cashier_closing  is 'This table serves to storge tb_cashier_closing records.';

        RAISE INFO 'Table tb_cashier_closing was created';
    ELSE
        RAISE INFO 'Table tb_cashier_closing already exists';
    END IF;
END
$$;