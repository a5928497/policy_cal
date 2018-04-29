$(function () {
    var $isSingle =  $("#isSingle");
    var $pd_amount = $("#pd_amount");
    var $amount_num = $("#amount_num");
    var $std = $("#std_container");
    var $MorP = $(".MorP");
    var $add_BTN = $("#add_policy");
    var $plc_container = $("#plc_container");
    var count = 2;
    var $plc_num = $("#plc_num");
    var $heightlight = $(".hightlight");
    //初始化页面
    $pd_amount.hide();
    $heightlight.css("color","red").css("font-weight","900");

    //点击添加策略按钮，新增一行策略
    $add_BTN.click(function () {

        $plc_container.append("<div id=\'plc"+count+ "\'>\n"+
            "            <label>策略"+count+"：</label>\n" +
            "            <select class=\'MorP\' name=\'MorP" + count + "\'>\n" +
            "                <option value=\'1\'>赠送商品</option>\n" +
            "                <option value=\'0\'>减免金额</option>\n" +
            "            </select>\n" +
            "            <span id=\"pcl"+ count+ "_text\">"+
            "            <input type=\'text\' size=\'1\'>送\n" +
            "            <input type=\'text\' size=\'1\'>箱+\n" +
            "            <input type=\"text\" size=\"1\">支"+
            "            <button class=\'del_plc_BTN\' name=\'"+count+"\'>删除</button> "+"</span></div>");
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
    })

    //点击删除按钮删除当前策略行，使用时间委派
    $plc_container.on("click",".del_plc_BTN",function () {
       $(this).parent().parent().remove();
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
    })

    //选择赠送则显示送，选择减钱则显示减，使用事件委派
    //选赠送显示商品、减钱显示元
    $plc_container.on("change","select",function () {
        var MorP_select = $(this).find("option:selected").val();
        var span_num = "#"+$(this).parent().attr("id")+"_text";
            // "#statu" + $(this).attr("name").substring(4,7);
        console.log(span_num);
        console.log(MorP_select);
        var $span_text = $(span_num);
        $span_text.empty()
         //等于0，减钱
        if (MorP_select == "0") {
            $span_text.append("            <input type=\'text\' size=\'1\'>减\n" +
                    "            <input type=\'text\' size=\'1\'>元\n" +
                    "            <button class=\'del_plc_BTN\' name=\'"+count+"\'>删除</button> ");
        }
        //等于1，赠送
        if (MorP_select == "1") {
            $span_text .append("            <input type=\'text\' size=\'1\'>送\n" +
                    "            <input type=\'text\' size=\'1\'>箱+\n" +
                    "            <input type=\"text\" size=\"1\">支"+
                    "            <button class=\'del_plc_BTN\' name=\'"+count+"\'>删除</button> ");
        }
    })


    //选择多个商品时显示数目
    $isSingle.change(function () {
        var $opt_select = $(this).find("option:selected").val();
        if ($opt_select == "0") {
            $pd_amount.show();
        }
        if ($opt_select == "1") {
            $pd_amount.hide();
            $amount_num.val("1")
            $std.empty();
            $std.append(" <label>商品规格：1*</label><input type=\'text\' size=\'1\'><br>");
        }
   })

    //根据商品数目添加商品规格
    $amount_num.change(function () {
        var times = parseInt($(this).val());
        $std.empty();
        for (var i = 0;i<times;i++){
            $std.append(" <label>商品规格：1*</label><input type=\'text\' size=\'1\'><br>");
        }
    })
})