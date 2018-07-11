// var onBtnClick = function () {
//     var id = $(this).attr("id");
//     id = id.substr(0,id.lastIndexOf("_"));
//     if($("#"+id+"Content").is(":hidden")){
//         $("#" + id + "Content").find("tbody").empty();
//         var arr = getSubContentArray(id);
//         createTable(id,arr,id+"ContentBody");
//         $("#"+id+"Content").show();
//     }
// };

function getContentArray(interfaceName) {
    var content = new Array();
    var obj = eval(interfaceName);
    if(obj != undefined || obj != ''){
        var paramsArray = obj.split("|");
        var defaultValue = eval(interfaceName+"_defaultvalue");
        for(var i = 0; i < paramsArray.length; i++){
            var arrayStr = new Array();
            var paramName = paramsArray[i];
            arrayStr[0] = paramName;

            if(defaultValue[paramName] != undefined){
                var tempStr;
                if(paramName == "request_id"){
                    tempStr = "IPROLEID_" + new Date().format("yyyyMMddhhmmss") + "_" + parseInt(Math.random()*100000000);
                }else if(paramName == "out_order_no"){
                    tempStr = "IPROLEID_" + parseInt(Math.random()*100000000);
                }else{
                    tempStr = defaultValue[paramName];
                }
                arrayStr[1] = tempStr;
            }else{
                arrayStr[1] = "";
            }
            var paramsDetail = eval(interfaceName+"_detail");
            arrayStr[2] = paramsDetail[paramName];
            var subContentArr = "";
            try{
                subContentArr = eval(interfaceName+"_"+paramName);
            }catch(e){}

            if(typeof(subContentArr) != "undefined" && subContentArr != undefined && subContentArr != ""){
                arrayStr[3] = "JSON";
            }

            if(typeof(defaultValue) != "undefined"){
                var editAble = defaultValue["EDITCOLUMN"];
                if(editAble != undefined && editAble != "undefined"){
                    var editAbleArr = editAble.split("|");
                    if(editAbleArr.length > 0){
                        for(var j=0;j<editAbleArr.length;j++){
                            if(paramName == editAbleArr[j]){
                                arrayStr[4] = "EDIT";
                            }
                        }
                    }
                }
            }
            content[i] = arrayStr;
        }
    }
    return content;
}

function getSubContentArray(interfaceName,paramName){
    var subContentArray = new Array();
    if(typeof(paramName) == "undefined" || paramName == "undefined"){
        return subContentArray;
    }
    paramName = interfaceName +"_"+ paramName;
    var array;
    try{
        array  = eval(paramName);
    }catch(e){};

    if(array != undefined || array != ''){
        var params = array.split("|");
        /***********************给特定业务字段添加固定的值**********************/
        var defaultValue = "";
        try{
            defaultValue = eval(paramName+"_defaultvalue");
        }catch(e){}

        for(var i = 0; i<params.length; i++){
            var arr = new Array();
            arr[0] = params[i];
            if(defaultValue != "" && defaultValue[params[i]] != undefined){
                arr[1] = defaultValue[params[i]];
            }else{
                arr[1] = "";
            }
            var paramsDetail = eval(paramName+"_detail");
            arr[2] = paramsDetail[params[i]];
            var subTemp = "";
            try{
                subTemp = eval(paramName+"_"+params[i]);
            }catch(e){}
            if(typeof(subTemp) != "undefined" && subTemp != undefined && subTemp != ""){
                arr[3] = "JSON";
            }

            if(typeof(defaultValue) != "undefined" && typeof(defaultValue) != undefined){
                var editAble = defaultValue["EDITCOLUMN"];
                if(typeof (editAble) != undefined && typeof (editAble) != "undefined"){
                    var editAbleArr = editAble.split("|");
                    if(editAbleArr.length > 0){
                        for(var j=0;j<editAbleArr.length;j++){
                            if(params[i] == editAbleArr[j]){
                                arr[4] = "EDIT";
                            }
                        }
                    }
                }
            }

            subContentArray.push(arr);
        }
    }
    return subContentArray;
}

