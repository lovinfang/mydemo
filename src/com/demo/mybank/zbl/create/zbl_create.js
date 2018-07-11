$(function(){
    //创建一级菜单
    var obj = getContentArray("mybank_credit_supplychain_trade_create");
    createTable(null,obj,"contentBody");

    function createSecondSubject(){
        var needCreate;
        try {
            needCreate = eval("need_create_content");
        }catch(e){};

        if(needCreate != undefined){
            var arr = needCreate.split("|");
            for(var i=0;i<arr.length;i++){
                var obj = getSubContentArray("mybank_credit_supplychain_trade_create",arr[i]);
                createTable(arr[i],obj,arr[i] + "ContentBody");
            }
        }
    }
    createSecondSubject();
    $("body").on("click","#jsonStyle",onCrtJsonClick);
});

var need_create_content="buyer|seller|pay_account|rcv_account|rcv_account_account_ext|ext_data";

var mybank_credit_supplychain_trade_create="trade_type|request_id|buyer|seller|pay_account|rcv_account|sale_pd_code|out_order_no|trade_amount|channel|expire_date|out_order_title|ext_data";
var mybank_credit_supplychain_trade_create_detail = {
    trade_type:"FACTORING：保理，PREPAYMENT：预付融资，CREDITPAY：信任付",
    request_id:"幂等编号，用于幂等控制，格式：机构ipRoleId_yyyymmddhhmmss_8位uniqId(226601231123123_20170911121110_12345678)",
    buyer:"买家会员信息(json格式)",
    seller:"卖家会员信息(json格式)",
    pay_account:"买家付款账户信息",
    rcv_account:"卖家收款账户信息",
    sale_pd_code:"销售产品码",
    out_order_no:"外部订单号，格式：机构ipRoleId_外部订单号(2266121002121_T11110000)",
    trade_amount:"交易金额（单位：元），只支持两位小数点的正数",
    channel:"渠道，枚举如下：TMGXBL：天猫供销保理，TYZBL：通用自保理，TMZBL：天猫自保理，DSCYFRZ：大搜车预付融资",
    expire_date:"账款到期支付日期(2017-09-20 12:11:00)",
    out_order_title:"订单标题",
    ext_data:"由具体业务决定填充内容，JSON格式"
}


var mybank_credit_supplychain_trade_create_buyer="use_type|ip_id|ip_role_id|site|site_user_id|site_login_id";
var mybank_credit_supplychain_trade_create_buyer_detail = {
    use_type:"身份使用类型；SITE:站点类型，MYBK:网商银行客户角色类型，MYBK_SITE:站点网商类型；如果是SITE，那么site为必填字段，site_user_id和site_login_id不能全部为空；如果是MYBK，那么ip_id和ip_role_id不能全部为空；如果是MYBK_SITE，那么ip_id，ip_role_id，site为必填，site_login_id和site_user_id至少填一项",
    ip_id:"网商银行参与者会员ID(226610000054341135809)",
    ip_role_id:"网商银行参与者会员角色ID(226610000054341135809)",
    site:"会员站点：ALIPAY:支付宝，MYBANK:网商银行，TAOBAO：淘宝",
    site_user_id:"会员站点ID(2088100110011001)",
    site_login_id:"站点会员登录ID(abc123)"
}

var mybank_credit_supplychain_trade_create_seller="use_type|site|site_login_id";
var mybank_credit_supplychain_trade_create_seller_detail = {
    use_type:"身份使用类型；SITE:站点类型，MYBK:网商银行客户角色类型，MYBK_SITE:站点网商类型；如果是SITE，那么site为必填字段，site_user_id和site_login_id不能全部为空；如果是MYBK，那么ip_id和ip_role_id不能全部为空；如果是MYBK_SITE，那么ip_id，ip_role_id，site为必填，site_login_id和site_user_id至少填一项",
    site:"会员站点：ALIPAY:支付宝，MYBANK:网商银行，TAOBAO：淘宝",
    site_login_id:"站点会员登录ID(abc123)"
}

var mybank_credit_supplychain_trade_create_pay_account="account_type|account_no|account_name";
var mybank_credit_supplychain_trade_create_pay_account_detail = {
    account_type:"账户类型,ALIPAY('ALIPAY','Alipay','支付宝账号','支付宝账号'),CURRENT('CURRENT','MayiBank','网商银行账号','网商银行账号'),OUT_BANK('OUT_BANK','OutBank','外部银行账号','外部银行账号')",
    account_no:"资金账号,支付宝2088开头或银行卡号(2088100010001122)",
    account_name:"账户名称，如银行账户名,支付宝手机号、email(xxxaa@alibaba-inc.com)"
}
var mybank_credit_supplychain_trade_create_rcv_account="account_type|account_no|account_name|account_ext";
var mybank_credit_supplychain_trade_create_rcv_account_detail = {
    account_type:"账户类型,ALIPAY('ALIPAY','Alipay','支付宝账号','支付宝账号'),CURRENT('CURRENT','MayiBank','网商银行账号','网商银行账号'),OUT_BANK('OUT_BANK','OutBank','外部银行账号','外部银行账号')",
    account_no:"资金账号,支付宝2088开头或银行卡号(2088100010001122)",
    account_name:"账户名称，如银行账户名,支付宝手机号、email(xxxaa@alibaba-inc.com)",
    account_ext:"json字符串，扩展信息，支付宝：alipayId，外部银行卡：银行机构名称、银行机构号（总行、支行）、对公/对私等"
}

