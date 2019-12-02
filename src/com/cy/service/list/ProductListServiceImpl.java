package com.cy.service.list;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;
import javax.swing.event.ChangeListener;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.model.Model;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.cglib.core.MethodWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cy.board.Repair;
import com.cy.dao.line.WorkLineDao;
import com.cy.dao.list.ProductListDao;
/*import com.cy.dao.list.ProductListDao;*/
import com.cy.dao.produce.tv.ProduceTvDao;
import com.cy.domain.line.ProductDetails;
import com.cy.domain.line.WorkLine;
import com.cy.domain.line.WorkLocation;
import com.cy.domain.list.Binding_code;
import com.cy.domain.list.BomCode;
import com.cy.domain.list.Boxnum;
import com.cy.domain.list.PrintModel;
import com.cy.domain.list.ProductCode;
import com.cy.domain.list.ProductList;
import com.cy.domain.list.ProductModel;
import com.cy.domain.list.ProductScheduling;
import com.cy.domain.list.SnCode;
import com.cy.domain.list.SnCodeDomain;
import com.cy.domain.list.WorkStationCode;
import com.cy.domain.produce.tv.BomMaterials;
import com.cy.domain.produce.tv.MaterialManage;
import com.cy.domain.produce.tv.TvProduce;
import com.cy.utils.CodeImportByExcel;
import com.cy.utils.CreateSimpleExcelToCode;
import com.cy.utils.CreateSimpleExcelToDisk;
import com.cy.utils.CreateSimpleExcelToDiskByBoxNum;
import com.cy.utils.CyResult;
import com.cy.utils.CyUtil;
import com.cy.utils.ImeiUtils;
import com.cy.utils.ImportExcel;
import com.cy.utils.NumericalUtil;
import com.cy.utils.SchedulingUtil;
import com.cy.utils.UploadUtil;
import com.cy.utils.UrlAddress;
import com.mysql.jdbc.StringUtils;


@Service("productListService")
@Transactional
public class ProductListServiceImpl implements ProductListService {
	
	@Resource
	private ProduceTvDao tvdao;
	@Resource
	private ProductListDao dao;
    @Resource
    private WorkLineDao lineDao;
    @Resource
    private ProduceTvDao produceTvDao;
    
	Map<Integer, String> mappp = new HashMap<Integer, String>();
	Map<Integer, List> map = new HashMap<Integer, List>();

	// Map<Integer, String> mappp = new HashMap<Integer, String>();

	@Override
	public CyResult loadlist() {
		CyResult result = new CyResult();
		List<ProductList> list = dao.loadlist();

		if (list == null || list.size() < 1) {
			result.setMsg("无产品列表信息!");
			result.setState(0);
			return result;
		}

		/*
		 * for(ProductList w:list){ Integer n=dao.findCountValue(w); Integer c =
		 * dao.countLocation(w); w.setCheckNum(c); w.setCountValue(n);
		 * 
		 * 
		 * }
		 */

		result.setMsg("加载产品列表成功");
		result.setState(1);
		result.setData(list);
		return result;
	}

	/*
	 * 加载全部条码列表
	 * 
	 * @see com.cy.service.list.ProductListService#loadProductCode()
	 */
	@Override
	public CyResult loadAllProductCode(Integer page , Integer limit, ProductCode product) {
        System.err.println(product);
		CyResult result = new CyResult();
		if(page==null){
			page=1;
		}
		int pageSize = (page-1)*limit;
		if(product.getTime()!=null && !"".equals(product.getTime())){
			String[] timeStr = product.getTime().split("~");
			product.setBeginTime(timeStr[0].trim());
			product.setEndTime(timeStr[1].trim());
		}
		
		if(product.getDeliver()!=null&&!"".equals(product.getDeliver())){
			String[] timeStr = product.getDeliver().split("~");
			System.out.println(Arrays.toString(timeStr));
			product.setDeliverStartime(timeStr[0].trim());
			product.setDeliverEndtime(timeStr[1].trim());
		}
		product.setLimit(limit);
		product.setPageSize(pageSize);
		System.out.println(product);
		int count = dao.loadAllProductCodeNumber(product);
		List<ProductCode> list = dao.loadAllProductCode(product);
			result.setCode(0);
			result.setCount(count);
			result.setState(0);
			result.setMsg("获取成功");
			result.setData(list);
			return result;
		
	}

	/*
	 * 查询条码列表
	 */
	@Override
	public CyResult loadProductCode(ProductCode pc) {
		CyResult result = new CyResult();
		if (pc.getPid() == null) {
			result.setMsg("未能选定一条列表!");
			result.setState(0);
			return result;
		}

		ProductCode code = dao.loadProductCode(pc.getPid());
		// ProductScheduling pd = dao.selectPnameByProcess(code.getProcess());
		code.setPcode(code.getProcess());
		System.out.println(code);
		if (code.getPid() == null) {
			// throw new RuntimeException("列表不存在!");
			result.setMsg("该列表不存在!");
			result.setState(0);
			return result;
		}
		result.setMsg("加载成功!");
		result.setState(1);
		result.setData(code);
		return result;
	}

	/*
	 * 根据SN1查询条码列表
	 */
	@Override
	public CyResult loadProductCodeBySN1(ProductCode pc) {
		CyResult result = new CyResult();
		if (pc.getSn1() == null) {
			result.setMsg("请输入SN1!");
			result.setState(0);
			return result;
		}
//		int n = pc.getSn1().length();
//		switch (n) {
//		case 18:
//			ProductCode pdc = dao.loadProductCodeBySN1(pc.getSn1());
//			if (pdc.getPid() == null) {
//				// throw new RuntimeException("列表不存在!");
//				result.setMsg("该SN1不存在!");
//				result.setState(0);
//				return result;
//			}
//			result.setMsg("加载成功!");
//			result.setState(1);
//			result.setData(pdc);
//			return result;
//		case 16:
			ProductCode pdcc = dao.loadProductCodeBySN1(pc.getSn1());
			if (pdcc.getPid() == null) {
				// throw new RuntimeException("列表不存在!");
				result.setMsg("该SN1不存在!");
				result.setState(0);
				return result;
			}
			result.setMsg("加载成功!");
			result.setState(1);
			result.setData(pdcc);
			return result;

//		default:
//			result.setMsg("请输入正确的SN1!");
//			result.setState(0);
//			return result;
//		}
	}

	/*
	 * 批量录入条码列表
	 */
	@Override
	public CyResult addProductCode(String snumber, String strsn1, String strsn2, ProductCode pc) {
		CyResult result = new CyResult();
		ProductListServiceImpl plsi = new ProductListServiceImpl();
		// 自定义编码
		// plsi.wxs(strsn1, strsn2, pc);
		return result;
	}

	/*
	 * 删除条码列表
	 */
	@Override
	public CyResult deleteProductCode(ProductCode pc) {
		CyResult result = new CyResult();
		if (pc.getPid() == null) {
			result.setMsg("未能选定一条列表!");
			result.setState(0);
			return result;
		}
		try {
			dao.deleteProductCode(pc);
			result.setMsg("success!");
			result.setState(1);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("error!");
		}
		
	}
	
	/*
	 * 修改条码列表
	 */
	@Override
	public CyResult updateProductCode(ProductCode pc) {
		CyResult result = new CyResult();
		if (pc.getPid() == null) {
			result.setMsg("未能选定一条列表!");
			result.setState(0);
			return result;
		}
		int n = dao.updateProductCode(pc);
		System.out.println(pc);
		if (n != 1) {
			throw new RuntimeException("修改列表失败!");
		}
		result.setMsg("修改成功!");
		result.setState(1);
		return result;
	}

	/*
	 * 新增工艺排程
	 */
	@Override
	public CyResult addProductScheduling(ProductScheduling ps) {
		CyResult result = new CyResult();
		if (ps.getSnumber() == null || ps.getSnumber().trim().isEmpty()) {
			result.setMsg("请填写排程编码");
			result.setState(0);
			return result;
		}
		if (ps.getSnumber().length() != 3) {
			result.setMsg("排程编码输入位数有误!请重新输入");
			result.setState(0);
			return result;
		}
		int n = dao.addProductScheduling(ps);
		System.out.println(ps);
		if (n != 1) {
			result.setMsg("新增工艺排程失败！");
		}
		result.setMsg("排程新增成功!");
		result.setState(1);
		return result;
	}

	/*
	 * 修改工艺排程
	 */
	@Override
	public CyResult updateProductScheduling(ProductScheduling ps) {
		CyResult result = new CyResult();
		if (ps.getPid() == null) {
			result.setMsg("未能选定一条工艺排程!");
			result.setState(0);
			return result;
		}
		int n = dao.updateProductScheduling(ps);
		System.out.println(ps);
		if (n != 1) {
			// throw new RuntimeException("删除列表失败!");
			result.setMsg("修改工艺排程失败!");
			result.setState(0);
			return result;
		}
		result.setMsg("修改成功!");
		result.setState(1);
		return result;
	}

	/*
	 * 查询工艺排程
	 */
	@Override
	public CyResult selectProductScheduling(ProductScheduling ps) {
		CyResult result = new CyResult();
		if (ps.getPid() == null) {
			result.setMsg("未能选定一条工艺排程!");
			result.setState(0);
			return result;
		}
		ProductScheduling scheduling = dao.selectProductScheduling(ps);
		System.out.println(ps);
		if (scheduling.getPid() == null) {
			// throw new RuntimeException("删除列表失败!");
			result.setMsg("工艺排程不存在!");
			result.setState(0);
			return result;
		}
		result.setMsg("查询成功!");
		result.setState(1);
		result.setData(scheduling);
		return result;
	}

	/*
	 * 查询全部工艺排程
	 */
	@Override
	public CyResult selectAllProductScheduling(Integer page, Integer limit) {
		CyResult result = new CyResult();
		if(page==null){
			page=1;
		}
		int pageSize = (page-1)*limit;
		ProductScheduling pds = new ProductScheduling();
		pds.setLimit(limit);
		pds.setPageSize(pageSize);
		List<ProductScheduling> list = dao.selectAllProductScheduling(pds);
		List<ProductScheduling> pList = new ArrayList<>();
		for(ProductScheduling productScheduling : list){
			ProductDetails product = dao.findProduct(productScheduling.getC_pid());
			if(product==null){
				productScheduling.setPname("");
				pList.add(productScheduling);
			}else{
				productScheduling.setPname(product.getPname());
				pList.add(productScheduling);
			}
		}
		int count = dao.countPds();
		if (pList != null) {
			result.setCode(0);
			result.setCount(count);
			result.setState(0);
			result.setMsg("获取成功");
			result.setData(pList);
			return result;
		}
		result.setState(2);
		result.setMsg("获取失败");
		return result;
	}

	/*
	 * 自定义编码规则
	 * 
	 * @see com.cy.service.list.ProductListService#wxs(java.lang.String,
	 * java.lang.String, com.cy.domain.list.ProductCode)
	 */

	@Override
	public CyResult wxs(String strsn1, String strsn2, Double cost, ProductCode pc) {
		CyResult result = new CyResult();
		if (pc.getSn1() == null || pc.getSn1().trim().isEmpty()) {
			System.out.println(pc);
			result.setMsg("列表数据不全！");
			result.setState(0);
			return result;
		}
//		if (strsn1.length() != 16 || strsn2.length() != 16 || !strsn1.substring(0, 2).equals("1J")
//				|| !strsn2.substring(0, 2).equals("1J")) {
//			System.out.println(pc);
//			result.setMsg("SN1输入有误!请重新输入");
//			result.setState(0);
//			return result;
//		}
		int n = Integer.parseInt(strsn1.substring(strsn1.length() - 5, strsn1.length()));
		int m = 1 + Integer.parseInt(strsn2.substring(strsn1.length() - 5, strsn1.length()));
		int j = 0;
		String sst = null;
		if (m < n) {
			result.setMsg("录入的格式出现错误");
			result.setState(0);
			return result;
		}
		// 录入产品列表
		ProductList pl = new ProductList();
		ProductScheduling pd = dao.selectPnameByProcess(pc.getProcess());
		pl.setLcost(cost);
		pl.setLname(pd.getPname());
		pl.setLmodel(pc.getModelCode());
		pl.setLmaterial(pc.getPnCode());
		pl.setLorder(pc.getPorder());
		pl.setLplanyield(m - n);
		dao.addProductList(pl);
		System.out.println(pl);
		// ProductListServiceImpl plsi = new ProductListServiceImpl();
		// plsi.addProductList(pl);
		// ProductList ppl = dao.findProductList(pl);
		// if (ppl != null) {
		// int i = pl.getLplanyield() + ppl.getLplanyield();
		// pl.setLplanyield(i);
		// dao.updateProductListLplanyield(pl);
		// } else {
		// dao.addProductList(pl);
		// }

		for (int i = n; i < m; i++) {
			StringBuffer sb = new StringBuffer(strsn1.substring(0, 11));
			sst = sb.append(String.format("%05d", i)).toString();
			ProductCode pp = dao.loadProductCodeBySN1(sst);
			if (pp != null) {
				System.out.println(sst);
				result.setMsg("SN1码已存在！");
				result.setState(0);
				continue;
			}
			pc.setSn1(sst);
			j = dao.addProductCode(pc);
		}

		if (j != 1) {
			// throw new RuntimeException("新增列表失败!");
			result.setMsg("新增列表失败!");
			result.setState(0);
			return result;
		}
		result.setMsg("新增列表成功!");
		result.setState(1);
		return result;

	}

