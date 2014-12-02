package logg;



import javax.interceptor.AroundInvoke;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.interceptor.InvocationContext;
public class LoggerInterceptor {
	static final Logger LOG = LoggerFactory.getLogger(LoggerInterceptor.class);
	
    @AroundInvoke
    public Object logg(InvocationContext context) throws Exception{
    	
    	StringBuffer buff = null;
    	for(Object param : context.getParameters()){
    	    if(buff==null){
    	        buff = new StringBuffer();
    	        buff.append(param.toString());
    	    }else{
    	        buff.append("; ").append(param.toString());
    	    }
    	}
    	LOG.info("Kaller metode: " + context.getMethod() + " Input: " + (buff != null ? buff.toString() : ""));

    	try{

    	    Object response = context.proceed();
    	    LOG.info("Kaller metode: " + context.getMethod() + " Output: " + response);
    	    return response;
    	}catch(Exception e){
    		LOG.info("Kaller metode: " + context.getMethod() + " Exception: " + e.getMessage());
    	    throw e;
    	}
    }
}



