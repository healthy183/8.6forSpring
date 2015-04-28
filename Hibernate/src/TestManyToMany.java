import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.kang.model.ManyRole;
import com.kang.model.ManyUser;
import com.kang.model.ManyUserRole;


public class TestManyToMany {

	
	public void test(){
		
		List<ManyUser> userList = new ArrayList<ManyUser>();
		
		ManyUser user = new ManyUser();
		user.setUserId("userA");
		user.setUserName("userA");
		userList.add(user);
		
		user = new ManyUser();
		user.setUserId("userB");
		user.setUserName("userB");
		userList.add(user);
		
		List<ManyRole> roleList = new ArrayList<ManyRole>();
		ManyRole  role = new ManyRole();
		role.setRoleId("roleA");
		role.setRoleName("roleA");
		roleList.add(role);
		
		role = new ManyRole();
		role.setRoleId("roleB");
		role.setRoleName("roleB");
		roleList.add(role);
		
		ManyUserRole manyUserRole = null;
		List<ManyUserRole> urList = new ArrayList<ManyUserRole>();
		
		for(ManyUser u : userList){
			for(ManyRole r :roleList){
				
				manyUserRole = new ManyUserRole();
				manyUserRole.setUserRoleId(u.getUserId()+r.getRoleId());
				manyUserRole.setUrName(u.getUserId()+r.getRoleId());
				
				manyUserRole.setManyUser(u);
				u.getManyUserRoles().add(manyUserRole);
				
				manyUserRole.setManyRole(r);
				r.getManyUserRoles().add(manyUserRole);
				
				urList.add(manyUserRole);
			}
		}
		
		
		
		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		Transaction tx	= session.beginTransaction();
		tx.begin();
		
		/**/for(ManyUser u : userList){
			session.save(u);
		}
		
		for(ManyRole r : roleList){
			session.save(r);
		}
		
		//session.flush();
		//session.clear();
		for(ManyUserRole ur : urList){
			session.save(ur);
			
		}
		
		tx.commit();
		session.close();
	}
	
	
	
	
	public static void main(String[] args) {

		TestManyToMany t = new TestManyToMany();
		t.test();
	}

}
