package com.cy.controller.Produce.tv;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cy.board.Precious;
import com.cy.dao.produce.tv.ProduceTvDao;
import com.cy.domain.list.FileUrl;
import com.cy.domain.produce.tv.AccessoryHistory;
import com.cy.domain.produce.tv.BoardcardHistory;
import com.cy.domain.produce.tv.BomList;
import com.cy.domain.produce.tv.BomMaterials;
import com.cy.domain.produce.tv.ClientList;
import com.cy.domain.produce.tv.CodingList;
import com.cy.domain.produce.tv.CompanyList;
import com.cy.domain.produce.tv.CostList;
import com.cy.domain.produce.tv.DemandList;
import com.cy.domain.produce.tv.DumpsterHistory;
import com.cy.domain.produce.tv.ImportantParts;
import com.cy.domain.produce.tv.Instruct;
import com.cy.domain.produce.tv.MakerList;
import com.cy.domain.produce.tv.MaterialManage;
import com.cy.domain.produce.tv.OrderList;
import com.cy.domain.produce.tv.PreciousHistory;
import com.cy.domain.produce.tv.PriceList;
import com.cy.domain.produce.tv.ProductHistory;
import com.cy.domain.produce.tv.PurchaseList;
import com.cy.domain.produce.tv.PurchaseStuat;
import com.cy.domain.produce.tv.ReportPrice;
import com.cy.domain.produce.tv.RequestList;
import com.cy.domain.produce.tv.Shortage;
import com.cy.domain.produce.tv.ShortageList;
import com.cy.domain.produce.tv.StructureHistory;
import com.cy.domain.produce.tv.Warehouse;
import com.cy.domain.produce.tv.WarehouseHistory;
import com.cy.service.produce.tv.ProduceTvService;
import com.cy.utils.CyResult;

@RequestMapping("/tv")
@Controller
public class ProduceTVController {
	
	@Resource
	private ProduceTvService produceTvService;
	
	@RequestMapping("/loadTV.do")
	@ResponseBody
	public CyResult loadProduceTv(Integer pageNumber){
		CyResult result = produceTvService.loadProduce(pageNumber);
		return result;
	}
	
	@RequestMapping("/loadTvById.do")
	@ResponseBody
	public CyResult loadProduceTvById(Integer tv_test_id){
		CyResult result = produceTvService.loadProduceTvById(tv_test_id);
		return result;
	}
	
	
	@RequestMapping("/loadmm.do")
	@ResponseBody
	public CyResult loadMM(Integer page , Integer limit,MaterialManage mm){
		System.out.println("mm:"+mm);
		CyResult result = produceTvService.loadMaterial(page, limit,mm);
		return result;
	}
	
	
	@RequestMapping("/addmm.do")
	@ResponseBody
	public CyResult addMM(MaterialManage mm){
		System.err.println(mm.getMaterialName());
		CyResult result = produceTvService.addMaterial(mm);
		return result;
	}
	
	@RequestMapping("/updatemm.do")
	@ResponseBody
	public CyResult updateMM(MaterialManage mm){
		CyResult result = produceTvService.updateMaterial(mm);
		return result;
	}
	
	
	@RequestMapping("/deletemm.do")
	@ResponseBody
	public CyResult deleteMM(Integer id){
		CyResult result = produceTvService.removeMaterial(id);
		return result;
	}
	
	@RequestMapping("/loadwh.do")
	@ResponseBody
	public CyResult loadWarehouse(Integer pageNumber){
		CyResult result = produceTvService.loadWarehouse(pageNumber);
		return result;
	}
	
	@RequestMapping("/loadhistory.do")
	@ResponseBody
	public CyResult loadwareHistory(Integer mid){
		CyResult result = produceTvService.loadWareHistory(mid);
		return result;
	}
	
	@RequestMapping("/delhistory.do")
	@ResponseBody
	public CyResult deletewareHistory(Integer id){
		CyResult result = produceTvService.removeWareHistory(id);
		return result;
	}
	
	@RequestMapping("/addhistory.do")
	@ResponseBody
	public CyResult insertwareHistory(WarehouseHistory wh){
		CyResult result = produceTvService.addWareHistory(wh);
		return result;
	}
	
	@RequestMapping("/loadmate.do")
	@ResponseBody
	public CyResult searchloadmm(String pn, String storeState){
		CyResult result = produceTvService.searchloadMate(pn, storeState);
		return result;
	}
	
