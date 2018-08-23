$.fn.fillArea = function(options){

        var defaults = {
            url:'',
            type:'POST',
            callback:null,
            execute:null,
            data:{}
        };
        var settings = $.extend(defaults, options);

        var thisFrom = this;

        if(settings.execute && typeof(settings.execute) == 'function'){
            settings.execute(form,loading,function(data,thisFrom){
                for(var key in data){
                    thisFrom.find("[name='"+key+"']").each(function(){
                        var tagName = $(this)[0].tagName.toUpperCase();
                        if(tagName == 'INPUT' || tagName == 'SELECT' || tagName == 'TEXTAREA' ){
                            $(this).val(data[key]);
                        }else{
                            $(this).text(data[key]);
                        }
                    });
                }
            });
        }else{
            $.cjax({
                url:settings.url,
                dataType:'json',
                type:settings.type,
                data:settings.data,
                success:function(data,status){
                	var _data = data.data;
                	
                    for(var key in _data){
                        thisFrom.find("[name='"+key+"']").each(function(){
                            var tagName = $(this)[0].tagName.toUpperCase();
                            var val = _data[key];
                            if(typeof val === 'boolean'){
                                val = String(val);
                            }
                            
                            if(tagName === 'INPUT' || tagName === 'SELECT' || tagName === 'TEXTAREA' ){
                                $(this).val(val);
                            }else{
                                $(this).text(val);
                            }
                        });
                    }
                    if(settings.callback && typeof(settings.callback) == 'function' ){
                        settings.callback(data);
                    }
                },error:function(data){
                    if(settings.callback && typeof(settings.callback) == 'function' ){
                        settings.callback(data);
                    }
                }
            });
        }

    }