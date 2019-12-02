package com.cy.controller.list;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.cy.service.list.ProductListService;
import com.cy.utils.CyResult;
import com.mysql.jdbc.ResultSetInternalMethods;

@RequestMapping("list")
@Controller
public class ProductListController {

	@Resource
	private ProductListService service;

	@RequestMapping("/loadlist.do")
	@ResponseBody
	public CyResult loadlist() {
		CyResult result = service.loadlist();
		return result;

	}

	/*
	 * 加载全部条码列表
	 */
	@RequestMapping("/loadAllProductCode.do")
	@ResponseBody
	public CyResult loadAllProductCode(Integer page , Integer limit, ProductCode product) {
		CyResult result = service.loadAllProductCode(page, limit, product);
		return result;

	}

	/*
	 * 查询条码列表
	 */
	@RequestMapping("/loadProductCode.do")
	@ResponseBody
	public CyResult loadProductCode(ProductCode pc) {
		CyResult result = service.loadProductCode(pc);
		return result;
	}
	
	/*
	 * 根据SN1查询条码列表
	 */
	@RequestMapping("/loadProductCodeBySN.do")
	@ResponseBody
	public CyResult loadProductCodeBySN1(ProductCode pc) {
		CyResult result = service.loadProductCodeBySN1(pc);
		return result;
	}

	/*
	 * 根据箱号扫描导出
	 */
	@RequestMapping("/exportByBoxNum.do")
	@ResponseBody
	public CyResult exportByBox(ProductCode pc) throws Exception{
		CyResult result = service.exportByBoxNum(pc);
		return result;
	}
	
	/*
	 * 批量导出
	 */
	@RequestMapping("/export.do")
	@ResponseBody
	public CyResult export(HttpServletRequest request,HttpServletResponse response, ProductCode pc) throws Exception{
		String strs1 = request.getParameter("str0sn6");
		String strs2 = request.getParameter("str1sn6");
		CyResult result = service.ExpotExcel(strs1, strs2, pc);
		return result;
	}
	
	/*
	 * 出货批量导出
	 */
	@RequestMapping("/exportShipment.do")
	@ResponseBody
	public CyResult exportShipment(HttpServletRequest request,HttpServletResponse response, ProductCode pc) throws Exception{
		String strs1 = request.getParameter("date0");
		String strs2 = request.getParameter("date1");
		CyResult result = service.exportShipment(strs1, strs2, pc);
		return result;
	}
	
	/*
	 * 新增条码列表
	 */
	@RequestMapping("/addProductCode.do")
	@ResponseBody
	public CyResult addProductCode(HttpServletRequest request, HttpServletResponse response, ProductCode pc) {
		CyResult result = new CyResult();
		String pname = request.getParameter("pname");
		String strsn1 = request.getParameter("str0sn1");
		String strsn2 = request.getParameter("str1sn1");
		String strsn3 = request.getParameter("str0sn2");
		String strsn4 = request.getParameter("str1sn2");
		String strsn5 = request.getParameter("str0sn3");
		String strsn6 = request.getParameter("str1sn3");
		String strsn7 = request.getParameter("str0sn4");
		String strsn8 = request.getParameter("str1sn4");
		String strsn9 = request.getParameter("str0sn5");
		String strsn10 = request.getParameter("str1sn5");
		String strsn11 = request.getParameter("str0sn6");
		String strsn12 = request.getParameter("str1sn6");
		String strsn13 = request.getParameter("str0sn7");
		String strsn14 = request.getParameter("str1sn7");
		String strsn15 = request.getParameter("str0data1");
		String strsn16 = request.getParameter("str1data1");
		String strsn17 = request.getParameter("str0data2");
		String strsn18 = request.getParameter("str1data2");
//		String strsn19 = request.getParameter("pcost");
		String process1 = request.getParameter("process1");
		String process2 = request.getParameter("process2");
		String process3 = request.getParameter("process3");
		String process4 = request.getParameter("process4");
		String process5 = request.getParameter("process5");
		String process6 = request.getParameter("process6");
		String process7 = request.getParameter("process7");
		String process8 = request.getParameter("process8");
		String process9 = request.getParameter("process9");
//		Double cost = Double.parseDouble(strsn19);
//		pc.setSn1(strsn1);
//		pc.setSn2(strsn3);
//		pc.setSn3(strsn5);
//		pc.setSn4(strsn7);
//		pc.setSn5(strsn9);
//		pc.setSn6(strsn11);
//		pc.setSn7(strsn13);
//		System.out.println(pc);
//		System.out.println(strsn1);
//		System.out.println(strsn2);
//		System.out.println(strsn3);
//		System.out.println(strsn4);
//		System.out.println(strsn15);
//		System.out.println(strsn16);
		
//		Binding_code bc = new Binding_code();
//		bc.setModelCode(pc.getModelCode());
//		bc.setPNCode(pc.getPnCode());
//		bc.setBxk_SN(pc.getPorder());
//		CyResult result = service.addProductCode(snumber,strsn1,strsn2,pc);
		
//		if (process.equals("001")) {
//			result = service.addCodes(strsn1, strsn2, strsn3, strsn4, strsn15, strsn16, cost, pc);
//		} else if (process.equals("002")) {
//			result = service.wxs(strsn1, strsn2, cost, pc);
//		} else if (process.equals("003")) {
//			result = service.wxs(strsn1, strsn2, cost, pc);
//		} else if (process.equals("004")) {
//			result = service.wxs(strsn1, strsn2, cost, pc);
//		}
		result = service.addCurrency(pname, strsn1, strsn2, strsn3, strsn4, strsn5, strsn6, strsn7, strsn8, strsn9, strsn10, strsn11, strsn12, strsn13, strsn14, strsn15, strsn16, strsn17, strsn18, process1, process2, process3, process4, process5, process6, process7, process8, process9, pc);
		return result;
	}

