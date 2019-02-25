package mybatis.mapper;

import mybatis.pojo.Emp;

import java.util.List;

public interface EmpMapper {
    int deleteByPrimaryKey(Long empno);

    int insert(Emp record);

    int insertSelective(Emp record);

    Emp selectByPrimaryKey(Long empno);

    int updateByPrimaryKeySelective(Emp record);

    int updateByPrimaryKey(Emp record);

    List<Emp> findAll();
}