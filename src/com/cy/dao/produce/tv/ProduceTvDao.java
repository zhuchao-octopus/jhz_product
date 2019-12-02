package com.cy.dao.produce.tv;

import java.io.File;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Update;
import org.springframework.core.annotation.Order;

import com.cy.board.Precious;
import com.cy.domain.list.FileUrl;
import com.cy.domain.produce.tv.AccessoryHistory;
import com.cy.domain.produce.tv.BoardcardHistory;
import com.cy.domain.produce.tv.BomList;
import com.cy.domain.produce.tv.BomMaterials;
import com.cy.domain.produce.tv.BomOrderList;
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
import com.cy.domain.produce.tv.ReportDemandList;
import com.cy.domain.produce.tv.ReportPrice;
import com.cy.domain.produce.tv.RequestList;
import com.cy.domain.produce.tv.Shortage;
import com.cy.domain.produce.tv.ShortageList;
import com.cy.domain.produce.tv.StructureHistory;
import com.cy.domain.produce.tv.TvProduce;
import com.cy.domain.produce.tv.Warehouse;
import com.cy.domain.produce.tv.WarehouseHistory;
import com.cy.domain.user.ErpUser;
import com.cy.utils.CyResult;
import com.fasterxml.jackson.core.Versioned;

public interface ProduceTvDao {
	
	int loadAllTvproduces();
	
	List<TvProduce> loadTvproduces(int pageNumber);

	TvProduce loadAllTvproduceById(Integer tv_test_id);
	
	int loadAllMM(MaterialManage mm);
	
	List<MaterialManage> loadMM(int pageNumber);
	
	List<MaterialManage> loadzhuMM(int pageNumber);
	
	List<MaterialManage> loadcaiMM();
	
	List<MaterialManage> searchMM(MaterialManage mm);
	
	MaterialManage searchMMpn(String pn);
	
	int insertMM(MaterialManage mm);
	
	int updateMM(MaterialManage mm);
	
	int removeMM(int id);
	
	List<WarehouseHistory> loadwh(int mid);
	
	String outwh(int mid);
	
	String putwh(int mid);
	
    String outwhss(Map<String, Object> map);
	
	String putwhss(Map<String, Object> map);
	
	int deletewh(int id);
	
	int insertwh(WarehouseHistory wh);
	
	MaterialManage searchMate(int mid);
	
	List<MaterialManage> loadsearchmm(Map<String, Object> map);
	
	int loadcpMM(Warehouse warehouse);
	
	List<MaterialManage> loadprodMM(Warehouse warehouse);
	
    String outph(int mid);
	
	String putph(int mid);
	
    String outphss(Map<String, Object> map);
	
	String putphss(Map<String, Object> map);

	WarehouseHistory findWhById(Integer id);
	
	List<ProductHistory> loadph(ProductHistory history);
	
	int insertph(ProductHistory ph);
	
	int deleteph(int id);

	ProductHistory findProductHistoryById(int id);

	TvProduce findTvBySn(TvProduce tv);

	TvProduce findTvByMac(TvProduce tv);
	
	int findAllOrders(OrderList order);
	
	int findAllEndOrders();
	
	List<OrderList>loadOrders(OrderList order);
	
	List<OrderList>loadEndOrders(OrderList order);
	
	int addOrder(OrderList orderList);
	
	List<OrderList> findOrder(OrderList orderList);
	
	List<OrderList> findOrderstate(Integer storeState);
	
	String outwhOrderNum(Map map);
	
	String outphOrderNum(Map map);
	
	String outstrhOrderNum(Map map);
	
	String outbkhOrderNum(Map map);
	
	String outgzhOrderNum(Map map);
	
	String outflhOrderNum(Map map);
	
	String outzfhOrderNum(Map map);
	
	OrderList findOrderid(Integer oid);
	
	List<OrderList> findOrders(String orderNumber);
	
	String outOrderNumW(OrderList order);
	
	String outOrderNumH(OrderList order);

