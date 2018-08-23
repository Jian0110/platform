$(function() {
	
	//获取按钮权限许可，完成后再进行表格初使化
	let perms = ['user-list:edit','user-list:new','user-list:excel','user-list:menu-perm','user-list:button-perm','user-list:data-perm'];
	$.ajax({
		url:$contextPath + '/perm/perm-button-valid',
		dataType:'json',
        contentType:'application/json; charset=utf-8',
        type:'POST',
        data:JSON.stringify(perms),
        success:function(data, textStatus){
        	perms = data;
        	initTable();
        },
		error:function(XMLHttpRequest, textStatus, errorThrown){},
        complete:function(XHR, TS){}
	});
	

	
	//初始化面包屑
    let breadcrumb = $(".breadcrumb").breadcrumb();
    //表格初始化
	//格式为 $object.SimpleDataTable(options);
	let table = null;
	let initTable = function(){
		let buttons = [];
		let add = {
				'type':'custom',
				'text':'新增',
				"icon":"fa-plus blue",//图标 必须有
				//按钮事件
				"action":function(e, dt, button, config){
                    breadcrumb.push({
                        url:$contextPath + '/user/user-edit-page #wrapper-content',
                        title:'新增用户',
                        callback:function() {
							$('#user-edit-form [name="password"]').attr("placeholder","登录密码");
                        }
                    });
				}
		};
		let excel = {'type':'excel','enable':true};
		let copy = {'type':'copy','enable':false};
		/*
		var remove = ,{
			'text':'删除',
			"icon":"fa-trash red",
			"action":function(e, dt, button, config){
				var rows = table.rows('.selected').data();
				if(rows.length == 0){
                    parent.$.gritter.add({
                        title: '操作失败',
                        text: '请至少选择一条记录',
                        class_name: 'gritter-error',
                        sticky: false
                    });
                    return ;
				}
				var data = [];
				for(var i = 0 ;i < rows.length ;i++){
                    data.push(rows[i]['userId']);
				}
                parent.layer.confirm('确认删除吗？该操作无法撤销。', {
                    btn: ['确定','取消'] //按钮
                }, function(){
                    parent.layer.close(arguments[0]);
                    $.cjax({
						url:$contextPath+'/user/user-list-remove',
						data:JSON.stringify({ids:data}),
						success:function (data,status) {
                            table.draw();
                        }
					});
                });
			}
		};
		*/
		
		
		if(perms['user-list:new']){
			buttons.push(add);
		}
		if(perms['user-list:excel']){
			buttons.push(excel);
		}
		buttons.push(copy);
		
		table = $('#dynamic-table')
			.SimpleDataTable({
	            //数据请求地址
				//要加 $contextpath 否则在非根目录环境下 程序 会不正常
				'url' : $contextPath + '/user/user-page-data',
				//搜索条件控件区 ID
				'searchFormBlock' : '#search-form-block',
				"select" : true,//复选框开启
				//"order" : [ [ 1, "desc" ] ],
				
				//自定义按钮
				"buttons" : buttons,
				//定义表格列属性
				//具体返回数据格式可以参考 DataTablesObject.java
				//按顺序，下标从0开始
				//data：json的key
				//name：数据表对应字段名 排序用
				//render：内容自定义函数
				//bSortable：是否可以排序
				"columns" : [
					{"data" : "userId"},
					{"data" : "username", "name" : "username"},
					{"data" : "realname", "name" : "realname"},
					{"data" : "roleName", "name" : "role_id"},
					{"data" : "departmentName", "name" : "department_id", "bSortable" : true},
					{"data" : "enableText", "name" : "enable"},
					{
						"data" : "lastLoginTs",
						"name" : "last_login_ts",
						"render" : function(data, type, row) {
							if (data === null || data === '')
								return null;
							return new Date(data)
									.Format("yyyy-MM-dd hh:mm:ss");
						}
					} , {
	                    "data" : "userId",
	                    "bSortable" : false,
						"render" : function(data, type, row) {
							
	                    	let str = '';
	                    	if(perms['user-list:edit']){
	                    		str += '<a href="javascript:;" class="datatable-btn edit-btn" data-id="' + data + '" title="编辑信息"><i class="fa fa-edit bigger-150"></i></a>';
	                    	}
	                    	if(perms['user-list:menu-perm']){
	                    		str += '<a href="javascript:;" class="datatable-btn menu-perm-btn" data-id="'+data+'" title="菜单权限"><i class="fa fa-list-ul bigger-150"></i></a>';
	                    	}
	                    	if(perms['user-list:button-perm']){
	                    		str += '<a href="javascript:;" class="datatable-btn button-perm-btn" data-id="'+data+'" title="按钮权限"><i class="fa fa-keyboard-o bigger-150"></i></a>';
	                    	}
	                    	if(perms['user-list:data-perm']){
	                    		str += '<a href="javascript:;" class="datatable-btn department-perm-btn" data-id="'+data+'" title="数据权限"><i class="fa fa-database bigger-150"></i></a>';
	                    	}
	                    	return str;
						}
					}
				]
			});
	};
	//多选下拉列表

	$('.multiselect').multiselect({
        buttonClass: 'btn btn-white btn-primary',
        buttonWidth: '100%',
        templates: {
            button: '<button type="button" class="multiselect dropdown-toggle" data-toggle="dropdown"><span class="multiselect-selected-text"></span> &nbsp;<b class="fa fa-caret-down"></b></button>',

        }
    });

	//信息编辑按钮点击事件注册
	$("body").on("click",".edit-btn",function(){
        let id = $(this).data("id");
        breadcrumb.push({
            url:$contextPath + '/user/user-edit-page #wrapper-content',
            title:'用户详情',
            callback:function() { //页面加载完成后的回调
            	$('input[name="username"],input[name="realname"]').attr("readonly","readonly");
            	$('#user-edit-form').fillArea({
                    url:$contextPath + '/user/user-detail-data',
                    data:JSON.stringify({userId:id})
                });

            }
        });
	}).on("click",'#edit-department-show',function(){ //子页面，部门选择框点击事件
		//实现方式为弹出框，定位在input下方
        let top = $(this).offset().top;
        let left = $(this).offset().left;
        let width = $(this).width();
        let scrollTop = $(document).scrollTop();
        let scrollLeft = $(document).scrollLeft();
        let _this = $(this);
		//弹出框
		let opended = layer.open({
			  type: 1,
			  title: false, //不显示 title
			  offset: [top - scrollTop, left - scrollLeft], //定位
			  closeBtn: 0, //不显示右上关闭按钮
			  shade:0.001, //遮罩透明
			  area:[width,150], //尺寸
			  id:'department-choese-layer', //防止重复弹出
			  isOutAnim :false, //不显示退出动画
			  anim:-1, //不显示出现动画
			  fixed :false, //不随屏幕滚动
			  skin:'select', //自定义皮肤
			  shadeClose: true, //点遮罩关闭
			  content: $('#department-choese-tree'),
			  success: function(layero, index){
			  		//tree
				  	//changed.jstree 点击事件
			  		$('#department-choese-tree').on("changed.jstree", function (e, data) {
						if(data.selected.length) {
							let text = data.instance.get_node(data.selected[0]).text;
							let id = data.instance.get_node(data.selected[0]).id;
							$(_this).val(text);
							$('#edit-department-hidden').val(id);
							layer.close(opended);
						}
					}).jstree({ //初始化
						'core' : {
							'data' : {
								"url" : $contextPath + "/dept/dept-tree-data",
								"dataType" : "json"
							},
							multiple :false //关闭多选
						}
					});
			  }
		});
	}).on("click","#edit-user-submit-btn",function(){ //点击提交按钮事件
		let $form = $($(this)[0].form);
		//异步提交
		$form.ajaxSubmit({
			type:'POST',
			dataType:'json',
			url:$contextPath + '/user/user-save-data',
			success:function(data,state){
				if(data.code === 'ACK'){
					parent.$.gritter.add({
	                    title: '操作成功',
	                    //text: data.message,
	                    class_name: 'gritter-success',
	                    sticky: false
	                });
					breadcrumb.pop(); //页面退回
					table.draw(false); //表格重绘 false 不翻页
				}else if(data.code === 'NACK'){
					parent.$.gritter.add({
	                    title: '操作失败',
	                    text: data.message,
	                    class_name: 'gritter-error',
	                    sticky: false
	                });
				}
			}
		});
	}).on("click",".menu-perm-btn",function(){//菜单权限管理
		let id = $(this).data("id");
        breadcrumb.push({
            url:$contextPath + '/user/user-edit-menu-perm-page #wrapper-content',
            title:'菜单权限',
			data:{userId:id},
            callback:function(response, status, xhr) { //页面加载完成后的回调
                $('#perm-data-list-tree').jstree({ //初始化
                    "checkbox" : {
                        "keep_selected_style" : false,
                        "three_state":false
                    },
                    "plugins" : [ "wholerow", "checkbox" ],
                    'core' : {
                        'data' : {
                            "url" : $contextPath+'/user/perm-menu-tree-data?userId='+id,
                            "dataType" : "json"
                        },
                        'multiple' :true //多选
                    }
                });
            }
        });
	}).on("click",'#edit-user-menu-perm-submit-btn',function(){
		let $form = $($(this)[0].form);
        let permMenuTreeSelectedData = $('#perm-data-list-tree').jstree('get_selected');
        if(permMenuTreeSelectedData == null) return ;
        let userId = $form.find('[name="userId"]').val();
        let data = {};
		data.userId = userId;
		data.menuIds = permMenuTreeSelectedData;
		$.cjax({
			url:$contextPath + '/user/user-save-menu-perm-data',
			data:JSON.stringify(data),
			success:function(data,state){
				if(data.code === 'ACK'){
					breadcrumb.pop();
				}else if(data.code === 'NACK'){
					
				}
			}
		});
	}).on("click",".button-perm-btn",function(){//按钮权限管理
        let id = $(this).data("id");
        breadcrumb.push({
            url:$contextPath + '/user/user-edit-button-perm-page #wrapper-content',
            title:'按钮权限',
            data:{userId:id},
            callback:function(response, status, xhr) { //页面加载完成后的回调
				$('#button-perm-data-list-tree').on("changed.jstree", function (e, data) {
					let menuId = data.selected[0];
					$('#button-perm-data-wrapper .button-perm-data-list-div').hide();
					let target = $('#button-perm-data-wrapper .button-perm-data-list-div[data-menu-id="'+menuId+'"]');
					if(target.length === 0){
						target = $('<div class="button-perm-data-list-div" data-menu-id="'+menuId+'"></div>');
						$.get($contextPath + '/user/perm-button-list-data',{userId:id,menuId:menuId},function(arr,state){
							for(let index = 0; index < arr.length ; index++) {
								let selected = 'checked="checked"';
								let str = '<div class="checkbox">\
												<label>\
													<input class="perm-button-checkbox ace" name="buttonId" type="checkbox" class="ace" '+(arr[index].selected ? selected : '')+' value="'+arr[index].button_id+'">\
													<span class="lbl">'+arr[index].text+'</span>\
												</label>\
											</div>';
								$(target).append(str);
							}
						});
						$('#button-perm-data-wrapper').append(target);
					}else{
						target.show();
					}
				}).jstree({ //初始化
					"checkbox" : {
						"keep_selected_style" : false
					},
					'core' : {
						'data' : {
							"url" : $contextPath+'/user/perm-button-tree-data?userId='+id,
							"dataType" : "json"
						},
						'multiple' :false //关闭多选
					}
				});
            }
        });
    }).on('click','#edit-user-button-perm-submit-btn',function () {
    	let $form = $($(this)[0].form);
        let userId = $form.find('[name="userId"]').val();
        let buttons = [];
    	$form.find('[name="buttonId"]').each(function(index,element){
            let button = {};
    		button.id = $(this).val();
    		button.selected = $(this)[0].checked;
    		buttons.push(button);
    	});
        let data = {};
		data.userId = userId;
		data.buttons = buttons;
		$.cjax({
			url:$contextPath + '/user/user-save-button-perm-data',
			data:JSON.stringify(data),
			success:function(data,state){
				if(data.code === 'ACK'){
					breadcrumb.pop();
				}else if(data.code === 'NACK'){
					
				}
			}
		});
		
    }).on("click",'.department-perm-btn',function(){//数据权限管理
        let id = $(this).data("id");
        breadcrumb.push({
            url:$contextPath + '/user/user-edit-department-perm-page #wrapper-content',
            title:'数据权限',
			data:{userId:id},
            callback:function(response, status, xhr) { //页面加载完成后的回调
				$('#perm-data-list-tree').jstree({ //初始化
					"checkbox" : {
						"keep_selected_style" : false,
						"three_state":true,
                        "cascade":"down"
					},
					"plugins" : [ "wholerow", "checkbox" ],
					'core' : {
						'data' : {
							"url" : $contextPath+'/user/perm-department-tree-data?userId='+id,
							"dataType" : "json"
						},
						'multiple' :true //多选
					}
				});
            }
        });
    }).on("click",'#edit-user-dept-perm-submit-btn',function(){
		let $form = $($(this)[0].form);
        let permMenuTreeSelectedData = $('#perm-data-list-tree').jstree('get_selected');
        if(permMenuTreeSelectedData == null) return ;
		let userId = $form.find('[name="userId"]').val();
		let data = {};
		data.userId = userId;
		data.deptIds = permMenuTreeSelectedData;
		$.cjax({
			url:$contextPath + '/user/user-save-dept-perm-data',
			data:JSON.stringify(data),
			success:function(data,state){
				if(data.code === 'ACK'){
					breadcrumb.pop();
				}else if(data.code === 'NACK'){
					
				}
			}
		});
	});
	
	

});