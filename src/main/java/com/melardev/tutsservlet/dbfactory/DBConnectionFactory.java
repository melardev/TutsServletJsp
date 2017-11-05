package com.melardev.tutsservlet.dbfactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class DBConnectionFactory {

	private DataSource dataSource;
	private static DBConnectionFactory instance = new DBConnectionFactory();

	public DataSource initDataSource() throws SQLException, IOException {
		InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("db-properties.conf");
		Properties dbProperties = new Properties();
		dbProperties.load(inStream);
		inStream.close();

		//create Tomcat specific pool properties
		PoolProperties props = new PoolProperties();
		props.setUrl("jdbc:mysql://" + dbProperties.getProperty("db_host") + ":" + dbProperties.getProperty("db_port")
				+ "/" + dbProperties.getProperty("db_name"));

		props.setDriverClassName(dbProperties.getProperty("db_driver"));
		props.setUsername(dbProperties.getProperty("db_username"));
		props.setPassword(dbProperties.getProperty("db_password"));
		props.setMaxActive(30);
		props.setRemoveAbandonedTimeout(60);
		dataSource = new DataSource();
		dataSource.setPoolProperties(props);
		return dataSource;
	}

	public static DBConnectionFactory getInstance() {
		return instance;
	}

	public Connection getConnection() throws SQLException, IOException {
		if (dataSource != null)
			return dataSource.getConnection();
		return initDataSource().getConnection();
	}

}
