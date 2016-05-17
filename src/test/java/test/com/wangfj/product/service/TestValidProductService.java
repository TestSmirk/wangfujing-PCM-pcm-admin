package test.com.wangfj.product.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.core.utils.CacheUtils;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.demo.domain.dto.GetUsersDto;
import com.wangfj.product.demo.domain.dto.UpdateUsersDto;
import com.wangfj.product.demo.domain.entity.Users;
import com.wangfj.product.demo.service.intf.UserService;
import com.wangfj.product.maindata.domain.vo.ProductListDto;
import com.wangfj.product.maindata.domain.vo.ValidProductDto;
import com.wangfj.product.maindata.domain.vo.ValidResultDto;
import com.wangfj.product.maindata.service.intf.IValidProductService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
        "classpath:applicationContext.xml"
        })
public class TestValidProductService  {
	
	@Autowired
	public IValidProductService vs;
	@Autowired
	protected CacheUtils utils;
	
	@Test
    public void test(){
		getPISValidProduct();
    }

	/**
	 * 测试验证serivce
	 * 
	 * @Methods Name getPISValidProduct
	 * @Create In 2015年7月14日 By wangsy void
	 */
	public void getPISValidProduct() {
		ValidProductDto vpd = new ValidProductDto();
		ValidResultDto vrVo = new ValidResultDto();
		List<ProductListDto> listProductDto = new ArrayList<ProductListDto>();
		vpd.setSupplierCode("001830A");
		vpd.setCounterCode("2101100023");
		vpd.setProductCount("1");
		for (int i = 0; i < 2; i++) {
			ProductListDto pld = new ProductListDto();
			if (i == 0) {
				pld.setErpProductCode("03529836");
				pld.setProductNum("6903148190821");
				pld.setBrandCode("1010807");
				pld.setColorCode("红色");
				pld.setSizeCode("100g");
			} else {
				pld.setErpProductCode("-1");
				pld.setProductNum("-1");
				pld.setBrandCode("-1");
				pld.setColorCode("-1");
				pld.setSizeCode("-1");
			}
			listProductDto.add(pld);
		}
		vpd.setListProductDto(listProductDto);
//		vrVo = vs.getPISValidProductFromEfuture(vpd);
		System.out.println(JsonUtil.getJSONString(vrVo));
	}
}
