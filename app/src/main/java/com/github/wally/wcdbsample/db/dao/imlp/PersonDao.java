package com.github.wally.wcdbsample.db.dao.imlp;

import com.github.wally.wcdbsample.common.model.vo.Page;
import com.github.wally.wcdbsample.db.dao.IPersonDao;
import com.github.wally.wcdbsample.model.dto.PersonDTO;
import com.github.wally.wcdbsample.model.entity.PersonEntity;
import com.github.wally.wcdbsample.model.vo.PersonVO;
import com.github.wally.wcdbsample.util.DBManager;
import com.github.wally.wcdbsample.util.DateUtil;
import com.github.wally.wcdbsample.util.PageHelper;
import com.github.wally.wcdbsample.util.UUIDUtil;
import com.tencent.wcdb.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Package: com.github.wally.wcdb_sample.dao
 * FileName: PersonDao
 * Date: on 2018/8/4  下午12:06
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */
public class PersonDao implements IPersonDao {
    private final DBManager mDbManager;

    public PersonDao() {
        mDbManager = DBManager.getInstance();
    }

    @Override
    public boolean addPerson(PersonDTO dto) {
        boolean result = false;
        try {
            mDbManager.beginTransaction();
            PersonEntity entity = buildEntity(dto);
            Object[] args = {
                    entity.getId(),
                    entity.getPersonName(),
                    entity.getSex(),
                    entity.getAge(),
                    DateUtil.getStringDate(entity.getCreateTime()),
                    DateUtil.getStringDate(entity.getUpdateTime()),
                    entity.getVersion(),
                    entity.getDeleteFlag()};
            String sql = "INSERT INTO person VALUES(? ,? ,? ,? ,? ,? ,? ,?)";
            mDbManager.insert(args, sql);
            result = true;
            mDbManager.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            mDbManager.endTransaction();
        }
        return result;
    }

    @Override
    public boolean addPersons(List<PersonDTO> dtos) {
        boolean result = false;
        try {
            mDbManager.beginTransaction();
            String sql = "INSERT INTO person VALUES(? ,? ,? ,? ,? ,? ,? ,?)";
            for (PersonDTO dto : dtos) {
                PersonEntity entity = buildEntity(dto);
                Object[] args = {
                        entity.getId(),
                        entity.getPersonName(),
                        entity.getSex(),
                        entity.getAge(),
                        DateUtil.getStringDate(entity.getCreateTime()),
                        DateUtil.getStringDate(entity.getUpdateTime()),
                        entity.getVersion(),
                        entity.getDeleteFlag()};
                mDbManager.insert(args, sql);
            }
            result = true;
            mDbManager.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            mDbManager.endTransaction();
        }
        return result;
    }

    @Override
    public Page<PersonVO> getAllPersonPageList(Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 15;
        }
        List<PersonVO> resultList = new ArrayList<>();
        //计算起始位置和偏移量
        int startIndex = PageHelper.calculatePageStartIndex(pageNum, pageSize);
        int offset = pageSize;
        //查询总条数
        PageHelper<PersonVO> helper = new PageHelper<>();
        long totalCount = getTotalCount();
        Page<PersonVO> pageVO = helper.handlePage(pageSize, totalCount, pageNum);
        String sql = "SELECT id, person_name, sex, age, create_time, update_time, version, delete_flag FROM person order by create_time desc limit " + startIndex + "," + offset;
        Cursor cursor = mDbManager.query(sql);
        while (cursor.moveToNext()) {
            PersonEntity entity = new PersonEntity();
            entity.setId(cursor.getString(cursor.getColumnIndex("id")));
            entity.setPersonName(cursor.getString(cursor.getColumnIndex("person_name")));
            entity.setSex(cursor.getString(cursor.getColumnIndex("sex")));
            entity.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            entity.setDeleteFlag(cursor.getInt(cursor.getColumnIndex("delete_flag")));
            String createTimeStr = cursor.getString(cursor.getColumnIndex("create_time"));
            entity.setCreateTime(DateUtil.formatDate(createTimeStr, DateUtil.FORMAT_NORMAL));
            String updateTimeStr = cursor.getString(cursor.getColumnIndex("update_time"));
            entity.setUpdateTime(DateUtil.formatDate(updateTimeStr));
            entity.setVersion(cursor.getInt(cursor.getColumnIndex("version")));
            resultList.add(buildVO(entity));
        }
        pageVO.setRecords(resultList);
        return pageVO;
    }

    @Override
    public long getTotalCount() {
        String sql = "SELECT COUNT(1) FROM person";
        Cursor cursor = mDbManager.query(sql);
        cursor.moveToFirst();
        return cursor.getLong(0);
    }

    @Override
    public boolean hasData() {
        long count = getTotalCount();
        return count > 0;
    }

    private PersonEntity buildEntity(PersonDTO dto) {
        PersonEntity entity = new PersonEntity();
        entity.setId(UUIDUtil.get32UUID());
        entity.setPersonName(dto.getPersonName());
        entity.setSex(dto.getSex());
        entity.setAge(dto.getAge());
        return entity;
    }

    private PersonVO buildVO(PersonEntity entity) {
        PersonVO vo = new PersonVO();
        vo.setId(entity.getId());
        vo.setPersonName(entity.getPersonName());
        vo.setSex(entity.getSex());
        vo.setAge(entity.getAge());
        vo.setCreateTime(entity.getCreateTime());
        vo.setUpdateTime(entity.getUpdateTime());
        return vo;
    }
}