function createTable(divName,arrayObj,idDiv){
    for(var i=0;i<arrayObj.length;i++){
        var $trTemp;
        if(arrayObj[i][1] != "" && (arrayObj[i][4] != "EDIT")){
            $trTemp = $("<tr style='display: none'></tr>");
        }else{
            $trTemp = $("<tr></tr>");
        }
        for(var j=0;j<arrayObj[i].length;j++){
            if (!divName && typeof(divName)!="undefined"&& divName!=0){
                inputId = arrayObj[i][0];
            }else{
                inputId = divName + "_" + arrayObj[i][0];
            }
            if(j == 1){
                if("JSON" == arrayObj[i][3]){
                    $trTemp.append("<td><input type='text' disabled='disabled' style='border:none' id='"+inputId+"' name='"+arrayObj[i][0]+"' value="+arrayObj[i][1]+"></td>");
                }else{
                    $trTemp.append("<td><input type='text' style='border:none' id='"+inputId+"' name='"+arrayObj[i][0]+"' value="+arrayObj[i][1]+"></td>");
                }
                $("body").on("blur","#"+inputId,onInputBlur);
            } else if(j == 3){
                if("JSON" == arrayObj[i][j]){
                    var btnId = inputId + "_btn";
                    $trTemp.append("<td style='display: none'><button type='button' style='display:none width:0px' id='"+btnId+"'>添加二级参数</button></td>");
                    // $("body").on("click","#"+btnId,onBtnClick);
                }
            }else if(j != 4){
                $trTemp.append("<td>"+ arrayObj[i][j]+"</td>");
            }
        }

        if(idDiv == "contentBody") {
            $trTemp.appendTo("#contentBody");
            if (i == arrayObj.length - 1) {
                //添加生成json按钮行
                var $trTemp = $("<tr></tr>");
                $trTemp.append("<td><button type='button' id='createJson'>生成json参数</button></td>");
                $trTemp.appendTo("#contentBody");
                $("body").on("click","#createJson",onBtnJsonClick);
            }
        }else{
            $trTemp.appendTo("#"+idDiv);
        }
    }
}

/**
 * 生成json字符串
 */

function createJsonStr(type,obj){
    var trList = obj.children("tr");
    var jsonStr = "";
    //二级参数非扩展字段的json字符串生成
    if(type == "1"){
        jsonStr = "{";
        for(var i=0;i<trList.length;i++){
            var tdArr = trList.eq(i).find("td");
            var key = tdArr.eq(0).html();
            var value = tdArr.eq(1).find("input").val();
            jsonStr = jsonStr + "\"" + key +  "\":\"" + value + "\"";
            if(i < trList.length-1){
                jsonStr += ",\n";
            }
        }
        jsonStr += "}";
    }else if (type == "2"){
        //二级参数扩展字段的json字符串生成
        jsonStr = "{";
        if(trList.length == 0){
            jsonStr = "";
        }else{
            for(var i=0;i<trList.length;i++){
                var tdArr = trList.eq(i).find("td");
                var key = tdArr.eq(0).html();
                var value = tdArr.eq(1).find("input").val();
                if(value != undefined && value != "undefined" && value != ""){
                    jsonStr = jsonStr +"\\"+"\"" + key +"\\"+ "\":"+"\\"+"\"" + value +"\\"+ "\"";
                    if(i < trList.length - 1){
                        jsonStr += ",\n";
                    }
                }
            }
            jsonStr += "}";
        }
    }else if (type == "3"){
        //最终json字符串的生成
        var jsonStr = "{\"biz_content\":{";
        for(var i=0;i<trList.length-1;i++){
            var tdArr = trList.eq(i).find("td");
            var key = tdArr.eq(0).html();
            var value = tdArr.eq(1).find("input").val();
            var btnId = tdArr.eq(3).find("button").attr("id");
            if(btnId == undefined || btnId == ''){
                jsonStr = jsonStr + "\"" + key +  "\":\"" + value + "\"";
            }else{
                if(value == ""){
                    jsonStr = jsonStr + "\"" + key + "\":" + "\"\"";
                }else{
                    if(btnId == "ext_data_btn"){
                        jsonStr = jsonStr + "\"" + key + "\":\"" + value + "\"";
                    }else{
                        jsonStr = jsonStr + "\"" + key + "\":" + value + "";
                    }
                }
            }
            if(i < trList.length-2){
                jsonStr += ",\n";
            }
        }
        jsonStr += "}}";
        $("#jsonTxt").val(jsonStr);
        $("#jsonContent").show();
        $("#jsonStyleBtn").show();
    }
    if(jsonStr == "{}"){
        jsonStr = "";
    }
    return jsonStr;
}

