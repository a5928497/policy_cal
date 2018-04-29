$(function () {
    var $isSingle =  $("#isSingle")
    var $pd_amount = $("#pd_amount")

    $pd_amount.hide();
    //选择多个商品时显示数目
    $isSingle.change(function () {
        var $opt_select = $(this).find("option:selected").val();
        if ($opt_select == "0") {
            $pd_amount.show();
        }
        if ($opt_select == "1") {
            $pd_amount.hide();
        }
   })
})