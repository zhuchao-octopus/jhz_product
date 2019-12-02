package com.cy.service.list;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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

public interface ProductListService {
	
	public CyResult loadlist();
	
	/*
	 * 加载全部条码列表
	 */
	public CyResult loadAllProductCode(Integer page , Integer limit, ProductCode product);
	
	/*
	 * 查询条码列表
	 */
	public CyResult loadProductCode(ProductCode pc);
	
	/*
	 * 根据SN1查询条码列表
	 */
	public CyResult loadProductCodeBySN1(ProductCode pc);
	
	/*
     * 通过扫描出的箱号导出数据
     */
    public CyResult exportByBoxNum(ProductCode pc) throws Exception;
    
    /*
     *批量导出数据 
     */
    public CyResult ExpotExcel(String str1,String str2,ProductCode pc) throws Exception;
    
    /*
     *出货批量导出数据 
     */
    public CyResult exportShipment(String str1,String str2,ProductCode pc) throws Exception;
	
	/*
	 * 新增条码列表
	 */
	public CyResult addProductCode(String snumber,String strsn1,String strsn2,ProductCode pc);
	
	/*
	 * 删除条码列表
	 */
	public CyResult deleteProductCode(ProductCode pc);
	
	/*
	 * 修改条码列表
	 */
	public CyResult updateProductCode(ProductCode pc);
	
	/*
	 * 新增工艺排程
	 */
	public CyResult addProductScheduling(ProductScheduling ps);
	
	/*
	 * 修改工艺排程
	 */
	public CyResult updateProductScheduling(ProductScheduling ps);

	/*
	 * 删除工艺排程
	 */
	public CyResult deleteProductScheduling(ProductScheduling ps);
	
	/*
	 * 查询工艺排程
	 */
	public CyResult selectProductScheduling(ProductScheduling ps);
	
	/*
	 * 查询全部工艺排程
	 */
	public CyResult selectAllProductScheduling(Integer page, Integer limit);
	
	/*
	 * 自定义编码规则
	 */
	public CyResult wxs(String strsn1,String strsn2,Double cost,ProductCode pc);
	
	/*
	 * 硬盘编码规则
	 */
	public CyResult addCodes(String str1,String str2,String str3,String str4,String str5,String str6,Double cost,ProductCode pc);
	
	/*
	 * 通用编码规则
	 */
	public CyResult addCurrency(String pname, String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12, String s13, String s14, String s15, String s16, String s17, String s18, String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, ProductCode pc);
	
	/*
	 * 条码新增页面查询工艺号
	 */
	public CyResult selectProcess();
	
	/*
	 * 新 条码新增页面查询产品名
	 */
	public CyResult selectAllPname();
	
	/*
	 * 新 条码新增页面根据产品名查询对应编码规则
	 */
	public CyResult selectCodeNameByPname(Integer i);
	
	/*
	 * 条码新增页面查询产品名
	 */
	public CyResult selectPcode();
	
	/*
	 * 新建产品列表
	 */
	public void addProductList(ProductList pl);
	
	/*
	 * 加载全部产品列表
	 */
	public CyResult selectAllProductList(Integer pageNumber);
	
	/*
	 * 加载单个产品列表
	 */
	public CyResult selectProductList(ProductList pl);
	
	/*
	 * 删除产品列表
	 */
	public CyResult deleteProductList(ProductList pl);
	
	/*
	 * 新建条码规则
	 */
	public CyResult insertProductScheduling(ProductScheduling ps, Integer ppid);
	
	/*
	 * 加载全部工作站
	 */
	public CyResult loadAllWorkStation(Integer page, Integer limit);
	
	/*
	 * 新建工作站
	 */
	public CyResult addWorkStation(WorkStationCode wsc);
	
	/*
	 * 删除工作站
	 */
	public CyResult deleteWorkStation(String mid);
	
	public CyResult updateStation(WorkStationCode wsc);

	public CyResult editProductScheduling(Integer ppid, ProductScheduling ps);

	public CyResult exportTestData(MultipartFile upSN, MultipartFile upMac, HttpServletRequest request) throws Exception;

	public CyResult xlsAddProductCode(MultipartFile productFile, ProductCode productCode, HttpServletRequest request) throws Exception;

	public CyResult searchProductScheduling(Integer cpid);

	public CyResult distinctProduct(String str);

	public CyResult deleteProductCodes(String pidString);

	public CyResult insertOneCode(ProductCode productCode);

	public CyResult updateOneProductCode(ProductCode productCode);

	public CyResult distinctData();

	public CyResult exportCode(ProductCode productCode) throws Exception;

	public CyResult addModel(PrintModel model);

	public CyResult loadAllMode(PrintModel model);
	
	public CyResult deleteModel(int templateId);

	public CyResult boxNumsAdd(MultipartFile file, HttpServletRequest request,Boxnum box) throws Exception;

	public CyResult loadAllBox(Boxnum boxnum);

	public CyResult deleteBoxnum(int bid);

	public CyResult editModel(PrintModel model);

	public CyResult deleteMoreBoxnum(String idstring);

	public CyResult bomMaterials(MultipartFile BOMfile, BomMaterials bomMaterials,
			HttpServletRequest request)throws Exception;

	public CyResult addPhenomenon(String blPhenom);

	public CyResult loadBlPhenom(Repair repair, Integer limit,Integer page);

	public CyResult deleteBlPhenom(Integer xId);

	public CyResult loadHealthyy(Repair repair);

	public CyResult addHealthyy(String unhealthyy);

	public CyResult deleteHealthyy(Integer brid);

	public CyResult loadProductModel(ProductModel productModel);

	public CyResult addProductModel(ProductModel productModel);

	public CyResult deleteProductModel(Integer id);

	public CyResult updateProductModel(ProductModel productModel);

	public CyResult loadProductModelByPid(ProductModel productModel);

	public CyResult addRepair(Repair repair);

	public CyResult deleteRepair(Integer rid);

	public CyResult updateRepair(Repair repair);



	
}
