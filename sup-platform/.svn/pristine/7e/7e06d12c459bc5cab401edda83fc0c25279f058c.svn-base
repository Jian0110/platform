
/**
 * 基本信息自定义验证
 */

$(function(){
	//手机号码验证
	jQuery.validator.addMethod("isMobile", function(value, element) {  
	    var length = value.length;  
	    //var regPhone = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;
	    var regPhone = /^1[0-9]{10}$/;
	    return this.optional(element) || ( length == 11 && regPhone.test( value ) );
	}, "请正确填写您的手机号码");  
	
    // 身份证号码验证 
    jQuery.validator.addMethod("isIdCardNo", function(value, element) { 
        return this.optional(element) || empInfoUtil.checkIdCardNo(value);     
    }, "请正确输入您的身份证号码"); 	
    
    //员工编码验证
   /* jQuery.validator.addMethod("checkCode",function(value, element){
       //var length = value.length;
 	   return this.optional(element)//||length == 11;//||empInfoUtil.checkCode(value);
 	},"员工编码格式错误");*/
    //银行卡号验证
    jQuery.validator.addMethod("checkBankNo",function(value, element){
 	   return this.optional(element)||empInfoUtil.checkBankNo(value);
 	},"银行卡号格式错误");
});