	/*
	 * 删除条码列表
	 */
	@RequestMapping("/deleteProductCode.do")
	@ResponseBody
	public CyResult deleteProductCode(ProductCode pc) {
		CyResult result = service.deleteProductCode(pc);
		return result;
	}
	
	/*
	 * 修改条码列表
	 */
	@RequestMapping("/updateProductCode.do")
	@ResponseBody
	public CyResult updateProductCode(ProductCode pc) {
		CyResult result = service.updateProductCode(pc);
		return result;
	}

	/*
	 * 新增工艺排程
	 */
	@RequestMapping("/addProductScheduling.do")
	@ResponseBody
	public CyResult addProductScheduling(ProductScheduling ps) {
		CyResult result = service.addProductScheduling(ps);
		return result;
	}
	
	/*
	 * 查询工艺排程
	 */
	@RequestMapping("/selectProductScheduling.do")
	@ResponseBody
	public CyResult selectProductScheduling(ProductScheduling ps) {
		CyResult result = service.selectProductScheduling(ps);
		return result;
	}
	
	/*
	 * 查询全部工艺排程
	 */
	@RequestMapping("/selectAllProductScheduling.do")
	@ResponseBody
	public CyResult selectAllProductScheduling(Integer page, Integer limit) {
		CyResult result = service.selectAllProductScheduling(page,limit);
		return result;
	}
	
	/*
	 * 修改工艺排程
	 */
	@RequestMapping("/updateProductScheduling.do")
	@ResponseBody
	public CyResult updateProductScheduling(ProductScheduling ps) {
		CyResult result = service.updateProductScheduling(ps);
		return result;
	}
	
	/*
	 * 删除工艺排程
	 */
	@RequestMapping("/deleteProductScheduling.do")
	@ResponseBody
	public CyResult deleteProductScheduling(ProductScheduling ps) {
		CyResult result = service.deleteProductScheduling(ps);
		return result;
	}

	/*
	 * 条码新增页面查询工艺号
	 */
	@RequestMapping("/selectProcess.do")
	@ResponseBody
	public CyResult selectProcess() {
		CyResult result = service.selectProcess();
		return result;
	}

	/*
	 * 新 条码新增页面查询产品名
	 */
	@RequestMapping("/selectAllPname.do")
	@ResponseBody
	public CyResult selectAllPname() {
		CyResult result = service.selectAllPname();
		return result;
	}

	/*
	 * 新 条码新增页面根据产品名查询对应编码规则
	*/
	 
	@RequestMapping("/selectCodeNameByPname.do")
	@ResponseBody
	public CyResult selectCodeNameByPname(Integer i) {
		CyResult result = service.selectCodeNameByPname(i);
		return result;
	}
	
	/*
	 *查询对应编码规则
	*/
	 
	@RequestMapping("/searchProductScheduling.do")
	@ResponseBody
	public CyResult searchProductScheduling(Integer cpid) {
		CyResult result = service.searchProductScheduling(cpid);
		return result;
	}
	
	/*
	 * 条码新增页面查询产品名
	 */
	@RequestMapping("/selectPcode.do")
	@ResponseBody
	public CyResult selectPcode() {
		CyResult result = service.selectPcode();
		return result;
	}
	
	/*
	 * 加载全部产品列表
	 */
	@RequestMapping("/selectAllProductList.do")
	@ResponseBody
	public CyResult selectAllProductList(Integer pageNumber) {
		CyResult result = service.selectAllProductList(pageNumber);
		return result;
	}
	
	/*
	 * 查询单个产品列表
	 */
	@RequestMapping("/selectProductList.do")
	@ResponseBody
	public CyResult selectProductList(ProductList pl) {
		CyResult result = service.selectProductList(pl);
		return result;
	}
	
