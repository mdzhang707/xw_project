/*
 * VARIABLES
 * Description: All Global Vars
 */
$.anchor_nav = $('nav ul');
$.root_ = $('body');
$.throttle_delay = 350;
$.menu_speed = 250;
$.left_panel = $('#left-panel');
$.page_content = $('#main');
$.navigation = $('nav');
$.shortcut_dropdown = $('#shortcut');

var DicConstants;

$(function () {
   // loadDicConstants();
});

function addCookie(name, value) {
    var days = 90;
    var exp = new Date();
    exp.setTime(exp.getTime() + days * 24 * 60 * 60 * 1000);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}

function getCookie(name) {
    var strCookie = document.cookie;
    var arrCookie = strCookie.split("; ");
    for (var i = 0; i < arrCookie.length; i++) {
        var arr = arrCookie[i].split("=");
        if (arr[0] == name) return arr[1];
    }
    return "";
}

function deleteCookie(name) {
    var date = new Date();
    date.setTime(date.getTime() - 100000);
    document.cookie = name + "=; expire=" + date.toGMTString();
}

function loadDicConstants() {
    $.ajax({
        async: false,
        type: 'POST',
        url: '/Home/DicConstants',                //TODO
        contentType: 'application/json, charset=utf-8',
        success: function (data) {
            DicConstants = data;
        }
    });
}

function MapText(code, value) {
    if (!DicConstants) {
        loadDicConstants();
    }
    var result;
    $.each(DicConstants[code], function (index, data) {
        if (data["Value"] == value || data["Value"] == '' + value) {
            result = data.Text;
        }
    });
    return result === undefined ? '' : result;
}
function MapValue(code, text) {
    if (!DicConstants) {
        loadDicConstants();
    }
    var result;
    $.each(DicConstants[code], function (index, data) {
        if (data["Text"] == text || data["Text"] == '' + text) {
            result = data.Value;
        }
    });
    return result === undefined ? '' : result;
}

var extendValidateOption = {
    highlight: function (element) {
        $(element).closest('.form-group').addClass('has-error');
    },
    unhighlight: function (element) {
        var parent = $(element).closest('.form-group');
        var l = parent.find('.error').not(element).length;
        if (l == 0) {
            parent.removeClass('has-error');
        }
    },
    showErrors: function (errorMap, errorList) {

        var getParent = function (element) {
            if ($(element).hasClass("select2") && (element ? element.type : "") === "select-one") {
                element = $(element).parent().find(">.select2-container")[0];
            }
            return element;
        };

        // Clean up any tooltips for valid elements
        $.each(this.validElements(), function (index, element) {
            var $element = $(getParent(element));

            $element.data("title", "") // Clear the title - there is no error associated anymore
                .removeClass("error")
            .tooltip("destroy");

            $element.parent().find('.tooltip').each(function () {
                $(this).remove();
            });
        });

        // Create new tooltips for invalid elements
        $.each(errorList, function (index, error) {
            var $element = $(getParent(error.element));

            $element.parent().find('.tooltip').each(function () {
                $(this).remove();
            });
            $element.tooltip("destroy").data("title", error.message)
                .addClass("error")
                .tooltip({ placement: 'bottom' }); // Create a new tooltip based on the error messsage we just set in the title
        });

        var i, elements;
        for (i = 0; this.errorList[i]; i++) {
            var error = this.errorList[i];
            if (this.settings.highlight) {
                this.settings.highlight.call(this, getParent(error.element), this.settings.errorClass, this.settings.validClass);
            }
        }
        if (this.errorList.length) {
            this.toShow = this.toShow.add(this.containers);
        }
        if (this.settings.success) {
            for (i = 0; this.successList[i]; i++) {
                this.showLabel(this.successList[i]);
            }
        }
        if (this.settings.unhighlight) {
            for (i = 0, elements = this.validElements() ; getParent(elements[i]) ; i++) {
                this.settings.unhighlight.call(this, getParent(elements[i]), this.settings.errorClass, this.settings.validClass);
            }
        }
        this.toHide = this.toHide.not(this.toShow);
        this.hideErrors();
        this.addWrapper(this.toShow).show();
    }

};

/*
 *将一个json集合，映射为一个下拉框的html
 */
function loadSelect2Data(array, value, text) {
    if (!array || array.length < 1) {
        return "";
    }
    var options = '';
    var hash = {};
    for (var i = 0, elem; i < array.length; i++) {
        elem = array[i][value];
        if (!hash[elem]) {
            options += '<option value="' + array[i][value] + '">' + array[i][text] + '</option>';
            hash[elem] = true;
        }
    }
    return options;
}

function ViewDialog(title, html, width, height) {

    width = !width ? 600 : width;
    height = !height ? 500 : height;

    $('#_commondialog_').remove();

    $.root_.append("<div id='_commondialog_' title='" + title + "'><div class='row' style='padding: 2px 15px 2px;'>" + html + "</div></div>");
    _ViewDialog(title, width, height);
}

function _ViewDialog(title, width, height) {

    $('#_commondialog_').dialog({
        autoOpen: false,
        width: width,
        height: height,
        resizable: true,
        modal: true,
        close: function (event, ui) { $('#_commondialog_').remove(); },
        title: "<div class='widget-header'><h4> " + title + " </h4></div>",
        buttons: [{
            html: "<i class='fa fa-check'></i>&nbsp; 确定",
            "class": "btn btn-primary",
            click: function () {
                $(this).dialog("close");
            }
        }]
    });
    $('#_commondialog_').dialog('open');
}

function getCheckboxValues($chs) {
    var portfs;
    portfs = new Array();
    $chs.find('input[type="checkbox"]').each(function () {

        if (this.checked == true && $(this).val() != 'all') {
            portfs.push($(this).val());
        }
    });
    return portfs.join(',');
}

function queryradioValue(group) {
    var chk = group.find("input:radio:checked").first(0);
    if (chk) {
        return chk.attr("value");
    }
    return "";
}

function initCheckbox() {
    //click all
    $(".filter-all").click(function () {
        $line = $(this).parent().parent();
        if (this.checked == true) {
            $line.find("input").prop('checked', true);
            //$line.find(".filter-others").prop('checked', false);
        } else {
            $line.find("input").prop('checked', false);
        }
    });
    //click others
    $(".filter-others").click(function () {
        $line = $(this).parent().parent();
        if (this.checked == true) {
            SetCheckAll($line);
            //$line.find(".filter-others").prop('checked', false);
        } else {
            $line.find(".filter-all").prop('checked', false);
            $(this).prop('checked', false);
        }
    });
    //click normal
    $(".filter-normal").click(function () {
        $line = $(this).parent().parent();
        if (this.checked == true) {
            SetCheckAll($line);
            //$line.find(".filter-others").prop('checked', false);
        } else {
            $line.find(".filter-all").prop('checked', false);
            $(this).prop('checked', false);
        }
    });
}

