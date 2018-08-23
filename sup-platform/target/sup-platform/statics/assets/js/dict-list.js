$(function() {
	
	//获取按钮权限许可，完成后再进行表格初使化
	let perms = ['dict-list:edit','dict-list:new'];
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
	
	//菜单权限已选择，如果未改变，该变量不会有数据
	let permMenuTreeSelectedData = null;
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
				"icon":"fa-plus blue",
				"action":function(e, dt, button, config){
                    breadcrumb.push({
                        url:$contextPath + '/dict/dict-edit-page #wrapper-content',
                        title:'新增字典',
                        callback:function() {
							//填充组名选择下拉列表(edit-page转向group-list-data获取下拉列表)
                        	$.get($contextPath + '/dict/dict-group-list-data',{},function(data){
                        		let firstGroupName = data[0].groupName;
                        		$('#groupName').val(firstGroupName);
                        		for( let i= 0 ; i<data.length; i++){
                        				 $('#edit-groupCode').append('<option value="'+data[i].groupCode+'">'+data[i].groupName+'</option>');
                                }
                        		 //获取隐藏的groupName
                        		 $('#edit-groupCode').on("change",function(){
                        			 let groupName = $(this).find("option:selected").text();
                        			 $('#groupName').val(groupName);
                        		 });
                            });
                        }
                    });
                     
				}
		};
		
		let copy = {'type':'copy','enable':false};
	    if(perms['dict-list:new']){
			buttons.push(add);
		}
	    buttons.push(copy);
	table = $('#dynamic-table')
			.SimpleDataTable(
					{
						'url' : $contextPath + '/dict/dict-page-data',
						'searchFormBlock' : '#dict-search-form-block',
						"select" : true,//复选框开启
						//"order" : [ [ 1, "desc" ] ],
						//"excel": false,//关闭excel导出
						//"copy": false,//关闭copy
						"buttons" : buttons,
						"columns" : [
								{"data" : "dictId"},
								{"data" : "groupName","name" : "group_name"},
								{"data" : "groupCode", "name" : "group_code"},
								{"data" : "chooseName", "name" : "choose_name"},
								
								{
                                    "data" : "dictId",
                                    "bSortable" : false,
									"render" : function(data, type, row) {
										let str = '';
										if(perms['dict-list:edit']){
											
											str += '<a href="javascript:;" class="datatable-btn edit-btn" data-id="'+data+'" title="编辑"><i class="fa fa-edit bigger-150"></i></a>';
										}
										return str;
									}
								}
						]
						
					});
	};
	
	$('.multiselect').multiselect({
        buttonClass: 'btn btn-white btn-primary',
        buttonWidth: '100%',
        templates: {
            button: '<button type="button" class="multiselect dropdown-toggle" data-toggle="dropdown"><span class="multiselect-selected-text"></span> &nbsp;<b class="fa fa-caret-down"></b></button>',

        }
    });

	$("body").on("click",".edit-btn",function(){
		let id = $(this).data("id");
        breadcrumb.push({
            url:$contextPath + '/dict/dict-edit-page #wrapper-content',
            title:'字典编辑',
            callback:function() {
                $.get($contextPath + '/dict/dict-group-list-data',{dictId:id},function(data,state){
                	$('#edit-groupCode').append('<option value="'+data[0].groupCode+'">'+data[0].groupName+'</option>');
                    $('#dict-edit-form').fillArea({
                        url:$contextPath + '/dict/dict-detail-data',
                        data:JSON.stringify({dictId:id}),
                    });
                });
            }
        });
	}).on("click","#edit-dict-submit-btn",function(){
		let $form = $($(this)[0].form);
		$form.ajaxSubmit({
			type:'POST',
			dataType:'json',
			url:$contextPath + '/dict/dict-save-data',
			success:function(data,state){
				if(data.code === 'ACK'){
					parent.$.gritter.add({
	                    title: '操作成功',
	                    class_name: 'gritter-success',
	                    sticky: false
	                });
					breadcrumb.pop();
					table.draw(false);
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
		
	});
	
	

});