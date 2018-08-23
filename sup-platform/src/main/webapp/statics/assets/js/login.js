$(function() {
	'use strict';
	if(parent.document != this){  
        top.location.href = $contextPath + "/login";
	}
	var errorAlertHtml = '\
		<div class="alert alert-danger">\
			<button type="button" class="close" data-dismiss="alert">\
				<i class="ace-icon fa fa-times"></i>\
			</button>\
			<strong>\
				<i class="ace-icon fa fa-times"></i>\
				登录失败\
			</strong>\
			<span class="reason">用户名/密码 错误</span>\
			<br>\
		</div>';
	var successAlertHtml = '\
		<div class="alert alert-success">\
			<button type="button" class="close" data-dismiss="alert">\
				<i class="ace-icon fa fa-times"></i>\
			</button>\
			<strong>\
				<i class="ace-icon fa fa-check"></i>\
				登录成功\
			</strong>\
			<span class="reason">正在跳转...</span>\
			<br>\
		</div>';
	
	$("body").on("click",".submit-btn",function(){
		var _this = $(this);
		var form = $(this).closest('form');
		_this.attr('disabled',true);
		form.ajaxSubmit({
			url:$contextPath + '/login',
			type:'POST',
			dataType:'json',
			success:function(data,state){
				$('.login-box').find('.alert').remove();
				var alert = null;
				if(data.code == 'NACK'){
					_this.attr('disabled',false);
					alert = $(errorAlertHtml);
					alert.find('.reason').text(data.message);
					$('.login-box').append(alert);
					
				}else if(data.code == 'ACK'){
					alert = $(successAlertHtml);
					window.location.href= $contextPath + "/";
					$('.login-box').append(alert);
				}
			},
			error:function(){
				_this.attr('disabled',false);
				$('.login-box').append(errorAlertHtml);
				
			},
			complete:function(){
				
			}
		});
	}).on("keypress","#login-form input",function(event){
		if ( event.which == 13 ) {
			$('.submit-btn').trigger('click');
		}
	});
	
	
	 
	
});