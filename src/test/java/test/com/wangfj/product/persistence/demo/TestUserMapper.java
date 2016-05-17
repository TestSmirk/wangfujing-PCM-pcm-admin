package test.com.wangfj.product.persistence.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.demo.domain.entity.Users;
import com.wangfj.product.demo.persistence.UsersMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
        "classpath:applicationContext.xml"
        })
public class TestUserMapper  {
	@Autowired
	public UsersMapper usersMapper;
	
	@Test
    public void test(){
		//updateUser();
//		insertUser();
    }
	public void updateUser(){
		Users para = new Users();
		para.setName("ko");
		para.setSid(4);		
		int update = usersMapper.update(para);
		if (update == 0) {
			throw new BleException(ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
		}
	}
	public void insertUser(){
		Users para  = new Users();
		para.setName("test1");
		para.setAge(1);
		Integer result = usersMapper.insert(para);
		if(result.intValue()!=1){
			throw new BleException(ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
		}
	}
}