function SetCheckAll($line) {
    var i = 0;
    var j = 0;
    $line.find(".filter-normal").each(function (e) {
        if (this.checked) {
            i++;
        } else {
            j++;
        }

    });
    $line.find(".filter-others").each(function (e) {
        if (this.checked) {
            i++;
        } else {
            j++;
        }

    });
    if (i != 0 && j == 0) {
        $line.find(".filter-all").prop('checked', true);
    } else {
        $line.find(".filter-all").prop('checked', false);
    }
}
function checkCheckBox() {
    //click all
    $(".filter-all").off("click").click(function () {
        var $line = $(this).parents("div[data-role='grid']:first");
        if (this.checked == true) {
            $line.find("input").prop('checked', true);
            //$line.find(".filter-others").prop('checked', false);
        } else {
            $line.find("input").prop('checked', false);
        }
    });
    //click normal
    $(".filter-normal").off("click").click(function () {
        var $line = $(this).parents("div[data-role='grid']:first");
        if (this.checked == true) {
            SetCheckAll($line);
        } else {
            $line.find(".filter-all").prop('checked', false);
            $(this).prop('checked', false);
        }
    });
}

/*
 * 通用对话框封装
 */

function ShowInfo(content, callback) {
    $('#_dialog_').remove();
    $.root_.append("<div id='_dialog_' title='Dialog Title'><p>" + content + "</p></div>");
    _DialogSingleButton('info', '信息', callback);
}

function ShowWarning(content, callback) {
    $('#_dialog_').remove();
    $.root_.append("<div id='_dialog_' title='Dialog Title'><p>" + content + "</p></div>");
    _DialogSingleButton('warning', '警告', callback);
}

function ShowError(content, callback) {
    $('#_dialog_').remove();
    $.root_.append("<div id='_dialog_' title='Dialog Title'><p>" + content + "</p></div>");
    _DialogSingleButton('error', '错误', callback);
}

function ShowConfirm(content, callback) {
    $('#_dialog_').remove();
    $.root_.append("<div id='_dialog_' title='Dialog Title'><p>" + content + "</p></div>");
    _Dialog('question', '确认', callback);
}
function ShowYesNo(content, yes, no) {
    $('#_dialog_').remove();
    $.root_.append("<div id='_dialog_' title='Dialog Title'><p>" + content + "</p></div>");
    _Dialog('yesno', '确认', yes, no);
}

function _DialogSingleButton(level, title, callback) {

    var icon_title;
    var class_ok;
    if (level == "info") {
        icon_title = 'fa-info';
        class_ok = 'btn-info';
    } else if (level == 'warning') {
        icon_title = 'fa-warning';
        class_ok = 'btn-warning';
    } else {
        icon_title = 'fa-times-circle';
        class_ok = 'btn-danger';
    }

    $('#_dialog_').dialog({
        autoOpen: false,
        width: 400,
        resizable: true,
        modal: true,
        close: function (event, ui) { $('#_dialog_').remove(); },
        title: "<div class='widget-header'><h4><i class='fa " + icon_title + "'></i> " + title + "</h4></div>",
        buttons: [{
            html: "<i class='fa fa-check'></i>&nbsp; 确定",
            "class": "btn " + class_ok + "",
            click: function () {
                if (callback) {
                    callback();
                }
                $(this).dialog("close");
            }
        }]
    });
    $('#_dialog_').dialog('open');
}
function _Dialog(level, title, yes, no) {

    var icon_title;
    var class_ok;
    var button_ok = "确认";
    var button_cancel = "取消";
    if (level == "info") {
        icon_title = 'fa-info';
        class_ok = 'btn-info';
    } else if (level == 'warning') {
        icon_title = 'fa-warning';
        class_ok = 'btn-warning';
    } else if (level == 'question') {
        icon_title = 'fa-question-circle';
        class_ok = 'btn-primary';
    } else if (level == 'yesno') {
        icon_title = 'fa-question-circle';
        class_ok = 'btn-primary';
        button_ok = "是";
        button_cancel = "否";
    } else {
        icon_title = 'fa-times-circle';
        class_ok = 'btn-danger';
    }

    $('#_dialog_').dialog({
        autoOpen: false,
        width: 400,
        resizable: true,
        modal: true,
        close: function (event, ui) { $('#_dialog_').remove(); },
        title: "<div class='widget-header'><h4><i class='fa " + icon_title + "'></i> " + title + "</h4></div>",
        buttons: [{
            html: "<i class='fa fa-check'></i>&nbsp; " + button_ok,
            "class": "btn " + class_ok + "",
            click: function () {
                $(this).dialog("close");
                if (yes) {
                    yes();
                }
            }
        }, {
            html: "<i class='fa fa-times'></i>&nbsp; " + button_cancel,
            "class": "btn btn-default",
            click: function () {
                $(this).dialog("close");
                if (no) {
                    no();
                }
            }
        }]
    });
    $('#_dialog_').dialog('open');
}

function ShowProcessing() {
    if ($("#_processing_").length == 0) {
        $.root_.append('<div id="_processing_"><p>后台处理中，请耐心等待...<div class="progress progress-striped active no-margin"><div class="progress-bar bg-color-blue" role="progressbar" style="width: 100%"></div></div></p></div>');
        _Processing();
    }
}

function HideProcessing() {
    $('#_processing_').dialog('close');
}

function _Processing() {

    $('#_processing_').dialog({
        autoOpen: false,
        width: 300,
        modal: true,
        close: function (event, ui) { $('#_processing_').remove(); },
        title: null,
        resizable: false
    });
    $('#_processing_').dialog('open');
    $('#_processing_').css('min-height', 'inherit');
    $('#_processing_').prev().remove();
}

function initValidator($form) {
    var baseOption = {
        onfocusout: function (element) { $(element).valid(); },
    };
    var setting = $.extend({}, extendValidateOption, baseOption);
    var $validator = $form.validate(setting);
    return $validator;
}


/*
 * 验证方法
 */
function isNumber(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}

if (typeof String.prototype.startWith != 'function') {
    String.prototype.startWith = function (str) {
        return this.slice(0, str.length) == str;
    };
}
if (typeof String.prototype.endWith != 'function') {
    String.prototype.endWith = function (str) {
        return this.slice(-str.length) == str;
    };
}

