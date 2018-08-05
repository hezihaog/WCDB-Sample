package com.github.wally.wcdbsample.db.biz;

import com.github.wally.wcdbsample.common.model.vo.PageVO;
import com.github.wally.wcdbsample.model.dto.PersonDTO;
import com.github.wally.wcdbsample.model.vo.PersonVO;

import java.util.List;

/**
 * Package: com.github.wally.wcdb_sample.biz
 * FileName: IPersonBiz
 * Date: on 2018/8/4  下午12:11
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */
public interface IPersonBiz {
    boolean addPerson(PersonDTO dto);

    boolean addPersons(List<PersonDTO> dtos);

    PageVO<PersonVO> getAllPersonPageList(Integer pageNum, Integer pageSize);

    boolean hasData();
}