	/*
	 * 硬盘编码规则
	 */
	public CyResult addCodes(String str1, String str2, String str3, String str4, String str5, String str6, Double cost,
			ProductCode pc) {
		CyResult result = new CyResult();

		String num1 = str1.substring(14);

		String num2 = str2.substring(14);

		String chsn1 = str3.substring(2);

		String chsn2 = str4.substring(2);

		int n = Integer.parseInt(str5.substring(str5.length() - 5, str5.length()));
		int m = 1 + Integer.parseInt(str6.substring(str6.length() - 5, str6.length()));

		if (str1.length() != 18 || str2.length() != 18) {
			System.out.println(pc);
			result.setMsg("SN1输入有误!请重新输入");
			result.setState(0);
			return result;
		}

		if (str3.length() != 20 || str4.length() != 20) {
			System.out.println(pc);
			result.setMsg("SN2输入有误!请重新输入");
			result.setState(0);
			return result;
		}

		// if (str5.length() != 15 || str5.length() != 15) {
		// System.out.println(pc);
		// result.setMsg("DATA1输入有误!请重新输入");
		// result.setState(0);
		// return result;
		// }

		if (!chsn1.equals(str1) || !chsn2.equals(str2)) {
			result.setMsg("彩盒码格式错误");
			result.setState(2);
			return result;
		}

		// if (pc.getPcode() == null || pc.getPcode().trim().isEmpty()) {
		// result.setMsg("选择产品名错误");
		// result.setState(2);
		// return result;
		// }

		String num4 = str4.substring(16);

		int n1 = Integer.parseInt(NumericalUtil.BaseConvert(num1, 34, 10));

		int n2 = Integer.parseInt(NumericalUtil.BaseConvert(num2, 34, 10));

		if (n2 < n1) {
			result.setMsg("录入的格式出现错误");
			result.setState(0);
			return result;
		}

		// 录入产品列表
		ProductList pl = new ProductList();
		ProductScheduling pd = dao.selectPnameByProcess(pc.getProcess());
		pl.setLcost(cost);
		pl.setLname(pd.getPname());
		pl.setLmodel(pc.getModelCode());
		pl.setLmaterial(pc.getPnCode());
		pl.setLorder(pc.getPorder());
		pl.setLplanyield(m - n);
		dao.addProductList(pl);
		System.out.println(pl);
		// ProductListServiceImpl plsi = new ProductListServiceImpl();
		// plsi.addProductList(pl);
		// ProductList ppl = dao.findProductList(pl);
		// if (ppl != null) {
		// int i = pl.getLplanyield() + ppl.getLplanyield();
		// pl.setLplanyield(i);
		// dao.updateProductListLplanyield(pl);
		// } else {
		// dao.addProductList(pl);
		// }

		List<ProductCode> listcode = new ArrayList<ProductCode>();
		List<String> batchList = dao.findBatch();
		List<Integer> intlist = new ArrayList<Integer>();
		for (String sn7 : batchList) {
			// System.out.println(batch);
			if (sn7 != null && !sn7.trim().isEmpty()) {
				int number = Integer.parseInt(sn7.split("@")[1]);
				// System.out.println(number);
				intlist.add(number);
			}
		}
		if (intlist == null || intlist.size() < 1) {
			intlist.add(0);
		}
		int max = Collections.max(intlist);
		String sst = null;
		for (int i = n1; i <= n2; i++) {
			String str = str1.substring(0, 14) + NumericalUtil.BaseConvert(String.valueOf(i), 10, 34);
			ProductCode twoCode = dao.findSnByStr(str);
			if (twoCode != null) {
				continue;
			}
			StringBuffer sb = new StringBuffer(str5.substring(0, 9));
			sst = sb.append(String.format("%05d", n++)).toString();
			ProductCode pp = dao.loadProductCodeBySN1(sst);
			if (pp != null) {
				System.out.println(sst);
				result.setMsg("DATA1码已存在！");
				result.setState(0);
				continue;
			}
			ProductCode oneCode = new ProductCode();
			oneCode.setSn1(str);
			oneCode.setPcode(pc.getPcode());
			oneCode.setColor(pc.getColor());
			oneCode.setProcess(pc.getProcess());
			oneCode.setPorder(pc.getPorder());
			oneCode.setModelCode(pc.getModelCode());
			oneCode.setPnCode(pc.getPnCode());
			oneCode.setData1(sst);
			oneCode.setSn2(str3.substring(0, 2) + str);
			System.out.println(pc.getPnCode() + "@" + (max + 1));
			oneCode.setSn7(pc.getPnCode() + "@" + (max + 1));
			dao.addProductCode(oneCode);
			listcode.add(oneCode);
		}
		result.setMsg("批量录入成功");
		result.setState(1);
		return result;

	}

	/*
	 * 通过箱号导出数据(non-Javadoc)
	 * 
	 * @see com.cy.service.code.CodeService#exportByBoxNum(java.lang.String)
	 */
	public CyResult exportByBoxNum(ProductCode pc) throws Exception {
		CyResult result = new CyResult();

		ProductCode code = new ProductCode();
		if (pc.getSn6() == null || pc.getSn6().trim().isEmpty()) {
			result.setMsg("箱号输入不成功!");
			result.setState(2);
			return result;
		}
		code.setSn6(pc.getSn6());
		System.out.println("sn6:"+pc.getSn6());
		List<ProductCode> dataList = dao.exportByBoxNum(pc.getSn6());

		// 集合的长度为装箱数
		System.out.println(dataList.size());
		if (dataList == null || dataList.size() < 1) {
			result.setMsg("未能查找到相关的数据!");
			result.setState(0);
			return result;
		}
		List<String> chList = new ArrayList<String>();
		// int temp = 0;
		// if ("1".equals(pc.getSn1())) {
		// temp += 1;
		// }
		// if ("1".equals(pc.getSn2())) {
		// temp += 1;
		// }
		// if ("1".equals(pc.getSn3())) {
		// temp += 1;
		// }
		// if ("1".equals(pc.getSn4())) {
		// temp += 1;
		// }
		// if ("1".equals(pc.getSn5())) {
		// temp += 1;
		// }
		// if ("1".equals(pc.getSn7())) {
		// temp += 1;
		// }

		chList.add(pc.getSn6());
        chList.add(dataList.get(0).getWeight2());
		for (int i = 0; i < dataList.size(); i++) {
			if ("1".equals(pc.getSn1())) {
				if(dataList.get(i).getSn1()==null){
				   chList.add("");
				}else{
					chList.add(dataList.get(i).getSn1());
				}
			}
			if ("1".equals(pc.getSn2())) {
				if(dataList.get(i).getSn2()==null){
					   chList.add("");
					}else{
						chList.add(dataList.get(i).getSn2());
					}
			}
			if ("1".equals(pc.getSn3())) {
				if(dataList.get(i).getSn3()==null){
					 chList.add("");
				}else{
					 chList.add(dataList.get(i).getSn3());
				}
			}
			if ("1".equals(pc.getSn4())) {
				if(dataList.get(i).getSn4()==null){
					   chList.add("");
					}else{
						chList.add(dataList.get(i).getSn4());
					}
			}
			if ("1".equals(pc.getSn5())) {
				if(dataList.get(i).getSn5()==null){
					chList.add("");
				}else{
					chList.add(dataList.get(i).getSn5());
					}
			}
			if ("1".equals(pc.getSn7())) {
				if(dataList.get(i).getSn7()==null){
					   chList.add("");
				}else{
						chList.add(dataList.get(i).getSn7());
				}
			}
            System.out.println(chList);
		}
		System.out.println(pc.getSn6());
		System.out.println(chList);
		String fileName=CyUtil.getName()+"@"+chList.get(0);
		FileOutputStream fout = new FileOutputStream(UrlAddress.folder+fileName+".xls"); 
		String downloadUrl = CreateSimpleExcelToDiskByBoxNum.createExcel(chList,fout,fileName);

		result.setMsg("文件導出成功");
		result.setState(1);
		result.setData(downloadUrl);
		return result;
	}

	/*
	 * 批量导出数据(non-Javadoc)
	 * 
	 * @see com.cy.service.code.CodeService#ExpotExcel(java.lang.String,
	 * java.lang.String, com.cy.domain.code.Binding_code)
	 */
	public CyResult ExpotExcel(String str1, String str2, ProductCode pc) throws Exception {
		CyResult result = new CyResult();
		Map<Integer, String> map = new HashMap<Integer, String>();
		if (str1 == null || str1.trim().isEmpty() || str2 == null || str2.trim().isEmpty()) {
			result.setMsg("箱号输入不成功!");
			result.setState(2);
			return result;
		}
		List<ProductCode> dataList = dao.exportByBoxNum(str1);
		System.out.println(dataList.size());
		// 集合的长度为装箱数
		if (dataList == null || dataList.size() < 1) {
			result.setMsg("未能查找到相关的数据!");
			result.setState(0);
			return result;
		}
		
		ProductScheduling ps = dao.selectCodeNameBySnumber(dataList.get(0).getPcode());

		List<ProductCode> chList = new ArrayList<ProductCode>();

		if (ps == null) {
			result.setMsg("此箱号未绑定对应箱号条码编码规则。请联系管理员！");
			result.setState(0);
			return result;
		}
		if (ps.getCodeLength() != str1.length() || ps.getCodeLength() != str2.length()) {
			result.setMsg("此箱号输入有误！请查证后重新输入。");
			result.setState(0);
			return result;
		}

		int n = 0;
		int m = 0;
		switch (ps.getSerialSystem()) {
		case "10":
			n = Integer.parseInt(str1.substring(ps.getCodeLength() - ps.getSerialLength()));

			m = 1 + Integer.parseInt(str2.substring(ps.getCodeLength() - ps.getSerialLength()));

			break;
		case "34":
			String num1 = str1.substring(ps.getCodeLength() - ps.getSerialLength());

			String num2 = str2.substring(ps.getCodeLength() - ps.getSerialLength());

			n = Integer.parseInt(NumericalUtil.BaseConvert(num1, 34, 10));

			m = 1 + Integer.parseInt(NumericalUtil.BaseConvert(num2, 34, 10));

			break;
		default:
			result.setMsg("无此箱号条码进制编码规则。请联系管理员！");
			result.setState(0);
			return result;
		}

		if (m < n) {
			result.setMsg("箱号输入有误");
			result.setState(0);
			return result;
		}
		for (int i = n; i < m; i++) {
			if (ps.getSerialSystem().equals("34")) {
				String ss1 = str1.substring(0, str1.length() - ps.getSerialLength())
						+ NumericalUtil.BaseConvert(String.valueOf(i), 10, 34);
				dataList = dao.exportByBoxNum(ss1);
					for (int j = 0; j < dataList.size(); j++) {
						ProductCode pdc = dataList.get(j);
						if (!"1".equals(pc.getSn1())) {
							pdc.setSn1(null);
						}
						if (!"1".equals(pc.getSn2())) {
							pdc.setSn2(null);
						}
						if (!"1".equals(pc.getSn3())) {
							pdc.setSn3(null);
						}
						if (!"1".equals(pc.getSn4())) {
							pdc.setSn4(null);
						}
						if (!"1".equals(pc.getSn5())) {
							pdc.setSn5(null);
						}
						if (!"1".equals(pc.getSn7())) {
							pdc.setSn7(null);
						
						}
						chList.add(pdc);
					}
				
				/*
				 * ProductCode pro = chList.get(0); int q = 0; if (pro.getPid()
				 * != null) { map.put(q, "条码ID"); q += 1; } if
				 * (pro.getModelCode() != null) { map.put(q, "型号"); q += 1; } if
				 * (pro.getPnCode() != null) { map.put(q, "料号"); q += 1; } if
				 * (pro.getSn1() != null) { map.put(q, "SN1"); q += 1; } if
				 * (pro.getSn2() != null) { map.put(q, "SN2"); q += 1; } if
				 * (pro.getSn3() != null) { map.put(q, "SN3"); q += 1; } if
				 * (pro.getSn4() != null) { map.put(q, "SN4"); q += 1; } if
				 * (pro.getSn5() != null) { map.put(q, "SN5"); q += 1; } if
				 * (pro.getSn6() != null) { map.put(q, "SN6"); q += 1; } if
				 * (pro.getSn7() != null) { map.put(q, "SN7"); q += 1; }
				 */

			} else if (ps.getSerialSystem().equals("10")) {
				StringBuffer strsn1 = new StringBuffer(str1.substring(0, str1.length() - ps.getSerialLength()));
				String sss = "%0" + ps.getSerialLength() + "d";
				String ss1 = strsn1.append(String.format(sss, i)).toString();
				dataList = dao.exportByBoxNum(ss1);
					for (int j = 0; j < dataList.size(); j++) {
						ProductCode pdc = dataList.get(j);
						if (!"1".equals(pc.getSn1())) {
							pdc.setSn1(null);
						}
						if (!"1".equals(pc.getSn2())) {
							pdc.setSn2(null);
						}
						if (!"1".equals(pc.getSn3())) {
							pdc.setSn3(null);
						}
						if (!"1".equals(pc.getSn4())) {
							pdc.setSn4(null);
						}
						if (!"1".equals(pc.getSn5())) {
							pdc.setSn5(null);
							
						}
						if (!"1".equals(pc.getSn7())) {
							pdc.setSn7(null);
							
						}
						chList.add(pdc);
					}
				
			}
			System.out.println(chList);
		}
		System.out.println(chList);
		ProductCode pro = chList.get(0);
		int q = 0;
		if (pro.getPid() != null) {
			map.put(q, "条码ID");
			q += 1;
		}
		if (pro.getModelCode() != null) {
			map.put(q, "型号");
			q += 1;
		}
		if (pro.getPnCode() != null) {
			map.put(q, "料号");
			q += 1;
		}
		if (pro.getSn1() != null) {
			map.put(q, "SN1");
			q += 1;
		}
		if (pro.getSn2() != null) {
			map.put(q, "SN2");
			q += 1;
		}
		if (pro.getSn3() != null) {
			map.put(q, "SN3");
			q += 1;
		}
		if (pro.getSn4() != null) {
			map.put(q, "SN4");
			q += 1;
		}
		if (pro.getSn5() != null) {
			map.put(q, "SN5");
			q += 1;
		}
		if (pro.getSn6() != null) {
			map.put(q, "SN6");
			q += 1;
		}
		if (pro.getSn7() != null) {
			map.put(q, "SN7");
			q += 1;
		}
		if(pro.getColor()!=null){
			map.put(q, "颜色");
			q += 1;
		}

		String downloadUrl = CreateSimpleExcelToDisk.createExcel(chList, map);
		result.setMsg("文件导出成功");
		result.setData(downloadUrl);
		result.setState(1);
		return result;
	}
	
