package com.github.wally.wcdbsample.db.biz.impl;

import com.github.wally.wcdbsample.common.model.vo.Page;
import com.github.wally.wcdbsample.common.model.vo.PageVO;
import com.github.wally.wcdbsample.db.biz.IPersonBiz;
import com.github.wally.wcdbsample.db.dao.IPersonDao;
import com.github.wally.wcdbsample.db.dao.imlp.PersonDao;
import com.github.wally.wcdbsample.model.dto.PersonDTO;
import com.github.wally.wcdbsample.model.vo.PersonVO;

import java.util.List;

/**
 * Package: com.github.wally.wcdb_sample.biz
 * FileName: PersonBiz
 * Date: on 2018/8/4  下午12:05
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */
public class PersonBiz implements IPersonBiz {
    private final IPersonDao mDao;

    public PersonBiz() {
        mDao = new PersonDao();
    }

    @Override
    public boolean addPerson(PersonDTO dto) {
        return mDao.addPerson(dto);
    }

    @Override
    public boolean addPersons(List<PersonDTO> dtos) {
        return mDao.addPersons(dtos);
    }

    @Override
    public PageVO<PersonVO> getAllPersonPageList(Integer pageNum, Integer pageSize) {
        Page<PersonVO> page = mDao.getAllPersonPageList(pageNum, pageSize);
        return new PageVO<>(page);
    }

    @Override
    public boolean hasData() {
        return mDao.hasData();
    }
}