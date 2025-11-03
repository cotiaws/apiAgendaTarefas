package br.com.cotiinformatica.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.entities.Categoria;
import br.com.cotiinformatica.factories.ConnectionFactory;

@Repository
public class CategoriaRepository {

	@Autowired
	private ConnectionFactory connectionFactory;
	
	/*
	 * Método para consultar as categorias
	 */
	public List<Categoria> findAll() {
		
		try {
			
			//Abrindo uma conexão com o banco de dados do PostgreSQL
			var connection = connectionFactory.getConnection();
			
			//escrever o comando SQL que será executado no banco de dados
			var sql = """
						select id, nome
						from categoria
						order by nome
					""";
			
			//executar o comando sql no banco de dados e capturar a resposta
			var statement = connection.prepareStatement(sql);
			var result = statement.executeQuery();
			
			//criando uma lista de categorias vazia
			var lista = new ArrayList<Categoria>();
			
			//ler cada registro obtido do banco de dados
			while(result.next()) {
				
				//criando um objeto da classe Categoria
				var categoria = new Categoria();
				
				//preenchendo o objeto da classe Categoria com os
				//valores dos campos que estamos lendo da tabela do banco
				categoria.setId(UUID.fromString(result.getString("id")));
				categoria.setNome(result.getString("nome"));
				
				lista.add(categoria); //adicionando o objeto na lista
			}
			
			//fechando a conexão do banco de dados
			connection.close();
			
			//retornando a lista de categorias
			return lista;
		}
		catch(Exception e) {
			//imprimir um log de erros no servidor
			e.printStackTrace();
			//retornando vazio
			return null;
		}
	}
}
