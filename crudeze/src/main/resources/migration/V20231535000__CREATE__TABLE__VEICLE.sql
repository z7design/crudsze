DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_veicle') THEN
            CREATE TABLE tb_veicle
            (
              veicle_id          								SERIAL PRIMARY KEY,
							description 											VARCHAR(60) NULL DEFAULT NULL,
							purchase_value 										DECIMAL(10,0) NULL DEFAULT NULL,
							purchase_date 										DATE NULL DEFAULT NULL,
							current_km 												VARCHAR(8) NULL DEFAULT NULL,
							prefix 														VARCHAR(45) NULL DEFAULT NULL,
							chassis 													VARCHAR(45) NULL DEFAULT NULL,
							plate 														VARCHAR(45) NULL DEFAULT NULL,
							year_veicle 											VARCHAR(45) NULL DEFAULT NULL,
							model_veicle 										  VARCHAR(45) NULL DEFAULT NULL,
							vehicle_license_plate 						VARCHAR(45) NULL DEFAULT NULL,
							uf 																VARCHAR(45) NULL DEFAULT NULL,
							quatify_poltronas 								VARCHAR(45) NULL DEFAULT NULL,
							brand 														VARCHAR(45) NULL DEFAULT NULL,
							cia_insureance 										VARCHAR(45) NULL DEFAULT NULL,
							number_insurance_policy 					VARCHAR(45) NULL DEFAULT NULL,
							insurance_expiration 							DATE NULL DEFAULT NULL,
							validate_cartao_der 							DATE NULL DEFAULT NULL,
							historyc_trips 										VARCHAR(45) NULL DEFAULT NULL,
							imagens 													VARCHAR(100) NULL DEFAULT NULL,
							release_status 										VARCHAR(45) NULL DEFAULT NULL,
							operational_core 									VARCHAR(45) NULL DEFAULT NULL,
							observation 											VARCHAR(100) NULL DEFAULT NULL,
							mandatory_items 									VARCHAR(45) NULL DEFAULT NULL,
							ipva 															VARCHAR(45) NULL DEFAULT NULL,
							expiration_ipva 									DATE NULL DEFAULT NULL,
							fleet_number 											VARCHAR(45) NULL DEFAULT NULL,
							combustive 												VARCHAR(45) NULL DEFAULT NULL,
							market_value 											DECIMAL(10,0) NULL DEFAULT NULL,
							tb_accessory_id 									INT NOT NULL,
							tb_tipoveiculo 										INT NOT NULL,
							tb_trip_id 										    INT NOT NULL,
							tb_client_id 											INT NOT NULL,
							tb_fretamento_cf_codigo 					INT NOT NULL,
							tb_controlemultas_cm_codigo 			INT NOT NULL,
							tb_accessories 										INT NOT NULL,
							tb_company_id 										INT NOT NULL,
							tb_document_id 										INT NOT NULL
            );

        comment on table tb_veicle  is 'This table serves to storge tb_veicle records.';

        RAISE INFO 'Table tb_veicle was created';
    ELSE
        RAISE INFO 'Table tb_veicle already exists';
    END IF;
END
$$;