$(function(){
    $("select").change(function(){
        $("#content").hide();
        $("#contentBody").empty();
        $("#jsonTxt").val("");
        $("#jsonContent").hide();
        $("#subContent").hide();
        $("#jsonStyleBtn").hide();
        $("#extContent").hide();
        $("input[type='text']").attr("value","");
    });

    $("#btnConfirm").click(function(){
        var business = $("#business").val();
        var interfaceName = $("#inerfaceName").val();
        $("#contentBody").empty();

        var busInterface = business+"_"+interfaceName;
        getArray(busInterface);
        $("#content").show();
    });

    $("#btnCancel").click(function(){
        $("#content").hide();
        $("#subContent").hide();
        $("#extContent").hide();
        $("#business").val("zbl");
        $("#inerfaceName").val("create");
        $("#contentBody").empty();
        $("#jsonTxt").val("");
        $("#jsonContent").hide();
    });

    function getArray(name){
        var biz_name;
        switch(name){
            case "zbl_create":
                biz_name = "mybank_credit_supplychain_trade_create";
                break;
            case "zbl_pay":
                biz_name = "mybank_credit_supplychain_trade_pay";
                break;
        }
        var obj = getContentArray(biz_name);
        createTable(null,obj,"contentBody");
    }

    function getSubArray(id){
        id = id.substr(0,id.lastIndexOf("_"));
        var obj;
        if("ext_data" == id){
            obj = new Array();
        }else{
            var interfaceName = $("#inerfaceName").find("option:selected").text();
            var subBtnParam = interfaceName.replace(new RegExp(/\./g),"_")+"_"+id;
            obj = getSubContentArray(subBtnParam);
        }
        createTable(id,obj,"subContent");
    }

    function createTable(divName,arrayObj,idDiv){
        if(arrayObj.length ==0){
            //添加 ext扩展字段
            $("#subContent").hide();
            $("#extContent").show();
            $("#extBody").empty();
            $("#jsonTxt").val("");
            $("#jsonContent").hide();
            $("#jsonStyleBtn").hide();
        }else{
            $("#extContent").hide();
            for(var i=0;i<arrayObj.length;i++){
                var $trTemp = $("<tr></tr>");
                for(var j=0;j<arrayObj[i].length;j++){
                    if(j == 1){
                        $trTemp.append("<td><input type='text' style='border:none' id='"+arrayObj[i][0]+"' name='"+arrayObj[i][0]+"' value=''></td>");
                    }else if(j == 3){
                        if("Array" == arrayObj[i][j]){
                            var btnId = arrayObj[i][0]+"_btn";
                            $trTemp.append("<td><button type='button' id='"+btnId+"'>添加二级参数</button></td>");
                            $("body").on("click","#"+btnId,onBtnClick);
                        }
                    }else{
                        $trTemp.append("<td>"+ arrayObj[i][j]+"</td>");
                    }
                }

                if(idDiv == "contentBody"){
                    $trTemp.appendTo("#contentBody");
                    if(i == arrayObj.length -1){
                        //添加生成json按钮行
                        var $trTemp = $("<tr></tr>");
                        $trTemp.append("<td><button type='button' id='createJson'>生成参数</button></td>");
                        $trTemp.append("<td><button type='button' id='cancelJson'>重新填值</button></td>");
                        $trTemp.appendTo("#contentBody");
                        $("body").on("click","#createJson",onBtnJsonClick);
                        $("body").on("click","#cancelJson",onCancelJsonClick);
                    }
                }else if(idDiv == "subContent"){
                    $trTemp.appendTo("#subContentBody");
                    if(i == arrayObj.length -1){
                        //添加生成json按钮行
                        var $trTemp = $("<tr></tr>");
                        var crtsonBtn = divName+"_crtjson";
                        var cancelJson = divName+"_canceljson";
                        $trTemp.append("<td><button type='button' id='"+crtsonBtn+"'"+">确认</button></td>");
                        $trTemp.append("<td><button type='button' id='"+cancelJson+"'"+">取消</button></td>");
                        $trTemp.appendTo("#subContentBody");
                        $("body").on("click","#"+crtsonBtn,onBtnJsonClick);
                        $("body").on("click","#"+cancelJson,onBtnUnJsonClick);
                    }
                    $("#subContent").show();
                }
            }
        }
    }

    var onBtnClick = function() {
        $("#subContent").hide();
        $("#subContentBody").empty();
        var id = $(this).attr("id");
        getSubArray(id);
    }

    var onBtnJsonClick = function (){
        var id = $(this).attr("id");
        var tBody;
        if("ext_data_crtjson" == id){
            tBody = $(this).parent().parent().parent().parent().find("tbody");
        }else{
            tBody = $(this).parent().parent().parent();
        }
        var trList = tBody.children("tr");
        if("createJson" == id){
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
                        jsonStr = jsonStr + "\"" + key + "\":" + value;
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
            console.log(jsonStr);
        }else if ("ext_data_crtjson" == id){
            //二级参数扩展字段的json字符串生成
            var jsonStr = "{";
            if(trList.length == 0){
                jsonStr = "";
            }else{
                for(var i=0;i<trList.length;i++){
                    var tdArr = trList.eq(i).find("td");
                    var key = tdArr.eq(0).find("input").val();
                    var value = tdArr.eq(1).find("input").val();
                    jsonStr = jsonStr + "\"" + key +  "\":\"" + value + "\"";
                    if(i < trList.length - 1){
                        jsonStr += ",\n";
                    }
                }
                jsonStr += "}";
            }
            //填充对应的div
            var divName = id.substr(0,id.lastIndexOf("_"));
            $("#contentBody #"+divName+"").attr("value",jsonStr);
        }else{
            //二级参数非扩展字段的json字符串生成
            var jsonStr = "{";
            for(var i=0;i<trList.length-1;i++){
                var tdArr = trList.eq(i).find("td");
                var key = tdArr.eq(0).html();
                var value = tdArr.eq(1).find("input").val();
                jsonStr = jsonStr + "\"" + key +  "\":\"" + value + "\"";
                if(i < trList.length-2){
                    jsonStr += ",\n";
                }
            }
            jsonStr += "}";
            //填充对应的div
            var divName = id.substr(0,id.lastIndexOf("_"));
            $("#contentBody #"+divName+"").attr("value",jsonStr);
        }
    }

    function onCancelJsonClick() {
        $("input[type='text']").attr("value","");
        $("#jsonTxt").val("");
        $("#jsonContent").hide();
        $("#jsonStyleBtn").hide();
        $("#extContent").hide();
    }

    var onBtnUnJsonClick = function () {
        $("#subContent").hide();
        $("#subContentBody").empty();
        $("#jsonTxt").val("");
        $("#jsonContent").hide();
        $("#jsonStyleBtn").hide();
    }

    $("#jsonStyle").click(function () {
        var jsonStr = $("#jsonTxt").val();
        var jsonFormatStr = formatJson(jsonStr);
        console.log(jsonFormatStr);
        $("#jsonTxt").val(jsonFormatStr);
    });

    $("#addExDataBtn").click(function(){
        var $trTemp = $("<tr></tr>");
        $trTemp.append("<td><input type='text' style='border:none'/></td>");
        $trTemp.append("<td><input type='text' style='border:none'/></td>");
        $trTemp.append("<td><button type='button' id='deleteTr''>删除此行</button></td>");
        $trTemp.appendTo("#extBody");
        $("body").on("click","#deleteTr",deleteTr);
        $("body").on("click","#ext_data_crtjson",onBtnJsonClick);
    });

    function deleteTr(){
        $(this).parent().parent("tr").remove();
    }

    $("#ext_data_cancel_crtjson").click(function () {
        alert("cancelExtData 取消!");
        $("#extBody").empty();
        $("#extContent").hide();
        $("#contentBody").find("#ext_data").attr("value","");
    });
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
        if (typeof json !== 'string') {
            json = JSON.stringify(json);
        } else {
            json = JSON.parse(json);
            json = JSON.stringify(json);
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
        return formatted;
    };
});

