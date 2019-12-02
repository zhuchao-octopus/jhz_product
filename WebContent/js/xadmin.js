$(function () {
	
	
    //加载弹出层
    layui.use(['form','element'],
    function() {
        layer = layui.layer;
        element = layui.element;
    });

    //触发事件
  var tab = {
        tabAdd: function(title,url,id){
          //新增一个Tab项
          element.tabAdd('xbs_tab', {
            title: title 
            ,content: '<iframe tab-id="'+id+'" frameborder="0" src="'+url+'" scrolling="yes" class="x-iframe"></iframe>'
            ,id: id
          })
        }
        ,tabDelete: function(othis){
          //删除指定Tab项
          element.tabDelete('xbs_tab', '44'); //删除：“商品管理”
          
          
          othis.addClass('layui-btn-disabled');
        }
        ,tabChange: function(id){
          //切换到指定Tab项
          element.tabChange('xbs_tab', id); //切换到：用户管理
        }
      };


    tableCheck = {
        init:function  () {
            $(".layui-form-checkbox").click(function(event) {
                if($(this).hasClass('layui-form-checked')){
                    $(this).removeClass('layui-form-checked');
                    if($(this).hasClass('header')){
                        $(".layui-form-checkbox").removeClass('layui-form-checked');
                    }
                }else{
                    $(this).addClass('layui-form-checked');
                    if($(this).hasClass('header')){
                        $(".layui-form-checkbox").addClass('layui-form-checked');
                    }
                }
                
            });
        },
        getData:function  () {
            var obj = $(".layui-form-checked").not('.header');
            var arr=[];
            obj.each(function(index, el) {
                arr.push(obj.eq(index).attr('data-id'));
            });
            return arr;
        }
    }

    //开启表格多选
    tableCheck.init();
      

    $('.container .left_open i').click(function(event) {
        if($('.left-nav').css('left')=='0px'){
            $('.left-nav').animate({left: '-221px'}, 100);
            $('.page-content').animate({left: '0px'}, 100);
            $('.page-content-bg').hide();
        }else{
            $('.left-nav').animate({left: '0px'}, 100);
            $('.page-content').animate({left: '221px'}, 100);
            if($(window).width()<768){
                $('.page-content-bg').show();
            }
        }

    });

    $('.page-content-bg').click(function(event) {
        $('.left-nav').animate({left: '-221px'}, 100);
        $('.page-content').animate({left: '0px'}, 100);
        $(this).hide();
    });

    $('.layui-tab-close').click(function(event) {
        $('.layui-tab-title li').eq(0).find('i').remove();
    });

    //左侧菜单效果
    // $('#content').bind("click",function(event){
    $('.left-nav #nav li').click(function (event) {

        if($(this).children('.sub-menu').length){
            if($(this).hasClass('open')){
                $(this).removeClass('open');
                $(this).find('.nav_right').html('&#xe697;');
                $(this).children('.sub-menu').stop().slideUp();
                $(this).siblings().children('.sub-menu').slideUp();
            }else{
                $(this).addClass('open');
                $(this).children('a').find('.nav_right').html('&#xe6a6;');
                $(this).children('.sub-menu').stop().slideDown();
                $(this).siblings().children('.sub-menu').stop().slideUp();
                $(this).siblings().find('.nav_right').html('&#xe697;');
                $(this).siblings().removeClass('open');
            }
        }else{

            var url = $(this).children('a').attr('_href');
            var title = $(this).find('cite').html();
            var index  = $('.left-nav #nav li').index($(this));

            for (var i = 0; i <$('.x-iframe').length; i++) {
                if($('.x-iframe').eq(i).attr('tab-id')==index+1){
                    tab.tabChange(index+1);
                    event.stopPropagation();
                    return;
                }
            };
            
            tab.tabAdd(title,url,index+1);
            tab.tabChange(index+1);
        }
        
        event.stopPropagation();
         
    })
    
})

