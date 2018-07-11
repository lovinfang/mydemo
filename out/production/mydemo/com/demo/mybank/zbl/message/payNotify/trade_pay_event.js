$(function(){
    //创建一级菜单
    var obj = getContentArray("trade_pay_event");
    createTable(null,obj,"contentBody");
    $("body").on("click","#jsonStyle",onCrtJsonClick);
});

var trade_pay_event ="request_id|dataOrgId|ipId|ipRoleId|outOrderNo|success|amount|accountNo|accountType";
var trade_pay_event_detail = {
    request_id:"幂等流水号",
    dataOrgId:"机构在网商银行注册的会员编号",
    ipId:"买家在网商银行注册的会员编号",
    ipRoleId:"买家在网商银行注册的会员角色编号",
    outOrderNo:"订单编号",
    success:"提前收款结果:TRUE(成功),FALSE(失败)",
    amount:"买家实还额度",
    accountNo:"买家付款账号，买家支付宝账号",
    accountType:"买家付款账号类型:ALIPAY(支付宝)"
}


var trade_pay_event_defaultvalue = {
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
            $("#amount").val(bizContent.pay_amount);
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
    var val = $("#buyer_ip_role_id").val();
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