package com.thinkgem.jeesite.common;

public enum EightStatus {
	/*
	 * 正常
	 * */
	ok,
	/*
	 * 密码验证失败或权限不足
	 * */
	auth,
	/*
	 * 内部RPC错误 
	 * */
	rpc,
	/*
	 * 数据库错误
	 * */
	db,
	/*
	 * 参数错误 
	 * */
	param,
	/*
	 * 流不存在
	 * */
	notexists,
	/*
	 * 内部错误
	 * */
	internal
	
}
