package com.zl.resources;

public class SimpleResponseFactory {
	public static RSimpleResponse generateSuccessfulSerciveResponseTemplate() {
		RSimpleResponse response = new RSimpleResponse();
		
		response.setError(new RError());
		response.getError().setCode(0);
		response.getError().setType("");
		response.getError().setMessage("");
		
		response.setResponse(new RResponse());
		response.getResponse().setIs_succeed(true);
		
		return response;
	}
	
	public static RSimpleResponse generateFailSerciveResponseTemplate() {
		RSimpleResponse response = new RSimpleResponse();
		
		response.setError(new RError());
		response.getError().setCode(1);
		response.getError().setType("");
		response.getError().setMessage("");
		
		response.setResponse(new RResponse());
		response.getResponse().setIs_succeed(false);
		
		return response;
	}
	
	public static RSimpleResponse generateFailSerciveResponseTemplate(int code, String type, String message) {
		RSimpleResponse response = new RSimpleResponse();
		
		response.setError(new RError());
		response.getError().setCode(code);
		response.getError().setType(type);
		response.getError().setMessage(message);
		
		response.setResponse(new RResponse());
		response.getResponse().setIs_succeed(false);
		
		return response;
	}
}
