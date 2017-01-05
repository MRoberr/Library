package edu.msg.library_server.backend.service;

public class ServiceException extends RuntimeException  {
	private static final long serialVersionUID = 1L;
	
	public ServiceException(){
		super();
	}
	public ServiceException(String message){
		super(message);
	}
	public ServiceException(String message, Exception ex){
		super(message,ex);
	}
}