	@RequestMapping("/loadps.do")
	@ResponseBody
	public CyResult loadProducts(Warehouse warehouse){
		CyResult result = produceTvService.loadProducts(warehouse);
		return result;
	}
	@RequestMapping("/loadtemporaryManager.do")
	@ResponseBody
	public CyResult loadtemporaryManager(Warehouse warehouse){
		CyResult result = produceTvService.loadtemporaryManager(warehouse);
		return result;
	}
	@RequestMapping("/loadErwork.do")
	@ResponseBody
	public CyResult loadErwork(Warehouse warehouse){
		CyResult result = produceTvService.loadErwork(warehouse);
		return result;
	}
	
	
	@RequestMapping("/loadHistoryByIds.do")
	@ResponseBody
	public CyResult loadHistoryByIds(String ids){
		CyResult result = produceTvService.loadHistoryByIds(ids);
		return result;
	}
	
	@RequestMapping("/loadpdhistory.do")
	@ResponseBody
	public CyResult loadproductHistory(ProductHistory history){
		CyResult result = produceTvService.loadProductHistory(history);
		return result;
	}
	
	@RequestMapping("/addproductHistory.do")
	@ResponseBody
	public CyResult addProductHistory(ProductHistory ph){
		CyResult result = produceTvService.addProductHistory(ph);
		return result;
	}
	@RequestMapping("/addtemoraryManager.do")
	@ResponseBody
	public CyResult addtemoraryManager(ProductHistory ph){
		CyResult result = produceTvService.addtemoraryManager(ph);
		return result;
	}
	
	@RequestMapping("/delProducthistory.do")
	@ResponseBody
	public CyResult removeProductHistory(Integer id){
		CyResult result = produceTvService.removeProductHistory(id);
		return result;
	}
	
	
	@RequestMapping("/loadHistoryWarByIds.do")
	@ResponseBody
	public CyResult loadHistoryWarByIds(String ids, String clientNo){
		CyResult result = produceTvService.loadHistoryWarByIds(ids, clientNo);
		return result;
	}
	
	@RequestMapping("/loadOrders.do")
	@ResponseBody
	public CyResult loadOrderList(OrderList orders){
		CyResult result = produceTvService.loadOrderList(orders);
		return result;
	}
	
	@RequestMapping("/addOrder.do")
	@ResponseBody
	public CyResult addOrderList(OrderList orderList){
		CyResult result = produceTvService.addOrderList(orderList);
		return result;
	}
	
	@RequestMapping("/loadOrdersByState.do")
	@ResponseBody
	public CyResult findOrderstate(Integer storeState){
		CyResult result = produceTvService.loadOrderState(storeState);
		return result;
	}
	
	
	@RequestMapping("/editOrder.do")
	@ResponseBody
	public CyResult editOrder(OrderList order){
		CyResult result=produceTvService.editOrder(order);
		return result;
	}
	
	
	@RequestMapping("/loadOrdersByIdStrs.do")
	@ResponseBody
	public CyResult loadduizhang(String idstrs){
		CyResult result=produceTvService.loadduizhang(idstrs);
		return result;
	}
	
	
	@RequestMapping("/editProductHistoryById.do")
	@ResponseBody
	public CyResult editProductHistoryById(ProductHistory history){
		CyResult result=produceTvService.editProductHistoryById(history);
		return result;
	}
	
	
	@RequestMapping("/editWartHistoryById.do")
	@ResponseBody
	public CyResult editWartHistoryById(WarehouseHistory history){
		CyResult result=produceTvService.editWartHistoryById(history);
		return result;
	}
	
	
	@RequestMapping("/delOrderById.do")
	@ResponseBody
	public CyResult delOrderById(Integer id){
		CyResult result=produceTvService.delOrderById(id);
		return result;
	}	
	
	@RequestMapping("/addBom.do")
	@ResponseBody
	public CyResult addBomList(Integer mid,String uname,BomList bom){
		System.err.println(bom);
		CyResult result = produceTvService.addBomList(mid,uname);
		return result;
	}
	
	@RequestMapping("/deleteBomById.do")
	@ResponseBody
	public CyResult deleteBomList(Integer id){
		CyResult result = produceTvService.deleteBomList(id);
		return result;
	}
	
	@RequestMapping("/loadBom.do")
	@ResponseBody
	public CyResult loadBomList(BomList bomss){
		CyResult result = produceTvService.loadBomList(bomss);
		return result;
	}
	
	
	@RequestMapping("/loadBomMate.do")
	@ResponseBody
	public CyResult loadBomMate(BomMaterials bomMaterials){
		CyResult result = produceTvService.loadBomMate(bomMaterials);
		return result;
	}
	
