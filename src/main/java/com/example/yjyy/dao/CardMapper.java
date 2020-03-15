package com.example.yjyy.dao;

import com.example.yjyy.entity.Card;
import com.example.yjyy.entity.Course;
import com.example.yjyy.entity.Venue;
import com.example.yjyy.entity.dto.CardDto;
import com.example.yjyy.result.business.PageResult.CardPageResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface CardMapper {
    int deleteByPrimaryKey(String cardid);

    int insert(Card record);

    int insertSelective(Card record);

    Card selectByPrimaryKey(String cardid);

    int updateByPrimaryKeySelective(Card record);

    int updateByPrimaryKey(Card record);

    int insertCardVenue(CardDto cardDto);
    int insertCardCourse(CardDto cardDto);
    List<Venue> getVenueListByCardId(String cardid);
    List<Course> getCourseListByCardId(String cardid);
    List<CardPageResult> getCardList(@Param("cardname") String cardname,
                                     @Param("cardtype") String cardtype,
                                     @Param("begin") int begin,
                                     @Param("end") int end,
                                     @Param("pagesize") int pagesize);
    int deleteCardVenue(String cardid);
    int deleteCardCourse(String cardid);
}