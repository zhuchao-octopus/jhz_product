package com.cy.service.produce.tv;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;
import org.springframework.web.multipart.MultipartFile;

import com.cy.board.Precious;
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
import com.cy.domain.produce.tv.OweMaterials;
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
import com.cy.utils.CyResult;

public interface ProduceTvService {
	
	public CyResult loadProduce(Integer pageNumber);

	public CyResult loadProduceTvById(Integer tv_test_id);
	
	public CyResult loadMaterial(Integer page , Integer limit,MaterialManage mm);
	
	public CyResult addMaterial(MaterialManage mm);
	
	public CyResult updateMaterial(MaterialManage mm);
	
	public CyResult removeMaterial(Integer id);
	
	public CyResult loadWarehouse(Integer pageNumber);
	
	public CyResult loadWareHistory(Integer mid);
	
	public CyResult removeWareHistory(Integer id);
	
	public CyResult addWareHistory(WarehouseHistory wh);
	
	public CyResult searchloadMate(String pn, String storeState);
	
	public CyResult loadProducts(Warehouse warehouse);
	
	public CyResult loadProductHistory(ProductHistory history);

	public CyResult loadHistoryByIds(String ids);
	
	public CyResult addProductHistory(ProductHistory ph);
	
	public CyResult removeProductHistory(Integer id);

	public CyResult loadHistoryWarByIds(String ids, String clientNo);
	
	public CyResult loadOrderList(OrderList orders);
	
	public CyResult addOrderList(OrderList orderList);
	
	public CyResult loadOrderState(Integer storeState);
	
	public CyResult loadduizhang(String idstrs);

	public CyResult editOrder(OrderList order);

	public CyResult editProductHistoryById(ProductHistory history);

	public CyResult editWartHistoryById(WarehouseHistory history);

	public CyResult delOrderById(Integer id);
	
	public CyResult addBomList(Integer mid,String uname);
	
	public CyResult deleteBomList(Integer id);
	
	public CyResult loadBomList(BomList bomss);
	
	public CyResult loadBomMate(BomMaterials bomMaterials);
	
	public CyResult addBomMate(BomMaterials bm);
	
	public CyResult deleteBomMate(Integer id);
	
	public CyResult updateBomMate(BomMaterials bm);
	
	public CyResult searchBomList(String pn);
	
	public CyResult oweMate(Integer pageNumber);
	
	public CyResult searchOweMate(String orderNumber, String pname);
	
	public CyResult loadPriceList(PriceList price);
	
	public CyResult addPriceList(PriceList priceList);
	
	public CyResult removePriceList(Integer id);
	
	public CyResult updatePriceList(PriceList priceList);
	
	public CyResult sreachPriceList(String pn);
	
	public CyResult placeOrder(Integer bid, Double demandNumber, String site, String deliveryTime, Integer sort);
	
	public CyResult loadPurchaseList(PurchaseList purchase);
	
	public CyResult loadDemandList(PurchaseList purchaseList);
	
	public CyResult deletePurchaseList(Integer id);
	
	public CyResult updateDemandList(Integer id, String demandNumber, String deliveryTime);
	
	public CyResult deleteDemandList(Integer id);
	
	public CyResult updatePruchaseStuat(PurchaseStuat ps);
	
	public CyResult searchProceedPruchase(String purchaseOrders);
	
	public CyResult loadEndPurchaseList(PurchaseList purchase);
	
	public CyResult searchEndPruchase(String purchaseOrders);
	
	public CyResult loadEndOrderList(OrderList orders);
	
	public CyResult searchEndOrderList(String orderNumber);
	
	public CyResult searchOrderList(String orderNumber);
	
	public CyResult updateOrderStuat(String id, Integer stuat);
	
	public CyResult loadStructure(Integer pageNumber);
	
	public CyResult loadStructureHistory(Integer mid);
	
	public CyResult addStructureHistory(StructureHistory sh);
	
	public CyResult removeStructureHistory(Integer id);
	
	public CyResult loadStructureHistoryWarByIds(String ids);
	
	public CyResult updateStructureHistory(StructureHistory history);
	
	public CyResult loadBoardcard(Integer pageNumber);
	
	public CyResult loadBoardcardHistory(Integer mid);
	
	public CyResult addBoardcardHistory(BoardcardHistory sh);
	
	public CyResult removeBoardcardHistory(Integer id);
	
	public CyResult loadBoardcardHistoryWarByIds(String ids);
	
	public CyResult updateBoardcardHistory(BoardcardHistory history);
	
	public CyResult loadPrecious(Integer pageNumber);
	
	public CyResult loadPreciousHistory(Integer mid);
	
	public CyResult addPreciousHistory(PreciousHistory sh);
	
	public CyResult removePreciousHistory(Integer id);
	
	public CyResult loadPreciousHistoryWarByIds(String ids);
	
	public CyResult updatePreciousHistory(PreciousHistory history);
	
	public CyResult loadCostList(CostList cost);
	
	public CyResult deleteCostList(Integer id);
	
	public CyResult loadCostListDemandList(DemandList deman);
	
	public CyResult placeCostListOrder(Integer bid, Double demandNumber);
	
