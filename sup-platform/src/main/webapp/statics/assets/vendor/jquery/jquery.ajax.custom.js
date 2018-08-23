(function($){
    //首先备份下jquery的ajax方法
    var _ajax=$.ajax;

    //重写jquery的ajax方法
    $.cjax=function(options){
        //备份opt中error和success方法
        var defaults = {
            error:function(XMLHttpRequest, textStatus, errorThrown){},
            success:function(data, textStatus){},
            beforeSend:function(XHR){},
            complete:function(XHR, TS){},
            dataType:'json',
            contentType:'application/json; charset=utf-8',
            type:'POST'
        }

        var settings = $.extend({},defaults, options);
        var fn = {
            error: settings.error,
            success:settings.success,
            beforeSend:settings.beforeSend,
            complete:settings.complete
        };

        var layerIndex = 0;
        settings.error = function(response, textStatus, errorThrown){
            var doc = $.parseXML(response.responseText.replace("<!doctype html>",''));
            var body = $(doc).find("body").html();
            parent.$.gritter.add({
                title: '操作失败',
                text: body || '',
                //gritter-info gritter-center gritter-success
                class_name: 'gritter-error',
                sticky: true
                //image: 'assets/images/avatars/avatar3.png'
            });
            fn.error(XMLHttpRequest, textStatus, errorThrown);
        };
        settings.success = function(data, textStatus){
            if(data.code == 'ACK') {
                parent.$.gritter.add({
                    title: '操作成功',
                    //text: 'Just add a "gritter-light" class_name to your $.gritter.add or globally to $.gritter.options.class_name',
                    //gritter-info gritter-center gritter-success
                    class_name: 'gritter-success',
                    sticky: false
                    //image: 'assets/images/avatars/avatar3.png'
                });
            } else if(data.code == 'NACK') {
            	var message = data.message || '';
                parent.$.gritter.add({
                    title: '操作失败',
                    text: message,
                    //gritter-info gritter-center gritter-success
                    class_name: 'gritter-error',
                    sticky: false
                    //image: 'assets/images/avatars/avatar3.png'
                });
            }
            fn.success(data, textStatus);
        };
        settings.beforeSend = function(XHR){
            //提交前回调方法
            layerIndex = parent.layer.load(1,{shade: [0.5,'#000']});
            fn.beforeSend(XHR);
        };
        settings.complete = function(XHR, TS){
            parent.layer.close(layerIndex);
            layerIndex = 0;
            fn.complete(XHR, TS);
        };
        return _ajax(settings);
    };
})(jQuery);