function toRound(n, prec) {
    prec = prec ? Math.abs(prec) : 0;
    var k = Math.pow(10, prec);
    var sign = n < 0 ? -1 : 1;
    var num = Math.round(Math.abs(n) * k) / k;
    return num * sign;
}

/*
 * 数字格式化，包含千分符，指定小数位数 
 */
if (typeof Number.prototype.digitalFormat != 'function') {
    Number.prototype.digitalFormat = function (decimals) {
        if (typeof decimals === "undefined" || decimals == null)
            decimals = 2;
        if (!new RegExp(/[0-9]+/).test(decimals))
            decimals = 2;
        var number = this;
        number = (number + '').replace(/[^0-9+-.]/g, '');
        var n = !isFinite(+number) ? 0 : +number,
            prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
            s = ('' + toRound(n, prec)).split('.');
        if ((s[1] || '').length < prec) {
            s[1] = s[1] || '';
            s[1] += new Array(prec - s[1].length + 1).join('0');
        }
        return s.join('.');
    };
}

/*
 * 数字格式化，包含千分符，指定小数位数 
 */
if (typeof Number.prototype.numberFormat != 'function') {
    Number.prototype.numberFormat = function (decimals) {
        if (typeof decimals === "undefined" || decimals == null)
            decimals = 2;
        if (!new RegExp(/[0-9]+/).test(decimals))
            decimals = 2;
        var number = this;
        number = (number + '').replace(/[^0-9+-.]/g, '');
        var n = !isFinite(+number) ? 0 : +number,
            prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
            s = ('' + toRound(n, prec)).split('.');
        var x1 = s[0];
        var rgx = /(\d+)(\d{3})/;
        while (rgx.test(x1)) {
            x1 = x1.replace(rgx, '$1' + ',' + '$2');
        }
        s[0] = x1;
        if ((s[1] || '').length < prec) {
            s[1] = s[1] || '';
            s[1] += new Array(prec - s[1].length + 1).join('0');
        }
        return s.join('.');
    };
}

/*
 * 百分比数字格式化，指定小数位数 
 */
if (typeof Number.prototype.percentFormat != 'function') {
    Number.prototype.percentFormat = function (decimals) {
        if (typeof decimals === "undefined" || decimals == null)
            decimals = 4;
        if (!new RegExp(/[0-9]+/).test(decimals))
            decimals = 4;
        var number = this * 100;
        number = (number + '').replace(/[^0-9+-.]/g, '');
        var n = !isFinite(+number) ? 0 : +number,
            prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
            s = ('' + toRound(n, prec)).split('.');
        if ((s[1] || '').length < prec) {
            s[1] = s[1] || '';
            s[1] += new Array(prec - s[1].length + 1).join('0');
        }
        return s.join('.') + "%";
    };
}

if (typeof Date.prototype.dateFormat != 'function') {
    Date.prototype.dateFormat = function (format) {
        var o = {
            "M+": this.getMonth() + 1, //month
            "d+": this.getDate(), //day
            "H+": this.getHours(), //hour
            "m+": this.getMinutes(), //minute
            "s+": this.getSeconds(), //second
            "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
            "S": this.getMilliseconds() //millisecond
        };
        if (typeof format === "undefined" || format == null || format == "")
            format = "yyyy-MM-dd";
        if (/(y+)/.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(format)) {
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
            }
        }
        return format;
    };
}

/*
 * 日期格式化
 */
if (typeof String.prototype.dateFormat != 'function') {
    String.prototype.dateFormat = function (fmt) {
        if (typeof fmt === "undefined" || fmt == null || fmt == "")
            fmt = "yyyy-MM-dd";
        var v = this.replace(/[^/-\d\s:]+/g, " ");
        var date = new Date(v);
        if (date.toString() === 'Invalid Date')
            return "";
        return date.dateFormat(fmt);
    };
}
if (typeof String.prototype.format != 'function') {
    String.prototype.format = function (fmt) {
        if ($.isNumeric(this)) {
            var t = fmt.substr(0, 1);
            var d = parseInt(fmt.substr(1));
            var n = parseFloat(this);
            if (t == "N" || t == "n") {
                return n.numberFormat(d);
            } else if (t == "P" || t == "p") {
                return n.percentFormat(d);
            } else if (t == "F" || t == "f") {
                return n.digitalFormat(d);
            }
        } else if ($.isDate(new Date(this))) {
            return v.dateFormat(fmt);
        }
        return this;
    };
}

