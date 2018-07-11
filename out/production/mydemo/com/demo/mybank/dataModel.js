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
var mybank_credit_supplychain_trade_create_seller="use_type|ip_id|ip_role_id|site|site_user_id|site_login_id";
var mybank_credit_supplychain_trade_create_seller_detail = {
    use_type:"身份使用类型；SITE:站点类型，MYBK:网商银行客户角色类型，MYBK_SITE:站点网商类型；如果是SITE，那么site为必填字段，site_user_id和site_login_id不能全部为空；如果是MYBK，那么ip_id和ip_role_id不能全部为空；如果是MYBK_SITE，那么ip_id，ip_role_id，site为必填，site_login_id和site_user_id至少填一项",
    ip_id:"网商银行参与者会员ID(226610000054341135809)",
    ip_role_id:"网商银行参与者会员角色ID(226610000054341135809)",
    site:"会员站点：ALIPAY:支付宝，MYBANK:网商银行，TAOBAO：淘宝",
    site_user_id:"会员站点ID(2088100110011001)",
    site_login_id:"站点会员登录ID(abc123)"
}
var mybank_credit_supplychain_trade_create_pay_account="account_type|account_no|account_name|account_ext";
var mybank_credit_supplychain_trade_create_pay_account_detail = {
    account_type:"账户类型,ALIPAY('ALIPAY','Alipay','支付宝账号','支付宝账号'),CURRENT('CURRENT','MayiBank','网商银行账号','网商银行账号'),OUT_BANK('OUT_BANK','OutBank','外部银行账号','外部银行账号')",
    account_no:"资金账号,支付宝2088开头或银行卡号(2088100010001122)",
    account_name:"账户名称，如银行账户名,支付宝手机号、email(xxxaa@alibaba-inc.com)",
    account_ext:"json字符串，扩展信息，支付宝：alipayId，外部银行卡：银行机构名称、银行机构号（总行、支行）、对公/对私等"
}
var mybank_credit_supplychain_trade_create_rcv_account="account_type|account_no|account_name|account_ext";
var mybank_credit_supplychain_trade_create_rcv_account_detail = {
    account_type:"账户类型,ALIPAY('ALIPAY','Alipay','支付宝账号','支付宝账号'),CURRENT('CURRENT','MayiBank','网商银行账号','网商银行账号'),OUT_BANK('OUT_BANK','OutBank','外部银行账号','外部银行账号')",
    account_no:"资金账号,支付宝2088开头或银行卡号(2088100010001122)",
    account_name:"账户名称，如银行账户名,支付宝手机号、email(xxxaa@alibaba-inc.com)",
    account_ext:"json字符串，扩展信息，支付宝：alipayId，外部银行卡：银行机构名称、银行机构号（总行、支行）、对公/对私等"
}
var mybank_credit_supplychain_trade_create_ext_data="ext_data";


var mybank_credit_supplychain_trade_pay="trade_type|request_id|buyer|pay_amount|out_order_no|sale_pd_code|channel|ext_data";
var mybank_credit_supplychain_trade_pay_detail = {
    trade_type:"FACTORING:保理,PREPAYMENT：预付融资,CREDITPAY:信任付",
    request_id:"幂等编号,用于幂等控制,格式 instIpRoleId_yyyymmddhhmmss_8位uniqId(226601231123123_20170911121110_12345678)",
    buyer:"买家会员信息(json格式)",
    pay_amount:"支付金额（单位：元）,只支持两位小数点的正数",
    out_order_no:"外部订单号,格式:机构ipRoleId_外部订单号",
    sale_pd_code:"销售产品码",
    channel:"渠道，TMGXBL:天猫供销保理:TYZBL:通用自保理,TMZBL:天猫自保理",
    ext_data:"业务扩展字段,JSON格式"
}
var mybank_credit_supplychain_trade_pay_buyer="use_type|ip_id|ip_role_id|site|site_user_id|site_login_id";
var mybank_credit_supplychain_trade_pay_buyer_detail = {
    use_type:"身份使用类型；SITE:站点类型，MYBK:网商银行客户角色类型，MYBK_SITE:站点网商类型；如果是SITE，那么site为必填字段，site_user_id和site_login_id不能全部为空；如果是MYBK，那么ip_id和ip_role_id不能全部为空；如果是MYBK_SITE，那么ip_id，ip_role_id，site为必填，site_login_id和site_user_id至少填一项",
    ip_id:"网商银行参与者会员ID(226610000054341135809)",
    ip_role_id:"网商银行参与者会员角色ID(226610000054341135809)",
    site:"会员站点：ALIPAY:支付宝，MYBANK:网商银行，TAOBAO：淘宝",
    site_user_id:"会员站点ID(2088100110011001)",
    site_login_id:"站点会员登录ID(abc123)"
}
mybank_credit_supplychain_trade_pay_ext_data="ext_data";

function getContentArray(interfaceName) {
    var content = new Array();
    var obj = eval(interfaceName);

    if(obj != undefined || obj != ''){
        var paramsArray = obj.split("|");
        for(var i = 0; i < paramsArray.length; i++){
            var arrayStr = new Array();

            var paramName = paramsArray[i];
            arrayStr[0] = paramName;
            arrayStr[1] = "";
            var paramsDetail = eval(interfaceName+"_detail");
            arrayStr[2] = paramsDetail[paramName];
            var subContentArr = "";
            try{
                subContentArr = eval(interfaceName+"_"+paramName);
            }catch(e){}
            if(typeof(subContentArr) != "undefined" && subContentArr != undefined && subContentArr != ""){
                arrayStr[3] = "Array";
            }
            content[i] = arrayStr;
        }
    }
    return content;
}

function getSubContentArray(paramName){
    var subContentArray = new Array();
    if(typeof(paramName) == "undefined" || paramName == "undefined"){
        return;
    }
    var array = eval(paramName);
    if(array != undefined || array != ''){
        var params = array.split("|");
        for(var i = 0; i<params.length; i++){
            var arr = new Array();
            arr[0] = params[i];
            arr[1] = "";
            var paramsDetail = eval(paramName+"_detail");
            arr[2] = paramsDetail[params[i]];
            subContentArray.push(arr);
        }
    }
    return subContentArray;
}