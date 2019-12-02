package com.cy.dao.list;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cy.board.Repair;
import com.cy.domain.line.ProductDetails;
import com.cy.domain.list.Binding_code;
import com.cy.domain.list.Boxnum;
import com.cy.domain.list.PrintModel;
import com.cy.domain.list.ProductCode;
import com.cy.domain.list.ProductList;
import com.cy.domain.list.ProductModel;
import com.cy.domain.list.ProductScheduling;
import com.cy.domain.list.WorkStationCode;
import com.cy.domain.produce.tv.BomMaterials;
import com.cy.utils.CyResult;

public interface ProductListDao {

	public List<ProductList> loadlist();

	/*
	 * 加载全部条码列表
	 */
	public List<ProductCode> loadAllProductCode(ProductCode pc);
	
	/*
	 * 加载全部条码列表总条数
	 */
	public int loadAllProductCodeNumber(ProductCode product);

	/*
	 * 查询条码列表
	 */
	public ProductCode loadProductCode(int pid);

	/*
	 * 新增条码列表
	 */
	public int addProductCode(ProductCode pc);

	/*
	 * 删除条码列表
	 */
	public int deleteProductCode(ProductCode pc);
	
	/*
	 * 修改条码列表
	 */
	public int updateProductCode(ProductCode pc);

	/*
	 * 根据SN1查询条码列表
	 */
	public ProductCode loadProductCodeBySN1(String sn1);

	/*
	 * 新增工艺排程
	 */
	public int addProductScheduling(ProductScheduling ps);

	/*
	 * 修改工艺排程
	 */
	public int updateProductScheduling(ProductScheduling ps);

	/*
	 * 删除工艺排程
	 */
	public int deleteProductScheduling(ProductScheduling ps);

	/*
	 * 查询工艺排程
	 */
	public ProductScheduling selectProductScheduling(ProductScheduling ps);

	/*
	 * 查询全部工艺排程
	 */
	public List<ProductScheduling> selectAllProductScheduling(ProductScheduling pds);

	/*
	 * 硬盘查询出现有的批次号码
	 */
	public List<String> findBatch();
	
	/*
	 * 新硬盘查询出现有的批次号码
	 */
	public List<String> findCatch();
	

	/*
	 * 硬盘通过SN2来查找产品
	 */
	public ProductCode findSnByStr(String str);
	
	/*
	 * 硬盘通过PCBA来查找产品
	 */
	public Binding_code findSnByStrr(String str);
	
	/*
	 * 根据箱号扫描导出 
	 */
	public List<ProductCode> exportByBoxNum(String str);
	
	/*
	 * 出货导出 
	 */
	public List<String> selectSn1ByPorderAndTime10(String str1,String str2);
	
	/*
	 * 录入新硬盘
	 */
	public int insertCode(Binding_code bc);
	
	/*
	 * 条码新增页面查询工艺号
	 */
	public List<ProductScheduling> selectProcess();

	/*
	 * 新 条码新增页面查询产品名
	 */
	public List<String> selectAllPname();
	
	/*
	 * 新 条码新增页面根据产品名查询对应编码规则
	 */
	public List<ProductScheduling> selectCodeNameByPname(String str);
	
	/*
	 * 新 批量导出条码页面根据箱号查询对应编码规则
	 */
	public ProductScheduling selectCodeNameBySnumber(String str);
	
	/*
	 * 条码新增页面查询产品名
	 */
	public List<ProductDetails> selectPcode();
	
	/*
	 * 条码新增页面查询对应产品名
	 */
	public ProductScheduling selectPnameByProcess(String str);
	
	/*
	 * 根据型号料号订单号查询产品列表
	 */
	public ProductList findProductList(ProductList pl);
	
	/*
	 * 修改产品列表
	 */
	public int updateProductList(ProductList pl);
	
	/*
	 * 修改产品列表计划产量
	 */
	public int updateProductListLplanyield(ProductList pl);
	
	/*
	 * 新增产品列表
	 */
	public int addProductList(ProductList pl);
	
	/*
	 * 加载全部产品列表
	 */
	public List<ProductList> selectAllProductList(Integer pageNumber);
	