var mybank_credit_supplychain_trade_create_ext_data = "rcv_login_id|rcv_name|rcvr_contact_name|rcv_contact_phone|rcv_contact_email|purchase_content|rcv_fee_rate_term_unit|rcv_fee_rate";
var mybank_credit_supplychain_trade_create_ext_data_detail = {
    rcv_login_id:"卖家登录号",
    rcv_name:"卖家名称",
    rcvr_contact_name:"卖家联系人姓名",
    rcv_contact_phone:"卖家联系人电话",
    rcv_contact_email:"卖家联系人邮箱",
    purchase_content:"采购内容, 对应购买商品类型，可以根据业务属性写一个字段",
    rcv_fee_rate_term_unit:"收款方费率期限单位 是DAY（固定），日费率信息 就是年费率/360 计算得到的",
    rcv_fee_rate:"要收卖家的费率不能大于0.0006,由买家设置的。"
}

var mybank_credit_supplychain_trade_create_rcv_account_account_ext = "bankBranchFipCode|bankCardCategory|bankName|alipayId";
var mybank_credit_supplychain_trade_create_rcv_account_account_ext_detail = {
    bankBranchFipCode:"分行号",
    bankCardCategory:"账户类型(对公/对私) 1：对私,2：对公",
    bankName:"开户行名称",
    alipayId:"支付宝id"
}

var mybank_credit_supplychain_trade_create_defaultvalue = {
    "request_id":"IPROLEID_DATEFORMAT_RANDOM8",
    "trade_type":"FACTORING",
    "sale_pd_code":"01025200100000000002",
    "channel":"TYZBL",
    "out_order_no":"IPROLEID_RANDOM8",
    "trade_amount":"10.00",
    "expire_date":defaultDate(),
    "EDITCOLUMN":"trade_amount|expire_date"
}

var mybank_credit_supplychain_trade_create_buyer_defaultvalue = {
    "site":"ALIPAY",
    "use_type":"MYBK_SITE",
    "EDITCOLUMN":"use_type"
}

var mybank_credit_supplychain_trade_create_seller_defaultvalue = {
    "site":"ALIPAY",
    "use_type":"SITE",
    "EDITCOLUMN":"use_type"
}

var mybank_credit_supplychain_trade_create_pay_account_defaultvalue = {
    "account_type":"OUT_BANK"
}

var mybank_credit_supplychain_trade_create_rcv_account_defaultvalue = {
    "account_type":"OUT_BANK"
}

var mybank_credit_supplychain_trade_create_ext_data_defaultvalue = {
    "rcv_fee_rate":"0.00003",
    "rcv_fee_rate_term_unit":"DAY"
}

var onBtnJsonClick = function () {
    //1、将rcv_account.account_ext的参数生成到rcv_account的account_ext中
    var rcvActExtTBody = $("#rcv_account_account_extContent").find("tbody");
    var rcvActExtJsonStr = createJsonStr("2",rcvActExtTBody);
    $("#rcv_account_account_ext").val(rcvActExtJsonStr);

    //2、将ext_data的参数生成到ext_data中
    var extDataTBody = $("#ext_dataContent").find("tbody");
    var extJsonStr = createJsonStr("2",extDataTBody);
    $("#ext_data").val(extJsonStr);

    //3、将buyer,seller,pay_account,rcv_account的参数生成到contentBody对应的input中
    var list= "buyer|seller|pay_account|rcv_account";
    var tmpArr = list.split("|");
    for(var i in tmpArr){
        var tBody = $("#"+tmpArr[i]+"Content").find("tbody");
        var jsonStr = createJsonStr("1",tBody);
        $("#"+tmpArr[i]).val(jsonStr);
    }

    //生成最后的json字符串
    var contentTBody = $("#content").find("tbody");
    var contentJsonStr = createJsonStr("3",contentTBody);
}