	@RequestMapping("/addBomMate.do")
	@ResponseBody
	public CyResult addBomMate(BomMaterials bm){
		CyResult result = produceTvService.addBomMate(bm);
		return result;
	}
	
	@RequestMapping("/deleBomMate.do")
	@ResponseBody
	public CyResult deleteBomMate(Integer id){
		CyResult result = produceTvService.deleteBomMate(id);
		return result;
	}
	
	@RequestMapping("/updateBM.do")
	@ResponseBody
	public CyResult updateBomMate(BomMaterials bm){
		CyResult result = produceTvService.updateBomMate(bm);
		return result;
	}
	
	@RequestMapping("/sreachBom.do")
	@ResponseBody
	public CyResult sreachBomList(String pn){
		CyResult result = produceTvService.searchBomList(pn);
		return result;
	}
	
	
	@RequestMapping("/oweMate.do")
	@ResponseBody
	public CyResult oweMate(Integer pageNumber){
		CyResult result = produceTvService.oweMate(pageNumber);
		return result;
	}
	
	@RequestMapping("/findOweMate.do")
	@ResponseBody
	public CyResult sreachOweMate(String orderNumber, String pname){
		CyResult result = produceTvService.searchOweMate(orderNumber, pname);
		return result;
	}
	
	@RequestMapping("/loadPrice.do")
	@ResponseBody
	public CyResult loadPrices(PriceList price){
		CyResult result = produceTvService.loadPriceList(price);
		return result;
	}
	
	@RequestMapping("/addPrice.do")
	@ResponseBody
	public CyResult addPrice(PriceList priceList){
		CyResult result = produceTvService.addPriceList(priceList);
		return result;
	}
	
	@RequestMapping("/deletePrice.do")
	@ResponseBody
	public CyResult deletePrice(Integer id){
		CyResult result = produceTvService.removePriceList(id);
		return result;
	}
	
	@RequestMapping("/updatePrice.do")
	@ResponseBody
	public CyResult updatePrice(PriceList priceList){
		CyResult result = produceTvService.updatePriceList(priceList);
		return result;
	}
	
	@RequestMapping("/searchPrice.do")
	@ResponseBody
	public CyResult sreachPrice(String pn){
		CyResult result = produceTvService.sreachPriceList(pn);
		return result;
	}
	
	@RequestMapping("/placeOrder.do")
	@ResponseBody
	public CyResult placeOrder(Integer bid, Double demandNumber, String site, String deliveryTime, Integer sort){
		CyResult result = produceTvService.placeOrder(bid, demandNumber, site, deliveryTime, sort);
		return result;
	}
	
	@RequestMapping("/loadpl.do")
	@ResponseBody
	public CyResult loadPurchaseList(PurchaseList purchase){
		CyResult result = produceTvService.loadPurchaseList(purchase);
		return result;
	}
	
	@RequestMapping("/deletePl.do")
	@ResponseBody
	public CyResult deletePurchaseList(Integer id){
		CyResult result = produceTvService.deletePurchaseList(id);
		return result;
	}
	
	@RequestMapping("/loadDemand.do")
	@ResponseBody
	public CyResult loadDemandList(PurchaseList purchaseList){
		CyResult result = produceTvService.loadDemandList(purchaseList);
		return result;
	}
	
	@RequestMapping("/loadPriceReportDemand.do")
	@ResponseBody
	public CyResult loadPriceReportDemand(ReportPrice reportPrice){
		CyResult result = produceTvService.loadPriceReportDemand(reportPrice);
		return result;
	}
	
	
	@RequestMapping("/updateDemand.do")
	@ResponseBody
	public CyResult updateDemandList(Integer id, String demandNumber, String deliveryTime){
		CyResult result = produceTvService.updateDemandList(id, demandNumber, deliveryTime);
		return result;
	}
	
	@RequestMapping("/deleteDemand.do")
	@ResponseBody
	public CyResult deleteDemandList(Integer id){
		CyResult result = produceTvService.deleteDemandList(id);
		return result;
	}
	
	@RequestMapping("/updatePs.do")
	@ResponseBody
	public CyResult updatePruchaseStuat(PurchaseStuat ps){
		CyResult result = produceTvService.updatePruchaseStuat(ps);
		return result;
	}
	
	@RequestMapping("/searchPP.do")
	@ResponseBody
	public CyResult searchProceedPruchase(String purchaseOrders){
		CyResult result = produceTvService.searchProceedPruchase(purchaseOrders);
		return result;
	}
	
	@RequestMapping("/loadEndpl.do")
	@ResponseBody
	public CyResult loadEndPurchaseList(PurchaseList purchase){
		CyResult result = produceTvService.loadEndPurchaseList(purchase);
		return result;
	}
	