	/*
	 * 删除产品列表
	 */
	@RequestMapping("/deleteProductList.do")
	@ResponseBody
	public CyResult deleteProductList(ProductList pl) {
		CyResult result = service.deleteProductList(pl);
		return result;
	}
	
	/**
	 * 新建条码规则
	 * @param ps
	 * @return
	 */
	@RequestMapping("/addPs.do")
	@ResponseBody
	public CyResult insertPs(ProductScheduling ps,Integer ppid){
		CyResult result = service.insertProductScheduling(ps,ppid);
		return result;
	}
	
	/**
	 * 加载全部工作站
	 * @return
	 */
	@RequestMapping("/loadWorkStation.do")
	@ResponseBody
	public CyResult loadWorkStation(Integer page, Integer limit){
		CyResult result = service.loadAllWorkStation(page, limit);
		return result;
	}
	
	
	
	
	/**
	 * 新建工作站
	 * @param wsc
	 * @return
	 */
	@RequestMapping("/addWorkStation.do")
	@ResponseBody
	public CyResult insertWorkStation(WorkStationCode wsc){
		CyResult result = service.addWorkStation(wsc);
		return result;
	}
	
	/**
	 * 删除工作站
	 * @param sid
	 * @return
	 */
	@RequestMapping("/deleteWorkStation.do")
	@ResponseBody
	public CyResult deleteWorkStation(String mid){
		CyResult result = service.deleteWorkStation(mid);
		return result;
	}
	
	/**
	 * 修改工作站状态
	 * @param wsc
	 * @return
	 */
	@RequestMapping("/updateWSS.do")
	@ResponseBody
	public CyResult updateStation(WorkStationCode wsc){
		CyResult result = service.updateStation(wsc);
		return result;
	}
	
	@RequestMapping("/editProductScheduling.do")
	@ResponseBody
	public CyResult editProductScheduling(Integer ppid,ProductScheduling ps){
		
		CyResult  result=service.editProductScheduling(ppid,ps);
		return result;
	}
	
	@RequestMapping("/exportTestData.do")
	@ResponseBody
	public  CyResult exportTestData(@RequestParam(value="upSN",required=false) MultipartFile upSN,@RequestParam(value="upMac",required=false) MultipartFile upMac,HttpServletRequest request) throws Exception{
		CyResult result =service.exportTestData(upSN,upMac,request);
		return result;
		
	}
	
	@RequestMapping("/xlsAddProductCode.do")
	@ResponseBody
	public CyResult xlsAddProductCode(@RequestParam("file") MultipartFile file, ProductCode productCode, HttpServletRequest request) throws Exception{
		CyResult result = service.xlsAddProductCode(file, productCode, request);
		
		//CyResult result=new CyResult();
		//result.setData("123");
		System.out.println(result);
		return result;
	}
	
	@RequestMapping("/distinctProduct.do")
	@ResponseBody
	public CyResult distinctProduct(String str){
		CyResult result = service.distinctProduct(str);
		return result;
	}
	
	@RequestMapping("/deleteProductCodes.do")
	@ResponseBody
	public CyResult deleteProductCodes(String pidString){
		CyResult result = service.deleteProductCodes(pidString);
		return result;
	}
	
	@RequestMapping("/insertOneCode.do")
	@ResponseBody
	public CyResult insertOneCode(ProductCode productCode){
		CyResult result = service.insertOneCode(productCode);
		return result;
	}
	
	@RequestMapping("/updateOneProductCode.do")
	@ResponseBody
	public CyResult updateOneProductCode(ProductCode productCode){
		CyResult result = service.updateOneProductCode(productCode);
		return result;
	}
	
	@RequestMapping("/distinctData.do")
	@ResponseBody
	public CyResult distinctData(){
		CyResult result = service.distinctData();
		return result;
	}
	
	@RequestMapping("/exportCode.do")
	@ResponseBody
	public CyResult exportCode(ProductCode productCode) throws Exception {
		CyResult result = service.exportCode(productCode);
		return result;
	}
	
	@RequestMapping("/addModel.do")
	@ResponseBody
	public CyResult addModel(PrintModel model){
		CyResult result=service.addModel(model);
		return result;
	}
	
	@RequestMapping("/loadAllMode.do")
	@ResponseBody
	public CyResult loadAllMode(PrintModel model){
		CyResult result=service.loadAllMode(model);
		return result;
	}
	
	@RequestMapping("/deleteModel.do")
	@ResponseBody
	public CyResult deleteModel(int templateId){
		System.out.println(templateId);
		CyResult result=service.deleteModel(templateId);
		return result;
	}
	