	/*
	 * 出货批量导出数据
	 */
	public CyResult exportShipment(String str1, String str2, ProductCode pc) throws Exception {
		CyResult result = new CyResult();
		Map<Integer, String> map = new HashMap<Integer, String>();
		List<ProductCode> dataList = new ArrayList<ProductCode>();
		if (str1 == null || str1.trim().isEmpty() || str2 == null || str2.trim().isEmpty()) {
			result.setMsg("日期输入不成功!");
			result.setState(2);
			return result;
		}
		if (str1.length() != 8 || str2.length() != 8 ) {
			result.setMsg("日期格式位数不正确！");
			result.setState(2);
			return result;
		}
		int n = Integer.parseInt(str1.substring(6));
		int m = Integer.parseInt(str2.substring(6));
		if (m < n) {
			result.setMsg("日期输入有误");
			result.setState(2);
			return result;
		}
		for (int i = n; i <= m; i++) {
			String str = str1.substring(0, 6)+String.format("%02d", n);
			System.out.println(pc.getPorder());
			List<String> list = dao.selectSn1ByPorderAndTime10(pc.getPorder(), str);
			for (String string : list) {
				if (string != null) {
					ProductCode pdc = dao.loadProductCodeBySN1(string);
					if (!"1".equals(pc.getSn1())) {
						pdc.setSn1(null);
					}
					if (!"1".equals(pc.getSn2())) {
						pdc.setSn2(null);
					}
					if (!"1".equals(pc.getSn3())) {
						pdc.setSn3(null);
					}
					if (!"1".equals(pc.getSn4())) {
						pdc.setSn4(null);
					}
					if (!"1".equals(pc.getSn5())) {
						pdc.setSn5(null);					
					}
					if (!"1".equals(pc.getSn6())) {
						pdc.setSn6(null);					
					}
					if (!"1".equals(pc.getSn7())) {
						pdc.setSn7(null);					
					}
					dataList.add(pdc);				
				}
			}
		}	
		if (dataList.size()==0 || dataList.size()<1) {
			result.setMsg("该日期内无条码信息！");
			result.setState(2);
			return result;
		}
		ProductCode pro = dataList.get(0);
		int q = 0;
		if (pro.getPid() != null) {
			map.put(q, "条码ID");
			q += 1;
		}
		if (pro.getModelCode() != null) {
			map.put(q, "型号");
			q += 1;
		}
		if (pro.getPnCode() != null) {
			map.put(q, "料号");
			q += 1;
		}
		if (pro.getSn1() != null) {
			map.put(q, "SN1");
			q += 1;
		}
		if (pro.getSn2() != null) {
			map.put(q, "SN2");
			q += 1;
		}
		if (pro.getSn3() != null) {
			map.put(q, "SN3");
			q += 1;
		}
		if (pro.getSn4() != null) {
			map.put(q, "SN4");
			q += 1;
		}
		if (pro.getSn5() != null) {
			map.put(q, "SN5");
			q += 1;
		}
		if (pro.getSn6() != null) {
			map.put(q, "SN6");
			q += 1;
		}
		if (pro.getSn7() != null) {
			map.put(q, "SN7");
			q += 1;
		}
		if(pro.getColor()!=null){
			map.put(q, "颜色");
			q += 1;
		}
		
		String downloadUrl = CreateSimpleExcelToDisk.createExcel(dataList, map);
		result.setMsg("文件导出成功");
		result.setData(downloadUrl);
		result.setState(1);
		return result;
		
	}

