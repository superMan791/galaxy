package com.cloud.galaxy.business.common.controller;

import com.cloud.galaxy.business.common.entity.dto.MessageTemplateInsertDto;
import com.cloud.galaxy.business.common.entity.dto.MessageTemplateUpdateDto;
import com.cloud.galaxy.business.common.entity.po.MessageTemplate;
import com.cloud.galaxy.business.common.entity.vo.GetMessageTemplateVo;
import com.cloud.galaxy.business.common.service.IMessageTemplateService;
import com.cloud.galaxy.common.core.base.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

@Api(tags = "消息模板")
@RestController
@RequestMapping("template")
@Slf4j
public class MessageTemplateController {
    @Autowired
    private IMessageTemplateService messageTemplateService;

    @ApiOperation("新增消息模板")
    @PostMapping("insert")
    public R insert(@Validated @RequestBody MessageTemplateInsertDto messageTemplateInsertDto) {
        MessageTemplate messageTemplate = messageTemplateService.getByCode(messageTemplateInsertDto.getCode());
        if (messageTemplate != null) {
            return R.fail("模板code已存在");
        }
        messageTemplate = new MessageTemplate();
        BeanUtils.copyProperties(messageTemplateInsertDto, messageTemplate);
        if (messageTemplateService.insert(messageTemplate)) {
            return R.ok();
        }
        return R.fail();
    }

    @ApiOperation("删除短信模板")
    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        messageTemplateService.delete(id);
        return R.ok();
    }

    @ApiOperation("修改短信模板")
    @PutMapping("update/{id}")
    public R update(@Validated @RequestBody MessageTemplateUpdateDto messageTemplateUpdateDto, @PathVariable("id") Long id) {
        MessageTemplate messageTemplate = new MessageTemplate();
        BeanUtils.copyProperties(messageTemplateUpdateDto, messageTemplate);
        messageTemplate.setId(id);
        if (messageTemplateService.updateById(messageTemplate)) {
            return R.ok();
        }
        return R.fail();
    }

    @ApiOperation("根据id查询模板详情")
    @GetMapping("get/{id}")
    public R<GetMessageTemplateVo> get(@PathVariable("id") Long id) {
        return R.ok(messageTemplateService.getById(id));
    }

    @ApiOperation("分页查询短信模板")
    @GetMapping("page")
    @PermitAll
    public R<Page<GetMessageTemplateVo>> findByPage(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize, @RequestParam("title") String title) {
        return R.ok(messageTemplateService.findByPage(PageRequest.of(pageNo, pageSize)));
    }
}
