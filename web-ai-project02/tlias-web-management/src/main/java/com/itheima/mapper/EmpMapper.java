package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface EmpMapper {

    // 原始分页查询
   /* @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
    public Long count();

    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id " +
            "where e.name like concat('%',#{name},'%') and e.gender = #{gender} and e.entry_date between #{begin} and #{end} " +
            "limit #{start},#{pageSize}")
    public List<Emp>list(Integer start, Integer pageSize,
                         String name, Integer gender,
                         LocalDateTime begin, LocalDateTime end);
}*/
    /*@Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id " +
            "where e.name like concat('%',#{name},'%') and e.gender = #{gender} and e.entry_date between #{begin} and #{end} ")
    */
    public List<Emp> list(EmpQueryParam empQueryParam);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert emp (username, password, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username},#{password},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    public void insert(Emp emp);
}