	@RequestMapping("/searchEndPP.do")
	@ResponseBody
	public CyResult searchEndPruchase(String purchaseOrders){
		CyResult result = produceTvService.searchEndPruchase(purchaseOrders);
		return result;
	}
	
	@RequestMapping("/loadEndOrder.do")
	@ResponseBody
	public CyResult loadEndOrderList(OrderList orders){
		CyResult result = produceTvService.loadEndOrderList(orders);
		return result;
	}
	
	@RequestMapping("/searchEndOrder.do")
	@ResponseBody
	public CyResult searchEndOrderList(String orderNumber){
		CyResult result = produceTvService.searchEndOrderList(orderNumber);
		return result;
	}
	
	@RequestMapping("/searchOrder.do")
	@ResponseBody
	public CyResult searchOrderList(String orderNumber){
		CyResult result = produceTvService.searchOrderList(orderNumber);
		return result;
	}
	
	@RequestMapping("/orderJd.do")
	@ResponseBody
	public CyResult updateOrderStuat(String id, Integer stuat){
		CyResult result = produceTvService.updateOrderStuat(id, stuat);
		return result;
	}
	
	@RequestMapping("/loadStr.do")
	@ResponseBody
	public CyResult loadStructure(Integer pageNumber){
		CyResult result = produceTvService.loadStructure(pageNumber);
		return result;
	}
	
	@RequestMapping("/loadStrHistory.do")
	@ResponseBody
	public CyResult loadStructureHistory(Integer mid){
		CyResult result = produceTvService.loadStructureHistory(mid);
		return result;
	}
	
	@RequestMapping("/addStructureHistory.do")
	@ResponseBody
	public CyResult addStructureHistory(StructureHistory sh){
		CyResult result = produceTvService.addStructureHistory(sh);
		return result;
	}
	
	@RequestMapping("/delStructureHistory.do")
	@ResponseBody
	public CyResult removeStructureHistory(Integer id){
		CyResult result = produceTvService.removeStructureHistory(id);
		return result;
	}
	
	@RequestMapping("/loadStrhIds.do")
	@ResponseBody
	public CyResult loadStructureHistoryWarByIds(String ids){
		CyResult result = produceTvService.loadStructureHistoryWarByIds(ids);
		return result;
	}
	
	@RequestMapping("/updateStructureHistory.do")
	@ResponseBody
	public CyResult updateStructureHistory(StructureHistory history){
		CyResult result = produceTvService.updateStructureHistory(history);
		return result;
	}
	
	@RequestMapping("/loadBoardcard.do")
	@ResponseBody
	public CyResult loadBoardcard(Integer pageNumber){
		CyResult result = produceTvService.loadBoardcard(pageNumber);
		return result;
	}
	
	@RequestMapping("/loadBoardcardHistory.do")
	@ResponseBody
	public CyResult loadBoardcardHistory(Integer mid){
		CyResult result = produceTvService.loadBoardcardHistory(mid);
		return result;
	}
	
	@RequestMapping("/addBoardcardHistory.do")
	@ResponseBody
	public CyResult addBoardcardHistory(BoardcardHistory sh){
		CyResult result = produceTvService.addBoardcardHistory(sh);
		return result;
	}
	
	@RequestMapping("/delBoardcardHistory.do")
	@ResponseBody
	public CyResult removeBoardcardHistory(Integer id){
		CyResult result = produceTvService.removeBoardcardHistory(id);
		return result;
	}
	
	@RequestMapping("/loadbkhIds.do")
	@ResponseBody
	public CyResult loadBoardcardHistoryWarByIds(String ids){
		CyResult result = produceTvService.loadBoardcardHistoryWarByIds(ids);
		return result;
	}
	
	@RequestMapping("/updateBoardcardHistory.do")
	@ResponseBody
	public CyResult updateBoardcardHistory(BoardcardHistory history){
		CyResult result = produceTvService.updateBoardcardHistory(history);
		return result;
	}
	
	@RequestMapping("/loadPrecious.do")
	@ResponseBody
	public CyResult loadPrecious(Integer pageNumber){
		CyResult result = produceTvService.loadPrecious(pageNumber);
		return result;
	}
	
	@RequestMapping("/loadPreciousHistory.do")
	@ResponseBody
	public CyResult loadPreciousHistory(Integer mid){
		CyResult result = produceTvService.loadPreciousHistory(mid);
		return result;
	}
	
	@RequestMapping("/addPreciousHistory.do")
	@ResponseBody
	public CyResult addPreciousHistory(PreciousHistory sh){
		CyResult result = produceTvService.addPreciousHistory(sh);
		return result;
	}
	
