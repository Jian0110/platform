$(function() {
	
	//验证密码
	let validateOptions = {
			errorElement: 'div',
			errorClass: 'help-block',
			focusInvalid: false,
			ignore: "",
			rules: {
				password: {
				 	rangelength:[6,12]
				},
			},
			messages: {
				password: {
					rangelength: "密码长度为6-12位"
				},
			},
			highlight: function (e) {
				$(e).closest(".form-group").removeClass('has-info').addClass('has-error');
			},
			success: function (e) {
				$(e).closest('.form-group').removeClass('has-error');
				$(e).remove();
			},

			errorPlacement: function (error, element) {
				error.insertAfter(element.parent());
			},
			submitHandler: function (form) {
			},
			invalidHandler: function (form) {
			}
		};
	
	
	//初始化validate
	$('#user-info-edit-form').validate(validateOptions);
	
	$('body').on('click','#image',function(){
		//上传头像
		$('#user-image-input').trigger('click');
	}).on('change','#user-image-input',function(){
		//var MAX_WIDTH = 200;
		//var MAX_HEIGHT = 200;
		var MAX_SIZE = 20*1024; //MAX_SIZE*2014 = 2M
		let file = $(this)[0].files[0];	
		//检测文件类型是否为图片
		if(!/image\/\w+/.test(file.type)){
			parent.$.gritter.add({
                title: '请选择图片类型',
                class_name: 'gritter-error',
                sticky: false
            });
		    return false;  
		}
		var size  = (file.size)/1024;
    	//验证图片大小
    	if(size > MAX_SIZE){
    		parent.$.gritter.add({
    			title: '请选择图像小于2M的图片',
    			class_name: 'gritter-error',
    			sticky: false
    		});
    		return false;
    	}
		let fileReader = new FileReader();
			fileReader.onload = function(e){
				$('#avatar-base64-input').val(fileReader.result);
	        	$('#image').prop('src',fileReader.result);
	    };
        //将文件读取为DataURL
		fileReader.readAsDataURL(file); 
	}).on('click','#edit-user-submit-btn',function(){
		let $form = $('#user-info-edit-form');
		let result = $form.valid();
		if(!result) return;
		var userId = $form.find('[name="userId"]').val();
		var password = $form.find('[name="password"]').val();
		var image = $form.find('[name="image"]').val();
		var default_password = $('#default_password').val();
		var default_image = $('#default_image').val();
		
		//判断密码为空（头像不会为空，有默认的）
		let isPassChange = (password == '' || password == null);
		let isImageChange = (image == default_image);
		//密码为空头像未改变，实则是没有改变，不需要ajax
		if(isPassChange && isImageChange){
			parent.$.gritter.add({
                title: '无更改操作',
                //text: data.message,
                class_name: 'gritter-error',
                sticky: false
            });
		}else{
			if(image == '') image = null //null处理
			if(isPassChange) password = default_password; //密码为空则置为默认
			let data = {}
			data['password'] = password;
			data['image'] = image;
			data['userId'] = userId;
			$.cjax({
				url:$contextPath + '/user/user-info-save-data',
				data:JSON.stringify(data),
				success:function(data,state){
					if(data.code === 'ACK'){
						parent.$.gritter.add({
		                    title: '保存成功',
		                    //text: data.message,
		                    class_name: 'gritter-success',
		                    sticky: false
		                });
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
		}
	})
});