	/*
	 * 通用编码规则
	 */
	public CyResult addCurrency(String pname, String s1, String s2, String s3, String s4, String s5, String s6,
			String s7, String s8, String s9, String s10, String s11, String s12, String s13, String s14, String s15,
			String s16, String s17, String s18, String p1, String p2, String p3, String p4, String p5, String p6,
			String p7, String p8, String p9, ProductCode pc) {
		CyResult result = new CyResult();
		ProductScheduling pdsd = new ProductScheduling();
		int key = Integer.parseInt(pname);
		String name = mappp.get(key);
		pdsd.setPname(name);
		pdsd.setSnumber(p1);
		ProductScheduling ps = dao.loadPSBySnumber(pdsd);
		System.out.println(ps);
		int n = 0;
		int m = 0;
		int length = 0;
		switch (ps.getSerialSystem()) {
		case "10":
			n = Integer.parseInt(s1.substring(s1.length() - ps.getSerialLength()));

			m = 1 + Integer.parseInt(s2.substring(s2.length() - ps.getSerialLength()));

			break;
			
		case "34":
			String num1 = s1.substring(s1.length() - ps.getSerialLength());

			String num2 = s2.substring(s2.length() - ps.getSerialLength());

			n = Integer.parseInt(NumericalUtil.BaseConvert(num1, 34, 10));

			m = 1 + Integer.parseInt(NumericalUtil.BaseConvert(num2, 34, 10));

			break;
		
		case "16":
			String mac1 = s1.substring(s1.length() - ps.getSerialLength());
			
			String mac2 = s2.substring(s2.length() - ps.getSerialLength());
			
			n = Integer.parseInt(NumericalUtil.BaseConvert(mac1, 16, 10));
			
			m = 1 + Integer.parseInt(NumericalUtil.BaseConvert(mac2, 16, 10));
			
			break;
		
		case "11":
			length = ps.getSerialLength() + 1;
			
			n = Integer.parseInt(s1.substring(s1.length() - length,s1.length() - 1));

			m = 1 + Integer.parseInt(s2.substring(s2.length() - length,s1.length() - 1));
			
			break;
			
		default:
			result.setMsg("无此进制编码规则。请联系管理员！");
			result.setState(0);
			return result;
		}

//		if (s1.length() != ps.getCodeLength() || s2.length() != ps.getCodeLength()
//				|| !ps.getKeyCharOne()
//						.equals(s1.substring(Integer.parseInt(ps.getKeyLocatlOne()) - 1,
//								Integer.parseInt(ps.getKeyLocatlOne())))
//				|| !ps.getKeyCharOne().equals(s2.substring(Integer.parseInt(ps.getKeyLocatlOne()) - 1,
//						Integer.parseInt(ps.getKeyLocatlOne())))) {
//			System.out.println(pc);
//			result.setMsg("SN1输入有误!请重新输入");
//			result.setState(0);
//			return result;
//		}
		String sst = null;
		int o = n;

		// 录入产品列表
		ProductList pl = new ProductList();
		ProductScheduling pd = dao.selectPnameByProcess(p1);
		pl.setLname(pd.getPname());
		pl.setLmodel(pc.getModelCode());
		pl.setLmaterial(pc.getPnCode());
		pl.setLorder(pc.getPorder());
		pl.setLplanyield(m - n);
		dao.addProductList(pl);
		System.out.println(pl);

		if (m < n) {
			result.setMsg("录入的格式出现错误");
			result.setState(0);
			return result;
		}

		List<ProductCode> listcode = new ArrayList<ProductCode>();
		int sm0n2;
		
		for (int i = n; i < m; i++) {
			pdsd.setSnumber(p1);
			ps = dao.loadPSBySnumber(pdsd);
			ProductCode oneCode = new ProductCode();
			
			// 创建条码对象根据流水号进制存储sn1
			if (ps.getSerialSystem().equals("34")) {
				String str1 = s1.substring(0, s1.length() - ps.getSerialLength())
						+ NumericalUtil.BaseConvert(String.valueOf(i), 10, 34);
				// ProductCode twoCode = dao.findSnByStr(str1);
				// if (twoCode != null) {
				// System.out.println("33");
				// continue;
				// }
				ProductCode pp = dao.loadProductCodeBySN1(str1);
				if (pp != null) {
					System.out.println("录入失败!SN1码:" + str1 + "已存在");
					result.setMsg("SN1码已存在！");
					result.setState(0);
					continue;
				}
				oneCode.setSn1(str1);
				// oneCode.setPcode(p1);
				oneCode.setColor(pc.getColor());
				oneCode.setProcess(name);
				oneCode.setPorder(pc.getPorder());
				oneCode.setModelCode(pc.getModelCode());
				oneCode.setPnCode(pc.getPnCode());
				// dao.addProductCode(oneCode);
			} else if (ps.getSerialSystem().equals("16")) {
				int x = (ps.getCodeLength() - ps.getSerialLength());
				StringBuffer strsn1 = new StringBuffer();
				String str1 = NumericalUtil.BaseConvert(String.valueOf(i), 10, 16);
				if(str1.length()<4){
					str1=String.format("%04d", Integer.parseInt(str1));
				}
				System.out.println(str1);
//				for (int j = 0; j < x; j+=2) {
//					strsn1.append(s1.substring(j, j+2)+":");
//				}
				//strsn1=strsn1.subSequence(0, 2)
				String sss=s1.substring(0, s1.length()-ps.getSerialLength())+""+str1;
				System.out.println("sss的值"+sss);
				sss=sss.subSequence(0, 2)+":"+sss.subSequence(2, 4)+":"+sss.subSequence(4, 6)+":"+sss.subSequence(6, 8)+":"+sss.subSequence(8, 10)+":"+sss.subSequence(10, 12);
				ProductCode pp = dao.loadProductCodeBySN1(sss);
				if (pp != null) {
					System.out.println(sss);
					result.setMsg("SN1码已存在！");
					result.setState(0);
					continue;
				}
				oneCode.setSn1(sss.toString());
				// oneCode.setPcode(p1);
				oneCode.setColor(pc.getColor());
				oneCode.setProcess(name);
				oneCode.setPorder(pc.getPorder());
				oneCode.setModelCode(pc.getModelCode());
				oneCode.setPnCode(pc.getPnCode());
				// dao.addProductCode(oneCode);
			} else if (ps.getSerialSystem().equals("10")) {
				StringBuffer strsn1 = new StringBuffer(s1.substring(0, s1.length() - ps.getSerialLength()));
				String sss = "%0" + ps.getSerialLength() + "d";
				String str1 = strsn1.append(String.format(sss, i)).toString();
				// ProductCode twoCode = dao.findSnByStr(str1);
				// if (twoCode != null) {
				// System.out.println("55");
				// continue;
				// }
				ProductCode pp = dao.loadProductCodeBySN1(str1);
				if (pp != null) {
					System.out.println(str1);
					result.setMsg("SN1码已存在！");
					result.setState(0);
					continue;
				}
				oneCode.setSn1(str1);
				// oneCode.setPcode(p1);
				oneCode.setColor(pc.getColor());
				oneCode.setProcess(name);
				oneCode.setPorder(pc.getPorder());
				oneCode.setModelCode(pc.getModelCode());
				oneCode.setPnCode(pc.getPnCode());
				// dao.addProductCode(oneCode);
			}else if (ps.getSerialSystem().equals("11")) {
				length = ps.getSerialLength() + 1;
				StringBuffer strsn1 = new StringBuffer(s1.substring(0, s1.length() - length));
				String sss = "%0" + ps.getSerialLength() + "d";
				String str1 = strsn1.append(String.format(sss, i)).toString();
				// ProductCode twoCode = dao.findSnByStr(str1);
				// if (twoCode != null) {
				// System.out.println("55");
				// continue;
				// }
				ProductCode pp = dao.loadProductCodeBySN1(ImeiUtils.getImeiBy14(str1));
				if (pp != null) {
					System.out.println(pp.getSn1());
					result.setMsg("SN1码已存在！");
					result.setState(0);
					continue;
				}
				oneCode.setSn1(ImeiUtils.getImeiBy14(str1));
				// oneCode.setPcode(p1);
				oneCode.setColor(pc.getColor());
				oneCode.setProcess(name);
				oneCode.setPorder(pc.getPorder());
				oneCode.setModelCode(pc.getModelCode());
				oneCode.setPnCode(pc.getPnCode());
				// dao.addProductCode(oneCode);
			}

			// 判断sn2是否需要录入
			if (s3 != null && !"".equals(s3)) {
				pdsd.setSnumber(p2);
				ps = dao.loadPSBySnumber(pdsd);
				// 需要录入时判断sn2是否符合规则
				if (s3.length() != ps.getCodeLength() || s4.length() != ps.getCodeLength()
						|| !ps.getKeyCharOne()
								.equals(s3.substring(Integer.parseInt(ps.getKeyLocatlOne()) - 1,
										Integer.parseInt(ps.getKeyLocatlOne())))
						|| !ps.getKeyCharOne().equals(s4.substring(Integer.parseInt(ps.getKeyLocatlOne()) - 1,
								Integer.parseInt(ps.getKeyLocatlOne())))) {
					System.out.println(pc);
					result.setMsg("SN2输入有误!请重新输入");
					result.setState(0);
					return result;
				}
				// 需要录入时判断sn2的条码进制规则
				if (ps.getSerialSystem().equals("34")) {
					
					String mac = s3.substring(s3.length() - ps.getSerialLength());					
					sm0n2 = Integer.parseInt(NumericalUtil.BaseConvert(mac, 34, 10));
					
					String str2 = s3.substring(0, s3.length() - ps.getSerialLength())
							+ NumericalUtil.BaseConvert(String.valueOf(sm0n2+(i-n)), 10, 34);
					oneCode.setSn2(str2);

				} else if (ps.getSerialSystem().equals("16")) {
					
					String mac = s3.substring(s3.length() - ps.getSerialLength());					
					sm0n2 = Integer.parseInt(NumericalUtil.BaseConvert(mac, 16, 10));
					
					int x = (ps.getCodeLength() - ps.getSerialLength());
					StringBuffer strsn2 = new StringBuffer();
					String str2 = NumericalUtil.BaseConvert(String.valueOf(sm0n2+(i-n)), 10, 16);
					for (int k = 0; k < x; k+=2) {
						strsn2.append(s3.substring(k, k+2)+":");
					}
					for (int j = 0; j < ps.getSerialLength(); j+=2) {
						if(j+2 == ps.getSerialLength()) {
							strsn2.append(str2.substring(j));
						}else{
							strsn2.append(str2.substring(j, j+2)+":");
						}
					}
					oneCode.setSn2(strsn2.toString());

				}else if (ps.getSerialSystem().equals("10")) {				
					sm0n2 = Integer.parseInt(s3.substring(s3.length() - ps.getSerialLength()));
					StringBuffer strsn2 = new StringBuffer(s3.substring(0, s3.length() - ps.getSerialLength()));
					String sss = "%0" + ps.getSerialLength() + "d";
					String str2 = strsn2.append(String.format(sss, sm0n2+(i-n))).toString();
					oneCode.setSn2(str2);

				}else if (ps.getSerialSystem().equals("11")) {	
					length = ps.getSerialLength() + 1;
					sm0n2 = Integer.parseInt(s3.substring(s3.length() - length,s3.length() - 1));
					StringBuffer strsn2 = new StringBuffer(s3.substring(0, s3.length() - length));
					String sss = "%0" + ps.getSerialLength() + "d";
					String str2 = strsn2.append(String.format(sss, sm0n2+(i-n))).toString();
					oneCode.setSn2(ImeiUtils.getImeiBy14(str2));

				}
			}

			// 判断sn3是否需要录入
			if (s5 != null && !"".equals(s5)) {
				pdsd.setSnumber(p3);
				ps = dao.loadPSBySnumber(pdsd);
				// 需要录入时判断sn3是否符合规则
				if (s5.length() != ps.getCodeLength() || s6.length() != ps.getCodeLength()
						|| !ps.getKeyCharOne().equals(s5.substring(Integer.parseInt(ps.getKeyLocatlOne()) - 1,Integer.parseInt(ps.getKeyLocatlOne())))
						|| !ps.getKeyCharOne().equals(s6.substring(Integer.parseInt(ps.getKeyLocatlOne()) - 1,Integer.parseInt(ps.getKeyLocatlOne())))) {
					System.out.println(pc);
					result.setMsg("SN3输入有误!请重新输入");
					result.setState(0);
					return result;
				}
				// 需要录入时判断sn3的条码规则
				if (ps.getSerialSystem().equals("34")) {
					String mac = s5.substring(s5.length() - ps.getSerialLength());					
					sm0n2 = Integer.parseInt(NumericalUtil.BaseConvert(mac, 34, 10));
					String str2 = s5.substring(0, s5.length() - ps.getSerialLength()) + NumericalUtil.BaseConvert(String.valueOf(sm0n2+(i-n)), 10, 34);
					oneCode.setSn3(str2);

				} else if (ps.getSerialSystem().equals("16")) {

					String mac = s5.substring(s5.length() - ps.getSerialLength());					
					sm0n2 = Integer.parseInt(NumericalUtil.BaseConvert(mac, 16, 10));
					
					int x = (ps.getCodeLength() - ps.getSerialLength());
					StringBuffer strsn2 = new StringBuffer();
					String str2 = NumericalUtil.BaseConvert(String.valueOf(sm0n2+(i-n)), 10, 16);
					for (int k = 0; k < x; k+=2) {
						strsn2.append(s5.substring(k, k+2)+":");
					}
					for (int j = 0; j < ps.getSerialLength(); j+=2) {
						if(j+2 == ps.getSerialLength()) {
							strsn2.append(str2.substring(j));
						}else{
							strsn2.append(str2.substring(j, j+2)+":");
						}
					}
					oneCode.setSn3(strsn2.toString());

				}else if (ps.getSerialSystem().equals("10")) {
					sm0n2 = Integer.parseInt(s5.substring(s5.length() - ps.getSerialLength()));
					StringBuffer strsn2 = new StringBuffer(s5.substring(0, s5.length() - ps.getSerialLength()));
					String sss = "%0" + ps.getSerialLength() + "d";
					String str2 = strsn2.append(String.format(sss, sm0n2+(i-n))).toString();
					oneCode.setSn3(str2);

				}else if (ps.getSerialSystem().equals("11")) {
					length = ps.getSerialLength() + 1;
					sm0n2 = Integer.parseInt(s5.substring(s5.length() - length,s5.length() - 1));
					StringBuffer strsn2 = new StringBuffer(s5.substring(0, s5.length() - length));
					String sss = "%0" + ps.getSerialLength() + "d";
					String str2 = strsn2.append(String.format(sss, sm0n2+(i-n))).toString();
					oneCode.setSn3(ImeiUtils.getImeiBy14(str2));

				}
			}

			// 判断sn4是否需要录入
			if (s7 != null && !"".equals(s7)) {
				pdsd.setSnumber(p4);
				ps = dao.loadPSBySnumber(pdsd);
				// 需要录入时判断sn4是否符合规则
				if (s7.length() != ps.getCodeLength() || s8.length() != ps.getCodeLength()
						|| !ps.getKeyCharOne()
								.equals(s7.substring(Integer.parseInt(ps.getKeyLocatlOne()) - 1,
										Integer.parseInt(ps.getKeyLocatlOne())))
						|| !ps.getKeyCharOne().equals(s8.substring(Integer.parseInt(ps.getKeyLocatlOne()) - 1,
								Integer.parseInt(ps.getKeyLocatlOne())))) {
					System.out.println(pc);
					result.setMsg("SN4输入有误!请重新输入");
					result.setState(0);
					return result;
				}
				// 需要录入时判断sn4的条码规则
				if (ps.getSerialSystem().equals("34")) {
					String mac = s7.substring(s7.length() - ps.getSerialLength());					
					sm0n2 = Integer.parseInt(NumericalUtil.BaseConvert(mac, 34, 10));
					
					String str2 = s7.substring(0, s7.length() - ps.getSerialLength())
							+ NumericalUtil.BaseConvert(String.valueOf(sm0n2+(i-n)), 10, 34);
					oneCode.setSn4(str2);

				} else if (ps.getSerialSystem().equals("16")) {

					String mac = s7.substring(s7.length() - ps.getSerialLength());					
					sm0n2 = Integer.parseInt(NumericalUtil.BaseConvert(mac, 16, 10));
					
					int x = (ps.getCodeLength() - ps.getSerialLength());
					StringBuffer strsn2 = new StringBuffer();
					String str2 = NumericalUtil.BaseConvert(String.valueOf(sm0n2+(i-n)), 10, 16);
					for (int k = 0; k < x; k+=2) {
						strsn2.append(s7.substring(k, k+2)+":");
					}
					for (int j = 0; j < ps.getSerialLength(); j+=2) {
						if(j+2 == ps.getSerialLength()) {
							strsn2.append(str2.substring(j));
						}else{
							strsn2.append(str2.substring(j, j+2)+":");
						}
					}
					oneCode.setSn4(strsn2.toString());

				}else if (ps.getSerialSystem().equals("10")) {
					sm0n2 = Integer.parseInt(s7.substring(s7.length() - ps.getSerialLength()));
					StringBuffer strsn2 = new StringBuffer(s7.substring(0, s7.length() - ps.getSerialLength()));
					String sss = "%0" + ps.getSerialLength() + "d";
					String str2 = strsn2.append(String.format(sss, sm0n2+(i-n))).toString();
					oneCode.setSn4(str2);

				}else if (ps.getSerialSystem().equals("11")) {		
					length = ps.getSerialLength() + 1;
					sm0n2 = Integer.parseInt(s7.substring(s7.length() - length,s7.length() - 1));
					StringBuffer strsn2 = new StringBuffer(s7.substring(0, s7.length() - length));
					String sss = "%0" + ps.getSerialLength() + "d";
					String str2 = strsn2.append(String.format(sss, sm0n2+(i-n))).toString();
					oneCode.setSn4(ImeiUtils.getImeiBy14(str2));

				}
			}

			// 判断sn5是否需要录入
			if (s9 != null && !"".equals(s9)) {
				pdsd.setSnumber(p5);
				ps = dao.loadPSBySnumber(pdsd);
				// 需要录入时判断sn5是否符合规则
				if (s9.length() != ps.getCodeLength() || s10.length() != ps.getCodeLength()
						|| !ps.getKeyCharOne().equals(s9.substring(Integer.parseInt(ps.getKeyLocatlOne()) - 1,
										Integer.parseInt(ps.getKeyLocatlOne())))
						|| !ps.getKeyCharOne().equals(s10.substring(Integer.parseInt(ps.getKeyLocatlOne()) - 1,
								Integer.parseInt(ps.getKeyLocatlOne())))) {
					System.out.println(pc);
					result.setMsg("SN5输入有误!请重新输入");
					result.setState(0);
					return result;
				}
				// 需要录入时判断sn5的条码规则
				if (ps.getSerialSystem().equals("34")) {
					String mac = s9.substring(s9.length() - ps.getSerialLength());					
					sm0n2 = Integer.parseInt(NumericalUtil.BaseConvert(mac, 34, 10));
					
					String str2 = s9.substring(0, s9.length() - ps.getSerialLength())
							+ NumericalUtil.BaseConvert(String.valueOf(sm0n2+(i-n)), 10, 34);
					oneCode.setSn5(str2);

				} else if (ps.getSerialSystem().equals("16")) {

					String mac = s9.substring(s9.length() - ps.getSerialLength());					
					sm0n2 = Integer.parseInt(NumericalUtil.BaseConvert(mac, 16, 10));
					
					int x = (ps.getCodeLength() - ps.getSerialLength());
					StringBuffer strsn2 = new StringBuffer();
					String str2 = NumericalUtil.BaseConvert(String.valueOf(sm0n2+(i-n)), 10, 16);
					for (int k = 0; k < x; k+=2) {
						strsn2.append(s9.substring(k, k+2)+":");
					}
					for (int j = 0; j < ps.getSerialLength(); j+=2) {
						if(j+2 == ps.getSerialLength()) {
							strsn2.append(str2.substring(j));
						}else{
							strsn2.append(str2.substring(j, j+2)+":");
						}
					}
					oneCode.setSn5(strsn2.toString());

				}else if (ps.getSerialSystem().equals("10")) {
					sm0n2 = Integer.parseInt(s9.substring(s9.length() - ps.getSerialLength()));
					StringBuffer strsn2 = new StringBuffer(s9.substring(0, s9.length() - ps.getSerialLength()));
					String sss = "%0" + ps.getSerialLength() + "d";
					String str2 = strsn2.append(String.format(sss, sm0n2+(i-n))).toString();
					oneCode.setSn5(str2);

				}else if (ps.getSerialSystem().equals("11")) {				
					length = ps.getSerialLength() + 1;
					sm0n2 = Integer.parseInt(s9.substring(s9.length() - length,s9.length() - 1));
					StringBuffer strsn2 = new StringBuffer(s9.substring(0, s9.length() - length));
					String sss = "%0" + ps.getSerialLength() + "d";
					String str2 = strsn2.append(String.format(sss, sm0n2+(i-n))).toString();
					oneCode.setSn5(ImeiUtils.getImeiBy14(str2));

				}
			}

			// 判断sn6是否需要录入
			if (s11 != null && !"".equals(s11)) {
				pdsd.setSnumber(p6);
				ps = dao.loadPSBySnumber(pdsd);
				// 需要录入时判断sn6是否符合规则
				if (s11.length() != ps.getCodeLength() || s12.length() != ps.getCodeLength()
						|| !ps.getKeyCharOne()
								.equals(s11.substring(Integer.parseInt(ps.getKeyLocatlOne()) - 1,
										Integer.parseInt(ps.getKeyLocatlOne())))
						|| !ps.getKeyCharOne().equals(s12.substring(Integer.parseInt(ps.getKeyLocatlOne()) - 1,
								Integer.parseInt(ps.getKeyLocatlOne())))) {
					System.out.println(pc);
					result.setMsg("SN6输入有误!请重新输入");
					result.setState(0);
					return result;
				}
				// 需要录入时判断sn6的条码规则
				if (ps.getSerialSystem().equals("34")) {
					String mac = s11.substring(s11.length() - ps.getSerialLength());					
					sm0n2 = Integer.parseInt(NumericalUtil.BaseConvert(mac, 34, 10));
					
					String str2 = s11.substring(0, s11.length() - ps.getSerialLength())
							+ NumericalUtil.BaseConvert(String.valueOf(sm0n2+(i-n)), 10, 34);
					oneCode.setSn6(str2);

				} else if (ps.getSerialSystem().equals("16")) {

					String mac = s11.substring(s11.length() - ps.getSerialLength());					
					sm0n2 = Integer.parseInt(NumericalUtil.BaseConvert(mac, 16, 10));
					
					int x = (ps.getCodeLength() - ps.getSerialLength());
					StringBuffer strsn2 = new StringBuffer();
					String str2 = NumericalUtil.BaseConvert(String.valueOf(sm0n2+(i-n)), 10, 16);
					for (int k = 0; k < x; k+=2) {
						strsn2.append(s11.substring(k, k+2)+":");
					}
					for (int j = 0; j < ps.getSerialLength(); j+=2) {
						if(j+2 == ps.getSerialLength()) {
							strsn2.append(str2.substring(j));
						}else{
							strsn2.append(str2.substring(j, j+2)+":");
						}
					}
					oneCode.setSn6(strsn2.toString());

				}else if (ps.getSerialSystem().equals("10")) {
					sm0n2 = Integer.parseInt(s11.substring(s11.length() - ps.getSerialLength()));
					StringBuffer strsn2 = new StringBuffer(s11.substring(0, s11.length() - ps.getSerialLength()));
					String sss = "%0" + ps.getSerialLength() + "d";
					String str2 = strsn2.append(String.format(sss, sm0n2+(i-n))).toString();
					oneCode.setSn6(str2);

				}else if (ps.getSerialSystem().equals("11")) {		
					length = ps.getSerialLength() + 1;
					sm0n2 = Integer.parseInt(s11.substring(s11.length() - length,s11.length() - 1));
					StringBuffer strsn2 = new StringBuffer(s11.substring(0, s11.length() - length));
					String sss = "%0" + ps.getSerialLength() + "d";
					String str2 = strsn2.append(String.format(sss, sm0n2+(i-n))).toString();
					oneCode.setSn6(ImeiUtils.getImeiBy14(str2));

				}
			}

			// 判断sn7是否需要录入
			if (s13 != null && !"".equals(s13)) {
				pdsd.setSnumber(p7);
				ps = dao.loadPSBySnumber(pdsd);
				// 需要录入时判断sn7是否符合规则
				if (s13.length() != ps.getCodeLength() || s14.length() != ps.getCodeLength()
						|| !ps.getKeyCharOne()
								.equals(s13.substring(Integer.parseInt(ps.getKeyLocatlOne()) - 1,
										Integer.parseInt(ps.getKeyLocatlOne())))
						|| !ps.getKeyCharOne().equals(s14.substring(Integer.parseInt(ps.getKeyLocatlOne()) - 1,
								Integer.parseInt(ps.getKeyLocatlOne())))) {
					System.out.println(pc);
					result.setMsg("SN7输入有误!请重新输入");
					result.setState(0);
					return result;
				}
				// 需要录入时判断sn7的条码规则
				if (ps.getSerialSystem().equals("34")) {
					String mac = s13.substring(s13.length() - ps.getSerialLength());					
					sm0n2 = Integer.parseInt(NumericalUtil.BaseConvert(mac, 34, 10));
					
					String str2 = s13.substring(0, s13.length() - ps.getSerialLength())
							+ NumericalUtil.BaseConvert(String.valueOf(sm0n2+(i-n)), 10, 34);
					oneCode.setSn7(str2);

				} else if (ps.getSerialSystem().equals("16")) {

					String mac = s13.substring(s13.length() - ps.getSerialLength());					
					sm0n2 = Integer.parseInt(NumericalUtil.BaseConvert(mac, 16, 10));
					
					int x = (ps.getCodeLength() - ps.getSerialLength());
					StringBuffer strsn2 = new StringBuffer();
					String str2 = NumericalUtil.BaseConvert(String.valueOf(sm0n2+(i-n)), 10, 16);
					for (int k = 0; k < x; k+=2) {
						strsn2.append(s13.substring(k, k+2)+":");
					}
					for (int j = 0; j < ps.getSerialLength(); j+=2) {
						if(j+2 == ps.getSerialLength()) {
							strsn2.append(str2.substring(j));
						}else{
							strsn2.append(str2.substring(j, j+2)+":");
						}
					}
					oneCode.setSn7(strsn2.toString());

				}else if (ps.getSerialSystem().equals("10")) {
					sm0n2 = Integer.parseInt(s13.substring(s13.length() - ps.getSerialLength()));
					StringBuffer strsn2 = new StringBuffer(s13.substring(0, s13.length() - ps.getSerialLength()));
					String sss = "%0" + ps.getSerialLength() + "d";
					String str2 = strsn2.append(String.format(sss, sm0n2+(i-n))).toString();
					oneCode.setSn7(str2);

				}else if (ps.getSerialSystem().equals("11")) {
					length = ps.getSerialLength() + 1;
					sm0n2 = Integer.parseInt(s13.substring(s13.length() - length,s13.length() - 1));
					StringBuffer strsn2 = new StringBuffer(s13.substring(0, s13.length() - length));
					String sss = "%0" + ps.getSerialLength() + "d";
					String str2 = strsn2.append(String.format(sss, sm0n2+(i-n))).toString();
					oneCode.setSn7(ImeiUtils.getImeiBy14(str2));

				}
			}

			// 判断data1是否需要录入
			if (s15 != null && !"".equals(s15)) {
				pdsd.setSnumber(p8);
				ps = dao.loadPSBySnumber(pdsd);
				// 需要录入时判断data1是否符合规则
				if (s15.length() != ps.getCodeLength() || s16.length() != ps.getCodeLength()
						|| !ps.getKeyCharOne()
								.equals(s15.substring(Integer.parseInt(ps.getKeyLocatlOne()) - 1,
										Integer.parseInt(ps.getKeyLocatlOne())))
						|| !ps.getKeyCharOne().equals(s16.substring(Integer.parseInt(ps.getKeyLocatlOne()) - 1,
								Integer.parseInt(ps.getKeyLocatlOne())))) {
					System.out.println(pc);
					result.setMsg("data1输入有误!请重新输入");
					result.setState(0);
					return result;
				}
				// 需要录入时判断data1的条码规则
				if (ps.getSerialSystem().equals("34")) {
					String mac = s15.substring(s15.length() - ps.getSerialLength());					
					sm0n2 = Integer.parseInt(NumericalUtil.BaseConvert(mac, 34, 10));
					
					String str2 = s15.substring(0, s15.length() - ps.getSerialLength())
							+ NumericalUtil.BaseConvert(String.valueOf(sm0n2+(i-n)), 10, 34);
					oneCode.setData1(str2);

				} else if (ps.getSerialSystem().equals("16")) {

					String mac = s15.substring(s15.length() - ps.getSerialLength());					
					sm0n2 = Integer.parseInt(NumericalUtil.BaseConvert(mac, 16, 10));
					
					int x = (ps.getCodeLength() - ps.getSerialLength());
					StringBuffer strsn2 = new StringBuffer();
					String str2 = NumericalUtil.BaseConvert(String.valueOf(sm0n2+(i-n)), 10, 16);
					for (int k = 0; k < x; k+=2) {
						strsn2.append(s15.substring(k, k+2)+":");
					}
					for (int j = 0; j < ps.getSerialLength(); j+=2) {
						if(j+2 == ps.getSerialLength()) {
							strsn2.append(str2.substring(j));
						}else{
							strsn2.append(str2.substring(j, j+2)+":");
						}
					}
					oneCode.setData1(strsn2.toString());

				}else if (ps.getSerialSystem().equals("10")) {
					sm0n2 = Integer.parseInt(s15.substring(s15.length() - ps.getSerialLength()));
					StringBuffer strsn2 = new StringBuffer(s15.substring(0, s15.length() - ps.getSerialLength()));
					String sss = "%0" + ps.getSerialLength() + "d";
					String str2 = strsn2.append(String.format(sss, sm0n2+(i-n))).toString();
					oneCode.setData1(str2);

				}else if (ps.getSerialSystem().equals("11")) {
					length = ps.getSerialLength() + 1;
					sm0n2 = Integer.parseInt(s15.substring(s15.length() - length,s15.length() - 1));
					StringBuffer strsn2 = new StringBuffer(s15.substring(0, s15.length() - length));
					String sss = "%0" + ps.getSerialLength() + "d";
					String str2 = strsn2.append(String.format(sss, sm0n2+(i-n))).toString();
					oneCode.setData1(ImeiUtils.getImeiBy14(str2));

				}
			}

			// 判断data2是否需要录入
			if (s17 != null && !"".equals(s17)) {
				pdsd.setSnumber(p9);
				ps = dao.loadPSBySnumber(pdsd);
				// 需要录入时判断data2是否符合规则
				if (s17.length() != ps.getCodeLength() || s18.length() != ps.getCodeLength()
						|| !ps.getKeyCharOne()
								.equals(s17.substring(Integer.parseInt(ps.getKeyLocatlOne()) - 1,
										Integer.parseInt(ps.getKeyLocatlOne())))
						|| !ps.getKeyCharOne().equals(s18.substring(Integer.parseInt(ps.getKeyLocatlOne()) - 1,
								Integer.parseInt(ps.getKeyLocatlOne())))) {
					System.out.println(pc);
					result.setMsg("data2输入有误!请重新输入");
					result.setState(0);
					return result;
				}
				// 需要录入时判断data2的条码规则
				if (ps.getSerialSystem().equals("34")) {
					String mac = s17.substring(s17.length() - ps.getSerialLength());					
					sm0n2 = Integer.parseInt(NumericalUtil.BaseConvert(mac, 34, 10));
					
					String str2 = s17.substring(0, s17.length() - ps.getSerialLength())
							+ NumericalUtil.BaseConvert(String.valueOf(sm0n2+(i-n)), 10, 34);
					oneCode.setData2(str2);

				} else if (ps.getSerialSystem().equals("16")) {
					String mac = s17.substring(s17.length() - ps.getSerialLength());					
					sm0n2 = Integer.parseInt(NumericalUtil.BaseConvert(mac, 16, 10));
					
					int x = (ps.getCodeLength() - ps.getSerialLength());
					StringBuffer strsn2 = new StringBuffer();
					String str2 = NumericalUtil.BaseConvert(String.valueOf(sm0n2+(i-n)), 10, 16);
					for (int k = 0; k < x; k+=2) {
						strsn2.append(s17.substring(k, k+2)+":");
					}
					for (int j = 0; j < ps.getSerialLength(); j+=2) {
						if(j+2 == ps.getSerialLength()) {
							strsn2.append(str2.substring(j));
						}else{
							strsn2.append(str2.substring(j, j+2)+":");
						}
					}
					oneCode.setData2(strsn2.toString());

				}else if (ps.getSerialSystem().equals("10")) {
					sm0n2 = Integer.parseInt(s17.substring(s17.length() - ps.getSerialLength()));
					StringBuffer strsn2 = new StringBuffer(s17.substring(0, s17.length() - ps.getSerialLength()));
					String sss = "%0" + ps.getSerialLength() + "d";
					String str2 = strsn2.append(String.format(sss, sm0n2+(i-n))).toString();
					oneCode.setData2(str2);

				}else if (ps.getSerialSystem().equals("11")) {
					length = ps.getSerialLength() + 1;
					sm0n2 = Integer.parseInt(s17.substring(s17.length() - length,s17.length() - 1));
					StringBuffer strsn2 = new StringBuffer(s17.substring(0, s17.length() - length));
					String sss = "%0" + ps.getSerialLength() + "d";
					String str2 = strsn2.append(String.format(sss, sm0n2+(i-n))).toString();
					oneCode.setData2(ImeiUtils.getImeiBy14(str2));
					
				}
			}
			int q = dao.addProductCode(oneCode);
			if (q != 1) {
				System.out.println("录入失败:" + oneCode);
				result.setMsg("录入失败！");
				result.setState(0);
				continue;
			}
			System.out.println(oneCode);
			listcode.add(oneCode);
		}

		result.setMsg("批量录入成功");
		result.setState(1);
		return result;

	}