	public CyResult searchCostList(String costOrders);
	
	public CyResult placeMinNumberCostListOrder(Integer bid);
	
	public CyResult loadAccessory(Integer pageNumber);
	
	public CyResult loadAccessoryHistory(Integer mid);
	
	public CyResult addAccessoryHistory(AccessoryHistory sh);
	
	public CyResult removeAccessoryHistory(Integer id);
	
	public CyResult loadAccessoryWarByIds(String ids);
	
	public CyResult updateAccessoryHistory(AccessoryHistory history);
	
	public CyResult loadDumpster(Integer pageNumber);
	
	public CyResult loadDumpsterHistory(Integer mid);
	
	public CyResult addDumpsterHistory(DumpsterHistory sh);
	
	public CyResult removeDumpsterHistory(Integer id);
	
	public CyResult loadDumpsterWarByIds(String ids);
	
	public CyResult updateDumpsterHistory(DumpsterHistory history);
	
	public CyResult loadCodingList(CodingList codine);
	
	public CyResult addCodingList(CodingList codingList);
	
	public CyResult updateCodingList(CodingList codingList);
	
	public CyResult deleteCodingList(Integer id);
	
	public CyResult searchCodingList(String number);
	
	public CyResult loadMakerList(MakerList maker);
	
	public CyResult addMakerList(HttpServletRequest request, MakerList makerList) throws Exception;
	
	public CyResult updateMakerList(HttpServletRequest request, MakerList makerList) throws Exception;
	
	public CyResult loadMaker(Integer id);
	
	public CyResult deleteMakerList(Integer id);
	
	public CyResult searchMakerList(String makerName);
	
	public CyResult loadClientList(Integer page, Integer limit);
	
	public CyResult addClientList(HttpServletRequest request, ClientList clientList) throws Exception ;
	
	public CyResult updateClientList(HttpServletRequest request, ClientList clientList) throws Exception;
	
	public CyResult deleteClientList(Integer id);
	
	public CyResult loadClientListId(Integer id);
	
	public CyResult searchClientList(String clientName);
	
	public CyResult loadAllClientList();
	
	public CyResult searchWarehouse(String pn);
	
	public CyResult searchProduct(String pn);
	
	public CyResult placeMaterialManage(Integer id, Double demandNumber, String site, String deliveryTime, Integer sort);
	
	public CyResult loadRequestList(RequestList reqDemo);
	
	public  CyResult addRequestList(RequestList requestList);
	
	public CyResult updateRequestList(RequestList requestList);
	
	public CyResult deleteRequestList(Integer id);
	
	public CyResult loadCompanyList(CompanyList company);
	
	
	
	public CyResult loadAllMakerIdName();
	
	public CyResult loadAllCompanyIdName();
	
	public CyResult loadAllRequestListId(RequestList req);
	
	public CyResult updateCompanyList(HttpServletRequest request, CompanyList companyList) throws Exception;
	
	public CyResult loadRequestListId(Integer id);
	
	public CyResult purchasePrint(Integer id1, Integer id2, Integer id3, Integer thisId);
	
	public CyResult searchCompanyListId(Integer id);
	
	public CyResult deleteCompany(Integer id);
	
	public CyResult searchCompany(String companyName);
	
	public CyResult placeMachiningManage(Integer id, Double demandNumber, String site, String deliveryTime, Integer sort, Integer technology);
	
	public CyResult addDemandList(Integer id, Integer mid, Double demandNumber, String deliveryTime);

	public CyResult loadOrdersByMid(Integer mid);

	public CyResult addCompanyList(HttpServletRequest request, CompanyList companyList);

	public CyResult loadReportPriceList(ReportPrice rp);

	public CyResult pricePlaceOrder(Integer bid, Double demandNumber, String site, String deliveryTime, Integer sort);

	public CyResult priceMaterialManage(Integer id, Double demandNumber, String site, String deliveryTime,Integer sort);

	public CyResult loadPriceReportDemand(ReportPrice reportPrice);

	public CyResult delreportPrice(Integer id);

	public CyResult dayingReportPrice(Integer id1, Integer id2, Integer id3,
			Integer thisId);

	public CyResult loadInstruct(Instruct instruct);

	public CyResult giveAccess(String idString, Integer instruct_pzState,String storeState);

	public CyResult loadboxFileList(FileUrl fileUrl);

	public CyResult addShortage(Shortage shortage);

	public CyResult loadShortage(Shortage shortage);

	public CyResult loadShortagelist(ShortageList shortageList);

	public CyResult placeShortage(String pname,String pn,Integer sid, Integer shortageNumber,
			String site, String shortageCode, Integer sort);

	public CyResult deleteShortage(Integer sid);

	public CyResult addtemoraryManager(ProductHistory ph);

	public CyResult loadtemporaryManager(Warehouse warehouse);

	public CyResult loadErwork(Warehouse warehouse);

	public CyResult loadprecious(Precious precious);
	
	public CyResult updataBomList(BomList bom);
	
	public CyResult getParts(ImportantParts parts);
	
	public CyResult insertParts(ImportantParts parts);
	
	public CyResult updateParts(ImportantParts parts);
	
	public CyResult deleteParts(String id);
	
}
