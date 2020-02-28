insert into customer (id, first_name, last_name, password) values (1, 'Gabriel', 'Pires', '123456');


SELECT * FROM USUARIO_PRODUTO;

INSERT INTO public.usuario_produto (id, created_by, created_date, last_modified_by, last_modified_date, ativo, produto_id, usuario_id)
VALUES(2, NULL, NULL, NULL, NULL, false, 1, 1);
INSERT INTO public.usuario_produto (id, created_by, created_date, last_modified_by, last_modified_date, ativo, produto_id, usuario_id)
VALUES(3, NULL, NULL, NULL, NULL, true, 2, 1);
INSERT INTO public.usuario_produto (id, created_by, created_date, last_modified_by, last_modified_date, ativo, produto_id, usuario_id)
VALUES(4, NULL, NULL, NULL, NULL, false, 3, 1);

SELECT DISTINCT U.* , p.*  FROM USUARIO_PRODUTO up
JOIN USUARIO u ON up.USUARIO_ID = u.ID
JOIN PRODUTO P ON P.ID = UP.PRODUTO_ID
WHERE u.ID = 1;

SELECT
	usuario1_.id AS id1_1_0_,
	produto2_.id AS id1_0_1_,
	usuario1_.created_by AS created_2_1_0_,
	usuario1_.created_date AS created_3_1_0_,
	usuario1_.last_modified_by AS last_mod4_1_0_,
	usuario1_.last_modified_date AS last_mod5_1_0_,
	usuario1_.ativo AS ativo6_1_0_,
	usuario1_.cpf AS cpf7_1_0_,
	usuario1_.email AS email8_1_0_,
	usuario1_.endereco AS endereco9_1_0_,
	usuario1_.nome_composto AS nome_co10_1_0_,
	usuario1_.telefone AS telefon11_1_0_,
	produto2_.nome AS nome2_0_1_
FROM
	usuario_produto usuariopro0_
INNER JOIN usuario usuario1_ ON
	(
		usuario1_.id = usuariopro0_.usuario_id
		AND usuario1_.ativo = TRUE
	)
INNER JOIN produto produto2_ ON
	(
		produto2_.id = usuariopro0_.produto_id
	)
WHERE
	usuariopro0_.usuario_id =1