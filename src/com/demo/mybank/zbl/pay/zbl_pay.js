$(function(){
    //创建一级菜单
    var obj = getContentArray("mybank_credit_supplychain_trade_pay");
    createTable(null,obj,"contentBody");

    function createSecondSubject(){
        var needCreate;
        try {
            needCreate = eval("need_create_content");
        }catch(e){};

        if(needCreate != undefined){
            var arr = needCreate.split("|");
            for(var i=0;i<arr.length;i++){
                var obj = getSubContentArray("mybank_credit_supplychain_trade_pay",arr[i]);
                createTable(arr[i],obj,arr[i] + "ContentBody");
            }
        }
    }
    createSecondSubject();
    $("body").on("click","#jsonStyle",onCrtJsonClick);
});

var need_create_content="buyer|ext_data";

var mybank_credit_supplychain_trade_pay="trade_type|request_id|buyer|pay_amount|sale_pd_code|out_order_no|channel|ext_data";
var mybank_credit_supplychain_trade_pay_detail = {
    trade_type:"FACTORING：保理",
    request_id:"幂等编号，用于幂等控制，格式ip_role_id_yyyymmddhhmmss_8位uniqId",
    buyer:"买家信息，参照买家参数(json格式)",
    pay_amount:"支付金额(单位:元)只支持两位小数的正数",
    sale_pd_code:"销售产品码 例如: 01025200100000000002",
    out_order_no:"外部订单号,格式：机构ipRoleId_为前缀的外部订单号",
    channel:"渠道，TMGXBL：天猫供销保理，TYZBL：通用自保理，TMZBL：天猫自保理",
    ext_data:"业务扩展字段"
}

var mybank_credit_supplychain_trade_pay_defaultvalue = {
    "request_id":"IPROLEID_DATEFORMAT_RANDOM8"
}

var mybank_credit_supplychain_trade_pay_buyer="use_type|ip_id|ip_role_id|site|site_user_id|site_login_id";
var mybank_credit_supplychain_trade_pay_buyer_detail = {
    use_type:"基础类型：SITE:站点类型",
    ip_id:"网商会员ID,即银行参与者id，是在网商银行创建会员后生成的id，网商银行会员的唯一标识(226610000054341135809)",
    ip_role_id:"网商会员角色ID，即银行参与者角色id，是在网商银行创建会员后生成的角色id，网商银行会员角色的唯一标识(226610000054341135809)",
    site:"站点，ALIPAY（支付宝）",
    site_user_id:"会员站点ID(2088100110011001)",
    site_login_id:"站点会员登录ID(abc123)"
}

var mybank_credit_supplychain_trade_pay_ext_data = "payableRepayType";
var mybank_credit_supplychain_trade_pay_ext_data_detail = {
    payableRepayType:"付款类型：preRepay(提前付款);currentRepay(到期付款);ovdRepay(逾期付款)"
}

var mybank_credit_supplychain_trade_pay_ext_data_defaultvalue = {
    "payableRepayType":"preRepay",
    "EDITCOLUMN":"payableRepayType"
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
            $("#trade_type").val(bizContent.trade_type);
            $("#pay_amount").val(bizContent.trade_amount);
            $("#pay_amount").val(bizContent.trade_amount);
            $("#sale_pd_code").val(bizContent.sale_pd_code);
            $("#out_order_no").val(bizContent.out_order_no);
            $("#channel").val(bizContent.channel);
            var buyerObj = bizContent.buyer;
            $("#buyer_use_type").val(buyerObj.use_type);
            $("#buyer_use_type").val(buyerObj.use_type);
            $("#buyer_ip_id").val(buyerObj.ip_id);
            $("#buyer_ip_role_id").val(buyerObj.ip_role_id);
            $("#buyer_site").val(buyerObj.site);
            $("#buyer_site").val(buyerObj.site);
            $("#buyer_site_user_id").val(buyerObj.site_user_id);
            $("#buyer_site_user_id").val(buyerObj.site_user_id);
            $("#buyer_site_login_id").val(buyerObj.site_login_id);
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

    //1、将ext_data的参数生成到contentBody中的ext_data中
    var extDataTBody = $("#ext_dataContent").find("tbody");
    var extJsonStr = createJsonStr("2",extDataTBody);
    $("#ext_data").val(extJsonStr);

    //2、将buyer的数据写入到buyer中
    var buyerTBody = $("#buyerContent").find("tbody");
    var buyerJsonStr = createJsonStr("1",buyerTBody);
    $("#buyer").val(buyerJsonStr);

    //3、生成最后的json字符串
    var contentTBody = $("#content").find("tbody");
    var contentJsonStr = createJsonStr("3",contentTBody);
}

