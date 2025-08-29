INSERT INTO tb_usuario(nome, senha) VALUES ('José', '123');
INSERT INTO tb_usuario(nome, senha) VALUES ('Maria', '123');


INSERT INTO tb_conta(numero_conta, saldo, usuario_id) VALUES (1234, 1000.00, 1);
INSERT INTO tb_conta(numero_conta, saldo, usuario_id) VALUES (5678, 1000.00, 2);

INSERT INTO tb_transferencia(conta_origem_id, conta_destino_id, data_agendamento, data_transferencia, taxa, valor, status) VALUES (1, 2, '2025-08-29', '2025-08-29', 12.00, 100.00, 'CONCLUIDO');