	int updateOrder(OrderList order);

	int updatePdHistory(ProductHistory history);
	
	int updateStrHistory(StructureHistory history);
	
	int updatebkHistory(BoardcardHistory history);
	
	int updategzHistory(PreciousHistory history);
	
	int updateflHistory(AccessoryHistory history);
	
	int updatezfHistory(DumpsterHistory history);

	int updateWhHistory(WarehouseHistory history);

	void deleteOrderById(Integer id);
	
	int addBomList(BomList bom);
	
	int delBomList(Integer id);
	
	BomList loadBom(Integer id);
	
	BomList loadMidBom(Integer mid);
	
	void delBomMate(Integer bid);
	
	List<BomList> loadBomList(BomList b);
	
	int loadAllBomList(BomList bomss);
	
	List<BomMaterials> loadBM(Integer integer);
	
	int addBomMate(BomMaterials bm);
	
	int deleBomMate(Integer id);
	
	int updateBomMate(BomMaterials bm);
	
	List<BomList> searchBom(String pn);
	
	List<BomOrderList> loadbol(Integer pageNumber); 
	  
	List<MaterialManage> searchMateById(Map map);
	
	Integer searchOid(Map<String, Object> map);
	
	ProductHistory phid(Integer id);
	
	WarehouseHistory whid(Integer id);
	
	List<BomOrderList> sreachBol(Map<String, Object> map); 
	
	String outwhOrderNumNoOrder(Map map);
	
	String outphOrderNumNoOrder(Map map);
	
	String outstrhOrderNumNoOrder(Map map);
	
	String outbkhOrderNumNoOrder(Map map);
	
	String outgzhOrderNumNoOrder(Map map);
	
	String outflhOrderNumNoOrder(Map map);
	
	String outzfhOrderNumNoOrder(Map map);
	
	List<PriceList> loadPrices(PriceList priceList);
	
	int loadAllPrices(PriceList priceList);
	
	int addPrice(PriceList priceList);
	
	int deletePrice(Integer id);
	
	int updatePrice(PriceList priceList);
	
	PriceList sreachPrice(Integer mid);
	
	String searchPO(String time);
	
	int addDemandList(DemandList demand);
	
	int addPurchaseList(PurchaseList purchase);
	
	int addCostList(CostList purchase);
	
	double sumPrice(String purchaseOrders);
	
	List<PurchaseList> loadPurchaseList(PurchaseList purchase);
	
	List<PurchaseList> loadEndPurchaseList(PurchaseList purchase);
	
	int loadAllPurchaseList(PurchaseList purchase);
	
	int loadAllEndPurchaseList(PurchaseList purchase);
	
	int deletepl(Integer id);
	
	PurchaseList loadPl(Integer id);
	
	ReportPrice loadReportPriceDetails(Integer id);
	
	void deleteDemandList(String purchaseOrders);
	
	List<DemandList> loadDemandLists(String string);
	
	int updateDemand(Map<String, Object> map);
	
	int deleteDemand(Integer id);
	
	int updatePs(PurchaseStuat ps);
	
	PurchaseList searchPP(String purchaseOrders);
	
	int updateOrderStuat(Map<String, Object> map);
	
	List<MaterialManage> loadjgMM();
	
	List<MaterialManage> loadbkMM();
	
	List<MaterialManage> loadgzMM();
	
	List<MaterialManage> loadflMM();
	
	List<MaterialManage> loadzfMM();
	
	List<MaterialManage> loadpagejgMM(int pageNumber);
	
	List<MaterialManage> loadpagebkMM(int pageNumber);
	
	List<MaterialManage> loadpagegzMM(int pageNumber);
	
	List<MaterialManage> loadpageflMM(int pageNumber);
	
	List<MaterialManage> loadpagezfMM(int pageNumber);
	
    String outstrh(int mid);
	
	String putstrh(int mid);
	
    String outbkh(int mid);
	
	String putbkh(int mid);
	
    String outgzh(int mid);
	