var onBtnUnJsonClick = function(){
    var id = $(this).attr("id");
    id = id.substr(0,id.lastIndexOf("_"));
    var trList = $("#contentBody").children("tr");
    for(var i=0;i<trList.length;i++){
        var tdArr = trList.eq(i).find("td");
        var key = tdArr.eq(0).html();
        if(key == id){
            tdArr.eq(1).find("input").attr("value","");
        }
    }
    $(this).parent().parent().parent().parent().parent().hide();
}

var onInputBlur = function () {
    var id = $(this).attr("id");
    if(id == "buyer_ip_role_id"){
        var val = $(this).val();
        //修改request_id的值
        var requestIdVal = $("#contentBody #request_id").val();
        requestIdVal = requestIdVal.replace("IPROLEID",val);
        $("#contentBody #request_id").val(requestIdVal);
        //修改out_order_id的值
        var outOrderId = $("#contentBody #out_order_no").val();
        outOrderId = outOrderId.replace("IPROLEID",val);
        $("#contentBody #out_order_no").val(outOrderId);
     }else if(id == "buyer_site_user_id"){
        var val = $(this).val();
        //修改pay_account的值的
        $("#pay_accountContentBody #pay_account_account_no").attr("value",val);
    }else if(id == "seller_site_login_id"){
        var val = $(this).val();
        $("#rcv_accountContentBody #rcv_account_account_no").attr("value",val);
        $("#ext_dataContentBody #ext_data_rcv_login_id").attr("value",val);
        $("#ext_dataContentBody #ext_data_rcv_contact_phone").attr("value",val);
        $("#ext_dataContentBody #ext_data_rcv_contact_email").attr("value",val+"126@.com");
    }else if(id == "rcv_account_account_name"){
        var val = $(this).val();
        $("#ext_dataContentBody #ext_data_rcv_name").attr("value",val);
        $("#ext_dataContentBody #ext_data_rcvr_contact_name").attr("value",val);
    }
}

/**
 *
 * @returns {string}
 */
function defaultDate(){
    var date = new Date();
    date.setDate(date.getDate()+15);
    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
}


function sellerOnfocus() {
    var str = $("#sellerInfo").val();
    if(str == "请输入卖家相关参数信息"){
        $("#sellerInfo").val("");
    }
}

function sellerOnblur() {
    var str = $("#sellerInfo").val();
    if("" == str){
        $("#sellerInfo").val("请输入卖家相关参数信息");
    }else{
        analysisStr("seller",str);
    }
}

function buyerOnfocus() {
    var str = $("#buyerInfo").val();
    if(str == "请输入买家相关参数信息"){
        $("#buyerInfo").val("");
    }
}

function buyerOnblur() {
    var str = $("#buyerInfo").val();
    if("" == str){
        $("#buyerInfo").val("请输入买家相关参数信息");
    }else{
        analysisStr("buyer",str);
    }
}

/**
 * 解析字符串
 */
function analysisStr(type,str){
    if(str != undefined && str != "undefined" && str != ""){
        var tmpArr = str.split(",");
        for(var i in tmpArr){
            var str = tmpArr[i];
            var paramArr = str.split(":");
            if(paramArr != ""){
                if(type == "buyer"){
                    var key = Trim(paramArr[0]);
                    var value = Trim(paramArr[1]);
                    if(key == "支付宝昵称"){
                        $("#buyer_site_login_id").val(value);
                        $("#pay_account_account_name").val(value);
                    }else if(key == "银行ipId"){
                        $("#buyer_ip_id").val(value);
                    }else if(key == "银行roleId"){
                        $("#buyer_ip_role_id").val(value);
                        var outOrderId = $("#contentBody #out_order_no").val();
                        outOrderId = outOrderId.replace("IPROLEID",value);
                        $("#contentBody #out_order_no").val(outOrderId);
                        var requestId = $("#contentBody #request_id").val();
                        requestId = requestId.replace("IPROLEID",value);
                        $("#contentBody #request_id").val(requestId);
                    }else if(key == "支付宝Id"){
                        $("#buyer_site_user_id").val(value);
                        $("#pay_account_account_no").val(value);
                    }
                }else if(type == "seller"){
                    var key = Trim(paramArr[0]);
                    var value = Trim(paramArr[1]);
                    if(key == "支付宝昵称"){
                        $("#seller_site_login_id").val(value);
                        $("#rcv_account_account_no").val(value);
                        $("#ext_data_rcv_login_id").val(value);
                        $("#ext_data_rcv_contact_phone").val(value);
                        $("#ext_data_rcv_contact_email").val(value+"@qq.com");
                    }else if(key == "用户姓名"){
                        $("#rcv_account_account_name").val(value);
                        $("#ext_data_rcv_name").val(value);
                        $("#ext_data_rcvr_contact_name").val(value);
                    }
                }
            }
        }
    }
}

function Trim(str)
{
    return str.replace(/(^\s*)|(\s*$)/g, "");
}








