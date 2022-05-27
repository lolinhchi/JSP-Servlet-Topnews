package com.topnews.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.topnews.dao.GenericDAO;
import com.topnews.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T>{

	ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
	public Connection getConnection() {
		try {
			/*Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@//localhost:1521/orcl";
			String user = "system";
			String pass = "Lolinhchi09";*/
			Class.forName(resourceBundle.getString("driverName"));
			String url = resourceBundle.getString("url");
			String user = resourceBundle.getString("user");
			String pass = resourceBundle.getString("password");
			return DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException  | SQLException e) {
			System.out.println("Kết nối oracle không thành công!");
			return null;
			
		}
	}

	@Override
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
		List<T> results = new ArrayList<T>();
		Connection conn = null; 
		PreparedStatement state = null;
		ResultSet resultSet = null;
		try {
			conn = getConnection();
			state = conn.prepareStatement(sql);
			//set parameters
			setParameter(state, parameters);
			resultSet = state.executeQuery();
			while(resultSet.next()) {
				results.add(rowMapper.mapRow(resultSet));
			}
			return results;
		} catch (SQLException e) {
			return null;
		}finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(state != null) {
					state.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e2) {
				return null;
			}
		}
	}

	private void setParameter(PreparedStatement state, Object... parameters) {
		try {
			for(int i = 0; i < parameters.length; i++) {
				Object parameter = parameters[i];
				int index = i +1;
				if(parameter instanceof Long) {
					state.setLong(index, (Long) parameter);
				}else if(parameter instanceof String) {
					state.setString(index, (String) parameter);
				}else if (parameter instanceof Integer) {
					state.setInt(index, (Integer) parameter);
				}else if(parameter instanceof Timestamp) {
					state.setTimestamp(index, (Timestamp) parameter);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(String sql, Object... parameters) {
		Connection conn = null;
		PreparedStatement state = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			state = conn.prepareStatement(sql);
			setParameter(state, parameters);
			state.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			if(conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(state != null) {
					state.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			
		}
	}

	@Override
	public Long insert(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Long id = null;
			connection = getConnection();
			connection.setAutoCommit(false);
			String generatedColumns[] = {"id"};
			statement = connection.prepareStatement(sql, generatedColumns);
			setParameter(statement, parameters);
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getLong(1);
			}
			connection.commit();
			return id;
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public int count(String sql, Object... parameters) {
		Connection conn = null; 
		PreparedStatement state = null;
		ResultSet resultSet = null;
		try {
			int count = 0;
			conn = getConnection();
			state = conn.prepareStatement(sql);
			//set parameters
			setParameter(state, parameters);
			resultSet = state.executeQuery();
			while(resultSet.next()) {
				count = resultSet.getInt("COUNT(*)");
			}
			return count;
		} catch (SQLException e) {
			return 0;
		}finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(state != null) {
					state.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e2) {
				return 0;
			}
		}
	}
}
