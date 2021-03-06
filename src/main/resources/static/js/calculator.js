$(function () {
    var $isSingle =  $("#isSingle");
    var $pd_amount = $("#pd_amount");
    var $amount_num = $("#amount_num");
    var $s_std = $("#s_std_container");
    var $MorP = $(".MorP");
    var $add_BTN = $("#add_policy");
    var $plc_container = $("#plc_container");
    var count = 2;
    var $plc_num = $("#plc_num");
    var $heightlight = $(".hightlight");
    var $multi_form = $("#multi_product");
    var $single_form = $("#single_product");
    var $goods = $("#goods");
    var $spec = $("#spec");
    var $price = $("#price");
    var $good_name  = $("#good_name");
    var $good_selected = $goods.find("option:selected");
    var $add_pd_BTN = $("#add_pd_BTN");
    var $products_container = $("#products_container");
    var m_count =2;
    var $pd1 = $("#pd1");
    var $spec1 = $("#spec1");
    var $price1 = $("#price1");
    var $confirm_pd_BTN = $("#confirm_pd_BTN");
    var $cancle_pd_BTN = $("#cancle_pd_BTN");
    var $m_commit = $("#m_commit");
    var $products_msg = $("#products_msg");
    var $con_container  = $(".con_container");

    //初始化页面信息
    $pd_amount.hide();
    $heightlight.css("color","red").css("font-weight","900");
    $multi_form.hide();
    $good_name.val($good_selected.html());
    $products_msg.hide();

    //条件中若出现多个商品进行运算，则添加商品信息
    $con_container.on("change",".math",function () {
        //若该项不是选择无，添加条件
        if ($(this).val() != 0 ){
            var tmp_array = new Array();
            //处理字符串
            tmp_array = $(this).attr("name").split("_");
            var next_symbol = tmp_array[0]+"_";
            var prefix = parseInt(tmp_array[0].substring(4,6));
            var suffix = parseInt(tmp_array[1]);
            next_symbol = "#" + next_symbol+(suffix +1);
            console.log(next_symbol);
            console.log($(next_symbol).length);
            if ($(next_symbol).length == 0){
                    suffix = suffix+1;
                $(this).after("<select id=\"con_pd"+prefix+"_"+suffix+"\" name=\"con_pd"+prefix+"_"+suffix+"\">\n" +
                    "                <option value=\"1\">--请先确认商品--</option>\n" +
                    "            </select>\n" +
                    "            <select id=\"con_std"+prefix+"_"+suffix+"\" name=\"con_std"+prefix+"_"+suffix+"\">\n" +
                    "                <option value=\"0\">数量</option>\n" +
                    "                <option value=\"1\">单价</option>\n" +
                    "                <option value=\"2\">小计</option>\n" +
                    "            </select>\n" +
                    "            <select class=\"math\" name=\"math"+prefix+"_"+suffix+"\" id='math"+prefix+"_"+suffix+"'>\n" +
                    "                <option value=\"0\">无</option>\n" +
                    "                <option value=\"1\">+</option>\n" +
                    "                <option value=\"2\">-</option>\n" +
                    "                <option value=\"3\">*</option>\n" +
                    "                <option value=\"4\">/(取整)</option>\n" +
                    "                <option value=\"5\">/(四舍五入)</option>\n" +
                    "            </select>");
            }
        }
    });

    //点击取消确认按钮，取消锁定商品，同时商品下拉菜单可用，此按钮、计算按钮不可用，确认按钮可用
    $cancle_pd_BTN.click(function () {
        var $product_select  = $(".product").children();
        $product_select.removeAttr("disabled");
        $confirm_pd_BTN.removeAttr("disabled");
        $add_pd_BTN.removeAttr("disabled");
        $m_commit.attr("disabled","disabled");
        $(this).attr("disabled","disabled");
        return false;
    });

    //点击确定商品按钮，记录已选商品并去重，同时商品下拉菜单不可用，此按钮不可用，取消确认和计算按钮可用
    $confirm_pd_BTN.click(function () {
        var product_select = $(".product select");
        //隐藏信息栏
        $products_msg.hide();
        //创建数组
        var goods_array = new Array();
        product_select.each(function () {
           goods_array.push($(this).find("option:selected").val()+","+$(this).find("option:selected").html());
        });
        var temp_num  = goods_array.length;
        //去重
        $.unique(goods_array.sort());
        //若不存在重复项
        if (temp_num == goods_array.length){
        var $product_select  = $(".product").children();
        $product_select.attr("disabled","disabled");
        $cancle_pd_BTN.removeAttr("disabled");
        $m_commit.removeAttr("disabled");
        $add_pd_BTN.attr("disabled","disabled");
        $(this).attr("disabled","disabled");
        //调用fill_products()，将选中商品填入下文选项中
        $(this).fill_products($("#con_pd1_1"),goods_array);
        }else {
            //若商品重复，取消操作，提示信息
            $products_msg.html("商品存在重复！").show();
        }
        return false;
    });

    //商品改变时，填入相应信息，使用事件委派
    $products_container.on("change","select",function () {
        //尾数最大999
       var num = $(this).attr("id").substring(2,5);
       var $spec_temp = $("#spec" + num);
       var $price_temp = $("#price"+num);
       $(this).get_detail($(this),$spec_temp,$price_temp);
    });

    //点击删除按钮删除当前商品行，使用事件委派
    $products_container.on("click",".del_pd_BTN",function () {
       $(this).parent().remove();
       m_count = m_count-1;
        if(m_count>1){
            var prev_del_btn = "#del_pd_BTN"+ (m_count-1);
            $(prev_del_btn).show();
        }
       return false;
    });

    //点击添加商品按钮添加商品
    $add_pd_BTN.click(function () {
        $products_container.append("        <div class=\"product\" >\n" +
            "            <select id='pd"+m_count+"'>\n" +
            "            </select>\n" +
            "            <label>1*</label><input type='text' size='1' id='spec"+m_count+"'>\n" +
            "            <label>单支价格</label><input type='text' size='1' id='price"+m_count+"'>"+
            "            <button class='del_pd_BTN' id='del_pd_BTN"+m_count+"'>删除</button>"+
            "        </div>");
        $.get("goods_json",function (data) {
            //获取商品ID和商品名的JSON字符串并转为JSON对象
            var jsondata=eval("("+data+")");
            var pd = "#pd" + m_count;
            $.each(jsondata.goods,function (i,n) {
               $(pd).append("<option value=\""+n.id+"\" >"+n.spell+"</option>");
            });
            //计数加1
            var $this_select  = $("#pd"+(m_count));
            $(this).get_detail($this_select,$("#spec"+(m_count)),$("#price"+(m_count)));
            m_count = m_count+1;

        });
        //隐藏上一个删除按钮
        if(m_count>2){
            var prev_del_btn = "#del_pd_BTN"+ (m_count-1);
            $(prev_del_btn).hide();
        }

        return false;
    });

    //切换商品时，自动填入对应信息
    $s_std.on("change","select",function () {
        $(this).get_detail($goods,$spec,$price);
    });

    //点击添加策略按钮，新增一行策略
    $add_BTN.click(function () {

        $plc_container.append("<div id=\'plc"+count+ "\'>\n"+
            "            <label>策略"+count+"：</label>\n" +
            "            <select class=\'MorP\' name=\'MorP" + count + "\'>\n" +
            "                <option value=\'1\'>赠送商品</option>\n" +
            "                <option value=\'0\'>减免金额</option>\n" +
            "            </select>\n" +
            "            <span id=\"plc"+ count+ "_text\">"+
            "            满<input type=\'text\' size=\'1\' name=\'p_box"+count+"\'>箱送\n" +
            "            <input type=\'text\' size=\'1\' name=\'box"+count+"\'>箱+\n" +
            "            <input type=\"text\" value=\'0\' size=\"1\" name=\'bottle"+count+"\'>支</span>"+
            "            <button class=\'del_plc_BTN\' name=\'"+count+"\'>删除</button> "+"</div>");
        if(count >2){
            //隐藏上一个删除按钮
            var prev_BTN = "button[name='"+(count-1) +"']";
            var $prev_BTN = $(prev_BTN);
            $prev_BTN.hide();
        }
        //更新策略数目
        $plc_num.attr("value",count);
        //更新常量
        // char_code=char_code+1;
        count=count+1;
        return false;
    });

    //点击删除按钮删除当前策略行，使用事件委派
    $plc_container.on("click",".del_plc_BTN",function () {
       $(this).parent().remove();
       count = count-1;
        if(count >1){
            //显示上一个删除按钮
            var prev_BTN = "button[name='"+(count-1) +"']";
            console.log(prev_BTN);
            var $prev_BTN = $(prev_BTN);
            $prev_BTN.show();
        }
        //更新策略数目
        $plc_num.attr("value",(count-1));
        return false;
    });

    //选择赠送则显示送，选择减钱则显示减，使用事件委派
    //选赠送显示商品、减钱显示元
    $plc_container.on("change","select",function () {
        var MorP_select = $(this).find("option:selected").val();
        var span_text = "#"+$(this).parent().attr("id")+"_text";
        var $span_text = $(span_text);
        var plc_num = $(this).parent().attr("id").substring(3,6);
        console.log(plc_num);
        console.log(span_text);
        console.log(MorP_select);
        $span_text.empty();
         //等于0，减钱
        if (MorP_select == "0") {
            $span_text.append("            每<input type=\'text\' value=\'1\' size=\'1\' name=\'p_box"+plc_num+"'>箱减\n" +
                    "            <input type=\'text\' size=\'1\' name=\'money"+plc_num+"\'>元\n");
        }
        //等于1，赠送
        if (MorP_select == "1") {
            $span_text .append("满<input type=\'text\' size=\'1\' name=\'p_box"+plc_num+"\'>箱送\n" +
                    "            <input type=\'text\' size=\'1\' name=\'box"+plc_num+"\'>箱+\n" +
                    "            <input type=\"text\" value=\'0\' size=\"1\" name=\'bottle"+plc_num+"\'>支");
        }
    });

    //选择多个商品时显示数目
    $isSingle.change(function () {
        var $opt_select = $(this).find("option:selected").val();
        if ($opt_select == "0") {
            $multi_form.show();
            $single_form.hide();
            $pd_amount.show();
            $(this).get_detail($pd1,$spec1,$price1);
        }
        if ($opt_select == "1") {
            $multi_form.hide();
            $pd_amount.hide();
            $single_form.show();
            $amount_num.val("1");
        }
   });

    //根据商品数目添加商品规格
    $amount_num.change(function () {
        var times = parseInt($(this).val());
        $s_std.empty();
        for (var i = 0;i<times;i++){
            $s_std.append(" <label>商品规格：1*</label><input type=\'text\' size=\'1\'><br>");
        }
    });

    //商品详情函数
    $.fn.get_detail=function (JqObj,specN,priceN) {
        var id = JqObj.find("option:selected").val();
        var good_name = JqObj.find("option:selected").html();
        //通过传入ID获得商品信息
        $.get("/goods_detail",{id:id },function (data) {
            //解析JSON
            var jsondata = $.parseJSON(data);
            var spec = jsondata.spec;
            var price = jsondata.price;
            price = price/spec;
            //填入数值
            specN.val(spec);
            priceN.val(price);
            $good_name.val(good_name);
        });
    }

    //select填充商品函数
    $.fn.fill_products = function (JqObj,array) {
        JqObj.empty();
        $.each(array,function (i,v) {
            var temp_array = new Array();
            temp_array = v.split(",");
            JqObj.append("<option value=\""+temp_array[0]+"\">"+temp_array[1]+"</option>");

        });
    }

    //初始化页面信息
    $(this).get_detail($goods,$spec,$price);

});