	/*
	 * 硬盘编码规则新规则录入
	 */
	/*
	 * public CyResult addCodes(String str1, String str2, String str3, String
	 * str4, String str5, String str6, Double cost, Binding_code code) {
	 * CyResult result = new CyResult();
	 * 
	 * String num1 = str1.substring(10);
	 * 
	 * String num2 = str2.substring(10);
	 * 
	 * String chsn1 = str3.substring(10);
	 * 
	 * String chsn2 = str4.substring(10);
	 * 
	 * // int n = Integer.parseInt(str5.substring(str5.length() - 5,
	 * str5.length())); // int m = 1 +
	 * Integer.parseInt(str6.substring(str6.length() - 5, str6.length()));
	 * 
	 * // if (str1.length() != 18 || str2.length() != 18) { //
	 * System.out.println(pc); // result.setMsg("SN1输入有误!请重新输入"); //
	 * result.setState(0); // return result; // } // // if (str3.length() != 20
	 * || str4.length() != 20) { // System.out.println(pc); //
	 * result.setMsg("SN2输入有误!请重新输入"); // result.setState(0); // return result;
	 * // }
	 * 
	 * // if (str5.length() != 15 || str5.length() != 15) { //
	 * System.out.println(pc); // result.setMsg("DATA1输入有误!请重新输入"); //
	 * result.setState(0); // return result; // }
	 * 
	 * if (!chsn1.equals(str1) || !chsn2.equals(str2)) {
	 * result.setMsg("彩盒码格式错误"); result.setState(2); return result; }
	 * 
	 * // if (pc.getPcode() == null || pc.getPcode().trim().isEmpty()) { //
	 * result.setMsg("选择产品名错误"); // result.setState(2); // return result; // }
	 * 
	 * String num4 = str4.substring(10);
	 * 
	 * int n1 = Integer.parseInt(num1);
	 * 
	 * int n2 = Integer.parseInt(num2);
	 * 
	 * if (n2 < n1) { result.setMsg("录入的格式出现错误"); result.setState(0); return
	 * result; }
	 * 
	 * // 录入产品列表 ProductList pl = new ProductList(); //ProductScheduling pd =
	 * dao.selectPnameByProcess(pc.getProcess()); pl.setLcost(cost);
	 * pl.setLname("硬盘"); pl.setLmodel(code.getModelCode());
	 * pl.setLmaterial(code.getPNCode()); pl.setLorder(code.getBxk_SN());
	 * pl.setLplanyield(n2 - n1 + 1); dao.addProductList(pl);
	 * System.out.println(pl); // ProductListServiceImpl plsi = new
	 * ProductListServiceImpl(); // plsi.addProductList(pl); // ProductList ppl
	 * = dao.findProductList(pl); // if (ppl != null) { // int i =
	 * pl.getLplanyield() + ppl.getLplanyield(); // pl.setLplanyield(i); //
	 * dao.updateProductListLplanyield(pl); // } else { //
	 * dao.addProductList(pl); // }
	 * 
	 * List<Binding_code> listcode=new ArrayList<Binding_code>(); List<String>
	 * batchList=dao.findCatch(); List<Integer> intlist=new
	 * ArrayList<Integer>(); for(String batch:batchList){
	 * //System.out.println(batch); if(batch!=null&&!batch.trim().isEmpty()){
	 * int number=Integer.parseInt(batch.split("@")[1]); //
	 * System.out.println(number); intlist.add(number); } }
	 * if(intlist==null||intlist.size()<1){ intlist.add(0); } int
	 * max=Collections.max(intlist);
	 * 
	 * String sst = null;
	 * 
	 * for(int i=n1;i<=n2;i++){ String
	 * str=str1.substring(0,10)+String.format("%05d", i).toString();
	 * 
	 * Binding_code twoCode=dao.findSnByStrr(str);
	 * 
	 * if(twoCode!=null){ continue; } // StringBuffer sb = new
	 * StringBuffer(str5.substring(0, 9)); // sst =
	 * sb.append(String.format("%05d", n++)).toString();
	 * 
	 * Binding_code oneCode=new Binding_code(); //oneCode.setDisk_SN(sst);
	 * oneCode.setPCBA_SN(str); oneCode.setModelCode(code.getModelCode());
	 * oneCode.setPNCode(code.getPNCode()); oneCode.setCreatime(CyUtil.getTime()
	 * ); oneCode.setCh_SN(str3.substring(0, 10)+str);
	 * 
	 * 
	 * 
	 * //System.out.println(max);
	 * //System.out.println(code.getPNCode()+"@"+(max+1));
	 * System.out.println(oneCode.getCh_SN());
	 * oneCode.setBatch(code.getPNCode()+"@"+(max+1)); dao.insertCode(oneCode);
	 * listcode.add(oneCode); } // for(Binding_code coderrr:listcode){ //
	 * System.out.println(coderrr); // } result.setMsg("批量录入成功");
	 * result.setState(1); return result;
	 * 
	 * }
	 */

