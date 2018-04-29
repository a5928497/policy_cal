$(function () {
    var $isSingle =  $("#isSingle");
    var $pd_amount = $("#pd_amount");
    var $amount_num = $("#amount_num");
    var $std = $("#std_container");
    var $MorP = $(".MorP");
    var $add_BTN = $("#add_policy");
    var $plc_container = $("#plc_container");
    var count = 2;
    //初始隐藏
    $pd_amount.hide();

    //点击添加策略按钮，新增一行策略
    $add_BTN.click(function () {

        $plc_container.append("<div id=\'plc"+count+ "\'>\n"+
            "            <label>策略"+count+"：</label>\n" +
            "            <select class=\'MorP\' name=\'MorP" + count + "\'>\n" +
            "                <option value=\'1\'>赠送商品</option>\n" +
            "                <option value=\'0\'>减免金额</option>\n" +
            "            </select>\n" +
            "            <input type=\'text\' size=\'1\'>\n" +
            "            <span id=\'statu"+count+"\'>送</span>\n" +
            "            <input type=\'text\' size=\'1\'>"+
            "            <button class=\'del_plc_BTN\' name=\'"+count+"\'>删除</button> "+"</div>");
        if(count >2){
            //隐藏上一个删除按钮
            var prev_BTN = "button[name='"+(count-1) +"']";
            var $prev_BTN = $(prev_BTN);
            $prev_BTN.hide();
        }

        count=count+1;
        return false;
    })

    //点击删除按钮删除当前策略行
    $plc_container.on("click",".del_plc_BTN",function () {
       $(this).parent().remove();
       count = count-1;
        return false;
    })

    //选择赠送则显示送，选择减钱则显示减，使用事件委派
    $plc_container.on("change","select",function () {
        var $MorP_select = $(this).find("option:selected").val();
        var span_num = "#statu" + $(this).attr("name").substring(4,7);
        console.log(span_num);
        var $span = $(span_num);
        //等于0，减钱
        if ($MorP_select == "0") {
            $span.html("减")
        }
        //等于1，赠送
        if ($MorP_select == "1") {
            $span.html("送")
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