	@RequestMapping("/delPreciousHistory.do")
	@ResponseBody
	public CyResult removePreciousHistory(Integer id){
		CyResult result = produceTvService.removePreciousHistory(id);
		return result;
	}
	
	@RequestMapping("/loadgzhIds.do")
	@ResponseBody
	public CyResult loadPreciousHistoryWarByIds(String ids){
		CyResult result = produceTvService.loadPreciousHistoryWarByIds(ids);
		return result;
	}
	
	@RequestMapping("/updatePreciousHistory.do")
	@ResponseBody
	public CyResult updatePreciousHistory(PreciousHistory history){
		CyResult result = produceTvService.updatePreciousHistory(history);
		return result;
	}
	
	@RequestMapping("/loadCostList.do")
	@ResponseBody
	public CyResult loadCostList(CostList cost){
		CyResult result = produceTvService.loadCostList(cost);
		return result;
	}
	
	@RequestMapping("/deleteCost.do")
	@ResponseBody
	public CyResult deleteCostList(Integer id){
		CyResult result = produceTvService.deleteCostList(id);
		return result;
	}
	
	@RequestMapping("/loadCostDemand.do")
	@ResponseBody
	public CyResult loadCostListDemandList(DemandList deman){
		CyResult result = produceTvService.loadCostListDemandList(deman);
		return result;
	}
	
	@RequestMapping("/placeCostList.do")
	@ResponseBody
	public CyResult placeCostListOrder(Integer bid, Double demandNumber){
		CyResult result = produceTvService.placeCostListOrder(bid, demandNumber);
		return result;
	}
	
	@RequestMapping("/searchCostList.do")
	@ResponseBody
	public CyResult searchCostList(String costOrders){
		CyResult result = produceTvService.searchCostList(costOrders);
		return result;
	}
	
	@RequestMapping("/placeMnco.do")
	@ResponseBody
	public CyResult placeMinNumberCostListOrder(Integer bid){
		CyResult result = produceTvService.placeMinNumberCostListOrder(bid);
		return result;
	}
	
	@RequestMapping("/loadAccessory.do")
	@ResponseBody
	public CyResult loadAccessory(Integer pageNumber){
		CyResult result = produceTvService.loadAccessory(pageNumber);
		return result;
	}
	
	@RequestMapping("/loadAccessoryHistory.do")
	@ResponseBody
	public CyResult loadAccessoryHistory(Integer mid){
		CyResult result = produceTvService.loadAccessoryHistory(mid);
		return result;
	}
	
	@RequestMapping("/addAccessoryHistory.do")
	@ResponseBody
	public CyResult addAccessoryHistory(AccessoryHistory sh){
		CyResult result = produceTvService.addAccessoryHistory(sh);
		return result;
	}
	
	@RequestMapping("/delAccessoryHistory.do")
	@ResponseBody
	public CyResult removeAccessoryHistory(Integer id){
		CyResult result = produceTvService.removeAccessoryHistory(id);
		return result;
	}
	
	@RequestMapping("/loadflhIds.do")
	@ResponseBody
	public CyResult loadAccessoryWarByIds(String ids) {
		CyResult result = produceTvService.loadAccessoryWarByIds(ids);
		return result;
	}
	
	@RequestMapping("/updateAccessoryHistory.do")
	@ResponseBody
	public CyResult updateAccessoryHistory(AccessoryHistory history){
		CyResult result = produceTvService.updateAccessoryHistory(history);
		return result;
	}
	
	@RequestMapping("/loadDumpster.do")
	@ResponseBody
	public CyResult loadDumpster(Integer pageNumber) {
		CyResult result = produceTvService.loadDumpster(pageNumber);
		return result;
	}
	
	@RequestMapping("/loadDumpsterHistory.do")
	@ResponseBody
	public CyResult loadDumpsterHistory(Integer mid){
		CyResult result = produceTvService.loadDumpsterHistory(mid);
		return result;
	}
	
	@RequestMapping("/addDumpsterHistory.do")
	@ResponseBody
	public CyResult addDumpsterHistory(DumpsterHistory sh){
		CyResult result = produceTvService.addDumpsterHistory(sh);
		return result;
	}
	
	@RequestMapping("/delDumpsterHistory.do")
	@ResponseBody
	public CyResult removeDumpsterHistory(Integer id){
		CyResult result = produceTvService.removeDumpsterHistory(id);
		return result;
	}
	
	@RequestMapping("/loadzfhIds.do")
	@ResponseBody
	public CyResult loadDumpsterWarByIds(String ids){
		CyResult result = produceTvService.loadDumpsterWarByIds(ids);
		return result;
	}
	
