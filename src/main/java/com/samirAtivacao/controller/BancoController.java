package com.samirAtivacao.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.samirAtivacao.banco.ConexaoSQLite;
import com.samirAtivacao.modelo.InfomacoesDosPrev;
import com.samirAtivacao.modelo.InformacoesCessado;
import com.samirAtivacao.modelo.Usuario;

public class BancoController {
	ControllerUsuario controller;
	 public Usuario loginInformacoes() {
		 Usuario usuario =  new Usuario();
		 ConexaoSQLite conexao = new ConexaoSQLite();
	        conexao.conectar();
	        String sql = "SELECT * FROM usuario where id= 1";
	        PreparedStatement stmt = conexao.criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        try {
	        	ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	            	usuario.setNome(rs.getString("nome"));
	            	usuario.setCpf(rs.getString("cpf"));
	            	usuario.setSenha(rs.getString("senha"));
	            	usuario.setEtiqueta(rs.getString("etiqueta"));
	            	
	            }
				
			} catch (Exception e) {
				// TODO: handle exception
			}
	        conexao.desconectar();
	        return usuario;
		 
	 }
	 public List<InfomacoesDosPrev> litaDosPrev() {
		 ConexaoSQLite conexao = new ConexaoSQLite();
	        conexao.conectar();
	        String sql = "SELECT * FROM InfomacoesDosPrev";
	        PreparedStatement stmt = conexao.criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        @SuppressWarnings("unchecked")
			List<InfomacoesDosPrev> lista = new ArrayList<InfomacoesDosPrev>();
	        try {
	        	ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	            	InfomacoesDosPrev info = new InfomacoesDosPrev();
	            	info.setNome(rs.getString("nome"));
	            	info.setCpf(rs.getString("cpf"));
	            	info.setAps(rs.getString("aps"));
	            	info.setNumeroDoProcesso(rs.getString("numeroDoProcesso"));
	            	info.setDataAjuizamento(rs.getString("dataAjuizamento"));
	            	info.setDibInicial(rs.getString("dibInicial"));
	            	info.setDibFinal(rs.getString("dibFinal"));
	            	info.setRmi(rs.getString("rmi"));
	            	info.setBeneficio(rs.getString("beneficio"));
	            	info.setNb(rs.getString("nb"));
	            	info.setDip(rs.getString("dip"));
	            	info.setCitacao(rs.getString("citacao"));
					info.setCessado(rs.getString("nbUnido"));
					info.setUrlProcesso(rs.getString("urlProcesso"));
					info.setDibAnterior(rs.getString("dibAnterior"));
					info.setTipo(rs.getString("tipo"));
	            	lista.add(info);
	            	
	            }
				
			} catch (Exception e) {
				// TODO: handle exception
			}
	        
	        conexao.desconectar();
	        return lista;
	}

	public List<InformacoesCessado> listaCessado() {
		ConexaoSQLite conexao = new ConexaoSQLite();
		conexao.conectar();
		String sql = "SELECT * FROM informacoesCessado";
		PreparedStatement stmt = conexao.criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		@SuppressWarnings("unchecked")
		List<InformacoesCessado> lista = new ArrayList<InformacoesCessado>();
		try {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				InformacoesCessado info = new InformacoesCessado();
				info.setEspecie(rs.getString("especie"));
				info.setDataDeinicio(rs.getString("dataDeInicio"));
				info.setDataFinalBeneficio(rs.getString("dataFinalBeneficio"));
				info.setRmi(rs.getString("rmi"));
				info.setNb(rs.getString("nb"));
				lista.add(info);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		conexao.desconectar();
		return lista;
	}

	
}
