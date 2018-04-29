$(function () {
    var $isSingle =  $("#isSingle");
    var $pd_amount = $("#pd_amount");
    var $amount_num = $("#amount_num");
    var $std = $("#std_container");
    var $MorP = $(".MorP");
    //初始隐藏
    $pd_amount.hide();

    //选择赠送则显示送，选择减钱则显示减
    $MorP.change(function () {
        var $MorP_select = $(this).find("option:selected").val();
        var span_num = "#statu" + $(this).attr("name").substring(4,7);
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