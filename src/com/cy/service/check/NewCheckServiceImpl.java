package com.cy.service.check;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cy.dao.check.CheckDao;
import com.cy.dao.check.NewCheckDao;
import com.cy.dao.line.ProductWorkerDao;
import com.cy.dao.line.WorkLineDao;
import com.cy.domain.check.NewCheck;
import com.cy.domain.check.WorkTime;
import com.cy.domain.line.CheckStatus;
import com.cy.domain.line.ProductDetails;
import com.cy.domain.line.ProductWorker;
import com.cy.domain.line.WorkLine;
import com.cy.domain.line.WorkLocation;
import com.cy.utils.CyResult;
import com.cy.utils.CyUtil;
import com.google.gson.Gson;

@Service
public class NewCheckServiceImpl implements NewCheckService {
    
	@Resource
	private NewCheckDao newDao;
	@Resource
	private WorkLineDao lineDao;
	@Resource
	private CheckDao checkDao;
	@Resource
	private ProductWorkerDao workerDao;
	
	@Override
	public CyResult checkDown(ProductWorker worker, WorkLine line, Integer status) throws ParseException {
		System.out.println("---------------------------------");
		CyResult result=new CyResult();
		if(line.getLineCode()==null||line.getLineCode().trim().isEmpty()||worker.getWorkerCode()==null||
				worker.getWorkerCode().trim().isEmpty()){
			result.setMsg("产线编码或人员不能为空!");
			result.setState(7);
			return result;
		}
		List<WorkLine> wrl=lineDao.findLineByCode(line);
		System.out.println(wrl);
		if(wrl==null||wrl.size()<1){
			result.setMsg("未能查到相关产线，请确认！");
			result.setState(30);
			return result;
		}
		ProductDetails pd=lineDao.findProductByLine(line);//查询出产线对应的产品
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("lineCode", line.getLineCode());
		map.put("workerCode", worker.getWorkerCode());
		map.put("pid", pd.getPid());
	
		List<WorkLine> linelist=lineDao.findLineByCodeAndWorker(map);//通过员工工号和产线编码反查生产线
		List<WorkLocation> list=checkDao.findWorkerByCode(line);//查询出当前产线下所有的工位和人员信息
		//System.out.println(list);
		
		List<ProductWorker> workerList=workerDao.findWorker(worker);
		if(workerList!=null&&workerList.size()>0){
		   worker=workerList.get(0);
		}
		DecimalFormat df = new DecimalFormat("#.00");
		
		
		
		Gson gs=new Gson();
		String nowDate=CyUtil.getTime2();//获取当前系统的时间(年月日)
		map.put("nowDate",nowDate);
		if(linelist==null||linelist.size()<1){//如果通过员工工号和产线编码反查生产线  没有得到结果
			result.setMsg("该产线未能查到相关人员,请确认!");
			List<WorkLocation> newList=new ArrayList<WorkLocation>();
			list=checkDao.findWorkerByCode(line);
			for(WorkLocation w:list){
				if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
					newList.add(w);
				}
			}

			List<WorkLocation> wwlist=newDao.findCheckByLineCode(map);//计算出当日当前产线当前产品的所有的打卡记录
		    
			for(WorkLocation lt:wwlist){
				System.out.println(lt);
		    	Double timeSum=0.0;
		    	if(lt.getWorker()!=null){
		    		List<NewCheck> chlist=lt.getWorker().getNewCheckList();
		    		//System.out.println(chlist);
		    		for(NewCheck cst:chlist){
		    			if(cst.getUpLine()!=null&&cst.getDownLine()==null){
		    				Long time=CyUtil.parseTime(CyUtil.getTime1(), cst.getUpLine());
		    				Double dtime=(double) (time/1000.0/60/60);
		    				System.out.println(dtime);
		    				cst.setNowTm(dtime);
		    			}else if(cst.getUpLine()!=null&&cst.getDownLine()!=null){
		    				Long time=CyUtil.parseTime(cst.getDownLine(), cst.getUpLine());
		    				Double dtime=(double) (time/1000.0/60/60);
		    				System.out.println(dtime);
		    				cst.setNowTm(dtime);
		    			}
		    			System.out.println(cst.getNowTm());
		    			timeSum+=cst.getNowTm();
		    		}
		    		System.out.println("**********************");
		    	
		    		lt.getWorker().setObjThing(df.format(timeSum));
		    		System.out.println(timeSum);
		    		System.out.println(timeSum.toString().substring(0, 1).equals("0"));
		    		if(timeSum.toString().substring(0, 1).equals("0")){
		    			lt.getWorker().setObjThing("0"+df.format(timeSum));
		    		}
		    		
		    	}
		    }
			
			for(WorkLocation location:newList){
				for(WorkLocation lt:wwlist){
					if(lt.getWorker().getWid().equals(location.getWorker().getWid())){
						
						location.getWorker().setObjThing(lt.getWorker().getObjThing());
					}
				}
			}
			for(WorkLocation location:newList){
				if(location.getWorker()!=null&&location.getWorker().getObjThing()==null){
					location.getWorker().setObjThing("");
				}
			}
			result.setState(0);
			if(newList.size()>20){
				result.setData(gs.toJson(newList.subList(0, 20)));
				result.setData1(gs.toJson(newList.subList(20,newList.size())));
			}else{
				result.setData(gs.toJson(newList));
			}
			List<WorkLocation> locationList=new ArrayList<WorkLocation>();
			
			for (WorkLocation wl:newList) {
				if (wl.getLocationState()!=null && wl.getLocationState()==1) {
					locationList.add(wl);
				}
			}
			result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
			result.setData2(worker.getWorkerName());
			return result;
		}
		
		
		
