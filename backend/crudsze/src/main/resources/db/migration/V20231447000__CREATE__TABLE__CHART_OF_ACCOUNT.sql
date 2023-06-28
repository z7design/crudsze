DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_chart_of_accounts') THEN
            CREATE TABLE tb_chart_of_accounts
            (
              chart_of_accounts_id          						UUID PRIMARY KEY DEFAULT gen_random_uuid(),
							 name_chart_of_account 										VARCHAR(45) NULL DEFAULT NULL,
  						 description 															VARCHAR(45) NULL DEFAULT NULL
            );

        comment on table tb_check  is 'This table serves to storge tb_check records.';

        RAISE INFO 'Table tb_check was created';
    ELSE
        RAISE INFO 'Table tb_check already exists';
    END IF;
END
$$;