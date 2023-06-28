DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_movement_cash_bank') THEN
            CREATE TABLE tb_movement_cash_bank
            (
              movement_cash_bank_id          						UUID PRIMARY KEY DEFAULT gen_random_uuid()

            );

        comment on table tb_movement_cash_bank  is 'This table serves to storge tb_movement_cash_bank records.';

        RAISE INFO 'Table tb_movement_cash_bank was created';
    ELSE
        RAISE INFO 'Table tb_movement_cash_bank already exists';
    END IF;
END
$$;