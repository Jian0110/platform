$(function() {
	'use strict';
	
	let websocket;
	
	if(parent.document !== this){
        top.location.href = $contextPath + "/login";
	}
    /**
	 * 重置 tab 内容尺寸
     */
	let resizeTabContent = function(){
		let windowHeight = $(window).height();
		let height = $(window).height() - 91 ;
		$("#page-content-tab .tab-content .tab-pane,#page-content-tab .tab-content .tab-pane iframe.tab-content-iframe").height(height);
		let iframes = document.getElementsByTagName('iframe');
        for(let i = 0 ; i< iframes.length ;i++){
        	let elements = iframes[i].contentWindow.document.getElementsByClassName("page-content");
            for(let j = 0 ; j < elements.length ; j++){
                elements[j].style.height = $(window).height() - 91 - 37;
            }
        }

	};
	/**
	 * 加载菜单
	 */
	let loadMenu = function(){
		$.get($contextPath + '/user/getCurrentMenu',null,function(data,state){
			if(data.code === 'ACK'){
				let list = buildMenu(data.data);
				$('#menu-nav-list').append(list);
			}
		});
	};
	
	
	
	let  userName = null;
	let socketConn = function(){
		let username ;
		$.get($contextPath + '/user/user-get-username',{},function(data,state){
			userName = data.data.username;
			username = data.data.username;
			connect();
		});
		let connect = function(){
			let onopen = function(event){
				console.log("websocket onopen");
			};
			let onclose = function(event){
				console.log("websocket onclose");
			};
			let onmessage = function(event){
				console.log("websocket onmessage : " + event.data);
			};
			let onerror = function(onerror){
				console.log("websocket onerror");
			};
			if ('WebSocket' in window) {  
		        websocket = new WebSocket("ws://" + window.location.host + $contextPath + "/websocket/1?username=" + username); 
		    } else if ('MozWebSocket' in window) {  
		        websocket = new MozWebSocket("ws://" + window.location.host + $contextPath + "/websocket/1?username=" + username);  
		    } else {  
		    	$.gritter.add({
	                title: 'websocket初使化失败',
	                text: '浏览器不支持websocket',
	                class_name: 'gritter-error',
	                sticky: false
	            });  
		    } 
	        websocket.onopen = onopen;
	        websocket.onclose = onclose;
	        websocket.onmessage = onmessage;
	        websocket.onerror = onerror;
		}
		
	};

    /**
	 * 构建菜单列表
     * @param data
     * @returns {Array}
     */
	let buildMenu = function(data){
        let liList = [];
		for(let index in data){
            let li = $('<li></li>');
            let a = $('<a href="javascript:;"></a>');
            let span = $('<span class="menu-text"></span>');
			span.text(data[index].title);
			a.append(data[index].icon); 
			a.append(span);
			if(data[index].href == null || ( data[index].children && data[index].children.length > 0)){
				a.append('<b class="arrow fa fa-angle-down"></b>');
				a.addClass('dropdown-toggle');
			}else{
				a.data('href',data[index].href);
				a.data('key',data[index].menuId);
				//data-href="two-menu-1.html" data-key="two-menu-1"
			}
			li.append(a);
			
			if(data[index]['children'] && data[index]['children'].length > 0){
                let ul = $('<ul class="submenu"></ul>');
                let children = buildMenu(data[index].children);
				ul.append(children);
				li.append(ul);
			}
			liList.push(li);
		}
		return liList;
	
	};
	
	$('body').on('click','.tab-close-btn',function() {
        let id = $(this).parent('a').attr('href');
        let li = $(this).parents('li');
        let prev = li.prev().length > 0 ? li.prev() : li.next();
		$(this).parents('ul.nav-tabs').siblings('div.tab-content')
				.find(id).remove();
		li.remove();
		prev.children('a').trigger('click');
	}).on('click','.menu-nav-list a[class!="dropdown-toggle"]',function(event){
        let href = $(this).data('href');
        let key = $(this).data('key');
        let text = $(this).find('.menu-text').text();
        let length = $('#page-content-tab .tab-title-ul li a[href="#' + key + '"]').length;
		if(length > 0){
			$('#page-content-tab .tab-title-ul li a[href="#'+key+'"]').trigger('click');
			$('.menu-nav-list li.active').removeClass('active');
			$(this).parents('li').addClass('active');
		}else{
            appendTab(key,text,href);
            $('.menu-nav-list').find('li.active').removeClass('active');
            $(this).parents('li').addClass('active');
		}
	}).on('appendChildren','#page-content-tab .tab-content',function(){
		resizeTabContent();
	}).on("click",'#websocket-test-btn',function(){
		websocket.send("ok");
	}).on('click','#tip-list-ul li a',function(){
		let id = $(this).data('id');
		let key = 'tip-tab-div';
		let text = '通知中心';
		let href = $contextPath+'/user/tip-list-page?id='+id;
		let tabTitleList = $('#page-content-tab .tab-title-ul');
        let length = tabTitleList.find('li a[href="#'+key+'"]').length;
        if(length > 0) {
            tabTitleList.find('li a[href="#'+key+'"]').trigger('click');
        }else{
            appendTab(key,text,href);
		}
	}).on('click','#tip-list-all-a',function(){
        let key = 'tip-tab-div';
        let text = '通知中心';
        let href = $contextPath+'/user/tip-list-page';
        let tabTitleList = $('#page-content-tab .tab-title-ul');
        let length = tabTitleList.find('li a[href="#'+key+'"]').length;
        if(length > 0) {
            tabTitleList.find('li a[href="#'+key+'"]').trigger('click');
        }else{
            appendTab(key,text,href);
        }
	}).on('click','#user-info',function(){
		let key = 'user-info-tab-div';
        let text = '个人信息';
        let href = $contextPath+'/user/user-edit-info-page';
        let tabTitleList = $('#page-content-tab .tab-title-ul');
        let length = tabTitleList.find('li a[href="#'+key+'"]').length;
        if(length > 0) {
            tabTitleList.find('li a[href="#'+key+'"]').trigger('click');
        }else{
            appendTab(key,text,href);
        }
    	$.get($contextPath + '/user/user-edit-info-page',{},function(data,state){
    	});
	})
	

    /**
	 * 新增tab
     * @param key id
     * @param text 标题
     * @param href 链接
     */
	let appendTab = function(key,text,href){
        $('#page-content-tab .tab-title-ul')
            .find('li.active')
            .removeClass('active')
            .end()
            .append('<li class="closeable active">\
							<a data-toggle="tab" href="#'+key+'">\
								<span class="tab-title">'+text+'</span>\
								<span class="tab-close-btn"><i class="red ace-icon glyphicon glyphicon-remove"></i></span>\
							</a>\
						</li>');
        $('#page-content-tab .tab-content')
            .find('div.active')
            .removeClass('active')
            .end()
            .append('\
					<div id="'+key+'" class="tab-pane fade in active">\
						<iframe src="'+href+'" width="100%" frameborder="no" border="0" marginwidth="0" marginheight="0" allowtransparency="yes" class="tab-content-iframe" />\
					</div>')
            .trigger('appendChildren');
        $('.menu-nav-list').find('li.active').removeClass('active');
	};
	
	$(window).on('resize',function(){
		resizeTabContent();
	});
	
	resizeTabContent();
	loadMenu();

//
	
});
