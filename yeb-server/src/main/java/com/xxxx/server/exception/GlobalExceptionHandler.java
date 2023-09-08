package com.xxxx.server.exception;

import com.xxxx.server.common.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常
 *
 * @since 1.0.0
 */
@RestControllerAdvice  //监听Controller的异常
public class GlobalExceptionHandler {


	//监听SQL异常
	@ExceptionHandler(SQLException.class)
	public RespBean mySQLException(SQLException e) {
		if (e instanceof SQLIntegrityConstraintViolationException) {
			return RespBean.error("该数据有关数据，操作失败！");
		}
		return RespBean.error("数据库异常，操作失败！");

	}
}