	/*
	 * 条码新增页面查询工艺号
	 */
	@Override
	public CyResult selectProcess() {
		CyResult result = new CyResult();
		List list = dao.selectProcess();
		result.setData(list);
		return result;
	}

	/*
	 * 条码新增页面查询产品名
	 */
	@Override
	public CyResult selectPcode() {
		CyResult result = new CyResult();
		List list = dao.selectPcode();
		result.setData(list);
		return result;
	}

	/*
	 * 删除工艺排程
	 */
	@Override
	public CyResult deleteProductScheduling(ProductScheduling ps) {
		CyResult result = new CyResult();
		if (ps.getPid() == null) {
			result.setMsg("未能选定一条排程!");
			result.setState(0);
			return result;
		}
		ProductScheduling psLing = dao.selectProductScheduling(ps);
		int n = dao.deleteProductScheduling(ps);
		// System.out.println(ps);
		if (n != 1) {
			throw new RuntimeException("删除列表失败!");
		}
		int db = dao.selectCountName(psLing.getPname());
		if (db == 0) {
			dao.removePname(psLing.getPname());
		}
		result.setMsg("删除成功!");
		result.setState(1);
		return result;
	}

	/*
	 * 新建产品列表
	 */
	@Override
	public void addProductList(ProductList pl) {
		System.out.println(pl);
		ProductList ppl = dao.findProductList(pl);
		if (ppl != null) {
			int i = pl.getLplanyield() + ppl.getLplanyield();
			pl.setLplanyield(i);
			dao.updateProductListLplanyield(pl);
		} else {
			dao.addProductList(pl);
		}
	}

	/*
	 * 加载全部产品列表
	 * 
	 * @see com.cy.service.list.ProductListService#selectAllProductList()
	 */
	@Override
	public CyResult selectAllProductList(Integer pageNumber) {
		CyResult result = new CyResult();
		if(pageNumber==null){
			pageNumber=0;
		}else{
			pageNumber=(pageNumber-1)*15;
		}
		List<ProductList> list = dao.selectAllProductList(pageNumber);
		
		if (list == null || list.size() < 1) {
			result.setMsg("无产品列表信息!");
			result.setData1(0);
			result.setState(0);
			return result;
		}
		pageNumber=null;
		List<ProductList> list1 = dao.selectAllProductList(pageNumber);
		result.setData1(list1.size());
		result.setState(1);
		result.setMsg("加载产品列表成功");
		result.setData(list);
		return result;
	}

	/*
	 * 查询单个产品列表
	 * 
	 * @see com.cy.service.list.ProductListService#selectProductList()
	 */
	@Override
	public CyResult selectProductList(ProductList pl) {
		CyResult result = new CyResult();
		if (pl.getLid() == null) {
			result.setMsg("未能选定一条工艺排程!");
			result.setState(0);
			return result;
		}
		ProductList ppl = dao.selectProductList(pl);
		if (ppl.getLid() == null) {
			result.setMsg("产品列表不存在!");
			result.setState(0);
			return result;
		}
		result.setMsg("查询成功!");
		result.setState(1);
		result.setData(ppl);
		return result;
	}

	/*
	 * 删除产品列表
	 */
	@Override
	public CyResult deleteProductList(ProductList pl) {
		CyResult result = new CyResult();
		if (pl.getLid() == null) {
			result.setMsg("未能选定一条列表!");
			result.setState(0);
			return result;
		}
		int n = dao.deleteProductList(pl);
		if (n != 1) {
			// throw new RuntimeException("删除列表失败!");
			result.setMsg("产品列表不存在!");
			result.setState(0);
			return result;
		}
		result.setMsg("删除成功!");
		result.setState(1);
		return result;
	}

	/*
	 * 新 条码新增页面查询产品名
	 */
	@Override
	public CyResult selectAllPname() {
		CyResult result = new CyResult();
		List<String> list = dao.selectAllPname();
		if (list == null || list.size() < 1) {
			result.setMsg("无产品名信息!请先在条码规则中添加!");
			result.setState(0);
			return result;
		}
		for (int i = 0; i < list.size(); i++) {
			mappp.put(i, list.get(i));
			List<ProductScheduling> list2 = dao.selectCodeNameByPname(list.get(i));
			map.put(i, list2);
		}

		result.setMsg("加载产品名成功");
		result.setState(1);
		result.setData1(list);
		result.setData(mappp);
		// result.setData1(map);
		return result;
	}

	/*
	 * 新 条码新增页面根据产品名查询对应编码规则
	 */
	@Override
	public CyResult selectCodeNameByPname(Integer i) {
		CyResult result = new CyResult();
		String st = mappp.get(i);
		List<ProductScheduling> list = dao.selectCodeNameByPname(st);
		if (list == null || list.size() < 1) {
			result.setMsg("无条码名信息!");
			result.setState(0);
			return result;
		}
		result.setMsg("加载条码名成功");
		result.setState(1);
		result.setData(list);
		return result;
	}

	@Override
	public CyResult insertProductScheduling(ProductScheduling ps,Integer ppid) {
		CyResult result = new CyResult();
		List<ProductScheduling> psList = dao.loadPs(ps.getSnumber());
		if (psList.size() > 0) {
			result.setMsg("该编号已存在");
			result.setState(1);
			return result;
		}
		
		Map<String, Object> psMap = new HashMap<String, Object>();
		psMap.put("codeName", ps.getCodeName());
		ProductDetails pd=new ProductDetails();
		pd.setPid(ppid);
		List<ProductDetails> pList=lineDao.findProductById(pd);
		if(pList==null||pList.size()<1){
			result.setMsg("该产品不存在请尝试刷新!");
			result.setState(5);
			return result;
		}
		psMap.put("pname",pList.get(0).getPname());
		List<ProductScheduling> psCodeName = dao.loadCodeName(psMap);
		if (psCodeName.size() > 0) {
			result.setMsg("该产品条码名已存在");
			result.setState(2);
			return result;
		}
//		if ("0".equals(ps.getSerialSystem())) {
//			ps.setSerialSystem("11");
//		}
//		if ("1".equals(ps.getSerialSystem())) {
//			ps.setSerialSystem("10");
//		}
//		if ("2".equals(ps.getSerialSystem())) {
//			ps.setSerialSystem("16");
//		}
//		if ("3".equals(ps.getSerialSystem())) {
//			ps.setSerialSystem("34");
//		}
		ps.setPd(pList.get(0));
		ps.setPname(pList.get(0).getPname());
		try {
			int addPs = dao.addPs(ps);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("增加失败");
			result.setState(3);
			return result;
		}
		
		
		result.setMsg("增加成功!");
		result.setState(0);
		return result;
	}

	/*
	 * 加载所有工作站 (non-Javadoc)
	 * 
	 * @see com.cy.service.list.ProductListService#loadAllWorkStation()
	 */
	@Override
	public CyResult loadAllWorkStation(Integer page, Integer limit) {
		CyResult result = new CyResult();
		if(page==null){
			page=1;
		}
		int pageSize = (page-1)*limit;
		WorkStationCode wsc = new WorkStationCode();
		wsc.setPageSize(pageSize);
		wsc.setLimit(limit);
		List<WorkStationCode> wscList=dao.loadAllWSC(wsc);
		List<WorkStationCode> wList = new ArrayList<>();
		for(WorkStationCode workStationCode : wscList){
			if(workStationCode!=null){
				ProductScheduling ps = dao.selectPidByProcess(workStationCode.getSn1());
				if(ps!=null){
					workStationCode.setSnName1(ps.getCodeName());
				}
				
				ProductScheduling ps2 = dao.selectPidByProcess(workStationCode.getSn2());
				if(ps2!=null){
					workStationCode.setSnName2(ps2.getCodeName());
				}
				
				ProductScheduling ps3 = dao.selectPidByProcess(workStationCode.getSn3());
				if(ps3!=null){
					workStationCode.setSnName3(ps3.getCodeName());
				}
				
				ProductScheduling ps4 = dao.selectPidByProcess(workStationCode.getSn4());
				if(ps4!=null){
					workStationCode.setSnName4(ps4.getCodeName());
				}
				
				ProductScheduling ps5 = dao.selectPidByProcess(workStationCode.getSn5());
				if(ps5!=null){
					workStationCode.setSnName5(ps5.getCodeName());
				}
				
				ProductScheduling ps6 = dao.selectPidByProcess(workStationCode.getSn6());
				if(ps6!=null){
					workStationCode.setSnName6(ps6.getCodeName());
				}
				
				ProductScheduling ps7 = dao.selectPidByProcess(workStationCode.getSn7());
				if(ps7!=null){
					workStationCode.setSnName7(ps7.getCodeName());
				}
				
				ProductScheduling ps8 = dao.selectPidByProcess(workStationCode.getData1());
				if(ps8!=null){
					workStationCode.setDataName1(ps8.getCodeName());
				}
				
				ProductScheduling ps9 = dao.selectPidByProcess(workStationCode.getData2());
				if(ps9!=null){
					workStationCode.setDataName2(ps9.getCodeName());
				}
				
			}
			ProductDetails product = dao.findProduct(workStationCode.getPid());
			if(product==null){
				workStationCode.setPname("");
				wList.add(workStationCode);
			}else{
				workStationCode.setPname(product.getPname());
				wList.add(workStationCode);
			}
		}
		int count = dao.countAllWsc();
		if (wscList != null) {
			result.setCode(0);
			result.setCount(count);
			result.setState(0);
			result.setMsg("获取成功");
			result.setData(wList);
			return result;
		}
		result.setState(2);
		result.setMsg("获取失败");
		return result;
	}

	/*
	 * 增加工作站 (non-Javadoc)
	 * 
	 * @see
	 * com.cy.service.list.ProductListService#addWorkStation(com.cy.domain.list.
	 * WorkStationCode)
	 */
	@Override
	public CyResult addWorkStation(WorkStationCode wsc) {
		CyResult result = new CyResult();
	/*	if (wsc == null) {
			result.setMsg("未输入工作站");
			result.setState(1);
			return result;
		}*/
		
		List<WorkStationCode> wscList=dao.findStationByCode(wsc.getWsNumber());
		if(wscList.size()>0){
			result.setMsg("工作站编码重复!");
			result.setState(2);
			return result;
		}
		List<String> list = dao.selectAllPname();
		if (list == null || list.size() < 1) {
			result.setMsg("无产品名信息!请先在条码规则中添加!");
			result.setState(0);
			return result;
		}
	
//		if (wsc.getSn1() != null && !"".equals(wsc.getSn1())) {
//			ProductScheduling ps = dao.selectPnameByProcess(wsc.getSn1());
//			wsc.setSn1(ps.getCodeName());
//		}
//		if (wsc.getSn2() != null && !"".equals(wsc.getSn2())) {
//			ProductScheduling ps = dao.selectPnameByProcess(wsc.getSn2());
//			wsc.setSn2(ps.getCodeName());
//		}
//		if (wsc.getSn3() != null && !"".equals(wsc.getSn3())) {
//			ProductScheduling ps = dao.selectPnameByProcess(wsc.getSn3());
//			wsc.setSn3(ps.getCodeName());
//		}
//		if (wsc.getSn4() != null && !"".equals(wsc.getSn4())) {
//			ProductScheduling ps = dao.selectPnameByProcess(wsc.getSn4());
//			wsc.setSn4(ps.getCodeName());
//		}
//		if (wsc.getSn5() != null && !"".equals(wsc.getSn5())) {
//			ProductScheduling ps = dao.selectPnameByProcess(wsc.getSn5());
//			wsc.setSn5(ps.getCodeName());
//		}
//		if (wsc.getSn6() != null && !"".equals(wsc.getSn6())) {
//			ProductScheduling ps = dao.selectPnameByProcess(wsc.getSn6());
//			wsc.setSn6(ps.getCodeName());
//		}
//		if (wsc.getSn7() != null && !"".equals(wsc.getSn7())) {
//			ProductScheduling ps = dao.selectPnameByProcess(wsc.getSn7());
//			wsc.setSn7(ps.getCodeName());
//		}
//		if (wsc.getData1() != null && !"".equals(wsc.getData1())) {
//			ProductScheduling ps = dao.selectPnameByProcess(wsc.getData1());
//			wsc.setData1(ps.getCodeName());
//		}
//		if (wsc.getData2() != null && !"".equals(wsc.getData2())) {
//			ProductScheduling ps = dao.selectPnameByProcess(wsc.getData2());
//			wsc.setData2(ps.getCodeName());
//		}
		int row = dao.addWSC(wsc);
		if (row == 1) {
			result.setMsg("添加成功");
			result.setState(0);
			return result;
		}
		result.setMsg("添加失败");
		result.setState(2);
		return result;
	}