	/*
	 * 查询单个产品列表
	 */
	public ProductList selectProductList(ProductList pl);
	
	/*
	 * 删除产品列表
	 */
	public int deleteProductList(ProductList pl);
	
	/*
	 * 根据编号加载条码
	 */
	List<ProductScheduling> loadPs(String snumber);
	
	/*
	 * 根据产品名和条码名加载条码
	 */
	List<ProductScheduling> loadCodeName(Map<String, Object> map);
	
	/*
	 * 根据产品名和编号加载条码规则
	 */
	public ProductScheduling loadPSBySnumber(ProductScheduling ps);
	
	/*
	 * 新建条码规则
	 */
	int addPs(ProductScheduling ps);
	
	/*
	 * 新建产品
	 */
	int addPD(String pname);
	
	/*
	 * 根据产品名统计数量
	 */
	int searchPname(String pname);
	
	/*
	 * 根据产品名统计数量
	 */
	int selectCountName(String pname);
	
	/*
	 * 删除产品
	 */
	void removePname(String pname);
	
	/*
	 * 加载全部工作站
	 */
	List<WorkStationCode> loadAllWSC(WorkStationCode wsc);
	
	/*
	 * 新建工作站
	 */
	int addWSC(WorkStationCode wsc);
	
	/*
	 * 删除工作站
	 */
	int deleteWorkStation(String mid);
	
	List<ProductScheduling> findSchedulingByPd(int aaa);
	
	/*
	 * 修改工作站状态
	 */
	int updateStation(WorkStationCode wsc);
	
	/**
	 * 修改条码规则
	 * @param ps
	 * @return
	 */
	public int updateProductSchedulingById(ProductScheduling ps);

	public int countPds();

	public ProductDetails findProduct(Integer pid);

	public int countAllWsc();

	public List<ProductScheduling> searchProductScheduling(Integer cpid);

	public ProductScheduling selectPidByProcess(String str);

	public int xlsAddProductCode(List<ProductCode> list);

	public List<String> distinctModel();

	public List<String> distinctColor();

	public List<String> distinctPorder();
	
	public int deleteProductCodes(List<String> list);

	public int insertOneCode(ProductCode productCode);

	public int updateOneCode(ProductCode productCode);

	public List<String> distinctScpc();

	public List<String> distinctJhdh();

	public List<String> distinctPzf();

	public List<String> distinctChdq();

	public List<ProductCode> exportCode(ProductCode productCode);

	public void addModel(PrintModel model);
	
	public List<PrintModel> loadModel(PrintModel model);
	
	public int loadModelCount(PrintModel model);
	
	public Integer deleteModel(int templateId);

	public void addBoxnums(List<Boxnum> list);

	public List<Boxnum> loadAllBox(Boxnum boxnum);

	public int loadAllCount(Boxnum boxnum);

	public List<String> distinctPncode();

	public void deleteBoxnum(int bid);

	public void editModel(PrintModel model);

	public Boxnum loadBoxByBoxnum(String boxnum);

	public void deleteMoreBoxnum(String[] arr);
	
	public List<ProductCode> findBarcodeBySn1(String sn1);

	public List<WorkStationCode> findStationByCode(String wsNumber);

	public List<String> distinctPn();

	public int xlsAddbomMaterials(List<BomMaterials> bomMaterialslist);

	public void addPhenomenon(String blPhenom);

	public List<Repair> loadBlPhenom(Repair repair);

	public void deleteBlPhenom(Integer xId);

	public List<Repair> loadHealthyy(Repair repair);

	public void addHealthyy(String unhealthyy);

	public void deleteHealthyy(Integer brid);

	public int countLoadHealthyy(Repair repair);

	public int countLoadBlPhenom(Repair repair);

	public List<ProductModel> loadProductModel(ProductModel productModel);

	public int countLoadProductModel(ProductModel productModel);

	public void addProductModel(ProductModel productModel);

	public void deleteProductModel(Integer id);

	public int updateProductModel(ProductModel productModel);

	public List<ProductModel> loadProductModelByPid(ProductModel productModel);

	public void addRepair(Repair repair);

	public String findPcodeBySN(String sn);

	public void deleteRepair(Integer rid);

	public void updateRepair(Repair repair);
}