	String putgzh(int mid);
	
    String outflh(int mid);
	
	String putflh(int mid);
	
    String outzfh(int mid);
	
	String putzfh(int mid);
	
	List<StructureHistory> loadstrh(Integer mid);
	
	List<BoardcardHistory> loadbkh(Integer mid);
	
	List<PreciousHistory> loadgzh(Integer mid);
	
	List<AccessoryHistory> loadflh(Integer mid);
	
	List<DumpsterHistory> loadzfh(Integer mid);
	
	int insertstrh(StructureHistory sh);
	
	int insertbkh(BoardcardHistory sh);
	
	int insertgzh(PreciousHistory sh);
	
	int insertflh(AccessoryHistory sh);
	
	int insertzfh(DumpsterHistory sh);
	
	int deletestrh(int id);
	
	int deletebkh(int id);
	
	int deletegzh(int id);
	
	int deleteflh(int id);
	
	int deletezfh(int id);
	
	StructureHistory findStructureHistoryById(int id);
	
	BoardcardHistory findBoardcardHistoryById(int id);
	
	PreciousHistory findPreciousHistoryById(int id);
	
	AccessoryHistory findAccessoryHistoryById(int id);
	
	DumpsterHistory findDumpsterHistoryById(int id);
	
	int loadAllCostList(CostList cost);
	
	List<CostList> loadCostList(CostList cost);
	
	CostList loadCost(Integer id);
	
	int deleteCost(int id);
	
	String searchCO(String time);
	
	CostList searchCost(String costOrders);
	
	int loadAllCodingList(CodingList code);
	
	List<CodingList> loadCodingList(CodingList code);
	
	List<CodingList> searchNameCoding(String pname);
	
	List<CodingList> codingNumberCoding(String Number);
	
	int addCodingList(CodingList codingList);
	
	int updateCodingList(CodingList codingList);
	
	int delCodingList(Integer id);
	
	int allMakerList(MakerList maker);
	
	int allclientList();
	
	List<MakerList> loadMakerList(MakerList maker);
	
	List<ClientList> loadClientList(ClientList cl);
	
	List<MakerList> loadMakerListNo(String makerNo);
	
	List<ClientList> loadClientListNo(String makerNo);
	
	List<MakerList> loadMakerListName(String makerName);
	
	List<ClientList> loadClientListName(String clientName);
	
	int addMakerList(MakerList makerList);
	
	int addClientList(ClientList clientList);
	
	int updateMakerList(MakerList makerList);
	
	int updateClientList(ClientList clientList);
	
	int deleteMakerList(Integer id);
	
	int deleteClientList(Integer id);
	
	List<MakerList> searchMakerName(String makerName);
	
	List<ClientList> searchClientName(String makerName);
	
	void deletePriceMM(Integer id);
	
	String maxNo(Map<String, Object> map);
	
	int updateProductHistoryJobNo(Map<String, Object> map);
	
	List<ClientList> loadAllClientList();
	
	List<OrderList> loadchuhuodang(Integer id);
	
	int searchwarehouseHistoryId(String ptime);
	
	int searchproductHistoryId(String ptime);
	
	MaterialManage searchwh(String pn);
	
	MaterialManage searchph(String pn);
	
	MakerList searchMaker(Integer id);
	
	int loadAllCountRequestList();
	
	List<RequestList> loadReqestList(RequestList reqDemo);
	
	int addRequestList(RequestList requestList);
	
	int updateRequestList(RequestList requestList);
	
	int deleteRequestList(Integer id);
	
	int allCompanyList(CompanyList company);
	
	List<CompanyList> loadCompanyList(CompanyList company);
	
	List<CompanyList> loadCompanyListNo(String companyNo);
	
	List<CompanyList> loadCompanyListName(String companyName);
	
	int addCompanyList(CompanyList companyList);
	
	List<MakerList> allMakerIdName();
	
	List<CompanyList> allCompanyIdName();
	
	List<RequestList> allRequestId(RequestList req);
	
