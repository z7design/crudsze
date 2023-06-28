DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_cost_center') THEN
            CREATE TABLE tb_cost_center
            (
              cost_center_id          		UUID PRIMARY KEY DEFAULT gen_random_uuid(),
              description 								VARCHAR(45) NULL DEFAULT NULL,
  						departament 								VARCHAR(45) NULL DEFAULT NULL
            );

        comment on table tb_cost_center  is 'This table serves to storge tb_cost_center records.';

        RAISE INFO 'Table tb_cost_center was created';
    ELSE
        RAISE INFO 'Table tb_cost_center already exists';
    END IF;
END
$$;