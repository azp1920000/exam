package com.example.exam.domain.Service;

import com.example.exam.domain.controller.param.SkuParam;
import com.example.exam.domain.dto.SkuDTO;
import com.example.exam.domain.mapper.SkuMapper;
import com.example.exam.domain.model.Sku;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SkuServiceImp implements SkuService{

    @Autowired
    SkuMapper skuMapper;

    /**
     * 单条创建商品信息
     * @param sku
     * @return
     */
    @Override
    public Long createSkuInfo(Sku sku) {
        return skuMapper.createSkuInfo(sku);
    }

    /**
     * 批量创建商品信息
     * @param skuList
     * @return
     */
    @Override
    public Long createSkuInfos(List<Sku> skuList) {
        return skuMapper.createSkuInfos(skuList);
    }

    /**
     * 根据id查询商品信息
     * @param id
     * @return
     */
    @Override
    public SkuDTO getSkuById(Long id) {
        return skuMapper.getSkuById(id);
    }

    /**
     * 根据多条件查询商品信息
     * @param skuDTO
     * @return
     */
    @Override
    public List<SkuDTO> getSkuInfo(SkuDTO skuDTO) {
        return skuMapper.getSkuInfo(skuDTO);
    }

    /**
     * 根据id删除信息
     * @param id
     * @return
     */
    @Override
    public Long deleteSkuById(Long id) {
        return skuMapper.deleteSkuById(id);
    }

    /**
     * 根据id更新商品信息
     * @param sku
     * @return
     */
    @Override
    @Transactional
    public Long updateSkuById(Sku sku) {

        //根据Name查询id
        SkuDTO skuDTO = getSkuById(sku.getSkuId());
        sku.setCategoryId(skuDTO.getCategoryId());
        sku.setBrandId(skuDTO.getBrandId());
        return skuMapper.updateSkuById(sku);
    }
}