	@RequestMapping("/updateDumpsterHistory.do")
	@ResponseBody
	public CyResult updateDumpsterHistory(DumpsterHistory history){
		CyResult result = produceTvService.updateDumpsterHistory(history);
		return result;
	}
	
	@RequestMapping("/loadCodingList.do")
	@ResponseBody
	public CyResult loadCodingList(CodingList codine){
		CyResult result = produceTvService.loadCodingList(codine);
		return result;
	}
	
	@RequestMapping("/addCodingList.do")
	@ResponseBody
	public CyResult addCodingList(CodingList codingList){
		CyResult result = produceTvService.addCodingList(codingList);
		return result;
	}
	
	@RequestMapping("/updateCodingList.do")
	@ResponseBody
	public CyResult updateCodingList(CodingList codingList){
		CyResult result = produceTvService.updateCodingList(codingList);
		return result;
	}
	
	@RequestMapping("/delCodingList.do")
	@ResponseBody
	public CyResult deleteCodingList(Integer id){
		CyResult result = produceTvService.deleteCodingList(id);
		return result;
	}
	
	@RequestMapping("/searchCoding.do")
	@ResponseBody
	public CyResult searchCodingList(String number){
		CyResult result = produceTvService.searchCodingList(number);
		return result;
	}
	
	@RequestMapping("/loadMakerList.do")
	@ResponseBody
	public CyResult loadMakerList(MakerList maker){
		CyResult result = produceTvService.loadMakerList(maker);
		return result;
	}
	
	@RequestMapping("/addMakerList.do")
	@ResponseBody
	public CyResult addMakerList(HttpServletRequest request, MakerList makerList) throws Exception {
		CyResult result = produceTvService.addMakerList(request, makerList);
		return result;
	}
	
	@RequestMapping("/updateMakerList.do")
	@ResponseBody
	public CyResult updateMakerList(HttpServletRequest request, MakerList makerList) throws Exception {
		CyResult result = produceTvService.updateMakerList(request, makerList);
		return result;
	}
	
	@RequestMapping("/deleteMakerList.do")
	@ResponseBody
	public CyResult deleteMakerList(Integer id){
		CyResult result = produceTvService.deleteMakerList(id);
		return result;
	}
	
	@RequestMapping("/searchMakerList.do")
	@ResponseBody
	public CyResult searchMakerList(String makerName){
		CyResult result = produceTvService.searchMakerList(makerName);
		return result;
	}
	
	@RequestMapping("/loadClientList.do")
	@ResponseBody
	public CyResult loadClientList(Integer page, Integer limit){
		CyResult result = produceTvService.loadClientList(page, limit);
		return result;
	}
	
	
	
	
	@RequestMapping("/addClientList.do")
	@ResponseBody
	public CyResult addClientList(HttpServletRequest request, ClientList clientList) throws Exception {
		CyResult result = produceTvService.addClientList(request,clientList);
		return result;
	}
	
	@RequestMapping("/updateClientList.do")
	@ResponseBody
	public CyResult updateClientList(/*MultipartFile files, */HttpServletRequest request, ClientList clientList) throws Exception {
		CyResult result = produceTvService.updateClientList(request,clientList);
		return result;
	}
	
	@RequestMapping("/deleteClientList.do")
	@ResponseBody
	public CyResult deleteClientList(Integer id){
		CyResult result = produceTvService.deleteClientList(id);
		return result;
	}
	
	@RequestMapping("/searchClientList.do")
	@ResponseBody
	public CyResult searchClientList(String clientName){
		CyResult result = produceTvService.searchClientList(clientName);
		return result;
	}
	
	@RequestMapping("/loadAllCust.do")
	@ResponseBody
	public CyResult loadAllClientList() {
		CyResult result = produceTvService.loadAllClientList();
		return result;
	}
	
	@RequestMapping("/searchWh.do")
	@ResponseBody
	public CyResult searchWarehouse(String pn) {
		CyResult result = produceTvService.searchWarehouse(pn);
		return result;
	}
	
	@RequestMapping("/searchPh.do")
	@ResponseBody
	public CyResult searchProduct(String pn){
		CyResult result = produceTvService.searchProduct(pn);
		return result;
	}
	
	@RequestMapping("/loadMaker.do")
	@ResponseBody
	public CyResult loadMaker(Integer id) {
		CyResult result = produceTvService.loadMaker(id);
		return result;
	}
	