function parseDate(format, value, settings) {
    if (format == null || value == null) {
        throw "Invalid arguments";
    }

    value = (typeof value === "object" ? value.toString() : value + "");
    if (value === "") {
        return null;
    }

    var iFormat, dim, extra,
        iValue = 0,
        shortYearCutoffTemp = (settings ? settings.shortYearCutoff : null) || this._defaults.shortYearCutoff,
        shortYearCutoff = (typeof shortYearCutoffTemp !== "string" ? shortYearCutoffTemp :
            new Date().getFullYear() % 100 + parseInt(shortYearCutoffTemp, 10)),
        dayNamesShort = (settings ? settings.dayNamesShort : null) || this._defaults.dayNamesShort,
        dayNames = (settings ? settings.dayNames : null) || this._defaults.dayNames,
        monthNamesShort = (settings ? settings.monthNamesShort : null) || this._defaults.monthNamesShort,
        monthNames = (settings ? settings.monthNames : null) || this._defaults.monthNames,
        year = -1,
        month = -1,
        day = -1,
        doy = -1,
        literal = false,
        date,
        // Check whether a format character is doubled
        lookAhead = function (match) {
            var matches = (iFormat + 1 < format.length && format.charAt(iFormat + 1) === match);
            if (matches) {
                iFormat++;
            }
            return matches;
        },
        // Extract a number from the string value
        getNumber = function (match) {
            var isDoubled = lookAhead(match),
                size = (match === "@" ? 14 : (match === "!" ? 20 :
                (match === "y" && isDoubled ? 4 : (match === "o" ? 3 : 2)))),
                digits = new RegExp("^\\d{1," + size + "}"),
                num = value.substring(iValue).match(digits);
            if (!num) {
                throw "Missing number at position " + iValue;
            }
            iValue += num[0].length;
            return parseInt(num[0], 10);
        },
        // Extract a name from the string value and convert to an index
        getName = function (match, shortNames, longNames) {
            var index = -1,
                names = $.map(lookAhead(match) ? longNames : shortNames, function (v, k) {
                    return [[k, v]];
                }).sort(function (a, b) {
                    return -(a[1].length - b[1].length);
                });

            $.each(names, function (i, pair) {
                var name = pair[1];
                if (value.substr(iValue, name.length).toLowerCase() === name.toLowerCase()) {
                    index = pair[0];
                    iValue += name.length;
                    return false;
                }
            });
            if (index !== -1) {
                return index + 1;
            } else {
                throw "Unknown name at position " + iValue;
            }
        },
        // Confirm that a literal character matches the string value
        checkLiteral = function () {
            if (value.charAt(iValue) !== format.charAt(iFormat)) {
                throw "Unexpected literal at position " + iValue;
            }
            iValue++;
        };

    for (iFormat = 0; iFormat < format.length; iFormat++) {
        if (literal) {
            if (format.charAt(iFormat) === "'" && !lookAhead("'")) {
                literal = false;
            } else {
                checkLiteral();
            }
        } else {
            switch (format.charAt(iFormat)) {
                case "d":
                    day = getNumber("d");
                    break;
                case "D":
                    getName("D", dayNamesShort, dayNames);
                    break;
                case "o":
                    doy = getNumber("o");
                    break;
                case "m":
                    month = getNumber("m");
                    break;
                case "M":
                    month = getName("M", monthNamesShort, monthNames);
                    break;
                case "y":
                    year = getNumber("y");
                    break;
                case "@":
                    date = new Date(getNumber("@"));
                    year = date.getFullYear();
                    month = date.getMonth() + 1;
                    day = date.getDate();
                    break;
                case "!":
                    date = new Date((getNumber("!") - this._ticksTo1970) / 10000);
                    year = date.getFullYear();
                    month = date.getMonth() + 1;
                    day = date.getDate();
                    break;
                case "'":
                    if (lookAhead("'")) {
                        checkLiteral();
                    } else {
                        literal = true;
                    }
                    break;
                default:
                    checkLiteral();
            }
        }
    }

    if (iValue < value.length) {
        extra = value.substr(iValue);
        if (!/^\s+/.test(extra)) {
            throw "Extra/unparsed characters found in date: " + extra;
        }
    }

    if (year === -1) {
        year = new Date().getFullYear();
    } else if (year < 100) {
        year += new Date().getFullYear() - new Date().getFullYear() % 100 +
            (year <= shortYearCutoff ? 0 : -100);
    }

    if (doy > -1) {
        month = 1;
        day = doy;
        do {
            dim = this._getDaysInMonth(year, month - 1);
            if (day <= dim) {
                break;
            }
            month++;
            day -= dim;
        } while (true);
    }

    date = this._daylightSavingAdjust(new Date(year, month - 1, day));
    if (date.getFullYear() !== year || date.getMonth() + 1 !== month || date.getDate() !== day) {
        throw "Invalid date"; // E.g. 31/02/00
    }
    return date;
}

function defaultDigitalFormat(data, decimals) {
    if (typeof data === "undefined" || data == null) {
        return "";
    }
    return data.digitalFormat(decimals);
}
function defaultNumberFormat(data, decimals) {
    if (typeof data === "undefined" || data == null) {
        return "";
    }
    return data.numberFormat(decimals);
}
function defaultPercentFormat(data, decimals) {
    if (typeof data === "undefined" || data == null) {
        return "";
    }
    return data.percentFormat(decimals);
}
function defaultDateFormat(data) {
    if (typeof data === "undefined" || data == null) {
        return "";
    }
    //return data.dateFormat();
    return Easy.Date.format(data, "yyyy-MM-dd");
}
function defaultDatetimeFormat(data) {
    if (typeof data === "undefined" || data == null) {
        return "";
    }
    //return data.dateFormat("yyyy-MM-dd HH:mm:ss");
    return Easy.Date.format(data, "yyyy-MM-dd HH:mm:ss");
}

/*
 * 将页面滚动条移动到最底端
 */
function GoToBottom() {
    var bh = $('body').height();
    var wh = $(window).height();
    if (bh > wh) {
        $('body').scrollTop(bh - wh);
    }
}

/*
 * 按层级结构序列化json对象。
 */
(function ($) {

    var VERSION = "0.0.1";
    var utils = {
        isMsie: function () {
            var match = /(msie) ([\w.]+)/i.exec(navigator.userAgent);
            return match ? parseInt(match[2], 10) : false;
        },
        isBlankString: function (str) {
            return !str || /^\s*$/.test(str);
        },
        escapeRegExChars: function (str) {
            return str.replace(/[\-\[\]\/\{\}\(\)\*\+\?\.\\\^\$\|]/g, "\\$&");
        },
        isString: function (obj) {
            return typeof obj === "string";
        },
        isNumber: function (obj) {
            return typeof obj === "number";
        },
        isArray: $.isArray,
        isFunction: $.isFunction,
        isObject: $.isPlainObject,
        isUndefined: function (obj) {
            return typeof obj === "undefined";
        },
        isDate: function (obj) {
            // return o instanceof Date;
            return {}.toString.call(obj) === "[object Date]" && obj.toString() !== 'Invalid Date' && !isNaN(obj);
        }
    };
    (function () {
        var methods;
        var settings;
        var r20 = /%20/g,
            rbracket = /\[\]$/,
            rCRLF = /\r?\n/g,
	        manipulation_rcheckableType = /^(?:checkbox|radio)$/i,
            rsubmitterTypes = /^(?:submit|button|image|reset|file)$/i,
            rsubmittable = /^(?:input|select|textarea|keygen)/i;

        function serialize() {
            var propertyName = $(this).data(settings.property);
            if (utils.isBlankString(propertyName)) {
                throw new Error("请配置节点的[domname]属性！");
            }

            var jsonData = {};

            parseRoot(jsonData, this);

            var bb = {};

            bb[propertyName] = jsonData;

            return bb;

        }

        function parseRoot(jsonData, obj) {
            var tagName = $(obj)[0].tagName;
            if (tagName == 'INPUT' || tagName == 'SELECT') {
                parseElement(jsonData, obj);
            } else {
                var cs = $(obj).children();
                if (cs && cs.length > 0) {
                    cs.each(function (index, obj2) {
                        parseDom(jsonData, this);
                    });
                }
            }
        }

        function parseDom(jsonData, obj) {
            var propertyName = $(obj).data(settings.property);
            if (utils.isBlankString(propertyName)) {
                parseDomValue(jsonData, obj);
            } else {
                var arr = new Array();
                var cs = $(obj).children();
                if (cs && cs.length > 0) {
                    cs.each(function (index, obj2) {
                        var temp = {};
                        if (settings.compact)
                            parseDom(temp, this);
                        else
                            parseDomValue(temp, this);
                        if (!$.isEmptyObject(temp)) {
                            arr.push(temp);
                        }
                    });
                }

                jsonData[propertyName] = arr;
            }

        }

        function parseDomValue(jsonData, obj) {
            var type = obj.type;
            /* && !$(obj).is(":disabled")*/
            var need = obj.name &&
				rsubmittable.test(obj.nodeName) && !rsubmitterTypes.test(type) &&
				(obj.checked || !manipulation_rcheckableType.test(type));
            if (need) {
                parseElement(jsonData, obj);
            } else {
                var cs = $(obj).children();
                if (cs && cs.length > 0) {
                    cs.each(function (index, obj2) {
                        parseDom(jsonData, this);
                    });
                }
            }
        }

        function parseElement(jsonData, obj) {
            jsonData[$(obj).attr('name')] = $(obj).val();
            /*var tagName = $(obj)[0].tagName;
            console.log(tagName);
            var name = $(obj).attr('name');
            if (tagName == 'INPUT') {
                if (name != undefined) {
                    jsonData[$(obj).attr('name')] = $(obj).val();
                }
            }
            if (tagName == 'SELECT') {
                if (name != undefined) {
                    jsonData[$(obj).attr('name')] = $(obj).val();
                }
            }*/
        }

        $.fn.domserialize = function (options) {
            settings = $.extend({}, $.fn.domserialize.defaults, options);
            return serialize.apply(this, arguments);
        };
        $.fn.domserialize.defaults = {
            property: "json",
            compact: false
        };

        $.getUrlParam = function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.href.slice(window.location.href.indexOf('?') + 1).match(reg);
            if (r != null) return unescape(r[2]);
            return null;
            //var query = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
            //for (var i = 0; i < query.length; i++) {
            //    var qs = query[i].split("=");
            //    if (qs[0] == name) {
            //        return qs[1];
            //    }
            //}
            //return null;
        };
    })();

}(window.jQuery));

