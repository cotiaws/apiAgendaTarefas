package br.com.cotiinformatica.factories;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConnectionFactory {

	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	/*
	 * Método para realizar a conexão com o banco de dados
	 * do PostgreSQL e retornar esta conexão para as 
	 * classes de repositório do sistema.
	 */
	public Connection getConnection() {
		try {			
			//Abrindo e retornando uma conexão com o banco de dados
			return DriverManager.getConnection(url, username, password);
		}
		catch(Exception e) {
			e.printStackTrace(); //imprimir um log de erro
			return null; //retornar vazio
		}
	}
}