	@RequestMapping("/loadRequest.do")
	@ResponseBody
	public CyResult loadRequestList(RequestList reqDemo) {
		CyResult result = produceTvService.loadRequestList(reqDemo);
		return result;
	}
	
	@RequestMapping("/addRequestList.do")
	@ResponseBody
	public CyResult addRequestList(RequestList requestList) {
		CyResult result = produceTvService.addRequestList(requestList);
		return result;
	}
	
	@RequestMapping("/updateRequestList.do")
	@ResponseBody
	public CyResult updateRequestList(RequestList requestList) {
		CyResult result = produceTvService.updateRequestList(requestList);
		return result;
	}
	
	@RequestMapping("/deleteRequestList.do")
	@ResponseBody
	public CyResult deleteRequestList(Integer id) {
		CyResult result = produceTvService.deleteRequestList(id);
		return result;
	}
	
	@RequestMapping("/loadCompanyList.do")
	@ResponseBody
	public CyResult loadCompanyList(CompanyList company) {
		CyResult result = produceTvService.loadCompanyList(company);
		return result;
	}
	
	@RequestMapping("/addCompanyList.do")
	@ResponseBody
	public CyResult addCompanyList(HttpServletRequest request, CompanyList companyList)
			throws Exception {
		CyResult result = produceTvService.addCompanyList(request, companyList);
		return result;
	}
	
	@RequestMapping("/makerIdName.do")
	@ResponseBody
	public CyResult loadAllMakerIdName() {
		CyResult result = produceTvService.loadAllMakerIdName();
		return result;
	}
	
	@RequestMapping("/companyIdName.do")
	@ResponseBody
	public CyResult loadAllCompanyIdName() {
		CyResult result = produceTvService.loadAllCompanyIdName();
		return result;
	}
	
	@RequestMapping("/requestId.do")
	@ResponseBody
	public CyResult loadAllRequestListId(RequestList req) {
		CyResult result = produceTvService.loadAllRequestListId(req);
		return result;
	}
	
	@RequestMapping("/updateCompanyList.do")
	@ResponseBody
	public CyResult updateCompanyList(HttpServletRequest request, CompanyList companyList)
			throws Exception {
		CyResult result = produceTvService.updateCompanyList(request, companyList);
		return result;
	}
	
	@RequestMapping("/loadRequiredById.do")
	@ResponseBody
	public CyResult loadRequestListId(Integer id) {
		CyResult result = produceTvService.loadRequestListId(id);
		return result;
	}
	
	@RequestMapping("/purchasePrint.do")
	@ResponseBody
	public CyResult purchasePrint(Integer id1, Integer id2, Integer id3, Integer thisId) {
		CyResult result = produceTvService.purchasePrint(id1, id2, id3, thisId);
		return result;
	}
	
	@RequestMapping("/searchCompanyId.do")
	@ResponseBody
	public CyResult searchCompanyListId(Integer id) {
		CyResult result = produceTvService.searchCompanyListId(id);
		return result;
	}
	
	@RequestMapping("/deleteCompany.do")
	@ResponseBody
	public CyResult deleteCompany(Integer id) {
		CyResult result = produceTvService.deleteCompany(id);
		return result;
	}
	
	@RequestMapping("/searchCompany.do")
	@ResponseBody
	public CyResult searchCompany(String companyName) {
		CyResult result = produceTvService.searchCompany(companyName);
		return result;
	}
	
	@RequestMapping("/placeMaterialManage.do")
	@ResponseBody
	public CyResult placeMaterialManage(Integer id, Double demandNumber, String site, String deliveryTime, Integer sort){
		CyResult result = produceTvService.placeMaterialManage(id, demandNumber, site, deliveryTime, sort);
		return result;
	}
	
	@RequestMapping("/priceMaterialManage.do")
	@ResponseBody
	public CyResult priceMaterialManage(Integer id, Double demandNumber, String site, String deliveryTime, Integer sort){
		CyResult result = produceTvService.priceMaterialManage(id, demandNumber, site, deliveryTime, sort);
		return result;
	}
	
	
	@RequestMapping("/placeMachiningManage.do")
	@ResponseBody
	public CyResult placeMachiningManage(Integer id, Double demandNumber, String site, String deliveryTime,
			Integer sort, Integer technology) {
		CyResult result = produceTvService.placeMachiningManage(id, demandNumber, site, deliveryTime, sort, technology);
		return result;
	}
	
	@RequestMapping("/addDemandList.do")
	@ResponseBody
	public CyResult addDemandList(Integer id, Integer mid, Double demandNumber, String deliveryTime) {
		CyResult result = produceTvService.addDemandList(id, mid, demandNumber, deliveryTime);
		return result;
	}
	