$(function () {
    var r20 = /%20/g,
        rbracket = /\[\]$/,
        rCRLF = /\r?\n/g,
        manipulation_rcheckableType = /^(?:checkbox|radio)$/i,
        rsubmitterTypes = /^(?:submit|button|image|reset|file)$/i,
        rsubmittable = /^(?:input|select|textarea|keygen)/i;
    jQuery.fn.extend({
        //表单验证
        validateForm: function () {
            var ele = this[0];
            var $form = $(ele);
            if (ele.tagName == "FORM") {
                var baseOption = {
                    onfocusout: function (element) { $(element).valid(); },
                };
                var setting = $.extend({}, extendValidateOption, baseOption);
                var $validator = $form.validate(setting);
                var $valid = $form.valid();
                if (!$valid) {
                    $validator.focusInvalid();
                }
                return $validator;
            }
        },
        checkDate: function () {
            var $this = $(this);
            var txtDate = $this.val();
            if ($.trim(txtDate) == '') {
                return false;
            }
            var aoDate,
                ms,
                month,
                day,
                year;
            var separator = txtDate.indexOf('-') > 0 ? '-' : txtDate.indexOf('/') > 0 ? '/' : undefined;

            if (separator === undefined) {
                separator = '/';
            }

            aoDate = txtDate.split(separator);

            if (aoDate.length !== 3) {
                return false;
            }

            month = aoDate[1] - 1;
            day = aoDate[2] - 0;
            year = aoDate[0] - 0;

            if (year < 1900 || year > 2200) {
                return false;
            }

            ms = (new Date(year, month, day)).getTime();

            aoDate = new Date();
            aoDate.setTime(ms);

            if (aoDate.getFullYear() !== year ||
                aoDate.getMonth() !== month ||
                aoDate.getDate() !== day) {
                return false;
            }
            return true;
        },
        serialize2: function () {
            var disabled = this.find(':input:disabled').removeAttr('disabled');
            var serialized = this.serializeArray();
            disabled.attr('disabled', 'disabled');
            return jQuery.param(serialized);
        },
        serializeObject: function () {
            var o = {};
            var a = this.serializeArray();
            $.each(a, function () {
                if (o[this.name] !== undefined) {
                    if (!o[this.name].push) {
                        o[this.name] = [o[this.name]];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            });
            return o;
        },
        formatInput: function () {
            var ele = this[0];
            var $ele = $(ele);
            if (ele.tagName == "INPUT") {
                try {
                    var v = $ele.val();
                    var fmt = $ele.attr("data-format");
                    var tp = ele.type; //  $ele.attr("type");
                    if (v && fmt && tp == "text") {
                        if ($.isNumeric(v)) {
                            var t = fmt.substr(0, 1);
                            var d = parseInt(fmt.substr(1));
                            var n = parseFloat(v);
                            if (t == "N" || t == "n") {
                                $ele.val(n.numberFormat(d));
                            } else if (t == "P" || t == "p") {
                                $ele.val(n.percentFormat(d));
                            } else if (t == "F" || t == "f") {
                                $ele.val(n.digitalFormat(d));
                            }
                        } else if ($.isDate(new Date(v))) {
                            $ele.val(v.dateFormat(fmt));
                        }
                    }
                } catch (e) {

                }
            }
        },
        onlyDigital: function (size) {
            var ele = this[0];
            var $ele = $(ele);
            if (ele.tagName == "INPUT" && ele.type == "text") {
                var s = parseInt(size);
                if (s > 0) {
                    $ele.attr("maxlength", size);
                }
                $ele.on('input', function (e) {
                    this.value = this.value.replace(/[^0-9]/g, '');
                    e.preventDefault();
                });
            }
        },
        onlySDigital: function (size) {
            var ele = this[0];
            var $ele = $(ele);
            if (ele.tagName == "INPUT" && ele.type == "text") {
                var s = parseInt(size);
                if (s > 0) {
                    $ele.attr("maxlength", size);
                }
                $ele.on('input', function (e) {
                    var val = $(this).val();
                    val = val.replace(/[^0-9-]/g, '');
                    val = val.replace(/-+$/, "-");
                    if (val.lastIndexOf("-") > 0) val = val.replace(/-+$/, "");
                    $(this).val(val);
                    e.preventDefault();
                });
            }
        },
        onlyNumeric: function (size) {
            var ele = this[0];
            var $ele = $(ele);
            if (ele.tagName == "INPUT" && ele.type == "text") {
                var s = parseInt(size);
                if (s > 0) {
                    $ele.attr("maxlength", size);
                }
                $ele.on('input', function (e) {
                    var val = $(this).val();
                    if (isNaN(val)) {
                        if (val.startWith(".")) val = "0" + val;
                        val = val.replace(/[^0-9\.]/g, '');
                        var len = val.split('.').length;
                        if (len > 2) val = val.replace(/\.+$/, "");
                    }
                    $(this).val(val);
                    e.preventDefault();
                });
            }
        },
        onlySNumeric: function (size) {
            var ele = this[0];
            var $ele = $(ele);
            if (ele.tagName == "INPUT" && ele.type == "text") {
                var s = parseInt(size);
                if (s > 0) {
                    $ele.attr("maxlength", size);
                }
                $ele.on('input', function (e) {
                    var val = $(this).val();
                    if (isNaN(val)) {
                        if (val.startWith(".")) val = "0" + val;
                        val = val.replace(/[^0-9\.-]/g, '');
                        var len = val.split('.').length;
                        if (len > 2) val = val.replace(/\.+$/, "");
                        val = val.replace(/-+$/, "-");
                        if (val.lastIndexOf("-") > 0) val = val.replace(/-+$/, "");
                    }
                    $(this).val(val);
                    e.preventDefault();
                });
            }
        },
        removeComma: function () {
            $(this).val($(this).val().replace(/,/g, ''));
            $(this).setCursorPosition($(this).val().length);
        },
        setCursorPosition: function (position) {
            if (this.lengh == 0) return this;
            return $(this).setSelection(position, position);
        },
        setSelection: function (selectionStart, selectionEnd) {
            if (this.lengh == 0) return this;
            input = this[0];
            if (input.createTextRange) {
                var range = input.createTextRange();
                range.collapse(true);
                range.moveEnd('character', selectionEnd);
                range.moveStart('character', selectionStart);
                range.select();
            } else if (input.setSelectionRange) {
                input.focus();
                input.setSelectionRange(selectionStart, selectionEnd);
            }
            return this;
        },
        getCursortPosition: function () {
            var caretPos = 0; // IE Support
            var input = this[0];
            if (input.selectionStart || input.selectionStart == '0')
                caretPos = input.selectionStart;
            return caretPos;
        },
        getCursortEnd: function () {
            var caretPos = 0; // IE Support
            var input = this[0];
            if (input.selectionEnd || input.selectionEnd == '0')
                caretPos = input.selectionEnd;
            return caretPos;
        },
        focusEnd: function () {
            this.setCursorPosition(this.val().length);
        },
        buildJson: function () {
            var result = {};
            $(this).find('input,select,textarea').each(function () {
                var obj = $(this)[0];
                var type = obj.type;
                var need = obj.name &&
                    rsubmittable.test(obj.nodeName) && !rsubmitterTypes.test(type) &&
                    (obj.checked || !manipulation_rcheckableType.test(type));
                if (need) {
                    result[$(this).attr('name')] = $(this).val();
                }
            });
            return result;
        },
        fullScreen: function () {
            $(this).wrap('<div id="fullscreen-mode"/>');
        },
        cancelFullScreen: function () {
            $(this).unwrap();
        },
        select3: function (array, fn) {
            var ddpm = $(this).parent().find(".dropdown-menu");
            var txt = $(this);
            var ul = txt.parent().find("ul.select3-menu");
            ul.html("");
            for (var i = 0; i < array.length; i++) {
                ul.append("<li><span class='" + array[i] + "' title='" + array[i] + "'></span></li>");
            }
            var spans = ddpm.find(">li>span");
            if (txt.hasClass("select3")) {
                txt.on("click", function (e) {
                    var $parent = txt.parent();
                    var isActive = $parent.hasClass('open');
                    if (isActive) return;

                    $(".dropdown-menu-container").removeClass("open");
                    var left = parseInt(txt.css("margin-left")) + parseInt($parent.css("padding-left")) - 1;
                    var top = parseInt(txt.css("height")) + parseInt(txt.css("margin-top")) + parseInt($parent.css("padding-top")) - 1;
                    ddpm.css("left", left + "px");
                    ddpm.css("top", top + "px");
                    ddpm.css("min-width", parseInt(txt.css("width")) - 22 + "px");
                    e.stopPropagation();
                    $parent.addClass("open");
                });

                txt.on("select", function () { return false; });

                //txt.next().click(function (e) {
                //    txt.click();
                //    txt.focus();
                //    e.stopPropagation();
                //});

                ddpm.click(function (e) {
                    e.stopPropagation();
                });

                spans.click(function () {
                    var css = $(this).attr("class");
                    //txt.val(css);
                    if (fn) {
                        fn(css);
                    }
                });

                $(document.body).click(function () {
                    var $parent = txt.parent();
                    if (!$parent.hasClass('open')) return;
                    $parent.removeClass("open");
                });
            } else {
                return;
            }
        }
    });
    jQuery.extend({
        isDate: function (obj) {
            // return o instanceof Date;
            return {}.toString.call(obj) === "[object Date]" && obj.toString() !== 'Invalid Date' && !isNaN(obj);
        },
        isNumber: function (obj) {
            return '[object Number]' == Object.prototype.toString.call(obj) && isFinite(obj);
        },
        getKeyByValue: function (enums, val) {
            for (key in enums) {
                if (enums[key] == val) {
                    return (key);
                }
            }
            return (null);
        }
    });

    /*$.validator.methods.range = function (value, element, param) {
        var globalizedValue = value.replace(/,/g, "");
        return this.optional(element) || (globalizedValue >= param[0] && globalizedValue <= param[1]);
    };
    
    $.validator.methods.min = function (value, element, param) {
        var globalizedValue = value.replace(/,/g, "");
        return this.optional(element) || globalizedValue >= param;
    };
    
    $.validator.methods.max = function (value, element, param) {
        var globalizedValue = value.replace(/,/g, "");
        return this.optional(element) || globalizedValue <= param;
    };
    
    $.validator.methods.number = function (value, element) {
        return this.optional(element) || /^-?(?:\d{1,3}(?:(?:(?:,\d{3})*)|(?:(?:\d{3})*)))(?:[.]\d*)?$/.test(value);
    };*/
});

function setDateRange(start, end) {
    start.removeClass('hasDatepicker').datepicker({
        onClose: function (selectedDate) {
            end.datepicker("option", "minDate", selectedDate);
        }
    });
    end.removeClass('hasDatepicker').datepicker({
        onClose: function (selectedDate) {
            start.datepicker("option", "maxDate", selectedDate);
        }
    });
}

function setDateRangeKendo(start, end, fn) {
    start.data("kendoDatePicker").bind("close", function (e) {
        end.data("kendoDatePicker").min(start.data("kendoDatePicker").value());
        if (fn !== null) {
            fn();
        }
    });
    end.data("kendoDatePicker").bind("close", function (e) {
        start.data("kendoDatePicker").max(end.data("kendoDatePicker").value());
        if (fn !== null) {
            fn();
        }
    });
}

function dateDiff(begin, end) {
    var one_day = 1000 * 60 * 60 * 24
    return (Math.round((end.getTime() - begin.getTime()) / one_day));
}


function setDateIconEvent() {
    $(".datepicker:not(:disabled) ~ .input-group-addon").click(function () {
        $(this).prev(".datepicker").datepicker('show');
    });
}

function formatInputs() {
    $(":text[data-format]").each(function () { $(this).formatInput(); });
}


Date.prototype.addDays = function (num) {
    var value = this.valueOf();
    value += 86400000 * num;
    return new Date(value);
};

Date.prototype.addSeconds = function (num) {
    var value = this.valueOf();
    value += 1000 * num;
    return new Date(value);
};

Date.prototype.addMinutes = function (num) {
    var value = this.valueOf();
    value += 60000 * num;
    return new Date(value);
};

Date.prototype.addHours = function (num) {
    var value = this.valueOf();
    value += 3600000 * num;
    return new Date(value);
};

Date.prototype.addMonths = function (num) {
    var value = new Date(this.valueOf());

    var mo = this.getMonth();
    var yr = this.getFullYear();

    mo = (mo + num) % 12;
    if (0 > mo) {
        yr += (this.getMonth() + num - mo - 12) / 12;
        mo += 12;
    } else
        yr += ((this.getMonth() + num - mo) / 12);

    value.setMonth(mo);
    value.setFullYear(yr);
    return value;
};

function deleteArrayItem(array, fn) {
    if (typeof fn == 'function') {
        for (var i = array.length - 1; i >= 0; i--) {
            if (fn(array[i])) {
                array.splice(i, 1);
            }
        }
    }
}

function getDateOnYearAgo() {
    var now = new Date();
    var yearAgo = now.addMonths(-12);
    return yearAgo.dateFormat("yyyy-MM-dd");
}

function getDateWeekLater() {
    var now = new Date();
    var date = now.addDays(7);
    return date.dateFormat("yyyy-MM-dd");
}

function html_encode(str) {
    var s = "";
    if (str.length == 0) return "";
    s = str.replace(/&/g, "&amp;");
    s = s.replace(/</g, "&lt;");
    s = s.replace(/>/g, "&gt;");
    s = s.replace(/ /g, "&nbsp;");
    s = s.replace(/\'/g, "&#39;");
    s = s.replace(/\"/g, "&quot;");
    s = s.replace(/\n/g, "<br>");
    return s;
}

function html_decode(str) {
    var s = "";
    if (str.length == 0) return "";
    s = str.replace(/&amp;/g, "&");
    s = s.replace(/&lt;/g, "<");
    s = s.replace(/&gt;/g, ">");
    s = s.replace(/&nbsp;/g, " ");
    s = s.replace(/&#39;/g, "\'");
    s = s.replace(/&quot;/g, "\"");
    s = s.replace(/<br>/g, "\n");
    return s;
}

function select2multi() {
    $(".select2[multiple]").each(function () {
        var $this = $(this);
        var sname = $this.attr('data-name');
        $this.attr("placeholder", "选择" + sname);

        var html = $this.html();
        html = "<option value=''>全部" + sname + "</option>" + html;
        $this.html(html);

        $this.change(function () {
            var length = this.options.length;
            var selectedItems = $(this).select2("val");
            if (selectedItems.indexOf("") >= 0 || length - 1 == selectedItems.length) {
                $(this).select2("val", new Array(""));
                var contains = $(this).prev(".select2-container-multi");
                var selecteddiv = contains.find("ul > li.select2-search-choice > div");
                selecteddiv.each(function () {
                    if ($(this).html() != "全部" + sname) {
                        $(this).parent().remove();
                    }
                });
            }
        });
    });
}

function openDialog(url, title, width, height) {
    var $dialog = $("<div></div>")
    .load(url)
    .dialog({
        autoOpen: false,
        title: title,
        width: width,
        height: height,
        modal: true,
    });
    $dialog.dialog('open');
}

function printdiv(printpage) {
    //对于chrome浏览器，打印功能以Iframe嵌套网页实现，避免用户点击关闭按钮导致系统假死
    if (document.all) {
        var htmldiv = $(printpage)[0];
        var headstr = "<html><head><title></title></head><body>";
        var footstr = "</body>";
        var newstr = htmldiv.outerHTML;
        var w = window.open("", "_blank", "k");
        w.document.write(headstr + newstr + footstr);
        w.document.close();
        w.focus();
        //if (navigator.appName == 'Microsoft Internet Explorer') window.print();
        //else 
        w.window.print();
        w.close();
        return false;
    } else {
        var $w = $("#printIframe");
        if (!$w.length) {
            $w = $('<div style="display:none"><iframe src="about:blank" id="printIframe"></iframe></div>')
                .appendTo('body').find('iframe');
        }
        var htmldiv = $(printpage)[0];
        var newstr = htmldiv.outerHTML;
        $w.contents().find('body').html(newstr);
        $w[0].contentWindow.print();
        return false;
    }
}

function toNumber(a) {
    if (a == undefined) {
        return undefined;
    }
    return isNaN(parseFloat(a.replace(/,/g, ""))) ? 0 : parseFloat(a.replace(/,/g, ""));
}

function replaceLine(str) {
    if (str == null)
        return "";
    return str.replace(/(^[\s\n]*)|([\r+])|([\s\n]*$)/g, "").replace(/\n/g, "<br />");
}

function formatStr(str) {
    if (str == null) {
        return "";
    }
    var rtn = replaceLine(str).replace(/<br\s*\/>/ig, "|");
    var bonds = rtn.split("|");
    var text = "";
    for (var i = 0; i < bonds.length; i++) {
        var ss = bonds[i].split("_");
        if (i < bonds.length - 1)
            text += "<div>";
        else
            text += "<div>";
        text += "<span>" + ss[0] + "</span>&nbsp;[<span> " + ss[1] + " </span>]&nbsp;&nbsp;<span class='text-success'>" + ss[2] + "</span></div>";
    }
    return text;
}

function formatStr2(str) {
    if (str == null) {
        return "";
    }
    var rtn = replaceLine(str).replace(/<br\s*\/>/ig, "|");
    var bonds = rtn.split("|");
    var text = "";
    for (var i = 0; i < bonds.length; i++) {
        var ss = bonds[i].split("_");
        var cls = "text-warning";
        if (ss[3].startWith("-"))
            cls = "txt-color-red";
        text += "<div>";
        text += "<span>" + ss[0] + "</span>&nbsp;[<span> " + ss[1] + " </span>]&nbsp;&nbsp;<span class='text-success'>" + ss[2] + "</span> / <span class='" + cls + "'>" + ss[3] + "</span></div>";
    }
    return text;
}

function drawRowIndex(oTable, index) {
    var oSettings = oTable.fnSettings();
    if (oSettings.bSorted || oSettings.bFiltered) {
        for (var i = 0; i < oSettings.aiDisplay.length; i++) {
            oTable.fnUpdate(i + 1, oSettings.aiDisplay[i], index, false, false);
        }
    }
}

function getCheckboxCondition($obj) {
    var $checks = $obj.find("input:checked");
    if ($checks.length == 0) {
        return '';
    }
    //全部
    if ($checks.length == 1 && $checks[0].value == "all") {
        return '';
    }
    var value = "";
    $checks.each(function () {
        if ($(this).val() == "all")
            return;
        value += $(this).val() + ",";
    });

    return value;
}

function convertCurrency(currencyDigits, perfix, endfix) {
    // Constants:
    var MAXIMUM_NUMBER = 99999999999.99;
    // Predefine the radix characters and currency symbols for output:
    var CN_ZERO = "零";
    var CN_ONE = "壹";
    var CN_TWO = "贰";
    var CN_THREE = "叁";
    var CN_FOUR = "肆";
    var CN_FIVE = "伍";
    var CN_SIX = "陆";
    var CN_SEVEN = "柒";
    var CN_EIGHT = "捌";
    var CN_NINE = "玖";
    var CN_TEN = "拾";
    var CN_HUNDRED = "佰";
    var CN_THOUSAND = "仟";
    var CN_TEN_THOUSAND = "万";
    var CN_HUNDRED_MILLION = "亿";
    var CN_SYMBOL = perfix || "";// "人民币";
    var CN_DOLLAR = endfix || "";//"元";
    var CN_TEN_CENT = "角";
    var CN_CENT = "分";
    var CN_INTEGER = "";//"整";
    // Variables:
    var integral; // Represent integral part of digit number.
    var decimal; // Represent decimal part of digit number.
    var outputCharacters; // The output result.
    var parts;
    var digits, radices, bigRadices, decimals;
    var zeroCount;
    var i, p, d;
    var quotient, modulus;
    // Validate input string:
    currencyDigits = currencyDigits.toString();
    if (currencyDigits == "") {
        return "";
    }
    if (currencyDigits.match(/[^,.\d]/) != null) {
        return "";
    }
    if ((currencyDigits).match(/^((\d{1,3}(,\d{3})*(.((\d{3},)*\d{1,3}))?)|(\d+(.\d+)?))$/) == null) {
        return "";
    }
    // Normalize the format of input digits:
    currencyDigits = currencyDigits.replace(/,/g, ""); // Remove comma delimiters.
    currencyDigits = currencyDigits.replace(/^0+/, ""); // Trim zeros at the beginning.
    // Assert the number is not greater than the maximum number.
    if (Number(currencyDigits) > MAXIMUM_NUMBER) {
        return "";
    }
    // Process the coversion from currency digits to characters:
    // Separate integral and decimal parts before processing coversion:
    parts = currencyDigits.split(".");
    if (parts.length > 1) {
        integral = parts[0];
        decimal = parts[1];
        // Cut down redundant decimal digits that are after the second.
        decimal = decimal.substr(0, 2);
    }
    else {
        integral = parts[0];
        decimal = "";
    }
    // Prepare the characters corresponding to the digits:
    digits = new Array(CN_ZERO, CN_ONE, CN_TWO, CN_THREE, CN_FOUR, CN_FIVE, CN_SIX, CN_SEVEN, CN_EIGHT, CN_NINE);
    radices = new Array("", CN_TEN, CN_HUNDRED, CN_THOUSAND);
    bigRadices = new Array("", CN_TEN_THOUSAND, CN_HUNDRED_MILLION);
    decimals = new Array(CN_TEN_CENT, CN_CENT);
    // Start processing:
    outputCharacters = "";
    // Process integral part if it is larger than 0:
    if (Number(integral) > 0) {
        zeroCount = 0;
        for (i = 0; i < integral.length; i++) {
            p = integral.length - i - 1;
            d = integral.substr(i, 1);
            quotient = p / 4;
            modulus = p % 4;
            if (d == "0") {
                zeroCount++;
            }
            else {
                if (zeroCount > 0) {
                    outputCharacters += digits[0];
                }
                zeroCount = 0;
                outputCharacters += digits[Number(d)] + radices[modulus];
            }
            if (modulus == 0 && zeroCount < 4) {
                outputCharacters += bigRadices[quotient];
            }
        }
        outputCharacters += CN_DOLLAR;
    }
    // Process decimal part if there is:
    if (decimal != "") {
        for (i = 0; i < decimal.length; i++) {
            d = decimal.substr(i, 1);
            if (d != "0") {
                outputCharacters += digits[Number(d)] + decimals[i];
            }
        }
    }
    // Confirm and return the final output string:
    if (outputCharacters == "") {
        outputCharacters = CN_ZERO + CN_DOLLAR;
    }
    if (decimal == "") {
        outputCharacters += CN_INTEGER;
    }
    outputCharacters = CN_SYMBOL + outputCharacters;
    return outputCharacters;
}

function kendoGridEmptyData(grid) {
    var data = $("#" + grid).data("kendoGrid").dataItem("tr:eq(0)");
    if (!data) {
        var tds = $("#" + grid + " table thead tr th").length;
        $("#" + grid + " table tbody").html("<tr><td colspan='" + tds + "' class='dataTables_empty'>暂无数据</td></tr>");
    }
}

function kendoGridEmptyData2(grid) {
    var data = grid.dataItem("tr:eq(0)");
    if (!data) {
        var tds = $(grid.thead).find("tr th").length;
        $(grid.tbody).html("<tr><td colspan='" + tds + "' class='dataTables_empty'>暂无数据</td></tr>");
    }
}