	int updateCompanyList(CompanyList companyList);
	
	RequestList loadRequestId(Integer id);
	
	CompanyList loadCompanyId(Integer id);
	
	RequestList loadRequestNumber(String serialNumber);
	
	int deleteCompanyList(Integer id);
	
	List<CompanyList> searchCompanyName(String companyName);
	
	void deletePurchaseListBid(Integer id);
	
	void deleteCostListBid(Integer id);
	
	DemandList searchDemandListId(Integer id);
	
	int sumDemandList(String purchaseOrders);
	
	void updateSumPurchaseList(Map<String, Object> map);
	
	ClientList loadClientId(Integer id);

	List<MaterialManage> loadfzMM(MaterialManage mm);
	
	int loadphCount(ProductHistory history);

	List<String> loadOrdersByMid(Integer mid);

	int loadDemandListsCount(PurchaseList purchaseList);

	List<DemandList> loadDemandListsLimit(PurchaseList purchaseList);

	int loadBMCount(BomMaterials bomMaterials);

	List<BomMaterials> loadBMSS(BomMaterials bomMaterials);

	List<DemandList> loadDemandListssss(DemandList deman);

	int loadDemandListsCountssss(DemandList deman);

	int loadAllReportPriceListCount(ReportPrice rp);

	List<ReportPrice> loadReportPriceList(ReportPrice rp);

	String searchPOJO(String time2);

	int addPriceDemandList(ReportDemandList demand);

	double sumReportPrice(String purchaseOrders);

	int addReportList(ReportPrice purchase);

	List<ReportDemandList> loadPriceReportDemand(ReportPrice reportPrice);
	List<ReportDemandList> dyPriceReportDemand(ReportPrice reportPrice);

	int reportDemandListCount(ReportPrice reportPrice);

	int delreportPrice(Integer id);

	int countLoadInstruct(Instruct instruct);

	List<Instruct> LoadInstruct(Instruct instruct);

	int insertInstruct(Instruct instruct);

	ErpUser findUserById(int userId);

	List<Instruct> loadInstructByIds(String[] idArr);

	int addHistory(List<ProductHistory> hisList);

	int updataPzState(Map<String, Object> map);
	
	public List<FileUrl> loadboxFileList(FileUrl fileUrl);
	
	public  int loadboxFileListCount(FileUrl fileUrl);

	List<BomMaterials> loadCKBM(String pn);

	List<ProductHistory> loadckHistory(Integer mid);

	void addkcnumber(ProductHistory ph);

	void addShortage(Shortage shortage);

	int countLoadShortage(Shortage shortage);

	List<Shortage> loadShortage(Shortage shortage);

	List<BomList> findcksl(BomMaterials bm);

	List<Shortage> findQLSid(String shortageCode);

	int addShortageList(List<ShortageList> list);

	int countLoadShortagelist(ShortageList shortageList);

	List<ShortageList> loadShortagelist(ShortageList shortageList);

	double sumQLPrice(String pn);

	void addQLXD(PurchaseList purchase);

	Shortage findQLbXD(Integer sid);

	void updateQLXD(Integer sid);

	PriceList PriceByPn(String pcode);

	int addQLPurchaseList(List<DemandList> xDlist);

	void deleteShortage(String qsid);

	void delCK(String qsid);

	void deleteShortageList(Integer sid);

	MaterialManage findByPn(String pcode);

	String outzfph(Integer id);

	String putzfph(Integer id);

	String outfxph(Integer id);

	String putfxph(Integer id);

	List<Precious> loadprecious(Precious precious);

	int countloadprecious(Precious precious);
	
	int updataBomList(BomList bom);

	List<ImportantParts> getParts(ImportantParts inportant);
	
	ImportantParts getPartsById(Integer id);
	
	int countParts(ImportantParts inportant);
	
	Integer insertInportant(ImportantParts inportant);

	Integer updateInportant(ImportantParts inportant);
	
	void deleteInportant(Integer id);

}
