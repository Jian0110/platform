$.extend($.fn.dataTable.defaults, {
    //在 dom 里面不配置 f ，可以隐藏掉默认的搜索框
    "language": {
        "url": $contextPath + "/assets/resources/zh_CN.txt"
    },
    "dom": '<"row"<"dataTables_filter col-xs-12 col-sm-10 no-padding"><"pull-right col-xs-12 col-sm-2 no-padding"B>>rt<"row"<"info-and-size"il>p>',
    "processing": true,
    "serverSide": true,
    "searching": false,
    "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "全部"]],
    "ajax": {
        'type': "POST",
        'contentType': "application/json",
        'dataType': 'json',
        'data': function (object) {
            return JSON.stringify(object);
        },
        error:function(d){
        	console.log(d.statusCode());
        }
    },
    "fnServerParams": function (aoData) {
        let data = getFormData(this.fnSettings().oInit.searchFormBlock);
        for (let x = 0; x < data.length; x++) {
            for (let key in data[x]) {
                aoData[key] = data[x][key];
            }
        }
    },

    //DT初始化完毕回调函数

    initComplete: function (settings) {
        let _$this = this;
        let block = $(_$this.selector + '_wrapper .dataTables_filter');
        if (settings.oInit.searchFormBlock) {
            let form = $(settings.oInit.searchFormBlock);
            block.append(form);
            $(_$this.selector + '_wrapper .dataTables_filter ' + settings.oInit.searchFormBlock).bind('keypress', function (e) {
                if (e.keyCode === 13) {
                    _$this.api().draw();
                }
            });
            $(_$this.selector + '_wrapper .dataTables_filter #search-btn ').bind('click', function (e) {
                _$this.api().draw();
            });
        }
    }
});


/**
 * @return {null}
 */
$.fn.SimpleDataTable = function (options) {

    let copy_button = {
        "extend": "copy",
        "text": "<i class='fa fa-copy bigger-110 pink'></i> <span class='hidden'>Copy to clipboard</span>",
        "className": "btn btn-white btn-primary btn-bold",
        "exportOptions": {
            "modifier": {
                "selected": true
            }
        }
    };
    let excel_button = {
        "extend": "excel",
        "text": "<i class='fa fa-file-excel-o bigger-110 green'></i> <span class='hidden'>Export to Excel</span>",
        "className": "btn btn-white btn-primary btn-bold",
        "action": function (e, dt, button, config) {

            let url = dt.ajax.url() + '-excel';
            let params = JSON.parse(dt.ajax.params());
            delete params['columns'];
            delete params['draw'];
            delete params['length'];
            delete params['order'];
            delete params['search'];
            delete params['start'];

            let $form = $('<form method="POST"></form>');
            $form.attr('action', url);
            for (let x in params) {
                let $input = $('<input type="hidden"/>');
                $input.attr('name', x);
                $input.val(params[x]);
                $form.append($input);
            }
            $form.appendTo("body").submit().remove();
        }
    };


    let _this = this;
    let defaults = {
        //"order": [[0, "desc"]],
        "order": [] //默认不排序
    };
    let settings = $.extend({}, defaults, options);

    if (options.url === null || options.url === '') {
        console.log('url is null');
        return null;
    }

    settings.ajax = {
        'url': options.url
    };

    if (options.select === true) {
        settings.select = {
            selector: 'td:first-child',
            style: 'multi',
            info: false
        };
        settings.columns[0].orderable = false;
        settings.columns[0].className = "center";
        settings.columns[0].width = "60px";
        settings.columns[0].render = function (data, type, row) {
            return '<label class="pos-rel">\
						<input type="checkbox" class="ace" data-id="' + data + '"/>\
						<span class="lbl"></span>\
					</label>';
        };
        $(_this).find('thead > tr > th:eq(0)').addClass('center').html('<label class="pos-rel">\
    																		<input type="checkbox" class="ace" />\
    																		<span class="lbl"></span>\
    																	</label>');


    }

    //处理按钮
    delete settings["buttons"];
    settings['buttons'] = [];


    if (options.buttons) {
        options.buttons.forEach(function(element){
            if(element['type']) {
                if (element['type'] === 'custom') {
                    let _icon = element['icon'];
                    let _text = element['text'];
                    let _action = element['action'];
                    let button = {
                        "text": "<i class='fa bigger-110 " + _icon + "' title='" + _text + "'></i> <span class='hidden'>" + _text + "</span>",
                        "className": "btn btn-white btn-primary btn-bold"
                    };
                    if (_action) {
                        button.action = _action;
                    }
                    settings['buttons'].push(button);
                } else if (element['type'] === 'excel' && element['enable'] === true) {
                    settings['buttons'].push(excel_button);
                } else if (element['type'] === 'copy' && element['enable'] === true) {
                    settings['buttons'].push(copy_button);
                }
            }
        });

    }


    let dataTable = $(_this).DataTable(settings);

    if (options.select === true) {
        dataTable.on('select', function (e, dt, type, index) {
            if (type === 'row') {
                $(dataTable.row(index).node()).find('input:checkbox').prop('checked', true);
            }
        });
        dataTable.on('deselect', function (e, dt, type, index) {
            if (type === 'row') {
                $(dataTable.row(index).node()).find('input:checkbox').prop('checked', false);
            }
        });

        //select/deselect all rows according to table header checkbox
        $(_this).find('thead > tr > th input[type=checkbox]:eq(0)').on('click', function () {
            let th_checked = this.checked;//checkbox inside "TH" table header

            $(_this).find('tbody > tr').each(function () {
                let row = this;
                if (th_checked) dataTable.row(row).select();
                else dataTable.row(row).deselect();
            });
        });

        //select/deselect a row when the checkbox is checked/unchecked
        $(_this).on('click', 'td input[type=checkbox]', function () {
            let row = $(this).closest('tr').get(0);

            if ($(this).is(':checked')) {
                dataTable.row(row).deselect();
                $(_this).find('thead > tr > th input[type=checkbox]:eq(0)').prop('checked', false);
            } else {
                dataTable.row(row).select();
            }
        });
    }


    return dataTable;
};

/**
 * 取得搜索区域的表单值
 * @param form_id
 * @returns
 */
function getFormData(form_id) {
    let result = [];
    $(form_id).find('input,textarea,select').each(function () {
        let key = $(this).attr('name');
        let obj = {};
        if (key) {
            if ($(this).attr("Type") === "checkbox") {
                obj[key] = $(this).prop("checked");
            } else if ($(this).attr("Type") === "radio") {
                if ($(this).prop("checked")) {
                    obj[key] = $(this).val();
                } else {
                    return;
                }
            } else {
                obj[key] = $(this).val();
            }
            result.push(obj);
        }
    });
    return result;
}

/*
(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
*/
Date.prototype.Format = function (fmt) {
    //author: meizz
    let o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (let k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};