		//正式打卡
		NewCheck model=new NewCheck();
		
		model.setWorker(worker);
		model.setNowDate(CyUtil.getTime2());
		List<ProductDetails> pdList=lineDao.findProductById(pd);
	    model.setProduct(pdList.get(0));
	    model.setLine(line);
	    model.setScstate(wrl.get(0).getScstate());
		List<NewCheck> wcList=newDao.findNewCheckByWorkerCode(model); //查询出该人当日当前产线下的打卡信息
		//System.out.println(wcList);
		
		
		
	
		//上线,打上线卡失败是因为这条该员工还没有下线,成功即是增加一条打卡记录
		if(status!=null&&status==1){
			Map<String,Object> cmap=new HashMap<String,Object>();
			/*
			 *如果该人当日当前产线下的打卡信息为空，则新增一条记录
			 */
			model.setUpLine(CyUtil.getTime1());
			
			if(wcList==null||wcList.size()<1){
				int n=newDao.addNewCheck(model);
				if(n!=1){
					throw new RuntimeException("打卡失败");
				}
				for(WorkLocation w:list){
					//System.out.println(worker.getWorkerCode());
					
					if(w.getWorker()!=null&&worker.getWorkerCode().equals(w.getWorker().getWorkerCode())){
						cmap.put("locationState",1);
						cmap.put("sid", w.getSid());
						cmap.put("nowTime",CyUtil.getTime1());
						System.out.println("你的眼睛:"+w.getWorker());
						try {
							int m=lineDao.updateLocation(cmap);
							w.getWorker().setStateTwo(1);
							int k=workerDao.updateWorker(w.getWorker());

						} catch (Exception e) {
							throw new RuntimeException("打卡失败");
						}
						
					}
				}
				
				
				list=checkDao.findWorkerByCode(line);
				List<WorkLocation> newList=new ArrayList<WorkLocation>();
				for(WorkLocation w:list){
					if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
						newList.add(w);
					}
				}
				
				List<WorkLocation> wwlist=newDao.findCheckByLineCode(map);//计算出当日当前产线当前产品的所有的打卡记录
				
				for(WorkLocation lt:wwlist){
					//System.out.println(lt);
			    	Double timeSum=0.0;
			    	if(lt.getWorker()!=null){
			    		List<NewCheck> chlist=lt.getWorker().getNewCheckList();
			    		
			    		for(NewCheck cst:chlist){
			    			
			    			if(cst.getUpLine()!=null&&cst.getDownLine()==null){
			    				Long time=CyUtil.parseTime(CyUtil.getTime1(), cst.getUpLine());
			    				Double dtime=(double) (time/1000.0/60/60);
			    				cst.setNowTm(dtime);
			    			}else if(cst.getUpLine()!=null&&cst.getDownLine()!=null){
			    				Long time=CyUtil.parseTime(cst.getDownLine(), cst.getUpLine());
			    				Double dtime=(double) (time/1000.0/60/60);
			    				cst.setNowTm(dtime);
			    			}
			    			System.out.println(cst.getNowTm());
			    			timeSum+=cst.getNowTm();
			    		}
			    		lt.getWorker().setObjThing(df.format(timeSum));
			    		if(timeSum.toString().substring(0, 1).equals("0")){
			    			lt.getWorker().setObjThing("0"+df.format(timeSum));
			    		}
			    	}
			    }
				
				for(WorkLocation location:newList){
					for(WorkLocation lt:wwlist){
						
						if(lt.getWorker().getWid().equals(location.getWorker().getWid())){
							
							location.getWorker().setObjThing(lt.getWorker().getObjThing());
						}
					}
				}
				for(WorkLocation location:newList){
					if(location.getWorker()!=null&&location.getWorker().getObjThing()==null){
						location.getWorker().setObjThing("");
					}
				}
				
				if(newList.size()>20){
					result.setData(gs.toJson(newList.subList(0, 20)));
					result.setData1(gs.toJson(newList.subList(20,newList.size())));
				}else{
					result.setData(gs.toJson(newList));
				}
				List<WorkLocation> locationList=new ArrayList<WorkLocation>();
				for (WorkLocation wl:newList) {
					if (wl.getLocationState()!=null && wl.getLocationState()==1) {
						locationList.add(wl);
					}
				}
				result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
				result.setData2(worker.getWorkerName());
				result.setMsg("打卡成功!");
				result.setState(1);
				return result;
			}
			for(NewCheck wc:wcList){
				//如果有一条记录的下线时间为空,则需要提示请先下线
				if(wc.getUpLine()!=null&&wc.getDownLine()==null){
					list=checkDao.findWorkerByCode(line);
					List<WorkLocation> newList=new ArrayList<WorkLocation>();
					for(WorkLocation w:list){
						if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
							newList.add(w);
						}
					}
					
					List<WorkLocation> wwlist=newDao.findCheckByLineCode(map);//计算出当日当前产线当前产品的所有的打卡记录
				  
					for(WorkLocation lt:wwlist){
						
				    	Double timeSum=0.0;
				    	if(lt.getWorker()!=null){
				    		List<NewCheck> chlist=lt.getWorker().getNewCheckList();
				    		
				    		for(NewCheck cst:chlist){
				    			
				    			if(cst.getUpLine()!=null&&cst.getDownLine()==null){
				    				Long time=CyUtil.parseTime(CyUtil.getTime1(), cst.getUpLine());
				    				Double dtime=(double) (time/1000.0/60/60);
				    				cst.setNowTm(dtime);
				    			}else if(cst.getUpLine()!=null&&cst.getDownLine()!=null){
				    				Long time=CyUtil.parseTime(cst.getDownLine(), cst.getUpLine());
				    				Double dtime=(double) (time/1000.0/60/60);
				    				cst.setNowTm(dtime);
				    			}
				    			//System.out.println(cst.getNowTm());
				    			timeSum+=cst.getNowTm();
				    		}
				    		lt.getWorker().setObjThing(df.format(timeSum));
				    		if(timeSum.toString().substring(0, 1).equals("0")){
				    			lt.getWorker().setObjThing("0"+df.format(timeSum));
				    		}
				    	}
				    }
					
					for(WorkLocation location:newList){
						for(WorkLocation lt:wwlist){
						
							if(lt.getWorker().getWid().equals(location.getWorker().getWid())){
								
								location.getWorker().setObjThing(lt.getWorker().getObjThing());
							}
						}
					}
					for(WorkLocation location:newList){
						if(location.getWorker()!=null&&location.getWorker().getObjThing()==null){
							location.getWorker().setObjThing("");
						}
					}
					
					if(newList.size()>20){
						result.setData(gs.toJson(newList.subList(0, 20)));
						result.setData1(gs.toJson(newList.subList(20,newList.size())));
					}else{
						result.setData(gs.toJson(newList));
					}
					List<WorkLocation> locationList=new ArrayList<WorkLocation>();
					for (WorkLocation wl:newList) {
						if (wl.getLocationState()!=null && wl.getLocationState()==1) {
							locationList.add(wl);
						}
					}
					result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
					result.setData2(worker.getWorkerName());
					result.setMsg("请先下线!");
					result.setState(0);
					return result;
				}
			}
			
			int n=newDao.addNewCheck(model);//当前所有的 打卡记录均是成对存在的,需增加一条记录
			if(n!=1){
				throw new RuntimeException("打卡失败");
			}
			for(WorkLocation w:list){
				System.out.println(worker.getWorkerCode());
				System.out.println();
				if(w.getWorker()!=null&&worker.getWorkerCode().equals(w.getWorker().getWorkerCode())){
					cmap.put("locationState",1);
					cmap.put("sid", w.getSid());
					cmap.put("nowTime",CyUtil.getTime1());
					
					
					try {
						int m=lineDao.updateLocation(cmap);
						w.getWorker().setStateTwo(1);
						int k=workerDao.updateWorker(w.getWorker());
					} catch (Exception e) {
						throw new RuntimeException("打卡失败");
					}
				
					//System.out.println(w.getLocationCode());
				}
			}
			list=checkDao.findWorkerByCode(line);
			System.out.println("-------------------------------------------------------------------");
			List<WorkLocation> newList=new ArrayList<WorkLocation>();
			for(WorkLocation w:list){
				if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
					newList.add(w);
				}
			}
			
			List<WorkLocation> wwlist=newDao.findCheckByLineCode(map);//计算出当日当前产线当前产品的所有的打卡记录
			//System.out.println("fsdfkjsdfksdf..getClass().getName()...getClass().getName()..");
			//System.out.println("打卡记录:"+wwlist);
			for(WorkLocation lt:wwlist){
				//System.out.println(lt);
		    	Double timeSum=0.0;
		    	if(lt.getWorker()!=null){
		    		List<NewCheck> chlist=lt.getWorker().getNewCheckList();
		    		//System.out.println(chlist);
		    		for(NewCheck cst:chlist){
		    			
		    			if(cst.getUpLine()!=null&&cst.getDownLine()==null){
		    				Long time=CyUtil.parseTime(CyUtil.getTime1(), cst.getUpLine());
		    				Double dtime=(double) (time/1000.0/60/60);
		    				//System.out.println(dtime);
		    				cst.setNowTm(dtime);
		    			}else if(cst.getUpLine()!=null&&cst.getDownLine()!=null){
		    				Long time=CyUtil.parseTime(cst.getDownLine(), cst.getUpLine());
		    				Double dtime=(double) (time/1000.0/60/60);
		    				//System.out.println(dtime);
		    				cst.setNowTm(dtime);
		    			}
		    			System.out.println(cst.getNowTm());
		    			timeSum+=cst.getNowTm();
		    		}
		    		lt.getWorker().setObjThing(df.format(timeSum));
		    		if(timeSum.toString().substring(0, 1).equals("0")){
		    			lt.getWorker().setObjThing("0"+df.format(timeSum));
		    		}
		    	}
		    }
			
			for(WorkLocation location:newList){
				for(WorkLocation lt:wwlist){
					
					if(lt.getWorker().getWid().equals(location.getWorker().getWid())){
						
						location.getWorker().setObjThing(lt.getWorker().getObjThing());
					}
				}
			}
			for(WorkLocation location:newList){
				if(location.getWorker()!=null&&location.getWorker().getObjThing()==null){
					location.getWorker().setObjThing("");
				}
			}
			result.setState(0);
			if(newList.size()>20){
				result.setData(gs.toJson(newList.subList(0, 20)));
				result.setData1(gs.toJson(newList.subList(20,newList.size())));
			}else{
				result.setData(gs.toJson(newList));
			}
			List<WorkLocation> locationList=new ArrayList<WorkLocation>();
			for (WorkLocation wl:newList) {
				if (wl.getLocationState()!=null && wl.getLocationState()==1) {
					locationList.add(wl);
				}
			}
			result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
			result.setData2(worker.getWorkerName());
            System.out.println("-----------------------------------------");			
			result.setMsg("打卡成功!");
			result.setState(1);
			return result;
		}
		
        //下线,
        //下线失败，该员工当天还没有打过卡,以及该员工当前已处于下线状态
		//下线成功,有一条记录的下线时间为空
		if(status!=null&&status==0){
			if(wcList==null||wcList.size()<1){
				List<WorkLocation> newList=new ArrayList<WorkLocation>();
				for(WorkLocation w:list){
					if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
						newList.add(w);
					}
				}
				
				List<WorkLocation> wwlist=newDao.findCheckByLineCode(map);//计算出当日当前产线当前产品的所有的打卡记录
			    System.out.println(wwlist);
				for(WorkLocation lt:wwlist){
					System.out.println(lt);
			    	Double timeSum=0.0;
			    	if(lt.getWorker()!=null){
			    		List<NewCheck> chlist=lt.getWorker().getNewCheckList();
			    		//System.out.println(chlist);
			    		for(NewCheck cst:chlist){
			    	
			    			if(cst.getUpLine()!=null&&cst.getDownLine()==null){
			    				Long time=CyUtil.parseTime(CyUtil.getTime1(), cst.getUpLine());
			    				Double dtime=(double) (time/1000.0/60/60);
			    				System.out.println(dtime);
			    				cst.setNowTm(dtime);
			    			}else if(cst.getUpLine()!=null&&cst.getDownLine()!=null){
			    				Long time=CyUtil.parseTime(cst.getDownLine(), cst.getUpLine());
			    				Double dtime=(double) (time/1000.0/60/60);
			    				System.out.println(dtime);
			    				cst.setNowTm(dtime);
			    			}
			    			//System.out.println(cst.getNowTm());
			    			timeSum+=cst.getNowTm();
			    		}
			    		lt.getWorker().setObjThing(df.format(timeSum));
			    		if(timeSum.toString().substring(0, 1).equals("0")){
			    			lt.getWorker().setObjThing("0"+df.format(timeSum));
			    		}
			    	}
			    }
				list=checkDao.findWorkerByCode(line);
				for(WorkLocation location:newList){
					for(WorkLocation lt:wwlist){
						
						if(lt.getWorker().getWid().equals(location.getWorker().getWid())){
							
							location.getWorker().setObjThing(lt.getWorker().getObjThing());
						}
					}
				}
				for(WorkLocation location:newList){
					if(location.getWorker()!=null&&location.getWorker().getObjThing()==null){
						location.getWorker().setObjThing("");
					}
				}
				result.setState(0);
				if(newList.size()>20){
					result.setData(gs.toJson(newList.subList(0, 20)));
					result.setData1(gs.toJson(newList.subList(20,newList.size())));
				}else{
					result.setData(gs.toJson(newList));
				}
				List<WorkLocation> locationList=new ArrayList<WorkLocation>();
				for (WorkLocation wl:newList) {
					if (wl.getLocationState()!=null && wl.getLocationState()==1) {
						locationList.add(wl);
					}
				}
				result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
				result.setData2(worker.getWorkerName());
				result.setMsg("当天当前产品该条产线还未上线,无法下线!");
				result.setState(0);
				return result;
			}
			model.setDownLine(CyUtil.getTime1());
			
			for(NewCheck wc:wcList){
				//如果有一条记录的下线时间为空,则打卡成功!
				model.setCcid(wc.getCcid());
				if(wc.getDownLine()==null){
					
					int n=newDao.updateNewCheck(model);
					if(n!=1){
						throw new RuntimeException("打卡失败");
					}
					Map<String,Object> cmap=new HashMap<String,Object>();
					for(WorkLocation w:list){
						//System.out.println(worker.getWorkerCode());
						//System.out.println();
						if(w.getWorker()!=null&&worker.getWorkerCode().equals(w.getWorker().getWorkerCode())){
							cmap.put("locationState",0);
							cmap.put("sid", w.getSid());
							cmap.put("nowTime",CyUtil.getTime1());
							int m=lineDao.updateLocation(cmap);
							
						}
					}
					
					list=checkDao.findWorkerByCode(line);
					List<WorkLocation> newList=new ArrayList<WorkLocation>();
					for(WorkLocation w:list){
						if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
							newList.add(w);
						}
					}
					
					List<WorkLocation> wwlist=newDao.findCheckByLineCode(map);//计算出当日当前产线当前产品的所有的打卡记录
				    //System.out.println(wwlist);
					for(WorkLocation lt:wwlist){
						//System.out.println(lt);
				    	Double timeSum=0.0;
				    	if(lt.getWorker()!=null){
				    		List<NewCheck> chlist=lt.getWorker().getNewCheckList();
				    		for(NewCheck cst:chlist){
				    			if(cst.getUpLine()!=null&&cst.getDownLine()==null){
				    				Long time=CyUtil.parseTime(CyUtil.getTime1(), cst.getUpLine());
				    				Double dtime=(double) (time/1000.0/60/60);
				    				//System.out.println(dtime);
				    				cst.setNowTm(dtime);
				    			}else if(cst.getUpLine()!=null&&cst.getDownLine()!=null){
				    				Long time=CyUtil.parseTime(cst.getDownLine(), cst.getUpLine());
				    				Double dtime=(double) (time/1000.0/60/60);
				    				//System.out.println(dtime);
				    				cst.setNowTm(dtime);
				    			}
				    			//System.out.println(cst.getNowTm());
				    			timeSum+=cst.getNowTm();
				    		}
				    		lt.getWorker().setObjThing(df.format(timeSum));
				    		if(timeSum.toString().substring(0, 1).equals("0")){
				    			lt.getWorker().setObjThing("0"+df.format(timeSum));
				    		}
				    	}
				    }
					
					for(WorkLocation location:newList){
						for(WorkLocation lt:wwlist){
							
							if(lt.getWorker().getWid().equals(location.getWorker().getWid())){
								
								location.getWorker().setObjThing(lt.getWorker().getObjThing());
							}
						}
					}
					for(WorkLocation location:newList){
						if(location.getWorker()!=null&&location.getWorker().getObjThing()==null){
							location.getWorker().setObjThing("");
						}
					}
					if(newList.size()>20){
						result.setData(gs.toJson(newList.subList(0, 20)));
						result.setData1(gs.toJson(newList.subList(20,newList.size())));
					}else{
						result.setData(gs.toJson(newList));
					}
					List<WorkLocation> locationList=new ArrayList<WorkLocation>();
					for (WorkLocation wl:newList) {
						if (wl.getLocationState()!=null && wl.getLocationState()==1) {
							locationList.add(wl);
						}
					}
					result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
					result.setData2(worker.getWorkerName());
					result.setMsg("打卡成功!");
					result.setState(1);
					return result;
				}
			}
			
			list=checkDao.findWorkerByCode(line);
			List<WorkLocation> newList=new ArrayList<WorkLocation>();
			for(WorkLocation w:list){
				if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
					newList.add(w);
				}
			}
			
			List<WorkLocation> wwlist=newDao.findCheckByLineCode(map);//计算出当日当前产线当前产品的所有的打卡记录
		    //System.out.println(wwlist);
			for(WorkLocation lt:wwlist){
				//System.out.println(lt);
		    	Double timeSum=0.0;
		    	if(lt.getWorker()!=null){
		    		List<NewCheck> chlist=lt.getWorker().getNewCheckList();
		    
		    		for(NewCheck cst:chlist){
		    			//System.out.println(cst.getCcid());
		    			//System.out.println(cst.getUpLine());
		    			//System.out.println(cst.getDownLine());
		    			if(cst.getUpLine()!=null&&cst.getDownLine()==null){
		    				Long time=CyUtil.parseTime(CyUtil.getTime1(), cst.getUpLine());
		    				Double dtime=(double) (time/1000.0/60/60);
		    				//System.out.println(dtime);
		    				cst.setNowTm(dtime);
		    			}else if(cst.getUpLine()!=null&&cst.getDownLine()!=null){
		    				Long time=CyUtil.parseTime(cst.getDownLine(), cst.getUpLine());
		    				Double dtime=(double) (time/1000.0/60/60);
		    				//System.out.println(dtime);
		    				cst.setNowTm(dtime);
		    			}
		    			//System.out.println(cst.getNowTm());
		    			timeSum+=cst.getNowTm();
		    		}
		    		lt.getWorker().setObjThing(df.format(timeSum));
		    		if(timeSum.toString().substring(0, 1).equals("0")){
		    			lt.getWorker().setObjThing("0"+df.format(timeSum));
		    		}
		    	}
		    }
			
			for(WorkLocation location:newList){
				for(WorkLocation lt:wwlist){
					System.out.println(lt.getWorker().getWid().equals(location.getWorker().getWid()));
					if(lt.getWorker().getWid().equals(location.getWorker().getWid())){
						//System.out.println(lt.getWorker().getWorkerName());
						//System.out.println(lt.getWorker().getObjThing());
						location.getWorker().setObjThing(lt.getWorker().getObjThing());
					}
				}
			}
			for(WorkLocation location:newList){
				if(location.getWorker()!=null&&location.getWorker().getObjThing()==null){
					location.getWorker().setObjThing("");
				}
			}
			if(newList.size()>20){
				result.setData(gs.toJson(newList.subList(0, 20)));
				result.setData1(gs.toJson(newList.subList(20,newList.size())));
			}else{
				result.setData(gs.toJson(newList));
			}
			List<WorkLocation> locationList=new ArrayList<WorkLocation>();
			for (WorkLocation wl:newList) {
				if (wl.getLocationState()!=null && wl.getLocationState()==1) {
					locationList.add(wl);
				}
			}
			result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
			result.setData2(worker.getWorkerName());
			result.setMsg("请先上线!");
			result.setState(0);
			return result;
			
		}
		result.setState(3);
        result.setMsg("系统异常！");		
		return result;
	}
    
	
	/*
	 * 更换产品，结算当前产线，当前铲平的工时(non-Javadoc)
	 * @see com.cy.service.check.NewCheckService#upProductByLine(com.cy.domain.line.ProductDetails, com.cy.domain.line.WorkLine)
	 */
	@Override
	public CyResult upProductByLine(ProductDetails pd,WorkLine line) {
		//System.out.println(line);
		//System.out.println(pd.getPid());
		CyResult result=new CyResult();
		if(pd.getPid()==null){
			result.setState(0);
			result.setMsg("产品不能为空!");
			return result;
		}
		List<WorkLine> lineList=lineDao.findLineByCode(line);
		if(lineList==null||lineList.size()<1){
			result.setMsg("该产线不存在!");
			result.setState(4);
			return result;
		}
		  List<ProductDetails> list=lineDao.findProductById(pd);//查询现在的产品
		  if(list==null||list.size()<1){
				result.setMsg("未能找到相关产品!");
				result.setState(2);
				return result;
		  }
		  ProductDetails pde=lineDao.findProductByLine(line);//查询原来的产品
		  if(pde!=null&&pde.getPid()!=null&&(list.get(0).getPid().equals(pde.getPid()))){
				    result.setMsg("产品相同,无需切换!");
					result.setData(pde.getPname());
					result.setState(3);
					return result;
		  }
		  
		
		
		
		
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("lid", lineList.get(0).getLid());
		map.put("pid",pd.getPid() );
		//map.put("scstate",lineList.get(0).getScstate());
		int n=lineDao.updateLine(map);
		if(n!=1){
			throw new RuntimeException("产线修改失败!");
		}
		NewCheck check=new NewCheck();
		check.setNowDate(CyUtil.getTime2());
		check.setProduct(pde);
		check.setLine(line);
		List<NewCheck> mlist=newDao.findNewCheckByWorkerCode(check);
		
		System.out.println(mlist);
		if(mlist!=null&&mlist.size()>0){
			for(NewCheck mcheck:mlist){
			   if(mcheck.getUpLine()!=null&&mcheck.getDownLine()==null){
				   mcheck.setDownLine(CyUtil.getTime1());
				   int p=newDao.updateNewCheck(mcheck);
				   if(p!=1){
					   throw new RuntimeException("下线失败!");
				   }
				   //System.out.println(mcheck.getWorker());
				   mcheck.setDownLine(null);
				   System.out.println("产线编码:"+line);
				   mcheck.setLine(line);
				   mcheck.setUpLine(CyUtil.getTime1());
				   mcheck.setNowDate(CyUtil.getTime2());
				   mcheck.setProduct(list.get(0));
				   mcheck.setScstate(lineList.get(0).getScstate());
				   int d=newDao.addNewCheck(mcheck);
				   if(d!=1){
					   throw  new RuntimeException("上线失败!");
				   }
			   }
			}
		}
		result.setMsg("产品切换成功!");
		if(lineList.get(0)!=null&&lineList.get(0).getScstate()!=null){
			result.setData8(lineList.get(0).getScstate());
		}else{
			result.setData8("");
		}
		result.setData(list.get(0).getPname());
		result.setState(1);
		return result;
	}

    
	public CyResult upScstate(WorkLine line){
		CyResult result=new CyResult();
		if(line.getLineCode()==null||line.getScstate()==null){
			result.setState(0);
			result.setMsg("产线或工艺不能为空");
			return result;
		}
		List<WorkLine> linelist=lineDao.findLineByCode(line);
		Map<String,Object> map=new HashMap<String,Object>();
		if(linelist!=null&&linelist.size()>0){
			map.put("lid", linelist.get(0).getLid());
		}
		map.put("scstate", line.getScstate());
		try{
		   int n=lineDao.updateLine(map);
		}catch(Exception e){
			
			result.setMsg("异常!");
			result.setState(2);
			return result;
		}
		result.setData8(line.getScstate());
		result.setMsg("切换工艺成功!");
		result.setState(1);
		return result;
		
	}
	
	
	
	/*
	 * 加载打卡记录
	 */
	@Override
	public CyResult loadNewCheck(ProductWorker worker) {
		CyResult result=new CyResult();
		if(worker.getWid()==null){
			result.setMsg("人员wid不能为空!");
			result.setState(0);
			return result;
		}
		System.out.println(worker.getWid());
		ProductWorker workerOne=workerDao.findWorkerById(worker);
		System.out.println(workerOne);
		if(workerOne==null){
			result.setMsg("人员不存在");
			result.setState(2);
			return result;
		}
		NewCheck check=new NewCheck();
		check.setWorker(workerOne);
		List<NewCheck> list=newDao.findNewCheckByWorkerCode(check);
//		System.out.println("打卡记录:"+list);
//		for(NewCheck ncheck:list){
//			List<ProductDetails> pdList=lineDao.findProductById(ncheck.getProduct());
//			System.out.println("产品:"+pdList);
//			ncheck.getProduct().setPname(pdList.get(0).getPname());
//			ncheck.getWorker().setWorkerName(workerOne.getWorkerName());
//		}
		result.setData(list);
		result.setState(1);
		result.setMsg("打卡记录加载成功!");
		return result;
	}
	
	
	/*
	 * App(non-Javadoc)加载打卡记录
	 * @see com.cy.service.check.NewCheckService#loadWorkTime(com.cy.domain.line.ProductWorker)
	 */
	@Override
	public CyResult loadAppNewCheck(String workerCode) {
		Gson gson=new Gson();
		CyResult result=new CyResult();
		if(workerCode==null){
			result.setMsg("工号不能为空");
			result.setState(0);
			return result;
		}
		
		
		
		ProductWorker workerOne=workerDao.findWorkerByCode(workerCode);
		if(workerOne==null){
			result.setMsg("人员不存在");
			result.setState(2);
			return result;
		}
		NewCheck check=new NewCheck();
		check.setWorker(workerOne);
		List<NewCheck> list=newDao.findNewCheckByWorkerCode(check);
		for(NewCheck newcheck:list){
			System.out.println(newcheck);
			if(newcheck.getDownLine()==null){
				System.out.println(check.getDownLine());
				newcheck.setDownLine("");
			}
		}
		if(list!=null&&list.size()>20){
			list=list.subList(0, 20);
		}
		result.setData(gson.toJson(list));
		result.setData1(workerOne.getWorkerName());
		result.setState(1);
		result.setMsg("打卡记录加载成功!");
		return result;
		
	}
	
	
	


	@Override
	public CyResult loadWorkTime(ProductWorker worker) throws ParseException {
		DecimalFormat df = new DecimalFormat("#.00");
		CyResult result=new CyResult();
		if(worker.getWid()==null){
			result.setMsg("人员wid不能为空!");
			result.setState(0);
			return result;
		}
		System.out.println(worker.getWid());
		ProductWorker workerOne=workerDao.findWorkerById(worker);
		System.out.println(workerOne);
		if(workerOne==null){
			result.setMsg("人员不存在");
			result.setState(2);
			return result;
		}
		NewCheck check=new NewCheck();
		check.setWorker(workerOne);
		check.setWorkerName(workerOne.getWorkerName());
		List<NewCheck> list=newDao.findNewCheckByWorkerCode(check);
		System.out.println("公示列表:"+list);
		Set<WorkTime> set=new HashSet<WorkTime>();
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getProduct());
			check.setNowDate(list.get(i).getNowDate());
			check.setPname(list.get(i).getPname());
			check.setLine(list.get(i).getLine());
			List<NewCheck> listOne=newDao.findNewCheckByWorkerCode(check);	
			Long sumtime=0L;
			for(NewCheck ck:listOne){
				if(ck.getUpLine()!=null&&ck.getDownLine()==null){
					sumtime+=CyUtil.parseTime(CyUtil.getTime1(), ck.getUpLine());
				}
				if(ck.getUpLine()!=null&&ck.getDownLine()!=null){
					sumtime+=CyUtil.parseTime(ck.getDownLine(), ck.getUpLine());
				}	
			}
			WorkTime wt=new WorkTime();
			wt.setLineCode(list.get(i).getLine().getLineCode());
			wt.setNowDate(list.get(i).getNowDate());
			System.out.println(list.get(i).getProduct());
			//List<ProductDetails> pdList=lineDao.findProductById(list.get(i).getProduct());
			//System.out.println(pdList);
			wt.setPname(list.get(i).getPname());
			wt.setWorkerCode(list.get(i).getWorker().getWorkerCode());
			wt.setWorkerName(workerOne.getWorkerName());
			System.out.println(sumtime);
			System.out.println(sumtime.toString().substring(0, 1));
			Double tm=(double)(sumtime*1.0/1000/60/60);
			if(tm.toString().substring(0, 1).equals("0")){
			   wt.setWtime("0"+df.format(tm)+"小时");
		    }else{
		       wt.setWtime(df.format(tm)+"小时");
		    }
			set.add(wt);
		}
		result.setState(1);
		result.setMsg("加载工时成功!");
		result.setData(set);
		return result;
	}

    /*
     * app端修改工艺(non-Javadoc)
     * @see com.cy.service.check.NewCheckService#updateLocationState()
     */
	@Override
	public CyResult updateLocationState() {
	    CyResult result=new CyResult();
	    try {
	    	lineDao.updateLocationState();
		} catch (Exception e) {
			result.setMsg("网络异常!");
			result.setState(0);
			return result;
		}
	    result.setMsg("重置成功!");
	    result.setState(1);
		return result;
	}

	/*
     * App端加载人员的详情
     */
	@Override
	public CyResult appLoadWorkerByCode(String workerCode) {
		Gson gson=new Gson();
		CyResult result=new CyResult();
		if(workerCode==null){
			result.setMsg("工号不能为空!");
			result.setState(0);
			return result;
		}
		ProductWorker worker=new ProductWorker();
		worker.setWorkerCode(workerCode);
		List<ProductWorker> list=workerDao.findWorker(worker);
		if(list==null||list.size()<1){
			result.setMsg("查无此人!");
			result.setState(0);
			return result;
		}
		for(ProductWorker w:list){
			if(w.getLocation()==null){
			   WorkLocation wl=new WorkLocation();
			   WorkLine line=new WorkLine();
			   line.setLineCode("");
			   wl.setLine(line);
			   w.setLocation(wl);
			}
			if(w.getWorkPost()==null){
				w.setWorkPost("");
			}
			if(w.getTel()==null){
				w.setTel("");
			}
			if(w.getAbi()==null){
				w.setAbi("");
			}
			
		}
		result.setData(gson.toJson(list));
		result.setData1(list.get(0).getWorkerName());
		result.setMsg("人员信息加载成功!");
		result.setState(1);
		return result;
		
	}
	
	
	
	public CyResult loadWorkStatus(WorkLine line,ProductWorker worker,String nowDate) throws ParseException{
		CyResult result=new CyResult();
		CheckStatus cs=new CheckStatus();
		
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sd=new SimpleDateFormat("HH:mm");
	
		List<WorkLine> lineOne=lineDao.findLineByCode(line);
		List<ProductWorker> plist=workerDao.findWorker(worker);
		if(plist==null||plist.size()<1){
			result.setMsg("查无此人");
			result.setState(0);
			return result;
			
		}
		String timeScope1=lineOne.get(0).getTimeScope1();
		String timeScope2=lineOne.get(0).getTimeScope2();
		String flushTime=lineOne.get(0).getFlushTime();
		long longflushTime=Integer.parseInt(flushTime)*60*1000;//转换为毫秒
		
		
		
		
		NewCheck check=new NewCheck();
		check.setNowDate(nowDate);
		//check.setLine(line);
		check.setWorker(worker);
		List<NewCheck> newList=newDao.findNewCheckByWorkerCode(check);
		if(newList==null||newList.size()<1){
			result.setMsg("当日尚无打卡记录");
			result.setState(2);
			return result;
		}
	    NewCheck checkFirst=newList.get(0);//当天第一条打卡记录
	    String upLine=checkFirst.getUpLine();//当天第一次上线时间
	    Long upLineTime=sdf.parse(upLine).getTime();//转换为毫秒
	    String  upWorkLimit=timeScope1.substring(0, timeScope1.indexOf("-"));//上午上班打卡临界值
	    String  downWorkLimit=timeScope1.substring(timeScope1.indexOf("-")+1);//上午下班打卡临界值
	    cs.setWorkerCode(worker.getWorkerCode());
    	cs.setWorkerName(plist.get(0).getWorkerName());
    	cs.setNowDate(CyUtil.getTime());
	    if(upLineTime<sd.parse(downWorkLimit).getTime()&&upLineTime>sd.parse(upWorkLimit).getTime()){
	    	cs.setAmLate(1);//上午上班迟到
	    }
	    if(upLineTime<sd.parse(upWorkLimit).getTime()){
	    	cs.setAmLate(0);//上午按时上班
	    }
	    for(NewCheck newcheck:newList){
	        if(sdf.parse(newcheck.getDownLine()).getTime()<sd.parse(upWorkLimit).getTime()){
	    	  cs.setAmLate(0);//上午按时上班
	        }
	    }
		
	    
	    String afterUplineLimit=timeScope2.substring(0, timeScope2.indexOf("-"));//上午上班打卡临界值
	    String afterDownlineLimit=timeScope2.substring(timeScope2.indexOf("-")+1);//上午下班打卡临界值
	    for(NewCheck newcheck:newList){
	    	if(sdf.parse(newcheck.getUpLine()).getTime()>sd.parse(downWorkLimit).getTime()&&sdf.parse(newcheck.getUpLine()).getTime()<sd.parse(afterDownlineLimit).getTime()){
	    		
	    	}
	    }
	    
		
		return null;
		
	}

    
	
	

	
}
