package aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WriterName {
    protected static final Logger logger = LogManager.getLogger();
    
    @Pointcut("execution(* controllers.NameController.getSex(..))")
    private void addHeading() {}
    
    @After("addHeading()")
    public void returnRes() {
        logger.info("get sex operation");
    }
}
