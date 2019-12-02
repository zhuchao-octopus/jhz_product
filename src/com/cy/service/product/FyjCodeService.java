package com.cy.service.product;

import com.cy.domain.product.fyj.FyjCode;
import com.cy.utils.CyResult;

public interface FyjCodeService {
    public CyResult exportByBoxNum(String boxNum) throws Exception;
    
    /*
     * 批量导出
     */
	public CyResult exportToBoxNums(String boxNum1, String boxNum2) throws Exception;
	
	/*
	 * 查询或者加载数据
	 */
	public CyResult loadCode(FyjCode code);
    
	/*
	 *根据SN删除数据
	 */
	public CyResult deleteBySn(FyjCode code);
    
	/*
	 * 根据箱号删除一项的数据数据
	 */
	public CyResult deleteByBoxNum(FyjCode code);
}
