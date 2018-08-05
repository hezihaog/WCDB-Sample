package com.github.wally.wcdbsample.db.dao;

import com.github.wally.wcdbsample.common.model.vo.Page;
import com.github.wally.wcdbsample.model.dto.PersonDTO;
import com.github.wally.wcdbsample.model.vo.PersonVO;

import java.util.List;

/**
 * Package: com.github.wally.wcdb_sample.dao
 * FileName: IPersonDao
 * Date: on 2018/8/4  下午12:28
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */
public interface IPersonDao {
    boolean addPerson(PersonDTO dto);

    boolean addPersons(List<PersonDTO> dtos);

    Page<PersonVO> getAllPersonPageList(Integer pageNum, Integer pageSize);

    long getTotalCount();

    boolean hasData();
}