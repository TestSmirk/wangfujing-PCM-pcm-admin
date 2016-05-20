package com.wangfj.product.core.controller;

import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.*;
import com.wangfj.product.brand.domain.entity.PcmBrand;
import com.wangfj.product.brand.domain.vo.PcmBrandDto;
import com.wangfj.product.brand.domain.vo.SelectPcmBrandPageDto;
import com.wangfj.product.brand.service.intf.IPcmBrandService;
import com.wangfj.product.core.controller.support.PcmBrandPara;
import com.wangfj.product.core.controller.support.PcmBrandRelationPara;
import com.wangfj.product.core.controller.support.SelectPcmBrandPagePara;
import com.wangfj.util.Constants;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 品牌管理Controller
 *
 * @Class Name PcmBrandController
 * @Author wangx
 * @Create In 2015年7月28日
 */
@Controller
@RequestMapping(value = "/pcmAdminBrand", produces = "application/json;charset=utf-8")
public class PcmBrandController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(PcmBrandController.class);

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private IPcmBrandService brandService;

    /**
     * 创建品牌
     *
     * @param brandPara
     * @param request
     * @return Map<String,Object>
     * @Methods Name addPcmBrand
     * @Create In 2015-8-18 By wangx
     */
    @RequestMapping(value = "/addPcmBrand", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> addPcmBrand(@RequestBody @Valid PcmBrandPara brandPara,
                                           HttpServletRequest request) {

        Integer result = Constants.PUBLIC_0;

        PcmBrand brand = new PcmBrand();
        try {
            BeanUtils.copyProperties(brand, brandPara);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        brand.setOptUpdateTime(new Date());
        // 如果没有关系将关系置空
        if (!StringUtils.isNotEmpty(brandPara.getParentSid())) {
            brand.setParentSid(new Long(Constants.PUBLIC_0));
        }
        if (!StringUtils.isNotEmpty(brandPara.getShopType())) {
            brand.setShopType(null);
        }
        if (!StringUtils.isNotEmpty(brandPara.getShopSid())) {
            brand.setShopSid(null);
        }

        try {
            result = brandService.addBrand(brand);
        } catch (BleException ble) {
            return ResultUtil.creComErrorResult(ble.getCode(), ble.getMessage());
        }

        if (result.equals(Constants.PUBLIC_1)) {

            // 下发门店品牌数据(增量)
            Long sid = brand.getSid();
            if (sid != null) {

                List<Map<String, Object>> pushList = new ArrayList<Map<String, Object>>();
                Map<String, Object> paraMap = new HashMap<String, Object>();
                paraMap.put("sid", sid);
                paraMap.put("actionCode", Constants.A);
                pushList.add(paraMap);
                final String json = JsonUtil.getJSONString(pushList);

                taskExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        String url = PropertyUtil.getSystemUrl("pcm-syn")
                                + "pcmSynBrand/pushBrand.htm";
                        HttpUtil.doPost(url, json);
                    }
                });

                taskExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        String searchUrl = PropertyUtil.getSystemUrl("pcm-syn")
                                + "pcmSynBrand/pushBrandToSearch.htm";
                        HttpUtil.doPost(searchUrl, json);
                    }
                });

                taskExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        String offlineSearchUrl = PropertyUtil.getSystemUrl("pcm-syn")
                                + "pcmSynBrand/pushBrandToOfflineSearch.htm";
                        HttpUtil.doPost(offlineSearchUrl, json);
                    }
                });

                taskExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        String brandGroupUrl = PropertyUtil.getSystemUrl("pcm-syn")
                                + "pcmSynBrand/pushBrandGroupToSearch.htm";
                        HttpUtil.doPost(brandGroupUrl, json);
                    }
                });
            }

            return ResultUtil.creComSucResult("");
        } else {
            return ResultUtil.creComErrorResult(
                    ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
        }

    }

    /**
     * 修改品牌
     *
     * @param brandPara
     * @param request
     * @return Map<String,Object>
     * @Methods Name updateBrand
     * @Create In 2015-8-18 By wangx
     */
    @RequestMapping(value = "/updateBrand", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> updateBrand(@RequestBody @Valid PcmBrandPara brandPara,
                                           HttpServletRequest request) {

        PcmBrand brand = new PcmBrand();

        Integer result = Constants.PUBLIC_0;
        if (brandPara.getSid() != null) {

            // 参数赋值
            try {
                BeanUtils.copyProperties(brand, brandPara);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            if (!StringUtils.isNotEmpty(brandPara.getParentSid())) {
                brand.setParentSid(null);
            }
            if (!StringUtils.isNotEmpty(brandPara.getShopSid())) {
                brand.setShopSid(null);
            }

            // 判断修改的是集团品牌还是门店品牌
            Integer brandType = brandPara.getBrandType();
            if (brandType == Constants.PUBLIC_0) {
                try {
                    result = brandService.updateBrandGroup(brand);
                } catch (BleException ble) {
                    return ResultUtil.creComErrorResult(ble.getCode(), ble.getMessage());
                }
            }

            if (brandType == Constants.PUBLIC_1) {

                String shopType = brandPara.getShopType();
                if (!StringUtils.isNotEmpty(shopType)) {
                    throw new BleException(ErrorCode.BRAND_SHOPTYPE_IS_NULL.getErrorCode(),
                            ErrorCode.BRAND_SHOPTYPE_IS_NULL.getMemo());
                }

                try {
                    result = brandService.updatePcmBrand(brand);
                } catch (BleException ble) {
                    return ResultUtil.creComErrorResult(ble.getCode(), ble.getMessage());
                }
            }

        } else {
            logger.error("修改品牌时没有传入Sid");
            throw new BleException(ErrorCode.BRAND_SID_IS_NULL.getErrorCode(),
                    ErrorCode.BRAND_SID_IS_NULL.getMemo());
        }

        if (result.equals(1)) {

            // 下发门店品牌数据(增量)
            Long sid = brand.getSid();
            List<Map<String, Object>> pushList = new ArrayList<Map<String, Object>>();
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("sid", sid);
            paraMap.put("actionCode", Constants.U);
            pushList.add(paraMap);
            final String json = JsonUtil.getJSONString(pushList);
            if (sid != null) {
                taskExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        String url = PropertyUtil.getSystemUrl("pcm-syn")
                                + "pcmSynBrand/pushBrand.htm";
                        HttpUtil.doPost(url, json);
                    }
                });
                taskExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        String searchUrl = PropertyUtil.getSystemUrl("pcm-syn")
                                + "pcmSynBrand/pushBrandToSearch.htm";
                        HttpUtil.doPost(searchUrl, json);
                    }
                });
                taskExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        String offlineSearchUrl = PropertyUtil.getSystemUrl("pcm-syn")
                                + "pcmSynBrand/pushBrandToOfflineSearch.htm";
                        HttpUtil.doPost(offlineSearchUrl, json);
                    }
                });
            }

            return ResultUtil.creComSucResult("");

        } else {

            return ResultUtil.creComErrorResult(
                    ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
        }

    }

    /**
     * 分页查询品牌
     *
     * @param para
     * @param request
     * @return Map<String,Object>
     * @Methods Name findPageBrand
     * @Create In 2015-9-25 By wangxuan
     */
    @RequestMapping(value = "/findPageBrand", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> findPageBrand(@RequestBody SelectPcmBrandPagePara para,
                                             HttpServletRequest request) {

        SelectPcmBrandPageDto dto = new SelectPcmBrandPageDto();

        String brandName = para.getBrandName();
        String brandSid = para.getBrandSid();
        Integer brandType = para.getBrandType();
        Integer currentPage = para.getCurrentPage();
        Integer pageSize = para.getPageSize();
        String parentSid = para.getParentSid();
        Integer shopType = para.getShopType();
        Long sid = para.getSid();
        String spell = para.getSpell();

        if (StringUtils.isNotEmpty(brandName)) {
            dto.setBrandName(brandName);
        }

        if (StringUtils.isNotEmpty(brandSid)) {
            dto.setBrandSid(brandSid);
        }

        if (brandType != null) {
            dto.setBrandType(brandType);
        }

        if (currentPage != null) {
            dto.setCurrentPage(currentPage);
        } else {
            dto.setCurrentPage(1);
        }

        if (pageSize != null) {
            dto.setPageSize(pageSize);
        } else {
            dto.setPageSize(10);
        }

        if (StringUtils.isNotEmpty(parentSid)) {
            dto.setParentSid(Long.parseLong(parentSid));
        }

        if (shopType != null) {
            dto.setShopType(shopType);
        }

        if (sid != null) {
            dto.setSid(sid);
        }

        if (StringUtils.isNotEmpty(spell)) {
            dto.setSpell(spell);
        }

        Page<PcmBrandDto> page = brandService.findPageBrand(dto);

        return ResultUtil.creComSucResult(page);
    }

    /**
     * 查询品牌（模糊）
     *
     * @param brandPagePara
     * @param request
     * @return Map<String,Object>
     * @Methods Name findListBrand
     * @Create In 2015-8-18 By wangx
     */
    @RequestMapping(value = "/findListBrand", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> findListBrand(@RequestBody SelectPcmBrandPagePara brandPagePara,
                                             HttpServletRequest request) {

        SelectPcmBrandPageDto selectDto = new SelectPcmBrandPageDto();

        try {
            BeanUtils.copyProperties(selectDto, brandPagePara);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        if (brandPagePara.getSid() == null) {
            selectDto.setSid(null);
        }
        if (brandPagePara.getBrandType() == null) {
            selectDto.setBrandType(null);
        }
        if (brandPagePara.getShopType() == null) {
            selectDto.setShopType(null);
        }
        if (brandPagePara.getParentSid() == null) {
            selectDto.setParentSid(null);
        }

        List<PcmBrandDto> brandDtoList = new ArrayList<PcmBrandDto>();
        try {
            brandDtoList = brandService.findListBrand(selectDto);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return ResultUtil.creComSucResult(brandDtoList);
    }

    /**
     * 添加/修改门店品牌与集团品牌的关系
     *
     * @param pcmBrandRelationPara
     * @param request
     * @return Map<String,Object>
     * @Methods Name updateRelation
     * @Create In 2015-8-12 By wangx
     */
    @RequestMapping(value = "/updateRelation", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> updateRelation(
            @RequestBody @Valid PcmBrandRelationPara pcmBrandRelationPara,
            HttpServletRequest request) {

        // 门店品牌Sid
        String sid = pcmBrandRelationPara.getSid();
        // 集团品牌Sid
        String parentSid = pcmBrandRelationPara.getParentSid();

        Integer result = Constants.PUBLIC_0;

        if (StringUtils.isNotEmpty(sid)) {

            if (StringUtils.isNotEmpty(parentSid)) {

                PcmBrandDto brandDto = new PcmBrandDto();
                brandDto.setSid(Long.parseLong(sid));
                brandDto.setParentSid(Long.parseLong(parentSid));
                brandDto.setOptUpdateTimes(new Date());
                try {
                    result = brandService.updateRelation(brandDto);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        List<Map<String, Object>> pushList = new ArrayList<Map<String, Object>>();
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("sid", sid);
        paraMap.put("actionCode", Constants.U);
        pushList.add(paraMap);
        if (result.equals(1)) {

            // 下发门店品牌数据(增量)
            if (sid != null) {
                final String json = JsonUtil.getJSONString(pushList);
                taskExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        String url = PropertyUtil.getSystemUrl("pcm-syn")
                                + "pcmSynBrand/pushBrand.htm";
                        HttpUtil.doPost(url, json);
                    }
                });
                taskExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        String url = PropertyUtil.getSystemUrl("pcm-syn")
                                + "pcmSynBrand/pushBrandToSearch.htm";
                        HttpUtil.doPost(url, json);
                    }
                });
            }

            return ResultUtil.creComSucResult("");
        } else {
            return ResultUtil.creComErrorResult(
                    ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
        }

    }

    /**
     * 批量添加门店品牌与集团品牌的关系
     *
     * @param brandRelationParaList
     * @param request
     * @return Map<String,Object>
     * @Methods Name addRelationList
     * @Create In 2015-9-14 By wangxuan
     */
    @RequestMapping(value = "/addRelationList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> addRelationList(
            @RequestBody @Valid List<PcmBrandRelationPara> brandRelationParaList,
            HttpServletRequest request) {

        Integer result = Constants.PUBLIC_0;

        List<Map<String, Object>> pushList = new ArrayList<Map<String, Object>>();
        List<PcmBrandDto> brandDtoList = new ArrayList<PcmBrandDto>();
        for (int i = 0; i < brandRelationParaList.size(); i++) {

            PcmBrandRelationPara brandRelationPara = new PcmBrandRelationPara();
            try {
                BeanUtils.copyProperties(brandRelationPara, brandRelationParaList.get(i));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            // 门店品牌Sid
            String sid = brandRelationPara.getSid();
            // 集团品牌Sid
            String parentSid = brandRelationPara.getParentSid();

            PcmBrandDto brandDto = new PcmBrandDto();
            brandDto.setSid(Long.parseLong(sid));
            brandDto.setParentSid(Long.parseLong(parentSid));

            brandDtoList.add(brandDto);

            Map<String, Object> pushMap = new HashMap<String, Object>();
            pushMap.put("sid", sid);
            pushMap.put("actionCode", Constants.U);
            pushList.add(pushMap);
        }

        result = brandService.addRelationList(brandDtoList);

        if (result.equals(1)) {

            // 下发门店品牌数据(增量)
            if (pushList != null && !pushList.isEmpty()) {

                final String json = JsonUtil.getJSONString(pushList);
                taskExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        String url = PropertyUtil.getSystemUrl("pcm-syn")
                                + "pcmSynBrand/pushBrand.htm";
                        HttpUtil.doPost(url, json);
                    }
                });
                taskExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        String searchUrl = PropertyUtil.getSystemUrl("pcm-syn")
                                + "pcmSynBrand/pushBrandToSearch.htm";
                        HttpUtil.doPost(searchUrl, json);
                    }
                });
            }

            return ResultUtil.creComSucResult("");
        } else {
            return ResultUtil.creComErrorResult(
                    ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
        }

    }

    /**
     * 删除门店品牌与集团品牌的关系
     *
     * @param para
     * @param request
     * @return Map<String,Object>
     * @Methods Name deleteRelation
     * @Create In 2015-8-18 By wangx
     */
    @RequestMapping(value = "/deleteRelation", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> deleteRelation(@RequestBody Map<String, Object> para,
                                              HttpServletRequest request) {

        // 门店品牌Sid
        String sid = para.get("sid") + "";

        Integer result = Constants.PUBLIC_0;

        if (StringUtils.isNotEmpty(sid)) {

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("sid", sid);

            result = brandService.deleteRelation(paramMap);
        } else {
            throw new BleException(
                    ComErrorCodeConstants.ErrorCode.BRAND_SID_IS_NULL.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.BRAND_SID_IS_NULL.getMemo());
        }

        if (result.equals(1)) {
            return ResultUtil.creComSucResult("");
        } else {
            return ResultUtil.creComErrorResult(
                    ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
        }

    }

    /**
     * 分页查询门店品牌及其集团品牌
     *
     * @param para
     * @param request
     * @return Map<String,Object>
     * @Methods Name findPageBrandAndBrandGroup
     * @Create In 2015-8-17 By wangx
     */
    @RequestMapping(value = "/findPageBrandAndBrandGroup", method = {RequestMethod.POST,
            RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> findPageBrandAndBrandGroup(@RequestBody SelectPcmBrandPagePara para,
                                                          HttpServletRequest request) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("brandType", Constants.PUBLIC_1);

        if (para.getSid() != null) {
            paramMap.put("sid", para.getSid());
        }
        if (para.getShopType() != null) {
            paramMap.put("shopType", para.getShopType());
        }
        if (para.getCurrentPage() != null) {
            paramMap.put("currentPage", para.getCurrentPage());
        } else {
            paramMap.put("currentPage", 1);
        }
        if (para.getPageSize() != null) {
            paramMap.put("pageSize", para.getPageSize());
        } else {
            paramMap.put("pageSize", 10);
        }
        if (StringUtils.isNotEmpty(para.getBrandSid())) {
            paramMap.put("brandSid", para.getBrandSid());
        }
        if (StringUtils.isNotEmpty(para.getBrandName())) {
            paramMap.put("brandName", para.getBrandName());
        }
        if (StringUtils.isNotEmpty(para.getSpell())) {
            paramMap.put("spell", para.getSpell());
        }

        Page<PcmBrandDto> page = brandService.findPageBrandAndBrandGroup(paramMap);

        return ResultUtil.creComSucResult(page);
    }

    /**
     * 删除门店品牌
     *
     * @param paraMap
     * @return Map<String,Object>
     * @Methods Name deleteBrand
     * @Create In 2015-8-5 By wangx
     */
    @RequestMapping(value = "/deleteBrand", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> deleteBrand(@RequestBody Map<String, Object> paraMap) {

        Long sid = Long.parseLong(paraMap.get("sid") + "");

        Integer result = Constants.PUBLIC_0;
        if (sid != null) {
            Map<String, Object> para = new HashMap<String, Object>();
            para.put("sid", sid);
            result = brandService.deleteBrand(para);
        } else {
            logger.error("删除门店品牌时没有传入Sid");
            throw new BleException(
                    ComErrorCodeConstants.ErrorCode.BRAND_SID_IS_NULL.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.BRAND_SID_IS_NULL.getMemo());
        }

        if (result.equals(1)) {
            return ResultUtil.creComSucResult("");
        } else {
            return ResultUtil.creComErrorResult(
                    ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
        }
    }

    /**
     * 查询某集团品牌下的门店品牌（带分页）
     *
     * @param para
     * @return Map<String,Object>
     * @Methods Name findPageBrandByGroupBrandSid
     * @Create In 2015-8-10 By wangx
     */
    @RequestMapping(value = "/findPageBrandByParentSid", method = {RequestMethod.POST,
            RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> findPageBrandByParentSid(@RequestBody Map<String, Object> para,
                                                        HttpServletRequest request) {

        Page<PcmBrandDto> pageBrand = new Page<PcmBrandDto>();
        SelectPcmBrandPageDto pageDto = new SelectPcmBrandPageDto();

        String currentPage = para.get("currentPage") + "";
        String pageSize = para.get("pageSize") + "";
        if (StringUtils.isNotEmpty(currentPage)) {
            pageDto.setCurrentPage(Integer.parseInt(currentPage));
        } else {
            pageDto.setCurrentPage(1);
        }
        if (StringUtils.isNotEmpty(pageSize)) {
            pageDto.setPageSize(Integer.parseInt(pageSize));
        } else {
            pageDto.setPageSize(10);
        }

        String sid = para.get("sid") + "";
        if (StringUtils.isNotEmpty(sid)) {
            pageDto.setParentSid(Long.parseLong(sid));
            pageDto.setBrandType(Constants.PUBLIC_1);
            pageBrand = brandService.findPageBrand(pageDto);
        } else {
            logger.error("查询某集团品牌下的门店品牌时传入的集团品牌sid不能为空");
            throw new BleException(
                    ComErrorCodeConstants.ErrorCode.BRANDGROUP_SID_IS_NULL.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.BRANDGROUP_SID_IS_NULL.getMemo());
        }

        return ResultUtil.creComSucResult(pageBrand);
    }

    /**
     * 查询某集团品牌下的门店品牌
     *
     * @param para
     * @return Map<String,Object>
     * @Methods Name findListBrandByParentSid
     * @Create In 2015-9-14 By wangxuan
     */
    @RequestMapping(value = "/findListBrandByParentSid", method = {RequestMethod.POST,
            RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> findListBrandByParentSid(@RequestBody Map<String, Object> para,
                                                        HttpServletRequest request) {

        String sid = para.get("sid") + "";
        List<PcmBrandDto> brandList = new ArrayList<PcmBrandDto>();
        if (StringUtils.isNotEmpty(sid)) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("parentSid", Long.parseLong(sid));
            paramMap.put("brandType", Constants.PUBLIC_1);
            try {
                brandList = brandService.findListBrand(paramMap);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {
            logger.error("查询某集团品牌下的门店品牌时传入的集团品牌sid不能为空");
            throw new BleException(
                    ComErrorCodeConstants.ErrorCode.BRANDGROUP_SID_IS_NULL.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.BRANDGROUP_SID_IS_NULL.getMemo());
        }

        return ResultUtil.creComSucResult(brandList);
    }

    /**
     * 查询所有没有关联集团品牌的门店品牌
     *
     * @param request
     * @return Map<String,Object>
     * @Methods Name findListBrandWithoutRelation
     * @Create In 2015-9-14 By wangxuan
     */
    @RequestMapping(value = "/findListBrandWithoutRelation", method = {RequestMethod.POST,
            RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> findListBrandWithoutRelation(
            @RequestBody HttpServletRequest request) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 没有关联集团品牌的门店品牌parentSid为0
        paramMap.put("parentSid", new Long(Constants.PUBLIC_0));
        paramMap.put("brandType", Constants.PUBLIC_1);
        List<PcmBrandDto> brandList = new ArrayList<PcmBrandDto>();
        try {
            brandList = brandService.findListBrand(paramMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return ResultUtil.creComSucResult(brandList);
    }

}
