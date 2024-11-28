package edu.fra.uas.v3autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("pleaseFlex")
public class Flexing implements Work{
    
     public static final Logger logger = LoggerFactory.getLogger(Flexing.class);
	
    @Override
	public void doWork() {
		logger.info(" --> flex through the metal bar");
	}
}
