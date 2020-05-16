package com.edsoft.framework.utilities;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Hashtable;

import com.edsoft.framework.configs.Settings;

public class DatabaseUtil {
	public static Connection Open(String connectionString) {
		try {
			Class.forName(Settings.DriverType).newInstance();
			return DriverManager.getConnection(connectionString);
		} catch (Exception e) {
			System.out.println("Erro data: " + e.getMessage());
		}
		return null;
	}

	public static void Close() {
	}

	public static void ExecuteQuery(String query, Connection connection) {
		Statement statement = null;
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultSet = statement.executeQuery(query);
			resultSet.first();
			do {
				System.out.println(resultSet.getString("username"));
			} while (resultSet.next());
		} catch (Exception e) {
			System.out.println("Erro :" + e.getMessage());
		}
	}

	public static void ExecuteStoredProc(String procedureName, Hashtable parameters, Connection connection) {
		try {
			int parameterLength = parameters.size();
			String paraAppender = null;
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < parameters.size(); i++) {
				builder.append("?,");
			}
			paraAppender = builder.toString();
			paraAppender = paraAppender.substring(0, paraAppender.length() - 1);

			CallableStatement stmt = connection.prepareCall("{Call " + procedureName + "{" + paraAppender + ")}");

			Enumeration params = parameters.keys();

			while (params.hasMoreElements()) {
				String paramsName = (String) params.nextElement();
				stmt.setString(paramsName, parameters.get(paramsName).toString());
			}
			stmt.execute();
		} catch (Exception e) {
		}
	}

//	public Statement stm; 
//	public ResultSet rs; 
//	private final String driver = "org.postgresql.Driver"; 
//	private String caminho = "jdbc:postgresql://localhost:5432/automacaodb";
//	private final String usuario = "postgres";
//	private final String password = "eduhit00";
//	public static Connection con;
//
//	public void massaBanco() {
//		try {
//			System.setProperty("jdbc.Drivers", driver); // setar a propriedade do driver
//			con = DriverManager.getConnection(caminho, usuario, password);
//		} catch (Exception ex) {
//			System.out.println("no caminha da conexao:\n" + ex.getMessage());
//		}
//		try {
//			stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY); // verifica��o de
//			rs = stm.executeQuery("select * from  sco where cod='1'");
//		} catch (Exception ex) {
//			System.out.println("Erro na Query:\n" + ex.getMessage());
//		}
//		try {
//			rs.first();
//			setLogin(rs.getString("login"));
//			setSenha(rs.getString("senha"));
//		} catch (SQLException ex) {
//			JOptionPane.showMessageDialog(null, "Erro na setgem: " + ex.getStackTrace());
//		}
//	}

}