	/*
	 * 删除工作站 (non-Javadoc)
	 * 
	 * @see com.cy.service.list.ProductListService#deleteWorkStation(java.lang.
	 * String)
	 */
	@Override
	public CyResult deleteWorkStation(String mid) {
		CyResult result = new CyResult();
		int row = dao.deleteWorkStation(mid);
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
	public CyResult updateStation(WorkStationCode wsc) {
		CyResult result = new CyResult();
		/*if(wsc==null){
			result.setMsg("对象为空");
			result.setState(1);
			return result;
		}*/
		
//		Map<String, Object> map = new HashMap<>();
//		map.put("mid", wsc.getMid());
//		if("0".equals(wsc.getMissStation())){
//			map.put("missStation", "可以");
//		}
//		if("1".equals(wsc.getMissStation())){
//			map.put("missStation", "不可");
//		}
		int abc = dao.updateStation(wsc);
		if(abc==1){
			result.setMsg("修改成功");
			result.setState(0);
			return result;
		}
		result.setMsg("修改失败");
		result.setState(2);
		return result;
	}
    
	
	/*
	 * 修改条码规则(non-Javadoc)
	 * @see com.cy.service.list.ProductListService#editProductScheduling(java.lang.Integer, com.cy.domain.list.ProductScheduling)
	 */
	@Override
	public CyResult editProductScheduling(Integer ppid, ProductScheduling ps) {
		CyResult result=new CyResult();
		List<ProductScheduling> psList = dao.loadPs(ps.getSnumber());
		if (psList.size() > 1) {
			result.setMsg("该编号已存在");
			result.setState(1);
			return result;
		}
		
		Map<String, Object> psMap = new HashMap<String, Object>();
		psMap.put("codeName", ps.getCodeName());
		ProductDetails pd=new ProductDetails();
		pd.setPid(ppid);
		List<ProductDetails> pList=lineDao.findProductById(pd);
		if(pList==null||pList.size()<1){
			result.setMsg("该产品不存在请尝试刷新!");
			result.setState(5);
			return result;
		}
		psMap.put("pname",pList.get(0).getPname());
		List<ProductScheduling> psCodeName = dao.loadCodeName(psMap);
		if (psCodeName.size() > 1) {
			result.setMsg("该产品条码名已存在");
			result.setState(2);
			return result;
		}
		if ("0".equals(ps.getSerialSystem())) {
			ps.setSerialSystem("11");
		}
		if ("1".equals(ps.getSerialSystem())) {
			ps.setSerialSystem("10");
		}
		if ("2".equals(ps.getSerialSystem())) {
			ps.setSerialSystem("16");
		}
		if ("3".equals(ps.getSerialSystem())) {
			ps.setSerialSystem("34");
		}
		ps.setPd(pList.get(0));
		ps.setPname(pList.get(0).getPname());
		System.out.println("产品:"+ps);
		try {
			dao.updateProductSchedulingById(ps);
		} catch (Exception e) {
		    e.printStackTrace();
		    throw new RuntimeException("修改条码规则失败!");
		}
		
		result.setMsg("修改成功!");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult exportTestData(MultipartFile upSN, MultipartFile upMac, HttpServletRequest request) throws Exception {
		CyResult result = new CyResult();
		// 文件夹名字
		String folderName = "upSSS";
		String downLoadUrl1=null;
		String downLoadUrl2=null;
		// 接收上传结果
		Map<String, Object> map = null;
		if (upSN != null&&!upSN.isEmpty()) {
			map = UploadUtil.upload(upSN, request, folderName);
			if (!map.get("msg").toString().equals("保存成功")) {
				result.setState(0);
				result.setMsg(map.get("msg") + ",请重新传送!!");
				return result;
			} else {
				String localAppAdd = map.get("path").toString() + File.separator + map.get("filename").toString();
				System.out.println(localAppAdd);
				List<String> list = new ArrayList<String>();
				FileInputStream fis = new FileInputStream(localAppAdd);
				InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
				BufferedReader br = new BufferedReader(isr);
				String line = "";
				while ((line = br.readLine()) != null) {
					list.add(line);
				}
				br.close();
				isr.close();
				fis.close();
				List<TvProduce> tvList=new ArrayList<TvProduce>();
 				for(String sn:list){
					if("".equals(sn.trim())){
						continue;
					}
					TvProduce tv=new TvProduce();
					tv.setTv_sn_code(sn);
					TvProduce tvOne=produceTvDao.findTvBySn(tv);
					tvList.add(tvOne);	
				}
 				downLoadUrl1=CreateSimpleExcelToDisk.createFunctionExcel(tvList);
 				
			}
         }
		
		if (upMac != null&&!upMac.isEmpty()) {
			map = UploadUtil.upload(upMac, request, folderName);
			if (!map.get("msg").toString().equals("保存成功")) {
				result.setState(0);
				result.setMsg(map.get("msg") + ",请重新传送!!");
				return result;
			} else {
				String localAppAdd = map.get("path").toString() + File.separator + map.get("filename").toString();
				System.out.println(localAppAdd);
				List<String> list = new ArrayList<String>();
				FileInputStream fis = new FileInputStream(localAppAdd);
				InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
				BufferedReader br = new BufferedReader(isr);
				String line = "";
				while ((line = br.readLine()) != null) {
					list.add(line);
				}
				br.close();
				isr.close();
				fis.close();
				List<TvProduce> tvList=new ArrayList<TvProduce>();
 				for(String mac:list){
					if("".equals(mac.trim())){
						continue;
					}
					TvProduce tv=new TvProduce();
					tv.setMac(mac);
					TvProduce tvOne=produceTvDao.findTvByMac(tv);
					tvList.add(tvOne);
					
				}
 				System.err.println(tvList);
 				downLoadUrl2=CreateSimpleExcelToDisk.createFunctionExcel(tvList);
			}
        }
		if(downLoadUrl1!=null){
		   result.setData1(downLoadUrl1);
		}
		if(downLoadUrl2!=null){
			result.setData2(downLoadUrl2);
		}
		result.setState(1);
		result.setMsg("导出成功!");
		return result;
		}

	@Override
	public CyResult xlsAddProductCode(MultipartFile productFile, ProductCode productCode, HttpServletRequest request) throws Exception {
		CyResult result = new CyResult();
		// 文件夹名字
		String folderName = "mac";
		// 接收上传结果
		Map<String, Object> map = null;
		System.out.println("productCode:"+productCode);
		if (productFile != null&&!productFile.isEmpty()) {
			map = UploadUtil.upload(productFile, request, folderName);
			if (!map.get("msg").toString().equals("保存成功")) {
				result.setState(0);
				result.setMsg(map.get("msg") + ",请重新传送!!");
				return result;
			} else {
				String localAppAdd = map.get("path").toString() + File.separator + map.get("filename").toString();
				List<Map<String, String>> list = null;
		       //String colums[]={"SN","GD","RJMac","STBID","WIFIMac","YSTSN","VN","TN","YPRO","PN"};
		       list=CodeImportByExcel.ImportExcelUtils(localAppAdd);
		       List<ProductCode> productCodeList=new ArrayList<ProductCode>();
		       for (Map<String,String> mapone : list) {
		    	   ProductCode code=new ProductCode();
		    	   SnCodeDomain snCode = new SnCodeDomain();
		           BeanUtils.populate(snCode, mapone);
		           code.setSn1(snCode.getSN号().replace(" ", ""));
		           code.setSn2(snCode.get广电号().replace(" ", ""));
		           code.setSn3(snCode.getRJMac地址().replace(" ", ""));
		           code.setSn4(snCode.getSTBID().replace(" ", ""));
		           code.setSn5(snCode.getWIFIMac地址().replace(" ", ""));
		           code.setSn7(snCode.getYSTSN号().replace(" ", ""));
		           code.setData1(snCode.getVN().replace(" ", ""));
		           code.setData2(snCode.getTN().replace(" ", ""));
		           code.setBatch(snCode.getPN().replace(" ", ""));
		           System.out.println("--->"+productCode.getModelCode());
		           if(productCode.getModelCode()==null||"".equals(productCode.getModelCode())){
		        	   code.setModelCode(snCode.getYPRO().replace(" ", ""));
		           }else {
		        	   code.setModelCode(productCode.getModelCode());
		           }
		           //code.setModelCode(snCode.getYPRO().replace("'", ""));
		           code.setColor(productCode.getColor());
		           code.setPcode(productCode.getPcode().trim());
		           code.setPnCode(productCode.getPnCode().trim());
		           code.setProcess(productCode.getProcess());
		           code.setPorder(productCode.getPorder());
		           code.setCreatime(CyUtil.getTime());
		          // code.setBatch(productCode.getBatch());
		           code.setCid(productCode.getCid());
		           code.setDeliverTime(productCode.getDeliverTime());
		           code.setHardwareVersion(productCode.getHardwareVersion().trim());
		           code.setSoftwareVersion(productCode.getSoftwareVersion().trim());
		           code.setLicenseTag(productCode.getLicenseTag().trim());
		           code.setMainboardModel(productCode.getMainboardModel().trim());
		           code.setProjectNumber(productCode.getProjectNumber().trim());
		           code.setRegion(productCode.getRegion().trim());
		           if(code.getSn1()!=null&&code.getSn1()!=""){
		   		   List<ProductCode> codeList=dao.findBarcodeBySn1(code.getSn1());
		   		   System.out.println("aaa:"+codeList);
		   			if(codeList.size()>0){
		   				continue;
		   			}
		   		   }
		           productCodeList.add(code);
		       }
		       if(productCodeList.size()<1){
		    	     result.setState(2);
					 result.setMsg("该Excel表中的SN已完全存在于数据库中，请确认!");
					 return result;
		       }
			    try {

			    	for(ProductCode code:productCodeList){
			    		System.err.println("code："+code);
			    	}
			    	 int n = dao.xlsAddProductCode(productCodeList);
			    	 result.setState(1);
					 result.setMsg("录入成功!");
					 return result;
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("批量录入失败!");
				}
			}
		}

		result.setState(2);
		result.setMsg("录入失败!");
		return result;
	}

	@Override
	public CyResult searchProductScheduling(Integer cpid) {
		CyResult result = new CyResult();
		List<ProductScheduling> list = dao.searchProductScheduling(cpid);
		if (list == null || list.size() < 1) {
			result.setMsg("无条码规则!");
			result.setState(0);
			return result;
		}
		result.setMsg("加载条码规则成功");
		result.setState(1);
		result.setData(list);
		return result;
	}

	@Override
	public CyResult distinctProduct(String str) {
		CyResult result = new CyResult();
		if("modelCode".equals(str)){
			List<String> dm = dao.distinctModel();
			result.setData(dm);
			result.setState(0);
			result.setMsg("SUCCESSFUL");
			return result;
		}
		if("color".equals(str)){
			List<String> dc = dao.distinctColor();
			result.setData(dc);
			result.setState(0);
			result.setMsg("SUCCESSFUL");
			return result;
		}
		if("porder".equals(str)){
			List<String> dp = dao.distinctPorder();
			result.setData(dp);
			result.setState(0);
			result.setMsg("SUCCESSFUL");
			return result;
		}
		if("pn".equals(str)){
			List<String> dp = dao.distinctPn();
			result.setData(dp);
			result.setState(0);
			result.setMsg("SUCCESSFUL");
			return result;
		}
	   return result;
	}

	@Override
	public CyResult deleteProductCodes(String pidString) {
		CyResult result=new CyResult();
		String[] pidArr=pidString.split(",");
		List<String> list=Arrays.asList(pidArr);
		try {
			dao.deleteProductCodes(list);
			result.setMsg("删除成功!");
			result.setState(1);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("删除失败");
		}
	}

	@Override
	public CyResult insertOneCode(ProductCode productCode) {
		CyResult result = new CyResult();
		if(productCode.getSn1()!=null&&productCode.getSn1()!=""){
			List<ProductCode> codeList=dao.findBarcodeBySn1(productCode.getSn1());
			System.err.println(codeList.get(0).getModelCode());
			System.out.println("aaa:"+codeList);
			if(codeList.size()>0){
				result.setMsg("SN重复!");
				result.setState(1);
				return result;
			}
		}
			productCode.setCreatime(CyUtil.getTime2()); 
			try {
				dao.insertOneCode(productCode);
				result.setMsg("succssful");
				result.setState(0);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("faield");
			}
			 
		
	}

	@Override
	public CyResult updateOneProductCode(ProductCode productCode) {
		CyResult result = new CyResult();
		
		try {
			dao.updateOneCode(productCode);
			result.setMsg("successful");
			result.setState(0);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("error");
		}

	}

	@Override
	public CyResult distinctData() {
		CyResult result = new CyResult();
		List<String> scpc = dao.distinctScpc();
		List<String> jhdh = dao.distinctJhdh();
		List<String> pzf = dao.distinctPzf();
		List<String> chdq = dao.distinctChdq();
		List<String> pnCodeList=dao.distinctPncode();
		List<String> str = new ArrayList<>();
		str.add(null);
		str.add("null");
		scpc.removeAll(str);
		jhdh.removeAll(str);
		pzf.removeAll(str);
		chdq.removeAll(str);
		result.setData(scpc);
		result.setData1(jhdh);
		result.setData2(pzf);
		result.setData3(chdq);
		result.setData4(pnCodeList);
		result.setMsg("succssful");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult exportCode(ProductCode productCode) throws Exception {
		CyResult result = new CyResult();
		String boxnumString=productCode.getBoxnumString();
		List<String> boxNumsArr=new ArrayList<String>();
		if(boxnumString!=null&&boxnumString!=""){
			String[] boxArr=boxnumString.split(",");
			for(int i=0;i<boxArr.length;i++){
				boxNumsArr.add(boxArr[i].trim());
			}
		}
	    productCode.setBoxNumsArr(boxNumsArr);
		if(productCode.getDeliver()!=null && !"".equals(productCode.getDeliver())){
			String[] timeStr = productCode.getDeliver().split("~");
			productCode.setBeginTime(timeStr[0].trim());
			productCode.setEndTime(timeStr[1].trim());
		}
		System.out.println(productCode);
		List<ProductCode> pcList = dao.exportCode(productCode);
		String downloadUrl = CreateSimpleExcelToCode.createExcel(pcList);
		System.out.println("d:"+downloadUrl);
		result.setMsg("文件导出成功");
		result.setData(downloadUrl);
		result.setState(0);
		return result;
	}

	@Override
	public CyResult addModel(PrintModel model) {
		CyResult result=new CyResult();
		System.out.println("model:"+model);
		try {
			dao.addModel(model);
			result.setCode(0);
			result.setMsg("success!");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("增加模板失败!");
		}
	
	}

	@Override
	public CyResult loadAllMode(PrintModel model) {
		CyResult result=new CyResult();
		model.setPageSize((model.getPage()-1)*model.getLimit());
		List<PrintModel> list=dao.loadModel(model);
		System.out.println(list);
		int count=dao.loadModelCount(model);
        result.setData(list);
        result.setCount(count);
        result.setMsg("success!");
		result.setCode(0);
        return result;
	}

	@Override
	public CyResult deleteModel(int templateId) {
		CyResult result=new CyResult();
		try {
			int n=dao.deleteModel(templateId);
			result.setCode(0);
			result.setMsg("success!");
			return result;
		} catch (Exception e) {
		  e.printStackTrace();
		  throw new RuntimeException("error!");
		}
	}

	@Override
	public CyResult boxNumsAdd(MultipartFile file, HttpServletRequest request,Boxnum boxOne) throws Exception {
		
		CyResult result = new CyResult();
		// 文件夹名字
		String folderName = "boxnum";
		// 接收上传结果
		Map<String, Object> map = null;
		if (file != null&&!file.isEmpty()) {
			map = UploadUtil.upload(file, request, folderName);
			if (!map.get("msg").toString().equals("保存成功")) {
				result.setState(0);
				result.setMsg(map.get("msg") + ",请重新传送!!");
				return result;
			} else {
				String localAppAdd = map.get("path").toString() + File.separator + map.get("filename").toString();
				System.out.println("路径:"+localAppAdd);
				List<String> list = new ArrayList<String>();
				FileInputStream fis = new FileInputStream(localAppAdd);
				InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
				BufferedReader br = new BufferedReader(isr);
				String line = "";
				while ((line = br.readLine()) != null) {
					list.add(line);
				}
				br.close();
				isr.close();
				fis.close();
			    List<Boxnum> boxList=new ArrayList<Boxnum>();
			    for(String boxnum:list){
			    	Boxnum box=new Boxnum();
			    	box.setModelCode(boxOne.getModelCode());
			    	box.setPnCode(boxOne.getPnCode());
			    	box.setBoxNum(boxnum);
			    	box.setCreatime(CyUtil.getTime2());
			    	box.setPorder(boxOne.getPorder());
			    	Boxnum demo=dao.loadBoxByBoxnum(boxnum.trim());
			    	if(demo!=null){
			    		continue;
			    	}
			    	boxList.add(box);
			    	System.out.println("sss："+boxList.size());
			    }
			    if(boxList.size()<1){
			    	result.setState(1);
			        result.setMsg("该文件中的内容已完全存在,请确认!");
			        return result;
			    }
			    try {
			    	dao.addBoxnums(boxList);
			    	result.setState(0);
			        result.setMsg("Succeed");
			        return result;
				
				} catch (Exception e) {
					 e.printStackTrace();
				     throw new RuntimeException("录入失败!");
				}
			    
			}
		}
		return result;
	}

	@Override
	public CyResult loadAllBox(Boxnum boxnum) {
		CyResult result=new CyResult();
		boxnum.setPageSize((boxnum.getPage()-1)*boxnum.getLimit());
		List<Boxnum> list=dao.loadAllBox(boxnum);
		int count=dao.loadAllCount(boxnum);
		System.err.println(list.get(0).getBoxState());
		result.setData(list);
		result.setCode(0);
		result.setCount(count);
		return result;
	}

	@Override
	public CyResult deleteBoxnum(int bid) {
		CyResult result=new CyResult();
		try {
			dao.deleteBoxnum(bid);
			result.setCode(0);
			result.setState(0);
			result.setMsg("success!");
			return result;
		} catch (Exception e) {
		  e.printStackTrace();
		  throw new RuntimeException("faild!");
		}
		
	}

	@Override
	public CyResult editModel(PrintModel model) {
		CyResult result=new CyResult();
		try {
			dao.editModel(model);
			result.setCode(0);
			result.setState(0);
			result.setMsg("success!");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			  throw new RuntimeException("faild!");
		}		
	}

	@Override
	public CyResult deleteMoreBoxnum(String idstring) {
		CyResult result=new CyResult();
		String arr[]=idstring.split(",");
		try {
			dao.deleteMoreBoxnum(arr);
			result.setCode(0);
			result.setMsg("success!");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("failed");
		}
	}

	@Override
	public CyResult bomMaterials(MultipartFile BOMfile,
			BomMaterials bomMaterials, HttpServletRequest request)
			throws Exception {
		CyResult result = new CyResult();
		// 文件夹名字
		String folderName = "mac";
		// 接收上传结果
		Map<String, Object> map = null;
		System.out.println("bid="+bomMaterials.getBid());
		System.out.println("bomMaterials:"+bomMaterials);
		if (BOMfile != null&&!BOMfile.isEmpty()) {
			map = UploadUtil.upload(BOMfile, request, folderName);
			if (!map.get("msg").toString().equals("保存成功")) {
				result.setState(0); 
				result.setMsg(map.get("msg") + ",请重新传送!!");
				return result;
			} else {
				String localAppAdd = map.get("path").toString() + File.separator + map.get("filename").toString();
				List<Map<String, String>> list = null;
		       String colums[]={"PN","useNumer","bitNumber"};
		       list=ImportExcel.ImportExcelUtils(localAppAdd, colums);
		       List<BomMaterials> bomMaterialslist=new ArrayList<BomMaterials>();
		       for (Map<String,String> mapone : list) {
		    	   BomMaterials code=new BomMaterials();
		    	   BomCode bomCode = new BomCode();
		           BeanUtils.populate(bomCode, mapone);
		           code.setPn(bomCode.getPN());
		           Map<String, Object> sp = new HashMap<>();
		           sp.put("pn", bomCode.getPN());
		           System.out.println("pn-->"+sp);
		           List<MaterialManage> mateList = tvdao.loadsearchmm(sp);
		           if(mateList.size()==0){
		        	   result.setState(2);
		        	   result.setMsg("料号为："+sp+"不存在");
		        	   return result;
		           }
		           System.out.println("mateList"+mateList);
		           System.out.println(mateList.get(0).getId());
		           code.setMid(mateList.get(0).getId());
		           code.setUseNumer(bomCode.getUseNumer());
		           code.setBitNumber(bomCode.getBitNumber());
		           code.setBid(bomMaterials.getBid());
		           bomMaterialslist.add(code);
		           System.out.println("bomMaterialslist-->"+bomMaterialslist);
		       }
	
			    try {

			    	for(BomMaterials code:bomMaterialslist){
			    		System.err.println("code："+code);
			    	}
			    	 int n = dao.xlsAddbomMaterials(bomMaterialslist);
			    	 result.setState(1);
					 result.setMsg("录入成功!");
					 return result;
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("批量录入失败!");
				}
			}
		}

		result.setState(2);
		result.setMsg("录入失败!");
		return result;
	}

	@Override
	public CyResult addPhenomenon(String blPhenom) {
		CyResult result = new CyResult();
		System.out.println("blPhenom:"+blPhenom);
		if(blPhenom==null||blPhenom==""){
			result.setState(2);
			result.setMsg("error！");
			return result;
			}
			try {
				dao.addPhenomenon(blPhenom);
				result.setState(0);
				result.setMsg("添加成功！");
				return result;
			} catch (Exception e) {
			throw new RuntimeException("error!");
			}
	}

	@Override
	public CyResult loadBlPhenom(Repair repair, Integer limit,Integer page) {
		CyResult result=new CyResult();
		if(page==null||"".equals(page)){
			repair.setPage(1);
		}
		if(limit==null||"".equals(limit)){
			repair.setLimit(10);
		}
		int pageSize = (repair.getPage()-1)*repair.getLimit();
		repair.setPageSize(pageSize);
		System.out.println("repair-->"+repair);
		List<Repair> list=dao.loadBlPhenom(repair);
		int count=dao.countLoadBlPhenom(repair);
		result.setCount(count);
		result.setState(0);
		result.setData(list);
		return result;
	}

	@Override
	public CyResult deleteBlPhenom(Integer xId) {
		CyResult result=new CyResult();
		try {
			dao.deleteBlPhenom(xId);
			result.setCode(0);
			result.setState(0);
			result.setMsg("success!");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("failed");
		}
	}

	@Override
	public CyResult loadHealthyy(Repair repair) {
		CyResult result=new CyResult();
		if(repair.getPage()==null){
			repair.setPage(1);
			repair.setLimit(10);
		}
		int pageSize = (repair.getPage()-1)*repair.getLimit();
		repair.setPageSize(pageSize);
		System.out.println("repair-->"+repair);
		List<Repair> list=dao.loadHealthyy(repair);
		int count=dao.countLoadHealthyy(repair);
		result.setCount(count);
		result.setState(0);
		result.setData(list);
		return result;
	}

	@Override
	public CyResult addHealthyy(String unhealthyy) {
		CyResult result = new CyResult();
		System.out.println("blPhenom:"+unhealthyy);
		if(unhealthyy==null||unhealthyy==""){
			result.setState(2);
			result.setMsg("error！");
			return result;
			}
			try {
				dao.addHealthyy(unhealthyy);
				result.setState(0);
				result.setMsg("添加成功！");
				return result;
			} catch (Exception e) {
			throw new RuntimeException("error!");
			}
	}

	@Override
	public CyResult deleteHealthyy(Integer brid) {
		CyResult result=new CyResult();
		try {
			dao.deleteHealthyy(brid);
			result.setCode(0);
			result.setState(0);
			result.setMsg("success!");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("failed");
		}
	}

	@Override
	public CyResult loadProductModel(ProductModel productModel) {
		CyResult result=new CyResult();
		if(productModel.getPage()==null){
			productModel.setPage(1);
		}
		int pageSize = (productModel.getPage()-1)*productModel.getLimit();
		productModel.setPageSize(pageSize);
		System.out.println("productModel-->"+productModel);
		List<ProductModel> list=dao.loadProductModel(productModel);
		int count=dao.countLoadProductModel(productModel);
		result.setCount(count);
		result.setState(0);
		result.setData(list);
		return result;
	}

	@Override
	public CyResult addProductModel(ProductModel productModel) {
		CyResult result = new CyResult();
		if(productModel.getPname()=="--请选择产品名--"||"--请选择产品名--".equals(productModel.getPname())){
			productModel.setPname("");
		}
		System.out.println("ProductModel:"+productModel);
			try {
				dao.addProductModel(productModel);
				result.setState(0);
				result.setMsg("添加成功！");
				return result;
			} catch (Exception e) {
			throw new RuntimeException("error!");
			}
	}

	@Override
	public CyResult deleteProductModel(Integer id) {
		CyResult result=new CyResult();
		try {
			dao.deleteProductModel(id);
			result.setCode(0);
			result.setState(0);
			result.setMsg("success!");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("failed");
		}
	}

	@Override
	public CyResult updateProductModel(ProductModel productModel) {
		CyResult result = new CyResult();
		System.out.println("ProductModel:"+productModel);
		if(productModel.getPname()=="--请选择产品名--"||"--请选择产品名--".equals(productModel.getPname())){
			productModel.setPname("");
		}
				int n=dao.updateProductModel(productModel);
				if(n==1){
				result.setState(0);
				result.setMsg("添加成功！");
				return result;
			}else{
				result.setState(2);
				result.setMsg("编辑失败");
				return result;
			}
	}

	@Override
	public CyResult loadProductModelByPid(ProductModel productModel) {
		CyResult result=new CyResult();
		/*if(productModel.getPage()==null){
			productModel.setPage(1);
		}
		int pageSize = (productModel.getPage()-1)*productModel.getLimit();
		productModel.setPageSize(pageSize);*/
		System.out.println("productModel-->"+productModel);
		List<ProductModel> list=dao.loadProductModelByPid(productModel);
		//int count=dao.countLoadProductModel(productModel);
		//result.setCount(count);
		result.setState(0);
		result.setData(list);
		return result;
	
	}

	@Override
	public CyResult addRepair(Repair repair) {
		CyResult result=new CyResult();
		if(repair==null||repair.getSn()==null||repair.getSn().equals("")){
			result.setState(2);
			result.setMsg("请输入SN号");
			return result;
		}
		//String model=dao.findPcodeBySN(repair.getSn());//通过sn在条码表查询型号
		try {
			repair.setWxTime(CyUtil.getTime());
			
			dao.addRepair(repair);
			result.setState(0);
			result.setMsg("添加成功");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("failed！");
		}
	}

	@Override
	public CyResult deleteRepair(Integer rid) {
		CyResult result=new CyResult();
		try {
			dao.deleteRepair(rid);
			result.setCode(0);
			result.setState(0);
			result.setMsg("success!");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("failed");
		}
	}

	@Override
	public CyResult updateRepair(Repair repair) {
		CyResult result=new CyResult();
		if(repair==null||repair.getSn()==null||repair.getSn().equals("")){
			result.setState(2);
			result.setMsg("请输入SN号");
			return result;
		}
		//String model=dao.findPcodeBySN(repair.getSn());//通过sn在条码表查询型号
		try {
			repair.setWxTime(CyUtil.getTime());
			
			dao.updateRepair(repair);
			result.setState(0);
			result.setMsg("编辑成功");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("failed！");
		}
	}
}