/**
 * json格式化
 */
var onCrtJsonClick = function(){
    var jsonStr = $("#jsonTxt").val();
    var jsonFormatStr = formatJson(jsonStr);
    console.log(jsonFormatStr);
    $("#jsonTxt").val(jsonFormatStr);
};

/**
 * 格式化json字符串
 * @param json
 * @param options
 * @returns {string}
 */
function formatJson(json, options) {
    var reg = null,
        formatted = '',
        pad = 0,
        PADDING = '    ';
    options = options || {};
    options.newlineAfterColonIfBeforeBraceOrBracket = (options.newlineAfterColonIfBeforeBraceOrBracket === true) ? true : false;
    options.spaceAfterColon = (options.spaceAfterColon === false) ? false : true;
    var replaceArr = new Array();
    if (typeof json !== 'string') {
        json = JSON.stringify(json);
    } else {
        json = JSON.parse(json);
        json = JSON.stringify(json);
        var i = 0;
        while (json.indexOf("\\\"") != -1){
            i++;
            var start = json.indexOf("\\");
            var leftParenthesis = json.indexOf("{",start-1);
            var rightParenthesis = json.indexOf("}",start-1);
            var arr = json.substr(leftParenthesis,(rightParenthesis-leftParenthesis)+1);
            replaceArr.push(arr);
            json = json.replace(""+arr+"","REPLACE"+i);
        }
    }

    reg = /([\{\}])/g;
    json = json.replace(reg, '\r\n$1\r\n');

    reg = /([\[\]])/g;
    json = json.replace(reg, '\r\n$1\r\n');
    reg = /(\,)/g;
    json = json.replace(reg, '$1\r\n');
    reg = /(\r\n\r\n)/g;
    json = json.replace(reg, '\r\n');
    reg = /\r\n\,/g;
    json = json.replace(reg, ',');
    if (!options.newlineAfterColonIfBeforeBraceOrBracket) {
        reg = /\:\r\n\{/g;
        json = json.replace(reg, ':{');
        reg = /\:\r\n\[/g;
        json = json.replace(reg, ':[');
    }
    if (options.spaceAfterColon) {
        reg = /\:/g;
        json = json.replace(reg, ':');
    }
    (json.split('\r\n')).forEach(function (node, index) {
            var i = 0,
                indent = 0,
                padding = '';
            if (node.match(/\{$/) || node.match(/\[$/)) {
                indent = 1;
            } else if (node.match(/\}/) || node.match(/\]/)) {
                if (pad !== 0) {
                    pad -= 1;
                }
            } else {
                indent = 0;
            }
            for (i = 0; i < pad; i++) {
                padding += PADDING;
            }
            formatted += padding + node + '\r\n';
            pad += indent;
        }
    );

    if(typeof(replaceArr) != undefined && replaceArr.length > 0){
        for(var i=0;i<replaceArr.length;i++){
            formatted = formatted.replace("REPLACE"+(i+1),replaceArr[i]);
        }
    }
    return formatted;
};



/**
 * 时间对象的格式化;
 */
Date.prototype.format = function(format) {
    /*
     * eg:format="YYYY-MM-dd hh:mm:ss";
     */
    var o = {
        "M+" :this.getMonth() + 1, // month
        "d+" :this.getDate(), // day
        "h+" :this.getHours(), // hour
        "m+" :this.getMinutes(), // minute
        "s+" :this.getSeconds(), // second
        "q+" :Math.floor((this.getMonth() + 3) / 3), // quarter
        "S" :this.getMilliseconds()
    }
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    }
    for ( var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
                : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}


