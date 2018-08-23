var empInfoUtil = {
		
	
	/*籍贯下拉框动态加载
	 * t:$select
	 * id:省编码/市编码
	 * sid1:市编码
	 * sid2:县编码
	 */
	fillAndSelectOptions: function(t,id,sid1,sid2){
		//第二次渲染县区下拉选择框
		var $select =  $('#edit-birthplace-area').find('option[value="'+sid2+'"]');
		$select.attr('selected','selected');
		$select.trigger("chosen:updated").change();
		$.get($contextPath + '/area/sub-list',{id:id},function(data,state){
			t.children().remove();
			$.each(data,function(i,o){
				t.append('<option value="'+o.areaId+'">'+o.text+'</option>')
			});
			t.find('option[value='+sid1+']').attr('selected','selected');
			t.chosen().trigger("chosen:updated");
		});
		
	},
	/**
	 * 15位身份证号码：第15位代表性别，奇数为男，偶数为女。 
	 * 18位身份证号码：第17位代表性别，奇数为男，偶数为女。
	 */
	getGenderByNoid:function(idCard){
			if(idCard.length == 15){
				let genderNum = idCard.substring(13,14);
				if(genderNum % 2 != 0){
					return 'M'
				}
				return 'F';
			}
			else if(idCard.length == 18){
				let genderNum = idCard.substring(15,16);
				if(genderNum % 2 != 0){
					return 'M'
				}
				return 'F';
			}
	},
	
	/*
	 *验证身份证正确之后填充数据 
	 */
	fillAreaByNoid:function($element){
			let area = [];
			let birthday = [];
			let key = $element.data('name');
			let value = $element.val();
			let gender = empInfoUtil.getGenderByNoid(value);
			//省市县编码存储
			area.push(value.substring(0,2));
			area.push(value.substring(0,4));
			area.push(value.substring(0,6));
			//年月日生日存储
			birthday.push(value.substring(6,10));
			birthday.push(value.substring(10,12));
			birthday.push(value.substring(12,14));
			
			$('#edit-birthplace-province option[value='+area[0]+']').attr("selected","selected");
			$('#edit-birthplace-province').trigger("chosen:updated");
			empInfoUtil.fillAndSelectOptions($('#edit-birthplace-city'),area[0],area[1],area[2]);
			empInfoUtil.fillAndSelectOptions($('#edit-birthplace-area'),area[1],area[2],area[2]);
			if(gender === 'M'){
				$("#edit-gender option[value='男']").attr('selected','selected');
			}else{
				$("#edit-gender option[value='女']").attr('selected','selected');
			}
			
			let birth = birthday.join('-');
			let myDate = new Date();
			let year = myDate.getFullYear();
			let month = myDate.getMonth()+1;
			let day = myDate.getDate();
			currentDate=year+(month<10 ? "0" + month : month)+(day<10 ? "0"+ day : day)
			let age = ''+(currentDate-birthday.join(''));
			$('#age').val(age.substring(0,2));
			$("#edit-birthdate").datepicker('update', birth);
	},
	
	
	
	//子公司编码
	companys:{JH:"嘉华",JM:"佳禾",BW:"饼屋",XH:"鲜花饼",ZZ:"种植",SM:"思茅"},
	//派遣公司编码
	dispatchCompanys: {LL:"劳联"},
	
	//检查是否为数字
	isNumberic:function(value){
		var ival = parseInt(value);//如果变量val是字符类型的数则转换为int类型 如果不是则ival为NaN
		if(!isNaN(ival)){// isNaN()函数，如果传入的参数是数字返回false,否则返回true
       		return true;
	    }else{
	       return false;
	    }
	},
	
	//检查日期日期部分
	checkDateStr:function(dateStr){
		//日期格式：长度为6
		if(dateStr.length !=6)
			return false;
		//日期必须为数字
		if (!empInfoUtil.isNumberic(dateStr)) 
			return false;
		var year = parseInt(dateStr.substring(0,2));
		var month = parseInt(dateStr.substring(2,4));
		var days = parseInt(dateStr.substring(4,6));
		if((month>12 || month<1) || (days<1 || days>31)){
			return false;
		}
		return true;
	},
	
	//检查大写字母前缀
	checkPreStr:function(preStr){
		if (preStr.length != 2 && preStr.length !=4)
			return false;
		var strArray = {};//派遣公司与分子公司的编码组合
		$.each(empInfoUtil.dispatchCompanys,function(key1,value1){
			$.each(empInfoUtil.companys,function(key2,value2){
				strArray[key1+key2]=value1+value2;
			})
		});
		if(!empInfoUtil.companys.hasOwnProperty(preStr) && !strArray.hasOwnProperty(preStr))
			return false;
		return true;
	},
	
	//检查入职顺序后缀
	checkPostStr:function(postStr){
		if (postStr.length != 2) 
			return false;
		return true;	
	},
	
	//分割员工编码
	spiltCode:function(codeStr,spiltStr){
		
		if (codeStr.length != 10 && codeStr.length != 12) {
			return false;
		}
		if (codeStr.length === 10) {
			spiltStr.push(codeStr.substring(0,2));
			spiltStr.push(codeStr.substring(2,8));
			spiltStr.push(codeStr.substring(8,10));
		}
		else if (codeStr.length == 12) {
			spiltStr.push(codeStr.substring(0,4));
			spiltStr.push(codeStr.substring(4,10));
			spiltStr.push(codeStr.substring(10,12));
		}	
		return true;
	},
	/**
	 * 验证员工编码
	 */
	//主函数：检查员工编码
	checkCode:function(code){
		//分割员工编码
		var spiltStr = [];
		var flag = empInfoUtil.spiltCode(code,spiltStr);
		if(!flag)			
			return false;
		var pre = empInfoUtil.checkPreStr(spiltStr[0]);
		var date = empInfoUtil.checkDateStr(spiltStr[1]);
		var post =  empInfoUtil.checkPostStr(spiltStr[2]);
		if(pre && date && post){
			return true;
		}
		return false;
	},
	
	
	provinceAndCitys: {11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",
		31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",
		45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",
		65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"},

	powers: ["7","9","10","5","8","4","2","1","6","3","7","9","10","5","8","4","2"],

	parityBit: ["1","0","X","9","8","7","6","5","4","3","2"],

	genders: {male:"男",female:"女"},

	checkAddressCode: function(addressCode){
		var check = /^[1-9]\d{5}$/.test(addressCode);
		if(!check) return false;
		if(empInfoUtil.provinceAndCitys[parseInt(addressCode.substring(0,2))]){
			return true;
		}else{
			return false;
		}
	},
	
	checkBirthDayCode: function(birDayCode){
		var check = /^[1-9]\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))$/.test(birDayCode);
		if(!check) return false;
		var yyyy = parseInt(birDayCode.substring(0,4),10);
		var mm = parseInt(birDayCode.substring(4,6),10);
		var dd = parseInt(birDayCode.substring(6),10);
		var xdata = new Date(yyyy,mm-1,dd);
		if(xdata > new Date()){
			return false;//生日不能大于当前日期
		}else if ( ( xdata.getFullYear() == yyyy ) && ( xdata.getMonth () == mm - 1 ) && ( xdata.getDate() == dd ) ){
			return true;
		}else{
			return false;
		}
	},

	getParityBit: function(idCardNo){
		var id17 = idCardNo.substring(0,17);
		var power = 0;
		for(var i=0;i<17;i++){
			power += parseInt(id17.charAt(i),10) * parseInt(empInfoUtil.powers[i]);
		}
		var mod = power % 11;
		return empInfoUtil.parityBit[mod];
	},

	checkParityBit: function(idCardNo){
		var parityBit = idCardNo.charAt(17).toUpperCase();
		if(empInfoUtil.getParityBit(idCardNo) == parityBit){
			return true;
		}else{
			return false;
		}
	},
	/**
	 * 验证身份证号码（15/18通用）
	 * 
	 */
	checkIdCardNo: function(idCardNo){
		//15位和18位身份证号码的基本校验
		var check = /^\d{15}|(\d{17}(\d|x|X))$/.test(idCardNo);
		if(!check) return false;
		
		//判断长度为15位或18位
		if(idCardNo.length==15){
			return empInfoUtil.check15IdCardNo(idCardNo);
		}else if(idCardNo.length==18){
			return empInfoUtil.check18IdCardNo(idCardNo);
		}else{
			return false;
		}
	},

	//校验15位的身份证号码
	check15IdCardNo: function(idCardNo){
		//15位身份证号码的基本校验
		var check = /^[1-9]\d{7}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))\d{3}$/.test(idCardNo);
		if(!check) return false;
		//校验地址码
		var addressCode = idCardNo.substring(0,6);
		check = empInfoUtil.checkAddressCode(addressCode);
		if(!check) return false;
		var birDayCode = '19' + idCardNo.substring(6,12);
		//校验日期码
		return empInfoUtil.checkBirthDayCode(birDayCode);
	},

	//校验18位的身份证号码
	check18IdCardNo: function(idCardNo){
		//18位身份证号码的基本格式校验
		var check = /^[1-9]\d{5}[1-9]\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))\d{3}(\d|x|X)$/.test(idCardNo);
		if(!check) return false;
		
		//校验地址码
		var addressCode = idCardNo.substring(0,6);
		check = empInfoUtil.checkAddressCode(addressCode);
		if(!check) return false;
		
		//校验日期码
		var birDayCode = idCardNo.substring(6,14);
		check = empInfoUtil.checkBirthDayCode(birDayCode);
		if(!check) return false;
		
		//验证校检码
		return empInfoUtil.checkParityBit(idCardNo);
	},
	formateDateCN: function(day){
		var yyyy =day.substring(0,4);
		var mm = day.substring(4,6);
		var dd = day.substring(6);
		return yyyy + '-' + mm +'-' + dd;
	},
	//获取信息
	getIdCardInfo: function(idCardNo){
		var idCardInfo = {
			gender:"", //性别
			birthday:"" // 出生日期(yyyy-mm-dd)
		};
		if(idCardNo.length==15){
			var aday = '19' + idCardNo.substring(6,12);
			
			idCardInfo.birthday=empInfoUtil.formateDateCN(aday);
			
			if(parseInt(idCardNo.charAt(14))%2==0){
				idCardInfo.gender=empInfoUtil.genders.female;
			}else{
				idCardInfo.gender=empInfoUtil.genders.male;
			}
		}else if(idCardNo.length==18){
			var aday = idCardNo.substring(6,14);
			
			idCardInfo.birthday = empInfoUtil.formateDateCN(aday);
			
			if(parseInt(idCardNo.charAt(16))%2==0){
				idCardInfo.gender = empInfoUtil.genders.female;
			}else{
				idCardInfo.gender = empInfoUtil.genders.male;
			}
		}
		return idCardInfo;
	},

	getId15:function(idCardNo){
		if(idCardNo.length==15){
			return idCardNo;
		}else if(idCardNo.length==18){
			return idCardNo.substring(0,6) + idCardNo.substring(8,17);
		}else{
			return null;
		}
	},

	getId18: function(idCardNo){
		if(idCardNo.length==15){
			var id17 = idCardNo.substring(0,6) + '19' + idCardNo.substring(6);
			var parityBit = empInfoUtil.getParityBit(id17);
			return id17 + parityBit;
		}else if(idCardNo.length==18){
			return idCardNo;
		}else{
			return null;
		}
	},
	/*
	 *银行卡号长度等格式验证 
	 */
	checkBankFormat:function(bankno){
	   //长度16或者19位
	   if(bankno.length < 16 || bankno.length > 19) {
	     return false;
	   }
	   //必须全为数字
	   var num = /^\d*$/; //全数字
	   if(!num.exec(bankno)) {
	     return false;
	   }
	   //开头6位要符合规则
	   var strBin = "10,18,30,35,37,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,58,60,62,65,68,69,84,87,88,94,95,98,99";
	   if(strBin.indexOf(bankno.substring(0, 2)) == -1) {
	     return false;
	   }
	   return true;
	},
	
    /* Luhm校验银行卡号规则：16位银行卡号（19位通用）:
     * 1.将未带校验位的 15（或18）位卡号从右依次编号 1 到 15（18），位于奇数位号上的数字乘以 2。
       2.将奇位乘积的个十位全部相加，再加上所有偶数位上的数字。
       3.将加法和加上校验位能被 10 整除。
	*/
    luhmCheck: function(bankno){
        var lastNum=bankno.substr(bankno.length-1,1);//取出最后一位（与luhm进行比较）

        var first15Num=bankno.substr(0,bankno.length-1);//前15或18位
        var newArr=new Array();
        for(var i=first15Num.length-1;i>-1;i--){    //前15或18位倒序存进数组
            newArr.push(first15Num.substr(i,1));
        }
        var arrJiShu=new Array();  //奇数位*2的积 <9
        var arrJiShu2=new Array(); //奇数位*2的积 >9

        var arrOuShu=new Array();  //偶数位数组
        for(var j=0;j<newArr.length;j++){
            if((j+1)%2==1){//奇数位
                if(parseInt(newArr[j])*2<9)
                arrJiShu.push(parseInt(newArr[j])*2);
                else
                arrJiShu2.push(parseInt(newArr[j])*2);
            }
            else //偶数位
            arrOuShu.push(newArr[j]);
        }

        var jishu_child1=new Array();//奇数位*2 >9 的分割之后的数组个位数
        var jishu_child2=new Array();//奇数位*2 >9 的分割之后的数组十位数
        for(var h=0;h<arrJiShu2.length;h++){
            jishu_child1.push(parseInt(arrJiShu2[h])%10);
            jishu_child2.push(parseInt(arrJiShu2[h])/10);
        }        

        var sumJiShu=0; //奇数位*2 < 9 的数组之和
        var sumOuShu=0; //偶数位数组之和
        var sumJiShuChild1=0; //奇数位*2 >9 的分割之后的数组个位数之和
        var sumJiShuChild2=0; //奇数位*2 >9 的分割之后的数组十位数之和
        var sumTotal=0;
        for(var m=0;m<arrJiShu.length;m++){
            sumJiShu=sumJiShu+parseInt(arrJiShu[m]);
        }

        for(var n=0;n<arrOuShu.length;n++){
            sumOuShu=sumOuShu+parseInt(arrOuShu[n]);
        }

        for(var p=0;p<jishu_child1.length;p++){
            sumJiShuChild1=sumJiShuChild1+parseInt(jishu_child1[p]);
            sumJiShuChild2=sumJiShuChild2+parseInt(jishu_child2[p]);
        }      
        //计算总和
        sumTotal=parseInt(sumJiShu)+parseInt(sumOuShu)+parseInt(sumJiShuChild1)+parseInt(sumJiShuChild2);

        //计算Luhm值
        var k= parseInt(sumTotal)%10==0?10:parseInt(sumTotal)%10;        
        var luhm= 10-k;

        if(lastNum==luhm)
        	return true;
        else
        	return false;
    },
    /**
     * 验证银行卡号主函数:
     * 	1.满足基本格式
     * 	2.满足Luhm校验
     */
	checkBankNo: function(bankNo){
		if(empInfoUtil.checkBankFormat(bankNo) && empInfoUtil.luhmCheck(bankNo))
			return true;
		return false;
	}
};
