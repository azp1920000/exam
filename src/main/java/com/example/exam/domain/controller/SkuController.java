package com.example.exam.domain.controller;

import com.example.exam.domain.Service.SkuService;
import com.example.exam.domain.controller.param.SkuParam;
import com.example.exam.domain.dto.SkuDTO;
import com.example.exam.domain.model.Sku;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Api(tags = "商品信息API")
@RequestMapping("/sku")
public class SkuController {
    @Autowired
    SkuService skuService;

    @ApiOperation("创建商品信息")
    @PutMapping("/createInfo")
    public Long createSkuInfo(@RequestBody Sku sku) {
        if (sku.getBrandId()==null || sku.getCategoryId() == null)
            throw new RuntimeException("单创建参数错误");
        return skuService.createSkuInfo(sku);
    }

    @ApiOperation("批量创建商品信息")
    @PutMapping("/createInfos")
    public Long createSkuInfos(@RequestBody List<Sku> skuList){
        for (Sku sku: skuList){
            if (sku.getBrandId()==null || sku.getCategoryId() == null)
                throw new RuntimeException("批量创建参数异常");
        }
        return skuService.createSkuInfos(skuList);
    }

    @ApiOperation("根据Id查询商品信息")
    @ApiImplicitParam(name = "id", value = "商品id")
    @GetMapping("/getInfo")
    public SkuDTO getSkuById(@RequestParam Long id) {
        if (id == null)
            throw new RuntimeException("根据id查询参数错误");
        return skuService.getSkuById(id);
    }

    @ApiOperation("根据条件查询")
    @GetMapping("/get-list")
    public List<SkuDTO> getSkuInfo(@RequestBody SkuParam skuParam) {
        if (skuParam.getStartTime() != null)
            skuParam.setEndTime(LocalDateTime.now());
        if (skuParam.getEndTime() != null)
            skuParam.setStartTime(LocalDateTime.parse("1950-01-01 00:00:00"));
        SkuDTO skuDTO = new SkuDTO();
        BeanUtils.copyProperties(skuParam,skuDTO);
        return skuService.getSkuInfo(skuDTO);
    }

    @ApiOperation("根据id删除信息")
    @ApiImplicitParam(name = "id", value = "商品id")
    @RequestMapping("/delete")
    public Long deleteSkuById(@RequestParam Long id) {
        if (id == null)
            throw new RuntimeException("根据id删除参数错误");
        return skuService.deleteSkuById(id);
    }

    @ApiOperation("根据所给信息更新商品信息")
    @RequestMapping("/update")
    public Long updateSkuById(@RequestBody SkuParam skuParam) {
        if (skuParam.getSkuId() == null )
            throw new RuntimeException("根据id更新参数错误");
        Sku sku = new Sku();
        BeanUtils.copyProperties(skuParam,sku);
        return skuService.updateSkuById(sku);
    }
}
