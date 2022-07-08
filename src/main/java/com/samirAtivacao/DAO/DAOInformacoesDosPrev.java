package com.samirAtivacao.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.samirAtivacao.banco.ConexaoSQLite;
import com.samirAtivacao.modelo.InfomacoesDosPrev;

//teste git

public class DAOInformacoesDosPrev extends ConexaoSQLite {
	public boolean salvarInformacoesDosPrev  (InfomacoesDosPrev informacoes) {
		conectar();
		String sql = "INSERT INTO InfomacoesDosPrev("
				+"numeroDoProcesso, "
				+"nome,"
				+"dataAjuizamento,"
				+"cpf,"
				+"dibInicial,"
				+"dibFinal,"
				+"rmi,"
				+"beneficio,"
				+"nb,"
				+"aps,"
				+ "dip,"
				+ "citacao,"
				+ "nbUnido,"
				+"urlProcesso,"
		        +"dibAnterior)"
				+"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		try {
			preparedStatement.setString(1, informacoes.getNumeroDoProcesso());
			preparedStatement.setString(2, informacoes.getNome());
			preparedStatement.setString(3, informacoes.getDataAjuizamento());
			preparedStatement.setString(4, informacoes.getCpf());
			preparedStatement.setString(5, informacoes.getDibInicial());
			preparedStatement.setString(6, informacoes.getDibFinal());
			preparedStatement.setString(7, informacoes.getRmi());
			preparedStatement.setString(8, informacoes.getBeneficio());
			preparedStatement.setString(9, informacoes.getNb());
			preparedStatement.setString(10, informacoes.getAps());
			preparedStatement.setString(11, informacoes.getDip());
			preparedStatement.setString(12, informacoes.getCitacao());
			preparedStatement.setString(13, informacoes.getCessado());
			preparedStatement.setString(14, informacoes.getUrlProcesso());
			preparedStatement.setString(15, informacoes.getDibAnterior());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		desconectar();
		return true;
	}
	public boolean excluirUInfomacoesDosPrev() {
		conectar();
		PreparedStatement preparedStatment;
		String sql = "DELETE FROM InfomacoesDosPrev";
		preparedStatment = this.criarPreparedStatement(sql);
		try {
			preparedStatment.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		} finally {
			if (preparedStatment != null) {
				try {
					preparedStatment.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
		
		this.desconectar();
		return true;
	}

}
