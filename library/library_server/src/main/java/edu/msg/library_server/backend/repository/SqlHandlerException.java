package edu.msg.library_server.backend.repository;

public class SqlHandlerException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public SqlHandlerException(){
		super();
	}
	public SqlHandlerException(String message){
		super(message);
	}
	public SqlHandlerException(String message, Exception ex){
		super(message,ex);
	}

	
}
