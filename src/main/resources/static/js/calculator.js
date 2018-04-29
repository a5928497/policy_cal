$(function () {
    var $isSingle =  $("#isSingle");
    var $pd_amount = $("#pd_amount");
    var $amount_num = $("#amount_num");
    var $std = $("#std_container");

    $pd_amount.hide();
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