	@RequestMapping("/editModel.do")
	@ResponseBody
	public CyResult editModel(PrintModel model){
	
		CyResult result=service.editModel(model);
		return result;
	}
	
	
	@RequestMapping("/boxNumsAdd.do")
	@ResponseBody
	public CyResult boxNumsAdd(@RequestParam(value = "file", required = false)  MultipartFile file,HttpServletRequest request,Boxnum box) throws Exception{
		CyResult result=service.boxNumsAdd(file,request,box);
		return result;
	}
	
	@RequestMapping("/loadAllBox.do")
	@ResponseBody
	public CyResult loadAllBox(Boxnum boxnum){
		CyResult result=service.loadAllBox(boxnum);
		return result;
	}
	
	@RequestMapping("/deleteBoxnum.do")
	@ResponseBody
	public  CyResult deleteBoxnum(int bid){
		CyResult result=service.deleteBoxnum(bid);
		return result;
	}
	
	
	@RequestMapping("/deleteMoreBoxnum.do")
	@ResponseBody
	public  CyResult deleteMoreBoxnum(String idstring){
		CyResult result=service.deleteMoreBoxnum(idstring);
		return result;
	}
	@RequestMapping("/bomMaterials.do")
	@ResponseBody
	public CyResult bomMaterials(@RequestParam("file") MultipartFile file, BomMaterials bomMaterials, HttpServletRequest request) throws Exception{
		CyResult result = service.bomMaterials(file, bomMaterials, request);
		System.out.println(result);
		return result;
	}

	@RequestMapping("/addPhenomenon.do")
	@ResponseBody
	public CyResult addPhenomenon(String blPhenom) {
		CyResult result = service.addPhenomenon(blPhenom);
		System.out.println(result);
		return result;
	}
	
	@RequestMapping("/loadBlPhenom.do")
	@ResponseBody
	public CyResult loadBlPhenom(Repair repair, Integer limit,Integer page) {
		CyResult result = service.loadBlPhenom(repair,limit,page);
		System.out.println(result);
		return result;
	}
	
	@RequestMapping("/deleteBlPhenom.do")
	@ResponseBody
	public CyResult deleteBlPhenom(Integer xId) {
		CyResult result = service.deleteBlPhenom(xId);
		System.out.println(result);
		return result;
	}
	@RequestMapping("/loadHealthyy.do")
	@ResponseBody
	public CyResult loadHealthyy(Repair repair) {
		CyResult result = service.loadHealthyy(repair);
		System.out.println(result);
		return result;
	}
	@RequestMapping("/addHealthyy.do")
	@ResponseBody
	public CyResult addHealthyy(String unhealthyy) {
		CyResult result = service.addHealthyy(unhealthyy);
		System.out.println(result);
		return result;
	}
	@RequestMapping("/deleteHealthyy.do")
	@ResponseBody
	public CyResult deleteHealthyy(Integer brid) {
		CyResult result = service.deleteHealthyy(brid);
		System.out.println(result);
		return result;
	}
	@RequestMapping("/loadProductModel.do")
	@ResponseBody
	public CyResult loadProductModel(ProductModel productModel) {
		CyResult result = service.loadProductModel(productModel);
		System.out.println(result);
		return result;
	}
	@RequestMapping("/loadProductModelByPid.do")
	@ResponseBody
	public CyResult loadProductModelByPid(ProductModel productModel) {
		CyResult result = service.loadProductModelByPid(productModel);
		System.out.println(result);
		return result;
	}
	@RequestMapping("/addProductModel.do")
	@ResponseBody
	public CyResult addProductModel(ProductModel productModel) {
		CyResult result = service.addProductModel(productModel);
		System.out.println(result);
		return result;
	}
	
	@RequestMapping("/deleteProductModel.do")
	@ResponseBody
	public CyResult deleteProductModel(Integer id) {
		CyResult result = service.deleteProductModel(id);
		System.out.println(result);
		return result;
	}
	
	@RequestMapping("/updateProductModel.do")
	@ResponseBody
	public CyResult updateProductModel(ProductModel productModel) {
		CyResult result = service.updateProductModel(productModel);
		System.out.println(result);
		return result;
	}
	@RequestMapping("/addRepair.do")
	@ResponseBody
	public CyResult addRepair(Repair repair) {
		CyResult result = service.addRepair(repair);
		System.out.println(result);
		return result;
	}
	
	@RequestMapping("/deleteRepair.do")
	@ResponseBody
	public CyResult deleteRepair(Integer rid) {
		CyResult result = service.deleteRepair(rid);
		System.out.println(result);
		return result;
	}
	
	@RequestMapping("/updateRepair.do")
	@ResponseBody
	public CyResult updateRepair(Repair repair) {
		CyResult result = service.updateRepair(repair);
		System.out.println(result);
		return result;
	}
}