	@RequestMapping("/loadClientId.do")
	@ResponseBody
	public CyResult loadClientListId(Integer id){
		CyResult result = produceTvService.loadClientListId(id);
		return result;
	}
	
	@RequestMapping("/loadOrdersByMid.do")
	@ResponseBody
	public CyResult loadOrdersByMid(Integer mid){
		CyResult result=produceTvService.loadOrdersByMid(mid);
		return  result;
	}
	
	@RequestMapping("/loadReportPriceList.do")
	@ResponseBody
	public CyResult loadReportPriceList(ReportPrice rp){
		CyResult result=produceTvService.loadReportPriceList(rp);
		return  result;
	}
	
	@RequestMapping("/pricePlaceOrder.do")
	@ResponseBody
	public CyResult pricePlaceOrder(Integer bid, Double demandNumber, String site, String deliveryTime, Integer sort){
		CyResult result = produceTvService.pricePlaceOrder(bid, demandNumber, site, deliveryTime, sort);
		return result;
	}
	@RequestMapping("/delreportPrice.do")
	@ResponseBody
	public CyResult delreportPrice(Integer id) {
		CyResult result = produceTvService.delreportPrice(id);
		return result;
	}
	@RequestMapping("/dayingReportPrice.do")
	@ResponseBody
	public CyResult dayingReportPrice(Integer id1, Integer id2, Integer id3, Integer thisId) {
		CyResult result = produceTvService.dayingReportPrice(id1, id2, id3, thisId);
		return result;
	}
	@RequestMapping("/giveAccess.do")
	@ResponseBody
	public CyResult giveAccess(String idString ,Integer instruct_pzState,String storeState) {
		CyResult result = produceTvService.giveAccess(idString ,instruct_pzState ,storeState);
		return result;
	}
	
	@RequestMapping("/loadInstruct.do")
	@ResponseBody
	public CyResult loadInstruct(Instruct instruct) {
		CyResult result = produceTvService.loadInstruct(instruct);
		return result;
	}
	
	
	@RequestMapping("/loadboxFileList.do")
	@ResponseBody
	public CyResult loadboxFileList(FileUrl fileUrl){
		CyResult result=produceTvService.loadboxFileList(fileUrl);
		return result;
	}
	@RequestMapping("/addShortage.do")
	@ResponseBody
	public CyResult addShortage(Shortage shortage){
		CyResult result=produceTvService.addShortage(shortage);
		return result;
	}
 
	@RequestMapping("/loadShortage.do")
	@ResponseBody
	public CyResult loadShortage(Shortage shortage){
		CyResult result=produceTvService.loadShortage(shortage);
		return result;
	}
 
	@RequestMapping("/loadShortagelist.do")
	@ResponseBody
	public CyResult loadShortagelist(ShortageList shortageList){
		CyResult result=produceTvService.loadShortagelist(shortageList);
		return result;
	}

	@RequestMapping("/placeShortage.do")
	@ResponseBody
	public CyResult placeShortage(String pname,String pn,Integer sid, Integer shortageNumber, String site, String shortageCode, Integer sort){
		CyResult result=produceTvService.placeShortage(pname,pn,sid,shortageNumber,site,shortageCode,sort);
		return result;
	}
	@RequestMapping("/deleteShortage.do")
	@ResponseBody
	public CyResult deleteShortage(Integer sid){
		CyResult result=produceTvService.deleteShortage(sid);
		return result;
	}
	@RequestMapping("/loadprecious.do")
	@ResponseBody
	public CyResult loadprecious(Precious precious){
		CyResult result=produceTvService.loadprecious(precious);
		return result;
	}
	
	@RequestMapping("/editBom.do")
	@ResponseBody
	public CyResult editBomAll(BomList bom) {
		CyResult result=produceTvService.updataBomList(bom);
		return result;
	}
	
	@RequestMapping("/getParts.do")
	@ResponseBody
	public CyResult getParts(ImportantParts precious){
		CyResult result=produceTvService.getParts(precious);
		return result;
	}
	@RequestMapping("/insertParts.do")
	@ResponseBody
	public CyResult insertParts(ImportantParts precious){
		CyResult result=produceTvService.insertParts(precious);
		return result;
	}
	@RequestMapping("/updateParts.do")
	@ResponseBody
	public CyResult updateParts(ImportantParts precious){
		CyResult result=produceTvService.updateParts(precious);
		return result;
	}
	@RequestMapping("/deleteParts.do")
	@ResponseBody
	public CyResult deleteParts(String id){
		CyResult result=produceTvService.deleteParts(id);
		return result;
	}
}
