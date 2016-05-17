package test.com.wangfj.product.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.core.utils.PropertyUtil;
import com.wangfj.product.core.controller.support.PcmChannelSaleConfigPara;
import com.wangfj.product.core.controller.support.PcmShoppeAUPara;
import com.wangfj.product.core.controller.support.SelectPcmShoppePara;
import com.wangfj.product.organization.domain.vo.PcmChannelSaleConfigDto;

public class TestPcmShoppeMainController {

    @Test
    public void test() {

        String url = PropertyUtil.getSystemUrl("shoppe.shoppeShippingPoint_url");
        System.out.println(url);
        Map<String, Object> map = new HashMap<String, Object>();
        String method = "/StoremanService/store/organizational/structure/warehouseByStore/storeCode=21015";
        String json = HttpUtil.HttpGet(url, method, map);
        System.out.println("请求返回数据：" + json);

        JSONObject fromObject = JSONObject.fromObject(json);
        JSONArray jsonArray = fromObject.getJSONArray("warehouses");
        Object[] array = jsonArray.toArray();
        System.out.println("解析后的数组：" + array);
        System.out.println("解析后的数组：" + array.toString());
        if (array != null && array.length != 0) {
            for (int i = 0; i < array.length; i++) {
                System.out.println("第" + i + "个数组" + array[i]);
                System.out.println();
            }
        }

        List<Map<String, Object>> list = (List<Map<String, Object>>) fromObject.get("warehouses");
        System.out.println(list);
        if (list != null && !list.isEmpty()) {
            for (Map<String, Object> map1 : list) {
                System.out.println("warehouse_id:" + map1.get("warehouse_id") + ",warehouse_name:"
                        + map1.get("warehouse_name"));
            }
        }

    }

    @Test
    public void addShoppe() {

        PcmShoppeAUPara para = new PcmShoppeAUPara();
        // para.setGroupSid(303L);
        // para.setShopSid(306L);
        para.setGroupSid(145L);
        para.setShopSid(149L);
        para.setShoppeName("王府井专柜添加销售可售");
        para.setFromSystem("PCM");

        PcmChannelSaleConfigPara channelSalePara = new PcmChannelSaleConfigPara();
        channelSalePara.setChannelSid("0");
        channelSalePara.setSaleStauts(0);

        List<PcmChannelSaleConfigPara> channelSaleConfigParaList = new ArrayList<PcmChannelSaleConfigPara>();
        channelSaleConfigParaList.add(channelSalePara);
        para.setChannelSaleConfigParaList(channelSaleConfigParaList);

        // PcmChannelSaleConfigDto channelSalePara = new
        // PcmChannelSaleConfigDto();
        // channelSalePara.setChannelSid(2L);
        // channelSalePara.setSaleStauts(0);
        //
        // List<PcmChannelSaleConfigDto> channelSaleConfigParaList = new
        // ArrayList<PcmChannelSaleConfigDto>();
        // channelSaleConfigParaList.add(channelSalePara);
        // para.setChannelSaleConfigParaList(channelSaleConfigParaList);

        System.out.println(JsonUtil.getJSONString(para));

        String url = "http://127.0.0.1:8083/pcm-admin/shoppe/addShoppe.htm";
        String response = HttpUtil.doPost(url, JsonUtil.getJSONString(para));
        System.out.println(response);

    }

    @Test
    public void findPageShoppe() {

        SelectPcmShoppePara selectShoppePara = new SelectPcmShoppePara();

        // selectShoppePara.setShoppeName("鸿星尔克");

        // selectShoppePara.setShoppeName("天津化装欧莱雅专柜");
        String response = HttpUtil.doPost(
                "http://127.0.0.1:8083/pcm-admin/shoppe/findPageShoppe.htm",
                JsonUtil.getJSONString(selectShoppePara));
        System.out.println(response);

    }

}
