$(function(){
    //创建一级菜单
    var obj = getContentArray("trade_cancel_event");
    createTable(null,obj,"contentBody");
    $("body").on("click","#jsonStyle",onCrtJsonClick);
});

var trade_cancel_event ="request_id|dataOrgId|ipId|ipRoleId|outOrderNo|success";
var trade_cancel_event_detail = {
    request_id:"幂等流水号",
    dataOrgId:"机构在网商银行注册的会员编号",
    ipId:"买家在网商银行注册的会员编号",
    ipRoleId:"买家在网商银行注册的会员角色编号",
    outOrderNo:"订单编号",
    success:"提前收款结果:TRUE(成功),FALSE(失败)"
}


var trade_cancel_event_defaultvalue = {
    "request_id":"IPROLEID_DATEFORMAT_RANDOM8",
    "success":"TRUE"
}


function createJsonOnfocus() {
    var str = $("#jsonInfo").val();
    if(str == "请输入交易创建json字符串"){
        $("#jsonInfo").val("");
    }
}

function createJsonOnblur() {
    var str = $("#jsonInfo").val();
    if(str == ""){
        $("#jsonInfo").val("请输入交易创建json字符串");
    }else{
        //解析json字符串
        try{
            var jsonObj = JSON.parse(str);
            var bizContent;
            for(var item in jsonObj){
                bizContent = jsonObj[item];
            }
            $("#outOrderNo").val(bizContent.out_order_no);

            var buyerObj = bizContent.buyer;
            $("#dataOrgId").val(buyerObj.ip_id);
            $("#ipId").val(buyerObj.ip_id);
            $("#ipRoleId").val(buyerObj.ip_role_id);

        }catch(e){
            alert("输入创建交易信息不是JSON格式串,请确认后再试!");
            $("#jsonInfo").val("请输入交易创建json字符串");
        }
    }
}

var onInputBlur = function () {}

var onBtnJsonClick = function () {
    var requestIdVal = $("#contentBody #request_id").val();
    var val = $("#ipRoleId").val();
    if(requestIdVal.indexOf("IPROLEID") != -1){
        requestIdVal = requestIdVal.replace("IPROLEID",val);
    }else{
        var requestArr = requestIdVal.split("_");
        if(requestArr.length == 3){
            requestIdVal = val + "_" + requestArr[1] + "_" + requestArr[2];
        }
    }

    $("#contentBody #request_id").val(requestIdVal);
    //生成最后的json字符串
    var contentTBody = $("#content").find("tbody");
    var contentJsonStr = createJsonStr("3",contentTBody);
}