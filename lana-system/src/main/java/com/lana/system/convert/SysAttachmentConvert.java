package com.lana.system.convert;

import com.lana.system.entity.SysAttachmentEntity;
import com.lana.system.entity.vo.result.SysAttachmentResult;
import com.lana.system.entity.vo.save.SysAttachmentSave;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysAttachmentConvert {
    SysAttachmentConvert INSTANCE = Mappers.getMapper(SysAttachmentConvert.class);

    SysAttachmentEntity convert(SysAttachmentResult vo);

    SysAttachmentEntity convert(SysAttachmentSave saveVO);

    SysAttachmentResult convert(SysAttachmentEntity entity);

    List<SysAttachmentResult> convertList(List<SysAttachmentEntity> list);

}