/*弹出层*/
/*
    参数解释：
    title   标题
    url     请求的url
    id      需要操作的数据id
    w       弹出层宽度（缺省调默认值）
    h       弹出层高度（缺省调默认值）
*/
function x_admin_show(title,url,w,h){
    if (title == null || title == '') {
        title=false;
    };
    if (url == null || url == '') {
        url="404.html";
    };
    if (w == null || w == '') {
        w=($(window).width()*0.9);
    };
    if (h == null || h == '') {
        h=($(window).height() - 50);
    };
    layer.open({
        type: 2,
        area: [w+'px', h +'px'],
        fix: false, //不固定
        maxmin: true,
        shadeClose: true,
        shade:0.4,
        title: title,
        content: url
    });
}

/*关闭弹出框口*/
function x_admin_close(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

function loadCustomer(cy_brand_id){//获取客户的下拉选
	var options=null;
	//var data = null
	$.ajax({
		type:"get",
		async:false,
		url:"/jhz_mtvCms/wxx/loadCusRelation.do",
		data:{"cy_brand_id":cy_brand_id},
		dataType:"json",
		success:function(result){
			if(result.status==1){
				
				for(var i=0;i<result.data.length;i++){
				   var option="<option value="+result.data[i].cid+">"+result.data[i].customerName+"</option>";
				   options+=option;
				}
				//console.log(options);
				
		  }else{
			/*  layer.msg(result.msg)*/
			  
			 
		  }
		},	
		error:function(result){
			/*layer.msg("error");*/
		}
	})
	return options;
}


function loadLunchOption(cy_brand_id){
	var options=null;
	//var data = null
	$.ajax({
		type:"get",
		async:false,
		url:"/jhz_mtvCms/syy/loadLuncher.do",
		data:{"cy_brand_id":cy_brand_id},
		dataType:"json",
		success:function(result){
			if(result.status==1){
				
				for(var i=0;i<result.data.length;i++){
				   var option="<option value="+result.data[i].lunchname+">"+result.data[i].lunchname+"</option>";
				   options+=option;
				}
				//console.log(options);
				
		  }else{
			  /*layer.msg(result.msg)*/
			  
			 
		  }
		},	
		error:function(result){
			/*layer.msg("error");*/
		}
	})
	return options;
}

function loadMarketClass(cy_brand_id){
	var options=null;
	//var data = null
	$.ajax({
		type:"get",
		async:false,
		url:"/jhz_mtvCms/syy/loadMarketClassOption.do",
		data:{"cy_brand_id":cy_brand_id},
		dataType:"json",
		success:function(result){
			if(result.status==1){
				
				for(var i=0;i<result.data.length;i++){
				   var option="<option value="+result.data[i].market_class_id+">"+result.data[i].market_class_name+"</option>";
				   options+=option;
				}
				//console.log(options);
				
		  }else{
			  /*layer.msg(result.msg)*/
			  
			 
		  }
		},	
		error:function(result){
			/*layer.msg("error");*/
		}
	})
	return options;
}


function loadBrand(){
	var options=null;
	//var data = null
	$.ajax({
		type:"get",
		async:false,
		url:"/jhz_mtvCms/wxx/loadBrand.do",
		/*data:{"cy_brand_id":cy_brand_id},*/
		dataType:"json",
		success:function(result){
			if(result.status==1){
				
				for(var i=0;i<result.data.length;i++){
				   var option="<option value="+result.data[i].cy_brand_id+">"+result.data[i].cy_brand_name+"</option>";
				   options+=option;
				}
				//console.log(options);
				
		  }else{
			  /*layer.msg(result.msg)*/
			  
			 
		  }
		},	
		error:function(result){
			/*layer.msg("error");*/
		}
	})
	return options;
}

/*function contains(value,arr){
	var index = $.inArray(value,arr);
	if(index>0){
		return true;
	}else{
		return false;
	}
}*/

function contains(value,arr){
	for(var i=0;i<arr.length;i++){
		if(arr[i]==value){
			return true;
		}
	}
	return false;
}

var roleId=loginUser.kid;
function getContextPath(){   
    var pathName = document.location.pathname;   
    var index = pathName.substr(1).indexOf("/");   
    var result = pathName.substr(0,index+1);   
    return result;   
} 
function loadPermissionById(roleId){

	var permissionStr=null;
	$.ajax({
		type:"get",
		async: false,
		url:getContextPath()+"/user/findPermissionsByKid.do",
		data:{"kid":roleId},
		dataType:"json",
		success:function(res){
			if(res.state==1){
				permissionStr=res.data.permissionStr;
				console.log(permissionStr);
			}
		},
		error:function(){
			
		}
		
	});
	return permissionStr;
}

var perstr=loadPermissionById(roleId);
var per_arr=new Array();
if(perstr==null){
	per_arr=[];
}else{
	per_arr=perstr.split(",");
	console.log("权限数组",per_arr);
}




function loadPitOption(cid,lunchname,cy_brand_id,pushNumber){
	var options;
	$.ajax({
		type:"get",
		async:false,
		url:"/jhz_mtvCms/syy/loadPitByPit.do",
		data:{
			"cid":cid,
			"lunchname":lunchname,
			"cy_brand_id":cy_brand_id,
			"pushNumber":pushNumber
		},
	    dataType:"json",
	    success:function(res){
	    	for(var i=0;i<res.data.length;i++){
	    		options+="<option value="+res.data[i].syy_pit_local+">"+res.data[i].syy_pit_local+"</option>";
	    	}
	    },
	    error:function(res){
	    	//layer.msg("error");
	    }
	});
	return options;
}


function loadAllMarketName(cy_brand_id){
	var options;
	$.ajax({
		type:"get",
		async:false,
		url:"/jhz_mtvCms/syyMarket/loadAllMarketName.do",
		data:{
			"cy_brand_id":cy_brand_id,
		},
	    dataType:"json",
	    success:function(res){
	    	for(var i=0;i<res.data.length;i++){
	    		options+="<option value="+res.data[i].nid+">"+res.data[i].marketName+"</option>";
	    	}
	    },
	    error:function(res){
	    	//layer.msg("error");
	    }
	});
	return options;
}

function loadAllMarketClass(cy_brand_id){
	layui.use('form', function(){
		  var form = layui.form;
	});
	var options="";
	$.ajax({
		type:"get",
		async:false,
		url:"/jhz_mtvCms/syyMarket/loadAllMarketClass.do",
		data:{
			"cy_brand_id":cy_brand_id,
		},
	    dataType:"json",
	    success:function(res){
	    	for(var i=0;i<res.data.length;i++){
	    		var optgroup ="<optgroup label='"+res.data[i].marketName+"'>";
	    		var opt="";
	    		var list=res.data[i].appClassList;
	    		for(var j=0;j<list.length;j++){
	    			opt+="<option value='"+list[j].market_class_id+"'>"+list[j].market_class_name+"</option>";
	    			//opt+="<option value='"+res.data[i].appClassList[j].market_class_id+"'>"+res.data[i].appClassList[j].market_class_name+"</option>"
	    			optgroup+=opt;
	    		}
	    		optgroup+="</optgroup>";
	    		options+=optgroup;
	    	}
	    },
	    error:function(res){
	    	//layer.msg("error");
	    }
	});
	return options;
}

function loadRegion(cy_brand_id){
	var option="";
	$.ajax({
		type:"get",
		async:false,
		url:"/jhz_mtvCms/user/findRegion.do",
		data:{
			"cy_brand_id":cy_brand_id,
		},
	    dataType:"json",
	    success:function(res){
	    	for(var i=0;i<res.data.length;i++){
	    		option+="<option value='"+res.data[i]+"'>"+res.data[i]+"</option>";
	    	}
	    }
	});
	return option;
}


function loadProductOption(){
	var option="";
	$.ajax({
		type:"get",
		async:false,
		url:"line/loadOptions.do",
		data:{
			
		},
	    dataType:"json",
	    success:function(res){
	    	for(var i=0;i<res.data1.length;i++){
	    		option+="<option value='"+res.data1[i].pid+"'>"+res.data1[i].pname+"</option>";
	    	}
	    }
	});
	return option;
}
function loadUserGroupBoss(){
	var option="";
	$.ajax({
		type:"get",
		async:false,
		url:getContextPath()+"/board/loadUserGroupBoss.do",
		data:{
			
		},
	    dataType:"json",
	    success:function(res){
	    	for(var i=0;i<res.data.length;i++){
	    		option+="<option value='"+res.data[i].userId+"'>"+res.data[i].realName+"</option>";
	    	}
	    }
	});
	return option;
}

function selectCodeNameByPname(pid){
	var option="";
	$.ajax({
		type:"get",
		async:false,
		url:"list/searchProductScheduling.do",
		data:{
			"cpid":pid
		},
	    dataType:"json",
	    success:function(res){
	    	for(var i=0;i<res.data.length;i++){
	    		option+="<option value='"+res.data[i].pid+"'>"+res.data[i].codeName+"</option>";
	    	}
	    }
	});
	return option;
}

function findOptionData(str){
	var option="";
	$.ajax({
		type:"get",
		async:false,
		url:"list/distinctProduct.do",
		data:{
			"str":str
		},
		dataType:"json",
		success:function(res){
			for(var i=0;i<res.data.length;i++){
				option+="<option value='"+res.data[i]+"'>"+res.data[i]+"</option>";
			}
		}
	});
	console.log(option);
	return option;
}


function loadAllLid(){
	var option="";
	$.ajax({
		type:"get",
		async:false,
		url:"line/loadAllworkLine.do",
		dataType:"json",
		success:function(res){
			for(var i=0;i<res.data.length;i++){
				option+="<option value='"+res.data[i].lid+"'>"+res.data[i].lineCode+"</option>";
			}
		}
	});
	console.log(option);
	return option;
}

function loadAllLineCode(){
	var option="";
	$.ajax({
		type:"get",
		async:false,
		url:"line/loadAllworkLine.do",
		dataType:"json",
		success:function(res){
			for(var i=0;i<res.data.length;i++){
				option+="<option value='"+res.data[i].lineCode+"'>"+res.data[i].lineCode+"</option>";
			}
		}
	});
	console.log(option);
	return option;
}
function loadAllLineCodeId(){
	var option="";
	$.ajax({
		type:"get",
		async:false,
		url:"line/loadAllworkLine.do",
		dataType:"json",
		success:function(res){
			for(var i=0;i<res.data.length;i++){
				option+="<option value='"+res.data[i].lid+"'>"+res.data[i].lineCode+"</option>";
			}
		}
	});
	console.log(option);
	return option;
}



function loadAllWorkStation(pid){
	var option="";
	$.ajax({
		type:"get",
		async:false,
		url:"line/loadOptions.do?pid="+pid,
		dataType:"json",
		success:function(res){
			for(var i=0;i<res.data4.length;i++){
				option+="<option value='"+res.data4[i].mid+"'>"+res.data4[i].wsNumber+"("+res.data4[i].wsFunction+")"+"</option>";
			}
		}
	});
	console.log(option);
	return option;
}

function loadAllRole(){
	var option="";
	$.ajax({
		type:"get",
		async:false,
		url:"user/loadAllRole.do",
		dataType:"json",
		success:function(res){
			for(var i=0;i<res.data.length;i++){
				option+="<option value='"+res.data[i].kid+"'>"+res.data[i].roleName+"</option>";
			}
		}
	});
	console.log(option);
	return option;
}


function loadAllCust(){
	var options="";
	$.ajax({
		type:"get",
		async:false,
		url:"tv/loadAllCust.do",
		dataType:"json",
		success:function(res){
			for(var i=0;i<res.data.length;i++){
				options+="<option value='"+res.data[i].id+"'>"+res.data[i].clientName+"</option>";
			}
		}
	});
	return options
}

function loadAllCustNumber(){
	var options="";
	$.ajax({
		type:"get",
		async:false,
		url:"tv/loadAllCust.do",
		dataType:"json",
		success:function(res){
			for(var i=0;i<res.data.length;i++){
				options+="<option value='"+res.data[i].clientNo+"'>"+res.data[i].clientName+"</option>";
			}
		}
	});
	return options
}


function loadPnCode(pn){
	var options="";
	$.ajax({
		type:"get",
		async:false,
	    url:"tv/loadmate.do",
	    data:{"pn":pn},
	    dataType:"json",
	    success:function(res){
	    	if(res.state==0){
	    		for(var i=0;i<res.data.length;i++){
	    			options+="<option value='"+res.data[i].id+"' selected='selected'>"+res.data[i].materialName+"</option>"
	    		}
	    	}
	    	
	    }
	    
	});
	return options;
}

function searchBom(pn){
	var options="";
	$.ajax({
		type:"get",
		async:false,
	    url:"tv/sreachBom.do",
	    data:{"pn":pn},
	    dataType:"json",
	    success:function(res){
	    	if(res.state==0){
	    		for(var i=0;i<res.data.length;i++){
	    			options+="<option value='"+res.data[i].id+"'>"+res.data[i].pname+"</option>"
	    		}
	    	}
	    	
	    }
	    
	});
	return options;
}



function loadOrders(mid){
	var options="";
	$.ajax({
		type:"get",
		async:false,
	    url:"tv/loadOrdersByMid.do",
	    data:{"mid":mid},
	    dataType:"json",
	    success:function(res){
	    	if(res.code==0){
	    		for(var i=0;i<res.data.length;i++){
	    			options+="<option value='"+res.data[i]+"'>"+res.data[i]+"</option>"
	    		}
	    	}
	    	console.log(options);
	    	
	    }
	    
	});
	return options;
}

function uniqueArray(array, key){
	  var result = [array[0]];
	  for(var i = 1; i < array.length; i++){
	    var item = array[i];
	    var repeat = false;
	    for (var j = 0; j < result.length; j++) {
	      if (item[key] == result[j][key]) {
	        repeat = true;
	        break;
	      }
	    }
	    if (!repeat) {
	      result.push(item);
	    }
	  }
	  return result;
	}

function loadCompany(){
	var options="";
	$.ajax({
		type:"get",
		async:false,
	    url:"tv/companyIdName.do",
	   /* data:{"mid":mid},*/
	    dataType:"json",
	    success:function(res){
	    	if(res.code==0){
	    		for(var i=0;i<res.data.length;i++){
	    			options+="<option value='"+res.data[i].id+"'>"+res.data[i].companyName+"</option>"
	    		}
	    	}
	    	console.log(options);
	    	
	    }
	    
	});
	return options;
}


function loadMaketOption(){
	var options="";
	$.ajax({
		type:"get",
		async:false,
	    url:"tv/makerIdName.do",
	    /*data:{"mid":mid},*/
	    dataType:"json",
	    success:function(res){
	    	if(res.code==0){
	    		for(var i=0;i<res.data.length;i++){
	    			options+="<option value='"+res.data[i].id+"'>"+res.data[i].makerName+"</option>"
	    		}
	    	}
	    	console.log(options);
	    	
	    }
	    
	});
	return options;
}

function loadAllShiftType(){
	var options="";
	$.ajax({
		type:"get",
		async:false,
	    url:"getShift.do",
	    /*data:{"mid":mid},*/
	    dataType:"json",
	    success:function(res){
	    	if(res.code==0){
	    		for(var i=0;i<res.data.length;i++){
	    			options+="<option value='"+res.data[i].shiftType+"'>"+res.data[i].shiftType+"</option>"
	    		}
	    	}
	    	console.log(options);
	    	
	    }
	    
	});
	return options;
}


function loadRequest(id){
	var options="";
	$.ajax({
		type:"get",
		async:false,
	    url:"tv/requestId.do",
	    data:{"id":id},
	    dataType:"json",
	    success:function(res){
	    	if(res.code==0){
	    		for(var i=0;i<res.data.length;i++){
	    			options+="<option value='"+res.data[i].id+"'>"+res.data[i].serialNumber+"</option>"
	    		}
	    	}
	    	console.log(options);
	    	
	    }
	    
	});
	return options;
}


function loadRequestDetails(id){
	var str="";
	$.ajax({
		type:"get",
		async:false,
	    url:"tv/requestId.do",
	    data:{"id":id},
	    dataType:"json",
	    success:function(res){
	    	if(res.code==0){
	    		for(var i=0;i<res.data.length;i++){
	    			str+=res.data[i].qualityRequest+"</br>"+res.data[i].deliveryRequest+"</br>"+res.data[i].clearingform+"</br>"+res.data[i].taxDemands+"</br>"+res.data[i].informationChange+"</br>"+res.data[i].dissResolution
	    		}
	    	}
	    	console.log(str);
	    	
	    }
	    
	});
	return str;
}
//原因
function loadBadReasons(){
	var option="";
	$.ajax({
		type:"get",
		async:false,
		url:"list/loadHealthyy.do",
		data:{
			
		},
	    dataType:"json",
	    success:function(res){
	    	for(var i=0;i<res.data.length;i++){
	    		option+="<option value='"+res.data[i].brid+"'>"+res.data[i].unhealthyy+"</option>";
	    	}
	    }
	});
	return option;
}

//现象
function loadPhenomenon(){
	var option="";
	$.ajax({
		type:"get",
		async:false,
		url:"list/loadBlPhenom.do",
		data:{
			
		},
	    dataType:"json",
	    success:function(res){
	    	for(var i=0;i<res.data.length;i++){
	    		option+="<option value='"+res.data[i].xId+"'>"+res.data[i].blPhenom+"</option>";
	    	}
	    }
	});
	return option;
}
function loadProductModelByPid(pid){
	var option="";
	$.ajax({
		type:"get",
		async:false,
		url:"list/loadProductModelByPid.do",
		data:{
			"pid":pid
		},
	    dataType:"json",
	    success:function(res){
	    	for(var i=0;i<res.data.length;i++){
	    		option+="<option value='"+res.data[i].id+"'>"+res.data[i].cy_model+"</option>";
	    	}
	    }
	});
	return option;
}
function loadMyXM(userId){
	var MyXM="";
	$.ajax({
		type:"get",
		async:false,
		url:getContextPath()+'/board/loadProject.do',
		data:{
			"userId":userId
		},
	    dataType:"json",
	    success:function(res){
	    	for(var i=0;i<res.data.length;i++){
	    		MyXM=MyXM+res.data[i].jhz_projectName+";";
	    	}
	    }
	});
	return MyXM;
}
function loadMyIntegra(userId){
	var MyXM=0;
	$.ajax({
		type:"get",
		async:false,
		url:getContextPath()+'/board/loadMyIntegra.do',
		data:{
			"userId":userId
		},
	    dataType:"json",
	    success:function(res){
	    	for(var i=0;i<res.data.length;i++){
	    		MyXM=MyXM+res.data[i].jhz_integral;
	    	}
	    }
	});
	return MyXM;
}
function loadMyTasks(userId){
	var MyRW="";
	$.ajax({
		type:"get",
		async:false,
		url:getContextPath()+'/board/loadTasks.do',
		data:{
			"userId":userId
		},
	    dataType:"json",
	    success:function(res){
	    	for(var i=0;i<res.data.length;i++){
	    		MyRW=MyRW+res.data[i].jhz_tasksName+";";
	    	}
	    }
	});
	return MyRW;
}

