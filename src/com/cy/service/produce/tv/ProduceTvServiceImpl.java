package com.cy.service.produce.tv;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.cy.board.Batch;
import com.cy.board.Precious;
import com.cy.dao.produce.tv.ProduceTvDao;
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
import com.cy.domain.produce.tv.OweMaterials;
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
import com.cy.utils.CyUtil;

@Service
public class ProduceTvServiceImpl implements ProduceTvService {

	@Resource
	private ProduceTvDao tvdao;

	@Override
	public CyResult loadProduce(Integer pageNumber) {
		CyResult result = new CyResult();
		int tvAlls = tvdao.loadAllTvproduces();
		if (pageNumber == null) {
			pageNumber = 0;
		} else {
			pageNumber = (pageNumber - 1) * 20;
		}
		List<TvProduce> tvProduces = tvdao.loadTvproduces(pageNumber);
		result.setData(tvAlls);
		result.setData1(tvProduces);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult loadProduceTvById(Integer tv_test_id) {
		CyResult result = new CyResult();
		TvProduce produce = tvdao.loadAllTvproduceById(tv_test_id);
		result.setData(produce);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult loadMaterial(Integer page, Integer limit, MaterialManage mm) {
		CyResult result = new CyResult();
		int count = tvdao.loadAllMM(mm);
		if (page == null) {
			page = 1;
		}
		int pageSize = (page - 1) * limit;

		mm.setPageSize(pageSize);
		mm.setLimit(limit);
		List<MaterialManage> mms = tvdao.loadfzMM(mm);
		result.setState(1);
		result.setCount(count);
		result.setData(mms);
		result.setMsg("加载成功");
		return result;
	}

	@Override
	public CyResult addMaterial(MaterialManage mm) {
		CyResult result = new CyResult();
		if (mm == null) {
			result.setState(1);
			return result;
		}
		List<MaterialManage> man = tvdao.searchMM(mm);
		if (man.size() > 0) {
			result.setMsg("已有该料号");
			result.setState(2);
			return result;
		}
		int aaa = tvdao.insertMM(mm);
		System.err.println(mm.getMaterialName());
		if (aaa == 1) {
			result.setMsg("增加成功");
			result.setState(0);
			return result;
		}
		result.setMsg("增加失败");
		result.setState(4);
		return result;
	}

	@Override
	public CyResult updateMaterial(MaterialManage mm) {
		CyResult result = new CyResult();
		if (mm == null) {
			result.setState(1);
			return result;
		}
		List<MaterialManage> man = tvdao.searchMM(mm);
		if (man.size() > 1) {
			result.setMsg("已有该料号");
			result.setState(3);
			return result;
		}
		int upmm = tvdao.updateMM(mm);
		if (upmm == 1) {
			result.setMsg("修改成功");
			result.setState(0);
			return result;
		}
		result.setMsg("修改失败");
		result.setState(2);
		return result;
	}

	@Override
	public CyResult removeMaterial(Integer id) {
		CyResult result = new CyResult();
		int qwe = tvdao.removeMM(id);
		if (qwe > 0) {
			tvdao.deletePriceMM(id);
			result.setMsg("删除成功");
			result.setState(0);
			return result;
		}
		result.setMsg("删除失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult loadWarehouse(Integer pageNumber) {
		CyResult result = new CyResult();
		List<MaterialManage> loadmm = tvdao.loadcaiMM();
		if (pageNumber == null) {
			pageNumber = 0;
		} else {
			pageNumber = (pageNumber - 1) * 15;
		}
		List<MaterialManage> mms = tvdao.loadMM(pageNumber);
		List<Warehouse> whList = new ArrayList<Warehouse>();
		for (MaterialManage mate : mms) {
			Warehouse war = new Warehouse();
			String outNumString = tvdao.outwh(mate.getId());// 出库数量
			String putNumString = tvdao.putwh(mate.getId());// 入库数量
			if (outNumString == null) {
				outNumString = "0";
			}
			if (putNumString == null) {
				putNumString = "0";
			}
			int outNum = Integer.parseInt(outNumString);
			int putNum = Integer.parseInt(putNumString);
			int yushu = putNum - outNum;// 余数
			war.setId(mate.getId());
			war.setResidue(yushu);
			war.setProductName(mate.getMaterialName());
			war.setPn(mate.getPn());
			war.setWeight(mate.getWeight());
			war.setSpecification(mate.getMexplain());
			war.setOutNumber(outNum);
			war.setPutNumber(putNum);
			whList.add(war);
		}
		if (mms.size() < 1) {
			result.setMsg("暂无数据");
			result.setState(3);
			return result;
		}
		result.setState(1);
		result.setData(loadmm.size());
		result.setData1(whList);
		result.setMsg("加载成功");
		return result;
	}

	@Override
	public CyResult loadWareHistory(Integer mid) {
		CyResult result = new CyResult();
		List<WarehouseHistory> whList = tvdao.loadwh(mid);
		MaterialManage loadmm = tvdao.searchMate(mid);
		if (loadmm == null) {
			result.setMsg("无材料数据");
			result.setState(2);
			return result;
		}
		if (whList.size() < 1) {
			result.setState(1);
			result.setMsg("暂无出入库记录");
			return result;
		}
		List<WarehouseHistory> wareList = new ArrayList<>();
		for (WarehouseHistory wgod : whList) {
			OrderList ordersss = tvdao.findOrderid(wgod.getOid());
			System.err.println(ordersss);
			if (ordersss != null) {
				wgod.setOrderNumber(ordersss.getOrderNumber());
			} else {
				wgod.setOrderNumber("");
			}

			wgod.setMaterialName(loadmm.getMaterialName());

			wgod.setPn(loadmm.getPn());
			wareList.add(wgod);
		}
		result.setData(wareList);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult removeWareHistory(Integer id) {
		CyResult result = new CyResult();
		int qaz = tvdao.deletewh(id);
		if (qaz > 0) {
			result.setMsg("删除成功");
			result.setState(0);
			return result;
		}
		result.setMsg("删除失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult addWareHistory(WarehouseHistory wh) {
		CyResult result = new CyResult();
		if (wh == null) {
			result.setMsg("不能传空对象");
			result.setState(1);
			return result;
		}
		Map<String, Object> oMap = new HashMap<>();
		oMap.put("orderNumber", wh.getOrderNumber());
		oMap.put("mid", wh.getMid());
		Integer oid = tvdao.searchOid(oMap);
		wh.setOid(oid);
		String time = CyUtil.getTime();
		wh.setPtime(time);
		int asd = tvdao.insertwh(wh);
		if (asd == 1) {
			result.setMsg("添加成功");
			result.setState(0);
			return result;
		}
		result.setMsg("添加失败");
		result.setState(2);
		return result;
	}

	@Override
	public CyResult searchloadMate(String pn, String storeState) {

		CyResult result = new CyResult();
		Map<String, Object> sp = new HashMap<>();
		sp.put("pn", pn);
		sp.put("storeState", storeState);
		System.out.println(sp);
		List<MaterialManage> mateList = tvdao.loadsearchmm(sp);

		if (mateList.size() > 0) {
			result.setData(mateList);
			result.setMsg("加载成功");
			result.setState(0);
			return result;
		}
		result.setMsg("暂无数据");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult loadHistoryByIds(String ids) {
		CyResult result = new CyResult();
		if (ids == null) {
			result.setState(0);
			result.setMsg("ids不能为空!");
			return result;
		}
		List<WarehouseHistory> list = new ArrayList<WarehouseHistory>();
		String[] idstr = ids.split(",");
		Map<String, Object> map = new HashMap<String, Object>();
		for (String id : idstr) {
			WarehouseHistory wh = tvdao.findWhById(Integer.parseInt(id));
			Integer oid = wh.getOid();
			OrderList order = tvdao.findOrderid(oid);
			if (order != null) {
				wh.setNumbers(order.getNumbers());
				wh.setOrderNumber(order.getOrderNumber());
				map.put("oid", order.getId());
			} else {
				wh.setNumbers(null);
				wh.setOrderNumber(null);
				map.put("oid", null);
			}
			map.put("id", wh.getId());
			String outsumString = tvdao.outwhOrderNum(map);
			if (outsumString == null) {
				outsumString = "0";
			}
			int outsum = Integer.parseInt(outsumString);
			Integer yushu = 0;
			if (order != null) {
				yushu = order.getNumbers() - outsum;
			} else {
				yushu = null;
			}
			wh.setOutNumber(outsum);
			wh.setResidue(yushu);
			list.add(wh);
		}
		result.setData(list);
		result.setMsg("加载成功");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult loadProducts(Warehouse warehouse) {
		CyResult result = new CyResult();
		if (warehouse.getPage() == null) {
			warehouse.setPage(1);
		}
		warehouse.setPageSize((warehouse.getPage() - 1) * warehouse.getLimit());
		int count = tvdao.loadcpMM(warehouse);

		List<MaterialManage> mms = tvdao.loadprodMM(warehouse);
		List<Warehouse> whList = new ArrayList<Warehouse>();
		for (MaterialManage mate : mms) {
			Warehouse war = new Warehouse();
			String outNumString = tvdao.outph(mate.getId());// 出库数量
			String putNumString = tvdao.putph(mate.getId());// 入库数量
			if (outNumString == null) {
				outNumString = "0";
			}
			if (putNumString == null) {
				putNumString = "0";
			}
			int outNum = Integer.parseInt(outNumString);
			int putNum = Integer.parseInt(putNumString);
			int yushu = putNum - outNum;// 余数
			war.setId(mate.getId());
			war.setResidue(yushu);
			war.setProductName(mate.getMaterialName());
			war.setSoftwareAddress(mate.getSoftwareAddress());
			war.setSoftwarePasssword(mate.getDownPassword());
			war.setPn(mate.getPn());
			war.setWeight(mate.getWeight());
			war.setSpecification(mate.getMexplain());
			war.setOutNumber(outNum);
			war.setPutNumber(putNum);
			war.setBatch(mate.getBatch());
			war.setSupplier(mate.getSupplier());
			war.setInDate(mate.getInDate());
			war.setIncomingQuantity(mate.getIncomingQuantity());
			war.setBindingQuantity(mate.getBindingQuantity());
			whList.add(war);
		}

		result.setState(1);
		result.setCode(0);
		result.setCount(count);
		result.setData(whList);
		result.setMsg("加载成功");
		return result;
	}

	@Override
	public CyResult loadProductHistory(ProductHistory history) {
		CyResult result = new CyResult();
		history.setPageSize((history.getPage() - 1) * history.getLimit());
		List<ProductHistory> whList = tvdao.loadph(history);
		MaterialManage loadmm = tvdao.searchMate(history.getMid());
		/*
		 * if(loadmm==null){ result.setMsg("无材料数据"); result.setState(2); return
		 * result; }
		 */
		/*
		 * if(whList.size()<1){ result.setState(1); result.setMsg("暂无出入库记录");
		 * return result; }
		 */
		List<ProductHistory> wareList = new ArrayList<>();
		for (ProductHistory wgod : whList) {
			OrderList ordersss = tvdao.findOrderid(wgod.getOid());
			if (ordersss != null) {
				wgod.setOrderNumber(ordersss.getOrderNumber());
			} else {
				wgod.setOrderNumber("");
			}
			wgod.setMaterialName(loadmm.getMaterialName());
			wgod.setPn(loadmm.getPn());
			wareList.add(wgod);
		}
		result.setData(wareList);
		result.setCode(0);
		result.setCount(tvdao.loadphCount(history));
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult addProductHistory(ProductHistory ph) {
		System.out.println("ph:"+ph);
		CyResult result = new CyResult();
		Map<String, Object> oMap = new HashMap<>();
		oMap.put("orderNumber", ph.getOrderNumber());
		oMap.put("mid", ph.getMid());
		Integer oid = tvdao.searchOid(oMap);
		ph.setOid(oid);
		String time = CyUtil.getTime();
		ph.setPtime(time);
		Instruct instruct=new Instruct();
		instruct.setInstruct_mid(ph.getMid());
		instruct.setInstruct_number(ph.getPnumber());
		instruct.setInstruct_oid(oid);
		instruct.setInstruct_state(ph.getState());
		instruct.setInstruct_pname(ph.getMaterialName());
		instruct.setInstruct_pcode(ph.getPn());
		instruct.setInstruct_order(ph.getOrderNumber());
		instruct.setInstruct_time(CyUtil.getTime2());
		instruct.setInstruct_user(ph.getInstruct_user());
		instruct.setStoreState(ph.getStoreState());
		instruct.setInstructPermision(ph.getInstructPermision());
		instruct.setInstruct_userId(ph.getInstruct_userId());
		instruct.setInstruct_pzState(0);
		if(ph.getInstructPermision()==1){
			try {
				tvdao.insertph(ph);
				result.setMsg("添加成功!当前用户已具备审批权限,数据将直接通过审批!");
				result.setState(0);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("error");
			}
		}else{
			try {
				tvdao.insertInstruct(instruct);
				result.setMsg("添加成功!请等待审批!");
				result.setState(0);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("error");
			}
		}
		
		
		

	}

	@Override
	public CyResult removeProductHistory(Integer id) {
		CyResult result = new CyResult();
		int qaz = tvdao.deleteph(id);
		if (qaz > 0) {
			result.setMsg("删除成功");
			result.setState(0);
			return result;
		}
		result.setMsg("删除失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult loadHistoryWarByIds(String ids, String clientNo) {
		CyResult result = new CyResult();
		if (ids == null) {
			result.setState(0);
			result.setMsg("ids不能为空!");
			return result;
		}
		List<ProductHistory> list = new ArrayList<ProductHistory>();
		String[] idstr = ids.split(",");

		for (String id : idstr) {
			ProductHistory wh = tvdao.findProductHistoryById(Integer.parseInt(id));
			Map<String, Object> maxMap = new HashMap<>();
			maxMap.put("clientNo", clientNo);
			maxMap.put("times", CyUtil.getTime3());
			String maxNo = tvdao.maxNo(maxMap);
			if (maxNo == null) {
				String jobNo = clientNo + CyUtil.getTime3() + "001";
				Map<String, Object> upMap = new HashMap<>();
				upMap.put("jobNo", jobNo);
				upMap.put("id", wh.getId());
				int count = tvdao.updateProductHistoryJobNo(upMap);
				if (count != 1) {
					result.setMsg("添加出货单号失败");
					result.setState(4);
					return result;
				}
				wh.setJobNo(jobNo);
			} else {
				int ops = Integer.parseInt(maxNo) + 1;
				DecimalFormat df = new DecimalFormat("000");
				String jobNo = clientNo + CyUtil.getTime3() + df.format(ops);// 生成出货单号
				Map<String, Object> upMap = new HashMap<>();
				upMap.put("jobNo", jobNo);
				upMap.put("id", wh.getId());
				int count = tvdao.updateProductHistoryJobNo(upMap);
				if (count != 1) {
					result.setMsg("添加出货单号失败");
					result.setState(4);
					return result;
				}
				wh.setJobNo(jobNo);
			}
			Integer oid = wh.getOid();
			OrderList order = tvdao.findOrderid(oid);
			if (order == null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", wh.getId());
				wh.setNumbers(null);
				wh.setOrderNumber("");
				wh.setOutNumber(Integer.parseInt(tvdao.outphOrderNumNoOrder(map)));
				wh.setResidue(null);
			} else {
				Map<String, Object> map = new HashMap<String, Object>();
				wh.setNumbers(order.getNumbers());
				wh.setOrderNumber(order.getOrderNumber());
				map.put("oid", order.getId());
				map.put("id", wh.getId());
				String outsumString = tvdao.outphOrderNum(map);
				System.out.println(outsumString);
				if (outsumString == null) {
					wh.setOutNumber(null);
					wh.setResidue(order.getNumbers());
				} else {
					int outsum = Integer.parseInt(outsumString);
					int yushu = order.getNumbers() - outsum;
					wh.setOutNumber(outsum);
					wh.setResidue(yushu);
				}

			}

			list.add(wh);
		}
		result.setData(list);
		result.setMsg("加载成功");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult loadOrderList(OrderList order) {
		CyResult result = new CyResult();
		int allOrder = tvdao.findAllOrders(order);
		order.setPageSize((order.getPage() - 1) * order.getLimit());
		List<OrderList> loadOrders = tvdao.loadOrders(order);
		if (loadOrders.size() < 1) {
			result.setMsg("暂无数据");
			result.setState(1);
			return result;
		}

		List<OrderList> lol = new ArrayList<>();

		for (OrderList orderDemo : loadOrders) {
			MaterialManage loadmm = tvdao.searchMate(orderDemo.getMid());
			if(loadmm!=null){
			if ("0".equals(loadmm.getStoreState())) {
				String outsumString = tvdao.outOrderNumW(orderDemo);
				if (outsumString == null) {
					outsumString = "0";
				}
				
				int outsum = Integer.parseInt(outsumString);
				int yushu = orderDemo.getNumbers() - outsum;
				orderDemo.setResidue(yushu);
				orderDemo.setOutNumber(outsum);
				if (orderDemo.getPrice() != null) {
					orderDemo.setMoney(outsum * orderDemo.getPrice());
				} else {
					orderDemo.setMoney(null);
				}

			}
			if ("1".equals(loadmm.getStoreState())) {
				String outsumString = tvdao.outOrderNumH(orderDemo);
				if (outsumString == null) {
					outsumString = "0";
				}
				int outsum = Integer.parseInt(outsumString);
				if(orderDemo.getNumbers()==null){
					orderDemo.setNumbers(0);
				}
				int yushu = orderDemo.getNumbers() - outsum;
				orderDemo.setResidue(yushu);
				orderDemo.setOutNumber(outsum);
				if (orderDemo.getPrice() != null) {
					orderDemo.setMoney(outsum * orderDemo.getPrice());
				} else {
					orderDemo.setMoney(null);
				}
				
			}
			}
			
			if (loadmm != null) {
				orderDemo.setProductName(loadmm.getMaterialName());
				orderDemo.setPn(loadmm.getPn());
				orderDemo.setStoreState(Integer.parseInt(loadmm.getStoreState()));
			} else {
				orderDemo.setProductName("");
				orderDemo.setPn("");
			}
            
			lol.add(orderDemo);
		}

		result.setCount(allOrder);
		result.setData(lol);
		result.setCode(0);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult addOrderList(OrderList orderList) {
		CyResult result = new CyResult();
		if (orderList == null) {
			result.setMsg("不能传空对象");
			result.setState(1);
			return result;
		}
		List<OrderList> orderOne = tvdao.findOrder(orderList);
		if (orderOne.size() > 0) {
			result.setMsg("已有该订单号");
			result.setState(2);
			return result;
		}
		// if(orderList.getNumbers()!=null && orderList.getPrice()!=null){
		// orderList.setMoney(orderList.getPrice() * orderList.getOutNumber());
		// }
		orderList.setCreateTime(CyUtil.getTime());
		orderList.setStuat(1);
		int asd = tvdao.addOrder(orderList);
		if (asd == 1) {
			result.setMsg("增加成功");
			result.setState(0);
			return result;
		}
		result.setMsg("增加失败");
		result.setState(3);
		return result;
	}

	@Override
	public CyResult loadOrderState(Integer storeState) {
		CyResult result = new CyResult();
		List<OrderList> old = tvdao.findOrderstate(storeState);

		if (old.size() < 1) {
			result.setMsg("暂无订单数据");
			result.setState(1);
			return result;
		}
		System.out.println(old);
		result.setMsg("加载成功");
		result.setState(0);
		result.setData(old);
		return result;
	}

	@Override
	public CyResult loadduizhang(String idstrs) {
		CyResult result = new CyResult();
		if (idstrs == "" || "null".equals(idstrs)) {
			result.setMsg("空字符串");
			result.setState(1);
			return result;
		}
		String[] str = idstrs.split(",");
		List<OrderList> lol = new ArrayList<>();
		for (int i = 0; i < str.length; i++) {
			OrderList order = tvdao.findOrderid(Integer.parseInt(str[i]));
			if (order == null) {
				result.setMsg("暂无数据");
				result.setState(1);
				return result;
			}

			if (order.getStoreState() == 0) {
				List<OrderList> chuList = tvdao.loadchuhuodang(order.getId());
				for (OrderList orders : chuList) {
					MaterialManage loadmm = tvdao.searchMate(orders.getMid());
					if (loadmm != null) {
						orders.setProductName(loadmm.getMaterialName());
						orders.setPn(loadmm.getPn());
					} else {
						orders.setProductName("");
						orders.setPn("");
					}
					lol.add(orders);
				}

			}
			if (order.getStoreState() == 1) {
				List<OrderList> chuList = tvdao.loadchuhuodang(order.getId());
				for (OrderList orders : chuList) {
					MaterialManage loadmm = tvdao.searchMate(orders.getMid());
					if (loadmm != null) {
						orders.setProductName(loadmm.getMaterialName());
						orders.setPn(loadmm.getPn());
					} else {
						orders.setProductName("");
						orders.setPn("");
					}
					List<ProductHistory> phListss = orders.getPhList();
					for (ProductHistory phs : phListss) {
						int phid = tvdao.searchproductHistoryId(phs.getPtime());
						Map<String, Object> map = new HashMap<>();
						map.put("oid", orders.getId());
						map.put("id", phid);
						String outsumString = tvdao.outphOrderNum(map);
						phs.setTotalNumber(Integer.parseInt(outsumString));
						if (orders.getPrice() != null) {
							phs.setAddMoney(String.valueOf(orders.getPrice() * phs.getPnumber()));
						} else {
							phs.setAddMoney("");
						}
					}
					lol.add(orders);
				}

			}

		}
		result.setData(lol);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult editOrder(OrderList order) {
		CyResult result = new CyResult();
		String createTime = CyUtil.getTime();
		order.setCreateTime(createTime);
		List<OrderList> orderOne = tvdao.findOrder(order);
		if (orderOne.size() > 1) {
			result.setMsg("已有该订单号");
			result.setState(2);
			return result;
		}
		try {
			tvdao.updateOrder(order);
			result.setMsg("修改成功!");
			result.setState(0);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("修改失败!");
		}
	}

	@Override
	public CyResult editProductHistoryById(ProductHistory history) {
		CyResult result = new CyResult();
		try {
			MaterialManage mmone = tvdao.searchMMpn(history.getPn());
			Map<String, Object> oMap = new HashMap<>();
			oMap.put("orderNumber", history.getOrderNumber());
			oMap.put("mid", mmone.getId());
			Integer oid = tvdao.searchOid(oMap);
			history.setOid(oid);
			int a = tvdao.updatePdHistory(history);
			// if(a==1){
			// ProductHistory pdd = tvdao.phid(history.getId());
			// result.setData(pdd);
			// }
			result.setMsg("修改成功!");
			result.setState(0);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("异常!");
		}

	}

	@Override
	public CyResult editWartHistoryById(WarehouseHistory history) {
		CyResult result = new CyResult();
		try {
			MaterialManage mmone = tvdao.searchMMpn(history.getPn());
			Map<String, Object> oMap = new HashMap<>();
			oMap.put("orderNumber", history.getOrderNumber());
			oMap.put("mid", mmone.getId());
			Integer oid = tvdao.searchOid(oMap);
			history.setOid(oid);
			int a = tvdao.updateWhHistory(history);
			// if(a==1){
			// WarehouseHistory wdd = tvdao.whid(history.getId());
			// result.setData(wdd);
			// }
			result.setMsg("修改成功!");
			result.setState(0);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("异常!");
		}
	}

	@Override
	public CyResult delOrderById(Integer id) {
		CyResult result = new CyResult();
		if (id == null) {
			result.setState(1);
			result.setMsg("该条数据不存在,请刷新!");
			return result;
		}
		try {
			tvdao.deleteOrderById(id);
			result.setMsg("删除成功!");
			result.setState(0);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("异常!");
		}

	}

	@Override
	public CyResult addBomList(Integer mid,String uname) {
		CyResult result = new CyResult();
		BomList bl = tvdao.loadMidBom(mid);
		if (bl != null) {
			result.setMsg("重复bom");
			result.setState(3);
			return result;
		}
		MaterialManage loadmm = tvdao.searchMate(mid);
		if (loadmm == null) {
			result.setMsg("请新建材料");
			result.setState(1);
			return result;
		}
		BomList bom = new BomList();
		bom.setMid(mid);
		bom.setUname(uname);
		bom.setPname(loadmm.getMaterialName());
		bom.setPn(loadmm.getPn());
		bom.setMexplain(loadmm.getMexplain());
		bom.setWeight(loadmm.getWeight());
		bom.setDosage(0);
		bom.setCreamTime(CyUtil.getTime());
		int qwe = tvdao.addBomList(bom);
		if (qwe == 1) {
			result.setMsg("添加成功");
			result.setState(0);
			return result;
		}
		result.setMsg("添加失败");
		result.setState(2);
		return result;
	}

	@Override
	public CyResult deleteBomList(Integer id) {
		CyResult result = new CyResult();
		BomList bom = tvdao.loadBom(id);
		if (bom == null) {
			result.setMsg("bom不存在");
			result.setState(1);
			return result;
		}
		int asd = tvdao.delBomList(id);
		if (asd == 1) {
			tvdao.delBomMate(id);
			tvdao.deletePurchaseListBid(id);
			tvdao.deleteCostListBid(id);
			result.setMsg("删除成功");
			result.setState(0);
			return result;
		}
		result.setMsg("删除失败");
		result.setState(2);
		return result;
	}

	@Override
	public CyResult loadBomList(BomList bomss) {
		CyResult result = new CyResult();
		if (bomss.getPage() == null) {
			bomss.setPage(1);
		}
		bomss.setPageSize((bomss.getPage() - 1) * bomss.getLimit());
		int count = tvdao.loadAllBomList(bomss);
		List<BomList> bomLists = tvdao.loadBomList(bomss);
		if (bomLists.size() < 1) {
			result.setMsg("暂无数据");
			result.setState(1);
			return result;
		}
		List<BomList> boms = new ArrayList<>();
		for (BomList bom : bomLists) {
			List<BomMaterials> bmList = tvdao.loadBM(bom.getId());
			bom.setDosage(bmList.size());
			boms.add(bom);
		}
		result.setMsg("加载成功");
		result.setData(boms);
		result.setCount(count);
		result.setState(0);
		return result;
	}

	@Override
	public CyResult loadBomMate(BomMaterials bomMaterials) {
		CyResult result = new CyResult();
		if (bomMaterials.getPage() == null) {
			bomMaterials.setPage(1);
		}
		bomMaterials.setPageSize((bomMaterials.getPage() - 1) * bomMaterials.getLimit());
		List<BomMaterials> bmList = tvdao.loadBMSS(bomMaterials);
		int count = tvdao.loadBMCount(bomMaterials);
		if (bmList.size() < 1) {
			result.setMsg("请添加材料");
			result.setState(1);
			return result;
		}
		List<BomMaterials> bms = new ArrayList<>();
		for (BomMaterials bomm : bmList) {
			BomMaterials bm = new BomMaterials();
			if (bomm.getMid() == null) {
				continue;
			}
			MaterialManage loadmm = tvdao.searchMate(bomm.getMid());
			bm.setId(bomm.getId());
			bm.setBid(bomm.getBid());
			bm.setMid(bomm.getMid());
			if (loadmm == null) {
				bm.setMaterialName("");
				bm.setPn("");
				bm.setMexplain("");
				bm.setWeight("");
			} else {
				bm.setMaterialName(loadmm.getMaterialName());
				bm.setPn(loadmm.getPn());
				bm.setMexplain(loadmm.getMexplain());
				bm.setWeight(loadmm.getWeight());
			}
			bm.setBitNumber(bomm.getBitNumber());
			bm.setUseNumer(bomm.getUseNumer());
			bms.add(bm);
		}
		result.setData(bms);
		result.setCount(count);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult addBomMate(BomMaterials bm) {
		CyResult result = new CyResult();
		if (bm == null) {
			result.setMsg("对象为空");
			result.setState(1);
			return result;
		}
		int zxc = tvdao.addBomMate(bm);
		if (zxc == 1) {
			result.setData(bm.getId());
			result.setMsg("增加成功");
			result.setState(0);
			return result;
		}
		result.setMsg("增加失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult deleteBomMate(Integer id) {
		CyResult result = new CyResult();
		int asd = tvdao.deleBomMate(id);
		if (asd == 1) {
			result.setMsg("删除成功");
			result.setState(0);
			return result;
		}
		result.setMsg("删除失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult updateBomMate(BomMaterials bm) {
		CyResult result = new CyResult();
		int jugg = tvdao.updateBomMate(bm);
		if (jugg == 1) {
			result.setMsg("修改成功");
			result.setState(0);
			return result;
		}
		result.setMsg("修改失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult searchBomList(String pn) {
		CyResult result = new CyResult();
		List<BomList> boms = tvdao.searchBom(pn);
		if (boms.size() < 1) {
			result.setMsg("未找到该料号");
			result.setState(1);
			return result;
		}
		result.setData(boms);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult oweMate(Integer pageNumber) {
		CyResult result = new CyResult();
		if (pageNumber == null) {
			pageNumber = 0;
		} else {
			pageNumber = (pageNumber - 1) * 15;
		}
		List<BomOrderList> bomOrderLists = tvdao.loadbol(pageNumber);
		List<OweMaterials> wmsList = new ArrayList<>();
		List<Integer> idStrings = new ArrayList<Integer>();
		for (BomOrderList bos : bomOrderLists) {

			List<BomMaterials> bmList = tvdao.loadBM(bos.getBid());
			if (bmList.size() < 1) {
				result.setMsg("bom未添加材料");
				result.setState(1);
				return result;
			}
			for (BomMaterials bm : bmList) {
				OweMaterials om = new OweMaterials();
				MaterialManage loadmm = tvdao.searchMate(bm.getMid());
				idStrings.add(bm.getMid());
				if (bos.getStoreState() == 0) {
					String putNumString = tvdao.putwh(bm.getMid());// 入库数量
					String outNumString = tvdao.outwh(bm.getMid());// 出库数量
					if (outNumString == null) {
						outNumString = "0";
					}
					if (putNumString == null) {
						putNumString = "0";
					}
					int outNum = Integer.parseInt(outNumString);
					int putNum = Integer.parseInt(putNumString);
					int yushu = putNum - outNum;// 余数
					om.setNotNumber(bos.getNumbers() - putNum);// 未生产数
					om.setInventory(yushu);// 库存
					om.setOweMaterial(bos.getNumbers() * bm.getUseNumer() - yushu);
				}
				if (bos.getStoreState() == 1) {
					String putNumString = tvdao.putph(bm.getMid());// 入库数量
					String outNumString = tvdao.outph(bm.getMid());// 出库数量
					if (outNumString == null) {
						outNumString = "0";
					}
					if (putNumString == null) {
						putNumString = "0";
					}
					int outNum = Integer.parseInt(outNumString);
					int putNum = Integer.parseInt(putNumString);
					int yushu = putNum - outNum;// 余数
					om.setNotNumber(bos.getNumbers() - putNum);// 未生产数
					om.setInventory(yushu);// 库存
					om.setOweMaterial(bos.getNumbers() * bm.getUseNumer() - yushu);
				}
				om.setBomName(bos.getPname());
				om.setOrderNumber(bos.getOrderNumber());
				om.setNumbers(bos.getNumbers());
				om.setMaterialName(loadmm.getMaterialName());
				om.setPn(loadmm.getPn());
				om.setUseNumer(bm.getUseNumer());
				wmsList.add(om);
			}

		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", idStrings);
		map.put("pageNumber", pageNumber);
		List<MaterialManage> mapList = tvdao.searchMateById(map);
		for (MaterialManage m : mapList) {
			System.err.println(m);
		}

		result.setData(wmsList);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult searchOweMate(String orderNumber, String pname) {
		CyResult result = new CyResult();
		Map<String, Object> map = new HashMap<>();
		map.put("orderNumber", orderNumber);
		map.put("pn", pname);

		List<BomOrderList> bomOrderLists = tvdao.sreachBol(map);
		System.out.println(bomOrderLists);
		List<OweMaterials> wmsList = new ArrayList<>();
		for (BomOrderList bos : bomOrderLists) {

			List<BomMaterials> bmList = tvdao.loadBM(bos.getBid());
			if (bmList.size() < 0) {
				result.setMsg("bom未添加材料");
				result.setState(1);
				return result;
			}
			Map<String, Object> osmap = new HashMap<>();
			osmap.put("mid", bos.getMid());
			osmap.put("oid", bos.getOid());
			String yichukuString = tvdao.putphss(osmap);
			if (yichukuString == null) {
				yichukuString = "0";
			}
			int yichuku = Integer.parseInt(yichukuString);// bom成品已出库数
			for (BomMaterials bm : bmList) {
				OweMaterials om = new OweMaterials();
				MaterialManage loadmm = tvdao.searchMate(bm.getMid());
				if (bos.getStoreState() == 0) {
					Map<String, Object> qwmap = new HashMap<>();
					osmap.put("mid", bm.getMid());
					osmap.put("oid", bos.getOid());
					String putNumString = tvdao.putwhss(qwmap);// 入库数量
					String outNumString = tvdao.outwhss(qwmap);// 出库数量
					if (outNumString == null) {
						outNumString = "0";
					}
					if (putNumString == null) {
						putNumString = "0";
					}
					int outNum = Integer.parseInt(outNumString);
					int putNum = Integer.parseInt(putNumString);
					int yushu = putNum - outNum;// 余数
					om.setInventory(yushu);// 库存
					om.setNotNumber(bos.getNumbers() - yushu - yichuku);// 未生产数
					om.setOweMaterial(bos.getNumbers() * bm.getUseNumer() - yushu);
				}
				if (bos.getStoreState() == 1) {
					Map<String, Object> asmap = new HashMap<>();
					osmap.put("mid", bm.getMid());
					osmap.put("oid", bos.getOid());
					String putNumString = tvdao.putphss(asmap);// 入库数量
					String outNumString = tvdao.outphss(asmap);// 出库数量
					if (outNumString == null) {
						outNumString = "0";
					}
					if (putNumString == null) {
						putNumString = "0";
					}
					int outNum = Integer.parseInt(outNumString);
					int putNum = Integer.parseInt(putNumString);
					int yushu = putNum - outNum;// 余数
					om.setInventory(yushu);// 库存
					om.setNotNumber(bos.getNumbers() - yushu - yichuku);// 未生产数
					om.setOweMaterial(om.getNotNumber() * bm.getUseNumer() - yushu);
				}
				om.setBomName(bos.getPname());
				om.setOrderNumber(bos.getOrderNumber());
				om.setNumbers(bos.getNumbers());
				if (loadmm == null) {
					om.setMaterialName("");
					om.setPn("");
				} else {
					om.setMaterialName(loadmm.getMaterialName());
					om.setPn(loadmm.getPn());
				}

				om.setUseNumer(bm.getUseNumer());
				wmsList.add(om);
			}

		}
		result.setData(wmsList);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult loadPriceList(PriceList price) {
		CyResult result = new CyResult();
		if (price.getPage() == null) {
			price.setPage(1);
		}
		price.setPageSize((price.getPage() - 1) * price.getLimit());
		if (price.getPn() != null) {
			MaterialManage mm = tvdao.searchMMpn(price.getPn());
			price.setMid(mm.getId());
		}
		int allPrice = tvdao.loadAllPrices(price);
		List<PriceList> priceLists = tvdao.loadPrices(price);
		if (priceLists.size() < 1) {
			result.setMsg("无数据");
			result.setState(1);
			return result;
		}
		List<PriceList> pList = new ArrayList<>();
		for (PriceList priceList : priceLists) {
			MaterialManage loadmm = tvdao.searchMate(priceList.getMid());
			if (loadmm == null) {
				result.setMsg("无材料数据");
				result.setState(2);
				return result;
			}
			priceList.setPname(loadmm.getMaterialName());
			priceList.setPn(loadmm.getPn());
			priceList.setMexplain(loadmm.getMexplain());
			pList.add(priceList);
		}
		result.setData(pList);
		result.setCount(allPrice);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult addPriceList(PriceList priceList) {
		CyResult result = new CyResult();
		if (priceList == null) {
			result.setMsg("对象为空");
			result.setState(1);
			return result;
		}
		MaterialManage loadmm = tvdao.searchMate(priceList.getMid());
		if (loadmm == null) {
			result.setMsg("无材料数据");
			result.setState(2);
			return result;
		}
		PriceList price = tvdao.sreachPrice(priceList.getMid());
		if (price != null) {
			result.setMsg("已有此料号价格");
			result.setState(5);
			return result;
		}
		priceList.setMid(priceList.getMid());
		priceList.setCreamTime(CyUtil.getTime());
		int asd = tvdao.addPrice(priceList);
		if (asd == 1) {
			result.setMsg("增加成功");
			result.setState(0);
			return result;
		}
		result.setMsg("增加失败");
		result.setState(4);
		return result;
	}

	@Override
	public CyResult removePriceList(Integer id) {
		CyResult result = new CyResult();
		int asd = tvdao.deletePrice(id);
		if (asd == 1) {
			result.setMsg("删除成功");
			result.setState(0);
			return result;
		}
		result.setMsg("删除失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult updatePriceList(PriceList priceList) {
		CyResult result = new CyResult();
		if (priceList == null) {
			result.setMsg("对象为空");
			result.setState(1);
			return result;
		}
		int asd = tvdao.updatePrice(priceList);
		if (asd == 1) {
			result.setMsg("修改成功");
			result.setState(0);
			return result;
		}
		result.setMsg("修改失败");
		result.setState(2);
		return result;
	}

	@Override
	public CyResult sreachPriceList(String pn) {
		CyResult result = new CyResult();
		MaterialManage mmone = tvdao.searchMMpn(pn);
		if (mmone == null) {
			result.setMsg("没有这个品名的料号");
			result.setState(2);
			return result;
		}
		PriceList price = tvdao.sreachPrice(mmone.getId());
		List<PriceList> priceLists = new ArrayList<>();
		if (price == null) {
			result.setMsg("无数据");
			result.setState(1);
			return result;
		}
		price.setPname(mmone.getMaterialName());
		price.setPn(mmone.getPn());
		price.setMexplain(mmone.getMexplain());
		priceLists.add(price);
		result.setData(priceLists);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult placeOrder(Integer bid, Double demandNumber, String site, String deliveryTime, Integer sort) {
		CyResult result = new CyResult();
		BomList bom = tvdao.loadBom(bid);
		if (bom == null) {
			result.setMsg("未找到该料号");
			result.setState(1);
			return result;
		}
		List<BomMaterials> bmList = tvdao.loadBM(bom.getId());
		if (bmList.size() < 1) {
			result.setMsg("请添加材料");
			result.setState(2);
			return result;
		}
		String oldpo = tvdao.searchPO(CyUtil.getTime2());
		PurchaseList purchase = new PurchaseList();
		if (oldpo == null) {
			String purchaseOrders = "JC" + CyUtil.getTime3() + "001";
			for (BomMaterials bomm : bmList) {
				MaterialManage loadmm = tvdao.searchMate(bomm.getMid());
				if (loadmm != null) {
					PriceList price = tvdao.sreachPrice(bomm.getMid());
					String outNumString = tvdao.outwh(bomm.getMid());// 出库数量
					String putNumString = tvdao.putwh(bomm.getMid());// 入库数量
					if (outNumString == null) {
						outNumString = "0";
					}
					if (putNumString == null) {
						putNumString = "0";
					}
					int outNum = Integer.parseInt(outNumString);
					int putNum = Integer.parseInt(putNumString);
					int yushu = putNum - outNum;// 库存
					DemandList demand = new DemandList();
					demand.setMid(loadmm.getId());
					demand.setBid(bom.getId());
					demand.setCreamTime(CyUtil.getTime());
					demand.setDemandNumber(bomm.getUseNumer() * demandNumber - yushu);
					demand.setDeliveryTime(deliveryTime);
					if (price != null) {
						demand.setJprice(price.getJprice());
						demand.setDmoney(demand.getDemandNumber() * price.getJprice());
					}
					demand.setPurchaseOrders(purchaseOrders);
					int sf = tvdao.addDemandList(demand);
					if (sf == 1) {
						double sums = tvdao.sumPrice(purchaseOrders);
						purchase.setBid(bid);
						purchase.setDemandNumber(demandNumber);
						purchase.setPurchaseOrders(purchaseOrders);
						purchase.setOperator("");
						purchase.setMaterialNumber(bmList.size());
						purchase.setTotalMoney(sums);
						purchase.setCreamTime(CyUtil.getTime());
						purchase.setStuat(1);
						purchase.setOrderStuat(1);
						purchase.setSite(site);
						purchase.setDeliveryTime(deliveryTime);
						purchase.setSort(sort);
					}

				}

			}
		} else {
			String oldpostr = oldpo.substring(10, 13);
			int ops = Integer.parseInt(oldpostr) + 1;
			DecimalFormat df = new DecimalFormat("000");
			String s = df.format(ops);
			String purchaseOrders = "JC" + CyUtil.getTime3() + s;
			for (BomMaterials bomm : bmList) {
				MaterialManage loadmm = tvdao.searchMate(bomm.getMid());
				if (loadmm != null) {
					PriceList price = tvdao.sreachPrice(bomm.getMid());
					String outNumString = tvdao.outwh(bomm.getMid());// 出库数量
					String putNumString = tvdao.putwh(bomm.getMid());// 入库数量
					if (outNumString == null) {
						outNumString = "0";
					}
					if (putNumString == null) {
						putNumString = "0";
					}
					int outNum = Integer.parseInt(outNumString);
					int putNum = Integer.parseInt(putNumString);
					int yushu = putNum - outNum;// 库存
					DemandList demand = new DemandList();
					demand.setMid(loadmm.getId());
					demand.setBid(bom.getId());
					demand.setCreamTime(CyUtil.getTime());
					demand.setDemandNumber(bomm.getUseNumer() * demandNumber - yushu);
					demand.setDeliveryTime(deliveryTime);
					if (price != null) {
						demand.setJprice(price.getJprice());
						demand.setDmoney(demand.getDemandNumber() * price.getJprice());
					}

					demand.setPurchaseOrders(purchaseOrders);
					int sf = tvdao.addDemandList(demand);
					if (sf == 1) {
						double sums = tvdao.sumPrice(purchaseOrders);
						purchase.setBid(bid);
						purchase.setDemandNumber(demandNumber);
						purchase.setPurchaseOrders(purchaseOrders);
						purchase.setOperator("");
						purchase.setMaterialNumber(bmList.size());
						purchase.setTotalMoney(sums);
						purchase.setCreamTime(CyUtil.getTime2());
						purchase.setStuat(1);
						purchase.setOrderStuat(1);
						purchase.setSite(site);
						purchase.setDeliveryTime(deliveryTime);
						purchase.setSort(sort);
					}
				}
			}

		}
		int cx = tvdao.addPurchaseList(purchase);
		if (cx == 1) {
			result.setMsg("下单成功");
			result.setState(0);
			return result;
		}
		result.setMsg("下单失败");
		result.setState(4);
		return result;
	}

	// 单个材料下单采购
	@Override
	public CyResult placeMaterialManage(Integer id, Double demandNumber, String site, String deliveryTime,
			Integer sort) {
		CyResult result = new CyResult();
		MaterialManage loadmm = tvdao.searchMate(id);
		if (loadmm == null) {
			result.setMsg("请录入材料");
			result.setState(1);
			return result;
		}
		String oldpo = tvdao.searchPO(CyUtil.getTime2());
		PurchaseList purchase = new PurchaseList();
		if (oldpo == null) {
			String purchaseOrders = "JC" + CyUtil.getTime3() + "001";
			PriceList price = tvdao.sreachPrice(id);
			DemandList demand = new DemandList();
			demand.setMid(loadmm.getId());
			demand.setCreamTime(CyUtil.getTime());
			demand.setDemandNumber(demandNumber);
			demand.setDeliveryTime(deliveryTime);
			if (price != null) {
				if(price.getJprice()==null){
					price.setJprice(0.0);
				}
				demand.setJprice(price.getJprice());
				demand.setDmoney(demand.getDemandNumber() * price.getJprice());
			}
			demand.setPurchaseOrders(purchaseOrders);
			int sf = tvdao.addDemandList(demand);
			if (sf == 1) {
				double sums = tvdao.sumPrice(purchaseOrders);
				purchase.setBid(id);
				purchase.setDemandNumber(demandNumber);
				purchase.setPurchaseOrders(purchaseOrders);
				purchase.setOperator("");
				purchase.setMaterialNumber(1);
				purchase.setTotalMoney(sums);
				purchase.setCreamTime(CyUtil.getTime2());
				purchase.setStuat(1);
				purchase.setOrderStuat(1);
				purchase.setSite(site);
				purchase.setDeliveryTime(deliveryTime);
				purchase.setSort(sort);
			}
		} else {
			String oldpostr = oldpo.substring(10, 13);
			int ops = Integer.parseInt(oldpostr) + 1;
			DecimalFormat df = new DecimalFormat("000");
			String s = df.format(ops);
			String purchaseOrders = "JC" + CyUtil.getTime3() + s;
			PriceList price = tvdao.sreachPrice(id);
			DemandList demand = new DemandList();
			demand.setMid(loadmm.getId());
			demand.setCreamTime(CyUtil.getTime());
			demand.setDemandNumber(demandNumber);
			demand.setDeliveryTime(deliveryTime);
			if (price != null) {
				if(price.getJprice()==null){
					price.setJprice(0.0);
				}
				demand.setJprice(price.getJprice());
				if(demand.getDemandNumber()==null){
					demand.setDemandNumber(0.0);
				}
				demand.setDmoney(demand.getDemandNumber() * price.getJprice());
			}
			demand.setPurchaseOrders(purchaseOrders);
			int sf = tvdao.addDemandList(demand);
			if (sf == 1) {
				double sums = tvdao.sumPrice(purchaseOrders);
				purchase.setBid(id);
				purchase.setDemandNumber(demandNumber);
				purchase.setPurchaseOrders(purchaseOrders);
				purchase.setOperator("");
				purchase.setMaterialNumber(1);
				purchase.setTotalMoney(sums);
				purchase.setCreamTime(CyUtil.getTime2());
				purchase.setStuat(1);
				purchase.setOrderStuat(1);
				purchase.setSite(site);
				purchase.setDeliveryTime(deliveryTime);
				purchase.setSort(sort);
			}
		}
		int cx = tvdao.addPurchaseList(purchase);
		if (cx == 1) {
			result.setMsg("下单成功");
			result.setState(0);
			return result;
		}
		result.setMsg("下单失败");
		result.setState(4);
		return result;
	}

	@Override
	public CyResult loadPurchaseList(PurchaseList purchase) {
		CyResult result = new CyResult();
		if (purchase.getPage() == null) {
			purchase.setPage(1);
		}
		purchase.setPageSize((purchase.getPage() - 1) * purchase.getLimit());
		int allpl = tvdao.loadAllPurchaseList(purchase);
		List<PurchaseList> purchaseLists = tvdao.loadPurchaseList(purchase);
		if (purchaseLists.size() < 1) {
			result.setMsg("无数据");
			result.setState(1);
			return result;
		}
		List<PurchaseList> purList = new ArrayList<>();
		for (PurchaseList purchaseList : purchaseLists) {
			if(purchaseList.getBid()==null&&purchaseList.getSid()!=null){
				Shortage qlb=tvdao.findQLbXD(purchaseList.getSid());
				purchaseList.setPname(qlb.getPname());
				purchaseList.setPn(qlb.getPn());
				purList.add(purchaseList);
			}else{
			BomList bom = tvdao.loadBom(purchaseList.getBid());
			if (bom == null) {
				MaterialManage loadmm = tvdao.searchMate(purchaseList.getBid());
				if (loadmm != null) {
					purchaseList.setPname(loadmm.getMaterialName());
					purchaseList.setPn(loadmm.getPn());
				}
				purList.add(purchaseList);
			} else {
				purchaseList.setPname(bom.getPname());
				purchaseList.setPn(bom.getPn());
				purList.add(purchaseList);
			}
			}
		}
		result.setData(purList);
		result.setCount(allpl);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult loadDemandList(PurchaseList purchaseList) {
		CyResult result = new CyResult();
		PurchaseList purchase = tvdao.loadPl(purchaseList.getId());
		if (purchase == null) {
			result.setMsg("不存在");
			result.setState(2);
			return result;
		}
		if (purchaseList.getPage() == null) {
			purchaseList.setPage(1);
		}
		purchaseList.setPageSize((purchaseList.getPage() - 1) * purchaseList.getLimit());
		purchaseList.setPurchaseOrders(purchase.getPurchaseOrders());
		List<DemandList> demandLists = tvdao.loadDemandListsLimit(purchaseList);
		int count = tvdao.loadDemandListsCount(purchaseList);
		if (demandLists.size() < 1) {
			result.setMsg("无数据");
			result.setState(1);
			return result;
		}
		List<DemandList> demands = new ArrayList<>();
		for (DemandList demand : demandLists) {
			if (demand.getMid() != null) {
				MaterialManage loadmm = tvdao.searchMate(demand.getMid());
				if (loadmm != null) {
					demand.setPname(loadmm.getMaterialName());
					demand.setPn(loadmm.getPn());
					demands.add(demand);
				}

			}
		}
		result.setData(demands);
		result.setCount(count);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult deletePurchaseList(Integer id) {
		CyResult result = new CyResult();
		PurchaseList pl = tvdao.loadPl(id);
		if (pl == null) {
			result.setMsg("已删除");
			result.setState(2);
			return result;
		}
		tvdao.deleteDemandList(pl.getPurchaseOrders());
		int pom = tvdao.deletepl(id);
		if (pom == 1) {
			result.setMsg("删除成功");
			result.setState(0);
			return result;
		}
		result.setMsg("删除失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult updateDemandList(Integer id, String demandNumber, String deliveryTime) {
		CyResult result = new CyResult();
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("demandNumber", demandNumber);
		map.put("deliveryTime", deliveryTime);
		int qwe = tvdao.updateDemand(map);
		if (qwe == 1) {
			result.setMsg("修改成功");
			result.setState(0);
			return result;
		}
		result.setMsg("修改失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult deleteDemandList(Integer id) {
		CyResult result = new CyResult();
		int zxc = tvdao.deleteDemand(id);
		if (zxc == 1) {
			result.setMsg("删除成功");
			result.setState(0);
			return result;
		}
		result.setMsg("删除失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult updatePruchaseStuat(PurchaseStuat ps) {
		CyResult result = new CyResult();
		if (ps != null) {
			int sdf = tvdao.updatePs(ps);
			if (sdf == 1) {
				result.setMsg("修改成功");
				result.setState(0);
				return result;
			}
		}
		result.setMsg("修改失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult searchProceedPruchase(String purchaseOrders) {
		CyResult result = new CyResult();
		PurchaseList purchase = tvdao.searchPP(purchaseOrders);
		if (purchase == null) {
			result.setMsg("无此采购单号");
			result.setState(1);
			return result;
		}
		if (purchase.getStuat() == 0) {
			result.setMsg("此采购单号已完结");
			result.setState(2);
			return result;
		}
		List<PurchaseList> purList = new ArrayList<>();
		BomList bom = tvdao.loadBom(purchase.getBid());
		purchase.setPname(bom.getPname());
		purchase.setPn(bom.getPn());
		purList.add(purchase);
		result.setData(purList);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult loadEndPurchaseList(PurchaseList purchase) {
		CyResult result = new CyResult();
		if (purchase.getPage() == null) {
			purchase.setPage(1);
		}
		purchase.setPageSize((purchase.getPage() - 1) * purchase.getLimit());
		int allpl = tvdao.loadAllEndPurchaseList(purchase);
		List<PurchaseList> purchaseLists = tvdao.loadEndPurchaseList(purchase);
		if (purchaseLists.size() < 1) {
			result.setMsg("无数据");
			result.setState(1);
			return result;
		}
		List<PurchaseList> purList = new ArrayList<>();
		for (PurchaseList purchaseList : purchaseLists) {
			BomList bom = tvdao.loadBom(purchaseList.getBid());
			if (bom == null) {
				MaterialManage loadmm = tvdao.searchMate(purchaseList.getBid());
				if (loadmm != null) {
					purchaseList.setPname(loadmm.getMaterialName());
					purchaseList.setPn(loadmm.getPn());

				}
				purList.add(purchaseList);
			} else {
				purchaseList.setPname(bom.getPname());
				purchaseList.setPn(bom.getPn());
				purList.add(purchaseList);
			}

		}
		result.setData(purList);
		result.setCount(allpl);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult searchEndPruchase(String purchaseOrders) {
		CyResult result = new CyResult();
		PurchaseList purchase = tvdao.searchPP(purchaseOrders);
		if (purchase == null) {
			result.setMsg("无此采购单号");
			result.setState(1);
			return result;
		}
		if (purchase.getStuat() == 1) {
			result.setMsg("此采购单号正在进行中");
			result.setState(2);
			return result;
		}
		List<PurchaseList> purList = new ArrayList<>();
		BomList bom = tvdao.loadBom(purchase.getBid());
		purchase.setPname(bom.getPname());
		purchase.setPn(bom.getPn());
		purList.add(purchase);
		result.setData(purList);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult loadEndOrderList(OrderList orderDemo) {
		CyResult result = new CyResult();
		int allOrder = tvdao.findAllEndOrders();
		orderDemo.setPageSize((orderDemo.getPage() - 1) * orderDemo.getLimit());
		List<OrderList> loadOrders = tvdao.loadEndOrders(orderDemo);
		if (loadOrders.size() < 1) {
			result.setMsg("暂无数据");
			result.setState(1);
			return result;
		}

		List<OrderList> lol = new ArrayList<>();

		for (OrderList order : loadOrders) {

			if (order.getStoreState() == 0) {
				String outsumString = tvdao.outOrderNumW(order);
				if (outsumString == null) {
					outsumString = "0";
				}
				int outsum = Integer.parseInt(outsumString);
				int yushu = order.getNumbers() - outsum;
				order.setResidue(yushu);
				order.setOutNumber(outsum);
				if (order.getPrice() != null) {
					order.setMoney(outsum * order.getPrice());
				} else {
					order.setMoney(null);
				}

			}
			if (order.getStoreState() == 1) {
				String outsumString = tvdao.outOrderNumH(order);
				if (outsumString == null) {
					outsumString = "0";
				}
				int outsum = Integer.parseInt(outsumString);
				int yushu = order.getNumbers() - outsum;
				order.setResidue(yushu);
				order.setOutNumber(outsum);
				if (order.getPrice() != null) {
					order.setMoney(outsum * order.getPrice());
				} else {
					order.setMoney(null);
				}
			}

			MaterialManage loadmm = tvdao.searchMate(order.getMid());
			if (loadmm != null) {
				order.setProductName(loadmm.getMaterialName());
				order.setPn(loadmm.getPn());
			} else {
				order.setProductName("");
				order.setPn("");
			}

			lol.add(order);
		}

		result.setCount(allOrder);
		result.setData(lol);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult searchEndOrderList(String orderNumber) {
		CyResult result = new CyResult();
		List<OrderList> orders = tvdao.findOrders(orderNumber);
		if (orders.size() < 1) {
			result.setMsg("无此订单号");
			result.setState(1);
			return result;
		}
		List<OrderList> lol = new ArrayList<>();
		for (OrderList order : orders) {
			if (order.getStuat() == 0) {
				if (order.getStoreState() == 0) {
					String outsumString = tvdao.outOrderNumW(order);
					if (outsumString == null) {
						outsumString = "0";
					}
					int outsum = Integer.parseInt(outsumString);
					int yushu = order.getNumbers() - outsum;
					order.setResidue(yushu);
					order.setOutNumber(outsum);
					if (order.getPrice() != null) {
						order.setMoney(outsum * order.getPrice());
					} else {
						order.setMoney(null);
					}

				}
				if (order.getStoreState() == 1) {
					String outsumString = tvdao.outOrderNumH(order);
					if (outsumString == null) {
						outsumString = "0";
					}
					int outsum = Integer.parseInt(outsumString);
					int yushu = order.getNumbers() - outsum;
					order.setResidue(yushu);
					order.setOutNumber(outsum);
					if (order.getPrice() != null) {
						order.setMoney(outsum * order.getPrice());
					} else {
						order.setMoney(null);
					}
				}

				MaterialManage loadmm = tvdao.searchMate(order.getMid());
				if (loadmm != null) {
					order.setProductName(loadmm.getMaterialName());
					order.setPn(loadmm.getPn());
				} else {
					order.setProductName("");
					order.setPn("");
				}
				lol.add(order);
			}
		}
		if (lol.size() < 1) {
			result.setMsg("此订单号正在进行中");
			result.setState(2);
			return result;
		}
		result.setData(lol);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult searchOrderList(String orderNumber) {
		CyResult result = new CyResult();
		List<OrderList> orders = tvdao.findOrders(orderNumber);
		if (orders.size() < 1) {
			result.setMsg("无此订单号");
			result.setState(1);
			return result;
		}
		List<OrderList> lol = new ArrayList<>();
		for (OrderList order : orders) {
			if (order.getStuat() == 1) {
				if (order.getStoreState() == 0) {
					String outsumString = tvdao.outOrderNumW(order);
					if (outsumString == null) {
						outsumString = "0";
					}
					int outsum = Integer.parseInt(outsumString);
					int yushu = order.getNumbers() - outsum;
					order.setResidue(yushu);
					order.setOutNumber(outsum);
					if (order.getPrice() != null) {
						order.setMoney(outsum * order.getPrice());
					} else {
						order.setMoney(null);
					}

				}
				if (order.getStoreState() == 1) {
					String outsumString = tvdao.outOrderNumH(order);
					if (outsumString == null) {
						outsumString = "0";
					}
					int outsum = Integer.parseInt(outsumString);
					int yushu = order.getNumbers() - outsum;
					order.setResidue(yushu);
					order.setOutNumber(outsum);
					if (order.getPrice() != null) {
						order.setMoney(outsum * order.getPrice());
					} else {
						order.setMoney(null);
					}
				}

				MaterialManage loadmm = tvdao.searchMate(order.getMid());
				if (loadmm != null) {
					order.setProductName(loadmm.getMaterialName());
					order.setPn(loadmm.getPn());
				} else {
					order.setProductName("");
					order.setPn("");
				}
				lol.add(order);
			}
		}
		if (lol.size() < 1) {
			result.setMsg("此订单号已结单");
			result.setState(2);
			return result;
		}
		result.setData(lol);
		result.setMsg("加载成功");
		result.setState(0);
		return result;

	}

	@Override
	public CyResult updateOrderStuat(String id, Integer stuat) {
		CyResult result = new CyResult();
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("stuat", stuat);
		int jugg = tvdao.updateOrderStuat(map);
		if (jugg == 1) {
			result.setMsg("修改成功");
			result.setState(0);
			return result;
		}
		result.setMsg("修改失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult loadStructure(Integer pageNumber) {
		CyResult result = new CyResult();
		List<MaterialManage> loadmm = tvdao.loadjgMM();
		if (pageNumber == null) {
			pageNumber = 0;
		} else {
			pageNumber = (pageNumber - 1) * 15;
		}
		List<MaterialManage> mms = tvdao.loadpagejgMM(pageNumber);
		List<Warehouse> whList = new ArrayList<Warehouse>();
		for (MaterialManage mate : mms) {
			Warehouse war = new Warehouse();
			String outNumString = tvdao.outstrh(mate.getId());// 出库数量
			String putNumString = tvdao.putstrh(mate.getId());// 入库数量
			if (outNumString == null) {
				outNumString = "0";
			}
			if (putNumString == null) {
				putNumString = "0";
			}
			int outNum = Integer.parseInt(outNumString);
			int putNum = Integer.parseInt(putNumString);
			int yushu = putNum - outNum;// 余数
			war.setId(mate.getId());
			war.setResidue(yushu);
			war.setProductName(mate.getMaterialName());
			war.setPn(mate.getPn());
			war.setWeight(mate.getWeight());
			war.setSpecification(mate.getMexplain());
			war.setOutNumber(outNum);
			war.setPutNumber(putNum);
			whList.add(war);
		}
		if (mms.size() < 1) {
			result.setMsg("暂无数据");
			result.setState(3);
			return result;
		}
		result.setState(1);
		result.setData(loadmm.size());
		result.setData1(whList);
		result.setMsg("加载成功");
		return result;
	}

	@Override
	public CyResult loadStructureHistory(Integer mid) {
		CyResult result = new CyResult();
		List<StructureHistory> whList = tvdao.loadstrh(mid);
		MaterialManage loadmm = tvdao.searchMate(mid);
		if (loadmm == null) {
			result.setMsg("无材料数据");
			result.setState(2);
			return result;
		}
		if (whList.size() < 1) {
			result.setState(1);
			result.setMsg("暂无出入库记录");
			return result;
		}
		List<StructureHistory> wareList = new ArrayList<>();
		for (StructureHistory wgod : whList) {
			OrderList ordersss = tvdao.findOrderid(wgod.getOid());
			if (ordersss != null) {
				wgod.setOrderNumber(ordersss.getOrderNumber());
			} else {
				wgod.setOrderNumber("");
			}
			wgod.setMaterialName(loadmm.getMaterialName());
			wgod.setPn(loadmm.getPn());
			wareList.add(wgod);
		}
		result.setData(wareList);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult addStructureHistory(StructureHistory sh) {
		CyResult result = new CyResult();
		if (sh == null) {
			result.setMsg("不能传空对象");
			result.setState(1);
			return result;
		}
		Map<String, Object> oMap = new HashMap<>();
		oMap.put("orderNumber", sh.getOrderNumber());
		oMap.put("mid", sh.getMid());
		Integer oid = tvdao.searchOid(oMap);
		sh.setOid(oid);
		String time = CyUtil.getTime();
		sh.setPtime(time);
		int asd = tvdao.insertstrh(sh);
		if (asd == 1) {
			result.setMsg("添加成功");
			result.setState(0);
			return result;
		}
		result.setMsg("添加失败");
		result.setState(2);
		return result;
	}

	@Override
	public CyResult removeStructureHistory(Integer id) {
		CyResult result = new CyResult();
		int qaz = tvdao.deletestrh(id);
		if (qaz > 0) {
			result.setMsg("删除成功");
			result.setState(0);
			return result;
		}
		result.setMsg("删除失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult loadStructureHistoryWarByIds(String ids) {
		CyResult result = new CyResult();
		if (ids == null) {
			result.setState(0);
			result.setMsg("ids不能为空!");
			return result;
		}
		List<StructureHistory> list = new ArrayList<StructureHistory>();
		String[] idstr = ids.split(",");
		for (String id : idstr) {
			StructureHistory wh = tvdao.findStructureHistoryById(Integer.parseInt(id));
			Integer oid = wh.getOid();
			OrderList order = tvdao.findOrderid(oid);
			if (order == null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", wh.getId());
				wh.setNumbers(null);
				wh.setOrderNumber("");
				wh.setOutNumber(Integer.parseInt(tvdao.outstrhOrderNumNoOrder(map)));
				wh.setResidue(null);
			} else {
				Map<String, Object> map = new HashMap<String, Object>();
				wh.setNumbers(order.getNumbers());
				wh.setOrderNumber(order.getOrderNumber());
				map.put("oid", order.getId());
				map.put("id", wh.getId());
				String outsumString = tvdao.outstrhOrderNum(map);
				if (outsumString == null) {
					wh.setOutNumber(null);
					wh.setResidue(order.getNumbers());
				} else {
					int outsum = Integer.parseInt(outsumString);
					int yushu = order.getNumbers() - outsum;
					wh.setOutNumber(outsum);
					wh.setResidue(yushu);
				}

			}
			list.add(wh);
		}
		result.setData(list);
		result.setMsg("加载成功");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult updateStructureHistory(StructureHistory history) {
		CyResult result = new CyResult();
		try {
			MaterialManage mmone = tvdao.searchMMpn(history.getPn());
			Map<String, Object> oMap = new HashMap<>();
			oMap.put("orderNumber", history.getOrderNumber());
			oMap.put("mid", mmone.getId());
			Integer oid = tvdao.searchOid(oMap);
			history.setOid(oid);
			int a = tvdao.updateStrHistory(history);
			result.setMsg("修改成功!");
			result.setState(0);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("异常!");
		}
	}

	@Override
	public CyResult loadBoardcard(Integer pageNumber) {
		CyResult result = new CyResult();
		List<MaterialManage> loadmm = tvdao.loadbkMM();
		if (pageNumber == null) {
			pageNumber = 0;
		} else {
			pageNumber = (pageNumber - 1) * 15;
		}
		List<MaterialManage> mms = tvdao.loadpagebkMM(pageNumber);
		List<Warehouse> whList = new ArrayList<Warehouse>();
		for (MaterialManage mate : mms) {
			Warehouse war = new Warehouse();
			String outNumString = tvdao.outbkh(mate.getId());// 出库数量
			String putNumString = tvdao.putbkh(mate.getId());// 入库数量
			if (outNumString == null) {
				outNumString = "0";
			}
			if (putNumString == null) {
				putNumString = "0";
			}
			int outNum = Integer.parseInt(outNumString);
			int putNum = Integer.parseInt(putNumString);
			int yushu = putNum - outNum;// 余数
			war.setId(mate.getId());
			war.setResidue(yushu);
			war.setProductName(mate.getMaterialName());
			war.setPn(mate.getPn());
			war.setWeight(mate.getWeight());
			war.setSpecification(mate.getMexplain());
			war.setOutNumber(outNum);
			war.setPutNumber(putNum);
			whList.add(war);
		}
		if (mms.size() < 1) {
			result.setMsg("暂无数据");
			result.setState(3);
			return result;
		}
		result.setState(1);
		result.setData(loadmm.size());
		result.setData1(whList);
		result.setMsg("加载成功");
		return result;
	}

	@Override
	public CyResult loadBoardcardHistory(Integer mid) {
		CyResult result = new CyResult();
		List<BoardcardHistory> whList = tvdao.loadbkh(mid);
		MaterialManage loadmm = tvdao.searchMate(mid);
		if (loadmm == null) {
			result.setMsg("无材料数据");
			result.setState(2);
			return result;
		}
		if (whList.size() < 1) {
			result.setState(1);
			result.setMsg("暂无出入库记录");
			return result;
		}
		List<BoardcardHistory> wareList = new ArrayList<>();
		for (BoardcardHistory wgod : whList) {
			OrderList ordersss = tvdao.findOrderid(wgod.getOid());
			if (ordersss != null) {
				wgod.setOrderNumber(ordersss.getOrderNumber());
			} else {
				wgod.setOrderNumber("");
			}
			wgod.setMaterialName(loadmm.getMaterialName());
			wgod.setPn(loadmm.getPn());
			wareList.add(wgod);
		}
		result.setData(wareList);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult addBoardcardHistory(BoardcardHistory sh) {
		CyResult result = new CyResult();
		if (sh == null) {
			result.setMsg("不能传空对象");
			result.setState(1);
			return result;
		}
		Map<String, Object> oMap = new HashMap<>();
		oMap.put("orderNumber", sh.getOrderNumber());
		oMap.put("mid", sh.getMid());
		Integer oid = tvdao.searchOid(oMap);
		sh.setOid(oid);
		String time = CyUtil.getTime();
		sh.setPtime(time);
		int asd = tvdao.insertbkh(sh);
		if (asd == 1) {
			result.setMsg("添加成功");
			result.setState(0);
			return result;
		}
		result.setMsg("添加失败");
		result.setState(2);
		return result;
	}

	@Override
	public CyResult removeBoardcardHistory(Integer id) {
		CyResult result = new CyResult();
		int qaz = tvdao.deletebkh(id);
		if (qaz > 0) {
			result.setMsg("删除成功");
			result.setState(0);
			return result;
		}
		result.setMsg("删除失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult loadBoardcardHistoryWarByIds(String ids) {
		CyResult result = new CyResult();
		if (ids == null) {
			result.setState(0);
			result.setMsg("ids不能为空!");
			return result;
		}
		List<BoardcardHistory> list = new ArrayList<BoardcardHistory>();
		String[] idstr = ids.split(",");
		for (String id : idstr) {
			BoardcardHistory wh = tvdao.findBoardcardHistoryById(Integer.parseInt(id));
			Integer oid = wh.getOid();
			OrderList order = tvdao.findOrderid(oid);
			if (order == null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", wh.getId());
				wh.setNumbers(null);
				wh.setOrderNumber("");
				wh.setOutNumber(Integer.parseInt(tvdao.outbkhOrderNumNoOrder(map)));
				wh.setResidue(null);
			} else {
				Map<String, Object> map = new HashMap<String, Object>();
				wh.setNumbers(order.getNumbers());
				wh.setOrderNumber(order.getOrderNumber());
				map.put("oid", order.getId());
				map.put("id", wh.getId());
				String outsumString = tvdao.outbkhOrderNum(map);
				if (outsumString == null) {
					wh.setOutNumber(null);
					wh.setResidue(order.getNumbers());
				} else {
					int outsum = Integer.parseInt(outsumString);
					int yushu = order.getNumbers() - outsum;
					wh.setOutNumber(outsum);
					wh.setResidue(yushu);
				}

			}
			list.add(wh);
		}
		result.setData(list);
		result.setMsg("加载成功");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult updateBoardcardHistory(BoardcardHistory history) {
		CyResult result = new CyResult();
		try {
			MaterialManage mmone = tvdao.searchMMpn(history.getPn());
			Map<String, Object> oMap = new HashMap<>();
			oMap.put("orderNumber", history.getOrderNumber());
			oMap.put("mid", mmone.getId());
			Integer oid = tvdao.searchOid(oMap);
			history.setOid(oid);
			int a = tvdao.updatebkHistory(history);
			result.setMsg("修改成功!");
			result.setState(0);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("异常!");
		}
	}

	@Override
	public CyResult loadPrecious(Integer pageNumber) {
		CyResult result = new CyResult();
		List<MaterialManage> loadmm = tvdao.loadgzMM();
		if (pageNumber == null) {
			pageNumber = 0;
		} else {
			pageNumber = (pageNumber - 1) * 15;
		}
		List<MaterialManage> mms = tvdao.loadpagegzMM(pageNumber);
		List<Warehouse> whList = new ArrayList<Warehouse>();
		for (MaterialManage mate : mms) {
			Warehouse war = new Warehouse();
			String outNumString = tvdao.outgzh(mate.getId());// 出库数量
			String putNumString = tvdao.putgzh(mate.getId());// 入库数量
			if (outNumString == null) {
				outNumString = "0";
			}
			if (putNumString == null) {
				putNumString = "0";
			}
			int outNum = Integer.parseInt(outNumString);
			int putNum = Integer.parseInt(putNumString);
			int yushu = putNum - outNum;// 余数
			war.setId(mate.getId());
			war.setResidue(yushu);
			war.setProductName(mate.getMaterialName());
			war.setPn(mate.getPn());
			war.setWeight(mate.getWeight());
			war.setSpecification(mate.getMexplain());
			war.setOutNumber(outNum);
			war.setPutNumber(putNum);
			whList.add(war);
		}
		if (mms.size() < 1) {
			result.setMsg("暂无数据");
			result.setState(3);
			return result;
		}
		result.setState(1);
		result.setData(loadmm.size());
		result.setData1(whList);
		result.setMsg("加载成功");
		return result;
	}

	@Override
	public CyResult loadPreciousHistory(Integer mid) {
		CyResult result = new CyResult();
		List<PreciousHistory> whList = tvdao.loadgzh(mid);
		MaterialManage loadmm = tvdao.searchMate(mid);
		if (loadmm == null) {
			result.setMsg("无材料数据");
			result.setState(2);
			return result;
		}
		if (whList.size() < 1) {
			result.setState(1);
			result.setMsg("暂无出入库记录");
			return result;
		}
		List<PreciousHistory> wareList = new ArrayList<>();
		for (PreciousHistory wgod : whList) {
			OrderList ordersss = tvdao.findOrderid(wgod.getOid());
			if (ordersss != null) {
				wgod.setOrderNumber(ordersss.getOrderNumber());
			} else {
				wgod.setOrderNumber("");
			}
			wgod.setMaterialName(loadmm.getMaterialName());
			wgod.setPn(loadmm.getPn());
			wareList.add(wgod);
		}
		result.setData(wareList);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult addPreciousHistory(PreciousHistory sh) {
		CyResult result = new CyResult();
		if (sh == null) {
			result.setMsg("不能传空对象");
			result.setState(1);
			return result;
		}
		Map<String, Object> oMap = new HashMap<>();
		oMap.put("orderNumber", sh.getOrderNumber());
		oMap.put("mid", sh.getMid());
		Integer oid = tvdao.searchOid(oMap);
		sh.setOid(oid);
		String time = CyUtil.getTime();
		sh.setPtime(time);
		int asd = tvdao.insertgzh(sh);
		if (asd == 1) {
			result.setMsg("添加成功");
			result.setState(0);
			return result;
		}
		result.setMsg("添加失败");
		result.setState(2);
		return result;
	}

	@Override
	public CyResult removePreciousHistory(Integer id) {
		CyResult result = new CyResult();
		int qaz = tvdao.deletegzh(id);
		if (qaz > 0) {
			result.setMsg("删除成功");
			result.setState(0);
			return result;
		}
		result.setMsg("删除失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult loadPreciousHistoryWarByIds(String ids) {
		CyResult result = new CyResult();
		if (ids == null) {
			result.setState(0);
			result.setMsg("ids不能为空!");
			return result;
		}
		List<PreciousHistory> list = new ArrayList<PreciousHistory>();
		String[] idstr = ids.split(",");
		for (String id : idstr) {
			PreciousHistory wh = tvdao.findPreciousHistoryById(Integer.parseInt(id));
			Integer oid = wh.getOid();
			OrderList order = tvdao.findOrderid(oid);
			if (order == null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", wh.getId());
				wh.setNumbers(null);
				wh.setOrderNumber("");
				wh.setOutNumber(Integer.parseInt(tvdao.outgzhOrderNumNoOrder(map)));
				wh.setResidue(null);
			} else {
				Map<String, Object> map = new HashMap<String, Object>();
				wh.setNumbers(order.getNumbers());
				wh.setOrderNumber(order.getOrderNumber());
				map.put("oid", order.getId());
				map.put("id", wh.getId());
				String outsumString = tvdao.outgzhOrderNum(map);
				if (outsumString == null) {
					wh.setOutNumber(null);
					wh.setResidue(order.getNumbers());
				} else {
					int outsum = Integer.parseInt(outsumString);
					int yushu = order.getNumbers() - outsum;
					wh.setOutNumber(outsum);
					wh.setResidue(yushu);
				}

			}
			list.add(wh);
		}
		result.setData(list);
		result.setMsg("加载成功");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult updatePreciousHistory(PreciousHistory history) {
		CyResult result = new CyResult();
		try {
			MaterialManage mmone = tvdao.searchMMpn(history.getPn());
			Map<String, Object> oMap = new HashMap<>();
			oMap.put("orderNumber", history.getOrderNumber());
			oMap.put("mid", mmone.getId());
			Integer oid = tvdao.searchOid(oMap);
			history.setOid(oid);
			int a = tvdao.updategzHistory(history);
			result.setMsg("修改成功!");
			result.setState(0);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("异常!");
		}
	}

	@Override
	public CyResult loadCostList(CostList cost) {
		CyResult result = new CyResult();
		if (cost.getPage() == null) {
			cost.setPage(1);
		}
		cost.setPageSize((cost.getPage() - 1) * cost.getLimit());
		int allpl = tvdao.loadAllCostList(cost);
		List<CostList> costLists = tvdao.loadCostList(cost);
		if (costLists.size() < 1) {
			result.setMsg("无数据");
			result.setState(1);
			return result;
		}
		List<CostList> purList = new ArrayList<>();
		for (CostList purchaseList : costLists) {
			BomList bom = tvdao.loadBom(purchaseList.getBid());
			purchaseList.setPname(bom.getPname());
			purchaseList.setPn(bom.getPn());
			purList.add(purchaseList);
		}
		result.setData(purList);
		result.setCount(allpl);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult deleteCostList(Integer id) {
		CyResult result = new CyResult();
		CostList pl = tvdao.loadCost(id);
		if (pl == null) {
			result.setMsg("已删除");
			result.setState(2);
			return result;
		}
		tvdao.deleteDemandList(pl.getCostOrders());
		int pom = tvdao.deleteCost(id);
		if (pom == 1) {
			result.setMsg("删除成功");
			result.setState(0);
			return result;
		}
		result.setMsg("删除失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult loadCostListDemandList(DemandList deman) {
		CyResult result = new CyResult();
		CostList cost = tvdao.loadCost(deman.getId());
		if (cost == null) {
			result.setMsg("不存在");
			result.setState(2);
			return result;
		}
		deman.setPurchaseOrders(cost.getCostOrders());
		if (deman.getPage() == null) {
			deman.setPage(1);
		}
		deman.setPageSize((deman.getPage() - 1) * deman.getLimit());
		List<DemandList> demandLists = tvdao.loadDemandListssss(deman);
		int count = tvdao.loadDemandListsCountssss(deman);
		if (demandLists.size() < 1) {
			result.setMsg("无数据");
			result.setState(1);
			return result;
		}
		List<DemandList> demands = new ArrayList<>();
		for (DemandList demand : demandLists) {
			if (demand.getMid() != null) {
				MaterialManage loadmm = tvdao.searchMate(demand.getMid());
				if (loadmm != null) {
					demand.setPname(loadmm.getMaterialName());
					demand.setPn(loadmm.getPn());
				}

				demands.add(demand);
			}
		}
		result.setData(demands);
		result.setCount(count);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult placeCostListOrder(Integer bid, Double demandNumber) {
		CyResult result = new CyResult();
		BomList bom = tvdao.loadBom(bid);
		if (bom == null) {
			result.setMsg("未找到该料号");
			result.setState(1);
			return result;
		}
		List<BomMaterials> bmList = tvdao.loadBM(bom.getId());
		if (bmList.size() < 1) {
			result.setMsg("请添加材料");
			result.setState(2);
			return result;
		}
		String oldpo = tvdao.searchCO(CyUtil.getTime2());
		CostList purchase = new CostList();
		if (oldpo == null) {
			String purchaseOrders = "JS" + CyUtil.getTime3() + "001";
			try {

				for (BomMaterials bomm : bmList) {
					if(bomm.getMid()==null){
						continue;
					}
					MaterialManage loadmm = tvdao.searchMate(bomm.getMid());
					if (loadmm != null) {
						PriceList price = tvdao.sreachPrice(bomm.getMid());
						DemandList demand = new DemandList();
						demand.setMid(loadmm.getId());
						demand.setBid(bom.getId());
						demand.setCreamTime(CyUtil.getTime());
						demand.setDemandNumber(bomm.getUseNumer() * demandNumber);
						if (price != null) {
							demand.setJprice(price.getJprice());
							demand.setDmoney(demand.getDemandNumber() * price.getJprice());
						}

						demand.setPurchaseOrders(purchaseOrders);
						int sf = tvdao.addDemandList(demand);
						if (sf == 1) {
							double sums = tvdao.sumPrice(purchaseOrders);
							purchase.setBid(bid);
							purchase.setDemandNumber(demandNumber);
							purchase.setCostOrders(purchaseOrders);
							purchase.setOperator("");
							purchase.setMaterialNumber(bmList.size());
							purchase.setTotalMoney(sums);
							purchase.setCreamTime(CyUtil.getTime());
							purchase.setStuat(0);
						}

					}

				}

			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}

		} else {
			String oldpostr = oldpo.substring(10, 13);
			int ops = Integer.parseInt(oldpostr) + 1;
			DecimalFormat df = new DecimalFormat("000");
			String s = df.format(ops);
			String purchaseOrders = "JS" + CyUtil.getTime3() + s;
			try {

				for (BomMaterials bomm : bmList) {
					System.out.println("bom:"+bomm);
					if(bomm.getMid()==null){
						continue;
					}
					MaterialManage loadmm = tvdao.searchMate(bomm.getMid());
					if (loadmm != null) {
						PriceList price = tvdao.sreachPrice(bomm.getMid());
						DemandList demand = new DemandList();
						demand.setMid(loadmm.getId());
						demand.setBid(bom.getId());
						demand.setCreamTime(CyUtil.getTime());
						if(bomm.getUseNumer()==null||bomm.getUseNumer().equals("")){
							bomm.setUseNumer(0.0);
						}
						if(demandNumber==null||demandNumber.equals("")){
							demandNumber=0.0;
						}
						demand.setDemandNumber(bomm.getUseNumer() * demandNumber);
						if (price != null) {
							if(price.getJprice()==null){
								price.setJprice(0.0);
							}
							demand.setJprice(price.getJprice());
							demand.setDmoney(demand.getDemandNumber() * price.getJprice());
						}

						demand.setPurchaseOrders(purchaseOrders);
						int sf = tvdao.addDemandList(demand);
						if (sf == 1) {
							double sums = tvdao.sumPrice(purchaseOrders);
							purchase.setBid(bid);
							purchase.setDemandNumber(demandNumber);
							purchase.setCostOrders(purchaseOrders);
							purchase.setOperator("");
							purchase.setMaterialNumber(bmList.size());
							purchase.setTotalMoney(sums);
							purchase.setCreamTime(CyUtil.getTime2());
							purchase.setStuat(1);
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}

		}
		int cx = tvdao.addCostList(purchase);
		if (cx == 1) {
			result.setMsg("核算成功");
			result.setState(0);
			return result;
		}
		result.setMsg("核算失败");
		result.setState(4);
		return result;
	}

	@Override
	public CyResult searchCostList(String costOrders) {
		CyResult result = new CyResult();
		CostList purchase = tvdao.searchCost(costOrders);
		if (purchase == null) {
			result.setMsg("无此成本单号");
			result.setState(1);
			return result;
		}
		List<CostList> purList = new ArrayList<>();
		BomList bom = tvdao.loadBom(purchase.getBid());
		purchase.setPname(bom.getPname());
		purchase.setPn(bom.getPn());
		purList.add(purchase);
		result.setData(purList);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult placeMinNumberCostListOrder(Integer bid) {
		CyResult result = new CyResult();
		BomList bom = tvdao.loadBom(bid);
		if (bom == null) {
			result.setMsg("未找到该料号");
			result.setState(1);
			return result;
		}
		List<BomMaterials> bmList = tvdao.loadBM(bom.getId());
		if (bmList.size() < 1) {
			result.setMsg("请添加材料");
			result.setState(2);
			return result;
		}
		String oldpo = tvdao.searchCO(CyUtil.getTime2());
		CostList purchase = new CostList();
		if (oldpo == null) {
			String purchaseOrders = "JS" + CyUtil.getTime3() + "001";

			for (BomMaterials bosss : bmList) {
				MaterialManage loadmm = tvdao.searchMate(bosss.getMid());
				if (loadmm != null) {
					PriceList price = tvdao.sreachPrice(bosss.getMid());
					if (price == null) {
						result.setMsg("请去价格表录入料号为:" + loadmm.getPn() + "的价格");
						result.setState(6);
						return result;
					}
					if (price.getMinNumbers() == null) {
						result.setMsg("请去价格表录入料号为:" + loadmm.getPn() + "的最小包装数");
						result.setState(5);
						return result;
					}
				}

			}

			try {

				for (BomMaterials bomm : bmList) {
					MaterialManage loadmm = tvdao.searchMate(bomm.getMid());
					if (loadmm != null) {
						PriceList price = tvdao.sreachPrice(bomm.getMid());
						DemandList demand = new DemandList();
						demand.setMid(loadmm.getId());
						demand.setBid(bom.getId());
						demand.setCreamTime(CyUtil.getTime());
						demand.setDemandNumber(bomm.getUseNumer() * price.getMinNumbers());
						demand.setJprice(price.getJprice());
						demand.setDmoney(demand.getDemandNumber() * price.getJprice());
						demand.setPurchaseOrders(purchaseOrders);
						int sf = tvdao.addDemandList(demand);
						if (sf == 1) {
							double sums = tvdao.sumPrice(purchaseOrders);
							purchase.setBid(bid);
							purchase.setDemandNumber(price.getMinNumbers());
							purchase.setCostOrders(purchaseOrders);
							purchase.setOperator("");
							purchase.setMaterialNumber(bmList.size());
							purchase.setTotalMoney(sums);
							purchase.setCreamTime(CyUtil.getTime());
							purchase.setStuat(0);
						}

					}

				}

			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}

		} else {
			String oldpostr = oldpo.substring(10, 13);
			int ops = Integer.parseInt(oldpostr) + 1;
			DecimalFormat df = new DecimalFormat("000");
			String s = df.format(ops);
			String purchaseOrders = "JS" + CyUtil.getTime3() + s;

			for (BomMaterials bosss : bmList) {
				MaterialManage loadmm = tvdao.searchMate(bosss.getMid());
				if (loadmm != null) {
					PriceList price = tvdao.sreachPrice(bosss.getMid());
					if (price == null) {
						result.setMsg("请去价格表录入料号为:" + loadmm.getPn() + "的价格");
						result.setState(6);
						return result;
					}
					if (price.getMinNumbers() == null) {
						result.setMsg("请去价格表录入料号为:" + loadmm.getPn() + "的最小包装数");
						result.setState(5);
						return result;
					}
				}

			}

			try {

				for (BomMaterials bomm : bmList) {
					MaterialManage loadmm = tvdao.searchMate(bomm.getMid());
					if (loadmm != null) {
						PriceList price = tvdao.sreachPrice(bomm.getMid());
						DemandList demand = new DemandList();
						demand.setMid(loadmm.getId());
						demand.setBid(bom.getId());
						demand.setCreamTime(CyUtil.getTime());
						demand.setDemandNumber(bomm.getUseNumer() * price.getMinNumbers());
						demand.setJprice(price.getJprice());
						demand.setDmoney(demand.getDemandNumber() * price.getJprice());

						demand.setPurchaseOrders(purchaseOrders);
						int sf = tvdao.addDemandList(demand);
						if (sf == 1) {
							double sums = tvdao.sumPrice(purchaseOrders);
							purchase.setBid(bid);
							purchase.setDemandNumber(price.getMinNumbers());
							purchase.setCostOrders(purchaseOrders);
							purchase.setOperator("");
							purchase.setMaterialNumber(bmList.size());
							purchase.setTotalMoney(sums);
							purchase.setCreamTime(CyUtil.getTime2());
							purchase.setStuat(1);
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}

		}
		int cx = tvdao.addCostList(purchase);
		if (cx == 1) {
			result.setMsg("核算成功");
			result.setState(0);
			return result;
		}
		result.setMsg("核算失败");
		result.setState(4);
		return result;
	}

	@Override
	public CyResult loadAccessory(Integer pageNumber) {
		CyResult result = new CyResult();
		List<MaterialManage> loadmm = tvdao.loadflMM();
		if (pageNumber == null) {
			pageNumber = 0;
		} else {
			pageNumber = (pageNumber - 1) * 15;
		}
		List<MaterialManage> mms = tvdao.loadpageflMM(pageNumber);
		List<Warehouse> whList = new ArrayList<Warehouse>();
		for (MaterialManage mate : mms) {
			Warehouse war = new Warehouse();
			String outNumString = tvdao.outflh(mate.getId());// 出库数量
			String putNumString = tvdao.putflh(mate.getId());// 入库数量
			if (outNumString == null) {
				outNumString = "0";
			}
			if (putNumString == null) {
				putNumString = "0";
			}
			int outNum = Integer.parseInt(outNumString);
			int putNum = Integer.parseInt(putNumString);
			int yushu = putNum - outNum;// 余数
			war.setId(mate.getId());
			war.setResidue(yushu);
			war.setProductName(mate.getMaterialName());
			war.setPn(mate.getPn());
			war.setWeight(mate.getWeight());
			war.setSpecification(mate.getMexplain());
			war.setOutNumber(outNum);
			war.setPutNumber(putNum);
			whList.add(war);
		}
		if (mms.size() < 1) {
			result.setMsg("暂无数据");
			result.setState(3);
			return result;
		}
		result.setState(1);
		result.setData(loadmm.size());
		result.setData1(whList);
		result.setMsg("加载成功");
		return result;
	}

	@Override
	public CyResult loadAccessoryHistory(Integer mid) {
		CyResult result = new CyResult();
		List<AccessoryHistory> whList = tvdao.loadflh(mid);
		MaterialManage loadmm = tvdao.searchMate(mid);
		if (loadmm == null) {
			result.setMsg("无材料数据");
			result.setState(2);
			return result;
		}
		if (whList.size() < 1) {
			result.setState(1);
			result.setMsg("暂无出入库记录");
			return result;
		}
		List<AccessoryHistory> wareList = new ArrayList<>();
		for (AccessoryHistory wgod : whList) {
			OrderList ordersss = tvdao.findOrderid(wgod.getOid());
			if (ordersss != null) {
				wgod.setOrderNumber(ordersss.getOrderNumber());
			} else {
				wgod.setOrderNumber("");
			}
			wgod.setMaterialName(loadmm.getMaterialName());
			wgod.setPn(loadmm.getPn());
			wareList.add(wgod);
		}
		result.setData(wareList);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult addAccessoryHistory(AccessoryHistory sh) {
		CyResult result = new CyResult();
		if (sh == null) {
			result.setMsg("不能传空对象");
			result.setState(1);
			return result;
		}
		Map<String, Object> oMap = new HashMap<>();
		oMap.put("orderNumber", sh.getOrderNumber());
		oMap.put("mid", sh.getMid());
		Integer oid = tvdao.searchOid(oMap);
		sh.setOid(oid);
		String time = CyUtil.getTime();
		sh.setPtime(time);
		int asd = tvdao.insertflh(sh);
		if (asd == 1) {
			result.setMsg("添加成功");
			result.setState(0);
			return result;
		}
		result.setMsg("添加失败");
		result.setState(2);
		return result;
	}

	@Override
	public CyResult removeAccessoryHistory(Integer id) {
		CyResult result = new CyResult();
		int qaz = tvdao.deleteflh(id);
		if (qaz > 0) {
			result.setMsg("删除成功");
			result.setState(0);
			return result;
		}
		result.setMsg("删除失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult loadAccessoryWarByIds(String ids) {
		CyResult result = new CyResult();
		if (ids == null) {
			result.setState(0);
			result.setMsg("ids不能为空!");
			return result;
		}
		List<AccessoryHistory> list = new ArrayList<AccessoryHistory>();
		String[] idstr = ids.split(",");
		for (String id : idstr) {
			AccessoryHistory wh = tvdao.findAccessoryHistoryById(Integer.parseInt(id));
			Integer oid = wh.getOid();
			OrderList order = tvdao.findOrderid(oid);
			if (order == null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", wh.getId());
				wh.setNumbers(null);
				wh.setOrderNumber("");
				wh.setOutNumber(Integer.parseInt(tvdao.outflhOrderNumNoOrder(map)));
				wh.setResidue(null);
			} else {
				Map<String, Object> map = new HashMap<String, Object>();
				wh.setNumbers(order.getNumbers());
				wh.setOrderNumber(order.getOrderNumber());
				map.put("oid", order.getId());
				map.put("id", wh.getId());
				String outsumString = tvdao.outflhOrderNum(map);
				if (outsumString == null) {
					wh.setOutNumber(null);
					wh.setResidue(order.getNumbers());
				} else {
					int outsum = Integer.parseInt(outsumString);
					int yushu = order.getNumbers() - outsum;
					wh.setOutNumber(outsum);
					wh.setResidue(yushu);
				}

			}
			list.add(wh);
		}
		result.setData(list);
		result.setMsg("加载成功");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult updateAccessoryHistory(AccessoryHistory history) {
		CyResult result = new CyResult();
		try {
			MaterialManage mmone = tvdao.searchMMpn(history.getPn());
			Map<String, Object> oMap = new HashMap<>();
			oMap.put("orderNumber", history.getOrderNumber());
			oMap.put("mid", mmone.getId());
			Integer oid = tvdao.searchOid(oMap);
			history.setOid(oid);
			int a = tvdao.updateflHistory(history);
			result.setMsg("修改成功!");
			result.setState(0);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("异常!");
		}
	}

	@Override
	public CyResult loadDumpster(Integer pageNumber) {
		CyResult result = new CyResult();
		List<MaterialManage> loadmm = tvdao.loadzfMM();
		if (pageNumber == null) {
			pageNumber = 0;
		} else {
			pageNumber = (pageNumber - 1) * 15;
		}
		List<MaterialManage> mms = tvdao.loadpagezfMM(pageNumber);
		List<Warehouse> whList = new ArrayList<Warehouse>();
		for (MaterialManage mate : mms) {
			Warehouse war = new Warehouse();
			String outNumString = tvdao.outzfh(mate.getId());// 出库数量
			String putNumString = tvdao.putzfh(mate.getId());// 入库数量
			if (outNumString == null) {
				outNumString = "0";
			}
			if (putNumString == null) {
				putNumString = "0";
			}
			int outNum = Integer.parseInt(outNumString);
			int putNum = Integer.parseInt(putNumString);
			int yushu = putNum - outNum;// 余数
			war.setId(mate.getId());
			war.setResidue(yushu);
			war.setProductName(mate.getMaterialName());
			war.setPn(mate.getPn());
			war.setWeight(mate.getWeight());
			war.setSpecification(mate.getMexplain());
			war.setOutNumber(outNum);
			war.setPutNumber(putNum);
			whList.add(war);
		}
		if (mms.size() < 1) {
			result.setMsg("暂无数据");
			result.setState(3);
			return result;
		}
		result.setState(1);
		result.setData(loadmm.size());
		result.setData1(whList);
		result.setMsg("加载成功");
		return result;
	}

	@Override
	public CyResult loadDumpsterHistory(Integer mid) {
		CyResult result = new CyResult();
		List<DumpsterHistory> whList = tvdao.loadzfh(mid);
		MaterialManage loadmm = tvdao.searchMate(mid);
		if (loadmm == null) {
			result.setMsg("无材料数据");
			result.setState(2);
			return result;
		}
		if (whList.size() < 1) {
			result.setState(1);
			result.setMsg("暂无出入库记录");
			return result;
		}
		List<DumpsterHistory> wareList = new ArrayList<>();
		for (DumpsterHistory wgod : whList) {
			OrderList ordersss = tvdao.findOrderid(wgod.getOid());
			if (ordersss != null) {
				wgod.setOrderNumber(ordersss.getOrderNumber());
			} else {
				wgod.setOrderNumber("");
			}
			wgod.setMaterialName(loadmm.getMaterialName());
			wgod.setPn(loadmm.getPn());
			wareList.add(wgod);
		}
		result.setData(wareList);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult addDumpsterHistory(DumpsterHistory sh) {
		CyResult result = new CyResult();
		if (sh == null) {
			result.setMsg("不能传空对象");
			result.setState(1);
			return result;
		}
		Map<String, Object> oMap = new HashMap<>();
		oMap.put("orderNumber", sh.getOrderNumber());
		oMap.put("mid", sh.getMid());
		Integer oid = tvdao.searchOid(oMap);
		sh.setOid(oid);
		String time = CyUtil.getTime();
		sh.setPtime(time);
		int asd = tvdao.insertzfh(sh);
		if (asd == 1) {
			result.setMsg("添加成功");
			result.setState(0);
			return result;
		}
		result.setMsg("添加失败");
		result.setState(2);
		return result;
	}

	@Override
	public CyResult removeDumpsterHistory(Integer id) {
		CyResult result = new CyResult();
		int qaz = tvdao.deletezfh(id);
		if (qaz > 0) {
			result.setMsg("删除成功");
			result.setState(0);
			return result;
		}
		result.setMsg("删除失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult loadDumpsterWarByIds(String ids) {
		CyResult result = new CyResult();
		if (ids == null) {
			result.setState(0);
			result.setMsg("ids不能为空!");
			return result;
		}
		List<DumpsterHistory> list = new ArrayList<DumpsterHistory>();
		String[] idstr = ids.split(",");
		for (String id : idstr) {
			DumpsterHistory wh = tvdao.findDumpsterHistoryById(Integer.parseInt(id));
			Integer oid = wh.getOid();
			OrderList order = tvdao.findOrderid(oid);
			if (order == null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", wh.getId());
				wh.setNumbers(null);
				wh.setOrderNumber("");
				wh.setOutNumber(Integer.parseInt(tvdao.outzfhOrderNumNoOrder(map)));
				wh.setResidue(null);
			} else {
				Map<String, Object> map = new HashMap<String, Object>();
				wh.setNumbers(order.getNumbers());
				wh.setOrderNumber(order.getOrderNumber());
				map.put("oid", order.getId());
				map.put("id", wh.getId());
				String outsumString = tvdao.outzfhOrderNum(map);
				if (outsumString == null) {
					wh.setOutNumber(null);
					wh.setResidue(order.getNumbers());
				} else {
					int outsum = Integer.parseInt(outsumString);
					int yushu = order.getNumbers() - outsum;
					wh.setOutNumber(outsum);
					wh.setResidue(yushu);
				}

			}
			list.add(wh);
		}
		result.setData(list);
		result.setMsg("加载成功");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult updateDumpsterHistory(DumpsterHistory history) {
		CyResult result = new CyResult();
		try {
			MaterialManage mmone = tvdao.searchMMpn(history.getPn());
			Map<String, Object> oMap = new HashMap<>();
			oMap.put("orderNumber", history.getOrderNumber());
			oMap.put("mid", mmone.getId());
			Integer oid = tvdao.searchOid(oMap);
			history.setOid(oid);
			int a = tvdao.updatezfHistory(history);
			result.setMsg("修改成功!");
			result.setState(0);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("异常!");
		}
	}

	@Override
	public CyResult loadCodingList(CodingList codine) {
		CyResult result = new CyResult();
		int count = tvdao.loadAllCodingList(codine);
		int pageSize = (codine.getPage() - 1) * codine.getLimit();
		codine.setPageSize(pageSize);
		List<CodingList> codings = tvdao.loadCodingList(codine);
		result.setData(codings);
		result.setCount(count);
		result.setMsg("加载成功");
		result.setCode(0);
		result.setState(0);
		return result;
	}

	@Override
	public CyResult addCodingList(CodingList codingList) {
		CyResult result = new CyResult();
		if (codingList == null) {
			result.setMsg("对象问空");
			result.setState(1);
			return result;
		}
		List<CodingList> codingLists = tvdao.searchNameCoding(codingList.getPname());
		if (codingLists.size() > 0) {
			result.setMsg("已有这个品名的编号");
			result.setState(2);
			return result;
		}
		List<CodingList> codingNo = tvdao.codingNumberCoding(codingList.getNumber());
		if (codingNo.size() > 0) {
			result.setMsg("已有这个品名的编号");
			result.setState(2);
			return result;
		}
		codingList.setCreamTime(CyUtil.getTime());
		int asd = tvdao.addCodingList(codingList);
		if (asd == 1) {
			result.setMsg("增加成功");
			result.setState(0);
			return result;
		}
		result.setMsg("增加失败");
		result.setState(3);
		return result;
	}

	@Override
	public CyResult updateCodingList(CodingList codingList) {
		CyResult result = new CyResult();
		int qwe = tvdao.updateCodingList(codingList);
		if (qwe == 1) {
			result.setMsg("修改成功");
			result.setState(0);
			return result;
		}
		result.setMsg("修改失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult deleteCodingList(Integer id) {
		CyResult result = new CyResult();
		int zxc = tvdao.delCodingList(id);
		if (zxc == 1) {
			result.setMsg("删除成功");
			result.setState(0);
			return result;
		}
		result.setMsg("删除失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult searchCodingList(String number) {
		CyResult result = new CyResult();
		List<CodingList> coding = tvdao.codingNumberCoding(number);
		if (coding.size() < 1) {
			result.setMsg("没有这个编号");
			result.setState(1);
			return result;
		}
		result.setData(coding);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult loadMakerList(MakerList maker) {
		CyResult result = new CyResult();
		if (maker.getPage() == null) {
			maker.setPage(1);
		}
		maker.setPageSize((maker.getPage() - 1) * maker.getLimit());
		int count = tvdao.allMakerList(maker);
		List<MakerList> makerList = tvdao.loadMakerList(maker);
		if (makerList.size() < 1) {
			result.setMsg("无数据");
			result.setState(1);
			return result;
		}
		result.setData(makerList);
		result.setCount(count);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult addMakerList(HttpServletRequest request, MakerList makerList) throws Exception {
		CyResult result = new CyResult();
		List<MakerList> no = tvdao.loadMakerListNo(makerList.getMakerNo());
		if (no.size() > 0) {
			result.setMsg("已有这个厂商编号");
			result.setState(1);
			return result;
		}
		List<MakerList> names = tvdao.loadMakerListName(makerList.getMakerName());
		if (names.size() > 0) {
			result.setMsg("已有这个厂商名字");
			result.setState(2);
			return result;
		}
		makerList.setCreamTime(CyUtil.getTime());
		/*
		 * if(files!=null){ // 保存图片地址 String imaUrl = ""; // 保存图片名字 String
		 * imgName = ""; // 文件夹名字 String folderName = "syyPhoto"; // 接受上传结果
		 * Map<String, Object> map = null; map = UploadUtil.upload(files,
		 * request, folderName);
		 * 
		 * // 获取上传文件二进制 File targetFile = (File) map.get("targetFile"); // //
		 * 判断是否为图片 // if (!(targetFile == null)) { // BufferedImage bi =
		 * ImageIO.read(targetFile); // if (bi != null) { // imaUrl =
		 * map.get("url").toString() + folderName + "/" +
		 * map.get("filename").toString(); // imgName =
		 * map.get("filename").toString(); // } // } imaUrl =
		 * map.get("url").toString() + folderName + "/" +
		 * map.get("filename").toString(); makerList.setPapers(imaUrl); }
		 */
		int qaz = tvdao.addMakerList(makerList);
		if (qaz == 1) {
			result.setMsg("增加成功");
			result.setState(0);
			return result;
		}
		result.setMsg("增加失败");
		result.setState(3);
		return result;
	}

	@Override
	public CyResult updateMakerList(HttpServletRequest request, MakerList makerList) throws Exception {
		CyResult result = new CyResult();

		int row = tvdao.updateMakerList(makerList);
		if (row == 1) {
			result.setMsg("修改成功");
			result.setState(0);
			return result;
		}
		result.setMsg("修改失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult deleteMakerList(Integer id) {
		CyResult result = new CyResult();
		int bow = tvdao.deleteMakerList(id);
		if (bow == 1) {
			result.setMsg("删除成功");
			result.setState(0);
			return result;
		}
		result.setMsg("删除失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult searchMakerList(String makerName) {
		CyResult result = new CyResult();
		List<MakerList> maker = tvdao.searchMakerName(makerName);
		if (maker.size() < 1) {
			result.setMsg("未找到数据");
			result.setState(1);
			return result;
		}
		result.setData(maker);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult loadClientList(Integer page, Integer limit) {
		CyResult result = new CyResult();
		int count = tvdao.allclientList();
		if (page == null) {
			page = 1;
		}
		int pageSize = (page - 1) * limit;
		ClientList cl = new ClientList();
		cl.setPageSize(pageSize);
		cl.setLimit(limit);
		List<ClientList> clientList = tvdao.loadClientList(cl);
		if (clientList.size() < 1) {
			result.setMsg("无数据");
			result.setState(1);
			return result;
		}
		result.setData(clientList);
		result.setCount(count);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult addClientList(HttpServletRequest request, ClientList clientList) throws Exception {
		CyResult result = new CyResult();
		List<ClientList> no = tvdao.loadClientListNo(clientList.getClientNo());
		if (no.size() > 0) {
			result.setMsg("已有这个客户编号");
			result.setState(1);
			return result;
		}
		List<ClientList> names = tvdao.loadClientListName(clientList.getClientName());
		if (names.size() > 0) {
			result.setMsg("已有这个客户名字");
			result.setState(2);
			return result;
		}
		/*
		 * if(files!=null){ // 保存图片地址 String imaUrl = ""; // 保存图片名字 String
		 * imgName = ""; // 文件夹名字 String folderName = "syyPhoto"; // 接受上传结果
		 * Map<String, Object> map = null; map = UploadUtil.upload(files,
		 * request, folderName);
		 * 
		 * // 获取上传文件二进制 // File targetFile = (File) map.get("targetFile");
		 * imaUrl = map.get("url").toString() + folderName + "/" +
		 * map.get("filename").toString(); clientList.setPapers(imaUrl); }
		 */
		clientList.setCreamTime(CyUtil.getTime());
		int qaz = tvdao.addClientList(clientList);
		if (qaz == 1) {
			result.setMsg("增加成功");
			result.setState(0);
			return result;
		}
		result.setMsg("增加失败");
		result.setState(3);
		return result;
	}

	@Override
	public CyResult updateClientList(HttpServletRequest request, ClientList clientList) throws Exception {
		CyResult result = new CyResult();
		/*
		 * if(files!=null){ // 保存图片地址 String imaUrl = ""; // 保存图片名字 String
		 * imgName = ""; // 文件夹名字 String folderName = "syyPhoto"; // 接受上传结果
		 * Map<String, Object> map = null; map = UploadUtil.upload(files,
		 * request, folderName);
		 * 
		 * // 获取上传文件二进制 // File targetFile = (File) map.get("targetFile");
		 * imaUrl = map.get("url").toString() + folderName + "/" +
		 * map.get("filename").toString(); clientList.setPapers(imaUrl); }
		 */
		int row = tvdao.updateClientList(clientList);
		if (row == 1) {
			result.setMsg("修改成功");
			result.setState(0);
			return result;
		}
		result.setMsg("修改失败");
		result.setState(1);
		return result;

	}

	@Override
	public CyResult deleteClientList(Integer id) {
		CyResult result = new CyResult();
		int bow = tvdao.deleteClientList(id);
		if (bow == 1) {
			result.setMsg("删除成功");
			result.setState(0);
			return result;
		}
		result.setMsg("删除失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult searchClientList(String clientName) {
		CyResult result = new CyResult();
		List<ClientList> maker = tvdao.searchClientName(clientName);
		if (maker.size() < 1) {
			result.setMsg("未找到数据");
			result.setState(1);
			return result;
		}
		result.setData(maker);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult loadAllClientList() {
		CyResult result = new CyResult();
		List<ClientList> allClient = tvdao.loadAllClientList();
		if (allClient.size() < 1) {
			result.setMsg("无数据");
			result.setState(1);
			return result;
		}
		result.setData(allClient);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	public CyResult loadClientListId(Integer id) {
		CyResult result = new CyResult();
		ClientList client = tvdao.loadClientId(id);
		if (client == null) {
			result.setMsg("该客户不存在");
			result.setState(1);
			return result;
		}
		result.setData(client);
		result.setState(0);
		return result;
	}

	@Override
	public CyResult searchWarehouse(String pn) {
		CyResult result = new CyResult();
		if (pn == null) {
			result.setMsg("不能为空");
			result.setState(2);
			return result;
		}
		MaterialManage mate = tvdao.searchwh(pn);
		if (mate == null) {
			result.setMsg("没有这个料号");
			result.setState(1);
			return result;
		}
		List<Warehouse> whList = new ArrayList<>();
		Warehouse war = new Warehouse();
		String outNumString = tvdao.outwh(mate.getId());// 出库数量
		String putNumString = tvdao.putwh(mate.getId());// 入库数量
		if (outNumString == null) {
			outNumString = "0";
		}
		if (putNumString == null) {
			putNumString = "0";
		}
		int outNum = Integer.parseInt(outNumString);
		int putNum = Integer.parseInt(putNumString);
		int yushu = putNum - outNum;// 余数
		war.setId(mate.getId());
		war.setResidue(yushu);
		war.setProductName(mate.getMaterialName());
		war.setPn(mate.getPn());
		war.setWeight(mate.getWeight());
		war.setSpecification(mate.getMexplain());
		war.setOutNumber(outNum);
		war.setPutNumber(putNum);
		if (war != null) {
			whList.add(war);
			result.setData(whList);
			result.setMsg("搜索成功");
			result.setState(0);
		}
		return result;
	}

	@Override
	public CyResult searchProduct(String pn) {
		CyResult result = new CyResult();
		if (pn == null) {
			result.setMsg("不能为空");
			result.setState(2);
			return result;
		}
		MaterialManage mate = tvdao.searchph(pn);
		if (mate == null) {
			result.setMsg("没有这个料号");
			result.setState(1);
			return result;
		}
		List<Warehouse> whList = new ArrayList<>();
		Warehouse war = new Warehouse();
		String outNumString = tvdao.outph(mate.getId());// 出库数量
		String putNumString = tvdao.putph(mate.getId());// 入库数量
		if (outNumString == null) {
			outNumString = "0";
		}
		if (putNumString == null) {
			putNumString = "0";
		}
		int outNum = Integer.parseInt(outNumString);
		int putNum = Integer.parseInt(putNumString);
		int yushu = putNum - outNum;// 余数
		war.setId(mate.getId());
		war.setResidue(yushu);
		war.setProductName(mate.getMaterialName());
		war.setPn(mate.getPn());
		war.setWeight(mate.getWeight());
		war.setSpecification(mate.getMexplain());
		war.setOutNumber(outNum);
		war.setPutNumber(putNum);
		if (war != null) {
			whList.add(war);
			result.setData(whList);
			result.setMsg("搜索成功");
			result.setState(0);
		}
		return result;
	}

	@Override
	public CyResult loadMaker(Integer id) {
		CyResult result = new CyResult();
		MakerList maker = tvdao.searchMaker(id);
		if (maker == null) {
			result.setMsg("不存在");
			result.setState(1);
			return result;
		}
		result.setData(maker);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult loadRequestList(RequestList reqDemo) {
		CyResult result = new CyResult();
		int count = tvdao.loadAllCountRequestList();
		reqDemo.setPageSize((reqDemo.getPage() - 1) * reqDemo.getLimit());
		List<RequestList> listRequest = tvdao.loadReqestList(reqDemo);

		result.setData(listRequest);
		result.setCount(count);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult addRequestList(RequestList requestList) {
		CyResult result = new CyResult();
		RequestList request = tvdao.loadRequestNumber(requestList.getSerialNumber());
		if (request != null) {
			result.setMsg("该编号已存在");
			result.setState(2);
			return result;
		}
		int row = tvdao.addRequestList(requestList);
		if (row == 1) {
			result.setMsg("增加成功");
			result.setState(0);
			return result;
		}
		result.setMsg("增加失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult updateRequestList(RequestList requestList) {
		CyResult result = new CyResult();
		int row = tvdao.updateRequestList(requestList);
		if (row == 1) {
			result.setMsg("修改成功");
			result.setState(0);
			return result;
		}
		result.setMsg("修改失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult deleteRequestList(Integer id) {
		CyResult result = new CyResult();
		int row = tvdao.deleteRequestList(id);
		if (row == 1) {
			result.setMsg("删除成功");
			result.setState(0);
			return result;
		}
		result.setMsg("删除失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult loadCompanyList(CompanyList company) {
		CyResult result = new CyResult();
		if (company.getPage() == null) {
			company.setPage(1);
		}
		company.setPageSize((company.getPage() - 1) * company.getLimit());
		int count = tvdao.allCompanyList(company);
		List<CompanyList> companyList = tvdao.loadCompanyList(company);
		if (companyList.size() < 1) {
			result.setMsg("无数据");
			result.setState(1);
			return result;
		}
		result.setData(companyList);
		result.setCount(count);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult addCompanyList(HttpServletRequest request, CompanyList companyList) {
		CyResult result = new CyResult();
		List<CompanyList> no = tvdao.loadCompanyListNo(companyList.getCompanyNo());
		if (no.size() > 0) {
			result.setMsg("已有这个公司编号");
			result.setState(1);
			return result;
		}
		companyList.setCreamTime(CyUtil.getTime());

		int qaz = tvdao.addCompanyList(companyList);
		if (qaz == 1) {
			result.setMsg("增加成功");
			result.setState(0);
			return result;
		}
		result.setMsg("增加失败");
		result.setState(3);
		return result;
	}

	@Override
	public CyResult loadAllMakerIdName() {
		CyResult result = new CyResult();
		List<MakerList> makerid = tvdao.allMakerIdName();
		if (makerid.size() < 1) {
			result.setMsg("暂无数据");
			result.setState(1);
			return result;
		}
		result.setData(makerid);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult loadAllCompanyIdName() {
		CyResult result = new CyResult();
		List<CompanyList> companyid = tvdao.allCompanyIdName();
		if (companyid.size() < 1) {
			result.setMsg("暂无数据");
			result.setState(1);
			return result;
		}
		result.setData(companyid);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult loadAllRequestListId(RequestList req) {
		CyResult result = new CyResult();
		List<RequestList> requestId = tvdao.allRequestId(req);
		if (requestId.size() < 1) {
			result.setMsg("暂无数据");
			result.setState(1);
			return result;
		}
		result.setData(requestId);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult updateCompanyList(HttpServletRequest request, CompanyList companyList) {
		CyResult result = new CyResult();

		int row = tvdao.updateCompanyList(companyList);
		if (row == 1) {
			result.setMsg("修改成功");
			result.setState(0);
			return result;
		}
		result.setMsg("修改失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult loadRequestListId(Integer id) {
		CyResult result = new CyResult();
		RequestList requestId = tvdao.loadRequestId(id);
		if (requestId == null) {
			result.setMsg("不存在");
			result.setState(1);
			return result;
		}
		result.setData(requestId);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult purchasePrint(Integer id1, Integer id2, Integer id3, Integer thisId) {
		CyResult result = new CyResult();
		MakerList maker = tvdao.searchMaker(id1);
		CompanyList company = tvdao.loadCompanyId(id2);
		RequestList request = tvdao.loadRequestId(id3);
		PurchaseList purchase = tvdao.loadPl(thisId);
		if (purchase == null) {
			result.setMsg("采购单不存在");
			result.setState(2);
			return result;
		}
		List<DemandList> demandLists = tvdao.loadDemandLists(purchase.getPurchaseOrders());
		if (demandLists.size() < 1) {
			result.setMsg("无数据");
			result.setState(1);
			return result;
		}
		List<DemandList> demands = new ArrayList<>();
		for (DemandList demand : demandLists) {
			if (demand.getMid() != null) {
				MaterialManage loadmm = tvdao.searchMate(demand.getMid());
				if (loadmm != null) {
					demand.setPname(loadmm.getMaterialName());
					demand.setPn(loadmm.getPn());
					demand.setMexplain(loadmm.getMexplain());
				}

				// demand.setDemandNumber(purchase.getDemandNumber());
				if (demand.getJprice() == null) {
					demand.setDmoney(0.0);
				} else {
					demand.setDmoney(demand.getJprice() * demand.getDemandNumber());
				}
				demands.add(demand);
			}
		}
		result.setData(maker);
		result.setData1(company);
		result.setData2(request);
		result.setData3(demands);
		result.setData4(purchase);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult searchCompanyListId(Integer id) {
		CyResult result = new CyResult();
		CompanyList company = tvdao.loadCompanyId(id);
		if (company == null) {
			result.setMsg("不存在");
			result.setState(1);
			return result;
		}
		result.setData(company);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult deleteCompany(Integer id) {
		CyResult result = new CyResult();
		int row = tvdao.deleteCompanyList(id);
		if (row == 1) {
			result.setMsg("删除成功");
			result.setState(0);
			return result;
		}
		result.setMsg("删除失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult searchCompany(String companyName) {
		CyResult result = new CyResult();
		List<CompanyList> company = tvdao.searchCompanyName(companyName);
		if (company.size() < 1) {
			result.setMsg("不存在");
			result.setState(1);
			return result;
		}
		result.setData(company);
		result.setMsg("搜索成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult placeMachiningManage(Integer id, Double demandNumber, String site, String deliveryTime,
			Integer sort, Integer technology) {
		CyResult result = new CyResult();
		MaterialManage loadmm = tvdao.searchMate(id);
		if (loadmm == null) {
			result.setMsg("请录入材料");
			result.setState(1);
			return result;
		}
		String oldpo = tvdao.searchPO(CyUtil.getTime2());
		PurchaseList purchase = new PurchaseList();
		if (oldpo == null) {
			String purchaseOrders = "JC" + CyUtil.getTime3() + "001";
			PriceList price = tvdao.sreachPrice(id);
			DemandList demand = new DemandList();
			demand.setMid(loadmm.getId());
			demand.setCreamTime(CyUtil.getTime());
			demand.setDemandNumber(demandNumber);
			demand.setDeliveryTime(deliveryTime);
			if (price != null) {
				if (technology == 0) {
					demand.setJprice(price.getProcessCost());
					demand.setDmoney(demand.getDemandNumber() * price.getProcessCost());
				} else if (technology == 1) {
					demand.setJprice(price.getSampleCost());
					demand.setDmoney(demand.getDemandNumber() * price.getSampleCost());
				}

			}
			demand.setPurchaseOrders(purchaseOrders);
			System.out.println("demand:"+demand);
			int sf = tvdao.addDemandList(demand);
			if (sf == 1) {
				double sums = tvdao.sumPrice(purchaseOrders);
				purchase.setBid(id);
				purchase.setDemandNumber(demandNumber);
				purchase.setPurchaseOrders(purchaseOrders);
				purchase.setOperator("");
				purchase.setMaterialNumber(1);
				purchase.setTotalMoney(sums);
				purchase.setCreamTime(CyUtil.getTime2());
				purchase.setStuat(1);
				purchase.setOrderStuat(1);
				purchase.setSite(site);
				purchase.setDeliveryTime(deliveryTime);
				purchase.setSort(sort);
				purchase.setTechnology(technology);
			}
		} else {
			String oldpostr = oldpo.substring(10, 13);
			int ops = Integer.parseInt(oldpostr) + 1;
			DecimalFormat df = new DecimalFormat("000");
			String s = df.format(ops);
			String purchaseOrders = "JC" + CyUtil.getTime3() + s;
			PriceList price = tvdao.sreachPrice(id);
			DemandList demand = new DemandList();
			demand.setMid(loadmm.getId());
			demand.setCreamTime(CyUtil.getTime());
			demand.setDemandNumber(demandNumber);
			demand.setDeliveryTime(deliveryTime);
			if (price != null) {
				if (technology == 0) {
					demand.setJprice(price.getProcessCost());
					demand.setDmoney(demand.getDemandNumber() * price.getProcessCost());
				} else if (technology == 1) {
					demand.setJprice(price.getSampleCost());
					demand.setDmoney(demand.getDemandNumber() * price.getSampleCost());
				}
			}
			demand.setPurchaseOrders(purchaseOrders);
			System.out.println("demond:"+demand);
			int sf = tvdao.addDemandList(demand);
			if (sf == 1) {
				double sums = tvdao.sumPrice(purchaseOrders);
				purchase.setBid(id);
				purchase.setDemandNumber(demandNumber);
				purchase.setPurchaseOrders(purchaseOrders);
				purchase.setOperator("");
				purchase.setMaterialNumber(1);
				purchase.setTotalMoney(sums);
				purchase.setCreamTime(CyUtil.getTime2());
				purchase.setStuat(1);
				purchase.setOrderStuat(1);
				purchase.setSite(site);
				purchase.setDeliveryTime(deliveryTime);
				purchase.setSort(sort);
				purchase.setTechnology(technology);
			}
		}
		int cx = tvdao.addPurchaseList(purchase);
		if (cx == 1) {
			result.setMsg("下单成功");
			result.setState(0);
			return result;
		}
		result.setMsg("下单失败");
		result.setState(4);
		return result;
	}

	@Override
	public CyResult addDemandList(Integer id, Integer mid, Double demandNumber, String deliveryTime) {
		CyResult result = new CyResult();
		PurchaseList purchase = tvdao.loadPl(id);
		DemandList demand = new DemandList();
		if (purchase == null) {
			result.setMsg("采购单不存在");
			result.setState(1);
			return result;
		}
		MaterialManage loadmm = tvdao.searchMate(mid);
		if (loadmm == null) {
			result.setMsg("请去料号表录入料号");
			result.setState(2);
			return result;
		}
		PriceList price = tvdao.sreachPrice(loadmm.getId());
		if (price == null) {
			result.setMsg("请去价格表录入料号为:" + loadmm.getPn() + "的价格");
			result.setState(3);
			return result;
		}

		if (purchase.getSort() == 2) {
			if (price.getJprice() == null) {
				demand.setJprice(0.0);
			} else {
				demand.setJprice(price.getJprice());
			}
		} else if (purchase.getSort() == 3) {
			if (purchase.getTechnology() == 1) {
				demand.setJprice(price.getProcessCost());
			} else if (purchase.getTechnology() == 2) {
				demand.setJprice(price.getSampleCost());
			}
		}
		demand.setMid(mid);
		demand.setDemandNumber(demandNumber);
		if (price.getJprice() == null) {
			demand.setDmoney(0.0);
		} else {
			demand.setDmoney(demandNumber * price.getJprice());
		}
		demand.setPurchaseOrders(purchase.getPurchaseOrders());
		demand.setCreamTime(CyUtil.getTime());
		demand.setDeliveryTime(deliveryTime);
		int sf = tvdao.addDemandList(demand);
		if (sf == 1) {
			DemandList mand = tvdao.searchDemandListId(demand.getId());
			mand.setPname(loadmm.getMaterialName());
			mand.setPn(loadmm.getPn());
			result.setData(mand);
			int sum = tvdao.sumDemandList(mand.getPurchaseOrders());
			double sums = tvdao.sumPrice(purchase.getPurchaseOrders());
			Map<String, Object> map = new HashMap<>();
			map.put("materialNumber", sum);
			map.put("purchaseOrders", mand.getPurchaseOrders());
			map.put("totalMoney", sums);
			tvdao.updateSumPurchaseList(map);
			result.setMsg("添加成功");
			result.setState(0);
			return result;
		}
		result.setMsg("添加失败");
		result.setState(4);
		return result;
	}

	@Override
	public CyResult loadOrdersByMid(Integer mid) {
		CyResult result = new CyResult();
		List<String> list = tvdao.loadOrdersByMid(mid);
		result.setCode(0);
		result.setData(list);
		result.setMsg("success!");
		return result;
	}

	@Override
	public CyResult loadReportPriceList(ReportPrice rp) {
		CyResult result = new CyResult();
		if (rp.getPage() == null) {
			rp.setPage(1);
		}
		rp.setPageSize((rp.getPage() - 1) * rp.getLimit());
		int count = tvdao.loadAllReportPriceListCount(rp);
		List<ReportPrice> reportPriceList = tvdao.loadReportPriceList(rp);
		if (reportPriceList.size() < 1) {
			result.setMsg("无数据");
			result.setState(1);
			return result;
		}
		List<ReportPrice> purList = new ArrayList<ReportPrice>();
		for (ReportPrice reportPrice : reportPriceList) {

			BomList bom = tvdao.loadBom(reportPrice.getBid());
			if (bom == null) {
				MaterialManage loadmm = tvdao.searchMate(reportPrice.getBid());
				if (loadmm != null) {
					reportPrice.setPname(loadmm.getMaterialName());
					reportPrice.setPn(loadmm.getPn());
				}
				purList.add(reportPrice);
			} else {
				reportPrice.setPname(bom.getPname());
				reportPrice.setPn(bom.getPn());
				purList.add(reportPrice);
			}

		}
		result.setData(purList);
		result.setCount(count);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult pricePlaceOrder(Integer bid, Double demandNumber, String site, String deliveryTime, Integer sort) {
		CyResult result = new CyResult();
		BomList bom = tvdao.loadBom(bid);
		if (bom == null) {
			result.setMsg("未找到该料号");
			result.setState(1);
			return result;
		}
		List<BomMaterials> bmList = tvdao.loadBM(bom.getId());
		if (bmList.size() < 1) {
			result.setMsg("请添加材料");
			result.setState(2);
			return result;
		}
		String oldpo = tvdao.searchPOJO(CyUtil.getTime2());
		ReportPrice purchase = new ReportPrice();
		String purchaseOrders = null;
		if (oldpo == null) {
			purchaseOrders = "JC" + CyUtil.getTime3() + "001";
		} else {
			String oldpostr = oldpo.substring(10, 13);
			int ops = Integer.parseInt(oldpostr) + 1;
			DecimalFormat df = new DecimalFormat("000");
			String s = df.format(ops);
			purchaseOrders = "JC" + CyUtil.getTime3() + s;
		}
		for (BomMaterials bomm : bmList) {
			MaterialManage loadmm = tvdao.searchMate(bomm.getMid());
			if (loadmm != null) {
				PriceList price = tvdao.sreachPrice(bomm.getMid());
				String outNumString = tvdao.outwh(bomm.getMid());// 出库数量
				String putNumString = tvdao.putwh(bomm.getMid());// 入库数量
				if (outNumString == null) {
					outNumString = "0";
				}
				if (putNumString == null) {
					putNumString = "0";
				}
				int outNum = Integer.parseInt(outNumString);
				int putNum = Integer.parseInt(putNumString);
				int yushu = putNum - outNum;// 库存
				ReportDemandList demand = new ReportDemandList();
				demand.setMid(loadmm.getId());
				demand.setBid(bom.getId());
				demand.setCreamTime(CyUtil.getTime());
				demand.setDemandNumber(bomm.getUseNumer() * demandNumber - yushu);
				demand.setDeliveryTime(deliveryTime);
				if (price != null) {
					demand.setJprice(price.getJprice());
					demand.setDmoney(demand.getDemandNumber() * price.getJprice());
				}
				demand.setPurchaseOrders(purchaseOrders);
				int sf = tvdao.addPriceDemandList(demand);
				if (sf == 1) {
					double sums = tvdao.sumReportPrice(purchaseOrders);
					purchase.setBid(bid);
					purchase.setDemandNumber(demandNumber);
					purchase.setPurchaseOrders(purchaseOrders);
					purchase.setOperator("");
					purchase.setMaterialNumber(bmList.size());
					purchase.setTotalMoney(sums);
					purchase.setCreamTime(CyUtil.getTime());
					purchase.setStuat(1);
					purchase.setOrderStuat(1);
					purchase.setSite(site);
					purchase.setDeliveryTime(deliveryTime);
					purchase.setSort(sort);
				}

			}

		}

		int cx = tvdao.addReportList(purchase);
		if (cx == 1) {
			result.setMsg("下单成功");
			result.setState(0);
			return result;
		}
		result.setMsg("下单失败");
		result.setState(4);
		return result;
	}

	@Override
	public CyResult priceMaterialManage(Integer id, Double demandNumber, String site, String deliveryTime,
			Integer sort) {
		
		CyResult result = new CyResult();
		MaterialManage loadmm = tvdao.searchMate(id);
		if (loadmm == null) {
			result.setMsg("请录入材料");
			result.setState(1);
			return result;
		}
		String oldpo = tvdao.searchPOJO(CyUtil.getTime2());
		ReportPrice purchase = new ReportPrice();
		String purchaseOrders = null;
		if (oldpo == null) {
			purchaseOrders = "JC" + CyUtil.getTime3() + "001";
		} else {
			String oldpostr = oldpo.substring(10, 13);
			int ops = Integer.parseInt(oldpostr) + 1;
			DecimalFormat df = new DecimalFormat("000");
			String s = df.format(ops);
			purchaseOrders = "JC" + CyUtil.getTime3() + s;
		}
		PriceList price = tvdao.sreachPrice(id);
		ReportDemandList demand = new ReportDemandList();
		demand.setMid(loadmm.getId());
		demand.setCreamTime(CyUtil.getTime());
		demand.setDemandNumber(demandNumber);
		demand.setDeliveryTime(deliveryTime);
		if (price != null) {
			demand.setJprice(price.getJprice());
			demand.setDmoney(demand.getDemandNumber() * price.getJprice());
		}
		demand.setPurchaseOrders(purchaseOrders);
		int sf = tvdao.addPriceDemandList(demand);
		if (sf == 1) {
			double sums = tvdao.sumReportPrice(purchaseOrders);
			purchase.setBid(id);
			purchase.setDemandNumber(demandNumber);
			purchase.setPurchaseOrders(purchaseOrders);
			purchase.setOperator("");
			purchase.setMaterialNumber(1);
			purchase.setTotalMoney(sums);
			purchase.setCreamTime(CyUtil.getTime2());
			purchase.setStuat(1);
			purchase.setOrderStuat(1);
			purchase.setSite(site);
			purchase.setDeliveryTime(deliveryTime);
			purchase.setSort(sort);
		}

		int cx = tvdao.addReportList(purchase);
		if (cx == 1) {
			result.setMsg("下单成功");
			result.setState(0);
			return result;
		}
		result.setMsg("下单失败");
		result.setState(4);
		return result;
	}

	

	@Override
	public CyResult loadPriceReportDemand(ReportPrice reportPrice) {
		CyResult result = new CyResult();
		ReportPrice reportPriceDomain = tvdao.loadReportPriceDetails(reportPrice.getId());
		System.out.println("reportPriceDomain:"+reportPriceDomain);
		/*if (reportPriceDomain == null) {
			result.setMsg("不存在");
			result.setState(2);
			return result;
		}*/
		if (reportPrice.getPage() == null) {
			reportPrice.setPage(1);
		}
		reportPrice.setPageSize((reportPrice.getPage() - 1) * reportPrice.getLimit());
		reportPrice.setPurchaseOrders(reportPriceDomain.getPurchaseOrders());
		List<ReportDemandList> demandLists = tvdao.loadPriceReportDemand(reportPrice);
		int count = tvdao.reportDemandListCount(reportPrice);
		if (demandLists.size() < 1) {
			result.setMsg("无数据");
			result.setState(1);
			return result;
		}
		List<ReportDemandList> demands = new ArrayList<>();
		for (ReportDemandList demand : demandLists) {
			if (demand.getMid() != null) {
				MaterialManage loadmm = tvdao.searchMate(demand.getMid());
				if (loadmm != null) {
					demand.setPname(loadmm.getMaterialName());
					demand.setPn(loadmm.getPn());
					demands.add(demand);
				}

			}
		}
		result.setData(demands);
		result.setCount(count);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult delreportPrice(Integer id) {
		CyResult result = new CyResult();
		int row = tvdao.delreportPrice(id);
		if (row == 1) {
			result.setMsg("删除成功");
			result.setState(0);
			return result;
		}
		result.setMsg("删除失败");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult dayingReportPrice(Integer id1, Integer id2, Integer id3,
			Integer thisId) {
		CyResult result = new CyResult();
		MakerList maker = tvdao.searchMaker(id1);
		CompanyList company = tvdao.loadCompanyId(id2);
		RequestList request = tvdao.loadRequestId(id3);
		/*PurchaseList purchase = tvdao.loadPl(thisId);*/
		ReportPrice purchase = tvdao.loadReportPriceDetails(thisId);
		if (purchase == null) {
			result.setMsg("报价单不存在");
			result.setState(2);
			return result;
		}
		List<ReportDemandList> demandLists = tvdao.dyPriceReportDemand(purchase);
		if (demandLists.size() < 1) {
			result.setMsg("无数据");
			result.setState(1);
			return result;
		}
		List<ReportDemandList> demands = new ArrayList<>();
		for (ReportDemandList demand : demandLists) {
			if (demand.getMid() != null) {
				MaterialManage loadmm = tvdao.searchMate(demand.getMid());
				if (loadmm != null) {
					demand.setPname(loadmm.getMaterialName());
					demand.setPn(loadmm.getPn());
					demand.setMexplain(loadmm.getMexplain());
				}

				// demand.setDemandNumber(purchase.getDemandNumber());
				if (demand.getJprice() == null) {
					demand.setDmoney(0.0);
				} else {
					demand.setDmoney(demand.getJprice() * demand.getDemandNumber());
				}
				demands.add(demand);
			}
		}
		result.setData(maker);
		result.setData1(company);
		result.setData2(request);
		result.setData3(demands);
		result.setData4(purchase);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult loadInstruct(Instruct instruct) {
		CyResult result=new CyResult();
		if (instruct.getPage() == null) {
			instruct.setPage(1);
		}
		instruct.setPageSize((instruct.getPage() - 1) * instruct.getLimit());
		int count=tvdao.countLoadInstruct(instruct);
		List<Instruct> instructlist=tvdao.LoadInstruct(instruct);
		for(Instruct instructDemo:instructlist){
			ErpUser user=tvdao.findUserById(instructDemo.getInstruct_userId());
			if(user!=null){
				instructDemo.setInstruct_user(user.getRealName());
			}else{
				instructDemo.setInstruct_user("");
			}
		}
		result.setCount(count);
		result.setData(instructlist);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult giveAccess(String idString ,Integer instruct_pzState,String storeState) {
		CyResult result=new CyResult();
		System.out.println("storeState:"+storeState);
		String[] idArr=idString.split(",");
		
		List<Instruct> instructList=tvdao.loadInstructByIds(idArr);//通过对应的主键查询出指令表里面的所有数据
		List<ProductHistory> hisList=new ArrayList<ProductHistory>();
		
        Map<String,Object> map=new HashMap<String,Object>();
        if(instruct_pzState==1){//批准
        	for(Instruct ins:instructList){
    			ProductHistory history=new ProductHistory();
    			history.setMid(ins.getInstruct_mid());
    			history.setPnumber(ins.getInstruct_number());
    			history.setPtime(CyUtil.getTime());
    			history.setState(ins.getInstruct_state());
    			history.setOid(ins.getInstruct_oid());
    			if(storeState=="8"||"8".equals(storeState)){
    				history.setQsid("1");
    			}
    			hisList.add(history);
    		}
        	try {
        		System.out.println("hisList-->"+hisList);
    			int n=tvdao.addHistory(hisList);
    			map.put("instruct_pzState",instruct_pzState);
    			map.put("idArr", idArr);
    			tvdao.updataPzState(map);
    			System.out.println("n-->"+n);
    			result.setMsg("success!");
    			result.setState(0);
    			return result;
    		} catch (Exception e) {
    			e.printStackTrace();
    			throw new RuntimeException("error!");
    		}
		}else{
			try {
				map.put("instruct_pzState",instruct_pzState);
    			map.put("idArr", idArr);
    			tvdao.updataPzState(map);
				result.setMsg("success!");
				result.setState(0);
				return result;
			} catch (Exception e) {
    			e.printStackTrace();
    			throw new RuntimeException("error!");
    		}
		}

		
	}

	@Override
	public CyResult loadboxFileList(FileUrl fileUrl) {
		Integer page=fileUrl.getPage();
		Integer pageSize=(page-1)*fileUrl.getLimit();
		fileUrl.setPageSize(pageSize);
		CyResult result=new CyResult();
		List<FileUrl>  list=tvdao.loadboxFileList(fileUrl);
		int count=tvdao.loadboxFileListCount(fileUrl);
		result.setCount(count);
		result.setData(list);
		result.setMsg("success!");
		result.setCode(0);
		return result;
	}

	@Override
	public CyResult addShortage(Shortage shortage) {
		CyResult result=new  CyResult();
		if(shortage==null||shortage.getBid()==null||shortage.getBid().equals("")){
			result.setState(2);
			result.setMsg("添加失败");
			return result;
		}
		//BomMaterials bomMaterials=new BomMaterials();
		//根据料号查询bom表里的bom数据
		//List<BomMaterials> ckbmlist=tvdao.loadCKBM(shortage.getPn());
		//根据mid查询仓库该料号数据
		List<ProductHistory> ckphlist=tvdao.loadckHistory(shortage.getMid());
		int kcnumber=0;//库存数
		for(ProductHistory history:ckphlist){
			System.out.println("history"+history);
			if(history.getState().equals("1")){
				kcnumber=kcnumber+history.getPnumber();
			}
			if(history.getState().equals("0")){
				kcnumber=kcnumber-history.getPnumber();
			}
			if(history.getState().equals("2")){
				kcnumber=kcnumber-history.getPnumber();
			}
		}
		ProductHistory ph=new ProductHistory();
		String qsid=CyUtil.getTime4();
		ph.setQsid(qsid);
		shortage.setQsid(qsid);
		if(shortage.getProduceNumber()>=kcnumber){
			ph.setPnumber(kcnumber);
			ph.setMid(shortage.getMid());
			ph.setOid(shortage.getOid());
			ph.setState("2");
			ph.setPtime(CyUtil.getTime());
			tvdao.addkcnumber(ph);
		}
		if(shortage.getProduceNumber()<kcnumber){
			ph.setPnumber(shortage.getProduceNumber());
			ph.setMid(shortage.getMid());
			ph.setOid(shortage.getOid());
			ph.setState("2");
			ph.setPtime(CyUtil.getTime());
			tvdao.addkcnumber(ph);
			shortage.setStockNumber(kcnumber);
			tvdao.addShortage(shortage);
			result.setState(0);
			result.setMsg("仓库成品够用");
			result.setData(shortage);
			return result;
		}
		int shortageNumber=shortage.getProduceNumber()-kcnumber;//欠料数
		shortage.setShortageNumber(shortageNumber);
		shortage.setStockNumber(kcnumber);
		tvdao.addShortage(shortage);
		
		List<BomMaterials> ckbmlist=tvdao.loadCKBM(shortage.getPn());
		System.out.println("ckbmlist-->"+ckbmlist);
		List<ShortageList> list=new ArrayList<ShortageList>();
		for(BomMaterials bm:ckbmlist){
			ShortageList slb=new ShortageList();
			double yl=bm.getUseNumer();
			int produceNumber=((int) yl)*shortage.getProduceNumber();
			if(yl>0){
				slb.setProduceNumber(produceNumber);
			}
			slb.setPn(bm.getPn());
			slb.setMaterialName(bm.getMaterialName());
			
			List<Shortage> sidList=tvdao.findQLSid(shortage.getShortageCode());//查询欠料表主键
			for(Shortage qlsid:sidList){
				slb.setSid(qlsid.getSid());
			}
			slb.setStockNumber(kcnumber);
			slb.setShortageNumber(produceNumber-kcnumber);//欠数
			slb.setPname(bm.getMaterialName());
			list.add(slb);
			System.out.println("list-->"+list);
		}
		int n=tvdao.addShortageList(list);
		result.setState(0);
		result.setMsg("添加成功");
		result.setData(list);
		return result;
		
	}

	@Override
	public CyResult loadShortage(Shortage shortage) {
		CyResult result=new CyResult();
		if (shortage.getPage() == null) {
			shortage.setPage(1);
		}
		shortage.setPageSize((shortage.getPage() - 1) * shortage.getLimit());
		int count=tvdao.countLoadShortage(shortage);
		List<Shortage> list=tvdao.loadShortage(shortage);
		result.setCount(count);
		result.setData(list);
		result.setState(0);
		result.setMsg("success!");
		return result;
	}

	@Override
	public CyResult loadShortagelist(ShortageList shortageList) {
		System.out.println("shortageList-->"+shortageList);
		CyResult result=new CyResult();
		if (shortageList.getPage() == null) {
			shortageList.setPage(1);
			shortageList.setLimit(10);
		}
		shortageList.setPageSize((shortageList.getPage() - 1) * shortageList.getLimit());
		int count=tvdao.countLoadShortagelist(shortageList);
		List<ShortageList> list=tvdao.loadShortagelist(shortageList);
		result.setCount(count);
		result.setData(list);
		result.setState(0);
		result.setMsg("加载成功！");
		return result;
	}

	@Override
	public CyResult placeShortage(String pname,String pn,Integer sid, Integer shortageNumber,
			String site, String shortageCode, Integer sort) {
		System.out.println("sid="+sid+"shortageNumber="+shortageNumber+"site="+site+"shortageCode="+shortageCode+"sort="+sort);
		CyResult result=new CyResult();
		//查询是否下单
		Shortage order=tvdao.findQLbXD(sid);
		if(order.getOrder()==1){
			result.setState(1);
			result.setMsg("该单号已下单！");
			return result;
		}
		PurchaseList purchase = new PurchaseList();
		if(order.getShortageNumber()==null){
			order.setShortageNumber(0);
		}
		purchase.setMaterialNumber(order.getShortageNumber());
		purchase.setSid(sid);
		if(shortageNumber==null){
			shortageNumber=0;
		}
		purchase.setDemandNumber((double)shortageNumber);
		purchase.setSite(site);
		purchase.setSort(sort);
		purchase.setCreamTime(CyUtil.getTime2());
		String oldpo = tvdao.searchPO(CyUtil.getTime2());
		String purchaseOrders;
		if(oldpo == null){
			purchaseOrders = "JC" + CyUtil.getTime3() + "001";
			purchase.setPurchaseOrders(purchaseOrders);
		}else{
			String oldpostr = oldpo.substring(10, 13);
			int ops = Integer.parseInt(oldpostr) + 1;
			DecimalFormat df = new DecimalFormat("000");
			String s = df.format(ops);
			purchaseOrders = "JC" + CyUtil.getTime3() + s;
			purchase.setPurchaseOrders(purchaseOrders);
		}
		double sums = tvdao.sumQLPrice(pn);//在价格表查询价格
		System.out.println(sums);
		purchase.setTotalMoney(sums*order.getShortageNumber());
		purchase.setStuat(1);
		tvdao.addQLXD(purchase);//下单插入
		
		ShortageList shortageList=new ShortageList();
		shortageList.setSid(sid);
		shortageList.setPage(1);
		shortageList.setLimit(10);
		shortageList.setPageSize((shortageList.getPage() - 1) * shortageList.getLimit());
		List<ShortageList> list=tvdao.loadShortagelist(shortageList);
		List<DemandList> XDlist=new ArrayList<DemandList>();
		for(ShortageList slb:list){
			System.out.println(slb);
			DemandList demand = new DemandList();
			PriceList price = tvdao.PriceByPn(slb.getPcode());
			if(price==null){
				demand.setJprice(0.0);
				demand.setDmoney(0.0);
				MaterialManage mmg=tvdao.findByPn(slb.getPcode());
				demand.setMid(mmg.getId());
			}else{
				if(price.getMid()==null||price.getJprice()==null){
					price.setJprice(0.0);
				}
				demand.setMid(price.getMid());
				demand.setJprice(price.getJprice());
				demand.setDmoney(price.getJprice()*slb.getShortageNumber());
			}
			if(slb.getShortageNumber()==null){
				slb.setShortageNumber(0);
			}
			demand.setDemandNumber((double) slb.getShortageNumber());
			demand.setPurchaseOrders(purchaseOrders);
			demand.setCreamTime(CyUtil.getTime2());
			XDlist.add(demand);
			
		}
		System.out.println(XDlist);
		int n=tvdao.addQLPurchaseList(XDlist);
		tvdao.updateQLXD(sid);//修改欠料表进行中变成已下单
		result.setState(0);
		result.setMsg("下单成功");
		return result;
		
	}

	@Override
	public CyResult deleteShortage(Integer sid) {
		CyResult result=new CyResult();
		Shortage shortage=tvdao.findQLbXD(sid);
		if(shortage.getOrder()==1){
			result.setState(1);
			result.setMsg("已下单不能删除");
			return result;
		}
		tvdao.deleteShortage(shortage.getQsid());
		tvdao.delCK(shortage.getQsid());
		tvdao.deleteShortageList(sid);
		result.setState(0);
		result.setMsg("删除成功");
		return result;
	}

	@Override
	public CyResult addtemoraryManager(ProductHistory ph) {
		System.out.println("ph:"+ph);
		ph.setStoreState(8);
		if(ph.getQsid()==null){
			ph.setQsid("1");
		}
		CyResult result = new CyResult();
		Map<String, Object> oMap = new HashMap<>();
		oMap.put("orderNumber", ph.getOrderNumber());
		oMap.put("mid", ph.getMid());
		Integer oid = tvdao.searchOid(oMap);
		ph.setOid(oid);
		String time = CyUtil.getTime();
		ph.setPtime(time);
		Instruct instruct=new Instruct();
		instruct.setInstruct_mid(ph.getMid());
		instruct.setInstruct_number(ph.getPnumber());
		instruct.setInstruct_oid(oid);
		instruct.setInstruct_state(ph.getState());
		instruct.setInstruct_pname(ph.getMaterialName());
		instruct.setInstruct_pcode(ph.getPn());
		instruct.setInstruct_order(ph.getOrderNumber());
		instruct.setInstruct_time(CyUtil.getTime2());
		instruct.setInstruct_user(ph.getInstruct_user());
		instruct.setStoreState(ph.getStoreState());
		instruct.setInstructPermision(ph.getInstructPermision());
		instruct.setInstruct_userId(ph.getInstruct_userId());
		instruct.setInstruct_pzState(0);
		instruct.setQsid(ph.getQsid());
		if(ph.getInstructPermision()==1){
			try {
				tvdao.insertph(ph);
				result.setMsg("添加成功!当前用户已具备审批权限,数据将直接通过审批!");
				result.setState(0);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("error");
			}
		}else{
			try {
				tvdao.insertInstruct(instruct);
				result.setMsg("添加成功!请等待审批!");
				result.setState(0);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("error");
			}
		}
	}

	@Override
	public CyResult loadtemporaryManager(Warehouse warehouse) {
		CyResult result = new CyResult();
		if (warehouse.getPage() == null) {
			warehouse.setPage(1);
		}
		warehouse.setPageSize((warehouse.getPage() - 1) * warehouse.getLimit());
		int count = tvdao.loadcpMM(warehouse);

		List<MaterialManage> mms = tvdao.loadprodMM(warehouse);
		List<Warehouse> whList = new ArrayList<Warehouse>();
		for (MaterialManage mate : mms) {
			Warehouse war = new Warehouse();
			String outNumString = tvdao.outzfph(mate.getId());// 出库数量
			String putNumString = tvdao.putzfph(mate.getId());// 入库数量
			if (outNumString == null) {
				outNumString = "0";
			}
			if (putNumString == null) {
				putNumString = "0";
			}
			int outNum = Integer.parseInt(outNumString);
			int putNum = Integer.parseInt(putNumString);
			int yushu = putNum - outNum;// 余数
			war.setId(mate.getId());
			war.setResidue(yushu);
			war.setProductName(mate.getMaterialName());
			war.setSoftwareAddress(mate.getSoftwareAddress());
			war.setSoftwarePasssword(mate.getDownPassword());
			war.setPn(mate.getPn());
			war.setWeight(mate.getWeight());
			war.setSpecification(mate.getMexplain());
			war.setOutNumber(outNum);
			war.setPutNumber(putNum);
			whList.add(war);
		}

		result.setState(1);
		result.setCode(0);
		result.setCount(count);
		result.setData(whList);
		result.setMsg("加载成功");
		return result;
	}
	@Override
	public CyResult loadErwork(Warehouse warehouse) {
		CyResult result = new CyResult();
		if (warehouse.getPage() == null) {
			warehouse.setPage(1);
		}
		warehouse.setPageSize((warehouse.getPage() - 1) * warehouse.getLimit());
		int count = tvdao.loadcpMM(warehouse);

		List<MaterialManage> mms = tvdao.loadprodMM(warehouse);
		List<Warehouse> whList = new ArrayList<Warehouse>();
		for (MaterialManage mate : mms) {
			Warehouse war = new Warehouse();
			String outNumString = tvdao.outfxph(mate.getId());// 出库数量
			String putNumString = tvdao.putfxph(mate.getId());// 入库数量
			if (outNumString == null) {
				outNumString = "0";
			}
			if (putNumString == null) {
				putNumString = "0";
			}
			int outNum = Integer.parseInt(outNumString);
			int putNum = Integer.parseInt(putNumString);
			int yushu = putNum - outNum;// 余数
			war.setId(mate.getId());
			war.setResidue(yushu);
			war.setProductName(mate.getMaterialName());
			war.setSoftwareAddress(mate.getSoftwareAddress());
			war.setSoftwarePasssword(mate.getDownPassword());
			war.setPn(mate.getPn());
			war.setWeight(mate.getWeight());
			war.setSpecification(mate.getMexplain());
			war.setOutNumber(outNum);
			war.setPutNumber(putNum);
			whList.add(war);
		}

		result.setState(1);
		result.setCode(0);
		result.setCount(count);
		result.setData(whList);
		result.setMsg("加载成功");
		return result;
	}

	@Override
	public CyResult loadprecious(Precious precious) {
		CyResult result=new CyResult();
		System.out.println("batch-->"+precious);
		if (precious.getPage() == null) {
			precious.setPage(1);
			precious.setLimit(10);
		}
		precious.setPageSize((precious.getPage() - 1) * precious.getLimit());
		precious.setTime(CyUtil.getTime());
		List<Precious> pus=tvdao.loadprecious(precious);
		int count=tvdao.countloadprecious(precious);
		result.setCount(count);
		result.setState(0);
		result.setMsg("成功");
		result.setData(pus);
		return result;
	}

	@Override
	public CyResult updataBomList(BomList bom) {
		CyResult result = new CyResult();
		BomList bl = tvdao.loadMidBom(bom.getMid());
		if (bl != null) {
			result.setMsg("重复bom");
			result.setState(3);
			return result;
		}
		bom.setDosage(0);
		bom.setCreamTime(CyUtil.getTime());
		int qwe = tvdao.updataBomList(bom);
		if (qwe == 1) {
			result.setMsg("修改成功");
			result.setState(0);
			return result;
		}
		result.setMsg("修改失败");
		result.setState(2);
		return result;
	}
	@Override
	public CyResult getParts(ImportantParts parts) {
		CyResult result = new CyResult();
		parts.setPageSize((parts.getPage()-1)*parts.getLimit());
		List<ImportantParts> list = tvdao.getParts(parts);
		if(list != null){
			int num  = tvdao.countParts(parts);
			result.setState(1);
			result.setCode(0);
			result.setCount(num);
			result.setData(list);
			result.setMsg("加载成功");
		
		}
		return result;
	}
	
	

	@Override
	public CyResult insertParts(ImportantParts parts) {
		CyResult result = new CyResult();
		if(parts.getSN1() == null && parts.getSN1() == ""){
			result.setMsg("SN不能为空!");
			result.setState(2);
			return result;
		}
		ImportantParts sn = new ImportantParts();
		sn.setSN1(parts.getSN1());
		int count = tvdao.countParts(sn);
		int num = tvdao.insertInportant(parts);
		if(num > 0){
			result.setMsg("添加成功!");
			result.setState(0);
			return result;
		}
		result.setMsg("添加失败!");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult updateParts(ImportantParts parts) {
		CyResult result = new CyResult();
		int num = tvdao.updateInportant(parts);
		if(num > 0){
			result.setMsg("修改成功!");
			result.setState(0);
			return result;
		}
		result.setMsg("修改失败!");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult deleteParts(String id) {
		CyResult result = new CyResult();
		System.out.println(id);
		System.out.println(11);
		 tvdao.deleteInportant(Integer.valueOf(id));
			result.setMsg("删除成功!");
			result.setState(0);
			return result;
	}
}
