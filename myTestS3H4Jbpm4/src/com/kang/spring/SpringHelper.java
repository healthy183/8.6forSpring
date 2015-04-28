package com.kang.spring;

import org.jbpm.api.ProcessEngine;
import org.jbpm.pvm.internal.cfg.ConfigurationImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringHelper  implements ApplicationContextAware, DisposableBean {

	public SpringHelper() {
		jbpmCfg = "jbpm.cfg.xml";
	}

	public void setJbpmCfg(String jbpmCfg) {
		this.jbpmCfg = jbpmCfg;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	public ProcessEngine createProcessEngine() {
		
	/*	System.out.println("jbpmCfg:"+jbpmCfg == null);
		System.out.println("applicationContext:"+applicationContext == null);
		System.out.println("processEngine:"+processEngine == null);*/
		
		System.out.println(jbpmCfg);
		System.out.println(applicationContext);
		System.out.println(processEngine);
		
	processEngine = 
		(new ConfigurationImpl())
			.springInitiated(applicationContext)
					.setResource(jbpmCfg)
							.buildProcessEngine();
		
		System.out.println(processEngine);
		
		return processEngine;
	}

	public void destroy() throws Exception {
		if (processEngine != null) {
			processEngine.close();
			processEngine = null;
		}
	}

	protected ApplicationContext applicationContext;
	protected String jbpmCfg;
	protected ProcessEngine processEngine;
}
