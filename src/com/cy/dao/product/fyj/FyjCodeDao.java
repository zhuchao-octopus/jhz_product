package com.cy.dao.product.fyj;

import java.util.List;

import com.cy.domain.product.fyj.FyjCode;

public interface FyjCodeDao {
    public List<FyjCode> findAll(FyjCode code);
    
    /**
     * 根据SN号删除数据
     * @param code
     * @return
     */
	public int deleteBySn(FyjCode code);
    
    /*
     * 根据箱号删除一箱的数据
     */
	public int deleteByBoxNum(FyjCode code); 
}
