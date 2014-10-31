package com.sdw.soft.core.dao;

/**
 * @author Sonicery_D
 * @date 2014年10月31日
 * @version 1.0.0
 * @description base Dao
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class AncestorDao {

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public AncestorDao() {
		super();
	}

}